package com.webbres.knime.tools.scaling;

import org.knime.core.node.NodeView;

/**
 * <code>NodeView</code> for the "Scaling" Node.
 * Simple linear scaling
 *
 * @author Sam Webb
 */
public class ScalingNodeView extends NodeView<ScalingNodeModel> {

    /**
     * Creates a new view.
     * 
     * @param nodeModel The model (class: {@link ScalingNodeModel})
     */
    protected ScalingNodeView(final ScalingNodeModel nodeModel) {
        super(nodeModel);
        // TODO: generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void modelChanged() {
        // TODO: generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onClose() {
        // TODO: generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onOpen() {
        // TODO: generated method stub
    }

}

