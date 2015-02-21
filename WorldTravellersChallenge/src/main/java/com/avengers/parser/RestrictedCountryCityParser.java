package com.avengers.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import com.avengers.model.*;

import com.avengers.constants.FilePath;

public class RestrictedCountryCityParser {

	private Set<CountryCity> restrictedCountryCity;
	
	public Set<CountryCity> getRestrictedCountryCity() {
		return restrictedCountryCity;
	}

	RestrictedCountryCityParser(String filePath) throws IOException {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			String[] temp;
			line = br.readLine();
			
	        while (line != null) {
	        	line=line.trim();
	        	temp=line.split(":");
	        	
	        	CountryCity countryCityObj = new CountryCity();
	        	if(temp[0] != null && temp[1] != null ) {
	        		countryCityObj.setCountry(temp[0]);
	        		countryCityObj.setCity(temp[1]);
	        		
	        		restrictedCountryCity.add(countryCityObj);
	        	}
	            line = br.readLine();
	        }
	        
		}catch(FileNotFoundException e) {
			
		}
	
	}
	
	
}
