package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.LatexEditorController;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class LatexEditorView
{

	private JFrame frame;
	private JTextArea textArea;
	private JButton submitChangeButton, volatileButton, stableButton, rollbackToPreviousVersionButton;
	private JButton btnReportTemplate, btnBookTemplate, btnArticleTemplate, btnLetterTemplate,btnEmptyTemplate;
	private JButton btnLoadDocument, btnSaveDocument;
	private JButton enableButton,disableButton;
	private JButton btnAddChapter, btnAddSection, btnAddSubSection, btnAddSubSubSection, btnAddItemizeList, btnAddEnumerationList, btnAddATable, btnAddFigure;
	private JMenu menuFile, menuCreate, menuAddLatexCommand;
	private JMenuBar menuBar;
	private JPanel panel;
	private LatexEditorController theController = new LatexEditorController(this);
	private String latexCommand;
	private boolean letterFlag = false;
	private boolean articleFlag = false;
	private boolean enableFlag = false;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try 
				{
					LatexEditorView window = new LatexEditorView();
					window.frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public LatexEditorView() 
	{
		initialize();
	}


	private void initialize() 
	{
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1200,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
		
		//-------------------- ENABLE ----------------------
		//---------------------------------------------------
		
		enableButton  = new JButton("Enable Version Managament");
		enableButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				enableFlag = true;
				
				try
				{	
					volatileButton.setEnabled(true);
					stableButton.setEnabled(true);
					rollbackToPreviousVersionButton.setEnabled(true);
					theController.enact("Enable", "");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
		});
		
		
		//-------------------- DISABLE ----------------------
		//---------------------------------------------------
		
		disableButton = new JButton("Disable Version Management");
		disableButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				enableFlag = false;

				try
				{		
					volatileButton.setEnabled(false);
					stableButton.setEnabled(false);
					rollbackToPreviousVersionButton.setEnabled(false);
					theController.enact("Disable", "");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
		});
		
		//-------------------- VOLATILE----------------------
		//---------------------------------------------------
		
		volatileButton = new JButton("Volatile");
		volatileButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				try
				{		
					if(enableFlag == true) 
					{
						volatileButton.setEnabled(false);
						stableButton.setEnabled(true);
						theController.enact("ChangeVersionsStrategy", "");
						theController.enact("Enable", "");
						
					}else
					{
						JOptionPane.showMessageDialog(null, "You have to choose Enable Version Management to use a version management");					
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
		});
		
		//----------------- STABLE -------------------
		//--------------------------------------------
		
		stableButton = new JButton("Stable");
		stableButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{		
					if(enableFlag == true) 
					{	
							volatileButton.setEnabled(true);
							stableButton.setEnabled(false);
							theController.enact("ChangeVersionsStrategy", "");
							theController.enact("Stable", "");
						
					}else 
					{
						JOptionPane.showMessageDialog(null, "You have to choose Enable Version Management to use a version management");
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
		});
		
		
		
		//--------- ROLLBACK TO PREVIOUS VERSION----------------------
		//------------------------------------------------	
		
		rollbackToPreviousVersionButton = new JButton("Rollback To Previous Version");
		rollbackToPreviousVersionButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{		
					if(enableFlag == true) 
					{
						theController.enact("Rollback To Previous Version", "");
					}else 
					{
						JOptionPane.showMessageDialog(null, "You have to choose Enable Version Management to rollback to a previous version");
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		
		//-------------- EDIT CHANGES-------------------
		//----------------------------------------------
		
		submitChangeButton = new JButton("Submit Changes");
		submitChangeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				try
				{		
					theController.enact("Submit Changes", "");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
		});
		
		
		panel = new JPanel();
		
		textArea= new JTextArea(30,70);
		JScrollPane sp= new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(sp);
		

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(stableButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(volatileButton, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
					.addGap(4)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 846, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(disableButton, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addComponent(enableButton, GroupLayout.PREFERRED_SIZE, 155, Short.MAX_VALUE)
						.addComponent(rollbackToPreviousVersionButton, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addComponent(submitChangeButton, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
					.addGap(63))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(145)
									.addComponent(volatileButton)
									.addGap(26)
									.addComponent(stableButton))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(80)
									.addComponent(enableButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(disableButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(rollbackToPreviousVersionButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
							.addGap(39)
							.addComponent(submitChangeButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)))
		);
		frame.getContentPane().setLayout(groupLayout);
		
		
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		menuFile = new JMenu("File");
		menuBar.add(menuFile);

		menuCreate = new JMenu("Create");
		menuFile.add(menuCreate);
		
		
		//-------------CREATE COMMAND------------------------
		//----------------------------------------------------
		btnReportTemplate = new JButton("Report Template");
		btnReportTemplate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				try
				{
					articleFlag = false;
					letterFlag = false;
					theController.enact("Report", "");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
		});
		menuCreate.add(btnReportTemplate);
		
		
		
		btnBookTemplate = new JButton("Book Template");
		btnBookTemplate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				try
				{	
					articleFlag = false;
					letterFlag = false;
					theController.enact("Book", "");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		menuCreate.add(btnBookTemplate);
		
		
		
		btnArticleTemplate = new JButton("Article Template");
		btnArticleTemplate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				try
				{		
					articleFlag = true;
					letterFlag = false;
					theController.enact("Article", "");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
		});
		menuCreate.add(btnArticleTemplate);
		
		
		
		btnLetterTemplate = new JButton("Letter Template");
		btnLetterTemplate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{		
					articleFlag = false;
					letterFlag = true;
					theController.enact("Letter", "");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		menuCreate.add(btnLetterTemplate);
		
		btnEmptyTemplate = new JButton("Empty Template");
		btnEmptyTemplate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{		
					articleFlag = false;
					letterFlag = false;
					theController.enact("Empty", "");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		menuCreate.add(btnEmptyTemplate);
		
		
		
		//-------- LOAD DOCUMENT------------------
		//----------------------------------------
		
		btnLoadDocument = new JButton("Load Document");
		btnLoadDocument.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{		
					theController.enact("Load Document", "");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		menuFile.add(btnLoadDocument);
		
		
		
		
		//-------- SAVE DOCUMENT------------------
		//----------------------------------------
		btnSaveDocument = new JButton("Save Document");
		btnSaveDocument.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{		
					theController.enact("Save Document", "");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		menuFile.add(btnSaveDocument);
		

		//------------ ADD LATEX COMMAND ----------------------
		//-----------------------------------------------------
		
		menuAddLatexCommand = new JMenu("Add Latex Command");
		menuBar.add(menuAddLatexCommand);
		
		btnAddChapter = new JButton("Add Chapter");
		btnAddChapter.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{		
							if (letterFlag == false && articleFlag == false)
							{
								latexCommand = "\\chapter{...}"; 
								theController.enact("Add Chapter", latexCommand);
							}
							else
							{
								if (letterFlag == true)
								{
									JOptionPane.showMessageDialog(null, "You are using a Letter template.You can't use this Latexcommand.");
								}
								else
								{
									JOptionPane.showMessageDialog(null, "You are using a Article template.You can't use this Latexcommand.");
								}
								
							}
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
					}
				});
		menuAddLatexCommand.add(btnAddChapter);
		
		
		
		btnAddSection = new JButton("Add Section");
		btnAddSection.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{		
					if (letterFlag == false)
					{
						latexCommand = "\\section{}";
						theController.enact("Add Section", latexCommand);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You are using a Letter template.You can't use this Latexcommand.");
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		menuAddLatexCommand.add(btnAddSection);
		
		
		
		btnAddSubSection = new JButton("Add SubSection");
		btnAddSubSection.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{		
					if(letterFlag == false)
					{
						latexCommand = "\\subsection{}"; 
						theController.enact("Add SubSection", latexCommand);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You are using a Letter template.You can't use this Latexcommand.");
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		menuAddLatexCommand.add(btnAddSubSection);
		
		
		
		btnAddSubSubSection = new JButton("Add SubSubSection");
		btnAddSubSubSection.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{		
					if (letterFlag == false)
					{
						latexCommand = "\\subsubsection{}"; 
						theController.enact("Add SubSubSection", latexCommand);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You are using a Letter template.You can't use this Latexcommand.");
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		menuAddLatexCommand.add(btnAddSubSubSection);
		
		
		
		btnAddItemizeList= new JButton("Add Itemize List");
		btnAddItemizeList.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{		
					if (letterFlag == false)
					{
						latexCommand = "\\begin{itemize}\r\n" + "\\item ...\r\n" + "\\item ...\r\n" + "\\end{itemize}";  
						theController.enact("Add Itemize List", latexCommand);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You are using a Letter template.You can't use this Latexcommand.");
					}

				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		menuAddLatexCommand.add(btnAddItemizeList);
		
		
		
		btnAddEnumerationList = new JButton("Add Enumeration List");
		btnAddEnumerationList.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{		
					if (letterFlag == false)
					{
						latexCommand = "\\begin{enumerate}\r\n" + "\\item ...\r\n" + "\\item ...\r\n" + "\\end{enumerate}";
						theController.enact("Add Enumeration List", latexCommand);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You are using a Letter template.You can't use this Latexcommand.");
					}

				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		menuAddLatexCommand.add(btnAddEnumerationList);
		
		
		
		btnAddATable = new JButton("Add Table");
		btnAddATable.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{		
					if (letterFlag == false)
					{
						latexCommand = "\\begin{table}\r\n" + "\\caption{....}\\label{...}\r\n" + "\\begin{tabular}{|c|c|c|}\r\n" + "\\hline\r\n" + "... &...&...\\\\\r\n" + "... &...&...\\\\\r\n" + "... &...&...\\\\\r\n" + "\\hline\r\n" + "\\end{tabular}\r\n" + "\\end{table}"; 
						theController.enact("Add Enumeration List", latexCommand);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You are using a Letter template.You can't use this Latexcommand.");
					}

				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		menuAddLatexCommand.add(btnAddATable);
		
		
		
		btnAddFigure = new JButton("Add Figure");
		btnAddFigure.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{		
					if (letterFlag == false)
					{
						latexCommand = "\\begin{figure}\r\n" + "\\includegraphics[width=...,height=...]{...}\r\n" + "\\caption{....}\\label{...}\r\n" + "\\end{figure}"; 
						theController.enact("Add Enumeration List", latexCommand);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You are using a Letter template.You can't use this Latexcommand.");
					}

				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		menuAddLatexCommand.add(btnAddFigure);
	}
	
	
	
	private static void addPopup(Component component, final JPopupMenu popup) 
	{
		component.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) 
			{
				if (e.isPopupTrigger()) 
				{
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) 
			{
				if (e.isPopupTrigger()) 
				{
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) 
			{
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	

	
	public void setTextArea(String aString)
	{
		textArea.setText(aString);
	}
	
	
	
	public JTextArea getTextArea()
	{
		return textArea;
	}
}
