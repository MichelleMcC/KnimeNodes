package com.webbres.knime.reporting.rsscode;

/**
 * Made available under EPL from http://www.vogella.com/articles/RSSFeed/article.html
 * @author Sam
 *
 */

public class ReadTest 
{
	  public static void main(String[] args) 
	  {
	    RSSFeedParser parser = new RSSFeedParser("http://www.vogella.de/article.rss");
	    
	    Feed feed = parser.readFeed();
	   
	    System.out.println(feed);
	    
	    for (FeedMessage message : feed.getMessages()) 
	    {
	      System.out.println(message);
	    }

	  }
}
