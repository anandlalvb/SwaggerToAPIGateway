/**
 * 
 */
package com.anandlal.utilities.swaggertoapigateway;

/**
 * @author anandlal
 *
 */

public class AmazonAPIGateway {
	String type;
	String httpMethod;
	String passthroughBehavior;
	String uri;
	Response responses;
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the httpMethod
	 */
	public String getHttpMethod() {
		return httpMethod;
	}

	/**
	 * @param httpMethod the httpMethod to set
	 */
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	/**
	 * @return the passthroughBehavior
	 */
	public String getPassthroughBehavior() {
		return passthroughBehavior;
	}

	/**
	 * @param passthroughBehavior the passthroughBehavior to set
	 */
	public void setPassthroughBehavior(String passthroughBehavior) {
		this.passthroughBehavior = passthroughBehavior;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the responses
	 */
	public Response getResponses() {
		return responses;
	}

	/**
	 * @param responses the responses to set
	 */
	public void setResponses(Response responses) {
		this.responses = responses;
	}


}
