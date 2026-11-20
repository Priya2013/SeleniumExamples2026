# Playwright Java in VS Code

This standalone Maven project keeps browser tests separate from the TradKarma
application. It includes a JUnit smoke test that opens the Playwright website
in Chromium.

## Prerequisites

- JDK 25 (the current LTS release). Verify in a VS Code terminal with
  `java -version`.
- Maven is configured to compile and run tests with the JDK at
  `%USERPROFILE%\.jdk\jdk-25\jdk-25.0.4.1+1`, even if the terminal that starts
  Maven has a different Java version. If JDK 25 is installed elsewhere, set
  `jdk25.home` in `pom.xml` to its installation directory.
- Apache Maven. It was not on PATH when this project was prepared. On Windows,
  install it from [maven.apache.org](https://maven.apache.org/install.html) or
  with `winget install Apache.Maven`, then restart VS Code and verify with
  `mvn -version`.
- The VS Code **Extension Pack for Java**
  (`vscjava.vscode-java-pack`), which is recommended when this folder is opened.

## Open and run

1. In VS Code, choose **File > Open Folder...** and open this `playwright-java`
   folder.
2. Open a terminal in this folder. The workspace configures new VS Code
   terminals to use JDK 25.
3. Install the Chromium browser used by the test:

   ```powershell
   mvn exec:java "-Dexec.mainClass=com.microsoft.playwright.CLI" "-Dexec.args=install chromium"
   ```

4. Run the sample browser test:

   ```powershell
   mvn test
   ```

The first Maven run downloads Java dependencies; Playwright also downloads its
Chromium browser to the local Playwright cache.

## Point a test at another site

Edit `src/test/java/com/tradekarma/PlaywrightSmokeTest.java`. Change the URL
passed to `page.navigate(...)` and add assertions for the page or workflow you
want to verify. Avoid using real account credentials in test source.
