package com.marcosk.agenda;

import android.app.Application;

import com.marcosk.agenda.dao.AlunoDAO;
import com.marcosk.agenda.model.Aluno;

public class AgendaAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AlunoDAO dao = new AlunoDAO();
        criaExemplo(dao);
    }

    private void criaExemplo(AlunoDAO dao) {
        dao.salva(new Aluno(
                "Alex",
                "al@gmail.com",
                "000111"

        ));
        dao.salva(new Aluno(
                "Luana",
                "Lu@gmail.com",
                "000111"

        ));
    }
}
