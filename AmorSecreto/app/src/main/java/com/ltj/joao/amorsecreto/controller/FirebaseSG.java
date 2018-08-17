package com.ltj.joao.amorsecreto.controller;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseSG {




        public static FirebaseStorage fs;
        public static StorageReference sr;

        public static void initFirebase(){
            if(sr == null){
                fs = FirebaseStorage.getInstance();
                sr = fs.getReference();
            }
        }

    public static FirebaseStorage getFs() {
        return fs;
    }

    public static void setFs(FirebaseStorage fs) {
        FirebaseSG.fs = fs;
    }

    public static StorageReference getSr(String pasta) {
        return sr.child(pasta);
    }

    public static void setSr(StorageReference sr) {
        FirebaseSG.sr = sr;
    }
}
