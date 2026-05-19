import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        int steps = 1000000;


        String path = Files.readString(Path.of("./src/teste.txt"));

        int seaLevel = 0;
        int seaLevelM = 0;
        int valley = 0;
        for (char cPath : path.toCharArray()) {
            if (cPath == 'U') {
                seaLevel++;
            } else {
                seaLevel--;
            }

            if (seaLevel < 0 && seaLevelM == 0) {
                valley++;
            }

            seaLevelM = seaLevel;
           // System.out.println(seaLevel +" - "+ seaLevelM);
        }
            System.out.println(valley);

    }
}