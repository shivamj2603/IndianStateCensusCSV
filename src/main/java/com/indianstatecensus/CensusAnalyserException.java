package com.indianstatecensus;

public class CensusAnalyserException extends Exception {
	public enum ExceptionType {
		UNABLE_TO_PARSE, INCORRECT_FILE
	}
	public ExceptionType type;
	public CensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}