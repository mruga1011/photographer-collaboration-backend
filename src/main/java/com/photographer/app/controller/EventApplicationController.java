package com.photographer.app.controller;

import com.photographer.app.entity.EventApplication;
import com.photographer.app.service.EventApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class EventApplicationController {

    @Autowired
    private EventApplicationService eventApplicationService;

    @PostMapping("/apply/{eventId}")
    public EventApplication applyEvent(@PathVariable Long eventId, @RequestBody EventApplication eventApplication){
        return eventApplicationService.applyForEvent(eventId,eventApplication);
    }


}
