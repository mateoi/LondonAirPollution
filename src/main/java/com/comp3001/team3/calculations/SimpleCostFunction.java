package com.comp3001.team3.calculations;

public class SimpleCostFunction implements CostFunction {

	private PollutionDataSource datasource;
	
	public SimpleCostFunction(PollutionDataSource datasource) {
		this.datasource = datasource;
	}

	@Override
	public double getCost(LatLong... coords) {
		double total = 0;
		for (LatLong latlong : coords) {
			total += datasource.getValueAt(latlong);
		}
		return total;
	}

}
