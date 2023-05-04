package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Attendee;
import net.javaguides.springboot.model.Conference;
import net.javaguides.springboot.repository.AttendeeRepository;
import net.javaguides.springboot.repository.ConferenceRepository;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class AttendeeService {

    private AttendeeRepository attendeeRepository;
    private ConferenceRepository conferenceRepository;

    public AttendeeService (AttendeeRepository attendeeRepository,ConferenceRepository conferenceRepository){
        this.attendeeRepository = attendeeRepository;
        this.conferenceRepository = conferenceRepository;

    }

    public Set<Attendee> getAllAttendees() {
        return (Set<Attendee>) attendeeRepository.findAll();
    }

    public Attendee getAttendeeById(Long id) {
        return attendeeRepository.findById(id).orElse(null);
    }

    /*public Attendee saveAttendee(Attendee attendee) {
        Conference conference =attendee.getConference();
        Set<Attendee> attendees = conference.getAttendees();
        attendees.add(attendee);
        conference.setAttendees(attendees);
        return attendeeRepository.save(attendee);
    }

     */


    public Attendee saveAttendee(Attendee attendee) {
        Conference conference = attendee.getConference();
        Set<Attendee> attendees = (Set<Attendee>) conference.getAttendees();
        attendees.add(attendee);
        conference.setAttendees((Set<Attendee>) attendees);
        conferenceRepository.save(conference);
        return attendeeRepository.save(attendee);
    }
    public void deleteAttendee(Long id) {
        attendeeRepository.deleteById(id);
    }

}
