package com.photographer.app.repository;

import com.photographer.app.entity.EventApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventApplicationRepository extends JpaRepository<EventApplication,Long> {

    List<EventApplication> findByEventId(Long eventId);
}
