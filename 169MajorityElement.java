/*
169.
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
 */
 // moore vote: dp without extra spaces, sorting/hashmap. just a for loop then done. 100
 public class Solution {
    public int majorityElement(int[] num) {

        int major=num[0], count = 1;
        for(int i=1; i<num.length;i++){
            if(count==0){
                count++;
                major=num[i];
            }else if(major==num[i]){
                count++;
            }else count--;
            
        }
        return major;
    }
}
//bit manipulation
class Solution {
    public int majorityElement(int[] nums) {
    int[] bit = new int[32];
    for (int num: nums)
        for (int i=0; i<32; i++) 
            if ((num>>(31-i) & 1) == 1)
                bit[i]++;
    int ret=0;
    for (int i=0; i<32; i++) {
        bit[i]=bit[i]>nums.length/2?1:0;
        ret += bit[i]*(1<<(31-i));
    }
    return ret;
}

}
//37.3, 5.15， hashmap
class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        int max = 1;
        int res = nums[0];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < n; i++){
            if(map.containsKey(nums[i])){
                int temp = map.get(nums[i]);
                map.put(nums[i],temp+1);
                if(max < temp+1){
                    max = temp+1;
                    res = nums[i];
                }
            }
            else
                map.put(nums[i],1);
        }
        return res;
    }
}
//sorting and count， 63.37， 27.21
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int count = 1;
        int max = 1;
        int res = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i-1] != nums[i]){
                if(max < count){
                    max = count;
                    res = nums[i-1];
                }
                count = 1;
            }
            else
                count++;
        }
        if(max < count){
            max = count;
            return nums[nums.length-1];
        }
        return res;
    }
}
// since majority is > n/2, just get half
public int majorityElement1(int[] nums) {
    Arrays.sort(nums);
    return nums[nums.length/2];
}