package com.avengers.main;

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
}
