package org.fifidianana.controller;

import java.util.ArrayList;
import java.util.List;

import org.fifidianana.model.Commune;
import org.fifidianana.model.District;
import org.fifidianana.repository.CommuneRepository;
import org.fifidianana.repository.DistrictRepository;
import org.fifidianana.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class CommuneController {
	@Autowired
	CommuneRepository communeRepository;
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	ObjectMapper objectMapper;

	@GetMapping("/communes")
	public ResponseEntity<String> getAllCommunes() {
		try {

			Commune commune = new Commune();
			List<Commune> listes = commune.toObject("/home/liantsiky/ITU/Semestre 5/Mr Tahina/fifidianana/datas/communes.csv",districtRepository);
			communeRepository.saveAll(listes);
			List <Commune> liste = new ArrayList<>();
			communeRepository.findAll().forEach(liste::add);
			String json = objectMapper.writeValueAsString(liste);
			
			return new ResponseEntity<>(json,HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
