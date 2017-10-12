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

> Dans la suite du TP, il faut systématiquement remplacer `p1234567` par votre login étudiant.

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

> Dans la suite du TP la machine `192.168.73.202` sera référencée en tant que `master`.

## HDFS

Avant de commencer à écrire des requêtes Spark, il est nécessaire de se familiariser avec les commandes d'accès au HDFS.

L'accès aux commandes permettant d'accéder au HDFS se fera sur la machine `master`

Lister le contenu d'un répertoire
```shell
hdfs dfs -ls /le/repertoire/a/lister
```

Copier un fichier vers HDFS
```shell
hdfs dfs -copyFromLocal fichier-a-copier /destination/dans/hdfs
```

Copier un fichier depuis HDFS
```shell
hdfs dfs -copyToLocal /fichier/dans/hdfs fichier-local
```

Lire le contenu d'un fichier depuis HDFS
```shell
hdfs dfs -cat /fichier/dans/hdfs
```
à combiner avec, par exemple ` | less`

> Remarque: les fichiers avec un nom relatifs sont traités dans HDFS comme s'il étaient référencés à partir du répertoire `/home/p1234567`

La commande suivante affiche toutes commandes disponibles pour interagir avec le HDFS:
```shell
hdfs dfs
``` 

### Exercice

* lister le contenu du répertoire `/home/p1234567`
* copier le fichier `README.md` du répertoire `/tp-data` de `master` vers le HDFS
* vérifier que le fichier a bien été copié en listant le contenu du répertoire, puis en affichant le contenu du fichier `README.md` après sa copie dans HDFS

## Premiers pas avec Spark

### Avec spark-shell

Sur la machine `master`, réaliser le tutoriel [Quick start](http://spark.apache.org/docs/1.6.0/quick-start.html) jusqu'à la partie Caching inclue.
Le fichier `README.md` à utiliser se trouve dans le répertoire `/tp-data`.

Copier le fichier `README.md` dans votre répertoire HDFS, puis reprendre le début du tutoriel en accédant cette fois si au fichier dans le HDFS (supprimer le fichier du répertoire Linux courant après l'avoir copié dans HDFS pour être sûr(e) de soi).

### Application utilisant Spark

Sur votre machine, continuer le [tutoriel](http://spark.apache.org/docs/1.6.0/quick-start.html#self-contained-applications) avec les modifications suivantes, afin d'utiliser le projet pré-configuré fourni:

* Le fichier `build.sbt`contient déjà la configuration nécessaire
* Le fichier scala de l'application, déjà partiellement rempli est `src/main/scala/SparkTPApp1.scala`
* Remplacer le `"YOUR_SPARK_HOME/README.md"` par `"hdfs://tp-hadoop-cdh-secondary-tpl-01-0:9000/home/p1234567/README.md"`
* lancer `sbt assembly` après avoir lancé `sbt package`
* copier le fichier `target/scala-2.10/SparkTPApp-assembly-1.0.jar` [^1] sur `master`
* lancer l'application directement depuis `master`:
  ```shell
  spark-submit --class SparkTPApp1 --master yarn --deploy-mode client /home/p1234567/SparkTPApp-assembly-1.0.jar
  ```

## Alternatives

Il doit également être possible de réaliser ce TP en Python, mais aucun test n'a été effectué en amont et le support sera limité.

