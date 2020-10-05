find all numbers that occur more than once in an array of integers. What is a second way you might do the same problem? Which approach would you prefer and why?class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }
}
//python3 61.09%, 20%
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        res = {}
        for i in range (len(nums)):
            if nums[i] in res:
                return [res[nums[i]], i]
            res[target-nums[i]] = i
        return []