# SwaggerToAPIGateway
A Java utility project to covert your Swagger file to AWS API Gateway JSON

**Steps to deploy to API Gateway**
1. Checkout the utility project, SwaggerToAPIGateway 
2. There are two configurations in the project, please select the environment and module you want to import to API Gateway
	- Env env = Env.QA;
	- Module moduleSelected = Module.A;
3. Save latest Swagger file to your utility project
	- For example QA/A, Save with file name input/QA_A_document.json
4. Run the application (App.java) and you can see a new files created in your utility folder
	- output/QA_A_document_gateway.json
5. Make sure to commit the latest version of JSON files in your utility proj input folder
Import the json file to your AWS API Gateway

**Steps to import JSON file to API Gateway**

1. Select API Gateway service
<div align="center">
    <img src="/docs/1. API Gateway.png" width="400px"</img> 
</div>
2. API Selection - Module Customer
<div align="center">
    <img src="2. API Selection - Customer.png" width="400px"</img> 
</div>
3. Actions - Import API
<div align="center">
    <img src="/docs/3. Actions - Import API.png" width="400px"</img> 
</div>
4. Paster Swagger json
<div align="center">
    <img src="4. Paster Swagger json.png" width="400px"</img> 
</div>

5. Import with Overwrite
<div align="center">
    <img src="5. Import with Overwrite.png" width="400px"</img> 
</div>

6. Actions - Deploy API
<div align="center">
    <img src="6. Actions - Deploy API.png" width="400px"</img> 
</div>

7. Stage and description
<div align="center">
    <img src="7. Stage and description.png" width="400px"</img> 
</div>

8. Select Stage
<div align="center">
    <img src="8. Select Stage.png" width="400px"</img> 
</div>

9. Add deployment description
<div align="center">
    <img src="9. Add deployment description.png" width="400px"</img> 
</div>

10. Navigate to Stages page
<div align="center">
    <img src="10. Stages page.png" width="400px"</img> 
</div>

11. Verify the Stage variable is correct
<div align="center">
    <img src="11. Verify Stage variables.png" width="400px"</img> 
</div>
