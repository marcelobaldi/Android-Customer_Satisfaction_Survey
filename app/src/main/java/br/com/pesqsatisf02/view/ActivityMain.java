//Pacote, Libs, Arquivos, Etc
package br.com.pesqsatisf02.view;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import br.com.pesqsatisf02.R;
import br.com.pesqsatisf02.dao.DaoFirebase;

//Classe View
public class ActivityMain extends AppCompatActivity {

    //Método Inicial
    @Override protected void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);         //View Chamada (Xml)
        setTitle("Menu Administrativo");                //Barra Menu (Título)
    }

    //Botão Comando - Pesquisa Satisfação               //Chama View Pesquisa
    public void btnPesquisa(View view) {
        startActivity(new Intent(this, ActivityPesquisa01.class));
    }

    //Botão Comando - Relatório                         //Chama View Relatório
    public void btnRelatorio(View view) {
        startActivity(new Intent(this, ActivityRelatorio.class));
    }
}






