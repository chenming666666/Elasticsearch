package com.example.elasticsearch.document;

public class HotelPolicy {

	private String policyName;

	private String policyValue;

	private String policyNameEn;

	private String policyValueEn;

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		if (policyName == null) {
			this.policyName = "";
		} else {
			this.policyName = policyName;
		}

	}

	public String getPolicyValue() {
		return policyValue;
	}

	public void setPolicyValue(String policyValue) {
		if (policyValue == null) {
			this.policyValue = "";
		} else {
			this.policyValue = policyValue;
		}

	}

	public String getPolicyNameEn() {
		return policyNameEn;
	}

	public void setPolicyNameEn(String policyNameEn) {
		if (policyNameEn == null) {
			this.policyNameEn = "";
		} else {
			this.policyNameEn = policyNameEn;
		}

	}

	public String getPolicyValueEn() {
		return policyValueEn;
	}

	public void setPolicyValueEn(String policyValueEn) {
		if (policyValueEn == null) {
			this.policyValueEn = "";
		} else {
			this.policyValueEn = policyValueEn;
		}

	}
}
