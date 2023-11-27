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
import org.fifidianana.repository.FokontanyRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Centre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column
	String designation;
	
	@ManyToOne
	@JoinColumn(name = "idfokontany")
	Fokontany fokontany;
	

	/** 
	 * get a list from a CSV 
	 * 
	 */
	public List<Centre> toObject(String chemin, FokontanyRepository fokontanyRepository) throws Exception { //"/home/liantsiky/ITU/Semestre 5/Mr Tahina/fifidianana/datas/regions.csv"
		try {
			InputStream inputStream = new FileInputStream(chemin);
			BufferedReader fileReader = new BufferedReader( new InputStreamReader(inputStream,"UTF-8"));
			CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			
			List <Centre> centres = new ArrayList<Centre>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			
			for (CSVRecord csvRecord : csvRecords) {
				Centre centre = new Centre();
				centre.setId(null);
				centre.setDesignation(csvRecord.get("CENTRE DE VOTE"));
				Fokontany fokontany = fokontanyRepository.findByDesignation(csvRecord.get("FOKONTANY"));
				centre.setFokontany(fokontany);
				centres.add(centre);
			}
			return centres;
		}catch (Exception e) {
			 throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
	//constructor
	public Centre() {
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
		public Fokontany getFokontany() {
			return fokontany;
		}
		public void setFokontany(Fokontany fokontany) {
			this.fokontany = fokontany;
		}
}
