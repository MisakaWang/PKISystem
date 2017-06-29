<<<<<<< HEAD
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;
public class Client extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static int SendNum=-1;
	//����
	//north
	JMenuBar bar = new JMenuBar();
	JMenu m1 = new JMenu("���ӷ�����");
	JMenu m2 = new JMenu("����");
	JMenuItem conServer = new JMenuItem("���ӷ�����");
	JMenuItem disServer = new JMenuItem("�Ͽ�������");
    JMenuItem exit = new JMenuItem("�˳�");
    JMenuItem about = new JMenuItem("���ڱ����");
	//ImageIcon img = new ImageIcon(Client.class.getResource("top.jpg"));
    //JLabel top = new JLabel(img);
    JPanel north = new JPanel();
    //west
    JButton Cert = new JButton("����֤��");
    JTextField wanted = new JTextField();
    
    
    JPanel west = new JPanel();
    //ImageIcon img2 = new ImageIcon(Client.class.getResource("left.jpg"));
    //JLabel left = new JLabel(img2);
    DefaultListModel dlm = new DefaultListModel();
    JList userList = new JList(dlm);
    JScrollPane listPane = new JScrollPane(userList);
    //center
    JPanel center = new JPanel();
    JTextArea showMsg = new JTextArea(10,20);
    JScrollPane showPane = new JScrollPane(showMsg);
    JPanel operPane = new JPanel();
    //JLabel input = new JLabel("������:");
    //JTextField msgInput = new JTextField(24);
    //JButton send = new JButton("send");
    //net
    static Socket socket;
    static String name;
    static ObjectOutputStream oos;
    static List<PersonalFrame> personalFrames = new ArrayList<PersonalFrame>();
	
    public Client() {
		init();
		addEvent();
		initFrame();
	}

    
    
	public void init(){
		//north
		m1.add(conServer);
		m1.add(disServer);
		m1.addSeparator();
		m1.add(exit);
		m2.add(about);
		bar.add(m1);
		bar.add(m2);
		BorderLayout bl = new BorderLayout();
		north.setLayout(bl);
		north.add(bar,BorderLayout.NORTH);
		//north.add(top,BorderLayout.SOUTH);
		add(north,BorderLayout.NORTH);
		//west
		Dimension dim = new Dimension(100,150);
		west.setPreferredSize(dim);
		Dimension dim2 = new Dimension(100,300);
		listPane.setPreferredSize(dim2);
		BorderLayout bl2 = new BorderLayout();
		west.setLayout(bl2);
		//west.add(left,BorderLayout.NORTH);
		west.add(listPane,BorderLayout.NORTH);
		west.add(wanted, BorderLayout.CENTER);
		west.add(Cert,BorderLayout.SOUTH);
		add(west,BorderLayout.EAST);
		userList.setFont(new Font("����",Font.BOLD,18));
		//center
		//showMsg.setFont(new Font("����",Font.BOLD,28));
		//msgInput.setActionCommand("enterKey");
		showMsg.setEditable(false);
		BorderLayout bl3 = new BorderLayout();
		center.setLayout(bl3);
		FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
		operPane.setLayout(fl);
		//operPane.add(input);
		//operPane.add(msgInput);
		//operPane.add(send);
		center.add(showPane,BorderLayout.CENTER);
		center.add(operPane,BorderLayout.SOUTH);
		add(center,BorderLayout.CENTER);
		
	}
	public void addEvent(){
		//�¼��İ�
		Cert.addActionListener(this);
		//send.addActionListener(this);
		//msgInput.addActionListener(this);
		conServer.addActionListener(this);
		disServer.addActionListener(this);
		exit.addActionListener(this);
		about.addActionListener(this);
		SymMouse   picker   =   new   SymMouse();
		userList.addMouseListener(picker);
	}
	public void initFrame(){
		setResizable(false);
		setTitle("��ʱͨѶϵͳ�ͻ���");
		setSize(500,400);
		setLocation(200,50);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	@Override
	public void dispose(){
		try {
			ExitProgram();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/*
	 * ���͸�CA֤������
	 * contentΪ˽�Ķ����ID
	 */
	public void SendToCA()throws Exception{	
			//PersonalFrame myFrame=new PersonalFrame(name,userList.getSelectedValue().toString());
			//personalFrames.add(myFrame);
				String tmpcontent = wanted.getText();
				String content = PackageEva.CreatePackage(tmpcontent, 0);
				Message msg = new Message(name,content,MessageType.Chat,ChatState.Personal,"CA");
				oos.writeObject(msg);
				oos.flush();
	}
	
	public void ConnectServer()throws Exception{
		//�����������ʾ�û����������IP��ַ
		int cnt = 0;
		do{
			if(++cnt == 4){
				JOptionPane.showMessageDialog(this,"�Բ������������Ѿ�����3��\n�����Զ��˳���");
				System.exit(0);
			}
			
			String ip = JOptionPane.showInputDialog("�������������IP:");
			String portStr = JOptionPane.showInputDialog("�������������PORT:");
			
			System.out.println("������յ�����Ϣ�У�"+ip+" "+portStr);
			
		/* */	socket=new Socket(ip,Integer.parseInt(portStr));
			name = "0";
			/*if(name == null || name.trim().equals("")){
				name="�ο�";
			}*/
			
			OutputStream os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
			String content = "�û� "+name+" "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"����\n";

			
			Message msg = new Message(name,content,MessageType.Login);
			oos.writeObject(msg);
			oos.flush();
		    
		}while(socket == null);
//		�ɹ��������Ӻ�����һ�����߳̽�����Ϣ
		new Thread(){
			@Override
			public void run() {
				try {
					boolean flag=true;
					while(flag){
						//��ȡ�Է����͹�������Ϣ
						InputStream is = socket.getInputStream();
						ObjectInputStream ois = new ObjectInputStream(is);
						Message msg = (Message)(ois.readObject());																	
						MessageType type = msg.getMsgType();
						
						//��¼״̬
						if(type.equals(MessageType.Login)){	
							String content = msg.getContent();
							//System.out.println(content);
							String old = showMsg.getText();
							if(old == null || old.trim().equals("")){
								showMsg.setText(content);
							}else{
								String temp = old+"\n"+content;
								showMsg.setText(temp);
							}
							showMsg.setCaretPosition(showMsg.getText().length());
							if(dlm.size() == 0){
								List<String> names = msg.getNames();
								for(String s : names){
									
									if(!s.equals(name)){
										//���name���б���
										dlm.addElement(s);
										System.out.println(s+"��ӳɹ�");
									}
								}
							}else{
								if(!msg.getFrom().equals(name))
									dlm.addElement(msg.getFrom());
							}
						}
						
						//����״̬
						else if(type.equals(MessageType.Chat)){
							//˽��
							
							if(msg.getChatState().equals(ChatState.Personal)){
								boolean FindIt=false;
								for(PersonalFrame pf:personalFrames){
									if(pf.getName().equals(msg.getFrom())||pf.getName().equals(msg.getTo())){										
																		
										
										pf.showMsg(msg);
										
										
										System.out.println("�ɹ��ҵ���");
										FindIt=true;
										break;
									}									
								}
								//���û�ҵ� �½�һ��˽��
								if(FindIt==false){
									
									System.out.println("�ɹ�������");
									System.out.println("֤���ǣ�"+RSACoder.Certificate+"\n");
									System.out.println("PK�ǣ�"+RSACoder.getPublicKey(2)+"\n");
									//myFrame.showMsg(msg);
								}
							}/*else{
									
									String old = showMsg.getText();
									if(old == null || old.trim().equals("")){
										showMsg.setText(content);
									}else{
										String temp = old+"\n"+content;
										showMsg.setText(temp);
									}
									showMsg.setCaretPosition(showMsg.getText().length());
							}*/
						}else if(type.equals(MessageType.Logout)){
							String content = msg.getContent();
							String old = showMsg.getText();
							if(old == null || old.trim().equals("")){
								showMsg.setText(content);
								System.out.println(content);
							}else{
								String temp = old+"\n"+content;
								showMsg.setText(temp);
							}
							showMsg.setCaretPosition(showMsg.getText().length());
							if(!msg.getFrom().equals(name))
								dlm.removeElement(msg.getFrom());
							if(msg.getFrom().equals(name))
								flag=false;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}.start();
		setTitle("��ʱͨѶϵͳ�ͻ���---"+name);
	}
	public void BreakServer()throws Exception{
		if(socket!=null){
			String content = "�û� "+name+" "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"�Ѿ�����"+"\n";
			Message msg=new Message(name, content, MessageType.Logout);
			System.out.println(content);
			oos.writeObject(msg);
			oos.flush();
			dlm.removeAllElements();
			socket=null;
			setTitle("��ʱͨѶϵͳ�ͻ���---"+name+"������");
		}
	}
	public void ExitProgram()throws Exception{
		int flag;
		if(socket!=null){
			flag = JOptionPane.showConfirmDialog(this, "��û��ע����ֱ���˳����Զ�ע�����ӣ�\nȷ��Ҫ�˳���");
		}else{
			flag=0;
		}
		//System.out.println("flag : "+flag);
		if(flag == 0){
			if(socket!=null){
				String content = "�û� "+name+" "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"�Ѿ�����"+"\n";
				Message msg=new Message(name, content, MessageType.Logout);
				oos.writeObject(msg);
				oos.flush();
				socket=null;
				dlm.removeAllElements();
			}
			System.exit(0);
		}
	}
	//Button��Ӧ�¼�
	public void actionPerformed(ActionEvent e) {
		try{
		String comm = e.getActionCommand();
		System.out.println(comm);
		//if(comm.equals("send") || comm.equals("enterKey")||comm.equals("sendPersonalMessage")){
			//SendMessage();
		//}
		 if(comm.equals("���ӷ�����")){
			ConnectServer();
		}else if(comm.equals("�Ͽ�������")){
			BreakServer();
		}else if(comm.equals("�˳�")){
			ExitProgram();
		}else if(comm.equals("���ڱ����")){
			JOptionPane.showMessageDialog(this, "�ͻ���");
		}else if(comm.equals("����֤��")){			
			SendToCA();
		}
		else{
			System.out.println("��ʶ����¼�");
		}
		}catch(Exception e1){
			//��������
		}
	}
	//�ڲ��࣬������Ӧ˫��JList��item���¼�
	class   SymMouse   extends   java.awt.event.MouseAdapter 
    { 
        public   void   mouseClicked(java.awt.event.MouseEvent   e) 
        { 
            Object   object   =   e.getSource(); 
            if(object==userList)
				try {
					userList_mouseClicked(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
        } 
    } 

  void   userList_mouseClicked(java.awt.event.MouseEvent   event) throws Exception 
    { 
        if(event.getModifiers()==MouseEvent.BUTTON1_MASK&&event.getClickCount()==2) 
        { 
        	if(!userList.getSelectedValue().toString().equals("CA"))
        		
        	{
        		System.out.println("ѡ�пͻ���"+userList.getSelectedValue().toString());
        	//JOptionPane.showMessageDialog(this, userList.getSelectedValue().toString(), "���� ",JOptionPane.WARNING_MESSAGE);
        		//SendNum=Integer.parseInt(userList.getSelectedValue().toString());
        		PersonalFrame myFrame=new PersonalFrame(name,userList.getSelectedValue().toString());
        		personalFrames.add(myFrame);
        		//System.out.println(SendNum);
        	}
        	if(userList.getSelectedValue().toString().equals("CA"))
        		{
        			System.out.println("ѡ��CA");
        		//SendToCA();
        		}
    		
        } 
    } 
	public static void main(String[] args) throws Exception {
		RSACoder.KeyInit();
		CAServer.GeneraeCert();
		RSACoder.Certificate=CAServer.GetCert(0);
		
		new Client();
	}
}
=======
import java.util.Base64;

public class Client {
	
    public static void main(String[] args) throws Exception {  
    	RSACoder.KeyInit();
    	//CAServer.GeneraeCert();
    String tmp=RSACoder.PublicEncrypt("hello", RSACoder.getPublicKey(1));
    System.out.println(RSACoder.PrivateDecrypt(tmp, RSACoder.getPrivateKey()));
    	
  }  
    
     
}
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
