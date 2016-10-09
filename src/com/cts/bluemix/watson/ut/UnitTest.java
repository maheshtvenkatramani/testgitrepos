/**
 * 
 */
package com.cts.bluemix.watson.ut;

import java.io.IOException;

import com.cts.bluemix.watson.documentconversion.WatsonDocumentConversion;
import com.cts.bluemix.watson.personalityinsights.WatsonPersonalityInsights;
import com.cts.bluemix.watson.toneanalyzer.WatsonToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;

/**
 * @author 481535
 *
 */
public class UnitTest {

	/**
	 * @param args
	 */
	public static final String BLMX_PI_USER = "0bb1d027-c96d-4fea-8c97-405408622b68";
	public static final String BLMX_PI_PWORD = "UGa7xn0YZpHL";
	public static final String BLMX_TA_USER = "42937df4-6997-4d19-b2e6-223c24873426";
	public static final String BLMX_TA_PWORD = "uMWGfTLzYAHo";
	public static final String BLMX_DC_USER = "5ccdcd46-c34f-465d-b41e-bd4e133cbe18";
	public static final String BLMX_DC_PWORD = "OcYUOhNVN0d7";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jsonResults = "";
		String text = "Before you discuss the resolution, let me place before you one or two things, I want you to understand two things very clearly and to consider them from the same point of view from which I am placing them before you. I ask you to consider it from my point of view, because if you approve of it, you will be enjoined to carry out all I say. It will be a great responsibility. There are people who ask me whether I am the same man that I was in 1920, or whether there has been any change in me. You are right in asking that question.Let me, however, hasten to assure that I am the same Gandhi as I was in 1920. I have not changed in any fundamental respect. I attach the same importance to non-violence that I did then. If at all, my emphasis on it has grown stronger. There is no real contradiction between the present resolution and my previous writings and utterances.Occasions like the present do not occur in everybody’s and but rarely in anybody’s life. I want you to know and feel that there is nothing but purest Ahimsa in all that I am saying and doing today. The draft resolution of the Working Committee is based on Ahimsa, the contemplated struggle similarly has its roots in Ahimsa. If, therefore, there is any among you who has lost faith in Ahimsa or is wearied of it, let him not vote for this resolution. Let me explain my position clearly. God has vouchsafed to me a priceless gift in the weapon of Ahimsa. I and my Ahimsa are on our trail today. If in the present crisis, when the earth is being scorched by the flames of Himsa and crying for deliverance, I failed to make use of the God given talent, God will not forgive me and I shall be judged unworthy of the great gift. I must act now. I may not hesitate and merely look on, when Russia and China are threatened.";
		//WatsonPersonalityInsights pi = new WatsonPersonalityInsights();
		//jsonResults = pi.getPersonality(BLMX_PI_USER, BLMX_PI_PWORD, text);
		//WatsonToneAnalyzer ta = new WatsonToneAnalyzer();
		//jsonResults  = ta.getTone(ToneAnalyzer.VERSION_DATE_2016_05_19, BLMX_TA_USER, BLMX_TA_PWORD, text);
		
		WatsonDocumentConversion dc = new WatsonDocumentConversion();
		String url = "https://cognizant-ipm.bpm.ibmcloud.com:443/bpm/dev/portal/jsp/ecmDocument?operation=ajax_getDocumentContent&snapshotId=2064.3829fc2c-b76f-4f9d-84fc-e5cf34cb8697&ecmServerConfigurationName=EMBEDDED_ECM_SERVER&documentId=idd_80D78E56-0000-C815-939A-57352E6D520B";
		String fileName = "";
		try {
			jsonResults = dc.convertDocument("ANSWERS",url,fileName, BLMX_DC_USER, BLMX_DC_PWORD, "mahesh.thiagarajanvenkatramani@cognizant.com", "Anika@2612");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Results > "+jsonResults);
		
		

	}

}
