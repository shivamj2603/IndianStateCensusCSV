package com.test.indianstatecensus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import com.indianstatecensus.CSVStateCensus;
import com.indianstatecensus.CensusAnalyser;
import com.indianstatecensus.CensusAnalyserException;
import com.indianstatecensus.StateCodeCSV;
import CSVBuilder.CSVBuilderException;

public class CensusAnalyserTest {
	private static final String STATECENSUS_CSVFILE = "C:\\Users\\shiva\\eclipse-workspace\\Census\\IndiaStateCensusData.csv";
	private static final String WRONG_FILE = "C:\\Users\\shiva\\eclipse-workspace\\Census\\IndianStateCensus.csv";
	private static final String WRONG_EXTENSION = "C:\\Users\\shiva\\eclipse-workspace\\Census\\IndiaStateCensusData.txt";
	private static final String CSVFILE = "C:\\Users\\shiva\\eclipse-workspace\\Census\\USCensusData.csv";
	private static final String STATECODE_CSV = "C:\\Users\\shiva\\eclipse-workspace\\Census\\IndiaStateCode.csv";
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
	@Test
	public void givenCSVFile_whenFileCorrect_butHeaderIncorrect_shouldThrowError() throws IOException {
		CensusAnalyser analyser = new CensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(CSVFILE);
		} catch (CensusAnalyserException exception) {
			System.out.println("Incorrect file");
			assertEquals(CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, exception.type);
		}
	}
	@Test
	public void givenStateCodeCSVFile_ifMatchesTotalNumberOfRecords_ShouldReturnTrue() throws IOException {
		CensusAnalyser analyser = new CensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadStateCodeData(STATECODE_CSV);
		} catch (CensusAnalyserException exception) {
			exception.printStackTrace();
		}
		assertEquals(37, count);
	}
	@Test
	public void givenStateCodeCSVFile_whenWrongFile_ShouldThrowError() throws IOException {
		CensusAnalyser analyser = new CensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadStateCodeData(WRONG_FILE);
		} catch (CensusAnalyserException exception) {
			System.out.println("No such file found");
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, exception.type);
		}
	}
	@Test
	public void givenStateCodeCSVFile_butExtensionIncorrect_shouldThrowError() throws IOException {
		CensusAnalyser analyser = new CensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadStateCodeData(WRONG_EXTENSION);
		} catch (CensusAnalyserException exception) {
			System.out.println("No such file found");
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, exception.type);
		}
	}
	@Test
	public void givenStateCodeCSVFile_whenFileCorrect_butDelimiterIncorrect_shouldThrowError() throws IOException {
		CensusAnalyser analyser = new CensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadStateCodeData(CSVFILE);
		} catch (CensusAnalyserException exception) {
			System.out.println("Incorrect file");
			assertEquals(CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, exception.type);
		}
	}
	@Test
	public void givenStateCodeCSVFile_whenFileCorrect_butHeaderIncorrect_shouldThrowError() throws IOException {
		CensusAnalyser analyser = new CensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadStateCodeData(CSVFILE);
		} catch (CensusAnalyserException exception) {
			System.out.println("Incorrect file");
			assertEquals(CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, exception.type);
		}
	}
	/**
	 * TestCase 3
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getStateWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Andhra Pradesh", censusCSV[0].state);
	}
	/**
	 * TestCase 3
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResultForLastState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getStateWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("West Bengal", censusCSV[censusCSV.length - 1].state);
	}
	/**
	 * TestCase 4
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnStateCode_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadStateCodeData(STATECODE_CSV);
		String sortedCensusData = analyser.getStateCodeWiseSortedCensusData();
		StateCodeCSV[] censusCSV = new Gson().fromJson(sortedCensusData, StateCodeCSV[].class);
		assertEquals("Andhra Pradesh New", censusCSV[0].state);
	}
	/**
	 * TestCase 4
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnStateCode_ShouldReturnSortedResultForLastState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadStateCodeData(STATECODE_CSV);
		String sortedCensusData = analyser.getStateCodeWiseSortedCensusData();
		StateCodeCSV[] censusCSV = new Gson().fromJson(sortedCensusData, StateCodeCSV[].class);
		assertEquals("West Bengal", censusCSV[censusCSV.length - 1].state);
	}
	/**
	 * TestCase 5
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnStatePopulation_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getPopulationWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Uttar Pradesh", censusCSV[0].state);
	}
	/**
	 * TestCase 5
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnStatePopulation_ShouldReturnSortedResultForLast()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getPopulationWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Sikkim", censusCSV[censusCSV.length - 1].state);
	}
	/**
	 * TestCase 6
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getPopulationDensityWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		for(CSVStateCensus c : censusCSV) {
			System.out.println(c.state);
		}
		assertEquals("Bihar", censusCSV[0].state);
	}
	/**
	 * TestCase 6
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResultForLast()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getPopulationDensityWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Arunachal Pradesh", censusCSV[censusCSV.length - 1].state);
	}
	@Test
	public void givenIndianCensusData_WhenSortedOnArea_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getAreaWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Rajasthan", censusCSV[0].state);
	}
	@Test
	public void givenIndianCensusData_WhenSortedOnArea_ShouldReturnSortedResultForLast()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getAreaWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Rajasthan", censusCSV[0].state);
	}
}
