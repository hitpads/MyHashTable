import java.util.Random;

public class MyHashTableTest {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(10000);
            String name = "Name" + id;
            MyTestingClass key = new MyTestingClass(id, name);
            Student value = new Student(id, name);
            table.put(key, value);
        }

        // number of elements in buckets
        int[] buckets = new int[table.getM()];
        for (MyHashTable<MyTestingClass, Student>.HashNode<MyTestingClass, Student> node : table.getChainArray()) {
            while (node != null) {
                int index = (node.key.hashCode() & 0x7fffffff) % table.getM();
                buckets[index]++;
                node = node.next;
            }
        }

        for (int i = 0; i < buckets.length; i++) {
            System.out.println("bucket " + i + ": " + buckets[i] + " elements");
        }
    }
}
