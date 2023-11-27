package org.fifidianana.controller;

import java.util.ArrayList;
import java.util.List;

import org.fifidianana.model.Centre;
import org.fifidianana.model.Fokontany;
import org.fifidianana.repository.CentreRepository;
import org.fifidianana.repository.FokontanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class CentreController {
	@Autowired
	CentreRepository centreRepository;
	@Autowired
	FokontanyRepository fokontanyRepository;
	@Autowired
	ObjectMapper objectMapper;
	
	@GetMapping("/centres")
	public ResponseEntity<String> getAllCentre() {
		try {

			Centre centre = new Centre();
			List<Centre> listes = centre.toObject("/home/liantsiky/ITU/Semestre 5/Mr Tahina/fifidianana/datas/centrevotes.csv",fokontanyRepository);
			centreRepository.saveAll(listes);
			List <Centre> liste = new ArrayList<>();
			centreRepository.findAll().forEach(liste::add);
			String json = objectMapper.writeValueAsString(liste);
			
			return new ResponseEntity<>(json,HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
