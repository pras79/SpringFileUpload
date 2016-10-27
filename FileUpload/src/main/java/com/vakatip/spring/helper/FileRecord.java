package com.vakatip.spring.helper;


public class FileRecord {

    private String Date; 
    private String rowNumber;
    private String description;
    private String value;
    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        Date = date;
    }
    public String getRowNumber() {
        return rowNumber;
    }
    public void setRowNumber(String rowNumber) {
        this.rowNumber = rowNumber;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "CSVFileRecord [Date=" + Date + ", rowNumber=" + rowNumber + ", description=" + description + ", value="
                + value + "]";
    }
    
    
    
}
