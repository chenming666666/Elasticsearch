package com.example.elasticsearch.document;

public class HotelFacility {

	private String facilityName;

	private String facilityValue;

	private String facilityNameEn;

	private String facilityValueEn;

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		if (facilityName == null) {
			this.facilityName = "";
		} else {
			this.facilityName = facilityName;
		}

	}

	public String getFacilityValue() {
		return facilityValue;
	}

	public void setFacilityValue(String facilityValue) {

		if (facilityValue == null) {
			this.facilityValue = "";
		} else {
			this.facilityValue = facilityValue;
		}

	}

	public String getFacilityNameEn() {
		return facilityNameEn;
	}

	public void setFacilityNameEn(String facilityNameEn) {

		if (facilityNameEn == null) {
			this.facilityNameEn = "";
		} else {
			this.facilityNameEn = facilityNameEn;
		}

	}

	public String getFacilityValueEn() {
		return facilityValueEn;
	}

	public void setFacilityValueEn(String facilityValueEn) {
		if (facilityValueEn == null) {
			this.facilityValueEn = "";
		} else {
			this.facilityValueEn = facilityValueEn;
		}

	}
}
