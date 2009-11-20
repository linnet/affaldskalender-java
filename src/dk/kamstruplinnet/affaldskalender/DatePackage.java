package dk.kamstruplinnet.affaldskalender;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DatePackage implements Serializable {
	private static final long serialVersionUID = -1274871642335287947L;
	
	private final Map<String, List<Date>> dates = new HashMap<String, List<Date>>();
	
	public void addGarbageType(String garbageType, List<Date> dateList) {
		dates.put(garbageType, dateList);
	}
	
	public List<String> getGarbageTypes() {
		Set<String> garbageTypes = dates.keySet();
		return new ArrayList<String>(garbageTypes);
	}
	
	public List<Date> getDates(String garbageType) {
		return dates.get(garbageType);
	}
}
