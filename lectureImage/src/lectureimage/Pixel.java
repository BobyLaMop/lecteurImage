/* En-tête du programme
====================================================================================================
Fichier:            Image.java (TP1)
Auteur:             Simon Lagacé, Christopher True et Pierre-Luc Pépin
Date création:      01/02/2018
But:                Classe représentant un pixel qui représente un element d'une image 
                    ayant une valeur de rouge, de vert et de bleu entre 0 et 255 pour 
                    représenter la couleur du pixel;
====================================================================================================
*/
package lectureimage;

/**
 * Classe représentant un pixel. Un pixel est une couleur ayant 3 valeurs RGB
 * d'une valeur entre 0 et 255
 * @author Simon Lagacé
 * @author Christopher True
 */
public class Pixel 
{
    int rouge;  //Valeur RGB de rouge
    int vert;   //Valeur RGB de vert
    int bleu;   //Valeur RGB de bleu

    /**
     * Constructeur avec les valeurs RGB en paramètres
     * @param rouge
     * @param vert
     * @param bleu
     */
    public Pixel(int rouge, int vert, int bleu)
    {
        this.rouge = rouge;
        this.vert = vert;
        this.bleu = bleu;
    }
    
    /**
     * Retourne si vrai si le pixel passé en paramètre a les même valeurs RGB
     * que l'objet, faux sinon.
     * @param p Pixel que l'on veut comparer
     * @return
     */
    public boolean egal(Pixel p)
    {
        return (rouge==p.rouge && vert==p.vert && bleu==p.bleu);
    }
    
    /**
     * Permet de changer la valeur des valeurs RGB de l'objet par un offset
     * donné
     * @param v Valeur de offset que l'on veut ajouter aux 3 valeurs RGB
     */
    public void changerCouleur(int v)
    {
        
        rouge+=v;
        vert+=v;
        bleu+=v;
        
        if(rouge<0)
            rouge =0;
        if(vert<0)
            vert =0;
        if(bleu<0)
            bleu=0;
        
    }
    
    /**
     * Getter pour le rouge
     * @return Valeur RGB pour le rouge
     */
    public int getRouge() 
    {
        return rouge;
    }

    /**
     * Setter pour le rouge
     * @param rouge Valeur RGB que l'on veut appliquer au rouge
     */
    public void setRouge(int rouge) 
    {
        this.rouge = rouge;
    }

    /**
     * Getter pour le vert
     * @return Valeur RGB pour le vert
     */
    public int getVert() 
    {
        return vert;
    }

     /**
     * Setter pour le vert
     * @param vert Valeur RGB que l'on veut appliquer au vert
     */
    public void setVert(int vert) 
    {
        this.vert = vert;
    }

    /**
     * Getter pour le bleu
     * @return Valeur RGB pour le bleu
     */
    public int getBleu() 
    {
        return bleu;
    }

     /**
     * Setter pour le bleu
     * @param bleu Valeur RGB que l'on veut appliquer au bleu
     */
    public void setBleu(int bleu) 
    {
        this.bleu = bleu;
    }
    
}
