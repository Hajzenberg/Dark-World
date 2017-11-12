package com.tfs.graphics.transformations.grayscale;

import java.awt.image.WritableRaster;

import com.tfs.graphics.callers.IProcessingInvoker;

/**
 * Invokes grayscale transformation
 * 
 * @author Corax
 *
 */
public class GrayscaleInvoker implements IProcessingInvoker {
	/**
	 * Grayscale default 
	 */
	public static final IGrayscale DEFAULT_GRAYSCALE = new Grayscale();
	
	/**
	 * grayscale algorithm 
	 */
	private IGrayscale grayscaleAlgorithm;
	private GrayscaleType grayscaleType;
	
	public GrayscaleInvoker(IGrayscale grayscaleAlgorithm, GrayscaleType grayscaleType) {
		if (grayscaleAlgorithm == null || grayscaleType == null) {
			throw new IllegalArgumentException();
		}
		this.grayscaleAlgorithm = grayscaleAlgorithm;
		this.grayscaleType = grayscaleType;
	}
	
	public GrayscaleInvoker(GrayscaleType grayscaleType) {
		this(DEFAULT_GRAYSCALE, grayscaleType);
	}
	
	public GrayscaleInvoker() {
		this(DEFAULT_GRAYSCALE,GrayscaleType.LUMINOSITY);
	}
	
	
	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		return grayscaleAlgorithm.grayscale(source,grayscaleType);
	}

}
