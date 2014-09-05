package de.zeros.ones.guestbook.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import de.zeros.ones.answerbuilder.GuestBookAnswerBuilder;
import de.zeros.ones.answerobject.GuestBookAnswer;
import de.zeros.ones.model.Guestbook;
import de.zeros.ones.webservice.Webservice;

public class WebserviceTests {
	//test data
	GuestBookAnswerBuilder builder = new GuestBookAnswerBuilder();
	String author = "Authortest";
	String comment = "Hallo Test";
	
	@Test
	public void createNewEntryTest() {
		//open new hibernate session
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		GuestBookAnswer answer = builder.createGuestBookEntryAnswer(author, comment, session);
		
		//assertions
		assertEquals("Message Saved" , answer.getMessage());
		assertNotEquals("testss", answer.getComment());
	
		//delete testobjects
		Guestbook guestbook = (Guestbook) session.get(Guestbook.class, answer.getNumber());
		session.delete(guestbook);
		
		//close session
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void getAllEntryTest() {
		//open new hibernat session
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		//receive list of all guestbook entries
		List<GuestBookAnswer> answer = builder.receiveAllGuestBookEntriesAnswer(session);
		
		//check if there are some entries otherwise errormessage should be equal
		if (answer.get(0).isSuccess()) {
			assertTrue(answer.size()>0);
		}
		else
		{
			assertEquals(answer.get(0).getMessage(), "No Guestbook Entries");
		}
		
		//close session
		session.close();
		sessionFactory.close();
	}
}
