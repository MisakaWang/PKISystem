import java.util.*;
import javax.swing.text.PlainDocument;
import java.text.SimpleDateFormat;
import java.applet.*;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.spec.RSAPrivateKeySpec;

public abstract class PackageEva
{
	public static Package ToSend=new Package();
	public static Package ToReceive=new Package();
	public static String PackageSend;
	public static String PackageRecv;
	public static String Package;
	static String Name="CA";
	
public static String CreatePackage(String Plain,int SendNum) throws Exception
{
	/*if(SendNum==0)
		return CreatePackageToCA(Plain);*/
	ToSend.SendHeader=Integer.toString(SendNum);
	ToSend.Certifacte=RSACoder.Certificate;
	ToSend.Sig=RSACoder.GetSig(Name, RSACoder.getPrivateKey());
	ToSend.Plain=RSACoder.PublicEncrypt(Plain, RSACoder.getPublicKey(SendNum));
	/*if(ToSend.Sig==null)
		System.out.println("Sig miss");
	if(ToSend.Certifacte==null)
		System.out.println("cert miss");
	if(ToSend.Plain==null)
		System.out.println("plain miss");*/
	PackageSend = ToSend.Header+ToSend.SendHeader+ToSend.Sig + ToSend.Certifacte + ToSend.Plain;
	return PackageSend;
}

public  static String DecreatePackage(String InputStream) throws Exception
{
	  ToReceive.Header = InputStream.substring(0, 1);
	  ToReceive.SendHeader=InputStream.substring(1,2);
	  //if(ToReceive.Header=="0")
	  		//return DecreatePackageFromCA(InputStream);
	  ToReceive.Sig = InputStream.substring(2, 90);
	  ToReceive.Certifacte = InputStream.substring(90, 178);
	  ToReceive.Plain = InputStream.substring(178, 266);
	  PackageRecv=InputStream;
	  if(RSACoder.VerifySig(Integer.parseInt(ToReceive.Header), ToReceive.Sig, RSACoder.getPublicKey(Integer.parseInt(ToReceive.Header)))&&
			  RSACoder.VerifyCert(ToReceive.Certifacte, ToReceive.Header))
	  {
		  
		  return RSACoder.PrivateDecrypt(ToReceive.Plain, RSACoder.getPrivateKey());
		  
	  }
	  else if(RSACoder.VerifySig(Integer.parseInt(ToReceive.Header), ToReceive.Sig, RSACoder.getPublicKey(Integer.parseInt(ToReceive.Header))))
	  {
		  
		  return "Fail to Verify Sigment!";
	  }
	  else 
	  {
		  return "Fail to Verify certificate!";
	  }
}

public static String CreatePackageToCA(String Plain) throws Exception
{
	ToSend.SendHeader="0";
	ToSend.Sig=RSACoder.GetSig(Name, RSACoder.getPrivateKey());
	ToSend.Plain=RSACoder.PublicEncrypt(Plain, RSACoder.getPublicKey(0));	
	PackageSend=ToSend.Header+ToSend.SendHeader+ToSend.Sig+ToSend.Plain;
	return PackageSend;

}
public static String DecreatePackageFromCA(String InputStream) throws Exception
 {
	 ToReceive.Header = InputStream.substring(0, 1);
	 ToReceive.SendHeader=InputStream.substring(1,2);
 	  ToReceive.Sig = InputStream.substring(2, 90);
 	  ToReceive.Certifacte = InputStream.substring(90, 178);
 	  int KeyNum=Integer.parseInt(InputStream.substring(178,179));
 	 if(RSACoder.VerifySig(Integer.parseInt(ToReceive.Header), ToReceive.Sig, RSACoder.getPublicKey(Integer.parseInt(ToReceive.Header)))&&
 			  RSACoder.VerifyCert(ToReceive.Certifacte, ToReceive.Header))
 	 {
 		 RSACoder.Certificate=InputStream.substring(179, 267);
 		 RSACoder.publicKeyString[KeyNum] = InputStream.substring(267, InputStream.length());
 		 RSACoder.KeyInit();
 		 return "Have updated Certifacte.Now you can build connection";
 	 }
 	 else if(RSACoder.VerifySig(Integer.parseInt(ToReceive.Header), ToReceive.Sig, RSACoder.getPublicKey(Integer.parseInt(ToReceive.Header))))
	  {
		  
		  return "Fail to Verify Sigment of CA!";
	  }
	  else 
	  {
		  return "Fail to Verify certificate of CA!";
	  }
  }
}
