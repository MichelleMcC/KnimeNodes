package com.webbres.knime.reporting.rss.rssfreedcreator;

import javax.swing.JFileChooser;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentFileChooser;
import org.knime.core.node.defaultnodesettings.DialogComponentMultiLineString;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

/**
 * <code>NodeDialog</code> for the "RssFeedCreator" Node.
 * Create an RSS feed
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author Samuel Webb
 */
public class RssFeedCreatorNodeDialog extends DefaultNodeSettingsPane 
{
	
	

    /**
     * New pane for configuring the RssFeedCreator node.
     */
    protected RssFeedCreatorNodeDialog() 
    {
    	
    	createNewGroup("Location");
    	// URL and filepath
    	setHorizontalPlacement(true);
    	addDialogComponent(new DialogComponentString(new SettingsModelString(RssFeedCreatorNodeModel.url, null), "URL:"));
    	addDialogComponent(new DialogComponentString(new SettingsModelString(RssFeedCreatorNodeModel.filename, null), "Filename:"));
    	setHorizontalPlacement(false);
    	addDialogComponent(new DialogComponentFileChooser(new SettingsModelString(RssFeedCreatorNodeModel.filepath, null), "", JFileChooser.OPEN_DIALOG, true));

    	
    	
    	createNewGroup("Details"); 
    	// Title, description, copyright
    	addDialogComponent(new DialogComponentString(new SettingsModelString(RssFeedCreatorNodeModel.title, null), "Title:"));
    	addDialogComponent(new DialogComponentMultiLineString(new SettingsModelString(RssFeedCreatorNodeModel.description, null), "Description:"));
    	addDialogComponent(new DialogComponentMultiLineString(new SettingsModelString(RssFeedCreatorNodeModel.copyright, null), "Copyright:"));
    	

    }
}

