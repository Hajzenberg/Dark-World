package com.tfs.graphics.transformations.fisheye;

import java.awt.image.WritableRaster;

import com.tfs.graphics.callers.IProcessingInvoker;

public class FisheyeInvoker implements IProcessingInvoker {
	public static final IFisheye DEFAULT_FISHEYE_METHOD = new Fisheye();
	
	private IFisheye fisheyeMethod;
	private float power;
	
	public FisheyeInvoker(IFisheye fisheyeMethod, float power) {
		super();
		if (fisheyeMethod == null) {
			throw new IllegalArgumentException();
		}
		this.fisheyeMethod = fisheyeMethod;
		this.power = power;
	}

	public FisheyeInvoker(float power) {
		this(DEFAULT_FISHEYE_METHOD, power);
	}

	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		return fisheyeMethod.fisheye(source, power);
	}
	
}
