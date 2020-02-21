CATALINA_HOME=/opt/tomcat/apache-tomcat-7.0.96
wget http://apache.mirror.digionline.de/tomcat/tomcat-7/v7.0.100/bin/apache-tomcat-7.0.100.tar.gz
sudo mkdir /opt/tomcat
sudo tar xf apache-tomcat-7.0.100.tar.gz -C /opt/tomcat
sudo apt install default-jdk
sudo apt install maven
sudo $CATALINA_HOME/bin/startup.sh
sudo rm $CATALINA_HOME/webapps/ROOT.war
sudo rm $CATALINA_HOME/webapps/ROOT/ -r
mvn clean install
sudo mv target/ProProWeb-0.1.war $CATALINA_HOME/webapps/ROOT.war
echo "Please insert the absolute path to the graph file:"
read pathToFile
curl "localhost:8080/api/internal/initGraph?path=$pathToFile"
