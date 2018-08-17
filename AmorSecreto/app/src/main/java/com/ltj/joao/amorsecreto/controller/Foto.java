package com.ltj.joao.amorsecreto.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Foto{

    private String extensao;
    private Bitmap bitmap;
    private String imagemNome;

    public String getImagemNome() {
        return imagemNome;
    }

    public void setImagemNome(String imagemNome) {
        this.imagemNome = imagemNome;
    }



    public void setExtensaoDaFoto(String path){
        String[] aux = path.split("\\.");
        this.extensao = aux[aux.length - 1];
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }



    public  Bitmap ajustarTamanho(Context context,File file, float newWidth, float newHeight) throws IOException {
        Bitmap bmpOriginal = BitmapFactory.decodeFile(file.getPath());
        Bitmap novoBmp = null;
        int w = bmpOriginal.getWidth();
        int h = bmpOriginal.getHeight();

        float densityFactor = context.getResources().getDisplayMetrics().density;
        float novoW = newWidth * densityFactor;
        float novoH = newHeight * densityFactor;

        //Calcula escala em percentagem do tamanho original para o novo tamanho
        float scalaW = novoW / w;
        float scalaH = novoH / h;

        // Criando uma matrix para manipulação da imagem BitMap
        Matrix matrix = new Matrix();

        // Definindo a proporção da escala para o matrix
        matrix.postScale(scalaW, scalaH);

        //criando o novo BitMap com o novo tamanho
        novoBmp = Bitmap.createBitmap(bmpOriginal, 0, 0, w, h, matrix, true);

        novoBmp = ajustarRotacao(file,novoBmp);
        bmpOriginal.recycle();
        bitmap = novoBmp;


        return bitmap;
    }


    private static Bitmap ajustarRotacao(File file, Bitmap bitmap) throws IOException {
        Matrix matrix = new Matrix();
        boolean fixed = false;
        ExifInterface exif = new ExifInterface(file.getPath()); // Classe para ler tags escritas no JPEG
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL); // Orienta��o que foi salva a foto

        // Rotate bitmap
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.postRotate(180);
                fixed = true;
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.postRotate(90);
                fixed = true;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.postRotate(270);
                fixed = true;
                break;
            default:
                fixed = false;
                break;
        }

        if(!fixed) {
            return bitmap;
        }

        // Corre��o da orienta��o da foto (passa a matrix)
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,true);

        bitmap.recycle();
        bitmap = null;

        return newBitmap;
    }
}