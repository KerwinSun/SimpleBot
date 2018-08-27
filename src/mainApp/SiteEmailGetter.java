package mainApp;

import java.util.ArrayList;
import java.util.List;

public class SiteEmailGetter {

	private ArrayList<String> _sites;
	private ArrayList<String> _emails;
	
	public SiteEmailGetter(ArrayList<String> sites) {
		
		_emails = new ArrayList<String>();
		_sites = sites;
		
	}
	
	public ArrayList<String> getEmailsFromSites() {
		
		for(String site : _sites) {

			SiteHTMLGETTER getter = new SiteHTMLGETTER(site);
			String HTML = getter.getSiteHTML();
			//get encoded emails
			SiteTagSearcher searcher = new SiteTagSearcher(HTML, "[a-zA-Z0-9_.+-]+\\&#64;[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
			
			
			_emails.addAll(searcher.getMatchElements());

			//get clear text emails
			searcher = new SiteTagSearcher(HTML, "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
			
			_emails.addAll(searcher.getMatchElements());
		}
		
		return _emails;
	}
	
	
}
