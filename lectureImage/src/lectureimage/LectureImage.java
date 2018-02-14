/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectureimage;

import java.nio.charset.Charset;
import java.util.Objects;

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
        Fichier fichierFrontenacPpm = new Fichier("ppm","Sherbrooke_Frontenac_nuit",Charset.forName("US-ASCII"));
        Fichier fichierFrontenacPgm = new Fichier("pgm","Sherbrooke_Frontenac_nuit",Charset.forName("US-ASCII"));
        Fichier fichierPpmTest = new Fichier("ppm","testPpm",Charset.forName("US-ASCII"));
        Fichier fichierPgmTest = new Fichier("pgm","testPgm",Charset.forName("US-ASCII"));
        Image imageFrontenacPpm = new Image();
        Image imageFrontenacPpm2 = new Image();
        Image imageFrontenacPgm = new Image();
        
        //Test de lecture
        TraiteurImage.lire(imageFrontenacPpm, fichierFrontenacPpm);
        TraiteurImage.lire(imageFrontenacPgm, fichierFrontenacPgm);
        
        
        //Test d'écriture
        TraiteurImage.ecrire(fichierPpmTest, imageFrontenacPpm);
        TraiteurImage.ecrire(fichierPgmTest, imageFrontenacPgm);
        
        //Test sont_identiques
        TraiteurImage.lire(imageFrontenacPpm2, fichierPpmTest);
        if(TraiteurImage.sont_identique(imageFrontenacPpm, imageFrontenacPpm2))
        {
            System.out.println("Les deux images sont identiques!");
        }
        else
        {
            System.out.println("Les deux images ne sont pas identiques!");
        }
        
        //Test couleur preponderante
        Pixel couleur = TraiteurImage.couleur_preponderante(imageFrontenacPpm);
        System.out.println(Integer.toString(couleur.getRouge()) + " " + Integer.toString(couleur.getVert()) + " " + Integer.toString(couleur.getBleu()));
        
        //Test eclaircir_noircir
        TraiteurImage.eclaircir_noircir(imageFrontenacPgm, 80);
        TraiteurImage.eclaircir_noircir(imageFrontenacPpm, -90);
        
        TraiteurImage.ecrire(fichierPgmTest,imageFrontenacPgm);
        TraiteurImage.ecrire(fichierPpmTest,imageFrontenacPpm);
        //Test pivoter fonctionne pas
        //TraiteurImage.pivoter90(imageFrontenacPpm);
    }
    
}
