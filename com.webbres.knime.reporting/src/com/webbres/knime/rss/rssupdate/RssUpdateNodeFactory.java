package com.webbres.knime.rss.rssupdate;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "RssUpdate" Node.
 * Add a new element to the RSS feed
 *
 * @author Sam Webb
 */
public class RssUpdateNodeFactory 
        extends NodeFactory<RssUpdateNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public RssUpdateNodeModel createNodeModel() {
        return new RssUpdateNodeModel();
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
    public NodeView<RssUpdateNodeModel> createNodeView(final int viewIndex,
            final RssUpdateNodeModel nodeModel) {
        return new RssUpdateNodeView(nodeModel);
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
        return new RssUpdateNodeDialog();
    }

}

