package dk.kamstruplinnet.affaldskalender;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

public class DataFetcher {
	private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.6; en-US; rv:1.9.2b2) Gecko/20091108 Firefox/3.6b2";
	private String fetchUrl;
	
	public DataFetcher(String fetchUrl) {
		this.fetchUrl = fetchUrl;
	}

	public String fetch(String address) throws IOException {
		URL url = new URL(fetchUrl + encodeAddress(address));
		
		URLFetchService fetchService = URLFetchServiceFactory.getURLFetchService();
		HTTPRequest request = new HTTPRequest(url);

		request.setHeader(new HTTPHeader("User-Agent", USER_AGENT));
		request.setHeader(new HTTPHeader("Accept", "*/*"));

		HTTPResponse response = fetchService.fetch(request);
		byte[] content = response.getContent();

		String result = new String(content);
		return result;
	}

	private String encodeAddress(String address) throws UnsupportedEncodingException {
		return URLEncoder.encode(address, "ISO-8859-1");
	}

}
