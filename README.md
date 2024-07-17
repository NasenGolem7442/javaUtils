# NasenGolem Java Utility Collection

## Introduction
This collection of Java utilities provides a range of tools designed to simplify and enhance Java development. Featuring custom DataStructures and utility methods.

## Features
- CappedResizableList: ArrayList like data structure with capped size that allows resizing the List in constant time. Usefull if you often want to discard the last n elements of your list.  

Please take a look at my JavaDoc for detailed Information.

## Installation
You can either download the compiled source code or you can compile it yourself.

To compile it yourself, go to [releases](https://github.com/NasenGolem7442/javaUtils/releases) and download the latest util-v#.#.#.zip file. Unpack it and move the files to C:\Users\YourUser\.m2\repository\org\nasengolem\util\[VersionID]

If you want to compile it yourself, follow these steps:
1. **Clone the repository:**
   ```
   git clone [https://github.com/NasenGolem7442/javaUtils.git](https://github.com/NasenGolem7442/javaUtils.git)
   ```
2. **Run the tests:**
   ```
   mvn test
   ```
3. **Compile the code:**
   ```
   mvn clean install
   ```
   Move the compiled files to the correct location, if they are not there already:
   ```
   mv [compiled files] to C:\Users\YourUser\.m2\repository\org\nasengolem\util\[VersionID]
   ```

## Usage
Add the following dependency to your project's `pom.xml`:
```xml
<dependency>
  <groupId>org.nasengolem</groupId>
  <artifactId>util</artifactId>
  <version>[latest_version_id_here]</version>
</dependency>
```
Now you can use my Java Utilities. Read the JavaDocs for detailed explanation of the features. 
