package dk.kamstruplinnet.affaldskalender;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class AffaldskalenderServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		PrintWriter writer = resp.getWriter();
		writer.println("Hello, world\n");
		
		writer.println("Headers:");
		writer.println("-------");
		for (Enumeration headerNames = req.getHeaderNames(); headerNames.hasMoreElements(); ) {
			String headerName = (String) headerNames.nextElement();
			
			writer.println(headerName + "=");
			for (Enumeration headers = req.getHeaders(headerName); headers.hasMoreElements(); ) {
				String header = (String) headers.nextElement();
				writer.println("\t" + header);
			}
		}
	}
}
