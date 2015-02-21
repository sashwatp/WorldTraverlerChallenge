package com.avengers.model;

public class CountryCity {

	private String Country;
	private String City;
	private boolean isVisited;
	private boolean isRestricted;
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public boolean isVisited() {
		return isVisited;
	}
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	public boolean isRestricted() {
		return isRestricted;
	}
	public void setRestricted(boolean isRestricted) {
		this.isRestricted = isRestricted;
	}
	
}
