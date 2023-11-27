package org.fifidianana.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.fifidianana.repository.RegionRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class District {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column
	String designation;
	
	@ManyToOne
	@JoinColumn(name = "idregion")
	Region region;
	
	/** 
	 * get a list from a CSV 
	 * 
	 */
	public List<District> toObject(String chemin, RegionRepository regionRepository) throws Exception { //"/home/liantsiky/ITU/Semestre 5/Mr Tahina/fifidianana/datas/regions.csv"
		try {
			InputStream inputStream = new FileInputStream(chemin);
			BufferedReader fileReader = new BufferedReader( new InputStreamReader(inputStream,"UTF-8"));
			CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			
			List <District> districts = new ArrayList<District>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			
			for (CSVRecord csvRecord : csvRecords) {
				District district = new District();
				district.setId(null);
				district.setDesignation(csvRecord.get("DISTRICT"));
				Region region = regionRepository.findByDesignation(csvRecord.get("REGION"));
				district.setRegion(region);
				districts.add(district);
			}
			return districts;
		}catch (Exception e) {
			 throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
	//constructor
	public District() {
		// TODO Auto-generated constructor stub
	}
	
	//getters & setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	
}
