JAVAC = javac
Classpath = ./src
MainClass = zombieapocalypse.Livrable2
ClassesDir = bin/classes

all: run

run: clean compile exec

compile:
	mkdir -p $(ClassesDir)
	$(JAVAC) -cp $(Classpath) \
		-d $(ClassesDir) \
		./src/zombieapocalypse/**/*.java \
		./src/zombieapocalypse/Livrable2.java 
	
	$(JAVAC) -cp junit-console.jar:$(Classpath) \
		-d $(ClassesDir) \
		./test/**/*.java \
		./test/**/**/*.java

exec:
	cd bin/classes; java $(MainClass) 5 5

clean:
	rm -rf bin