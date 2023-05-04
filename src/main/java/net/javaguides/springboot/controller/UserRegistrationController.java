package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Attendee;
import net.javaguides.springboot.model.Conference;
import net.javaguides.springboot.repository.AttendeeRepository;
import net.javaguides.springboot.repository.ConferenceRepository;
import net.javaguides.springboot.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import net.javaguides.springboot.dto.UserRegistrationDto;
import net.javaguides.springboot.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserService userService;
	private ConferenceService conferenceService;
	@Autowired
	private ConferenceRepository conferenceRepository;
	@Autowired
	private AttendeeRepository attendeeRepository;

	public UserRegistrationController(ConferenceService conferenceService,UserService userService) {
		super();
		this.userService = userService;
		this.conferenceService = conferenceService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}

}
