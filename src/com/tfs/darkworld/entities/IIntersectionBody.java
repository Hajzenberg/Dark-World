package com.tfs.darkworld.entities;

public interface IIntersectionBody extends IRenderable{
	IntersectType isIntersecting(IIntersectionBody intersectionBody);
}
