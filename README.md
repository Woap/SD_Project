# SD_Project

Le projet a été codé en Java avec la technologie RMI. Pour démarrer une simulation lancer le programme “Lancement”
Il existe trois types d’agents dans le systèmes : Client - Producteur - Coordinateur

![alt text](http://image.noelshack.com/fichiers/2017/40/6/1507328320-b.png)

Il existe trois types de producteur :
- Producteur d’or produisant 10 or
- Producteur d’argent produisant 10 argent
- Producteur de bronze produisant 10 bronze
tous les 150ms.
L’objectif des clients est de récolter 100 or, 100 argent et 100 bronze.
Il existe six clients avec des personnalités différentes :
- Client Individualiste qui cherche à d’abord récolter tout l’or puis l’argent puis le bronze. Il récolte 10 ressources par récolte. Il ne peut pas faire d’observation.
- Client Coopérateur qui alterne de producteur lorsqu’il n’y a plus de ressources disponibles chez un producteur. Il récolte 10 ressources par récolte. Pas d’observation possible.
- Client Voleur qui alterne de producteur comme le coopérateur cependant il tente d’abord ( lorsqu’il n’y a plus de ressource chez un producteur ) de voler  10 ressources du type de ressource qui est épuisée chez le producteur
actuel. Il ne peut non plus pas faire d’observation. 
- Client Prudent, agit également comme un coopérateur cependant il passe en mode observation lorsqu’il ne récolte pas.
- Client Coopérateur Malin, agit comme un coopérateur cependant il observe d’abord le nombre de ressources restantes chez le producteur pour pouvoir récolter le montant exact des ressources restantes chez le producteur ( <=10 ). Il passe en mode observation lorsqu’il ne récolte pas.
- Client Humain, cas humain peut jouer. Il peut récolter ou voler chaque tour sur le producteur ou client qu’il veut.

Il existe cinq types de paramètres disponible :
- On peut activer l’ordonnancement permettant aux clients de jouer chacun son tour. L’ordre est défini par le coordinateur.
- On peut jouer en activant l’option Humain vs ordinateur.
- On peut définir si les ressources sont illimités ou épuisables. La production est de n/2 +1 si épuisable sinon 10 000 ressources tous les 150ms ( 10 000 ) étant la limite supérieur de production des producteurs )
- On peut activer/désactiver l’observation. La désactivation fait que les clients de personnalités Prudent et Coopérateur Malin ne puisse plus faire d’observation.
- On peut activer/désactiver le vol. La désactivation engendre le fait que les clients voleurs ne peuvent plus voler. Si un client en observation se fait voler, il punit le client voleur de 2 secondes et ne se fait pas voler sinon le voleur reçoit ses ressources.
- Deux types de fin de partie : Le premier joueur qui fini ou lorsque tous les clients ont atteint leurs objectifs ( un classement est défini dans ce cas )

Le Coordinateur est unique et permet le bon déroulement de la partie. C’est lui qui
se charge du lancement de tous les clients et producteurs lorsque tous les agents
sont prêts. Il s’occupe du tour par tour si l’option est activé et s’occupe de la fin de la
partie selon l’option choisi. Il affiche le classement en fin de partie et arrête les
clients et producteurs si nécessaire.

Des logs sont générés durant la partie et permettent de voir l’évolution des
ressources chez les producteurs et clients. Voir le dossier “Logs”

![alt text](http://image.noelshack.com/fichiers/2017/40/6/1507328317-a.png)

Dans le cas général, les meilleurs clients sont de type Coopératif Malin > Prudent > Voleur > Individualiste > Coopératif. Le résultat peut varier selon le nombre de clients de chaque types, du nombre de producteurs et des paramètres.
