#!/bin/bash

mvn clean

mvn release:clean release:prepare release:perform
