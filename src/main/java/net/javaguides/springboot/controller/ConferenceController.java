package net.javaguides.springboot.controller;

import java.util.List;

import net.javaguides.springboot.model.Attendee;
import net.javaguides.springboot.model.Conference;
import net.javaguides.springboot.repository.AttendeeRepository;
import net.javaguides.springboot.repository.ConferenceRepository;
import net.javaguides.springboot.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.javaguides.springboot.service.ConferenceService;

@Controller
public class ConferenceController {

	private ConferenceService conferenceService;
	private ConferenceRepository conferenceRepository;

	private ConferenceController (ConferenceService conferenceService, ConferenceRepository conferenceRepository){
		this.conferenceRepository = conferenceRepository;
		this.conferenceService = conferenceService;
	}

	// display list
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "conferenceName", "asc", model);
	}

	@PreAuthorize("hasRole('USER_ADMIN')")
	@GetMapping("/showNewConferenceForm")
	public String showNewConferenceForm(Model model) {
		// create model attribute to bind form data
		Conference conference = new Conference();
		model.addAttribute("conference", conference);
		return "new_conference";
	}

	@PreAuthorize("hasRole('USER_ADMIN')")
	@PostMapping("/saveConference")
	public String saveConference(@ModelAttribute("conference") Conference conference) {
		// save  to database
		conferenceService.saveConference(conference);
		return "redirect:/";
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get from the service
		Conference conference = conferenceService.getConferenceById(id);
		
		// set sonference as a model attribute to pre-populate the form
		model.addAttribute("conference", conference);
		return "update_conference";
	}

	@PreAuthorize("hasRole('USER_ADMIN')")
	@GetMapping("/deleteConference/{id}")
	public String deleteConference(@PathVariable (value = "id") long id) {
		
		// call delete  method
		this.conferenceService.deleteConferenceById(id);
		return "redirect:/";
	}
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Conference> page = conferenceService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Conference> listConferences = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listConferences", listConferences);
		return "index";
	}
}
