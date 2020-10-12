:: This script starts the standalone application client in an ACC (Application Client Container).
:: Note that 
:: - The EAR must be deployed to application server
:: - The target Jar file must be in an EAR archive
@ECHO OFF

SETLOCAL

SET APPCLIENT_PATH=C:\bin\wildfly-16.0.0.Final\bin\appclient.bat 

%APPCLIENT_PATH% project-ear\target\project-tst.ear#project-app.jar

ENDLOCAL