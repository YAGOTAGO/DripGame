import java.awt.Image;
import java.awt.Toolkit;

public class ImageRequest {
    protected static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    protected static final String fileSeparator = System.getProperty("file.separator");
	protected static final String userDir = System.getProperty("user.dir") + fileSeparator;
    protected static final String imageDir = "images";
    protected String extension = "";

    protected String folder = "";
    protected String fileName;

    public ImageRequest(String folder, String fileName, FileExtension extension){
        this.folder = folder;
        this.fileName = fileName;
        this.extension = getExtension(extension);
    }

    public Image getImage(){
        return toolkit.getImage(userDir + imageDir + fileSeparator + folder + fileSeparator + fileName + extension);
    }   

    public Image getImage(int num){
        String index = String.valueOf(num);
        return toolkit.getImage(userDir + imageDir + fileSeparator + folder + fileSeparator + fileName + index + extension);
    }   
   
    private String getExtension(FileExtension ext){
        switch (ext) {
            case PNG:
                return ".png";
            case JPEG:
                return ".jpeg";
            default:
                return null;
        }
    }
}

