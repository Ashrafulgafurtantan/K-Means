package KMean;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class test {

    public static void main(String[] args) throws IOException {

        BufferedImage img=null;
        img = ImageIO.read(new File("test.jpg"));
        Scanner sc = new Scanner(System.in);
        int number=3;
        System.out.println("Enter an integer");
        number= sc.nextInt();
        /*  public static */
        //  double[]lowest = new double[number];
        //  int[]size = new int[number];
//This part is usefull
     /*   int [] red=new int [number];
        red[0]=255;
        red[1]=0;
        red[2]=0;
        int [] green=new int [number];
        green[0]=0;
        green[1]=255;
        green[2]=0;
        int [] blue=new int [number];
        blue[0]=0;
        blue[1]=0;
        blue[2]=255;*/
//end usefull part

        int [] red=new int [number];
        int [] green=new int [number];
        int [] blue=new int [number];

/*

        red[0]=255;
        red[1]=0;
        red[2]=0;

        green[0]=0;
        green[1]=255;
        green[2]=0;

        blue[0]=0;
        blue[1]=0;
        blue[2]=255;
*/





        Random rand = new Random();

// Obtain a number between [0 - 255].

        System.out.println("RED");
        for(int i=0;i<number;i++)
        {
            int n = rand.nextInt(256);
            red[i]=n;
            System.out.println(n);
        }
        System.out.println("green");

        for(int i=0;i<number;i++)
        {
            int n = rand.nextInt(256);
            green[i]=n;
            System.out.println(n);

        }
        System.out.println("blue");

        for(int i=0;i<number;i++)
        {
            int n = rand.nextInt(256);
            blue[i]=n;
            System.out.println(n);

        }
        //  System.out.println("rand finish");
        int w = img.getWidth();
        File f=new File("Testresult.jpg");

        int h = img.getHeight();
        //  int imagArr[][]=new int [h][w];
        System.out.println(h +" height and weight is = "+w);
        // BufferedImage imge=null;
        /*int shit = h;
        h = w;
        w = shit;*/
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int pixel = img.getRGB(j, i);
                int alpha = (pixel >> 24) & 0xff;
                int Red = (pixel >> 16) & 0xff;
                int Green = (pixel >> 8) & 0xff;
                int Blue = (pixel) & 0xff;

                int lowestDistencePos=0;
                double testDis=9999;
                for(int k=0;k<number;k++)
                {
                    // System.out.println("for "+k);
                    int a=red[k]-Red;
                    int b=green[k]-Green;
                    int c=blue[k]-Blue;

                    // System.out.println("ans of subs= "+a+" "+b+" "+c);
                    //  System.out.println(Red+" "+Green+" "+Blue);

                    double cal =Math.sqrt(Math.pow(a,2) + Math.pow(b,2)  +Math.pow(c,2));
                    //  System.out.println("cal "+k +" = "+cal );
                    if(cal<testDis) {
                        lowestDistencePos = k;
                        testDis=cal;
                    }
                }
                System.out.println("lowest = "+lowestDistencePos);

                //  imagArr[i][j]=lowestDistencePos;
                //   System.out.println(red[lowestDistencePos]+" "+green[lowestDistencePos]+" "+blue[lowestDistencePos]);
                // int pix=alpha<<24|red[lowestDistencePos]<<16|green[lowestDistencePos]<<8|blue[lowestDistencePos];
                int pix=alpha<<24|red[lowestDistencePos]|green[lowestDistencePos]|blue[lowestDistencePos];

                //   System.out.println("pixel = "+pix);
                try {

                    //   img.setRGB(j,i,pix);
                    img.setRGB(j,i,new Color(red[lowestDistencePos],green[lowestDistencePos],blue[lowestDistencePos]).getRGB());
                  /*  if(lowestDistencePos==0)
                        img.setRGB(j,i,new Color(255,0,0).getRGB());
                    else if(lowestDistencePos==1)
                        img.setRGB(j,i,new Color(0,255,0).getRGB());

                    else
                        img.setRGB(j,i,new Color(0,0,255).getRGB());
*/

                } catch (ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("SHIT");
                    System.out.println(i + ", " + j + ", " + pix);
                    int alphay = (pix >> 24) & 0xff;
                    int Redy = (pix >> 16) & 0xff;
                    int Greeny = (pix >> 8) & 0xff;
                    int Bluey = (pix) & 0xff;
                    System.out.println(Redy + ", " + Greeny + ", " + Bluey);
                    e.printStackTrace();
                }
                //  System.out.println("lol 1 ");

            }
        }
        ImageIO.write(img, "jpg", f);




    }
}
