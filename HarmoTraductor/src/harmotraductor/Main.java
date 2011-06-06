/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package harmotraductor;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.TextField.*;
import javax.swing.JFrame.*;
import javax.swing.BoxLayout.*;
import javax.swing.filechooser.*;


/**
 *
 * @author Joel Castro Mur
 */



class formulario extends JFrame implements ActionListener
{
    JTextArea notastxt = new JTextArea(10,32);
    String texto = " ";
    JPanel cajaNotas = new JPanel();
    JPanel zonaNorte = new JPanel();
    JPanel menuBotones = new JPanel();
    JPanel zonaTexto = new JPanel();
    JLabel codLabel = new JLabel("");
    JButton actualizar = new JButton();
    BorderLayout entera = new BorderLayout();
    public ArrayList <JLabel> imnotas = new <JLabel> ArrayList(160);
    Partitura Cancion = new Partitura();
    File ArchivoSeleccionado = new File(" ");

    //Creamos la barra de menus
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuArchivo, menuCod;

        JMenuItem abrir, guardar, guardarComo, exportar, salir;
        JMenuItem simbolos, parentesis;

    formulario(String a)
    {
        super(a);
    }


   private void ponerMenu()
    {
        //creamos los menus y los añadimos a la barra de menu
        menuArchivo=new JMenu("Archivo");
        barraMenu.add(menuArchivo);
        menuCod=new JMenu("Codificacion");
        barraMenu.add(menuCod);


        //Botones del Menu Archivo
        abrir= new JMenuItem("Abrir");
        abrir.setToolTipText("Permite abrir un fichero de texto");
        abrir.addActionListener(this);
        menuArchivo.add(abrir);

        guardar=new JMenuItem("Guardar/Actualizar");
        guardar.setToolTipText("Guarda y actualiza el fichero de texto");
        guardar.addActionListener(this);
        menuArchivo.add(guardar);

        guardarComo=new JMenuItem("Guardar como...");
        guardarComo.setToolTipText("Guarda el texto con el nombre que quieras");
        guardarComo.addActionListener(this);
        menuArchivo.add(guardarComo);

        exportar=new JMenuItem("Exportar a gif");
        exportar.setToolTipText("Exporta la partitura a un GIF");
        exportar.addActionListener(this);
        exportar.setEnabled(false);
        menuArchivo.add(exportar);

        salir=new JMenuItem("Salir");
        salir.setToolTipText("Crea un nuevo archivo de texto vacio");
        salir.addActionListener(this);
        menuArchivo.add(salir);

        //Botones del Menu Codificacion
        parentesis= new JMenuItem("Parentesis");
        parentesis.setToolTipText("(5),5,...");
        parentesis.addActionListener(this);
        menuCod.add(parentesis);

        simbolos= new JMenuItem("Simbolos Matematicos");
        simbolos.setToolTipText("-5,5,...");
        simbolos.addActionListener(this);
        menuCod.add(simbolos);


        //asignamos la barra de menus al frame
        this.setJMenuBar(barraMenu);
    }

    String devPath(int a)
    {
        String imagename = new String();
        if(a>0)
        {
            imagename = a+".gif";
            System.out.println(imagename);
        }
        else
        {
            imagename = "("+a+").gif";
            System.out.println(imagename);
        }

        return imagename;
    }


    void crearimages(ArrayList a)
    {
        System.out.println(a.size()+" "+imnotas.size());
        for(int i=0;i<a.size();i++)
        {
           imnotas.add(new JLabel(""+((Nota)(a.get(i))).getnum()));
           (imnotas.get(i)).setIcon(new ImageIcon(getClass().getResource((((Nota)(a.get(i))).getpath()))));
        }
    }

    void dentrotexto(String a)
       {
             notastxt.setText(a) ;
       }

    void cargainicial()
    {
        
        codLabel.setText("Necesita introducir un archivo");
        actualizar.setIcon(new ImageIcon(getClass().getResource("refresh.gif")));
        actualizar.setBorderPainted(false);
        actualizar.setMargin(insets());
        actualizar.setActionCommand("bactualizar");
        actualizar.addActionListener(this);

        this.ponerMenu();
        this.setLayout(new BorderLayout());
        this.add(zonaNorte, BorderLayout.NORTH);
        this.add(cajaNotas, BorderLayout.CENTER);

        zonaNorte.setLayout(new BorderLayout());
        zonaNorte.add(zonaTexto, BorderLayout.CENTER);

        zonaTexto.setLayout(new FlowLayout());
        zonaTexto.add(notastxt);
        

        menuBotones.setLayout(new BorderLayout());
        menuBotones.add(actualizar,BorderLayout.WEST);
        menuBotones.add(codLabel,BorderLayout.CENTER);

        zonaNorte.add(menuBotones, BorderLayout.NORTH);
        cajaNotas.setLayout(new GridLayout(10,16));

        for (int i = 0; i<160;i++)
        {
            JLabel a = new JLabel();
            a.setIcon(new ImageIcon(getClass().getResource("120.gif")));
            a.setDisplayedMnemonic(i);
            imnotas.add(a);
        }
        
      for(int j=0;j<16;j++)
        {
            for(int i=0;i<10;i++)
            {
                cajaNotas.add(imnotas.get((j*10)+i));
               System.out.println("etiq x: "+i+" y: "+j);
            }
        }
        
        this.setSize(1000, 700);
        
        this.setVisible(true);

    }
    void cargar(Partitura a,int b)
    {
            

        if(b==1)
        {
          codLabel.setText("Codificacion: Parentesis");
          notastxt.getText();
          notastxt.setText(a.toString2());
        }
        else
        {
          codLabel.setText("Codificacion: matemática");
          notastxt.getText();
          notastxt.setText(a.toString());
        }


        int j = 0;
        int i = 0;
        int z = 0;
        int actual = 0;

            for(i=0;i<Cancion.notas.size();i++)
            {
                String bf = Cancion.notas.get(i).imagename;
               
              (imnotas.get((j*16)+z)).setIcon(new ImageIcon(getClass().getResource(bf)));
              System.out.println("Redibujando: "+bf+"  --->"+((j*16)+z) );
               z++;
               if(Cancion.notas.get(i).getnum()==120)
               {
                   j++;
                   z=0;
               }
            }


        this.setSize(1000, 700);
        this.setVisible(true);
    }


     public void actionPerformed(ActionEvent evento)
     {

     String command=evento.getActionCommand();

         if(command.equals("Abrir"))
         {
            JFileChooser fc= new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.addChoosableFileFilter( new FileNameExtensionFilter("Archivos de texto (.txt)", "txt"));
            fc.showOpenDialog(this);
            ArchivoSeleccionado = fc.getSelectedFile();
            
  
                int cod = 0;
                Cancion.erase();
                cod = Cancion.cargardeArchivo(ArchivoSeleccionado.getPath(), 1);

                this.cargar(Cancion,cod);
                //this.repaint();



         }
        else if(command.equals("Guardar/Actualizar"))
         {
                try{
                int cod = 0;
                FileWriter guardador = new FileWriter(ArchivoSeleccionado);
                guardador.write(notastxt.getText(),0,(notastxt.getText()).length());
                guardador.close();
                Cancion.erase();
                cod = Cancion.cargardeArchivo(ArchivoSeleccionado.getPath(), 1);

                this.cargar(Cancion,cod);
                
                }
                catch (Exception ex_3)
                {
                    System.out.println (ex_3.getMessage());
                }


                System.out.println("Archivo "+ArchivoSeleccionado.getName()+" guardado: "+notastxt.getText());
         }
              else if(command.equals("Guardar como..."))
         {

            JFileChooser fc= new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.addChoosableFileFilter( new FileNameExtensionFilter("Archivos de texto (.txt)", "txt"));
            fc.showSaveDialog(this);
            ArchivoSeleccionado=fc.getSelectedFile();

            PrintWriter escritor;
            try
            {
                escritor =new PrintWriter(new FileWriter(ArchivoSeleccionado));
                notastxt.write(escritor);
            }
            catch(Exception e)
            {}
         }
              else if(command.equals("Exportar a gif"))
         {

                /*
                 * BufferedImage image1 has size[100, 200]
                   BufferedImage image2 has size[150, 150]
                   int w = image1.width + image2.width;
                   int h = Math.max(image1.height, image2.height);
                   BufferedImage image = new BufferedImage(w, h, yourType);
                   Graphics2D g2 = image.createGraphics();
                   g2.drawImage(image1, 0, 0, imageObserver);
                   g2.drawImage(image2, 100, 25, imageObserver);
                   g2.dispose();
                 */
         }

         else if(command.equals("Salir"))
         {

                System.exit(0);
         }

              else if(command.equals("bactualizar"))
         {

                try{
                int cod = 0;
                FileWriter guardador = new FileWriter(ArchivoSeleccionado);
                guardador.write(notastxt.getText(),0,(notastxt.getText()).length());
                guardador.close();
                Cancion.erase();
                cod = Cancion.cargardeArchivo(ArchivoSeleccionado.getPath(), 1);

                this.cargar(Cancion,cod);

                }
                catch (Exception ex_3)
                {
                    System.out.println (ex_3.getMessage());
                }
         }

         else if(command.equals("Parentesis"))
         {
                String nuevanotas = new String();
                nuevanotas = notastxt.getText();
                nuevanotas = nuevanotas.replace("-10","(10)");
                nuevanotas = nuevanotas.replace("-9","(9)");
                nuevanotas = nuevanotas.replace("-8","(8)");
                nuevanotas = nuevanotas.replace("-7","(7)");
                nuevanotas = nuevanotas.replace("-6","(6)");
                nuevanotas = nuevanotas.replace("-5","(5)");
                nuevanotas = nuevanotas.replace("-4","(4)");
                nuevanotas = nuevanotas.replace("-3","(3)");
                nuevanotas = nuevanotas.replace("-2","(2)");
                nuevanotas = nuevanotas.replace("-1","(1)");
                notastxt.setText(nuevanotas);
                codLabel.setText("Codificacion: Parentesis");
         }

         else if(command.equals("Simbolos Matematicos"))
         {
                String nuevanotas = new String();
                nuevanotas = notastxt.getText();
                nuevanotas = nuevanotas.replace("(","-");
                nuevanotas = nuevanotas.replace(")","");
                notastxt.setText(nuevanotas);
                codLabel.setText("Codificacion: matemática");

         }
}

}

class Partitura
{

   public ArrayList <Nota> notas = new <Nota> ArrayList();
   public ArrayList letra = new ArrayList();


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
                    System.out.println("Salto de Linea");
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

   public void erase()
    {
       notas.clear();
   }

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


   public void addnota (int a)
    {
       //System.out.println("anadida -> "+a);
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


    public String toString()
    {
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
    
   public String toString2()
    {
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







class Nota
{
    int numeronota;
    int modificador;
    String imagename;

    Nota(int a)
    {
        if(a>0)
        {
            numeronota=a;
            modificador=1;
            imagename = numeronota+".gif";
            System.out.println(imagename);
        }
        else
        {
            numeronota=(a*-1);
            modificador=-1;
            imagename = "("+numeronota+").gif";
            System.out.println(imagename);
        }
    }

    public String getpath()
    {
        return imagename;
    }


    public int getnum()
    {
        return numeronota;
    }

    public String toString()
    {
        if(this.numeronota==120)
        {
            return "\n";
        }
        else
        {
            if(this.modificador==-1)
            {
                return "-"+this.numeronota;
            }
            else
            {
                return String.valueOf(this.numeronota);
            }
        }
    }


        public String toString2()
    {
        if(this.numeronota==120)
        {
            return "\n";
        }
        else
        {
            if(this.modificador==-1)
            {
                return "("+this.numeronota+")";
            }
            else
            {
                return ""+this.numeronota;
            }
        }
    }
}

public class Main {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {

        formulario form = new formulario("HarmoTraductor");
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //form.ArchivoSeleccionado = new File("");
        form.cargainicial();



    }

}