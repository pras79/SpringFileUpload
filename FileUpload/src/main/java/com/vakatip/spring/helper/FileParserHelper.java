package com.vakatip.spring.helper;

import java.util.Set;

/*helper is designed to parse the uploaded file using Apache CSV commons */

public interface FileParserHelper {

	/*  read CSV file using CSVParser from Apache Commons CSV */
	public  Set<FileRecord> parseCSV(String filePath);
	
}
