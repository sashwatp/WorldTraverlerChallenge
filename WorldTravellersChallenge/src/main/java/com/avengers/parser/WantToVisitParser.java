package com.avengers.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import com.avengers.model.CountryCity;

public class WantToVisitParser {

	private Set<CountryCity> wantToVisitCountryCity;

	public Set<CountryCity> getWantToVisitCountryCity() {
		return wantToVisitCountryCity;
	}

	WantToVisitParser(String filePath) throws IOException {

		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			String[] temp;
			line = br.readLine();

			while (line != null) {
				line = line.trim();
				temp = line.split(":");

				CountryCity countryCityObj = new CountryCity();
				countryCityObj.setCountry(temp[0]);
				if (temp[1] == null) {
					countryCityObj.setCity(null);
				}else {
					countryCityObj.setCity(temp[1]);
				}
				wantToVisitCountryCity.add(countryCityObj);
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {

		}

	}

}
