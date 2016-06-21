package edu.hdu.lab.checkIn.model;

import java.util.List;

public class StudentDayRecord {

	private String name;
	
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private List<Record> records;

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "StudentDayRecord [name=" + name + ", records=" + records + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StudentDayRecord() {
		super();
	}
	
}
