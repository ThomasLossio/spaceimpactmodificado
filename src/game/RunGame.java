/**
 *
 * @author ThohaGames
 * Tho - Thomas / Ha - Hallef
 * Classe criada para executar o RodarJogo(RunGame) dentro de um laço...
 */
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.URL;
import jplay.Window;

public class RunGame {
    //Meus Atributos 
    private Window janela;
    private Keyboard teclado;
    private Cenario cenario;
    private GameImage telaMenu; 
    private boolean exit = false; 
    private int op = 1; 
    Font f = new Font("arial", Font.BOLD, 100); 
    Ranking rank = new Ranking();
    ArrayList<Ranking> lista = new ArrayList();    
    
    public RunGame() {
        this.janela = Jogo.janela;
        teclado = janela.getKeyboard();
        telaMenu = new GameImage(URL.sprite("Tela_MenuPrincipalJogar.png")); //Coloca a imagem do menu inicial (Para comecar ja com o menu)

        //Inicio do laço do jogo...
        while (exit != true) { 
            Som.stop();
            //Salvando os botoes para eventos (Down e Up)...
            teclado.addKey(KeyEvent.VK_DOWN); 
            teclado.addKey(KeyEvent.VK_UP); 
            telaMenu.draw();
            janela.update();
            
            //Esquema para transição das telas do menu logo abaixo...
            switch (op) { 
                case 1:{
                    telaMenu = new GameImage(URL.tile("Tela_MenuPrincipalJogar.png")); 
                break;}
                case 2:{
                    telaMenu = new GameImage(URL.tile("Tela_MenuPrincipalRanking.png")); 
                break;}
                case 3:{
                    telaMenu = new GameImage(URL.tile("Tela_Informacoes.png"));
                break;}
                case 4:{
                    telaMenu = new GameImage(URL.tile("Tela_MenuPrincipalExit.png")); 
                break;}
            }
            //Condições para as transições referente ao botão DOWN...
            if (teclado.keyDown(KeyEvent.VK_DOWN)) {
                switch(op){
                    case 1:{
                        op = 2;
                    break;}
                    case 2:{
                        op = 3;
                    break;}
                    case 3:{
                        op = 4;
                    break;}
                    case 4:{
                        op = 1;
                    break;}
                }
            }
            //Condições para as transições referente ao botão Up...
            if (teclado.keyDown(KeyEvent.VK_UP)) { 
                switch (op){
                    case 4:{
                        op = 3;
                    break;}
                    case 3:{
                        op = 2;
                    break;}
                    case 2:{
                        op = 1;
                    break;}
                    case 1:{
                        op = 4;
                    break;}
                }
            }

            //Acionador do evento... Botao Enter...
            if (teclado.keyDown(KeyEvent.VK_ENTER)) {
                switch (op) { 
                    case 1:{
                        //Salvando os botoes para eventos (Down e Up)...
                        teclado.addKey(KeyEvent.VK_DOWN, KeyEvent.KEY_RELEASED); 
                        teclado.addKey(KeyEvent.VK_UP, KeyEvent.KEY_RELEASED); 
                        //Startando o cenário do jogo...
                        cenario = new Cenario(janela);
                    break;}
                    
                    case 2:{ 
                        System.out.println("Menu Ranking");
                        lista = rank.listaRank();
                        for(int i = 0; i < lista.size() && i < 10; i++){
                            System.out.println(lista.get(i).getPos() + "- " + lista.get(i).getNome() + " " + lista.get(i).getPontos());
                            
                        } 
                        janela.drawText(lista.get(0).getPos() + "- " + lista.get(0).getNome() + " " + lista.get(0).getPontos(), 50, 50, Color.white, f);
                    break;}
                    
                    case 3:{
                        telaMenu = new GameImage(URL.tile("Tela_Informacoes"));
                        
                    break;}
                    case 4:{
                        exit = true;
                        janela.exit();
                    break;}
                }
            }
        }
    }
}
