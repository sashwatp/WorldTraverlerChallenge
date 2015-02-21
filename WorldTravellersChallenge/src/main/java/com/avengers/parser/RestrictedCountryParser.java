package com.avengers.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RestrictedCountryParser {

	private Set<String> restrictedCountries;

	public Set<String> getRestrictedCountries() {
		return restrictedCountries;
	}

	public RestrictedCountryParser(String filePath) throws IOException {

		restrictedCountries = new HashSet<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			line = br.readLine();

			while (line != null) {
				line = line.trim();
				if (!line.isEmpty()) {
					restrictedCountries.add(line);
				}
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {

		}

	}
}
