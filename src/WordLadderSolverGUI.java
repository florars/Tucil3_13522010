import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class WordLadderSolverGUI implements ActionListener{
    private JFrame window;
    private JPanel panel;
    private JPanel panel2;
    private JPanel panel3;
    private JButton UCS;
    private JButton GBFS;
    private JButton Astar;
    private JTextField startWord;
    private JTextField endWord;
    private ImageIcon icon;
    private ImageIcon success;
    private ImageIcon fail;
    private DictReader dictionary;
    private UCS ucs;
    private GBFS gbfs;
    private Astar astar;


    public WordLadderSolverGUI() {
        this.window = new JFrame();
        window.setTitle("Word Ladder Solver");
        window.setSize(1200, 800);
        icon = new ImageIcon("elements/icon.png");
        window.setIconImage(this.icon.getImage());
        success = new ImageIcon("elements/search_success.png");
        fail = new ImageIcon("elements/search_fail.png");

        this.dictionary = new DictReader("Dictionary.txt");
        if (!this.dictionary.dictValid()) {
            JPanel failPanel = new JPanel();
            failPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
            failPanel.setLayout(new GridLayout(0, 1));
            JLabel failLabel = new JLabel("File dictionary src/Dictionary.txt tidak ditemukan!\n Pastikan file tersebut berada di tempat yang benar.");
            failLabel.setFont(new Font("Dialog", Font.BOLD, 24));
            failPanel.add(new Box(0));
            failPanel.add(new JLabel(fail,0));
            failPanel.add(failLabel);
            window.add(failPanel, BorderLayout.CENTER);
        }
        else {
            this.dictionary.makeList();
    
            startWord = new JTextField();
            JLabel startLabel = new JLabel("Kata awal");
            startLabel.setLabelFor(startWord);
            endWord = new JTextField();
            JLabel endLabel = new JLabel("Kata akhir");
            endLabel.setLabelFor(endWord);
            UCS = new JButton("UCS");
            UCS.addActionListener(this);
            UCS.setSize(40, 20);
            GBFS = new JButton("Greedy BFS");
            GBFS.addActionListener(this);
            Astar = new JButton("A*");
            Astar.addActionListener(this);

            ucs = new UCS();
            gbfs = new GBFS();
            astar = new Astar();
    
            panel = new JPanel();
            panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            panel.setLayout(new GridLayout(0, 2));
            panel.add(startLabel);
            panel.add(endLabel);
            panel.add(startWord);
            panel.add(endWord);
    
            panel2 = new JPanel();
            panel2.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            GridBagLayout grid = new GridBagLayout();
            GridBagConstraints gbc = new GridBagConstraints();
            //gbc.gridwidth = 3;
            gbc.ipadx = 50;
            gbc.insets = new Insets(0, 30, 0, 30);
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel2.setLayout(grid);
            panel2.add(UCS, gbc);
            gbc.gridx = 1;
            panel2.add(GBFS, gbc);
            gbc.gridx = 2;
            panel2.add(Astar, gbc);
    
            panel3 = new JPanel();
            panel3.add(new Box(0));

            window.add(panel,BorderLayout.NORTH);
            window.add(panel2, BorderLayout.CENTER);
            window.add(panel3, BorderLayout.SOUTH);
        }
        window.pack();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        window.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        TreeNode startNode = new TreeNode(startWord.getText());
        String end = endWord.getText();
        
        StringBuilder forLabel = new StringBuilder();
        JTextArea ta = new JTextArea();
        ta.setFont(new Font("Dialog", Font.BOLD, 18));
        ta.setEditable(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setPreferredSize(new Dimension(900,400));

        panel3.removeAll();
        panel3.revalidate();
        panel3.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagLayout gh = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel3.setLayout(gh);
        gbc.ipady = 20;
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 15, 15, 15);

        if (startNode.getRoot().length() != end.length()) {
            ta.setText("The length of starting word and goal word are different!\nCan not compute ladder for these words");
            panel3.add(ta, gbc);
            gbc.gridwidth = 1;
            gbc.gridx = 3;
            panel3.add(new JLabel(fail)); 
        }

        else {
            HashMap<Boolean, String> res = new HashMap<Boolean,String>();
            if (e.getSource() == UCS) {
                res = ucs.run(startNode, end, this.dictionary);
            }
            if (e.getSource() == GBFS) {
                res = gbfs.run(startNode, end, this.dictionary);
            }
            if (e.getSource() == Astar) {
                res = astar.run(startNode, end, this.dictionary);
            }

            if (res.containsKey(false)) {
                forLabel.append(res.get(false));

                ta.setText(forLabel.toString());
                panel3.add(ta, gbc);
                gbc.gridwidth = 1;
                gbc.gridy = 3;
                panel3.add(new JLabel(fail));
                res.remove(false);
            }
            else if (res.containsKey(true)) {
                forLabel.append(res.get(true));

                ta.setText(forLabel.toString());
                panel3.add(ta, gbc);
                gbc.gridwidth = 1;
                gbc.gridx = 3;
                panel3.add(new JLabel(success));
                res.remove(true);
            }
        }
        panel3.revalidate();
        window.pack();
    }
}
