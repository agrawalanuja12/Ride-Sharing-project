package com.rideshare.controller;

import com.rideshare.dto.RideRequest;
import com.rideshare.dto.RideBookingResponse;
import com.rideshare.model.Ride;
import com.rideshare.repository.RideRepository;
import com.rideshare.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rides")
@CrossOrigin(origins = "http://localhost:3000")
public class RideController {

    @Autowired
    private RideService rideService;

    @Autowired
    private RideRepository rideRepository;

    // Book a Ride
    @PostMapping("/book")
    public RideBookingResponse bookRide(@RequestBody RideRequest request) {
        try {
            Ride ride = new Ride();
            ride.setSource(request.getPickupLocation());
            ride.setDestination(request.getDropLocation());
            ride.setUserEmail(request.getUserEmail());
            ride.setUserLat(request.getUserLat());
            ride.setUserLng(request.getUserLng());

            // Let the service handle driver assignment, vehicle, fare, etc.
            Ride bookedRide = rideService.bookRide(ride);

            return new RideBookingResponse(
                    "Ride Confirmed! Pay with Cash on Delivery.",
                    bookedRide.getDriverName(),
                    bookedRide.getFare(),
                    bookedRide.getVehicleNo(),
                    bookedRide.getId()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new RideBookingResponse(
                    "Ride booking failed", null, 0, null, null
            );
        }
    }

    // Mark ride as completed
    @PutMapping("/{rideId}/complete")
    public ResponseEntity<String> completeRide(@PathVariable Long rideId) {
        Optional<Ride> optionalRide = rideRepository.findById(rideId);
        if (optionalRide.isPresent()) {
            Ride ride = optionalRide.get();
            ride.setCompleted(true);
            rideRepository.save(ride);
            return ResponseEntity.ok("Ride marked as completed.");
        }
        return ResponseEntity.badRequest().body("Ride not found.");
    }

    // Confirm Payment (Cash on Delivery)
    @PatchMapping("/{rideId}/payment-success")
    public ResponseEntity<String> confirmPayment(@PathVariable Long rideId) {
        Optional<Ride> optionalRide = rideRepository.findById(rideId);
        if (optionalRide.isPresent()) {
            Ride ride = optionalRide.get();
            ride.setPaid(true);
            ride.setPaymentMethod("Cash on Delivery");
            rideRepository.save(ride);
            return ResponseEntity.ok("Payment successful (Cash on Delivery) and ride marked as paid.");
        }
        return ResponseEntity.badRequest().body("Ride not found.");
    }
}
