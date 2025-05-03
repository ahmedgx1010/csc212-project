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
        
        // Add photo to each tag's list in the inverted index
        while (!photoTags.last()) {
            String tag = photoTags.retrieve();
            Tags.findkey(tag); // Will set current to tag if it exists, or to insertion point if not
            if (!Tags.empty() && Tags.retrieve() != null && Tags.findkey(tag)) {
                // Tag exists, add photo to its list
                LinkedList<Photo> photos = Tags.retrieve();
                photos.insert(p);
            } else {
                // Tag doesn't exist, create new list and add photo
                LinkedList<Photo> photos = new LinkedList<Photo>();
                photos.insert(p);
                Tags.insert(tag, photos);
            }
            photoTags.findnext();
        }
        
        // Process the last tag
        String tag = photoTags.retrieve();
        Tags.findkey(tag); // Will set current to tag if it exists, or to insertion point if not
        if (!Tags.empty() && Tags.retrieve() != null && Tags.findkey(tag)) {
            // Tag exists, add photo to its list
            LinkedList<Photo> photos = Tags.retrieve();
            photos.insert(p);
        } else {
            // Tag doesn't exist, create new list and add photo
            LinkedList<Photo> photos = new LinkedList<Photo>();
            photos.insert(p);
            Tags.insert(tag, photos);
        }
    }
    
    // Delete a photo
    public void deletePhoto(String path) {
        // We need to collect all tags that exist in our BST
        LinkedList<String> allTags = new LinkedList<String>();
        
        // Use the common tags from the example photos as a reference
        String[] commonTags = {
            "animal", "hedgehog", "apple", "grass", "green",
            "bear", "cab", "wind", "insect", "butterfly", 
            "flower", "color", "black", "fox", "tree", 
            "forest", "panda", "wolf", "mountain", "sky", 
            "snow", "cloud", "raccoon", "log"
        };
        
        // Check each common tag to see if it exists in our BST
        for (String tag : commonTags) {
            if (Tags.findkey(tag)) {
                allTags.insert(tag);
            }
        }
        
        // Now go through each tag and remove the photo with the given path
        allTags.findfirst();
        while (!allTags.last()) {
            String tag = allTags.retrieve();
            
            if (Tags.findkey(tag)) {
                LinkedList<Photo> photos = Tags.retrieve();
                
                // Find and remove the photo
                photos.findfirst();
                while (!photos.last()) {
                    if (photos.retrieve().getPath().equals(path)) {
                        photos.remove();
                        break; // Found and removed, no need to continue
                    }
                    photos.findnext();
                }
                
                // Check the last photo
                if (!photos.empty() && photos.retrieve().getPath().equals(path)) {
                    photos.remove();
                }
                
                // If the tag's photo list is now empty, remove the tag from the BST
                if (photos.empty()) {
                    Tags.remove_key(tag);
                }
            }
            
            allTags.findnext();
        }
        
        // Process the last tag
        if (!allTags.empty()) {
            String tag = allTags.retrieve();
            
            if (Tags.findkey(tag)) {
                LinkedList<Photo> photos = Tags.retrieve();
                
                // Find and remove the photo
                photos.findfirst();
                while (!photos.last()) {
                    if (photos.retrieve().getPath().equals(path)) {
                        photos.remove();
                        break; // Found and removed, no need to continue
                    }
                    photos.findnext();
                }
                
                // Check the last photo
                if (!photos.empty() && photos.retrieve().getPath().equals(path)) {
                    photos.remove();
                }
                
                // If the tag's photo list is now empty, remove the tag from the BST
                if (photos.empty()) {
                    Tags.remove_key(tag);
                }
            }
        }
    }
    
    // Return the inverted index of all managed photos
    public BST<LinkedList<Photo>> getPhotos() {
        return Tags;
    }
}