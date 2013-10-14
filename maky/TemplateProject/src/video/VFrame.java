package video;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class VFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public VFrame()
	{
		super("JMF - Example...");
		
		setSize(400, 300);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth())/2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight())/2);
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent evt)
			{
				System.exit(0);
			}
		});
		
		setContentPane(new VPanel());
		setVisible(true);
	}
}
