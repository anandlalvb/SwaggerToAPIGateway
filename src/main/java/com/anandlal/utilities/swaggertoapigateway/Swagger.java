/**
 * 
 */
package com.anandlal.utilities.swaggertoapigateway;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author anandlal
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Swagger {
	
	@JsonProperty("swagger")
	private String swagger;
	
	/*@JsonProperty("paths")
	private Path paths;*/
	/**
	 * @return the swagger
	 */
	public String getSwagger() {
		return swagger;
	}

	/**
	 * @param swagger the swagger to set
	 */
	public void setSwagger(String swagger) {
		this.swagger = swagger;
	}

/*	*//**
	 * @return the paths
	 *//*
	public Path getPaths() {
		return paths;
	}

	*//**
	 * @param paths the paths to set
	 *//*
	public void setPaths(Path paths) {
		this.paths = paths;
	}*/
	
}
