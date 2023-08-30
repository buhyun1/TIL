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
	JLabel[] feed = new JLabel[10];		//Thread run������ ����� ���� new JLabel �̸� ����
	JLabel[] devil = new JLabel[10];	//10�� ������ ���� �迭ȭ
	JLabel fish = new JLabel(icons[5]);
	JPanel playground = new JPanel();	//Thread run������ ����� ���� playground �̸� ����
	
	
	@Override
	public void run() {
		int level = 7;					//�̵��ӵ� ������ ���� ��ġ
		int[] xSpeed = new int[devil.length];	//x���� �̵��ӵ� ����
		int[] ySpeed = new int[devil.length];	//y���� �̵��ӵ� ����
		for(int i=0; i< devil.length; i++) {	//devil���� �ӵ� ���� ����
			if(Math.random()<=0.25) {			//������ ���۽� �̵����� ����
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
				int devilX = devil[i].getX();		//devil ��ġ �ݺ��ؼ� Ȯ��
				int devilY = devil[i].getY();
				
				if(devilX <= 0 || devilX >= playground.getWidth()) { //���� ������ ���� ��ȯ
					xSpeed[i] = -xSpeed[i];
				}
				if(devilY <= 0 || devilY >= playground.getHeight()) {
					ySpeed[i] = -ySpeed[i];
				}
				devil[i].setLocation(devil[i].getX()+xSpeed[i],devil[i].getY()+ySpeed[i]); //��ġ ����
				
				if(checkMeet(fish,devil[i])){   //devil�� fish ���� �ݺ��ؼ� Ȯ��
					System.out.print("sex");	//������ �ݰ��ٰ� ����
				}
	
			}
			
			for(int i=0; i< feed.length; i++) { //feed�� fish ���� �ݺ��ؼ� Ȯ��
				if(checkMeet(fish,feed[i])){	//������ �ݰ��ٰ� �轺
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
		
		

		for(int i = 0; i < feed.length; i++) {		//feed[]������ŭ feed �߰�
			feed[i] = new JLabel(icons[6]);
			playground.add(feed[i]);
			Random r = new Random();
			feed[i].setBounds(r.nextInt(300), r.nextInt(300), icons[6].getIconWidth(), icons[6].getIconHeight());
		}
	

		for(int i = 0; i < devil.length; i++) {		//devil[]������ŭ devil �߰�
			devil[i] = new JLabel(icons[7]);
			playground.add(devil[i]);
			Random r = new Random();
			devil[i].setBounds(r.nextInt(300), r.nextInt(300), icons[7].getIconWidth(), icons[7].getIconHeight());
		}
		
		new Thread(this).start(); // ��� JLabel add �� Thread ����
		
		getContentPane().add(BorderLayout.CENTER, playground);
		setBounds(50, 50, 400, 400);
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		playground.setFocusable(true);  //playground�� ��Ŀ��
		playground.addKeyListener(new KeyListener() {   //keyListener �߰�
			public void keyPressed(KeyEvent e) {
				Point present = fish.getLocation();		//keyPressed ������ fish��ġ
				
				if(e.getKeyCode()==KeyEvent.VK_UP) {	//pressed Key ���� ��ġ ����
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
				fish.setLocation(present);				//����� fish ��ġ ����
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
	
	public double getDistance(Point a, Point b) {		//����ǥ�� �Ÿ� ��� �Լ�
		double d = Math.sqrt(Math.pow(a.x - b.x,2)+Math.pow(a.y - b.y,2));
		return d;
	}
	
	public boolean checkMeet(JLabel fish, JLabel some) {	//�� JLabel�� ��Ҵ��� �Ǵ� �Լ�
		Point fishPoint = fish.getLocation();
		Point somePoint = some.getLocation();
		double distance = getDistance(fishPoint, somePoint);
		
		int limit = (fish.getWidth()+fish.getHeight())/2;	//���μ��� ������� ��°Ÿ� ����
		if(distance < limit-20) {		//�ʹ� �ָ����� ���� �����̶� 20 ���̳ʽ�
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