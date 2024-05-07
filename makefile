JAVAC = javac
Classpath = ./src
MainClass = zombieapocalypse.Livrable3
ClassesDir = bin/classes

all: run

run: clean compile exec

compile:
	mkdir -p $(ClassesDir)
	$(JAVAC) -cp $(Classpath) \
		-d $(ClassesDir) \
		./src/zombieapocalypse/**/*.java \
		./src/zombieapocalypse/Livrable3.java 

test:
	$(JAVAC) -cp junit-console.jar:$(Classpath) \
		-d $(ClassesDir) \
		./test/**/*.java \
		./test/**/**/*.java

exec:
	cd bin/classes; java $(MainClass) 10 5

clean:
	rm -rf bin
	rm -rf docs
	rm -rf classes
	rm -rf Livrable3.jar