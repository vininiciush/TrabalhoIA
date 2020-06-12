package br.com.academic.genetic.uitl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

public class FileUtil {

	public static BufferedImage loadImage(String fileName) {
		File file = new File(fileName);

		if (file.exists()) {
			try {
				return ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		throw new NoSuchElementException("Imagem não encontrada");
	}
}
