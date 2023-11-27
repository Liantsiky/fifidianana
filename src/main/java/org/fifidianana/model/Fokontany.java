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
import org.fifidianana.repository.CommuneRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Fokontany {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column
	String designation;
	
	@ManyToOne
	@JoinColumn(name = "idcommune")
	Commune commune;
	

	/** 
	 * get a list from a CSV 
	 * 
	 */
	public List<Fokontany> toObject(String chemin, CommuneRepository communeRepository) throws Exception { //"/home/liantsiky/ITU/Semestre 5/Mr Tahina/fifidianana/datas/regions.csv"
		try {
			InputStream inputStream = new FileInputStream(chemin);
			BufferedReader fileReader = new BufferedReader( new InputStreamReader(inputStream,"UTF-8"));
			CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			
			List <Fokontany> fokontanys = new ArrayList<Fokontany>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			
			for (CSVRecord csvRecord : csvRecords) {
				Fokontany fokontany = new Fokontany();
				fokontany.setId(null);
				fokontany.setDesignation(csvRecord.get("FOKONTANY"));
				Commune commune = communeRepository.findByDesignation(csvRecord.get("COMMUNE"));
				fokontany.setCommune(commune);
				fokontanys.add(fokontany);
			}
			return fokontanys;
		}catch (Exception e) {
			 throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
	//constructor
	public Fokontany() {
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
	public Commune getCommune() {
		return commune;
	}
	public void setCommune(Commune commune) {
		this.commune = commune;
	}
	
}
