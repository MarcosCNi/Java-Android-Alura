package com.marcosk.agenda.dao;

import androidx.annotation.Nullable;

import com.marcosk.agenda.model.Aluno;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeId = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeId);
        alunos.add(aluno);
        adicionaNovoId();
    }

    private void adicionaNovoId() {
        contadorDeId ++;
    }

    public void edita(Aluno aluno){
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);
        if (alunoEncontrado != null){
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPeloId(Aluno aluno) {
        for (Aluno a :
                alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void removeAluno(Aluno aluno) {
        Aluno alunoRemovido = buscaAlunoPeloId(aluno);
        if (alunoRemovido != null){
            alunos.remove(alunoRemovido);
        }
    }
}
