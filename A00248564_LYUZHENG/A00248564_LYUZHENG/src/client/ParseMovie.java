package client;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import server.Movie;
import server.Actor;

public class ParseMovie {

	boolean inMovies = false;
	boolean inMovie = false;
	boolean inId = false;
	boolean inName = false;
	boolean inDirector = false;
	boolean inDate = false;
	boolean inPrice = false;
	boolean inPlace = false;
	boolean inCabinet = false;
	
	Movie currentMovie = new Movie();
	List<Movie> currentMovieList = new ArrayList<Movie>();
	
	public List<Movie> doParseMovie(String s) {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser pullParser = factory.newPullParser();
			pullParser.setInput(new StringReader(s));
			processDocument(pullParser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentMovieList; 
	}
	
	public void processDocument(XmlPullParser pullParser) throws XmlPullParserException, IOException {
		int eventType = pullParser.getEventType();
		do {
			if (eventType == XmlPullParser.START_DOCUMENT) {
				System.out.println("Start document");
			} else if (eventType == XmlPullParser.END_DOCUMENT) {
				System.out.println("End document");
			} else if (eventType == XmlPullParser.START_TAG) {
				processStartElement(pullParser);
			} else if (eventType == XmlPullParser.END_TAG) {
				processEndElement(pullParser);
			} else if (eventType == XmlPullParser.TEXT) {
				//System.out.println("processText");
				processText(pullParser);
			}
			eventType = pullParser.next();
		} while (eventType != XmlPullParser.END_DOCUMENT);
	}
	
	public void processStartElement(XmlPullParser event) {
		String name = event.getName();
		if (name.equals("movies")) {
			inMovies = true;
			currentMovieList = new ArrayList<Movie>();
		}
		else if (name.equals("Movie")) {
			inMovie = true;
			currentMovie = new Movie();
		} else if (name.equals("id")) {
			inId = true;
		} else if (name.equals("name")) {
			inName = true;
		} else if (name.equals("director")) {
			inDirector = true;
		} else if (name.equals("date")) {
			inDate = true;
		} else if (name.equals("price")) {
			inPrice = true;
		} else if (name.equals("place")) {
			inPlace = true;
		} 
	}
	
	public void processText(XmlPullParser event) throws XmlPullParserException {
		if (inId) {
			String s = event.getText();
			currentMovie.setId(Integer.parseInt(s));
		}
		if (inName) {
			String s = event.getText();
			currentMovie.setName(s);
		
		}
		if (inDirector) {
			String s = event.getText();
			currentMovie.setDirector(s);
		}
		if (inDate) {
			String s = event.getText();
			currentMovie.setDate(s);
		}
		if (inPrice) {
			String s = event.getText();
			currentMovie.setPrice(s);
		}
		if (inPlace) {
			String s = event.getText();
			currentMovie.setPlace(s);
		}
		
	}	
	
	public void processEndElement(XmlPullParser event) {
		String name = event.getName();
		
		if (name.equals("movies")) {
			inMovies = false;
		}
		else if (name.equals("Movie")) {
			inMovie = false;
			currentMovieList.add(currentMovie);
		} else if (name.equals("id")) {
			inId = false;
		} else if (name.equals("name")) {
			inName = false;
		} else if (name.equals("director")) {
			inDirector = false;
		} else if (name.equals("date")) {
			inDate = false;
		} else if (name.equals("price")) {
			inPrice = false;
		} else if (name.equals("place")) {
			inPlace = false;
		} 
	}


}
