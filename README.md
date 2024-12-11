# NasenGolem7442s Java Utility Collection

## Introduction
This collection of Java utilities provides a range of tools designed to simplify and enhance Java development. Featuring custom data structures and utility methods.

This repository has nothing to do with `java.util`

## Features
- CappedList: ArrayList like data structure with capped size that allows resizing the List in constant time. Useful if you often want to discard the last elements of your list.
- Class System for units (prototype): Class system to handle physical quantities (e.g. temperatures)
- Zip: Classes to iterate over several Iterables in a single enhanced-for loop 

Please take a look at [my JavaDoc](https://nasengolem7442.github.io/javaUtils/) for detailed Information.

## Installation
You can either download the compiled source code or you can compile it yourself.

For download, go to [releases](https://github.com/NasenGolem7442/javaUtils/releases) and download the latest `util-v#.#.#.zip`. Unpack it and move the `#.#.#` folder to `~/.m2/repository/org/nasengolem/util/`

If you want to compile it yourself, follow these steps:
1. **Open Powershell or Bash (Either works)** 
2. **Select the target location for the source code**
   ```bash
   cd [path]
   ```
3. **Clone the repository:**
   ```bash
   git clone https://github.com/NasenGolem7442/javaUtils.git
   ```
4. **Select the new folder:**
   ```bash
   cd javaUtils
   ```
5. **Compile the code:**
   ```bash
   mvn clean install
   ```
5. **Check if the compiled files are at the correct location**
   ```bash
   cd ~/.m2/repository/org/nasengolem/util
   ls
   ```
   Now you should see the latest version in your terminal.

## Usage
Add the following dependency to your project's `pom.xml`:
```xml
<dependency>
  <groupId>org.nasengolem</groupId>
  <artifactId>util</artifactId>
  <version>[latest_version_id]</version>
</dependency>
```
Now you can use my Java Utilities in your project.

## Contribution
This is my first github repository. My main goal is to improve my programming and documentation skills, while developping some (potentially) usefull utility stuff. I use this repo to save and organize some solutions to problems that I've come across while working on other projects.

Please share your thoughts to my project in the [discussions](https://github.com/NasenGolem7442/javaUtils/discussions) section.
Additionally, feel free to use the [issues](https://github.com/NasenGolem7442/javaUtils/issues/) section to address faulty code and request new features that extend certain functionality or the overall construct.
