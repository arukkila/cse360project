package cse360pro1;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
	
	// handle resizing
	class resizeListener extends ComponentAdapter {
        public void componentResized(ComponentEvent e) {
        	if (e.getComponent().getWidth() > 0 && e.getComponent().getHeight() > 0)
    		{
        		/*
        		 * This will trigger a resize, but the quality will be reduced for increasing sizes.
        		 * It's also possible that the resizing could happen after the icon is painted.
        		 * TODO: A better way would be to handle upscales as grabbing the image from the static
        		 * array and downscales as resizing, all in its own thread.
        		 */
    			setImage(curImage);
    		}
        }
	}
	
	/**
	 * Creates a UI component that represents an image of a die
	 */
	public DiceImage() {
		init();
		
		curSide = 0;
		curImage = images.get(0);
		setIcon(new ImageIcon(curImage));
		
		addComponentListener(new resizeListener());
	}
	
	/**
	 * Initialize the image array with images for each side of a die
	 */
	public static void init() {
		images.clear();
		for (int index = MIN_SIDE; index <= MAX_SIDE; ++index) {
			try {
				// get properly formatted filename e.g. "d6_0.png" for an empty die
				String imgPath = String.format(DICE_PATH, MAX_SIDE, index);
	        	InputStream inStream = GameGui.class.getResourceAsStream(imgPath);
	        	images.add(ImageIO.read(inStream));
			}
			catch (Exception e) {
				images.add(null);
			}
		}
	}
	
	/**
	 * Returns a resized image that's width x height.
	 * If any of the parameters are invalid, throws an IllegalArgumentException
	 * @param img		BufferedImage to resize
	 * @param width		New width (must be a positive integer)
	 * @param height	New height (must be a positive integer)
	 */
	public static BufferedImage resize(BufferedImage img, int width, int height) {
		// Width and height must be positive
		if (img == null || width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Width = " + width + ", height = " + height);
		}
		
	    Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = newImg.createGraphics();
	    g2.drawImage(tmp, 0, 0, null);
	    g2.dispose();

	    return newImg;
	}
	
	/**
	 * Returns a BufferedImage representation of a side
	 * @param side	Side to search for. If out of range, throws an IllegalArgumentException
	 */
	public static BufferedImage getSideImage(int side) {
		if (side < MIN_SIDE || side > MAX_SIDE) {
			throw new IllegalArgumentException(Integer.toString(side));
		}
		if (images.isEmpty()) {
			init();
		}
		return images.get(side); // side will also be to the right index here
	}
	
	/**
	 * Returns a BufferedImage representation of a side that's scaled to width x height
	 * @param side		Side to search for
	 * @param width		New width (must be a positive integer)
	 * @param height	New height (must be a positive integer)
	 */
	public static BufferedImage getSideImage(int side, int width, int height) {
		return DiceImage.resize(getSideImage(side), width, height); // resize proportionally
	}
	
	/**
	 * Returns a BufferedImage representation of a dice roll action,
	 * or null if that representation can't be found
	 */
	public static BufferedImage getRollImage() {
		try {
	    	InputStream inStream = GameGui.class.getResourceAsStream(String.format(ROLL_PATH, MAX_SIDE));
			return ImageIO.read(inStream);
		} 
		catch (Exception e) {
			return null;
		} 
	}
	
	/**
	 * Returns a BufferedImage representation of a dice roll action resized to width x height
	 * @param width		New width (must be a positive integer)
	 * @param height	New height (must be a positive integer)
	 */
	public static BufferedImage getRollImage(int width, int height) {
		return DiceImage.resize(getRollImage(), width, height); // resize proportionally
	}
	
	/**
	 * Sets the current image to display, resized to fit inside the component
	 * @param img	If null, throws an IllegalArgumentException
	 */
	private void setImage(BufferedImage img) {
		if (img == null) {
			throw new IllegalArgumentException();
		}
		
		// can't resize without an actual width or height
		if (getWidth() > 0 && getHeight() > 0) {
			curImage = resize(img, getWidth(), getHeight());
		}
		setIcon(new ImageIcon(curImage));
	}
	
	/**
	 * Sets the current side to display
	 * @param side	If out of range, throws an IllegalArgumentException
	 */
	public void setSide(int side) {
		if (side < MIN_SIDE || side > MAX_SIDE) {
			throw new IllegalArgumentException(Integer.toString(side));
		}
		curSide = side;
		setImage(getSideImage(side));
	}
	
	/**
	 * Returns the currently displayed side
	 */
	public int getSide() {
		return curSide;
	}
}
