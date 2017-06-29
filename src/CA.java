<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
public class CA extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	//界面
	//north
	JMenuBar bar = new JMenuBar();
	JMenu m1 = new JMenu("连接服务器");
	JMenu m2 = new JMenu("关于");
	JMenuItem conServer = new JMenuItem("连接服务器");
	JMenuItem disServer = new JMenuItem("断开服务器");
    JMenuItem exit = new JMenuItem("退出");
    JMenuItem about = new JMenuItem("关于本软件");
	//ImageIcon img = new ImageIcon(Client.class.getResource("top.jpg"));
   // JLabel top = new JLabel(img);
    JPanel north = new JPanel();
    //west
    //JButton Cert = new JButton("请求证书");
    
    JPanel west = new JPanel();
    //ImageIcon img2 = new ImageIcon(Client.class.getResource("left.jpg"));
    //JLabel left = new JLabel(img2);
    DefaultListModel dlm = new DefaultListModel();
    JList userList = new JList(dlm);
    JScrollPane listPane = new JScrollPane(userList);
    //center
    JPanel center = new JPanel();
    JTextArea showMsg = new JTextArea(10,20);
    JTextArea showCert = new JTextArea(10,20);
    JScrollPane showPane = new JScrollPane(showMsg);
    
    JScrollPane showPane2 = new JScrollPane(showCert);
    
    JPanel operPane = new JPanel();
   
    //net
    static Socket socket;
    static String name;
    static ObjectOutputStream oos;
    static List<PersonalFrame> personalFrames = new ArrayList<PersonalFrame>();
    static String target;
    static String source;
	
    public CA() {
		init();
		addEvent();
		initFrame();
	}
  
    
    //初始化
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
		west.add(listPane,BorderLayout.CENTER);
		//west.add(Cert,BorderLayout.SOUTH);
		add(west,BorderLayout.EAST);
		userList.setFont(new Font("隶书",Font.BOLD,18));
		//center
		//showMsg.setFont(new Font("宋体",Font.BOLD,28));
		//msgInput.setActionCommand("enterKey");
		showMsg.setEditable(false);
		BorderLayout bl3 = new BorderLayout();
		center.setLayout(bl3);
		FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
		operPane.setLayout(fl);
		//operPane.add(input);
		//operPane.add(msgInput);
		//operPane.add(send);
		center.add(showPane,BorderLayout.NORTH);
		
		center.add(showPane2, BorderLayout.CENTER);
		
		center.add(operPane,BorderLayout.SOUTH);
		add(center,BorderLayout.CENTER);
		
	}
	//添加监听
	public void addEvent(){
		//事件的绑定
		//Cert.addActionListener(this);
		//send.addActionListener(this);
		//msgInput.addActionListener(this);
		conServer.addActionListener(this);
		disServer.addActionListener(this);
		exit.addActionListener(this);
		about.addActionListener(this);
		SymMouse   picker   =   new   SymMouse();
		userList.addMouseListener(picker);
	}
	//初始化界面
	public void initFrame(){
		setResizable(false);
		setTitle("CA");
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
	
	//发送信息
	public void SendMessageToClient(int getCert,int getPk)throws Exception{
		//判断是否连接了服务器
		if(socket == null){
			JOptionPane.showMessageDialog(this, "没有连接服务器或已经断开服务器");
			return;
		}
		/*
		 * 发送消息
		 * CA发送的内容应该是请求者的证书，属于私聊
		 * 格式  CA，证书信息，消息类Chat，聊天类型Personal，证书请求者
		 *
		 */
		String content = CAServer.CreateCAPackage(getCert,getPk );
<<<<<<< HEAD
		if(content.isEmpty() || content.trim().equals("")){
=======
		if(content == null || content.trim().equals("")){
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
			JOptionPane.showMessageDialog(this, "不能发送空字符串");
		}
		else{
			//清空文本输入框
			//msgInput.setText("");
		}		
<<<<<<< HEAD
		Message msg = new Message("0", content, MessageType.Chat, ChatState.Personal, Integer.toString(getCert));
=======
		Message msg = new Message("CA", content, MessageType.Chat, ChatState.Personal, Integer.toString(getCert));
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
		
			oos.writeObject(msg);
			oos.flush();
	}
	
	
	public void ConnectServer()throws Exception{
		//弹出输入框，提示用户输入服务器IP地址
		int cnt = 0;
		do{
			if(++cnt == 4){
				JOptionPane.showMessageDialog(this,"对不起，您的输入已经超出3次\n程序将自动退出！");
				System.exit(0);
			}
			
			String ip = JOptionPane.showInputDialog("请您输入服务器IP:");
			String portStr = JOptionPane.showInputDialog("请您输入服务器PORT:");
			
			System.out.println("程序接收到的信息有："+ip+" "+portStr);
			
		/* */	socket=new Socket(ip,Integer.parseInt(portStr));
		//名字固定为CA
<<<<<<< HEAD
			name = "0";
=======
			name = "CA";
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
			
			OutputStream os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
			String content = "用户 "+name+" "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"上线\n";
			Message msg = new Message(name,content,MessageType.Login);
			oos.writeObject(msg);
			oos.flush();
		    
		}while(socket == null);
<<<<<<< HEAD
		
		
		
//		成功创建连接后启动一个新线程接收消息
		new Thread(){
			
			@Override
			public void run() {
				try {
					
					boolean flag=true;
					while(flag){
						
						//对象流输入
						InputStream is = socket.getInputStream();
						System.out.println("收到一条新信息");
						//读取输入对象流
						ObjectInputStream ois = new ObjectInputStream(is);
						//写成Message格式（发送者，content，MessageType，ChatState，接收方）
						Message msg = (Message)(ois.readObject());													
=======
//		成功创建连接后启动一个新线程接收消息
		new Thread(){
			@Override
			public void run() {
				try {
					boolean flag=true;
					while(flag){
						//对象流输入
						InputStream is = socket.getInputStream();
						//读取输入对象流
						ObjectInputStream ois = new ObjectInputStream(is);
						//写成Message格式（发送者，content，MessageType，ChatState，接收方）
						Message msg = (Message)(ois.readObject());	
						
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
						MessageType type = msg.getMsgType();						
				//消息类型为登录（更新用户列表）
						if(type.equals(MessageType.Login)){
							//TextArea显示接收到的信息
							String content = msg.getContent();
							String old = showMsg.getText();
<<<<<<< HEAD
							if(old .isEmpty()  || old.trim().equals("")){
=======
							if(old == null || old.trim().equals("")){
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
								showMsg.setText(content);
							}else{
								String temp = old+"\n"+content;
								showMsg.setText(temp);
							}							
							//滚动条拉到最下端
<<<<<<< HEAD
							showMsg.setCaretPosition(showMsg.getText().length());							
				//添加在线用户名称
							if(dlm.size() == 0){
								List<String> names = msg.getNames();
								for(String s : names){									
=======
							showMsg.setCaretPosition(showMsg.getText().length());
							
				//添加在线用户名称
							if(dlm.size() == 0){
								List<String> names = msg.getNames();
								for(String s : names){
									
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
									if(!s.equals(name)){
										//添加name到列表中
										dlm.addElement(s);
										System.out.println(s+"添加成功");
									}
								}
							}
							else{
								//如果列表中没有，添加name
								if(!msg.getFrom().equals(name))
									dlm.addElement(msg.getFrom());
							}
						}						
				//消息类型为聊天
<<<<<<< HEAD
						else if(type.equals(MessageType.Chat)){							
								System.out.println("接收到来自："+msg.getFrom()+"的信息");	
								String er=CAServer.DecreateCAPackage(msg.getContent());
								System.out.println(er);
								boolean FindIt=false;																																											
								 /*
								  * 解密msg中的content
								  * 获取想要私聊对象的id
								  * 并存到target中
								*/
								CAServer.DecreateCAPackage(msg.getContent());
								int target = CAServer.Receiver;
								int source = CAServer.Sender;
								/*
								  * 收到私聊根据发送者 返回证书信息和公钥
								  * 给source 发送 source的证书和target的公钥
								  * 给target 发送 target的证书和source的公钥
								  */
								SendMessageToClient(target,source);
								SendMessageToClient(source,target);										 
								System.out.println("请求已经反馈！");
								FindIt=true;															
=======
						else if(type.equals(MessageType.Chat)){
							//如果是私聊
							if(msg.getChatState().equals(ChatState.Personal)){
								System.out.println("接收到来自："+msg.getFrom()+"的信息");	
								String er=CAServer.DecreateCAPackage(msg.getContent());
								System.out.println(er);
								boolean FindIt=false;
								//for(PersonalFrame pf:personalFrames){
									//if(pf.getName().equals(msg.getFrom())||pf.getName().equals(msg.getTo())){	
										//显示信息
										showMsg.setText("接收到来自："+msg.getFrom()+"的信息");
										
										 /*
										  * 解密msg中的content
										  * 获取想要私聊对象的id
										  * 并存到target中
										  */
										 CAServer.DecreateCAPackage(msg.getContent());
										 int target = CAServer.Receiver;
										 int source = CAServer.Sender;
										 /*
										  * 收到私聊根据发送者 返回证书信息和公钥
										  * 给source 发送 source的证书和target的公钥
										  * 给target 发送 target的证书和source的公钥
										  */
										 SendMessageToClient(target,source);
										 SendMessageToClient(source,target);
										 
										System.out.println("成功发送！");
										FindIt=true;
										break;
									}									
								}
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
								//如果没找到 新建一个私聊
								/*if(FindIt==false){
									PersonalFrame myFrame=new PersonalFrame(name, msg.getFrom());
									personalFrames.add(myFrame);
									System.out.println("成功创建！");
<<<<<<< HEAD
									
								}*/
					}
				
=======
									myFrame.showMsg(msg);
								}*/
							
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
							//聊天室聊天
							/*else{
									String content = msg.getContent();
									String old = showMsg.getText();
									if(old == null || old.trim().equals("")){
										showMsg.setText(content);
									}else{
										String temp = old+"\n"+content;
										showMsg.setText(temp);
									}
									showMsg.setCaretPosition(showMsg.getText().length());
<<<<<<< HEAD
							}*/						
						//}						
=======
							}*/
							
						//}
						
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
						//登出状态
						else if(type.equals(MessageType.Logout)){
							String content = msg.getContent();
							String old = showMsg.getText();
<<<<<<< HEAD
							if(old.isEmpty() || old.trim().equals("")){
=======
							if(old == null || old.trim().equals("")){
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
								showMsg.setText(content);
							}else{
								String temp = old+"\n"+content;
								showMsg.setText(temp);
							}
							showMsg.setCaretPosition(showMsg.getText().length());
							if(!msg.getFrom().equals(name))
								dlm.removeElement(msg.getFrom());
<<<<<<< HEAD
							//if(msg.getFrom().equals(name))
								//flag=false;
						}					
					}					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
=======
							if(msg.getFrom().equals(name))
								flag=false;
						}
					
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
		}.start();
		setTitle("CA");
	}
	//断开连接
	public void BreakServer()throws Exception{
		if(socket!=null){
			String content = "用户 "+name+" "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"已经下线"+"\n";
			Message msg=new Message(name, content, MessageType.Logout);
			oos.writeObject(msg);
			oos.flush();
			dlm.removeAllElements();
			socket=null;
			setTitle("CA下线");
		}
	}
	//直接退出
	public void ExitProgram()throws Exception{
		int flag;
		if(socket!=null){
			flag = JOptionPane.showConfirmDialog(this, "还没有注销，直接退出将自动注销连接！\n确定要退出吗？");
		}else{
			flag=0;
		}
		//System.out.println("flag : "+flag);
		if(flag == 0){
			if(socket!=null){
				String content = "用户 "+name+" "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"已经下线"+"\n";
				Message msg=new Message(name, content, MessageType.Logout);
				oos.writeObject(msg);
				oos.flush();
				socket=null;
				dlm.removeAllElements();
			}
			System.exit(0);
		}
	}
	//Button对应事件
	public void actionPerformed(ActionEvent e) {
		try{
		String comm = e.getActionCommand();
<<<<<<< HEAD
		System.out.println(comm);					
=======
		System.out.println(comm);
		
			
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
		 if(comm.equals("连接服务器")){
			ConnectServer();
		}else if(comm.equals("断开服务器")){
			BreakServer();
		}else if(comm.equals("退出")){
			ExitProgram();
		}else if(comm.equals("关于本软件")){
			JOptionPane.showMessageDialog(this, "证书发放机构");
		}else if(comm.equals("请求证书")){
			
<<<<<<< HEAD
			String content = "请求证书";			
=======
			String content = "请求证书";
			
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
		}
		else{
			System.out.println("不识别的事件");
		}
		}catch(Exception e1){
			//不作处理
		}
	}
	//内部类，用于响应双击JList中item的事件
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
	 
  void   userList_mouseClicked(java.awt.event.MouseEvent   event) throws NumberFormatException, Exception 
    { 
        if(event.getModifiers()==MouseEvent.BUTTON1_MASK&&event.getClickCount()==2) 
        { 
        	//JOptionPane.showMessageDialog(this, userList.getSelectedValue().toString(), "警告 ",JOptionPane.WARNING_MESSAGE);
        	String getvalue = userList.getSelectedValue().toString();
        	showCert.setText(CAServer.GetCert(Integer.parseInt(getvalue)));
        	
        } 
    } 
	public static void main(String[] args) throws Exception {
		new CA();
		CAServer.GeneraeCert();
	}
}

<<<<<<< HEAD
=======
=======
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
public class CA extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	//界面
	//north
	JMenuBar bar = new JMenuBar();
	JMenu m1 = new JMenu("连接服务器");
	JMenu m2 = new JMenu("关于");
	JMenuItem conServer = new JMenuItem("连接服务器");
	JMenuItem disServer = new JMenuItem("断开服务器");
    JMenuItem exit = new JMenuItem("退出");
    JMenuItem about = new JMenuItem("关于本软件");
	ImageIcon img = new ImageIcon(Client.class.getResource("top.jpg"));
    JLabel top = new JLabel(img);
    JPanel north = new JPanel();
    //west
    JButton Cert = new JButton("请求证书");
    
    JPanel west = new JPanel();
    ImageIcon img2 = new ImageIcon(Client.class.getResource("left.jpg"));
    JLabel left = new JLabel(img2);
    DefaultListModel dlm = new DefaultListModel();
    JList userList = new JList(dlm);
    JScrollPane listPane = new JScrollPane(userList);
    //center
    JPanel center = new JPanel();
    JTextArea showMsg = new JTextArea(10,20);
    JScrollPane showPane = new JScrollPane(showMsg);
    JPanel operPane = new JPanel();
    JLabel input = new JLabel("请输入:");
    JTextField msgInput = new JTextField(24);
    JButton send = new JButton("send");
    //net
    static Socket socket;
    static String name;
    static ObjectOutputStream oos;
    static List<PersonalFrame> personalFrames = new ArrayList<PersonalFrame>();
    static String requester;
	
    public CA() {
		init();
		addEvent();
		initFrame();
	}
  //字符串转换成二进制
    private String StrToBinstr(String str){
    	char[] strChar = str.toCharArray();
    	String result ="";
    	for(int i=0;i<strChar.length;i++){
    		result+=Integer.toBinaryString(strChar[i])+" ";
    	}
    	return result;
    }
    //寻找证书
    public static String FindCert(String requester)throws IOException{
    	File file = new File("F:\\证书.txt");
    	BufferedReader br = new BufferedReader(new FileReader(file));
    	String cert="";
    	String StdID="";
    	if(requester=="01"){ 
    		String StuID = "20141003501";
    		
    	}
    	if(requester=="02"){
    		String StuID = "20141003502";
    		
    	}
    	if(requester=="03"){
    		String StuID = "20141003503";
    	}
    	while((cert=br.readLine())!=null){
    		Pattern p = Pattern.compile(StdID);
    		Matcher m = p.matcher(cert);
    		if(m.find())
    			return cert;
    	}
		return cert;
    }
    
    //初始化
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
		west.add(listPane,BorderLayout.CENTER);
		west.add(Cert,BorderLayout.SOUTH);
		add(west,BorderLayout.EAST);
		userList.setFont(new Font("隶书",Font.BOLD,18));
		//center
		//showMsg.setFont(new Font("宋体",Font.BOLD,28));
		msgInput.setActionCommand("enterKey");
		showMsg.setEditable(false);
		BorderLayout bl3 = new BorderLayout();
		center.setLayout(bl3);
		FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
		operPane.setLayout(fl);
		operPane.add(input);
		operPane.add(msgInput);
		operPane.add(send);
		center.add(showPane,BorderLayout.CENTER);
		center.add(operPane,BorderLayout.SOUTH);
		add(center,BorderLayout.CENTER);
		
	}
	//添加监听
	public void addEvent(){
		//事件的绑定
		Cert.addActionListener(this);
		send.addActionListener(this);
		msgInput.addActionListener(this);
		conServer.addActionListener(this);
		disServer.addActionListener(this);
		exit.addActionListener(this);
		about.addActionListener(this);
		SymMouse   picker   =   new   SymMouse();
		userList.addMouseListener(picker);
	}
	//初始化界面
	public void initFrame(){
		setResizable(false);
		setTitle("CA");
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
	
	//发送信息
	public void SendMessageToRequester()throws Exception{
		//判断是否连接了服务器
		if(socket == null){
			JOptionPane.showMessageDialog(this, "没有连接服务器或已经断开服务器");
			return;
		}
		/*
		 * 发送消息
		 * CA发送的内容应该是请求者的证书，属于私聊
		 * 格式  CA，证书信息，消息类Chat，聊天类型Personal，证书请求者
		 *
		 */
		//String tempcontent = msgInput.getText();
		//String content = StrToBinstr(tempcontent);
		String content = FindCert(requester);
		if(content == null || content.trim().equals("")){
			JOptionPane.showMessageDialog(this, "不能发送空字符串");
		}
		else{
			//清空文本输入框
			msgInput.setText("");
		}
		
		Message msg = new Message("CA", "证书", MessageType.Chat, ChatState.Personal, requester);
		System.out.println(requester);
			oos.writeObject(msg);
			oos.flush();
	}
	
	
	public void ConnectServer()throws Exception{
		//弹出输入框，提示用户输入服务器IP地址
		int cnt = 0;
		do{
			if(++cnt == 4){
				JOptionPane.showMessageDialog(this,"对不起，您的输入已经超出3次\n程序将自动退出！");
				System.exit(0);
			}
			
			String ip = JOptionPane.showInputDialog("请您输入服务器IP:");
			String portStr = JOptionPane.showInputDialog("请您输入服务器PORT:");
			
			System.out.println("程序接收到的信息有："+ip+" "+portStr);
			
		/* */	socket=new Socket(ip,Integer.parseInt(portStr));
		//名字固定为CA
			name = "CA";
			
			OutputStream os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
			Message msg = new Message(name,null,MessageType.Login);
			oos.writeObject(msg);
			oos.flush();
		    
		}while(socket == null);
//		成功创建连接后启动一个新线程接收消息
		new Thread(){
			@Override
			public void run() {
				try {
					boolean flag=true;
					while(flag){
						//对象流输入
						InputStream is = socket.getInputStream();
						//读取输入对象流
						ObjectInputStream ois = new ObjectInputStream(is);
						
						Message msg = (Message)(ois.readObject());
						
						MessageType type = msg.getMsgType();
						
				//消息类型为登录（更新用户列表）
						if(type.equals(MessageType.Login)){
							//TextArea显示接收到的信息
							String content = msg.getContent();
							String old = showMsg.getText();
							
							if(old == null || old.trim().equals("")){
								showMsg.setText(content);
							}else{
								String temp = old+"\n"+content;
								showMsg.setText(temp);
							}							
							//滚动条拉到最下端
							showMsg.setCaretPosition(showMsg.getText().length());
							
							//添加在线用户名称
							if(dlm.size() == 0){
								List<String> names = msg.getNames();
								for(String s : names){
									
									if(!s.equals(name)){
										//添加name到列表中
										dlm.addElement(s);
										System.out.println(s+"添加成功");
									}
								}
							}else{
								//如果列表中没有，添加name
								if(!msg.getFrom().equals(name))
									dlm.addElement(msg.getFrom());
							}
						}						
				//消息类型为聊天
						else if(type.equals(MessageType.Chat)){
							//如果是私聊
							if(msg.getChatState().equals(ChatState.Personal)){
								boolean FindIt=false;
								for(PersonalFrame pf:personalFrames){
									if(pf.getName().equals(msg.getFrom())||pf.getName().equals(msg.getTo())){	
										//显示信息
										//pf.showMsg(msg);
										showMsg.setText(pf.getName());
										//JOptionPane.showMessageDialog(null, "收到证书");
										 requester = msg.getFrom();
										 //收到私聊根据发送者 返回证书信息；
										 SendMessageToRequester();
										System.out.println("成功找到！");
										FindIt=true;
										break;
									}									
								}
								//如果没找到 新建一个私聊
								/*if(FindIt==false){
									PersonalFrame myFrame=new PersonalFrame(name, msg.getFrom());
									personalFrames.add(myFrame);
									System.out.println("成功创建！");
									myFrame.showMsg(msg);
								}*/
							}
							//聊天室聊天
							else{
									String content = msg.getContent();
									String old = showMsg.getText();
									if(old == null || old.trim().equals("")){
										showMsg.setText(content);
									}else{
										String temp = old+"\n"+content;
										showMsg.setText(temp);
									}
									showMsg.setCaretPosition(showMsg.getText().length());
							}
							
						}
						
						//登出状态
						else if(type.equals(MessageType.Logout)){
							String content = msg.getContent();
							String old = showMsg.getText();
							if(old == null || old.trim().equals("")){
								showMsg.setText(content);
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
		setTitle("CA");
	}
	//断开连接
	public void BreakServer()throws Exception{
		if(socket!=null){
			Message msg=new Message(name, null, MessageType.Logout);
			oos.writeObject(msg);
			oos.flush();
			dlm.removeAllElements();
			socket=null;
			setTitle("CA下线");
		}
	}
	//直接退出
	public void ExitProgram()throws Exception{
		int flag;
		if(socket!=null){
			flag = JOptionPane.showConfirmDialog(this, "还没有注销，直接退出将自动注销连接！\n确定要退出吗？");
		}else{
			flag=0;
		}
		//System.out.println("flag : "+flag);
		if(flag == 0){
			if(socket!=null){
				Message msg=new Message(name, null, MessageType.Logout);
				oos.writeObject(msg);
				oos.flush();
				socket=null;
				dlm.removeAllElements();
			}
			System.exit(0);
		}
	}
	//Button对应事件
	public void actionPerformed(ActionEvent e) {
		try{
		String comm = e.getActionCommand();
		System.out.println(comm);
		if(comm.equals("send") || comm.equals("enterKey")||comm.equals("sendPersonalMessage")){
			SendMessageToRequester();
		}else if(comm.equals("连接服务器")){
			ConnectServer();
		}else if(comm.equals("断开服务器")){
			BreakServer();
		}else if(comm.equals("退出")){
			ExitProgram();
		}else if(comm.equals("关于本软件")){
			JOptionPane.showMessageDialog(this, "证书发放机构");
		}else if(comm.equals("请求证书")){
			
			String content = "请求证书";
			
		}
		else{
			System.out.println("不识别的事件");
		}
		}catch(Exception e1){
			//不作处理
		}
	}
	//内部类，用于响应双击JList中item的事件
	class   SymMouse   extends   java.awt.event.MouseAdapter 
    { 
        public   void   mouseClicked(java.awt.event.MouseEvent   e) 
        { 
            Object   object   =   e.getSource(); 
            if(object==userList)
				try {
					userList_mouseClicked(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
        } 
    } 
	 
  void   userList_mouseClicked(java.awt.event.MouseEvent   event) throws IOException 
    { 
        if(event.getModifiers()==MouseEvent.BUTTON1_MASK&&event.getClickCount()==2) 
        { 
        	//JOptionPane.showMessageDialog(this, userList.getSelectedValue().toString(), "警告 ",JOptionPane.WARNING_MESSAGE);
        	if(userList.getSelectedValue().toString()=="01"){ 
        		/*String StuID = "20141003501";
        		String cert = FindCert(StuID);
        		showMsg.setText(cert);*/
        		
        	}
        	if(userList.getSelectedValue().toString()=="02"){
        		String StuID = "20141003501";
        		String cert = FindCert(StuID);
        		showMsg.setText(cert);
        	}
        	if(userList.getSelectedValue().toString()=="03"){
        		String StuID = "20141003501";
        		String cert = FindCert(StuID);
        		showMsg.setText(cert);
        		
        	}
        	
        } 
    } 
	public static void main(String[] args) {
		new CA();
	}
}

>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
