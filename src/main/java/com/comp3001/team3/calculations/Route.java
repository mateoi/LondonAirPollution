package com.comp3001.team3.calculations;

public class Route {
	private final String polyline;
	private final float cost;
	private final float distance;
	
	public Route(String polyline, float cost, float distance) {
		this.polyline = polyline;
		this.cost = cost;
		this.distance = distance;
	}
	
	public String getPolyline() {
		return polyline;
	}
	public float getCost() {
		return cost;
	}
	
	public float getDistance() {
		return distance;
	}
}
