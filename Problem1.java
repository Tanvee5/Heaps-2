// Problem 1 : Top K Frequent Elements
// Time Complexity :
/*
 * Heap Mathod - O(n log k) where n is the length of the nums list
 * Hash Map Method - O(n) where n is the length of the nums list
 */
// Space Complexity :
/*
 * Heap Mathod - O(n) where n is the length of the nums list
 * Hash Map Method - O(n) where n is the length of the nums list
 */
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach

// Heap Method 
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // define hash map which will store num as key and frequency as value
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        // loop through nums list
        for(int num : nums) {
            // update the frequency for the num by getting the existing value of frequeny of num and increment by 1
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }

        // define priority queue and sort the element in ascending by its frequency
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
            int v1 = hashMap.get(a);
            int v2 = hashMap.get(b);
            return v1 - v2;
        });

        // loop through keys of the hash map
        for(int key : hashMap.keySet()) {
            // add the key to the priority queue
            pq.add(key);
            // check if the size of the priority queue is greater than k and if it is then pop the minimum element from the queue
            if(pq.size() > k) {
                pq.poll();
            }
        }
        // define result list which will k frequent num from nums list
        int [] result = new int[k];
        // loop from 0 to k 
        for(int i = 0; i < k; i++) {
            // pop the element from the queue and add to the result list
            result[i] = pq.poll();
        }
        // return result
        return result;
    }
}

// Hash Map Method

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // define hash map which will store num as key and frequency as value
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        // define min and max which will store the minimum and maximum frequency
        int min = Integer.MAX_VALUE;
        int max = 0;  
        // loop through nums list
        for(int num : nums) {
            // update the frequency for the num by getting the existing value of frequeny of num and increment by 1
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        // define hash map which will store frequency as key and list of number as value
        HashMap<Integer, List<Integer>> freqToNum = new HashMap<>();
        // loop through the list of keys of hashMap
        for(int key: hashMap.keySet()) {
            // get the value of the key from hash Map which will be the frequency key from freqToNum
            int freq = hashMap.get(key);
            // get the minimum value between minimum and freq
            min = Math.min(min, freq);
            // get the maximum value between maximum and freq
            max = Math.max(max, freq);
            // check if the freq is present as key in the freqToNum and if it is true then create an entery with freq as key and empty list as value
            if(!freqToNum.containsKey(freq)) {
                freqToNum.put(freq, new ArrayList<>());
            }
            // add the key to the value list for the freq key in the freqToNum hash map
            freqToNum.get(freq).add(key);
        }
        // define result list which will k frequent num from nums list
        int [] result = new int[k];
        int j = 0;
        // loop from max to min value and j should be less than k
        for(int i = max; i >= min && j < k; i--) {
            // get the list of values from freqToNum hash map of the ith key
            List<Integer> li = freqToNum.get(i);
            // if list is empty then continue
            if(li == null) continue;
            // loop through the list and and j should be less than k
            for(int f = 0; f < li.size() && j < k; f++) {
                // get the element from the list and add that element to the result
                int el = li.get(f);
                result[j] = el;
                // increment the j pointer
                j++;
            }
        }
        // return result
        return result;
    }
}
