# l2s4-projet-2024

Vous devez *forker* ce projet dans votre espace de travail Gitlab (bouton `Fork`) et vidéo sur le [portail](https://www.fil.univ-lille.fr/portail/index.php?dipl=L&sem=S4&ue=Projet&label=Documents)
Un unique fork doit être réalisé par équipe.

Une fois cela réalisé, supprimer ces premières lignes et remplissez les noms des membres de votre équipe.
N'oubliez pas d'ajouter les autres membres de votre équipe aux membres du projet, ainsi que votre enseignant·e (statut Maintainer).

# Equipe

- Owen Wanaverbecque
- Farzadde Soleimanian
- Ziad El Hassan
- Abderazak Kassa-Beghdouche

# Sujet

[Le sujet 2024](https://www.fil.univ-lille.fr/~varre/portail/l2s4-projet/sujet2024.pdf)

# UML

https://lucid.app/lucidchart/456a610a-a383-48a7-834a-eb0eaf49c5d9/edit?invitationId=inv_6f89afeb-2641-4162-bb06-7419f331ac1c&page=0_0#

# Livrable

## Livrable 1

Pour compiler et executer le jeu:  
```make```  
Pour choisir la taille de la map:  
```cd bin/classes```  
```java zombieapocalypse.TestMain 5 5```

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 2

Pour compiler et executer le jeu:
```make```
Pour executer les Tests:
```java -jar junit-console.jar -classpath bin/classes:test -select-class Test.(Le class du Test comme ActorTest)``` 

Pour générer la documentation:
```javadoc -sourcepath src -subpackages zombieapocalypse -d docs```

Pour voir l'ajout des acteurs et de leurs équipements comme demandé dans l'énoncé:

- Dans le code: src>zombieapocalypse>Livrable2.java

- Dans le terminal: 

    - "-" bleu: Door
    - "S" jaune: Sewer
    - "S" bleu: Street
    - "R" rouge: Room
    - "C" jaune: Continental
    - "+" vert: Pharmacy
    - nombre supérieur droit: nombre de survivants dans la case
    - nombre supérieur gauche: nombre de zombies dans la case


### Atteinte des objectifs

L'UML a été mis à jour, vous pouvez constater l'ajout des classes Item et Actor ainsi que les classes héritières de celles-ci qui modélisent respectivement tous les outils/armes et tous les survivants/zombies du jeu.

De plus, nous avons terminé les modélisations des égoûts et des portes.

Pour finir, nous avons déplacé tous les survivants de chaque rôle vers le nord d'une case et nous les avons dotés d'une fiole dans la main et d'une carte dans le sac à dos. Les zombies de chaque type ont eux aussi été placés.

### Difficultés restant à résoudre

## Livrable 3

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 4

### Atteinte des objectifs

### Difficultés restant à résoudre

# Journal de bord

## Semaine 1

- Absent (neige)

## Semaine 2

- Familiarisation avec le sujet
- Mise en commun des idées
- Début de l'UML

## Semaine 3

- 1er version de l'UML
- Discussion de l'UML avec l'enseignant
- Création des premières class java

## Semaine 4

- 1er version de createStreets
- Ajout des RoomCells dans la Map

## Semaine 5

- Ajout de canBeSplit et addStreet
- Ajout du makefile
- Ajout de PimpStyle
- Rendu du Livrable 1

## Semaine 6

- Réorganisation des fichiers
- Ajout AddDoors()

## Semaine 7

- Mise à jour UML pour second livrable
- Mise à jour UML pour les classes anciennes
- Ajout Classes Actors, Items
- Ajout des classes de tous les zombies et survivants

## Semaine 8

## Semaine 9

## Semaine 10

## Semaine 11

## Semaine 12
