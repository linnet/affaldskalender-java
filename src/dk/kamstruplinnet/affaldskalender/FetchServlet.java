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
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/calendar");
	
//		String kkUrl = "http://www.csvw.com/servlet/snoop?address=";
//		String kkUrl = "http://affaldkbh.appspot.com/affaldskalender?adresse=";
//		String kkUrl = "http://localhost:8080/affaldskalender?adresse=";
		String kkUrl = "http://kk.sites.itera.dk/apps/kk_afhentningstider/afhentningstider.asp?mode=detalje&id=";
//		String kkUrl = "http://kk.sites.itera.dk/apps/kk_afhentningstider/afhentningstider.asp?result=yes&vej=";
		
		DataFetcher fetcher = new DataFetcher(kkUrl);
		String data = fetcher.fetch("Kildel¿bet 11");

		DataParser parser = new DataParser(data);
		parser.parse();
		
		DatePackage dates = parser.getDates();

		PrintWriter writer = resp.getWriter();

		ICalGenerator outputter = new ICalGenerator(writer);
		outputter.generate(dates);
		
//		writer.println("Dates:");
//		for (String garbageType : dates.keySet()) {
//			List<Date> dateList = dates.get(garbageType);
//			writer.print("- " + garbageType + ": ");
//			DateFormat df = new SimpleDateFormat("dd.MM.yy");
//			for (Date date : dateList) {
//				writer.print(df.format(date));
//				writer.print(", ");
//			}
//			writer.println();
//		}
	}
}
