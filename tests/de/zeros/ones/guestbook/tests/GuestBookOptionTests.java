package de.zeros.ones.guestbook.tests;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import de.zeros.ones.exceptions.GuestBookListEmptyException;
import de.zeros.ones.guestbook.GuestBookOptions;
import de.zeros.ones.model.Guestbook;

public class GuestBookOptionTests {

	GuestBookOptions options = new GuestBookOptions();
	String author = "Authortest";
	String comment = "Hallo Test";
	
	@Test
	public void createNewGuestBookEntry() {
	
		//new hibernate session
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		//create new test object
		Guestbook guestbook = options.createGuestBookEntry(author, comment, session);
		
		//assert test object
		assertEquals("Authortest", guestbook.getAuthor());
		assertNotEquals("Hi", comment);
		
		//delete testobjects
		session.delete(guestbook);
		
		//teardown session
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void getListOfGuestBookEntries() throws GuestBookListEmptyException {
	
		//new hibernate session
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		//create new test object
		Guestbook guestbook = options.createGuestBookEntry(author, comment, session);
		//assert List
		assertTrue(options.getListOfAllGuestBookEntries(session).size() > 0);
		
		//delete testobjects
		session.delete(guestbook);
		
		//teardown session
		session.close();
		sessionFactory.close();
	}
	
	

}
