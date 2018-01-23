package com.zwj.blog;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.junit.Before;
import org.junit.Test;

public class EncryptorTest {

	StandardPBEStringEncryptor encryptor;

	EnvironmentStringPBEConfig config;

	@Before
	public void setUp() {
		encryptor = new StandardPBEStringEncryptor();
		EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setPassword("123456");
		encryptor.setConfig(config);
	}

	@Test
	public void encryptPassword() {
		encrypt("postgres");
		encrypt("intern@123");
		encrypt("secret");
		encrypt("password");
		encrypt("ciemdus");
		encrypt("mongodb://user:secret@192.168.1.196:27017/sample");
	}

	void encrypt(String plaintext) {
		System.out.printf("Encrypt '%s' -> '%s'\n", plaintext, encryptor.encrypt(plaintext));
	}
}
