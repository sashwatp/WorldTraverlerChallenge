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
			System.out
					.println("1:Shortest dependent and restriction free route for WantToVisit cities.");
			System.out.println("2:Number of \"No route\" results");
			System.out
					.println("3:Enter country:city to find shortest or longest dependent and restriction free route");
			System.out
					.println("4:Enter country:city to find all the dependent routes for the city");
			System.out.println("5:Enter country:city to be marked as visited");
			System.out.println("0:Exit\n\n\n");

			Scanner stdin = new Scanner(System.in);
			Integer input = null;
			try {
				input = stdin.nextInt();
			} catch (Exception e) {
				System.err.println("Invalid input");
				continue;
			}
			switch (input) {
			case 1:

				if (processAdapter.processWantToVisit(true) == 0) {
					System.out.println("No want to visit cities");
				}
				break;
			case 2:
				System.out
						.println("---------------------------------------------------------------------------------");

				System.out.println("Number of \"No Route\" count :"
						+ processAdapter.getNoRouteCount());

				break;
			case 3:
				System.out
						.println("---------------------------------------------------------------------------------");
				System.out.println("Enter (Format: country:city)");
				stdin = new Scanner(System.in);
				countryCityString = getInputLine();
				try {
					countryCity = CountryCityParser.parse(countryCityString);
				} catch (Exception e) {
					System.out
							.println("Invalid input. Should be of the format country:city");
					continue;
				}

				Path shortestPath = processAdapter
						.getShortestUnrestrictedPath(countryCity);
				Path longestPath = processAdapter
						.getLongestUnrestrictedPath(countryCity);

				if (shortestPath == null && longestPath == null) {
					System.out.println("Route for " + countryCity.toString()
							+ " : No Route");
				} else {

					System.out.println("Route[Shortest]: \t" + shortestPath);
					System.out.println("Route[Longest] : \t" + longestPath);
				}
				break;
			case 4:
				boolean notValidPath= true;
				System.out
						.println("---------------------------------------------------------------------------------");
				System.out.println("Enter (Format: country:city)");
				stdin = new Scanner(System.in);
				countryCityString = getInputLine();
				try {
					countryCity = CountryCityParser.parse(countryCityString);
				} catch (Exception e) {
					System.out
							.println("Invalid input. Should be of the format country:city");
					continue;
				}
				List<Path> paths = processAdapter.getPaths(countryCity);
				if (paths.size() == 0) {
					System.out.println("No Route");
					continue;
				}
				for (Path pathVariable : paths) {
					if (pathVariable.isValid()) {
						notValidPath = false;
						if (pathVariable.HasRestricted()) {
							System.out.println(pathVariable.toString()
									+ "[Is Restricted]");
						} else {
							System.out.println(pathVariable.toString()
									+ "[Restriction Free]");
						}
					}
				}
				
				if(notValidPath) {
					System.out.println("No route");
				}
				

				break;
			case 5:
				System.out
						.println("---------------------------------------------------------------------------------");
				System.out.println("Enter country:city");
				stdin = new Scanner(System.in);
				countryCityString = getInputLine();
				try {
					countryCity = CountryCityParser.parse(countryCityString);
				} catch (Exception e) {
					System.out
							.println("Invalid input. Should be of the format country:city");
					continue;
				}
				processAdapter.addVisitedCountryCity(countryCity);
				System.out.println("Added "+countryCityString+" to visited cities list");
				break;
			case 0:
				System.out.println("Exiting Program.");
				System.exit(0);

				break;
			default:
				System.out.println("Invalid input");

			}
		}
	}

	private static String getInputLine() {
		Scanner stdin = new Scanner(System.in);
		String countryCityString = null;

		while ((countryCityString = stdin.nextLine()).isEmpty())
			;

		return countryCityString;
	}
}
