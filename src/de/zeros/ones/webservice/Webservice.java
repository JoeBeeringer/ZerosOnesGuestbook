package de.zeros.ones.webservice;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;

import de.zero.ones.answerobject.GuestBookAnswer;
import de.zeros.ones.answerbuilder.GuestBookAnswerBuilder;



@Path("guestbook")
public class Webservice {
	
	GuestBookAnswerBuilder guestbookAnswerBuilder = new GuestBookAnswerBuilder();

	/**
	 * get all guestbook entries
	 * @return webanswer as jsonp object
	 */
	@GET
	@Produces("text/plain")
	public String  receiveAllGuestbookEntry()  {
		
		//configure new hibernate session
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		//get list of all guestbook entries
		List<GuestBookAnswer> guestbookAnswer = guestbookAnswerBuilder.receiveAllGuestBookEntriesAnswer(session);	
		
		//close hibernate session
		session.close();
		sessionFactory.close();
		
		//create jsonp
		Gson gson = new Gson();
		String webanswer = gson.toJson(guestbookAnswer);
		webanswer = "guestbookCallBack("+webanswer+")";
		
		//send back to web as jsonp
		return webanswer;
	}

	/**
	 * create new guestbook entry
	 * @param author author name
	 * @param comment comment 
	 * @return gson object answer
	 */
	@GET
	@Path("new/{author}/{comment}")
	@Produces("text/plain")
	public String createNewGuestBookEntry(@PathParam("author") String author, @PathParam("comment") String comment){

		//configure new hibernate session
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		//create new guestbook entry
		GuestBookAnswer gestbookAnswer = guestbookAnswerBuilder.createGuestBookEntryAnswer(author, comment, session);	
		
		//close session
		session.close();
		sessionFactory.close();
		
		//create jsonp
		Gson gson = new Gson();
		String webanswer = gson.toJson(gestbookAnswer);
		webanswer = "newEntryCallBack("+webanswer+")";
		
		//return answer to web as jsonp
		return webanswer;
	}
}
