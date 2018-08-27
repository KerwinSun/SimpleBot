package mainApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	
	public static void main(String[] args) {
	    
		String combinedHTML ="";
		ArrayList<String> sites = new ArrayList<>();
		sites.add("http://www.indexnz.com/Top/Computers-and-Internet/Software/Software-Firms/");
		sites.add("http://www.indexnz.com/Top/Computers-and-Internet/Software/Software-Firms/2");
		sites.add("http://www.indexnz.com/Top/Computers-and-Internet/Software/Software-Firms/3");
		
		
		for(String site : sites) {
		SiteHTMLGETTER getter = new SiteHTMLGETTER(site);
		
		combinedHTML += getter.getSiteHTML();
		}
		
		
		SiteTagSearcher searcher = new SiteTagSearcher(combinedHTML, "><a href=(.+?) rel=");
		searcher.getBetweenElements();
		searcher.printElements();
		
		SiteEmailGetter emailGetter = new SiteEmailGetter(searcher.getBetweenElements());
		ArrayList<String> emails = emailGetter.getEmailsFromSites();
		
		//lazy way to sanatize email duplicates
		Set<String> hashmails = new HashSet<>();
		hashmails.addAll(emails);
		emails.clear();
		emails.addAll(hashmails);
		
		
		System.out.println(Arrays.toString(emails.toArray()));
		
		
	}



}
