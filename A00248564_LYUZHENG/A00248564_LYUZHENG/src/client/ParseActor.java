package client;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import server.Actor;

public class ParseActor {

	boolean inActors = false;
	boolean inActor = false;
	boolean inId = false;
	boolean inFName = false;
	boolean inSname = false;
	boolean inBirthday = false;
	boolean inPhone = false;
	boolean inEmail = false;
	boolean inAddress = false;

	//////////////////////////////////////////////////////////////////
	///////// 			Actor
	/////////////////////////////////////////////////////////////////
	
	Actor currentActor = new Actor();
	List<Actor> currentActorList = new ArrayList<Actor>();
	
	public List<Actor> doParseActor(String s) {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser pullParser = factory.newPullParser();
			pullParser.setInput(new StringReader(s));
			processDocument(pullParser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentActorList; 
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
		if (name.equals("Actors")) {
			inActors = true;
			currentActorList = new ArrayList<Actor>();
		}
		else if (name.equals("Actor")) {
			inActor = true;
			currentActor = new Actor();
		} else if (name.equals("id")) {
			inId = true;
		} else if (name.equals("fname")) {
			inFName = true;
		} else if (name.equals("sname")) {
			inSname = true;
		} else if (name.equals("birthday")) {
			inBirthday = true;
		} else if (name.equals("phone")) {
			inPhone = true;
		} else if (name.equals("email")) {
			inEmail = true;
		} else if (name.equals("address")) {
			inAddress = true;
		}
	}
	
	public void processText(XmlPullParser event) throws XmlPullParserException {
		if (inId) {
			String s = event.getText();
			currentActor.setId(Integer.parseInt(s));
		}
		if (inFName) {
			String s = event.getText();
			currentActor.setFname(s);
		
		}
		if (inSname) {
			String s = event.getText();
			currentActor.setSname(s);
		}
		if (inBirthday) {
			String s = event.getText();
			currentActor.setBirthday(s);
		}
		if (inPhone) {
			String s = event.getText();
			currentActor.setPhone(s);
		}
		if (inEmail) {
			String s = event.getText();
			currentActor.setEmail(s);
		}
		if (inAddress) {
			String s = event.getText();
			currentActor.setAddress(s);
		}
	}	
	
	public void processEndElement(XmlPullParser event) {
		String name = event.getName();
		
		if (name.equals("Actors")) {
			inActors = false;
		}
		else if (name.equals("Actor")) {
			inActor = false;
			currentActorList.add(currentActor);
		} else if (name.equals("id")) {
			inId = false;
		} else if (name.equals("fname")) {
			inFName = false;
		} else if (name.equals("sname")) {
			inSname = false;
		} else if (name.equals("birthday")) {
			inBirthday = false;
		} else if (name.equals("phone")) {
			inPhone = false;
		} else if (name.equals("email")) {
			inEmail = false;
		} else if (name.equals("address")) {
			inAddress = false;
		}
	}


}
