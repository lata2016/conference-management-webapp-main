package net.javaguides.springboot.model;

import javax.persistence.*;
import java.security.PermissionCollection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "conference")

public class Conference {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column(name = "conference_name")
    private String conferenceName;

    @Column(name = "location")
    private String location;

    @Column(name = "speaker")

    private String speaker;
    /*
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)

    private Set<Attendee> attendees = new HashSet<>();

     */
    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL)
    private Set<Attendee> attendees = new HashSet<>();

    public Conference(long id, String conferenceName, String location, String speaker, Set<Attendee> attendees) {
        this.id = id;
        this.conferenceName = conferenceName;
        this.location = location;
        this.speaker = speaker;
        this.attendees = attendees;
    }

    public Conference() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public Set<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<Attendee> attendees) {
        this.attendees = attendees;
    }
}
