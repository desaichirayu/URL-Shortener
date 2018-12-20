package com.cd.algos.urlshortner.model;

import java.net.MalformedURLException;

/**
 * Interface for methods that return short and long URL given the other as input
 * 
 * @author Chirayu Desai
 *
 */
public interface UrlShortner {

	/**
	 * Creates a short representation of a long URL
	 * 
	 * @param String
	 *            longUrl the original URL that needs to be shortened
	 * @return String the shortened URL
	 * @throws MalformedURLException
	 *             : when the input URL is not in proper format
	 */
	public String getShortUrl(String longUrl) throws MalformedURLException;

	/**
	 * Finds a original representation of a shortened URL
	 * 
	 * @param shortUrl
	 *            String the shortened URL
	 * @return longUrl the original URL that was initially shortened
	 * @throws MalformedURLException
	 *             : when the input URL is not in proper format
	 */
	public String getLongUrl(String shortUrl) throws MalformedURLException;

}
