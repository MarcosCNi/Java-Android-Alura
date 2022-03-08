package com.marcosk.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.marcosk.agenda.dao.AlunoDAO;
import com.marcosk.agenda.model.Aluno;
import com.marcosk.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final AlunoDAO dao;
    private final ListaAlunosAdapter adapter;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        this.dao = new AlunoDAO();
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Deseja remover o aluno?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                    remove(alunoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaLista() {

        adapter.atualizaLista(dao.todos());

    }

    protected void remove(Aluno alunoEscolinho) {

        dao.removeAluno(alunoEscolinho);
        adapter.remove(alunoEscolinho);

    }

    public void configuraAdapter(ListView studentList) {
        studentList.setAdapter(adapter);
    }

}
