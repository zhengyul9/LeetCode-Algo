//find all numbers that occur more than once in an array of integers. 
//What is a second way you might do the same problem? 
//Which approach would you prefer and why?
class Solution {
    public List<Integer> func(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
		HashSet<Integer> record = new HashSet<>();
		List<Integer> res = new LinkedList<>();
		for(int i : nums){
			if(set.contains(nums[i]) && !record.contains(nums[i])){
				res.add(nums[i]);
				record.add(nums[i]);
			}
			set.add(nums[i]);
		}
		return res;
    }
}
//sort
class Solution {
    public List<Integer> func(int[] nums) {
        Arrays.sort(nums);
		List<Integer> res = new LinkedList<>();
		for(int i = 1; i < nums.length; i++){
			if(nums[i] = nums[i-1]){
				res.add(nums[i]);
				for(int j = i+1; j < nums.length; j++){
					if(nums[j] == nums[i])
						i++;
				}
			}
		}
		return res;
    }
}
