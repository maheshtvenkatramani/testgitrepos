/**
 * @author Mahesh T Venkatramani (481535)
 * Created Date: 05 October, 2016
 */
package com.cts.bluemix.watson.utility;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import javax.net.ssl.HttpsURLConnection;

/**
 * @author 481535
 *
 */

public class DownloadDocument {
	
	/**
	 * @param urlString - URL of the document
	 * @param destinationDir - temporary destination directory where file will be saved.
	 * @param fileName - File Name of the file
	 * @param ecmUser - ECM User name of the ECM repository where document is stored.
	 * @param ecmPassword - ECM password of the ECM repository where document is stored.
	 * Method uses basic authentication to fetch the document from the ECM system and save it locally.
	 * @throws IOException 
	 */
	public static void downloadFileFromURL(String urlString, String destinationDir,String fileName,String ecmUser, String ecmPassword) throws IOException {    
		System.out.println("Entered DownloadFile Method ---"+urlString+ " "+ destinationDir + " "+fileName+" "+ecmUser+" "+ecmPassword);
		URL website = new URL(urlString);
		HttpsURLConnection con = (HttpsURLConnection)website.openConnection();
		String userPass = ecmUser+":"+ecmPassword;
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userPass.getBytes());
		con.setRequestProperty ("Authorization", basicAuth);            
		ReadableByteChannel rbc;
		rbc = Channels.newChannel(con.getInputStream());
		File destination = new File(destinationDir);
		if(destination.exists()){
			System.out.println("Directory Exists!");
		} else {
			destination.mkdirs();
		}
		File file = new File(destinationDir+fileName);
		file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		rbc.close();
		System.out.println("Downloaded and copied file successfully to temp directory!!"); 

	}
	

}

