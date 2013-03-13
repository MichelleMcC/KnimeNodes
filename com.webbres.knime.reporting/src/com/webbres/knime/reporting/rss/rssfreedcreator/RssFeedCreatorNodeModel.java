package com.webbres.knime.reporting.rss.rssfreedcreator;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowKey;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import com.webbres.knime.reporting.rsscode.Feed;
import com.webbres.knime.reporting.rsscode.RSSFeedWriter;

/**
 * This is the model implementation of RssFeedCreator.
 * Create an RSS feed
 *
 * @author Samuel Webb
 */
public class RssFeedCreatorNodeModel extends NodeModel 
{
	
	// Dialog variable keys
	public static final String copyright = "copyright";
	public static final String title = "title";
	public static final String description = "description";
	public static final String url = "url";
	public static final String filepath = "filepath";
	public static final String filename = "filename";
	
	// Dialog components
	
	private final SettingsModelString copyrightText = new SettingsModelString(copyright, "N/A");
	private final SettingsModelString titleText = new SettingsModelString(title, "KNIME Output Feed");
	private final SettingsModelString descriptionText = new SettingsModelString(description, "");
	private final SettingsModelString urlText = new SettingsModelString(url, "http://localhost/");
	private final SettingsModelString filepathText = new SettingsModelString(filepath, "");
	private final SettingsModelString filenameText = new SettingsModelString(filename, "");
	
	
	
	
    
    /**
     * Constructor for the node model.
     */
    protected RssFeedCreatorNodeModel() 
    {
    
        // TODO: Specify the amount of input and output ports needed.
        super(0, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected BufferedDataTable[] execute(final BufferedDataTable[] inData, final ExecutionContext exec) throws Exception 
    {

    	
    	// Read the dialog input
    	
    	String givenCopyright;
		String givenURL;
		String givenFilepath;
		String givenTitle;
		String givenDescription;
		String givenFilename;
		
		try 
		{
			givenCopyright = copyrightText.getStringValue();
			givenURL = urlText.getStringValue();
			givenFilepath = filepathText.getStringValue();
			givenTitle = titleText.getStringValue();
			givenDescription = descriptionText.getStringValue();
			givenFilename = filenameText.getStringValue();
		} catch (Exception e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new Exception("failed to read configuration");
		}
    	
    	
    	
    	// Setup the variables
    	
    	String fullPath = givenFilepath + "\\" + givenFilename;
    	
    	String language = "en";

    	Calendar cal = new GregorianCalendar();
    	Date creationDate = cal.getTime();
    	SimpleDateFormat date_format = new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.UK);
  	
    	String pubdate = date_format.format(creationDate);
    	Feed rssFeeder = new Feed(givenTitle, givenURL, givenDescription, language, givenCopyright, pubdate);
    	
    	
    	RSSFeedWriter writer = new RSSFeedWriter(rssFeeder, fullPath);
        try 
        {
          writer.write();
        } catch (Exception e) 
        {
          e.printStackTrace();
          throw new Exception("Failed to write RSS feed");
        }
    	
    	
    	// Create and output the table
    	
    	DataTableSpec outputSpec = createOutputTable();								// Create the table spec
    	BufferedDataContainer container = exec.createDataContainer(outputSpec);		// create the data container
    	
    	// Create the output cells
    	
    	DataRow row = createRow(givenCopyright, givenURL, givenFilepath, givenTitle, givenDescription, givenFilename);
		container.addRowToTable(row);
    	
    	
    	
    	exec.checkCanceled();
    	
		container.close();
        BufferedDataTable out = container.getTable();
        return new BufferedDataTable[]{out};
	
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
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs) throws InvalidSettingsException 
    {
        return new DataTableSpec[]{createOutputTable()}; // Return the table spec
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) 
    {
         copyrightText.saveSettingsTo(settings);
         titleText.saveSettingsTo(settings);
         descriptionText.saveSettingsTo(settings);
         urlText.saveSettingsTo(settings);
         filepathText.saveSettingsTo(settings);
         filenameText.saveSettingsTo(settings);
         
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings) throws InvalidSettingsException 
    {
    	copyrightText.loadSettingsFrom(settings);
    	titleText.loadSettingsFrom(settings);
    	descriptionText.loadSettingsFrom(settings);
    	urlText.loadSettingsFrom(settings);
    	filepathText.loadSettingsFrom(settings);
    	filenameText.loadSettingsFrom(settings);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validateSettings(final NodeSettingsRO settings) throws InvalidSettingsException 
    {
    	copyrightText.validateSettings(settings);
    	titleText.validateSettings(settings);
    	descriptionText.validateSettings(settings);
    	urlText.validateSettings(settings);
    	filepathText.validateSettings(settings);
    	filenameText.validateSettings(settings);
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

    /**
     * Creates a data table containing 6 columns
     * @return
     */
    private DataTableSpec createOutputTable()
    {
    	
    	DataColumnSpec[] allColSpecs = new DataColumnSpec[6];
		
		allColSpecs[0] = new DataColumnSpecCreator("URL", StringCell.TYPE).createSpec();
		allColSpecs[1] = new DataColumnSpecCreator("Filepath", StringCell.TYPE).createSpec();
		allColSpecs[2] = new DataColumnSpecCreator("Filename", StringCell.TYPE).createSpec();
		allColSpecs[3] = new DataColumnSpecCreator("Title", StringCell.TYPE).createSpec();
		allColSpecs[4] = new DataColumnSpecCreator("Description", StringCell.TYPE).createSpec();
		allColSpecs[5] = new DataColumnSpecCreator("Copyright", StringCell.TYPE).createSpec();;

		
		DataTableSpec outputSpec = new DataTableSpec(allColSpecs);

		return outputSpec;
    	
    }
    
    
    /**
     * Creates the cells for the 6 columns
     * @param givenCopyright
     * @param givenURL
     * @param givenFilepath
     * @param givenTitle
     * @param givenDescription
     * @param givenFilename
     * @return
     */
    private DataRow createRow(String givenCopyright, String givenURL,
			String givenFilepath, String givenTitle, String givenDescription,
			String givenFilename) 
	{
		DataCell[] cells = new DataCell[6];
		
		cells[0] = new StringCell(givenURL);			// URL
		cells[1] = new StringCell(givenFilepath);		// Filepath
		cells[2] = new StringCell(givenFilename);		// Filename
		cells[3] = new StringCell(givenTitle);			// Title
		cells[4] = new StringCell(givenDescription);	// Description
		cells[5] = new StringCell(givenCopyright);		// Copyright
		
		DataRow row = new DefaultRow(new RowKey("RSS Details"), cells);
		return row;
	}
    
    
}

