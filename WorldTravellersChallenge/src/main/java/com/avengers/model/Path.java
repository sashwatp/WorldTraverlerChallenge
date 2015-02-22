package com.avengers.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Path {

	private CountryCity destination;
	private LinkedHashSet<CountryCity> dependentCities;
	private boolean isValid;
	private boolean hasRestricted;
	private int pathLength;
	private PathSet pathSet;

	public Path(CountryCity destination, PathSet pathSet) {
		this.destination = destination;
		this.dependentCities = new LinkedHashSet<CountryCity>();
		isValid = true;
		hasRestricted = false;
		pathLength = 0;
		this.pathSet = pathSet;
	}

	public void addCountry(CountryCity country) {
		dependentCities.add(country);
		if (country.isRestricted()) {
			setHasRestricted(true);
		}
		if (!country.isVisited()) {
			setValid(false);
		}
		pathLength++;
	}

	public void markCountryAsVisited(CountryCity countryCity) {
		for (CountryCity tempCity : dependentCities) {
			if (tempCity.equals(countryCity) && !tempCity.isVisited()) {
				tempCity.setVisited(true);
				reEvaluate();
			}
		}
	}

	private void reEvaluate() {
		boolean isValid = true;
		if (!this.isValid) {
			for (CountryCity countryCity : dependentCities) {
				if (!countryCity.isVisited()) {
					isValid = false;
					break;
				}
			}

			this.isValid = isValid;
			if (isValid) {
				pathSet.reEvaluate(this);
			}
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

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean HasRestricted() {
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

	@Override
	public String toString() {
		@SuppressWarnings("rawtypes")
		List pathStringList = new ArrayList(dependentCities);
		System.out.println(pathStringList.size());
		StringBuilder pathString = new StringBuilder();
		for (int i = dependentCities.size() - 1; i >= 0; i--) {
			if (i != 0) {
				pathString.append((pathStringList.get(i)).toString()).append(
						"/");

			} else {
				pathString.append((pathStringList.get(i)).toString());
			}
		}
		return pathString.toString();
	}
}
