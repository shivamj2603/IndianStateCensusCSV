package com.indianstatecensus;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvRuntimeException;

public class CensusAnalyser {
	public int loadCSVData(String csvFile) throws CensusAnalyserException, IOException {
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFile));) {
			Iterator<CSVStateCensus> censusIterator = this.getCSVFileIterator(reader, CSVStateCensus.class);
			int countOfRecord = this.getCount(censusIterator);
			return countOfRecord;
		} 
		catch (IOException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		}
	}
	public int loadStateCodeData(String csvFile) throws CensusAnalyserException, IOException {
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFile));) {
			Iterator<StateCodeCSV> censusIterator = this.getCSVFileIterator(reader, StateCodeCSV.class);
			int countOfRecord = this.getCount(censusIterator);
			return countOfRecord;
		} 
		catch (IOException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		}
	}
	private <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} 
		catch (RuntimeException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
	private <E> int getCount(Iterator<E> iterator) {
		int countOfRecord = 0;
		while (iterator.hasNext()) {
			countOfRecord++;
			E censusData = iterator.next();
		}
		return countOfRecord;
	}
}
