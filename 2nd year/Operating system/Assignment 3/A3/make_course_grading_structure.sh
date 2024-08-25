mkdir grades
cd grades
touch student1 student2 student3
echo "A">student1
echo "B">student2
echo "C">student3
cd ..
sudo adduser --disabled-password --gecos automatic coordinator
sudo adduser --disabled-password --gecos automatic ta1
sudo adduser --disabled-password --gecos automatic ta2
sudo adduser --disabled-password --gecos automatic ta3
sudo groupadd ta
sudo usermod -a -G ta ta1
sudo usermod -a -G ta ta2
sudo usermod -a -G ta ta3
sudo adduser --disabled-password --gecos automatic student1
sudo adduser --disabled-password --gecos automatic student2
sudo adduser --disabled-password --gecos automatic student3
sudo chown coordinator grades
sudo chown coordinator grades/*
sudo chgrp ta grades
sudo chgrp ta grades/*
sudo chmod 0755 grades
sudo chmod 0765 grades/*

