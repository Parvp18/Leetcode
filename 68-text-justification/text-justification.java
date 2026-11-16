
import java.util.*;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        int i = 0;
        int n = words.length;

        while (i < n) {
            int j = i;
            int letters = 0;

            while (j < n && letters + words[j].length() + (j - i) <= maxWidth) {
                letters += words[j].length();
                j++;
            }

            int gaps = j - i - 1;
            int spaces = maxWidth - letters;

            StringBuilder line = new StringBuilder();

            if (j == n || gaps == 0) {
                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k < j - 1) {
                        line.append(" ");
                    }
                }

                while (line.length() < maxWidth) {
                    line.append(" ");
                }
            } 
            else {
                int spacesPerGap = spaces / gaps;
                int extraSpaces = spaces % gaps;

                for (int k = i; k < j - 1; k++) {
                    line.append(words[k]);

                    int count = spacesPerGap;

                    if (extraSpaces > 0) {
                        count++;
                        extraSpaces--;
                    }

                    for (int s = 0; s < count; s++) {
                        line.append(" ");
                    }
                }

                line.append(words[j - 1]);
            }

            result.add(line.toString());
            i = j;
        }

        return result;
    }
}
