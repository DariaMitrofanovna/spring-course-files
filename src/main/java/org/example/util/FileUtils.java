package org.example.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class FileUtils {

	public static void readFileWithInputStream(File file) {
		try (FileInputStream fis = new FileInputStream(file)) {
			byte[] bytes = new byte[fis.available()];
			fis.read(bytes, 0, bytes.length);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void writeFileWithOutputStream(File file) {
		try (FileOutputStream fos = new FileOutputStream(file)) {
			byte[] bytes = "writeFileWithOutputStream".getBytes();
			fos.write(bytes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void readFileWithBufferedInputStream(File file) {
		try (FileInputStream fis = new FileInputStream(file);
				BufferedInputStream bfis = new BufferedInputStream(fis)) {
			byte[] bytes = new byte[bfis.available()];
			bfis.read(bytes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void writeFileWithBufferedOutputStream(File file) {
		try (FileOutputStream fos = new FileOutputStream(file);
				BufferedOutputStream bfos = new BufferedOutputStream(fos)) {
			byte[] bytes = "writeFileWithBufferedOutputStream".getBytes();
			bfos.write(bytes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void readFileWithReader(File file) {
		try (FileReader fr = new FileReader(file)) {
			char[] chars = new char[100];
			fr.read(chars);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void writeFileWithWriter(File file) {
		try (FileWriter fw = new FileWriter(file)) {
			fw.write("writeFileWithWriter");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void readFileWithBufferedReader(File file) {
		try (FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr)) {
			char[] chars = new char[100];
			br.read(chars);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void writeFileWithBufferedWriter(File file) {
		try (FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write("writeFileWithBufferedWriter");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
