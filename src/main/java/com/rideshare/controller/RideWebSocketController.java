package com.rideshare.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.rideshare.model.RideStatusUpdate;

@Controller
public class RideWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public RideWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/ride/update")
    public void sendRideUpdate(RideStatusUpdate update) {
        messagingTemplate.convertAndSend("/topic/rideUpdates", update); // Sending update to subscribed clients
    }
}
