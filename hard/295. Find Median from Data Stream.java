/*
295.
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
 

Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
 

Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
*/
//one small queue, one larger queue, peek of large queue or small+large/2 is the median. 25.29 5.08
class MedianFinder {

    /** initialize your data structure here. */  
    Queue<Integer> large, small;
    public MedianFinder() {
        large = new PriorityQueue<Integer>();
        small = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        large.add(num);
        small.add(-large.poll());
        if(large.size() < small.size())
            large.add(-small.poll());
    }
    
    public double findMedian() {
        return large.size() <= small.size() ? (double)(large.peek()-small.peek())/2 : (double)large.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
*/