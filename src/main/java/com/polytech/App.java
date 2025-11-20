package com.polytech;

import com.polytech.tp.Cours;
import com.polytech.tp.CoursBuilder;
import com.polytech.tp.CoursEnLigne;
import com.polytech.tp.CoursMagistral;
import com.polytech.tp.Etudiant;
import com.polytech.tp.GestionnaireEmploiDuTemps;
import com.polytech.tp.ICours;
import com.polytech.tp.Responsable;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

    // En-tête stylisé
    System.out.println("==============================================");
    System.out.println("          DÉMONSTRATION DESIGN PATTERNS        ");
    System.out.println("==============================================\n");

    // =====================================================
    // EXERCICE 1 : BUILDER
    // =====================================================
    System.out.println("[ EXERCICE 1 : BUILDER ]\n");

    Cours cours1 = new CoursBuilder()
            .setMatiere("Génie Logiciel")
            .setEnseignant("Mr Oussama")
            .setSalle("D23")
            .setDate("Lundi 20/11/2024")
            .setHeureDebut("8h00")
            .setEstOptionnel(false)
            .setNiveau("2A")
            .setNecessiteProjecteur(true)
            .build();

    System.out.println(">> Cours créé : ");
    System.out.println("--------------------------------------------------");
    System.out.println(cours1.getDescription());
    System.out.println("Durée estimée : " + cours1.getDuree() + "h");
    System.out.println("--------------------------------------------------\n");


    // =====================================================
    // EXERCICE 2 : DECORATOR
    // =====================================================
    System.out.println("[ EXERCICE 2 : DECORATOR ]\n");

    ICours coursBase = new CoursBuilder()
            .setMatiere("Assurance Qualité Logiciel")
            .setEnseignant("Mr Omar")
            .setSalle("C15")
            .setDate("Mardi 21/11/2024")
            .setHeureDebut("10h00")
            .build();

    System.out.println("Cours de base :");
    System.out.println("  - " + coursBase.getDescription());

    ICours enLigne = new CoursEnLigne(coursBase);
    ICours magistral = new CoursMagistral(coursBase);

    System.out.println("\nAjout de décorations :");
    System.out.println("  + En ligne      → " + enLigne.getDescription());
    System.out.println("  + Magistral     → " + magistral.getDescription());

    ICours combinaison = new CoursMagistral(new CoursEnLigne(coursBase));
    System.out.println("\nCombinaison :");
    System.out.println("  ★ " + combinaison.getDescription() + "\n");


    // =====================================================
    // EXERCICE 3 : OBSERVER
    // =====================================================
    System.out.println("[ EXERCICE 3 : OBSERVER ]\n");

    GestionnaireEmploiDuTemps gestionnaire = new GestionnaireEmploiDuTemps();

    Etudiant e1 = new Etudiant("Alice");
    Etudiant e2 = new Etudiant("Bob");
    Responsable resp = new Responsable("Dr. Martin");

    gestionnaire.attach(e1);
    gestionnaire.attach(e2);
    gestionnaire.attach(resp);

    System.out.println(">>> Ajout d'un cours (notification envoyée) ");
    gestionnaire.ajouterCours(enLigne);

    System.out.println("\n>>> Modification d'un cours ");
    gestionnaire.modifierCours(enLigne, "Déplacement du cours en salle D23");

    System.out.println("\n>>> Changement général dans l'emploi du temps ");
    gestionnaire.setChangement("Cours du lundi 8h annulé");

    System.out.println("\n>>> Bob se désabonne ");
    gestionnaire.detach(e2);

    System.out.println(">>> Nouveau changement (Bob ne reçoit plus) ");
    gestionnaire.setChangement("C15 devient C16");


    // =====================================================
    // DÉMONSTRATION FINALE
    // =====================================================
    System.out.println("\n==============================================");
    System.out.println("             DÉMONSTRATION FINALE              ");
    System.out.println("==============================================\n");

    ICours coursComplet = new CoursBuilder()
            .setMatiere("Intelligence Artificielle")
            .setEnseignant("Dr. Turing")
            .setSalle("Amphi A")
            .setDate("Mercredi 22/11/2024")
            .setHeureDebut("14h00")
            .setEstOptionnel(true)
            .setNiveau("3A")
            .setNecessiteProjecteur(true)
            .build();

    ICours coursDecoré = new CoursMagistral(new CoursEnLigne(coursComplet));

    GestionnaireEmploiDuTemps gestIA = new GestionnaireEmploiDuTemps();
    gestIA.attach(new Etudiant("Charlie"));
    gestIA.attach(new Etudiant("Diana"));
    gestIA.attach(new Responsable("Prof. Lovelace"));

    System.out.println(">>> Ajout du cours complet décoré :");
    gestIA.ajouterCours(coursDecoré);

    System.out.println("\n============== FIN DEMO ==============");
}

}
