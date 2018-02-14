/* En-tête du programme
====================================================================================================
Fichier:            Image.java (TP1)
Auteur:             Simon Lagacé, Christopher True et Pierre-Luc Pépin
Date création:      01/02/2018
But:                Classe qui permet de faire la manipulation d'image et de
                    fichier en format ppm et pgm.
====================================================================================================
*/
package lectureimage;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Classe qui permet de faire la manipulation d'image et de fichier en 
 * format ppm (portable pixel map) et pgm (portable grey map) avec entre autre,
 * des méthodes de lecture et d'écriture
 * 
 * @author Christopher True
 * @author Simon Lagacé
 * @author Pierre-Luc Pépin
 */
public class TraiteurImage
{

    /**
     * Ouvre en lecture le fichier en format PGM ou PPM spécifié et charge les données dans l’image
     * @param i Image dans laquelle on veut charger les données du fichier
     * @param f Fichier duquel on veut lire les données
     */
    public static void lire(Image i, Fichier f)
    {
        try
        {

            Scanner scan = new Scanner(f.getFichier()).useDelimiter("\r\n");

            String ligne = scan.nextLine();
            String[] ligneDiv;
            //Lecture si c'est un fichier pgm
            if (ligne.equals("P2"))
            {
                //Lecture hauteur et largeur 
                ligne = scan.nextLine();
                ligneDiv = ligne.split(" ");
                
                //On change les propriétés de l'image
                i.setFormat("pgm");
                i.setHauteur(Integer.parseInt(ligneDiv[0]));
                i.setLargeur(Integer.parseInt(ligneDiv[1]));
                i.construireMatrice();
                
                //Lecture du chiffre max
                scan.nextLine();
                
                //Lecture de la matrice
                int x, y;
                x = y = 0;
                while (scan.hasNext())
                {
                    ligne = scan.nextLine();
                    ligneDiv = ligne.split(" ");
                    for (String str : ligneDiv)
                    {
                        int valeur = Integer.parseInt(str);
                        i.getMatrice()[x][y] = new Pixel(valeur, valeur, valeur);
                        y++;
                        //On change de ligne et on revient a la premiere colonne quand on atteint la largeur
                        if (y == i.getLargeur())
                        {
                            y = 0;
                            x++;
                        }
                    }
                }
            } //Lecture si c'est un fichier ppm
            else if (ligne.equals("P3"))
            {
                //Lecture hauteur et largeur 
                ligne = scan.nextLine();
                ligneDiv = ligne.split(" ");
                
                //On change les propriétés de l'image
                i.setFormat("ppm");
                i.setHauteur(Integer.parseInt(ligneDiv[0]));
                i.setLargeur(Integer.parseInt(ligneDiv[1]));
                i.construireMatrice();
                
                //Lecture du chiffre max
                scan.nextLine();
                
                 //Lecture de la matrice
                int x, y, z;
                x = y = z = 0;
                int[] pix = new int[3];
                while (scan.hasNext())
                {
                    ligne = scan.nextLine();
                    ligneDiv = ligne.split(" ");
                    
                    for (String str : ligneDiv)
                    {
                        pix[z] = Integer.parseInt(str);
                        z++; 
                        
                        //Lorsqu'on a lut 3 valeur, on crée le pixel et on l'ajoute 
                        if(z==3)
                        {
                            i.getMatrice()[x][y] = new Pixel(pix[0],pix[1],pix[2]);
                            z=0;
                            y++;
                        }
                        
                        //On change de ligne et on revient a la premiere colonne quand on atteint la largeur
                        if (y == i.getLargeur())
                        {
                            y = 0;
                            x++;
                        }
                    }
                }
            }
            scan.close();
        } //Exception fichier non trouvé
        catch (FileNotFoundException ex)
        {
            System.out.println("Impossible d'ouvrir le fichier '" + f.getNomFichier() + f.getType() + "'");
        } //Autre exception
        catch (IOException ex)
        {
            System.out.println("Erreur de lecture pour '" + f.getNomFichier() + f.getType() + "'");
        }
    }

    /**
     * Ouvre le fichier spécifié et y sauvegarde en format PGM ou PPM l’image
     * @param f Fichier que l'on veut sauvegarder les données de l'image
     * @param i Image que l'on veut lire les données
     */
    public static void ecrire(Fichier f, Image i)
    {
        //Si le fichier et l'image sont de même format 
        if (f.getType().equals(i.getFormat()))
        {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(f.getFichier())))
            {
                if (i.getFormat().equals("pgm"))
                {
                    //Écriture de l'entete du fichier
                    writer.write("P2", 0, 2);
                    writer.newLine();
                    writer.write(Integer.toString(i.getHauteur()), 0, Integer.toString(i.getHauteur()).length());
                    writer.write(" ", 0, 1);
                    writer.write(Integer.toString(i.getLargeur()), 0, Integer.toString(i.getHauteur()).length());
                    writer.newLine();
                    writer.write("255", 0, 3);
                    writer.newLine();

                    //Écriture des pixels
                    for (int j = 0; j < i.getHauteur(); j++)
                    {
                        for (int k = 0; k < i.getLargeur(); k++)
                        {
                            writer.write(Integer.toString(i.getMatrice()[j][k].getRouge()), 0, Integer.toString(i.getMatrice()[j][k].getRouge()).length());
                            writer.write(" ", 0, 1);
                        }
                        writer.newLine();
                    }
                } else if (i.getFormat().equals("ppm"))
                {
                    //Écriture de l'entete du fichier
                    writer.write("P3", 0, 2);
                    writer.newLine();
                    writer.write(Integer.toString(i.getHauteur()), 0, Integer.toString(i.getHauteur()).length());
                    writer.write(" ", 0, 1);
                    writer.write(Integer.toString(i.getLargeur()), 0, Integer.toString(i.getHauteur()).length());
                    writer.newLine();
                    writer.write("255", 0, 3);
                    writer.newLine();

                    //Écriture des pixels
                    for (int j = 0; j < i.getHauteur(); j++)
                    {
                        for (int k = 0; k < i.getLargeur(); k++)
                        {
                            writer.write(Integer.toString(i.getMatrice()[j][k].getRouge()), 0, Integer.toString(i.getMatrice()[j][k].getRouge()).length());
                            writer.write(" ", 0, 1);
                            writer.write(Integer.toString(i.getMatrice()[j][k].getVert()), 0, Integer.toString(i.getMatrice()[j][k].getVert()).length());
                            writer.write(" ", 0, 1);
                            writer.write(Integer.toString(i.getMatrice()[j][k].getBleu()), 0, Integer.toString(i.getMatrice()[j][k].getBleu()).length());
                            writer.write(" ", 0, 1);
                        }
                        writer.newLine();
                    }
                }
                writer.close();
            } catch (IOException x)
            {
                System.err.format("IOException: %s%n", x);
            }
        } else
        {
            System.out.println("Le fichier et l'image ne sont pas de même format!");
        }
    }
    
    /**
     * Fait une copie de l'image i1 dans l'image i2
     * @param i1 Image que l'on veut copier
     * @param i2 Image dans laquelle on copie
     */
    public static void copier(Image i1, Image i2)
    {
        i2.setFormat(i1.getFormat());
        i2.setHauteur(i1.getHauteur());
        i2.setLargeur(i1.getLargeur());
        i2.setMatrice(i1.getMatrice());
    }
    
    /**
     * Retourne la couleur la plus prépondérante dans l'image i sous forme de 
     * pixel
     * @param i Image que l'on veut trouver la couleur prépondérante
     * @return La couleur prépondérante en pixel
     */
    public static Pixel couleur_preponderante(Image i)
    {
        long[] total = {0,0,0};
        
        //On parcourt la matrice en additionnant chaque couleur a un total
        for(int j =0;j<i.getHauteur();j++)
            for(int k=0;k<i.getLargeur();k++)
            {
                total[0]+= i.getMatrice()[j][k].getRouge();
                total[1]+= i.getMatrice()[j][k].getVert();
                total[2]+= i.getMatrice()[j][k].getBleu();
            }
        
        //Calcul du nombre de pixel
        long nbPixel = i.getHauteur() * i.getLargeur();
        
        //Calcul des moyennes
        for(int x=0;x<3;x++)
        {
             total[x] = Math.round((double)total[x]/nbPixel);       
        }
        return new Pixel((int)total[0],(int)total[1],(int)total[2]);
    }
    
    /**
     * Éclaircit ou noircit l'image selon une valeur donnée
     * @param i L'image que l'on veut éclaircir ou noircir
     * @param v La valeur d'éclaircissement (negatif)  
     *          ou de noircissement (positif)
     */
    public static void eclaircir_noircir(Image i, int v)
    {
       for(int j =0;j<i.getHauteur();j++)
            for(int k=0;k<i.getLargeur();k++)
            {
                i.getMatrice()[j][k].changerCouleur(-v);
            } 
    }
    
    /**
     * Retourne vrai si les deux images sont identiques, faux sinon
     * @param i1 Image que l'on veut comparer
     * @param i2 Image 2 que l'on veut comparer
     * @return Si les deux images sont identiques ou non
     */
    public static boolean sont_identique(Image i1, Image i2)
    {
        
        if(i1.getFormat().equals(i2.getFormat()) 
                && i1.getHauteur() == i2.getHauteur()
                && i1.getLargeur() == i2.getLargeur())
        {
            for(int i =0;i<i1.getHauteur();i++)
                for(int j=0;j<i2.getLargeur();j++)
                    if(!i1.getMatrice()[i][j].egal(i1.getMatrice()[i][j]))
                        return false;
        }
        else
            return false;
        
        return true;
    }
    
     /**
     * Extrait un sous ensemble de l’image à partir du point p1,c1 jusqu’à p2,c2
     * @param i Image que l'on veut extraire le sous ensemble
     * @param x1 Coordonne en x du point d'origine du sous ensemble voulu
     * @param y1 Coordonne en y du point d'origine du sous ensemble voulu
     * @param x2 Coordonne en x du point de fin du sous ensemble voulu
     * @param y2 Coordonne en y du point de fin du sous ensemble voulu
     * @return Si les deux images sont identiques ou non
     */
    public static Image extraire(Image i, int x1, int y1, int x2, int y2)
    {
        //Calcul de la nouvelle hauteur et largeur
        int nouvHauteur = x2 - x1;
        int nouvLargeur = y2 - y1;
        
        //On crée la nouvelle image
        Image imageExt = new Image(i.getFormat(),nouvLargeur,nouvHauteur);
        
        //On copie les pixels de l'image dans la nouvelle
        for(int j =0;j<nouvHauteur;j++)
                for(int k=0;k<nouvLargeur;k++)
                    imageExt.getMatrice()[j][k] = i.getMatrice()[j+x1][k+y1];
        
        return imageExt;
        
    }
    
    /**
     * Réduit l'image de moitié en appliquant la moyenne des couleurs à chaque
     * quarts d'image réduit.
     */
    public static void reduire(Image i)
    {
        
    }
    
    /**
     * Fait pivoter l'image de 90 degrée vers la droite
     * @param img Image que l'on veut faire pivoter
     */
    public static void pivoter90(Image img)
    {
        Pixel[][] temp = new Pixel[img.getLargeur()][img.getHauteur()];
        Pixel[][] photo = img.getMatrice();

        for (int i = 0; i < img.getLargeur()-1; i++) 
        {
            for (int j = 0, z = img.getHauteur()-1; j < img.getHauteur()-1; j++,z--) 
            {
                temp[i][j] = photo[z][i];
            }
        }
        int hauteurTemp = img.getHauteur();
        img.setMatrice(temp);
        img.setHauteur(img.getLargeur()-1);
        img.setLargeur(hauteurTemp-1);
    }
}
