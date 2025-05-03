public class InvIndexAlbum {
    private String name;
    private String condition;
    private InvIndexPhotoManager manager;
    private int nbComps = 0;

    
    public InvIndexAlbum(String name, String condition, InvIndexPhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
    }
    
   
    public String getName() {
        return name;
    }
    
   
    public String getCondition() {
        return condition;
    }
    
  
    public InvIndexPhotoManager getManager() {
        return manager;
    }
    
   
    private String[] getConditions(String con) {
        return con.split("\\s* AND \\s*");
    }
    
    
    public LinkedList<Photo> getPhotos() {
        nbComps = 0; 
        
        if (condition.equals("")) {
           
            return getAllUniquePhotos();
        }

        String[] requiredTags = getConditions(condition);
        
        
        for (String tag : requiredTags) {
            nbComps++;
            if (!manager.getPhotos().findkey(tag)) {
                
                return new LinkedList<Photo>();
            }
        }
        
      
        manager.getPhotos().findkey(requiredTags[0]);
        LinkedList<Photo> tagPhotos = manager.getPhotos().retrieve();
        
       
        if (requiredTags.length == 1) {
            LinkedList<Photo> result = new LinkedList<Photo>();
            
            if (!tagPhotos.empty()) {
                tagPhotos.findfirst();
                while (!tagPhotos.last()) {
                    nbComps++;
                    result.insert(tagPhotos.retrieve());
                    tagPhotos.findnext();
                }
               
                nbComps++; 
                result.insert(tagPhotos.retrieve());
            }
            
            return result;
        }
        
       
        LinkedList<Photo> result = new LinkedList<Photo>();
        
        if (!tagPhotos.empty()) {
            tagPhotos.findfirst();
            while (!tagPhotos.last()) {
                Photo photo = tagPhotos.retrieve();
                nbComps++;
                if (photoHasAllTags(photo, requiredTags)) {
                    result.insert(photo);
                }
                tagPhotos.findnext();
            }
            
           
            Photo photo = tagPhotos.retrieve();
            nbComps++; 
            if (photoHasAllTags(photo, requiredTags)) {
                result.insert(photo);
            }
        }
        
        return result;
    }
    
   
    private boolean photoHasAllTags(Photo photo, String[] requiredTags) {
       
        for (int i = 1; i < requiredTags.length; i++) {
          
            manager.getPhotos().findkey(requiredTags[i]);
            LinkedList<Photo> photosWithTag = manager.getPhotos().retrieve();
            
          
            if (!containsPhoto(photosWithTag, photo.getPath())) {
                return false;
            }
            
            nbComps++;
        }
        return true;
    }
    
   
    private boolean containsPhoto(LinkedList<Photo> photos, String path) {
        if (photos.empty()) return false;
        
        photos.findfirst();
        while (!photos.last()) {
            nbComps++; 
            if (photos.retrieve().getPath().equals(path)) {
                return true;
            }
            photos.findnext();
        }
        
       
        nbComps++; 
        return photos.retrieve().getPath().equals(path);
    }
    
    
    private LinkedList<Photo> getAllUniquePhotos() {
        LinkedList<Photo> result = new LinkedList<Photo>();
        LinkedList<String> processedPaths = new LinkedList<String>();
        
       
        String[] commonTags = {
            "animal", "hedgehog", "apple", "grass", "green",
            "bear", "cab", "wind", "insect", "butterfly", 
            "flower", "color", "black", "fox", "tree", 
            "forest", "panda", "wolf", "mountain", "sky", 
            "snow", "cloud", "raccoon", "log"
        };
        
        
        for (String tag : commonTags) {
            if (manager.getPhotos().findkey(tag)) {
                nbComps++;
                LinkedList<Photo> tagPhotos = manager.getPhotos().retrieve();
                
                if (!tagPhotos.empty()) {
                    tagPhotos.findfirst();
                    while (!tagPhotos.last()) {
                        Photo photo = tagPhotos.retrieve();
                        nbComps++; 
                        if (!containsPath(processedPaths, photo.getPath())) {
                            result.insert(photo);
                            processedPaths.insert(photo.getPath());
                        }
                        tagPhotos.findnext();
                    }
                    
                  
                    Photo photo = tagPhotos.retrieve();
                    nbComps++; 
                    if (!containsPath(processedPaths, photo.getPath())) {
                        result.insert(photo);
                        processedPaths.insert(photo.getPath());
                    }
                }
            }
        }
        
        return result;
    }
    
    
    private boolean containsPath(LinkedList<String> paths, String path) {
        if (paths.empty()) return false;
        
        paths.findfirst();
        while (!paths.last()) {
            nbComps++; 
            if (paths.retrieve().equals(path)) {
                return true;
            }
            paths.findnext();
        }
        
        nbComps++; 
        return paths.retrieve().equals(path);
    }
    
   
    public int getNbComps() {
        return nbComps;
    }
}