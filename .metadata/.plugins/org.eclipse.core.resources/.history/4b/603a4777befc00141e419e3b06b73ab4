package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyWindow {

	private JFrame frame;
	private JTextField textField;
	private int index =0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
		//	public void run() {
				try {
					MyWindow window = new MyWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			//}
		//});
	}

	/**
	 * Create the application.
	 */
	public MyWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				textField.setEditable(false);
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(textField.getText() + " " + index);
				index++;
			}
		});
		frame.getContentPane().add(btnNewButton, BorderLayout.WEST);
		
		textField = new JTextField();
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
	}

}
