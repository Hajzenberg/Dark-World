package com.tfs.graphics.transformations.negative;

import java.awt.image.WritableRaster;

import com.tfs.graphics.callers.IProcessingInvoker;

/**
 * 
 * Invoker of negative raster transformation
 * 
 * @author Corax
 *
 */
public class NegativeInvoker implements IProcessingInvoker {
	/**
	 * Constants of default negative algorithm
	 */
	public static final INegative DEFAULT_NEGATIVE = new Negative();
	
	private INegative negative;
	
	public NegativeInvoker(INegative negative) {
		super();
		if (negative == null) {
			throw new IllegalArgumentException();
		}
		this.negative = negative;
	}
	
	public NegativeInvoker() {
		this(DEFAULT_NEGATIVE);
	}
	

	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		return negative.negative(source);
	}

}
