package com.avengers.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.avengers.model.CountryCity;

public class WantToVisitParser {

    private List<String> wantToVisitCountryCity;

    public List<String> getWantToVisitCountryCity() {
        return wantToVisitCountryCity;
    }

    public WantToVisitParser(String filePath) throws IOException {

        wantToVisitCountryCity = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        String[] temp;
        line = br.readLine();

        while (line != null) {

            line = line.trim();
            if (!line.isEmpty()) {

                wantToVisitCountryCity.add(line);

            }
            line = br.readLine();
        }

    }

}
