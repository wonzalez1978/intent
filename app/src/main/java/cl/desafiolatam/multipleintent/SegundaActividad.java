package cl.desafiolatam.multipleintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SegundaActividad extends AppCompatActivity {
    static final String TAG = "AAA";
    static final int INTENT_MSG = 1;
    static final int INTENT_WEB = 2;
    protected String textoUrl;
    protected boolean imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividadsegunda);
        Log.d(TAG, "en ResultsActivity en onCreate");
        Bundle datosAnteriores = getIntent().getExtras();
        Log.d(TAG, "en ResultsActivity en onCreate "+datosAnteriores.get("imagen"));
        this.imagen = (boolean) datosAnteriores.get("imagen");
        Log.d(TAG, "en ResultsActivity en onCreate "+datosAnteriores.get("Url"));
        this.textoUrl = (String) datosAnteriores.get("Url");
        Button btnMsg = findViewById(R.id.boton_msg);
        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMsg();
            }
        });
        Button btnWeb = findViewById(R.id.boton_web);
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarWeb();
            }
        });
    }

    private void enviarWeb(){
        Log.d(TAG, "en ResultsActivity en enviarWeb");
        Uri uri = Uri.parse(textoUrl);
        Intent web = new Intent(Intent.ACTION_VIEW, uri);
        startActivityForResult(web, INTENT_WEB);
    }

    private void enviarMsg(){
        Log.d(TAG, "en ResultsActivity en enviarMsg");
        Intent msg = new Intent(Intent.ACTION_SEND);
        msg.putExtra(Intent.EXTRA_TEXT, textoUrl);
        msg.setType("text/plain");
        startActivityForResult(msg, INTENT_MSG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG, "en ResultsActivity en onActivityResult");
        if(requestCode == INTENT_WEB){
            Toast.makeText(this, "Se activó el Intet a página web", Toast.LENGTH_SHORT).show();
        }else if(requestCode == INTENT_MSG){
            Toast.makeText(this, "Se activó el Intet a msg de texto", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Se activó algo desconocido", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
