
# Description de l'application

L'application développée avec Guillaume permet de modéliser dynamiquement des étiquettes textuelles (`LabelModel`) composées de divers éléments de contenu (`LabelElement`, `TypeElement`, etc.). Chaque étiquette peut être personnalisée en combinant des éléments simples ou composites, et enrichie via des décorateurs de contenu (comme la mise en majuscule).

Le modèle des étiquettes prend également en charge un système d'observation permettant à des classes tierces (comme des loggers) de réagir aux modifications. Cette architecture flexible est conçue pour permettre :

- La construction fluide d’étiquettes via le pattern **Builder**
- L’ajout dynamique de comportement aux éléments via le pattern **Decorator**
- L’organisation hiérarchique d’éléments via le pattern **Composite**
- La réaction aux changements via le pattern **Observer**

Cette application pourrait servir, par exemple, dans un système de génération d’étiquettes personnalisées pour des produits, des documents ou des interfaces utilisateur dynamiques.

# Architecture logicielle globale

L'architecture de l'application repose sur une approche modulaire et extensible, facilitée par l'utilisation de plusieurs design patterns bien établis. Le cœur de l'application est centré autour des modèles d’étiquettes (`LabelModel`), composés d’éléments de texte et de type (`ILabelElement`), qui peuvent être enrichis, observés et composés dynamiquement.

## Flux global

Un `LabelModelBuilder` est utilisé pour construire un `LabelModel` contenant un ou plusieurs `ILabelElement`.

Ces éléments peuvent être simples (`LabelElement`, `TypeElement`), composites (`CompositeLabelElement`) ou décorés (`UpperCaseContentDecorator`).

Lorsqu’un changement est effectué (ajout, suppression, etc.), le `LabelModel` notifie tous les `LabelObserver`.

Les observateurs comme `LoggerObserver` peuvent alors réagir (par exemple, en loggant les modifications).

## Couplage des design patterns

Cette architecture permet :

- Une construction fluide et sécurisée des objets (**Builder**)
- Une hiérarchie extensible d’éléments (**Composite**)
- Un ajout comportemental non-invasif (**Decorator**)
- Une réactivité et séparation des responsabilités (**Observer**)

# Justification claire de l’usage de chaque design pattern

## Builder Pattern

**Classes concernées** : `LabelModelBuilder`, `LabelElementBuilder`

Le Builder Pattern est utilisé pour faciliter la construction progressive et lisible des objets complexes comme `LabelModel` ou `ILabelElement`, en séparant la logique d’assemblage de la logique métier.

**Justification** :
- Créer une syntaxe fluide
- Simplifie la création d’étiquettes complexes avec plusieurs éléments
- Évite les constructeurs à rallonge

## Composite Pattern

**Classes concernées** : `ILabelElement`, `CompositeLabelElement`

Le Composite Pattern permet de traiter de manière uniforme des éléments simples et des groupes d’éléments.

**Justification** :
- Permet de créer des hiérarchies d’éléments de label
- Représente des blocs structurés (ex : adresse complète)
- Facilite l'affichage récursif

## Decorator Pattern

**Classes concernées** : `LabelElementDecorator`, `UpperCaseContentDecorator`

Le Decorator Pattern permet d’ajouter dynamiquement du comportement ou du style à un élément sans modifier sa structure de base.

**Justification** :
- Permet de transformer le texte (ex : tout en majuscule)
- Peut être étendu à la couleur, typographie, etc.
- Évite la multiplication des sous-classes

## Observer Pattern

**Classes concernées** : `LabelObserver`, `LoggerObserver`, `LabelModel`

Le Observer Pattern est utilisé pour permettre à des objets externes de réagir aux modifications d’un `LabelModel`.

**Justification** :
- Découple données et logique de notification
- Permet d’ajouter d’autres observateurs (UI, export...)
- Maintient les composants synchronisés

# Diagramme de classes UML

```mermaid
classDiagram
    direction LR

    class App {
        +main(String[]): void
        +printLabel(LabelModel): void
        +printElement(ILabelElement, String): void
        +decorateCompositeElements(CompositeLabelElement): CompositeLabelElement
    }

    class LabelModel {
        +addElement(ILabelElement): void
        +getElements(): List~ILabelElement~
    }

    class ILabelElement {
        <<interface>>
        +getContent(): String
        +getType(): TypeElement
    }

    class LabelElement {
        -content: String
        -type: TypeElement
        +setContent(String): void
        +addObserver(LabelObserver): void
    }

    class CompositeLabelElement {
        -children: List~ILabelElement~
        +addElement(ILabelElement): void
        +getChildren(): List~ILabelElement~
        +addObserver(LabelObserver): void
    }

    class LabelElementDecorator {
        -wrapped: ILabelElement
        +getContent(): String
    }

    class UpperCaseContentDecorator {
        +getContent(): String
    }

    class LabelModelBuilder {
        +withName(...): LabelModelBuilder
        +withElements(...): LabelModelBuilder
        +build(): LabelModel
    }

    class LabelElementBuilder {
        +withContent(...): LabelElementBuilder
        +withObserver(...): LabelElementBuilder
        +build(): LabelElement
    }

    class LabelObserver {
        <<interface>>
        +onElementChanged(...)
    }

    class LoggerObserver {
        +onElementChanged(...)
    }

    ILabelElement <|.. LabelElement
    ILabelElement <|.. CompositeLabelElement
    ILabelElement <|.. LabelElementDecorator
    LabelElementDecorator <|-- UpperCaseContentDecorator

    CompositeLabelElement --> "1..*" ILabelElement : children
    LabelElement --> "*" LabelObserver
    CompositeLabelElement --> "*" LabelObserver
    LabelObserver <|.. LoggerObserver
```

# Diagramme de séquence : Création d’un LabelModel avec notification

```mermaid
sequenceDiagram
    participant App
    participant Builder as LabelElementBuilder
    participant LE1 as LabelElement ("12,99 €")
    participant LE2 as LabelElement ("REFERENCE")
    participant Deco as UpperCaseContentDecorator
    participant Bloc as CompositeLabelElement
    participant Barcode as LabelElement ("123456789012")
    participant Promo as LabelElement ("Promo du jour")
    participant ModelBuilder as LabelModelBuilder
    participant Model as LabelModel
    participant Logger as LoggerObserver

    App->>Builder: build() -> "12,99 €"
    Builder-->>LE1: retourne LabelElement
    App->>Bloc: addElement(LE1)

    App->>Builder: build() -> "REFERENCE"
    Builder-->>LE2: retourne LabelElement
    App->>Deco: new UpperCaseContentDecorator(LE2)
    App->>Bloc: addElement(Deco)

    App->>Builder: build() -> "123456789012"
    Builder-->>Barcode: retourne LabelElement

    App->>Builder: build() -> "Promo du jour"
    Builder-->>Promo: retourne LabelElement

    App->>ModelBuilder: build() avec les éléments
    ModelBuilder->>Model: new LabelModel
    ModelBuilder->>Model: addElement(Bloc)
    ModelBuilder->>Model: addElement(Barcode)
    ModelBuilder->>Model: addElement(Promo)

    App->>Model: addObserver(Logger)
    App->>Model: printLabel()
    Model->>Bloc: getElements()
    Bloc->>LE1: getContent()
    Bloc->>Deco: getContent()
    Deco->>LE2: getContent()
    Model->>Barcode: getContent()
    Model->>Promo: getContent()
```
