package Interface;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
public class IDE extends JPanel implements ActionListener, KeyListener, MouseWheelListener, MouseListener, MouseMotionListener  {
    Controller controller;
    Button analyzeInput, uploadOuts, saveOLC;
    EditorArea editorArea;
    public Icon icono;
    public ImageIcon image;
    int indexFilePJ = -1;
    int posCaret;
    JLabel cursorPosition;
    public JLabel img;
    JPanel editorAreaContent;
    JPanel editorAreaContentFalse;
    JPanel projects;
    JScrollPane consoleScroll;
    public JTextPane console;
    String input;
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
        copyright();
    }
    void initComponents() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {}
        projects = new JPanel();
        editorAreaContent = new JPanel();
        cursorPosition = new JLabel();
        console = new JTextPane();
        analyzeInput = new Button();
        uploadOuts = new Button();
        saveOLC = new Button();
    }
    void defineComponents() {
        //projects
        projects.setBackground(Colors.MEDIUMCOLOR2);
        projects.setBounds(40, 105, 160, 575);
        projects.setLayout(null);
        //editorArea
        editorAreaContent.setLayout(new BorderLayout());
        editorAreaContent.setBorder(BorderFactory.createLineBorder(Colors.DARKCOLOR, 8));
        editorArea = new EditorArea();
        editorArea.editor.addKeyListener(this);
        editorAreaContent.add(editorArea, BorderLayout.WEST);
        editorAreaContent.add(editorArea.scroll, BorderLayout.CENTER);
        editorAreaContent.setBounds(220, 105, 550, 575);
        editorArea.scroll.addKeyListener(this);
        //cursorPosition
        cursorPosition.setText("1 : 1");
        cursorPosition.setForeground(Colors.WHITE);
        cursorPosition.setBounds(220, 685, 550, 10);
        cursorPosition.setHorizontalAlignment(JLabel.RIGHT);
        cursorPosition.setVerticalAlignment(JLabel.CENTER);
        //console
        console.setEditable(false);
        console.setForeground(Colors.WHITE);
        console.setBackground(Colors.DARKCOLOR);
        console.setFont(new java.awt.Font("Consolas",  0,  11));
        console.setBounds(0, 0, 550, 575);;
        console.setText("MiniJ:");
        consoleScroll = new JScrollPane(console);
        consoleScroll.setBorder(BorderFactory.createLineBorder(Colors.DARKCOLOR, 8));
        consoleScroll.setBounds(790, 105, 550, 575);
        //analyzeInput
        analyzeInput.locationSize(440, 56, 30, 30);
        analyzeInput.Icon(Icons.PLAY);
        analyzeInput.setDesign(Colors.COLOR2);
        analyzeInput.setHoverColor(Colors.COLOR3);
        analyzeInput.addMouseListener(this);
        //analyzeStrings
        uploadOuts.locationSize(475, 56, 30, 30);
        uploadOuts.Icon(Icons.UPLOAD);
        uploadOuts.setDesign(Colors.COLOR2);
        uploadOuts.setHoverColor(Colors.COLOR3);
        uploadOuts.addMouseListener(this);
        //saveOLC
        saveOLC.locationSize(510, 56, 30, 30);
        saveOLC.Icon(Icons.SAVE);
        saveOLC.setDesign(Colors.COLOR2);
        saveOLC.setHoverColor(Colors.COLOR3);
        saveOLC.addMouseListener(this);
    }
    public void updateTag() {
        if(tag != null) {
            tag.removeAll();
			this.remove(tag);
		}
		tag = new Tag(indexFilePJ, this, controller);
        this.add(tag);
		this.repaint();
    }
    public void lookPJFiles() {
        projects.removeAll();
        projects.add(new Label(0, 10, projects.getWidth(), 25, "Proyectos", 16));
        for(int i = 0; i < controller.countPJ(); i ++) {
            controller.pjs.get(i).locationSize(0, i * 25 + 40, this.projects.getWidth(), 25);
            controller.pjs.get(i).setHoverColor(Colors.LIGHTCOLOR);
            projects.add(controller.pjs.get(i));
        }
        projects.repaint();
    }
    void addComponents() {
        this.add(projects);
        this.add(editorAreaContent);
        this.add(cursorPosition);
        this.add(consoleScroll);
        // this.add(graphics);
        this.add(analyzeInput);
        this.add(uploadOuts);
        this.add(saveOLC);
    }
    void copyright() {
        this.add(new Label(0, 695, 1390, 15, "MiniJ", 11));
        this.add(new Label(0, 710, 1390, 15, "© Danny Hugo Bryan Tejaxún Pichiyá", 11));
    }
    void addToolBar() {
        toolbar = new ToolBar(controller, this, w);
        toolbar.setBounds(0, 0, 1390, 40);
        this.add(toolbar);
    }
    void init() {
        this.setBackground(Colors.MEDIUMCOLOR1);
        this.setLayout(null);
    }
    void execute() {
        controller.setFormat(editorArea.editor);
        controller.analyze(this,  indexFilePJ,  editorArea.editor,  console);
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
                        int row = 1, col = 1;
                        int lastRow = -1;
                        String text = editorArea.editor.getText().replaceAll("\r", "");
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
    public void mouseWheelMoved(MouseWheelEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == analyzeInput) {
            if(indexFilePJ != -1) {
                execute();
            }
            else {
                console.setText("MiniJ:\n");
            }
        }
        else if(e.getSource() == uploadOuts) {
            if(indexFilePJ != -1) {
                //controller.validateString(indexFilePJ, editorArea.editor, console);
            }
            else {
                console.setText("MiniJ:");
            }
        }
        else if(e.getSource() == saveOLC) {
            if(indexFilePJ != -1) {
                controller.saveOLCPJ(indexFilePJ, editorArea.editor);
            }
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    @Override
    public void actionPerformed(ActionEvent e) {}
}