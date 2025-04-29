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
        photos.insert(p);
    }
    // Delete a photo
    public void deletePhoto(String path){
        photos.findfirst();
        while(! photos.last()){
            if(photos.retrieve().path.equals(path))
                photos.remove();
        }
        if(photos.retrieve().path.equals(path))
                photos.remove();
        
    }
}