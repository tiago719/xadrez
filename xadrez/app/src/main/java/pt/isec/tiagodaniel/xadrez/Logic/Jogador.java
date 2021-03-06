/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isec.tiagodaniel.xadrez.Logic;

import android.os.Handler;
import android.widget.Chronometer;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import static pt.isec.tiagodaniel.xadrez.Logic.Constantes.*;

/**
 *
 * @author Tiago Coutinho
 */
public class Jogador
{
    protected ArrayList<Peca> pecasTabuleiro;
    protected ArrayList<Peca> pecasMortas;
    protected boolean check;
    protected Tabuleiro tabuleiro;

    public Jogador(Tabuleiro t)
    {
        pecasTabuleiro=new ArrayList<Peca>();
        pecasMortas=new ArrayList<Peca>();
        check=false;
        tabuleiro=t;
    }

    public boolean isCheck()
    {
        return check;
    }

    public void setCheck(boolean check)
    {
        this.check = check;
    }

    public ArrayList<Peca> getPecasTabuleiro()
    {
        return pecasTabuleiro;
    }

    public void setPecasTabuleiro(ArrayList<Peca> pecasTabuleiro)
    {
        this.pecasTabuleiro = pecasTabuleiro;
    }

    public void verificaCheck()
    {
        Jogador jogador=tabuleiro.getJogadorAdversarioPeca(pecasTabuleiro.get(0));

        for(Peca peca :jogador.getPecasTabuleiro())
            if(peca.poeCheck(this))
            {
                check = true;
                return;
            }
        check=false;
    }

    public void addPecaMorta(Peca p)
    {
        pecasMortas.add(p);
        pecasTabuleiro.remove(p);
    }

    public void addPeca(Peca p)
    {
        pecasTabuleiro.add(p);
    }
    
    public void joga()
    {
        Comparator<Jogada> comparator = new ComparadorJogadasPC();
        PriorityQueue<Jogada> queue = new PriorityQueue<Jogada>(16, comparator);

        for(Peca peca : pecasTabuleiro)
            for(Posicao posicao : peca.getDisponiveis(this, false))
                queue.add(new Jogada(tabuleiro.encontraPeca(peca), posicao, tabuleiro.getJogadorAtual(), tabuleiro));

        Jogada jogada=queue.remove();

        tabuleiro.movePara(jogada.getPosicaoOriginal(), jogada.getPosicaoDestino(), tabuleiro.getJogadorAtual(), tabuleiro.getJogadorAdversario());
    }

    public boolean ficaEmCheck(Posicao posicao, Peca peca)
    {
        Peca temp=posicao.getPeca();
        Posicao original=tabuleiro.encontraPeca(peca);
        posicao.setPeca(peca);
        original.setPeca(null);

        for(Peca atual : tabuleiro.getOutroJogador(this).getPecasTabuleiro())
        {
            if(atual==temp)
                continue;
            if(atual.poeCheck(this))
            {
                original.setPeca(peca);
                posicao.setPeca(temp);
                return true;
            }
        }
        original.setPeca(peca);
        posicao.setPeca(temp);
        return false;
    }

    public boolean hasMovimentos(Jogador atual)
    {
        for(Peca peca : pecasTabuleiro)
        {
            if(peca.getDisponiveis(atual, false).size()>0)
                return true;
        }
        return false;
    }
}
