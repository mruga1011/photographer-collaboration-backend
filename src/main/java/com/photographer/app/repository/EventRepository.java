package com.photographer.app.repository;

import com.photographer.app.entity.Event;
import com.photographer.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByUser (User user);

}
