
# 🛫 Yatra Lowest Fare Automation using Selenium & Java

A real-world automation project that interacts with [Yatra.com](https://www.yatra.com) to identify the **lowest flight fare** between the current and next months. 
The project is built using **Selenium WebDriver**, **Java**, **TestNG**, and follows the **Page Object Model (POM)** design pattern.

---
## 📌 Project Objective

Automate the calendar fare-checking process on Yatra's flight search page to:
- Open the site and handle popups
- Click on the departure calendar
- Scan both current and next months for prices
- Identify the lowest fare in each month
- Print a human-readable comparison

---

## 📁 Project Structure

```
YatraFareAutomation/
├── src/
│   ├── main/java/pages/
│   │   ├── HomePage.java         # Handles popup and calendar click
│   │   └── CalendarPage.java     # Scans calendar for lowest fare
│   └── test/java/tests/
│       └── YatraTest.java        # Main test flow and comparison
├── pom.xml                       # Maven config with dependencies
```

---

## 🧪 Technologies Used

- Java 21
- Selenium WebDriver 4.x
- TestNG 7.x
- ChromeDriver
- Maven (build and dependencies)

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/yatra-fare-automation.git
   cd yatra-fare-automation
   ```

2. Make sure Java 21, Maven, and ChromeDriver are installed and configured.

3. Run the tests via terminal:
   ```bash
   mvn test
   ```

4. Or open in IntelliJ / Eclipse and run `YatraTest.java` directly.

---

## 🖼 Sample Output

```
✅ Login popup closed.
✅ Current Month: Saturday, August 10, 2025 - ₹1456
✅ Next Month: Wednesday, September 4, 2025 - ₹1349
🔽 Lowest fare is in next month on Wednesday, September 4, 2025 with ₹1349
```

---

## 📄 Documentation

Refer to the file `YatraFareAutomation_CodeExplanation.docx` for a full walkthrough of each class and method with layman explanations.

---

## 💬 Connect with Me

Feel free to post this as a carousel or share insights from this project on LinkedIn!

---

## 🔁 How to Push This to GitHub

```bash
# Step 1: Initialize git
git init

# Step 2: Add all files
git add .

# Step 3: Commit the code
git commit -m "Initial commit: Yatra fare automation"

# Step 4: Create repo on GitHub (via UI), then add remote URL
git remote add origin https://github.com/yourusername/yatra-fare-automation.git

# Step 5: Push to GitHub
git branch -M main
git push -u origin main
```

---

## 🏷 Suggested LinkedIn Title

**"Automated Yatra's calendar to find the lowest airfare using Selenium & Java ✈️💻 | Real-World POM Framework Implementation"**




