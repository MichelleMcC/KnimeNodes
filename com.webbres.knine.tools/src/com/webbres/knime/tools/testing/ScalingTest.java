/**
 * 
 */
package com.webbres.knime.tools.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import com.webbres.knime.tools.functionality.Scaling;

/**
 * @author Sam
 *
 */
public class ScalingTest 
{

	@Test
	public void testScaling() throws Exception 
	{
		
		
		// Setup the values
		double[] newRange = {0.0, 10.0};		// [min, max]
		double[] oldRange = {0.0, 1.0};			// [min, max]
		
		double value = 0.6;						// value to be scaled
		
		Scaling newScale = new Scaling(oldRange, newRange, value);
		
		double scaledValue = newScale.getScaledValue();
		
		
		assertTrue("We expect a value of 6.0", scaledValue == 6d);
		

	}

}
