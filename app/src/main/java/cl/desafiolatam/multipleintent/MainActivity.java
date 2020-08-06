package cl.desafiolatam.multipleintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    static final String TAG = "AAA";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String currentPhotoPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private void dispatchTakePictureIntent() throws IOException {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            File photoFile = null;
            photoFile = CreatImageFile();
            //Continuar solo si File es creado satisfactoriamente
                    if (photoFile!=null){
                        Uri photoUri = FileProvider.getUriForFile(this, "desafiolatam.multipleIntent", photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
        }
    }

    private File CreatImageFile() throws IOException{
            String momento = new SimpleDateFormat("yyyymmdd hhmmss").format(new Date());
            String nombrePhoto = "JPEG " + momento + " ";
            File ubicacion = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File imagen = File.createTempFile(nombrePhoto, "JEPG", ubicacion);

            currentPhotoPath = imagen.getAbsolutePath();
            return imagen;
        }

    }


