package net.javaguides.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "attendee")
public class Attendee {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "conference_id", insertable = false, updatable = false)
    private Conference conference;

     /*
    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Conference conference;

      */

    public Attendee() {
    }

    public Attendee(String firstName, String lastName, String email, String password, Conference conference) {
        this.firstName = firstName;
        this. lastName = lastName;
        this.email = email;
        this.password = password;
        this.conference=conference;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }
}
