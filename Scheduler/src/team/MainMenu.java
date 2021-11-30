package team;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;

public class MainMenu extends JFrame{
	
		
    public MainMenu() {
    	
        setSize(270, 250);
        setTitle("�޴� ����");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setLayout(null);
        
        JButton btn2 = new JButton("������ ����");
        JButton btn3 = new JButton("���� ����");
        JButton btn4 = new JButton("�� �ð�ǥ");
        
        btn4.setBounds(30, 30, 200, 40);
        btn3.setBounds(30, 90, 200, 40);
        btn2.setBounds(30, 150, 200, 40);
        
        
        getContentPane().add(btn2);
        getContentPane().add(btn3);
        getContentPane().add(btn4);
        
        
        btn2.addActionListener(event -> {
        	new Review();
          
        });
        
        btn3.addActionListener(event -> {
            new Grade();
            
        });
        
        btn4.addActionListener(event -> {
        	new TimeTableMenu();
        });
        
    }
}
