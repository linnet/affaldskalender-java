package dk.kamstruplinnet.affaldskalender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class DataFetcher {
	private String fetchUrl;
	
	public DataFetcher(String fetchUrl) {
		this.fetchUrl = fetchUrl;
	}

	public String fetch(String address) throws IOException {
		String encodedAddress = URLEncoder.encode(address, "UTF-8"); 
		URL url = new URL(fetchUrl + encodedAddress);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("User-Agent", "Mozilla/5.0"); // (Macintosh; U; Intel Mac OS X 10.6; en-US; rv:1.9.2b2) Gecko/20091108 Firefox/3.6b2"
//		Map<String, List<String>> headerFields = connection.getHeaderFields();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		StringBuilder sb = new StringBuilder();
		int ch;
		while ((ch = reader.read()) != -1) {
			sb.append((char) ch);
		}
		String result = sb.toString();
		return result;
	}

}
