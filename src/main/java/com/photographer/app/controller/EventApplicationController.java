package com.photographer.app.controller;

import com.photographer.app.entity.EventApplication;
import com.photographer.app.service.EventApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class EventApplicationController {

    @Autowired
    private EventApplicationService eventApplicationService;

    @PostMapping("/apply/{eventId}")
    public EventApplication applyEvent(@PathVariable Long eventId, @RequestBody EventApplication eventApplication){
        return eventApplicationService.applyForEvent(eventId,eventApplication);
    }

    @GetMapping("/event/{eventId}")
    public List<EventApplication> getApplication(@PathVariable Long eventId){
        return eventApplicationService.getApplicationByEvent(eventId);

    }

    @PutMapping("{applicationId}/status")
    public EventApplication updateStatus(@PathVariable Long applicationId, @RequestParam String status, Authentication authentication){
        String email = authentication.getName();
        return eventApplicationService.updateStatus(applicationId,status,email);

    }


}
