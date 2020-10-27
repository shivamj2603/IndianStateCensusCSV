package com.indianstatecensus;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import CSVBuilder.CSVBuilderException;
import CSVBuilder.CSVBuilderFactory;
import CSVBuilder.ICSVBuilder;

import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
	List<CSVStateCensus> censusCSVList = null;
	List<StateCodeCSV> stateCodeCSVList = null;
	public int loadCSVData(String csvFile) throws CensusAnalyserException, IOException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			ICSVBuilder<CSVStateCensus> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			censusCSVList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
			return censusCSVList.size();
		} 
		catch(CSVBuilderException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
		catch (IOException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		}
	}
	public int loadStateCodeData(String csvFile) throws CensusAnalyserException, IOException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			ICSVBuilder<StateCodeCSV> csvBuilder = CSVBuilderFactory.createCSVBuilder();
		    stateCodeCSVList = csvBuilder.getCSVFileList(reader, StateCodeCSV.class);
			return stateCodeCSVList.size();
		} 
		catch(CSVBuilderException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
		catch (IOException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		}
	}
	private <E> String sort(List<E> censusList, Comparator<E> censusComparator) throws CensusAnalyserException {
		if(censusList == null || censusList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		for (int i = 0; i < censusList.size(); i++) {
			for (int j = 0; j < censusList.size() - i - 1; j++) {
				E census1 =  censusList.get(j);
				E census2 =  censusList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					censusList.set(j, census2);
					censusList.set(j + 1, census1);
				}
			}
		}
		String sortedCensusJson = new Gson().toJson(censusList);
		return sortedCensusJson;
	}
	/**
	 * UseCase 3
	 * Sort the data according to State Name
	 * @return 
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getStateWiseSortedCensusData() throws CensusAnalyserException {
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.state);
		String sortedState = this.sort(censusCSVList, censusComparator);
		return sortedState;
	}
	/**
	 * UseCase 4
	 * Sort StateCode data
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
		Comparator<StateCodeCSV> censusComparator = Comparator.comparing(census -> census.stateCode);
		String sortedStateCode = this.sort(stateCodeCSVList, censusComparator);
		return sortedStateCode;
	}
	/**
	 * UseCase 5
	 * Sort Data by Population
	 * @return 
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.population);
		String sortedPopulation = this.sort(censusCSVList, censusComparator.reversed());
		return sortedPopulation;
	}
	/**
	 * Usecase 6
	 * Sort Data by population density
	 * @return 
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getPopulationDensityWiseSortedCensusData() throws CensusAnalyserException {
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
		String sortedDensity = this.sort(censusCSVList, censusComparator.reversed());
		return sortedDensity;
	}	
	/**
	 * Usecase 7
	 * Sort Data by Area
	 * @return 
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getAreaWiseSortedCensusData() throws CensusAnalyserException {
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
		String sortedArea = this.sort(censusCSVList, censusComparator.reversed());
		return sortedArea;
	}		
}
