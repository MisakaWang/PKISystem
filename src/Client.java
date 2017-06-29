import java.util.Base64;

public class Client {
	
    public static void main(String[] args) throws Exception {  
    	RSACoder.KeyInit();
    	//CAServer.GeneraeCert();
    String tmp=RSACoder.PublicEncrypt("hello", RSACoder.getPublicKey(1));
    System.out.println(RSACoder.PrivateDecrypt(tmp, RSACoder.getPrivateKey()));
    	
  }  
    
     
}