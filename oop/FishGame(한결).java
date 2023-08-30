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
		new ImageIcon(getClass().getResource("/oop/gui/resource/face-devilish.png"))
	};

	private JLabel info = new JLabel("move fish =>", icons[4], SwingConstants.LEFT);
	private BasicArrowButton
		up = new BasicArrowButton(BasicArrowButton.NORTH),
		down = new BasicArrowButton(BasicArrowButton.SOUTH),
		left = new BasicArrowButton(BasicArrowButton.WEST),
		right = new BasicArrowButton(BasicArrowButton.EAST);
	private JButton addFeed = new JButton("Feed", icons[0]);
	private JToggleButton holdPlay = new JToggleButton("Hold", icons[3]);
	
	
	Random r = new Random();
	JLabel[] feed = new JLabel[10];		//Thread run에서의 사용을 위한 new JLabel 미리 선언
	JLabel[] devil = new JLabel[10];	//10개 생성을 위한 배열화
	JLabel fish = new JLabel(icons[5]);
	JPanel playground = new JPanel();	//Thread run에서의 사용을 위한 playground 미리 선언
	
	
	@Override
	public void run() {
		int level = 7;					//이동속도 조절을 위한 장치
		int[] xSpeed = new int[devil.length];	//x방향 이동속도 설정
		int[] ySpeed = new int[devil.length];	//y방향 이동속도 설정
		for(int i=0; i< devil.length; i++) {	//devil마다 속도 각각 지정
			if(Math.random()<=0.25) {			//랜덤한 시작시 이동방향 지정
				xSpeed[i] = level;
				ySpeed[i] = level;
			}
			if(Math.random() > 0.25 && Math.random() <= 0.50) {
				xSpeed[i] = level;
				ySpeed[i] = -level;
			}
			if(Math.random() > 0.50 && Math.random() <= 0.75) {
				xSpeed[i] = -level;
				ySpeed[i] = level;
			}
			else {
				xSpeed[i] = level;
				ySpeed[i] = -level;
			}
		}
		
		while(true) {
			for(int i=0; i< devil.length; i++) {	
				int devilX = devil[i].getX();		//devil 위치 반복해서 확인
				int devilY = devil[i].getY();
				
				if(devilX <= 0 || devilX >= playground.getWidth()) { //벽에 닿으면 방향 전환
					xSpeed[i] = -xSpeed[i];
				}
				if(devilY <= 0 || devilY >= playground.getHeight()) {
					ySpeed[i] = -ySpeed[i];
				}
				devil[i].setLocation(devil[i].getX()+xSpeed[i],devil[i].getY()+ySpeed[i]); //위치 변경
				
				if(checkMeet(fish,devil[i])){   //devil과 fish 접촉 반복해서 확인
					System.out.print("sex");	//만나면 반갑다고 섹스
				}
	
			}
			
			for(int i=0; i< feed.length; i++) { //feed와 fish 접촉 반복해서 확인
				if(checkMeet(fish,feed[i])){	//만나면 반갑다고 삭스
					System.out.print("sax");
				}
			}
		try {
			Thread.sleep(100);
		} catch(Exception e) {}
		}
	}
	
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
		
		

		for(int i = 0; i < feed.length; i++) {		//feed[]갯수만큼 feed 추가
			feed[i] = new JLabel(icons[6]);
			playground.add(feed[i]);
			Random r = new Random();
			feed[i].setBounds(r.nextInt(300), r.nextInt(300), icons[6].getIconWidth(), icons[6].getIconHeight());
		}
	

		for(int i = 0; i < devil.length; i++) {		//devil[]갯수만큼 devil 추가
			devil[i] = new JLabel(icons[7]);
			playground.add(devil[i]);
			Random r = new Random();
			devil[i].setBounds(r.nextInt(300), r.nextInt(300), icons[7].getIconWidth(), icons[7].getIconHeight());
		}
		
		new Thread(this).start(); // 모든 JLabel add 후 Thread 시작
		
		getContentPane().add(BorderLayout.CENTER, playground);
		setBounds(50, 50, 400, 400);
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		playground.setFocusable(true);  //playground에 포커스
		playground.addKeyListener(new KeyListener() {   //keyListener 추가
			public void keyPressed(KeyEvent e) {
				Point present = fish.getLocation();		//keyPressed 시점의 fish위치
				
				if(e.getKeyCode()==KeyEvent.VK_UP) {	//pressed Key 별로 위치 조절
					present.y = (present.y - 10 <= 0) ? 0 : present.y - 10;
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					present.y = (present.y + fish.getHeight() + 10 >= playground.getHeight()) ?
					playground.getHeight() - fish.getHeight() : present.y + 10;
				}
				if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					present.x = (present.x - 10 <= 0) ? 0 : present.x - 10;
				}
				if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					present.x = (present.x + fish.getWidth() + 10 >= playground.getWidth()) ?
					playground.getWidth() - fish.getWidth() : present.x + 10;
				}
				fish.setLocation(present);				//변경된 fish 위치 적용
			}
			public void keyReleased(KeyEvent e) {

			}
			public void keyTyped(KeyEvent e) {

			}
		});
		
		
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
			present.y = (present.y + fish.getHeight() + 10 >= playground.getHeight()) ?
			playground.getHeight() - fish.getHeight() : present.y + 10;
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
			present.x = (present.x + fish.getWidth() + 10 >= playground.getWidth()) ?
			playground.getWidth() - fish.getWidth() : present.x + 10;
			fish.setLocation(present);
			}
		});
	
		
	}
	
	public double getDistance(Point a, Point b) {		//두좌표계 거리 계산 함수
		double d = Math.sqrt(Math.pow(a.x - b.x,2)+Math.pow(a.y - b.y,2));
		return d;
	}
	
	public boolean checkMeet(JLabel fish, JLabel some) {	//두 JLabel이 닿았는지 판단 함수
		Point fishPoint = fish.getLocation();
		Point somePoint = some.getLocation();
		double distance = getDistance(fishPoint, somePoint);
		
		int limit = (fish.getWidth()+fish.getHeight())/2;	//가로세로 평균으로 닿는거리 어림계산
		if(distance < limit-20) {		//너무 멀리서도 닿은 판정이라 20 마이너스
			return true;
		}
		else {
			return false;
		}
	}
	

	
	
	
	public static void main(String[] args) {
		new FishGame("Move Fish!");

		
	}
}