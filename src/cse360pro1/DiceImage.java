package cse360pro1;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

// TODO: handle window resizing
public class DiceImage extends JLabel
{
	// image access
	private static ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	private final static String DICE_PATH = "/res/d%d_%d.png";
    private final static String ROLL_PATH = "/res/d%d_roll.png";
	private final static int MIN_SIDE = 0;
	private final static int MAX_SIDE = 6;
	
	// image contents
	private BufferedImage curImage;
	private int curSide;
	
	public DiceImage() 
	{
		init();
		
		curSide = 0;
		curImage = images.get(0);
		setIcon(new ImageIcon(curImage));
	}
	
	public static void init()
	{
		images.clear();
		for (int index = MIN_SIDE; index <= MAX_SIDE; ++index)
		{
			try
			{
				// TODO: make this not fail inside eclipse
				// get properly formatted filename e.g. "d6_0.png" for an empty die
				String imgPath = String.format(DICE_PATH, MAX_SIDE, index);
	        	InputStream inStream = GameGui.class.getResourceAsStream(imgPath);
	        	images.add(ImageIO.read(inStream));
			}
			catch (Exception e)
			{
				images.add(null);
			}
		}
	}
	
	/**
	 * Returns a resized image that's width x height
	 * @param img
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage resize(BufferedImage img, int width, int height) 
	{
		// Width or height and height must be positive
		if (img != null && width > 0 && height > 0)
		{
		    Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		    BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2 = newImg.createGraphics();
		    g2.drawImage(tmp, 0, 0, null);
		    g2.dispose();
	
		    return newImg;
		}
		return img;
	}
	
	public static BufferedImage getSideImage(int side)
	{
		if (images.isEmpty())
		{
			init();
		}
		if (side >= MIN_SIDE && side <= MAX_SIDE)
		{
			return images.get(side); // curSide will correspond to the image of that curSide here
		}
		return null;
	}
	
	public static BufferedImage getSideImage(int side, int width, int height)
	{
		return DiceImage.resize(getSideImage(side), width, height); // resize proportionally
	}
	
	public static BufferedImage getRollImage()
	{
		try 
		{
			// TODO: make this not fail inside eclipse
	    	InputStream inStream = GameGui.class.getResourceAsStream(String.format(ROLL_PATH, MAX_SIDE));
			return ImageIO.read(inStream);
		} 
		catch (Exception e) 
		{
			return null;
		} 
	}
	
	public static BufferedImage getRollImage(int width, int height)
	{
		return DiceImage.resize(getRollImage(), width, height); // resize proportionally
	}
	
	private void setImage(BufferedImage img)
	{
		if (img != null)
		{
			// can't resize without an actual width or height
			if (getWidth() > 0 && getHeight() > 0)
			{
				curImage = resize(img, getWidth(), getHeight());
			}
			setIcon(new ImageIcon(curImage));
		}
	}
	
	public void setSide(int side)
	{
		if (side >= MIN_SIDE && side <= MAX_SIDE)
		{
			curSide = side;
			setImage(getSideImage(side));
		}
	}
	
	public int getSide()
	{
		return curSide;
	}
}
