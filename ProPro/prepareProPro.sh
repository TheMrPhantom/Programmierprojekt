sudo apt-get install -y software-properties-common;
sudo apt-get update;
sudo apt-get install gradle;
sudo add-apt-repository ppa:cwchien/gradle;
sudo apt-get update;
sudo apt upgrade gradle;
sudo gradle build;