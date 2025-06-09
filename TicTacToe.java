import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {

	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textfield = new JLabel();
	JButton[] buttons = new JButton[9];
	boolean player1_turn;

	TicTacToe() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1250, 630);
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Tic TacToe");

		ImageIcon icon = new ImageIcon(getClass().getResource("TicTacToeGame.png"));
		frame.setIconImage(icon.getImage());

		textfield.setBackground(new Color(25, 25, 25));
		textfield.setForeground(new Color(25, 255, 0));
		textfield.setFont(new Font("", Font.BOLD, 75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);

		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0, 0, 800, 100);

		button_panel.setLayout(new GridLayout(3, 3));
		button_panel.setBackground(new Color(150, 150, 150));

		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}

		title_panel.add(textfield);
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(button_panel);

		firstTurn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 9; i++) {
			if (e.getSource() == buttons[i]) {
				if (buttons[i].getText().equals("")) {
					if (player1_turn) {
						buttons[i].setForeground(new Color(255, 0, 0));
						buttons[i].setText("X");
						player1_turn = false;
						textfield.setText("O turn");
					} else {
						buttons[i].setForeground(new Color(0, 0, 255));
						buttons[i].setText("O");
						player1_turn = true;
						textfield.setText("X turn");
					}
					check();
				}
			}
		}
	}

	public void firstTurn() {
//		try {
//			Thread.sleep(00);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		if (random.nextInt(2) == 0) {
			player1_turn = true;
			textfield.setText("X turn");
		} else {
			player1_turn = false;
			textfield.setText("O turn");
		}
	}

	public void check() {
		if (checkWin("X")) return;
		if (checkWin("O")) return;

		boolean draw = true;
		for (int i = 0; i < 9; i++) {
			if (buttons[i].getText().equals("")) {
				draw = false;
				break;
			}
		}

		if (draw) {
			drawGame();
		}
	}

	public boolean checkWin(String player) {
		int[][] winPatterns = {
				{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
				{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
				{0, 4, 8}, {2, 4, 6}             // diagonals
		};

		for (int[] p : winPatterns) {
			if (
					buttons[p[0]].getText().equals(player) &&
							buttons[p[1]].getText().equals(player) &&
							buttons[p[2]].getText().equals(player)
			) {
				if (player.equals("X")) {
					xWins(p[0], p[1], p[2]);
				} else {
					oWins(p[0], p[1], p[2]);
				}
				return true;
			}
		}
		return false;
	}

	public void xWins(int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		disableButtons();
		textfield.setText("X wins");
	}

	public void oWins(int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		disableButtons();
		textfield.setText("O wins");
	}

	public void drawGame() {
		for (int i = 0; i < 9; i++) {
			buttons[i].setBackground(Color.YELLOW);
			buttons[i].setEnabled(false);
		}
		textfield.setText("It's a Draw!");
	}

	public void disableButtons() {
		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
	}

	public static void main(String[] args) {
		new TicTacToe();
	}
}
