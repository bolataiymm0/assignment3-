import java.util.Random;

public class Main {
    public static void main(String[] args) {

        MyHashTable<MyTestingClass, String> table = new MyHashTable<>(100);
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            table.put(new MyTestingClass(rand.nextInt(100000), "User" + i), "Value" + i);
        }
        System.out.println("--- HashTable Bucket Distribution ---");
        table.printBucketSizes();


        BST<Integer, String> tree = new BST<>();
        tree.put(20, "Root");
        tree.put(10, "Left");
        tree.put(30, "Right");
        tree.put(5, "Leaf");

        System.out.println("\n--- BST Traversal BEFORE delete ---");
        for (var node : tree) System.out.println("Key: " + node.getKey());

        tree.delete(10);

        System.out.println("\n--- BST Traversal AFTER deleting 10 ---");
        for (var node : tree) System.out.println("Key: " + node.getKey());
        System.out.println("Size: " + tree.size());
    }
}