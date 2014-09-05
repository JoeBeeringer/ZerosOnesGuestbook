package de.zeros.ones.guestbook;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import de.zeros.ones.exceptions.GuestBookListEmptyException;
import de.zeros.ones.model.Guestbook;

public interface IGuestBookEntry {
	/**
	 * get date of guestbook entry
	 * @param id id of guestbook entry
	 * @param session hibernate session
	 * @return date of guestbook entry
	 */
	public Date getDate(long id, Session session);
	/**
	 * get number of guestbook entry
	 * @param id id of guestbook entry
	 * @param session hibernate session
	 * @return number of guestbook entry
	 */
	public Long getNumber(long id, Session session);
	/**
	 * get author of guestbook entry
	 * @param id id of guestbook entry
	 * @param session hibernate session
	 * @return author of guestbook entry
	 */
	public String getAuthor(long id, Session session);
	/**
	 * get comment text of guestbook entry
	 * @param id id of guestbook entry
	 * @param session hibernate session
	 * @return comment text of guestbook entry
	 */
	public String getComment(long id, Session session);
	/**
	 * saves a guestbook entry to db
	 * @param author author of guestbook entry
	 * @param comment text of comment
	 * @param session hibernate session
	 * @return a guestbook model object
	 */
	public Guestbook createGuestBookEntry(String author, String comment, Session session );
	/**
	 * receive list of all guestbook entries 
	 * @param session hibernate session
	 * @return list of guestbook entries
	 * @throws GuestBookListEmptyException 
	 */
	public List<Guestbook> getListOfAllGuestBookEntries(Session session) throws GuestBookListEmptyException;
}
