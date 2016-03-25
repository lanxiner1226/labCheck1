package edu.hdu.lab.checkIn.serviceImpls;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hdu.lab.checkIn.mapper.RecordMapper;
import edu.hdu.lab.checkIn.model.Record;
import edu.hdu.lab.checkIn.model.RecordExample;
import edu.hdu.lab.checkIn.services.RecordService;

@Service("RecordService")
public class RecordServiceImpl implements RecordService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private RecordMapper recordMapper;

	@Override
	public List<Record> getRecord(Integer user_id) {
		RecordExample example = new RecordExample();
		Date value1=new Date();
		Date value2=new Date();
		value2.setDate(value1.getDate()-1);
		example.createCriteria().andUserIdEqualTo(user_id).andRecordTimeBetween(value2, value1);;
		example.setOrderByClause("id desc");
		List<Record> list = recordMapper.selectByExample(example);
		
		return list;
	}

	@Override
	public int addRecord(Record record) {
		
		 return  recordMapper.insert(record);
	}

	@Override
	public int updateRecord(Record Record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRecord(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Record> getRecords(Integer user_id) {
		RecordExample example = new RecordExample();
		Date value1=new Date();
		Date value2=new Date();
		value2.setDate(value1.getDate()-7);
		example.createCriteria().andRecordTimeBetween(value2, value1);;
		example.setOrderByClause("id desc");
		List<Record> list = recordMapper.selectByExample(example);
		
		return list;
	}

}
