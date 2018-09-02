package com.example.elasticsearch.component;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.csvreader.CsvReader;
import com.example.elasticsearch.dao.HotelDao;
import com.example.elasticsearch.dao.LandmarkDao;
import com.example.elasticsearch.dao.PriceDao;
import com.example.elasticsearch.document.Hotel;
import com.example.elasticsearch.document.HotelFacility;
import com.example.elasticsearch.document.HotelPolicy;
import com.example.elasticsearch.document.Landmark;
import com.example.elasticsearch.document.Price;

@Component
public class InitComponent implements CommandLineRunner {

	private static final List<String> TIMES;	//作用是什么？？

	static {
		TIMES = new ArrayList<>();
		TIMES.add("create_time");
		TIMES.add("version_no");
		TIMES.add("latest_order_time");
	}

	Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${hotel:gt10_hotel_adapter.csv}")
	String hotel;

	@Value("${landmark:gt10_landmark.csv}")
	String landmark;

	@Value("${price:gt10_base_price.csv}")
	String price;

	@Autowired
	HotelDao hotelDao;

	@Autowired
	LandmarkDao landmarkDao;

	@Autowired
	PriceDao priceDao;

	@Override
	public void run(String... args) throws Exception {
		uploadPrice();
		uploadHotel();
		uploadLandmark();
	}

	private void uploadPrice() throws Exception {
		File priceFile = new File(price);

		if (priceFile.exists()) {

			Field[] priceFields = getField(Price.class);  //获取类price的所有字段信息；

			CsvReader csvReader = new CsvReader(new FileInputStream(priceFile), ',', Charset.forName("UTF-8"));
			csvReader.readHeaders();    //Read the first record of data as column headers.

			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			int i = 0;
			
			List<Price> list = new ArrayList<>();

			while (csvReader.readRecord()) {

				try {
					csvReader.getRawRecord();  //相当于next();
					Price o = new Price();

					for (Field field : priceFields) {

						String fieldname = field.getName();
						field.setAccessible(true);

						if (StringUtils.isNotBlank(csvReader.get(fieldname))) {

							if ("bizdate".equals(fieldname)) {

								try {
									Date date = sdf2.parse(csvReader.get(fieldname));  //??看CsvReader源码
									field.set(o, sdf3.format(date));
								} catch (Exception e) {
									throw e;
								}
							} else if ("create_time".equals(fieldname) || "update_time".equals(fieldname)) {
								try {
									Date date = sdf1.parse(csvReader.get(fieldname));
									field.set(o, sdf4.format(date));
								} catch (Exception e) {
									throw e;
								}
							} else if ("base_price".equals(fieldname)) {
								field.set(o, Float.valueOf(csvReader.get(fieldname)));
							}

							else {
								field.set(o, csvReader.get(fieldname));
							}

						} else {
							if (field.getGenericType().toString().equals("class java.lang.String")) {
								field.set(o, "");
							}
						} 
					}
					list.add(o);
					
					i++;
					
					if(i % 1000 == 0) {
						priceDao.saveAll(list);
						list = null;
						list = new ArrayList<>();
					}
					
					logger.info("已成功导入price数据: " + i);
				} catch (Exception e) {
					logger.error(e.getMessage());
					continue;
				}

			}
			
			if(list.size() > 0) {
				priceDao.saveAll(list);
			}
			
			logger.info("========== 导入price数据完成 ==========");
		} else {
			logger.info("未发现" + price + "文件");
		}
	}

	private void uploadHotel() throws Exception {
		File hotelFile = new File(hotel);

		// 导入hotel数据

		if (hotelFile.exists()) {

			Field[] hotelFields = getField(Hotel.class);
			CsvReader csvReader = new CsvReader(new FileInputStream(hotelFile), ',', Charset.forName("UTF-8"));
			csvReader.readHeaders();   //作用？？
			

			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			int i = 0;
			
			List<Hotel> list = new ArrayList<>();
			
			while (csvReader.readRecord()) {

				try {
					csvReader.getRawRecord();
					Hotel o = new Hotel();

					for (Field field : hotelFields) {

						String fieldname = field.getName();
						field.setAccessible(true);

						if (StringUtils.isNotBlank(csvReader.get(fieldname))) {

							if ("tags".equals(fieldname)) {
								String[] tags = csvReader.get(fieldname).split(",");
								field.set(o, tags);
							} else if ("business_area_name".equals(fieldname)
									|| "old_business_area_name".equals(fieldname)) {
								String[] tags = csvReader.get(fieldname).split("、");
								field.set(o, tags);
							} else if (TIMES.contains(fieldname)) {
								
								//try vatch{}的作用？
								try {
									//读入前的数据类型是1或3，读入后都变成类型2.
									Date date = sdf1.parse(csvReader.get(fieldname));
									field.set(o, sdf2.format(date));
								} catch (Exception e) {
									try {
										Date date = sdf3.parse(csvReader.get(fieldname));
										field.set(o, sdf2.format(date));
									} catch (Exception ex) {
										throw ex;
									}
								}

							//嵌套数据类型的处理方法：
							} else if ("hotel_facility".equals(fieldname)) {
								JSONArray jsonArray = JSONArray.parseArray(csvReader.get(fieldname));
								
								field.set(o, jsonArray.toJavaList(HotelFacility.class));

							} else if ("hotel_policy".equals(fieldname)) {
								//读取字段内容到json结构中
								JSONArray jsonArray = JSONArray.parseArray(csvReader.get(fieldname));
								field.set(o, jsonArray.toJavaList(HotelPolicy.class));
							} else {
								field.set(o, csvReader.get(fieldname));
							}
						} else {
							if (field.getGenericType().toString().equals("class java.lang.String")
									&& !TIMES.contains(fieldname)) {
								field.set(o, "");
							}
						}

					}
					
					//新增的字段。
					if (o.getLatitude() != null && o.getLongitude() != null) {
						o.setLocation(new StringBuffer().append(o.getLatitude()).append(",").append(o.getLongitude())
								.toString());
					}
					list.add(o);
					i++;
					if(i % 1000 == 0) {
						hotelDao.saveAll(list);
						list = null;
						list = new ArrayList<>();
					}
					
					logger.info("已成功导入hotel数据: " + i);
				} catch (Exception e) {
					logger.error(e.getMessage());
					continue;
				}

			}
			if(list.size() > 0) {
				hotelDao.saveAll(list);
			}

			logger.info("========== 导入hotel数据完成 ==========");

		} else {
			logger.info("未发现" + hotel + "文件");
		}
	}

	private void uploadLandmark() throws Exception {

		// 导入landmark数据

		File landmarkFile = new File(landmark);

		if (landmarkFile.exists()) {

			Field[] landmarkFields = getField(Landmark.class);
			CsvReader csvReader = new CsvReader(new FileInputStream(landmarkFile), ',', Charset.forName("UTF-8"));
			csvReader.readHeaders();

			int i = 0;
			
			List<Landmark> list = new ArrayList<>();

			while (csvReader.readRecord()) {

				try {
					csvReader.getRawRecord();
					Landmark o = new Landmark();

					for (Field field : landmarkFields) {
						field.setAccessible(true);

						String fieldname = field.getName();

						if (StringUtils.isNotBlank(csvReader.get(fieldname))) {

							field.set(o, csvReader.get(fieldname));
						} else {
							if (field.getGenericType().toString().equals("class java.lang.String")) {
								field.set(o, "");
							}
						}
					}

					if (o.getLat() != null && o.getLng() != null) {
						o.setLocation(new StringBuffer().append(o.getLat()).append(",").append(o.getLng()).toString());
					}
					
					list.add(o);
					i++;
					
					if(i % 1000 == 0) {
						landmarkDao.saveAll(list);
						list = null;
						list = new ArrayList<>();
					}
					
					logger.info("已成功导入landmark数据: " + i);
				} catch (Exception e) {
					logger.error(e.getMessage());
					continue;
				}
			}
			
			if(list.size() > 0) {
				landmarkDao.saveAll(list);
			}
			
			logger.info("========== 导入landmark数据完成 ==========");

		} else {
			logger.info("未发现" + landmark + "文件");
		}
	}

	@SuppressWarnings("rawtypes")
	private Field[] getField(Class clazz) {
		return clazz.getDeclaredFields();  
	}

}
 