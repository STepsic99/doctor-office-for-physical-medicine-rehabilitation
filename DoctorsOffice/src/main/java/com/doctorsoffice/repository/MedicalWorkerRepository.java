package com.doctorsoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctorsoffice.model.MedicalWorker;

public interface MedicalWorkerRepository extends JpaRepository<MedicalWorker, Long>{

}
