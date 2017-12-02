import main.controller.MainController;
import main.view.Main;


public class main {
    public static void main(String[] args) {
        Main frame = new Main();
        new MainController(frame);
        frame.setVisible(true);
    }
}
