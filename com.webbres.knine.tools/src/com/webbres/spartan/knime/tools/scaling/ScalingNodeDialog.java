package com.webbres.spartan.knime.tools.scaling;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;

/**
 * <code>NodeDialog</code> for the "Scaling" Node.
 * Simple linear scaling
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author Sam Webb
 */
public class ScalingNodeDialog extends DefaultNodeSettingsPane {

    /**
     * New pane for configuring the Scaling node.
     */
    protected ScalingNodeDialog() {

    }
}

