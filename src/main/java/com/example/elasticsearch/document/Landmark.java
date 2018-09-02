package com.example.elasticsearch.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "landmark", type = "landmark")
public class Landmark {

	@Id
	private String landmark_code;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String landmark_name;

	@Field(type = FieldType.Keyword)
	private String landmark_type;

	@Field(type = FieldType.Keyword)
	private String valid_flag;

	@Field(type = FieldType.Text)
	private String city_code;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String city_name;

	@Field(type = FieldType.Text)
	private String belong_code;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String belong_name;

	@Field(type = FieldType.Keyword)
	private String hot_flag;

	@Field(type = FieldType.Text)
	private String lng;

	@Field(type = FieldType.Text)
	private String lat;

	@Field(type = FieldType.Keyword)
	private String priority;

	@Field(type = FieldType.Auto)
	private String location;

	public String getLandmark_code() {
		return landmark_code;
	}

	public void setLandmark_code(String landmark_code) {
		this.landmark_code = landmark_code;
	}

	public String getLandmark_name() {
		return landmark_name;
	}

	public void setLandmark_name(String landmark_name) {
		this.landmark_name = landmark_name;
	}

	public String getLandmark_type() {
		return landmark_type;
	}

	public void setLandmark_type(String landmark_type) {
		this.landmark_type = landmark_type;
	}

	public String getValid_flag() {
		return valid_flag;
	}

	public void setValid_flag(String valid_flag) {
		this.valid_flag = valid_flag;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getBelong_code() {
		return belong_code;
	}

	public void setBelong_code(String belong_code) {
		this.belong_code = belong_code;
	}

	public String getBelong_name() {
		return belong_name;
	}

	public void setBelong_name(String belong_name) {
		this.belong_name = belong_name;
	}

	public String getHot_flag() {
		return hot_flag;
	}

	public void setHot_flag(String hot_flag) {
		this.hot_flag = hot_flag;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
