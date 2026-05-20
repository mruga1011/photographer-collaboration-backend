package com.photographer.app.controller;

import com.photographer.app.entity.Event;
import com.photographer.app.entity.User;
import com.photographer.app.exception.CustomException;
import com.photographer.app.repository.EventRepository;
import com.photographer.app.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/create")
    public Event creatEvent(@RequestBody Event event, Authentication authentication){
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new CustomException("User not found"));

        event.setUser(user);
        return eventRepository.save(event);
    }

    @GetMapping("/eventList")
    public List<Event> getAllEvents(){
        return eventRepository.findAll();


    }

}
