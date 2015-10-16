package br.com.ftech.webcom.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class CodigoBarras {

	public String geraCodigoBarras(String value) {
		try {
			Barcode barcode = BarcodeFactory.createCode128(null);
			BufferedImage image = new BufferedImage(220, 130, BufferedImage.TYPE_BYTE_GRAY);
			Graphics2D g = (Graphics2D) image.getGraphics();
			g.setBackground(Color.BLUE);
			barcode.draw(g, 10, 56);
			File f = new File(value + ".jpg");
			BarcodeImageHandler.saveJPEG(barcode, f);
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return  null;
	}

}
