package com.avengers.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.avengers.constants.BeanIdentifiers;
import com.avengers.model.CountryCity;
import com.avengers.model.Path;
import com.avengers.model.PathSet;
import com.avengers.parser.CountryCityParser;

@Component(value = BeanIdentifiers.APPLICATION_INITIALIZER)
@DependsOn(BeanIdentifiers.WANT_TO_VISIT_CITIES)
public class ApplicationInitializer {

    @Resource(name = BeanIdentifiers.RESTRICTRED_COUNTRY_CITIES_SET)
    private Set<CountryCity> restrictedCountryCities;

    @Resource(name = BeanIdentifiers.RESTRICTRED_COUNTRIES_SET)
    private Set<String> restrictedCounties;

    @Resource(name = BeanIdentifiers.VISITED_CITIES)
    private Set<CountryCity> visitorCountryCities;

    @Resource(name = BeanIdentifiers.WANT_TO_VISIT_CITIES)
    private Set<CountryCity> wantToVisitCities;

    @Resource(name = BeanIdentifiers.DESTINATION_PATHS_MAP)
    private Map<CountryCity, PathSet> destinationCityPaths;

    @Resource(name = BeanIdentifiers.COUNTRY_CITY_PATHS_REVERSE_MAP)
    private Map<CountryCity, List<Path>> countryCityReverseMap;

    @Resource(name = BeanIdentifiers.DEPENDENT_PATHS)
    private List<String[]> dependentPaths;

    public void init() {
        for (String[] pathArray : dependentPaths) {

            CountryCity destination = extractCountryCity(pathArray[pathArray.length - 1]);

            PathSet pathSet = destinationCityPaths.get(destination);

            if (pathSet == null) {
                pathSet = new PathSet();
            }

            Path path = new Path(destination, pathSet);
            for (int i = pathArray.length - 2; i >= 0; i--) {
                CountryCity countryCity = extractCountryCity(pathArray[i]);
                path.addCountry(countryCity);
                addReversePathMapping(countryCity, path);
            }

            if (path.getPathLength() == 0) {
                continue;
            }

            pathSet.addPath(path);
            destinationCityPaths.put(destination, pathSet);

        }
        System.out.println("");
    }

    private void addReversePathMapping(CountryCity destination, Path path) {
        List<Path> existing = countryCityReverseMap.get(destination);

        if (existing == null) {
            existing = new ArrayList<Path>();
        }

        existing.add(path);

        countryCityReverseMap.put(destination, existing);
    }

    private CountryCity checkAndSetIfRestricted(CountryCity countryCity) {
        if (restrictedCounties.contains(countryCity.getCountry())) {
            countryCity.setRestricted(true);
        }

        if (restrictedCountryCities.contains(countryCity)) {
            countryCity.setRestricted(true);
        }
        return countryCity;
    }

    private CountryCity checkAndSetIfVisited(CountryCity countryCity) {
        if (visitorCountryCities.contains(countryCity)) {
            countryCity.setVisited(true);
        }
        return countryCity;
    }

    private CountryCity extractCountryCity(String countryCityString) {
        CountryCity countryCity = CountryCityParser.parse(countryCityString);
        countryCity = checkAndSetIfRestricted(countryCity);
        countryCity = checkAndSetIfVisited(countryCity);

        return countryCity;
    }
}
