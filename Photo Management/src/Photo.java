public class Photo {
    public String path;
    public LinkedList<String> tags = new LinkedList<String>();

    public Photo(String path, LinkedList<String> tags){
        this.path = path;
        this.tags = tags;
    }
    
    
    public String getPath(){
        return path;
    }
    
    public LinkedList<String> getTags(){
        return tags;
    }
}
