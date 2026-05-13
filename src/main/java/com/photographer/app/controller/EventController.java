package com.photographer.app.controller;

import com.photographer.app.entity.Event;
import com.photographer.app.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/create")
    public Event creatEvent(@RequestBody Event event){
        return eventRepository.save(event);
    }

    @GetMapping("/eventList")
    public List<Event> getAllEvents(){
        return eventRepository.findAll();


    }

}
