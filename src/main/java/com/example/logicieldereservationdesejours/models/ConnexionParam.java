package com.example.logicieldereservationdesejours.models;

public class ConnexionParam {
    String identifiant;
    String motDePasse;
    String typeConnexion;

    private static final ConnexionParam instance = new ConnexionParam();

    private ConnexionParam() {
    }

    public static final ConnexionParam getInstance(){
        return instance;
    }

    public void setConnexion(String identifiant, String typeConnexion){
        System.out.println("identifiant = "+identifiant);
        System.out.println("motDePasse = " + motDePasse);
        System.out.println("typeConnexion = "+typeConnexion);

        this.identifiant=identifiant;
        this.motDePasse = motDePasse;
        this.typeConnexion=typeConnexion;
    }

    public String getId() {
        return identifiant;
    }
    public String getMotDePasse() {
        return motDePasse;
    }


    public String getTypeConnexion() {
        return typeConnexion;
    }
}

