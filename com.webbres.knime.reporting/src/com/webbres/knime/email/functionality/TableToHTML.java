package com.webbres.knime.email.functionality;

import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.node.BufferedDataTable;

public class TableToHTML 
{
	
	public static String convertToHtml(BufferedDataTable table)
	{
		/*
		 * The first column becomes the header
		 * The rowID becomes the left index
		 * 
		 * Work row by row adding a new row to the HTLM table
		 * 
		 */
		
		int numRows = table.getRowCount();
		
		String html = "<table border style=\"border:1px solid black\">"; // start table
		
		// The first row should be the column headers
		DataTableSpec spec = table.getDataTableSpec();
		
		html = html + "\n \t <tr>"; // start row
		
		// For each column header create a new cell in the rop row
		for(int i = 0; i < spec.getNumColumns(); i++)
		{
			String cell = "\t <td> <b> " + spec.getColumnNames()[i] + " </b> </td>";
			
			html = html + cell;
		}
		
		html = html + "\n \t </tr>"; // end row containing the headers

		// For each row, work through each cell creating a new cell	
		for (DataRow row : table)
		{
			html = html + "\n \t <tr>"; // start row
			
			
			// Do the rowID first
			
			String key = row.getKey().toString();
			
			// Create a new cell
			
			String keyCell = "\t <td> " + key + " </td>";
			
			html = html + keyCell;
			
			for(int i = 0; i < row.getNumCells(); i++)
			{
				
				// Create a new cell
				
				String cell = "\t <td> " + row.getCell(i).toString() + " </td>";
				
				html = html + cell;
				
			}
			
			
			html = html + "\t </tr>"; // end row
		}

		
		
		html = html + "\n </table>"; // end table
		
		return html;
	}
	
	
}
