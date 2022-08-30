package com.doctorsoffice.dto;

import com.doctorsoffice.model.Appointment;
import com.doctorsoffice.model.AppointmentDoctor;
import com.doctorsoffice.model.AppointmentPhysiotherapist;

public class AppointmentDTOFactory {
	
	 public AppointmentDTO getAppointmentDTO(Appointment app){
	      if(app == null){
	         return null;
	      }		
	      if(app instanceof AppointmentDoctor){
	         return new DoctorAppointmentDTO((AppointmentDoctor) app);
	         
	      } else if(app instanceof AppointmentPhysiotherapist){
	         return new PhysiotherapistAppointmentDTO((AppointmentPhysiotherapist) app);     
	      } 
	      return null;
	   }

}
