package dk.kamstruplinnet.affaldskalender;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;

public class ICalGenerator {

	private final PrintWriter writer;

	public ICalGenerator(PrintWriter writer) {
		this.writer = writer;
	}

	public void generate(DatePackage dates) {
		Calendar calendar = new Calendar();
		calendar.getProperties().add(new ProdId("-//Jesper Kamstrup Linnet//Affaldskalender 1.0//EN"));
		calendar.getProperties().add(Version.VERSION_2_0);
		calendar.getProperties().add(CalScale.GREGORIAN);
		
		for (String garbageType : dates.getGarbageTypes()) {
			List<Date> pickupDates = dates.getDates(garbageType);
			for (Date date : pickupDates) {
				net.fortuna.ical4j.model.Date icalDate = new net.fortuna.ical4j.model.Date(date);
				VEvent event = new VEvent(icalDate, garbageType);
				
				calendar.getComponents().add(event);
			}
		}

		writer.println(calendar);
	}

}
