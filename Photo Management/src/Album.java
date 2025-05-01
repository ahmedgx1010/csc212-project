public class Album {
    String name;
    String condition;
    PhotoManager manager;
    private int nbComps = 0;

    // Constructor
    public Album(String name, String condition, PhotoManager manager){
        this.name = name;
        this.condition = condition;
        this.manager = manager;
    }
    // Return the name of the album
    public String getName(){
        return name;
    }
    // Return the condition associated with the album
    public String getCondition(){
        return condition;
    }
    // Return the manager
    public PhotoManager getManager(){
        return manager;
    }
    //get array of conditions
    private String[] getConditions (String con){
        return con.split("\\s* AND \\s*");
    }
   // Return all photos that satisfy the album condition
    public LinkedList<Photo> getPhotos() {
        if(condition.equals("")) return manager.getPhotos();//match all

        LinkedList<Photo> result = new LinkedList<>();
        LinkedList<Photo> allPhotos = manager.getPhotos(); // Get all photos from the manager
        String[] requiredTags = getConditions(condition); // Split condition into tags

        allPhotos.findfirst();
        while (!allPhotos.last()) {
            Photo photo = allPhotos.retrieve();
            if (photoMatchesAllTags(photo, requiredTags)) {
                result.insert(photo);
            }
            allPhotos.findnext();
        }
        if (photoMatchesAllTags(allPhotos.retrieve(), requiredTags)) {
            result.insert(allPhotos.retrieve());
        }

        return result;
    }

    // Helper method to check if a photo matches all required tags (AND condition)
    private boolean photoMatchesAllTags(Photo photo, String[] requiredTags) {
        LinkedList<String> photoTags = photo.getTags(); // Get tags of the photo
        for (String tag : requiredTags) {
            if (!containsTag(photoTags, tag)) { // Check if the photo is missing any required tag
                return false;
            }
        }
        return true;
    }

    // Helper method to check if a LinkedList contains a specific tag
    private boolean containsTag(LinkedList<String> tags, String tag) {
        tags.findfirst();
        while (!tags.last()) {
            nbComps++;
            if (tags.retrieve().equals(tag)) {
                return true;
            }
            tags.findnext();
        }
        nbComps++;
        if (tags.retrieve().equals(tag)) {
            return true;
        }
        return false;
    }
    // Return the number of tag comparisons used to find all photos of the album
    public int getNbComps(){
        return nbComps;
    }
}