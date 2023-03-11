
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.filechooser.FileSystemView;

//this project is for learning purposes and not for commercial use
class Jedit extends JFrame implements ActionListener{
    JTextArea text;//creates a textbox
    JFrame frame;//creates a frame

    Jedit()//constructor
     {
        frame = new JFrame("Jedit");
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e){

        }
        text= new JTextArea();//area to write
        JMenuBar menu = new JMenuBar();//menubar
         JMenu menu1 = new JMenu("File");
         //items in the menu "file"
         JMenuItem menui1 = new JMenuItem("New");
         JMenuItem menui2 = new JMenuItem("Open");
         JMenuItem menui3 = new JMenuItem("Save");

            //action listner
         menui1.addActionListener(this);
         menui2.addActionListener(this);
         menui3.addActionListener(this);
         //add them to file
         menu1.add(menui1);
         menu1.add(menui2);
         menu1.add(menui3);

         JMenu menu2 = new JMenu("Edit");

         JMenuItem menui4 = new JMenuItem("cut");
         JMenuItem menui5 = new JMenuItem("copy");
         JMenuItem menui6 = new JMenuItem("paste");

         menui4.addActionListener(this);
         menui5.addActionListener(this);
         menui6.addActionListener(this);

         menu2.add(menui4);
         menu2.add(menui5);
         menu2.add(menui6);

         JMenuItem close = new JMenuItem("close");
         close.addActionListener(this);

         menu.add(menu1);
         menu.add(menu2);
         menu.add(close);

         frame.setJMenuBar(menu);
         frame.add(text);
         frame.setSize(800,800);
         frame.show();

     }
     @Override
public void actionPerformed(ActionEvent e) {
    String str = e.getActionCommand();
    if (str.equals("cut")) {
        text.cut();
    }
   else if (str.equals("paste")) {
        text.paste();

    }
    else if (str.equals("copy")) {
        text.copy();
    }
    else if (str.equals("Save")) {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.setDialogTitle("Choose destination.");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnint = chooser.showSaveDialog(null);
        if (returnint == JFileChooser.APPROVE_OPTION) {
            File fi = new File(chooser.getSelectedFile().getAbsolutePath());
            try {
                // Create a file writer
                FileWriter wr = new FileWriter(fi, false);

                // Create buffered writer to write
                BufferedWriter w = new BufferedWriter(wr);

                // Write
                w.write(text.getText());

                w.flush();
                w.close();
            }
            catch (Exception evt) {
                JOptionPane.showMessageDialog(frame, evt.getMessage());
            }
        }
        else
            JOptionPane.showMessageDialog(frame, "Welp, It seems like you cancelled this");

    }
    else if (str.equals("Open")){
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.setDialogTitle("Choose destination.");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnint = chooser.showOpenDialog(null);
        if (returnint == JFileChooser.APPROVE_OPTION) {
            File fi = new File(chooser.getSelectedFile().getAbsolutePath());
            try{
                String s1 = "", sl = "";
                FileReader fr = new FileReader(fi);
                BufferedReader br = new BufferedReader(fr);
                sl = br.readLine();
                while ((s1 = br.readLine()) != null) {
                    sl = sl + "\n" + s1;
                }
                text.setText(sl);
            }
            catch (Exception evt) {
                JOptionPane.showMessageDialog(frame, evt.getMessage());
            }
            }
        else
            JOptionPane.showMessageDialog(frame, "Welp, It seems like you cancelled this");
    }
    else if (str.equals("New")) {
        text.setText("");
    }
    else if (str.equals("close")) {
        frame.setVisible(false);
        System.exit(0);
}
}

    public static void main(String args[])
    {
        Jedit e = new Jedit();
    }
}