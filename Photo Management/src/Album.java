public class Album {
    String name;
    String condition;
    PhotoManager manager;
    private int nbComps = 0;

    
    public Album(String name, String condition, PhotoManager manager){
        this.name = name;
        this.condition = condition;
        this.manager = manager;
    }
   
    public String getName(){
        return name;
    }
   
    public String getCondition(){
        return condition;
    }
   
    public PhotoManager getManager(){
        return manager;
    }
    
    private String[] getConditions (String con){
        return con.split("\\s* AND \\s*");
    }
  
    public LinkedList<Photo> getPhotos() {
        if(condition.equals("")) return manager.getPhotos();

        LinkedList<Photo> result = new LinkedList<>();
        LinkedList<Photo> allPhotos = manager.getPhotos(); 
        String[] requiredTags = getConditions(condition); 

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

   
    private boolean photoMatchesAllTags(Photo photo, String[] requiredTags) {
        LinkedList<String> photoTags = photo.getTags(); 
        for (String tag : requiredTags) {
            if (!containsTag(photoTags, tag)) { 
                return false;
            }
        }
        return true;
    }

    
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
    
    public int getNbComps(){
        return nbComps;
    }
}