/**
 * 
 */
package com.anandlal.utilities.swaggertoapigateway;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author anandlal
 *
 */
public class Response {
	
	@JsonProperty("default")
	Default default1;

	@JsonProperty("304")
	Default threeNotFourNode;
	
	/**
	 * @return the default1
	 */
	public Default getDefault1() {
		return default1;
	}

	/**
	 * @param default1 the default1 to set
	 */
	public void setDefault1(Default default1) {
		this.default1 = default1;
	}

	/**
	 * @return the threeNotFourNode
	 */
	public Default getThreeNotFourNode() {
		return threeNotFourNode;
	}

	/**
	 * @param threeNotFourNode the threeNotFourNode to set
	 */
	public void setThreeNotFourNode(Default threeNotFourNode) {
		this.threeNotFourNode = threeNotFourNode;
	}
	
}
