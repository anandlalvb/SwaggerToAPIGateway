package com.anandlal.utilities.swaggertoapigateway;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author anandlal
 *
 * This is a utility to convert Swagger json to API Gateway json with appropriate HTTPS endpoint routing
 */

public class App {
	
	private static final String API_DOC_URL = "/v2/api-docs";
	private static final String USER_POOL_DEV = "UserPool_DEV";//Cognito UserPool 
	private static final String USER_POOL_ID_DEV = "us-west-2_1";
	private static final String SWAGGER_BASE_URL_DEV = "http://localhost:8080/";

	private static final String URI_ELB = "https://${stageVariables.URI_ELB}/";
	

	private static final String USER_POOL_QA = "UserPool_DEV";//Cognito UserPool 
	private static final String USER_POOL_ID_QA = "us-west-2_2";
	private static final String SWAGGER_BASE_URL_QA = "http://1.2.3.4:8080/";

	private static final String USER_POOL_DEMO = "UserPool_Demo";//Cognito UserPool 
	private static final String USER_POOL_ID_DEMO = "us-west-2_3";
	private static final String SWAGGER_BASE_URL_DEMO = "http://1.2.3.4:8080/";//"http://elbdemo.kisticket.com/";

	private static final String USER_POOL_STG = "UserPool_Stage";//Cognito UserPool 
	private static final String USER_POOL_ID_STG = "us-west-2_4";
	private static final String SWAGGER_BASE_URL_STG = "http://1.2.3.4:8080/";

	private static final String USER_POOL_UAT = "UserPool_UAT";//Cognito UserPool 
	private static final String USER_POOL_ID_UAT = "us-west-2_5";
	private static final String URI_ELB_UAT = "https://${stageVariables.URI_ELB_UAT_MODULE}/";
	private static final String SWAGGER_BASE_URL_UAT = "http://1.2.3.4:8080/";

	private static final String USER_POOL_PROD = "UserPool";//Cognito UserPool 
	private static final String USER_POOL_ID_PROD = "us-west-2_6";
	private static final String URI_ELB_PROD = "https://${stageVariables.URI_ELB_PROD_MODULE}/";
	private static final String SWAGGER_BASE_URL_PROD = "http://35.166.48.189:8080/";

	/**
	 * List of all the Environments for the WWL Project
	 * @author anandlal
	 *
	 */
	private enum Env {
		DEV, QA, DEMO, STG, UAT, PROD
	}

	private static final Boolean isFromServer = false;//Set it to get Swagger file from server 

	/**
	 * List of all the modules/microservices in the app
	 * @author anandlal
	 *
	 */
	private enum Module {
		A, B
	}

    public static void main(String[] args) throws IOException {
    		//System.out.println("CAHRSET :" + java.nio.charset.Charset.defaultCharset()); 
        // Configuration
        Env env = Env.QA;
        // End Configuration
        for (Module moduleSelected : Module.values()) {
            convert(env, moduleSelected);
        }
    }
	
	public static void convert(Env env, Module moduleSelected) throws IOException {
		
		System.out.println("===============================================================");
		System.out.println("===================== "+ moduleSelected + " =====================");
		System.out.println("================================================================");
		
		// private static final String 
		// read json file data to String
		String module = null;
		
		if (moduleSelected == Module.A){
			module = "A";
		}else if (moduleSelected == Module.B){
			 module = "B";
		}
		
		String uri = null;
		String userPoolName = null;
		String userPoolId = null;
		String apiDocUrl = null;
		uri = URI_ELB;
		if (env == Env.DEV){
			userPoolName = USER_POOL_DEV;
			userPoolId = USER_POOL_ID_DEV;
			apiDocUrl = SWAGGER_BASE_URL_DEV + module + API_DOC_URL;
		}else if (env == Env.QA){
			userPoolName = USER_POOL_QA;
			userPoolId = USER_POOL_ID_QA;
			apiDocUrl = SWAGGER_BASE_URL_QA + module + API_DOC_URL;
		}else if (env == Env.DEMO){
			userPoolName = USER_POOL_DEMO;
			userPoolId = USER_POOL_ID_DEMO;
			apiDocUrl = SWAGGER_BASE_URL_DEMO + module + API_DOC_URL;
		}else if (env == Env.STG){
			userPoolName = USER_POOL_STG;
			userPoolId = USER_POOL_ID_STG;
			apiDocUrl = SWAGGER_BASE_URL_STG + module + API_DOC_URL;
		}else if (env == Env.UAT){
			uri = URI_ELB_UAT.replace("MODULE", moduleSelected.toString());
			userPoolName = USER_POOL_UAT;
			userPoolId = USER_POOL_ID_UAT;
			apiDocUrl = SWAGGER_BASE_URL_UAT + module + API_DOC_URL;
		}else if (env == Env.PROD){
			uri = URI_ELB_PROD.replace("MODULE", moduleSelected.toString());
			userPoolName = USER_POOL_PROD;
			userPoolId = USER_POOL_ID_PROD;
			apiDocUrl = SWAGGER_BASE_URL_PROD + module + API_DOC_URL;
		}
		
		String outputFilename = "output/" + env + "_" + module + "_document_gateway.json";
		
		byte[] jsonData;
		String filename = "input/" + env + "_" + module + "_document.json";
		if(isFromServer){//Get Swagger JSON from Server
			jsonData = HtmlContentReader.readFromUrl(apiDocUrl);	
			
			//Save file for future reference
			FileOutputStream fos = new FileOutputStream(filename);
			fos.write(jsonData);
			fos.close();
		}else{//Use Swagger file from project itself
			jsonData = Files.readAllBytes(Paths.get(filename));				
		}
	
		

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		//objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		// convert json string to object
		// Swagger swagger = objectMapper.readValue(jsonData, Swagger.class);
		JsonNode json = objectMapper.readTree(jsonData);

		JsonNode paths = json.get("paths");

		JsonNode definitions = json.get("definitions");
		JsonNode empty = objectMapper.readTree("{\"type\": \"object\"}");
		((ObjectNode)definitions).put("Empty", empty);
		
		//FOR Cognito Authorization
		JsonNode securityDefinitions = objectMapper.readTree("{ \"" + userPoolName + "\": { \"type\": \"apiKey\", \"name\": \"Authorization\", \"in\": \"header\", \"x-amazon-apigateway-authtype\": \"cognito_user_pools\", \"x-amazon-apigateway-authorizer\": { \"providerARNs\": [ \"arn:aws:cognito-idp:us-west-2:105906274816:userpool/"+ userPoolId +"\" ], \"type\": \"cognito_user_pools\" } } }");
		//String a = "{\"type\": \"apiKey\",\"name\": \"Authorization\",\"in\": \"header\",\"x-amazon-apigateway-authtype\": \"cognito_user_pools\",\"x-amazon-apigateway-authorizer\": {\"providerARNs\": [\"arn:aws:cognito-idp:us-west-2:105906274816:userpool/us-west-2_22jwp69pl\"],\"type\": \"cognito_user_pools\"}}";
		
		JsonNode info = json.get("info");
		((ObjectNode)info).put("description", toTitle(module) + " " +  info.get("description").asText());//env + " " + 
		((ObjectNode)info).put("title", toTitle(module) + " " +  info.get("title").asText());//env + " " + 
		((ObjectNode) json).put("securityDefinitions", securityDefinitions);
		
		// MainObject mainObject = new MainObject();

		Iterator<String> pathFieldNames = paths.fieldNames();

		
		for (JsonNode jsonNode : paths) {
			//String queryName = "";
			AmazonAPIGateway amazonAPIGateway = new AmazonAPIGateway();

			// mainObject.setAmazonAPIGateway(amazonAPIGateway);
			// configure Object mapper for pretty print
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

			// writing to console, can write to any output stream such as file

			Response response = new Response();
			Default defaultNode = new Default();
			defaultNode.setStatusCode("200");
			
			Default threeNotFourNode1 = new Default();
			threeNotFourNode1.setStatusCode("304");
			
			
			//FOR CROSS - CORS
			ResponseParameters responseParameters = new ResponseParameters();
			responseParameters.setAccessControlAllowOrigin("'*'");
			responseParameters.seteTag("integration.response.header.ETag");
			defaultNode.setResponseParameters(responseParameters);
			
			response.setDefault1(defaultNode);
			response.setThreeNotFourNode(threeNotFourNode1);
			amazonAPIGateway.setResponses(response);

			// while (methodNames.hasNext()) {
			String methodName = (String) pathFieldNames.next();
			System.out.println("Paths:: "+methodName);
			boolean isCognitoRequired = true;
			if (moduleSelected == Module.B){//Skipping Cognito
				if(methodName.equalsIgnoreCase("/signup") || 
						methodName.equalsIgnoreCase("/forgotPassword") || 
						methodName.equalsIgnoreCase("/changePassword")){
					isCognitoRequired = false;	
				}
			}

			amazonAPIGateway.setUri(uri + module + methodName);
			// }
			Iterator<String> methodTypes = jsonNode.fieldNames();
			ArrayList<String> methodTypeList = new ArrayList<>();
			while (methodTypes.hasNext()) {
				String methodType = (String) methodTypes.next();
				methodTypeList.add(methodType);
				System.out.println( "		MethodType:: " + methodType);
				ArrayList<String> queryNames = new ArrayList<>();
				ArrayList<String> headers = new ArrayList<>();
				ArrayList<String> pathVariables = new ArrayList<>();
				//System.out.println("methodType ###"+methodType);
				//if (methodType.equalsIgnoreCase("get")) {}
				JsonNode getPostPutDeleteNode = jsonNode.get(methodType);
				JsonNode parameters = getPostPutDeleteNode.get("parameters");
				if (parameters != null) {
					//queryNames = new String[parameters.size()];
					if (parameters.isArray()) {
						for (final JsonNode objNode : parameters) {
							String in = objNode.get("in").asText();
							String name = objNode.get("name").asText();
							if(in.equalsIgnoreCase("header")){
								headers.add(name);
							}else if(in.equalsIgnoreCase("body")){
								
							}else if(in.equalsIgnoreCase("query")){
								queryNames.add(name); 
							}else if(in.equalsIgnoreCase("path")){
								pathVariables.add(name); 
							}
							
						}
					}
				}
				
				//FOR CROSS - CORS
				JsonNode responses = getPostPutDeleteNode.get("responses");
				if (responses != null) {
					JsonNode twoHundredNode = responses.get("200");
					JsonNode responseHeaders = objectMapper.readTree("{\"Access-Control-Allow-Origin\":{\"type\":\"string\"},\"ETag\":{\"type\":\"string\"}}");
					((ObjectNode) twoHundredNode).put("headers", responseHeaders);
					
					JsonNode threeNotFourNode = objectMapper.readTree("{\"description\": \"Not Modified\"}");
					((ObjectNode) responses).put("304", threeNotFourNode);
				}

				
				amazonAPIGateway.setHttpMethod(methodType.toUpperCase());
				StringWriter stringEmp = new StringWriter();

				amazonAPIGateway.setType("http");

				amazonAPIGateway.setPassthroughBehavior("when_no_match");

				objectMapper.writeValue(stringEmp, amazonAPIGateway);

				// System.out.println("AmazonJSON is JSON is\n"+stringEmp);
				JsonNode amazonApi = objectMapper.readTree(stringEmp.toString());

				// For requestParameters
				ObjectNode requestParameters = objectMapper.createObjectNode();
				if (amazonAPIGateway.getHttpMethod().equalsIgnoreCase("get") || amazonAPIGateway.getHttpMethod().equalsIgnoreCase("delete")) {
					for (String queryName : queryNames) {
						//System.out.println("Query:####"+queryName);
						String nodeName = "integration.request.querystring" + "." + queryName + "";
						String nodeValue = "method.request.querystring" + "." + queryName + "";
						requestParameters.put(nodeName, nodeValue);
					}
				}
				// end requestParameters
				
				// For requestParameter HEADER Access Token for all APIs
				for (String header : headers) {
					String nodeName = "integration.request.header." + header + "";
					String nodeValue = "method.request.header." + header + "";
					requestParameters.put(nodeName, nodeValue);				
				}
				//System.out.println("objectNode1:: "+objectNode1);

				for (String pathVariable : pathVariables) {
					String nodeName = "integration.request.path." + pathVariable + "";
					String nodeValue = "method.request.path." + pathVariable + "";
					requestParameters.put(nodeName, nodeValue);									
				}
				
				((ObjectNode) amazonApi).put("requestParameters", requestParameters);
				//System.out.println("amazonApi:: "+amazonApi);
				
				
				// End For requestParameter HEADER Access Token
				// System.out.println(amazonApi);
				//for (JsonNode methodNode : jsonNode) {
				((ObjectNode) getPostPutDeleteNode).put("x-amazon-apigateway-integration", amazonApi);
				//System.out.println("methodNode:: "+getPostPutDeleteNode);
				//System.out.println();
				//System.out.println();

				if(isCognitoRequired){
					// Cognito NODE
					ArrayNode securityObjectNode = objectMapper.createArrayNode();

					ObjectNode objectNode2 = objectMapper.createObjectNode();
					ArrayNode nodeValue = objectMapper.createArrayNode();
					objectNode2.put(userPoolName, nodeValue);

					securityObjectNode.add(objectNode2);
					((ObjectNode) getPostPutDeleteNode).put("security", securityObjectNode);
				}

			}
			
			//FOR Cross-Origin Resource Sharing (CORS)
			if(!methodTypeList.isEmpty()){
				//OPTIONS
				JsonNode optionsMethod = objectMapper.readTree(new File("options.json"));
				JsonNode responseParametersNode = optionsMethod.path("x-amazon-apigateway-integration").path("responses").path("default").path("responseParameters");//.path("method.response.header.Access-Control-Allow-Methods");
				String joinedString = methodTypeList.toString().toUpperCase().replaceAll(" ", "");
				String allowedMethods = "'"+ joinedString.substring(1, joinedString.length()-1) + ",OPTIONS'";
				((ObjectNode)responseParametersNode).put("method.response.header.Access-Control-Allow-Methods", allowedMethods);
				((ObjectNode)jsonNode).put("options", optionsMethod);
			}
		}
		String outputJSON = json + "";
		ObjectMapper mapper = new ObjectMapper();
		Object prettyPrintJson = mapper.readValue(outputJSON, Object.class);
		//Files.write(Paths.get(outputFilename), (mapper.writerWithDefaultPrettyPrinter().writeValueAsString(prettyPrintJson)).getBytes());
		
		Writer out = new BufferedWriter(new OutputStreamWriter(
			    new FileOutputStream(outputFilename), "UTF-8"));//Charset.forName("Windows-1252")
		try {
		    out.write((mapper.writerWithDefaultPrettyPrinter().writeValueAsString(prettyPrintJson)));
		} finally {
		    out.close();
		}
		
		//System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputJSON));
		//Files.write(Paths.get(outputFilename), outputJSON.getBytes());
		// System.out.println("Employee Object\n"+swagger.getSwagger());
		// JsonNode paths = objectMapper.read
		
		System.out.println("================================END===============================");
		System.out.println("");
		System.out.println("");
		
	}
	
	static String toTitle (String s) {
	      String s1 = s.substring(0,1).toUpperCase();
	      String sTitle = s1 + s.substring(1);
	      return sTitle;
	 }
}
