package DP.MinMax.ScoreDifferenceMethod;
class Solution {
    int[][] dp;  // dp[i][j] will store the maximum score difference (currentPlayer - opponent)
    int n;

    // Recursive function: returns max difference Alice (or current player) can achieve
    // on the subarray nums[i...j]
    int solve(int i, int j, int[] nums) {

        // Base case: no elements left
        if (i > j) return 0;

        // If already computed, return memoized result
        if (dp[i][j] != -1) return dp[i][j];

        // Current player chooses nums[i]
        // -> Opponent plays optimally on (i+1, j)
        // -> Net gain = nums[i] - opponent’s best difference
        int leftDiff = nums[i] - solve(i + 1, j, nums);

        // Current player chooses nums[j]
        // -> Opponent plays optimally on (i, j-1)
        // -> Net gain = nums[j] - opponent’s best difference
        int rightDiff = nums[j] - solve(i, j - 1, nums);

        // Current player tries to maximize their advantage
        return dp[i][j] = Math.max(leftDiff, rightDiff);
    }

    public boolean predictTheWinner(int[] nums) {
        n = nums.length;

        // Initialize DP table with -1 (uncomputed states)
        dp = new int[n][n];
        for (int[] arr : dp) Arrays.fill(arr, -1);

        // Start from full array (0...n-1), Alice plays first
        int ans = solve(0, n - 1, nums);

        // If Alice’s final advantage is >= 0, she can win or tie
        return ans >= 0;
    }
}
