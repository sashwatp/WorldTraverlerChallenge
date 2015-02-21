package com.avengers.model;

import java.util.HashSet;
import java.util.Set;

public class Path {

	private CountryCity destination;
	private Set<CountryCity> dependentCities;
	private boolean isValid;
	private boolean hasRestricted;
	private int pathLength;

	public Path(CountryCity destination) {
		this.destination = destination;
		this.dependentCities = new HashSet<CountryCity>();
		isValid = true;
		hasRestricted = false;
		pathLength = 0;
	}

	public void addCountry(CountryCity country) {
		dependentCities.add(country);
		if (country.isRestricted()) {
			setHasRestricted(true);
		}
		if (!country.isVisited()) {
			setValid(false);
		}
	}

	public CountryCity getDestination() {
		return destination;
	}

	public void setDestination(CountryCity destination) {
		this.destination = destination;
	}

	public Set<CountryCity> getDependentCities() {
		return dependentCities;
	}

	public void setDependentCities(Set<CountryCity> dependentCities) {
		this.dependentCities = dependentCities;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isHasRestricted() {
		return hasRestricted;
	}

	public void setHasRestricted(boolean hasRestricted) {
		this.hasRestricted = hasRestricted;
	}

	public int getPathLength() {
		return pathLength;
	}

	public void setPathLength(int pathLength) {
		this.pathLength = pathLength;
	}

}
