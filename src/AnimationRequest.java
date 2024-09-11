import java.awt.Image;

public class AnimationRequest extends ImageRequest {

    private Image[] frames;
    
    /**
     * 
     * @param folder name of folder under IMAGES folder
     * @param fileName  examples: coin1 ... coin10
     * @param extension examples: ".png", ".jpeg"
     * @param maxFrames size of the animation
     */
    public AnimationRequest(String folder, String fileName, FileExtension extension, int maxFrames) {
        super(folder, fileName, extension);
        frames = new Image[maxFrames];
        init();
    }
        
    private void init(){
        for(int i=0; i<frames.length; i++){
            frames[i] = getImage(i+1);
        }
    }

    public Image[] getFrames(){ return frames; }
}

