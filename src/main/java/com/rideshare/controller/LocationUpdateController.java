package com.rideshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import com.rideshare.model.LocationDTO;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LocationUpdateController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/location/update")
    public void updateLocation(@RequestBody LocationDTO location) {
        messagingTemplate.convertAndSend(
            "/topic/ride-tracking/" + location.getRideId(),
            location
        );
    }
}
