package team;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;




class Review extends Frame implements ActionListener, WindowListener {
	private Label list_lb = new Label("수강평");
	private Label read_lb = new Label("내용");
	private List list_li = new List(10, false);
	private TextArea read_ta = new TextArea();
	private Button write_bt = new Button("수강평쓰기");
	private Button edit_bt = new Button("수정");
	private Button delete_bt = new Button("삭제");

	private JFrame write_dlg = new JFrame("글쓰기");
	private Label dlg_toptitle_lb = new Label("글쓰"
			+ "기", Label.CENTER);
	private Label dlg_title_lb = new Label("과목명 : ", Label.RIGHT);
	private Label dlg_professor_lb = new Label("교수님 : ", Label.RIGHT);
	private Label dlg_star_lb = new Label("별점(1-5) : ", Label.RIGHT);
	JTextPane dlg_title_tf = new JTextPane();
	JTextPane dlg_author_tf = new JTextPane();
	JTextPane dlg_star_tf = new JTextPane();
	
	JTextPane  dlg_write_ta = new JTextPane();
	private Button dlg_register_bt = new Button("등록");
	private Button dlg_cancel_bt = new Button("취소");
	
	
	public Review() {
		//메인프레임
		super("수강평");
		this.init();
		this.start();
		this.setSize(500, 500);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		int xpos = (int) (screen.getWidth() / 2 - frm.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - frm.getHeight() / 2);
		this.setLocation(xpos, ypos);
		this.setVisible(true);
	}

	public void init_dlg() {
		//글쓰기 프레임 설정
		write_dlg.setLayout(new BorderLayout(10, 10));
		write_dlg.add("North", dlg_toptitle_lb);
		Panel p = new Panel(new BorderLayout(5, 5));
		Panel p2 = new Panel(new BorderLayout(3, 3));
		Panel p_incontent = new Panel(new GridLayout(3, 1, 5, 5));
		p_incontent.add(dlg_title_lb);
		p_incontent.add(dlg_professor_lb);
		p_incontent.add(dlg_star_lb);
		p2.add("West", p_incontent);
		Panel p_content = new Panel(new GridLayout(3, 1, 5, 5));
		
		p_content.add(dlg_title_tf);
		p_content.add(dlg_author_tf);
		p_content.add(dlg_star_tf);
		Panel p5 = new Panel(new BorderLayout());
		p5.add("Center", dlg_star_tf);
		p_content.add(p5);
		p2.add("Center", p_content);
		p.add("North", p2);
		p.add("Center", dlg_write_ta);
		write_dlg.add("Center", p);
		Panel p_regorcan = new Panel(new FlowLayout(FlowLayout.RIGHT));
		p_regorcan.add(dlg_register_bt);
		p_regorcan.add(dlg_cancel_bt);
		write_dlg.add("South", p_regorcan);
	}
	
	public void init() {
		this.init_dlg();
		this.setLayout(new GridLayout(1, 2, 10, 10));
		Panel p = new Panel(new BorderLayout(5, 5));
		p.add("North", list_lb);
		p.add("Center", list_li);
		Panel p2 = new Panel(new GridLayout(1, 2, 5, 5));
		p2.add(write_bt);
		p.add("South", p2);
		this.add(p);
		Panel p1 = new Panel(new BorderLayout(5, 5));
		p1.add("North", read_lb);
		read_ta.setEditable(false);
		p1.add("Center", read_ta);
		Panel p3 = new Panel(new GridLayout(1, 3, 5, 5));
		p3.add(edit_bt);
		p3.add(delete_bt);
		p1.add("South", p3);
		this.add(p1);
	}
	
	public void start() {
		write_bt.addActionListener(this);
		dlg_cancel_bt.addActionListener(this);
		dlg_register_bt.addActionListener(this);
		this.addWindowListener(this);
		list_li.addActionListener(this); // 글읽기.
		edit_bt.addActionListener(this);
		delete_bt.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == write_bt) {
			//수강평쓰기 버튼 눌렀을 시 새로운 창 생성
			write_dlg.setSize(300, 300);
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			Dimension dlg = write_dlg.getSize();
			int xpos = (int) (screen.getWidth() / 2 - dlg.getWidth() / 2);
			int ypos = (int) (screen.getHeight() / 2 - dlg.getHeight() / 2);
			write_dlg.setLocation(xpos, ypos);
			write_dlg.setVisible(true);
			
		} else if (e.getSource() == dlg_cancel_bt) {
			//수강평쓰기 취소시
			dlg_title_tf.setText("");
			dlg_author_tf.setText("");
			dlg_star_tf.setText("");
			dlg_write_ta.setText("");
			write_dlg.setVisible(false);
			
		} else if (e.getSource() == dlg_register_bt) {
			//수강평 등록 시 새로운 파일로 저장해서 로그아웃해도 유지되도록함
			
			String title = dlg_title_tf.getText();
			String author = dlg_author_tf.getText();
			String star = dlg_star_tf.getText();
			
			if (title == null
				|| author == null
				|| title.trim().length() == 0
				|| author.trim().length() == 0
				|| star.trim().length() == 0) {
				return;
			}
			title = title.trim();
			author = author.trim();
			star = star.trim();
			
			File dir = new File("boardData");//글 등록시 새로운 파일로써 저장
			if (!dir.exists()) {
				dir.mkdir();
			}
			File file = new File(dir, title + " (" + author + ")");//파일 이름을 '글제목(교수님)'으로 저장
			try {
				PrintWriter out =
					new PrintWriter(new BufferedWriter(new FileWriter(file)));
				out.println(dlg_star_tf.getText().trim());
				out.println(dlg_write_ta.getText().trim());//dlg_write_ta 부분에 내용 표시
				out.close();
			} catch (IOException ee) {
			}
			dlg_title_tf.setText("");
			dlg_author_tf.setText("");
			dlg_star_tf.setText("");
			dlg_write_ta.setText("");
			write_dlg.setVisible(false);
		} else if (e.getSource() == list_li) {
			String data = list_li.getSelectedItem();//글 리스트에서 글 선택
			data = data.substring(data.indexOf(":") + 1).trim(); // ":"이후로 자르고,공백제거해 저장
			File dir = new File("boardData");
			File file = new File(dir, data);
			String attach = "";
			String contents = "";
			try {
				BufferedReader in = new BufferedReader(new FileReader(file)); //저장한 파일에서 읽기
				attach = in.readLine();//한줄씩 불러오기
				while (true) {
					String s = in.readLine();
					if (s == null)
						break;
					contents += "\n" + s;
				}
				in.close();
			} catch (IOException ee) {
			}
			//저장된 내용들을 보드판에 출력
			read_ta.setText("");
			read_ta.append(
				"과목명 : " + data.substring(0, data.indexOf("(")) + "\n\n");
			read_ta.append(
				"교수님 : "
					+ data.substring(
						data.indexOf("(") + 1,
						data.lastIndexOf(")"))
					+ "\n\n");
			read_ta.append("별점 : " + attach + "\n\n");
			read_ta.append("내용 : " + contents);
			
		} else if (e.getSource() == edit_bt) {
			//수정 버튼
			if (edit_bt.getLabel().equals("수정")) {
				edit_bt.setLabel("완료");
				read_ta.setEditable(true);
			} else {
				//저장하고
				String data = read_ta.getText().trim();
				String title =
					data.substring(
						data.indexOf("과목명 : ") + 5,
						data.indexOf("교수님 : "));
				String author =
					data.substring(
						data.indexOf("작성자 : ") + 5,
						data.indexOf("별점 : "));
				String attach =
					data.substring(
						data.indexOf("별점 : ") + 4,
						data.indexOf("내용 : "));
				String contents = data.substring(data.indexOf("내용 : ") + 4);
				File dir = new File("boardData"); //변경된 내용 새롭게 저장
				File file =
					new File(dir, title.trim() + " (" + author.trim() + ")");
				try {
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
					out.println(attach);
					out.println(contents);
					out.close();
				} catch (IOException ee) {
				}
				edit_bt.setLabel("수정");
				read_ta.setEditable(false);
			}
			
		} else if (e.getSource() == delete_bt) {
			//삭제버튼
			String data = read_ta.getText().trim();
			String title =
				data.substring(
					data.indexOf("과목명 : ") + 5,
					data.indexOf("교수님 : "));
			String author =
				data.substring(
					data.indexOf("교수님 : ") + 5,
					data.indexOf("별점 : "));

			File dir = new File("boardData");
			File file =
				new File(dir, title.trim() + " (" + author.trim() + ")");
			file.delete();//파일 삭제
			read_ta.setText("");//보드부분 글자 삭제
			String[] list = dir.list();
			list_li.clear();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					list_li.add(i + 1 + " : " + list[i]);
				}
			}
		}
	}
	
	public void windowClosing(WindowEvent e) {//윈도우 x눌렀을때
		if (e.getSource() == this) {
			 e.getWindow().setVisible(false);
             e.getWindow().dispose();
		}
	}
	public void windowClosed(WindowEvent e) {
	}
	public void windowOpened(WindowEvent e) {
	}
	public void windowActivated(WindowEvent e) {
		if (e.getSource() == this) {
			File dir = new File("boardData");
			String[] list = dir.list();
			list_li.clear();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					list_li.add(i + 1 + " : " + list[i]);
				}
			}
		}
	}
	public void windowDeactivated(WindowEvent e) {
	}
	public void windowIconified(WindowEvent e) {
	}
	public void windowDeiconified(WindowEvent e) {
	}
	
	private void setDefaultCloseOperation(int disposeOnClose) {
		// TODO Auto-generated method stub
		
	}
}