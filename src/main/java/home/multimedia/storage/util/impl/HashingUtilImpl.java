package home.multimedia.storage.util.impl;

import home.multimedia.storage.util.HashingUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by nick on 6/9/14.
 */
@Service("hashingUtil")
public class HashingUtilImpl implements HashingUtil {

    private static final String hashAlgorithm = "SHA-256";

    private static final Logger logger = Logger.getLogger(HashingUtilImpl.class);

    @Override
    public String hash(String input) {
        MessageDigest md = getMessageDigest(hashAlgorithm);

        if (md == null) {
            return null;
        }

        md.update(input.getBytes());
        byte hashByteData[] = md.digest();

        return convertBytesToHexString(hashByteData);
    }

    private MessageDigest getMessageDigest(String hashAlgorithm) {
        try {
            return MessageDigest.getInstance(hashAlgorithm);
        } catch (NoSuchAlgorithmException e) {
            logger.error(String.format("Can not find hash algorithm: ", hashAlgorithm));
            return null;
        }
    }

    private String convertBytesToHexString(byte[] hashByteData) {
        StringBuilder stringBuilder = new StringBuilder();

        for (byte hashByte : hashByteData) {
            stringBuilder.append(String.format("%02x", hashByte));
        }

        return stringBuilder.toString();
    }
}