package oop.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class FishGame extends JFrame implements Runnable { 
	private Icon[] icons = new Icon[] { new ImageIcon(getClass().getResource("/oop/gui/resource/face-plain.png")),
			new ImageIcon(getClass().getResource("/oop/gui/resource/face-smirk.png")),
			new ImageIcon(getClass().getResource("/oop/gui/resource/face-surprise.png")),
			new ImageIcon(getClass().getResource("/oop/gui/resource/face-tired.png")),
			new ImageIcon(getClass().getResource("/oop/gui/resource/emblem-notice.png")),
			new ImageIcon(getClass().getResource("/oop/gui/resource/babelfish.png")),
			new ImageIcon(getClass().getResource("/oop/gui/resource/emblem-favorite.png")),
			new ImageIcon(getClass().getResource("/oop/gui/resource/face-devilish.png")) };

	private JLabel info = new JLabel("move fish =>", icons[4], SwingConstants.LEFT);
	private BasicArrowButton up = new BasicArrowButton(BasicArrowButton.NORTH),
			down = new BasicArrowButton(BasicArrowButton.SOUTH), left = new BasicArrowButton(BasicArrowButton.WEST),
			right = new BasicArrowButton(BasicArrowButton.EAST);
	private JButton addFeed = new JButton("Feed", icons[0]);
	private JToggleButton holdPlay = new JToggleButton("Hold", icons[3]);
	JLabel fish = new JLabel(icons[5]);
	JPanel playground = new JPanel(); // run에서 사용 할 playground 선언
	JLabel[] feed = new JLabel[5]; // run에서 사용 할 feed 선언
	JLabel[] devil = new JLabel[5]; // feed, devil 미리 배열로 선언
	JLabel score1 = new JLabel("0"); // Label에 추가할 score 점수
	JLabel score2 = new JLabel("1"); // feed와 닿을 때 마다 label에 있는 숫자 변경
	JLabel score3 = new JLabel("2");
	JLabel score4 = new JLabel("3");
	JLabel score5 = new JLabel("4");
	JLabel score6 = new JLabel("5");
	int resetscore = 0; // score 점수 비교를 위한 값 선언
	
	Random r = new Random(); // 랜덤함수 미리 선언

	public FishGame(String title) {
		super(title);
		JPanel controls = new JPanel();
		controls.add(info);
		controls.add(up);
		controls.add(down);
		controls.add(left);
		controls.add(right);
		
		controls.add(addFeed);
		addFeed.setRolloverIcon(icons[1]);
		addFeed.setPressedIcon(icons[2]);
		
		controls.add(holdPlay);
		getContentPane().add(BorderLayout.SOUTH, controls);

		playground.setBackground(Color.WHITE); 
		playground.setLayout(null);
		playground.add(fish);
		fish.setBounds(200, 200, icons[5].getIconWidth(), icons[5].getIconHeight());

		 for(int i = 0; i < feed.length; i++) {  // feed.length만큼 playground에 feed[i] 추가
	         feed[i] = new JLabel(icons[6]);
	         playground.add(feed[i]);
	         Random r = new Random(); 
	         feed[i].setBounds(r.nextInt(300), r.nextInt(300), icons[6].getIconWidth(), icons[6].getIconHeight());
	      }
	   
	      for(int i = 0; i < devil.length; i++) {  // devil.length만큼 playground에 feed[i] 추가
	         devil[i] = new JLabel(icons[7]);
	         playground.add(devil[i]);
	         Random r = new Random();
	         devil[i].setBounds(r.nextInt(300)+10, r.nextInt(300), icons[7].getIconWidth(), icons[7].getIconHeight());
	      }
	      
	      new Thread(this).start(); // Thread 시작
	      
	      getContentPane().add(BorderLayout.CENTER, playground);
	      setBounds(50, 50, 400, 400);
	      setVisible(true);
	      this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point present = fish.getLocation();
				present.y = (present.y - 10 <= 0) ? 0 : present.y - 10;
				fish.setLocation(present);
			}
		});

		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point present = fish.getLocation();
				present.y = (present.y + fish.getHeight() + 10 >= playground.getHeight())
						? playground.getHeight() - fish.getHeight()
						: present.y + 10;
				fish.setLocation(present);
			}
		});

		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point present = fish.getLocation();
				present.x = (present.x - 10 <= 0) ? 0 : present.x - 10;
				fish.setLocation(present);
			}
		});

		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point present = fish.getLocation();
				present.x = (present.x + fish.getWidth() + 10 >= playground.getWidth())
						? playground.getWidth() - fish.getWidth()
						: present.x + 10;
				fish.setLocation(present);
			}
		});
		
		playground.addKeyListener(new KeyListener() { // Challenge#1 KeyEventListener 추가
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) { 
					Point present = fish.getLocation(); //fish 현재 위치 입력받음.
					present.y = (present.y - 10 <= 0) ? 0 : present.y - 10; //올라갈때마다 10씩 이동
					fish.setLocation(present); //fish 현재 위치 반환.
				}
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					Point present = fish.getLocation(); 
					present.y = (present.y + fish.getHeight() + 10 >= playground.getHeight()) 
							? playground.getHeight() - fish.getHeight()
							: present.y + 10;
					fish.setLocation(present); 
				}
				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					Point present = fish.getLocation(); 
					present.x = (present.x - 10 <= 0) ? 0 : present.x - 10;
					fish.setLocation(present); 
				}
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					Point present = fish.getLocation();
					present.x = (present.x + fish.getWidth() + 10 >= playground.getWidth())
							? playground.getWidth() - fish.getWidth()
							: present.x + 10;
					fish.setLocation(present); 
				}

			}

			public void keyReleased(KeyEvent e) {
				
			}

			public void keyTyped(KeyEvent e) {
				
			}
		});
		
		score1.setForeground(Color.red); // score 빨간색 설정
		score1.setBounds(350, 0, 10,10); // score 라벨 위치 선정
		playground.add(score1); // score playground에 붙이기
		
	}

	@Override
	public void run() {
		int devilspeed_x[] = {5,6,7,8,9}; //5개의 각 devil 마다 속도 설정
		
		while (true) {
			playground.setFocusable(true); // playground에 포커스, FishGame 생성자 안에서 키보드 입력이 될때가 있고 안될때가 있어서 run()안에 넣음.
			for (int i = 0; i < devil.length; i++) {
				//Challenge #3 여러개의 devil 추가
				int devil_x = devil[i].getX();
				int devil_y = devil[i].getY();
				//Challenge #4 화면경계에 도달하면 반대방향으로 이동
				if (devil_x <= 0 || devil_x >= playground.getWidth()-30) { // 배경 밖으로 나가면 방향을 바꿔줌, 
					//devil이 오른쪽 밖으로 나가기에 30을 빼주면서 오른쪽 배경안으로 들어오게 함
					
					devilspeed_x[i] = - devilspeed_x[i]; // 방향 전환
				}
				//Challenge #4 수평으로 일정 크기만큼 이동함
				devil[i].setLocation(devil[i].getX() + devilspeed_x[i], devil[i].getY()); // 각 devil 마다 x축으로의 움직임 나타내기

				Point fish_location = fish.getLocation(); // fish, devil 위치 얻어내기
				Point devil_location = devil[i].getLocation();
				//거리 계산 루트((x1-x2)^2+(y1-y2)^2) 응용
				double distance = Math.sqrt(Math.pow(fish_location.x - devil_location.x, 2) + Math.pow(fish_location.y - devil_location.y, 2));
				boolean meeting;
				
				if (distance < 15) { // 실험시 거리 15 이하이면 닿는걸로 나와 거리가 15보다 작을시 충돌로 간주
					meeting = true;
					
				} else {
					meeting = false;
				}
				//Challenge #2 Fish가 Devil에 닿을 경우 프로그램 종료
				if (meeting) { //boolean meeting = true 일때 확인
					System.out.print("GAME_OVER");
					System.exit(0); // 프로그램 종료
				}

			}

			for (int i = 0; i < feed.length; i++) { 
				//Challenge #3 여러개의 feed 추가
				Point feed_location = feed[i].getLocation();
				Point fish_location = fish.getLocation();
				//거리 계산 루트((x1-x2)^2+(y1-y2)^2) 응용
				double distance = Math.sqrt(Math.pow(fish_location.x - feed_location.x, 2) + Math.pow(fish_location.y - feed_location.y, 2));
				boolean meeting;
				
				if (distance < 15) { // 실험시 거리 15 이하이면 닿는걸로 나와 거리가 15보다 작을시 충돌로 간주
					meeting = true;
					
				} else {
					meeting = false;
				}
				//Challenge #2 Fish가 Feed에 닿을 경우 화면 밖으로 보내서 제거
				if (meeting) {  
					feed[i].setLocation(30000, 30000); // 만나면 화면 밖으로 보내기
					playground.remove(feed[i]); // 화면에서 Feed 제거
					
					resetscore+=1;
					//Challenge #2 Fish가 Feed에 닿을 경우 score 1점 올려줌.
					if(resetscore==1) {
					score1.setLocation(30000, 30000); //화면 밖으로 보내서 제거
					score2.setForeground(Color.red); // score 빨간색 글씨로 나타냄
					score2.setBounds(350, 0, 10,10); // score 위치 시키기
					playground.add(score2); // 다음 score 붙여주기
					}
					if(resetscore==2) {
					score2.setLocation(30000, 30000);
					score3.setForeground(Color.red);
					score3.setBounds(350, 0, 10,10);
					playground.add(score3);
					}
					if(resetscore==3) {
						score3.setLocation(30000, 30000);
						score4.setForeground(Color.red);
						score4.setBounds(350, 0, 10,10);
						playground.add(score4);
					}
					if(resetscore==4) {
						score4.setLocation(30000, 30000);
						score5.setForeground(Color.red);
						score5.setBounds(350, 0, 10,10);
						playground.add(score5);
					}
					if(resetscore==5) {
						score5.setLocation(30000, 30000);
						score6.setForeground(Color.red);
						score6.setBounds(350, 0, 10,10);
						playground.add(score6);
						}
				}
			}
			try {
				Thread.sleep(100); // thread 100밀리초 중단
			} catch (Exception e) { 
			}
		}
	}
	
	public static void main(String[] args) {
		new FishGame("Move Fish!");

	}
}