//Pacote, Libs, Arquivos, Etc
package br.com.pesqsatisf02.view;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import br.com.pesqsatisf02.R;
import br.com.pesqsatisf02.dao.DaoFirebase;
import br.com.pesqsatisf02.helper.Helper;
import br.com.pesqsatisf02.model.Pesquisa;

//Classe View
public class ActivityPesquisa02 extends AppCompatActivity {
    //Variáveis - Classe (Classe e Model)
    private Helper              helper = new Helper();      //Suporte (Data, Hora, BD Firebase)
    private Pesquisa            pesquisa;                   //Model   (Dados Tela Anterior)

    //Variáveis - Classe (Compos Desta Tela)
    private String              conheceu = "";              //Variável (Onde Nos Conheceu);
    private EditText            observacoes;                //Variável (Observações Gerais);
    private RadioButton         tv, jornal, face,
                                midias, amigos, outros;     //Campos Desta Tela
    //Firebase
    private DaoFirebase         daoFirebase = new DaoFirebase();
    private DatabaseReference   databaseReference;

    //Método Inicial
    @Override protected void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);
        //View e Barra Menu
        setContentView(R.layout.activity_pesquisa02);       //View Chamada (Xml)
        setTitle("Pesquisa Satisfação - Pag.2/2");          //Barra Menu (Título)

        //Botões Opção (View)
        tv          = findViewById(R.id.rd_tv_radio);
        jornal      = findViewById(R.id.rd_jornal_revista);
        face        = findViewById(R.id.rd_facebook_google);
        midias      = findViewById(R.id.rd_midias);
        amigos      = findViewById(R.id.rd_amigos);
        outros      = findViewById(R.id.rd_outros);
        observacoes = findViewById(R.id.edt_observacoes);

        //Pegar Dados Tela Anterior
        pesquisa = getIntent().getParcelableExtra("PESQUISA01");
    }

    //Botão Opção - Pegar Valor Real Time
    public void botaoOpcaoSelecionado (View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.rd_tv_radio:
                if (checked)
                    conheceu = "tv-radio";
                    tv.setChecked(true);
                    jornal.setChecked(false);
                    face.setChecked(false);
                    midias.setChecked(false);
                    amigos.setChecked(false);
                    outros.setChecked(false);
                    break;
            case R.id.rd_jornal_revista:
                if (checked)
                    conheceu = "jornal-revista";
                    jornal.setChecked(true);
                    tv.setChecked(false);
                    face.setChecked(false);
                    midias.setChecked(false);
                    amigos.setChecked(false);
                    outros.setChecked(false);
                    break;
            case R.id.rd_facebook_google:
                if (checked)
                    conheceu = "facebook-google";
                    face.setChecked(true);
                    jornal.setChecked(false);
                    tv.setChecked(false);
                    midias.setChecked(false);
                    amigos.setChecked(false);
                    outros.setChecked(false);
                    break;
            case R.id.rd_midias:
                if (checked)
                    conheceu = "midias-digitais";
                    midias.setChecked(true);
                    face.setChecked(false);
                    jornal.setChecked(false);
                    tv.setChecked(false);
                    amigos.setChecked(false);
                    outros.setChecked(false);
                    break;
            case R.id.rd_amigos:
                if (checked)
                    conheceu = "amigos";
                    amigos.setChecked(true);
                    midias.setChecked(false);
                    face.setChecked(false);
                    jornal.setChecked(false);
                    tv.setChecked(false);
                    outros.setChecked(false);
                    break;
            case R.id.rd_outros:
                if (checked)
                    conheceu = "outros";
                    outros.setChecked(true);
                    amigos.setChecked(false);
                    midias.setChecked(false);
                    face.setChecked(false);
                    jornal.setChecked(false);
                    tv.setChecked(false);
                    break;
        }
    }

    //Botão Comando - Enviar Pesquisa
    public void btnEnviar (View view){
        String obsST  = observacoes.getText().toString();

        if(conheceu.isEmpty()) {
            String msg = "Como Nos Conheceu?";
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
        } else {
            //Dados Primeira Tela, Segunda Tela, Data e Hora
            pesquisa = new Pesquisa(pesquisa.getAtendimento(),pesquisa.getCardapio(), pesquisa.getTempoEspera(),
                                    pesquisa.getAmbienteLimpeza(), conheceu, obsST, helper.dataAtual(), helper.horaAtual() );

            //Enviar Firebase
            FirebaseDatabase  fireDados  = FirebaseDatabase.getInstance();
            DatabaseReference fireSatisf = fireDados.getReference().child("NovaBella").child("PesquisaSatisfacao").push();
            fireSatisf.setValue(pesquisa)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ActivityPesquisa02.this, "Pesquisa Registrada", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ActivityPesquisa02.this, "Falha ao Registrar", Toast.LENGTH_SHORT).show();
                        }
                    });

            //Mensagem ao Usuário
            String msg = "Muito Obrigado Pela Avaliação";
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

            finish(); //fecha a tela atual;
            Intent intent = new Intent (getBaseContext(), ActivityMain.class);            //volta para atela com dados zerados
            startActivity(intent);
        }
    }

    //Botão Voltar da Barra de Menu de Navegação
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getBaseContext(), ActivityPesquisa01.class));
    }
}



