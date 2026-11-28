import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] gain = new int[n + 1];

        for (int f = 1; f <= n; f++) {
            for (int j = f; j <= n; j += f) {
                gain[f] += freq[j];
            }
            gain[f]--; // Exclude the item itself.
        }

        List<int[]> deals = new ArrayList<>();

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            if (gain[factor] > 0 && (long) price < 2L * minPrice) {
                deals.add(new int[]{price, gain[factor]});
            }
        }

        deals.sort((a, b) -> Integer.compare(a[0], b[0]));

        long answer = 0;

        for (int[] deal : deals) {
            int price = deal[0];
            int available = deal[1];

            int count = Math.min(available, budget / price);

            answer += 2L * count;
            budget -= count * price;
        }

        answer += budget / minPrice;

        return (int) answer;
    }
}