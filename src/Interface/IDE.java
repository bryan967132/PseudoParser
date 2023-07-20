package Interface;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java_cup.runtime.Symbol;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import Controller.Controller;
import Painter.WordPainter;
import Templates.Button;
import Templates.Colors;
import Templates.Icons;
import Templates.Label;
import Templates.RadioButton;
public class IDE extends JPanel implements ActionListener,KeyListener,MouseWheelListener,MouseListener,MouseMotionListener  {
    Controller controller;
    Button analyzeInput,uploadOuts,saveOLC;
    public JComboBox<String> regexCB;
    public double zoomFactor = 1.05; // factor de zoom
    EditorArea editorArea;
    public Icon icono;
    public ImageIcon image;
    int indexFilePJ = -1;
    int posCaret,posXImg,posYImg,posXLabImg,posYLabImg;
    JLabel cursorPosition;
    public JLabel img;
    JPanel editorAreaContent;
    JPanel editorAreaContentFalse;
    // public JPanel graphics;
    JPanel projects;
    JScrollPane consoleScroll, outScroll;
    public JTextPane console, outConsole;
    public RadioButton treesR,nextsR,transitionsR,afdsR,afndsR;
    String input;
    Symbol sym;
    Tag tag;
    ToolBar toolbar;
    Window w;
    WordPainter painter;
    public IDE(Window w) {
        this.w = w;
        this.controller = w.controller;
        init();
        initComponents();
        defineComponents();
        addComponents();
        addToolBar();
        cursorPosition();
        lookPJFiles();
        initManagerGraphs();
        hideManagerGraphs();
        copyright();
    }
    void initComponents() {
        projects = new JPanel();
        editorAreaContent = new JPanel();
        cursorPosition = new JLabel();
        console = new JTextPane();
        outConsole = new JTextPane();
        // graphics = new JPanel();
        analyzeInput = new Button();
        uploadOuts = new Button();
        saveOLC = new Button();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {}
        treesR = new RadioButton();
        nextsR = new RadioButton();
        transitionsR = new RadioButton();
        afdsR = new RadioButton();
        afndsR = new RadioButton();
        regexCB = new JComboBox<>();
    }
    void defineComponents() {
        //projects
        projects.setBackground(Colors.MEDIUMVSCODE2);
        projects.setBounds(40,105,160,585);
        projects.setLayout(null);
        //editorArea
        editorAreaContent.setLayout(new BorderLayout());
        editorAreaContent.setBorder(BorderFactory.createLineBorder(Colors.DARKVSCODE,8));
        editorArea = new EditorArea();
        editorArea.editor.addKeyListener(this);
        editorAreaContent.add(editorArea,BorderLayout.WEST);
        editorAreaContent.add(editorArea.scroll,BorderLayout.CENTER);
        editorAreaContent.setBounds(220,105,550,425);
        editorArea.scroll.addKeyListener(this);
        //cursorPosition
        cursorPosition.setText("1 : 1");
        cursorPosition.setForeground(Colors.WHITE);
        cursorPosition.setBounds(220,535,550,10);
        cursorPosition.setHorizontalAlignment(JLabel.RIGHT);
        cursorPosition.setVerticalAlignment(JLabel.CENTER);
        //console
        console.setEditable(false);
        console.setForeground(Colors.WHITE);
        console.setBackground(Colors.DARKVSCODE);
        console.setFont(new java.awt.Font("Consolas", 0, 11));
        console.setBounds(0,0,1120,140);;
        console.setText("PseudoP:\n->");
        consoleScroll = new JScrollPane(console);
        consoleScroll.setBorder(BorderFactory.createLineBorder(Colors.DARKVSCODE,8));
        consoleScroll.setBounds(220,550,1120,140);
        //graphics
        // graphics.setBackground(Colors.DARKVSCODE);
        // graphics.setBounds(790,105,550,425);
        // graphics.setBorder(BorderFactory.createEmptyBorder());
        // graphics.setLayout(null);
        //out
        outConsole.setEditable(false);
        outConsole.setForeground(Colors.WHITE);
        outConsole.setBackground(Colors.DARKVSCODE);
        outConsole.setFont(new java.awt.Font("Consolas", 0, 11));
        outConsole.setBounds(0,0,550,425);;
        outConsole.setText("PseudoP:\n->");
        outScroll = new JScrollPane(outConsole);
        outScroll.setBorder(BorderFactory.createLineBorder(Colors.DARKVSCODE,8));
        outScroll.setBounds(790,105,550,425);
        //analyzeInput
        analyzeInput.locationSize(440,56,30,30);
        analyzeInput.Icon(Icons.PLAY);
        analyzeInput.setDesign(Colors.GREEN2);
        analyzeInput.setHoverColor(Colors.GREEN3);
        analyzeInput.addMouseListener(this);
        //analyzeStrings
        uploadOuts.locationSize(475,56,30,30);
        uploadOuts.Icon(Icons.UPLOAD);
        uploadOuts.setDesign(Colors.GREEN2);
        uploadOuts.setHoverColor(Colors.GREEN3);
        uploadOuts.addMouseListener(this);
        //saveOLC
        saveOLC.locationSize(510,56,30,30);
        saveOLC.Icon(Icons.SAVE);
        saveOLC.setDesign(Colors.GREEN2);
        saveOLC.setHoverColor(Colors.GREEN3);
        saveOLC.addMouseListener(this);
    }
    public void initManagerGraphs() {
        ButtonGroup group = new ButtonGroup();

        treesR.setText("Árbol",Colors.WHITE,12);
        treesR.setBoundsR(790,50,53,15);
        treesR.setSelected(true);
        treesR.addActionListener(this);
        group.add(treesR);
        this.add(treesR);

        nextsR.setText("Siguientes",Colors.WHITE,12);
        nextsR.setBoundsR(790,65,84,15);
        nextsR.addActionListener(this);
        group.add(nextsR);
        this.add(nextsR);

        transitionsR.setText("Transiciones",Colors.WHITE,12);
        transitionsR.setBoundsR(790,80,94,15);
        transitionsR.addActionListener(this);
        group.add(transitionsR);
        this.add(transitionsR);

        afdsR.setText("AFD",Colors.WHITE,12);
        afdsR.setBoundsR(890,50,44,15);
        afdsR.addActionListener(this);
        group.add(afdsR);
        this.add(afdsR);

        afndsR.setText("AFND",Colors.WHITE,12);
        afndsR.setBoundsR(890,65,52,15);
        afndsR.addActionListener(this);
        group.add(afndsR);
        this.add(afndsR);

        regexCB.setBounds(1000,60,190,22);
        regexCB.addActionListener(this);
        this.add(regexCB);
    }
    public void hideManagerGraphs() {
        treesR.setVisible(false);
        nextsR.setVisible(false);
        transitionsR.setVisible(false);
        afdsR.setVisible(false);
        afndsR.setVisible(false);
        regexCB.setVisible(false);
    }
    public void showManagerGraphs() {
        treesR.setVisible(true);
        nextsR.setVisible(true);
        transitionsR.setVisible(true);
        afdsR.setVisible(true);
        afndsR.setVisible(true);
        regexCB.setVisible(true);
    }
    public void updateTag() {
        if(tag != null) {
            tag.removeAll();
			this.remove(tag);
		}
		tag = new Tag(indexFilePJ,this,controller);
        this.add(tag);
		this.repaint();
    }
    public void lookPJFiles() {
        projects.removeAll();
        projects.add(new Label(0,10,projects.getWidth(),25,"Proyectos",16));
        for(int i = 0; i < controller.countPJ(); i ++) {
            controller.pjs.get(i).locationSize(0,i * 25 + 40,this.projects.getWidth(),25);
            controller.pjs.get(i).setHoverColor(Colors.LIGHTVSCODE);
            projects.add(controller.pjs.get(i));
        }
        projects.repaint();
    }
    void addComponents() {
        this.add(projects);
        this.add(editorAreaContent);
        this.add(cursorPosition);
        this.add(consoleScroll);
        this.add(outScroll);
        // this.add(graphics);
        this.add(analyzeInput);
        this.add(uploadOuts);
        this.add(saveOLC);
    }
    void copyright() {
        this.add(new Label(0,695,1390,15,"PseudoP",11));
        this.add(new Label(0,710,1390,15,"© Danny Hugo Bryan Tejaxún Pichiyá",11));
    }
    void addToolBar() {
        toolbar = new ToolBar(controller,this,w);
        toolbar.setBounds(0,0,1390,40);
        this.add(toolbar);
    }
    void init() {
        this.setBackground(Colors.MEDIUMVSCODE1);
        this.setLayout(null);
    }
    void execute() {
        controller.setFormat(editorArea.editor);
        controller.analyze(this, indexFilePJ, editorArea.editor, console);
    }
    void setFormat() {
        controller.setFormat(editorArea.editor);
    }
    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        setFormat();
    }
    public void keyReleased(KeyEvent e) {
        setFormat();
    }
    void cursorPosition() {
        editorArea.editor.addCaretListener(
            new CaretListener() {
                public void caretUpdate(CaretEvent e) {
                    try {
                        posCaret = e.getDot();
                        int row = 1,col = 1;
                        int lastRow = -1;
                        String text = editorArea.editor.getText().replaceAll("\r","");
                        for(int i = 0; i < posCaret; i ++) {
                            if(text.charAt(i) == '\n') {
                                row ++;
                                lastRow = i;
                            }
                        }
                        col = posCaret - lastRow;
                        cursorPosition.setText(row + " : " + col);
                    }
                    catch(Exception e1) {}
                }
            }
        );
    }
    public void mouseWheelMoved(MouseWheelEvent e) {
        try {
            int notches = e.getWheelRotation();
            if (notches < 0) {
                // zoom in
                zoomFactor *= 1.05;
            } else {
                // zoom out
                zoomFactor /= 1.05;
            }
            int w = image.getIconWidth();
            int h = image.getIconHeight();
            img.removeAll();
            icono = new ImageIcon(image.getImage().getScaledInstance((int) (w * zoomFactor),(int) (h * zoomFactor), Image.SCALE_DEFAULT));
            img.setIcon(icono);
            img.setSize(icono.getIconWidth(),icono.getIconHeight());
            // graphics.revalidate();
            // graphics.repaint();
        }
        catch(Exception e1) {}
    }
    public void mouseDragged(MouseEvent e) {
        try {
            int dx = e.getX() - posXImg;
            int dy = e.getY() - posYImg;
            img.setLocation(posXLabImg + dx,posYLabImg + dy);
        }
        catch(Exception e1) {}
    }
    public void mouseMoved(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == analyzeInput) {
            if(indexFilePJ != -1) {
                execute();
            }
            else {
                console.setText("PseudoP:\n->");
                outConsole.setText("PseudoP:\n->");
            }
        }
        else if(e.getSource() == uploadOuts) {
            if(indexFilePJ != -1) {
                //controller.validateString(indexFilePJ,editorArea.editor,console);
            }
            else {
                console.setText("PseudoP:\n->");
                outConsole.setText("PseudoP:\n->");
            }
        }
        else if(e.getSource() == saveOLC) {
            if(indexFilePJ != -1) {
                controller.saveOLCPJ(indexFilePJ,editorArea.editor);
            }
        }
    }
    public void mousePressed(MouseEvent e) {
        try {
            posXImg = e.getX();
            posYImg = e.getY();
            posXLabImg = img.getX();
            posYLabImg = img.getY();
        }
        catch(Exception e1) {}
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == treesR || e.getSource() == nextsR || e.getSource() == transitionsR || e.getSource() == afdsR || e.getSource() == afndsR || e.getSource() == regexCB) {
            controller.lookGraphs(this,indexFilePJ);
        }
    }
}