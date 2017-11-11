package com.tfs.graphics.transformations.blur;

import java.awt.image.WritableRaster;

import com.tfs.graphics.callers.IProcessingInvoker;

public class BlurInvoker implements IProcessingInvoker {
	public static final IBlur DEFAULT_BOX_BLUR_ALGORITHM = new BoxBlur();
	public static final IBlur DEFAULT_GAUSSIAN_BLUR_ALGORITHM = new GaussianBlur();
	public static final IBlur DEFAULT_SAMPLE_BLUR_ALGORITHM = new SampleBlur();
	
	
	private IBlur blurMethod;
	private int bluringPower;
	
	
	public BlurInvoker(IBlur boxBlur, int bluringPower) {
		super();
		this.blurMethod = boxBlur;
		this.bluringPower = bluringPower;
	}
	
	public BlurInvoker( int bluringPower) {
		this(DEFAULT_GAUSSIAN_BLUR_ALGORITHM, bluringPower);
	}

	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		return blurMethod.blur(source, bluringPower);
	}
	
	
}
