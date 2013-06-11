
Note: nodes not complete.



KnimeNodes
==========

KNIME nodes developed for a number of tasks. Initial commit includes the in development email and RSS functionality
designed to allow the reporting of a node output via email or by adding an item to a local RSS feed. 


Email
==========
Given an SMTP server and valid login send an email based on the given to, subject and body provided on the node dialog.
Alternatively combine the body text with an HTML conversion of the toString() for each cell in the input table. 

Optionally attach a file to either option.


RSS
==========
Given a URL and filepath for an XML file add a new item detailing the text provided and the toString() HTML table
for the intput table.

A node is also present which can make a new RSS feed XML file. 
