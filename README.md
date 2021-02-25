# GitHub WebHook Interceptor
A Spring Boot REST API application to intercept GitHub webhooks and call GitHub REST API's to complete certain tasks. 

#### Problem Statement: Create a simple web service that listens for organization events to know when a repository has been created. When the repository is created please automate the protection of the master branch. Notify yourself with an @mention in an issue within the repository that outlines the protections that were added

## Build (Maven Build)
`mvnw package`

## Run the application
`java -jar github-webhook-interceptor.jar`

## URL
Once we run the interceptor spring boot application, it will be listening for HTTP POST Webhook payload from GitHub on the following URL
> http://{ip or fqdn}:8080/post

## Prerequisites
>
The `application.properties` file available under src\main\resources\ folder has the following keys which needs to be preconfigured before building the application
| Key | Description |
---------|-------------|
access.token|	The personal access token for the organization to enable the calls to GitHub REST API's
logging.pattern.console|	The pattern in which the logs entries ought to be written (The application is using slf4j for logging)
logging.file.name|	The filename of the Log file
logging.pattern.file|	The pattern in which the log files names will be created (slf4j)

## WebHook Web Service

**Workflow**
* Register a webhook at organization level with:  
![Screenshot](/images/Organization.JPG)
* For the section `Which events would you like to trigger this webhook` select the `Repositories` event to send notifications to the GitHub WebHook Interceptor webservice on repo creation
![Screenshot](/images/RepoWebhook.JPG)
* Create a repo under the organization for which the webhook was created in the previous step
* This step will send a webhook payload to the webservice. Details of the payload can be referenced from the [Webhook GitHub](https://docs.github.com/en/developers/webhooks-and-events/webhook-events-and-payloads#repository) documentation
* The webservice will check for only `created` events for doing further processing. Other webhook notifications will be ignored
* The webservice will then make a REST API call to GitHub API for enabling branch protection of the master branch (using the access token stored in the `application.properties` file. [Branch Protection API](https://docs.github.com/en/rest/reference/repos#branches) can be referenced here
* Once the branch protection is enabled for the master branch, the webservice then creates a GitHub issue at the repository with @mention to notify the repository moderator or owner on the various protection rules that have been enabled
* This completes the flow

## Source
**Packages**

|Package|	Description|
---------|---------
com.github.webhook.interceptor.restservice|	Contains The REST controller that will intercept the POST message from GitHub and also GitHub REST API payload constructor
com.github.webhook.interceptor.datamap|	Contains classes which will use `jackson` api to read messages from webhook and construct json payload to be sent to GitHub REST API

## Build
Executable JAR is compiled using maven
**Java 11 JRE**

## Creating a Docker image for the interceptor application:
* Clone the github repo to your local machine
* In the project root directory issue the below command to build the application jar file
>
`mvn package `
* Once the jar is created, issue the below command to create a docker image (based on the Dockerfile definition)
>
`docker build -t github-webhook-interceptor.jar .`
* Type the below command to see is the docker image got created successfully
>
`docker image ls`
* Once the docker build is successful, issue the below command to run the container
>
`docker run -p 8080:8080 github-webhook-interceptor.jar`
* Run `docker ps` to check if the container is up and running
![Screenshot](/images/DockerUp.JPG)

