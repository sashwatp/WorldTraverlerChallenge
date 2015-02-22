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
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        line = br.readLine();

        while (line != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                CountryCity countryCityObj = null;
                try {
                    countryCityObj = CountryCityParser.parse(line);
                } catch (Exception e) {
                    System.out.println("Invalid Visited CountryCity: " + line);
                    continue;
                }
                visitedCountryCity.add(countryCityObj);
            }
            line = br.readLine();
        }

    }
}
