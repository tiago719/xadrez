package pt.isec.tiagodaniel.xadrez.Logic;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import pt.isec.tiagodaniel.xadrez.Exceptions.NullSharedPreferencesException;
import pt.isec.tiagodaniel.xadrez.R;

/**
 * Created by drmoreira on 28-12-2017.
 */

public class Ferramentas {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Activity mActivity;

    public Ferramentas(Activity activity) throws NullSharedPreferencesException {
        this.mActivity = activity;
        try {
            this.mSharedPreferences = mActivity.getSharedPreferences(Constantes.PREFERENCES, Context.MODE_PRIVATE);
        } catch (Exception e) {
            throw new NullSharedPreferencesException(activity);
        }
    }

    /**
     * Coloca a imagem na ImageView
     *
     * @param mImageView        ImageView onde se pretende colocar a imagem
     * @param mCurrentPhotoPath caminho para a imagem
     */
    public void setPic(ImageView mImageView, String mCurrentPhotoPath) {
        if (mCurrentPhotoPath.equalsIgnoreCase(Constantes.PHOTO_NOT_FOUND)) {
            mImageView.setBackgroundResource(R.drawable.b_peao);
        } else {
            // Get the dimensions of the View
            int targetW = mImageView.getWidth() <= 0 ? 300 : mImageView.getWidth();
            int targetH = mImageView.getHeight() <= 0 ? 300 : mImageView.getHeight();
            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions); // existem outros. Ex: decodeStream
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;
            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;
            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
            mImageView.setImageBitmap(bitmap); //em alternativa retornar apenas o Bitmap
        }
    }

    public void saveName(String mName) {
        this.mEditor = this.mSharedPreferences.edit();
        this.mEditor.putString(this.mActivity.getString(R.string.saved_name), mName);
        this.mEditor.commit();
    }

    public void saveFoto(String path) {
        this.mEditor = this.mSharedPreferences.edit();
        this.mEditor.putString(this.mActivity.getString(R.string.saved_photo), path);
        this.mEditor.commit();
    }

    public String getSavedName() {
        return this.mSharedPreferences.getString(this.mActivity.getString(R.string.saved_name), this.mActivity.getString(R.string.perfil_name_hint));
    }

    public String getSavedPhotoPath() {
        return this.mSharedPreferences.getString(this.mActivity.getString(R.string.saved_photo), Constantes.PHOTO_NOT_FOUND);
    }
}