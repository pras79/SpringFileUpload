package com.vakatip.spring.helper;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.inject.Named;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


@Named
public class FileParserHelperImpl implements FileParserHelper {

	private static final String  _PARSEPATTERN = "MM/dd/yyyy'T'HH:mm:ss'Z'";
	private static final String  _FORMATPATTERN = "M/dd/yy hh:mm a z";

	private static final Logger logger = LoggerFactory
			.getLogger(FileParserHelperImpl.class);


	/*  read CSV file using CSVParser from Apache Commons CSV */
	public Set<FileRecord> parseCSV(String filePath)  { 

		Set<FileRecord> refinedDataSet = null ;
		try{
			CSVParser parser = new CSVParser(new 
					FileReader(filePath), 
					CSVFormat.DEFAULT.withDelimiter(','));
			Map <String,FileRecord>  map = new java.util.HashMap<> () ;

			// setting values to HashMap and removing the duplicate by creating a key as date and description combination 
			for (CSVRecord record : parser) {
				FileRecord  fileRecord = new FileRecord();
				fileRecord.setDate( StringUtils.isEmpty(record.get(0)) ? record.get(0):formatDate(record.get(0)));
				fileRecord.setRowNumber(record.get(1));
				fileRecord.setDescription(record.get(2));
				fileRecord.setValue( record.get(3) !=null && StringUtils.isEmpty(record.get(3).trim())?record.get(3):convertValueIntoUSDFormat(new BigDecimal(record.get(3))));

				String key = fileRecord.getDate()+fileRecord.getDescription();
				map.put(key, fileRecord);

			}
			// populating to all values to set for UI iteration
			refinedDataSet = new HashSet<>(map.values());
			parser.close();

		}catch ( ParseException | IOException ex){

			logger.error("error while parsing the data:" + ex );

		}
		return refinedDataSet ;

	}
	/* date formatter */
	private  String formatDate (String date) throws ParseException {

		if ( date !=null && ! date.trim().equals("")){

			SimpleDateFormat format = new SimpleDateFormat(
					_PARSEPATTERN, Locale.US);
			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			Date convertedDate = format.parse(date);
			SimpleDateFormat uiFormat = new SimpleDateFormat(_FORMATPATTERN);
			return uiFormat.format(convertedDate);

		}
		return date;

	}
	/*converting the input value into US currency value */
	private String convertValueIntoUSDFormat ( BigDecimal inputValue ) {

		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US); 
		return currencyFormat.format(inputValue);
	}

}
