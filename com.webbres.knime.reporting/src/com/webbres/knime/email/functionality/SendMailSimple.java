package com.webbres.knime.email.functionality;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SendMailSimple 
{
	
	Email email;
	
	public SendMailSimple()
	{
		email = new SimpleEmail();
	}
	
	/**
	 * 
	 * @param hostName
	 * @param port
	 */
	public SendMailSimple(String hostName, int port)
	{
		email = new SimpleEmail();
		email.setHostName(hostName);
		email.setSmtpPort(port);
	}

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public void Authenticate(String username, String password, boolean ssl)
	{
		email.setAuthenticator(new DefaultAuthenticator(username, password));
		email.setSSLOnConnect(ssl);
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @param subject
	 * @param body
	 * @throws EmailException
	 */
	public void setupMail(String from, String to, String subject, String body) throws EmailException
	{
		email.setFrom(from);
		
		email.setSubject(subject);
		email.setMsg(body);
		email.addTo(to);
	}
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @param subject
	 * @param body
	 * @throws EmailException
	 */
	public void setupMail(String from, String[] to, String subject, String body) throws EmailException
	{
		email.setFrom(from);
		
		email.setSubject(subject);
		email.setMsg(body);
		email.addTo(to);
	}
	
	public void sendMail() throws EmailException
	{
		email.send();
	}

	public Email getMail()
	{
		return email;
	}
	
}
