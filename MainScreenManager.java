import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.*;
public class MainScreenManager extends JPanel implements ActionListener
{
    private static final String battleAction = "battleAction";
    private int indexP1 = 0, indexP2 = 0;
    private Player[] characters1, characters2;
    private ArrayList<GamePiece> entities;

    public MainScreenManager(ArrayList<GamePiece> pieces)
    {
        super(new BorderLayout(50, 100));
        entities = pieces;
        GamePiece g1 = entities.get(0); //player1
        characters1 = new Player[] {new Canoneer(g1.getX(), g1.getY(), Constants.PLAYER_1), new Swordsman(g1.getX(), g1.getY(), Constants.PLAYER_1)};
        GamePiece g2 = entities.get(1); //player2
        characters2 = new Player[] {new Canoneer(g2.getX(), g2.getY(), Constants.PLAYER_2), new Swordsman(g2.getX(), g2.getY(). Constants.PLAYER_2)};
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
    }

    public void createButtons() //call after added to JFrame
    {
        //makes the start button, starts game(battle) when pressed
        JButton startButton = new JButton("Start");
        startButton.setActionCommand(battleAction);
        startButton.addActionListener(this);
        add(startButton, BorderLayout.PAGE_END);
        //makes the buttons that allows user to change character properties
        JButton fwdEdit1 = new JButton(fwdEditAction1); //sets to next character in array (P1)
        fwdEdit1.setActionCommand(fwdEditAction1);
        fwdEdit1.addActionListener(this);
        add(fwdEdit1, BorderLayout.PAGE_START);
        JButton revEdit1 = new JButton(revEditAction1); //sets to previous character in array (P1)
        revEdit1.setActionCommand(revEditAction1);
        revEdit1.addActionListener(this);
        add(revEdit1, BorderLayout.LINE_END);
        JButton fwdEdit2 = new JButton(fwdEditAction2); //sets to next character in array (P2)
        fwdEdit2.setActionCommand(fwdEditAction2);
        fwdEdit2.addActionListener(this);
        add(fwdEdit2, BorderLayout.PAGE_START);
        JButton revEdit2 = new JButton(revEditAction2); //sets to previous character in array (P2)
        revEdit2.setActionCommand(revEditAction2);
        revEdit2.addActionListener(this);
        add(revEdit2, BorderLayout.LINE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if (command.equals(battleAction))
        {
            setEnabled(false);
            BattleManager bm = new BattleManager(3, entities);
            GameWindow w = new GameWindow(getWidth(), getHeight());
            w.setContentPane(bm);
            w.initialize();
            w.setVisible(true);
            JFrame parent = (JFrame) getTopLevelAncestor();
            parent.remove(this);
            parent.setVisible(false);
            parent.dispose();
        }
        if (command.equals(fwdEditAction1))
        {
          indexP1 = (indexP1 + 1) % characters1.length;
          entities.set(0, characters1[indexP1]);
        }
        if (command.equals(revEditAction1))
        {
          indexP1 = (indexP1 - 1) % characters1.length;
          entities.set(0, characters1[indexP1]);
        }
        if (command.equals(fwdEditAction2))
        {
          indexP2 = (indexP2 + 1) % characters2.length;
          entities.set(1, characters[indexP2]);
        }
        if (command.equals(revEditAction2))
        {
          indexP2 = (indexP2 - 1) % characters2.length;
          entities.set(1, characters[indexP2]);
        }
    }
}