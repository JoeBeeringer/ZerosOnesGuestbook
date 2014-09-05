package de.zeros.ones.answerbuilder;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import de.zeros.ones.answerobject.GuestBookAnswer;
import de.zeros.ones.exceptions.GuestBookListEmptyException;
import de.zeros.ones.guestbook.GuestBookOptions;
import de.zeros.ones.helper.DateHelper;
import de.zeros.ones.model.Guestbook;

public class GuestBookAnswerBuilder {

	GuestBookOptions options = new GuestBookOptions();
	/**
	 * creates a guestbook entry answer 
	 * @param author author of guestbook entry
	 * @param comment comment of guestbook entry
	 * @param session hibernate session
	 * @return guestbook answer
	 */
	public GuestBookAnswer createGuestBookEntryAnswer(String author, String comment, Session session){
		GuestBookAnswer answer = new GuestBookAnswer();
		
		//create new guestbook entry object
		Guestbook guestbook = options.createGuestBookEntry(author, comment, session);
		
		//assign the guestbook data to the answer object
		answer.setSuccess(true);
		answer.setAuthor(guestbook.getAuthor());
		answer.setComment(guestbook.getComment());
		answer.setNumber(guestbook.getId());
		
		//convert milliseconds to string
		answer.setDate(new DateHelper().dateToString(options.getDate(guestbook.getId(), session)));
		answer.setMessage("Message Saved");
		
		return answer;
	}
	/**
	 * receive list of all guestbook entries
	 * @param session hibernate sesssion
	 * @return Guestbookanswer list
	 */
	public List<GuestBookAnswer> receiveAllGuestBookEntriesAnswer(Session session){
		
		List<GuestBookAnswer> guestBookAnswerList = new ArrayList<GuestBookAnswer>();
		
		try {
			//receive all entries, throws guestbooklist exception if entry is empty
			List<Guestbook> guestBookList = options.getListOfAllGuestBookEntries(session);
			
			//assign each guestbook entry to the answer object
			for (Guestbook guestbook : guestBookList) {
				GuestBookAnswer answer = new GuestBookAnswer();
				answer.setSuccess(true);
				answer.setAuthor(options.getAuthor(guestbook.getId(), session));
				answer.setComment(options.getComment(guestbook.getId(), session));
				answer.setNumber(options.getNumber(guestbook.getId(), session));
				answer.setDate(new DateHelper().dateToString(options.getDate(guestbook.getId(), session)));
				
				guestBookAnswerList.add(answer);
			}
			
		} catch (GuestBookListEmptyException e) {
			//if no entry is in the db, it sends fail and error message
			GuestBookAnswer answer = new GuestBookAnswer();
			
			answer.setSuccess(false);
			answer.setMessage("No Guestbook Entries");
			
			guestBookAnswerList.add(answer);
		}

		return guestBookAnswerList;
	}
	
	
}
