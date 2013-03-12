package com.webbres.knime.createandsendattachment;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentFileChooser;
import org.knime.core.node.defaultnodesettings.DialogComponentMultiLineString;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentPasswordField;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import com.webbres.knime.createandsend.CreateAndSendNodeModel;

/**
 * <code>NodeDialog</code> for the "TableToEmail" Node.
 * Send email
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author WebbRes
 */
public class CreateAndSendAttachmentNodeDialog extends DefaultNodeSettingsPane 
{


    /**
     * New pane for configuring the TableToEmail node.
     */
    protected CreateAndSendAttachmentNodeDialog() 
    {
    	
    	
    	createNewGroup("SMPT Server"); 
    	setHorizontalPlacement(true);
        addDialogComponent(new DialogComponentString(new SettingsModelString(CreateAndSendAttachmentNodeModel.host, null), "Host:"));
        addDialogComponent(new DialogComponentNumber(new SettingsModelInteger(CreateAndSendAttachmentNodeModel.port, 465), "Port:", 0));
        addDialogComponent(new DialogComponentBoolean(new SettingsModelBoolean(CreateAndSendAttachmentNodeModel.ssl, true), "SSL:"));
        
        setHorizontalPlacement(false);
        
        createNewGroup("Account"); 
        setHorizontalPlacement(true);
        addDialogComponent(new DialogComponentString(new SettingsModelString(CreateAndSendAttachmentNodeModel.username, null), "Username:"));
        addDialogComponent(new DialogComponentPasswordField(new SettingsModelString(CreateAndSendAttachmentNodeModel.password, null), "Password:"));
        
        setHorizontalPlacement(false);
        
        createNewGroup("Mail Settings"); 
        addDialogComponent(new DialogComponentString(new SettingsModelString(CreateAndSendAttachmentNodeModel.sendTo, null), "To:"));
        addDialogComponent(new DialogComponentMultiLineString(new SettingsModelString(CreateAndSendAttachmentNodeModel.subject, "KNIME email"), "Subject:"));
        addDialogComponent(new DialogComponentMultiLineString(new SettingsModelString(CreateAndSendAttachmentNodeModel.body, null), "Body:"));
        addDialogComponent(new DialogComponentFileChooser(new SettingsModelString(CreateAndSendAttachmentNodeModel.attachment, null), "Attachment:"));
        
    }
}

