package controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {
	public static final String SHA256 = "SHA-256";
	public static final String MD5 = "MD5";
	
	protected String information;
	protected String standard;
	
	public Crypto(String information, String standard) {
		super();
		this.information = information;
		this.standard = standard;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}
	
	public String encrypt(){
		String information = getInformation();
		
		MessageDigest messageDigest;
		StringBuilder hexString = null;
		
		try {
			messageDigest = MessageDigest.getInstance(getStandard());
			byte[] hash = messageDigest.digest(information.getBytes(StandardCharsets.UTF_8));
			hexString = new StringBuilder(2* hash.length);
			for(int i=0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) {
					hexString.append("0");
				}
				hexString.append(hex);	
			}
			return hexString.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error on Encrypt process");
			e.printStackTrace();
			return "";
		}
		
	}
	

}
