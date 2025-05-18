package com.rideshare.service;

import com.rideshare.model.Ride;
import com.rideshare.repository.RideRepository;
import com.rideshare.utils.DijkstraAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    // Book a new ride
    public Ride bookRide(Ride ride) {
        ride.setDriverName(assignDriver());
        ride.setVehicleNo(generateVehicleNo());
        ride.setFare(calculateFare(ride.getSource(), ride.getDestination()));
        ride.setPaymentMethod("Cash on Delivery");
        ride.setCompleted(false);
        ride.setPaid(false);
        ride.setStatus("CONFIRMED");

        return rideRepository.save(ride);
    }

    // Assign a random driver to the ride
    private String assignDriver() {
        String[] drivers = {"Rajesh Kumar", "Anil Verma", "Sunita Singh"};
        return drivers[new Random().nextInt(drivers.length)];
    }

    // Generate a random vehicle number
    private String generateVehicleNo() {
        String[] vehicles = {"DL01AB1234", "MH12CD5678", "KA05EF9012"};
        return vehicles[new Random().nextInt(vehicles.length)];
    }

    // Calculate fare using Dijkstra's algorithm
    private double calculateFare(String source, String destination) {
        Map<String, Map<String, Integer>> graph = Map.of(
            "Delhi", Map.of("Noida", 15, "Gurgaon", 20),
            "Noida", Map.of("Delhi", 15, "Ghaziabad", 10),
            "Gurgaon", Map.of("Delhi", 20, "Faridabad", 25),
            "Ghaziabad", Map.of("Noida", 10),
            "Faridabad", Map.of("Gurgaon", 25)
        );

        Map<String, Integer> distances = DijkstraAlgorithm.dijkstra(graph, source);
        int shortestDistance = distances.getOrDefault(destination, 0);

        return shortestDistance > 0 ? shortestDistance * 10 : 150;
    }

    //  Mark ride as completed
    public String completeRide(Long rideId) {
        Ride ride = rideRepository.findById(rideId).orElseThrow();
        ride.setStatus("COMPLETED");
        ride.setCompleted(true);
        rideRepository.save(ride);
        return "Ride Completed!";
    }

    // Optional: update ride location (for live tracking)
    public void updateRideLocation(Long rideId, String location) {
        // Implementation goes here
    }

    // Optional: payment initiation (e.g., Razorpay)
    public String initiatePayment(Long rideId) {
        return "Payment Initiated!";
    }
}
