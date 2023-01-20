package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.exception.NotFoundException;
import com.example.nvt_kts_back.models.Ride;
import com.example.nvt_kts_back.repository.DriverRepository;
import com.example.nvt_kts_back.repository.RouteRepository;
import com.example.nvt_kts_back.repository.UserRepository;
import com.example.nvt_kts_back.DTOs.ReportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.RideRepository;

import java.util.List;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    DriverRepository driverRepository;

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

    public void deleteAllRides(){
        this.rideRepository.deleteAll();
    }

//    public HashMap<String, HashMap<String, Double>> getDriverReportData(ReportParams params) {
//        HashMap<String, HashMap<String, Double>> map = createMapWithDays(params);
//        List<Ride> rides  = rideRepository.findByDriverEmail(params.getEmail());
//        //HashMap<String, ArrayList<Ride>> map = getReportMap(rides, params);
//        map = putValuesInMap(map, rides);
//        return map;
//    }

    private HashMap<String, HashMap<String, Double>> putValuesInMap(HashMap<String, HashMap<String, Double>> map, List<Ride> rides) {
        for(Ride r:rides)
        {
            String key = r.getEndDateTime().toString().substring(0,10);
            map.get(key).put("price", map.get(key).get("price") + r.getPrice());
            map.get(key).put("distance", map.get(key).get("distance") + r.getDistance());
            map.get(key).put("num", map.get(key).get("num") + 1);
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
}
