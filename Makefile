# Ensimag 2A POO - TP 2018/19
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est ici d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     L'archive bin/gui.jar contient les classes de l'interface graphique
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: testPrintRob testFinal

testPrintRob:
	javac -d bin -classpath bin/gui.jar -sourcepath src test/TestPrintRob.java

testDrawMap:
	javac -d bin -classpath bin/gui.jar -sourcepath src test/TestDrawMap.java

testPCC:
	javac -d bin -classpath bin/gui.jar -sourcepath src test/TestPCC.java

testDeplacement:
	javac -d bin -classpath bin/gui.jar -sourcepath src test/TestDeplacement.java

testFinal:
	javac -d bin -classpath bin/gui.jar -sourcepath src TestFinal.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin:bin/gui.jar TestInvader
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeInvader
exePrintRob:
	java -classpath bin:bin/gui.jar TestPrintRob cartes/carteSujet.map

exeDrawMap:
	java -classpath bin:bin/gui.jar TestDrawMap cartes/carteSujet.map

exePCC:
	java -classpath bin:bin/gui.jar TestPCC cartes/carteSujet.map

exeDeplacement:
	java -classpath bin:bin/gui.jar TestDeplacement cartes/desertOfDeath-20x20.map
	
exeFinal:
	java -classpath bin:bin/gui.jar TestFinal cartes/mushroomOfHell-20x20.map


clean:
	rm -rf bin/*.class
