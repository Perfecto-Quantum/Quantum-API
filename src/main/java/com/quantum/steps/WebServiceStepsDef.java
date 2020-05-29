/**
 * 
 */
package com.quantum.steps;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qmetry.qaf.automation.rest.RestRequestBean;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.step.WsStep;
import com.qmetry.qaf.automation.ws.rest.RestTestBase;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author kulin.sitwala
 *
 */
@QAFTestStepProvider
public class WebServiceStepsDef {
	

	@SuppressWarnings("unchecked")
	@QAFTestStep(description = "user request with json data {0}")
	public static ClientResponse iTestData(Object data) throws KeyManagementException, NoSuchAlgorithmException {

		System.out.println(data.getClass());
		JsonObject jsonObject = new Gson().toJsonTree(data).getAsJsonObject();
		HashMap<String, Object> result;
		try {
			result = new ObjectMapper().readValue(jsonObject.toString(), HashMap.class);
			System.out.println(jsonObject.toString());
			RestRequestBean bean = new RestRequestBean();
			bean.fillData(jsonObject.toString());
			bean.resolveParameters(result);
			//return request(bean);
			return WsStep.request(bean);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	@QAFTestStep(description = "I check response code")
	public static void iTestResponseCode() {
		System.out.println(
				"@#@#@#@#@#@@#@#@#@ Response code " + new RestTestBase().getResponse().getStatus().getStatusCode());
	}

	@QAFTestStep(description = "I validate the application Urls for logged of users in response")
	public static void iTestResponseData() {
		System.out.println("@#@#@#@#@#@@#@#@#@ Response body " + new RestTestBase().getResponse().getMessageBody());
	}

	

}
