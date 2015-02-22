package com.avengers.parser;

import com.avengers.model.CountryCity;

public class CountryCityParser {

    public static CountryCity parse(String countryCityString) {
        CountryCity countryCity = new CountryCity();

        String[] temp = countryCityString.split(":");
        if (temp.length == 2) {
            countryCity.setCountry(temp[0].trim());
            countryCity.setCity(temp[1].trim());
        } else {
            throw new RuntimeException("Invalid Country city String");
        }
        return countryCity;
    }
}
