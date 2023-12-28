package com.appointment.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appointment.appointment.model.Appointment;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	
	@Query("select p from Appointment p where p.name like :n")
	List<Appointment> searchByName(@Param("n") String name);

}
