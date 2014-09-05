package de.zeros.ones.guestbook;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import de.zeros.ones.exceptions.GuestBookListEmptyException;
import de.zeros.ones.model.Guestbook;

public class GuestBookOptions implements IGuestBookEntry {
	
	@Override
	public Guestbook createGuestBookEntry(String author, String comment, Session session ){
		//get actual date time in milliseconds
		long date = new Date().getTime();
		
		//create new guestbook model object to save in db
		Guestbook guestbook = new Guestbook();
		
		//set data for all database columns
		guestbook.setAuthor(author);
		guestbook.setComment(comment);
		guestbook.setDate(date);
		
		//save guestbook object into db
		session.beginTransaction();
		session.save(guestbook);
		session.getTransaction().commit();
		
		return guestbook;
	}

	@Override
	public Date getDate(long id, Session session) {
		// receive guestbook object from db
		Guestbook guestbook =  (Guestbook) session.get(Guestbook.class, id);
		
		// returns guestbook date 
		return new Date(guestbook.getDate());
	}

	@Override
	public Long getNumber(long id, Session session) {
		// receive guestbook object from db
		Guestbook guestbook =  (Guestbook) session.get(Guestbook.class, id);
				
		// returns guestbook entry number 
		return guestbook.getId();
	}

	@Override
	public String getAuthor(long id, Session session) {
		// receive guestbook object from db
		Guestbook guestbook =  (Guestbook) session.get(Guestbook.class, id);
				
		// returns guestbook author 
		return guestbook.getAuthor();
	}

	@Override
	public String getComment(long id, Session session) {
		// receive guestbook object from db
		Guestbook guestbook =  (Guestbook) session.get(Guestbook.class, id);
				
		// returns guestbook entry comment text 
		return guestbook.getComment();
	}
	
	@Override
	public List<Guestbook> getListOfAllGuestBookEntries(Session session) throws GuestBookListEmptyException {
		//get all guestbook entries from db
		List<Guestbook> guestBookList = session.createCriteria(Guestbook.class).list();
		
		//throws exception if guestbook is empty
		if(guestBookList.size() == 0)
			throw new GuestBookListEmptyException();
		
		return guestBookList;
	}
}
