/*
705.
Design a HashSet without using any built-in hash table libraries.

To be specific, your design should include these functions:

add(value): Insert a value into the HashSet. 
contains(value) : Return whether the value exists in the HashSet or not.
remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.

Example:

MyHashSet hashSet = new MyHashSet();
hashSet.add(1);         
hashSet.add(2);         
hashSet.contains(1);    // returns true
hashSet.contains(3);    // returns false (not found)
hashSet.add(2);          
hashSet.contains(2);    // returns true
hashSet.remove(2);          
hashSet.contains(2);    // returns false (already removed)

Note:

All values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashSet library.
*/
//using list<>[] list arr, hash mapping element to arr. 66 5 15ms
class MyHashSet {
    List<Integer>[] container = null;
    int cap;
    double loadFactor;
    int count; 
    public MyHashSet() {
        cap = 1000;
        loadFactor = 0.7;
        count = 0;
        container = new LinkedList[cap];
    }
    
    public void add(int key) {
        if(contains(key)) return;
        if(loadFactor*cap == count){
            count = 0;
            //rehash
            cap *= 2;
            List<Integer>[] oldC = container;
            container = new LinkedList[cap];
            for(int i=0; i < oldC.length; i++){
                List<Integer> list = oldC[i];
                if(list != null){
                    for(int entry : list)
                       this.add(entry); 
                }
            }
        }
        int hash = key % cap;
        if(container[hash] == null)
            container[hash] = new LinkedList<>();
        container[hash].add(key);
        ++count;
    }
    
    public void remove(int key) {
        if(!contains(key)) return;
        int hash = key % cap;
        List<Integer> list = container[hash];
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) == key){
                list.remove(i);
                break;
            }
        }
        // if(list != null){
        //     Iterator<Integer> itr = list.iterator();
        //     while(itr.hasNext())
        //         if(itr.next() == key){
        //             itr.remove();
        //             --count;
        //             break;
        //         }
        // }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = key % cap;
        List<Integer> list = container[hash];
        if(list == null) return false;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) == key){
                return true;
            }
        }
        // if(list != null){
        //     Iterator<Integer> itr = list.iterator();
        //     while(itr.hasNext())
        //         if(itr.next() == key)
        //             return true;
        // }
        return false;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */