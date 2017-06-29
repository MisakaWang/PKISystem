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
<<<<<<< HEAD
	if(SendNum==0)
		return CreatePackageToCA(Plain);
=======
<<<<<<< HEAD
	if(SendNum==0)
		return CreatePackageToCA(Plain);
=======
	/*if(SendNum==0)
		return CreatePackageToCA(Plain);*/
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
	ToSend.SendHeader=Integer.toString(SendNum);
	ToSend.Certifacte=RSACoder.Certificate;
	ToSend.Sig=RSACoder.GetSig(Name, RSACoder.getPrivateKey());
	ToSend.Plain=RSACoder.PublicEncrypt(Plain, RSACoder.getPublicKey(SendNum));
<<<<<<< HEAD

=======
<<<<<<< HEAD

=======
	/*if(ToSend.Sig==null)
		System.out.println("Sig miss");
	if(ToSend.Certifacte==null)
		System.out.println("cert miss");
	if(ToSend.Plain==null)
		System.out.println("plain miss");*/
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
	PackageSend = ToSend.Header+ToSend.SendHeader+ToSend.Sig + ToSend.Certifacte + ToSend.Plain;
	return PackageSend;
}

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
public static String DecreatePackage(String InputStream) throws Exception
{
	  ToReceive.Header = InputStream.substring(0, 1);
	  ToReceive.SendHeader=InputStream.substring(1,2);
	  if(ToReceive.Header=="0")
	  		return DecreatePackageFromCA(InputStream);
<<<<<<< HEAD
	  
=======
=======
public  static String DecreatePackage(String InputStream) throws Exception
{
	  ToReceive.Header = InputStream.substring(0, 1);
	  ToReceive.SendHeader=InputStream.substring(1,2);
	  //if(ToReceive.Header=="0")
	  		//return DecreatePackageFromCA(InputStream);
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
	  ToReceive.Sig = InputStream.substring(2, 90);
	  ToReceive.Certifacte = InputStream.substring(90, 178);
	  ToReceive.Plain = InputStream.substring(178, 266);
	  PackageRecv=InputStream;
<<<<<<< HEAD
	  if(RSACoder.VerifySig(Integer.parseInt(ToReceive.Header), ToReceive.Sig, RSACoder.getPublicKey(Integer.parseInt(ToReceive.Header)))/*&&
			  RSACoder.VerifyCert(ToReceive.Certifacte, ToReceive.Header)*/)
=======
<<<<<<< HEAD
	  if(RSACoder.VerifySig(Integer.parseInt(ToReceive.Header), ToReceive.Sig, RSACoder.getPublicKey(Integer.parseInt(ToReceive.Header)))/*&&
			  RSACoder.VerifyCert(ToReceive.Certifacte, ToReceive.Header)*/)
=======
	  if(RSACoder.VerifySig(Integer.parseInt(ToReceive.Header), ToReceive.Sig, RSACoder.getPublicKey(Integer.parseInt(ToReceive.Header)))&&
			  RSACoder.VerifyCert(ToReceive.Certifacte, ToReceive.Header))
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
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

<<<<<<< HEAD
private static String CreatePackageToCA(String Plain) throws Exception
=======
<<<<<<< HEAD
private static String CreatePackageToCA(String Plain) throws Exception
=======
public static String CreatePackageToCA(String Plain) throws Exception
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
{
	ToSend.SendHeader="0";
	ToSend.Sig=RSACoder.GetSig(Name, RSACoder.getPrivateKey());
	ToSend.Plain=RSACoder.PublicEncrypt(Plain, RSACoder.getPublicKey(0));	
	PackageSend=ToSend.Header+ToSend.SendHeader+ToSend.Sig+ToSend.Plain;
	return PackageSend;

}
<<<<<<< HEAD
private static String DecreatePackageFromCA(String InputStream) throws Exception
=======
<<<<<<< HEAD
private static String DecreatePackageFromCA(String InputStream) throws Exception
=======
public static String DecreatePackageFromCA(String InputStream) throws Exception
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
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
<<<<<<< HEAD
		  return "Fail to Verify certificate of CA!"; 
=======
<<<<<<< HEAD
		  return "Fail to Verify certificate of CA!"; 
=======
		  return "Fail to Verify certificate of CA!";
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
>>>>>>> c5335c34a39622019ab7989759fa9ce16e75d89d
	  }
  }
}
