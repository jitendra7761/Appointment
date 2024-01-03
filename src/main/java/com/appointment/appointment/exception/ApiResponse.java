package com.appointment.appointment.exception;

import java.util.Date;
import java.util.List;



public class ApiResponse {
	    private int status;
	    private Object data;
	    private String message;
	    private Date timestamp;

	    public ApiResponse(int status, String message, Date timestamp, Object data) {
	        this.status = status;
	        this.message = message;
	         this.timestamp = new Date();
	        this.data = data;
	    }

		public int getStatus() {
			return status;
		}

		public Object getData() {
			return data;
		}

		public String getMessage() {
			return message;
		}

		public Date getTimestamp() {
			return timestamp;
		}
		
		
	}







