package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Attendee;
import net.javaguides.springboot.model.Conference;
import net.javaguides.springboot.repository.AttendeeRepository;
import net.javaguides.springboot.repository.ConferenceRepository;
import net.javaguides.springboot.service.AttendeeService;
import net.javaguides.springboot.service.ConferenceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AttendeeContoroller {

    private AttendeeService attendeeService;
    private AttendeeRepository attendeeRepository;
    private ConferenceService conferenceService;
    private ConferenceRepository conferenceRepository;

    private AttendeeContoroller(AttendeeRepository attendeeRepository, AttendeeService attendeeService,
                                ConferenceRepository conferenceRepository, ConferenceService conferenceService) {
        this.attendeeRepository = attendeeRepository;
        this.attendeeService = attendeeService;
        this.conferenceRepository = conferenceRepository;
        this.conferenceService = conferenceService;

    }

    @PreAuthorize("hasRole('USER_ROLE')")
    @GetMapping ("/registerForConference/{id}")
    public String showFormForRegister(@PathVariable(value = "id") long id, Model model) {
        Attendee attendee = new Attendee();
        Conference conference = conferenceRepository.findById(id).orElse(null);
        if (conference == null) {
            throw new IllegalArgumentException("Invalid conference Id:" + id);
        }
        attendee.setConference(conference);
        model.addAttribute("attendee", attendee);
        return "attendee_form";
    }


}
    /*
    @PreAuthorize("hasRole('USER_ROLE')")
    @GetMapping("/registerForConference/{id}")
    public String showFormForRegister(@PathVariable(value = "id") Long id, Model model) {
        Attendee attendee = new Attendee();
        Conference conference = conferenceRepository.findById(id).orElse(null);
        if (conference == null) {
            throw new IllegalArgumentException("Invalid conference Id:" + id);
        }
        attendee.setConference(conference);
        attendee.setEmail("dhurata@example.com");
        attendee.setFirstName("dhurata");
        attendee.setLastName("qosja");
        attendee.setPassword("123");
        attendeeService.saveAttendee(attendee);
        return "attendee_form";


    }

}

     */
