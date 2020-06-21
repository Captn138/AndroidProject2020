# AndroidProject2020
Par Mickael Almeida (classe 35)
## Présentation du projet
Ce projet de programmation mobile devait contenir un appel à une API REST afin d'afficher une liste d'éléments sur lesquels on peut cliquer pour avoir plus de détails.

J'ai choisi d'utiliser une API contenant tous les items du jeu *Minecraft*, disponible sur [ce site](https://minecraft-ids.grahamedgecombe.com).
## Prérequis
* Télécharger [Android Studio](https://developer.android.com/studio)
## Installation
Pour tester l'application sur un PC, il faudra installer un émulateur Android avec un niveau d'API minimum de 27. Dans le fichier *build.gradle*, il faudra rajouter les lignes suivantes dans les dépendances:
```java
implementation 'androidx.recyclerview:recyclerview:1.1.0'
implementation 'com.squareup.retrofit2:retrofit:2.6.0'
implementation 'com.squareup.retrofit2:converter-gson:2.6.0'
implementation 'com.squareup.picasso:picasso:2.71828'
```
Pour tester l'application sur un téléphone Android, on peut télécharger et installer l'apk de l'application [ici]().
## Consignes respectées
* Nombre d'activités minimales
* Affichage d'une liste
* Possibilité de cliquer sur un objet pour plus de détails
* Récupération des données via une API REST
* Stockage et récupération des données en cache
* Application codée en Java
## Ajouts supplémentaires
* Clean architecture
* Splash screen
* Architecture MVC
* Affichage d'images
* Principes SOLID
* Principe du singleton
## Focntionnalités
### Premier écran
Le premier écran est un splash screen, c'est un écran qui permet d'afficher une interface pour l'utilisateur pendant que l'application se charge.
![splash](https://i.imgur.com/qDyo8OE.png)
### Deuxième écran
Le deuxième écran est celui sur lequel on voit notre liste d'éléments.
![liste](https://i.imgur.com/r2WaYb3.png)
### Le troisième écran est celui sur lequel on peut voir des détails sur l'objet. *Item name* est le nom de l'objet, *under the set* est le type d'objet dont est dérivé l'objet sélectionné et *Minecraft item name* est le nom de l'objet pour le système, composé de som parent et d'un champ supplémentaire appelé *meta*.
![details]([https://i.imgur.com/dJcUggS.png)
