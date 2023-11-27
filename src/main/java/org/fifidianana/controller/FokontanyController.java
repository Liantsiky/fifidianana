package org.fifidianana.controller;

import java.util.ArrayList;
import java.util.List;

import org.fifidianana.model.Commune;
import org.fifidianana.model.Fokontany;
import org.fifidianana.repository.CommuneRepository;
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
public class FokontanyController {
	@Autowired
	CommuneRepository communeRepository;
	@Autowired
	FokontanyRepository fokontanyRepository;
	@Autowired
	ObjectMapper objectMapper;
	
	@GetMapping("/fokontanys")
	public ResponseEntity<String> getAllFokontany() {
		try {

			Fokontany fokontany = new Fokontany();
			List<Fokontany> listes = fokontany.toObject("/home/liantsiky/ITU/Semestre 5/Mr Tahina/fifidianana/datas/fokontany.csv",communeRepository);
			fokontanyRepository.saveAll(listes);
			List <Fokontany> liste = new ArrayList<>();
			fokontanyRepository.findAll().forEach(liste::add);
			String json = objectMapper.writeValueAsString(liste);
			
			return new ResponseEntity<>(json,HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
