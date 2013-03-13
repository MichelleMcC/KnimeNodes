package com.webbres.knime.reporting.email.createandsendattachment;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "TableToEmail" Node.
 * Send email
 *
 * @author WebbRes
 */
public class CreateAndSendAttachmentNodeFactory 
        extends NodeFactory<CreateAndSendAttachmentNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CreateAndSendAttachmentNodeModel createNodeModel() {
        return new CreateAndSendAttachmentNodeModel();
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
    public NodeView<CreateAndSendAttachmentNodeModel> createNodeView(final int viewIndex,
            final CreateAndSendAttachmentNodeModel nodeModel) {
        return new CreateAndSendAttachmentNodeView(nodeModel);
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
        return new CreateAndSendAttachmentNodeDialog();
    }

}

