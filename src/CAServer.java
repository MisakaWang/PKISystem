import java.security.cert.Certificate;
<<<<<<< HEAD

import sun.net.www.content.text.plain;
=======
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
public abstract class CAServer 
{
	private static String Name="CA";
	private static String[] Cert=new String[RSACoder.AllNum];
	public static Package ToSend=new Package();
	public static Package ToReceive=new Package();
	public static String PackageRecv;
	public static String PackageSend;
	public static int Sender;
	public static int Receiver;
	
	public static void GeneraeCert() throws Exception
	{
		if(RSACoder.flag==false)
			RSACoder.KeyInit();
		String Time=RSACoder.GettimeStamp();
		for(int i=0;i<RSACoder.AllNum;i++)
		{
			String Receiver=Integer.toString(i); 
			String Info=Receiver+Time+"Certificated by CA 2017";
		
			Cert[i]=RSACoder.PrivateEncrypt(Info, RSACoder.getPrivateKey());
		}
<<<<<<< HEAD
		RSACoder.Certificate=Cert[0];
=======
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
	}
	
	public static String GetCert(int Num) throws Exception
	{
		return Cert[Num];
	}
	public static String CreateCAPackage(int SendNum,int KeyNum) throws Exception
	{
<<<<<<< HEAD
		if(Cert[SendNum].isEmpty())
=======
		if(Cert[SendNum]==null)
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
			GeneraeCert();
		ToSend.SendHeader=Integer.toString(SendNum);
		ToSend.Certifacte=RSACoder.Certificate;
		ToSend.Sig=RSACoder.GetSig(Name, RSACoder.getPrivateKey());
		ToSend.Plain=Integer.toString(KeyNum)+Cert[SendNum]+RSACoder.publicKeyString[KeyNum];
		PackageSend=ToSend.Header+ToSend.SendHeader+ToSend.Sig + ToSend.Certifacte + ToSend.Plain;
		return PackageSend;
	}
	public static String DecreateCAPackage(String InputStream) throws Exception
	{
		 ToReceive.Header = InputStream.substring(0, 1);
		 ToReceive.SendHeader=InputStream.substring(1,2);
	 	 ToReceive.Sig = InputStream.substring(2, 90);
	 	 ToReceive.Plain=InputStream.substring(90,InputStream.length());
	 	 if(RSACoder.VerifySig(Integer.parseInt(ToReceive.Header), ToReceive.Sig, 
	 			 RSACoder.getPublicKey(Integer.parseInt(ToReceive.Header))))
	 			 {
<<<<<<< HEAD
	 		 		
	 		 		String Plain=RSACoder.PrivateDecrypt(ToReceive.Plain, RSACoder.getPrivateKey());
	 		 		System.out.println(Plain.length());
	 		 		Sender=Integer.parseInt(ToReceive.Header);
	 		 		Receiver=Integer.parseInt(Plain.substring(0, 1));//指代公钥持有者
=======
	 		 		String Plain=RSACoder.PrivateDecrypt(ToReceive.Plain, RSACoder.getPrivateKey());
	 		 		Sender=Integer.parseInt(ToReceive.Header);
<<<<<<< HEAD
	 		 		Receiver=Integer.parseInt(Plain.substring(0, 1));//指代公钥持有者
=======
	 		 		Receiver=Integer.parseInt(Plain.substring(0, 1));
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
	 		 		return "Have Verified!You want to connect with "+Plain.substring(0,1);
	 			 }
	 	 else
	 		 return "Fail to Verify!";
	}
	
	
}
