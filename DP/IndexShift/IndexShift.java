package DP.IndexShift;

import java.util.*;

class IndexShift {
    int[][] dp;
    
    int solve(int i, int sum, int[] nums, int target) {
        if (i == nums.length) {
            return (sum == target) ? 1 : 0;
        }

        if (dp[i][sum + 1000] != -1) return dp[i][sum + 1000];

        // add nums[i]
        int addCount = solve(i + 1, sum + nums[i], nums, target);
        // subtract nums[i]
        int subCount = solve(i + 1, sum - nums[i], nums, target);

        return dp[i][sum + 1000] = addCount + subCount;
    }

    public int findTargetSumWays(int[] nums, int target) {
        dp = new int[nums.length][2001]; // sum range [-1000..1000] → shifted by +1000
        for (int[] arr : dp) Arrays.fill(arr, -1);

        return solve(0, 0, nums, target);
    }
}
