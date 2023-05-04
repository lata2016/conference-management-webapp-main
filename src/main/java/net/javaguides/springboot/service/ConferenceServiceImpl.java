package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import net.javaguides.springboot.model.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.repository.ConferenceRepository;

@Service
public class ConferenceServiceImpl implements ConferenceService {

	@Autowired
	private ConferenceRepository conferenceRepository;

	@Override
	public List<Conference> getAllConferences() {
		return conferenceRepository.findAll();
	}

	@Override
	public void saveConference(Conference conference) {
		this.conferenceRepository.save(conference);
	}

	@Override
	public Conference getConferenceById(long id) {
		Optional<Conference> optional = conferenceRepository.findById(id);
		Conference conference = null;
		if (optional.isPresent()) {
			conference = optional.get();
		} else {
			throw new RuntimeException(" Conference not found for id :: " + id);
		}
		return conference;
	}

	@Override
	public void deleteConferenceById(long id) {
		this.conferenceRepository.deleteById(id);
	}

	@Override
	public Page<Conference> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.conferenceRepository.findAll(pageable);
	}
}
