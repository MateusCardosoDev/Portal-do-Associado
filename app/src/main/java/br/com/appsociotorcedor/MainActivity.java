package br.com.appsociotorcedor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAdicionar;
    private ListView lvSocios;
    private List<Socio> listSocios;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSocios = findViewById(R.id.lvSocios);
        btnAdicionar = findViewById(R.id.btnAdd);

        lvSocios.setOnItemLongClickListener((parent, view, position, id) -> {
            deletar(position);
            return true;
        });

        btnAdicionar.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,
                    FormularioTorcedorActivity.class);
            i.putExtra("acao", "inserir" );
            startActivity(i);
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        carregarSocios();
    }
        private void carregarSocios(){
        listSocios = SocioDAO.getSocios(this);

            if (listSocios.isEmpty()){
                lvSocios.setEnabled(false);
                String[] listaVazia = {getString(R.string.listavazia)};
                adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaVazia);
            }else {
                lvSocios.setEnabled(true);
                adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listSocios);
            }
            lvSocios.setAdapter(adapter);

        }

    private void deletar (int posicao){
        Socio socio = listSocios.get(posicao);
        System.out.println(socio);
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir");
        alerta.setIcon(android.R.drawable.ic_dialog_alert);
        alerta.setMessage("Confirma a exclusão de " + socio.getNome() + "? ");
        alerta.setNeutralButton("Não", null);
        alerta.setPositiveButton("Sim", (dialog, which) -> {
            SocioDAO.excluir(MainActivity.this, socio.getMatricula());
            carregarSocios();
        });
        alerta.show();
    }
}