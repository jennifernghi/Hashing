import java.util.*;
/**
 * @author Nghi Nguyen
 *Implement MyMap using open addressing with quadratic probing) 
 *Create a new concrete class that implements MyMap using open addressing with quadratic probing. 
 *For simplicity, use f(key) = key % size as the hash function,
 * where size is the hash-table size. Initially, the hash-table size is 4. 
 * The table size is doubled whenever the load factor exceeds the threshold (0.5).
 * Performing Quadratic Probing on put, remove, get in order to obtain O(1) for these methods
 */
public class MyHashMapQuadraticProbing<V, K> implements MyMap<K, V> {

	private static int DEFAULT_INITIAL_CAPACITY =4;// hash table's initial capacity 
	
	private static int MAXIMUM_CAPACITY =1>>10;// hash table's max capacity
	
	// Current hash table capacity. 
	private int capacity;
	
	// Specify a load factor used in the hash table
	private static double loadFactorThreshold=0.5;
	
	// The number of entries in the map
	private int size =0;
	
	//Hash Table is an array, each cell is an entry with key and value
	private Entry<K,V>[] table;
	
	
	
	public MyHashMapQuadraticProbing()
	{
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	
	public MyHashMapQuadraticProbing(int initialCapacity)
	{
		
		if(capacity > MAXIMUM_CAPACITY)
		{
			throw new RuntimeException("Exceeding maximum capacity"); 
		}else
		{
			this.capacity = initialCapacity;
			table = new Entry[capacity];
			size=0;
		}
		
		
	}
	
	
	/** hash function */
	private int hash(K key){
		return (key.hashCode()) % capacity;
	}
	/** Rehash the map */
	private void rehash()
	{
		Set<Entry<K,V>> set = entrySet();//copies all the entry into set
		capacity <<=1; //Double capacity
		table = new Entry[capacity];// new table with new capacity
		size=0;//no entry in the table now
		
		for(Entry<K,V> entry:set)
		{
			put(entry.getKey(), entry.getValue());//distribute copied entries in the set into the new table
		}
	}
		
	   /** Remove all entries in the table*/	
	   private void removeEntries()
	   {
		   for(int i=0; i< capacity; i++)
		   {
			   if(table[i]!=null)
			   {
				   table[i]=null; 
			   }
		   }
	   }
	   
	/** ----------------Override methods in the interface------------*/
	@Override/** clear all entries in the map*/
	public void clear() {
		size=0;
		removeEntries();
	}

	@Override /** Return true if the specified key is in the map - O(1)*/
	public boolean containsKey(K key) {
		return (get(key)!=null);
	}

	@Override/** Return true if this map contains the value  - O(capacity)*/ 
	public boolean containsValue(V value) {
		
		for(int i=0; i < capacity; i++)
		{
			if(table[i]!=null && table[i].getValue().equals(value))
			{
				return true;
			}
			
		}
		return false;
	}

	@Override/** Return a set of entries in the map - O(capacity)*/
	public Set<MyMap.Entry<K, V>> entrySet() {
		Set<Entry<K,V>> set = new HashSet<>();
		for(int i=0; i< capacity; i++)
		{
			if(table[i]!=null)
			{
				set.add(table[i]);
			}
		}
		return set;
	}

	@Override/** Return the value that matches the specified key - O(1)*/
	public V get(K key) {
		int index =hash(key);// get index by hash function
		int i = index;
		int j=1;
		do
		{
			if(table[i]!=null)
			{
				if(table[i].getKey().equals(key))
				{
					return table[i].getValue();
				}else{
					i = (i+ j*j++) & (capacity -1); // Quadratic probing
				}
			}else if(table[i]==null) //if there is any element just removed
			{
				i = (i+ j*j++) & (capacity -1); // Quadratic probing
			}
			
		}while(i!=index);
		
		return null;
	}

	@Override/** Return true if this map contains no entries */
	public boolean isEmpty() {
		
		return size ==0;
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<>();
		
		for(int i=0; i< capacity; i++)
		{
			if(table[i]!=null)
			{
				set.add(table[i].getKey());
			}
		}
		return set;
	}

	@Override
	public V put(K key, V value) {
		int index = hash(key);
		int i=index;
		int j=1;
		V oldValue;
		
		 if(currentLoad() >= loadFactorThreshold)
		 {
			 rehash();
		 }
		Entry<K,V> entry = new Entry<>(key, value);
		do{
			if(table[i]==null)
			{	
				table[i]=entry;
				size++;
				return value;
			}else
			{
				if(table[i].getKey().equals(key))
				{
					oldValue = table[i].getValue();
					table[i].value=value;
					return oldValue;
				}
			
			}
		
			i = (i+ j*j++) & (capacity-1); //Quadratic Probing 
		}while(i!=index);
		
		return value;
		
	}

	@Override
	public void remove(K key) {
		int index = hash(key);
		int i = index;
		int j=1;
		do{
			if(table[i]!=null && table[i].getKey().equals(key))
			{
				table[i]=null;
				size--;
			}else{
				i =(i + j*j++) & (capacity -1); //Quadratic Probing 
			}
		}while(i!=index);
		
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Set<V> values() {
		Set<V> set = new HashSet<>();
		
		for(int i=0; i<capacity; i++)
		{
			if(table[i]!=null)
			{
				set.add(table[i].getValue());
			}
		}
		return set;
	}

	@Override
	public int capacity() {
		
		return capacity;
	}
	@Override
	public int tableSize() {
		
		return table.length;
	}
	@Override/** return current load of hash table by taking size/capacity */
	public double currentLoad()
	{
		double currentLoad = ((double)size / (double)capacity);
		return currentLoad;
	}
	@Override /** Display the hash table*/
	   public void display()
		{
		   System.out.print("Entries in map: ");  
			for(int i=0; i< capacity; i++)
			{
				if(table[i]!=null)
				{
					System.out.print(" ["+table[i].getKey() + ", "+ table[i].getValue()+"] ");
				}
				
				if(table[i]==null)
				{
					System.out.print(" [null] ");
				}
			}
		}
	

}
