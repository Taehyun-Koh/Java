package team;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import java.awt.Choice;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class Grade extends JFrame {

	public Grade() {
		setSize(400, 500);
        setTitle("학점계산기");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        getContentPane().setLayout(null);
        
        Choice ch=new Choice();
        ch.addItem("시간표 1");
        ch.addItem("시간표 2");
        ch.addItem("시간표 3");
        ch.addItem("시간표 4");
        ch.addItem("시간표 5");
        add(ch);
        ch.setBounds(30,40,200,30);
        ch.setVisible(true);
        
        JButton addcredit=new JButton("학점 입력");
        addcredit.setBounds(250,30,100,40);
        add(addcredit);
        
        JButton calc=new JButton("계산하기");
        calc.setBounds(250,360,100,40);
        add(calc);
        
        JLabel string1=new JLabel("평균 학점  : --/4.5");
        string1.setHorizontalAlignment(JLabel.CENTER);
        string1.setBounds(30,300,200,80);
        
        JPanel background=new JPanel();
        background.setBackground(Color.white);
        background.setLayout(new GridBagLayout());
        add(background);
        background.setBounds(30,340,200,80);
        background.setVisible(true);
        
        background.add(string1);
        
        JPanel background2=new JPanel();
        background2.setBackground(Color.black);
        add(background2);
        background2.setBounds(29,339,202,82);
        background2.setVisible(true);
        
        String col_name[]= {"과목명","학수번호-분반","학점","취득학점"};
        
        DefaultTableModel model=new DefaultTableModel(col_name,0);
        JTable table= new JTable(model);
        JScrollPane sp1 = new JScrollPane(table);
        table.getColumn("학수번호-분반").setPreferredWidth(40);
        table.getColumn("학점").setPreferredWidth(10);
        table.getColumn("취득학점").setPreferredWidth(30);
        getContentPane().add(sp1);
        sp1.setBounds(20,100,350,200);
        sp1.setVisible(true);
        
        String type[]= {"A+","A","B+","B","C+","C","D+","D","F"};
        JComboBox<String> select_credit=new JComboBox<String>(type);
        TableCellEditor editor = new DefaultCellEditor(select_credit);
        
        addcredit.addActionListener(event -> {
        	String n=ch.getSelectedItem().split(" ")[1];
        	String filename="Timetable"+n+".txt";
        	
        	DefaultTableModel reset_model=(DefaultTableModel) table.getModel();
        	reset_model.setNumRows(0);
        	
        	String my_class[][];
			try {
				my_class = make_array(filename);
				table.setModel(new DefaultTableModel(my_class,col_name));
				table.getColumnModel().getColumn(3).setCellEditor(editor);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			string1.setText("평균 학점 : --/4.5");
        });
        calc.addActionListener(event -> {
        	int sum=0;
        	double average=0.0;
        	for(int i=0;i<table.getRowCount();i++) {
        		int a=Integer.parseInt((String) table.getValueAt(i,2));
        		double b=alp2num((String)table.getValueAt(i,3));
        		
        		sum+=a;
        		average+=(double)a*b;
        	}
        	average/=(double) sum;
            string1.setText("평균 학점 : "+String.format("%.2f", average)+"/4.5");
        });
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
				String tmp[]=new String[4];
				tmp[0]=line.split(" ")[0];
				tmp[1]=line.split(" ")[1];
				tmp[2]=line.split(" ")[3];
				tmp[3]="A+";
				to_ret.add(tmp);
			}
		}
       	String ret[][] = new String[to_ret.size()][];
       	to_ret.copyInto(ret);
       	
       	input.close();
    	
    	return ret;
    }
	
	double alp2num(String alp) {
		if(alp.equals("A+")) return 4.5;
		else if(alp.equals("A")) return 4.0;
		else if(alp.equals("B+")) return 3.5;
		else if(alp.equals("B")) return 3.0;
		else if(alp.equals("C+")) return 2.5;
		else if(alp.equals("C")) return 2.0;
		else if(alp.equals("D+")) return 1.5;
		else if(alp.equals("D")) return 1.0;
		else return 0.0;
	}
}