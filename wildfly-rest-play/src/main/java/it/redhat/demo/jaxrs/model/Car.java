package it.redhat.demo.jaxrs.model;

import java.util.Date;

public class Car {

	private String colour;
	private Date registeredOn;

	public Car() {
	}

	public Car(String colour) {
		this.colour = colour;

		// at the beginning fo time!
		this.registeredOn = new Date();
		this.registeredOn.setTime( 0l );
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Date getRegisteredOn() {
		return registeredOn;
	}

	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}
}
