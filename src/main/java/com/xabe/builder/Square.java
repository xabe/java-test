package com.xabe.builder;

public class Square extends Shape {

	private final int edge;

	public Square(String name, int edge) {
		super(name);
		this.edge = edge;
	}

	public int getEdge() {
		return edge;
	}

}
