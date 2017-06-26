import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Struct;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class Client {
	public int Header=0;
	private static int AllNum=4;
	public static String data="CA20170klj;kjCaoLibin"; 
    public static String[] publicKeyString={"MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJY4OqNN0kSr79WojHwSVVvch+oaazv4QJfQ+A9HNSgxOPXUfiXB7USat7PFkMN5UHdSXyZlIt0xlAtROPcZGk0CAwEAAQ==","MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI01Ce6LrNb8kdmjk9NJ7EgWwGLn51mf3XE4hFnNyq2WOiKK83sZjDVjIkYAFf+SpKIGohm54Jtfx54OypOtONUCAwEAAQ==","MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIYbJMsawGSGjH69jpj0VkDEZRvX7v64DsXjZtzRjY7nnjd7xs21dRe1wxKwvughYhl8TyTeWbNhxxdEg55BfL8CAwEAAQ==","MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJZ58VkGBM8MZxLYvY/QLVQigCkIBlhGhFo7Kymu0DKyTS9vJDMllWD2JJ/yN0M6Cw3fM5Bp/lVWVMx39QZz1i8CAwEAAQ=="};  
    public static String privateKeyString="MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAljg6o03SRKvv1aiMfBJVW9yH6hprO/hAl9D4D0c1KDE49dR+JcHtRJq3s8WQw3lQd1JfJmUi3TGUC1E49xkaTQIDAQABAkBt0rQD+fwU2oLf+Hakqp6fJy5yl1f4ovlORKdmPA//zkVbzgs6YOTYS7PNhMPxKUdMArmcK6y8yuSmev65D3ohAiEA0RDJiw1cRXxyXfaTf9tlB6dU5Ypx5f7m1XA3hJxtDwkCIQC38YGBcRRwufVS/rbGAND0YWvu6mqK4RU/dh+PMtP+JQIgJX+4VrunwELpFJeEr1s/zNajsKRbd6B38PK20o9oNFkCIQCpmSk5OhQIo8zIS0YmZlZDHRgxXz4A8utuafFLTSgdaQIhAMIIdLAU9g5lxTYZ5Pa7NV8zrK4V8QdDNFPalb1XOqFp";
    private static PrivateKey privateKey;
    public static String Name="ZhouYi";
    public static int[] Gap=new int[2];
    public static PublicKey[] publicKey=new PublicKey[4];
    static Map<Integer, String> NameTable=new HashMap<Integer,String>()
    		{{
    			put(0,"CA");
    			put(1, "ZhouYi");
    			put(2, "WangShiSheng");
    			put(3, "TangXie");
    		}};
    public static void main(String[] args) throws Exception {  
    	KeyInit();
        //私钥加密  
    		
        byte[] encryptedBytes=PrivateEncrypt(data, privateKey); 
        System.out.println("加密后长度为："+new String(encryptedBytes).length());  
          
        //公钥解密  
        byte[] decryptedBytes=PublicDecrypt(encryptedBytes, publicKey[0]);
        System.out.println("解密后："+new String(decryptedBytes));  
        String sig=GetSig("ZhouYi", privateKey);
        System.out.println("签名长度为:"+sig.length());
        System.out.println("验证结果"+VerifySig(1,sig, publicKey[0]));
    }  
    
      public static void KeyInit() throws Exception//初始化
      {
    	  //初始化钥匙串
    	  	privateKey=getPrivateKey(privateKeyString);
    	  	for(int i=0;i<AllNum;i++)
    	  	{
    	  		publicKey[i]=getPublicKey(publicKeyString[i]);
    	  	}
      }
      //将base64编码后的私钥字符串转成PublicKey实例 
    public static PublicKey getPublicKey(String publicKey) throws Exception{  
        byte[ ] keyBytes=Base64.getDecoder().decode(publicKey.getBytes());  
        X509EncodedKeySpec keySpec=new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");  
        return keyFactory.generatePublic(keySpec);    
    }  
      
    //将base64编码后的私钥字符串转成PrivateKey实例  
    public static PrivateKey getPrivateKey(String privateKey) throws Exception{  
        byte[ ] keyBytes=Base64.getDecoder().decode(privateKey.getBytes());  
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");  
        return keyFactory.generatePrivate(keySpec);  
    }  
     
    //公钥加密  
    public static byte[] PublicEncrypt(String content, PublicKey publicKey) throws Exception{  
    	
    	byte[] ContentBytes=content.getBytes();
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        return cipher.doFinal(ContentBytes);  
    }  
   //私钥加密
     public static byte[] PrivateEncrypt(String content,PrivateKey privatekey) throws Exception
     {
     	    byte[] ContentBytes=content.getBytes();
     	    
    	 	Cipher cipher=Cipher.getInstance("RSA");
    	 	cipher.init(Cipher.ENCRYPT_MODE, privatekey);  
    	    return cipher.doFinal(ContentBytes); 
     }
     //公钥解密
     public static byte[] PublicDecrypt(byte[] ContentByte, PublicKey publicKey) throws Exception{ 
         Cipher cipher=Cipher.getInstance("RSA");  
         cipher.init(Cipher.DECRYPT_MODE, publicKey);  
         return cipher.doFinal(ContentByte);  
     }  
    //私钥解密  
    public static byte[] PrivateDecrypt(byte[] ContentByte, PrivateKey privateKey) throws Exception{ 

        Cipher cipher=Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
        return cipher.doFinal(ContentByte); 
    }  
    //获取时间戳
    public static String GettimeStamp()
    {
    	Date date=new Date();
    	SimpleDateFormat df=new SimpleDateFormat("aaaabbccdd");
    	return df.format(date);
    }
    //签名生成
    public static String GetSig(String data,PrivateKey privatekey) throws Exception
    {
    	Signature SigFactory=Signature.getInstance("MD5withRSA");
    	SigFactory.initSign(privatekey);
    	SigFactory.update(data.getBytes());
    	byte[] signed=SigFactory.sign();
    	return new String(Base64.getEncoder().encode(signed));
    }
    //签名验证
    public static boolean VerifySig(int Header,String Sign,PublicKey publicKey) throws Exception
    {
    	Signature SigFactory=Signature.getInstance("MD5withRSA");
    	SigFactory.initVerify(publicKey);
    	String TrueSig=NameTable.get(Header);
    	SigFactory.update(TrueSig.getBytes());
    	return SigFactory.verify(Base64.getDecoder().decode(Sign.getBytes()));
    	
    }
    //0填充
    public static String FullFill(String CipherText,int GapNum)
    {
    		if(CipherText.length()==70)
    			return CipherText;
    		int gap=70-CipherText.length();
    		Gap[GapNum]=gap;
    		for(int i=1;i<=gap;i++)
    		{
    			CipherText+="0";
    		}
    		
    		return CipherText;
    }
    //生成秘钥对，备用
    public static void getKeyPair(int keyLength,int PublicKeyNum) throws Exception{  
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");  
        keyPairGenerator.initialize(keyLength);        
        KeyPair tmpKeyPair=keyPairGenerator.generateKeyPair();  
        publicKey[PublicKeyNum]=tmpKeyPair.getPublic();
        privateKey=tmpKeyPair.getPrivate();
    }  
}
