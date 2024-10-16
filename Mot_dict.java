public class Mot_dict {
    private String mot ;
    private String definition ;

    public Mot_dict(String mot, String definition) {
        this.mot = mot;
        this.definition = definition;
    }

    String getMot (){
        return mot ;
    }

    String getDefinition(){
        return definition ;
    }

    void setDefinition(String a){
        this.definition=a;
    }

     void setMot(String a){
        this.mot=a;
    }

    Boolean synonyme (String ch){
        return this.mot.compareTo(ch) == 0;
    }

    Boolean synonyme (Mot_dict m){
       return this.definition.equals(m.definition);
    }




    

    }
