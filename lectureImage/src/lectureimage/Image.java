/* En-tête du programme
====================================================================================================
Fichier:            Image.java (TP1)
Auteur:             Simon Lagacé, Christopher True et Pierre-Luc Pépin
Date création:      01/02/2018
But:                Classe représentant une image de type ppm ou pgm (format) avec une largeur et
                    une hauteur en pixel ainsi qu'une matrice de pixel représentant l'image
====================================================================================================
*/
package lectureimage;

/**
 * Classe qui représente une image de type ppm et pgm. L'image est charactérisé
 * par une matrice de pixel ainsi qu'une largeur et une hauteur.
 * @author Simon Lagacé
 * @author Pierre-Luc Pépin
 * @author Christopher True
 */
public class Image {
    
    private String format;      //Format (pgm ou ppm)
    private int largeur;        //Largeur de l'image
    private int hauteur;        //Hauteur de l'image
    private Pixel[][] matrice;  //Matrice de pixel
    
    /**
     * Constructeur sans paramètre
     */
    public Image()
    {
        format = null;
        largeur = 0;
        hauteur = 0;
    }
    
    /**
     * Constructeur avec format largeur et hauter
     * @param format    Format de l'image
     * @param largeur   Largeur de l'image
     * @param hauteur   Hauteur de l'image
     */
    public Image(String format, int largeur, int hauteur)
    {
        this.format = format;
        this.largeur = largeur;
        this.hauteur = hauteur;
        construireMatrice();
    }
    
    public void afficherMatriceConsole()
    {
        if(format=="pgm")
        {
            for(int i=0;i<hauteur;i++)
            {
                for(int j=0;j<largeur;j++)
                {
                    System.out.print(Integer.toString(matrice[i][j].getRouge()) + " ");
                }
                
            System.out.println();
            }
        }
        else if (format=="ppm")
        {
            for(int i=0;i<hauteur;i++)
            {
                for(int j=0;j<largeur;j++)
                {
                    System.out.print(Integer.toString(matrice[i][j].getRouge()) + " ");
                    System.out.print(Integer.toString(matrice[i][j].getVert()) + " ");
                    System.out.print(Integer.toString(matrice[i][j].getBleu()) + " ");
                }
                
            System.out.println();
            }
        }
            
    }
    /**
     * Construit la matrice selon la hauteur et la largeur de l'image
     */
    public void construireMatrice()
    {
        matrice = new Pixel[hauteur][largeur];
    }
    
    /**
     * Getter pour le format
     * @return Format de l'image
     */
    public String getFormat()
    {
        return format;
    }

    /**
     * Setter pour le format
     * @param format Format voulu
     */
    public void setFormat(String format)
    {
        this.format = format;
    }

    /**
     * Getter pour la largeur
     * @return Largeur de l'image
     */
    public int getLargeur()
    {
        return largeur;
    }

    /**
     * Setter pour la largeur
     * @param largeur Largeur de l'image
     */
    public void setLargeur(int largeur)
    {
        this.largeur = largeur;
    }

    /**
     * Getter pour la hauteur
     * @return Hauteur de l'image
     */
    public int getHauteur()
    {
        return hauteur;
    }

    /**
     * Setter pour la hauteur
     * @param hauteur Hauteur de l'image
     */
    public void setHauteur(int hauteur)
    {
        this.hauteur = hauteur;
    }

    /**
     * Getter pour la matrice de pixel
     * @return Matrice de pixel de l'image
     */
    public Pixel[][] getMatrice()
    {
        return matrice;
    }

    /**
     * Setter pour la matrice de pixel
     * @param matrice Matrice de pixel de l'image
     */
    public void setMatrice(Pixel[][] matrice)
    {
        this.matrice = matrice;
    }
    
    
}
