import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<String> names = List.of("Adriano",
                "Aparecido",
                "Lopes",
                "Amanda",
                "Laércio");

        List<String> result =
                names.stream()
                        .filter(n -> n.startsWith("L"))
                        .sorted()
                        .toList();

        System.out.println(result);

        List<Person> people = List.of(
                new Person(18, "Lopes"),
                new Person(18, "Teste"),
                new Person(23, "Aparecido"),
                new Person(45, "Teste")
        );

        Map<Integer, List<Person>> result2 =
              people.stream()
                      .collect(Collectors.groupingBy(p -> p.age()));

        System.out.println(result2);

        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);

        Map<Boolean, List<Integer>> result3 =
                numbers.stream()
                        .collect(Collectors.partitioningBy(n -> n > 4));

        System.out.println(result3);
    }
}