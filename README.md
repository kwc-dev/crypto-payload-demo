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
Payload: {"ciphertext":"...","iv":"...","algorithm":"AES/CBC/PKCS5Padding"}
Restored: [username:khaiwah, role:admin, active:true]

## Next Steps
- Split classes into separate files when moving to Gradle
- Integrate with Blazor for cross-platform payload handling
- Add unit tests
