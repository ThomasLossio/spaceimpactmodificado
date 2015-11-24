package game;

import java.awt.Color;
import java.awt.Font;
import jplay.Keyboard;
import jplay.Scene;
import jplay.Sprite;
import jplay.URL;
import jplay.Window;

/*
 * @author ThohaGames 
 * (Tho - Thomas / Ha - Hallef)
 */

public class Jogador extends Sprite{

    private double velocidade = 1.5;
    private int direcao = 3;
    private Keyboard teclado;
    private boolean movendo = false;
    private int cont = 0;
    private int vida = 3;
    private int pontos = 0;
    private int pontoslimite = 0;
    private int pontoslimiteVida = 0;
    private String nome;
    
    Font f = new Font("arial", Font.BOLD, 20);

    public int getPontoslimiteVida() {
        return pontoslimiteVida;
    }

    public void setPontoslimiteVida(int pontoslimiteVida) {
        this.pontoslimiteVida = pontoslimiteVida;
    }

    public int getPontoslimite() {
        return pontoslimite;
    }

    public void setPontoslimite(int pontoslimite) {
        this.pontoslimite = pontoslimite;
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
        this.pontos += pontos;
    }
    
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    
    
    public Jogador(int x, int y, String nome) {
        super(URL.sprite("navedon.png"),2);
        this.x = 15;
        this.y = y;
        this.nome = nome;
    }
    
    ControleTiro tiros =  new ControleTiro();

    //aqui precisa acrescentar o alien e o inimigo para ir passando 
    //até chegar em controle de tiro, para poder retirar tiro e inimigo da tela ^^
    public void atirar(Window janela, Scene cena, Keyboard teclado, Sprite alien, ControleInimigo control, Jogador jogador){ 
        if(teclado.keyDown(Keyboard.SPACE_KEY)){
            tiros.addTiro(x, y + 20, height, cena);
        }
        tiros.run(alien, cena, control, jogador); //Aqui também kkk
    }
    
    public void mover(Window janela){
        if(teclado == null){
            teclado = janela.getKeyboard();
        }
        
        if(teclado.keyDown(Keyboard.UP_KEY)){
            if(this.y > 80){
                   this.y -= velocidade;
               }
            setSequence(0, 2);
            movendo = true;
            }
        else if(teclado.keyDown(Keyboard.DOWN_KEY)){
            if(this.y < janela.getHeight()-160){
               this.y += velocidade;
           }
            setSequence(0, 2);
            movendo = true;
        }
        else if(teclado.keyDown(Keyboard.LEFT_KEY)){
            if(this.x > 15){
                this.x -= velocidade;
            }
            setSequence(0, 2);
            movendo = true;
        }
        else if(teclado.keyDown(Keyboard.RIGHT_KEY)){
            if(this.x < (janela.getWidth()/2)-80){
                this.x += velocidade;
            }
            setSequence(0, 2);
            movendo = true;
        }
        if(cont < 50){
            cont++;
        }else{
            update();
            cont = 0;
        }
    }
public boolean verificarVida(){
    if(this.vida > 0){
            return false;
    }
    return true;
}
    
    public void vida(Window janela, Scene cena, Cenario cenario){
        if(this.vida == 3){
        ImagensAleatorias c = new ImagensAleatorias(300, 5, "vida.png");
        ImagensAleatorias c2 = new ImagensAleatorias(350, 5, "vida.png");
        ImagensAleatorias c3 = new ImagensAleatorias(400, 5, "vida.png");
        c.draw();
        c2.draw();
        c3.draw();
        
        } else if (this.vida == 2){
            ImagensAleatorias c = new ImagensAleatorias(300, 5, "vida.png");
            ImagensAleatorias c2 = new ImagensAleatorias(350, 5, "vida.png"); 
            c.draw();
            c2.draw();
        } else if (this.vida == 1){
            ImagensAleatorias c = new ImagensAleatorias(300, 5, "vida.png");
            c.draw();
        } else {
                cenario.stop = true;
        }
        
        
        janela.drawText("Vida: " + this.vida, 30, 30, Color.WHITE, f);
        janela.drawText("Pontos: " + this.pontos, 150, 30, Color.WHITE, f);
        
        
    }
    public void resetPlayer(){
        this.vida = 3;
        this.pontos = 0;
    }


    
    
}
