import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Pessoa p1 = new Pessoa(1, "Adriano");
        Pessoa p2 = new Pessoa(1, "Adriano");

        System.out.println(p1.equals(p2));
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());

        Set<Pessoa> pessoas = new HashSet<>();
        pessoas.add(p1);
        pessoas.add(p2);

        System.out.println(pessoas.size());
    }
}

class Pessoa {
    Integer id;
    String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pessoa(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
