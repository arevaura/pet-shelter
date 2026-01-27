@echo off
set WRAPPER_DIR=%~dp0
set MAVEN_HOME=%WRAPPER_DIR%apache-maven-3.8.6
set MAVEN_CMD=%MAVEN_HOME%\bin\mvn.cmd
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-11.0.23.9-hotspot

if not exist "%MAVEN_CMD%" (
    echo ERROR: Maven not found at %MAVEN_CMD%
    exit /b 1
)

call "%MAVEN_CMD%" %*
