package com.vakatip.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import com.vakatip.spring.helper.FileParserHelper;
import com.vakatip.spring.helper.FileRecord;

/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileUploadController {

	@Resource 
	FileParserHelper fileParserHelper ;
	
	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file, ModelMap model) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("user.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				//read the CSV file
				Set<FileRecord> formattedData =   fileParserHelper.parseCSV(serverFile.getAbsolutePath());
				
				model.addAttribute("fileRecords", formattedData);
				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());
				
				
				//return to uploadResults.jsp
				return  "uploadResults";
			} catch (Exception e) {
				model.addAttribute("errorMessage" , "You failed to upload " + name + " => " + e.getMessage());
				return "uploadResults";
			}
		} else {
			model.addAttribute("errorMessage" , "You failed to upload " + name
					+ " because the file was empty.");
			
			return "uploadResults";
		}
	}
	
}
