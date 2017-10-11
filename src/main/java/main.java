import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class main extends Component{
    private JPanel mainPanel;
    private JButton importButton;

    final JFileChooser fc = new JFileChooser();

    public main() {

        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == importButton) {
                    int returnVal = fc.showOpenDialog(main.this);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        System.out.println("Opening: " + file.getName() + ".");
                    } else {
                        System.out.println("Open command cancelled by user.");
                    }
            }
        }});
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("main");
        frame.setContentPane(new main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
