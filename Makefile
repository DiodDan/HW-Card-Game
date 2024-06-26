run:
	(cd src && find -name "*.java" > sources.txt && javac -d bin @sources.txt) && java -classpath src/bin App/VisualApp

test:
	Xvfb :1 -screen 0 1024x768x24 &> xvfb.log ; DISPLAY=:1.0 ; (cd src && find -name "*.java" > sources.txt && javac -d bin @sources.txt) && java -classpath src/bin App/Test