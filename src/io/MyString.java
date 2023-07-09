package io;

import io.carte.Case;

public class MyString {
    public String str;

    /* ---------------------------------------------------------
                            CONSTRUCTEUR
    --------------------------------------------------------- */
    public MyString(String str){
        this.str=str;
    }

    /* ---------------------------------------------------------
                        Autres Methodes
    --------------------------------------------------------- */
    public boolean isInNatureTerrain(){
        for (Case.NatureTerrain type : Case.NatureTerrain.values()) {
            if (type.name().equalsIgnoreCase(this.str)) {
                return true;
            }
        }
        return false;
    }

}
