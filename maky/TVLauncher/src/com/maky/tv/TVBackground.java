package com.maky.tv;

import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.GLBuffers;

public class TVBackground implements IAppComp {

	private int texture;

	@Override
	public void start() {
	}
	
	@Override
	public void init(GL2 gl2, GLU glu) {
		gl2.glEnable(GL2.GL_TEXTURE_2D);
		texture = genTexture(gl2);
		gl2.glBindTexture(GL2.GL_TEXTURE_2D, texture);
		BufferedImage img = readPNGImage("res/NeHe.png");
		makeRGBTexture(gl2, glu, img, GL2.GL_TEXTURE_2D);
		gl2.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
		gl2.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
	}
	
	@Override
	public void update() {
	}

	@Override
	public void render(GL2 gl2) {
		gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
	    gl2.glBindTexture(GL2.GL_TEXTURE_2D, texture);
		gl2.glBegin(GL2.GL_TRIANGLES);
		gl2.glTexCoord2f(0.0f, 0.0f);gl2.glVertex3f(0.0f, 1.0f, 0.0f); // lower left vertex
		gl2.glTexCoord2f(1.0f, 0.0f);gl2.glVertex3f(1.0f, 1.0f, 0.0f); // lower right vertex
		gl2.glTexCoord2f(1.0f, 1.0f);gl2.glVertex3f(1.0f, 0.0f, 0.0f); // upper vertex
		//gl2.glTexCoord2f(0.0f, 1.0f);
		gl2.glEnd();
	}

	@Override
	public void action(int actionUpReleased) {
	}
	
	
//    public void display(GLDrawable gLDrawable)
//    {
//      final GL gl = gLDrawable.getGL();
//      gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
//
//      gl.glBindTexture(GL.GL_TEXTURE_2D, texture);
//
//      gl.glBegin(GL.GL_QUADS);
//        // Front Face
//        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);
//        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);
//        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);
//        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);
//        // Back Face
//        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);
//        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);
//        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);
//        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);
//        // Top Face
//        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);
//        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);
//        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);
//        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);
//        // Bottom Face
//        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);
//        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);
//        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);
//        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);
//        // Right face
//        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);
//        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);
//        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);
//        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);
//        // Left Face
//        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);
//        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);
//        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);
//        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);
//      gl.glEnd();
//
//    }
            
	private BufferedImage readPNGImage(String resourceName) {
		try {
			URL url = getResource(resourceName);
			if (url == null) {
				throw new RuntimeException("Error reading resource " + resourceName);
			}
			BufferedImage img = ImageIO.read(url);
			java.awt.geom.AffineTransform tx = java.awt.geom.AffineTransform.getScaleInstance(1, -1);
			tx.translate(0, -img.getHeight(null));
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			img = op.filter(img, null);
			return img;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
    
	private void makeRGBTexture(GL2 gl, GLU glu, BufferedImage img, int target) {
		ByteBuffer dest = null;
		switch (img.getType()) {
		case BufferedImage.TYPE_3BYTE_BGR:
		case BufferedImage.TYPE_CUSTOM: {
			byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
			dest = ByteBuffer.allocateDirect(data.length);
			dest.order(ByteOrder.nativeOrder());
			dest.put(data, 0, data.length);
			break;
		}
		case BufferedImage.TYPE_INT_RGB: {
			int[] data = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
			dest = ByteBuffer.allocateDirect(data.length * GLBuffers.SIZEOF_INT);
			dest.order(ByteOrder.nativeOrder());
			dest.asIntBuffer().put(data, 0, data.length);
			break;
		}
		default:
			throw new RuntimeException("Unsupported image type " + img.getType());
		}
		dest.rewind();
		gl.glTexImage2D(target, 0, GL2.GL_RGB, img.getWidth(), img.getHeight(), 0, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE,
				dest);
	}

	private int genTexture(GL2 gl2) {
		final int[] tmp = new int[1];
		gl2.glGenTextures(1, IntBuffer.wrap(tmp));
		return tmp[0];
	}

	/**
	 * Retrieve a URL resource from the jar. If the resource is not found, then
	 * the local disk is also checked.
	 * 
	 * @param filename
	 *            Complete filename, including parent path
	 * @return a URL object if resource is found, otherwise null.
	 */
	public final static URL getResource(final String filename) {
		// Try to load resource from jar
		URL url = ClassLoader.getSystemResource(filename);
		// If not found in jar, then load from disk
		if (url == null) {
			try {
				url = new URL("file", "localhost", filename);
			} catch (Exception urlException) {
			} // ignore
		}
		return url;
	}

}
