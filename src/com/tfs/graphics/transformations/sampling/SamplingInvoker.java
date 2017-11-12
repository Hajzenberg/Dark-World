package com.tfs.graphics.transformations.sampling;

import java.awt.image.WritableRaster;

import com.tfs.graphics.callers.IProcessingInvoker;

public class SamplingInvoker implements IProcessingInvoker {
	public static final ISampling DEFAULT_SCALE_METHOD = new Sampling();
	
	private ISampling scaleMethod;
	private SamplingType samplingType;
	private int width;
	private int height;
	
	public SamplingInvoker(ISampling scaleMethod, SamplingType samplingType, int width, int height) {
		super();
		if (scaleMethod == null || width <= 0 || height <= 0 || samplingType == null) {
			throw new IllegalArgumentException();
		}
		this.scaleMethod = scaleMethod;
		this.width = width;
		this.height = height;
		this.samplingType = samplingType;
	}


	public SamplingInvoker(SamplingType samplingType, int width, int height) {
		this(DEFAULT_SCALE_METHOD, samplingType, width, height);
	}

	public SamplingInvoker(int width, int height) {
		this(SamplingType.BILINEAR, width, height);
	}

	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		return scaleMethod.scale(source,samplingType, width, height);
	}
	
}
