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
		
		//add more pages to this for more results 
		int pages = 5;
		for(int i = 1; i < pages; i++) {
			//change this for different search queries
		sites.add("http://www.indexnz.com/Top/Computers-and-Internet/Software/Software-Firms/" + i);
		}
		
		for(String site : sites) {
		SiteHTMLGETTER getter = new SiteHTMLGETTER(site);
		
		combinedHTML += getter.getSiteHTML();
		}
		
		//This regex string is what wraps around a url on this website, for other sites
		//change this regex such that its in the appropriate format
		SiteTagSearcher searcher = new SiteTagSearcher(combinedHTML, "><a href=(.+?) rel=");
		
		
		searcher.getBetweenElements();
		searcher.printElements();
		
		SiteEmailGetter emailGetter = new SiteEmailGetter(searcher.getBetweenElements());
		ArrayList<String> emails = emailGetter.getEmailsFromSites();
		
		System.out.println("\n\nEmails found on employer sites were:");
		
		//lazy way to sanatize email duplicates
		Set<String> hashmails = new HashSet<>();
		hashmails.addAll(emails);
		emails.clear();
		emails.addAll(hashmails);
		
		//print all emails, remove brackets for easy copypastarino
		System.out.println(Arrays.toString(emails.toArray()).replaceAll("[\\[\\]]", ""));
		
		
	}



}
