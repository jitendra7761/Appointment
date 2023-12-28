package com.appointment.appointment.exception;

public class AppointmentException extends Exception{
	
	//rivate HttpStatus status;
		//ivate Object Data;
		private String message;
		
		public AppointmentException(String message) {
			super(message);
			
		}

}
