
package com.ltj.joao.amorsecreto.controller;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ltj.joao.amorsecreto.view.Pessoa;



public class FirebaseDB {

    public static FirebaseDatabase fb;
    public static DatabaseReference dr;

    public static void initFirebase(){
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
