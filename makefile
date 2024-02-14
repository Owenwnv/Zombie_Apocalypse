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
		./src/zombieapocalypse/structure/Building.java \
		./src/zombieapocalypse/cell/Cell.java \
		./src/zombieapocalypse/structure/Door.java \
		./src/zombieapocalypse/cell/EmptyCell.java \
		./src/zombieapocalypse/mapcreation/Map.java \
		./src/zombieapocalypse/style/PimpStyle.java \
		./src/zombieapocalypse/cell/RoomCell.java \
		./src/zombieapocalypse/structure/Street.java \
		./src/zombieapocalypse/cell/StreetCell.java \
		./src/zombieapocalypse/TestMain.java

exec:
	cd bin/classes; java $(MainClass) 5 10

clean:
	rm -rf bin