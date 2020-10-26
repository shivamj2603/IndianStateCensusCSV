package com.indianstatecensus;

public class CensusAnalyserException extends Exception{
	public enum ExceptionType {
		NO_FILE, INCORRECT_FILE, UNABLE_TO_PARSE, NO_CENSUS_DATA
	}
	public ExceptionType type;
	public CensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}
