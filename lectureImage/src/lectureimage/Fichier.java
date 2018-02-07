/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectureimage;

import java.io.File;

/**
 *
 * @author Simon
 */
public class Fichier
{
    String type;
    File fichier;

    public Fichier(String type, File fichier)
    {
        this.type = type;
        this.fichier = fichier;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
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
