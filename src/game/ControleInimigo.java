/*
 * @author ThohaGames 
 * (Tho - Thomas / Ha - Hallef)
 * Classe criada para controle dos metodos do objeto inimigo...
 */
package game;

import java.util.LinkedList;
import java.util.Random;
import jplay.Scene;

public class ControleInimigo {

    public static LinkedList<inimigo_comum> inimigos = new LinkedList<>(); 
    public static inimigo_comum ini;
    private int dificuldade = 200;
    int cont = 0;

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }
    
    //Metodo para adicionar um objeto inimigo...
    public void addInimigo(Scene cena) {
        Random rd = new Random();
        if(rd.nextInt(2) == 0){
            ini = new inimigo_comum("AlienInimigo1.gif", 1, 1);
        }else{
            ini = new inimigo_comum("alienInimigo2.gif", 1, 2);
        }
        
        inimigos.addFirst(ini);
        cena.addOverlay(ini);
    }

    //Remove o objeto inimigo da tela e remove do linkedlist...
    public void removeInimigo(Scene cena, int numero) {
        cena.removeOverlay(inimigos.get(numero));
        inimigos.remove(inimigos.get(numero));        

    }
    
    //Metodo que trata a dificuldade dos obj inimigos...
    public void inimigo(Scene cena) {
        Random rd = new Random();
        int num = rd.nextInt(dificuldade);
        if (num == 1){
            
            addInimigo(cena);
        }
        run(cena);
    }
    //Metodo usado para limpar todos os objetos inimigos da tela....
    public void clearInimigos(){
        inimigos.removeAll(inimigos);
    }
    
    public void run(Scene cena){
        int j= 0;
        for(int i = 0; i < inimigos.size(); i++){
            if(!inimigos.get(i).mover()){ //Quando o mover for false, ele remova o inimigo...
                removeInimigo(cena, i);

                
            }
        }
    }
}
