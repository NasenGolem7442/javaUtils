This is my personal Java utility collection that I find useful. The documentation is provided via JavaDoc.

If you want to use it yourself and are using Maven, I highly recommend following these steps:
1. Clone this repository.
2. Run `mvn test` to test if everything works.
3. Run `mvn clean install` to compile the code. Read the console output to identify the target.
4. On Windows: Move the compiled file(s) to `C:\Users\YourUser\.m2\repository\org\nasengolem\util\VersionID` if they aren't there already.

After following these steps, you should be able to add my javaUtils to your project by adding the following to your dependencies:

```xml
<dependency>
  <groupId>org.nasengolem</groupId>
  <artifactId>util</artifactId>
  <version>latest_version_id_here</version>
</dependency>
```
