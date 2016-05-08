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

	private static int DEFAULT_INITIAL_CAPACITY =4;// must be a prime
	
	private static int MAXIMUM_CAPACITY =997;// must be a prime
	
	// Current hash table capacity. Capacity is a power of 2
	private int capacity;
	
	// Specify a load factor used in the hash table
	private static double loadFactorThreshold=0.5;
	
	// The number of entries in the map
	private int size =0;
	
	//Hash Table is an array, each cell is an entry with key and value
	public Entry<K,V>[] table;
	
	public MyHashMapQuadraticProbing()
	{
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	
	public MyHashMapQuadraticProbing(int capacity)
	{
		
		if(capacity > MAXIMUM_CAPACITY)
		{
			throw new RuntimeException("Exceeding maximum capacity"); 
		}else
		{
			int primeCapacity = closetPrime(capacity);
			table = new Entry[primeCapacity];
			size=0;
		}
		
		
	}
	
	private int closetPrime(int number)
	{
		while(!isPrime(number))
		{
			number++;
		}
		return number;
	}
	private boolean isPrime(int number)
	{
		for(int divisor =2; divisor <= (number/2); divisor++)
		{
			if(number % divisor ==0)
				return false;
		}
		
		return true;
	
	}
	/** Rehash the map */
	private void rehash()
	{
		Set<Entry<K,V>> set = entrySet();//copies all the entry into set
		
		capacity <<=1; //Double capacity
		table = new Entry[closetPrime(capacity)];
		size=0;
		
		for(Entry<K,V> entry:set)
		{
			put(entry.getKey(), entry.getValue());
		}
	}
	/** Hash function */
	   private int hash(int hashcode)
	   {
	      return supplementalHash(hashcode) & (capacity -1);
	      // return supplementalHash(hashCode) % capacity;
	   }
	   /** Ensure the hashing is evenly distributed */
	   private static int supplementalHash(int h)
	   {
	      h ^= (h >>> 20) ^ (h >>> 12);
	      return h ^ (h >>> 7) ^ (h >>> 4);
	   }
	   
	   
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
	   @Override /** Return a string representation for this map */
	   public String toString()
	   {
	      StringBuilder builder = new StringBuilder("[");
	      
	      for(int i =0; i< capacity; i++)
	      {
	          if(table[i]!=null)
	          {
	              for(Entry<K, V> entry: table)
	              {
	                  builder.append(entry);
	              }
	          }
	      }
	      
	      builder.append("]");
	      return builder.toString();
	   }
	/** ----------------Override methods in the interface------------*/
	@Override
	public void clear() {
		size=0;
		removeEntries();
	}

	@Override
	public boolean containsKey(K key) {
		return (get(key)!=null);
	}

	@Override
	public boolean containsValue(V value) {
		for(int i=0; i< capacity; i++)
		{
			if(table[i].getValue().equals(value))
			{
				return true;
			}
		}
		return false;
	}

	@Override
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

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub
		
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

}
