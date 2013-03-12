package com.webbres.knime.rss.rssfreedcreator;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "RssFeedCreator" Node.
 * Create an RSS feed
 *
 * @author Samuel Webb
 */
public class RssFeedCreatorNodeFactory 
        extends NodeFactory<RssFeedCreatorNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public RssFeedCreatorNodeModel createNodeModel() {
        return new RssFeedCreatorNodeModel();
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
    public NodeView<RssFeedCreatorNodeModel> createNodeView(final int viewIndex,
            final RssFeedCreatorNodeModel nodeModel) {
        return new RssFeedCreatorNodeView(nodeModel);
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
        return new RssFeedCreatorNodeDialog();
    }

}

