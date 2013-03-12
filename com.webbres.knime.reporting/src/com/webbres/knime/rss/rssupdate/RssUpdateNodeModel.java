package com.webbres.knime.rss.rssupdate;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.knime.core.data.DataTableSpec;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

import com.webbres.knime.rsscode.Feed;
import com.webbres.knime.rsscode.FeedMessage;
import com.webbres.knime.rsscode.RSSFeedParser;
import com.webbres.knime.rsscode.RSSFeedWriter;

/**
 * This is the model implementation of RssUpdate.
 * Add a new element to the RSS feed
 *
 * @author Sam Webb
 */
public class RssUpdateNodeModel extends NodeModel 
{
    
    /**
     * Constructor for the node model.
     */
    protected RssUpdateNodeModel() {
    
        // TODO: Specify the amount of input and output ports needed.
        super(1, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected BufferedDataTable[] execute(final BufferedDataTable[] inData, final ExecutionContext exec) throws Exception 
    {
    	
    	// Get configuration
    	
    		//TODO: Get title and description 
    	
    	String link = null;					//TODO: get the URL
        String filename = null;				//TODO: get the filename variable from the dialog
    	
    	
    	// Get the RSS feed
        
        RSSFeedParser parser = new RSSFeedParser("http://lukpc87/RSS/articles.xml");
        Feed givenFeed = parser.readFeed();
       
        
        // Get the link
        
        
        
        // Create the date
        Calendar cal = new GregorianCalendar();
        Date creationDate = cal.getTime();
        SimpleDateFormat date_format = new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.UK);
        String pubdate = date_format.format(creationDate);
        
        // Create a GUID
        
        SimpleDateFormat guidDate = new SimpleDateFormat("yyy MMM ddd HH:mm:ss");
        String dateGUID = guidDate.format(creationDate);
        

        
        // Create the new message
	    FeedMessage feed = new FeedMessage();
	    feed.setTitle("Example Feed 2");
	    feed.setDescription("Example description, this should be aquired from the dialog + table 2");
	    feed.setAuthor("samuel.webb@lhasalimited.org 2");
	    feed.setGuid(dateGUID);
	    feed.setLink(link);
	    givenFeed.getMessages().add(feed);
	    Feed rssFeeder = givenFeed;

        
    	// Write the file
    	
        RSSFeedWriter writer = new RSSFeedWriter(rssFeeder, filename);
        try 
        {
          writer.write();
        } catch (Exception e) 
        {
          e.printStackTrace();
        }
      	
    	
    	
    	
    	
    	
    	
    	

        // TODO: Return a BufferedDataTable for each output port 
        return new BufferedDataTable[]{};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reset() {
        // TODO: generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs)
            throws InvalidSettingsException {

        // TODO: generated method stub
        return new DataTableSpec[]{null};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) {
         // TODO: generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
            throws InvalidSettingsException {
        // TODO: generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validateSettings(final NodeSettingsRO settings)
            throws InvalidSettingsException {
        // TODO: generated method stub
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException {
        // TODO: generated method stub
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException {
        // TODO: generated method stub
    }

}

