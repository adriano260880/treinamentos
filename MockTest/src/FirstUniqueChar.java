public class FirstUniqueChar {

    public int firstUniqueChar(String s) {
        if (s == null || s.isEmpty()) {
            return -1;
        }

        int[] frequency = new int[26];

        // Count frequency
        for (char c : s.toCharArray()) {
            frequency[c - 'a']++;
        }

        // Find first unique
        for (int i = 0; i < s.length(); i++) {
            if (frequency[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }
}
