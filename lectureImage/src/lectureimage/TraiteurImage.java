/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectureimage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Simon
 */
public class TraiteurImage
{
    public static void lire(Image i, Fichier f)
    {
        try 
        {
            
            Scanner scan = new Scanner(f.getFichier());
            while(scan.hasNext())
            {
                //Lecture si c'est un fichier pgm
                if(scan.next().equals("P2"))
                {
                    //Lecture hauteur et largeur 
                    i = new Image("pgm",scan.nextInt(),scan.nextInt());
                    scan.nextInt();
                    //Lecture de la matrice
                    for(int j=0;j<i.getHauteur();j++)
                    {
                        for(int k=0;k<i.getLargeur();k++)
                        {
                            int valeur = scan.nextInt();
                            i.getMatrice()[j][k] = new Pixel(valeur,valeur,valeur);
                        }
                    }
                }
                //Lecture si c'est un fichier ppm
                else if (scan.next().equals("P3"))
                {
                    //Lecture hauteur et largeur 
                    i = new Image("ppm",scan.nextInt(),scan.nextInt());
                    scan.nextInt();
                    //Lecture de la matrice
                    for(int j=0;j<i.getHauteur();j++)
                    {
                        for(int k=0;k<i.getLargeur();k++)
                        {
                            i.getMatrice()[j][k] = new Pixel(scan.nextInt(),scan.nextInt(),scan.nextInt());
                        }
                    }
                }     
            }
            scan.close();
        }
        //Exception fichier non trouvé
        catch(FileNotFoundException ex) 
        {
            System.out.println("Impossible d'ouvrir le fichier '" + f.getNomFichier() + "'");                
        }
        //Autre exception
        catch(IOException ex) 
        {
            System.out.println("Erreur de lecture pour '" + f.getNomFichier() + "'");                  
        }
    }
    
    public static void ecrire(Fichier f, Image i)
    {
        
    }
    
}   
