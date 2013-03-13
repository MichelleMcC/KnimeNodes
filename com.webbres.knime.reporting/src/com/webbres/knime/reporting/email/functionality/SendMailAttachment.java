package com.webbres.knime.reporting.email.functionality;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;


public class SendMailAttachment 
{

	MultiPartEmail email;
	EmailAttachment attachmnet;
	
	public SendMailAttachment()
	{
		email = new MultiPartEmail();
	}
	
	/**
	 * 
	 * @param hostName
	 * @param port
	 */
	public SendMailAttachment(String hostName, int port)
	{
		email = new MultiPartEmail();
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
	
	
	
	public void addAttachment(String path, String description, String name) throws EmailException
	{
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(path);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription(description);
		attachment.setName(name);
		
		email.attach(attachment);
		
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
