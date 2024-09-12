import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public final class RotationHelper {
    
    static AffineTransform tx;
    static AffineTransformOp op;
    static double locationX;
    static double locationY;
    static BufferedImage rotatedImage;
    static final Map<Double, BufferedImage> flameOnCache = new HashMap<>();
    static final Map<Double, BufferedImage> flameOffCache = new HashMap<>();
    
    public static BufferedImage getRotatedBufferedImage(BufferedImage img, double angleChange, double angle, boolean isFlameOn){
        Map<Double, BufferedImage> imageCache = isFlameOn ? flameOnCache : flameOffCache;
        if (imageCache.containsKey(angle)) {
            return imageCache.get(angle);
        }

        locationX = img.getWidth() / 2;
        locationY = img.getHeight() / 2;
        tx = AffineTransform.getRotateInstance(Math.toRadians(angleChange), locationX, locationY);
        op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
        rotatedImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        op.filter(img, rotatedImage);

        //cache
        imageCache.put(angle, rotatedImage);

        return rotatedImage;    
    }   
    
    // Normalize the angle to be within the range [0, 360)
    public static double normalizeAngle(double angle) {
        angle %= 360;
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

}
