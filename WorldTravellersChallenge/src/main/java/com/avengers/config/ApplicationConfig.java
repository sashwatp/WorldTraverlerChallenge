package com.avengers.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.avengers.constants.BeanIdentifiers;
import com.avengers.constants.FilePath;
import com.avengers.model.CountryCity;
import com.avengers.model.Path;
import com.avengers.model.PathSet;
import com.avengers.parser.DependentParser;
import com.avengers.parser.RestrictedCountryCityParser;
import com.avengers.parser.RestrictedCountryParser;
import com.avengers.parser.VisitedCountryCityParser;
import com.avengers.parser.WantToVisitParser;

@Configuration
public class ApplicationConfig {

	@Bean(name = BeanIdentifiers.RESTRICTRED_COUNTRY_CITIES_SET)
	public Set<CountryCity> restrictedCountryCitiesSet() throws IOException {
		return new RestrictedCountryCityParser(
				FilePath.RESTRICTED_COUNTRY_CITIES).getRestrictedCountryCity();
	}

	@Bean(name = BeanIdentifiers.RESTRICTRED_COUNTRIES_SET)
	public Set<String> restrictedCountriesSet() throws IOException {
		return new RestrictedCountryParser(FilePath.RESTRICTED_COUNTIRES)
				.getRestrictedCountries();
	}

	@Bean(name = BeanIdentifiers.VISITED_CITIES)
	public Set<CountryCity> visitedCities() throws IOException {
		return new VisitedCountryCityParser(FilePath.VISITED_COUTRY_CITIES)
				.getVisitedCountryCity();
	}

	@Bean(name = BeanIdentifiers.WANT_TO_VISIT_CITIES)
	public Set<CountryCity> wantToVisit() throws IOException {
		return new WantToVisitParser(FilePath.WANT_TO_VISIT)
				.getWantToVisitCountryCity();
	}

	@Bean(name = BeanIdentifiers.DESTINATION_PATHS_MAP)
	public Map<CountryCity, PathSet> destinationPaths() {
		return new HashMap<CountryCity, PathSet>();
	}

	@Bean(name = BeanIdentifiers.COUNTRY_CITY_PATHS_REVERSE_MAP)
	public Map<CountryCity, List<Path>> countryCityPathsReverseMap() {
		return new HashMap<CountryCity, List<Path>>();
	}

	@Bean(name = BeanIdentifiers.DEPENDENT_PATHS)
	public List<String[]> dependentPaths() throws IOException {
		return new DependentParser(FilePath.DEPENDENT).getDependentPath();
	}
}
