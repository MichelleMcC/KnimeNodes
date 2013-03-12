package com.webbres.knime.createandsendhtml;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "TableToEmail" Node.
 * Send email
 *
 * @author WebbRes
 */
public class CreateAndSendHtmlNodeFactory 
        extends NodeFactory<CreateAndSendHtmlNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CreateAndSendHtmlNodeModel createNodeModel() {
        return new CreateAndSendHtmlNodeModel();
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
    public NodeView<CreateAndSendHtmlNodeModel> createNodeView(final int viewIndex,
            final CreateAndSendHtmlNodeModel nodeModel) {
        return new CreateAndSendHtmlNodeView(nodeModel);
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
        return new CreateAndSendHtmlNodeDialog();
    }

}

