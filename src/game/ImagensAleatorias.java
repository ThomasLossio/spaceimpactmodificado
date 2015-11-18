/*
 * Classe usada apenas para padronizar a inserção de imagens.
 */
package game;

import jplay.Sprite;
import jplay.URL;

/*
 * @author ThohaGames 
 * (Tho - Thomas / Ha - Hallef)
 */
public class ImagensAleatorias extends Sprite{
    public ImagensAleatorias(int x, int y, String imagem) {
        super(URL.sprite(imagem),1);
        this.x = x;
        this.y = y;
    }    
}
