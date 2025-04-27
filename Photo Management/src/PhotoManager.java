import java.util.LinkedList;

public class PhotoManager {
    LinkedList<Photo> photos;

    public PhotoManager(){
        photos = new LinkedList<Photo>();
    }
    // Return all managed photos
    public LinkedList<Photo> getPhotos(){
        return photos;
    }
    // Add a photo
    public void addPhoto(Photo p){
        photos.add(p);
    }
    // Delete a photo
    public void deletePhoto(String path){
        int i = 0;
        for(Photo p: photos){
            if(p.path.equals(path)){
                photos.remove(i);
            }
            i++;
        }
    }
}