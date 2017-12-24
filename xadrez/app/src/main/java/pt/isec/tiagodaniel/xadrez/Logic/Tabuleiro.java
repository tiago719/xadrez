/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isec.tiagodaniel.xadrez.Logic;

import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import static pt.isec.tiagodaniel.xadrez.Logic.Constantes.*;

/**
 *
 * @author Tiago Coutinho
 */
public class Tabuleiro
{
    private ArrayList<Posicao> tabuleiro;
    private ArrayList<Jogador> jogadores;
    private Jogador jogadorAtual;
    private Jogador jogadorAdversario;
    
    public Tabuleiro(LinearLayout ll)
    {
        tabuleiro=new ArrayList<Posicao>();
        
        for(int i=1;i<=TABULEIRO_LINHAS;i++)
        {
            for(int j=0;j<TABULEIRO_COLUNAS;j++)
            {
                tabuleiro.add(new Posicao(i,(char)('a'+j), (ImageView)((LinearLayout) ll.getChildAt(8-i)).getChildAt(j)));
            }
        }
        jogadores=new ArrayList<Jogador>();
        jogadores.add(new JogadorDark(this));
        jogadores.add(new JogadorLight(this));
        jogadorAtual=jogadores.get(1);
        jogadorAdversario=jogadores.get(0);
    }
    
    public boolean dentroLimites(int linha, char coluna)
    {
        if(linha > TABULEIRO_LINHAS || linha<0 || (int)coluna >TABULEIRO_COLUNAS_SUP_CHAR_ASCII || (int)coluna <TABULEIRO_COLUNAS_INF_CHAR_ASCII)
            return false;
        return true;
    }
    
    public boolean isOcupado(Posicao p)
    {
        return p.isOcupado();
    }
    
    public boolean isOcupado(int linha, char coluna)
    {
        if(!dentroLimites(linha, coluna))
            return true;
        
        for(Posicao p : tabuleiro)
        {
            if(p.getLinha()==linha && p.getColuna()==coluna)
                return p.isOcupado();
        }
        System.out.println("DEU ERRO!");
        return true;
    }
    
    public Posicao getPosicao(int linha, char coluna)
    {
        if(!dentroLimites(linha, coluna))
            return null;
        
        for(Posicao p : tabuleiro)
        {
            if(p.getLinha()==linha && p.getColuna()==coluna)
                return p;
        }
        return null;
    }
    
    public boolean podeComer(int linha, char coluna, Jogador j)
    {
        Posicao p;
        if((p=getPosicao(linha, coluna))==null)
            return false;
            
        return p.isOcupado() && p.getPeca().getJogador()!=j;
    }
    
    public void setCheck()
    {
        for(Jogador j : jogadores)
        {
            if(j.poeEmCheck())
                j.setCheck(true);
            else
                j.setCheck(false);
        }
    }

    public void adiciona(ArrayList<Posicao> disponiveis, Posicao nova)
    {
        if(!nova.isOcupado())
            disponiveis.add(nova);
        else if(this.podeComer(nova.getLinha(), nova.getColuna(), this.jogadorAtual))
            disponiveis.add(nova);
    }
    public boolean ultima(ArrayList<Posicao> disponiveis, Posicao nova)
    {
        if(nova==null)
            return false;

        if(!nova.isOcupado())
        {
            disponiveis.add(nova);
            return false;
        }
        else
        {
            if(this.podeComer(nova.getLinha(), nova.getColuna(), this.jogadorAtual))
                disponiveis.add(nova);
            return true;
        }
    }

    public Posicao encontraPeca(Peca peca)
    {
        for(Posicao posicao : tabuleiro)
        {
            if(posicao.getPeca()==peca)
                return posicao;
        }
        return null;
    }

    public ArrayList<Posicao> horizontalVertival(Peca peca)
    {
        Posicao posicao=encontraPeca(peca);
        ArrayList<Posicao> disponiveis=new ArrayList<Posicao>();
        Posicao nova;

        for(int i=1;;i++)
        {
            nova=this.getPosicao(posicao.getLinha(),(char) (posicao.getColuna()+i));
            if(nova==null)
                break;
            if(ultima(disponiveis,nova))
                break;
        }
        for(int i=-1;;i--)
        {
            nova=this.getPosicao(posicao.getLinha(), (char) (posicao.getColuna()+i));
            if(nova==null)
                break;
            if(ultima(disponiveis,nova))
                break;
        }

        for(int i=1;;i++)
        {
            nova=this.getPosicao(posicao.getLinha()+i,(char) (posicao.getColuna()));
            if(nova==null)
                break;
            if(ultima(disponiveis,nova))
                break;
        }

        for(int i=-1;;i--)
        {
            nova=this.getPosicao(posicao.getLinha()+i,(char) (posicao.getColuna()));
            if(nova==null)
                break;
            if(ultima(disponiveis,nova))
                break;
        }

        return disponiveis;
    }

    public ArrayList<Posicao> cavalo(Peca peca)
    {
        ArrayList<Posicao> disponiveis=new ArrayList<Posicao>();
        Posicao p=encontraPeca(peca), nova;

        if((nova=this.getPosicao(p.getLinha()+2,(char) (p.getColuna()+1)))!=null)
            adiciona(disponiveis, nova);

        if((nova=this.getPosicao(p.getLinha()+2,(char) (p.getColuna()-1)))!=null)
            adiciona(disponiveis, nova);

        if((nova=this.getPosicao(p.getLinha()-2,(char) (p.getColuna()+1)))!=null)
            adiciona(disponiveis, nova);

        if((nova=this.getPosicao(p.getLinha()-2,(char) (p.getColuna()-1)))!=null)
            adiciona(disponiveis, nova);

        if((nova=this.getPosicao(p.getLinha()+1,(char) (p.getColuna()-2)))!=null)
            adiciona(disponiveis, nova);

        if((nova=this.getPosicao(p.getLinha()+1,(char) (p.getColuna()+2)))!=null)
            adiciona(disponiveis, nova);

        if((nova=this.getPosicao(p.getLinha()-1,(char) (p.getColuna()-2)))!=null)
            adiciona(disponiveis, nova);

        if((nova=this.getPosicao(p.getLinha()-1,(char) (p.getColuna()+2)))!=null)
            adiciona(disponiveis, nova);

        return disponiveis;
    }

    public ArrayList<Posicao> peao(Peao peca)
    {
        ArrayList<Posicao> disponiveis=new ArrayList<Posicao>();
        Posicao p=encontraPeca(peca);
        Posicao nova;

        if(jogadorAtual instanceof JogadorLight)
        {
            if ((nova = this.getPosicao(p.getLinha() + 1, (char) (p.getColuna()))) != null)
                if (!nova.isOcupado()) disponiveis.add(nova);

            if (peca.isPrimeiroLance())
                if ((nova = this.getPosicao(p.getLinha() + 2, (char) (p.getColuna()))) != null)
                    if (!nova.isOcupado()) disponiveis.add(nova);

            if ((nova = this.getPosicao(p.getLinha() + 1, (char) (p.getColuna() + 1))) != null)
                if (this.podeComer(nova.getLinha(), nova.getColuna(), jogadorAtual)) disponiveis.add(nova);

            if ((nova = this.getPosicao(p.getLinha() + 1, (char) (p.getColuna() - 1))) != null)
                if (this.podeComer(nova.getLinha(), nova.getColuna(), jogadorAtual)) disponiveis.add(nova);

            if((nova=this.getPosicao(p.getLinha(), (char)(p.getColuna()+1)))!=null)
                if(this.podeComer(nova.getLinha(), nova.getColuna(), jogadorAtual) && peca.isFoiPrimeiroLance())
                    if(dentroLimites(nova.getLinha()+1, (char)(nova.getColuna()+1)))
                    {
                        disponiveis.add(getPosicao(nova.getLinha() + 1, (char) (nova.getColuna() + 1)));
                        nova.setEnPassant(true);
                    }

            if((nova=this.getPosicao(p.getLinha(), (char)(p.getColuna()-1)))!=null)
                if(this.podeComer(nova.getLinha(), nova.getColuna(), jogadorAtual) && peca.isFoiPrimeiroLance())
                    if(dentroLimites(nova.getLinha()+1,nova.getColuna()))
                    {
                        disponiveis.add(getPosicao(nova.getLinha() + 1, nova.getColuna()));
                        nova.setEnPassant(true);
                    }
        }
        else
        {
            if ((nova = this.getPosicao(p.getLinha() - 1, (char) (p.getColuna()))) != null)
                if (!nova.isOcupado()) disponiveis.add(nova);

            if (peca.isPrimeiroLance())
                if ((nova = this.getPosicao(p.getLinha() - 2, (char) (p.getColuna()))) != null)
                    if (!nova.isOcupado()) disponiveis.add(nova);

            if ((nova = this.getPosicao(p.getLinha() - 1, (char) (p.getColuna() + 1))) != null)
                if (this.podeComer(nova.getLinha(), nova.getColuna(), this.jogadorAtual))
                    disponiveis.add(nova);

            if ((nova = this.getPosicao(p.getLinha() - 1, (char) (p.getColuna() - 1))) != null)
                if (this.podeComer(nova.getLinha(), nova.getColuna(), this.jogadorAtual))
                    disponiveis.add(nova);

            if ((nova = this.getPosicao(p.getLinha(), (char) (p.getColuna() + 1))) != null)
                if (this.podeComer(nova.getLinha(), nova.getColuna(), jogadorAtual) && peca.isFoiPrimeiroLance())
                    if (dentroLimites(nova.getLinha() - 1,nova.getColuna()))
                    {
                        disponiveis.add(getPosicao(nova.getLinha() - 1, nova.getColuna()));
                        nova.setEnPassant(true);
                    }

            if ((nova = this.getPosicao(p.getLinha(), (char) (p.getColuna() - 1))) != null)
                if (this.podeComer(nova.getLinha(), nova.getColuna(), jogadorAtual) && peca.isFoiPrimeiroLance())
                    if (dentroLimites(nova.getLinha() - 1, (char) (nova.getColuna())))
                    {
                        disponiveis.add(getPosicao(nova.getLinha() - 1, nova.getColuna()));
                        nova.setEnPassant(true);
                    }
        }
        return disponiveis;
    }

    public int getLinhaTorreOriginal()
    {
        if(jogadorAtual instanceof JogadorLight)
            return 1;
        else
            return 8;
    }

    public ArrayList<Posicao> rei(Peca rei)
    {
        ArrayList<Posicao> disponiveis=new ArrayList<Posicao>();
        Posicao p=encontraPeca(rei), nova;

        if((nova=this.getPosicao(p.getLinha(),(char) (p.getColuna()+1)))!=null)
            adiciona(disponiveis, nova);

        if((nova=this.getPosicao(p.getLinha(),(char) (p.getColuna()-1)))!=null)
            adiciona(disponiveis, nova);

        if((nova=this.getPosicao(p.getLinha()+1,(char) (p.getColuna()-1)))!=null)
            adiciona(disponiveis, nova);

        if((nova=this.getPosicao(p.getLinha()+1,(char) (p.getColuna()+1)))!=null)
            adiciona(disponiveis, nova);

        if((nova=this.getPosicao(p.getLinha()+1,(char) (p.getColuna())))!=null)
            adiciona(disponiveis, nova);

        if((nova=this.getPosicao(p.getLinha()-1,(char) (p.getColuna()+1)))!=null)
            adiciona(disponiveis, nova);

        if((nova=this.getPosicao(p.getLinha()-1,(char) (p.getColuna()-1)))!=null)
            adiciona(disponiveis, nova);

        if((nova=this.getPosicao(p.getLinha()-1,(char) (p.getColuna())))!=null)
            adiciona(disponiveis, nova);

        if(((Rei)rei).isMovido())
            return disponiveis;

        for(Peca pecaJogador : jogadorAtual.getPecasTabuleiro())
            if(pecaJogador instanceof Torre)
            {
                //TODO: pagina 8: 3.8 -> c -> 2 -> i
                for(int i=1;i<TABULEIRO_COLUNAS && encontraPeca(pecaJogador).getLinha()==getLinhaTorreOriginal();i++)
                {
                    if ((char) (encontraPeca(pecaJogador).getColuna() + i) == encontraPeca(rei).getColuna())
                    {
                        disponiveis.add(getPosicao(encontraPeca(pecaJogador).getLinha(), (char) (encontraPeca((Peca) rei).getColuna() - 2)));
                        encontraPeca(pecaJogador).setRocado(true);
                    }
                    else if (isOcupado(encontraPeca(pecaJogador).getLinha(), (char) (encontraPeca(pecaJogador).getColuna() + i)))
                        break;
                }

                for(int i=1;i<TABULEIRO_COLUNAS && encontraPeca(pecaJogador).getLinha()==getLinhaTorreOriginal();i++)
                {
                    if ((char) (encontraPeca(pecaJogador).getColuna() - i) == encontraPeca(rei).getColuna())
                    {
                        disponiveis.add(getPosicao(encontraPeca(pecaJogador).getLinha(), (char) (encontraPeca((Peca) rei).getColuna() + 2)));
                        encontraPeca(pecaJogador).setRocado(true);
                    }
                    else if (isOcupado(encontraPeca(pecaJogador).getLinha(), (char) (encontraPeca(pecaJogador).getColuna() - i)))
                        break;
                }
            }
        return disponiveis;
    }

    public ArrayList<Posicao> diagonal(Peca peca)
    {
        Posicao posicao=encontraPeca(peca);
        ArrayList<Posicao> disponiveis=new ArrayList<Posicao>();
        Posicao nova;

        for(int i=1;;i++)
        {
            nova=this.getPosicao(posicao.getLinha()+i, (char) (posicao.getColuna()+i));
            if(nova==null)
                break;
            if(ultima(disponiveis,nova))
                break;
        }
        for(int i=-1;;i--)
        {
            nova=this.getPosicao(posicao.getLinha()+i, (char) (posicao.getColuna()+i));
            if(nova==null)
                break;
            if(ultima(disponiveis,nova))
                break;
        }

        for(int i=1;;i++)
        {
            nova=this.getPosicao(posicao.getLinha()+i, (char) (posicao.getColuna()-i));
            if(nova==null)
                break;
            if(ultima(disponiveis,nova))
                break;
        }

        for(int i=-1;;i--)
        {
            nova=this.getPosicao(posicao.getLinha()+i, (char) (posicao.getColuna()-i));
            if(nova==null)
                break;
            if(ultima(disponiveis,nova))
                break;
        }

        return disponiveis;
    }

    public void movePara(Posicao posicaoOrigem, Posicao posicaoDestino)
    {
        Peca peca;
        Posicao novaPosicaoTorre;
        for(Peca p : jogadorAtual.getPecasTabuleiro())
            if(p instanceof Peao)
                ((Peao)p).setFoiPrimeiroLance(false);

        if(posicaoDestino.isOcupado())
        {
            this.jogadorAdversario.addPecaMorta(posicaoDestino.getPeca());
        }
        if((peca=posicaoOrigem.getPeca()) instanceof Peao)
        {
            ((Peao) peca).setPrimeiroLance(false);
            ((Peao) peca).setFoiPrimeiroLance(true);
        }
        else if((peca=posicaoOrigem.getPeca()) instanceof Rei)
        {
            ((Rei) peca).setMovido(true);
        }
        else if((peca=posicaoOrigem.getPeca()) instanceof Torre)
        {
            ((Torre) peca).setMovido(true);
        }

        peca.desenhaPeca(posicaoDestino.getImageView());
        posicaoDestino.setPeca(peca);
        posicaoOrigem.apagaPeca();

        for(Posicao posicao : tabuleiro)
        {
            if (posicao.isEnPassant())
            {
                posicao.setEnPassant(false);
                posicao.apagaPeca();
            }
            if (posicao.isRocado())
            {
                posicao.setRocado(false);
                if (posicao.getPeca() != null)
                {
                    if (posicao.getColuna() - posicaoOrigem.getColuna() > 0)
                    {
                        if(posicao==getPosicao(posicaoDestino.getLinha(), (char) (posicaoDestino.getColuna()+1)))
                        {
                            novaPosicaoTorre = getPosicao(posicaoOrigem.getLinha(), (char) (posicaoOrigem.getColuna() + 1));
                            posicao.getPeca().desenhaPeca(novaPosicaoTorre.getImageView());
                            novaPosicaoTorre.setPeca(posicao.getPeca());
                            posicao.setPeca(null);
                            posicao.apagaPeca();
                        }
                    }
                    else
                    {
                        if(posicao==getPosicao(posicaoDestino.getLinha(), (char) (posicaoDestino.getColuna()-2)))
                        {
                            novaPosicaoTorre = getPosicao(posicaoOrigem.getLinha(), (char) (posicaoOrigem.getColuna() - 1));
                            posicao.getPeca().desenhaPeca(novaPosicaoTorre.getImageView());
                            novaPosicaoTorre.setPeca(posicao.getPeca());
                            posicao.setPeca(null);
                            posicao.apagaPeca();
                        }
                    }
                }
                break;
            }
        }
    }

    public Jogador getJogadorAtual() {
        return this.jogadorAtual;
    }

    public void setJogadorAtual(Jogador jogador) {
        if(jogador != null){
            this.jogadorAtual = jogador;
        }
    }

    public void trocaJogadorActual() {
        Jogador adversario=jogadorAdversario;
        jogadorAdversario=jogadorAtual;
        jogadorAtual=adversario;
    }
}
