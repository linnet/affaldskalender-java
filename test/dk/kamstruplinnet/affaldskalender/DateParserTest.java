package dk.kamstruplinnet.affaldskalender;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class DateParserTest extends TestCase {
	private static final String data = "	<tr>\n" + 
			"		<td colspan=\"2\"><img src=\"g/popup_afhentningstider_boxtop.png\" alt=\"\" /></td>\n" + 
			"	</tr>\n" + 
			"	<tr>\n" + 
			"		<td class=\"boxelement\"><img src=\"g/popup_afhentningstider_storskrald.jpg\" alt=\"\" /><br /><br /></td>\n" + 
			"	<td class=\"boxelement\"><div class=\"title\">Storskrald</div>Der hentes storskrald den 23.01.09, 24.04.09, 24.07.09, 23.10.09.</td>\n" + 
			"</tr>\n" + 
			"	<tr>\n" + 
			"	<td class=\"boxbottom\" colspan=\"2\"><img src=\"g/popup_afhentningstider_boxbottom.png\" alt=\"\" /></td>\n" + 
			"	</tr>\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"	<tr>\n" + 
			"		<td colspan=\"2\"><img src=\"g/popup_afhentningstider_boxtop.png\" alt=\"\" /></td>\n" + 
			"	</tr>\n" + 
			"	<tr><td class=\"boxelement\"><img src=\"g/popup_afhentningstider_haveaffald.jpg\" alt=\"\" /><br /><br /></td>\n" + 
			"<td class=\"boxelement\"><div class=\"title\">Haveaffald</div>Der hentes haveaffald den 04.03.09, 01.04.09, 06.05.09, 03.06.09, 08.07.09, 05.08.09, 09.09.09, 07.10.09, 11.11.09.</td>\n" + 
			"</tr>\n" + 
			"	<tr>\n" + 
			"	<td class=\"boxbottom\" colspan=\"2\"><img src=\"g/popup_afhentningstider_boxbottom.png\" alt=\"\" /></td>\n" + 
			"	</tr>\n" + 
			"\n" + 
			"	<tr>\n" + 
			"		<td colspan=\"2\"><img src=\"g/popup_afhentningstider_boxtop.png\" alt=\"\" /></td>\n" + 
			"	</tr>\n" + 
			"	<tr><td class=\"boxelement\"><img src=\"g/popup_afhentningstider_papir.jpg\" alt=\"\" /><br /><br /></td>\n" + 
			"<td class=\"boxelement\"><div class=\"title\">Papiraffald</div>\n" + //  
			"    Afhentes den 09.01.09, 06.03.09, 01.05.09, 26.06.09, 21.08.09, 16.10.09, 11.12.09. Husk at stille beholderen ud p? fortovet senest kl. 06:00 p? t?mmedagen.</td>\n" + 
			"</tr>\n" + 
			"	<tr>\n" + 
			"	<td class=\"boxbottom\" colspan=\"2\"><img src=\"g/popup_afhentningstider_boxbottom.png\" alt=\"\" /></td>\n" + 
			"	</tr>\n" + 
			"\n" + 
			"	<tr>\n" + 
			"		<td colspan=\"2\"><img src=\"g/popup_afhentningstider_boxtop.png\" alt=\"\" /></td>\n" + 
			"	</tr>\n" + 
			"	<tr><td class=\"boxelement\"><img src=\"g/popup_afhentningstider_farligt.jpg\" alt=\"\" /><br /><br /></td>\n" + 
			"<td class=\"boxelement\"><div class=\"title\">Farligt affald og sm?t elektronikaffald</div>\n" + 
			"    Hentes den 19.03.09, 18.06.09, 17.09.09, 17.12.09. Husk kun at stille boksen frem hvis den skal t?mmes.</td>\n" + 
			"</tr>\n" + 
			"	<tr>\n" + 
			"	<td class=\"boxbottom\" colspan=\"2\"><img src=\"g/popup_afhentningstider_boxbottom.png\" alt=\"\" /></td>\n" + 
			"	</tr>\n" + 
			"\n" + 
			"	<tr>\n" + 
			"		<td colspan=\"2\"><img src=\"g/popup_afhentningstider_boxtop.png\" alt=\"\" /></td>\n" + 
			"	</tr>\n" + 
			"	<tr><td class=\"boxelement\"><img src=\"g/popup_afhentningstider_pvc.jpg\" alt=\"\" /><br /><br /></td>\n" + 
			"<td class=\"boxelement\"><div class=\"title\">PVC og impr?gneret tr?</div>\n" + 
			"    Afhentes den 27.01.09, 27.04.09, 27.07.09, 27.10.09. Dit PVC og impr?gneret tr? skal stilles samme sted som dit storskrald, men i en bunke for sig.</td>\n" + 
			"</tr>\n" + 
			"	<tr>\n" + 
			"	<td class=\"boxbottom\" colspan=\"2\"><img src=\"g/popup_afhentningstider_boxbottom.png\" alt=\"\" /></td>\n" + 
			"	</tr>\n" + 
			"\n" + 
			"	<tr>\n" + 
			"		<td colspan=\"2\"><img src=\"g/popup_afhentningstider_boxtop.png\" alt=\"\" /></td>\n" + 
			"	</tr>\n" + 
			"	<tr><td class=\"boxelement\"><img src=\"g/popup_afhentningstider_elektronik.jpg\" alt=\"\" /><br /><br /></td>\n" + 
			"<td class=\"boxelement\"><div class=\"title\">Elektronik, k?lem?bler og pap &amp; karton</div>\n" + 
			"\n" + 
			"    Afhentes den 26.01.09, 27.04.09, 27.07.09, 26.10.09. Dit elektronikaffald, dine k?lem?bler og dit pap &amp; karton skal stilles samme sted som dit storskrald, men i en bunke hver for sig.</td>\n" + 
			"</tr>\n" + 
			"	<tr>\n" + 
			"	<td class=\"boxbottom\" colspan=\"2\"><img src=\"g/popup_afhentningstider_boxbottom.png\" alt=\"\" /></td>\n" + 
			"	</tr>\n" + 
			"\n";
	
	public void testGrupper() {
		DataParser parser = new DataParser(data);
		parser.parse();
		
		DatePackage dates = parser.getDates();

		List<String> garbageTypes = dates.getGarbageTypes();
		assertEquals(6, garbageTypes.size());
		assertTrue(garbageTypes.contains("Storskrald"));
		assertTrue(garbageTypes.contains("Haveaffald"));
		assertTrue(garbageTypes.contains("Papiraffald"));
		assertTrue(garbageTypes.contains("Farligt"));
		assertTrue(garbageTypes.contains("PVC"));
		assertTrue(garbageTypes.contains("Elektronik"));
	}
	
	public void testStorskrald() {
		DataParser parser = new DataParser(data);
		parser.parse();
		
		DatePackage dates = parser.getDates();

		List<Date> garbageDates = dates.getDates("Storskrald");
		assertEquals(4, garbageDates.size());
		assertEquals(new GregorianCalendar(2009, 0, 23).getTime(), garbageDates.get(0));
		assertEquals(new GregorianCalendar(2009, 3, 24).getTime(), garbageDates.get(1));
		assertEquals(new GregorianCalendar(2009, 6, 24).getTime(), garbageDates.get(2));
		assertEquals(new GregorianCalendar(2009, 9, 23).getTime(), garbageDates.get(3));
		// 23.01.09, 24.04.09, 24.07.09, 23.10.09
		
	}
	
	public void testHaveaffald() {
		DataParser parser = new DataParser(data);
		parser.parse();
		
		DatePackage dates = parser.getDates();

		List<Date> garbageDates = dates.getDates("Haveaffald");

		assertEquals(9, garbageDates.size());
		assertEquals(new GregorianCalendar(2009, 2, 4).getTime(), garbageDates.get(0));
		assertEquals(new GregorianCalendar(2009, 3, 1).getTime(), garbageDates.get(1));
		assertEquals(new GregorianCalendar(2009, 4, 6).getTime(), garbageDates.get(2));
		assertEquals(new GregorianCalendar(2009, 5, 3).getTime(), garbageDates.get(3));
		assertEquals(new GregorianCalendar(2009, 6, 8).getTime(), garbageDates.get(4));
		assertEquals(new GregorianCalendar(2009, 7, 5).getTime(), garbageDates.get(5));
		assertEquals(new GregorianCalendar(2009, 8, 9).getTime(), garbageDates.get(6));
		assertEquals(new GregorianCalendar(2009, 9, 7).getTime(), garbageDates.get(7));
		assertEquals(new GregorianCalendar(2009, 10, 11).getTime(), garbageDates.get(8));
		// 04.03.09, 01.04.09, 06.05.09, 03.06.09, 08.07.09, 05.08.09, 09.09.09, 07.10.09, 11.11.09
	}

	public void testPapiraffald() {
		DataParser parser = new DataParser(data);
		parser.parse();

		DatePackage dates = parser.getDates();

		List<Date> garbageDates = dates.getDates("Papiraffald");

		assertEquals(7, garbageDates.size());
		assertEquals(new GregorianCalendar(2009, 0, 9).getTime(), garbageDates.get(0));
		assertEquals(new GregorianCalendar(2009, 2, 6).getTime(), garbageDates.get(1));
		assertEquals(new GregorianCalendar(2009, 4, 1).getTime(), garbageDates.get(2));
		assertEquals(new GregorianCalendar(2009, 5, 26).getTime(), garbageDates.get(3));
		assertEquals(new GregorianCalendar(2009, 7, 21).getTime(), garbageDates.get(4));
		assertEquals(new GregorianCalendar(2009, 9, 16).getTime(), garbageDates.get(5));
		assertEquals(new GregorianCalendar(2009, 11, 11).getTime(), garbageDates.get(6));
		// 09.01.09, 06.03.09, 01.05.09, 26.06.09, 21.08.09, 16.10.09, 11.12.09
	}
	
	public void testElektronik() {
		DataParser parser = new DataParser(data);
		parser.parse();
		
		DatePackage dates = parser.getDates();

		List<Date> garbageDates = dates.getDates("Elektronik");

		assertEquals(4, garbageDates.size());
		assertEquals(new GregorianCalendar(2009, 0, 26).getTime(), garbageDates.get(0));
		assertEquals(new GregorianCalendar(2009, 3, 27).getTime(), garbageDates.get(1));
		assertEquals(new GregorianCalendar(2009, 6, 27).getTime(), garbageDates.get(2));
		assertEquals(new GregorianCalendar(2009, 9, 26).getTime(), garbageDates.get(3));
		// 26.01.09, 27.04.09, 27.07.09, 26.10.09
	}

	public void testFarligt() {
		DataParser parser = new DataParser(data);
		parser.parse();

		DatePackage dates = parser.getDates();

		List<Date> garbageDates = dates.getDates("Farligt");

		assertEquals(4, garbageDates.size());
		assertEquals(new GregorianCalendar(2009, 2, 19).getTime(), garbageDates.get(0));
		assertEquals(new GregorianCalendar(2009, 5, 18).getTime(), garbageDates.get(1));
		assertEquals(new GregorianCalendar(2009, 8, 17).getTime(), garbageDates.get(2));
		assertEquals(new GregorianCalendar(2009, 11, 17).getTime(), garbageDates.get(3));
		// 19.03.09, 18.06.09, 17.09.09, 17.12.09
	}

	public void testPvc() {
		DataParser parser = new DataParser(data);
		parser.parse();

		DatePackage dates = parser.getDates();

		List<Date> garbageDates = dates.getDates("PVC");

		assertEquals(4, garbageDates.size());
		assertEquals(new GregorianCalendar(2009, 0, 27).getTime(), garbageDates.get(0));
		assertEquals(new GregorianCalendar(2009, 3, 27).getTime(), garbageDates.get(1));
		assertEquals(new GregorianCalendar(2009, 6, 27).getTime(), garbageDates.get(2));
		assertEquals(new GregorianCalendar(2009, 9, 27).getTime(), garbageDates.get(3));
		// 27.01.09, 27.04.09, 27.07.09, 27.10.09
	}

}
