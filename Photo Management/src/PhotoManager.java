public class PhotoManager {
    LinkedList<Photo> photos;

    public PhotoManager(){
        photos = new LinkedList<Photo>();
    }
    
    public LinkedList<Photo> getPhotos(){
        return photos;
    }
   
    public void addPhoto(Photo p){
        photos.insert(p);
    }
 
    public void deletePhoto(String path){
        photos.findfirst();
        while(! photos.last()){
            if(photos.retrieve().path.equals(path)){
                photos.remove();
                break;
            }
            photos.findnext();
                
        }
        if(photos.retrieve().path.equals(path))
                photos.remove();
        
    }
}