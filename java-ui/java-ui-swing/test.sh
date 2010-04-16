#!/bin/bash

# This script runs the Charva test program (if it is invoked as
# "test.sh swing", then the Swing version of the tutorial application
# is started instead).


# JAVA_HOME must be set to the JDK or JRE installation directory (for example,
# /usr/local/jdk1.4 or /usr/local/jre1.4).
if [ -z "$JAVA_HOME" ]
then
    echo "The JAVA_HOME environment variable is not set!"
    exit
fi

if [ "$1" = "swing" ]; then
    echo "Running the Swing version of the tutorial"
    ${JAVA_HOME}/bin/java  -cp "test/classes" tutorial.java.Tutorial
    exit
fi


if [ "$TERM" = "dumb" ]; then
    echo "The TERM environment variable is not set!"
    exit
fi

# Uncomment the next line to enable color.
TEST_OPTS="${TEST_OPTS} -Dcharva.color=1"
TEST_OPTS="${TEST_OPTS} -Djava.library.path=${HOME}/lib"

${JAVA_HOME}/bin/java \
  ${TEST_OPTS} \
  -cp target/java-ui-swing-0.0.1-SNAPSHOT-jar-with-dependencies.jar \
  org.nicerobot.ui.swing.HelloWorld 2>target/output.log
stty sane
