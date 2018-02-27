
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class ImageConvolution {
    public static void main(String[] args) throws IOException {
        
    String path="C:\\Users\\divya_000\\Documents\\NetBeansProjects\\ImageConvulation\\src\\images\\pc.jpg";
    BufferedImage img = null;
    img = ImageIO.read(new File(path));
    
    
    float[] SHARPEN3x3 = {
                            0.f, -1.f, 0.f,
                            -1.f, 5.0f, -1.f,
                            0.f, -1.f, 0.f};
    
BufferedImage dstbimg = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
Kernel kernel = new Kernel(3,3,SHARPEN3x3);
ConvolveOp cop = new ConvolveOp(kernel,ConvolveOp.EDGE_NO_OP,null);
BufferedImage output= cop.filter(img,null);

    File outputfile = new File("C:\\Users\\divya_000\\Documents\\NetBeansProjects\\ImageConvulation\\src\\images\\pc1.jpg");
    ImageIO.write(output, "gif", outputfile); // Write the Buffered Image into an output file
   
    
    
int x=10,y=20;    
int color =img.getRGB(x,y);
int blue =  color & 0xff;
int green = (color & 0xff00) >> 8;
int red = (color & 0xff0000) >> 16;
        System.out.println("\nRed="+red+"\nGreen="+green+"\nBlue="+blue);
    }
}

