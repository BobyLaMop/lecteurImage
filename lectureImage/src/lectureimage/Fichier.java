/* En-tête du programme
====================================================================================================
Fichier:            Fichier.java (TP1)
Auteur:             Simon Lagacé, Christopher True et Pierre-Luc Pépin
Date création:      01/02/2018
But:                Classe représentant un fichier avec un nom, un type, un charset ainsi que le
                    fichier en tant que tel représenté par un object File
====================================================================================================
*/
package lectureimage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classe qui représente un fichier de type pgm ou ppm. On y retrouve le nom
 * du fichier, son type, un charset ainsi que le fichier lui-même
 * @author Simon Lagacé
 */
public class Fichier
{
    private String type;        //Type de fichier (ppm ou pgm)
    private String nomFichier;  //Nom du fichier
    private File fichier;       //Fichier
    private Charset charset;    //Charset
    
    /**
     * Constructeur avec le type, le nom du fichier ainsi qu'un charset
     * @param type
     * @param nomFichier
     * @param charset
     */
    public Fichier(String type, String nomFichier, Charset charset)
    {
        this.type = type;
        this.nomFichier = nomFichier;
        this.charset = charset;
        //Crée le fichier
        //Si le fichier est déjà présent, il ne sera pas créé
        try
        {
            fichier = new File("src/Fichiers/" + nomFichier +"."+ type);
            //On crée le fichier si il n'est pas deja créer
            fichier.createNewFile();
            
        }
        catch(IOException ex)
        {
            System.out.println("Erreur de lecture pour '" + nomFichier +"."+type+ "'");
        }
     
    }

    /**
     * Getter pour le charset
     * @return Charset
     */
    public Charset getCharset()
    {
        return charset;
    }

    /**
     * Setter pour le charset
     * @param charset Charset voulu
     */
    public void setCharset(Charset charset)
    {
        this.charset = charset;
    }
    
    /**
     * Getter pour le type de fichier
     * @return Type de fichier
     */
    public String getType()
    {
        return type;
    }

    /**
     * Setter pour le type
     * @param type Type voulu
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Getter pour le nom du fichier
     * @return Nom du fichier
     */
    public String getNomFichier()
    {
        return nomFichier;
    }

    /**
     * Setter pour le nom du fichier
     * @param nomFichier Nom du fichier
     */
    public void setNomFichier(String nomFichier)
    {
        this.nomFichier = nomFichier;
    }

    /**
     * Getter pour le fichier
     * @return Fichier
     */
    public File getFichier()
    {
        return fichier;
    }

    /**
     * Setter pour le fichier
     * @param fichier Fichier
     */
    public void setFichier(File fichier)
    {
        this.fichier = fichier;
    }

    
    
    
}
