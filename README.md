# 4ESGI_DP_LABELDESIGNER

| Composant                   | Rôle                                                                                     |
| --------------------------- | ---------------------------------------------------------------------------------------- |
| `LabelModel`                | Contient et gère la liste des éléments d’une étiquette. Notifie les observateurs.        |
| `ILabelElement`             | Interface commune pour tous les éléments d’étiquette (texte, type, composites, décorés). |
| `LabelElement`              | Élément simple contenant du texte.                                                       |
| `TypeElement`               | Élément représentant un type particulier défini dans `LabelType`.                        |
| `CompositeLabelElement`     | Élément composite contenant plusieurs `ILabelElement`.                                   |
| `LabelElementDecorator`     | Élément décorateur qui ajoute dynamiquement un comportement à un `ILabelElement`.        |
| `UpperCaseContentDecorator` | Exemple de décorateur qui transforme le contenu en majuscules.                           |
| `LabelObserver`             | Interface pour les observateurs du `LabelModel`.                                         |
| `LoggerObserver`            | Implémentation qui log les changements dans le modèle.                                   |
| `LabelElementBuilder`       | Facilite la création de `ILabelElement`.                                                 |
| `LabelModelBuilder`         | Facilite la construction de `LabelModel` complets.                                       |
