package com.avengers.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.avengers.model.CountryCity;

public class WantToVisitParser {

	private Set<CountryCity> wantToVisitCountryCity;

	public Set<CountryCity> getWantToVisitCountryCity() {
		return wantToVisitCountryCity;
	}

	public WantToVisitParser(String filePath) throws IOException {

		wantToVisitCountryCity = new HashSet<CountryCity>();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		String[] temp;
		line = br.readLine();

		while (line != null) {

			line = line.trim();
			if (!line.isEmpty()) {
				temp = line.split(":");

				CountryCity countryCityObj = new CountryCity();
				if (temp.length == 2) {
					countryCityObj.setCountry(temp[0]);
					countryCityObj.setCity(temp[1]);

				} else {
					countryCityObj.setCountry(temp[0]);
					countryCityObj.setCity(null);

				}

				wantToVisitCountryCity.add(countryCityObj);

			}
			line = br.readLine();
		}

	}

}
