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
public class Peao extends Peca implements Constantes
{
    private boolean primeiroLance=true, foiPrimeiroLance=false;
    
    public Peao(Tabuleiro tabuleiro, Jogador j)
    {
        super(tabuleiro,j, PEAO);
    }

    @Override
    public ArrayList<Posicao> verificaDisponiveisCheck()
    {
        return tabuleiro.peaoVerificaCheck(this);
    }
    
    public ArrayList<Posicao> getDisponiveis(Jogador atual, boolean pc)
    {
        ArrayList<Posicao> disponiveis= tabuleiro.peao(this, pc);

        for (Iterator<Posicao> iterator = disponiveis.iterator(); iterator.hasNext();) {
            Posicao posicao = iterator.next();
            if(atual.ficaEmCheck(posicao, this))
            {
                iterator.remove();
            }
        }
        return disponiveis;
    }

    public boolean isFoiPrimeiroLance()
    {
        return foiPrimeiroLance;
    }

    public void setFoiPrimeiroLance(boolean foiPrimeiroLance)
    {
        this.foiPrimeiroLance = foiPrimeiroLance;
    }

    public boolean isPrimeiroLance()
    {
        return primeiroLance;
    }

    public void setPrimeiroLance(boolean primeiroLance)
    {
        this.primeiroLance = primeiroLance;
    }

    @Override
    public void desenhaPeca(ImageView childAt)
    {
        if(jogador instanceof JogadorLight)
            childAt.setImageResource(R.drawable.b_peao);
        else
            childAt.setImageResource(R.drawable.p_peao);
    }
}
