/** 
 * Copyright 2017 Telerik AD                                                                                                                                                                            
 *                                                                                                                                                                                                      
 * Licensed under the Apache License, Version 2.0 (the "License");                                                                                                                                      
 * you may not use this file except in compliance with the License.                                                                                                                                     
 * You may obtain a copy of the License at                                                                                                                                                              
 *                                                                                                                                                                                                      
 *     http://www.apache.org/licenses/LICENSE-2.0                                                                                                                                                       
 *                                                                                                                                                                                                      
 * Unless required by applicable law or agreed to in writing, software                                                                                                                                  
 * distributed under the License is distributed on an "AS IS" BASIS,                                                                                                                                    
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.                                                                                                                             
 * See the License for the specific language governing permissions and                                                                                                                                  
 * limitations under the License.                                                                                                                                                                       
                                                                                                                                                                                                       
                                                                                                                                                                                                       
                                                                                                                                                                                                       
                                                                                                                                                                                                       
                                                                                                                                                                                                       
                                                                                                                                                                                                       
                                                                                                                                                                                                       
                                                                                                                                                                                                       

*/

(function(f){
    if (typeof define === 'function' && define.amd) {
        define(["kendo.core"], f);
    } else {
        f();
    }
}(function(){
(function ($, undefined) {
/* Filter cell operator messages */

if (kendo.ui.FilterCell) {
kendo.ui.FilterCell.prototype.options.operators =
$.extend(true, kendo.ui.FilterCell.prototype.options.operators,{
  "date": {
    "eq": "Est ??gal ??",
    "gte": "Est post??rieur ou ??gal ??",
    "gt": "Est post??rieur",
    "lte": "Est ant??rieur ou ??gal ??",
    "lt": "Est ant??rieur",
    "neq": "N???est pas ??gal ??",
    "isnull": "Est nulle",
    "isnotnull": "N???est pas nulle"
  },
  "number": {
    "eq": "Est ??gal ??",
    "gte": "Est sup??rieur ou ??gal ??",
    "gt": "Est sup??rieur ??",
    "lte": "Est inf??rieur ou ??gal ??",
    "lt": "Est inf??rieur ??",
    "neq": "N???est pas ??gal ??",
    "isnull": "Est nulle",
    "isnotnull": "N???est pas nulle"
  },
  "string": {
    "endswith": "Se termine par",
    "eq": "Est ??gal ??",
    "neq": "N???est pas ??gal ??",
    "startswith": "Commence par",
    "contains": "Contient",
    "doesnotcontain": "Ne contient pas",
    "isnull": "Est nulle",
    "isnotnull": "N???est pas nulle",
    "isempty": "Est vide",
    "isnotempty": "N???est pas vide"
  },
  "enums": {
    "eq": "Est ??gal ??",
    "neq": "N???est pas ??gal ??",
    "isnull": "Est nulle",
    "isnotnull": "N???est pas nulle"
  }
});
}

/* Filter menu operator messages */

if (kendo.ui.FilterMenu) {
kendo.ui.FilterMenu.prototype.options.operators =
$.extend(true, kendo.ui.FilterMenu.prototype.options.operators,{
  "date": {
    "eq": "Est ??gal ??",
    "gte": "Est post??rieur ou ??gal ??",
    "gt": "Est post??rieur",
    "lte": "Est ant??rieur ou ??gal ??",
    "lt": "Est ant??rieur",
    "neq": "N???est pas ??gal ??",
    "isnull": "Est nulle",
    "isnotnull": "N???est pas nulle"
  },
  "number": {
    "eq": "Est ??gal ??",
    "gte": "Est sup??rieur ou ??gal ??",
    "gt": "Est sup??rieur ??",
    "lte": "Est inf??rieur ou ??gal ??",
    "lt": "Est inf??rieur ??",
    "neq": "N???est pas ??gal ??",
    "isnull": "Est nulle",
    "isnotnull": "N???est pas nulle"
  },
  "string": {
    "endswith": "Se termine par",
    "eq": "Est ??gal ??",
    "neq": "N???est pas ??gal ??",
    "startswith": "Commence par",
    "contains": "Contient",
    "doesnotcontain": "Ne contient pas",
    "isnull": "Est nulle",
    "isnotnull": "N???est pas nulle",
    "isempty": "Est vide",
    "isnotempty": "N???est pas vide"
  },
  "enums": {
    "eq": "Est ??gal ??",
    "neq": "N???est pas ??gal ??",
    "isnull": "Est nulle",
    "isnotnull": "N???est pas nulle"
  }
});
}

/* ColumnMenu messages */

if (kendo.ui.ColumnMenu) {
kendo.ui.ColumnMenu.prototype.options.messages =
$.extend(true, kendo.ui.ColumnMenu.prototype.options.messages,{
  "columns": "Colonnes",
  "sortAscending": "Tri croissant",
  "sortDescending": "Tri d??croissant",
  "settings": "Param??tres de colonne",
  "done": "Fini",
  "lock": "Bloquer",
  "unlock": "Ouvrir"
});
}

/* RecurrenceEditor messages */

if (kendo.ui.RecurrenceEditor) {
kendo.ui.RecurrenceEditor.prototype.options.messages =
$.extend(true, kendo.ui.RecurrenceEditor.prototype.options.messages,{
  "daily": {
    "interval": "jour(s)",
    "repeatEvery": "R??p??ter chaque:"
  },
  "end": {
    "after": " Apr??s",
    "occurrence": "occurrence(s)",
    "label": "Finir:",
    "never": "Jamais",
    "on": "Sur",
    "mobileLabel": "Ends"
  },
  "frequencies": {
    "daily": "Une fois par jour",
    "monthly": "Une fois par mois",
    "never": "Jamais",
    "weekly": "Une fois par semaine",
    "yearly": "Une fois par an"
  },
  "monthly": {
    "day": "Jour",
    "interval": "mois",
    "repeatEvery": "R??p??ter chaque:",
    "repeatOn": "R??p??ter l'op??ration sur:"
  },
  "offsetPositions": {
    "first": "premier",
    "fourth": "quatri??me",
    "last": "dernier",
    "second": "second",
    "third": "troisi??me"
  },
  "weekly": {
    "repeatEvery": "R??p??ter chaque:",
    "repeatOn": "R??p??ter l'op??ration sur:",
    "interval": "semaine(s)"
  },
  "yearly": {
    "of": "de",
    "repeatEvery": "R??p??ter chaque:",
    "repeatOn": "R??p??ter l'op??ration sur:",
    "interval": "ann??e(ans)"
  },
  "weekdays": {
    "day": "jour",
    "weekday": "jour de la semaine",
    "weekend": "jour de week-end"
  }
});
}

/* Grid messages */

if (kendo.ui.Grid) {
kendo.ui.Grid.prototype.options.messages =
$.extend(true, kendo.ui.Grid.prototype.options.messages,{
  "commands": {
    "create": "Ins??rer",
    "destroy": "Effacer",
    "canceledit": "Annuler",
    "update": "Mettre ?? jour",
    "edit": "??diter",
    "excel": "Exporter vers Excel",
    "pdf": "Exporter vers PDF",
    "select": "S??lectionner",
    "cancel": "Annuler les modifications",
    "save": "Enregistrer les modifications"
  },
  "editable": {
    "confirmation": "??tes-vous s??r de vouloir supprimer cet enregistrement?",
    "cancelDelete": "Annuler",
    "confirmDelete": "Effacer"
  },
  "noRecords": "Aucun enregistrement disponible."
});
}

/* Pager messages */

if (kendo.ui.Pager) {
kendo.ui.Pager.prototype.options.messages =
$.extend(true, kendo.ui.Pager.prototype.options.messages,{
  "allPages": "Tous",
  "page": "Page",
  "display": "Afficher les items {0} - {1} de {2}",
  "of": "de {0}",
  "empty": "Aucun enregistrement ?? afficher.",
  "refresh": "Actualiser",
  "first": "Aller ?? la premi??re page",
  "itemsPerPage": "articles par page",
  "last": "Aller ?? la derni??re page",
  "next": "Aller ?? la page suivante",
  "previous": "Aller ?? la page pr??c??dente",
  "morePages": "Plusieurs pages"
});
}

/* FilterCell messages */

if (kendo.ui.FilterCell) {
kendo.ui.FilterCell.prototype.options.messages =
$.extend(true, kendo.ui.FilterCell.prototype.options.messages,{
  "filter": "Filtrer",
  "clear": "Effacer filtre",
  "isFalse": "est fausse",
  "isTrue": "est vrai",
  "operator": "Op??rateur"
});
}

/* FilterMenu messages */

if (kendo.ui.FilterMenu) {
kendo.ui.FilterMenu.prototype.options.messages =
$.extend(true, kendo.ui.FilterMenu.prototype.options.messages,{
  "filter": "Filtrer",
  "and": "Et",
  "clear": "Effacer filtre",
  "info": "Afficher les lignes avec la valeur qui",
  "selectValue": "-S??lectionner-",
  "isFalse": "est fausse",
  "isTrue": "est vrai",
  "or": "Ou",
  "cancel": "Annuler",
  "operator": "Op??rateur",
  "value": "Valeur"
});
}

/* FilterMultiCheck messages */

if (kendo.ui.FilterMultiCheck) {
kendo.ui.FilterMultiCheck.prototype.options.messages =
$.extend(true, kendo.ui.FilterMultiCheck.prototype.options.messages,{
  "checkAll": "Choisir toutes",
  "clear": "Effacer filtre",
  "filter": "Filtrer",
  "search": "Recherche"
});
}

/* Groupable messages */

if (kendo.ui.Groupable) {
kendo.ui.Groupable.prototype.options.messages =
$.extend(true, kendo.ui.Groupable.prototype.options.messages,{
  "empty": "Faites glisser un en-t??te de colonne et d??poser ici pour grouper par cette colonne."
});
}

/* Editor messages */

if (kendo.ui.Editor) {
kendo.ui.Editor.prototype.options.messages =
$.extend(true, kendo.ui.Editor.prototype.options.messages,{
  "bold": "Gras",
  "createLink": "Ins??rer un lien hypertexte",
  "fontName": "Police",
  "fontNameInherit": "(police h??rit??e)",
  "fontSize": "Taille de police",
  "fontSizeInherit": "(taille h??rit??e)",
  "formatBlock": "Style du paragraphe",
  "indent": "Augmenter le retrait",
  "insertHtml": "Ins??rer HTML",
  "insertImage": "Ins??rer image",
  "insertOrderedList": "Liste num??rot??e",
  "insertUnorderedList": "Liste ?? puces",
  "italic": "Italique",
  "justifyCenter": "Centrer",
  "justifyFull": "Justifier",
  "justifyLeft": "Aligner le texte ?? gauche",
  "justifyRight": "Aligner le texte ?? droite",
  "outdent": "Diminuer le retrait",
  "strikethrough": "Barr??",
  "styles": "Styles",
  "subscript": "Subscript",
  "superscript": "Superscript",
  "underline": "Soulign??",
  "unlink": "Supprimer le lien hypertexte",
  "deleteFile": "??tes-vous s??r de vouloir supprimer \"{0}\"?",
  "directoryNotFound": "Un r??pertoire avec ce nom n'a pas ??t?? trouv??.",
  "emptyFolder": "Vider le dossier",
  "invalidFileType": "Le fichier s??lectionn?? \"{0}\" n'est pas valide. Les types de fichiers support??s sont {1}.",
  "orderBy": "Organiser par:",
  "orderByName": "Nom",
  "orderBySize": "Taille",
  "overwriteFile": "Un fichier avec le nom \"{0}\" existe d??j?? dans le r??pertoire courant. Voulez-vous le remplacer?",
  "uploadFile": "T??l??charger",
  "backColor": "Couleur de fond",
  "foreColor": "Couleur",
  "dialogButtonSeparator": "Ou",
  "dialogCancel": "Fermer",
  "dialogInsert": "Ins??rer",
  "imageAltText": "Le texte de remplacement",
  "imageWebAddress": "Adresse Web",
  "imageWidth": "Largeur (px)",
  "imageHeight": "Hauteur (px)",
  "linkOpenInNewWindow": "Ouvrir dans une nouvelle fen??tre",
  "linkText": "Text",
  "linkToolTip": "Info-bulle",
  "linkWebAddress": "Adresse Web",
  "search": "Search",
  "createTable": "Ins??rer un tableau",
  "addColumnLeft": "Add column on the left",
  "addColumnRight": "Add column on the right",
  "addRowAbove": "Add row above",
  "addRowBelow": "Add row below",
  "deleteColumn": "Supprimer la colonne",
  "deleteRow": "Supprimer ligne",
  "dropFilesHere": "drop files here to upload",
  "formatting": "Format",
  "viewHtml": "View HTML",
  "dialogUpdate": "Update",
  "insertFile": "Insert file",
  "dialogOk": "OK",
  "tableWizard": "Assistant de tableau",
  "tableTab": "Table",
  "cellTab": "Cellule",
  "accessibilityTab": "Accessibilit??",
  "caption": "Sous-titre",
  "summary": "Sommaire",
  "width": "Largeur",
  "height": "Hauteur",
  "cellSpacing": "Espacement des cellules",
  "cellPadding": "Rembourrage des cellules",
  "cellMargin": "Marge des cellules",
  "alignment": "Alignement",
  "background": "Fond",
  "cssClass": "CSS Classe",
  "id": "Id",
  "border": "Bordure",
  "borderStyle": "Style de bordure",
  "collapseBorders": "R??tracter bordures",
  "wrapText": "Renvoi ?? la ligne",
  "associateCellsWithHeaders": "Cellules associ??es aux ent??tes",
  "alignLeft": "Aligner ?? gauche",
  "alignCenter": "Aligner le centre",
  "alignRight": "Aligner ?? droite",
  "alignLeftTop": "Aligner ?? gauche et haut",
  "alignCenterTop": "Aligner le centre et haut",
  "alignRightTop": "Aligner ?? droite et haut",
  "alignLeftMiddle": "Aligner ?? gauche et milieu",
  "alignCenterMiddle": "Aligner le centre et milieu",
  "alignRightMiddle": "Aligner ?? droite et milieu",
  "alignLeftBottom": "Aligner ?? gauche et bas",
  "alignCenterBottom": "Aligner le centre et bas",
  "alignRightBottom": "Aligner ?? droite et bas",
  "alignRemove": "Retirer alignement",
  "columns": "Colonnes",
  "rows": "Lignes",
  "selectAllCells": "S??lectionner toutes les cellules"
});
}

/* FileBrowser and ImageBrowser messages */

var browserMessages = {
  "uploadFile" : "Charger",
  "orderBy" : "Trier par",
  "orderByName" : "Nom",
  "orderBySize" : "Taille",
  "directoryNotFound" : "Aucun r??p??rtoire de ce nom.",
  "emptyFolder" : "R??pertoire vide",
  "deleteFile" : 'Etes-vous s??r de vouloir supprimer "{0}"?',
  "invalidFileType" : "Le fichier s??lectionn?? \"{0}\" n'est pas valide. Les type fichiers support??s sont {1}.",
  "overwriteFile" : "Un fichier du nom \"{0}\" existe d??j?? dans ce r??pertoire. Voulez-vous le remplacer?",
  "dropFilesHere" : "glissez les fichiers ici pour les charger",
  "search" : "Recherche"
};

if (kendo.ui.FileBrowser) {
kendo.ui.FileBrowser.prototype.options.messages =
$.extend(true, kendo.ui.FileBrowser.prototype.options.messages, browserMessages);
}

if (kendo.ui.ImageBrowser) {
kendo.ui.ImageBrowser.prototype.options.messages =
$.extend(true, kendo.ui.ImageBrowser.prototype.options.messages, browserMessages);
}


/* Upload messages */

if (kendo.ui.Upload) {
kendo.ui.Upload.prototype.options.localization =
$.extend(true, kendo.ui.Upload.prototype.options.localization,{
  "cancel": "Annuler",
  "dropFilesHere": "d??poser les fichiers ?? t??l??charger ici",
  "remove": "Retirer",
  "retry": "R??essayer",
  "select": "S??lectionner...",
  "statusFailed": "??chou??",
  "statusUploaded": "t??l??charg??",
  "statusUploading": "t??l??chargement",
  "uploadSelectedFiles": "T??l??charger des fichiers",
  "headerStatusUploaded":  "Compl??t??",
  "headerStatusUploading": "T??l??chargement..."
});
}

/* Scheduler messages */

if (kendo.ui.Scheduler) {
kendo.ui.Scheduler.prototype.options.messages =
$.extend(true, kendo.ui.Scheduler.prototype.options.messages,{
  "allDay": "toute la journ??e",
  "cancel": "Annuler",
  "editable": {
    "confirmation": "Etes-vous s??r de vouloir supprimer cet ??l??ment?"
  },
  "date": "Date",
  "destroy": "Effacer",
  "editor": {
    "allDayEvent": "Toute la journ??e",
    "description": "Description",
    "editorTitle": "Ev??nement",
    "end": "Fin",
    "endTimezone": "End timezone",
    "repeat": "R??p??ter",
    "separateTimezones": "Use separate start and end time zones",
    "start": "D??but",
    "startTimezone": "Start timezone",
    "timezone": " ",
    "timezoneEditorButton": "Fuseau horaire",
    "timezoneEditorTitle": "Fuseaux horaires",
    "title": "Titre",
    "noTimezone": "Pas de fuseau horaire"
  },
  "event": "Ev??nement",
  "recurrenceMessages": {
    "deleteRecurring": "Voulez-vous supprimer seulement cet ??v??nement ou toute la s??rie?",
    "deleteWindowOccurrence": "Suppression de l'??l??ment courant",
    "deleteWindowSeries": "Suppression de toute la s??rie",
    "deleteWindowTitle": "Suppression d'un ??l??ment r??current",
    "editRecurring": "Voulez-vous modifier seulement cet ??v??nement ou toute la s??rie?",
    "editWindowOccurrence": "Modifier l'occurrence courante",
    "editWindowSeries": "Modifier la s??rie",
    "editWindowTitle": "Modification de l'??l??ment courant"
  },
  "save": "Sauvegarder",
  "time": "Time",
  "today": "Aujourd'hui",
  "views": {
    "agenda": "Agenda",
    "day": "Jour",
    "month": "Mois",
    "week": "Semaine",
    "workWeek": "Semaine de travail",
    "timeline": "Chronologie"
  },
  "deleteWindowTitle": "Suppression de l'??l??ment",
  "showFullDay": "Montrer toute la journ??e",
  "showWorkDay": "Montrer les heures ouvrables"
});
}


/* Validator messages */

if (kendo.ui.Validator) {
kendo.ui.Validator.prototype.options.messages =
$.extend(true, kendo.ui.Validator.prototype.options.messages,{
  "required": "{0} est requis",
  "pattern": "{0} n'est pas valide",
  "min": "{0} doit ??tre plus grand ou ??gal ?? {1}",
  "max": "{0} doit ??tre plus petit ou ??gal ?? {1}",
  "step": "{0} n'est pas valide",
  "email": "{0} n'est pas un courriel valide",
  "url": "{0} n'est pas une adresse web valide",
  "date": "{0} n'est pas une date valide",
  "dateCompare": "La date de fin doit ??tre post??rieure ?? la date de d??but"
});
}

/* Dialog */

if (kendo.ui.Dialog) {
kendo.ui.Dialog.prototype.options.messages =
$.extend(true, kendo.ui.Dialog.prototype.options.localization, {
  "close": "Fermer"
});
}

/* Alert */

if (kendo.ui.Alert) {
kendo.ui.Alert.prototype.options.messages =
$.extend(true, kendo.ui.Alert.prototype.options.localization, {
  "okText": "OK"
});
}

/* Confirm */

if (kendo.ui.Confirm) {
kendo.ui.Confirm.prototype.options.messages =
$.extend(true, kendo.ui.Confirm.prototype.options.localization, {
  "okText": "OK",
  "cancel": "Annuler"
});
}

/* Prompt */
if (kendo.ui.Prompt) {
kendo.ui.Prompt.prototype.options.messages =
$.extend(true, kendo.ui.Prompt.prototype.options.localization, {
  "okText": "OK",
  "cancel": "Annuler"
});
}

})(window.kendo.jQuery);
}));