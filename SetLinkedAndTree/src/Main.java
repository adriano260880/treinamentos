import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();
        numbers.add(9);
        numbers.add(0);
        numbers.add(1);
        numbers.add(3);
        numbers.add(7);
        numbers.add(6);
        numbers.add(5);
        numbers.add(8);
        numbers.add(4);
        numbers.add(2);
        numbers.add(null);
        numbers.add(null);


        Set<Integer> setList = new HashSet<>();
        Set<Integer> treeList = new TreeSet<>();
        Set<Integer> linkedList = new LinkedHashSet<>();

        for (Integer number : numbers) {
            setList.add(number);
            linkedList.add(number);
            if (number != null) {
                treeList.add(number);
            }

        }

        System.out.println(setList);
        System.out.println(treeList);
        System.out.println(linkedList);
        System.out.println(numbers);


    }
}