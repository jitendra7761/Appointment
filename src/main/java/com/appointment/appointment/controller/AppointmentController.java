package com.appointment.appointment.controller;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.appointment.exception.ApiResponse;
import com.appointment.appointment.exception.AppointmentException;
import com.appointment.appointment.payload.AppointmentDto;
import com.appointment.appointment.service.AppointmentService;

@RestController
@RequestMapping("/api")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;

	@PostMapping("/appointments")
	public ResponseEntity<ApiResponse> createAppointment(@RequestBody AppointmentDto appointmentDto) {
	    try {
	        // Create the appointment and get the result
	        AppointmentDto appointment = appointmentService.createAppointment(appointmentDto);
	        
	        // Populate a success message in the ApiResponse
	        ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Appointment created successfully", new Date(0), appointment);

	        // Return a ResponseEntity with the success response and HTTP status OK
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (AppointmentException e) {
	        // Handle the AppointmentException by returning an error response
	        ApiResponse errorResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Appointment creation failed: " + e.getMessage(), new Date(0), null);
	        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping("/appointments")
	public ResponseEntity<ApiResponse> getAllAppointments() {
	    try {
	        List<AppointmentDto> list = appointmentService.getAllAppointments();

	        // Create an ApiResponse object with a success status and the current timestamp
	        ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Appointments retrieved successfully", new Date(0), list);

	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	        // Handle the AppointmentException by returning an error response
	        ApiResponse errorResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Appointment creation failed: " + e.getMessage(), new Date(0), null);
	        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/appointments/{id}")
	public ResponseEntity<ApiResponse> getAppointmentById(@PathVariable Long id) {
	    try {
	        Optional<AppointmentDto> appointment = appointmentService.getAppointmentById(id);

	        if (appointment.isPresent()) {
	            // Create an ApiResponse object with a success status and the current timestamp
	            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Appointment found", new Date(0), appointment.get());
	            return ResponseEntity.ok(response);
	        } else {
	            // Create an ApiResponse object with a not found status and the current timestamp
	            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Appointment not found", new Date(0), null);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	    } catch (AppointmentException e) {
	        // Handle the AppointmentException by returning an error response
	        ApiResponse errorResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error: " + e.getMessage(), new Date(0), null);
	        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@PutMapping("/appointments/{id}")
	public ResponseEntity<ApiResponse> updateAppointment(@RequestBody AppointmentDto appointmentDto, @PathVariable("id") Long appointmentId) {
	    try {
	        // Attempt to update the appointment
	        AppointmentDto updatedAppointment = appointmentService.updateAppointment(appointmentDto, appointmentId);

	        if (updatedAppointment != null) {
	            // Create an ApiResponse with a success status and the current timestamp
	            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Appointment updated successfully", new Date(0), updatedAppointment);
	            return ResponseEntity.ok(response);
	        } else {
	            // Create an ApiResponse with a not found status and the current timestamp
	            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Appointment not found", new Date(0), null);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	    } catch (Exception e) {
	        // Handle the AppointmentException by returning an error response
	        ApiResponse errorResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error: " + e.getMessage(), new Date(0), null);
	        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	
	@DeleteMapping("/appointments/{id}")
	public ResponseEntity<ApiResponse> deleteAppointment(@PathVariable("id") Long appointmentId) {
	    try {
	        // Check if the appointment exists
	        Optional<AppointmentDto> appointment = appointmentService.getAppointmentById(appointmentId);
	        
	        if (appointment.isPresent()) {
	            // Delete the appointment
	            appointmentService.deleteAppointment(appointmentId);

	            // Create an ApiResponse with a success status and the current timestamp
	            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Appointment deleted successfully", new Date(0), null);
	            return ResponseEntity.ok(response);
	        } else {
	            // Create an ApiResponse with a not found status and the current timestamp
	            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Appointment not found", new Date(0), null);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	    } catch (AppointmentException e) {
	        // Handle the AppointmentException by returning an error response
	        ApiResponse errorResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error: " + e.getMessage(), new Date(0), null);
	        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@GetMapping("/search/appointments/{name}")
	public ResponseEntity<ApiResponse> searchAppointment(@PathVariable("name") String name) {
	    try {
	        List<AppointmentDto> result = appointmentService.searchAppointment(name);

	        // Create an ApiResponse with a success status, the current timestamp, and the list of results
	        ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Search results", new Date(0), result);
	        return ResponseEntity.ok(response);
	    } catch (AppointmentException e) {
	        // Handle the AppointmentException by returning an error response
	        ApiResponse errorResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error: " + e.getMessage(), new Date(0), null);
	        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

		
}