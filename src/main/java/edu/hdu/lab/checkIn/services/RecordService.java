package edu.hdu.lab.checkIn.services;

import java.util.Date;
import java.util.List;

import edu.hdu.lab.checkIn.model.Record;

public interface RecordService {

	public List<Record> getRecord(Integer user_id);
	
	public int addRecord(Record Record);
    
    public int updateRecord(Record Record);
    
    public int deleteRecord(int id);
	
//    public List<Record> getRecords(Record Record);

    public List<Record> getRecords(Integer user_id);
    
    public List<Record> getRecordsbyTime(Date date1,Date date2);
    
//    public List<Record> getStudentsRecords(Record Record);
}
