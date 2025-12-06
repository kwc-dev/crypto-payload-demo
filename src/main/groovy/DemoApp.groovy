import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.security.SecureRandom
import java.util.Base64

class DemoApp {
    static void main(String[] args) {
        // Pretend this comes from Key Vault
        String base64Key = "MDEyMzQ1Njc4OUFCQ0RFRg==" 

        def service = new PayloadCryptoService(base64Key)

        // Encrypt a map
        def payload = service.encryptMap([username: "kwc", role: "admin", active: true])
        println "Payload: ${JsonOutput.toJson(payload)}"

        // Decrypt the payload
        def restored = service.decryptPayload(payload)
        println "Restored: $restored"
    }
}

class PayloadCryptoService {

    private final String algorithm = "AES/CBC/PKCS5Padding"
    private final SecretKeySpec key

    PayloadCryptoService(String base64Key) {
        byte[] keyBytes = Base64.decoder.decode(base64Key)
        this.key = new SecretKeySpec(keyBytes, "AES")
    }

    Map<String, String> encryptMap(Map<String, Object> data) {
        String json = JsonOutput.toJson(data)
        Cipher cipher = Cipher.getInstance(algorithm)

        IvParameterSpec ivSpec = randomIv(cipher.blockSize)
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec)

        byte[] cipherBytes = cipher.doFinal(json.bytes)

        return [
            ciphertext: Base64.encoder.encodeToString(cipherBytes),
            iv        : Base64.encoder.encodeToString(ivSpec.getIV()),
            algorithm : algorithm
        ]
    }

    Map<String, Object> decryptPayload(Map<String, String> payload) {
        Cipher cipher = Cipher.getInstance(algorithm)

        byte[] ivBytes = Base64.decoder.decode(payload.iv)
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes)

        byte[] cipherBytes = Base64.decoder.decode(payload.ciphertext)

        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec)
        byte[] plainBytes = cipher.doFinal(cipherBytes)

        String json = new String(plainBytes)
        return new JsonSlurper().parseText(json)
    }

    private IvParameterSpec randomIv(int blockSize) {
        byte[] iv = new byte[blockSize]
        SecureRandom.instanceStrong.nextBytes(iv)
        return new IvParameterSpec(iv)
    }
}
