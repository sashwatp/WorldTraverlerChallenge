package com.avengers.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.avengers.constants.BeanIdentifiers;

public class Main {
	public static void main(String[] args) {
		String countryCity;

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"beans.xml");

		ApplicationInitializer applicationInitializer = (ApplicationInitializer) applicationContext
				.getBean(BeanIdentifiers.APPLICATION_INITIALIZER);

		applicationInitializer.init();

		while (true) {
			System.out.println("Input:");
			System.out
					.println("1:Shortest dependent and restriction free route for WantToVisit cities.");
			System.out.println("2:Number of \"No route\" results");
			System.out
					.println("3:Enter country:city to find shortest or longest dependent and restriction free route");
			System.out
					.println("4:Enter country:city to find all the dependent routes for the city");
			System.out.println("5:Enter country:city to be marked as visited");
			System.out.println("0:Exit");

			Scanner stdin = new Scanner(System.in);
			Integer input = stdin.nextInt();
			switch (input) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				System.out.println("Enter country:city");
				stdin = new Scanner(System.in);
				countryCity = stdin.toString();
				if (validateInput(countryCity)) {

				} else {
					System.out
							.println("Invalid input. Should be of the format country:city");
				}
				break;
			case 4:
				System.out.println("Enter country:city");
				stdin = new Scanner(System.in);
				countryCity = stdin.toString();
				if (validateInput(countryCity)) {

				} else {
					System.out
							.println("Invalid input. Should be of the format country:city");
				}
				break;
			case 5:
				System.out.println("Enter country:city");
				stdin = new Scanner(System.in);
				countryCity = stdin.toString();
				if (validateInput(countryCity)) {

				} else {
					System.out
							.println("Invalid input. Should be of the format country:city");
				}
				break;
			case 0:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid input");

			}
		}
	}

	public static boolean validateInput(String input) {
		String[] temp = input.split(":");
		if (temp[0].isEmpty() || temp[1].isEmpty()) {
			return false;
		}
		return true;
	}
}
