package com.promise.auth.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PasswordUtil
{

    public static class HashResult
    {

        private byte[] hash;
        private byte[] salt;

        public HashResult()
        {

        }

        public byte[] getHash()
        {
            return hash;
        }

        public void setHash(byte[] hash)
        {
            this.hash = hash;
        }

        public byte[] getSalt()
        {
            return salt;
        }

        public void setSalt(byte[] salt)
        {
            this.salt = salt;
        }

    }

    /**
     * Return the hash result of a password.
     * 
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static HashResult hashPassword(char[] password)
            throws NoSuchAlgorithmException
    {
        HashResult hashResult = new HashResult();
        final MessageDigest md = MessageDigest.getInstance("SHA-256");
        // should avoid the creation of String object, because it's immutable.
        md.update(toBytes(password));
        hashResult.setHash(md.digest());
        hashResult.setSalt(null);
        return hashResult;

    }

    /**
     * Convert the char[] to byte[]
     *
     * @param chars
     * @return
     */
    public static byte[] toBytes(char[] chars)
    {
        final CharBuffer charBuffer = CharBuffer.wrap(chars);
        final ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
        final byte[] bytes = Arrays.copyOfRange(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
        Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
        return bytes;
    }

}
