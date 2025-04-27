import java.util.LinkedList;

public class Photo {
    public String path;
    public LinkedList<String> tags = new LinkedList<>();

    public Photo(String path, LinkedList<String> tags);{
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
