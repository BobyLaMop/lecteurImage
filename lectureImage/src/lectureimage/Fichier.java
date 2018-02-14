/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectureimage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Simon
 */
public class Fichier
{
    private String type;
    private String nomFichier;
    private File fichier;
    private Charset charset;
    
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

    public Charset getCharset()
    {
        return charset;
    }

    public void setCharset(Charset charset)
    {
        this.charset = charset;
    }
    
    public Path getPath()
    {
        return Paths.get(fichier.getParent());
    }
    
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getNomFichier()
    {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier)
    {
        this.nomFichier = nomFichier;
    }

    public File getFichier()
    {
        return fichier;
    }

    public void setFichier(File fichier)
    {
        this.fichier = fichier;
    }

    
    
    
}
