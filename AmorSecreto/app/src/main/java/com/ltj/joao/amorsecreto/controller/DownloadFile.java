package com.ltj.joao.amorsecreto.controller;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

public class DownloadFile {
    DownloadManager downloadManager;
    Context context;
    DownloadManager.Request request;
    Uri uri;
    public DownloadFile(Context context){
        this.context = context;
    }
    public void download(String urlFile,String subPasta,String nome){
            File folder = new File(Environment.getExternalStorageDirectory() + "/AMOR SECRETO/"+subPasta);
            if (!folder.exists()) {
            folder.mkdir();
            }

            downloadManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
            uri = Uri.parse(urlFile);
            request = new DownloadManager.Request(uri);
            request.setTitle("AMOR SECRETO");
            request.setDescription(subPasta);
            request.setDestinationInExternalPublicDir("/AMOR SECRETO/"+subPasta, nome);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            downloadManager.enqueue(request);
    }
}

