TODO: regarder s'il est possible de définir l'IP comme une variable
TODO: fournir une commande SSH avec tous les ports utiles au TP
TODO: compléter la section HDFS en se basant sur le TP de l'an dernier
TODO: regarder le lancement d'une application Spark

# TP TIW6 2017 - Spark

L'objectif de ce TP est de prendre en main le _framework_ [Spark](http://spark.apache.org).
Le langage qui sera privilégié lors de ce TP sera Scala.
Il est donc important d'avoir suivi le [tutoriel Scala](TODO) avant de commencer ce TP(TODO footnote python).

## Jeu de données

Dans ce TP, on travaillera sur un jeu de données issue d'observations astronomiques.
On considèrera essentiellement deux relations: SOURCE et OBJECT.
SOURCE contient des données observationnelles.
OBJECT contient des informations sur les objets célestes associés aux observations.
Ces relations sont fournies sous forme de fichiers CSV compressés sans entête, dont les valeurs sont séparées par `,`. 
Les valeurs nulles sont représentées par les quatre lettres `NULL` qui remplacent alors la valeur.
On dispose également de deux fichiers SQL contenant des informations de schéma.
Ces deux relations comportent de nombreux attributs.
Même si les attributs d'intérêt sont mentionnés dans le TP, il sera nécessaire de connaître leur position dans les lignes.
On préfèrera une méthode où les positions des attributs sont déterminées automatiquement à partir des fichiers schéma SQL.

Fichiers utiles:

* [Source.sql](samples/Source.sql)
* [Object.sql](samples/Object.sql)
* [source-sample](samples/source-sample)
* [object-sample](samples/object-sample)

## Accès au cluster Hadoop

Chacun dispose d'un compte sur le cluster Hadoop (sur la machine `192.168.73.202`).
Le login est votre login étudiant.
Il faut se connecter en utilisant la clé SSH fournie dans la case `cle_SSH` de l'UE BigDataAnalytics sur [tomuss](http://tomusss.univ-lyon1.fr) (TODO footnote télécharger le fichier).

```bash
ssh -i fichier-cle-ssh p1234567@192.168.73.202
```

Pour copier un fichier sur la machine `192.168.73.202` on peut utiliser la commande suivante:

```bash
scp -i fichier-cle-ssh fichier-a-copier p1234567@192.168.73.202:
```

Si besoin, il est possible d'établir un **tunnel SSH** pour accéder à certaines interfaces Web.
Par exemple, pour accéder au service exposé à l'adresse http://TODO:TODO, il convient d'établir un tunnel SSH avec la commande suivante:

```bash
ssh -i fichier-cle-ssh p1234567@192.168.73.202 -LTODO:TODO:TODO
```

Pour éviter d'ajouter `-i fichier-cle-ssh`, il est possible d'utiliser ssh-agent via la commande suivante:
```bash
ssh-add fichier-cle-ssh
```

## HDFS

Avant de commencer à écrire des requêtes Spark, il est nécessaire de se familiariser avec les commandes d'accès au HDFS.

## Premiers pas avec Spark

### Avec spark-shell

Sur la machine `192.168.73.202`, réaliser le tutoriel [Quick start](http://spark.apache.org/docs/1.6.0/quick-start.html) jusqu'à la partie Caching inclue.
Le fichier `README.md` à utiliser se trouve dans le répertoire `/tp-data`.

Copier le fichier `README.md` dans votre répertoire HDFS, puis reprendre le début du tutoriel en accédant cette fois si au fichier dans le HDFS (supprimer le fichier du répertoire Linux courant après l'avoir copié dans HDFS pour être sûr(e) de soi).

### Application utilisant Spark



## Alternatives

Il doit également être possible de réaliser ce TP en Python, mais aucun test n'a été effectué en amont et le support sera limité.
