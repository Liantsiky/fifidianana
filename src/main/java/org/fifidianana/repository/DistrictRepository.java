package org.fifidianana.repository;

import org.fifidianana.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Integer> {

	District findByDesignation(String string);

}
