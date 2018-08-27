package mainApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SiteTagSearcher {

	
	private ArrayList<String> foundElements = new ArrayList<String>();
	private final Pattern TAG_REGEX;
	private String _site;
	
	public SiteTagSearcher(String site, String REGEX) {
		
		TAG_REGEX = Pattern.compile(REGEX);
		_site = site;
	}
	

	public ArrayList<String> getBetweenElements () {
	    final Matcher matcher = TAG_REGEX.matcher(_site);
	    while (matcher.find()) {
	    	
	    	String str = matcher.group(1).replaceAll("\"", "");
	    	if(!foundElements.contains(str)) {
	        foundElements.add(str);
	    	}
	    }
	    return foundElements;
	}

	public ArrayList<String> getMatchElements() {

		if(_site != null) {
	    Matcher m = TAG_REGEX.matcher(_site);
		
	    while (m.find()) {
	    	if(!m.group().contains(".png")) {
	    	foundElements.add(m.group().replaceAll("&#64;", "@"));
	    	}
	    }
	    
		}
	    return foundElements;
	}
	
	
	public void printElements() {
		
		System.out.println(Arrays.toString(foundElements.toArray()));
		
	}


}
