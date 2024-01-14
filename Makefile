args := -s -p sample- src/main/resources/in3.txt src/main/resources/in1.txt -h src/main/resources/in2.txt -f

all: clean package run

package:
	mvn -f pom.xml package

run:
	java -jar target/FilterFiles-1.0.jar $(shell echo $(args))

clean:
	mvn -f ./pom.xml clean
	rm -f *.txt
