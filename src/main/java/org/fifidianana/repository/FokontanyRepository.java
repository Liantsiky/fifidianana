package org.fifidianana.repository;

import org.fifidianana.model.Fokontany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FokontanyRepository extends JpaRepository<Fokontany,Integer> {

	Fokontany findByDesignation(String string);

}
