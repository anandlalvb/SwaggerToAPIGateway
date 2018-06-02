/**
 * 
 */
package com.anandlal.utilities.swaggertoapigateway;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author anandlal
 *
 */

public class ResponseParameters {
	
	@JsonProperty("method.response.header.Access-Control-Allow-Origin")
	private String accessControlAllowOrigin;
	
	@JsonProperty("method.response.header.ETag")
	private String eTag;

	/**
	 * @return the accessControlAllowOrigin
	 */
	public String getAccessControlAllowOrigin() {
		return accessControlAllowOrigin;
	}

	/**
	 * @param accessControlAllowOrigin the accessControlAllowOrigin to set
	 */
	public void setAccessControlAllowOrigin(String accessControlAllowOrigin) {
		this.accessControlAllowOrigin = accessControlAllowOrigin;
	}

	/**
	 * @return the eTag
	 */
	public String geteTag() {
		return eTag;
	}

	/**
	 * @param eTag the eTag to set
	 */
	public void seteTag(String eTag) {
		this.eTag = eTag;
	}
	
	
}
