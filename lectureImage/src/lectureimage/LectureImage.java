/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectureimage;

/**
 *
 * @author Simon
 * @author Pierre-Luc
 * @author Mr. True
 */
public class LectureImage
{
//Ta mère
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Fichier fichierFrontenac = new Fichier("ppm","Sherbrooke_Frontenac_nuit");
        Image imageFrontenac = new Image();
        TraiteurImage.lire(imageFrontenac, fichierFrontenac);
        
        Fichier test = new Fichier("ppm","test1");
        
    }
    
}
