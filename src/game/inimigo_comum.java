package game;

import java.util.Random;
import jplay.Scene;
import jplay.Sprite;
import jplay.URL;

/*
 * @author ThohaGames 
 * (Tho - Thomas / Ha - Hallef)
 */
public class inimigo_comum extends Sprite{
    private double velocidade = 1;
    private int direcao = 3;
    private int life = 0;
    private int tiponave = 0;

    public int getTiponave() {
        return tiponave;
    }

    public void setTiponave(int tiponave) {
        this.tiponave = tiponave;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
    
    
    public int gerarPosY(){
        Random gerador = new Random();
        int valor = gerador.nextInt(400)+50; //Sorteio de valores de Y para gerar a altura do objeto inimigo...
        return valor;
    }
    
    //Metodo que trata a velocidade do objeto inimigo...
    public inimigo_comum(String nomeNave, int x, int tiponave) {
        super(URL.sprite(nomeNave),x);
        this.x = 900; // maximo de x para gerar inimigo no eixo X 
        if(tiponave == 1){
            velocidade = 1.5;
            life = tiponave;
            this.tiponave = tiponave;
            this.y = gerarPosY();
        } else {
            velocidade = 1;
            life = tiponave;
            this.tiponave = tiponave;
            this.y = gerarPosY();
        }
    }
    
    public boolean mover(){
        if(this.x <= -50){
            return false;
        }else{
            this.x -= velocidade;
            setSequence(0, 2);
            return true;
        }
    }
    
    public void atacar(Jogador jogador, Scene cena, ControleInimigo control){
        for(int i = 0; i < ControleInimigo.inimigos.size(); i++){
            if(ControleInimigo.inimigos.get(i).collided(jogador)){
//                if(jogador.getVida()< 0){
                    jogador.setVida(jogador.getVida()-1);
//                } else {
//                    jogador.setVida(jogador.getVida()-1);
//                }
                control.removeInimigo(cena, i);
            }
        }
    }
}

