
## 🔎 Why we need index shifting

* In recursion we track `sum`, which can be **negative or positive**.
* Array indices in Java must be **non-negative**.
* To handle this, we use a **shift** so that all possible sums map to valid indices.

---

## 📊 Range of `sum`

* Problem constraint: each number ≤ 1000, `n ≤ 20`.
* Max absolute sum = 1000.
* So possible sums = from `-1000` to `+1000`.
* That’s `2001` distinct values.

---

## 🎯 Shifting formula

We map:

```
actual sum → dp array index
```

by adding an offset of `+1000`.

* Example:

  * sum = -1000 → index = -1000 + 1000 = 0
  * sum = 0 → index = 0 + 1000 = 1000
  * sum = +1000 → index = 1000 + 1000 = 2000

So any valid `sum` is shifted into range `[0 … 2000]`.

---

## ✅ Code usage

### Declaration

```java
dp = new int[nums.length][2001]; 
for (int[] row : dp) Arrays.fill(row, -1);
```

### Lookup

```java
if (dp[i][sum + 1000] != -1) return dp[i][sum + 1000];
```

### Store

```java
return dp[i][sum + 1000] = addCount + subCount;
```

---

## ⚡ Example walk-through

Say `nums = [1,1]`, `target = 0`.

1. Start: `solve(0, 0, nums, 0)`

   * `sum = 0 → index = 1000`
   * lookup `dp[0][1000]`

2. Take `+1`: `solve(1, 1, nums, 0)`

   * `sum = 1 → index = 1001`

3. Take `-1`: `solve(1, -1, nums, 0)`

   * `sum = -1 → index = 999`

All sums map safely into `[0..2000]`.

---

✅ So the trick is:

* Always shift `sum` by `+1000` whenever you use it as an index.
* This way, negatives become valid indices.

---