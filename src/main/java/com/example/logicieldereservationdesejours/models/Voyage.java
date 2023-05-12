package com.example.logicieldereservationdesejours.models;
//import com.opencsv.bean.CsvBindByPosition;

import com.opencsv.bean.CsvBindByPosition;

public class Voyage {
    @CsvBindByPosition(position = 0)
    private String ville;

    @CsvBindByPosition(position = 1)
    private String heure;

    @CsvBindByPosition(position = 2)
    private String type;

    @CsvBindByPosition(position = 3)
    private String contreparties;

    @CsvBindByPosition(position = 4)
    private String contraintes;

    @CsvBindByPosition(position = 5)
    private String services;

    @CsvBindByPosition(position = 6)
    private String transfert;

    @CsvBindByPosition(position = 7)
    private String restauration;

    @CsvBindByPosition(position = 8)
    private String dateArrivee;

    @CsvBindByPosition(position = 9)
    private String dateDepart;

    public String getVille() {
        return ville;
    }

    public String getHeure() {
        return heure;
    }

    public String getType() {
        return type;
    }

    public String getContreparties() {
        return contreparties;
    }

    public String getContraintes() {
        return contraintes;
    }

    public String getServices() {
        return services;
    }

    public String getTransfert() {
        return transfert;
    }

    public String getRestauration() {
        return restauration;
    }

    public String getDateArrivee() {
        return dateArrivee;
    }

    public String getDateDepart() {
        return dateDepart;
    }



    public void setVille(String value) {
    }

    public void setHeure(String value) {
    }

    public void setType(String value) {
    }

    public void setContreparties(String value) {
    }

    public void setContraintes(String value) {
    }

    public void setServices(String value) {
    }

    public void setTransfert(String value) {
    }

    public void setRestauration(String value) {
    }

    public void setDateArrivee(String value) {
    }

    public void setDateDepart(String value) {
    }

    @Override
    public String toString() {
        return "Voyage{" +
                "ville='" + ville + '\'' +
                ", heure='" + heure + '\'' +
                ", type='" + type + '\'' +
                ", contreparties='" + contreparties + '\'' +
                ", contraintes='" + contraintes + '\'' +
                ", services='" + services + '\'' +
                ", transfert='" + transfert + '\'' +
                ", restauration='" + restauration + '\'' +
                ", dateArrivee='" + dateArrivee + '\'' +
                ", dateDepart='" + dateDepart + '\'' +
                '}';
    }

    public Voyage(String ville, String heure, String type, String contreparties, String contraintes, String services, String transfert, String restauration, String dateArrivee, String dateDepart) {
        this.ville = ville;
        this.heure = heure;
        this.type = type;
        this.contreparties = contreparties;
        this.contraintes = contraintes;
        this.services = services;
        this.transfert = transfert;
        this.restauration = restauration;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
    }
}
