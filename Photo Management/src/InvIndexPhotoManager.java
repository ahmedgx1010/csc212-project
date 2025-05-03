public class InvIndexPhotoManager {
    private BST<LinkedList<Photo>> Tags;
    
    // Constructor
    public InvIndexPhotoManager() {
        Tags = new BST<LinkedList<Photo>>();
    }
    
    // Add a photo
    public void addPhoto(Photo p) {
        LinkedList<String> photoTags = p.getTags();
        photoTags.findfirst();
        

        while (!photoTags.last()) {
            String tag = photoTags.retrieve();
            Tags.findkey(tag); 
            if (!Tags.empty() && Tags.retrieve() != null && Tags.findkey(tag)) {
                
                LinkedList<Photo> photos = Tags.retrieve();
                photos.insert(p);
            } else {
               
                LinkedList<Photo> photos = new LinkedList<Photo>();
                photos.insert(p);
                Tags.insert(tag, photos);
            }
            photoTags.findnext();
        }
        
        
        String tag = photoTags.retrieve();
        Tags.findkey(tag); 
        if (!Tags.empty() && Tags.retrieve() != null && Tags.findkey(tag)) {
            
            LinkedList<Photo> photos = Tags.retrieve();
            photos.insert(p);
        } else {
           
            LinkedList<Photo> photos = new LinkedList<Photo>();
            photos.insert(p);
            Tags.insert(tag, photos);
        }
    }
    
  
    public void deletePhoto(String path) {
        
        LinkedList<String> allTags = new LinkedList<String>();
        
        
        String[] commonTags = {
            "animal", "hedgehog", "apple", "grass", "green",
            "bear", "cab", "wind", "insect", "butterfly", 
            "flower", "color", "black", "fox", "tree", 
            "forest", "panda", "wolf", "mountain", "sky", 
            "snow", "cloud", "raccoon", "log"
        };
        
        
        for (String tag : commonTags) {
            if (Tags.findkey(tag)) {
                allTags.insert(tag);
            }
        }
        
       
        allTags.findfirst();
        while (!allTags.last()) {
            String tag = allTags.retrieve();
            
            if (Tags.findkey(tag)) {
                LinkedList<Photo> photos = Tags.retrieve();
                
                
                photos.findfirst();
                while (!photos.last()) {
                    if (photos.retrieve().getPath().equals(path)) {
                        photos.remove();
                        break;
                    }
                    photos.findnext();
                }
                
                
                if (!photos.empty() && photos.retrieve().getPath().equals(path)) {
                    photos.remove();
                }
                
               
                if (photos.empty()) {
                    Tags.remove_key(tag);
                }
            }
            
            allTags.findnext();
        }
        
       
        if (!allTags.empty()) {
            String tag = allTags.retrieve();
            
            if (Tags.findkey(tag)) {
                LinkedList<Photo> photos = Tags.retrieve();
                
                
                photos.findfirst();
                while (!photos.last()) {
                    if (photos.retrieve().getPath().equals(path)) {
                        photos.remove();
                        break;
                    }
                    photos.findnext();
                }
                
               
                if (!photos.empty() && photos.retrieve().getPath().equals(path)) {
                    photos.remove();
                }
                
               
                if (photos.empty()) {
                    Tags.remove_key(tag);
                }
            }
        }
    }
    
    
    public BST<LinkedList<Photo>> getPhotos() {
        return Tags;
    }
}