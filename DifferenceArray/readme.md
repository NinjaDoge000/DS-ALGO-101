A **Difference Array** is a technique used to efficiently apply range updates on an array.

Instead of updating every element in a range directly, you:

* Mark the **start** of the increment.
* Mark the **end + 1** with a decrement.

Then, a **prefix sum** converts this difference array back into the final result.

---

## 🔥 Why Use It?

* To perform multiple **range updates** efficiently in **O(1) per update**, rather than O(N) per update.
* Especially useful when applying many range additions or subtractions.

---

## 📘 Basic Example:

### Problem:

* Start with array of zeros: `[0, 0, 0, 0, 0]`
* Perform:

  * Add `+5` to range `[1, 3]`
  * Add `+2` to range `[2, 4]`

---

### Using Difference Array:

1. Original difference array: `[0, 0, 0, 0, 0, 0]`

2. First update: add `+5` from `1` to `3`.

   * `diff[1] += 5`
   * `diff[4] -= 5`

3. Second update: add `+2` from `2` to `4`.

   * `diff[2] += 2`
   * `diff[5] -= 2`

Difference array after updates:

```
[0, 5, 2, 0, -5, -2]
```

4. Now take **prefix sum** to get the final array:

```
[0, 5, 7, 7, 2]
```

---

## 🎯 Why It’s Like Sweep Line:

* Each range update is treated like:

  * **Start event** (add).
  * **End event** (subtract).
* Prefix sum acts like sweeping across positions to accumulate the effect.

---

## 📊 Difference Array vs Sweep Line:

| Feature          | Difference Array      | Sweep Line with Heap        |
| ---------------- | --------------------- | --------------------------- |
| Used for         | Cumulative updates    | Dynamic active set tracking |
| Data structure   | Array + prefix sum    | Heap, TreeMap, or multiset  |
| Example problems | Range update problems | Skyline, Interval Covering  |
| Tracks           | Total count/value     | Max/min active element      |

---

Let me know if you'd like to solve a problem using a difference array to practice!
