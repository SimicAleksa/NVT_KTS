package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.DTOs.*;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.exception.NotFoundException;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.Ride;
import com.example.nvt_kts_back.models.RegisteredUser;
import com.example.nvt_kts_back.repository.DriverRepository;
import com.example.nvt_kts_back.repository.RouteRepository;
import com.example.nvt_kts_back.repository.RegisteredUserRepository;
import com.example.nvt_kts_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.RideRepository;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    public Ride createRide(Ride ride) { return rideRepository.save(ride);}

    public Ride createRide(Ride ride, long driver_id){
        Ride returnRide = this.rideRepository.save(ride);
        returnRide.setDriver_id(driver_id);
        return rideRepository.save(returnRide);
    }

    public Ride changeRide(long id){
        Ride ride = this.rideRepository.findById(id).orElseThrow(()->
                new NotFoundException("Ride does not exist"));
        ride.setRideState(RideState.ENDED);
        return this.rideRepository.save(ride);
    }

    public Ride changeRideToINPROGRESS(long id){
        Ride ride = this.rideRepository.findById(id).orElseThrow(()->
                new NotFoundException("Ride does not exist"));
        ride.setRideState(RideState.IN_PROGRESS);
        return this.rideRepository.save(ride);
    }

    public List<Ride> getRides(){
        return this.rideRepository.findAllByRideState(RideState.STARTED);
    }

    public List<Ride> getAllRides(){
        return this.rideRepository.findAll();
    }

    public Ride getDriversStartedRide(String id){
        Long temp = Long.parseLong(id);
        Ride ride = new Ride();
        ride.setRideState(RideState.NOT_FOUND);
        return  this.rideRepository.findByDriverAndRideStateSTARTED(temp).orElse(ride);
    }

    public Ride getDriversINPROGRESSRide(String id){
        Long temp = Long.parseLong(id);
        Ride ride = new Ride();
        ride.setRideState(RideState.NOT_FOUND);
        return  this.rideRepository.findByDriverAndRideStateINPROGRESS(temp).orElse(ride);
    }

    public Ride getDriversDrivingToStartRide(String id){
        Long temp = Long.parseLong(id);
        Ride ride = new Ride();
        ride.setRideState(RideState.NOT_FOUND);
        return  this.rideRepository.findByDriverAndRideStateDTS(temp).orElse(ride);
    }

    public void deleteAllRides(){
        this.rideRepository.deleteAll();
    }

    public HashMap<String, HashMap<String, Double>> getDriverReportData(ReportParams params) {
        HashMap<String, HashMap<String, Double>> map = createMapWithDays(params);

        Driver d = this.driverRepository.findByEmail(params.getEmail());
        List<Ride> rides  = d.getHistoryOfRides();
        //HashMap<String, ArrayList<Ride>> map = getReportMap(rides, params);
        map = putValuesInMap(map, rides, params);
        return map;
    }

    private HashMap<String, HashMap<String, Double>> putValuesInMap(HashMap<String, HashMap<String, Double>> map, List<Ride> rides, ReportParams params) {
        for(Ride r:rides)
        {
            if (isInTimePeriod(r,params))
            {
                String key = r.getEndDateTime().toString().substring(0,10);
                map.get(key).put("price", map.get(key).get("price") + r.getPrice());
                map.get(key).put("distance", map.get(key).get("distance") + r.getDistance());
                map.get(key).put("num", map.get(key).get("num") + 1);
            }

        }
        return map;
    }

    private HashMap<String, HashMap<String, Double>> createMapWithDays(ReportParams params) {
        HashMap<String, HashMap<String, Double>> retVal = new HashMap<>();
        String start = params.getStart() + " 00:00";
        String end = params.getEnd() + " 23:59";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(start, formatter1);
        LocalDateTime dateTime2 = LocalDateTime.parse(end, formatter1);

        while(dateTime1.isBefore(dateTime2))
        {
            HashMap<String, Double> map = new HashMap<>();
            map.put("price", 0.0);
            map.put("distance", 0.0);
            map.put("num", 0.0);
            retVal.put(dateTime1.toString().substring(0,10), map);
            dateTime1 = dateTime1.plusDays(1);
        }
        return retVal;
    }

    private HashMap<String, ArrayList<Ride>> getReportMap(List<Ride> rides, ReportParams params) {
        HashMap<String, ArrayList<Ride>> retVal = new HashMap<>();
        for (Ride r:rides)
        {
            if (isInTimePeriod(r,params))
            {
                String key = r.getEndDateTime().toString().substring(0,10);
                if (retVal.containsKey(key))
                {
                    retVal.get(key).add(r);
                }
                else
                {
                    ArrayList<Ride> list = new ArrayList<>();
                    list.add(r);
                    retVal.put(key, list);
                }
            }
        }
        return retVal;
    }

    private boolean isInTimePeriod(Ride r, ReportParams params) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
        String start = params.getStart() + " 00:00";
        String end = params.getEnd() + " 23:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(start, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);
        return r.getEndDateTime().isAfter(startDate) && r.getEndDateTime().isBefore(endDateTime);
    }

    public List<Ride> findAllInProgressAndDTS() {
        return this.rideRepository.findAllInProgressAndDTSRides();
    }

    public HashMap<String, HashMap<String, Double>> getUserReportData(ReportParams params) {
        HashMap<String, HashMap<String, Double>> map = createMapWithDays(params);
        RegisteredUser u = this.registeredUserRepository.findByEmail(params.getEmail());
        List<Ride> rides  = u.getHistoryOfRides();
        map = putValuesInMap(map, rides, params);
        return map;
    }

    public void saveRide(Ride ride){
        this.rideRepository.save(ride);
    }

    public HashMap<String, HashMap<String, Double>> getAdminReportData(ReportParams params) {
        HashMap<String, HashMap<String, Double>> map = createMapWithDays(params);
        List<Ride> rides  = this.rideRepository.findAll();
        map = putValuesInMap(map, rides, params);
        return map;
    }

    public ArrayList<RideNotificationDTO> findDriversUpcomingRides(String email) {
        Driver d= this.driverRepository.findByEmail(email);
        List<Ride> rides = rideRepository.findDriversUpcomingRides(d.getId());
        ArrayList<RideNotificationDTO> retVal = new ArrayList<>();
        for(Ride r: rides)
        {
            retVal.add(new RideNotificationDTO(r));
        }
        return retVal;
    }

    public void changeRideState(Long id, String state) {
        Ride r = rideRepository.findById(id).get();
        r.setRideState(RideState.valueOf(state));
        rideRepository.save(r);
    }

    public ArrayList<RideNotificationDTO> finUsersUpcomingRides(String email) {
        RegisteredUser ru = this.registeredUserRepository.findByEmail(email);
        List<Ride> rides = ru.getHistoryOfRides();
        ArrayList<RideNotificationDTO> retVal = new ArrayList<>();
        for(Ride r: rides)
        {
            if (r.getRideState()==RideState.IN_PROGRESS || r.getRideState()==RideState.STARTED ||
                    r.getRideState()==RideState.SCHEDULED || r.getRideState()==RideState.WAITING_FOR_PAYMENT ||
                    r.getRideState()==RideState.RESERVED) {
                retVal.add(new RideNotificationDTO(r));
                if(r.getRideState()==RideState.STARTED){
                    retVal.add(new RideNotificationDTO(this.getDriversDrivingToStartRide(String.valueOf(r.getDriver_id()))));
                }
            }
        }
        Collections.sort(retVal, Comparator.comparing(RideNotificationDTO::getStartDateTime));
        return retVal;
    }


    public RideDTO getUsersDTSride(String email) {
        RegisteredUser ru = this.registeredUserRepository.findByEmail(email);
        List<Ride> rides = ru.getHistoryOfRides();
        RideDTO retVal=new RideDTO();
        for(Ride r: rides)
        {
            if (r.getRideState()==RideState.STARTED){
                retVal.setExpectedDuration(r.getExpectedDuration());
                retVal.setDriver(r.getDriver_id());
                retVal.setRoute(new RouteDTO(r.getRoute()));
                retVal.setId(r.getId());
                retVal.setRideState(r.getRideState());
                break;
            }
        }
        Ride rrrride = this.getDriversDrivingToStartRide(String.valueOf(retVal.getId()));
        RideDTO returnRideDto = new RideDTO(rrrride);
        returnRideDto.setExpectedDuration(rrrride.getExpectedDuration());

        return returnRideDto;
    }

    public RideDTO getUsersInProgresssRide(String email) {
        RegisteredUser ru = this.registeredUserRepository.findByEmail(email);
        List<Ride> rides = ru.getHistoryOfRides();
        RideDTO retVal=new RideDTO();
        for(Ride r: rides)
        {
            if (r.getRideState()==RideState.IN_PROGRESS){
                retVal.setExpectedDuration(r.getExpectedDuration());
                retVal.setDriver(r.getDriver_id());
                retVal.setRoute(new RouteDTO(r.getRoute()));
                retVal.setId(r.getId());
                retVal.setRideState(r.getRideState());
                break;
            }
        }
        return retVal;
    }


    // ova funkcija ce za zadati ride da pronadje sve potencijane vozace i da ih sortira po blizini
    public void findDriverList(DataForRideFromFromDTO rideDTO)
    {
        // prvo cu naci sve prijavljene vozace koji imaju ovaj tip vozila i ljubimce i bebe
        ArrayList<Driver> activeFilteredDrivers = findActiveFilteredDrivers(rideDTO);
        // slucaj kada nema nijednog
        if (activeFilteredDrivers.size()==0)
        {
            // TODO salji odgovor da se voznja automatski odbija
            return;
        }
        // sad uzimam one koji nemaju nista
        ArrayList<Driver> freeNow =  filterFreeNow(activeFilteredDrivers);
        if (freeNow.size()>0)
        {
            // TODO ovdje treba sortirati vozace po udaljenosti od pocetka voznje
            freeNow = sortByDistance(freeNow, rideDTO);
            System.out.println("Nasla sam nekoga ko nema ni sad ni kasnije");
            return;
        }
        ArrayList<Driver> freeAfter = filterFreeAfter(activeFilteredDrivers);
        if (freeAfter.size()>0)
        {
            // TODO sada treba dodijeliti onoga koji ce najranije zavrsiti
            Driver best = sortByEndTime(freeAfter);
            System.out.println("Nasla sam nekoga ko ima sad, ali nema kasnije");
            return;
        }
        // posljednji slucaj je da svi imaju i sada i kasnije
        // TODO salji odgovor da se voznja automatski odbija
        System.out.println("Odbijamo voznju jer svi imaju i sada i kasnije");
    }

    private Driver sortByEndTime(ArrayList<Driver> freeAfter) {
        LocalDateTime min = LocalDateTime.now().plusYears(1);
        Driver best = freeAfter.get(0);
        for (Driver d : freeAfter)
        {
            Ride r = d.findCurrentRide();
            LocalDateTime l= r.getStartDateTime().plusMinutes(r.getExpectedDuration());
            if (l.isBefore(min))
            {
                min = l;
                best = d;
            }
        }
        return best;

    }

    private ArrayList<Driver> sortByDistance(ArrayList<Driver> freeNow, DataForRideFromFromDTO rideDTO) {
        for (int i = 0; i < freeNow.size()-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < freeNow.size(); j++)
                if (freeNow.get(j).getDistanceFromCoordDTO(rideDTO.getRoute().getStartLocation()) < freeNow.get(min_idx).getDistanceFromCoordDTO(rideDTO.getRoute().getStartLocation()))
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            Driver temp = freeNow.get(min_idx);
            freeNow.set(min_idx, freeNow.get(i));
            freeNow.set(i, temp);
        }
        return freeNow;
    }

    private ArrayList<Driver> filterFreeAfter(ArrayList<Driver> activeFilteredDrivers) {
        // sad treba proci kroz vozace i uzeti samo one koji nemaju scheduled
        ArrayList<Driver> retVal = new ArrayList<>();
        for (Driver d : activeFilteredDrivers)
        {
            if (!d.hasScheduled())
            {
                retVal.add(d);
            }
        }
        return retVal;
    }

    private ArrayList<Driver> filterFreeNow(ArrayList<Driver> activeFilteredDrivers) {
        // sad treba proci kroz vozace i uzeti samo one koji nemaju ni trenutnu voznju, ni scheduled
        ArrayList<Driver> retVal = new ArrayList<>();
        for (Driver d : activeFilteredDrivers)
        {
            if (!d.hasScheduledOrCurrentRides())
            {
                retVal.add(d);
            }
        }
        return retVal;
    }

    private void printDriversDebug(ArrayList<Driver> activeFilteredDrivers) {
        for (Driver d : activeFilteredDrivers)
        {
            System.out.println(d.getEmail());
        }
    }

    private ArrayList<Driver> findActiveFilteredDrivers(DataForRideFromFromDTO rideDTO) {
        ArrayList<Driver> retVal = new ArrayList<>();
        ArrayList<Driver> drivers = this.driverRepository.findDriversByPetBabyActive(rideDTO.isBabyAllowed(), rideDTO.isPetAllowed());
        // sad treba vidjeti da li imaju tip vozila
        for (Driver d : drivers)
        {
            if (rideDTO.getCarTypes().contains(d.getCarTypeString()))
                retVal.add(d);
        }
        return retVal;
    }

    public List<RegisteredUser> getLinkedPassangersFromStringArray(List<String> linkedPassengers,Ride ride) {
        List<RegisteredUser> registeredUsers = new ArrayList<>();
        for(String passEmail : linkedPassengers){
            registeredUsers.add(this.registeredUserRepository.findByEmail(passEmail));
            RideNotificationDTO dto = new RideNotificationDTO(ride,passEmail);
            this.simpMessagingTemplate.convertAndSend("/map-updates/ride-notification", dto);
        }
        return registeredUsers;
    }
}
