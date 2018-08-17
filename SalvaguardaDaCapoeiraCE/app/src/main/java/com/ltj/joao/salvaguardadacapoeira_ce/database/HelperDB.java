package com.ltj.joao.salvaguardadacapoeira_ce.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Criado por João Santos em 24/03/2018.
 */

public class HelperDB extends SQLiteOpenHelper {

    private static final String NOME = "capoeiraDB";
    private static final int VERSAO = 2;

    public HelperDB(Context context){
        super(context,NOME,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        StringBuilder tableCapoeiristas = new StringBuilder("CREATE TABLE IF NOT EXISTS capoeiristas(\n" +
                "  id INTEGER PRIMARY KEY,\n" +
                "  nome VARCHAR(200) NOT NULL,\n" +
                "  dataNascimento VARCHAR(200) NOT NULL,\n" +
                "  sexo VARCHAR(200) NOT NULL,\n" +
                "  cpf VARCHAR(200) DEFAULT NULL,\n" +
                "  rg VARCHAR(200) DEFAULT NULL,\n" +
                "  telefone VARCHAR(200) NOT NULL,\n" +
                "  email VARCHAR(200) NOT NULL,\n" +
                "  senha VARCHAR(200) NOT NULL,\n" +
                "  apelido VARCHAR(200) NOT NULL,\n" +
                "  whatsapp VARCHAR(200) DEFAULT NULL,\n" +
                "  graduacao VARCHAR(200) NOT NULL,\n" +
                "  anoGraduacao VARCHAR(200) DEFAULT NULL,\n" +
                "  grupo VARCHAR(200) DEFAULT NULL,\n" +
                "  estilo VARCHAR(200) NOT NULL,\n" +
                "  nomeMestre VARCHAR(200) DEFAULT NULL,\n" +
                "  apelidoMestre VARCHAR(200) NOT NULL,\n" +
                "  graduacaoMestre VARCHAR(200) DEFAULT NULL,endereco VARCHAR(200),foto BLOB);");
        db.execSQL(tableCapoeiristas.toString());




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
