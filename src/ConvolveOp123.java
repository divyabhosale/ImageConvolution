
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author divya_000
 */
public class ConvolveOp123 {
    public static void main(String[] args) throws IOException {
        int x=0,y=0;
        String path="C:\\Users\\divya_000\\Documents\\NetBeansProjects\\ImageConvulation\\src\\images\\pc.jpg";
    BufferedImage img = null;
    img = ImageIO.read(new File(path));
    int height=img.getHeight();
    byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
for(int i=0;i<img.getHeight();i++)
{
    System.out.println(pixels);
}
    }
}
//int blue[height];
/*int color =img.getRGB(x,y);
for(int i=0;i<img.getHeight();i++)
{
int blue =  color & 0xff;
int green = (color & 0xff00) >> 8;
int red = (color & 0xff0000) >> 16;
        System.out.println("\nRed="+red+"\nGreen="+green+"\nBlue="+blue);
    }*/
    
/*    import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class FastRGB
{

    private int width;
    private int height;
    private boolean hasAlphaChannel;
    private int pixelLength;
    private byte[] pixels;

    FastRGB(BufferedImage image)
    {

        pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        width = image.getWidth();
        height = image.getHeight();
        hasAlphaChannel = image.getAlphaRaster() != null;
        pixelLength = 3;
        if (hasAlphaChannel)
        {
            pixelLength = 4;
        }

    }

    int getRGB(int x, int y)
    {
        int pos = (y * pixelLength * width) + (x * pixelLength);

        int argb = -16777216; // 255 alpha
        if (hasAlphaChannel)
        {
            argb = (((int) pixels[pos++] & 0xff) << 24); // alpha
        }

        argb += ((int) pixels[pos++] & 0xff); // blue
        argb += (((int) pixels[pos++] & 0xff) << 8); // green
        argb += (((int) pixels[pos++] & 0xff) << 16); // red
        return argb;
    }
    
    }
    
}
*/