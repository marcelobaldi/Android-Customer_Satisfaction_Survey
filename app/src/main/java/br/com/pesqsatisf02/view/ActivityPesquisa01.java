//Pacote, Libs, Arquivos, Etc
package br.com.pesqsatisf02.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import br.com.pesqsatisf02.R;
import br.com.pesqsatisf02.helper.Helper;
import br.com.pesqsatisf02.model.Pesquisa;

//Classe View
public class ActivityPesquisa01 extends AppCompatActivity {
    //Variáveis - Classe (Classe, Cx Entrada, Botão Comando Imagem)
    private Helper      helper = new Helper();
    private EditText    atendimento, cardapio, tempoEspera, ambLimpeza;
    private ImageView   pess1, ruim1, reg1, bom1, otimo1, pess2, ruim2, reg2, bom2, otimo2;
    private ImageView   pess3, ruim3, reg3, bom3, otimo3, pess4, ruim4, reg4, bom4, otimo4;

    //Método Inicial
    @Override protected  void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);
        //View e Barra Menu
        setContentView(R.layout.activity_pesquisa01);               //View Chamada (Xml)
        setTitle("Pesquisa Satisfação - Pag.1/2");                  //Barra Menu (Título)

        //Caixas Entrada (View)
        atendimento = findViewById(R.id.edt_atendimento);
        cardapio    = findViewById(R.id.edt_cardapio);
        tempoEspera = findViewById(R.id.edt_tempo_espera);
        ambLimpeza  = findViewById(R.id.edt_ambiente_limpeza);

        //Botões Comando (View) - Declarar
        pess1       = findViewById(R.id.ibtn_01_pessimo);
        pess2       = findViewById(R.id.ibtn_02_pessimo);
        pess3       = findViewById(R.id.ibtn_03_pessimo);
        pess4       = findViewById(R.id.ibtn_04_pessimo);
        ruim1       = findViewById(R.id.ibtn_01_ruim);
        ruim2       = findViewById(R.id.ibtn_02_ruim);
        ruim3       = findViewById(R.id.ibtn_03_ruim);
        ruim4       = findViewById(R.id.ibtn_04_ruim);
        reg1        = findViewById(R.id.ibtn_01_reg);
        reg2        = findViewById(R.id.ibtn_02_reg);
        reg3        = findViewById(R.id.ibtn_03_reg);
        reg4        = findViewById(R.id.ibtn_04_reg);
        bom1        = findViewById(R.id.ibtn_01_bom);
        bom2        = findViewById(R.id.ibtn_02_bom);
        bom3        = findViewById(R.id.ibtn_03_bom);
        bom4        = findViewById(R.id.ibtn_04_bom);
        otimo1      = findViewById(R.id.ibtn_01_excelente);
        otimo2      = findViewById(R.id.ibtn_02_excelente);
        otimo3      = findViewById(R.id.ibtn_03_excelente);
        otimo4      = findViewById(R.id.ibtn_04_otimo);

        //Botões Comando (View) - Setar Na Caixa Entrada
        pess1.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            atendimento.setText(helper.PESSIMO);}
        });
        pess2.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {cardapio.setText(helper.PESSIMO);}});
        pess3.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {tempoEspera.setText(helper.PESSIMO);}});
        pess4.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {ambLimpeza.setText(helper.PESSIMO);}});
        ruim1.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {atendimento.setText(helper.RUIM);}});
        ruim2.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {cardapio.setText(helper.RUIM);}});
        ruim3.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {tempoEspera.setText(helper.RUIM);}});
        ruim4.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {ambLimpeza.setText(helper.RUIM);}});
        reg1.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {atendimento.setText(helper.REGULAR);}});
        reg2.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {cardapio.setText(helper.REGULAR);}});
        reg3.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {tempoEspera.setText(helper.REGULAR);}});
        reg4.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {ambLimpeza.setText(helper.REGULAR);}});
        bom1.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {atendimento.setText(helper.BOM);}});
        bom2.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {cardapio.setText(helper.BOM);}});
        bom3.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {tempoEspera.setText(helper.BOM);}});
        bom4.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {ambLimpeza.setText(helper.BOM);}});
        otimo1.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {atendimento.setText(helper.OTIMO);}});
        otimo2.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {cardapio.setText(helper.OTIMO);}});
        otimo3.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {tempoEspera.setText(helper.OTIMO);}});
        otimo4.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {ambLimpeza.setText(helper.OTIMO);}});
    }

    //Botão Comando - Navegar
    public void btn_proximo (View view){
        //Pegar Valor
        String atendST = atendimento.getText().toString();
        String cardaST = cardapio.getText().toString();
        String tempoST = tempoEspera.getText().toString();
        String ambLiST = ambLimpeza.getText().toString();

        //Navegar Com Tratamento
        if(atendST.isEmpty()|| cardaST.isEmpty() || tempoST.isEmpty() || ambLiST.isEmpty()) {
            String msg = "Por Favor, Preencha Todos os Campos";
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
        } else {
            Pesquisa pesquisa = new Pesquisa(atendST, cardaST, tempoST, ambLiST);

            finish();
            Intent intent = new Intent(getBaseContext(), ActivityPesquisa02.class);
            intent.putExtra("PESQUISA01", pesquisa);
            startActivity(intent);
        }
    }

    //Barra de Navegação - Botão Voltar (Se Vazio, Então o Desabilita) -  Mas falta o Botão Home e 2o. plano;
    @Override public void onBackPressed() {
        //...
    }
}


