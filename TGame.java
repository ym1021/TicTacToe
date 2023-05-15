/*
 *  자바프로그래밍 04분반
 *  스마트IoT전공 20116940 김보경
 *  2021.05.05 ~ 2021.06.15
 *  GUI tic-tac-toe game
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TGame extends JFrame {
	final static int BNUM = 3; //JButton 배열에 넣을 상수 정의
	JButton btn[][] = new JButton[BNUM][BNUM]; //JButton 배열 만들기
	static int turn = 0; //순서 체크를 위한 전역변수
	
	public TGame() {
		setTitle("tic-tac-toe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createMenu(); //메뉴 만드는 메소드로 이동
		
		//(예제 9-5) GridLayout으로 입력 폼 만들기 
		GridLayout grid = new GridLayout(BNUM, BNUM);
		Container c = getContentPane();
		c.setLayout(grid);
		
		for(int i = 0; i < BNUM; i++) {
			for(int j = 0; j < BNUM; j++) {
				btn[i][j] = new JButton(""); //(참고문헌[4]) 버튼을 배열로 선언
				//JButton에 Action 이벤트 리스너 만드는 내부 클래스 추가하기
				btn[i][j].addActionListener(new MyActionListener());
				c.add(btn[i][j]);
			}
		}
		setSize(300, 300);
		setVisible(true);
	}
	
	//(예제 14-1) 메뉴 만들기
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenuItem [] menuItem = new JMenuItem[2];
		String[] itemTitle = {"Restart", "Exit"};
		JMenu menu = new JMenu("Menu");
		//메뉴아이템에 Action 이벤트 리스너 추가하기
		MenuActionListener mal = new MenuActionListener();
		for(int i = 0; i < menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(mal);
			menu.add(menuItem[i]);
		}
		menuBar.add(menu);
		setJMenuBar(menuBar);
	}
	
	//(예제 14-2) 메뉴아이템 선택 시 동작하는 Action 이벤트 리스너 만들기
	private class MenuActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "Restart":
				for(int i = 0; i < BNUM; i++) {
					for(int j = 0; j < BNUM; j++) {
						btn[i][j].setText("");
					}
				}
				break;
			case "Exit":
				System.exit(0);
				break;
			}
		}
	}
	
	//(예제 10-2) 내부 클래스로 버튼 클릭 시 동작하는 Action 이벤트 리스너 만들기
	private class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			
			//사용자1과 사용자2 번갈아가며 표시
			if(turn % 2 == 0) {
				if(b.getText().equals("")) {
					b.setText("O");
					turn++;
				}
			} else {
				if(b.getText().equals("")) {
					b.setText("X");
					turn++;
				}
			}
			
			String result = winCheck(); //Action 이벤트하고 매번 결과 확인
			if(!result.equals("")) {
				JOptionPane.showMessageDialog(null, result, "Message", JOptionPane.CLOSED_OPTION);
				turn = 0;
				for(int i = 0; i < BNUM; i++) {
					for(int j = 0; j < BNUM; j++) {
						btn[i][j].setText("");
					}
				}
			}
		}
	}
	
	//결과 확인 메소드
	private String winCheck() {
		//행이 같은지 확인
		int i = 0;
		int j = 0;
		for(i = 0; i < BNUM; i++) {
			if(btn[i][j].getText().equals("O")&&btn[i][j+1].getText().equals("O")&&btn[i][j+2].getText().equals("O")) {
				return "O win";
			} else if(btn[i][j].getText().equals("X")&&btn[i][j+1].getText().equals("X")&&btn[i][j+2].getText().equals("X")) {
				return "X win";
			}
		}
		//열이 같은지 확인
		i = 0;
		j = 0;
		for(j = 0; j < BNUM; j++) {
			if(btn[i][j].getText().equals("O")&&btn[i+1][j].getText().equals("O")&&btn[i+2][j].getText().equals("O")) {
				return "O win";
			} else if(btn[i][j].getText().equals("X")&&btn[i+1][j].getText().equals("X")&&btn[i+2][j].getText().equals("X")) {
				return "X win";
			}
		}
		//대각선\이 같은지 확인
		i = 0;
		j = 0;
		if(btn[i][j].getText().equals("O")&&btn[i+1][j+1].getText().equals("O")&&btn[i+2][j+2].getText().equals("O")) {
			return "O win";
		} else if(btn[i][j].getText().equals("X")&&btn[i+1][j+1].getText().equals("X")&&btn[i+2][j+2].getText().equals("X")) {
			return "X win";
		}
		//대각선/이 같은지 확인
		i = 0;
		j = 0;
		if(btn[i][j+2].getText().equals("O")&&btn[i+1][j+1].getText().equals("O")&&btn[i+2][j].getText().equals("O")) {
			return "O win";
		} else if(btn[i][j+2].getText().equals("X")&&btn[i+1][j+1].getText().equals("X")&&btn[i+2][j].getText().equals("X")) {
			return "X win";
		}
		//무승부
		if(turn == 9) {
			return "draw";
		}
		return "";
	}

	//메인 실행
	public static void main(String[] args) {
		new TGame();
	}
}