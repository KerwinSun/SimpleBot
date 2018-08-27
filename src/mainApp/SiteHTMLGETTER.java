package mainApp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class SiteHTMLGETTER {

	private String _siteHTML;
	private String _site;

	public SiteHTMLGETTER(String site) {

		_site = site;

	}

	public String getSiteHTML() {

		try {

			URL obj = new URL(_site);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setReadTimeout(5000);
			conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
			conn.addRequestProperty("User-Agent", "Mozilla/4.76"); 
		    conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestMethod("GET");
	        conn.setDoOutput(true);
	        conn.connect();

	        
			System.out.println("Request URL ... " + _site);

			boolean redirect = false;

			// normally, 3xx is redirect
			int status = conn.getResponseCode();
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP
					|| status == HttpURLConnection.HTTP_MOVED_PERM
						|| status == HttpURLConnection.HTTP_SEE_OTHER)
				redirect = true;
			}

			System.out.println("Response Code ... " + status);

			if (redirect) {

				// get redirect url from "location" header field
				String newUrl = conn.getHeaderField("Location");

				// open the new connnection again
				conn = (HttpURLConnection) new URL(newUrl).openConnection();
				conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				conn.addRequestProperty("User-Agent", "Mozilla/4.76"); 
			    conn.setRequestProperty("Content-Type", "application/json");
		        conn.setRequestMethod("GET");
		        conn.setDoOutput(true);
		        conn.connect();
										
				System.out.println("Redirect to URL : " + newUrl);

			}
			
			InputStream is;
			if (conn.getResponseCode() >= 400) {
			    is = conn.getErrorStream();
			} else {
			    is = conn.getInputStream();
			}
			
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				
				_siteHTML = _siteHTML + "\n" + inputLine;
				
			}
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return _siteHTML;
	}


}
