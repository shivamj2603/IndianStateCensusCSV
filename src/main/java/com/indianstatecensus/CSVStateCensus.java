package com.indianstatecensus;
import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
	@CsvBindByName(column = "State")
	public String state;

	@CsvBindByName(column = "Population", required = true)
	public String population;

	@CsvBindByName(column = "AreaInSqKm")
	public String areaInSqKm;

	@CsvBindByName(column = "DensityPerSqKm", required = true)
	public String densityPerSqKm;
}
