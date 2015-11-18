/*
 * @author ThohaGames 
 * (Tho - Thomas / Ha - Hallef)
 * Classe criada para o cenário do jogo, aqui é onde tudo ganha vida no jogo...
 */

package game;

import java.awt.event.KeyEvent;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Cenario {
    private Window janela;
    private Scene cena;
    private Jogador MyPlayer;
    private inimigo_comum alien;
    private ControleInimigo control;
    private Keyboard teclado;
    public boolean stop = false;
    public boolean chefao = false;
    private GameImage telaMenu;
    private int menu;
    private boolean exitGame = false;
    private boolean pauseGame = false;
    private boolean GameOver = false;
    private Ranking rank = new Ranking();
    
    public Cenario(Window window){
        String nome = JOptionPane.showInputDialog("Digite seu nick");
        janela = window;
        cena = new Scene();
        cena.loadFromFile(URL.scenario("cenario.scn"));
        MyPlayer = new Jogador(300,300,nome);
        alien = new inimigo_comum("alienInimigo.png", 1, 1);
        teclado = janela.getKeyboard();
        control = new ControleInimigo();
        rank = new Ranking();
        Som.play("trilha.wav");
        run();
    }

    private void run(){
        
        while(exitGame != true){
            while(pauseGame != true){
                cena.draw();
                MyPlayer.draw();
                MyPlayer.mover(janela);
                MyPlayer.vida(janela, cena, this);
                alien.draw();
                control.inimigo(cena);
                MyPlayer.atirar(janela, cena, teclado, alien, control, MyPlayer);
                janela.update();
                alien.atacar(MyPlayer, cena, control);
                if(MyPlayer.getPontoslimite() >= 50 && control.getDificuldade() > 50){
                    control.setDificuldade(control.getDificuldade() - 30);
                    MyPlayer.setPontoslimite(0);
                }
                if(MyPlayer.getPontoslimiteVida() >= 2500){
//                    ImagensAleatorias vida = new ImagensAleatorias(menu, menu, null)
                }
                
                if (MyPlayer.verificarVida() && GameOver == false) {
                    LimparImgMenu();
                    if(rank.CadastraPontos(MyPlayer.getNome(), MyPlayer.getPontos())){
                        System.out.println("ok");
                    } else {
                        System.out.println("não");
                    }                                             
                    GameOver = true;                    
                    pauseGame = true;
                    
                }
                janela.update();
                if (teclado.keyDown(KeyEvent.VK_ESCAPE)) {
                    telaMenu = new GameImage(URL.tile("Tela_FundoTransparente.png"));
                    telaMenu.draw();
                    menu = 0;
                    pauseGame = true;
                }
            }
            
            switch (menu) {
                case 0:{
                    LimparImgMenu();
                    if(GameOver){
                        telaMenu = new GameImage(URL.tile("Tela_GameOverExit.png"));
                    }else{
                        telaMenu = new GameImage(URL.tile("Tela_PauseExit.png"));
                    }
                break;}
                case 1:{
                    LimparImgMenu();
                    if (GameOver) {
                        
                        telaMenu = new GameImage(URL.tile("Tela_GameOverTryAgain.png"));
                    } else {
                        telaMenu = new GameImage(URL.tile("Tela_PauseRetomar.png"));
                    }
                break;}
            }
            
            if (teclado.keyDown(KeyEvent.VK_DOWN)) {
                menu = 0;
            }
            if (teclado.keyDown(KeyEvent.VK_UP)) {
                menu = 1;
            }
            if (teclado.keyDown(KeyEvent.VK_ENTER)) {
                switch (menu) {
                    case 1:
                        if (GameOver) {
                            exitGame = true;
                        } else {
                            pauseGame = false;
                        }
                        break;
                    case 0:{
                        Som.stop();
                        control.clearInimigos();
                        MyPlayer.resetPlayer();
                        GameOver = false;
                        exitGame = true;
                    break;}

                }
            }

            telaMenu.draw();
            janela.update();
            
        }
        if(GameOver) {
                control.clearInimigos();
                MyPlayer.resetPlayer();       
                GameOver = false;
                new Cenario(janela);
                pauseGame = false;
                
        }

        
            
//            while(chefao){
//                
//            }
//            if(jogador.getPontoslimite() == 5000){
//                chefao = true;
//            }
        
//            if (stop){
//                telaMenu = new GameImage(URL.sprite("Tela_GameOver.png"));
//                telaMenu.draw();
//                janela.update();
//                Som.stop();
//                control.clear();
//                if(rank.CadastraPontos(MyPlayer.getNome(), MyPlayer.getPontos())){
//                    System.out.println("ok");
//                } else {
//                    System.out.println("não");
//                }                
//                break;
//            }
        }
    
    private void LimparImgMenu() {
        cena.moveScene(MyPlayer);
        MyPlayer.draw();
        if (GameOver) {
            telaMenu = new GameImage(URL.tile("Tela_FundoTransparente.png"));
        } else {
            telaMenu = new GameImage(URL.tile("Tela_FundoTransparente.png"));
        }
        telaMenu.draw();
    }
    }
    

