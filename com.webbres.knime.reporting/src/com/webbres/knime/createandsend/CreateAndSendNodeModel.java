package com.webbres.knime.createandsend;

import java.io.File;
import java.io.IOException;

import org.apache.commons.mail.EmailException;
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
import org.knime.core.node.NodeLogger;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import com.webbres.knime.email.functionality.SendMailSimple;

/**
 * This is the model implementation of TableToEmail.
 * Send email
 *
 * @author WebbRes
 */
public class CreateAndSendNodeModel extends NodeModel 
{

	// Test comment
	
	/*
	 * Dialog
	 */
 
    static final String host = "host";					// Host
    static final String username = "username";			// Uswrname
    static final String subject = "subject";			// Subject
    static final String body = "body";					// Body
    static final String port = "port";					// Port
    static final String password = "password";			// Password TODO: is there a way to encrypt this?
    static final String sendTo = "sendTo";				// To address TODO: update this to take multiple names
    static final String ssl =  "ssl";					// SSL
	
	
    int numOutCols = 5; // host, port, to, subject, body
    
    /*
     * User Settings
     */
    
    //TODO: convert these to seperate methods, reduces the risk of misconfiguring between the node model and dialog
    
    private final SettingsModelString m_host = new SettingsModelString(host, "localhost");
    private final SettingsModelInteger m_port = new SettingsModelInteger(port, 465);
    private final SettingsModelBoolean m_ssl = new SettingsModelBoolean(ssl, true);
    
    private final SettingsModelString m_username = new SettingsModelString(username, null);
    private final SettingsModelString m_password = new SettingsModelString(password, null);
    
    private final SettingsModelString m_sendTo = new SettingsModelString(sendTo, null);
    private final SettingsModelString m_subject = new SettingsModelString(subject, "KNIME email");
    private final SettingsModelString m_body = new SettingsModelString(body, "KNIME body");
    
    
    // Node Logger
	private static final NodeLogger logger = NodeLogger.getLogger(CreateAndSendNodeModel.class);
    
    /**
     * Constructor for the node model.
     */
    protected CreateAndSendNodeModel() 
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
    	
    	exec.checkCanceled();

    	// Create the array of emails
    	/** store the emails to send to */ 
    	String[] sendTo = splitEmails(m_sendTo.getStringValue());
    	
    	
    	
    	
    	// Setup the email
    	
    	try 
    	{
    		// Create a new simple email, this node just works on text boxes
			SendMailSimple newMail = new SendMailSimple(m_host.getStringValue(), m_port.getIntValue());
			newMail.Authenticate(m_username.getStringValue(), m_password.getStringValue(), m_ssl.getBooleanValue());
			
			// Setup the email using the username as the from, and the dialog settings for to, subject and body
			newMail.setupMail(m_username.getStringValue(), sendTo, m_subject.getStringValue(), m_body.getStringValue());
			
			// Print the host and port used
			System.out.println("SMTP host: " + m_host.getStringValue());
			System.out.println("SMTP port: " + m_port.getIntValue());
			
			logger.info("Setup connection on SMTP host: " + m_host.getStringValue() + " port " + m_port.getIntValue());
			
			
			// Send email
			newMail.sendMail();
			
			System.out.println("Mail Sent");
			
		} catch (EmailException e) 
		{
			e.printStackTrace();
			
			for(int i = 0; i < e.getStackTrace().length; i++)
			{
				logger.debug(e.getStackTrace()[i]);
			}
			
			logger.error(e.getCause());
			
			logger.error(e.getMessage());
			
			throw new Exception("Failed to send email");
			
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception("Failed to send email");
		}
    	
    	// Setup the table
    	
    	DataTableSpec outputSpec = createTableSpec();
    	BufferedDataContainer container = exec.createDataContainer(outputSpec);
    	
    	// Create the cells
    	
    	DataCell[] cells = new DataCell[numOutCols];
		
		cells[0] = new StringCell(m_host.getStringValue());
		cells[1] = new IntCell(m_port.getIntValue());
		cells[2] = new StringCell(m_sendTo.getStringValue());
		cells[3] = new StringCell(m_subject.getStringValue());
		cells[4] = new StringCell(m_body.getStringValue());

		DataRow row = new DefaultRow(new RowKey("emailDetails"), cells);
		container.addRowToTable(row);
    	

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

        // TODO: Check validity of configuration
    	
        return new DataTableSpec[]{createTableSpec()};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) 
    {
         m_host.saveSettingsTo(settings);
         m_password.saveSettingsTo(settings);
         m_subject.saveSettingsTo(settings);
         m_username.saveSettingsTo(settings);
         m_password.saveSettingsTo(settings);
         m_port.saveSettingsTo(settings);
         m_sendTo.saveSettingsTo(settings);
         m_body.saveSettingsTo(settings);
         m_ssl.saveSettingsTo(settings);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings) throws InvalidSettingsException 
    {
    	m_host.loadSettingsFrom(settings);
        m_password.loadSettingsFrom(settings);
        m_subject.loadSettingsFrom(settings);
        m_username.loadSettingsFrom(settings);
        m_password.loadSettingsFrom(settings);
        m_port.loadSettingsFrom(settings);
        m_sendTo.loadSettingsFrom(settings);
        m_body.loadSettingsFrom(settings);
        m_ssl.loadSettingsFrom(settings);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validateSettings(final NodeSettingsRO settings) throws InvalidSettingsException 
    {
    	m_host.validateSettings(settings);
        m_password.validateSettings(settings);
        m_subject.validateSettings(settings);
        m_username.validateSettings(settings);
        m_password.validateSettings(settings);
        m_port.validateSettings(settings);
        m_sendTo.validateSettings(settings);
        m_body.validateSettings(settings);
        m_ssl.validateSettings(settings);
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
     * Split the emails
     * @param emails
     * @return
     */
    public String[] splitEmails(String emails)
    {
    	
    	
    	/*
    	 * For the given string split on every "; " value as this is the delimeter used
    	 * Should a check be done to ensure the emails are valid?
    	 */
    	
    	
    	String split = emails;
    	String[] seperated = split.split("; ");
    	
    	return seperated;
    }

    
    /**
     * Create the output column spec
     * @return
     */
    public DataTableSpec createTableSpec()
    {
    	// Output table - this changes depending on what descriptors have been selected
		DataColumnSpec[] allColSpecs = new DataColumnSpec[numOutCols];
		
		allColSpecs[0] = new DataColumnSpecCreator("Host", StringCell.TYPE).createSpec();
		allColSpecs[1] = new DataColumnSpecCreator("Port", IntCell.TYPE).createSpec();
		allColSpecs[2] = new DataColumnSpecCreator("To", StringCell.TYPE).createSpec();
		allColSpecs[3] = new DataColumnSpecCreator("Subject", StringCell.TYPE).createSpec();
		allColSpecs[4] = new DataColumnSpecCreator("Body", StringCell.TYPE).createSpec();

		return new DataTableSpec(allColSpecs);
    }


}

