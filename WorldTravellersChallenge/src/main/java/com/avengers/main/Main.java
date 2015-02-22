package com.avengers.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.avengers.constants.BeanIdentifiers;
import com.avengers.model.CountryCity;
import com.avengers.model.Path;
import com.avengers.parser.CountryCityParser;

public class Main {
	public static void main(String[] args) {
		String countryCityString;
		CountryCity countryCity = null;
		Path path = null;

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");

		ApplicationInitializer applicationInitializer = (ApplicationInitializer) context
				.getBean(BeanIdentifiers.APPLICATION_INITIALIZER);

		ProcessAdapter processAdapter = (ProcessAdapter) context
				.getBean(BeanIdentifiers.PROCESS_ADAPTER);

		applicationInitializer.init();

        while (true) {

            System.out
                    .println("===================================== Input: ==========================================");
            System.out.println("1:Shortest dependent and restriction free route for WantToVisit cities.");
            System.out.println("2:Number of \"No route\" results");
            System.out.println("3:Enter country:city to find shortest or longest dependent and restriction free route");
            System.out.println("4:Enter country:city to find all the dependent routes for the city");
            System.out.println("5:Enter country:city to be marked as visited");
            System.out.println("0:Exit\n\n\n");

            Scanner stdin = new Scanner(System.in);
            Integer input = stdin.nextInt();
            switch (input) {
            case 1:
                System.out.println("Generating the shortest dependent and restriction free route for WantToVisit.txt");
                System.out.println("---------------------------------------------------------------------------------");
                processAdapter.processWantToVisit(true);
                break;
            case 2:
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println("Number of \"No Route\" count :" + processAdapter.getNoRouteCount());
                System.out.println("---------------------------------------------------------------------------------");
                break;
            case 3:
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println("Enter (Format: country:city)");
                stdin = new Scanner(System.in);
                countryCityString = stdin.nextLine();
                try {
                    countryCity = CountryCityParser.parse(countryCityString);
                } catch (Exception e) {
                    System.out.println("Invalid input. Should be of the format country:city");
                    continue;
                }

				Path shortestPath = processAdapter
						.getShortestUnrestrictedPath(countryCity);
				Path longestPath = processAdapter
						.getLongestUnrestrictedPath(countryCity);

				if (shortestPath == null && longestPath == null) {
					System.out.println("Route : No Route");
				} else {

					System.out.println("Route[Shortest]: " + shortestPath);
					System.out.println("Route[Longest] : " + longestPath);
				}
				System.out.println("---------------------------------------------------------------------------------");
				break;
			case 4:
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("Enter (Format: country:city)");
				stdin = new Scanner(System.in);
				countryCityString = stdin.nextLine();
				try {
					countryCity = CountryCityParser.parse(countryCityString);
				} catch (Exception e) {
					System.out
							.println("Invalid input. Should be of the format country:city");
				}
				List<Path> paths = processAdapter.getPaths(countryCity);
				for (Path pathVariable : paths) {
					if (pathVariable.isValid()) {
						if (pathVariable.HasRestricted()) {
							System.out.println(pathVariable.toString()
									+ "[Is Restricted]");
						} else {
							System.out.println(pathVariable.toString()
									+ "[Restriction Free]");
						}
					}
				}
				if (path == null) {
					System.out.println("No Route");
				} else {
					System.out.println("Route Found: " + path.getPathLength());
				}
				System.out.println("---------------------------------------------------------------------------------");
				break;
			case 5:
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("Enter country:city");
				stdin = new Scanner(System.in);
				countryCityString = stdin.nextLine();
				try {
					countryCity = CountryCityParser.parse(countryCityString);
				} catch (Exception e) {
					System.out
							.println("Invalid input. Should be of the format country:city");
				}
				processAdapter.addVisitedCountryCity(countryCity);
				break;
			case 0:
				System.out.println("Exiting Program.");
				System.exit(0);
				System.out.println("---------------------------------------------------------------------------------");
				break;
			default:
				System.out.println("Invalid input");

			}
		}
	}
}
