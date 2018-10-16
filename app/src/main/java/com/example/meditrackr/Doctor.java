package com.example.meditrackr;

public class Doctor {
    private String id;
    private String email;
    private String phone;
    private String name;
    private Patient patient;

    public Doctor(String id, String email, String phone, String name, Patient patient){
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.patient = patient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
