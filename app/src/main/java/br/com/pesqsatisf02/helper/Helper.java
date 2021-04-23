package br.com.pesqsatisf02.helper;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Helper {
    //Para Perguntas


    //Para Notas
    public static final String PESSIMO              = "Pessimo";
    public static final String RUIM                 = "Ruim";
    public static final String REGULAR              = "Regular";
    public static final String BOM                  = "Bom";
    public static final String OTIMO                = "Otimo";

    //Para SMS
    public static final String TEL_MARCELO          = "5511979787911";
    public static final String TEL_KLEBER           = "5511996722925";
    public static final String TEL_IRACI            = "5511976532221";
    public static final String MSG_PESSIMA          = "URGENTE! Avaliação Péssima" + "\n";          //O \n é para pular linha;

    //Para Data e Hora
    private Calendar momentoAtual       = Calendar.getInstance();

    public String dataAtual (){
        SimpleDateFormat retornoData  = new SimpleDateFormat( "dd/MM/yyyy" );
        return retornoData.format( momentoAtual.getTime() );
    }

    public String horaAtual (){
        SimpleDateFormat  retornoHora = new SimpleDateFormat( "HH:mm:ss:SS" );
        return retornoHora.format( momentoAtual.getTime() );
    }

}


//Ta Certo aqui ou no Strings. Eu acho que no Strings



// Como Pegar a Data e Hora do Firebase (Jone)                                                              ?!?!
//(se a data e hora do celular/tablet errado). Vai dar Pau a Pesquisa ????????????????????????????????


