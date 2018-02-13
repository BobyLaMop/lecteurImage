/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectureimage;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
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
            while (scan.hasNext())
            {
                //Lecture si c'est un fichier pgm
                if (scan.next().equals("P2"))
                {
                    //Lecture hauteur et largeur 
                    i = new Image("pgm", scan.nextInt(), scan.nextInt());
                    scan.nextInt();
                    //Lecture de la matrice
                    for (int j = 0; j < i.getHauteur(); j++)
                    {
                        for (int k = 0; k < i.getLargeur(); k++)
                        {
                            int valeur = scan.nextInt();
                            i.getMatrice()[j][k] = new Pixel(valeur, valeur, valeur);
                        }
                    }
                } //Lecture si c'est un fichier ppm
                else if (scan.next().equals("P3"))
                {
                    //Lecture hauteur et largeur 
                    i = new Image("ppm", scan.nextInt(), scan.nextInt());
                    scan.nextInt();
                    //Lecture de la matrice
                    for (int j = 0; j < i.getHauteur(); j++)
                    {
                        for (int k = 0; k < i.getLargeur(); k++)
                        {
                            i.getMatrice()[j][k] = new Pixel(scan.nextInt(), scan.nextInt(), scan.nextInt());
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

    public static void ecrire(Fichier f, Image i)
    {
        Charset charset = Charset.forName("US-ASCII");
        String s = "test";
        try (BufferedWriter writer = Files.newBufferedWriter(f.getPath(), charset))
        {
            
            writer.write(s, 0, s.length());
        } 
        catch (IOException x)
        {
            System.err.format("IOException: %s%n", x);
        }
    }

    public static boolean sont_identique(Image i1, Image i2)
    {
        return i1.getFormat().equals(i2.getFormat()) && i1.getHauteur() == i2.getHauteur()
                && i1.getLargeur() == i2.getLargeur() && Arrays.deepEquals(i1.getMatrice(), i2.getMatrice());
    }

}
