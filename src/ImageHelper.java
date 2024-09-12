import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class ImageHelper {
    private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
    private static final String FILE_SEPERATOR = System.getProperty("file.separator");
	private static final String USER_DIR = System.getProperty("user.dir") + FILE_SEPERATOR;
    private static final String IMAGE_DIR = "images";
    
    /**
	* @param folder the name of subfolder under images
    * @param fileName the name including extension (ex: burn.png) 
	 */
    public static Image getImage(String folder, String fileName){
        String path = USER_DIR + IMAGE_DIR + FILE_SEPERATOR + folder + FILE_SEPERATOR + fileName;
        Image img =  TOOLKIT.getImage(path);
        if(img == null){
            System.err.println("No image found at: " + path);
        }
        return img;
    }   

    public static List<Image> getAnimation(String folderName){
        List<Image> animations = new ArrayList<>();
        String path = USER_DIR + IMAGE_DIR + FILE_SEPERATOR + folderName;
        
        File folder = new File(path);
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("The folder path not found: " + path);
            return animations;
        }

        File[] files = folder.listFiles();
        if(files == null){
            System.err.println("No files found in the folder: " + path);
            return animations;
        }
        
        for (File file : files) {
                // Check if the file is an image based on its extension
                if (isImageFile(file)) {
                    
                    // Load the image
                    Image image = TOOLKIT.getImage(file.getPath());
                    animations.add(image);
                }
        }

        return animations;
    }

    // Helper method to check if a file is an image
    public static boolean isImageFile(File file) {
        String[] imageExtensions = { ".jpg", ".jpeg", ".png"};
        String fileName = file.getName().toLowerCase();
        
        for (String extension : imageExtensions) {
            if (fileName.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

}

