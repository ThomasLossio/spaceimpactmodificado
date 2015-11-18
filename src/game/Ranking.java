/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import Banco.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;



/**
CREATE TABLE RANK
(
ID BIGSERIAL PRIMARY KEY,
NOME VARCHAR(15),
PONTOS NUMERIC
);
 */
public class Ranking {
    private int pos;
    private String nome;
    private int pontos;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    
    public static boolean CadastraPontos(String jogador, int pontos){
        try{
            Banco.cadastrar(jogador, pontos);
            return true;
        }catch (RuntimeException ex){
            return false; 
        }
        
    }
    
    public static ArrayList listaRank(){
        ArrayList<Ranking> Rank = new ArrayList<>();
        String SELECT = "select nome, pontos from rank order by pontos desc;";
        try{
            Banco.conecte();
            ResultSet rs = Banco.executeQuery(SELECT);
            int i = 1;
            while (rs.next()){
                Ranking rank = new Ranking();
                rank.setPos(i);
                rank.setNome(rs.getString("nome"));
                rank.setPontos(rs.getInt("pontos"));
                Rank.add(rank);
                i++;
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
}
        return Rank;
    }            

}
