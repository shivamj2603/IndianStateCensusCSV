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
			List<StateCodeCSV> censusCSVList = csvBuilder.getCSVFileList(reader, StateCodeCSV.class);
			return censusCSVList.size();
		} 
		catch(CSVBuilderException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
		catch (IOException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		}
	}
}
