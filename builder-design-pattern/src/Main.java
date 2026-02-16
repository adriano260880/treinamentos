public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        User user = new User.Builder("Adriano")
                .email("adriano260880@gmail.com")
                .phone("19-996511771")
                .address("Rua que sobe e desce e numca aparece")
                .age(45)
                .build();

        User user2 = new User.Builder("Adriano2")
                .age(45)
                .build();

        System.out.println(user.toString());
        System.out.println(user2.toString());
    }

}