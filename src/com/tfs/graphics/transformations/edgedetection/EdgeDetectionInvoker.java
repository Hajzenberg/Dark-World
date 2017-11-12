package com.tfs.graphics.transformations.edgedetection;

import java.awt.image.WritableRaster;

import com.tfs.graphics.callers.IProcessingInvoker;

public class EdgeDetectionInvoker implements IProcessingInvoker{

	public static final IEdgeDetection DEFAULT_EDGEDETECTION = new EdgeDetection();
	
	private IEdgeDetection edgeDetectioin;
	private int multiplier;
	
	
	public EdgeDetectionInvoker(IEdgeDetection edgeDetectioin, int multiplier) {
		super();
		if (edgeDetectioin == null || multiplier < 1) {
			throw new IllegalArgumentException();
		}
		this.edgeDetectioin = edgeDetectioin;
		this.multiplier = multiplier;
	}


	public EdgeDetectionInvoker(int multiplier) {
		this(DEFAULT_EDGEDETECTION, multiplier);
	}
	
	public EdgeDetectionInvoker() {
		this(DEFAULT_EDGEDETECTION, 3);
	}

	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		return edgeDetectioin.detectEdges(source, multiplier);
	}

}
