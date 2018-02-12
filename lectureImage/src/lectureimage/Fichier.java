/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectureimage;

import java.io.File;
import java.nio.file.Path;

/**
 *
 * @author Simon
 */
public class Fichier
{
    String type;
    String nomFichier;
    File fichier;

    public Fichier(String type, String nomFichier)
    {
        this.type = type;
        this.nomFichier = nomFichier;
        fichier = new File("src/Fichiers/" + nomFichier +"."+ type);
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
