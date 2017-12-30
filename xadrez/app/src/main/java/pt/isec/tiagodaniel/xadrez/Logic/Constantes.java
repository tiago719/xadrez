/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isec.tiagodaniel.xadrez.Logic;

/**
 * @author Tiago Coutinho
 */
public interface Constantes {
    int TABULEIRO_LINHAS = 8;
    int TABULEIRO_COLUNAS = 8;

    int TABULEIRO_COLUNAS_SUP_CHAR_ASCII = 104;
    int TABULEIRO_COLUNAS_INF_CHAR_ASCII = 97;

    String PHOTO_FORMAT = ".jpg";
    String PREFERENCES = "AMOV";
    String PHOTO_NOT_FOUND = "Photo not Found";

    /**Dialogs**/
    String ERROR_DIALOG = "Error Dialog";
    String QUESTION_DIALOG = "Question Dialog";
    String WIN_DIALOG = "Win Dialog";
    String DRAW_DIALOG = "Draw Dialog";
    int QUESTION_OK = 1;
    int QUESTION_CANCELAR = 2;
    int ERROR_OK = 3;

    /**Modos de jogo**/
    int JOGADOR_VS_JOGADOR = 0;
    int JOGADOR_VS_COMPUTADOR = 1;
    int CRIAR_JOGO_REDE = 2;
    int JUNTAR_JOGO_REDE = 3;

    /**Peças**/
    String PEAO = "PEAO";
    String BISPO = "BISPO";
    String CAVALO = "CAVALO";
    String RAINHA = "RAINHA";
    String REI = "REI";
    String TORRE = "TORRE";

    /**Jogadores**/
    String PECAS_BRANCAS = "Peças Brancas";
    String PECAS_PRETAS = "Peças Pretas";
    String PC = "Computador";
    String EMPATE = "Empate";
    String DESISTIU = "Desistiu";

    /**Histórico**/
    String HISTORIC_FILE_NAME = "historico.bin";
    String HISTORICO_DATA = "DATA";
    String HISTORICO_MODO_JOGO1 = "MODOJOGO1";
    String HISTORICO_MODO_JOGO2 = "MODOJOGO2";
    String HISTORICO_VENCEDOR = "VENCEDOR";

    /**Intents**/
    String PUT_EXTRA_JOGADAS = "JOGADAS";

    /**Promocao do Peao**/
    static final int RAINHA_ESCOLHIDA=1;
    static final int BISPO_ESCOLHIDA=2;
    static final int TORRE_ESCOLHIDA=3;
    static final int CAVALO_ESCOLHIDA=4;

}
