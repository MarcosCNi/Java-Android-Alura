package com.marcosk.agenda.ui.activity;

import static com.marcosk.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.marcosk.agenda.R;
import com.marcosk.agenda.dao.AlunoDAO;
import com.marcosk.agenda.model.Aluno;


public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APP_BAR_ADD_ALUNO = "Adicionar Aluno";
    private static final String TITULO_APP_BAR_EDITA_ALUNO = "Editar Aluno";
    private EditText viewIdNome;
    private EditText viewIdEmail;
    private EditText viewIdtelefone;
    private final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        inicializacaoDasInformacoes();
        carregaInfoAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_aluno_menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaInfoAluno() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_ALUNO)){
            setTitle(TITULO_APP_BAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCamposFormulario();
        }else{
            setTitle(TITULO_APP_BAR_ADD_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCamposFormulario() {
        viewIdNome.setText(aluno.getNome());
        viewIdEmail.setText(aluno.getEmail());
        viewIdtelefone.setText(aluno.getTelefone());
    }


    private void finalizaFormulario() {
        addAluno();
        if (aluno.temIdValido()){
            dao.edita(aluno);
        }else{
            dao.salva(aluno);
        }
        finish();
    }

    private void inicializacaoDasInformacoes() {
        viewIdNome = findViewById(R.id.activity_formulario_aluno_nome);
        viewIdEmail = findViewById(R.id.activity_formulario_aluno_email);
        viewIdtelefone = findViewById(R.id.activity_formulario_aluno_telefone);
    }

    private void addAluno() {
        String nome = viewIdNome.getText().toString();
        String email = viewIdEmail.getText().toString();
        String telefone = viewIdtelefone.getText().toString();

        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);
    }
}