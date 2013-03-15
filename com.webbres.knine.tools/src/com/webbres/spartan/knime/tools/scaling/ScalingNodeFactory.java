package com.webbres.spartan.knime.tools.scaling;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "Scaling" Node.
 * Simple linear scaling
 *
 * @author Sam Webb
 */
public class ScalingNodeFactory 
        extends NodeFactory<ScalingNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ScalingNodeModel createNodeModel() {
        return new ScalingNodeModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNrNodeViews() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeView<ScalingNodeModel> createNodeView(final int viewIndex,
            final ScalingNodeModel nodeModel) {
        return new ScalingNodeView(nodeModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDialog() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeDialogPane createNodeDialogPane() {
        return new ScalingNodeDialog();
    }

}

