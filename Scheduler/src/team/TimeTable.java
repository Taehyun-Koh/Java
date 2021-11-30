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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;

public class TimeTable extends JFrame implements Runnable{
	Color random_colors[]= {Color.BLUE , Color.YELLOW, Color.CYAN, Color.GREEN , Color.red, Color.ORANGE, Color.pink};
	String filename;
	Vector<JPanel[]> v;
	JPanel background1;
	String my_class[][];

	public TimeTable(String _filename) throws IOException {
		filename=_filename;
		
		v=new Vector<JPanel[]>();
		
		setSize(450, 700);
        setTitle("내 시간표");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        getContentPane().setLayout(null);
        
        background1=new JPanel();
        background1.setBackground(Color.white);
        add(background1);
        background1.setBounds(20,20,401,551);
        background1.setVisible(true);
        background1.setLayout(null);
        
        JPanel bound1=new JPanel();
    	bound1.setBackground(Color.BLACK);
    	add(bound1);
    	bound1.setBounds(0,0,1,550);
    	bound1.setVisible(true);
    	background1.add(bound1);
    	
    	JPanel bound2=new JPanel();
    	bound2.setBackground(Color.BLACK);
    	add(bound2);
    	bound2.setBounds(0,0,400,1);
    	bound2.setVisible(true);
    	background1.add(bound2);
    	
        for(int i=0;i<6;i++) {
        	JPanel line=new JPanel();
        	line.setBackground(Color.GRAY);
        	add(line);
        	line.setBounds(50+70*i,0,1,550);
        	line.setVisible(true);
        	background1.add(line);
        }//세로줄 추가
        for(int i=0;i<14;i++) {
        	JPanel line=new JPanel();
    		line.setBackground(Color.GRAY);
    		add(line);
    		line.setBounds(0,30+40*i,400,1);
    		line.setVisible(true);
    		background1.add(line);
        }//가로줄 추가
        
        String string[]= {"Mon","Tue","Wed","Thu","Fri"};
        for(int i=0;i<5;i++) {
        	JLabel label=new JLabel(string[i]);
        	label.setBounds(50+70*i,0,70,30);
        	add(label);
        	label.setHorizontalAlignment(JLabel.CENTER);
        	background1.add(label);
        }
        
        for(int i=0;i<13;i++) {
        	JLabel time=new JLabel(Integer.toString((i+8)%12+1));
        	time.setBounds(0,30+40*i,45,40);
        	add(time);
        	time.setHorizontalAlignment(JLabel.RIGHT);
        	time.setVerticalAlignment(JLabel.TOP);
        	background1.add(time);
        }
        
        JButton addclass=new JButton("과목 추가하기");
        add(addclass);
        addclass.setBounds(70,590,150,40);
        JButton removeclass=new JButton("과목 제거하기");
        add(removeclass);
        removeclass.setBounds(230,590,150,40);
        
        my_class=make_array(filename);
		for(int i=0;i<my_class.length;i++) {
			String select_class=my_class[i][0];
			String class_num=my_class[i][i];
        	String[] select_time=my_class[i][4].split("/");
        
        	add_class(select_class,select_time,true);
		}
        
        String col_name[]= {"과목명","학수번호-분반","교수","학점","시간"};
        String menu[][]=make_array("All_classes.txt");
        
        DefaultTableModel model=new DefaultTableModel(menu,col_name);
        JTable table= new JTable(model);
        JScrollPane sp1 = new JScrollPane(table);
        table.getColumn("학수번호-분반").setPreferredWidth(40);
        table.getColumn("시간").setPreferredWidth(130);
        table.getColumn("학점").setPreferredWidth(10);
        table.getColumn("교수").setPreferredWidth(30);
        getContentPane().add(sp1);
        sp1.setBounds(450,0,600,400);
        sp1.setVisible(false);
        
        JButton bt1=new JButton("추가하기");
        getContentPane().add(bt1);
        bt1.setBounds(600,450,300,50);
        bt1.setVisible(false);
        
        DefaultTableModel model2=new DefaultTableModel(col_name,0);
        JTable table2= new JTable(model2);
        JScrollPane sp2 = new JScrollPane(table2);
        table2.getColumn("학수번호-분반").setPreferredWidth(40);
        table2.getColumn("시간").setPreferredWidth(130);
        table2.getColumn("학점").setPreferredWidth(10);
        table2.getColumn("교수").setPreferredWidth(30);
        getContentPane().add(sp2);
        sp2.setBounds(450,0,600,400);
        sp2.setVisible(false);
        
        JButton bt2=new JButton("제거하기");
        getContentPane().add(bt2);
        bt2.setBounds(600,450,300,50);
        bt2.setVisible(false);
        
        addclass.addActionListener(event -> {
        	setSize(1100,700);
        	setLocationRelativeTo(null);
        	sp1.setVisible(true);
        	bt1.setVisible(true);
        });
        bt1.addActionListener(event -> {
            setSize(450,700);
            setLocationRelativeTo(null);
            
            int a=table.getSelectedRow();
            if(a!=-1) {
            	String select_class=(String) table.getValueAt(a,0);
            	String[] select_time=((String) table.getValueAt(a,4)).split("/");
            	String[] all_info=new String[5];
            	for(int i=0;i<5;i++) {
            		all_info[i]=(String) table.getValueAt(a,i);
            	}
            
            	add_class(select_class,select_time,false);
            	try {
					add_infile(filename,all_info);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
            sp1.setVisible(false);
        	bt1.setVisible(false);
        });
        removeclass.addActionListener(event -> {
        	setSize(1100,700);
        	setLocationRelativeTo(null);
        	
        	try {
				my_class=make_array(filename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	for(int i=0;i<my_class.length;i++) {
        		DefaultTableModel tm = (DefaultTableModel)table2.getModel();
        		tm.addRow(my_class[i]);
        	}
        	
        	sp2.setVisible(true);
        	bt2.setVisible(true);
        });
        bt2.addActionListener(event -> {
            setSize(450,700);
            setLocationRelativeTo(null);
            
            int a=table2.getSelectedRow();
            if(a!=-1) {
            	for(int i=0;i<v.get(a).length;i++) {
            		background1.remove(v.get(a)[i]);
            	}
            	v.remove(a);
            	background1.updateUI();
            }
            try {
				remove_infile(filename,a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            DefaultTableModel reset_model=(DefaultTableModel) table2.getModel();
        	reset_model.setNumRows(0);
            
        	sp2.setVisible(false);
        	bt2.setVisible(false);
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
        	
        	ret[1]=(Integer.valueOf(tmp2[0])-9)*40+Integer.valueOf(tmp2[1])*2/3;
        	ret[2]=(Integer.valueOf(tmp[0])-9)*40+Integer.valueOf(tmp[1])*2/3;
        	ret[2]-=ret[1];
        	       		
        	return ret;
        }
        
        String [][] make_array(String _filename) throws IOException{
        	Vector<String[]> to_ret=new Vector<String[]> ();
        	
        	File file=new File(_filename);
        	
        	FileWriter output = new FileWriter(file, true) ;
        	output.close();
        	
        	FileInputStream input=new FileInputStream(file);
        	try (BufferedReader infile = new BufferedReader(new InputStreamReader(input,"UTF-8"))) {
				String line=null;
				while((line=infile.readLine())!=null) {
					to_ret.add(parse(line));
				}
			}
           	String ret[][] = new String[to_ret.size()][];
           	to_ret.copyInto(ret);
           	
           	input.close();
        	
        	return ret;
        }
        void add_class(String _classname,String _select_time[],boolean _withfile) {
        	Color col=random_colors[(int)(Math.random()*10000)%7];
        	JPanel panels[]=new JPanel[_select_time.length];
            
            for(int i=0;i<_select_time.length;i++) {
            	int tmp[]=time2int(_select_time[i]);
            	
            	JPanel panel_class=new JPanel();
            	JLabel label_class=new JLabel(_classname); 
            	panel_class.setLayout(new GridBagLayout());
            	label_class.setHorizontalAlignment(JLabel.CENTER);
            	label_class.setVerticalAlignment(SwingConstants.CENTER);
            	panel_class.add(label_class);
            	background1.add(panel_class);
            	panel_class.setBounds(51+70*tmp[0],31+tmp[1],69,tmp[2]);
            	panel_class.setBackground(col);
            	panel_class.setVisible(true);
            	
            	panels[i]=panel_class;
            }
            v.add(panels);
        }
        String[] parse(String _format) {//_format ex) JAVA프로그래밍실습 SWE2023-41 이준원 2 / 1800 2145 mon
        	String tmp[]=_format.split("/");
			String s="";
			String time[];
			for(int i=1;i<tmp.length;i++) {
				time=tmp[i].split(" ");
				s+=time[3]+" "+time[1].substring(0,time[1].length()-2)+":"+time[1].substring(time[1].length()-2)+"-"+time[2].substring(0,time[2].length()-2)+":"+time[2].substring(time[2].length()-2);
				if(i!=tmp.length-1) s+="/";
			}
			tmp=tmp[0].split(" ");
			String ret[]= {"","","","",""};
			for(int i=0;i<4;i++) ret[i]=tmp[i];
			ret[4]=s;
			
			return ret;
        }
        void remove_infile(String _filename,int a) throws IOException {
        	String line;
            File file = new File(filename);
            File temp = new File("tmp.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp),"UTF-8"));
            temp.createNewFile();
            int count=0;
            while((line = reader.readLine()) != null) {
            	if(count!=a) {
            		writer.write(line + System.getProperty("line.separator"));
            	}
            	count++;
            }
            reader.close(); 
            writer.close(); 
            file.delete();
            temp.renameTo(file);
        }
        void add_infile(String _filename,String _all_info[]) throws IOException{
        	File file = new File(filename);
        	String line;
        	
        	line=_all_info[0]+" "+_all_info[1]+" "+_all_info[2]+" "+_all_info[3]+" / ";
        	String time[]=_all_info[4].split("/");
        	for(int i=0;i<time.length;i++) {
        		String tmp[]=time[i].split(" |:|-");
        		line+=tmp[1]+tmp[2]+" "+tmp[3]+tmp[4]+" "+tmp[0];
        		if(i!=time.length-1) line+=" / ";
        		else line+="\n";
        	}
        	try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8"))) {
				writer.write(line);
				writer.close();
			}
        }
        
        @Override
        public void run() {
        	
        }

}