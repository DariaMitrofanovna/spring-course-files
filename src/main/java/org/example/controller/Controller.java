package org.example.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.example.util.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Controller {

	@Autowired
	MinioUtils minioUtils;

	@GetMapping("/copyFileWithBytes")
	public void copyFileWithBytes() {
		File inputFile = Paths.get("./gb_file.txt").toFile();
		File outputFile = Paths.get("./output_file.txt").toFile();
		try {
			byte[] fileContent = Files.readAllBytes(inputFile.toPath());
			Files.write(outputFile.toPath(), fileContent);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@GetMapping("/copyFileWithStream")
	public void copyFileWithStream() {
		File inputFile = Paths.get("./input_file.txt").toFile();
		File outputFile = Paths.get("./output_file.txt").toFile();
		try (FileInputStream fis = new FileInputStream(inputFile);
				FileOutputStream fos = new FileOutputStream(outputFile)) {
			IOUtils.copyLarge(fis, fos);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@PostMapping("/uploadFile")
	public void uploadFile(@RequestParam("file") MultipartFile file) {
		File outputFile = Paths.get("./output_file.txt").toFile();
		try (InputStream fis = file.getInputStream();
				OutputStream fos = Files.newOutputStream(outputFile.toPath())) {
			IOUtils.copyLarge(fis, fos);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@PostMapping("/uploadFileToMinio")
	public void uploadFileToMinio(@RequestParam("file") MultipartFile file) {
		String uuid = UUID.randomUUID().toString();
		minioUtils.putFile(file, uuid, "default");
	}


}
