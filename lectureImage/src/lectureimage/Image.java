/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectureimage;

/**
 *
 * @author Pierre-Luc
 */
public class Image {
    
    private String format;
    private int largeur;
    private int hauteur;
    private Pixel[][] matrice;
    
    public Image()
    {
        format = null;
        largeur = 0;
        hauteur = 0;
    }
    
    public Image(String format, int largeur, int hauteur)
    {
        this.format = format;
        this.largeur = largeur;
        this.hauteur = hauteur;
        matrice = new Pixel[hauteur][largeur];
    }
    
    public String getFormat()
    {
        return format;
    }

    public void setFormat(String format)
    {
        this.format = format;
    }

    public int getLargeur()
    {
        return largeur;
    }

    public void setLargeur(int largeur)
    {
        this.largeur = largeur;
    }

    public int getHauteur()
    {
        return hauteur;
    }

    public void setHauteur(int hauteur)
    {
        this.hauteur = hauteur;
    }

    public Pixel[][] getMatrice()
    {
        return matrice;
    }

    public void setMatrice(Pixel[][] matrice)
    {
        this.matrice = matrice;
    }
    
    
}
