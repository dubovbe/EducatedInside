package JDBC;

import org.apache.commons.codec.digest.DigestUtils;

public class DigestService {
    public String digest(String password) {
        return DigestUtils.md5Hex(password);
    }
    public static void main(String[] args) {
        DigestService service = new DigestService();
        System.out.println(service.digest("password"));
    }
}
