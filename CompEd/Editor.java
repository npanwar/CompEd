import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.DirectoryStream;

import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;

class Editor implements ActionListener {
	JFrame f;
	JTextPane tp ;
	JMenuBar m,m2;
	JFileChooser fc;
	File fileedit;
	FileReader fr;
	FileWriter fo;
	JMenu file,edit,run,help;
	JMenuItem new1,open,save,saveas,exit;
	JMenuItem cut,copy,paste,selectall,openReadMe;
	JMenuItem compile,run1,compileAndRun,cmdopen;
	JComboBox tf,cfont;
	String copyData,tsize="";
	Editor(){
		f = new JFrame();
		tp = new JTextPane();
		tp.setText("C ,C++ and  Java Files are  Compiled and Run \n And there is option to open cmd also!! \n");
		tp.setBackground(Color.BLACK);
		tp.setForeground(Color.WHITE);
		tp.setCaretColor(Color.white);
		
		//setsize
			tf=new JComboBox();
			for(int i=14;i<50;i++)
				tf.addItem(i+"");
			tf.setSelectedItem("20");
			
			tf.setMaximumSize(new Dimension(40,20));
			tf.addActionListener(this);
			tf.setToolTipText("Text Size");
			cfont=new JComboBox();
			String fonts[] = 
				      GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

				    for ( int i = 0; i < fonts.length; i++ )
				    {
				    	cfont.addItem(fonts[i]);
				    }
			cfont.setMaximumSize(new Dimension(150,20));
			cfont.addActionListener(this);
			cfont.setSelectedItem("Arial");
			cfont.setToolTipText("Text font");
		JPanel noWrapPanel = new JPanel( new BorderLayout() );
		noWrapPanel.add( tp );
		JScrollPane scrollPane = new JScrollPane( noWrapPanel );
		//file related
		fc = new JFileChooser();
		fc.setCurrentDirectory(new File("C:/Users/Vaibhav Bhatia/Desktop/"));
//		fc.setCurrentDirectory(new File);
		m = new JMenuBar();
		m2= new JMenuBar();
		//Menu
		file= new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		file.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
			//Menu Item
			new1 =new JMenuItem("New     ");
			new1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
			new1.addActionListener(this);
			open =new JMenuItem("Open    ");
			open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
			open.addActionListener(this);
			save =new JMenuItem("Save    ");
			save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
			save.addActionListener(this);
			saveas =new JMenuItem("Save As ");
			saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
			saveas.addActionListener(this);
			exit =new JMenuItem("Exit    ");
			exit.addActionListener(this);
			
			
		
		edit= new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
			//MenuItem
			copy = new JMenuItem("Copy   ");
			copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
			copy.addActionListener(this);
			cut = new JMenuItem("Cut   ");
			cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
			cut.addActionListener(this);
			paste = new JMenuItem("Paste   ");
			paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
			paste.addActionListener(this);
			paste.setEnabled(false);
			selectall =new JMenuItem("Select All  ");
			selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
			selectall.addActionListener(this);
			
		run= new JMenu("Run");
		run.setMnemonic(KeyEvent.VK_R);
			//MenuItem
			run1 = new JMenuItem("Run");
			run1.addActionListener(this);
			compile = new JMenuItem("Compile");
			compile.addActionListener(this);
			compileAndRun = new JMenuItem("Compile and Run");
			compileAndRun.addActionListener(this);
			cmdopen = new JMenuItem("Open CMD");
			cmdopen.addActionListener(this);
		
		help= new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		help.setToolTipText("Help Menu");
			openReadMe = new JMenuItem("Open ReadME file");
			
		//adding MenuItems
			file.add(new1);
			file.add(open);
			file.addSeparator();
			file.add(save);
			file.add(saveas);
			file.addSeparator();
			file.add(exit);
			edit.add(selectall);
			edit.add(copy);
			edit.add(cut);
			edit.add(paste);
			run.add(run1);
			run.add(compile);
//			run.add(compileAndRun);
			run.add(cmdopen);
		//---------
		
		m.add(file);
		m.add(edit);
		m.add(run);
		m.add(tf);
		m.add(cfont);
		m.add(help);
		//
		tp.setMargin(new Insets(10,10,0,0));
		Image image;
		f.setIconImage(new ImageIcon("icon.png").getImage());
		f.setJMenuBar(m);
		f.add(scrollPane);
		f.setTitle("Editor");
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600, 400);
		f.setLocation(200, 100);
		UIManager.getDefaults().put("Button.showMnemonics", Boolean.TRUE);
		f.addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		        tp.requestFocusInWindow();
		    }
		});
	}
	void openCmd()
	{
		try {
			Process p= Runtime.getRuntime().exec("cmd /c start cmd.exe /k");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void savecurrentnote(int retval)
	{
		if(retval==JFileChooser.APPROVE_OPTION)
		{
			fileedit=fc.getSelectedFile();
			try{
				fo = new FileWriter(fileedit);
				String filetext=tp.getText().toString();
				System.out.println(filetext);
				int i=0;
				while(i<filetext.length())
				{
					fo.write(filetext.charAt(i));
					i++;
				}
				fo.close();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(new JFrame(),e);
			}
			f.setTitle(fileedit.getName());
		}
	}
	void openFileSelected(int retval)
	{
		if(retval==JFileChooser.APPROVE_OPTION)
		{
			System.out.println(JFileChooser.APPROVE_OPTION);
			fileedit=fc.getSelectedFile();
			try {
				fr= new FileReader(fileedit);
				int c;
				String str="";
				while((c=fr.read())!=-1)
				{
					str+=(char)c;
				}
				tp.setText(str);
				f.setTitle(fileedit.getName());
			} 
			catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, e);
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
	public void compileFile() 
	{
		if(f.getTitle().equals("Editor"))
		{
			savecurrentnote(fc.showSaveDialog(f));
		}
		File dir=fc.getSelectedFile().getParentFile();
		String filename=fileedit.getName();
		String fl[]= new String[3];
		fl=filename.split("[.]");
		String pathexe=fl[0]+".exe";
		String path=fl[0]+"."+fl[1].toLowerCase();
		Runtime rt = Runtime.getRuntime();
		String commands = "";
		if(fl[1].toLowerCase().equals("cpp"))
		{
			commands=" cmd.exe /c g++ "+path;
			JOptionPane.showMessageDialog(f, "g++ compiler not found \n if problem persists open cmd under run option and type\n g++ filename.cpp -o filename.exe \n to compile ");
			return;
		}
		else if(fl[1].toLowerCase().equals("c"))
		{
			commands="cmd /c gcc.exe "+path+" -o "+pathexe;
			JOptionPane.showMessageDialog(f, "gcc compiler not found \n if problem persists open cmd under run option and type\n gcc filename.c -o filename.exe \n to compile ");
			return;
		}
		else if(fl[1].toLowerCase().equals("java"))
		{
			commands="cmd.exe /c javac "+path+"";
		}
		else
		{
			JOptionPane.showMessageDialog(f, "Can Only Compile C,C++and Java Source File");
			return;
		}
		System.out.println(commands);
		Process proc;
		try {
				proc = rt.exec(commands,null,dir);
				BufferedReader stdInput = new BufferedReader(new 
				     InputStreamReader(proc.getInputStream()));

				BufferedReader stdError = new BufferedReader(new 
				     InputStreamReader(proc.getErrorStream()));

				// read the output from the command
				System.out.println("Here is the standard output of the command:\n");
				String strOutput="",strError="";
				String s = null;
				while ((s = stdInput.readLine()) != null) {
				    strOutput=strOutput+s+"\n";
				}
				// read any errors from the attempted command
				System.out.println("Here is the standard error of the command (if any):\n");
				while ((s = stdError.readLine()) != null) {
				    strError=strError+s+"\n";
				}
				if(!strError.equals(""))
				{
					JOptionPane.showConfirmDialog(f, "Error Message: \n"+ strError, "Compiler Message", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showConfirmDialog(f, "Successfully Compiled \n"+strOutput, "Compiler Message", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
				}
		            int exitVal = proc.waitFor();
		            System.out.println("ExitValue: " + exitVal);


		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	public void runFile(){
		if(f.getTitle().equals("Editor"))
		{
			savecurrentnote(fc.showSaveDialog(f));
			compileFile();
		}
		File dir=fc.getSelectedFile().getParentFile();
		String filename=fileedit.getName();
		String fl[]= new String[3];
		fl=filename.split("[.]");
		Runtime rt = Runtime.getRuntime();
		String commands = "";
		if(fl[1].toLowerCase().equals("cpp"))
		{
			commands=" cmd.exe /c "+fl[0]+".exe" ;
//			JOptionPane.showMessageDialog(f, "g++ compiler not found \n if problem persists open cmd under run option and type\n g++ filename.cpp -o filename.exe \n to compile ");
		}
		else if(fl[1].toLowerCase().equals("c"))
		{
			commands=" cmd.exe /c "+fl[0]+".exe" ;
//			JOptionPane.showMessageDialog(f, "gcc compiler not found \n if problem persists open cmd under run option and type\n gcc filename.c -o filename.exe \n to compile ");
		}
		else if(fl[1].toLowerCase().equals("java"))
		{
			commands="cmd.exe /c java "+fl[0];
		}
		else
		{
			JOptionPane.showMessageDialog(f, "Can Only Compile C,C++and Java Source File");
			return;
		}
		System.out.println(commands);
		Process proc;
		try {
				proc = rt.exec(commands,null,dir);
				BufferedReader stdInput = new BufferedReader(new 
				     InputStreamReader(proc.getInputStream()));

				BufferedReader stdError = new BufferedReader(new 
				     InputStreamReader(proc.getErrorStream()));

				// read the output from the command
				System.out.println("Here is the standard output of the command:\n");
				String strOutput="",strError="";
				String s = null;
				while ((s = stdInput.readLine()) != null) {
				    strOutput=strOutput+s+"\n";
				}
				// read any errors from the attempted command
				System.out.println("Here is the standard error of the command (if any):\n");
				while ((s = stdError.readLine()) != null) {
				    strError=strError+s+"\n";
				}
				if(!strError.equals(""))
				{
					JOptionPane.showConfirmDialog(f, "Error Message: \n"+ strError, "Compiler Message", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showConfirmDialog(f, "Successfully Compiled \n"+strOutput, "Compiler Message", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
				}
		            int exitVal = proc.waitFor();
		            System.out.println("ExitValue: " + exitVal);


		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==new1)
		{
			f.setTitle("Editor");
			tp.setText("");
			System.out.println("new Clicked");
		}
		else if(e.getSource()==open)
		{
			int re=0;
			re=fc.showOpenDialog(f);
			openFileSelected(re);
			System.out.println("Open Clicked");
		}
		else if(e.getSource()==save)
		{
			int re=0;
			if(f.getTitle()=="Editor")		
				re = fc.showSaveDialog(f);
			else
				re=0;
			savecurrentnote(re);
			System.out.println("Save Clicked");
		}
		else if(e.getSource()==saveas)
		{
			int re=0;
			re=fc.showSaveDialog(f);
			savecurrentnote(re);
			System.out.println("save as Clicked");
		}
		else if(e.getSource()==exit)
		{
			System.exit(0);
		}
		else if(e.getSource()==copy)
		{
			tp.copy();
			paste.setEnabled(true);
		}
		else if(e.getSource()==cut)
		{
			tp.cut();
			paste.setEnabled(true);
		}
		else if(e.getSource()==paste)
		{
			tp.paste();
		}
		else if(e.getSource()==selectall)
		{
			tp.selectAll();
		}
		else if(e.getSource()==compile)
		{
			compileFile();
		}
		else if(e.getSource()==run1)
		{
			runFile();

		}
		else if(e.getSource()==compileAndRun)
		{
			String path =fc.getSelectedFile().getPath();
			String filename=fileedit.getParent();
			try {  
	            Process p = Runtime.getRuntime().exec("cmd.exe /C cd \""+filename+"\" /c java Demo");  
	            BufferedReader in = new BufferedReader(  
	                                new InputStreamReader(p.getInputStream()));  
	            String line = "";
	            String output="";
	            while ((line = in.readLine()) != null) {  
	                  output+=line;
	            }  
	            System.out.println(output);
	            JOptionPane.showMessageDialog(f, output);
	        } catch (IOException io) {  
	            io.printStackTrace();  
	        }  
		}
		else if(e.getSource()==cmdopen)
		{
			openCmd();
			JOptionPane.showMessageDialog(f, "Open CMd");

		}
		else if(e.getSource()==tf ||e.getSource()==cfont)
		{
			tp.setFont(new Font(cfont.getSelectedItem()+"",Font.BOLD,(int)Integer.parseInt((String) tf.getSelectedItem())));
		}
		else if(e.getSource()==openReadMe)
		{
			f.setTitle("Editor");
			String text="For Problem Regarding Compiler\n try CMD an try to compile file as";
			text=text+"\n for C   :gcc filename.c -o filename.exe\nto compile and filename.exe to run";
			text=text+"\n for C++ :g++ filename.cpp -o filename.exe\nto compile and filename.exe to run";
			text=text+"\n for Java:javac filename.java\n\n and java filename <-to run\n file name should be same as Class naem";
			
			
			tp.setText("For Problem Regarding Compiler\n");
		}
	}
	public static void main(String[] args) {
		new Editor();

	}

}
