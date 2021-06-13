import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SimPanel extends JPanel implements ActionListener {
    final private int PANEL_WIDTH = 500;
    final private int PANEL_HEIGHT = 500;
    private int rows;
    private int columns;
    private int genTime;
    private boolean[][] board;
    private Timer timer;
    private Random rand = new Random();

    public SimPanel(int aRows, int aColumns, int aGenTime) {
        rows = aRows;
        columns = aColumns;
        genTime = aGenTime;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setFocusable(true);
        timer = new Timer(genTime, this);

        board = new boolean[rows][columns];

        for(int r=0; r<rows; ++r) {
            for(int c=0; c<columns; ++c) {
                if(rand.nextInt(8) == 7) { board[r][c] = true; } 
                else { board[r][c] = false; }
            }
        }

        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.BLACK);
        for(int r=0; r<rows; ++r) {
            for(int c=0; c<columns; ++c) {
                if(board[r][c]) { g2.fillRect(c*(getWidth()/columns), r*(getHeight()/rows), (getWidth()/columns), (getHeight()/rows));}
            }
        }

        g2.setColor(Color.GRAY);
        for(int r=0; r<rows; ++r) {
            g2.drawLine(0, r*(getHeight()/rows), getWidth(), r*(getHeight()/rows));
        }
        for(int c=0; c<columns; ++c) {
            g2.drawLine(c*(getWidth()/columns), 0, c*(getWidth()/columns), getHeight());
        }
    }

    public void updateBoard() {
        boolean[][] temp = board;
        int surroundingCells = 0;
        for(int r=0; r<rows; ++r) {
            for(int c=0; c<columns; ++c) {
                surroundingCells = 0;
                try { if(board[r-1][c-1]) { surroundingCells++; } } catch(ArrayIndexOutOfBoundsException e) {}
                try { if(board[r-1][c]) { surroundingCells++; } } catch(ArrayIndexOutOfBoundsException e) {}
                try { if(board[r-1][c+1]) { surroundingCells++; } } catch(ArrayIndexOutOfBoundsException e) {}
                try { if(board[r][c-1]) { surroundingCells++; } } catch(ArrayIndexOutOfBoundsException e) {}
                try { if(board[r][c+1]) { surroundingCells++; } } catch(ArrayIndexOutOfBoundsException e) {}
                try { if(board[r+1][c-1]) { surroundingCells++; } } catch(ArrayIndexOutOfBoundsException e) {}
                try { if(board[r+1][c]) { surroundingCells++; } } catch(ArrayIndexOutOfBoundsException e) {}
                try { if(board[r+1][c+1]) { surroundingCells++; } } catch(ArrayIndexOutOfBoundsException e) {}
                if(board[r][c]) {
                    if(surroundingCells < 2) { temp[r][c] = false; } //rule 1
                    else if(surroundingCells == 2 || surroundingCells == 3) { temp[r][c] = true; } //rule 2
                    else if(surroundingCells > 3) { temp[r][c] = false; } //rule 3
                }
                else {
                    if(surroundingCells == 3) { temp[r][c] = true; } //rule 4
                }
            }
        }
        board = temp;
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateBoard();
    }

}
