#!/bin/bash

while true;
do
  if [ -a start ]
  then
    mv start tmp
  fi
  cat tmp | python p.py > tmp2
  mv tmp2 tmp
  clear
  cat tmp
  sleep 0.1
done
