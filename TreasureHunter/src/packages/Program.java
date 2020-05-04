package packages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Program extends JFrame {

	private JPanel contentPane;
	private Color paleYellow = new Color(255, 255, 170);
	private JLabel[][] Field = new JLabel[12][12];
	private JTextField textFileName;
	private JLabel lblOK, lblNO;
	private File sourceFile;
	private int FieldInfo[][] = new int[12][12];
	private String direction = "";
	private int inX = 0, inY = 0;
	private JLabel lblNewLabel_2;
	private JTextField textSteps;
	private JButton btnRun;
	private Timer timer;
	private int Akpx = 0, Akpy = 0, Als = 0, Alv = 0;
	private boolean AnimEnd = false;
	private String AnimSteps = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Program() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 450, 300);
		setTitle(" Treasure Hunter");
		setSize(620, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(paleYellow);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnLoadingTrack = new JButton("Loading Track");
		btnLoadingTrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				int returnValue = jfc.showOpenDialog(Program.this);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					sourceFile = jfc.getSelectedFile();
					textFileName.setText(sourceFile.getAbsolutePath());

					boolean ok = checkFile();
					if (ok)
						lblOK.setVisible(true);
					else
						lblNO.setVisible(true);
				}
			}
		});
		btnLoadingTrack.setBounds(10, 614, 124, 23);
		contentPane.add(btnLoadingTrack);

		JLabel lblNewLabel = new JLabel("File Name:");
		lblNewLabel.setBounds(144, 618, 96, 14);
		contentPane.add(lblNewLabel);

		textFileName = new JTextField();
		textFileName.setBounds(219, 615, 292, 20);
		contentPane.add(textFileName);
		textFileName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Status:");
		lblNewLabel_1.setBounds(531, 618, 48, 14);
		contentPane.add(lblNewLabel_1);

		lblOK = new JLabel("");
		lblOK.setIcon(new ImageIcon("pict\\ok.png"));
		lblOK.setBounds(580, 620, 24, 24);
		contentPane.add(lblOK);
		lblOK.setVisible(false);

		lblNO = new JLabel("");
		lblNO.setIcon(new ImageIcon("pict\no.png"));
		lblNO.setBounds(580, 620, 24, 24);
		contentPane.add(lblNO);
		
		lblNewLabel_2 = new JLabel("Steps:");
		lblNewLabel_2.setBounds(10, 660, 65, 14);
		contentPane.add(lblNewLabel_2);
		
		textSteps = new JTextField();
		textSteps.setBounds(85, 657, 313, 20);
		contentPane.add(textSteps);
		textSteps.setColumns(10);
		ActionListener Animation = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				boolean v = true;
				if(!AnimEnd) {
					String step = AnimSteps.substring(Als,Als+1);
					if(step.equals("F")) {
						if(direction.equals("LR")) {FieldInfo[Akpx][Akpy]=5;Akpx++;v=Check(Akpx,Akpy);
						if(v) Field[Akpx][Akpy].setIcon(new ImageIcon("pict\\stepHor.png")); else Als = Alv;
						}
						if(direction.equals("RJ")) {FieldInfo[Akpx][Akpy]=5;Akpx--;v=Check(Akpx,Akpy);
						if(v) Field[Akpx][Akpy].setIcon(new ImageIcon("pict\\stepHor.png")); else Als = Alv;
						}
						if(direction.equals("TB")) {FieldInfo[Akpx][Akpy]=5;Akpy++;v=Check(Akpx,Akpy);
						if(v) Field[Akpx][Akpy].setIcon(new ImageIcon("pict\\stepHor.png")); else Als = Alv;
						}
						if(direction.equals("BT")) {FieldInfo[Akpx][Akpy]=5;Akpy--;v=Check(Akpx,Akpy);
						if(v) Field[Akpx][Akpy].setIcon(new ImageIcon("pict\\stepHor.png")); else Als =Alv;
						}
					}
					if(step.equals("R")) {
						switch(direction) {
						case "LR": direction ="TB"; Field[Akpx][Akpy].setIcon(new ImageIcon("pict\\stepLeftDown.png")); break;
						case "RJ": direction ="BT"; Field[Akpx][Akpy].setIcon(new ImageIcon("pict\\stepRightTop.png")); break;
						case "TB": direction ="RJ"; Field[Akpx][Akpy].setIcon(new ImageIcon("pict\\stepLeftTop.png")); break;
						case "BT": direction ="LR"; Field[Akpx][Akpy].setIcon(new ImageIcon("pict\\stepRightDown.png")); break;
						}
					}
					
					if(step.equals("L")) {
						switch(direction) {
						case "LR": direction ="TB"; Field[Akpx][Akpy].setIcon(new ImageIcon("pict\\stepLeftTop.png")); break;
						case "RJ": direction ="BT"; Field[Akpx][Akpy].setIcon(new ImageIcon("pict\\stepRightDown.png")); break;
						case "TB": direction ="RJ"; Field[Akpx][Akpy].setIcon(new ImageIcon("pict\\stepLeftTop.png")); break;
						case "BT": direction ="LR"; Field[Akpx][Akpy].setIcon(new ImageIcon("pict\\stepLeftDown.png")); break;
						}
					}
				
				if(FieldInfo[Akpx][Akpy]==2) Field[Akpx][Akpy].setIcon(new ImageIcon("pict\\treasureOK.png"));
				if(Als==Alv) AnimEnd=true;else Als++;
				}
			
			if(AnimEnd) {
				timer.stop();
			}
			}
			};
			

		
		btnRun = new JButton("Animation");
		btnRun.addActionListener(new ActionListener() {

	public void actionPerformed(ActionEvent e) {
				AnimSteps = textSteps.getText();
				Akpx=inX;
				Akpy=inY;
				AnimEnd = false;
				Als=0;
				Alv=AnimSteps.length()-1;
				timer.start();
			}
		});
		btnRun.setBounds(509, 656, 89, 23);
		contentPane.add(btnRun);
		
		btnRun = new JButton("New button");
		btnRun.setBounds(515, 656, 89, 23);
		timer = new Timer(1000,Animation);
		contentPane.add(btnRun = new JButton("Run"));
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String steps = textSteps.getText();
				int kpx = inX, kpy=inY;
				boolean end = false, v = true;
				String step="";
				int ls = 0, lv = steps.length()-1;
				while(!end) {
					step=steps.substring(ls,ls+1);
					if(step.equals("F")) {
						if(direction.equals("LR")) {FieldInfo[kpx][kpy]=5;kpx++;v=Check(kpx,kpy);
						if(v) Field[kpx][kpy].setIcon(new ImageIcon("pict\\stepHor.png")); else ls = lv;
						}
						if(direction.equals("RJ")) {FieldInfo[kpx][kpy]=5;kpx--;v=Check(kpx,kpy);
						if(v) Field[kpx][kpy].setIcon(new ImageIcon("pict\\stepHor.png")); else ls = lv;
						}
						if(direction.equals("TB")) {FieldInfo[kpx][kpy]=5;kpy++;v=Check(kpx,kpy);
						if(v) Field[kpx][kpy].setIcon(new ImageIcon("pict\\stepHor.png")); else ls = lv;
						}
						if(direction.equals("BT")) {FieldInfo[kpx][kpy]=5;kpy--;v=Check(kpx,kpy);
						if(v) Field[kpx][kpy].setIcon(new ImageIcon("pict\\stepHor.png")); else ls = lv;
						}
					}
					if(step.equals("R")) {
						switch(direction) {
						case "LR": direction ="TB"; Field[kpx][kpy].setIcon(new ImageIcon("pict\\stepLeftDown.png")); break;
						case "RJ": direction ="BT"; Field[kpx][kpy].setIcon(new ImageIcon("pict\\stepRightTop.png")); break;
						case "TB": direction ="RJ"; Field[kpx][kpy].setIcon(new ImageIcon("pict\\stepLeftTop.png")); break;
						case "BT": direction ="LR"; Field[kpx][kpy].setIcon(new ImageIcon("pict\\stepRightDown.png")); break;
						}
					}
					
					if(step.equals("L")) {
						switch(direction) {
						case "LR": direction ="TB"; Field[kpx][kpy].setIcon(new ImageIcon("pict\\stepLeftTop.png")); break;
						case "RJ": direction ="BT"; Field[kpx][kpy].setIcon(new ImageIcon("pict\\stepRightDown.png")); break;
						case "TB": direction ="RJ"; Field[kpx][kpy].setIcon(new ImageIcon("pict\\stepLeftTop.png")); break;
						case "BT": direction ="LR"; Field[kpx][kpy].setIcon(new ImageIcon("pict\\stepLeftDown.png")); break;
						}
					}
				}
				if(FieldInfo[kpx][kpy]==2) Field[kpx][kpy].setIcon(new ImageIcon("pict\\treasureOK.png"));
				if(ls==lv) end =true;else ls++;
			
			}
		});
		btnRun.setBounds(408, 656, 89, 23);
		contentPane.add(btnRun);
		lblNO.setVisible(false);
		
		boolean ok = checkFile();
		if(ok) lblOK.setVisible(true);
		else lblNO.setVisible(true);
		if(ok) trackDisplay();
	}

	public void instantiation() {
		for (int j = 0; j < 12; j++) {
			for (int i = 0; i < 12; i++)
				Field[i][j] = new JLabel();
		}
	}

	public void trackDrawer() {
		for (int i = 0; i < 12; i++) {
			if (i == 0)
				Field[i][0].setIcon(new ImageIcon("pict\\topLeftCorner.png"));
			else if (i == 11)
				Field[i][0].setIcon(new ImageIcon("pict\\topRightCorner.png"));
			else
				Field[i][0].setIcon(new ImageIcon("pict\\topLine.png"));
			contentPane.add(Field[i][0]);
			Field[i][0].setBounds(10 + i * 50, 10, 50, 50);
		}

		for (int j = 1; j < 11; j++) {
			for (int i = 0; i < 12; i++) {
				if (i == 0)
					Field[i][j].setIcon(new ImageIcon("pict\\rightLine.png"));
				if (i == 1)
					Field[i][j].setIcon(new ImageIcon("pict\\leftLine.png"));
				if (i > 0 && i < 11)
					Field[i][j].setIcon(new ImageIcon("pict\\trackBase.png"));
				contentPane.add(Field[i][j]);
				Field[i][j].setBounds(10 + i * 50, 10 + j * 50, 50, 50);
			}
		}

		for (int i = 0; i < 12; i++) {
			if (i == 0)
				Field[i][11].setIcon(new ImageIcon("pict\\bottomLeftCorner.png"));
			else if (i == 11)
				Field[i][11].setIcon(new ImageIcon("pict\\bottomRightCorner.png"));
			else
				Field[i][11].setIcon(new ImageIcon("pict\\bottomLine.png"));
			contentPane.add(Field[i][11]);
			Field[i][11].setBounds(10 + i * 50, 560, 50, 50);
		}
	}

	public static void SM(String msg, int type) {
		JOptionPane.showMessageDialog(null, msg, "Treasure Hunter Message", type);
	}

	public int goodInt(String s) {
		try {
			int x = Integer.parseInt(s);
			return x;
		} catch (NumberFormatException e) {
			return -5;
		}
	}

	public boolean checkFile() {
		boolean ok = true;
		String s = "", sxk = "", syk = "", stp = "";
		int index = 0, index2 = 0, index3 = 0, xk = 0, yk = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(sourceFile));
			s = in.readLine();
			while (ok && s != null) {
				index = s.indexOf("Entrance:");
				if (index != -1 && ok) {
					index2 = s.indexOf(",");
					index3 = s.indexOf(" ");
					sxk = s.substring(index3 + 1, index2);
					syk = s.substring(index2 + 1, s.length());
					xk = goodInt(sxk);
					yk = goodInt(syk);
					ok = (xk > -1 && xk < 12) ? true : false;
					ok = (ok && yk > -1 && yk < 12) ? true : false;
					ok = (ok && (xk == 0 || xk == 11) && (yk == 0 || yk == 11)) ? false : true;
					if (ok)
						FieldInfo[xk][yk] = 4;
					if (!ok)
						SM("The Entrance is incorrectly specified in the file!", 0);

				}
				if (ok) {

					index = s.indexOf("Exit:");
					if (index != -1) {
						index2 = s.indexOf(",");
						index3 = s.indexOf(" ");
						sxk = s.substring(index3 + 1, index2);
						syk = s.substring(index2 + 1, s.length());
						xk = goodInt(sxk);
						yk = goodInt(syk);
						ok = (xk > -1 && xk < 12) ? true : false;
						ok = (ok && yk > -1 && yk < 12) ? true : false;
						ok = (ok && (xk == 0 || xk == 11) && (yk == 0 || yk == 11)) ? false : true;
						if (ok)
							FieldInfo[xk][yk] = 3;
						if (!ok)
							SM("The Entrance is incorrectly specified in the file!", 0);
					}
				}

				if (ok) {
					index = s.indexOf("Treasure:");
					if (index != -1) {

						index2 = s.indexOf(",");
						index3 = s.indexOf(" ");
						sxk = s.substring(index3 + 1, index2);
						syk = s.substring(index2 + 1, s.length());
						xk = goodInt(sxk);
						yk = goodInt(syk);
						ok = (xk > 0 && xk < 11) ? true : false;
						ok = (ok && yk > 0 && yk < 11) ? true : false;

						if (ok)
							FieldInfo[xk][yk] = 2;
						if (!ok)
							SM("The Entrance is incorrectly specified in the file!", 0);
					}
				}
				if (ok) {
					index = s.indexOf("Traps:");
					if (index != -1) {
						index = s.indexOf(" ");
						stp = s.substring(index + 1, s.length());
						String[] pks = stp.split(" ");
						for (String sc : pks) {
							index2 = sc.indexOf(",");

							sxk = s.substring(0, index2);
							syk = s.substring(index2 + 1, sc.length());
							xk = goodInt(sxk);
							yk = goodInt(syk);
							ok = (xk > 0 && xk < 11) ? true : false;
							ok = (ok && yk > 0 && yk < 11) ? true : false;

							if (ok)
								FieldInfo[xk][yk] = 1;
						}
						if (!ok)
							SM("The Entrance is incorrectly specified in the file!", 0);
					}
				}
				s = in.readLine();
			}
			in.close();

		} catch (IOException ioe) {
			ok = false;
			SM("checkFile: " + ioe.getMessage(), 0);
		}
		return ok;
	}

	public void trackDisplay() {
		String s = "", sxk = "", syk = "", stp = "";
		int index = 0, index2 = 0, index3 = 0, xk = 0, yk = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(sourceFile));
			s = in.readLine();
			while (s != null) {
				index = s.indexOf("Entrance:");
				if (index != -1) {
					index2 = s.indexOf(",");
					index3 = s.indexOf(" ");
					sxk = s.substring(index3 + 1, index2);
					syk = s.substring(index2 + 1, s.length());
					xk = goodInt(sxk);
					yk = goodInt(syk);
					if (xk == 0) {
						Field[0][yk].setIcon(new ImageIcon("pict\\entranceLeft.png"));
						direction = "LR";
					}
					if (xk == 11) {
						Field[11][yk].setIcon(new ImageIcon("pict\\entranceRight.png"));
						direction = "RL";
					}
					if (yk == 0) {
						Field[xk][0].setIcon(new ImageIcon("pict\\entranceTop.png"));
						direction = "TB";
					}
					if (yk == 11) {
						Field[xk][11].setIcon(new ImageIcon("pict\\entranceBottom.png"));
						direction = "BT";
					}
					inX = xk;
					inY = yk;
				}
				index = s.indexOf("Exit: ");
				if (index != -1) {
					index2 = s.indexOf(",");
					index3 = s.indexOf(" ");
					sxk = s.substring(index3 + 1, index2);
					syk = s.substring(index2 + 1, s.length());
					xk = goodInt(sxk);
					yk = goodInt(syk);
					if (xk == 0) {
						Field[0][yk].setIcon(new ImageIcon("pict\\entranceRight.png"));
						direction = "LR";
					}
					if (xk == 11) {
						Field[11][yk].setIcon(new ImageIcon("pict\\entranceLeft.png"));
						direction = "RL";
					}
					if (yk == 0) {
						Field[xk][0].setIcon(new ImageIcon("pict\\entranceBottom.png"));
						direction = "TB";
					}
					if (yk == 11) {
						Field[xk][11].setIcon(new ImageIcon("pict\\entranceTop.png"));
						direction = "BT";
					}

				}
				index = s.indexOf("Treasure");
				if (index != -1) {
					index2 = s.indexOf(",");
					index3 = s.indexOf(" ");
					sxk = s.substring(index3 + 1, index2);
					syk = s.substring(index2 + 1, s.length());
					xk = goodInt(sxk);
					yk = goodInt(syk);
					Field[xk][yk].setIcon(new ImageIcon("pict\\treasure.png"));

				}
				index = s.indexOf("Traps:");

				if (index != -1) {
					index = s.indexOf(" ");
					stp = s.substring(index + 1, s.length());
					String[] pks = stp.split(" ");
					for (String sc : pks) {
						index2 = sc.indexOf(",");

						sxk = s.substring(0, index2);
						syk = s.substring(index2 + 1, sc.length());
						xk = goodInt(sxk);
						yk = goodInt(syk);
						Field[xk][yk].setIcon(new ImageIcon("pict\\trap.png"));

					}
				}
				s = in.readLine();

			}
			in.close();

		} catch (IOException ioe) {
			SM("readFile:" + ioe.getMessage(), 0);
		}
	}

	public boolean Check(int kpx, int kpy) {
		boolean v = true;
		if (FieldInfo[kpx][kpy] == 2) {
			SM("You got the treasure!", 2);
		}
		if (FieldInfo[kpx][kpy] == 5) {
			SM("You have crossed your own route!", 0);
			v = false;
		}
		if (FieldInfo[kpx][kpy] == 1) {
			SM("You got the treasure!", 0);
			v = false;
		}
		if (FieldInfo[kpx][kpy] == 3) {
			v = false;
		} else {
			if (kpx == 0 || kpx == 11) {
				SM("You hit the wall!", 0);
				v = false;
			}
			if (kpy == 0 || kpy == 11) {
				SM("You hit the wall!", 0);
				v = false;
			}

		}
		return v;
	}
}