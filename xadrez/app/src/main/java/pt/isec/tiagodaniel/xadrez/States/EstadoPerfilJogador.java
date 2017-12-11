package pt.isec.tiagodaniel.xadrez.States;

import pt.isec.tiagodaniel.xadrez.Logic.GameModel;

/**
 * Created by drmoreira on 10-12-2017.
 */

public class EstadoPerfilJogador extends StateAdapter {

    public EstadoPerfilJogador(GameModel game) {
        super(game);
    }

    @Override
    public IState seguinte() {
        //Faz o que tem a fazer

        return new EstadoHistoricoJogos(this.getGame());
    }
}
