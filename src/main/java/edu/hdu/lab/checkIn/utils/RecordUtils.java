package edu.hdu.lab.checkIn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import edu.hdu.lab.checkIn.model.OneDayRecord;
import edu.hdu.lab.checkIn.model.Record;

public class RecordUtils {

	private static Logger logger = Logger.getLogger(RecordUtils.class);

	public static List<OneDayRecord> getOneDayRecord(List<Record> records) {

		List<OneDayRecord> dayRecords = new ArrayList<OneDayRecord>();
		int dayoff = 0;
		dayRecords.add(getOneDayRecord1(records.get(0)));
		Date date = new Date();
		date.setDate(records.get(0).getRecordTime().getDate());
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		for (int i = 0; i < records.size(); i++) {
			Record record = records.get(i);
			Date recordTime = record.getRecordTime();
			logger.error(recordTime);
			if (recordTime.after(date)) {
				if (dayRecords.get(dayoff).getList().size() >= 2) {
					dayRecords.get(dayoff).getList().remove(1);
					dayRecords.get(dayoff).getList().add(record);
				} else {
					dayRecords.get(dayoff).getList().add(record);
				}
			} else {
				dayoff++;
				dayRecords.add(getOneDayRecord1(record));
				date.setDate(record.getRecordTime().getDate());
			}
		}
		return dayRecords;
	}

	public static OneDayRecord getOneDayRecord1(Record record) {
		OneDayRecord oneDayRecord = new OneDayRecord();
		List<Record> list = new ArrayList<Record>();
		Record fristrecord = record;
		list.add(fristrecord);
		oneDayRecord.setList(list);
		return oneDayRecord;
	}
	
	/**
	 * 字符串转化为Date
	 * @param string
	 * @return
	 */
	public static Date StringtoDate(String string) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date =null;
		try {
			date = sdf.parse(string);
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
