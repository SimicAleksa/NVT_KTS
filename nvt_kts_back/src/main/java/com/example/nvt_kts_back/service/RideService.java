package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.*;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.exception.NotFoundException;
import com.example.nvt_kts_back.exception.RegisteredUserNotFound;
import com.example.nvt_kts_back.exception.RideNotFoundException;
import com.example.nvt_kts_back.models.*;
import com.example.nvt_kts_back.utils.mappers.EntityToDTOMapper;
import com.example.nvt_kts_back.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.RideRepository;

import java.util.Collections;
import java.util.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import static com.example.nvt_kts_back.service.DriverService.findActiveMinutes;

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
    private DataForRideFromFromRepository dataForRideFromFromRepository;


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    public Ride createRide(Ride ride) { return rideRepository.save(ride);}

    public Ride createRide(Ride ride, long driver_id){
        Ride returnRide = this.rideRepository.save(ride);
        returnRide.setDriver_id(driver_id);
        return rideRepository.save(returnRide);
    }

    // TODO Nevena (odradjeno)
    public Ride changeRide(long id){
        Ride ride = this.rideRepository.findById(id).orElseThrow(()->
                new NotFoundException("Ride does not exist"));
        ride.setRideState(RideState.ENDED);
        return this.rideRepository.save(ride);
    }

    // TODO Nevena (odradjeno)
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

    // TODO DODATNO ALEKSA (odradjeno)
    public Ride getDriversStartedRide(String id){
        Long temp = Long.parseLong(id);
        Ride ride = new Ride();
        ride.setRideState(RideState.NOT_FOUND);
        return this.rideRepository.findByDriverAndRideStateSTARTED(temp).orElse(ride);
    }

    //TODO Nevena (odradjeno)
    public Ride getDriversINPROGRESSRide(String id){
        Long temp = Long.parseLong(id);
        Ride ride = new Ride();
        ride.setRideState(RideState.NOT_FOUND);
        return this.rideRepository.findByDriverAndRideStateINPROGRESS(temp).orElse(ride);
    }

    // TODO ODBIJANJE voznje - jedninicni (odradjeno)
    public Ride getDriversDrivingToStartRide(String id){
        Long temp = Long.parseLong(id);
        Ride ride = new Ride();
        ride.setRideState(RideState.NOT_FOUND);
        return this.rideRepository.findByDriverAndRideStateDTS(temp).orElse(ride);
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
            if (r.getRideState().equals(RideState.ENDED) && isInTimePeriod(r,params))
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

    // TODO DODATNO ALEKSA (odradjeno) (samo u repo)
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

    // TODO DODATNO ALEKSA (odradjeno)
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

    //TODO ODBIJANJE voznje - jedninicni
    public Ride changeRideState(Long id, String state) {
        Ride r = rideRepository.findById(id).orElseThrow(RideNotFoundException::new);
        r.setRideState(RideState.valueOf(state));
        if (state.equals(RideState.IN_PROGRESS.toString()))
        {
            r.setStartDateTime(LocalDateTime.now());
        }
        return rideRepository.save(r);
    }

    //TODO ODBIJANJE voznje - jedinicni
    public ArrayList<RideNotificationDTO> findUsersUpcomingRides(String email) {
        RegisteredUser ru = this.registeredUserRepository.findByEmail(email);
        if(ru==null) throw new RegisteredUserNotFound("Registered user not found");
        List<Ride> rides = ru.getHistoryOfRides();
        ArrayList<RideNotificationDTO> retVal = new ArrayList<>();
        for(Ride r: rides)
        {
            if (r.getRideState()== RideState.IN_PROGRESS || r.getRideState()== RideState.STARTED ||
                    r.getRideState()== RideState.WAITING_FOR_PAYMENT ||
                    r.getRideState()== RideState.RESERVED) {
                RideNotificationDTO dto = new RideNotificationDTO(r);
                retVal.add(dto);
                if(r.getRideState()== RideState.STARTED){
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
            if (r.getRideState()== RideState.STARTED){
                retVal.setExpectedDuration(r.getExpectedDuration());
                retVal.setDriver(r.getDriver_id());
                retVal.setRoute(new RouteDTO(r.getRoute()));
                retVal.setId(r.getId());
                retVal.setRideState(r.getRideState());
                break;
            }
        }
        Ride rrrride = this.getDriversDrivingToStartRide(String.valueOf(retVal.getDriver()));
        RideDTO returnRideDto = new RideDTO(rrrride);
        returnRideDto.setExpectedDuration(rrrride.getExpectedDuration());

        return returnRideDto;
    }

    // TODO DODATNO ALEKSA (odradjeno)
    public RideDTO getUsersInProgresssRide(String email) {
        RegisteredUser ru = this.registeredUserRepository.findByEmail(email);
        List<Ride> rides = ru.getHistoryOfRides();
        RideDTO retVal=new RideDTO();
        for(Ride r: rides)
        {
            if (r.getRideState()== RideState.IN_PROGRESS){
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


    // TODO NEVENA - nema sta da se testira, samo su pozivi za dalje
    public Driver findDriver(DataForRideFromFrom rideDTO) {
        deactivateDrivers();
        if (rideDTO.getDateTime()==null || rideDTO.getDateTime().equals("")) {
            return findDriverForNow(rideDTO);
        }
        return findAnyDriver(rideDTO);
    }

    // TODO NEVENA - ovdje isto nema sta, ali sam testirala ostale
    public void deactivateDrivers()
    {
        for (Driver d : this.driverRepository.findAll())
        {
            if (findActiveMinutes(d.getActiveTime())>480)
            {
                d.setActive(false);
                this.driverRepository.save(d);
            }
        }
    }


    // TODO NEVENA
    private Driver findAnyDriver(DataForRideFromFrom rideDTO) {
        ArrayList<Driver> activeFilteredDrivers = findActiveFilteredDrivers(rideDTO);
        if (activeFilteredDrivers.size()==0)
        {
            return null;
        }
        // sada trazim drivera koji u rezervisanom periodu nema zakazanu nijednu voznju
        return findOneFreeAtReservedTime(activeFilteredDrivers, rideDTO);
    }

    // TODO NEVENA
    private Driver findOneFreeAtReservedTime(ArrayList<Driver> activeFilteredDrivers, DataForRideFromFrom rideDto) {
        LocalDateTime rideStart = getRideStartFromString(rideDto.getDateTime());

        LocalDateTime rideEnd = rideStart.plusMinutes(rideDto.getDuration());
        //outerloop:
        for (Driver d: activeFilteredDrivers)
        {
            List<Ride> rides = d.getHistoryOfRides();
            boolean free = true;
            for (Ride r : rides)
            {
                if (r.getRideState()== RideState.RESERVED)
                {
                    LocalDateTime existedStart = r.getStartDateTime();
                    LocalDateTime existedEnd = existedStart.plusMinutes(r.getExpectedDuration());
                    if (!((existedEnd.isBefore(rideStart) || rideEnd.isBefore(existedStart))))
                    {
                        // nista ne treba da se desi
                        free = false;
                        break;
                    }
                }

            }
            // sad smo obisli sve rideove
            if (free)
            {
                return d;
            }
        }
        return null;
    }

    private LocalDateTime getRideStartFromString(String dateTime) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String start = dateTime.replace('T', ' ');
        if (start.length()>=16)
        {
            start = start.substring(0, 16);
        }
        return LocalDateTime.parse(start, formatter1);
    }


    // TODO NEVENA
    public Driver findDriverForNow(DataForRideFromFrom rideDTO)
    {
        // prvo cu naci sve prijavljene vozace koji imaju ovaj tip vozila i ljubimce i bebe
        ArrayList<Driver> activeFilteredDrivers = findActiveFilteredDrivers(rideDTO);
        // slucaj kada nema nijednog
        if (activeFilteredDrivers.size()==0)
        {
            return null;
        }
        // sad uzimam one koji nemaju nista
        ArrayList<Driver> freeNow =  filterFreeNow(activeFilteredDrivers);
        if (freeNow.size()>0)
        {
            // freeNow = sortByDistance(freeNow, rideDTO);
            System.out.println("Nasla sam nekoga ko nema ni sad ni kasnije");
            return findNearestDriver(freeNow, rideDTO);

        }
        ArrayList<Driver> freeAfter = filterFreeAfter(activeFilteredDrivers);
        if (freeAfter.size()>0)
        {
            Driver best = sortByEndTime(freeAfter);
            System.out.println("Nasla sam nekoga ko ima sad, ali nema kasnije");
            return best;
        }
        // posljednji slucaj je da svi imaju i sada i kasnije
        System.out.println("Odbijamo voznju jer svi imaju i sada i kasnije");
        return null;
    }

    // TODO NEVENA
    private Driver findNearestDriver(ArrayList<Driver> freeNow, DataForRideFromFrom rideDTO) {
        double minDistance = Double.POSITIVE_INFINITY;
        Driver best = freeNow.get(0);
        for (Driver d: freeNow)
        {
            double dist = d.getDistanceFromCoordDTO(rideDTO.getStartLatitude(), rideDTO.getStartLongitude());
            if(dist < minDistance)
            {
                minDistance = dist;
                best = d;
            }
        }
        return best;

    }

    // TODO NEVENA
    private Driver sortByEndTime(ArrayList<Driver> freeAfter) {
        LocalDateTime min = LocalDateTime.now().plusYears(1);
        Driver best = freeAfter.get(0);
        for (Driver d : freeAfter)
        {
            Ride r = d.findCurrentRide();
            LocalDateTime l= r.getStartDateTime().plusSeconds(r.getExpectedDuration());
            if (l.isBefore(min))
            {
                min = l;
                best = d;
            }
        }
        return best;

    }

    // TODO ZAKAZIVANJE NEVENA
    /*private ArrayList<Driver> sortByDistance(ArrayList<Driver> freeNow, DataForRideFromFrom rideDTO) {
        for (int i = 0; i < freeNow.size()-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < freeNow.size(); j++)
                if (freeNow.get(j).getDistanceFromCoordDTO(rideDTO.getStartLatitude(), rideDTO.getStartLongitude()) < freeNow.get(min_idx).getDistanceFromCoordDTO(rideDTO.getStartLatitude(), rideDTO.getStartLongitude()))
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            Driver temp = freeNow.get(min_idx);
            freeNow.set(min_idx, freeNow.get(i));
            freeNow.set(i, temp);
        }
        return freeNow;
    }*/

    // TODO NEVENA - preuzeto (odradjeno)
    private ArrayList<Driver> filterFreeAfter(ArrayList<Driver> activeFilteredDrivers) {
        // sad treba proci kroz vozace i uzeti samo one koji nemaju scheduled
        ArrayList<Driver> retVal = new ArrayList<>();
        for (Driver d : activeFilteredDrivers)
        {
            if (!d.hasStarted())
            {
                retVal.add(d);
            }
        }
        return retVal;
    }

    // TODO zakazivanje NEVENA - preuzeto (odradjeno)
    private ArrayList<Driver> filterFreeNow(ArrayList<Driver> activeFilteredDrivers) {
        // sad treba proci kroz vozace i uzeti samo one koji nemaju ni trenutnu voznju, ni scheduled
        ArrayList<Driver> retVal = new ArrayList<>();
        for (Driver d : activeFilteredDrivers)
        {
            if (!d.hasStartedOrCurrentRides())
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

    //TODO ZAKAZIVANJE voznje - Jedninicni (odradjen) | hajde ponovo (odradjen ponovo)
    public List<RegisteredUser> getLinkedPassangersFromStringArray(List<String> linkedPassengers,Ride ride) throws Exception{
        List<RegisteredUser> registeredUsers = new ArrayList<>();
        for(String passEmail : linkedPassengers){
            RegisteredUser registeredUser = this.registeredUserRepository.findByEmail(passEmail);
            if(registeredUser==null){
                throw new RegisteredUserNotFound("Registered user not found");
            }
            else {
                if(!registeredUser.getIsBlocked() && registeredUser.isEnabled()){
                    registeredUsers.add(registeredUser);
                    RideNotificationDTO dto = new RideNotificationDTO(ride, passEmail);
                    this.simpMessagingTemplate.convertAndSend("/map-updates/ride-notification", dto);
                }
            }
        }
        return registeredUsers;
    }

    // TODO zakazivanje NEVENA
    private ArrayList<Driver> findActiveFilteredDrivers(DataForRideFromFrom rideDTO) {
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

    // TODO zakazivanje NEVENA
    public boolean tryAcceptRideUser(Long id, String email) {
        RegisteredUser user = registeredUserRepository.findByEmail(email);
        Ride ride = rideRepository.findById(id).get();
        if (user.getTokens() > ride.getPrice()/ride.getPassengers().size())
        {
            return acceptRide(id, email);
        }
        return declineRide(id);
    }

    // TODO zakazivanje NEVENA - preuzeto (odradjeno)
    // Metoda vraca false nakon sto odbije voznju
    private boolean declineRide(Long id) {
        Ride ride = rideRepository.findById(id).get();
        ride.setRideState(RideState.DECLINED);
        rideRepository.save(ride);
        return false;
    }

    // TODO zakazivanje NEVENA
    private boolean acceptRide(Long id, String email) {
        Ride ride = rideRepository.findById(id).orElseThrow(RideNotFoundException:: new);
        String old = ride.getApprovedBy();
        if (old==null || old.equals(""))
        {
            ride.setApprovedBy(email);
        }
        else
        {
            ride.setApprovedBy(ride.getApprovedBy()+";" + email);
        }
        if (ride.getApprovedBy()!=null && !ride.getApprovedBy().equals("") && ride.getApprovedBy().split(";").length == ride.getPassengers().size())
        {
            //System.out.println(ride.getId() + " je prethhodno sacuvani ID");
            //System.out.println(this.dataForRideFromFromRepository.findById(ride.getId()).get() + " je cijeli objekat ");
            DataForRideFromFrom dataDto = this.dataForRideFromFromRepository.findById(ride.getId()).get();
            Driver d = findDriver(dataDto);
            if (d==null)
            {
                // treba poslati da je stanje voznje odbijena
                ride.setRideState(RideState.DECLINED);
                this.simpMessagingTemplate.convertAndSend("/map-updates/no-drivers-available", new StringDTO(id));
            }
            else {
                // postaviti stanje na STARTED
                ride.setRideState(findNextState(ride.getStartDateTime()));
                // postaviti drivera
                ride.setDriver_id(d.getId());
                // treba poslati svima da je voznja prihvacena
                this.simpMessagingTemplate.convertAndSend("/map-updates/everyone-approved", new StringDTO(id));
                // TODO placanje   Probacu da prebacim u drugu, ako ne bude islo, vraticu
                payRide(ride);
            }
        }
        else {
            // ovo znaci da je ovaj nas potvrdio, ali da ostali nisu
            // stanje je valjda vec WAITING_FOR_PAYMENT
        }
        rideRepository.save(ride);
        return true;
    }

    private RideState findNextState(LocalDateTime startDateTime) {
        if (startDateTime!=null) return RideState.RESERVED;
        return RideState.STARTED;
    }

    // TODO zakazivanje NEVENA
    private void payRide(Ride ride) {
        double price = ride.getPrice();
        for (RegisteredUser ru : ride.getPassengers())
        {
            ru.setTokens(ru.getTokens() - price/ride.getPassengers().size());
            this.userRepository.save(ru);
        }
    }

    public List<UserRideHistoryDTO> getRideHistoryForRegisteredUser(Long userId) {
        List<UserRideHistoryDTO> rideHistory = new ArrayList<>();
        for (Ride ride : rideRepository.findAllThatEndedByPassengerId(userId))
            rideHistory.add(new UserRideHistoryDTO(
                    EntityToDTOMapper.mapRideToRideHistoryInfoDTO(ride),
                    EntityToDTOMapper.mapDriverToDriverInfoForRideHistoryDTO(
                            driverRepository.getReferenceById(ride.getDriver_id())
                    )
                )
            );

        return rideHistory;
    }

    public boolean userHadRideWitGivenDriverInLast3Days(Long passengerId, Long driverId) {
        return rideRepository.findThatEndedByPassengerIdAndDriverIdAndDate(
                passengerId, driverId, LocalDateTime.now().minusDays(3), LocalDateTime.now()
        ).size() > 0;
    }

    public List<DriverRideHistoryDTO> getRideHistoryForDriver(Long driverId) {
        List<DriverRideHistoryDTO> rideHistory = new ArrayList<>();
        for (Ride ride : rideRepository.findAllThatEndedByDriverId(driverId))
            rideHistory.add(new DriverRideHistoryDTO(
                            EntityToDTOMapper.mapRideToRideHistoryInfoDTO(ride),
                            ride.getPassengers().stream().map(
                                            EntityToDTOMapper::mapUserToRegUserInfoForRideHistoryDTO
                            ).collect(Collectors.toList())
                        )
                );

        return rideHistory;
    }

    public List<RideHistoryForAdminDTO> getRideHistoryForAdmin(String usrEmail) {
        User usr = userRepository.getByEmail(usrEmail).orElseThrow(UserDoesNotExistException::new);
        String roleName = usr.getRole().getName();

        List<Ride> rides = new ArrayList<>();
        if (roleName.equals(Settings.USER_ROLE_NAME))
            rides = rideRepository.findAllThatEndedByPassengerId(usr.getId());
        else if (roleName.equals(Settings.DRIVER_ROLE_NAME))
            rides = rideRepository.findAllThatEndedByDriverId(usr.getId());

        List<RideHistoryForAdminDTO> rideHistory = new ArrayList<>();
        for (Ride ride : rides)
            rideHistory.add(new RideHistoryForAdminDTO(
                        EntityToDTOMapper.mapRideToRideHistoryInfoDTO(ride),
                        EntityToDTOMapper.mapUserToUserInfoForAdminRideHistoryDTO(usr)
                    )
            );

        return rideHistory;
    }

}
