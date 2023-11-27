package org.fifidianana.repository;

import org.fifidianana.model.Commune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommuneRepository extends JpaRepository<Commune, Integer> {

	Commune findByDesignation(String string);

}
