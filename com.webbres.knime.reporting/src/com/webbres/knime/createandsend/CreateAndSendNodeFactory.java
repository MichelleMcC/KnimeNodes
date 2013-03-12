package com.webbres.knime.createandsend;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "TableToEmail" Node.
 * Send email
 *
 * @author WebbRes
 */
public class CreateAndSendNodeFactory 
        extends NodeFactory<CreateAndSendNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CreateAndSendNodeModel createNodeModel() {
        return new CreateAndSendNodeModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNrNodeViews() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeView<CreateAndSendNodeModel> createNodeView(final int viewIndex,
            final CreateAndSendNodeModel nodeModel) {
        return new CreateAndSendNodeView(nodeModel);
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
        return new CreateAndSendNodeDialog();
    }

}

