package pt.isec.tiagodaniel.xadrez.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import pt.isec.tiagodaniel.xadrez.Dialogs.ErrorDialog;
import pt.isec.tiagodaniel.xadrez.Dialogs.IpDialog;
import pt.isec.tiagodaniel.xadrez.Dialogs.OnCompleteListener;
import pt.isec.tiagodaniel.xadrez.Logic.Constantes;
import pt.isec.tiagodaniel.xadrez.Logic.SocketHandler;
import pt.isec.tiagodaniel.xadrez.R;

public class MainActivity extends Activity implements OnCompleteListener, Constantes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.INTERNET}, 1234);
            }

            if (checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 4321);
            }

            if (checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_WIFI_STATE}, 1324);
            }
        }
    }

    public void onJogarContraPC(View v) {
        Intent intent = new Intent(this, JogarContraPCActivity.class);
        intent.setAction(ACTION_JOGvsPC);
        startActivity(intent);
    }

    public void onModo2Jogadores(View v) {
        Intent intent = new Intent(this, Configurar2Jogadores.class);
        intent.setAction(ACTION_JOGvsJOG);
        startActivity(intent);
    }

    public void onCriarJogoOnline(View v) {
        Intent intent = new Intent(this, ConfigurarJogoRedeActivity.class);
        startActivity(intent);
    }

    public void onJuntarJogoOnline(View v) {
        IpDialog ipDialog = new IpDialog(this, getString(R.string.ip_title));
        ipDialog.show(getFragmentManager(), IP_DIALOG);
    }

    public void onCreditos(View v) {
        Intent intent = new Intent(this, CreditosActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = new MenuInflater(this);
        mi.inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuPrincipal) {

            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void connectClient(final String serverIP) {
        Thread t = new Thread(new Runnable() {
            Socket socketGame = null;

            @Override
            public void run() {
                try {
                    socketGame = new Socket();
                    socketGame.connect(new InetSocketAddress(serverIP, SERVER_PORT), TIMEOUT);
                    //socketGame = new Socket(serverIP, SERVER_PORT);
                    SocketHandler.setClientSocket(socketGame);
                } catch (Exception e) {
                    socketGame = null;
                }
                if (socketGame == null) {
                    showError();

                } else {
                    Intent intent = new Intent(getApplicationContext(), JogarContraPCActivity.class);
                    intent.setAction(Constantes.ACTION_JUNTAR_JOGO_REDE);
                    startActivity(intent);
                }
            }
        });
        t.start();
    }

    @Override
    public void onComplete(int code, String tag) {
        switch (code) {
            case IP_OK: {
                this.connectClient(tag);
                break;
            }
        }
    }

    private void showError() {
        ErrorDialog errorDialog = new ErrorDialog(this, getString(R.string.error_juntar_jogo_rede));
        errorDialog.show(getFragmentManager(), ERROR_DIALOG);
    }
}
