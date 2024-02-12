JAVAC = javac
Classpath = ./src
MainClass = zombieapocalypse.TestMain
ClassesDir = bin/classes

all: run

run: clean compile exec

compile:
	mkdir -p $(ClassesDir)
	$(JAVAC) -cp $(Classpath) \
		-d $(ClassesDir) \
		./src/zombieapocalypse/Building.java \
		./src/zombieapocalypse/Cell.java \
		./src/zombieapocalypse/Door.java \
		./src/zombieapocalypse/EmptyCell.java \
		./src/zombieapocalypse/Map.java \
		./src/zombieapocalypse/PimpStyle.java \
		./src/zombieapocalypse/RoomCell.java \
		./src/zombieapocalypse/Street.java \
		./src/zombieapocalypse/StreetCell.java \
		./src/zombieapocalypse/TestMain.java

exec:
	cd bin/classes; java $(MainClass) 5 5

clean:
	rm -rf bin