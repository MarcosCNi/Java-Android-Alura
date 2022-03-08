package com.marcosk.agenda.ui.activity;

import static com.marcosk.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.marcosk.agenda.R;
import com.marcosk.agenda.dao.AlunoDAO;
import com.marcosk.agenda.model.Aluno;

import java.util.List;


public class ListaAlunosActivity extends AppCompatActivity {

    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        configuraBtnNovoAluno();
        configuraLista();

        dao.salva(new Aluno(
                "Alex",
                "000111",
                "al@gmail.com"
        ));
        dao.salva(new Aluno(
                "Luana",
                "000111",
                "Lu@gmail.com"
        ));

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        CharSequence itemNome = item.getTitle();
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_menu_remover){
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            remove(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
    }

    private void abreFormularioAddAluno() {

        startActivity(new Intent(this, FormularioAlunoActivity.class));

    }

    @Override
    protected void onResume() {

        super.onResume();
        atualizaLista();

    }

    private void atualizaLista() {

        adapter.clear();
        adapter.addAll(dao.todos());

    }

    private void configuraLista() {

        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        configuraAdapter(listaAlunos);
        configuraListaDeCliquePorItem(listaAlunos);
        registerForContextMenu(listaAlunos);

    }

    private void remove(Aluno alunoEscolinho) {

        dao.removeAluno(alunoEscolinho);
        adapter.remove(alunoEscolinho);

    }

    private void abreFormularioEditaAluno(Aluno aluno) {

        Intent vaiFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiFormularioActivity);
    }

    private void configuraAdapter(ListView studentList) {

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        studentList.setAdapter(adapter);

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

}


