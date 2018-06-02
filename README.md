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
