package com.appointment.appointment.service;
import java.util.List;
import java.util.Optional;
import com.appointment.appointment.exception.AppointmentException;
import com.appointment.appointment.model.Appointment;
import com.appointment.appointment.payload.AppointmentDto;


public interface AppointmentService {
	

	 
	//Insert Appointment in Table
	 AppointmentDto createAppointment(AppointmentDto appointmentDto) throws AppointmentException;

	//fatch ALL Appointment in the Table
	 List<AppointmentDto> getAllAppointments();
	
	//findAppointmentid
	 Optional<AppointmentDto> getAppointmentById(Long id)throws AppointmentException;
	
	//search Appointment
	List<AppointmentDto> searchAppointment(String name) throws AppointmentException;
			
	//Update Appointment	
	AppointmentDto updateAppointment(AppointmentDto appointmentDto,Long appointmentId);
	
	//Delete Appointment
    void deleteAppointment(Long appointmentId)throws AppointmentException;
	
	

}
