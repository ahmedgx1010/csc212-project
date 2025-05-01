public class InvIndexPhotoManager {//TODO: adhere to spec
    BST<LinkedList<Photo>> Tags;

    // Constructor
    public InvIndexPhotoManager(){
        Tags = new BST<LinkedList<Photo>>();
    }
    // Add a photo
    public void addPhoto(Photo p){
        LinkedList<String> photoTags = p.tags;
        while(!photoTags.last()){
            String t = photoTags.retrieve();
            Tags.insert(t, new LinkedList<Photo>());//moves BST current to tag regardless
            LinkedList<Photo> photos = Tags.current.data;
            photos.insert(p);
        }
        String t = photoTags.retrieve();
        Tags.insert(t, new LinkedList<Photo>());//moves BST current to tag regardless
        LinkedList<Photo> photos = Tags.current.data;
        photos.insert(p);
    }
    // Delete a photo
    public void deletePhoto(String path){//TODO: placeholder
        return;
    }
    // Return the inverted index of all managed photos
    public BST<LinkedList<Photo>> getPhotos(){//TODO: placeholder
        return new BST<LinkedList<Photo>>();
    }
}