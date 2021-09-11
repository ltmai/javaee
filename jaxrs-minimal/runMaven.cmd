@setlocal

@set MVN_EXE="C:\bin\apache-maven-3.6.0-bin\apache-maven-3.6.0\bin\mvn.cmd"
@set MVN_CFG="C:\bin\apache-maven-3.6.0-bin\apache-maven-3.6.0\conf\settings_default.xml"

%MVN_EXE% -s %MVN_CFG% %* 

@endlocal