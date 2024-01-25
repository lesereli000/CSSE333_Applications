package main;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

public class Login {

	private Connect connect;
	private static final Random RANDOM = new SecureRandom();
	private static final Base64.Encoder enc = Base64.getEncoder();
	private static final Base64.Decoder dec = Base64.getDecoder();
	
	public Login(Connect connect) {
		this.connect = connect;
	}
	
	public boolean login(String username, String password) {
		Connection con = this.connect.getConnection();
		String query = "SELECT PasswordSalt, PasswordHash FROM [Login] WHERE Username = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next()) {
				return false;
			}
			
			String realPass = rs.getString("PasswordHash");
			byte[] salt = dec.decode(rs.getString("PasswordSalt"));
			
			String userPass = this.hashPassword(salt, password);
			
			System.out.println(userPass);
			System.out.println(realPass);
			
			if(userPass.equals(realPass)) {
				return true;
			}
		} catch (SQLException e) {
			// show non-detailed error
			e.printStackTrace();
		}
		return false;
	}

	public boolean register(String username, String password) {
		byte[] salt = this.getNewSalt();
		String newPass = this.hashPassword(salt, password);
		String newSalt = this.getStringFromBytes(salt);
		
		Connection con = connect.getConnection();
		try {
			CallableStatement cs = con.prepareCall("{? = call RegisterUser(?, ?, ?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, username);
			cs.setString(3, newSalt);
			cs.setString(4, newPass);
			
			cs.execute();
			
			int returnCode = cs.getInt(1);
			return returnCode == 0;
		} catch (SQLException e) {
			// show non-detailed error
			e.printStackTrace();
		}
		return false;
	}
	
	public byte[] getNewSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		return salt;
	}
	
	public String getStringFromBytes(byte[] data) {
		return enc.encodeToString(data);
	}

	public String hashPassword(byte[] salt, String password) {

		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f;
		byte[] hash = null;
		try {
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		}
		return getStringFromBytes(hash);
	}
	
}
