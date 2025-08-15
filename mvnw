#!/bin/sh
# Maven Wrapper script

MAVEN_WRAPPER_JAVA_VERSION=17
MAVEN_WRAPPER_VERSION=3.9.6

BASEDIR=$(dirname "$(readlink -f "$0")")
MAVEN_USER_HOME="${MAVEN_USER_HOME:-${HOME}/.m2}"

if [ -z "$MAVEN_OPTS" ]; then
    MAVEN_OPTS="-Xms256m -Xmx512m"
fi

MAVEN_DISTRIBUTION_URL="https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip"
MAVEN_HOME="$BASEDIR/.mvn/wrapper/maven-$MAVEN_WRAPPER_VERSION"

if [ ! -d "$MAVEN_HOME" ]; then
    echo "Downloading Maven to $MAVEN_HOME"
    mkdir -p "$MAVEN_HOME"
    curl -o "$MAVEN_HOME/maven.zip" "$MAVEN_DISTRIBUTION_URL"
    unzip -q "$MAVEN_HOME/maven.zip" -d "$MAVEN_HOME"
    mv "$MAVEN_HOME/apache-maven-$MAVEN_WRAPPER_VERSION"/* "$MAVEN_HOME/"
    rm -rf "$MAVEN_HOME/apache-maven-$MAVEN_WRAPPER_VERSION"
    rm "$MAVEN_HOME/maven.zip"
fi

exec "$MAVEN_HOME/bin/mvn" "$@"
