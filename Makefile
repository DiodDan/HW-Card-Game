run:
	(cd src && find -name "*.java" > sources.txt && javac -d bin @sources.txt) && java -classpath src/bin App/VisualApp

test:
    (cd src && find -name "*.java" > sources.txt && javac -d bin @sources.txt) && java -classpath src/bin App/Test