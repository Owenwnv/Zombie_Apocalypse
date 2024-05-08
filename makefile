JAVAC = javac
Classpath = ./src
MainClass = zombieapocalypse.Livrable4
ClassesDir = bin/classes


clean:
	rm -rf bin
	rm -rf docs
	rm -rf classes
	rm -rf zombicide.jar

doc:
	javadoc -sourcepath src -subpackages zombieapocalypse -d docs

zombicide.jar:
	jar cfm zombicide.jar manifest.txt -C bin/classes .

cls:
	mkdir -p $(ClassesDir)
	$(JAVAC) -cp $(Classpath) \
		-d $(ClassesDir) \
		./src/zombieapocalypse/**/*.java \
		./src/zombieapocalypse/Livrable4.java 

	$(JAVAC) -cp junit-console.jar:$(Classpath) \
		-d $(ClassesDir) \
		./test/**/*.java \
		./test/**/**/*.java