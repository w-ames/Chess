import javax.swing.*;

public class TestMain {

    public static void main(String[] args) {
        TestFrame testFrame = new TestFrame();
        ChessPanel chessPanel = new ChessPanel();

        testFrame.add(chessPanel);
    }
}