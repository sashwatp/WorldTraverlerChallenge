package com.avengers.main;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.avengers.constants.BeanIdentifiers;
import com.avengers.model.CountryCity;
import com.avengers.model.Path;
import com.avengers.model.PathSet;
import com.avengers.parser.CountryCityParser;

@Component(value = BeanIdentifiers.PROCESS_ADAPTER)
public class ProcessAdapter {

    private int noRouteCount = -1;

    @Resource(name = BeanIdentifiers.DESTINATION_PATHS_MAP)
    private Map<CountryCity, PathSet> destinationCityPaths;

    @Resource(name = BeanIdentifiers.COUNTRY_CITY_PATHS_REVERSE_MAP)
    private Map<CountryCity, List<Path>> countryCityReverseMap;

    @Resource(name = BeanIdentifiers.WANT_TO_VISIT_CITIES)
    private List<String> wantToVisit;

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

    public void addVisitedCountryCity(CountryCity countryCity) {
        List<Path> paths = countryCityReverseMap.get(countryCity);

        if (paths != null) {
             for (Path path : paths) {
                path.markCountryAsVisited(countryCity);
            }
        }
    }

    public void processWantToVisit(boolean doPrinting) {
        Path path = null;
        this.noRouteCount = 0;
        CountryCity countryCity = new CountryCity();
        for (String countryCityString : wantToVisit) {
            try{
                countryCity = CountryCityParser.parse(countryCityString);
            }catch (Exception e){
                System.out.println(" Invalid countryCity : "+ countryCityString);
            }
            path = getShortestUnrestrictedPath(countryCity);
            if (doPrinting) {
                System.out.println("Destination : " + countryCity.toString());

                if (path == null) {
                    System.out.println("Route       : No Route");
                    noRouteCount++;
                } else {
                    System.out.println("Route : " + path.toString());
                }
                System.out.println("--------------------------------------------------------------------------");
            }
        }
    }

    public int getNoRouteCount() {
        if (noRouteCount == -1) {
            processWantToVisit(false);
        }
        return noRouteCount;
    }
}
