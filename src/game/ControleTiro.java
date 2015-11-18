package game;

import java.util.LinkedList;
import jplay.Scene;
import jplay.Sound;
import jplay.Sprite;
import jplay.URL;

/*
 * @author ThohaGames 
 * (Tho - Thomas / Ha - Hallef)
 */
public class ControleTiro {
    LinkedList<Tiro>tiros = new LinkedList<>();
    
    public void addTiro(double x, double y, int caminho, Scene cena){
        Tiro tiro = new Tiro(x,y,caminho);
        tiros.addFirst(tiro);
        cena.addOverlay(tiro);
        somDisparo();
    }
    
    //remove da tela e do linkedlist o tiro equivalente a numero
    public void removeTiro(Scene cena, int numero){
        cena.removeOverlay(tiros.get(numero));
        tiros.remove(tiros.get(numero));
    }
    
    public void run(Sprite inimigo, Scene cena, ControleInimigo control, Jogador jogador){
        for(int i = 0; i < tiros.size(); i++){
            Tiro tiro = tiros.removeFirst();
            tiro.mover();
            tiros.addLast(tiro);
            if(tiros.get(i).x > 10000){ //como o método de colisão só funcionou quando eu colocava o eixo x do tiro igual a 10000, faço essa verificação para retirar os tiros que estão com o eixo x maior
                removeTiro(cena, i); //dai passo a cena e o número do tiro que devo retirar
            }
            for(int j = 0 ; j < ControleInimigo.inimigos.size() ; j++){ //Varrendo lista de inimigos
                if(tiro.collided(ControleInimigo.inimigos.get(j))){ //Verifica colisão de tiro com cada inimigo
                    if(ControleInimigo.inimigos.get(j).getTiponave() == 2){ 
                        if(ControleInimigo.inimigos.get(j).getLife() == 2){
                            tiro.x += 10000;
                            ControleInimigo.inimigos.get(j).setLife(ControleInimigo.inimigos.get(j).getLife() - 1);
                        } else if (ControleInimigo.inimigos.get(j).getLife() == 1){
                            tiro.x += 10000; //Se colidir ele joga a imagem do tiro pra fora da tela :#
        //                    removeTiro(cena, i);
                            control.removeInimigo(cena, j); 
                            jogador.setPontos(10);
                            jogador.setPontoslimite(jogador.getPontoslimite() + 10);
                            jogador.setPontoslimiteVida(jogador.getPontoslimiteVida() + 10);
                        }
                    } else {
                        if(ControleInimigo.inimigos.get(j).getLife() == 1){
                            tiro.x += 10000; //Se colidir ele joga a imagem do tiro pra fora da tela :#
        //                    removeTiro(cena, i);
                            control.removeInimigo(cena, j); 
                            jogador.setPontos(5);
                            jogador.setPontoslimite(jogador.getPontoslimite() + 5);                            
                            jogador.setPontoslimiteVida(jogador.getPontoslimiteVida() + 5);                            
                        }
                    }
                    

                }
            }
        }
        
    }
    
    private void somDisparo(){
        new Sound(URL.audio("Laser2.wav")).play();
    }
}
