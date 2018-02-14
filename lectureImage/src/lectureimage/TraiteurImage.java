/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Simon
 */
public class TraiteurImage
{

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

    public static void ecrire(Fichier f, Image i)
    {
        System.out.println(i.getFormat());
        System.out.println(f.getType());
        //Si le fichier et l'image sont de même format 
        if (f.getType().equals(i.getFormat()))
        {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(f.getFichier())))//Files.newBufferedWriter(f.getPath(), charset))
            {
                if (i.getFormat().equals("pgm"))
                {
                    //Entete du fichier
                    writer.write("P2", 0, 2);
                    writer.newLine();
                    writer.write(Integer.toString(i.getHauteur()), 0, Integer.toString(i.getHauteur()).length());
                    writer.write(" ", 0, 1);
                    writer.write(Integer.toString(i.getLargeur()), 0, Integer.toString(i.getHauteur()).length());
                    writer.newLine();
                    writer.write("255", 0, 3);
                    writer.newLine();

                    //Pixels
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
                    //Entete du fichier
                    writer.write("P3", 0, 2);
                    writer.newLine();
                    writer.write(Integer.toString(i.getHauteur()), 0, Integer.toString(i.getHauteur()).length());
                    writer.write(" ", 0, 1);
                    writer.write(Integer.toString(i.getLargeur()), 0, Integer.toString(i.getHauteur()).length());
                    writer.newLine();
                    writer.write("255", 0, 3);
                    writer.newLine();

                    //Pixels
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

    public static boolean sont_identique(Image i1, Image i2)
    {
        return i1.getFormat().equals(i2.getFormat()) && i1.getHauteur() == i2.getHauteur()
                && i1.getLargeur() == i2.getLargeur() && Arrays.deepEquals(i1.getMatrice(), i2.getMatrice());
    }

}
