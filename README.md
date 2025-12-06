# Crypto Payload Demo

This project demonstrates AES encryption and decryption of JSON payloads in Groovy.  
It shows how to generate a secure payload (`ciphertext`, `iv`, `algorithm`) and then restore the original JSON data.

## Prerequisites
- Groovy 3.x
- Java JDK 8+
- VS Code with Code Runner extension (optional)

## Running the Demo
- Open `DemoApp.groovy` in VS Code
- Right-click → Run Code
- or from the terminal `groovy DemoApp.groovy`

## Example Output

When you run the demo, you’ll see something like:

```bash
🔐 Payload:
{
  "ciphertext": "U2FsdGVkX1+...",
  "iv": "3f2a1c4d5e6f7g8h9i0j==",
  "algorithm": "AES/CBC/PKCS5Padding"
}

✅ Restored:
{
  "username": "kwc",
  "role": "admin",
  "active": true
}
```

## Next Steps
- Split classes into separate files when moving to Gradle
- Integrate with Blazor for cross-platform payload handling
- Add unit tests
