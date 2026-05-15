package com.photographer.app.service;

import com.photographer.app.entity.Event;
import com.photographer.app.entity.EventApplication;
import com.photographer.app.exception.CustomException;
import com.photographer.app.repository.EventApplicationRepository;
import com.photographer.app.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventApplicationService {

    @Autowired
    private EventApplicationRepository eventApplicationRepository;

    @Autowired
    private EventRepository eventRepository;

    public EventApplication applyForEvent(Long eventId , EventApplication eventApplication) {

        Event event = eventRepository.findById(eventId).orElseThrow(() -> new CustomException("Event not found"));
        eventApplication.setEvent(event);
        return eventApplicationRepository.save(eventApplication);



    }
}
