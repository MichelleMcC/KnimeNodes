package com.webbres.knime.reporting.rss.rssupdate;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;

/**
 * <code>NodeDialog</code> for the "RssUpdate" Node.
 * Add a new element to the RSS feed
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author Sam Webb
 */
public class RssUpdateNodeDialog extends DefaultNodeSettingsPane {

    /**
     * New pane for configuring the RssUpdate node.
     */
    protected RssUpdateNodeDialog() {

    }
}

