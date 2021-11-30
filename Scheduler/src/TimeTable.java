package team;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;

public class TimeTable extends JFrame {
	Color random_colors[]= {Color.BLUE , Color.YELLOW, Color.CYAN, Color.GREEN , Color.red, Color.ORANGE, Color.pink};

	public TimeTable() {
		setSize(450, 700);
        setTitle("내 시간표");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        getContentPane().setLayout(null);
        
        
        JPanel bound1=new JPanel();
    	bound1.setBackground(Color.BLACK);
    	add(bound1);
    	bound1.setBounds(20,20,1,550);
    	bound1.setVisible(true);
    	
    	JPanel bound2=new JPanel();
    	bound2.setBackground(Color.BLACK);
    	add(bound2);
    	bound2.setBounds(20,20,400,1);
    	bound2.setVisible(true);
    	
        for(int i=0;i<6;i++) {
        	JPanel line=new JPanel();
        	line.setBackground(Color.GRAY);
        	add(line);
        	line.setBounds(70+70*i,20,1,550);
        	line.setVisible(true);
        }//세로줄 추가
        for(int i=0;i<14;i++) {
        	JPanel line=new JPanel();
    		line.setBackground(Color.GRAY);
    		add(line);
    		line.setBounds(20,50+40*i,400,1);
    		line.setVisible(true);
        }//가로줄 추가
        
        String string[]= {"Mon","Tue","Wed","Thu","Fri"};
        for(int i=0;i<5;i++) {
        	JLabel label=new JLabel(string[i]);
        	label.setBounds(70+70*i,20,70,30);
        	add(label);
        	label.setHorizontalAlignment(JLabel.CENTER);
        }
        
        for(int i=0;i<13;i++) {
        	JLabel time=new JLabel(Integer.toString((i+8)%12+1));
        	time.setBounds(20,50+40*i,45,40);
        	add(time);
        	time.setHorizontalAlignment(JLabel.RIGHT);
        	time.setVerticalAlignment(JLabel.TOP);
        }
        
        JPanel background1=new JPanel();
        background1.setBackground(Color.white);
        add(background1);
        background1.setBounds(20,20,400,550);
        background1.setVisible(true);
        background1.setLayout(null);
        
        JButton addclass=new JButton("과목 추가하기");
        add(addclass);
        addclass.setBounds(70,590,150,40);
        JButton removeclass=new JButton("과목 제거하기");
        add(removeclass);
        removeclass.setBounds(230,590,150,40);
        
        getContentPane().add(addclass);
        
        String col_name[]= {"과목명","학수번호-분반","교수","학점","시간"};
        String test[][]= {
        		{"Java프로그래밍실습","SWE2023-41","이준원","2","mon 18:00-21:45"},
        		{"이산수학", "GEDB007-44", "설한국", "3","mon 9:00-10:15/wed 10:30-11:45"},
        		{"시스템프로그램", "SWE2001-41", "최형기", "3","mon 10:30-11:45/wed 10:30-11:45"},
        		{"종교와유럽문화", "GEDH053-41" ,"박규현", "3" ,"tue 9:00-11:45"},
        		{"컴퓨터구조개론", "SWE3005-41", "서의성", "3", "tue 13:30-14:45/thu 12:00-13:15"},
        		{"알고리즘개론", "SWE2016-42", "허재필", "3", "mon 13:30-14:45/wed 12:00-13:15"},
        		{"시스템프로그래밍실습", "SWE2024-41", "남범석","2","wed 18:00-21:45"}
        };
        DefaultTableModel model=new DefaultTableModel(test,col_name);
        JTable table= new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        table.getColumn("학수번호-분반").setPreferredWidth(40);
        table.getColumn("시간").setPreferredWidth(130);
        table.getColumn("학점").setPreferredWidth(10);
        table.getColumn("교수").setPreferredWidth(30);
        getContentPane().add(sp);
        sp.setBounds(450,0,600,400);
        sp.setVisible(false);
        
        JButton bt1=new JButton("추가하기");
        getContentPane().add(bt1);
        bt1.setBounds(600,450,300,50);
        bt1.setVisible(false);
        
        addclass.addActionListener(event -> {
        	setSize(1100,700);
        	setLocationRelativeTo(null);
        	sp.setVisible(true);
        	bt1.setVisible(true);
        });
        bt1.addActionListener(event -> {
            setSize(450,700);
            setLocationRelativeTo(null);
            
            int a=table.getSelectedRow();
            String select_class=(String) table.getValueAt(a,0);
            String[] select_time=((String) table.getValueAt(a,4)).split("/");
            Color col=random_colors[(int)(Math.random()*10000)%7];
            
            for(int i=0;i<select_time.length;i++) {
            	int tmp[]=time2int(select_time[i]);
            	
            	System.out.printf("%d %d %d\n",tmp[0],tmp[1],tmp[2]);
            	
            	JPanel panel_class=new JPanel();
            	JLabel label_class=new JLabel(select_class);           	
            	panel_class.setLayout(new GridBagLayout());
            	label_class.setHorizontalAlignment(JLabel.CENTER);
            	label_class.setVerticalAlignment(SwingConstants.CENTER);
            	panel_class.add(label_class);
            	background1.add(panel_class);
            	panel_class.setBounds(51+70*tmp[0],31+tmp[1],69,tmp[2]);
            	panel_class.setBackground(col);
            	panel_class.setVisible(true);
            }
            
            sp.setVisible(false);
        	bt1.setVisible(false);
        });
        };
        
        int[] time2int(String str) {
        	int ret[]= {0,0,0};
        	
        	String tmp[]=str.split(" ");
        	
        	if(tmp[0].contentEquals("mon")) ret[0]=0;
        	else if(tmp[0].contentEquals("tue")) ret[0]=1;
        	else if(tmp[0].contentEquals("wed")) ret[0]=2;
        	else if(tmp[0].contentEquals("thu")) ret[0]=3;
        	else if(tmp[0].contentEquals("fri")) ret[0]=4;
        	
        	tmp=tmp[1].split("-");
        	String tmp2[]=tmp[0].split(":");
        	tmp=tmp[1].split(":");
        	
        	System.out.printf("%s %s %s %s\n",tmp2[0],tmp2[1],tmp[0],tmp[1]);
        	
        	ret[1]=(Integer.valueOf(tmp2[0])-9)*40+Integer.valueOf(tmp2[1])*2/3;
        	ret[2]=(Integer.valueOf(tmp[0])-9)*40+Integer.valueOf(tmp[1])*2/3;
        	ret[2]-=ret[1];
        	       		
        	return ret;
        };
}
