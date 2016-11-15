#!/bin/bash

if [ -z "$1" ] 
   then students=`cat students.txt`
   else students=$1
fi

for s in $students ;
do
    echo "marking $s"
    git clone http://forge.univ-artois.fr/$s/projet-jai.git 
    cd projet-jai
    # get latest code from UPSTREAM
    git remote add upstream http://forge.univ-artois.fr/m2ili-2015-2016/projet-jai.git
    git fetch upstream
    git merge upstream/master
    # check if the tests have been changed
    modificateurstests=`git log tests/ili/jai/Test* | grep Author | grep -v "Daniel Le Berre"`
    cd ..
    if [ -z "$modificateurstests" ]
      then  ant -Detudiant=$s >$s.log
      else echo "Tests modifiés\n$modificateurstests" >$s.log
    fi     
    rm -fR projet-jai
done
       
