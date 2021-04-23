package br.com.pesqsatisf02.model;
import android.os.Parcel;
import android.os.Parcelable;

public class Pesquisa implements Parcelable {
    //Atributos Activity01
    private String  atendimento;
    private String  cardapio;
    private String  tempoEspera;
    private String  ambienteLimpeza;

    //Atributos Activity02
    private String  nosConheceu;
    private String  observacoes;
    private String  dataAtual;          //dataAvaliação seria melhor ...
    private String  horaAtual;          //hora avaliação seria melhor ...

    //Construtor Vazio - Firebase, Etc
    public Pesquisa(){
    }

    //Construtor Activity01 (Todos os Campos da Activity01)
    public Pesquisa(String atendimento, String cardapio, String tempoEspera, String ambienteLimpeza) {
        this.atendimento = atendimento;
        this.cardapio = cardapio;
        this.tempoEspera = tempoEspera;
        this.ambienteLimpeza = ambienteLimpeza;
    }

    //Construtor Completo (Activity01 + Activity02 + Data e Hora) - Todos os Campos da Activity01 e Activity02)
    public Pesquisa(String atendimento, String cardapio, String tempoEspera, String ambienteLimpeza, String nosConheceu, String observacoes, String dataAtual, String horaAtual) {
        this.atendimento = atendimento;
        this.cardapio = cardapio;
        this.tempoEspera = tempoEspera;
        this.ambienteLimpeza = ambienteLimpeza;
        this.nosConheceu = nosConheceu;
        this.observacoes = observacoes;
        this.dataAtual = dataAtual;
        this.horaAtual = horaAtual;
    }

    //Getter e Setter (Sem Regra de Negócios)
    public String getAtendimento() {
        return atendimento;
    }
    public void setAtendimento(String atendimento) {
        this.atendimento = atendimento;
    }
    public String getCardapio() {
        return cardapio;
    }
    public void setCardapio(String cardapio) {
        this.cardapio = cardapio;
    }
    public String getTempoEspera() {
        return tempoEspera;
    }
    public void setTempoEspera(String tempoEspera) {
        this.tempoEspera = tempoEspera;
    }
    public String getAmbienteLimpeza() {
        return ambienteLimpeza;
    }
    public void setAmbienteLimpeza(String ambienteLimpeza) {
        this.ambienteLimpeza = ambienteLimpeza;
    }
    public String getNosConheceu() {
        return nosConheceu;
    }
    public void setNosConheceu(String nosConheceu) {
        this.nosConheceu = nosConheceu;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    public String getDataAtual() {
        return dataAtual;
    }
    public void setDataAtual(String dataAtual) {
        this.dataAtual = dataAtual;
    }
    public String getHoraAtual() {
        return horaAtual;
    }
    public void setHoraAtual(String horaAtual) {
        this.horaAtual = horaAtual;
    }

    //Parcelable (Passar Dados Outra Tela)
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.atendimento);
        dest.writeString(this.cardapio);
        dest.writeString(this.tempoEspera);
        dest.writeString(this.ambienteLimpeza);
        dest.writeString(this.nosConheceu);
        dest.writeString(this.observacoes);
        dest.writeString(this.dataAtual);
        dest.writeString(this.horaAtual);
    }

    protected Pesquisa(Parcel in) {
        this.atendimento = in.readString();
        this.cardapio = in.readString();
        this.tempoEspera = in.readString();
        this.ambienteLimpeza = in.readString();
        this.nosConheceu = in.readString();
        this.observacoes = in.readString();
        this.dataAtual = in.readString();
        this.horaAtual = in.readString();
    }

    public static final Creator<Pesquisa> CREATOR = new Creator<Pesquisa>() {
        @Override
        public Pesquisa createFromParcel(Parcel source) {
            return new Pesquisa(source);
        }

        @Override
        public Pesquisa[] newArray(int size) {
            return new Pesquisa[size];
        }
    };
}

//O Nome das Variáveias Deve Ser Igual