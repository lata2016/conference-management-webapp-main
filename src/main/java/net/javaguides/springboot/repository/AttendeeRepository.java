package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AttendeeRepository extends JpaRepository<Attendee,Long> {
}
