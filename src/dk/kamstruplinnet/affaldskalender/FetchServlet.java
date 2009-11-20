package dk.kamstruplinnet.affaldskalender;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FetchServlet extends HttpServlet {
	private final String kkUrl = "http://kk.sites.itera.dk/apps/kk_afhentningstider/afhentningstider.asp?mode=detalje&id=";

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/calendar");
	
		String address = "Kildel¿bet 11";

		DatesCache cache = DatesCache.getInstance();
		
		DatePackage result = cache.findInCache(address);
		if (result == null) {
			DataFetcher fetcher = new DataFetcher(kkUrl);
			String data = fetcher.fetch(address);
	
			DataParser parser = new DataParser(data);
			parser.parse();
			
			result = parser.getDates();
			cache.putInCache(address, result);
		}

		PrintWriter writer = resp.getWriter();

		ICalGenerator outputter = new ICalGenerator(writer);
		outputter.generate(result);
	}
}
