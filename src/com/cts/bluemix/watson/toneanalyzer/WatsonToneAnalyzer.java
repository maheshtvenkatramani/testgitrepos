/**
 * @author Mahesh T Venkatramani (481535)
 * Created Date: 05 October, 2016
 *
 */
package com.cts.bluemix.watson.toneanalyzer;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;

public class WatsonToneAnalyzer {
	
	/**
	 * @param bluemixUser - Bluemix User name
	 * @param bluemixPassword - Bluemix Password
	 * @param text - Text to be analyzed
	 * @return - JSON String of the tone analysis results
	 */
	public String getTone(String watsonSvcVersion,String bluemixUser, String bluemixPassword, String text){
		ToneAnalyzer service = new ToneAnalyzer(watsonSvcVersion);
		service.setUsernameAndPassword(bluemixUser,bluemixPassword);
		ToneAnalysis tone = (ToneAnalysis) service.getTone(text, null).execute();
		Gson gson = new Gson();
		String jsonStrOutput = gson.toJson(tone);
		System.out.println(jsonStrOutput);
		return jsonStrOutput;
	}

}
