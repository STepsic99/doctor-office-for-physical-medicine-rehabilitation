package com.doctorsoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doctorsoffice.model.Disease;

public interface DiseaseRepository extends JpaRepository<Disease, Long>{
	
	@Query(value = "SELECT * FROM DISEASE WHERE LOWER(NAME) LIKE LOWER(CONCAT('%', ?1, '%'))", nativeQuery = true)
    List<Disease> findAllByName(String name);
	
	@Query(value = "SELECT * FROM DISEASE WHERE LOWER(CODE) LIKE LOWER(CONCAT('%', ?1, '%'))", nativeQuery = true)
	List<Disease> findAllByCode(String code);

}
