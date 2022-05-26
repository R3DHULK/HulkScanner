#!/bin/bash
if ["$1" == "" ]
then
  echo "Usage: ./hulkscanner.sh [IP]"
  echo "Example ./hulkscanner.sh 192.168.1.10"
else
  echo "Please wait while it is scanning all the open ports..."
  nc -nvz $1 1-65535 > $1.txt 2>&1
fi
tac $1.txt
rm -rf $1.txt