/**
 * 
 */
package com.anandlal.utilities.swaggertoapigateway;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author anandlal
 *
 */
@JsonInclude(Include.NON_NULL)
public class Default {
	private String statusCode;
	private ResponseParameters responseParameters;

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the responseParameters
	 */
	public ResponseParameters getResponseParameters() {
		return responseParameters;
	}

	/**
	 * @param responseParameters the responseParameters to set
	 */
	public void setResponseParameters(ResponseParameters responseParameters) {
		this.responseParameters = responseParameters;
	}
	
}
