import java.awt.*;
import java.awt.event.*;

public class WindowCloser2 extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
	{
		Window w = e.getWindow();
		w.setVisible(false);
		w.dispose();
	}
}