package com.photographer.app.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String title;

    private String location;

    private LocalDate eventDate;

    private Integer requiredPhotographers;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "event",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<EventApplication> applications;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getRequiredPhotographers() {
        return requiredPhotographers;
    }

    public void setRequiredPhotographers(Integer requiredPhotographers) {
        this.requiredPhotographers = requiredPhotographers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<EventApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<EventApplication> applications) {
        this.applications = applications;
    }
}
