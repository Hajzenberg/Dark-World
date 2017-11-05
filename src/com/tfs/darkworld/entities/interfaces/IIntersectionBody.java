package com.tfs.darkworld.entities.interfaces;

import com.tfs.darkworld.entities.IntersectType;

public interface IIntersectionBody extends IRenderable{
	IntersectType isIntersecting(IIntersectionBody intersectionBody);
}
