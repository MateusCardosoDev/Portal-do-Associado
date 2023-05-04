package br.com.appsociotorcedor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.Calendar;

public class FormularioTorcedorActivity extends AppCompatActivity {

    private EditText etNome, etEmail, etData;
    private RadioGroup modalidades;
    private RadioButton modalidadeSelecionada;
    private Button btnSalvar;
    private Socio socio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etData = findViewById(R.id.etData);
        modalidades = findViewById(R.id.radioModalidades);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(view -> salvar());

        etData.setInputType(InputType.TYPE_NULL);
        etData.setOnClickListener(v -> {
            // calender class's instance and get current date , month and year from calender
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
            // date picker dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(FormularioTorcedorActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        // set day of month , month and year value in the edit text
                        etData.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });
    }

    private void salvar() {
        modalidadeSelecionada = findViewById(modalidades.getCheckedRadioButtonId());
        String nome = etNome.getText().toString();
        String dataNasc = etData.getText().toString();
        String email = etEmail.getText().toString();
        String dataNascimento = etData.getText().toString();
        if (nome.isEmpty()) {
            Toast.makeText(this,
                    "O campo nome deve ser preenchido!",
                    Toast.LENGTH_LONG
            ).show();
        } else if (email.isEmpty()) {
            Toast.makeText(this, "O campo E-mail deve ser preenchido!",
                    Toast.LENGTH_LONG
            ).show();
        } else if (dataNascimento.isEmpty()) {
            Toast.makeText(this, "O campo Nascimento deve ser preenchido!",
                    Toast.LENGTH_LONG
            ).show();
        } else if (modalidadeSelecionada == null) {
            Toast.makeText(this, "Você deve selecionar uma modalidade!",
                    Toast.LENGTH_LONG
            ).show();
        } else {
            Modalidade modalidade = Modalidade.valueOf(modalidadeSelecionada.getText().toString().toUpperCase());

            socio = new Socio(nome, email, dataNasc, modalidade);

            SocioDAO.inserir(this, socio);
            etNome.setText("");
            etEmail.setText("");
            etData.setText("");
            modalidades.clearCheck();
            socio = null;
        }
    }
}