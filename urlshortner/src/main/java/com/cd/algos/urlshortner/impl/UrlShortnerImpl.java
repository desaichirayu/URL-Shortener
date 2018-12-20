package com.cd.algos.urlshortner.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.net.MalformedURLException;
import java.net.URL;

import com.cd.algos.urlshortner.model.UrlShortner;

/**
 * Class contains the core logic for the implementation of URL shortener
 * 
 * @author Chirayu Desai
 *
 */
public class UrlShortnerImpl implements UrlShortner {

	// Constants
	private static final String PROTOCOL = "Https://";
	private static final String DOMAIN_NAME = "cd.ly/";
	private static final String CHARACTERS = "~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private int index = 1;

	// Caches
	private Map<Integer, String> sUrlMappings = new HashMap<Integer, String>();
	private Map<Integer, String> lUrlMappings = new HashMap<Integer, String>();

	/**
	 * {@inheritDoc}
	 */
	public String getShortUrl(String longUrl) throws MalformedURLException {
		// Check if URL is in proper format
		URL mainURL = new URL(longUrl);

		// if short URL in cache directly return it
		if (lUrlMappings.containsValue(mainURL.toString())) {
			return PROTOCOL + DOMAIN_NAME
					+ sUrlMappings.get(getKeyByValue(lUrlMappings, mainURL.toString()));
		} else {
			// Transform long URL to short
			StringBuilder stringBuilder = new StringBuilder();
			int temp = index;
			while (temp > 0) {
				stringBuilder.append(CHARACTERS.charAt(temp % CHARACTERS.length()));
				temp = temp / CHARACTERS.length();
			}
			String sUrl = stringBuilder.reverse().toString();
			// update caches
			lUrlMappings.put(index, mainURL.toString());
			sUrlMappings.put(index, sUrl);

			// increment index
			index++;
			return PROTOCOL + DOMAIN_NAME + sUrl;
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws NoSuchElementException
	 *             : when no corresponding original long URL exists for a given
	 *             short URL Example: cd.ly/xyz
	 */
	public String getLongUrl(String shortUrl) throws MalformedURLException {
		// Check if URL is in proper format
		URL mainURL = new URL(shortUrl);
		String sURL = mainURL.getFile().substring(1);

		// Compute cache key
		int key = 0;
		for (int i = 0; i < sURL.length(); i++) {
			key = key * CHARACTERS.length() + CHARACTERS.indexOf(sURL.charAt(i));
		}

		// Throw exception if not found else return the original long URL
		if (!lUrlMappings.containsKey(key)) {
			throw new NoSuchElementException();
		} else {
			return lUrlMappings.get(key);
		}
	}

	/**
	 * Get Key for value for one to one mapping
	 * 
	 * @param map
	 *            Map to search
	 * @param value
	 *            value
	 * @return key
	 */
	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
		for (Entry<T, E> entry : map.entrySet()) {
			if (Objects.equals(value, entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

}
