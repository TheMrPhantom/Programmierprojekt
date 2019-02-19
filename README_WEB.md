# ProPro

## Technologies used
We decided to use Java for our backend and html, css and JavaScript for the frontend. For the map we used the JavaScript library Leaflet. Our external dependecies are:
  - Gson
  - javax.servlet
  - com.sun.xml
  - com.sun.jersey
  - asm
  - org.json
  - com.bytebybyte.gwt
  - org.eclipse.persistence

## Packaging
Our project is packaged by maven as .war file to deploy it easyly on a tomcat webserver

## Building and deploying the software
To deploy build and deploy the software on a tomcat 7 just need to run the `installpropro.sh` file. The script will sometimes ask for permissions for the installation. After the software was build the script will ask you for the path to the graph file. This will initialize the backend with the given the graph file. If the system does have enough ram you can change the graph file afterwards with an http call to `localhost:8080/api/internal/initGraph?path=<PATH>`.

## Building script
Our building script executes the follwing steps:
1. Downloading Tomcat7
2. Installing Tomcat7
3. Installing default-jdk
4. Installing maven
5. Deleting standard Tomcat7 placeholder website
6. Building the project with maven (packaging it to ProProWeb-0.1.war)
7. Deploying it as ROOT.war in the Tomcat7 webapps folder (Renaming it because only ROOT.war files will be deployed on the root adress of the Tomcat server)
8. Asking for the path to the graph file
9. Initializing the backend with the given graph file
