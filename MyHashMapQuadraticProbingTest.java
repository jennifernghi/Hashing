/**
 * 
 * @author Nghi Nguyen
 * assignment Hashing - 27.2
 * MyHashMapQudraticProbing test
 */

public class MyHashMapQuadraticProbingTest {
	public static void main(String[] args) {
		 MyMap<Integer, String> map = new MyHashMapQuadraticProbing<>();
		 	map.put(0, "0000000");
		 	map.put(4, "four");
		 	map.display(); System.out.println(" . Current load: "+ map.currentLoad() + " . Number of entries: " + map.size() + " . Hash table capacity: " + map.capacity());
		 	map.put(0, "zero");
		 	map.put(8, "eight");
		 	map.display(); System.out.println(" . Current load: "+ map.currentLoad() + " . Number of entries: " + map.size() + " . Hash table capacity: " + map.capacity());
		 	map.put(12, "twelve");
		    map.display(); System.out.println(" . Current load: "+ map.currentLoad() + " . Number of entries: " + map.size() + " . Hash table capacity: " + map.capacity());
		    System.out.println("Value of key 4: " +map.get(4));
		    System.out.println("Value of key 12: " +map.get(12));
		    System.out.println("Value of key 8: " +map.get(8));
		    System.out.println("Value of key 1: " +map.get(1));
		    System.out.println("is key 5 in the map? " + map.containsKey(5));
		    System.out.println("is key 12 in the map? " + map.containsKey(12));
		    System.out.println("is value 'zero' in the map? " + map.containsValue("zero"));
		    System.out.println("is value 'four' in the map? " + map.containsValue("four"));
		    System.out.println("is value 'nine' in the map? " + map.containsValue("nine"));
		    System.out.println("Entry Set: "+ map.entrySet());
		    System.out.println("Key Set: "+ map.keySet());
		    System.out.println("value set: " + map.values());
		    System.out.println("Remove key 12: "); map.remove(12);  
		    System.out.print("Now the hash table ");  
		    map.display();
		    System.out.println();
		    System.out.println("Remove key 0: ");map.remove(0);
		    System.out.print("Now the hash table ");  
		    map.display();	    
	}
}
/*
Entries in map:  [0, 0000000]  [4, four]  [null]  [null]  . Current load: 0.5 . Number of entries: 2 . Hash table capacity: 4
Entries in map:  [0, zero]  [8, eight]  [null]  [null]  [4, four]  [null]  [null]  [null]  . Current load: 0.375 . Number of entries: 3 . Hash table capacity: 8
Entries in map:  [0, zero]  [8, eight]  [null]  [null]  [4, four]  [12, twelve]  [null]  [null]  . Current load: 0.5 . Number of entries: 4 . Hash table capacity: 8
Value of key 4: four
Value of key 12: twelve
Value of key 8: eight
Value of key 1: null
is key 5 in the map? false
is key 12 in the map? true
is value 'zero' in the map? true
is value 'four' in the map? true
is value 'nine' in the map? false
Entry Set: [[4, four], [12, twelve], [8, eight], [0, zero]]
Key Set: [0, 4, 8, 12]
value set: [zero, four, twelve, eight]
Remove key 12: 
Now the hash table Entries in map:  [0, zero]  [8, eight]  [null]  [null]  [4, four]  [null]  [null]  [null] 
Remove key 0: 
Now the hash table Entries in map:  [null]  [8, eight]  [null]  [null]  [4, four]  [null]  [null]  [null] 
 */
