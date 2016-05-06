# Hashing: technique that retrives the value using the index, i, w/o performing a search
+ superefficient search, insert, delete O(1)
+ Hasing uses a hashing function to map a key to 1 index
+ map(dictionary, hash table, associative array) is a container object that stores entries
+ HashMap using hashing
+ if u can map a key to an index -> we can store the values in an array and use key as the index to find the value
+ array stores value: HASH TABLE
+ HASH FUNCTION: function that maps a key to an index in the hash table
  - hash function obtains 1 index from a key i=hash(key) and uses i to retrieve the value for the key
  - perfect hash function: a function that maps each search key to a differnt index in the hash table
  --> diificult
  collision: 2+ keys are mapped to the same hash value
+ HASH CODE: a typical hash function first converts a search key to 1 integer value called hash code; then compresses the hash code into 1 index to the hash table
  - Object class: hashCode()
      + by default, memory address returned
      + should override hashCode() whenever equals method is overridden -> 2 equal objects return same hash code.
      + during execution of a program, provided that ob's data not changed, invoking the hashCode() n times returns same integer
      + 2 !=obs may have the same hash code => to avoid, implement hashCode()
      + Hash code for primitive types:
        - byte, short, int, char ==> cast to int
        - float ==> Float.floatToIntBits(key)
        - long ==> using FOLDING: int hashCode = (int)(key ^ (key >> 32)) 
          + ">>" right-shift operator that shifts the bits 32 positions to the right
          + "^" exclusive-or operator
        - double: 
          long bits = Double.doubleToLongBits(key);
          int hashCode = (int)(bits ^ (bits >> 32));
      + String: take position of hashCode into consideration -> polinomial hash code
      + Compress Hash Codes
        - to scale the hash code down to fit in the index's range
        - h(hashCode) = hashCode % N // N is a value of power of 2
          + h(hashCode) = hashCode % N   SAME AS  h(hashCode) = hashCode & (N-1)
          + &: AND operator, faster than %
        - ensure hashing evenly distributed supplemetal hash function used along w/ primary hash function in HashMap
          + private static int supplementalHash(int h) {
          
              h ^= (h >>> 20) ^ (h >>> 12);
              
              return h ^ (h >>> 7) ^ (h >>> 4);
              
            } 
          + ==> h(hashCode) = supplementalHash(hashCode) % N  SAME AS  h(hashCode) = supplementalHash(hashCode) & (N -1)
        - BITWISE OPERATIONS ARE MUCH FASTER THAN MULTIPLICATION, DIVISION, REMAINDER OPERATION
+ Collision occures when 2 keys are mapped to the same index in a hash table
  - 2 ways for handling collisions: open addressing and seperate chaining
    + OPEN ADDRESSING: prcess of finding 1 open location in the hash table in the event of a collision
        1. LINEAR PROBING
           - when collision occurs during the insertion of an entry to a hash table, linear probing findsthe next available location sequentially
           - index = key % N
           - ex: if collision occurs at hashTable[k % N], then check hashTable[(k+1) % N]. If not, check hashTable[(k+2) % N]
           - when probing reaches the end of the table, it goes back at the beginning of the table => hash table treated as if it were circular
           - search: obtain index: k from hash function for the key. check hashTable[k % N] contains the entry. If not check hashTable[(k+1) % N] ...until it is found or 1 empty cell reached
           - remove: search the entry matches the key. I found, place a special MARKER to denote that the entry is available
              + each cell in hash table has 3 possible states: occupied, marked, empty
              + marked: available for insertion
           - ==> DISADVANTAGE: linear probing tends to cause groups of consecutive cells in hash table to be occupied. Each group called CLUSTER. Each cluster: a probe sequence that must search when retrieving, add, remove 1 entry. as cluster grows in size, they may merge into even larger clusters --> further slowing down the search time
        2. QUADRATIC PROBING
           - 
  
