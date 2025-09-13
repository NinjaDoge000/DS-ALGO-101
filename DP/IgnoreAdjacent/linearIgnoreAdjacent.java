class LinearIgnoreAdjacent {
    
    Integer[] dp;

    int solve(int i, int[] nums ) {

        if(i >= nums.length) return 0;

        if(dp[i] != null) return dp[i];

        // rob this house
        int robbed = nums[i] + solve(i + 2, nums);

        // not rob this house
        int notRobbed = solve(i + 1, nums);


        return dp[i] =  Math.max(robbed, notRobbed);

    }

    public int rob(int[] nums) {
        
        dp = new Integer[nums.length + 1];
        return solve(0, nums);
    }
}