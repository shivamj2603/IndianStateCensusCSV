package com.test.indianstatecensus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.indianstatecensus.CensusAnalyser;
import com.indianstatecensus.CensusAnalyserException;

public class CensusAnalyserTest {
	private static final String STATECENSUS_CSVFILE = "C:\\Users\\shiva\\eclipse-workspace\\Census\\IndiaStateCensusData.csv";
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
}