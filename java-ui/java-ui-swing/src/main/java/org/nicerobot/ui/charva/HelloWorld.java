package org.nicerobot.ui.charva;

import charva.awt.BorderLayout;
import charva.awt.Component;
import charva.awt.Container;
import charva.awt.Font;
import charva.awt.GridBagConstraints;
import charva.awt.GridBagLayout;
import charva.awt.event.ActionEvent;
import charva.awt.event.ActionListener;
import charva.awt.event.WindowAdapter;
import charva.awt.event.WindowEvent;
import charvax.swing.BoxLayout;
import charvax.swing.JButton;
import charvax.swing.JCheckBox;
import charvax.swing.JComboBox;
import charvax.swing.JDialog;
import charvax.swing.JFrame;
import charvax.swing.JLabel;
import charvax.swing.JList;
import charvax.swing.JMenu;
import charvax.swing.JMenuBar;
import charvax.swing.JMenuItem;
import charvax.swing.JPanel;
import charvax.swing.JRadioButton;
import charvax.swing.JTextArea;
import charvax.swing.JTextField;
import charvax.swing.border.TitledBorder;
import charvax.swing.event.ListSelectionEvent;
import charvax.swing.event.ListSelectionListener;

/**
 * This class tests the GridBagLayout
 */
class GridBagTest extends JDialog implements ActionListener, ListSelectionListener {
  private final JList _style;

  private final JCheckBox _bold;

  private final JCheckBox _italic;

  private final JTextField _size;

  private final JTextField _sample;

  /**
   * Constructor
   */
  public GridBagTest () {
    super();
    this.setTitle("Test GridBagLayout");

    final Container contentPane = this.getContentPane();

    final GridBagLayout gbl = new GridBagLayout();
    contentPane.setLayout(gbl);

    this._style =
        new JList(new String[] { "Serif", "SansSerif", "Monospaced", "Dialog", "DialogInput" });
    this._style.setSelectedIndex(0);

    this._bold = new JCheckBox("Bold");
    this._italic = new JCheckBox("Italic");
    final JLabel label = new JLabel("Size: ");
    this._size = new JTextField("10", 2);
    this._sample = new JTextField("", 20);

    final GridBagConstraints gbc = new GridBagConstraints();
    // The following line is for Swing only!
    // gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 0;
    gbc.weighty = 100;
    this.add(this._style, gbc, 0, 0, 1, 3);
    gbc.weightx = 100;
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.WEST;
    this.add(this._bold, gbc, 1, 0, 2, 1);
    this.add(this._italic, gbc, 1, 1, 2, 1);
    this.add(label, gbc, 1, 2, 1, 1);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    this.add(this._size, gbc, 2, 2, 1, 1);
    gbc.anchor = GridBagConstraints.SOUTH;
    gbc.weighty = 0;
    this.add(this._sample, gbc, 0, 3, 4, 1);
    this._sample.setText("The quick brown fox");

    final JButton okbutton = new JButton("OK");
    okbutton.addActionListener(this);
    this.add(okbutton, gbc, 2, 4, 1, 1);

    this._bold.addActionListener(this);
    this._italic.addActionListener(this);
    this._style.addListSelectionListener(this);
    this._size.addActionListener(this);

    this.pack();
  }

  public void actionPerformed (final ActionEvent e) {
    if (e.getActionCommand().equals("OK")) {
      this.setVisible(false);
    } else {
      this.updateFont();
    }
  }

  private void add (final Component c_, final GridBagConstraints gbc_, final int x_, final int y_,
      final int w_, final int h_) {
    gbc_.gridx = x_;
    gbc_.gridy = y_;
    gbc_.gridwidth = w_;
    gbc_.gridheight = h_;
    this.getContentPane().add(c_, gbc_);
  }

  private void updateFont () {
    final Font font =
        new Font((String) this._style.getSelectedValue(), (this._bold.isSelected() ? Font.BOLD : 0)
            + (this._italic.isSelected() ? Font.ITALIC : 0), Integer.parseInt(this._size.getText()));
    this._sample.setFont(font);
    this.repaint();
  }

  public void valueChanged (final ListSelectionEvent evt) {
    this.updateFont();
  }
}

public class HelloWorld extends JFrame implements ActionListener {
  public static void main (final String[] args) {
    final HelloWorld demo = new HelloWorld();
    demo.show();
  }

  private final JTextField _textfield;

  private final JTextArea _textarea;

  private final JComboBox _combobox;

  private final JRadioButton _radiobutton;

  private final JCheckBox _checkbox;

  /**
   * Constructor
   */
  public HelloWorld () {
    super("Charva Example");

    // Use an anonymous inner class
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing (final WindowEvent e) {
        HelloWorld.this.terminate();
      }
    });

    final JMenuBar menubar = new JMenuBar();

    final JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic('F');
    final JMenuItem exitItem = new JMenuItem("Exit", 'x');
    exitItem.addActionListener(this);
    fileMenu.add(exitItem);

    final JMenu layoutMenu = new JMenu("Layout");
    layoutMenu.setMnemonic('L');
    final JMenuItem flowLayoutItem = new JMenuItem("FlowLayout", 'F');
    layoutMenu.add(flowLayoutItem);

    final JMenuItem borderLayoutItem = new JMenuItem("BorderLayout", 'B');
    layoutMenu.add(borderLayoutItem);

    final JMenuItem boxLayoutItem = new JMenuItem("BoxLayout", 'x');
    layoutMenu.add(boxLayoutItem);

    final JMenuItem gridbagLayoutItem = new JMenuItem("GridBagLayout", 'G');
    gridbagLayoutItem.addActionListener(this);
    layoutMenu.add(gridbagLayoutItem);

    final JMenu dialogMenu = new JMenu("Dialog");
    dialogMenu.setMnemonic('D');

    final JMenuItem optionpaneItem = new JMenuItem("JOptionPane", 'O');
    dialogMenu.add(optionpaneItem);

    final JMenu selectMenu = new JMenu("Selection");
    selectMenu.setMnemonic('S');

    final JMenuItem listItem = new JMenuItem("JList", 'L');
    selectMenu.add(listItem);

    final JMenuItem radiobuttonItem = new JMenuItem("JRadioButton", 'R');
    selectMenu.add(radiobuttonItem);

    final JMenuItem checkboxItem = new JMenuItem("JCheckBox", 'C');
    selectMenu.add(checkboxItem);

    final JMenuItem filechooserItem = new JMenuItem("JFileChooser", 'F');
    selectMenu.add(filechooserItem);

    final JMenu textMenu = new JMenu("Text");
    textMenu.setMnemonic('T');

    final JMenuItem textfieldItem = new JMenuItem("JTextField", 'T');
    textMenu.add(textfieldItem);

    final JMenu miscMenu = new JMenu("Misc");
    miscMenu.setMnemonic('M');
    final JMenuItem hidetestItem = new JMenuItem("Hide/Show");
    hidetestItem.addActionListener(this);
    miscMenu.add(hidetestItem);

    menubar.add(fileMenu);
    menubar.add(layoutMenu);
    menubar.add(selectMenu);
    menubar.add(textMenu);
    menubar.add(miscMenu);

    this.setJMenuBar(menubar);

    final Container contentPane = this.getContentPane();

    /* Add a panel in the north
    */
    final JPanel northpan = new JPanel();
    northpan.setBorder(new TitledBorder("North Panel"));
    final JTextArea northtextarea = new JTextArea();
    northtextarea.setColumns(50);
    northtextarea.setRows(3);
    northtextarea.setEditable(false);
    northtextarea.setLineWrap(true);
    northtextarea.setWrapStyleWord(true);
    northtextarea.append("Use LEFT and RIGHT cursor keys to navigate in the menubar. ");
    northtextarea.append("Use BACKSPACE to cancel a menu popup. ");
    northtextarea.append("Use TAB and SHIFT-TAB to navigate between components. ");

    northpan.add(northtextarea);
    contentPane.add(northpan, BorderLayout.NORTH);

    /* Add a panel on the west
    */
    final JPanel westpan = new JPanel();
    westpan.setBorder(new TitledBorder("West Panel"));
    westpan.setLayout(new BoxLayout(westpan, BoxLayout.Y_AXIS));

    final JPanel westitem1 = new JPanel();
    westitem1.add(new JLabel("A JButton"));
    final JButton button = new JButton("Button");
    button.addActionListener(this);
    westitem1.add(button);
    westpan.add(westitem1);

    final JPanel westitem2 = new JPanel();
    westitem2.add(new JLabel("A JTextField"));
    this._textfield = new JTextField("this is some text");
    this._textfield.addActionListener(this);
    westitem2.add(this._textfield);
    westpan.add(westitem2);

    final JPanel westitem3 = new JPanel();
    westitem3.add(new JLabel("A JComboBox"));
    final String[] comboItems = { "Red", "Green", "Blue", "Mauve", "Pink" };
    this._combobox = new JComboBox(comboItems);
    this._combobox.addActionListener(this);
    westitem3.add(this._combobox);
    westpan.add(westitem3);

    final JPanel westitem4 = new JPanel();
    westitem4.add(new JLabel("A JRadioButton"));
    this._radiobutton = new JRadioButton("radio button");
    this._radiobutton.addActionListener(this);
    westitem4.add(this._radiobutton);
    westpan.add(westitem4);

    final JPanel westitem5 = new JPanel();
    westitem5.add(new JLabel("A JCheckBox"));
    this._checkbox = new JCheckBox("checkbox");
    this._checkbox.addActionListener(this);
    westitem5.add(this._checkbox);
    westpan.add(westitem5);

    contentPane.add(westpan, BorderLayout.WEST);

    /* Add a panel in the center
    */
    final JPanel centerpan = new JPanel();
    centerpan.setBorder(new TitledBorder("Center Panel"));
    this._textarea = new JTextArea();
    this._textarea.setColumns(30);
    this._textarea.setRows(10);
    this._textarea.append("This is an editable text area\n");
    centerpan.add(this._textarea);
    contentPane.add(centerpan);

    /* Add a panel for buttons in the south
    */
    final JPanel southpan = new JPanel();
    southpan.setBorder(new TitledBorder("South Panel"));
    final JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(this);
    southpan.add(exitButton);
    contentPane.add(southpan, BorderLayout.SOUTH);

    this.pack();
  }

  public void actionPerformed (final ActionEvent e) {
    final String action = e.getActionCommand();
    final Component source = (Component) e.getSource();
    if (source instanceof JMenuItem) {
      this.processMenuSelection(e);
    } else if (action.equals("Exit")) {
      this.terminate();
    } else if (action.equals("Button")) {
      this._textarea.append("Button was pressed\n");
    } else if (source == this._textfield) {
      this._textarea.append("JTextField contains \"" + this._textfield.getText() + "\"\n");
    } else if (source == this._combobox) {
      this._textarea.append(this._combobox.getSelectedItem() + " was selected in JComboBox\n");
    } else if (source == this._radiobutton) {
      this._textarea.append("JRadioButton is ");
      final String msg = this._radiobutton.isSelected() ? "selected\n" : "deselected\n";
      this._textarea.append(msg);
    } else if (source == this._checkbox) {
      this._textarea.append("JCheckBox is ");
      final String msg = this._checkbox.isSelected() ? "selected\n" : "deselected\n";
      this._textarea.append(msg);
    }
  }

  private void processMenuSelection (final ActionEvent e) {
    final String action = e.getActionCommand();
    if (action.equals("Exit")) {
      this.hide();
      this.terminate();
    } else if (action.equals("GridBagLayout")) {
      final GridBagTest dlg = new GridBagTest();
      dlg.show();
    } else if (action.equals("Hide/Show")) {
      final HideShowTest dlg = new HideShowTest();
      dlg.show();
    }
  }

  private void terminate () {
    System.exit(0);
  }
}

/**
 * This class tests the ability to hide and show components.
 */
class HideShowTest extends JDialog implements ActionListener {
  JButton _button;

  JTextField _text;

  /**
   * Constructor
   */
  HideShowTest () {
    super();
    this.setTitle("Test hide/show capability");

    final Container contentpane = this.getContentPane();

    final JPanel northpan = new JPanel();
    final JButton hideButton = new JButton("Hide");
    hideButton.addActionListener(this);
    northpan.add(hideButton);

    final JButton showButton = new JButton("Show");
    showButton.addActionListener(this);
    northpan.add(showButton);

    contentpane.add(northpan, BorderLayout.NORTH);

    final JPanel centerpan = new JPanel();
    this._text = new JTextField("Now you see the button");
    this._button = new JButton("A BUTTON");
    centerpan.add(this._text);
    centerpan.add(this._button);
    contentpane.add(centerpan, BorderLayout.CENTER);

    final JPanel southpan = new JPanel();
    final JButton okButton = new JButton("OK");
    okButton.addActionListener(this);
    southpan.add(okButton);
    contentpane.add(southpan, BorderLayout.SOUTH);

    this.pack();
  }

  public void actionPerformed (final ActionEvent e) {
    final String action = e.getActionCommand();
    if (action.equals("OK")) {
      this.hide();
    } else if (action.equals("Show")) {
      if (!this._button.isVisible()) {
        this._text.setText("Now you see the button");
        this._button.setVisible(true);
      }
    } else if (action.equals("Hide")) {
      if (this._button.isVisible()) {
        this._text.setText("Now you don't");
        this._button.setVisible(false);
      }
    }
  }
}
