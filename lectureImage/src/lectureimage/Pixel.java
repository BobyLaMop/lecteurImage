/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectureimage;

/**
 *
 * @author Christopher True
 */
public class Pixel 
{
    int rouge;
    int vert;
    int bleu;

    public Pixel(int rouge, int vert, int bleu)
    {
        this.rouge = rouge;
        this.vert = vert;
        this.bleu = bleu;
    }
    
    public boolean egal(Pixel p)
    {
        return (rouge==p.rouge && vert==p.vert && bleu==p.bleu);
    }
    
    public void changerCouleur(int v)
    {
        rouge+=v;
        vert+=v;
        bleu+=v;
    }
    
    public int getRouge() 
    {
        return rouge;
    }

    public void setRouge(int rouge) 
    {
        this.rouge = rouge;
    }

    public int getVert() 
    {
        return vert;
    }

    public void setVert(int vert) 
    {
        this.vert = vert;
    }

    public int getBleu() 
    {
        return bleu;
    }

    public void setBleu(int bleu) 
    {
        this.bleu = bleu;
    }
    
}
