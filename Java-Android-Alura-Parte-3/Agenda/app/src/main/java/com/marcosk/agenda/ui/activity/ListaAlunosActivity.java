package com.marcosk.agenda.ui.activity;

import static com.marcosk.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.marcosk.agenda.R;
import com.marcosk.agenda.model.Aluno;
import com.marcosk.agenda.ui.ListaAlunosView;


public class ListaAlunosActivity extends AppCompatActivity {

    private final ListaAlunosView listaAlunosView = new ListaAlunosView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        configuraBtnNovoAluno();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_menu_remover){
            listaAlunosView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    private void abreFormularioAddAluno() {

        startActivity(new Intent(this, FormularioAlunoActivity.class));

    }

    @Override
    protected void onResume() {

        super.onResume();
        listaAlunosView.atualizaLista();

    }

        //Configurações de Cliques

    //Botao para adicionar aluno
    private void configuraBtnNovoAluno() {

        FloatingActionButton btnAddAluno = findViewById(R.id.floatingActionButton);
        btnAddAluno.setOnClickListener(view -> abreFormularioAddAluno());

    }

    //Clique curto por item
    private void configuraListaDeCliquePorItem(ListView studentList) {

        studentList.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Aluno alunoEscolinho = (Aluno) adapterView.getItemAtPosition(posicao);
            abreFormularioEditaAluno(alunoEscolinho);

        });
    }

    private void configuraLista() {

        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listaAlunosView.configuraAdapter(listaAlunos);
        configuraListaDeCliquePorItem(listaAlunos);
        registerForContextMenu(listaAlunos);

    }

    private void abreFormularioEditaAluno(Aluno aluno) {

        Intent vaiFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiFormularioActivity);
    }


}


