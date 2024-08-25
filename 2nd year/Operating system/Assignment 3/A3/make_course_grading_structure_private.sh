mkdir grades
cd grades
touch student1 student2 student3
echo "A">student1
echo "B">student2
echo "C">student3
cd ..
sudo chown coordinator grades
sudo chown coordinator grades/*
sudo chgrp ta grades
sudo chgrp ta grades/*
sudo chmod 0755 grades
sudo chmod 0760 grades/*
sudo chown student1:ta grades/student1
sudo chown student2:ta grades/student2
sudo chown student3:ta grades/student3
