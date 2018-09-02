package com.example.elasticsearch.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "hotel", type = "hotel")
public class Hotel {

	@Id
	private String hotel_id;

	@Field(type = FieldType.Nested)
	private List<HotelPolicy> hotel_policy;

	@Field(type = FieldType.Auto)
	private String location;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String hotel_name;

	@Field(type = FieldType.Text)
	private String hotel_name_en;

	@Field(type = FieldType.Text)
	private String old_hotel_id;

	@Field(type = FieldType.Text)
	private String hotel_group;

	@Field(type = FieldType.Keyword)
	private String company_type;

	@Field(type = FieldType.Keyword)
	private String from_channel;

	@Field(type = FieldType.Keyword)
	private String star_level;

	@Field(type = FieldType.Keyword)
	private String old_star_level;

	@Field(type = FieldType.Keyword)
	private String chain_brand;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String chain_brand_name;

	@Field(type = FieldType.Keyword)
	private String old_chain_brand;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String old_chain_brand_name;

	@Field(type = FieldType.Keyword)
	private String is_online_sign_up;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String address;

	@Field(type = FieldType.Text)
	private String tel;

	@Field(type = FieldType.Text)
	private String email;

	@Field(type = FieldType.Text)
	private String fax;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String linkman;

	@Field(type = FieldType.Text)
	private String linkman_phone;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String linkman_other;

	@Field(type = FieldType.Keyword)
	private String priority;

	@Field(type = FieldType.Text)
	private String longitude;

	@Field(type = FieldType.Text)
	private String latitude;

	@Field(type = FieldType.Text)
	private String country;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String country_name;

	@Field(type = FieldType.Text)
	private String old_country;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String old_country_name;

	@Field(type = FieldType.Text)
	private String province;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String province_name;

	@Field(type = FieldType.Text)
	private String old_province;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String old_province_name;

	@Field(type = FieldType.Text)
	private String city;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String city_name;

	@Field(type = FieldType.Text)
	private String old_city;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String old_city_name;

	@Field(type = FieldType.Text)
	private String county_code;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String county_name;

	@Field(type = FieldType.Text)
	private String old_county_code;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String old_county_name;

	@Field(type = FieldType.Text)
	private String landmark;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String landmark_name;

	@Field(type = FieldType.Text)
	private String old_landmark;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String old_landmark_name;

	@Field(type = FieldType.Text)
	private String business_area;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String[] business_area_name;

	@Field(type = FieldType.Text)
	private String old_business_area;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String[] old_business_area_name;

	@Field(type = FieldType.Keyword)
	private String examine_type;

	@Field(type = FieldType.Keyword)
	private String hotel_state;

	@Field(type = FieldType.Keyword)
	private String valid_flag;

	@Field(type = FieldType.Text)
	private String hotel_score;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String announcement;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String introduction;

	@Field(type = FieldType.Text)
	private String setup_year;

	@Field(type = FieldType.Text)
	private String decorate_year;

	@Field(type = FieldType.Nested)
	private List<HotelFacility> hotel_facility;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String[] tags;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String hotel_tips;

	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String support_card;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
	private String create_time;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
	private String version_no;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
	private String latest_order_time;

	public String getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(String hotel_id) {
		this.hotel_id = hotel_id;
	}

	public String getOld_hotel_id() {
		return old_hotel_id;
	}

	public void setOld_hotel_id(String old_hotel_id) {
		this.old_hotel_id = old_hotel_id;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public String getHotel_name_en() {
		return hotel_name_en;
	}

	public void setHotel_name_en(String hotel_name_en) {
		this.hotel_name_en = hotel_name_en;
	}

	public String getHotel_group() {
		return hotel_group;
	}

	public void setHotel_group(String hotel_group) {
		this.hotel_group = hotel_group;
	}

	public String getCompany_type() {
		return company_type;
	}

	public void setCompany_type(String company_type) {
		this.company_type = company_type;
	}

	public String getFrom_channel() {
		return from_channel;
	}

	public void setFrom_channel(String from_channel) {
		this.from_channel = from_channel;
	}

	public String getStar_level() {
		return star_level;
	}

	public void setStar_level(String star_level) {
		this.star_level = star_level;
	}

	public String getOld_star_level() {
		return old_star_level;
	}

	public void setOld_star_level(String old_star_level) {
		this.old_star_level = old_star_level;
	}

	public String getChain_brand() {
		return chain_brand;
	}

	public void setChain_brand(String chain_brand) {
		this.chain_brand = chain_brand;
	}

	public String getChain_brand_name() {
		return chain_brand_name;
	}

	public void setChain_brand_name(String chain_brand_name) {
		this.chain_brand_name = chain_brand_name;
	}

	public String getOld_chain_brand() {
		return old_chain_brand;
	}

	public void setOld_chain_brand(String old_chain_brand) {
		this.old_chain_brand = old_chain_brand;
	}

	public String getOld_chain_brand_name() {
		return old_chain_brand_name;
	}

	public void setOld_chain_brand_name(String old_chain_brand_name) {
		this.old_chain_brand_name = old_chain_brand_name;
	}

	public String getIs_online_sign_up() {
		return is_online_sign_up;
	}

	public void setIs_online_sign_up(String is_online_sign_up) {
		this.is_online_sign_up = is_online_sign_up;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkman_phone() {
		return linkman_phone;
	}

	public void setLinkman_phone(String linkman_phone) {
		this.linkman_phone = linkman_phone;
	}

	public String getLinkman_other() {
		return linkman_other;
	}

	public void setLinkman_other(String linkman_other) {
		this.linkman_other = linkman_other;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getOld_country() {
		return old_country;
	}

	public void setOld_country(String old_country) {
		this.old_country = old_country;
	}

	public String getOld_country_name() {
		return old_country_name;
	}

	public void setOld_country_name(String old_country_name) {
		this.old_country_name = old_country_name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getOld_province() {
		return old_province;
	}

	public void setOld_province(String old_province) {
		this.old_province = old_province;
	}

	public String getOld_province_name() {
		return old_province_name;
	}

	public void setOld_province_name(String old_province_name) {
		this.old_province_name = old_province_name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getOld_city() {
		return old_city;
	}

	public void setOld_city(String old_city) {
		this.old_city = old_city;
	}

	public String getOld_city_name() {
		return old_city_name;
	}

	public void setOld_city_name(String old_city_name) {
		this.old_city_name = old_city_name;
	}

	public String getCounty_code() {
		return county_code;
	}

	public void setCounty_code(String county_code) {
		this.county_code = county_code;
	}

	public String getCounty_name() {
		return county_name;
	}

	public void setCounty_name(String county_name) {
		this.county_name = county_name;
	}

	public String getOld_county_code() {
		return old_county_code;
	}

	public void setOld_county_code(String old_county_code) {
		this.old_county_code = old_county_code;
	}

	public String getOld_county_name() {
		return old_county_name;
	}

	public void setOld_county_name(String old_county_name) {
		this.old_county_name = old_county_name;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getLandmark_name() {
		return landmark_name;
	}

	public void setLandmark_name(String landmark_name) {
		this.landmark_name = landmark_name;
	}

	public String getOld_landmark() {
		return old_landmark;
	}

	public void setOld_landmark(String old_landmark) {
		this.old_landmark = old_landmark;
	}

	public String getOld_landmark_name() {
		return old_landmark_name;
	}

	public void setOld_landmark_name(String old_landmark_name) {
		this.old_landmark_name = old_landmark_name;
	}

	public String getBusiness_area() {
		return business_area;
	}

	public void setBusiness_area(String business_area) {
		this.business_area = business_area;
	}

	public String[] getBusiness_area_name() {
		return business_area_name;
	}

	public void setBusiness_area_name(String[] business_area_name) {
		this.business_area_name = business_area_name;
	}

	public String getOld_business_area() {
		return old_business_area;
	}

	public void setOld_business_area(String old_business_area) {
		this.old_business_area = old_business_area;
	}

	public String[] getOld_business_area_name() {
		return old_business_area_name;
	}

	public void setOld_business_area_name(String[] old_business_area_name) {
		this.old_business_area_name = old_business_area_name;
	}

	public String getExamine_type() {
		return examine_type;
	}

	public void setExamine_type(String examine_type) {
		this.examine_type = examine_type;
	}

	public String getHotel_state() {
		return hotel_state;
	}

	public void setHotel_state(String hotel_state) {
		this.hotel_state = hotel_state;
	}

	public String getValid_flag() {
		return valid_flag;
	}

	public void setValid_flag(String valid_flag) {
		this.valid_flag = valid_flag;
	}

	public String getHotel_score() {
		return hotel_score;
	}

	public void setHotel_score(String hotel_score) {
		this.hotel_score = hotel_score;
	}

	public String getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getSetup_year() {
		return setup_year;
	}

	public void setSetup_year(String setup_year) {
		this.setup_year = setup_year;
	}

	public String getDecorate_year() {
		return decorate_year;
	}

	public void setDecorate_year(String decorate_year) {
		this.decorate_year = decorate_year;
	}

	public List<HotelFacility> getHotel_facility() {
		return hotel_facility;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getHotel_tips() {
		return hotel_tips;
	}

	public void setHotel_tips(String hotel_tips) {
		this.hotel_tips = hotel_tips;
	}

	public String getSupport_card() {
		return support_card;
	}

	public void setSupport_card(String support_card) {
		this.support_card = support_card;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getVersion_no() {
		return version_no;
	}

	public void setVersion_no(String version_no) {
		this.version_no = version_no;
	}

	public String getLatest_order_time() {
		return latest_order_time;
	}

	public void setLatest_order_time(String latest_order_time) {
		this.latest_order_time = latest_order_time;
	}

	public List<HotelPolicy> getHotel_policy() {
		return hotel_policy;
	}

	public void setHotel_policy(List<HotelPolicy> hotel_policy) {
		this.hotel_policy = hotel_policy;
	}

	public void setHotel_facility(List<HotelFacility> hotel_facility) {
		this.hotel_facility = hotel_facility;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
