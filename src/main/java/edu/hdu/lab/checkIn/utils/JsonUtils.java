package edu.hdu.lab.checkIn.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class JsonUtils {

	  /**
     * 生成Gson对象
     * @return 一个Gson对象
     */
	public static Gson createGson() {
		return
				new GsonBuilder().setDateFormat(Constants.FULL_DATE_FORMAT).create(); 
	}
}
