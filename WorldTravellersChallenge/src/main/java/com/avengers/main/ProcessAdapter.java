package com.avengers.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.avengers.constants.BeanIdentifiers;
import com.avengers.model.CountryCity;
import com.avengers.model.Path;
import com.avengers.model.PathSet;

@Component(value = BeanIdentifiers.PROCESS_ADAPTER)
public class ProcessAdapter {

    @Resource(name = BeanIdentifiers.DESTINATION_PATHS_MAP)
    private Map<CountryCity, PathSet> destinationCityPaths;

    @Resource(name = BeanIdentifiers.COUNTRY_CITY_PATHS_REVERSE_MAP)
    private Map<CountryCity, List<Path>> countryCityReverseMap;

    public Path getShortestUnrestrictedPath(CountryCity countryCity) {
        PathSet pathSet = null;
        Path path = null;
        if ((pathSet = destinationCityPaths.get(countryCity)) != null) {
            path = pathSet.getShortestUnrestrictedPath();
        }
        return path;
    }

    public Path getShortestPath(CountryCity countryCity) {
        PathSet pathSet = null;
        Path path = null;
        if ((pathSet = destinationCityPaths.get(countryCity)) != null) {
            path = pathSet.getShortestPath();
        }
        return path;
    }

    public List<Path> getPaths(CountryCity countryCity) {
    	
    	PathSet pathSet = destinationCityPaths.get(countryCity);
    	if (pathSet != null) {
    		return pathSet.getPaths();
    	}
    	return Collections.emptyList();
    	
    }
    
    public Path getLongestUnrestrictedPath(CountryCity countryCity) {
        PathSet pathSet = null;
        Path path = null;
        if ((pathSet = destinationCityPaths.get(countryCity)) != null) {
            path = pathSet.getLongestUnrestrictedPath();
        }
        return path;
    }

    public void addVisitedCountryCity(CountryCity countryCity) {
        List<Path> paths = countryCityReverseMap.get(countryCity);

        if (paths != null) {
            for (Path path : paths) {
                path.markCountryAsVisited(countryCity);
            }
        }
    }
    
    
    
}
