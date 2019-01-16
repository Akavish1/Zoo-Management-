 // Or Hadad / 308101591 / Beer Sheva

package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 */
public class ZooFrame extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    
    JMenuBar mb = new JMenuBar();
    
    JMenu m1 = new JMenu("File");
    JMenu m2 = new JMenu("Background");
    JMenu m3 = new JMenu("Help");
    
    JMenuItem jexit = new JMenuItem("Exit");
    JMenuItem jimage = new JMenuItem("Image");
    JMenuItem jgreen = new JMenuItem("Green");
    JMenuItem jnone = new JMenuItem("None");
    JMenuItem jhelp = new JMenuItem("Help");
    
    //setting up the buttons
    ZooPanel panel;
    public ZooFrame(){
        super("Zoo");
        panel = new ZooPanel(this);
        add(panel);
        panel.setVisible(true);
        jexit.addActionListener(this);
        jimage.addActionListener(this);
        jgreen.addActionListener(this);
        jnone.addActionListener(this);
        jhelp.addActionListener(this);
        m1.add(jexit);
        m2.add(jimage);
        m2.addSeparator();
        m2.add(jgreen);
        m2.addSeparator();
        m2.add(jnone);
        m3.add(jhelp);
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        setJMenuBar(mb);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == jexit)
            System.exit(0);
        if(e.getSource() == jimage){
            panel.background("Image");
        }
        if (e.getSource() == jgreen){
            panel.background("Green");
        }
        if(e.getSource() == jnone){
            panel.background("None");
        }
        if(e.getSource() == jhelp){
            JOptionPane.showMessageDialog(this, "Home Work 3\nGUI @ Threads");
        }
    }
    
    public static void main(String[]args){
	   ZooFrame frame = new ZooFrame();
	   frame.setSize(800,600);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setVisible(true);
   }
}
