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
	JPanel playground = new JPanel(); // run���� ��� �� playground ����
	JLabel[] feed = new JLabel[5]; // run���� ��� �� feed ����
	JLabel[] devil = new JLabel[5]; // feed, devil �̸� �迭�� ����
	JLabel score1 = new JLabel("0"); // Label�� �߰��� score ����
	JLabel score2 = new JLabel("1"); // feed�� ���� �� ���� label�� �ִ� ���� ����
	JLabel score3 = new JLabel("2");
	JLabel score4 = new JLabel("3");
	JLabel score5 = new JLabel("4");
	JLabel score6 = new JLabel("5");
	int resetscore = 0; // score ���� �񱳸� ���� �� ����
	
	Random r = new Random(); // �����Լ� �̸� ����

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

		 for(int i = 0; i < feed.length; i++) {  // feed.length��ŭ playground�� feed[i] �߰�
	         feed[i] = new JLabel(icons[6]);
	         playground.add(feed[i]);
	         Random r = new Random(); 
	         feed[i].setBounds(r.nextInt(300), r.nextInt(300), icons[6].getIconWidth(), icons[6].getIconHeight());
	      }
	   
	      for(int i = 0; i < devil.length; i++) {  // devil.length��ŭ playground�� feed[i] �߰�
	         devil[i] = new JLabel(icons[7]);
	         playground.add(devil[i]);
	         Random r = new Random();
	         devil[i].setBounds(r.nextInt(300)+10, r.nextInt(300), icons[7].getIconWidth(), icons[7].getIconHeight());
	      }
	      
	      new Thread(this).start(); // Thread ����
	      
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
		
		playground.addKeyListener(new KeyListener() { // Challenge#1 KeyEventListener �߰�
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) { 
					Point present = fish.getLocation(); //fish ���� ��ġ �Է¹���.
					present.y = (present.y - 10 <= 0) ? 0 : present.y - 10; //�ö󰥶����� 10�� �̵�
					fish.setLocation(present); //fish ���� ��ġ ��ȯ.
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
		
		score1.setForeground(Color.red); // score ������ ����
		score1.setBounds(350, 0, 10,10); // score �� ��ġ ����
		playground.add(score1); // score playground�� ���̱�
		
	}

	@Override
	public void run() {
		int devilspeed_x[] = {5,6,7,8,9}; //5���� �� devil ���� �ӵ� ����
		
		while (true) {
			playground.setFocusable(true); // playground�� ��Ŀ��, FishGame ������ �ȿ��� Ű���� �Է��� �ɶ��� �ְ� �ȵɶ��� �־ run()�ȿ� ����.
			for (int i = 0; i < devil.length; i++) {
				//Challenge #3 �������� devil �߰�
				int devil_x = devil[i].getX();
				int devil_y = devil[i].getY();
				//Challenge #4 ȭ���迡 �����ϸ� �ݴ�������� �̵�
				if (devil_x <= 0 || devil_x >= playground.getWidth()-30) { // ��� ������ ������ ������ �ٲ���, 
					//devil�� ������ ������ �����⿡ 30�� ���ָ鼭 ������ �������� ������ ��
					
					devilspeed_x[i] = - devilspeed_x[i]; // ���� ��ȯ
				}
				//Challenge #4 �������� ���� ũ�⸸ŭ �̵���
				devil[i].setLocation(devil[i].getX() + devilspeed_x[i], devil[i].getY()); // �� devil ���� x�������� ������ ��Ÿ����

				Point fish_location = fish.getLocation(); // fish, devil ��ġ ����
				Point devil_location = devil[i].getLocation();
				//�Ÿ� ��� ��Ʈ((x1-x2)^2+(y1-y2)^2) ����
				double distance = Math.sqrt(Math.pow(fish_location.x - devil_location.x, 2) + Math.pow(fish_location.y - devil_location.y, 2));
				boolean meeting;
				
				if (distance < 15) { // ����� �Ÿ� 15 �����̸� ��°ɷ� ���� �Ÿ��� 15���� ������ �浹�� ����
					meeting = true;
					
				} else {
					meeting = false;
				}
				//Challenge #2 Fish�� Devil�� ���� ��� ���α׷� ����
				if (meeting) { //boolean meeting = true �϶� Ȯ��
					System.out.print("GAME_OVER");
					System.exit(0); // ���α׷� ����
				}

			}

			for (int i = 0; i < feed.length; i++) { 
				//Challenge #3 �������� feed �߰�
				Point feed_location = feed[i].getLocation();
				Point fish_location = fish.getLocation();
				//�Ÿ� ��� ��Ʈ((x1-x2)^2+(y1-y2)^2) ����
				double distance = Math.sqrt(Math.pow(fish_location.x - feed_location.x, 2) + Math.pow(fish_location.y - feed_location.y, 2));
				boolean meeting;
				
				if (distance < 15) { // ����� �Ÿ� 15 �����̸� ��°ɷ� ���� �Ÿ��� 15���� ������ �浹�� ����
					meeting = true;
					
				} else {
					meeting = false;
				}
				//Challenge #2 Fish�� Feed�� ���� ��� ȭ�� ������ ������ ����
				if (meeting) {  
					feed[i].setLocation(30000, 30000); // ������ ȭ�� ������ ������
					playground.remove(feed[i]); // ȭ�鿡�� Feed ����
					
					resetscore+=1;
					//Challenge #2 Fish�� Feed�� ���� ��� score 1�� �÷���.
					if(resetscore==1) {
					score1.setLocation(30000, 30000); //ȭ�� ������ ������ ����
					score2.setForeground(Color.red); // score ������ �۾��� ��Ÿ��
					score2.setBounds(350, 0, 10,10); // score ��ġ ��Ű��
					playground.add(score2); // ���� score �ٿ��ֱ�
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
				Thread.sleep(100); // thread 100�и��� �ߴ�
			} catch (Exception e) { 
			}
		}
	}
	
	public static void main(String[] args) {
		new FishGame("Move Fish!");

	}
}