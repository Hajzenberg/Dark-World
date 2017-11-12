package com.tfs.graphics.transformations.binary;

import java.awt.image.WritableRaster;

import com.tfs.graphics.callers.IProcessingInvoker;
import com.tfs.graphics.transformations.grayscale.GrayscaleType;

/**
 * Invoker of algorithm for binary transformation
 * 
 * @author Corax
 *
 */
public class BinaryInvoker implements IProcessingInvoker {
	public static final IBinary DEFAULT_BINARY = new Binary();
	
	// light RGB
	private int lr;
	private int lg;
	private int lb;
	
	// dark RGB
	private int dr;
	private int dg;
	private int db;
	
	// percent of DARK/LIGHT color
	private float binaryPercent;
	
	private GrayscaleType grayscaleType;
	
	private IBinary binAlgorithm;
	
	/**
	 * Constructor
	 * 
	 * @param binAlgorithm
	 * @param binaryPercent
	 * @param grayscaleType
	 * @param dr
	 * @param dg
	 * @param db
	 * @param lr
	 * @param lg
	 * @param lb
	 */
	public BinaryInvoker(IBinary binAlgorithm, float binaryPercent,
			GrayscaleType grayscaleType,int dr, int dg, int db, int lr, int lg, int lb ) {
		super();
		if (binAlgorithm == null) {
			throw new IllegalArgumentException();
		}
		
		if (binaryPercent < 0.0f || binaryPercent > 1.0f) {
			throw new IllegalArgumentException();
		}
		if ((dr < 0 || dr > 255) || (dg < 0 || dg > 255) || (db < 0 || db > 255) || (lr < 0 || lr > 255) || (lg < 0 || lg > 255) || (lb < 0 || lb > 255)) {
			throw new IllegalArgumentException("Color numbers must be between 0 and 255");
		}
		
		this.lr = lr;
		this.lg = lg;
		this.lb = lb;
		this.dr = dr;
		this.dg = dg;
		this.db = db;
		this.binaryPercent = binaryPercent;
		this.grayscaleType = grayscaleType;
		this.binAlgorithm = binAlgorithm;
	}

	/**
	 * Constructor
	 * 
	 * @param binaryPercent
	 * @param grayscaleType
	 * @param dr
	 * @param dg
	 * @param db
	 * @param lr
	 * @param lg
	 * @param lb
	 */
	public BinaryInvoker(float binaryPercent,
			GrayscaleType grayscaleType,int dr, int dg, int db, int lr, int lg, int lb ) {
		this(DEFAULT_BINARY, binaryPercent, grayscaleType, dr, dg, db, lr, lg, lb);
	}
	
	/**
	 * Constructor 
	 * 
	 * @param binaryPercent
	 * @param grayscaleType
	 */
	public BinaryInvoker(float binaryPercent, GrayscaleType grayscaleType) {
		this(binaryPercent, grayscaleType, 0,0,0,255,255,255);
	}
	
	/**
	 * Constructor 
	 * 
	 * @param binaryPercent
	 */
	public BinaryInvoker(float binaryPercent) {
		this(binaryPercent, GrayscaleType.LUMINOSITY);
	}


	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		return binAlgorithm.binary(source, grayscaleType, binaryPercent, dr, dg, db, lr, lg, lb);
	}

}
