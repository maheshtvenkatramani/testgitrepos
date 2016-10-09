/**
 * @author Mahesh T Venkatramani (481535)
 * Created Date: 05 October, 2016
 *
 */
package com.cts.bluemix.watson.personalityinsights;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.personality_insights.v2.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v2.model.Profile;

public class WatsonPersonalityInsights {
	
	/**
	 * @param bluemixUser - Bluemix user name
	 * @param bluemixPassword - Bluemix password
	 * @param text - Text to be analyzed
	 * @return JSON String of the personality insights analysis results
	 */
	public String getPersonality(String bluemixUser, String bluemixPassword, String text){
		PersonalityInsights service = new PersonalityInsights();
		service.setUsernameAndPassword(bluemixUser,bluemixPassword);
		Profile profile = service.getProfile(text).execute();
		Gson gson = new Gson();
		String jsonStrResults = gson.toJson(profile);
		System.out.println(jsonStrResults);
		return jsonStrResults;
	}
}
