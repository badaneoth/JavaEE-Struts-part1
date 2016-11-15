Voici la procédure à suivre pour maintenir à jour son fork à partir du dépot git du groupe.

On suppose que vous avez une copie de votre fork dans le répertoire ```monfork```.

Cette première étape est à faire une seule fois : il faut associer un deuxième dépôt distant au dépôt local git.
Par défaut, le nom du dépôt de votre fork est ```origin```. Le nom du dépôt du groupe sera ```upstream```.

```
$ cd monfork
$ git remote add upstream http://forge.univ-artois.fr/m2ili-2015-2016/projet-jai.git
```

Pour récupérer dans votre dépôt local les changements du projet partagé, il faut 
récupérer les changements et les appliquer dans votre dépot local.

```
$ git fetch upstream
remote: Counting objects: 6, done.
remote: Compressing objects: 100% (4/4), done.
remote: Total 4 (delta 1), reused 0 (delta 0)
Unpacking objects: 100% (4/4), done.
From http://forge.univ-artois.fr/m2ili-2015-2016/projet-jai.git
 * [new branch]      master     -> upstream/master

$ git merge upstream/master
Updating 8786b97..19ad465
Fast-forward
 TestJDBC2.java | 4 +++-
 1 file changed, 3 insertions(+), 1 deletion(-)
```

Votre dépot local contient maintenant les derniers changements.
Il faut les déposer sur votre dépot sur la forge.

```
$ git push 
Counting objects: 1, done.
Writing objects: 100% (1/1), 276 bytes | 0 bytes/s, done.
Total 1 (delta 0), reused 0 (delta 0)
To https://forge.univ-artois.fr/letudiant/projet-jai.git
   5458f04..19ad465  master -> master
$ git branch
* master
```

Et voila !