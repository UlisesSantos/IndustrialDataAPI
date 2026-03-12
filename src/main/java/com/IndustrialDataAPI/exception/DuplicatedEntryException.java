package com.IndustrialDataAPI.exception;

public class DuplicatedEntryException extends RuntimeException {
    public DuplicatedEntryException() {
        super("Duplicated Entry");
    }
}
