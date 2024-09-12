import java.awt.Image;
import java.awt.Toolkit;

public final class ImageHelper {
    protected static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    protected static final String fileSeparator = System.getProperty("file.separator");
	protected static final String userDir = System.getProperty("user.dir") + fileSeparator;
    protected static final String imageDir = "images";
    /**
	* @param folder the name of subfolder under images
    * @param fileName the name including extension (ex: burn.png) 
	 */
    public static Image getImage(String folder, String fileName){
        return toolkit.getImage(userDir + imageDir + fileSeparator + folder + fileSeparator + fileName);
    }   

}

