#!/bin/bash
Hid=$(jps | grep FEBS | awk '{print $1}')
echo $Hid
  kill -9 $Hid
rm nohup.out;
cd /home/zenki/project/doc-helper/
echo $1
if [ ! -n "$1" ]; then
    echo "default branch"
else
  git fetch
  git checkout $1
  echo "git checkout $1"
  echo "default branch"
fi
git pull
mvn clean install -Dmaven.test.skip=true
cd target
nohup java -jar   -Xms256m -Xmx512m ./FEBS-Shiro-2.0.jar  --spring.profiles.active=prod &> ./nohup.out &