public class Test {
    public static void main(String[] args) {
        System.out.println("===== Testing Original Implementation =====");
        
        // Create and populate the original PhotoManager
        PhotoManager manager = new PhotoManager();

        Photo photo1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        manager.addPhoto(photo1);

        Photo photo2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
        manager.addPhoto(photo2);

        Photo photo3 = new Photo("orange-butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, color"));
        manager.addPhoto(photo3);

        Photo photo4 = new Photo("butterfly2.jpg", toTagsLinkedList("insect, butterfly, black, flower"));
        manager.addPhoto(photo4);
        
        Photo photo5 = new Photo("fox.jpg", toTagsLinkedList("animal, fox, tree, forest, grass"));
        manager.addPhoto(photo5);
        
        Photo photo6 = new Photo("panda.jpg", toTagsLinkedList("animal, bear, panda, grass"));
        manager.addPhoto(photo6);
        
        Photo photo7 = new Photo("wolf.jpg", toTagsLinkedList("animal, wolf, mountain, sky, snow, cloud"));
        manager.addPhoto(photo7);
        
        Photo photo8 = new Photo("raccoon.jpg", toTagsLinkedList("animal, raccoon, log, snow"));
        manager.addPhoto(photo8);
        
        Album album1 = new Album("Album1", "animal", manager);
        Album album2 = new Album("Album2", "animal AND grass", manager);

        System.out.println("Original PhotoManager Tests:");
        System.out.println("Get photo1 path and tags:");
        System.out.println("photo1 path: " + photo1.getPath());
        System.out.print("photo1 tags: ");
        printTags(photo1.getTags());

        System.out.println("\nGet album1 name, condition, and photos:");
        System.out.println("album1 name: " + album1.getName());
        System.out.println("album1 condition: " + album1.getCondition());
        System.out.println("album1 photos: ");
        printPhotos(album1.getPhotos());
        System.out.println("Number of comparisons: " + album1.getNbComps());

        System.out.println("\nGet album2 name, condition, and photos:");
        System.out.println("album2 name: " + album2.getName());
        System.out.println("album2 condition: " + album2.getCondition());
        System.out.println("album2 photos: ");
        printPhotos(album2.getPhotos());
        System.out.println("Number of comparisons: " + album2.getNbComps());

        System.out.println("\nDelete the photo 'bear.jpg':");
        manager.deletePhoto("bear.jpg");
        System.out.println("album1 photos after deletion: ");
        printPhotos(album1.getPhotos());
        System.out.println("Number of comparisons: " + album1.getNbComps());
        
        // Testing InvIndexPhotoManager
        System.out.println("\n===== Testing InvIndexPhotoManager Implementation =====");
        
        InvIndexPhotoManager invManager = new InvIndexPhotoManager();
        
        // Add all photos to the inverted index manager
        Photo invPhoto1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        invManager.addPhoto(invPhoto1);

        Photo invPhoto2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
        invManager.addPhoto(invPhoto2);

        Photo invPhoto3 = new Photo("orange-butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, color"));
        invManager.addPhoto(invPhoto3);

        Photo invPhoto4 = new Photo("butterfly2.jpg", toTagsLinkedList("insect, butterfly, black, flower"));
        invManager.addPhoto(invPhoto4);
        
        Photo invPhoto5 = new Photo("fox.jpg", toTagsLinkedList("animal, fox, tree, forest, grass"));
        invManager.addPhoto(invPhoto5);
        
        Photo invPhoto6 = new Photo("panda.jpg", toTagsLinkedList("animal, bear, panda, grass"));
        invManager.addPhoto(invPhoto6);
        
        Photo invPhoto7 = new Photo("wolf.jpg", toTagsLinkedList("animal, wolf, mountain, sky, snow, cloud"));
        invManager.addPhoto(invPhoto7);
        
        Photo invPhoto8 = new Photo("raccoon.jpg", toTagsLinkedList("animal, raccoon, log, snow"));
        invManager.addPhoto(invPhoto8);
        
        InvIndexAlbum invAlbum1 = new InvIndexAlbum("InvAlbum1", "bear", invManager);
        InvIndexAlbum invAlbum2 = new InvIndexAlbum("InvAlbum2", "animal AND grass", invManager);
        
        System.out.println("InvIndexPhotoManager Tests:");
        
        System.out.println("Get invAlbum1 name, condition, and photos:");
        System.out.println("invAlbum1 name: " + invAlbum1.getName());
        System.out.println("invAlbum1 condition: " + invAlbum1.getCondition());
        System.out.println("invAlbum1 photos: ");
        printPhotos(invAlbum1.getPhotos());
        System.out.println("Number of comparisons: " + invAlbum1.getNbComps());
        
        System.out.println("\nGet invAlbum2 name, condition, and photos:");
        System.out.println("invAlbum2 name: " + invAlbum2.getName());
        System.out.println("invAlbum2 condition: " + invAlbum2.getCondition());
        System.out.println("invAlbum2 photos: ");
        printPhotos(invAlbum2.getPhotos());
        System.out.println("Number of comparisons: " + invAlbum2.getNbComps());
        
        System.out.println("\nDelete the photo 'bear.jpg':");
        invManager.deletePhoto("bear.jpg");
        System.out.println("invAlbum1 photos after deletion: ");
        printPhotos(invAlbum1.getPhotos());
        System.out.println("Number of comparisons: " + invAlbum1.getNbComps());
        
        // Print performance comparison
        System.out.println("\n===== Performance Comparison =====");
        System.out.println("Original implementation vs. Inverted Index implementation:");
        System.out.println("Single tag condition ('bear'):");
        System.out.println("  Original: Required " + album1.getNbComps() + " comparisons");
        System.out.println("  Inverted Index: Required " + invAlbum1.getNbComps() + " comparisons");
        
        System.out.println("Compound tag condition ('animal AND grass'):");
        System.out.println("  Original: Required " + album2.getNbComps() + " comparisons");
        System.out.println("  Inverted Index: Required " + invAlbum2.getNbComps() + " comparisons");
    }

    private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<String>();
        String[] tagsArray = tags.split("\\s*,\\s*");

        for (int i = 0; i < tagsArray.length; i++) {
            result.insert(tagsArray[i]);
        }
        
        return result;
    }
    
    private static void printTags(LinkedList<String> tags) {
        if (tags.empty()) {
            System.out.println("No tags.");
            return;
        }
        
        tags.findfirst();
        System.out.print(tags.retrieve());
        
        while (!tags.last()) {
            tags.findnext();
            System.out.print(", " + tags.retrieve());
        }
        System.out.println();
    }
    
    private static void printPhotos(LinkedList<Photo> photos) {
        if (photos.empty()) {
            System.out.println("  No photos.");
            return;
        }
        
        photos.findfirst();
        while (!photos.last()) {
            System.out.println("  " + photos.retrieve().getPath());
            photos.findnext();
        }
        System.out.println("  " + photos.retrieve().getPath());
    }
}