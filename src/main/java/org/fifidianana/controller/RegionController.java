package org.fifidianana.controller;

import java.util.ArrayList;
import java.util.List;

import org.fifidianana.model.Region;
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
public class RegionController {
	@Autowired
	RegionRepository regionRepository;
	@Autowired
	ObjectMapper objectMapper;

	@GetMapping("/regions")
	public ResponseEntity<String> getAllRegion() {
		try {

			Region region = new Region();
			List<Region> listes = region.toObject("/home/liantsiky/ITU/Semestre 5/Mr Tahina/fifidianana/datas/regions.csv");
			regionRepository.saveAll(listes);
			List <Region> liste = new ArrayList<>();
			 regionRepository.findAll().forEach(liste::add);
			String json = objectMapper.writeValueAsString(liste);
			
			return new ResponseEntity<>(json,HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
