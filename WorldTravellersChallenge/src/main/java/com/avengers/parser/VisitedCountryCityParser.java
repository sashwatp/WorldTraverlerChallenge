package com.avengers.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.avengers.model.CountryCity;

public class VisitedCountryCityParser {

	private Set<CountryCity> visitedCountryCity;

	public Set<CountryCity> getVisitedCountryCity() {
		return visitedCountryCity;
	}

	public VisitedCountryCityParser(String filePath) throws IOException {

		visitedCountryCity = new HashSet<CountryCity>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			String[] temp;
			line = br.readLine();

			while (line != null) {
				line = line.trim();
				if (!line.isEmpty()) {
					temp = line.split(":");

					CountryCity countryCityObj = new CountryCity();
					if (temp[0] != null && temp[1] != null) {
						countryCityObj.setCountry(temp[0]);
						countryCityObj.setCity(temp[1]);

						visitedCountryCity.add(countryCityObj);
					}
				}
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {

		}

	}

}
