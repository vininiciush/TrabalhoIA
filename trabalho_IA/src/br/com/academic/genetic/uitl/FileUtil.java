package br.com.academic.genetic.uitl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileUtil {

	public static BufferedImage loadImage(String fileName) {
		if (StringUtil.isNullOrEmpty(fileName)) {
			return null;
		}

		File file = new File(fileName);

		if (file.exists()) {
			try {
				return ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}
