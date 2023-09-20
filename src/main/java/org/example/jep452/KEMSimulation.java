package org.example.jep452;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;

public class KEMSimulation {

	public static final String AES = "AES";
	public static final String AES_CBC_PKCS_5_PADDING = "AES/CBC/PKCS5Padding";

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, DecapsulateException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidParameterSpecException {
			byte[] msg = "hello world".getBytes(StandardCharsets.UTF_8);
			byte[] iv = new byte[16];

			KeyPairGenerator kpg = KeyPairGenerator.getInstance("X25519");
			KeyPair kp = kpg.generateKeyPair();

			// sender side
			SecretKey messageKey = KeyGenerator.getInstance(AES).generateKey();
			Cipher smc = Cipher.getInstance(AES_CBC_PKCS_5_PADDING);
			smc.init(Cipher.ENCRYPT_MODE, messageKey , new IvParameterSpec(iv));
			byte[] ciphertext = smc.doFinal(msg);

			KEM kem1 = KEM.getInstance("DHKEM");
			KEM.Encapsulator sender = kem1.newEncapsulator(kp.getPublic());
			KEM.Encapsulated encapsulated = sender.encapsulate(0, sender.secretSize(), AES);

			//wrap the shared key
			Cipher kc = Cipher.getInstance(AES);
			kc.init(Cipher.WRAP_MODE, encapsulated.key());
			byte[] sek = kc.wrap(messageKey);

			// receiver side
			KEM kem2 = KEM.getInstance("DHKEM");
			KEM.Decapsulator receiver = kem2.newDecapsulator(kp.getPrivate());
			SecretKey sharedSecret2 = receiver.decapsulate(encapsulated.encapsulation(), 0, receiver.secretSize(), AES);

			//unwrap the shared key
			Cipher rkc = Cipher.getInstance(AES);
			rkc.init(Cipher.UNWRAP_MODE, sharedSecret2);
			messageKey = (SecretKey) rkc.unwrap(sek, AES, Cipher.SECRET_KEY);

			//decrypt the message
			Cipher cipher = Cipher.getInstance(AES_CBC_PKCS_5_PADDING);
			cipher.init(Cipher.DECRYPT_MODE, messageKey,  new IvParameterSpec(iv));
			byte[] result = cipher.doFinal(ciphertext);

			System.out.println(new String(result));

		}
}
