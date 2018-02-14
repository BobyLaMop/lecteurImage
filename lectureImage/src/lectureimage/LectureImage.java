/* En-tête du programme
====================================================================================================
Fichier:            LectureImage.java (TP1)
Auteur:             Simon Lagacé, Christopher True et Pierre-Luc Pépin
Date création:      01/02/2018
But:                Classe permettant de faire des tests pour les méthodes de la classe TraiteurImage
                    qui permet de manipuler des fichiers et images de type ppm et pgm
====================================================================================================
*/
package lectureimage;

import java.nio.charset.Charset;
import java.util.Objects;

/**
 * Classe de main pour les tests de manipulations de fichiers et 
 * d'images ppm et pgm
 * @author Simon Lagacé
 * @author Pierre-Luc Pépin
 * @author Christopher True
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
        Fichier testPpm = new Fichier("ppm","testPpm",Charset.forName("US-ASCII"));
        Fichier testPgm = new Fichier("pgm","testPgm",Charset.forName("US-ASCII"));
        Fichier testPpmPivot = new Fichier("ppm","testPpmPivot",Charset.forName("US-ASCII"));
        Fichier fichierPgmExtrait = new Fichier("pgm","imageExtraitPgm", Charset.forName("US-ASCII"));
        Image imageFrontenacPpm = new Image();
        Image imageFrontenacPpm2 = new Image();
        Image imageFrontenacPgm = new Image();
        Image imageExtrait = new Image();
        
        //Test de lecture
        TraiteurImage.lire(imageFrontenacPpm, fichierFrontenacPpm);
        TraiteurImage.lire(imageFrontenacPgm, fichierFrontenacPgm);
        
        
        //Test d'écriture
        TraiteurImage.ecrire(testPpm, imageFrontenacPpm);
        TraiteurImage.ecrire(testPgm, imageFrontenacPgm);
        
        //Test sont_identiques
        TraiteurImage.lire(imageFrontenacPpm2, testPpm);
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
        TraiteurImage.eclaircir_noircir(imageFrontenacPgm, 40);
        TraiteurImage.eclaircir_noircir(imageFrontenacPpm, -40);
        
        TraiteurImage.ecrire(testPgm,imageFrontenacPgm);
        TraiteurImage.ecrire(testPpm,imageFrontenacPpm);
        
        //Test pivoter fonctionne pas
        TraiteurImage.pivoter90(imageFrontenacPpm);
        TraiteurImage.ecrire(testPpmPivot, imageFrontenacPpm);
        
        //Test extraire
        imageExtrait = TraiteurImage.extraire(imageFrontenacPgm, 10, 10, 120, 120);
        TraiteurImage.ecrire(fichierPgmExtrait,imageExtrait);
    }
    
}
