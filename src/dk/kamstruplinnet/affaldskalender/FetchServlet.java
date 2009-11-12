package dk.kamstruplinnet.affaldskalender;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FetchServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
	
		String kkUrl = "http://affaldkbh.appspot.com/affaldskalender?adresse=";
//		String kkUrl = "http://localhost:8080/affaldskalender?adresse=";
//		String kkUrl = "http://kk.sites.itera.dk/apps/kk_afhentningstider/afhentningstider.asp?mode=detalje&id=";
//		String kkUrl = "http://kk.sites.itera.dk/apps/kk_afhentningstider/afhentningstider.asp?result=yes&vej=";
		
		DataFetcher fetcher = new DataFetcher(kkUrl);
		String data = fetcher.fetch("M%F8llebakken%2034");
		
		resp.getWriter().println("Data fetched: " + data);
	}
}
