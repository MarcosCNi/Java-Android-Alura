package com.marcosk.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.marcosk.agenda.R;
import com.marcosk.agenda.dao.AlunoDAO;


public class ListaAlunosActivity extends AppCompatActivity {

        private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        configuraBtnNovoAluno();

    }

    private void configuraBtnNovoAluno() {
        FloatingActionButton btnAddAluno = findViewById(R.id.floatingActionButton);
        btnAddAluno.setOnClickListener(view -> {
            abrirFormularioActivity();
        });
    }

    private void abrirFormularioActivity() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        configuraLista();
    }

    private void configuraLista() {
        ListView studentList = findViewById(R.id.activity_lista_alunos_listview);
        studentList.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.todos()));
    }
}
