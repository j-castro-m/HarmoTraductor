/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package harmotraductor;

import javax.swing.*;
import java.awt.Component.*;
import java.awt.TextField.*;
import javax.swing.JFrame.*;
import javax.swing.BoxLayout.*;


/**
 *
 * @author Joel Castro
 */

/**
 *
 * Es la clase principal, a partir de la cual se ejecuta el resto
 */
public class Main {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {

        Formulario form = new Formulario("HarmoTraductor");
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.cargainicial();



    }

}