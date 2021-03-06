package pt.isec.tiagodaniel.xadrez.Logic;

import java.io.Serializable;

public class ClientServerMessage implements Serializable {
    static final long serialVersionUID = 1L;
    private String nomeJogador;
    private int linhaOrigem, linhaDestino;
    private char colunaOrigem,colunaDestino;
    private byte[] fotoJogador;
    private boolean isCheck = false;
    private boolean jogoComTempo = false;
    private long tempoMaximo, tempoGanho;
    private boolean flagTrocouPeao;
    private int pecaPromivida;

    public ClientServerMessage() {
        flagTrocouPeao = false;
        pecaPromivida = 0;
    }

    public String getNomeJogador() {
        return this.nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public int getLinhaOrigem() {
        return linhaOrigem;
    }

    public void setLinhaOrigem(int linhaOrigem) {
        this.linhaOrigem = linhaOrigem;
    }

    public int getLinhaDestino() {
        return linhaDestino;
    }

    public void setLinhaDestino(int linhaDestino) {
        this.linhaDestino = linhaDestino;
    }

    public char getColunaOrigem() {
        return colunaOrigem;
    }

    public void setColunaOrigem(char colunaOrigem) {
        this.colunaOrigem = colunaOrigem;
    }

    public char getColunaDestino() {
        return colunaDestino;
    }

    public void setColunaDestino(char colunaDestino) {
        this.colunaDestino = colunaDestino;
    }

    public void setFotoJogador(byte[] fotoJogador) {
        this.fotoJogador = fotoJogador;
    }

    public byte[] getFotoJogador() {
        return this.fotoJogador;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public void resetDados() {
        this.fotoJogador = null;
        this.nomeJogador = "";
    }

    public boolean isJogoComTempo() {
        return jogoComTempo;
    }

    public void setJogoComTempo(boolean jogoComTempo) {
        this.jogoComTempo = jogoComTempo;
    }

    public long getTempoMaximo() {
        return tempoMaximo;
    }

    public void setTempoMaximo(long tempoMaximo) {
        this.tempoMaximo = tempoMaximo;
    }

    public long getTempoGanho() {
        return tempoGanho;
    }

    public void setTempoGanho(long tempoGanho) {
        this.tempoGanho = tempoGanho;
    }

    public boolean isFlagTrocouPeao() {
        return flagTrocouPeao;
    }

    public void setFlagTrocouPeao(boolean flagTrocouPeao) {
        this.flagTrocouPeao = flagTrocouPeao;
    }

    public int getPecaPromivida() {
        return pecaPromivida;
    }

    public void setPecaPromivida(int pecaPromivida) {
        this.pecaPromivida = pecaPromivida;
    }
}