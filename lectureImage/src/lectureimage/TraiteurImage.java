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
        //Si le fichier et l'image sont de même format 
        if (f.getType().equals(i.getFormat()))
        {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(f.getFichier())))//Files.newBufferedWriter(f.getPath(), charset))
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
    
    public static void copier(Image i1, Image i2)
    {
        i2.setFormat(i1.getFormat());
        i2.setHauteur(i1.getHauteur());
        i2.setLargeur(i1.getLargeur());
        i2.setMatrice(i1.getMatrice());
    }
    
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
    
    public static void eclaircir_noircir(Image i, int v)
    {
       for(int j =0;j<i.getHauteur();j++)
            for(int k=0;k<i.getLargeur();k++)
            {
                i.getMatrice()[j][k].changerCouleur(v);
            } 
    }
    
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
    
    public static void pivoter90(Image img)
    {
        Pixel[][] temp = new Pixel[img.getLargeur()][img.getHauteur()];
        Pixel[][] photo = img.getMatrice();

        for (int i = 0; i < img.getLargeur(); i++) 
        {
            for (int j = 0, z = img.getHauteur(); j < img.getHauteur(); j++,z--) 
            {
                temp[i][j] = photo[z][i];
            }
        }
        img.setMatrice(temp);
    }
}
