package model;

import registry.AppointmentRegistry;

public class User {
	private String username;	
	private Role role;
	
	public User(String username, String password, Role role) {
		super();
		this.username = username;
		this.role = role;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void completeAppointment(String idUnivoco, MedicalRecord medicalRecord,
			AppointmentRegistry appointmentRegistry) {
		
	}	
}
