import javax.swing.*;

import com.apple.eawt.Application;

public class TestMain {

    public static void main(String[] args) {
        ApplicationFrame testFrame = new ApplicationFrame();
        ChessPanel chessPanel = new ChessPanel();

        testFrame.add(chessPanel);
    }
}