//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        FirstUniqueChar firstUniqueChar = new FirstUniqueChar();
        LengthOfLongestSubstring length = new LengthOfLongestSubstring();
        TopKFrequent topKFrequent = new TopKFrequent();
        //System.out.println(firstUniqueChar.firstUniqueChar("abcdefghijklmnopqrstuvwxyzab"));
        //System.out.println(length.lengthOfLongestSubstring("pwwkew"));

        System.out.println(topKFrequent.topKFrequent(new int[]{
                10,9,8,7,6,5,4,3,2,1
        }, 3));


    }
}