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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="region")
public class Region {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "designation")
	String designation;
	
	/** 
	 * get a list from a CSV 
	 * 
	 */
	public List<Region> toObject(String chemin) throws Exception { //"/home/liantsiky/ITU/Semestre 5/Mr Tahina/fifidianana/datas/regions.csv"
		try {
			InputStream inputStream = new FileInputStream(chemin);
			BufferedReader fileReader = new BufferedReader( new InputStreamReader(inputStream,"UTF-8"));
			CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			
			List <Region> regions = new ArrayList<Region>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			
			for (CSVRecord csvRecord : csvRecords) {
				Region region = new Region();
				region.setId(null);
				region.setDesignation(csvRecord.get("REGION"));
				regions.add(region);
			}
			return regions;
		}catch (Exception e) {
			 throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
	
	
	//contructors 
	public Region() {
		// TODO Auto-generated constructor stub
	}
	public Region(Integer id,String designation) {
		setId(id);
		setDesignation(designation);
	}	
	
	//getters & setters
	public int getId() {
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
}
