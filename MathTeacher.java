import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Random;
/**
 * 
 * @author HTZ
 * @version 3.3
 */
public class MathTeacher extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel title;
	JLabel question;
	JButton verify;
	JButton exit;
	TextField answer = new TextField(5);
	int first;
	int second;
	int symbol;
	String sym;
	int correct=0;
	int wrong=0;
	JButton[] buttons=new JButton[9];
	JButton num0;
	JButton nums;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MathTeacher frame = new MathTeacher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public MathTeacher() {
		setTitle("Math Teacher");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		JPanel east = new JPanel();
		JPanel num=new JPanel();		//store the key from 1 to 9
		JPanel special=new JPanel();	//store the key '0' and '-'
		num.setLayout(new GridLayout(3,3));
		special.setLayout(new GridLayout(1,2));
		east.setLayout(new BorderLayout(0,0));
		Random rand = new Random();
		first=rand.nextInt(10)+1;		//the first number of the question
		second =rand.nextInt(10)+1;		//the second number of the question
		symbol=rand.nextInt(4)+1;		//the symbol number of the question
		if(symbol==1)
			sym="+";

		if(symbol==2)
			sym="-";

		if(symbol==3)
			sym="x";
		
		if(symbol==4){
			sym="/";

			while(true){
				if(first%second==0)
					break;
				first=rand.nextInt(10)+1;
				second =rand.nextInt(10)+1;
			}	//make sure the answer must be an integer
		}
		
		question = new JLabel("Question "+first+sym+second);
		question.setBounds(300,100,100,50);	
		question.setFont(new java.awt.Font("Dialog",1,15));
		title = new JLabel(correct+" correct out of "+(wrong+correct));
		title.setFont(new Font("Monospaced",Font.BOLD,20));
		answer.setBounds(20,100,200,50);
		answer.setFont(new java.awt.Font("Dialog",1,15));
		answer.addKeyListener(new KeyAdapter(){  
            public void keyTyped(KeyEvent e) {  

                int keyint = e.getKeyChar();
                char[] content=answer.getText().toCharArray();
                
                
                if(keyint >= KeyEvent.VK_0 && keyint <= KeyEvent.VK_9 ){
                	if(content.length==2&&content[0]!='1'){
                		e.consume();

                	}
                	else if(content.length==2&&content[0]=='1'&&content[1]!='0'){
                		e.consume();

                	}
                	else if(content.length==2&&content[0]=='1'&&content[1]=='0'&&keyint!=KeyEvent.VK_0){
                		e.consume();

                	}
                	else if(content.length==2&&content[0]=='-'){
                		e.consume();

                	}
                	else if(content.length>0&&content[0]=='0'&&keyint==KeyEvent.VK_0){
                		e.consume();

                	}
                	else if(content.length==3){
                		e.consume();

                	}
                	else{

                	}
                }
              
                	
                
                else if(keyint==KeyEvent.VK_BACK_SPACE){
                	
                }
                else if(keyint==KeyEvent.VK_MINUS&&answer.getText().length()==0){

                }
                else{
                	e.consume();
                }

            }  
        });		//make sure that the input must range from -9 to 100
		verify = new JButton("Press for answer");
		verify.setBounds(20,200,200,20);
		verify.setFont(new java.awt.Font("Dialog",1,15));
		exit = new JButton("Exit");
		exit.setBounds(0,0,10,15);
		contentPane.add(panel1,BorderLayout.CENTER);
		contentPane.add(title,BorderLayout.NORTH);
		contentPane.add(exit,BorderLayout.SOUTH);
		contentPane.add(east,BorderLayout.EAST);
		panel1.add(answer);
		panel1.add(verify);
		panel1.add(question);
		
		east.add(num,BorderLayout.CENTER);
		east.add(special,BorderLayout.SOUTH);
		
		for(int i = 0; i<9; i++){
			buttons[i] = new JButton(Integer.toString(i+1));
			num.add(buttons[i]);
			buttons[i].addActionListener(this);
		}	//the keyboard on GUI
		num0=new JButton("0");
		num0.addActionListener(this);
		special.add(num0);
		nums=new JButton("-");
		nums.addActionListener(this);
		special.add(nums);
		
		panel1.setVisible(true);
		setVisible(true);
		
		verify.addActionListener(this);
		exit.addActionListener(this);

		

		
	}
	/**
	 * 
	 * @param event button event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String con=answer.getText();

		if(event.getSource() == verify){
			boolean judge=false;
			int answer1;
			
			if(answer.getText().length()!=0){
				try{answer1=Integer.parseInt(answer.getText());
				judge=calculate(first,second,symbol,answer1);
				}
			catch(Exception e){}
			if(judge){
				correct++;		//the number of questions you have answered correctly
				answer.setText("");		//clear the input text
				JOptionPane.showMessageDialog(null,"GOOD JOB!!!","Information",JOptionPane.INFORMATION_MESSAGE);

				Random rand = new Random();
				first=rand.nextInt(10)+1;
				second =rand.nextInt(10)+1;
				symbol=rand.nextInt(4)+1;
				if(symbol==1)
					sym="+";
				if(symbol==2)
					sym="-";
				if(symbol==3)
					sym="x";
				if(symbol==4)
				{
					sym="/";
					while(true){
						if(first%second==0)
							break;
						first=rand.nextInt(10)+1;
						second =rand.nextInt(10)+1;
					}//make sure the answer must be an integer
				}

				question.setText("Question "+first+sym+second);
				title.setText(correct+" correct out of "+(wrong+correct));
				//new question
				repaint();
			}
			else{
				wrong++;		//the number of questions you have answered wrongly
				answer.setText("");		//clear the input text
				JOptionPane.showMessageDialog(null,"Wrong Answer!!!","Information",JOptionPane.INFORMATION_MESSAGE);
				title.setText(correct+" correct out of "+(wrong+correct));
				Random rand = new Random();
				first=rand.nextInt(10)+1;
				second =rand.nextInt(10)+1;
				symbol=rand.nextInt(4)+1;
				if(symbol==1)
					sym="+";
				if(symbol==2)
					sym="-";
				if(symbol==3)
					sym="x";
				if(symbol==4)
				{
					sym="/";
					while(true){
						if(first%second==0)
							break;
						first=rand.nextInt(10)+1;
						second =rand.nextInt(10)+1;
					}//make sure the answer must be an integer
				}
				question.setText("Question "+first+sym+second);
				//new question
				repaint();
			}
			}
		}
		else if(event.getSource()==buttons[0]){
			con=add(con,1);
			answer.setText(con);
			repaint();
	}
		else if(event.getSource()==buttons[1]){
			con=add(con,2);
			answer.setText(con);
	}
		else if(event.getSource()==buttons[2]){
			con=add(con,3);
			answer.setText(con);
	}
		else if(event.getSource()==buttons[3]){
			con=add(con,4);
			answer.setText(con);
	}
		else if(event.getSource()==buttons[4]){
			con=add(con,5);
			answer.setText(con);
	}
		else if(event.getSource()==buttons[5]){
			con=add(con,6);
			answer.setText(con);
	}
		else if(event.getSource()==buttons[6]){
			con=add(con,7);
			answer.setText(con);
	}
		else if(event.getSource()==buttons[7]){
			con=add(con,8);
			answer.setText(con);
	}
		else if(event.getSource()==buttons[8]){
			con=add(con,9);
			answer.setText(con);
	}
		else if(event.getSource()==num0){
			con=add(con,0);
			answer.setText(con);
	}
		else if(event.getSource()==nums){
			if(con.equals(""))
			answer.setText(con+"-");
	}
		
		else if(event.getSource() == exit){
			dispose();
		}
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 * @param first the first number of the question
	 * @param second the second number of the question
	 * @param symbol the symbol number of the question
	 * @param answer the input answer
	 * @return return true if the answer if right, else return false
	 */
	public boolean calculate(int first,int second,int symbol,int answer){
		boolean judge=false;
		long result=0;
		double a,b,c;
		b=first;
		c=second;
		if(symbol==1)
			result=Math.round(first+second);
		if(symbol==2)
			result=Math.round(first-second);
		if(symbol==3)
			result=Math.round(first*second);
		if(symbol==4){
			a=b/c;
			result=Math.round(a);
		}

		
		if(result==answer)
			judge=true;
		
		return judge;
	}
	/**
	 * 
	 * @param con the content that already in text
	 * @param add the content that you want to input from the keyboard via GUI
	 * @return the new content on the text
	 */
	public String add(String con,int add){
		int test=0;
		char[] content=con.toCharArray();
		if(con.equals("-"))
			return con+add;
		if(con.equals("0")&&add==0){
			return con;
		}
		if(con.equals("-0")){
			return con;
		}
		try{
		test=Integer.parseInt(con);
		}
		catch(Exception e){}
		if(test<0){
			return con;
		}
		if(test<10&&content.length<2)
		{
			return (con+add);
		}
		if(test==10&&add==0){
			return (con+add);
		}
		return con;
	}

	
}
