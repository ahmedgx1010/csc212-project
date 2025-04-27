import java.util.LinkedList;

public class PhotoManager {
    LinkedList<Photo> photos;

    public PhotoManager(){
        photos = new LinkedList<>();
    }
    // Return all managed photos
    public LinkedList<Photo> getPhotos();
    // Add a photo
    public void addPhoto(Photo p);
    // Delete a photo
    public void deletePhoto(String path);
}