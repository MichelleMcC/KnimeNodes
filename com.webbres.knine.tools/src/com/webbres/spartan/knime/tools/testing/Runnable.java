package com.webbres.spartan.knime.tools.testing;

import com.webbres.spartan.knime.tools.functionality.Scaling;

public class Runnable 
{

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		
		
		// Setup the values
		double[] newRange = {0.0, 10.0};
		double[] oldRange = {0.0, 1.0};
		
		double value = 0.6;
		
		Scaling newScale = new Scaling(oldRange, newRange, value);
		
		double scaledValue = newScale.getScaledValue();
		
		System.out.println("The scaled value is: " + scaledValue);
		
		
	}

}
