package com.doctorsoffice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Report {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}