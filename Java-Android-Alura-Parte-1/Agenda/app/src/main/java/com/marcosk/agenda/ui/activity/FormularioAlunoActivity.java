package com.marcosk.agenda.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.marcosk.agenda.R;
import com.marcosk.agenda.dao.AlunoDAO;
import com.marcosk.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private EditText viewIdNome;
    private EditText viewIdEmail;
    private EditText viewIdtelefone;
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        inicializacaoDasInformacoes();
        configuraBtnSalvar();
    }

    private void configuraBtnSalvar() {
        Button btnSalvar = findViewById(R.id.activity_formulario_aluno_btn_salvar);
        btnSalvar.setOnClickListener(view -> {

            Aluno alunoAdicionado = addAluno();
            salvar(alunoAdicionado);

        });
    }

    private void inicializacaoDasInformacoes() {
        viewIdNome = findViewById(R.id.activity_formulario_aluno_nome);
        viewIdEmail = findViewById(R.id.activity_formulario_aluno_email);
        viewIdtelefone = findViewById(R.id.activity_formulario_aluno_telefone);
    }

    private void salvar(Aluno alunoAdicionado) {
        dao.salva(alunoAdicionado);
        finish();
    }

    @NonNull
    private Aluno addAluno() {
        String nome = viewIdNome.getText().toString();
        String email = viewIdEmail.getText().toString();
        String telefone = viewIdtelefone.getText().toString();

        return new Aluno(nome, email, telefone);
    }
}