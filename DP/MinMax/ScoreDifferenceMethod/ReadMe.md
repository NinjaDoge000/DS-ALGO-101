P---

# 🎮 Minimax with Memoization for Score Difference

## 📌 Problem Context

This approach is often used in **two-player zero-sum games** like:

* **Predict the Winner** (Leetcode 486)
* Stone Game variations
* Array partition games where **Alice and Bob** take turns picking numbers

The key idea:

* Players pick numbers alternately from a given range.
* Both play **optimally**.
* We want to check if **Player 1 (Alice)** can guarantee a win or tie.

---

## 🧠 Core Idea (Minimax with Score Difference)

Instead of tracking each player’s absolute score, we track the **score difference**:

$$
\text{difference} = \text{CurrentPlayerScore} - \text{OpponentScore}
$$

At any step:

* If Alice picks a number → she gains it, then Bob plays optimally.
* If Bob picks a number → he gains it, then Alice plays optimally.

So, the recurrence is:

$$
f(i, j) = \max(nums[i] - f(i+1, j), \; nums[j] - f(i, j-1))
$$

Where:

* `f(i, j)` = maximum difference the current player can achieve from subarray `nums[i...j]`.

---

## 🏗️ Approach

1. **Recursive Definition**

   * Base case: if `i > j`, return 0 (no numbers left).
   * Recursive case:

     * Pick left → `nums[i] - solve(i+1, j)`
     * Pick right → `nums[j] - solve(i, j-1)`
     * Take the max.

2. **Memoization**

   * Use a DP table `dp[i][j]` to store computed values of `solve(i, j)`.

3. **Final Check**

   * Compute `ans = solve(0, n-1)` (Alice’s max advantage over Bob).
   * If `ans >= 0` → Alice can win or tie.

---

## 💻 Java Implementation

```java
class Solution {
    int[][] dp;

    // Returns the max difference current player can achieve on nums[i...j]
    int solve(int i, int j, int[] nums) {
        if (i > j) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        int leftDiff = nums[i] - solve(i + 1, j, nums);
        int rightDiff = nums[j] - solve(i, j - 1, nums);

        return dp[i][j] = Math.max(leftDiff, rightDiff);
    }

    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);

        int ans = solve(0, n - 1, nums);
        return ans >= 0; // Alice can win or tie
    }
}
```

---

## 📊 Complexity

* **States:** `O(n^2)` (for every pair `(i, j)`)
* **Work per state:** `O(1)`
* **Time Complexity:** `O(n^2)`
* **Space Complexity:** `O(n^2)` for DP table + recursion stack

---

## ✅ Key Takeaways

* Use **score difference** (`current - opponent`) to simplify logic.
* Minimax recursion → optimize with **memoization**.
* Final result `ans >= 0` means **Player 1 (Alice) can guarantee non-loss**.
