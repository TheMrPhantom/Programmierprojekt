CATALINA_HOME=/opt/tomcat/apache-tomcat-7.0.92
sudo $CATALINA_HOME/bin/shutdown.sh
sudo rm $CATALINA_HOME/webapps/ROOT.war
sudo rm $CATALINA_HOME/webapps/ROOT/ -r
mvn -f ProProWeb/pom.xml clean install
sudo mv ProProWeb/target/ProProWeb-0.1.war $CATALINA_HOME/webapps/ROOT.war
sudo $CATALINA_HOME/bin/startup.sh