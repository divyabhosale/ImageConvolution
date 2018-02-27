
import java.awt.color.ICC_Profile;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.awt.image.ImagingOpException;
import java.awt.image.IndexColorModel;
import java.awt.image.Kernel;
import java.awt.image.Raster;
import java.awt.image.RasterOp;
import java.awt.image.WritableRaster;
import java.lang.annotation.Native;
import sun.awt.image.ImagingLib;


public class ConvolveOp1 implements BufferedImageOp {
    Kernel kernel;
    int edgeHint;
    RenderingHints hints;
    
    public int EDGE_ZERO_FILL = 0;
    
    public int EDGE_NO_OP = 1;

   
    public ConvolveOp1(Kernel kernel, int edgeCondition, RenderingHints hints) {
        this.kernel = kernel;
        this.edgeHint = edgeCondition;
        this.hints = hints;
    }

   
    public ConvolveOp1(Kernel kernel) {
        this.kernel = kernel;
        this.edgeHint = EDGE_ZERO_FILL;
    }

 
    public int getEdgeCondition() {
        return edgeHint;
    }

    public final Kernel getKernel() {
        return (Kernel) kernel.clone();
    }

   
    public final BufferedImage filter(BufferedImage src, BufferedImage dst) {
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        if (src == dst) {
            throw new IllegalArgumentException("src image cannot be the "
                    + "same as the dst image");
        }

        boolean needToConvert = false;
        ColorModel srcCM = src.getColorModel();
        ColorModel dstCM;
        BufferedImage origDst = dst;

        // Can't convolve an IndexColorModel.  Need to expand it
        if (srcCM instanceof IndexColorModel) {
            IndexColorModel icm = (IndexColorModel) srcCM;
            src = icm.convertToIntDiscrete(src.getRaster(), false);
            srcCM = src.getColorModel();
        }

        if (dst == null) {
            dst = createCompatibleDestImage(src, null);
            dstCM = srcCM;
            origDst = dst;
        } else {
            dstCM = dst.getColorModel();
            if (srcCM.getColorSpace().getType() != dstCM.getColorSpace()
                    .getType()) {
                needToConvert = true;
                dst = createCompatibleDestImage(src, null);
                dstCM = dst.getColorModel();
            } else if (dstCM instanceof IndexColorModel) {
                dst = createCompatibleDestImage(src, null);
                dstCM = dst.getColorModel();
            }
        }

        if (ImagingLib.filter(this, src, dst) == null) {
            throw new ImagingOpException("Unable to convolve src image");
        }

        if (needToConvert) {
            ColorConvertOp ccop = new ColorConvertOp(hints);
            ccop.filter(dst, origDst);
        } else if (origDst != dst) {
            java.awt.Graphics2D g = origDst.createGraphics();
            try {
                g.drawImage(dst, 0, 0, null);
            } finally {
                g.dispose();
            }
        }

        return origDst;
    }

  
   
    public final RenderingHints getRenderingHints() {
        return hints;
    }

    
    @Override
    public Rectangle2D getBounds2D(BufferedImage src) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  
        BufferedImage image;

        int w = src.getWidth();
        int h = src.getHeight();

        WritableRaster wr = null;

        if (destCM == null) {
            destCM = src.getColorModel();
            // Not much support for ICM
            if (destCM instanceof IndexColorModel) {
                destCM = ColorModel.getRGBdefault();
            } else {
                /* Create destination image as similar to the source
                 *  as it possible...
                 */
                wr = src.getData().createCompatibleWritableRaster(w, h);
            }
        }

        if (wr == null) {
            
            wr = destCM.createCompatibleWritableRaster(w, h);
        }

        image = new BufferedImage(destCM, wr,
                destCM.isAlphaPremultiplied(), null);

        return image;
    
    }

    @Override
     public Point2D getPoint2D(Point2D srcPt, Point2D dstPt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
