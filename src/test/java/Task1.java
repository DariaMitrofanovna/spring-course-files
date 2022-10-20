import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1 {

	//	todo поправить тест, чтоб работало
	@Test
	void test() {
		File inputFile = Paths.get("./gb_file.txt").toFile();
		File outputFile = Paths.get("./output_file.txt").toFile();
		try {
			byte[] fileContent = Files.readAllBytes(inputFile.toPath());
			Files.write(outputFile.toPath(), fileContent);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Assertions.assertTrue(outputFile.length() != 0);
	}


}
