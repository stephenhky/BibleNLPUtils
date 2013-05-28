package home.kwyho.bible.gui;
import home.kwyho.bible.data.AbstractBibleDAO;
import home.kwyho.bible.data.KJV.KJVBibleDAO;
import home.kwyho.bible.verseRef.VerseReference;
import home.kwyho.bible.verseRef.VerseReferenceParser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class SingleVerseGUI extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6918874876179880702L;
	private JTextField jRefStr;
	private JScrollPane jScrollPane1;
	private JTextArea jPassageArea;
	private JLabel jLabel1;
	private JButton jQuit;
	private JButton jShowVerse;
	
	private AbstractBibleDAO kjv;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SingleVerseGUI inst = new SingleVerseGUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public SingleVerseGUI() {
		super();
		initGUI();
		
		kjv = new KJVBibleDAO();
		try {
			kjv.loadSerializedBible();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jRefStr = new JTextField();
				getContentPane().add(jRefStr);
				jRefStr.setText("jn 3 16");
				jRefStr.setBounds(125, 12, 163, 22);
			}
			{
				jShowVerse = new JButton();
				getContentPane().add(jShowVerse);
				jShowVerse.setText("Show Verse");
				jShowVerse.setBounds(306, 12, 137, 21);
				jShowVerse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("jShowVerse.actionPerformed, event="+evt);
						VerseReference ref = VerseReferenceParser.parseSingleVerseReference(jRefStr.getText());
						jPassageArea.setText(ref.getText(kjv));
					}
				});
			}
			{
				jQuit = new JButton();
				getContentPane().add(jQuit);
				jQuit.setText("Quit");
				jQuit.setBounds(306, 38, 137, 22);
				jQuit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("jQuit.actionPerformed, event="+evt);
						dispose();
					}
				});
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Bible Verse Ref");
				jLabel1.setBounds(12, 15, 108, 15);
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1);
				jScrollPane1.setBounds(18, 78, 425, 123);
				{
					jPassageArea = new JTextArea();
					jScrollPane1.setViewportView(jPassageArea);
					jPassageArea.setText("jPasageArea");
					jPassageArea.setBounds(18, 78, 425, 123);
				}
			}
			pack();
			this.setSize(463, 252);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
