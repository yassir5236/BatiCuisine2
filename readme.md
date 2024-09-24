# 🛠️ Bati-Cuisine

## 📋  Présentation

Bati-Cuisine est une application Java destinée aux professionnels de la construction et de la rénovation de cuisines. L'application calcule le coût total des travaux en tenant compte des matériaux utilisés et du coût de la main-d'œuvre, cette dernière étant facturée à l'heure.

Le programme inclut des fonctionnalités avancées telles que la gestion des clients, la génération de devis personnalisés, et une vue d'ensemble sur les aspects financiers et logistiques des projets de rénovation.

Ce projet vise à offrir aux professionnels un outil puissant et pratique pour estimer avec précision les coûts et gérer efficacement les projets de rénovation de cuisines.

## ✨ Fonctionnalités

**📂 Gestion des projets**
- Ajout et gestion des clients
- Ajout des composants (matériaux, main-d'œuvre)
- Génération de devis personnalisés

**🛠️ Gestion des composants**
- Gestion des coûts des matériaux
- Calcul des coûts de main-d'œuvre basé sur le taux horaire, les heures travaillées, et la productivité

**👥 Gestion des clients**
- Enregistrement des informations de base des clients
- Différenciation entre les clients professionnels et particuliers
- Calcul et application de remises spécifiques selon le type de client

**🧾 Création de devis**
- Génération de devis avant le début des travaux
- Inclusion d'une date d'émission et d'une date de validité du devis
- Possibilité d'accepter ou de refuser le devis par le client

**💰 Calcul des coûts**
- Intégration des coûts des composants (matériaux, main-d'œuvre)
- Application d'une marge bénéficiaire
- Prise en compte des taxes (TVA) et des remises

**📋 Affichage des détails et résultats**
- Affichage détaillé des projets (client, composants, coût total)
- Génération d'un récapitulatif détaillé du coût total

## Prérequis

JRE pour java 8
PostgreSQL

## ⚙️ Installation

1. Clonez le dépôt Git :
```
git clone https://github.com/yassir5236/BatiCuisine2
```

2. Créez la base de données PostgreSQL :
```sql
CREATE DATABASE bati_cuisine;
```

3. Exécutez les scripts SQL pour la création de la base de données SQL/scriptSQL.sql

4. Compilez et exécutez l'application :
```
cd BatiCuisine2
java -jar BatiCuisine2.jar
```

## 🚀 Utilisation

Lors du lancement de l'application, un menu principal s'affiche avec les options suivantes :

1. Créer un nouveau projet
2. Afficher les projets existants
3. Calculer le coût d'un projet
4. Gestion des devis
5. Quitter

Suivez les instructions à l'écran pour naviguer dans l'application et gérer vos projets de rénovation de cuisines.

## 📊 Améliorations futures

    🖥️ Interface graphique (GUI) : Développer une interface graphique conviviale pour remplacer l'interface console.
    🔐 Authentification utilisateur : Ajouter un système de connexion avec différents rôles (administrateurs, utilisateurs).
    📊 Rapports détaillés : Génération de rapports détaillés sur les coûts et performances des projets.

## 👨‍💻  Auteurs

Yassir Ait Lahmadi : yassiraitlahmadi@gmail.com