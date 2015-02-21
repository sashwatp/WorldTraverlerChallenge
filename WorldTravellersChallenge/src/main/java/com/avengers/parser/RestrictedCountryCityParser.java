package com.avengers.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.avengers.model.CountryCity;

public class RestrictedCountryCityParser {

	private Set<CountryCity> restrictedCountryCity;

	public Set<CountryCity> getRestrictedCountryCity() {
		return restrictedCountryCity;
	}

	public RestrictedCountryCityParser(String filePath) throws IOException {
		restrictedCountryCity = new HashSet<CountryCity>();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		String[] temp;
		line = br.readLine();

		while (line != null) {
			line = line.trim();
			if (!line.isEmpty()) {
				temp = line.split(":");
				if (temp.length == 2) {
					CountryCity countryCityObj = new CountryCity();

					countryCityObj.setCountry(temp[0]);
					countryCityObj.setCity(temp[1]);

					restrictedCountryCity.add(countryCityObj);

				}
			}
			line = br.readLine();
		}

	}

}
