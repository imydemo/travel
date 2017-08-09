package org.vie.mydemo520.service.sys;


import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;



import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vie.mydemo520.mapper.sys.UserMapper;
import org.vie.mydemo520.entity.sys.User;
import org.vie.mydemo520.common.Global;
import org.vie.mydemo520.common.utils.Exceptions;

@Service
public class UserService {
	 //private static SecureRandom random = new SecureRandom();
     @Autowired
     UserMapper mapper;
	
     public User getByUsername(String username) {
		// TODO Auto-generated method stub
		User user= mapper.getByUsername(username);
		return user;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 * @throws NoSuchAlgorithmException 
	 * Global.HASH_INTERATIONS 加密循环次数
	 * Global.SALT_INTERATIONS 产生盐循环次数
	 */
	private  void entryptPassword(User user) throws NoSuchAlgorithmException {
		byte[] salt = generateSalt(10);
		user.setSalt(Hex.encodeHexString(salt));
		System.out.println("hello"+salt);
		System.out.println("bonjour"+user.getPassword().getBytes());
		byte[] hashPassword =sha1(user.getPassword().getBytes(),"SHA-1", salt, 10);
		user.setPassword(Hex.encodeHexString(hashPassword));
	}
	
	/**
	 * 生成随机的Byte[]作为salt.
	 * 根据算法名称返回SecureRandom对象，调用 nextBytes 来生成随机字节
	 * @param numBytes byte数组的大小
	 * @throws NoSuchAlgorithmException 
	 */
	    private   byte[] generateSalt(int numBytes) throws NoSuchAlgorithmException {
	    	byte [] b=new byte[numBytes];
	    	Random random=new Random();
	    	random.nextBytes(b);
		    return b;
	}
	/**
	 * 对字符串进行散列, 支持md5与sha1算法.
	 */
	private  byte[] sha1(byte[] input, String algorithm, byte[] salt, int iterations) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);

			if (salt != null) {
				digest.update(salt);
			}

			byte[] result = digest.digest(input);

			for (int i = 1; i < iterations; i++) {
				digest.reset();
				result = digest.digest(result);
			}
			return result;
		} catch (GeneralSecurityException e) {
			throw Exceptions.unchecked(e);
		}
	}

	public List<User> queryUser() {
		// TODO Auto-generated method stub
		return mapper.queryUser();
	}

	public void save(User user) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		entryptPassword(user);
		mapper.save(user);
		mapper.deleteUserRole(user);
		if (user.getRoles().size()>0){
		mapper.saveUserRole(user);
		}
	}

	public User get(int id) {
		// TODO Auto-generated method stub
		return mapper.get(id);
	}

	public void delete(List<Integer> idlist) {
		// TODO Auto-generated method stub
		 mapper.delete(idlist);
	}

}
