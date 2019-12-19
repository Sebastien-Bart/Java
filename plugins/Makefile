all: use

use:
	mvn package
	rm -rf userDir
	mkdir userDir
	mkdir userDir/available
	mkdir userDir/extensions
	mkdir userDir/extensions/plugins
	cp target/plugins-1.0-SNAPSHOT.jar userDir
	cp -r target/classes/plugins userDir/available
	touch userDir/texte.txt
	echo "Blaringhem est située en Flandre française, dans le Houtland pour la plus grande partie est de son territoire et en Artois au-delà du canal de Neufossée pour sa partie ouest, à 40 km de Dunkerque, 47 km de Lille, 50 km d'Arras, 50 km de Calais, à 30 km de la frontière belge et est limitrophe du département du Pas-de-Calais. Son relief est marqué par plusieurs hauteurs : Moulin Fontaine, Le Croquet, Le Mont d'Hiver et Le Mont Dupil. La commune est composée d'un village-centre et de plusieurs hameaux : Le Croquet, Le Mont d'Hiver, Le Pont Asquin, La Belle-Hôtesse, Le Mont Dupil et La Carnois." > userDir/texte.txt

clean:
	rm -rf userDir
	mvn clean
