package com.avengers.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.avengers.parser.RestrictedCountryCityParser;
import com.avengers.parser.RestrictedCountryParser;


public class RestrictedCountryParserTest {

	final static String FILE_PATH = "RestrictedCities.txt";
	
	RestrictedCountryCityParser systemUnderTest;
	
	@Before
	public void setup() throws IOException{

	 
		systemUnderTest = new RestrictedCountryCityParser(FILE_PATH);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void test() {
	
		Set<String> restrictedCountries = new HashSet<String>();
		restrictedCountries.add("Bhopal");
		restrictedCountries.add("Shimla");
		restrictedCountries.add("Lucknow");
		restrictedCountries.add("Newark");
		
		Assert.assertEquals(restrictedCountries, systemUnderTest.getRestrictedCountryCity());
		
	}

	
}
