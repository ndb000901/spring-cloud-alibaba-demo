package work.hello.demo.hls.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    // 示例密钥，生产环境请从安全存储中获取
    private static final String AES_KEY_HEX = "bba4d3caf0c67ada60a7f1fff02f7562";

    @GetMapping(value = "/api/hls/auth", params = { "token" }, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getEncryptionKey(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestParam("token") String token
    ) {

        System.out.println("authHeader: " + authHeader);
        System.out.println("token: " + token);
        // 可选：校验 Authorization token
        if (authHeader == null || !authHeader.equals("Bearer xxx")) {

//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 将十六进制字符串转换为字节数组
        byte[] keyBytes = hexStringToByteArray(AES_KEY_HEX);
        return ResponseEntity.ok(keyBytes);
    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                               + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
