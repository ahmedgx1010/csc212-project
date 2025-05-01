public class Test {
    public static void main(String[] args) {
        PhotoManager manager = new PhotoManager();

        Photo photo1 = new Photo("hedgehog.jpg",toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        manager.addPhoto(photo1);

        Photo photo2 = new Photo("bear.jpg",toTagsLinkedList("animal, bear, cab, grass, wind"));
        manager.addPhoto(photo2);

        Photo photo3 = new Photo("orange-butterfly.jpg",toTagsLinkedList("insect, butterfly, flower, color"));
        manager.addPhoto(photo3);

        Album album1 = new Album("Album1", "bear", manager);
        //album1.getConditions(album1.getCondition());
        Album album2 = new Album("Album2", "animal AND grass", manager);

        System.out.println("Get photo1 path and tags:");
        System.out.println("photo1 path: " + photo1.getPath());

        //You can get the list of tags of photo1 by calling photo1.getTags().
        //You can write a method that prints the list of tags of photo1.
        System.out.println("Get album2 name, condition, and photos:");
        System.out.println("album2 name: " + album2.getName());
        System.out.println("album2 condition: " + album2.getCondition());

        //You can get the list of photos in album2 by calling album2.getPhotos().
        //You can write a method that prints the list of photos in album2.
        System.out.println("Delete the photo 'bear.jpg':");
        manager.deletePhoto("bear.jpg");
    }

    private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<String>();
        String[] tagsArray = tags.split("\\s*,\\s*");

        for (int i = 0; i < tagsArray.length; i++){
            result.insert(tagsArray[i]);
        }
        
        return result;
    }
}