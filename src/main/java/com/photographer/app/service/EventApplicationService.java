package com.photographer.app.service;

import com.photographer.app.entity.Event;
import com.photographer.app.entity.EventApplication;
import com.photographer.app.exception.CustomException;
import com.photographer.app.repository.EventApplicationRepository;
import com.photographer.app.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventApplicationService {

    @Autowired
    private EventApplicationRepository eventApplicationRepository;

    @Autowired
    private EventRepository eventRepository;

    public EventApplication applyForEvent(Long eventId , EventApplication eventApplication) {

        Event event = eventRepository.findById(eventId).orElseThrow(() -> new CustomException("Event not found"));
        eventApplication.setEvent(event);
        eventApplication.setStatus("PENDING");
        return eventApplicationRepository.save(eventApplication);
    }

    public List<EventApplication> getApplicationByEvent(Long eventId){
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new CustomException("Event not found "));
        return eventApplicationRepository.findByEventId(eventId);
    }

    public EventApplication updateStatus(
            Long applicationId,
            String status,
            String email
    ){

        EventApplication application =
                eventApplicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new CustomException("Application not found"));

        Event event = application.getEvent();

        if(!event.getUser().getEmail().equals(email)){
            throw new CustomException("You are not authorized");
        }

        application.setStatus(status);

        return eventApplicationRepository.save(application);
    }
}
