Got it 👍 Let’s write this out as if it’s your **DP notes / cheat sheet** for **Target Sum** and **Subset Sum**.

---

# 📘 DP Notes: Target Sum & Subset Sum

---

## 🔹 1. Subset Sum (classic)

**Problem**: Given `nums[]` and `target`, check if there exists a subset with sum = `target`.

### Recurrence

At index `i`, target `t`:

* **Take** the element → `solve(i+1, t - nums[i])`
* **Not take** → `solve(i+1, t)`

```
dp[i][t] = take || notTake
```

### Template (Top-Down)

```java
boolean solve(int i, int target, int[] nums, Boolean[][] dp) {
    if (target == 0) return true;       // subset found
    if (i == nums.length) return false; // ran out of numbers

    if (dp[i][target] != null) return dp[i][target];

    boolean take = false;
    if (target >= nums[i]) {
        take = solve(i + 1, target - nums[i], nums, dp);
    }
    boolean notTake = solve(i + 1, target, nums, dp);

    return dp[i][target] = (take || notTake);
}
```

### Catch

* **Target can be up to `sum(nums)`** → `dp` size = `n × sum`.
* Use `Boolean[][] dp` (not `boolean[][]`) to allow `null` as "not visited".

---

## 🔹 2. Target Sum (Leetcode 494)

**Problem**: Assign `+` or `-` to numbers to reach `target`.

### Transformation

Let total = sum(nums).
We want:

```
P - N = target
P + N = total
```

→ `P = (target + total) / 2`

So: **Count subsets with sum = P**.

---

### Template (Count Subset Sums)

```java
int subsetSumCount(int[] nums, int target) {
    int n = nums.length;
    int[][] dp = new int[n + 1][target + 1];
    
    // base case: one way to make sum=0 (empty subset)
    for (int i = 0; i <= n; i++) dp[i][0] = 1;

    for (int i = 1; i <= n; i++) {
        for (int t = 0; t <= target; t++) {
            int notTake = dp[i - 1][t];
            int take = (t >= nums[i - 1]) ? dp[i - 1][t - nums[i - 1]] : 0;
            dp[i][t] = take + notTake;
        }
    }
    return dp[n][target];
}
```

Or optimized 1D:

```java
int subsetSumCount(int[] nums, int target) {
    int[] dp = new int[target + 1];
    dp[0] = 1;
    for (int num : nums) {
        for (int t = target; t >= num; t--) {
            dp[t] += dp[t - num];
        }
    }
    return dp[target];
}
```

---

### Catch

1. `(target + total)` must be **even** → else return 0.
2. `|target| > total` → impossible → return 0.
3. Subset sum here is **counting ways**, not just existence.

---

## 🔹 3. Key Differences

| Problem            | Goal                                | Recurrence                 | Notes           |           |            |
| ------------------ | ----------------------------------- | -------------------------- | --------------- | --------- | ---------- |
| **Subset Sum**     | Check if subset exists with sum = K | \`take                     |                 | notTake\` | Boolean DP |
| **Target Sum**     | Count ways to assign +/- to reach T | Reduce to subset sum count | Integer DP      |           |            |
| **Knapsack (0/1)** | Max value under weight constraint   | `max(take, notTake)`       | Similar pattern |           |            |

---

## 🔹 4. Quick Cheat Sheet

* Use `Boolean[][] dp` if the answer is true/false (existence).
* Use `Integer[][] dp` or `int[][] dp` if the answer is count/ways.
* Always consider constraints:

  * Subset Sum: `target` up to sum of array.
  * Target Sum: must reduce to subset sum before DP.
* For counts: initialize `dp[0] = 1` (empty subset counts as 1 way).
* For existence: initialize `dp[0] = true` (empty subset is valid base).

---

✅ **In one line**:

* **Subset Sum** = can we? (boolean DP).
* **Target Sum** = how many ways? (count DP via subset sum).

---
