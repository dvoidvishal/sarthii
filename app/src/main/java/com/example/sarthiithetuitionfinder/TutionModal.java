package com.example.sarthiithetuitionfinder;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TutionModal {
    String Name, Contact, Email, NoOfBatches;
    List<String> Classes = new ArrayList<>(), Qualifications = new ArrayList<>(), Subjects = new ArrayList<>();

    public TutionModal() {}

    public TutionModal(String name, String contact, String email, String noOfBatches, List<String> classes, List<String> qualifications, List<String> subjects) {
        Name = name;
        Contact = contact;
        Email = email;
        NoOfBatches = noOfBatches;
        Classes = classes;
        Qualifications = qualifications;
        Subjects = subjects;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNoOfBatches() {
        return NoOfBatches;
    }

    public void setNoOfBatches(String noOfBatches) {
        NoOfBatches = noOfBatches;
    }

    public List<String> getClasses() {
        return Classes;
    }

    public void setClasses(List<String> classes) {
        Classes = classes;
    }

    public List<String> getQualifications() {
        return Qualifications;
    }

    public void setQualifications(List<String> qualifications) {
        Qualifications = qualifications;
    }

    public List<String> getSubjects() {
        return Subjects;
    }

    public void setSubjects(List<String> subjects) {
        Subjects = subjects;
    }
}
