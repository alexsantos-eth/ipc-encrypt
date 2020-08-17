#!/bin/bash

find -name "*.java" > sources.txt
javac @sources.txt -d ./classes
cd classes
java Main
cd ../