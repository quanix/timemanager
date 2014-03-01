@echo off
echo %JAVA_HOME%
set JAVA_HOME=%JAVA_HOME%
set CLASSPATH=%JAVA_HOME%\lib
rem Add all jars....

for %%i in ("..\webapp\WEB-INF\lib\*.jar") do call "cpappend.bat" %%i
call "cpappend.bat" ..\webapp\WEB-INF\classes
echo CLASSPATH=%CLASSPATH%
"%JAVA_HOME%\bin\java" -version
"%JAVA_HOME%\bin\java" JettyRunner console%*

pause;

