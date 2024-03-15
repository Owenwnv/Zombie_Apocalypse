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
		./src/zombieapocalypse/structure/Building.java \
		./src/zombieapocalypse/cell/Cell.java \
		./src/zombieapocalypse/structure/Door.java \
		./src/zombieapocalypse/cell/EmptyCell.java \
		./src/zombieapocalypse/mapcreation/Map.java \
		./src/zombieapocalypse/mapcreation/MapGenerator.java \
		./src/zombieapocalypse/style/PimpStyle.java \
		./src/zombieapocalypse/cell/RoomCell.java \
		./src/zombieapocalypse/structure/Street.java \
		./src/zombieapocalypse/cell/StreetCell.java \
		./src/zombieapocalypse/actor/Actor.java \
		./src/zombieapocalypse/actor/zombie/Zombie.java \
		./src/zombieapocalypse/actor/zombie/Abomination.java \
		./src/zombieapocalypse/actor/zombie/Bigboy.java \
		./src/zombieapocalypse/actor/zombie/Runner.java \
		./src/zombieapocalypse/actor/zombie/Walker.java \
		./src/zombieapocalypse/actor/survivor/Survivor.java \
		./src/zombieapocalypse/actor/survivor/Fighter.java \
		./src/zombieapocalypse/actor/survivor/Healer.java \
		./src/zombieapocalypse/actor/survivor/Lucky.java \
		./src/zombieapocalypse/actor/survivor/Searcher.java \
		./src/zombieapocalypse/item/Item.java \
		./src/zombieapocalypse/item/weapon/Weapon.java \
		./src/zombieapocalypse/item/weapon/Axe.java \
		./src/zombieapocalypse/item/weapon/Chainsaw.java \
		./src/zombieapocalypse/item/weapon/Crowbar.java \
		./src/zombieapocalypse/item/weapon/Gun.java \
		./src/zombieapocalypse/item/weapon/Rifle.java \
		./src/zombieapocalypse/item/tool/Tool.java \
		./src/zombieapocalypse/item/tool/HandheldMap.java \
		./src/zombieapocalypse/item/tool/HealthPotion.java \
		./src/zombieapocalypse/item/tool/InfraredGlasses.java \
		./src/zombieapocalypse/item/tool/MedKit.java \
		./src/zombieapocalypse/item/tool/SkeletonKey.java \
		./src/zombieapocalypse/game/Game.java \
		./src/zombieapocalypse/Livrable2.java 
	
	$(JAVAC) -cp junit-console.jar:$(Classpath) \
		-d $(ClassesDir) \
		./Test/ActorTest.java \
		./Test/DoorTest.java \
		./Test/MapTest.java \
		./Test/StreetSewerTest.java \

exec:
	cd bin/classes; java $(MainClass) 5 5

clean:
	rm -rf bin