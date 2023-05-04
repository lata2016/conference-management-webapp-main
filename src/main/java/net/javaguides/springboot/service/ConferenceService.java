package net.javaguides.springboot.service;


import net.javaguides.springboot.model.Conference;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ConferenceService {
	List<Conference> getAllConferences();
	void saveConference(Conference conference);
	Conference getConferenceById(long id);
	void deleteConferenceById(long id);
	Page<Conference> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
