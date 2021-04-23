//Pacote, Libs, Arquivos, Etc
package br.com.pesqsatisf02.view;
import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import br.com.pesqsatisf02.R;
import br.com.pesqsatisf02.dao.DaoFirebase;
import static br.com.pesqsatisf02.dao.DaoFirebase.fireChildEventListener;

//Classe View
public class ActivityRelatorio extends AppCompatActivity {
    //Variáveis - Calendário
    private DatePickerDialog datePickerDialog;
    private int year;
    private int month;
    private int dayOfMonth;
    private Calendar calendar;

    //Variáveis - Classe Firebase
    private DaoFirebase  daoFirebase = new DaoFirebase();

    //Variáveis - Caixas Entrada - Período (Data Inicio e Data Final)
    private EditText     edt_dataInicioCalendario, edt_dataFimCalendario;

    //Variáveis - Texto - Resultado
    private TextView     txt_TotQuant, txt_MedAtend, txt_MedCard, txt_MedTemp, txt_MedAmbi;
    private TextView     txt_ResAtend, txt_ResCarda, txt_ResTemp, txt_ResAmbi;

    //Variáveis - Cálculo
    private int          qtTotalAvaliacao  = 0;                                                     //Total - Pesquisas
    private double       notAtedPess=0, notAtedRuim=0, notAtedReg=0, notAtedBom=0, notAtedOtim=0;   //Notas - Atendimento
    private double       notCardPess=0, notCardRuim=0, notCardReg=0, notCardBom=0, notCardOtim=0;   //Notas - Cardápio
    private double       notTempPess=0, notTempRuim=0, notTempReg=0, notTempBom=0, notTempOtim=0;   //Notas - Tempo de Espera
    private double       notAmbiPess=0, notAmbiRuim=0, notAmbiReg=0, notAmbiBom=0, notAmbiOtim=0;   //Notas - Ambiente e Limpeza
    private double       medAtend=0, medCardap=0, medTempo =0, medAmbiente=0;                       //Notas - Média
    private BigDecimal   medAtendArred,medCardapArred, medTempEspArred, medAmbienteArred;           //Notas - Arredondar

    //Método Inicial
    @Override protected void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);
        //View e Barra Menu
        setContentView(R.layout.activity_relatorio);
        setTitle("Relatório da Pesquisa");

        //Objetos da View
        edt_dataInicioCalendario    = findViewById(R.id.dataInicio_Xml);
        edt_dataFimCalendario       = findViewById(R.id.dataFim_Xml);
        txt_TotQuant                = findViewById(R.id.totAvaliacaoXml);
        txt_MedAtend                = findViewById(R.id.mediaAtendXml);
        txt_MedCard                 = findViewById(R.id.mediaCardapXml);
        txt_MedTemp                 = findViewById(R.id.mediaTempoXml);
        txt_MedAmbi                 = findViewById(R.id.mediaAmbienteXml);
        txt_ResAtend                = findViewById(R.id.resultAtendXml);
        txt_ResCarda                = findViewById(R.id.resultCardapXml);
        txt_ResTemp                 = findViewById(R.id.resultTempoXml);
        txt_ResAmbi                 = findViewById(R.id.resultAmbienteXml);

        //Calendário - Data de Inicio
        edt_dataInicioCalendario.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(ActivityRelatorio.this, new DatePickerDialog.OnDateSetListener() {
            @Override public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String dia = "", mes="";
                if(day < 10){
                    dia = String.format("0%d", day);                                //!!? %d  %2f
                } else{
                    dia = String.valueOf(day);
                }
                if(month < 10){
                    mes = String.format("0%d", month+1);
                } else{
                    mes = String.valueOf(month);
                }
                edt_dataInicioCalendario.setText(dia + "/" + mes + "/" + year);
            }}, year, month, dayOfMonth);
            datePickerDialog.show();
            }
        });

        //Calendário - Data de Término
        edt_dataFimCalendario.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(ActivityRelatorio.this, new DatePickerDialog.OnDateSetListener() {
            @Override public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String dia = "", mes="";
                if(day < 10){
                    dia = String.format("0%d", day);                                //!!? %d  %2f
                } else{
                    dia = String.valueOf(day);
                }
                if(month < 10){
                    mes = String.format("0%d", month+1);
                } else{
                    mes = String.valueOf(month);
                }
                edt_dataFimCalendario.setText(dia + "/" + mes + "/" + year);
            }}, year, month, dayOfMonth);
            datePickerDialog.show();
            }
        });
    }

    //Botão Comando - Pesquisar Resultado
    public void btnBuscarPesquisa (View view){
        //Limpar Objetos (quando clicar novamente no botão)
        qtTotalAvaliacao  = 0;
        medAtend=0; medCardap=0; medTempo =0; medAmbiente=0;
        notAtedPess=0; notAtedRuim=0; notAtedReg=0; notAtedBom=0; notAtedOtim=0;
        notCardPess=0; notCardRuim=0; notCardReg=0; notCardBom=0; notCardOtim=0;
        notTempPess=0; notTempRuim=0; notTempReg=0; notTempBom=0; notTempOtim=0;
        notAmbiPess=0; notAmbiRuim=0; notAmbiReg=0; notAmbiBom=0; notAmbiOtim=0;

        txt_TotQuant.setText("0"); txt_MedAtend.setText("0"); txt_MedCard.setText("0"); txt_MedTemp.setText("0");txt_MedAmbi.setText("0");
        txt_ResAtend.setText(""); txt_ResCarda.setText(""); txt_ResTemp.setText(""); txt_ResAmbi.setText("");

        relatorioSatisfacao();
    }

    //Método - Calcular Resultado
    public void relatorioSatisfacao(){
        daoFirebase.getNoPesquisa();                                                                //Nó Que Irá Buscar
        fireChildEventListener  = new ChildEventListener() {                                        //Tipo de Busca
        @Override public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            String dataPesquisaS = dataSnapshot.child( "dataAtual" ).getValue().toString();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date data = formatter.parse( dataPesquisaS );           //parse exige o tryCatch

                Calendar after = Calendar.getInstance();
                after.setTime(formatter.parse(edt_dataInicioCalendario.getText().toString()));
                after.add(Calendar.DATE, -1);                   //considerar o próprio dia (senão o "if" não considera)

                Calendar before = Calendar.getInstance();
                before.setTime(formatter.parse(edt_dataFimCalendario.getText().toString()));
                before.add(Calendar.DATE, 1);                   //considerar o próprio dia (senão o "if" não considera)

                if (data.after(after.getTime()) && data.before(before.getTime())){
                    //Somar Quantidades de Pesquisas
                    qtTotalAvaliacao++;

                    //Pegar Valores dos Campos
                    String atendiS  = dataSnapshot.child("atendimento").getValue().toString();
                    String cardapS  = dataSnapshot.child("cardapio").getValue().toString();
                    String tempEsS  = dataSnapshot.child("tempoEspera").getValue().toString();
                    String ambLimS  = dataSnapshot.child("ambienteLimpeza").getValue().toString();

                    //Somar Valores de Cada Campo(tem que entrar em todos os ifs. poderia ser o swith
                    if(atendiS.equalsIgnoreCase("Pessimo"))   notAtedPess = notAtedPess + 1;
                    if(atendiS.equalsIgnoreCase("Ruim"))      notAtedRuim = notAtedRuim + 2;
                    if(atendiS.equalsIgnoreCase("Regular"))   notAtedReg  = notAtedReg  + 3;
                    if(atendiS.equalsIgnoreCase("Bom"))       notAtedBom  = notAtedBom  + 4;
                    if(atendiS.equalsIgnoreCase("Otimo"))     notAtedOtim = notAtedOtim + 5;

                    if(cardapS.equalsIgnoreCase("Pessimo"))   notCardPess = notCardPess + 1;
                    if(cardapS.equalsIgnoreCase("Ruim"))      notCardRuim = notCardRuim + 2;
                    if(cardapS.equalsIgnoreCase("Regular"))   notCardReg  = notCardReg  + 3;
                    if(cardapS.equalsIgnoreCase("Bom"))       notCardBom  = notCardBom  + 4;
                    if(cardapS.equalsIgnoreCase("Otimo"))     notCardOtim = notCardOtim + 5;

                    if(tempEsS.equalsIgnoreCase("Pessimo"))   notTempPess = notTempPess + 1;
                    if(tempEsS.equalsIgnoreCase("Ruim"))      notTempRuim = notTempRuim + 2;
                    if(tempEsS.equalsIgnoreCase("Regular"))   notTempReg  = notTempReg  + 3;
                    if(tempEsS.equalsIgnoreCase("Bom"))       notTempBom  = notTempBom  + 4;
                    if(tempEsS.equalsIgnoreCase("Otimo"))     notTempOtim = notTempOtim + 5;

                    if(ambLimS.equalsIgnoreCase("Pessimo"))   notAmbiPess = notAmbiPess + 1;
                    if(ambLimS.equalsIgnoreCase("Ruim"))      notAmbiRuim = notAmbiRuim + 2;
                    if(ambLimS.equalsIgnoreCase("Regular"))   notAmbiReg  = notAmbiReg  + 3;
                    if(ambLimS.equalsIgnoreCase("Bom"))       notAmbiBom  = notAmbiBom  + 4;
                    if(ambLimS.equalsIgnoreCase("Otimo"))     notAmbiOtim = notAmbiOtim + 5;

                    //Calcular Média de Cada Campo e 2 Casas Decimais
                    medAtend = (notAtedPess + notAtedRuim + notAtedReg + notAtedBom + notAtedOtim) / (qtTotalAvaliacao);
                    medAtendArred = new BigDecimal(medAtend).setScale(2, RoundingMode.HALF_EVEN);

                    medCardap = (notCardPess + notCardRuim + notCardReg + notCardBom + notCardOtim) / (qtTotalAvaliacao);
                    medCardapArred= new BigDecimal(medCardap).setScale(2, RoundingMode.HALF_EVEN);

                    medTempo = (notTempPess + notTempRuim + notTempReg + notTempBom + notTempOtim) / (qtTotalAvaliacao);
                    medTempEspArred = new BigDecimal(medTempo).setScale(2, RoundingMode.HALF_EVEN);

                    medAmbiente = (notAmbiPess + notAmbiRuim + notAmbiReg + notAmbiBom + notAmbiOtim) / (qtTotalAvaliacao);
                    medAmbienteArred = new BigDecimal(medAmbiente).setScale(2, RoundingMode.HALF_EVEN);

                    //Exibir Resultados
                    txt_TotQuant.setText(String.valueOf(qtTotalAvaliacao));
                    txt_MedAtend.setText(String.valueOf(medAtendArred));
                    txt_MedCard.setText(String.valueOf(medCardapArred));
                    txt_MedTemp.setText(String.valueOf(medTempEspArred));
                    txt_MedAmbi.setText(String.valueOf(medAmbienteArred));

                    String a = "", b = "", c = "", d = "";
                    if(medAtend < 1.5){ a = "Péssimo";
                    } else if(medAtend >= 1.5 && medAtend < 2.5){ a = "Ruim";
                    } else if(medAtend >= 2.5 && medAtend < 3.5){ a = "Regular";
                    } else if(medAtend >= 3.5 && medAtend < 4.5){ a = "Bom";
                    } else { a = "Ótimo"; }

                    if(medCardap < 1.5){ b = "Péssimo";
                    } else if(medCardap >= 1.5 && medCardap < 2.5){ b = "Ruim";
                    } else if(medCardap >= 2.5 && medCardap < 3.5){ b = "Regular";
                    } else if(medCardap >= 3.5 && medCardap < 4.5){ b = "Bom";
                    } else { b = "Ótimo";}

                    if(medTempo < 1.5){ c = "Péssimo";
                    } else if(medTempo >= 1.5 && medTempo < 2.5){ c = "Ruim";
                    } else if(medTempo >= 2.5 && medTempo < 3.5){ c = "Regular";
                    } else if(medTempo >= 3.5 && medTempo < 4.5){ c = "Bom";
                    } else { c = "Ótimo"; }

                    if(medAmbiente < 1.5){ d = "Péssimo";
                    } else if(medAmbiente >= 1.5 && medAmbiente < 2.5){ d = "Ruim";
                    } else if(medAmbiente >= 2.5 && medAmbiente < 3.5){ d = "Regular";
                    } else if(medAmbiente >= 3.5 && medAmbiente < 4.5){ d = "Bom";
                    } else { d = "Ótimo"; }

                    txt_ResAtend.setText(a); txt_ResCarda.setText(b); txt_ResTemp.setText(c); txt_ResAmbi.setText(d);
                }
            } catch (ParseException e) {e.printStackTrace();}
        }
        @Override public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
        @Override public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {         }
        @Override public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {         }
        @Override public void onCancelled(@NonNull DatabaseError databaseError) { }};
        daoFirebase.getNoPesquisa().addChildEventListener(fireChildEventListener);
    }
}
