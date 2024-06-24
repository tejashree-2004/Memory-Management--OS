package memoryproject;

import javax.swing.JFrame;

public class BuildGui extends JFrame {
    private static final int WIDTH = 500, HEIGHT = 570;
    
    public BuildGui(){
        super("Memory Manager");
        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new Components());
      
        this.setVisible(true);
    }
}
