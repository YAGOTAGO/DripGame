import java.awt.Image;
import java.awt.Toolkit;

public class ImageRequest {
    protected final Toolkit toolkit = Toolkit.getDefaultToolkit();
    protected final String fileSeparator = System.getProperty("file.separator");
	protected final String userDir = System.getProperty("user.dir") + fileSeparator;
    protected final String imageDir = "images";
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
        return switch (ext) {
            case PNG -> ".png";
            case JPEG -> ".jpeg";
            default -> null;
        };
    }
}

