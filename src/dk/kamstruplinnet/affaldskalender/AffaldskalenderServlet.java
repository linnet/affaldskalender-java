package dk.kamstruplinnet.affaldskalender;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class AffaldskalenderServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		PrintWriter writer = resp.getWriter();
		writer.println("Hello, world\n");
		writer.println(req.getHeader("User-Agent"));
	}
}
