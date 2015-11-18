package game;

import jplay.Sprite;
import jplay.URL;

/*
 * @author ThohaGames 
 * (Tho - Thomas / Ha - Hallef)
 */
public class Tiro extends Sprite{

    protected static final int LEFT = 1, RIGHT = 2, STOP =3, UP =4, DOWN =5;
    private double velocidade_da_bala = 3;
    protected int caminho = STOP;
    protected boolean movendo = false;
    protected int direcao = 3;
    
    public Tiro(double x, double y, int caminho){
        super(URL.sprite("bala.png"), 1);
        this.caminho = caminho;
        this.x = x;
        this.y = y;
//        this.setTotalDuration(2000);
    }
    
    public void mover(){
        this.x += velocidade_da_bala;
        
        movendo = true;
        if(movendo){
            setSequence(0, 1);
            update();
            movendo = false;
        }
    }
    
    
}
