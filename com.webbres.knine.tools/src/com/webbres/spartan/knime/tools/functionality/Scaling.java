package com.webbres.spartan.knime.tools.functionality;


/**
 * Calculate a scaling factor to scale a value from within one range
 * to another range. A given value can then be scaled. 
 * 
 * @author Sam
 *
 */



public class Scaling 
{
	
	static final double notScaledValue = Double.MAX_VALUE;
	
	/** stores the oldRange values as: [min, max] */
	private double[] oldRange;
	/** stores the newRange values as: [min, max] */
	private double[] newRange;
	
	private double value;
	
	private double scaled;
	
	/**
	 * Default constructor
	 */
	public Scaling()
	{
		this.oldRange = new double[2];
		this.newRange = new double[2];
		this.value = Scaling.notScaledValue;
		this.scaled = Scaling.notScaledValue;
	}
	
	/**
	 * Set the new and old ranges [min, max]
	 * @param oldRange		Set the old range [min, max]
	 * @param newRange		Set the new range [min, max]
	 */
	public Scaling(double[] oldRange, double[] newRange)
	{
		this.oldRange = oldRange;
		this.newRange = newRange;
		this.value = Scaling.notScaledValue;
		this.scaled = Scaling.notScaledValue;
	}
	
	/**
	 * Set the new and old ranges [min, max] and provide the value
	 * to be scaled.
	 * @param oldRange		Set the old range [min, max]
	 * @param newRange		Set the new range [min, max]
	 * @param value			Set the value to be scaled
	 */
	public Scaling(double[] oldRange, double[] newRange, double value)
	{
		this.oldRange = oldRange;
		this.newRange = newRange;
		this.value = value;
		this.scaled = Scaling.notScaledValue;
	}

	
	// Getters & Setters
	
	/** The old range value [min, max] */
	public double[] getOldRange() 
	{
		return oldRange;
	}
	/** The old range value [min, max] */
	public void setOldRange(double[] oldRange) 
	{
		this.oldRange = oldRange;
	}
	/** The new range value [min, max] */
	public double[] getNewRange() 
	{
		return newRange;
	}
	/** The new range value [min, max] */
	public void setNewRange(double[] newRange) 
	{
		this.newRange = newRange;
	}
	/** the original unscaled value*/
	public double getValue() 
	{
		return value;
	}
	/** the original unscaled value*/
	public void setValue(double value) 
	{
		this.value = value;
		this.scaled = notScaledValue;
	}

//	public double getScaled() 
//	{
//		return scaled;
//	}

//	public void setScaled(double scaled) 
//	{
//		this.scaled = scaled;
//	}
	

	//Methods
	
	/**
	 * 
	 * @return
	 * @throws Exception 	if no value is provided for scaling a new exception is thrown
	 */
	public double getScaledValue() throws Exception
	{
		// If the scaled value has already been calculated just return the scaled value
		return scaled != notScaledValue ? scaled : continousScale();
	}
	

	/**
	 * f(x) = ((b-a) * (x - min) / (max - min)) + a
	 * Where a = current range min, b = current range max and x = value to scale
	 * max = old range max and min = old range min
	 * 
	 * @return	the scaled value (also assigns the scaled value)
	 */
	
	public double continousScale() throws Exception
	{
		// Calculate the scaling factor (max - min)
		double scaleFactor = oldRange[1] - oldRange[0];
		
		// Scale the given value
		
		if(value == notScaledValue)
		{
			throw new Exception("No value has been provided for scaling");
		} else
		{
			scaled = (((newRange[1] - newRange[0]) * (value - oldRange[0]))) / scaleFactor + newRange[0];
		}
		
		return scaled;
	}
	
	
	
	
	
	
	
}
