package com.test.indianstatecensus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.indianstatecensus.CensusAnalyser;
import com.indianstatecensus.CensusAnalyserException;

public class CensusAnalyserTest {
	private static final String STATECENSUS_CSVFILE = "C:\\Users\\shiva\\eclipse-workspace\\Census\\IndiaStateCensusData.csv";
	private static final String WRONG_FILE = "C:\\Users\\shiva\\eclipse-workspace\\Census\\IndianStateCensus.csv";
	private static final String WRONG_EXTENSION = "C:\\Users\\shiva\\eclipse-workspace\\Census\\IndiaStateCensusData.txt";
	private static final String CSVFILE = "C:\\Users\\shiva\\eclipse-workspace\\Census\\USCensusData.csv";
	@Test
	public void givenCSVFile_ifMatchesTotalNumberOfRecords_ShouldReturnTrue() throws IOException {
		CensusAnalyser analyser = new CensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(STATECENSUS_CSVFILE);
		} catch (CensusAnalyserException exception) {
			exception.printStackTrace();
		}
		assertEquals(29, count);
	}
	@Test
	public void givenCSVFile_whenWrongFile_ShouldThrowError() throws IOException {
		CensusAnalyser analyser = new CensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(WRONG_FILE);
		} catch (CensusAnalyserException exception) {
			System.out.println("No such file found");
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, exception.type);
		}
	}
	@Test
	public void givenCSVFile_whenFileCorrect_butExtensionIncorrect_shouldThrowError() throws IOException {
		CensusAnalyser analyser = new CensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(WRONG_EXTENSION);
		} catch (CensusAnalyserException exception) {
			System.out.println("No such file found");
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, exception.type);
		}
	}
	@Test
	public void givenCSVFile_whenFileCorrect_butDelimiterIncorrect_shouldThrowError() throws IOException {
		CensusAnalyser analyser = new CensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(STATECENSUS_CSVFILE);
		} catch (CensusAnalyserException exception) {
			System.out.println("Incorrect file");
			assertEquals(CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, exception.type);
		}
	}
}