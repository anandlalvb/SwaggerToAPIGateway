/**
 * 
 */
package com.anandlal.utilities.swaggertoapigateway;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author anandlal
 *
 */
public class MainObject {
	
	@JsonProperty("x-amazon-apigateway-integration")
	AmazonAPIGateway amazonAPIGateway;

	/**
	 * @return the amazonAPIGateway
	 */
	public AmazonAPIGateway getAmazonAPIGateway() {
		return amazonAPIGateway;
	}

	/**
	 * @param amazonAPIGateway the amazonAPIGateway to set
	 */
	public void setAmazonAPIGateway(AmazonAPIGateway amazonAPIGateway) {
		this.amazonAPIGateway = amazonAPIGateway;
	}
	
}
