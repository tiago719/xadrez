/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isec.tiagodaniel.xadrez.Logic;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Iterator;

import pt.isec.tiagodaniel.xadrez.R;

/**
 *
 * @author Tiago Coutinho
 */
public class Rainha extends Peca implements Constantes
{
    public Rainha(Tabuleiro tabuleiro, Jogador j)
    {
        super(tabuleiro,j, RAINHA);
    }
    
    @Override
    public ArrayList<Posicao> getDisponiveis(Jogador atual, boolean pc)
    {
        ArrayList<Posicao> disponiveis=tabuleiro.horizontalVertival(this, pc);

        disponiveis.addAll(tabuleiro.diagonal(this, pc));

        for (Iterator<Posicao> iterator = disponiveis.iterator(); iterator.hasNext();) {
            Posicao posicao = iterator.next();
            if(atual.ficaEmCheck(posicao, this))
            {
                iterator.remove();
            }
        }
        return disponiveis;
    }

    @Override
    public ArrayList<Posicao> verificaDisponiveisCheck()
    {
        ArrayList<Posicao> disponiveis=tabuleiro.horizontalVertival(this, false);

        try
        {
            disponiveis.addAll(tabuleiro.diagonal(this, false));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return disponiveis;
    }

    @Override
    public void desenhaPeca(ImageView childAt)
    {
        if(jogador instanceof JogadorLight)
            childAt.setImageResource(R.drawable.b_rainha);
        else
            childAt.setImageResource(R.drawable.p_rainha);
    }
}
