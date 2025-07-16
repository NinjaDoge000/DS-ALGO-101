from typing import List

class Solution:
    def meetRequirement(self, n: int, lights: List[List[int]], requirement: List[int]) -> int:


        delta = [0] * (n + 1)
        res = 0
        for position, coverage in lights:
            right_range = min(n - 1, position + coverage)
            left_range = max(0, position - coverage)
            
            # difference array
            delta[left_range]  += 1
            delta[right_range + 1] -=1
        
        # prefix sum
        actual_lighting = 0
        for i in range(n):

            actual_lighting += delta[i]
            if actual_lighting >= requirement[i]:
                res += 1

        return res

sol = Solution()
result = sol.meetRequirement(5, [[0,1],[2,1],[3,2]], [0,2,1,4,1])
print(result)

