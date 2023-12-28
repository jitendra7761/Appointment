package com.appointment.appointment.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appointment.appointment.exception.AppointmentException;
import com.appointment.appointment.model.Appointment;
import com.appointment.appointment.payload.AppointmentDto;
import com.appointment.appointment.repository.AppointmentRepository;



@Service
public class AppointmentServiceImpl implements AppointmentService{

	
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public AppointmentDto createAppointment(AppointmentDto appointmentDto) throws AppointmentException {

	    // Map the AppointmentDto to an Appointment entity using ModelMapper
	    Appointment appointment = modelMapper.map(appointmentDto, Appointment.class);

	    // Save the Appointment entity in the repository
	    Appointment savedAppointment = appointmentRepository.save(appointment);

	    // Map the saved Appointment entity back to an AppointmentDto using ModelMapper
	    AppointmentDto savedAppointmentDto = modelMapper.map(savedAppointment, AppointmentDto.class);

	    // Return the saved AppointmentDto
	    return savedAppointmentDto;
	}


	@Override
	public List<AppointmentDto> getAllAppointments() {
	    List<Appointment> appointments = appointmentRepository.findAll();

	    // Map the list of Appointment entities to a list of AppointmentDto objects using ModelMapper
	    List<AppointmentDto> appointmentDtos = appointments.stream()
	            .map(appointment -> modelMapper.map(appointment, AppointmentDto.class))
	            .collect(Collectors.toList());

	    return appointmentDtos;
	}

	

	@Override
	public Optional<AppointmentDto> getAppointmentById(Long id) throws AppointmentException {
	    Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);

	    if (appointmentOptional.isPresent()) {
	        Appointment appointment = appointmentOptional.get();
	        AppointmentDto appointmentDto = modelMapper.map(appointment, AppointmentDto.class);
	        return Optional.of(appointmentDto);
	    }

	    return Optional.empty();
	}

	

	@Override
	public List<AppointmentDto> searchAppointment(String name) throws AppointmentException {
		 List<Appointment> employees = appointmentRepository.searchByName("%" + name + "%");
		    
		    if (employees.isEmpty()) {
		        throw new AppointmentException("Employee name does not exist in the table");
		    }
		    
		    return employees.stream()
		            .map(employee -> modelMapper.map(employee, AppointmentDto.class))
		            .collect(Collectors.toList());
		}
	

	
	@Override
	public AppointmentDto updateAppointment(AppointmentDto appointmentDto, Long appointmentId) {
		Appointment apmtl=appointmentRepository.findById(appointmentDto.getId()).get();
		apmtl.setName(appointmentDto.getName());
		apmtl.setAge(appointmentDto.getAge());
		apmtl.setSymptoms(appointmentDto.getSymptoms());
		apmtl.setNumber(appointmentDto.getNumber());
		Appointment savedEmp=appointmentRepository.save(apmtl);
		AppointmentDto SaveEDto=modelMapper.map(savedEmp, AppointmentDto.class);
		return SaveEDto;
	}
	

	@Override
	public void deleteAppointment(Long appointmentId) throws AppointmentException {
		Optional<Appointment> employeeOptional = appointmentRepository.findById(appointmentId);

	    if (employeeOptional.isEmpty()) {
	        throw new AppointmentException("Appointmemnt ID does not exist in the table");
	    }

	    appointmentRepository.deleteById(appointmentId);
	}
		
	}





	

