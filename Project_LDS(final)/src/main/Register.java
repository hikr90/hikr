package main;

import java.io.Serializable;

public class Register implements Serializable{
	private String name; 
	private String phone; 
	private int m_price = 0;
	private String m_rating = "등급없음";

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getM_price() {
		return m_price;
	}
	public void setM_price(int m_price) {
		this.m_price = m_price;
	}
	public String getM_rating() {
		return m_rating;
	}
	public void setM_rating(String m_rating) {
		this.m_rating = m_rating;
	}
}
