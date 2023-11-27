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
import org.fifidianana.repository.DistrictRepository;
import org.fifidianana.repository.RegionRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Commune {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column
	String designation;
	
	@ManyToOne
	@JoinColumn(name = "iddistrict")
	District district;
	

	/** 
	 * get a list from a CSV 
	 * 
	 */
	public List<Commune> toObject(String chemin, DistrictRepository districtRepository) throws Exception { //"/home/liantsiky/ITU/Semestre 5/Mr Tahina/fifidianana/datas/regions.csv"
		try {
			InputStream inputStream = new FileInputStream(chemin);
			BufferedReader fileReader = new BufferedReader( new InputStreamReader(inputStream,"UTF-8"));
			CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			
			List <Commune> communes = new ArrayList<Commune>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			
			for (CSVRecord csvRecord : csvRecords) {
				Commune commune = new Commune();
				commune.setId(null);
				commune.setDesignation(csvRecord.get("COMMUNE"));
				District district = districtRepository.findByDesignation(csvRecord.get("DISTRICT"));
				commune.setDistrict(district);
				communes.add(commune);
			}
			return communes;
		}catch (Exception e) {
			 throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
	//constructors 
	public Commune() {
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
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District District) {
		this.district = District;
	}
}
