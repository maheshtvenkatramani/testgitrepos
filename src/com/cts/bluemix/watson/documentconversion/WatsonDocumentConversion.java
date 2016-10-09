/**
 * 
 */
package com.cts.bluemix.watson.documentconversion;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ibm.watson.developer_cloud.document_conversion.v1.DocumentConversion;
import com.ibm.watson.developer_cloud.document_conversion.v1.model.Answers;
import com.ibm.watson.developer_cloud.document_conversion.v1.model.Answers.AnswerUnits;
import com.cts.bluemix.watson.utility.DownloadDocument;
/**
 * @author Mahesh T Venkatramani (481535)
 * Created Date: 05 October, 2016
 *
 */
public class WatsonDocumentConversion {

	/**
	 * @param file - File object of the file to be converted. Document Conversion currently supports HTML, doc, docx, PDF files.
	 * @param watsonDocConvVersion: Version of the Watson Services to be used. As on 10/5/16, version: "2015-02-01"
	 * @param blmxUser - Bluemix Account user name
	 * @param blmxPass - Bluemix Account password
	 * @return JSON String of the converted Document
	 */
	private String convertDocumentToAnswers(File file,String watsonDocConvVersion, String blmxUser, String blmxPass){
		DocumentConversion service = new DocumentConversion(watsonDocConvVersion,blmxUser,blmxPass);
		Answers answers = service.convertDocumentToAnswer(file).execute();
		Gson gson = new Gson();
		Type type = new TypeToken<List<AnswerUnits>>() {}.getType();
		String jsonStrResults = gson.toJson(answers.getAnswerUnits(), type);
		return jsonStrResults;
	}
	
	/**
	 * @param file - File object of the file to be converted. Document Conversion currently supports HTML, doc, docx, PDF files.
	 * @param watsonDocConvVersion: Version of the Watson Services to be used. As on 10/5/16, version: "2015-02-01"
	 * @param blmxUser - Bluemix Account user name
	 * @param blmxPass - Bluemix Account password
	 * @return  String of the converted Document
	 */
	private String convertDocumentToText(File file,String watsonDocConvVersion, String blmxUser, String blmxPass){
		DocumentConversion service = new DocumentConversion(watsonDocConvVersion,blmxUser,blmxPass);
		String jsonStrResults = service.convertDocumentToText(file).execute();
		return jsonStrResults;
	}
	/**
	 * @param file - File object of the file to be converted. Document Conversion currently supports HTML, doc, docx, PDF files.
	 * @param watsonDocConvVersion: Version of the Watson Services to be used. As on 10/5/16, version: "2015-02-01"
	 * @param blmxUser - Bluemix Account user name
	 * @param blmxPass - Bluemix Account password
	 * @return HTML String of the converted Document
	 */
	private String convertDocumentToHTML(File file,String watsonDocConvVersion, String blmxUser, String blmxPass){
		DocumentConversion service = new DocumentConversion(watsonDocConvVersion,blmxUser,blmxPass);
		String jsonStrResults = service.convertDocumentToHTML(file).execute();
		return jsonStrResults;
	}
	/**
	 * @param documentURL - URL of the document
	 * @param fileName - Name of the file
	 * @param bluemixUser - Bluemix user name
	 * @param bluemixPass - Bluemix password
	 * @param ecmUser - ECM Repository user name
	 * @param ecmPass - ECM Repository password
	 * @return JSON String of the converted document
	 * @throws IOException
	 */
	public String convertDocument(String outputFormat, String documentURL,String fileName,String blmxUser,String blmxPass, String ecmUser, String ecmPass) throws IOException{
		String jsonStrResults = "";
		String destinationDir = "";
		String destinatonLoc = "";
		destinationDir = "src/tempdocs/";
		DownloadDocument.downloadFileFromURL(documentURL, destinationDir,fileName, ecmUser, ecmPass);
		destinatonLoc = destinationDir + fileName;
		System.out.println("Destination Location: "+destinatonLoc);
		File file = new File(destinatonLoc);
		System.out.println("File Exists? "+file.exists());
		if(!file.exists()){		
			throw new IOException("File does not exist in the temporary directory!");
		}
		if(outputFormat.toUpperCase() == "ANSWERS"){
			jsonStrResults = 	convertDocumentToAnswers(file, "2015-02-01", blmxUser, blmxPass);
		} else if(outputFormat.toUpperCase() == "TEXT") {
			jsonStrResults = 	convertDocumentToText(file, "2015-02-01", blmxUser, blmxPass);
		} else {
			jsonStrResults = 	convertDocumentToHTML(file, "2015-02-01", blmxUser, blmxPass);
		}
    	if(file.delete()){
    		System.out.println("File removed from temp location successfully!!");
    	} else {
    		System.err.println("Unable to remove file from temp location!!");
    	}
		return jsonStrResults;
	}

}
