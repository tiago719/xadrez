package pt.isec.tiagodaniel.xadrez.Dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import pt.isec.tiagodaniel.xadrez.Logic.Constantes;
import pt.isec.tiagodaniel.xadrez.R;

/**
 * Created by drmoreira on 29-12-2017.
 */

@SuppressLint("ValidFragment")
public class QuestionDialog extends DialogFragment {
    private String mMessage;
    private String mTitle;
    private OnCompleteListener mListener;
    private String mTag = Constantes.TAG_EMPTY;

    /**
     * Construtor usado quando este dialog é chamado várias vezes para saber o resultado
     * e a tag de quem o invocou
     * @param title
     * @param message
     * @param tag
     */
    public QuestionDialog(OnCompleteListener listener, String title, String message, String tag) {
        this.mTitle = title;
        this.mMessage = message;
        this.mTag = tag;
        this.mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(this.mTitle)
                .setIcon(R.drawable.ic_help_black_24dp)
                .setMessage(this.mMessage)
                .setPositiveButton(getString(R.string.question_positive_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onComplete(Constantes.QUESTION_OK, mTag);
                    }
                })
                .setNegativeButton(getString(R.string.question_negative_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onComplete(Constantes.QUESTION_CANCELAR, mTag);
                    }
                });
        // Create the IpDialog object and return it
        return builder.create();
    }
}