package br.com.appsociotorcedor;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SocioDAO {

    public static void inserir(Context context, Socio socio){
        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", socio.getNome() );
        valores.put("email", socio.getEmail() );
        valores.put("dataNasc", socio.getDataNasc().toString() );
        valores.put("modalidade", socio.getModalidade().toString() );


        db.insert("socio", null, valores);
    }

    public static void editar(Context context, Socio socio){
        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", socio.getNome() );
        valores.put("email", socio.getEmail() );
        valores.put("dataNasc", socio.getDataNasc().toString() );
        valores.put("modalidade", socio.getModalidade().toString());

        db.update("socio", valores ,
                " matricula = " + socio.getMatricula(), null  );
    }

    public static void excluir(Context context, int matricula){
        SQLiteDatabase db = new Conexao(context).getWritableDatabase();

        db.delete("socio",
                " matricula = " + matricula, null  );
    }

    public static List<Socio> getSocios(Context context){
        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getReadableDatabase();
        List<Socio> lista = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT matricula, nome, email, dataNasc, modalidade FROM socio",
                null);
        if( cursor != null && cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Socio socio = new Socio();
                socio.setMatricula( cursor.getInt( 0 )  );
                socio.setNome( cursor.getString( 1 )  );
                socio.setEmail( cursor.getString( 2 )  );
                socio.setDataNasc(cursor.getString( 3 )  );
                socio.setModalidade(Modalidade.valueOf(cursor.getString( 4 ))  );

                lista.add(socio);
            }while ( cursor.moveToNext() );
        }
        return lista;
    }
}