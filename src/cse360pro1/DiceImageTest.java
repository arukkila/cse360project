package cse360pro1;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Test;

public class DiceImageTest 
{
	/**
	 * Test method for {@link cse360pro1.DiceImage#DiceImage()}.
	 */
	@Test
	public final void testDiceImage() 
	{
		DiceImage dimg = new DiceImage();
		assertNotNull(dimg);
	}
	
	/**
	 * Test method for {@link cse360pro1.DiceImage#setSide(int)}.
	 */
	@Test
	public final void testSetSide() 
	{
		DiceImage dimg = new DiceImage();
		dimg.setSide(6);
		assertEquals(6, dimg.getSide());
	}

	/**
	 * Test method for {@link cse360pro1.DiceImage#getSide()}.
	 */
	@Test
	public final void testGetSide() 
	{
		// default side
		DiceImage dimg = new DiceImage();
		assertEquals(0, dimg.getSide());
	}
	
	/**
	 * Test method for {@link cse360pro1.DiceImage#getSideImage(int)}.
	 */
	@Test
	public final void testGetSideImageInt() 
	{
		BufferedImage img = DiceImage.getSideImage(0);
		assertNotNull(img);
	}

	/**
	 * Test method for {@link cse360pro1.DiceImage#resize(java.awt.image.BufferedImage, int, int)}.
	 */
	@Test
	public final void testResizeBufferedImageIntInt() 
	{
		BufferedImage img = DiceImage.resize(DiceImage.getSideImage(0), 50, 50);
		assertNotNull(img);
	}

	/**
	 * Test method for {@link cse360pro1.DiceImage#getSideImage(int, int, int)}.
	 */
	@Test
	public final void testGetSideImageIntIntInt() 
	{
		BufferedImage img = DiceImage.getSideImage(0, 50, 50);
		assertNotNull(img);
	}

	/**
	 * Test method for {@link cse360pro1.DiceImage#getRollImage()}.
	 */
	@Test
	public final void testGetRollImage() 
	{
		BufferedImage img = DiceImage.getRollImage();
		assertNotNull(img);
	}

	/**
	 * Test method for {@link cse360pro1.DiceImage#getRollImage(int, int)}.
	 */
	@Test
	public final void testGetRollImageIntInt() 
	{
		BufferedImage img = DiceImage.getRollImage(50, 50);
		assertNotNull(img);
	}
}
