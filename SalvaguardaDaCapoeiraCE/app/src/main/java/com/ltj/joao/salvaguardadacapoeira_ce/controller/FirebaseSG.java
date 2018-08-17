package com.ltj.joao.salvaguardadacapoeira_ce.controller;

import com.google.firebase.storage.StorageReference;

import com.google.firebase.storage.FirebaseStorage;

public class FirebaseSG {



        private static FirebaseStorage fs;
        private static StorageReference sr;

        public static void  init(){
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
