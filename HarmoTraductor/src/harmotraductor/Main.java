/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package harmotraductor;

import java.io.*;
import java.util.*;
/**
 *
 * @author alu03009
 */
public class Main {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        // TODO code application logic here
        String dir = "./docs/";
        File f = new File(dir);
        String[] salida;
        salida = f.list();
        String archivo = dir+salida[0];


          /*   int i = 0;

        for(i=0;i<salida.length;i++)
        {
            File r = new File(dir+salida[i]);
            if(r.isDirectory())
            {
                System.out.println("<DIR>"+salida[i]);
            }
 else
            {
                System.out.println("<FILE>"+salida[i]);

            }
        }*/

        try{
        FileReader cabezal = new FileReader(archivo);
        BufferedReader bf = new BufferedReader(cabezal);

        Partitura Cancion = new Partitura();
        int notatube;
        while((notatube = bf.read())!=-1){
        if(notatube!=32&&notatube!=41)
        {

            if(notatube==10)
            {
                System.out.println("Salto de Linea");
                Cancion.addnota(120);
            }
 else
            {
          if(notatube==40)
          {
              Cancion.addnota((-1)*(bf.read()-48));
          }
            else
          {
            Cancion.addnota(notatube-48);
          }

            }
        }
        }

        }
   	catch (Exception ex_3)
	{
	System.out.println (ex_3.getMessage());
        }




    }

}


class Partitura
{

   public ArrayList <Nota> notas = new <Nota> ArrayList();
   public ArrayList letra = new ArrayList();

   public void addnota (int a)
    {
       System.out.println("anadida -> "+a);
       Nota note = new Nota(a);
       notas.add(note);
     }

   public void deletenota (int index)
    {
       notas.remove(index);
    }


   public void duplinota(int index)
    {
       Nota dupli;
       dupli = notas.get(index);
       notas.add(index,dupli);
    }

}

class Nota
{
    int numeronota;
    int modificador;

    Nota(int a)
    {
        if(a>0)
        {
            numeronota=a;
            modificador=0;
        }
        else
        {
            numeronota=(a*-1);
            modificador=-1;
        }
    }
}
