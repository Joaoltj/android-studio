package com.ltj.joao.salvaguardadacapoeira_ce.controller;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
public class FirebaseDB {

    private static FirebaseDatabase fb;
    private static DatabaseReference dr;

    public static void  init(){
        if(dr == null){
            fb = FirebaseDatabase.getInstance();
            fb.setPersistenceEnabled(true);
            dr = fb.getReference();
        }
    }

    public static FirebaseDatabase getFb() {
        return fb;
    }

    public static void setFb(FirebaseDatabase fb) {
        FirebaseDB.fb = fb;
    }

    public static DatabaseReference getDr(String tabela) {
        return dr.child(tabela);
    }

    public static void setDr(DatabaseReference dr) {
        FirebaseDB.dr = dr;
    }
}
