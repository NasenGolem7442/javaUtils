# NasenGolem Java Utility Collection

## Introduction
This collection of Java utilities provides a range of tools designed to simplify and enhance Java development. Featuring custom DataStructures and utility methods.

## Features
- CappedResizableList: ArrayList like data structure with capped size that allows resizing the List in constant time. Usefull if you often want to discard the last n elements of your list.  

Please take a look at my JavaDoc for detailed Information.

## Installation
1. **Clone the repository:**
   ```
   git clone [repository URL]
   ```
2. **Run the tests:**
   ```
   mvn test
   ```
3. **Compile the code:**
   ```
   mvn clean install
   ```
   Move the compiled files to your Maven repository:
   ```
   mv [compiled files] to C:\Users\YourUser\.m2\repository\org\nasengolem\util\[VersionID]
   ```
4. **Add to your project:**
   Add the following dependency to your project's `pom.xml`:
   ```xml
   <dependency>
     <groupId>org.nasengolem</groupId>
     <artifactId>util</artifactId>
     <version>[latest_version_id_here]</version>
   </dependency>
   ```
