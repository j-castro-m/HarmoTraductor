/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package harmotraductor;

import java.io.*;
import java.util.*;
import java.awt.Component.*;
import java.awt.TextField.*;
import javax.swing.JFrame.*;
import javax.swing.BoxLayout.*;

/**
 *
 * @author Joel Castro
 */

 /**
 *  Es la que almacena internamente las notas y en un futuro la cancion
 */
public class Partitura
{

   /** ArrayList que representa la coleccion de todas las notas de la cancion */
   public ArrayList <Nota> notas = new <Nota> ArrayList();
   /** ArrayList que en una futura version representará la letra de la cancion */
   public ArrayList letra = new ArrayList();

     /**
     * Metodo que carga notas del archivo a la Partitura
     * @param archivo es el valor de la nota, a partir de la cual, devuelve la ruta
     * @param cod entero que representa la simbologia de notacion
     * @return int devuelve el entero de codificacion, para que se mantenga
     */
   public int cargardeArchivo(String archivo, int cod)
    {
       try{

            FileReader cabezal = new FileReader(archivo);
            BufferedReader bf = new BufferedReader(cabezal);


            int notatube;
            while((notatube = bf.read())!=-1){

            if(notatube!=32&&notatube!=41)
            {

                if(notatube==10)
                {
                    this.addnota(120);
                }
                else
                {
                    if(notatube==40)
                    {
                        this.addnota((-1)*(bf.read()-48));
                        cod = 1;
                    }
                    else
                    {

                        if(notatube==45)
                        {
                            this.addnota((-1)*(bf.read()-48));
                            cod = 0;
                        }
                        else
                        {
                            this.addnota(notatube-48);
                        }
                    }
                }
            }
        }
            bf.close();
        }
   	catch (Exception ex_3)
	{
            System.out.println (ex_3.getMessage());
        }

       return cod;
   }


     /**
     * Metodo que devuelve el numero de filas de la cancion
     * @return int que respresenta el numero de filas
     */
   public int filasCancion()
    {
       int num = 0 ;
       for(int i = 0; i<notas.size();i++)
       {
           if(((notas.get(i)).getnum())==(120))
           {
               num=num+1;
           }

       }
     return (num-1);
    }

     /**
     * Metodo que devuelve el numero de columnas de la cancion
     * @return int representa el numero de columnas
     */
   public int columnasCancion()
    {
       int num = 0 ;
       int numax = 0;
       for(int i = 0; i<notas.size();i++)
       {
               if(((notas.get(i)).getnum())==120)
               {
                   num=0;
               }
              else
               {
                   num=num+1;
                   if(num>=numax)
                   {
                       numax=num;
                   }
               }
       }
     return numax;
   }

     /**
     * Metodo añade una nota ala partitura
     * @param a es el valor de la nota, la cual añade
     */
   public void addnota (int a)
    {
       //System.out.println("anadida -> "+a);
       Nota note = new Nota(a);
       notas.add(note);
     }

     /**
     * Metodo que sobreescribe toString()
     * @return String representa la partitura de la cancion en notacion matematica
     */
    public String toString()
    {
        /** Cadena auxiliar */
       String prev = notas.toString();
       prev = prev.replace("[\n,","");
       prev = prev.replace("[","");
       prev = prev.replace("  ", " ");
       prev = prev.replace("\n ", "\n");
       prev = prev.replace(',',' ');
       prev = prev.replace("]","");
       prev = prev.replace("\n]","");
       return prev;
   }

     /**
     * Metodo que sobreescribe toString()
     * @return String representa la partitura de la cancion en notacion de parentesis
     */
   public String toString2()
    {
       /** ArrayList Auxiliar para la conversion */
       ArrayList <String> salida = new <String> ArrayList();

       for(int i = 0;i<notas.size();i++)
       {
           salida.add((notas.get(i)).toString2());
       }

       String prev = new String();
       prev = salida.toString();
       prev = prev.replace("[\n,","");
       prev = prev.replace("[","");
       prev = prev.replace("\n ", "\n");
       prev = prev.replace("  ", " ");
       prev = prev.replace(',',' ');
       prev = prev.replace("]","");
       prev = prev.replace("\n]","");
       return prev;
   }


}
