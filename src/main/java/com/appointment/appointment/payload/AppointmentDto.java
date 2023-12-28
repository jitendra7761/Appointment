package com.appointment.appointment.payload;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class AppointmentDto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    private String name;
    private String age;
    private String symptoms;
    private String number;

    // Constructors
    public AppointmentDto() {
    }

    public AppointmentDto(long id, String name, String age, String symptoms, String number) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.symptoms = symptoms;
        this.number = number;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "AppointmentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", symptoms='" + symptoms + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
