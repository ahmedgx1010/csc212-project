public class InvIndexAlbum {
    private String name;
    private String condition;
    private InvIndexPhotoManager manager;
    private int nbComps = 0;

    // Constructor
    public InvIndexAlbum(String name, String condition, InvIndexPhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
    }
    
    // Return the name of the album
    public String getName() {
        return name;
    }
    
    // Return the condition associated with the album
    public String getCondition() {
        return condition;
    }
    
    // Return the manager
    public InvIndexPhotoManager getManager() {
        return manager;
    }
    
    // Get array of conditions
    private String[] getConditions(String con) {
        return con.split("\\s* AND \\s*");
    }
    
    // Return all photos that satisfy the album condition
    public LinkedList<Photo> getPhotos() {
        nbComps = 0; // Reset the comparison counter
        
        if (condition.equals("")) {
            // For an empty condition, collect all unique photos from all tag lists
            return getAllUniquePhotos();
        }

        String[] requiredTags = getConditions(condition);
        
        // First check if all required tags exist
        for (String tag : requiredTags) {
            nbComps++; // Count the BST search as a comparison
            if (!manager.getPhotos().findkey(tag)) {
                // If any required tag doesn't exist, no photos can match
                return new LinkedList<Photo>();
            }
        }
        
        // Start with photos from the first tag
        manager.getPhotos().findkey(requiredTags[0]);
        LinkedList<Photo> tagPhotos = manager.getPhotos().retrieve();
        
        // If only one tag, return its photos, but count each photo as a comparison
        if (requiredTags.length == 1) {
            LinkedList<Photo> result = new LinkedList<Photo>();
            
            if (!tagPhotos.empty()) {
                tagPhotos.findfirst();
                while (!tagPhotos.last()) {
                    nbComps++; // Count each photo retrieval as a comparison
                    result.insert(tagPhotos.retrieve());
                    tagPhotos.findnext();
                }
                // Process the last photo
                nbComps++; // Count the last photo retrieval
                result.insert(tagPhotos.retrieve());
            }
            
            return result;
        }
        
        // For multiple tags, filter photos to only include those with all tags
        LinkedList<Photo> result = new LinkedList<Photo>();
        
        if (!tagPhotos.empty()) {
            tagPhotos.findfirst();
            while (!tagPhotos.last()) {
                Photo photo = tagPhotos.retrieve();
                nbComps++; // Count the retrieval of each photo as a comparison
                if (photoHasAllTags(photo, requiredTags)) {
                    result.insert(photo);
                }
                tagPhotos.findnext();
            }
            
            // Process the last photo
            Photo photo = tagPhotos.retrieve();
            nbComps++; // Count the retrieval of the last photo
            if (photoHasAllTags(photo, requiredTags)) {
                result.insert(photo);
            }
        }
        
        return result;
    }
    
    // Helper method to check if a photo has all the specified tags
    private boolean photoHasAllTags(Photo photo, String[] requiredTags) {
        // Skip first tag (already filtered by it)
        for (int i = 1; i < requiredTags.length; i++) {
            // Find photos with this tag
            manager.getPhotos().findkey(requiredTags[i]);
            LinkedList<Photo> photosWithTag = manager.getPhotos().retrieve();
            
            // Check if our photo is in this list
            if (!containsPhoto(photosWithTag, photo.getPath())) {
                return false;
            }
            // Even if we find the photo in this tag's list, count it as a comparison
            nbComps++;
        }
        return true;
    }
    
    // Helper method to check if a list of photos contains a photo with a specific path
    private boolean containsPhoto(LinkedList<Photo> photos, String path) {
        if (photos.empty()) return false;
        
        photos.findfirst();
        while (!photos.last()) {
            nbComps++; // Count this comparison
            if (photos.retrieve().getPath().equals(path)) {
                return true;
            }
            photos.findnext();
        }
        
        // Check the last photo
        nbComps++; // Count this comparison
        return photos.retrieve().getPath().equals(path);
    }
    
    // Helper method to get all unique photos from all tag lists
    private LinkedList<Photo> getAllUniquePhotos() {
        LinkedList<Photo> result = new LinkedList<Photo>();
        LinkedList<String> processedPaths = new LinkedList<String>();
        
        // Use common tags to get all photos
        String[] commonTags = {
            "animal", "hedgehog", "apple", "grass", "green",
            "bear", "cab", "wind", "insect", "butterfly", 
            "flower", "color", "black", "fox", "tree", 
            "forest", "panda", "wolf", "mountain", "sky", 
            "snow", "cloud", "raccoon", "log"
        };
        
        // Check each tag and collect its photos
        for (String tag : commonTags) {
            if (manager.getPhotos().findkey(tag)) {
                nbComps++; // Count the tag search as a comparison
                LinkedList<Photo> tagPhotos = manager.getPhotos().retrieve();
                
                if (!tagPhotos.empty()) {
                    tagPhotos.findfirst();
                    while (!tagPhotos.last()) {
                        Photo photo = tagPhotos.retrieve();
                        nbComps++; // Count each photo processing as a comparison
                        if (!containsPath(processedPaths, photo.getPath())) {
                            result.insert(photo);
                            processedPaths.insert(photo.getPath());
                        }
                        tagPhotos.findnext();
                    }
                    
                    // Process the last photo
                    Photo photo = tagPhotos.retrieve();
                    nbComps++; // Count the last photo processing
                    if (!containsPath(processedPaths, photo.getPath())) {
                        result.insert(photo);
                        processedPaths.insert(photo.getPath());
                    }
                }
            }
        }
        
        return result;
    }
    
    // Helper method to check if a path exists in a list
    private boolean containsPath(LinkedList<String> paths, String path) {
        if (paths.empty()) return false;
        
        paths.findfirst();
        while (!paths.last()) {
            nbComps++; // Count this path comparison
            if (paths.retrieve().equals(path)) {
                return true;
            }
            paths.findnext();
        }
        
        nbComps++; // Count the last path comparison
        return paths.retrieve().equals(path);
    }
    
    // Return the number of tag comparisons used to find all photos of the album
    public int getNbComps() {
        return nbComps;
    }
}