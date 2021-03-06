/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isec.tiagodaniel.xadrez.Logic;

import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 *
 * @author Tiago Coutinho
 */
public class Posicao
{
    private int linha;
    private char coluna;
    private Peca peca;
    private ImageView imageView;
    private boolean enPassant, rocado;
    
    public Posicao(int linha, char coluna, ImageView imageView)
    {
        this.linha=linha;
        this.coluna=coluna;
        peca=null;
        this.imageView=imageView;
        enPassant=false;
        rocado=false;
    }
    
    public Posicao(int linha, char coluna, Peca p)
    {
        this.linha=linha;
        this.coluna=coluna;
        peca=p;
    }

    public void desenhaPeca()
    {
        if(peca!=null)
            peca.desenhaPeca(imageView);
    }

    public boolean isEnPassant()
    {
        return enPassant;
    }

    public void setEnPassant(boolean enPassant)
    {
        this.enPassant = enPassant;
    }

    public ImageView getImageView()
    {
        return imageView;
    }

    public void apagaPeca()
    {
        peca=null;
        imageView.setImageDrawable(null);
    }

    public int getLinha()
    {
        return linha;
    }

    public void setLinha(int linha)
    {
        this.linha = linha;
    }

    public char getColuna()
    {
        return coluna;
    }

    public int getColunaNum()
    {
        switch (coluna)
        {
            case 'a':
                return 1;
            case 'b':
                return 2;
            case 'c':
                return 3;
            case 'd':
                return 4;
            case 'e':
                return 5;
            case 'f':
                return 6;
            case 'g':
                return 7;
            case 'h':
                return 8;
        }
      return -1;
    }

    public void setColuna(char coluna)
    {
        this.coluna = coluna;
    }

    public Peca getPeca()
    {
        return peca;
    }

    public void setPeca(Peca peca)
    {
        this.peca = peca;
    }
    
    public boolean isOcupado()
    {
        return peca != null;
    }

    public boolean isRocado()
    {
        return rocado;
    }

    public void setRocado(boolean rocado)
    {
        this.rocado = rocado;
    }

    public void setView(LinearLayout ll)
    {
        imageView=(ImageView)((LinearLayout) ll.getChildAt(8-linha)).getChildAt(converteColuna());
    }

    public int converteColuna()
    {
        switch (coluna)
        {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;

        }

        return 10;
    }
}
