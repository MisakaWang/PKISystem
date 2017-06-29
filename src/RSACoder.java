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
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public abstract class RSACoder {
	public static  String Certificate = null;
	public static int Header=0;
	public static Boolean flag=false;
	public static int AllNum=4;
    private static PrivateKey privateKey;
    public static String[] publicKeyString={"MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJY4OqNN0kSr79WojHwSVVvch+oaazv4QJfQ+A9HNSgxOPXUfiXB7USat7PFkMN5UHdSXyZlIt0xlAtROPcZGk0CAwEAAQ==","MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI01Ce6LrNb8kdmjk9NJ7EgWwGLn51mf3XE4hFnNyq2WOiKK83sZjDVjIkYAFf+SpKIGohm54Jtfx54OypOtONUCAwEAAQ==","MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIYbJMsawGSGjH69jpj0VkDEZRvX7v64DsXjZtzRjY7nnjd7xs21dRe1wxKwvughYhl8TyTeWbNhxxdEg55BfL8CAwEAAQ==","MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJZ58VkGBM8MZxLYvY/QLVQigCkIBlhGhFo7Kymu0DKyTS9vJDMllWD2JJ/yN0M6Cw3fM5Bp/lVWVMx39QZz1i8CAwEAAQ=="};  
<<<<<<< HEAD
    public static String privateKeyString="MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAljg6o03SRKvv1aiMfBJVW9yH6hprO/hAl9D4D0c1KDE49dR+JcHtRJq3s8WQw3lQd1JfJmUi3TGUC1E49xkaTQIDAQABAkBt0rQD+fwU2oLf+Hakqp6fJy5yl1f4ovlORKdmPA//zkVbzgs6YOTYS7PNhMPxKUdMArmcK6y8yuSmev65D3ohAiEA0RDJiw1cRXxyXfaTf9tlB6dU5Ypx5f7m1XA3hJxtDwkCIQC38YGBcRRwufVS/rbGAND0YWvu6mqK4RU/dh+PMtP+JQIgJX+4VrunwELpFJeEr1s/zNajsKRbd6B38PK20o9oNFkCIQCpmSk5OhQIo8zIS0YmZlZDHRgxXz4A8utuafFLTSgdaQIhAMIIdLAU9g5lxTYZ5Pa7NV8zrK4V8QdDNFPalb1XOqFp";
=======
    public static String privateKeyString="MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAjTUJ7ous1vyR2aOT00nsSBbAYufnWZ/dcTiEWc3KrZY6IorzexmMNWMiRgAV/5KkogaiGbngm1/Hng7Kk6041QIDAQABAkBZzfGTFKHH2LmpgK7zIYB0GIQiYvpvtYxQBXGm64qK+pauioT/CR4TrGA7di3CbTBhsXKe1cGMJQdgBuKAI6gBAiEA8yGeHNg2bE41foAZ29rc+vsGPLtW7O+q+rb28abxSrUCIQCUrl5rdePUjDZDhB2YoPUIADjHJe04DocuKVbsPcVpoQIgLn3bMkDWB1fdOtdcGoJ7hzLBOpPIR358/3xFNGhr85ECIG/lErY5EO+jXitNwKBfcklFMXXfOzpW5LF+9yXwDyRBAiA4BJ9kVaajbBAxMNS/YD3fxX1kv/D7cFu6WK4SgqlYqA==";
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de

    public static PublicKey[] publicKey=new PublicKey[4];
    public static Map<Integer, String> NameTable=new HashMap<Integer,String>()
    		{{
    			put(0,"CA");
    			put(1, "ZhouYi");
    			put(2, "WangShiSheng");
    			put(3, "TangXie");
    		}};

<<<<<<< HEAD
      public static void KeyInit() throws Exception//鍒濆鍖�
      {
    	  //鍒濆鍖栭挜鍖欎覆
=======
      public static void KeyInit() throws Exception//初始化
      {
    	  //初始化钥匙串
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
    	  	privateKey=GeneratePrivateKey(privateKeyString);
    	  	for(int i=0;i<AllNum;i++)
    	  	{
    	  		publicKey[i]=GeneratePublicKey(publicKeyString[i]);
    	  	}
    	  	flag=true;
      }
<<<<<<< HEAD
      //灏哹ase64缂栫爜鍚庣殑绉侀挜瀛楃涓茶浆鎴怭ublicKey瀹炰緥 
=======
      //将base64编码后的私钥字符串转成PublicKey实例 
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
    private static PublicKey GeneratePublicKey(String publicKey) throws Exception{  
    		
        byte[ ] keyBytes=Base64.getDecoder().decode(publicKey.getBytes());  
        X509EncodedKeySpec keySpec=new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");  
        return keyFactory.generatePublic(keySpec);    
    }  
      
<<<<<<< HEAD
    //灏哹ase64缂栫爜鍚庣殑绉侀挜瀛楃涓茶浆鎴怭rivateKey瀹炰緥  
=======
    //将base64编码后的私钥字符串转成PrivateKey实例  
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
    private static PrivateKey GeneratePrivateKey(String privateKey) throws Exception{  
   
        byte[ ] keyBytes=Base64.getDecoder().decode(privateKey.getBytes());  
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");  
        return keyFactory.generatePrivate(keySpec);  
    }  
     
<<<<<<< HEAD
    //鍏挜鍔犲瘑  
=======
    //公钥加密  
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
    public static String PublicEncrypt(String content, PublicKey publicKey) throws Exception{  

    		byte[] ContentBytes=content.getBytes();
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        byte[] EncryptedBytes= cipher.doFinal(ContentBytes);  
        return Base64.getEncoder().encodeToString(EncryptedBytes);
    }  
<<<<<<< HEAD
   //绉侀挜鍔犲瘑
=======
   //私钥加密
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
     public static String PrivateEncrypt(String content,PrivateKey privatekey) throws Exception
     {
	    	 
     	    byte[] ContentBytes=content.getBytes();
    	 	Cipher cipher=Cipher.getInstance("RSA");
    	 	cipher.init(Cipher.ENCRYPT_MODE, privatekey);  
    	 	byte[] EncryptedBytes= cipher.doFinal(ContentBytes);  
            return Base64.getEncoder().encodeToString(EncryptedBytes);
     }
<<<<<<< HEAD
     //鍏挜瑙ｅ瘑
=======
     //公钥解密
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
     public static String PublicDecrypt(String Encrypted, PublicKey publicKey) throws Exception{ 
    	 	
    	     Cipher cipher=Cipher.getInstance("RSA");  
         cipher.init(Cipher.DECRYPT_MODE, publicKey);
         byte[] DecryptedBytes=cipher.doFinal(Base64.getDecoder().decode(Encrypted));
         return new String(DecryptedBytes);
     }  
<<<<<<< HEAD
    //绉侀挜瑙ｅ瘑  
=======
    //私钥解密  
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
    public static String PrivateDecrypt(String Encrypted, PrivateKey privateKey) throws Exception{ 

        Cipher cipher=Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
        byte[] DecryptedBytes=cipher.doFinal(Base64.getDecoder().decode(Encrypted));
        return new String(DecryptedBytes);
    }  
<<<<<<< HEAD
    //鑾峰彇鏃堕棿鎴�
=======
    //获取时间戳
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
    public static String GettimeStamp()
    {
    		Date date=new Date();
    		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhh");
    		return df.format(date);
    }
<<<<<<< HEAD
    //绛惧悕鐢熸垚
=======
    //签名生成
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
    public static String GetSig(String data,PrivateKey privatekey) throws Exception
    {
    		Signature SigFactory=Signature.getInstance("MD5withRSA");
    		SigFactory.initSign(privatekey);
    		SigFactory.update(data.getBytes());
    		byte[] signed=SigFactory.sign();
    		return new String(Base64.getEncoder().encode(signed));
    }
<<<<<<< HEAD
    //绛惧悕楠岃瘉
=======
    //签名验证
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
    public static boolean VerifySig(int Header,String Sign,PublicKey publicKey) throws Exception
    {
    		Signature SigFactory=Signature.getInstance("MD5withRSA");
    		SigFactory.initVerify(publicKey);
    		String TrueSig=NameTable.get(Header);
    		SigFactory.update(TrueSig.getBytes());
    		return SigFactory.verify(Base64.getDecoder().decode(Sign.getBytes()));
    	
    }
    /*
<<<<<<< HEAD
    //0濉厖
=======
    //0填充
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
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
    }*/
    
<<<<<<< HEAD
    //鐢熸垚绉橀挜瀵癸紝澶囩敤
=======
    //生成秘钥对，备用
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
    public static void getKeyPair(int keyLength,int PublicKeyNum) throws Exception{  
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");  
        keyPairGenerator.initialize(keyLength);        
        KeyPair tmpKeyPair=keyPairGenerator.generateKeyPair();  
        publicKey[PublicKeyNum]=tmpKeyPair.getPublic();
        privateKey=tmpKeyPair.getPrivate();
    }  
<<<<<<< HEAD
    //鑾峰緱绉侀挜
=======
    //获得私钥
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
    public static PrivateKey getPrivateKey() throws Exception
    {
    		if(flag!=false)
    			return privateKey;
    		else
    		{	
    			if(flag==false)
    				KeyInit();
    			return privateKey;
    		}
    }
<<<<<<< HEAD
    //鑾峰緱鍏挜
=======
    //获得公钥
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
    public static PublicKey getPublicKey(int num) throws Exception
    {
    		if(num<AllNum&&publicKey[num]!=null)
    			return publicKey[num];
    		else 
    		{	
    			if(num>=AllNum)
    			{
    				System.out.println("Out of range!");
        			return null;
    			}
    				KeyInit();
    				return publicKey[num];   			
    		}
    }
<<<<<<< HEAD

=======
    //证书的验证
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
    public static boolean VerifyCert(String Cert,String Header) throws Exception
    {
    		String myCert=PublicDecrypt(Cert, getPublicKey(0));
    		
    		String time=GettimeStamp();
    		if(Header.equals(myCert.substring(0, 1))&&time.equals(myCert.substring(1,11)))
    			return true;
    		else 
    			return false;
    }
<<<<<<< HEAD

=======
    //0截断
    /*public static String Truncate(String Originial,int Gap)
    {
    		return Originial.substring(0,Originial.length()-Gap);
    }*/
>>>>>>> d36f1a5940359af85a1a60ef452aa152a80ab6de
}
 