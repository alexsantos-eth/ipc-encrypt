#!/bin/bash

find -name "*.java" > sources.txt
javac @sources.txt -d ./Classes
cd Classes
java Main
cd ../