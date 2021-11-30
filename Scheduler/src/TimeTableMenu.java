package team;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class TimeTableMenu extends JFrame{
	public TimeTableMenu() {
		setSize(150, 300);
        setTitle("시간표 선택");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        
        JPanel panel=new JPanel();
        JButton bt1=new JButton("시간표1");
        JButton bt2=new JButton("시간표2");
        JButton bt3=new JButton("시간표3");
        JButton bt4=new JButton("시간표4");
        JButton bt5=new JButton("시간표5");
        
        bt1.setSize(100,50);
        bt2.setSize(100,50);
        bt3.setSize(100,50);
        bt4.setSize(100,50);
        bt5.setSize(100,50);
        
        panel.add(bt1);
        panel.add(bt2);
        panel.add(bt3);
        panel.add(bt4);
        panel.add(bt5);
        
        add(panel);
        panel.setVisible(true);
        
        bt1.addActionListener(event->{
        	try {
				new Thread(new TimeTable("Timetable1.txt")).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        bt2.addActionListener(event->{
        	try {
				new Thread(new TimeTable("Timetable2.txt")).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        bt3.addActionListener(event->{
        	try {
				new Thread(new TimeTable("Timetable3.txt")).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        bt4.addActionListener(event->{
        	try {
				new Thread(new TimeTable("Timetable4.txt")).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        bt5.addActionListener(event->{
        	try {
				new Thread(new TimeTable("Timetable5.txt")).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
	}
}
