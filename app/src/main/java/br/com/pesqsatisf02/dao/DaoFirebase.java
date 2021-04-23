package br.com.pesqsatisf02.dao;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DaoFirebase {
    public static ChildEventListener    fireChildEventListener;   //Ações no Banco de Dados do Firebase;

    //Classe Firebase - Banco de Dados
    public FirebaseDatabase getBancoDadosFirebase(){
        return FirebaseDatabase.getInstance();
    }

    //Nó Banco de Dados - Pesquisa de Satisfação
    public DatabaseReference getNoPesquisa(){
        return getBancoDadosFirebase().getReference().child("NovaBella").child("PesquisaSatisfacao");
    }

    //Nó Banco de Dados - Configurações
    public DatabaseReference getNoConfiguracoes(){
        return getBancoDadosFirebase().getReference().child("NovaBella").child("Configuracoes");
    }

}


