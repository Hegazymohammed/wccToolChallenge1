# WC (Word Count) Java CLI Project

A lightweight Java command-line application that mimics the basic behavior of the Unix `wc` (word count) command. It supports both **file input** and **standard input (pipe)** modes.

---

## ✨ Features

* ✅ Count **bytes** (`-c`)
* ✅ Count **lines** (`-l`)
* ✅ Count **words** (`-w`)
* ✅ Count **characters** (`-m`)
* ✅ Supports **file input mode**
* ✅ Supports **pipe (standard input) mode**
* ✅ Clean architecture with separation of concerns
* ✅ Fully documented with Javadoc

---

## 📦 Project Structure

```
org.example
│
├── Main.java                     # Application entry point
├── FileInputProcessor.java       # Handles wc operations on files
└── StandardInputProcessor.java   # Handles wc operations from standard input
```

---

## 🚀 How to Run

### 1️⃣ Compile the Project

From the project root:

```
javac -d out src/org/example/*.java
```

---

### 2️⃣ Run in **File Mode**

Syntax:

```
wc <option> <file>
```

Examples:

```
java -cp out org.example.Main wc -l file.txt
java -cp out org.example.Main wc -w file.txt
java -cp out org.example.Main wc -c file.txt
java -cp out org.example.Main wc -m file.txt
```

---

### 3️⃣ Run in **Pipe (Standard Input) Mode**

Syntax:

```
<input> | wc <option>
```

Examples:

```
java -cp out org.example.Main "Hello World" | wc -w
java -cp out org.example.Main "Hello World" | wc -c
java -cp out org.example.Main "Hello World" | wc -l
```

---

## 🧪 Supported Options

| Option | Description      |
| ------ | ---------------- |
| `-c`   | Count bytes      |
| `-l`   | Count lines      |
| `-w`   | Count words      |
| `-m`   | Count characters |

---

## 🛡 Validation Rules

* The application validates:

  * Minimum number of arguments
  * Correct pipe structure
  * Supported `wc` options
  * File existence and validity

* Invalid input results in a clear `IllegalArgumentException` with guidance.

---

## 🧠 Design Highlights

* ✅ Pure Java (No external libraries)
* ✅ Stream API for clean processing
* ✅ UTF-8 byte handling
* ✅ Follows separation of concerns
* ✅ Interview-ready project design

---

## 👨‍💻 Author

**Mohammed**
Java Backend Developer
Focused on clean architecture, performance, and backend engineering fundamentals.

---

## ✅ Future Improvements

* Add JUnit 5 unit tests
* Add real OS-level standard input reading
* Add Apache Commons CLI or Picocli support
* Package as a runnable JAR

---

## 📄 License

This project is open for learning and demonstration purposes.
