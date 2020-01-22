# Liens utiles

- Cours de Thomas Vantroys

	https://tvantroys.plil.fr/IMA5/AL/docs/2.4_Architecture.pdf
	
################################################################################

Installations

################################################################################

VM Java

https://doc.ubuntu-fr.org/openjdk

--------------------------------------------------------------------------------
Eclipse installer:

https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2019-12/R/eclipse-inst-linux64.tar.gz

--------------------------------------------------------------------------------
Spring:

https://www.eclipse.org/community/eclipse_newsletter/2018/february/springboot.php


--------------------------------------------------------------------------------
Connecter GIT et Eclipse:

Recuperer la URL du projet sur Git




--------------------------------------------------------------------------------
RabbitMQ:

tutorial pour install:
https://computingforgeeks.com/how-to-install-latest-rabbitmq-server-on-ubuntu-18-04-lts/

https://packagecloud.io/rabbitmq/rabbitmq-server


tutorial pour installer sur la VM Cordouan:

 https://tecadmin.net/install-rabbitmq-server-on-ubuntu/

ajouter les users:

    sudo rabbitmqctl [add_user nom_user] [le_mot_de_passe]
    sudo rabbitmqctl set_user_tags  [mon_user] [type_user]

se connecter au server rabbitmq:
http://127.0.0.1:15672/

tutoriels:
https://www.rabbitmq.com/getstarted.html

Créer le client JMS
https://www.rabbitmq.com/jms-client.html
 
--------------------------------------------------------------------------------
Git sur linux:

aller dans le repertoire:

	cd ~/.ssh/

generer la cle publique dans le fichier ssh_git:

	ssh-keygen -t ed25519 -f ssh_git

copier le contenu du fichier et le rajoutter à la liste des clé sur gitlab 

	nano ssh_git.pub 

clone tout le contenu sur git vers le répertoire:

	git clone https://gitlab.univ-lille.fr/mamadoumalick.seck.etu/sysco.git
	git add *
	git status 
	git config --global user.email "jorge.cabraldasdores.etu@univ-lille.fr"
	git config --global user.email "jorge.cabraldasdores.etu"
	git commit -m "RabbitMQ"
	git push

