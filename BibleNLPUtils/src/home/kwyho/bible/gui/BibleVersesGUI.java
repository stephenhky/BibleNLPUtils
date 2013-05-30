package home.kwyho.bible.gui;
import home.kwyho.bible.data.AbstractBibleDAO;
import home.kwyho.bible.data.KJV.KJVBibleDAO;
import home.kwyho.bible.verseRef.VerseReference;
import home.kwyho.bible.verseRef.VerseReferenceParser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
public class BibleVersesGUI extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4662858969560113597L;
	private JTextField jVersesRefField;
	private JComboBox jBibleDAO;
	private JScrollPane jScrollPane1;
	private JButton jQuitButton;
	private JButton jDisplayButton;
	private JTextArea jScriptureTextArea;
	private JCheckBox jDisplayRefCheckBox;
	private JLabel jLabel1;
	
	private List<AbstractBibleDAO> bibles;
	private HashMap<String, Integer> hashBkName;
	private List<String> biblesName;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BibleVersesGUI inst = new BibleVersesGUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public BibleVersesGUI() {
		super();
		
		bibles = new ArrayList<AbstractBibleDAO>();
		hashBkName = new HashMap<String, Integer>();
		bibles.add(new KJVBibleDAO());
		
		for (int idx=0; idx<bibles.size(); idx++) {
			AbstractBibleDAO bible = bibles.get(idx);
			try {
				bible.loadSerializedBible();
				hashBkName.put(bible.getTranslation(), idx);
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
		biblesName = new ArrayList<String>(hashBkName.keySet());
		
		initGUI();				
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Bible Verses");
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Verses");
				jLabel1.setBounds(17, 17, 68, 15);
			}
			{
				jVersesRefField = new JTextField();
				getContentPane().add(jVersesRefField);
				jVersesRefField.setText("jn 3:16-17");
				jVersesRefField.setBounds(92, 14, 296, 22);
			}
			{
				jDisplayRefCheckBox = new JCheckBox();
				getContentPane().add(jDisplayRefCheckBox);
				jDisplayRefCheckBox.setText("Display Reference");
				jDisplayRefCheckBox.setBounds(92, 53, 178, 19);
				jDisplayRefCheckBox.setSelected(false);
			}
			{
				ComboBoxModel jBibleDAOModel = 
						new DefaultComboBoxModel(biblesName.toArray());
				jBibleDAO = new JComboBox();
				getContentPane().add(jBibleDAO);
				jBibleDAO.setModel(jBibleDAOModel);
				jBibleDAO.setBounds(92, 86, 174, 22);
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1);
				jScrollPane1.setBounds(12, 131, 376, 135);
				{
					jScriptureTextArea = new JTextArea();
					jScrollPane1.setViewportView(jScriptureTextArea);
					jScriptureTextArea.setText("jTextArea1");
					jScriptureTextArea.setBounds(12, 131, 376, 135);
				}
			}
			{
				jDisplayButton = new JButton();
				getContentPane().add(jDisplayButton);
				jDisplayButton.setText("Display");
				jDisplayButton.setBounds(281, 52, 105, 21);
				jDisplayButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("jDisplayButton.actionPerformed, event="+evt);
						AbstractBibleDAO bibleDAO = bibles.get(hashBkName.get(biblesName.get(jBibleDAO.getSelectedIndex())));
						List<VerseReference> verses = VerseReferenceParser.parseVersesReference(jVersesRefField.getText(), bibleDAO);
						String displayText = "";
						for (VerseReference verse: verses) {
							if (jDisplayRefCheckBox.isSelected()) {
								displayText += verse.getTextWithRef(bibleDAO)+"\n";
							} else {
								displayText += verse.getText(bibleDAO)+"\n";
							}
						}
						jScriptureTextArea.setText(displayText);
					}
				});
			}
			{
				jQuitButton = new JButton();
				getContentPane().add(jQuitButton);
				jQuitButton.setText("Quit");
				jQuitButton.setBounds(281, 87, 105, 22);
				jQuitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("jQuitButton.actionPerformed, event="+evt);
						
						dispose();
					}
				});
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
