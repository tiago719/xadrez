package pt.isec.tiagodaniel.xadrez.Activities;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import pt.isec.tiagodaniel.xadrez.Logic.GameModel;
import pt.isec.tiagodaniel.xadrez.Logic.Peca;
import pt.isec.tiagodaniel.xadrez.Logic.Posicao;
import pt.isec.tiagodaniel.xadrez.R;
import pt.isec.tiagodaniel.xadrez.States.EstadoEscolheDestino;
import pt.isec.tiagodaniel.xadrez.States.EstadoEscolhePeca;

public class JogarContraPCActivity extends Activity {
    private LinearLayout ll;
    private GameModel gameModel;
    private ArrayList<Posicao> posicoesDisponiveisAnteriores = null;
    private Resources resources;
    private ImageView Check;
    private Posicao reiCheck;

    public ImageView getCheck()
    {
        return Check;
    }

    public void setCheck(ImageView check)
    {
        Check = check;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogar_contra_pc);

        ll = findViewById(R.id.tabuleiro);
        Check=null;
        reiCheck=null;

        //tabuleiro=new Tabuleiro(ll);
        this.gameModel = new GameModel(this.ll, this);

        this.posicoesDisponiveisAnteriores = new ArrayList<>();
        resources=getResources();
    }

    public void onClickQuadrado(View v) {
        ArrayList<Posicao> posicoesDisponiveis = new ArrayList<>();
        String res = getResources().getResourceEntryName(v.getId());
        ImageView iv;
        Peca pecaClick;
        Posicao posicaoDestino;

        int linha = Character.getNumericValue(res.charAt(1));
        char coluna = res.charAt(0);

        gameModel.seguinte(linha, coluna);
    }

    public void setPosicoesJogaveis(ArrayList<Posicao> posicoesDisponiveis)
    {
        ImageView iv;
        for (Posicao p : posicoesDisponiveis) {
            iv = findViewById(resources.getIdentifier("" + p.getColuna() + p.getLinha(), "id", getBaseContext().getPackageName()));
            iv.setBackgroundColor(Color.BLACK);
            this.posicoesDisponiveisAnteriores.add(p);
        }
    }

    public void resetPosicoesDisponiveisAnteriores() {
        ImageView pecaImageView;
        for (Posicao posicao : this.posicoesDisponiveisAnteriores) {
            pecaImageView = findViewById(getResources().getIdentifier("" + posicao.getColuna()
                    + posicao.getLinha(), "id", getBaseContext().getPackageName()));

            resetCor(posicao, pecaImageView);
        }
    }

    public void resetCor(Posicao posicao, ImageView pecaImageView)
    {
        if ((int) posicao.getColuna() % 2 != 0) {
            if (posicao.getLinha() % 2 != 0) {
                pecaImageView.setBackgroundColor(getResources().getColor(R.color.tabLight));
            } else {
                pecaImageView.setBackgroundColor(getResources().getColor(R.color.tabDark));
            }
        } else {
            if (posicao.getLinha() % 2 != 0) {
                pecaImageView.setBackgroundColor(getResources().getColor(R.color.tabDark));
            } else {
                pecaImageView.setBackgroundColor(getResources().getColor(R.color.tabLight));
            }
        }
    }

    public void setReiCheck(Posicao PosicaoRei)
    {
        if(Check!=null)
            resetCheck();
        reiCheck=PosicaoRei;
        Check=findViewById(resources.getIdentifier("" + PosicaoRei.getColuna() + PosicaoRei.getLinha(), "id", getBaseContext().getPackageName()));
        Check.setBackgroundColor(Color.RED);
    }

    public void resetCheck()
    {
        if(Check!=null && reiCheck!=null)
            resetCor(reiCheck, Check);
        Check=null;
        reiCheck=null;
    }
}
