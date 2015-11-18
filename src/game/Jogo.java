package game;

/*
 * @author ThohaGames 
 * (Tho - Thomas / Ha - Hallef)
 */

import jplay.Window;

public class Jogo {
    //Determino as dimensões da Tela do Jogo logo abaixo...
    public static Window janela = new Window(800, 600);

    public static void main(String[] args) {
        //Instancio a classe que irá rodar o jogo...
        RunGame iniciar = new RunGame(); 
    }
    
}
 