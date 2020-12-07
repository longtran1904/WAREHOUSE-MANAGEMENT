package BusinessLogic;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SaveLocal {
    static Path path = Paths.get("BusinessLogic/data.txt");
    public static void save(List<String> list) throws IOException {
        Files.write(path, list, StandardCharsets.UTF_8);        
    }
}
