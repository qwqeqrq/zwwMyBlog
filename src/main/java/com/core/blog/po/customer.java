package com.core.blog.po;

public class customer {
	private int cust_id;
	private String cust_name;
	private int cust_user_id ;
	private int cust_creat_id;
	private String cust_source;
	private String cust_industry;
	private String cust_limkman ;
	private String cust_phone ;
	private String cust_mobile ;
	private String cust_zipcode ;
	private String cust_address ;
	private String cust_level ;
	private String cust_createtime;
	private Integer start;
	private Integer rows;
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public int getCust_user_id() {
		return cust_user_id;
	}
	public void setCust_user_id(int cust_user_id) {
		this.cust_user_id = cust_user_id;
	}
	public int getCust_creat_id() {
		return cust_creat_id;
	}
	public void setCust_creat_id(int cust_creat_id) {
		this.cust_creat_id = cust_creat_id;
	}
	public String getCust_source() {
		return cust_source;
	}
	public void setCust_source(String cust_source) {
		this.cust_source = cust_source;
	}
	public String getCust_industry() {
		return cust_industry;
	}
	public void setCust_industry(String cust_industry) {
		this.cust_industry = cust_industry;
	}
	public String getCust_limkman() {
		return cust_limkman;
	}
	public void setCust_limkman(String cust_limkman) {
		this.cust_limkman = cust_limkman;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_mobile() {
		return cust_mobile;
	}
	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}
	public String getCust_zipcode() {
		return cust_zipcode;
	}
	public void setCust_zipcode(String cust_zipcode) {
		this.cust_zipcode = cust_zipcode;
	}
	public String getCust_address() {
		return cust_address;
	}
	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
	}
	public String getCust_level() {
		return cust_level;
	}
	public void setCust_level(String cust_level) {
		this.cust_level = cust_level;
	}
	public String getCust_createtime() {
		return cust_createtime;
	}
	public void setCust_createtime(String cust_createtime) {
		this.cust_createtime = cust_createtime;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "customer [cust_id=" + cust_id + ", cust_name=" + cust_name
				+ ", cust_user_id=" + cust_user_id + ", cust_creat_id="
				+ cust_creat_id + ", cust_source=" + cust_source
				+ ", cust_industry=" + cust_industry + ", cust_limkman="
				+ cust_limkman + ", cust_phone=" + cust_phone
				+ ", cust_mobile=" + cust_mobile + ", cust_zipcode="
				+ cust_zipcode + ", cust_address=" + cust_address
				+ ", cust_level=" + cust_level + ", cust_createtime="
				+ cust_createtime + ", start=" + start + ", rows=" + rows + "]";
	}

}
