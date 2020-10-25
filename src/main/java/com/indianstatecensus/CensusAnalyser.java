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
			CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<CSVStateCensus>(reader);
			csvToBeanBuilder.withType(CSVStateCensus.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
			Iterator<CSVStateCensus> censusIterator = csvToBean.iterator();
			int countOfRecord = 0;
			while (censusIterator.hasNext()) {
				countOfRecord++;
				CSVStateCensus censusData = censusIterator.next();
			}
			return countOfRecord;
		} 
		catch (IOException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		}
		catch (RuntimeException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
	public int loadStateCodeData(String csvFile) throws CensusAnalyserException, IOException {
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFile));) {
			CsvToBeanBuilder<StateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<StateCodeCSV>(reader);
			csvToBeanBuilder.withType(StateCodeCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<StateCodeCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<StateCodeCSV> censusIterator = csvToBean.iterator();
			int countOfRecord = 0;
			while (censusIterator.hasNext()) {
				countOfRecord++;
				StateCodeCSV censusData = censusIterator.next();
			}
			return countOfRecord;
		} 
		catch (IOException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		}
		catch (RuntimeException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
}
