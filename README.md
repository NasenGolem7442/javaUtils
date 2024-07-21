# NasenGolem7442s Java Utility Collection

## Introduction
This collection of Java utilities provides a range of tools designed to simplify and enhance Java development. Featuring custom DataStructures and utility methods.

## Features
- CappedResizableList: ArrayList like data structure with capped size that allows resizing the List in constant time. Usefull if you often want to discard the last n elements of your list.  

Please take a look at [my JavaDoc](https://nasengolem7442.github.io/javaUtils/) for detailed Information.

## Installation
You can either download the compiled source code or you can compile it yourself.

To compile it yourself, go to [releases](https://github.com/NasenGolem7442/javaUtils/releases) and download the latest `util-v#.#.#.zip`. Unpack it and move the `#.#.#` folder to `C:\Users\YourUser\.m2\repository\org\nasengolem\util\`

If you want to compile it yourself, follow these steps on Windows*:
1. **Open PowerShell** 
2. **Select the target location for the source code**
   ```
   cd [path]
   ```
3. **Clone the repository:**
   ```
   git clone https://github.com/NasenGolem7442/javaUtils.git
   ```
4. **Select the new folder:**
   ```
   cd javaUtils
   ```
5. **Compile the code:**
   ```
   mvn clean install
   ```
5. **Check if the compiled files are at the correct location**
   ```
   cd ~\.m2\repository\org\nasengolem\util
   ```
   ```
   Get-ChildItem -Directory
   ```
   Now you should see a list of all avaiable versions.

\* If you use Linux, you should know how to do that stuff. If you use macOS, go f*** yourself.

## Usage
Add the following dependency to your project's `pom.xml`:
```xml
<dependency>
  <groupId>org.nasengolem</groupId>
  <artifactId>util</artifactId>
  <version>[latest_version_id_here]</version>
</dependency>
```
Now you can use my Java Utilities in your project.
