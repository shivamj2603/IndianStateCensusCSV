package com.indianstatecensus;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
	public int loadCSVData(String csvFile) throws CensusAnalyserException, IOException {
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFile));) {
			Iterator<CSVStateCensus> censusIterator = new OpenCSVBuilder().getCSVFileIterator(reader, CSVStateCensus.class);
            return this.getCount(censusIterator);
		} 
		catch (IOException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		}
	}
	public int loadStateCodeData(String csvFile) throws CensusAnalyserException, IOException {
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFile));) {
			Iterator<StateCodeCSV> censusIterator = new OpenCSVBuilder().getCSVFileIterator(reader, StateCodeCSV.class);
            return this.getCount(censusIterator);
		} 
		catch (IOException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		}
	}
	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		int countOfRecords = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return countOfRecords;
	}
}
