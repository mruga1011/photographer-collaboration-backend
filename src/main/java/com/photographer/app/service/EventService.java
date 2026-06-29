package com.photographer.app.service;

import com.photographer.app.entity.Event;
import com.photographer.app.entity.EventApplication;
import com.photographer.app.entity.User;
import com.photographer.app.exception.CustomException;
import com.photographer.app.repository.EventRepository;
import com.photographer.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Event> getMyEvents(String email){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new CustomException("User not found"));

        return eventRepository.findByUser(user);

    }

    public Event updateEvent(Long eventId, Event updateEvent,String email){
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new CustomException("Event not found"));

        if(!event.getUser().getEmail().equals(email)){
            throw new CustomException("You are not autorized to update this event");
        }

        if(updateEvent.getTitle() != null){
            event.setTitle(updateEvent.getTitle());
        }

        if(updateEvent.getLocation() != null){
            event.setLocation(updateEvent.getLocation());
        }

        if(updateEvent.getEventDate() != null){
            event.setEventDate(updateEvent.getEventDate());
        }

        if(updateEvent.getRequiredPhotographers() != null){
            event.setRequiredPhotographers(updateEvent.getRequiredPhotographers());
        }

        return eventRepository.save(event);

    }

    public void deleteEvent (Long eventId, String email){
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new CustomException("Event not found"));
        if(!event.getUser().getEmail().equals(email)){
            throw new CustomException("You are not authorized to delete this event");

        }
        eventRepository.delete(event);
    }


}
