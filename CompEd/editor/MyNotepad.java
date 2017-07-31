	import java.io.*;
	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import java.util.regex.*;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	import javax.swing.event.*;
	import java.net.URI;
	import java.util.Date;
	
	class MyNotepad extends WindowAdapter implements ActionListener 
	{  int pos=0,currentPos=0;int flagn=0;
		Frame f,f1,f2,f3,df,fontt,about;
	    public static String REGEX,INPUT,REPLACE;
		Panel p1,p,p3,p4,mainPanel,fontPanel,north_panel,south_panel;
		Button b1,b2,b3,b4,b5,b6,k1,k2,k3,okbutton,btn_yes;
		
		JTextArea ta;
		JScrollPane scrollPane;
		Label l1,l2,l3,dl,fl1,lbl_msg,lbl_msg1,statusBar;
		TextField t1,t2,t3;
		JList style,font,size;
		Menu m1,m2,m3,m4,m5;
		MenuBar mb;
		MenuItem nw,sve,sves,ext,opn,fnd,fndr,stts,fnt,wrdwrp,hlp,abt;
		MenuItem undo,cut,copy,delete,time,selectAll,paste;
		FileDialog fd;
		int flags=0;   String flag="";
		String name="Untitled",path,fname,msg;
		int offset,pos1=0; int caret=0;
		int flagk=0;;int flagf=0,flago=0,flagw=0,sttsf=0;
		int linenum,column=1;
	    FontChooser fontDialog=null;
	
		public MyNotepad()
		{ 
			
			f = new Frame("Untitled-CompEd");
			f.setSize(1000,550);
			WindowCloser3 wc3=new WindowCloser3();
	   	/////// Main frame //////
	   		ta = new JTextArea();
	   	
		    JScrollPane scrollPane= new JScrollPane(ta);
		   
		    //f.add(scrollPane);
		    statusBar=new Label("||       Ln 1, Col 1  ",Label.RIGHT);
	        f.add(scrollPane,BorderLayout.CENTER);
	        f.add(statusBar,BorderLayout.SOUTH);
	        f.add(new JLabel("  "),BorderLayout.EAST);
	        f.add(new JLabel("  "),BorderLayout.WEST);
		  
	     
			mb = new MenuBar();
		  //  m1=new Menu("File",KeyEvent.VK_F,mb);
			m1 = new Menu("File");
		JMenuItem menuItem = new JMenuItem("A",KeyEvent.VK_T);
	       menuItem.setMnemonic(KeyEvent.VK_T);
	       menuItem.addActionListener(this);
			m2 = new Menu("Edit");
			m3 = new Menu("Format");
			m4 = new Menu("View");
			m5 = new Menu("Help");
			
			nw = new MenuItem("New");
			nw.addActionListener(this);
			opn = new MenuItem("Open");
			opn.addActionListener(this);
			ext = new MenuItem("Exit");
			ext.addActionListener(this);
			fnt = new MenuItem("Font...");
			fnt.addActionListener(this);
			hlp = new MenuItem("View Help");
			hlp.addActionListener(this);
			stts= new MenuItem("Status Bar");
			stts.addActionListener(this);
			wrdwrp = new MenuItem("Word Wrap");
			wrdwrp.addActionListener(this);
			abt = new MenuItem("About CompEd");
			abt.addActionListener(this);
			paste = new MenuItem("Paste");
			paste.addActionListener(this);
			undo= new MenuItem("Undo");
			undo.addActionListener(this);
			sve = new MenuItem("Save");
			sve.addActionListener(this);
			sves = new MenuItem("Save As");
			sves.addActionListener(this);
			fnd = new MenuItem("Find");
			fnd.addActionListener(this);
			fndr = new MenuItem("Find & Replace");
			fndr.addActionListener(this);
			
			
	       	selectAll = new MenuItem("SelectAll");
			selectAll.addActionListener(this);
	        cut=new MenuItem("Cut");
	        cut.addActionListener(this);
	        copy=new MenuItem("Copy");
	        copy.addActionListener(this);
	        
	        delete=new MenuItem("Delete");
	        delete.addActionListener(this);
	        time=new MenuItem("Time/Date");
	        time.addActionListener(this);
	        

			m1.add(nw); m1.add(opn);m1.addSeparator(); m1.add(sve);  m1.add(sves); m1.addSeparator(); m1.add(ext);m2.add(undo);m2.add(cut);m2.add(copy);m2.add(paste);m2.add(delete);
			m2.add(selectAll);m2.add(fnd); m2.add(fndr);m2.add(time);m2.add(time);m3.add(fnt);m4.add(stts);m5.add(hlp);m3.add(wrdwrp);m5.add(abt);
			mb.add(m1); mb.add(m2);mb.add(m3);mb.add(m4);mb.add(m5);
			
			
/////////////////////  Find box    ///////////////
		
		GridLayout gd=new GridLayout(3,0);
			f1=new Frame("Find");
			f1.setSize(400,200);
			f1.setLayout(gd);
			 l1=new Label("Find");
			 t1=new TextField(10);
			 p=new Panel(); 
			  b1=new Button("FindNext ");
			  b2=new Button("Close");
			  b1.addActionListener(this);
			  b2.addActionListener(this);
			 p.add(b1);
	
			 p.add(b2);
			 f1.add(l1);
			 f1.add(t1);
			 f1.add(p);
			 
			 f1.setResizable(false);
			 
///////////////////// Find and Replace Box /////////////////
			 
			 GridLayout gd2=new GridLayout(5,0);
			 f2=new Frame("Find & Replace");
			 f2.setSize(400,200);
			 f2.setLayout(gd2);
			 l2=new Label("Find");
			 t2=new TextField(10);
			 l3=new Label("Replace with");
			 t3=new TextField(10);
			 p1=new Panel();
			 p4=new Panel();
			  b3=new Button("Find Next");
			  b4=new Button("Replace");
			  b5=new Button("Replace All");
			  b6=new Button("Close");
			  b3.addActionListener(this);
			  b4.addActionListener(this);
			  b5.addActionListener(this);
			  b6.addActionListener(this);
			 p1.add(b3);
			 p1.add(b4);
			 p1.add(b5);
			 p1.add(b6);
			 f2.add(l2);
			 f2.add(t2);
			 f2.add(l3);
			 f2.add(t3);
			 f2.add(p1);
			 f2.setResizable(false);
			 
//////////// Dialog box for match not found ///////////
			 
			 df=new Frame("My Notepad");
			 dl=new Label("No match Found ");
			 df.setBackground(Color.red);
			 df.setSize(200,100);
			 df.add(dl);
			 int caret=ta.getCaretPosition();
			 df.setResizable(false);
			 

			 
///////////// Window listener ///////////////////	 
			 f1.addWindowListener(new WindowAdapter(){
	    		
	    			public void windowClosing(WindowEvent e)
	    			{
	    				t1.setText("");
	    				f1.setVisible(false);
	    			} 
	    		});
	    		df.addWindowListener(new WindowAdapter(){
	    		
	    			public void windowClosing(WindowEvent e)
	    			{
	    				df.setVisible(false);
	    			} 
	    		});
			 f2.addWindowListener(new WindowAdapter(){
	    		
	    			public void windowClosing(WindowEvent e)
	    			{
	    				t2.setText("");
	    				t3.setText("");
	    				f2.setVisible(false);
	    			} 
	    		});
	    		
/////////////// Close box dialogue box Warning /////////////		
		    
		     f3=new Frame("Message");
		     k1=new Button("Save");
		     k2=new Button("Don,tSave");
		     k3=new Button("Cancel");
		     k1.addActionListener(this);
		     k2.addActionListener(this);
		     k3.addActionListener(this);
		     p3=new Panel();
		     f3.setLayout(new BorderLayout());
		     p3.setBackground(Color.magenta);
		     p4.setBackground(Color.magenta);
		     String ms1="Do u want to save the changes in the File ?>";
		     Label lbl=new Label(ms1,Label.CENTER);
		     p3.add(k1);	 
		     p3.add(k2);
		     p3.add(k3);
		     p4.add(lbl);
		     f3.add(p4)	; 
		     f3.add(p3,"South");
		     f3.setSize(300,150);
		    // f3.getRootPane.setDefaultButton(k3);
		     f3.setResizable(false);
		     
/////////////// About the notepad dialoge box ////////////
	
	        about=new Frame("CompEd Information");
	        
	        north_panel=new Panel();
	        south_panel=new Panel();
	        about.setSize(400,200);
	        about.setLocation(50,80);
	        lbl_msg=new Label("This CompEd was made by Narendra Panwar.",Label.CENTER);
	        Font font =new Font("tahoma",Font.BOLD,16);
	        lbl_msg.setForeground(Color.BLUE);
	        lbl_msg.setFont(font);
	        lbl_msg1=new Label("    CompEd   ",Label.CENTER);
	        Font font1 =new Font("castellar",Font.BOLD,20);
	        lbl_msg1.setForeground(Color.MAGENTA);
	        lbl_msg1.setFont(font1);
	        
	        btn_yes=new Button("  OK  ");
	        
	        north_panel.add(lbl_msg);
	        south_panel.add(btn_yes);
	        about.add(lbl_msg1,"Center");
	        about.add(south_panel,"South");
	        about.add(north_panel,"North");
	        btn_yes.addActionListener(this);
	         about.addWindowListener(new WindowAdapter(){
	    		
	    			public void windowClosing(WindowEvent e)
	    			{
	    				about.setVisible(false);
	    				about.setLocation(500,100);
	    			} 
	    		});
	    		about.setResizable(false);
	    	     
		     
//////////// mouse and keyboard listener //////////////////	     
	
	    	ta.addCaretListener(new CaretListener(){
	            public void caretUpdate(CaretEvent e)
	             {
	               int lineNumber=0, column=0, pos=0;
	               try
	                 {
	                 pos=ta.getCaretPosition();
	                 lineNumber=ta.getLineOfOffset(pos);
	                 column=pos-ta.getLineStartOffset(lineNumber);
	                  }catch(Exception excp){}
	                if(ta.getText().length()==0){lineNumber=0; column=0;}
	                statusBar.setText("||       Ln "+(lineNumber+1)+", Col "+(column+1));
	                }
	            } );
	            
	    	ta.addKeyListener(new KeyAdapter(){
	    		public void keyPressed(KeyEvent e)
	    		{
	    			flagk = 1;
	    			flags=0;
	    		}
	    	});
	    	
		     undo.setEnabled(false);
		     f3.addWindowListener(wc3);
			 f.setMenuBar(mb);
			 f.addWindowListener(this );
				
			 f.setVisible(true);
			 f.setLocation(150,50);
		} 
///////////////  Action Performed  //////////////////
		
		
		public void actionPerformed(ActionEvent e)
		{  
			offset=0;int pos1=0;
			String str = e.getActionCommand();
	    	System.out.println("Action is "+str);
			try
			{
				if(e.getSource()==nw)
				  {   if(flags==1||ta.getText().equals(""))
				    {if(flagk==0)
				    {ta.setText("");
				    	f.setTitle("Untitled-CompEd");}
				    	else{	f3.setVisible(true);
					            flag="new"; }
				    	}
					else if(flags==0){
				    	f3.setVisible(true);
					 flag="new"; } 
					}
					
					
				else if(e.getSource()==k1)
				  {
					f3.setVisible(false);
					
					if(flag.equals("new"))
					{save();
					 if(flagn==1)
					 {ta.setText("");
				      f.setTitle("Untitled-CompEd");
				     flags=0; flagk=0;flagn=0;}
					}
					else if(flag.equals("open"))
					{ save();
					 if(flago==1){open(); flags=1;flagk=0;}
					}
					else if(flag.equals("close"))
					{save();
					 
					}
				  }
			
				else if(e.getSource()==opn)
				   {  
		        	if(flagk==0)
		        	{ 
		        		open(); }
				    else{
						f3.setVisible(true);
						flag="open"; 
					}
			    	}
			    	
				else if(e.getSource()==k2)
				    {  f3.setVisible(false);
				    	if(flag.equals("open"))
				      {  open();}
			    	else if(flag.equals("close"))
				     { System.exit(1);
				     }
			    	else if(flag.equals("new"))
				     {ta.setText("");
				      f.setTitle("Untitled-CompEd");
				     flags=0; flagk=0;
				      }
				     else if(flag.equals("close")||flag.equals("exit"))
				      {
				       System.exit(1);
				      }
				      }
				      
				else if(e.getSource()==k1)
				    {   f3.setVisible(false);
				      System.out.println(""+flag+"");
					save();
					}
				
				
				else if(e.getSource()==k3)
				    {f3.setVisible(false);}
				
			
				else if(e.getSource()==sve)
				   {
					save();
				  }
			    else if(e.getSource()==sves)
				    {       
						FileDialog fd = new FileDialog(f,"Save As",FileDialog.SAVE);
						fd.setVisible(true);
						String n = fd.getFile();
						String p = fd.getDirectory();
						fname = p+n;
						if(!(n.equals("")))
						{
							name = n;
							path = p;
						FileOutputStream fos = new FileOutputStream(fname);
						String data = ta.getText();
						int len = data.length();
						for(int i=0; i<len; i++)
						fos.write(data.charAt(i));
						fos.close();
						
					if(name==""){ f.setTitle("Untitled-CompEd");}
						else	f.setTitle(name+" - "+"CompEd");	
							System.out.println();
							flags=1; flagk=0;
						if(flag.equals("exit")||flag.equals("close"))
					      {
					       System.exit(1);
					        }	
				        }
				     }
				else if(e.getSource()==ext)
				   {
					if(ta.getText().equals("") || flags == 1)
	    			System.exit(1);  
	    			else
	    		   {   flag="exit";
	    			f3.setVisible(true); }
				    }
				else if(e.getSource()==btn_yes)
				{about.setVisible(false);
				}    
				
				else if(e.getSource()==cut)
	               {ta.cut();
	                 }
	            else if(e.getSource()==copy)
	               {ta.copy();
	                 }
	                 else if(e.getSource()==paste)
	               {ta.paste();
	                 }
	            else if(e.getSource()==time)
	               {
	               	ta.insert(new Date().toString(),ta.getSelectionStart());
	                
	                 }
	              else if(e.getSource()==selectAll)
	                 { ta.selectAll();
		               }
		                
	           else if(e.getSource()==delete)
	               {
	                 ta.replaceSelection("");
	                 }
				
				else if(e.getSource()==fnd)
				   {	
					f1.setVisible(true);
					}
				else if(e.getSource()==b3)
				   {
				    find(t2.getText());
		            }
				else if(e.getActionCommand()=="Replace")
				   { 
					ta.requestFocus();
					
					String txt=ta.getSelectedText();
					if(!txt.equals(""))
					{ta.replaceRange(t3.getText(),ta.getSelectionStart(),ta.getSelectionEnd());
					}
					find1(t2.getText());
				    }
				else if(e.getActionCommand()=="Replace All")
				   { 
					REGEX = t2.getText();
					INPUT  =ta.getText();
					REPLACE = t3.getText();
					Pattern kp=Pattern.compile(REGEX);
					Matcher m=kp.matcher(INPUT);
					INPUT=m.replaceAll(REPLACE);
					ta.setText(INPUT);
				    }
				else if(e.getSource()==b1)
				    {
				  find(t1.getText());
				  	}
				
				else if(e.getSource()==fndr)
				    {
					f2.setVisible(true);
				     }
				
				else if(e.getSource()==b2)
		        	{   
				    f1.setVisible(false);
				    t1.setText("");
				    f1.dispose();
	
			         }
			   else if(e.getSource()==b6)
			     {   
				  f2.setVisible(false);
				  df.setVisible(false);   
				  t2.setText("");
				  t3.setText("");
				  pos=0;
				  f2.dispose();
	
			      }
			else if(e.getSource()==wrdwrp)
				{	
					if(flagw==0)
					{ta.setLineWrap(true);
					flagw=1;}
					else {
						ta.setLineWrap(false);
						scrollPane.validate();
						flagw=0;
					}
					
						
				}
			else if(e.getSource()==fnt)
				{if(fontDialog==null)
	    	        fontDialog=new FontChooser(ta.getFont());
	
	                if(fontDialog.showDialog(f,"Choose a font"))
		            ta.setFont(fontDialog.createFont());
	               }
						
				
			else if(e.getSource()==hlp)
				{	
					Desktop d=Desktop.getDesktop();
					d.browse(new URI("http://www.digitalcitizen.life/beginners-guide-notepad"));
						
				}
			else if(e.getSource()==abt)
				{	
					about.setVisible(true);
						
				}		
			else if(e.getSource()==stts)
				{	
					if(sttsf==1)
					{statusBar.setVisible(true); sttsf=0;}
					else if(sttsf==0)
					{	statusBar.setVisible(false); sttsf=1;}	
						
				}
			
			}
			catch(Exception e1)
			{
				System.out.print(e1.getMessage());
			}
		}
	
	
		 public void find(String s2)
	      {  
	    	try{
	    	 if(flagf==0)
	    	   {   int caret=ta.getCaretPosition();
	    	       offset=(ta.getText()).indexOf(s2,caret);
	    		  if(offset > -1)
	    		  { String s1 = ta.getText();
	    		    s1 = s1.replaceAll("\r", "");
	    		    ta.setText(s1);
	    	        Pattern p = Pattern.compile(s2);
	    	        Matcher	m = p.matcher(s1);
	     	
	    		   if(m.find(caret))
	    		   {
	    			ta.requestFocus();
	    			ta.select(m.start(),m.end());
	    			caret= m.end()+1;
	    		    }
	    		  }
	    		   else
	    		   {  flagf=1;
	    			  df.setVisible(true); pos=0;
	    			  offset=0;
	    		     }
	    	  }
	    	   else{ caret=0;
	    			offset=(ta.getText()).indexOf(s2,pos);
	    		   if(offset > -1)
	    		   { String s1 = ta.getText();
	    		     s1 = s1.replaceAll("\r", "");
	    		     ta.setText(s1);
	    	         Pattern p = Pattern.compile(s2);
	    	         Matcher	m = p.matcher(s1);
	     	
	    		    if(m.find(pos))
	    		     {
	    			 ta.requestFocus();
	    			 ta.select(m.start(),m.end());
	    			 pos= m.end()+1;
	    		     }
	    		  }
	    		else
	    		   {
	    			 df.setVisible(true); pos=0;
	    			 offset=0;
	    		   }
	    			
	    	    }
	    	}
	    	catch(Exception e)
	    		{
	    			e.printStackTrace();
	        		df.setVisible(true);
	    		}
	    		
	   }
	    
 /////////////  Find function  //////////////////  
	    
	     public void find1(String s2)
	       {  
	    	try{
	    		if(flagf==0)
	    		{ 
	    			int caret=ta.getCaretPosition();
	    		   offset=(ta.getText()).indexOf(s2,caret);
	    		      if(offset > -1)
	    		         {String s1 = ta.getText();
	    		           s1 = s1.replaceAll("\r", "");
	    	               ta.setText(s1);
	    		           Pattern p = Pattern.compile(s2);
	    		           Matcher m = p.matcher(s1); 
	    		           if(m.find(caret))
	    		             {
	    		           	  ta.requestFocus();
	    			          ta.select(m.start(),m.end());
	    			          caret= m.end()+1;
	    		             }
	    		 }
	    		else
	    		{
	    			df.setVisible(true); pos1=0;offset=0;
	    	      flagf=1;
	    		}
	    		}
	    		else
	    		{   caret=1;
	    			offset=(ta.getText()).indexOf(s2,pos1);
	    		    if(offset > -1)
	    		    { String s1 = ta.getText();
	    		      s1 = s1.replaceAll("\r", "");
	    		      ta.setText(s1);
	    	          Pattern p = Pattern.compile(s2);
	    	          Matcher	m = p.matcher(s1);
	     	
	    		      if(m.find(pos1))
	    		        {
	    			     ta.requestFocus();
	    			     ta.select(m.start(),m.end());
	    			     caret= m.end()+1;
	    		        }
	    		    }
	    		 else
	    		  {
	    			 df.setVisible(true); caret=0;
	    			 offset=0;
	    		   }
	    	     }
	    	  }
	    	catch(Exception e)
	    		{
	    			e.printStackTrace();
	      			df.setVisible(true);
	    		}
	    }
				
	
		public void windowClosing(WindowEvent w1)
	    {    flag="close";
	    		
	    		if(ta.getText().equals("")||flags==1)
	    		{
	    		if(flagk==0){flag="";	System.exit(1);}
	    		else{flag="close";
	    			f3.setVisible(true);
	    		}}
	    		else
	    			f3.setVisible(true);
	    		
	     }
	     
	     
////////////// Open function //////////////////   		
	
		public void open()
		  {	 try
		  	   {
		
					FileDialog fd = new FileDialog(f,"Open",FileDialog.LOAD);
					fd.setVisible(true);
					String n = fd.getFile();
					String p = fd.getDirectory();
					fname = p+n;
					if(!(n.equals("")))
					{
						name = n;
						path = p;
					f.setTitle(name+" - "+"CompEd");
					FileInputStream fis = new FileInputStream(fname);
					BufferedReader din=new BufferedReader(new InputStreamReader(fis));
					int ch;  
						String str="";
						while(str!=null)
	                    {
	                    str=din.readLine();
	                     if(str==null)
	                      break;
	                    ta.append(str+"\n");
	                        }
	
				
					statusBar.setText("File : "+n+" opened successfully.");
					flags=1; flagk=0;
					fis.close();
					}
			  }
			catch(Exception e1)
			{
				System.out.print(e1.getMessage());
			}
					
		}
		
//////////////  Save function ///////////////	
		public void save()
		{        try{ flags=1;
			        if((f.getTitle()).equals("Untitled-CompEd"))
					{
						FileDialog fd = new FileDialog(f,"Save As",FileDialog.SAVE);
						fd.setVisible(true);
						String n = fd.getFile();
						String p = fd.getDirectory();
						fname = p+n;
						if(!(n.equals("")))
						{
							name = n;
							path = p;
						FileOutputStream fos = new FileOutputStream(fname);
						String data = ta.getText();
						int len = data.length();
						for(int i=0; i<len; i++)
						fos.write(data.charAt(i));
						fos.close();
						flagn=1;flago=1;
						f.setTitle(name+" - "+"CompEd");
						statusBar.setText("File : "+n+" saved successfully.");
						flags=1; flagk=0;	
						}
					}
					else
					{
						FileOutputStream fos = new FileOutputStream(fname);
						String data = ta.getText();
						int len = data.length();
						for(int i=0; i<len; i++)
						fos.write(data.charAt(i));
						fos.close();
						flags=1; flagk=0;flagn=0;flago=1;
						f.setTitle(name+" - "+"CompEd");
						statusBar.setText("File : "+name+" saved successfully.");
						
						
					}if(flag.equals("close")||flag.equals("exit"))
							System.exit(1);
		}catch(Exception e1)
			{
				System.out.print(e1.getMessage());
			}		
		}
	
		void updateStatus(int linenumber, int columnnumber) {
	       // statusbar.setText("Line: " + linenumber + " Column: " + columnnumber);
	    }
		
	/////   MAIN FUNCTION ////////////	
		
		public static void main(String args[])
		{
			MyNotepad mn = new MyNotepad();
		}
	
	}
		
	
	
