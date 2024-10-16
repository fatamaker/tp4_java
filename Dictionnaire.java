public class Dictionnaire {
    private int nb_mot;
    private Mot_dict[] Dict;
    private String nom;

    public Dictionnaire(int taille, String nom) {
        Dict = new Mot_dict[taille]; 
        this.nom = nom;
        this.nb_mot = 0;
    }

   
    public void Ajouter_Mot(Mot_dict d) { 
        if (nb_mot < Dict.length) {
            Dict[nb_mot] = d;
            nb_mot++;
            Trier(); 
        } else {
            System.out.println("Le dictionnaire est plein!");
        }
    }

   
    public void Trier() {
        boolean test = true;
        while (test) {
            test = false;
            for (int i = 0; i < nb_mot - 1; i++) {
                if (Dict[i].getMot().compareTo(Dict[i + 1].getMot()) > 0) {
                   
                    Mot_dict aux = Dict[i];
                    Dict[i] = Dict[i + 1];
                    Dict[i + 1] = aux;
                    test = true;
                }
            }
        }
    }

   
    public void Supprimer_Mot(Mot_dict m) {
        int index = -1;
        for (int i = 0; i < nb_mot; i++) {
            if (Dict[i].getMot().equals(m.getMot())) { 
                index = i;
                break;
            }
        }
        if (index != -1) {
            
            for (int i = index; i < nb_mot - 1; i++) {
                Dict[i] = Dict[i + 1];
            }
            Dict[nb_mot - 1] = null; 
            nb_mot--; 
        } else {
            System.out.println("Mot non trouvé!");
        }
    }

   
    public String Recherche_dicho(String mot) {
        int gauche = 0;
        int droite = nb_mot - 1;

        while (gauche <= droite) {
            int milieu = (gauche + droite) / 2;
            int comparaison = Dict[milieu].getMot().compareToIgnoreCase(mot);

            if (comparaison == 0) {
                return Dict[milieu].getDefinition(); 
            } else if (comparaison < 0) {
                gauche = milieu + 1; 
            } else {
                droite = milieu - 1; 
            }
        }
        return "Le mot '" + mot + "' n'est pas dans le dictionnaire."; 
    }

    
    public void Lister_dictionnaire() {
        for (int i = 0; i < nb_mot; i++) {
            System.out.println("Mot : " + Dict[i].getMot() + " - Définition : " + Dict[i].getDefinition());
        }
    }

    
    public int Nombre_synonyme(Mot_dict m) {
        int c = 0;
        for (int i = 0; i < nb_mot; i++) {
            if (m.synonyme(Dict[i])) {
                c++;
            }
        }
        return c;
    }


    
    public static void main(String[] args) {
        
        Dictionnaire dico = new Dictionnaire(5, "MonDico");

        
        Mot_dict mot1 = new Mot_dict("heureux", "Qui éprouve du bonheur.");
        Mot_dict mot2 = new Mot_dict("joyeux", "Qui exprime de la joie.");
        Mot_dict mot3 = new Mot_dict("farah", "la friha.");
        Mot_dict mot4 = new Mot_dict("fatma", "fatyy");
        Mot_dict mot5 = new Mot_dict("Sombre", "Qui manque de lumière, obscur.");

        
        dico.Ajouter_Mot(mot1);
        dico.Ajouter_Mot(mot2);
        dico.Ajouter_Mot(mot3);
        dico.Ajouter_Mot(mot4);
        dico.Ajouter_Mot(mot5);

      
        System.out.println("Dictionnaire après ajout de mots :");
        dico.Lister_dictionnaire();

        
        System.out.println("\nRecherche du mot 'joyeux' :");
        String definition = dico.Recherche_dicho("joyeux");
        System.out.println("Définition de 'joyeux' : " + definition);

        
       
        int nb_synonymes = dico.Nombre_synonyme(mot1);
        System.out.println("Nombre de synonymes de 'heureux' : " + nb_synonymes);

      
        System.out.println("\nSuppression du mot 'Sombre' :");
        dico.Supprimer_Mot(mot5);

       
        System.out.println("\nDictionnaire après suppression de 'Sombre' :");
        dico.Lister_dictionnaire();

       
        System.out.println("\nRecherche du mot 'Sombre' (après suppression) :");
        String resultNonTrouve = dico.Recherche_dicho("Sombre");
        System.out.println(resultNonTrouve);
    }
}


