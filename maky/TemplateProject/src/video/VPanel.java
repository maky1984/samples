package video;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.RealizeCompleteEvent;
import javax.swing.JPanel;

public class VPanel extends JPanel implements ActionListener, ControllerListener
{
	private static final long serialVersionUID = 1L;
	
	private Component visualComponent;
	private Player player;
	
	public VPanel()
	{
		try
		{
			File file = new File("res/video1.mpg");
			//file.toURL();
			player = Manager.createPlayer(file.toURL());
			player.addControllerListener(this);
			
			player.start();
		}
		catch(NoPlayerException e)
		{
			e.printStackTrace();
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}

	public void actionPerformed(ActionEvent e)
	{

	}

	public void controllerUpdate(ControllerEvent c)
	{
		if(player == null)
			return;
		
		if(c instanceof RealizeCompleteEvent)
		{
			if((visualComponent = player.getVisualComponent()) != null)
				add(visualComponent);
		}
	}
}
