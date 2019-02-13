wget http://www.trieuvan.com/apache/tomcat/tomcat-7/v7.0.92/bin/apache-tomcat-7.0.92.tar.gz
sudo mkdir /opt/tomcat
sudo tar xf apache-tomcat-7.0.92.tar.gz -C /opt/tomcat
sudo apt install default-jdk
sudo apt install maven
$CATALINA_HOME/bin/startup.sh
sudo rm $CATALINA_HOME/webapps/ROOT/ -r
sudo mv /ProProWeb/target/ProProWeb-0.1.war $CATALINA_HOME/webapps/ROOT.war