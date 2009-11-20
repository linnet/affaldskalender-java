package dk.kamstruplinnet.affaldskalender;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

public class DatesCache {
	public static final Logger _log = Logger.getLogger(DatesCache.class
			.getName());

	private static DatesCache instance;

	private Cache cache;

	public static synchronized DatesCache getInstance() {
		if (instance == null) {
			instance = new DatesCache();
		}
		return instance;
	}

	public DatesCache() {
		try {
			CacheFactory cacheFactory = CacheManager.getInstance()
					.getCacheFactory();
			cache = cacheFactory.createCache(Collections.emptyMap());
		} catch (CacheException e) {
			// Log stuff
			_log.log(Level.WARNING, "Error in creating the Cache");
		}
	}
	
	public DatePackage findInCache(String address) {
		if (cache.containsKey(address)) {
			return (DatePackage) cache.get(address);
		}
		
		return null;
	}
	
	public void putInCache(String address, DatePackage dates) {
		cache.put(address, dates);
	}
}
