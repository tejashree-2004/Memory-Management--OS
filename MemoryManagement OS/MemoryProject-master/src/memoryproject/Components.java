package memoryproject;

import javax.swing.JOptionPane;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Components extends JPanel{
    private static final int WIDTH = 500, HEIGHT = 570;
    private Font font1 = new Font("SansSerif", Font.PLAIN, 13);
    
    private JLabel memSizeLabel = new JLabel("Size:");
    private JTextField memSizeInput = new JTextField();
    
    private JLabel algoLabel = new JLabel("Algorithms:");
    private static String[] algorithms = {"1. First Fit", "2. Best Fit", "3. Worst Fit"};
    private JComboBox memoryAlgos = new JComboBox(algorithms);
    
    private String algoSelected = "1. First Fit";
    private String process = "Process 1";
    
    private int processSize = 7;
    
    private JLabel pidLabel = new JLabel("PID #:");
    private int pid = 0;
    private String[] pids = {"Process 1",
                             "Process 2",
                             "Process 3",
                             "Process 4",
                             "Process 5",
                             "Process 6",
                             "Process 7",
                             "Process 8",
                             "Process 9",
                             "Process 10",
                             "Process 11"};
    private JComboBox pidList = new JComboBox(pids);
    
    private Color honeyDew = new Color(240, 255, 240);
    private Color cherryBlossom = new Color(255, 221, 228);
    
    private boolean processAdded = false;
    
    private JButton addMemoryBlock = new JButton("Add Memory Block");
    private JButton removeMemoryBlock = new JButton("Remove Memory Block");
    private JButton compactMemory = new JButton("Compact Memory");
    
    private ArrayList<MemoryBlock> blocks = new ArrayList();;
    
    public Components(){
        setLayout(null);
        setSize(WIDTH,HEIGHT);
        setBackground(cherryBlossom);
        
        addPIDLabel();
        addPIDList();
        
        addMemSizeLabel();
        addMemSizeInput();
        
        addAlgorithmLabel();
        addMemoryAlgorithmsList();
        
        
        //MemoryContainer containsMemory = new MemoryContainer();
        //add(containsMemory);
        
        addMemoryAddButton();
        addMemoryRemoveButton();
        addCompactMemoryButton();
        
        setVisible(true);
        repaint();
    }
    
    public void addMemoryAddButton(){
        addMemoryBlock.setBounds(60, 300, 170, 35);
        addMemoryBlock.setFont(font1);
        addMemoryBlock.setVisible(true);
        add(addMemoryBlock);
        addMemoryBlock.addActionListener(new AddMemoryBlock());
        repaint();
    }
    
    public void addMemoryRemoveButton(){
        removeMemoryBlock.setBounds(60, 350, 170, 35);
        removeMemoryBlock.setFont(font1);
        removeMemoryBlock.setVisible(true);
        add(removeMemoryBlock);
        removeMemoryBlock.addActionListener(new RemoveMemoryBlock());
        repaint();
    }
    
    public void addCompactMemoryButton(){
        compactMemory.setBounds(60, 400, 170, 35);
        compactMemory.setFont(font1);
        compactMemory.setVisible(true);
        add(compactMemory);
        repaint();
    }
     
    public void addAlgorithmLabel(){
        algoLabel.setBounds(5, 20, 90, 30);
        algoLabel.setFont(font1);
        algoLabel.setVisible(true);
        add(algoLabel);
        repaint();
    }
    
    public void addMemoryAlgorithmsList(){
        memoryAlgos.setEditable(false);
        memoryAlgos.setFont(font1);
        memoryAlgos.setBounds(80, 20, 125, 30);
        memoryAlgos.setVisible(true);
        add(memoryAlgos);
        repaint();
    }
    
    public void addPIDLabel(){
        pidLabel.setBounds(42, 100, 70, 30);
        pidLabel.setFont(font1);
        pidLabel.setVisible(true);
        add(pidLabel);
        repaint();
    }
    
    public void addPIDList(){
        pidList.setEditable(false);
        pidList.setBounds(80, 100, 125, 30);
        pidList.setFont(font1);
        pidList.setVisible(true);
        add(pidList);
        repaint();
    }
    
    public void addMemSizeLabel(){
        memSizeLabel.setBounds(47, 150, 70, 30);
        memSizeLabel.setFont(font1);
        memSizeLabel.setVisible(true);
        add(memSizeLabel);
        repaint();
    }
    
    public void addMemSizeInput(){
        memSizeInput.setBounds(80, 150, 70, 30);
        memSizeInput.setFont(font1);
        memSizeInput.setVisible(true);
        add(memSizeInput);
        repaint();
    }
    
    
    private int x = 300, y = 50, widthOfContainer = 182, heightOfContainer = 400;
    private int newY = 0;
    private int numOfProcesses = 0;
    
    @Override
    public void paintComponent(Graphics g){
        // x = 300 | y = 50 | width = 182 | height = 400
        g.drawRect(x, y, widthOfContainer, heightOfContainer);
         
        g.drawString("0KB", x-35, 57);
        g.drawString("8192KB", x-55, 455);
        
        for(MemoryBlock block: blocks){
            block.drawFirstFit(g);
            repaint();
        }
    }
    
    public class AddMemoryBlock implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(!memSizeInput.getText().equalsIgnoreCase("")){
                processSize = Integer.parseInt(memSizeInput.getText());
                pid = pidList.getSelectedIndex() + 1;
            }
            
            int heightOfBlock = processSize * 7;
            if(numOfProcesses == 0){ 
                addMemBlock(new MemoryBlock(x, y, 182, heightOfBlock, processSize, pid));   
                newY = y + heightOfBlock;
                numOfProcesses++;
            }else{
                if(newY <= heightOfContainer){
                    addMemBlock(new MemoryBlock(x, newY, 182, heightOfBlock, processSize, pid));
                    newY = newY + heightOfBlock;
                    numOfProcesses++;
                }  
            }
        }
        
        public void addMemBlock(MemoryBlock block){
            blocks.add(block);
            repaint();
        }
    }
        
        public class RemoveMemoryBlock implements ActionListener 
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                pid = pidList.getSelectedIndex() + 1;
                if(numOfProcesses == 0)
                {
                    JOptionPane.showMessageDialog(null, "There are no processes to remove!");
                }
                else
                {
                    for(int i = 0; i < blocks.size(); i++)
                    {
                        if(blocks.get(i).getPID() == pid)
                        {
                            blocks.remove(i);
                        }
                    }
                    repaint();
                }
            }
        }
}                  
