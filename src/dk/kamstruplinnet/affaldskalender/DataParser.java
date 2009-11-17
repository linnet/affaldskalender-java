package dk.kamstruplinnet.affaldskalender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
	private final Pattern pattern = Pattern.compile("<div class=\"title\">(\\w+).*?</div>\\s*.*[Hh]entes.*? den (\\d{2}.+\\d{2})\\."); //"<div class=\"title\">(\\w+).*?</div>.*[Hh]entes.*? den (\\d{2}.+\\d{2})\\.", 

	private final String data;
	private final DatePackage dates = new DatePackage(); 

	public DataParser(String data) {
		this.data = data;
	}

	public void parse() {
		Matcher matcher = pattern.matcher(data);
		while (matcher.find()) {
			String type = matcher.group(1);
			String dateGroup = matcher.group(2);
			
			List<Date> dateList = parseDates(dateGroup);
			
			dates.addGarbageType(type, dateList);
		}
	}

	private List<Date> parseDates(String dateGroup) {
		String[] dateStrings = dateGroup.split(", ");
		List<Date> dateList = new ArrayList<Date>();
		
		for (String dateString : dateStrings) {
			try {
				Date date = dateFormat.parse(dateString);
				dateList.add(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dateList;
	}
	
	public DatePackage getDates() {
		return dates;
	}
}
