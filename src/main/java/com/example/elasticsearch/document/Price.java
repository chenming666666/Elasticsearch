package com.example.elasticsearch.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "base_price", type = "base_price")
public class Price {

	@Id
	private String base_price_id;

	@Field(type = FieldType.Keyword)
	private String hotel_id;

	@Field(type = FieldType.Float)
	private Float base_price;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd")
	private String bizdate;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
	private String create_time;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
	private String update_time;

	public String getBase_price_id() {
		return base_price_id;
	}

	public void setBase_price_id(String base_price_id) {
		this.base_price_id = base_price_id;
	}

	public String getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(String hotel_id) {
		this.hotel_id = hotel_id;
	}

	public Float getBase_price() {
		return base_price;
	}

	public void setBase_price(Float base_price) {
		this.base_price = base_price;
	}

	public String getBizdate() {
		return bizdate;
	}

	public void setBizdate(String bizdate) {
		this.bizdate = bizdate;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
}
