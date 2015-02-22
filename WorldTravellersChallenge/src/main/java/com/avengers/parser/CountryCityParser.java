package com.avengers.parser;

import com.avengers.model.CountryCity;

public class CountryCityParser {

    public static CountryCity parse(String countryCityString) {
        CountryCity countryCity = new CountryCity();

        String[] temp = countryCityString.split(":");
        if (temp.length == 2) {
            String country = temp[0].trim();
            String city = temp[1].trim();
            if (!isValidCountryOrCity(country) || isValidCountryOrCity(city)) {
                throw new RuntimeException("Invalid Country or City");
            }
            countryCity.setCountry(country);
            countryCity.setCity(city);
        } else {
            throw new RuntimeException("Invalid Country city String");
        }
        return countryCity;
    }

    public static boolean isValidCountryOrCity(String input) {
        if (input.matches("[a-ZA-Z]")) {
            return true;
        }
        return false;
    }
}
