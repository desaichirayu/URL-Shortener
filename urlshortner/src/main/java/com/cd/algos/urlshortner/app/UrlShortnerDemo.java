package com.cd.algos.urlshortner.app;

import java.net.MalformedURLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.cd.algos.urlshortner.impl.UrlShortnerImpl;
import com.cd.algos.urlshortner.model.UrlShortner;

/**
 * This class Demos the URL shortener through simple java command line
 * 
 * @author Chirayu Desai
 *
 */

public class UrlShortnerDemo {

	public static void main(String[] args) {
		UrlShortner urls = new UrlShortnerImpl();
		Scanner sc = new Scanner(System.in);
		Scanner ins = new Scanner(System.in);
		int choice = -1;
		System.out.println("Enter\n\"1\"" + " to get Shortened URL \n" + "\"2\""
				+ " to get orginal URL from long URL" + "\n Any other number to exit");
		
		try {
			do {
				if (sc.hasNextInt()) {
					choice = sc.nextInt();
				}
				switch (choice) {
				case 1:
					System.out.println("Enter the URL you want to shorten:");
					String lURL = ins.nextLine();
					System.out.println("The shortened URL is:");
					System.out.println(urls.getShortUrl(lURL));
					break;
				case 2:
					System.out.println("Enter the shortened URL for which you want to original:");
					String sURL = ins.nextLine();
					System.out.println("The Orignal URL was: ");
					System.out.println(urls.getLongUrl(sURL));
					break;
				default:
					System.out.println("Invalid Input");
				}
			} while (choice == 1 || choice == 2);

		} catch (MalformedURLException e) {
			System.out.println("ERROR: the URL is not in proper format!");
			System.out.println("Example: Https://www.google.com/drive");
		} catch (NoSuchElementException nse) {
			System.out.print("Not Found");
		}
		sc.close();
		ins.close();
	}

}
