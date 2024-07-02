run:
	(cd src && find -name "*.java" > sources.txt && javac -d bin @sources.txt) && java -classpath src/bin App.VisualApp

test:
	Xvfb :1 -screen 0 1024x768x24 &> xvfb.log ; export DISPLAY=:1.0 ; (cd src && find -name "*.java" > sources.txt && javac -d bin @sources.txt) && java -classpath src/bin App.Test

build_exe_win:
	dir /s /b src\*.java > sources.txt && javac -d bin @sources.txt
	jar cvf app.jar -C src/bin .
	jlink --no-header-files --no-man-pages --add-modules java.base,java.desktop --output custom_jre
	jpackage --name MyApp --type app-image --input . --main-jar app.jar --runtime-image custom_jre --win-dir-chooser --win-shortcut --main-class App.VisualApp


build_exe:
	dir /s /b src\*.java > sources.txt
	javac -d bin @sources.txt