# 🚀 AI-Integrated Selenium Automation Framework — Flipkart

[![GitHub Actions](https://github.com/Chethu17/Flipkart-Selenium-TestNG/actions/workflows/maven.yml/badge.svg)](https://github.com/Chethu17/Flipkart-Selenium-TestNG/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-automation-orange)
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-green)
![TestNG](https://img.shields.io/badge/TestNG-testing-red)
![Maven](https://img.shields.io/badge/Maven-build-blue)
![MCP](https://img.shields.io/badge/MCP-AI%20integration-purple)

A Java-based web UI automation framework for Flipkart, built with **Selenium WebDriver, TestNG, and Maven**, enhanced with **GitHub Actions CI/CD** and **Model Context Protocol (MCP)** integration.

The project combines reliable browser automation with an MCP server that exposes selected automation capabilities to an AI assistant such as **Claude**. This makes it possible to interact with supported automation operations using natural-language requests and retrieve execution results.

> **Core idea:** Selenium + TestNG for UI automation · GitHub Actions for CI · MCP for AI-assisted interaction.

---

## ✨ Highlights

| Area | What this project demonstrates |
|---|---|
| UI automation | Java, Selenium WebDriver, TestNG, and Page Object Model |
| Test organization | Smoke and regression test execution |
| Reliability | Explicit waits, reusable methods, and failure screenshots |
| CI/CD | Automated Maven test execution through GitHub Actions |
| Headless execution | Browser tests running in a CI environment without a visible browser |
| AI integration | MCP server connecting supported automation operations to Claude |
| Reporting | Allure test reporting, if enabled in the project configuration |

## 🧠 MCP + Claude Integration

The project includes a **Model Context Protocol (MCP) server** that acts as an integration layer between an AI client and the automation framework.

With the MCP server configured and connected in Claude, you can ask Claude to use the tools exposed by the server. For example:

- “Run the smoke test suite.”
- “Start the supported regression test execution.”
- “Show me the latest test execution result.”
- “Which test cases failed?”
- “Help me understand this execution failure.”

The exact operations available depend on the tools implemented and registered by the MCP server.

**How it fits together**

```text
             Claude (AI client)
                    |
              MCP connection
                    |
                MCP Server
                    |
        Registered automation tools
                    |
       Selenium + TestNG framework
                    |
             Flipkart web UI
                    |
          Test execution results


        GitHub Actions CI pipeline
                    |
              Maven / TestNG
                    |
         Headless browser execution
                    |
             Build status
```

MCP does not replace Selenium or TestNG. It provides a standard way for a compatible AI client to invoke capabilities that the framework exposes.

## 🛠️ Technology Stack

- **Language:** Java
- **Browser automation:** Selenium WebDriver
- **Test framework:** TestNG
- **Build tool:** Maven
- **Design pattern:** Page Object Model (POM)
- **CI/CD:** GitHub Actions
- **AI integration:** Model Context Protocol (MCP)
- **AI client:** Claude
- **Reporting:** Allure (where configured)
- **Version control:** Git and GitHub

> Check `pom.xml` and the GitHub Actions workflow for the exact dependency and JDK versions used by the current branch.

## 🏗️ Framework Capabilities

### Selenium UI automation
- Page Object Model to separate page interactions from test logic.
- Reusable browser interaction methods.
- Explicit waits for synchronization.
- Window handling and common browser operations.
- Screenshot capture for failed tests (where configured).
- TestNG groups/suites for organized test execution.

### CI/CD with GitHub Actions
- Workflow-based automated test execution.
- Maven-based build and test commands.
- Headless browser execution in the CI environment.
- Execution status visible in the repository’s Actions tab.

[View GitHub Actions runs](https://github.com/Chethu17/Flipkart-Selenium-TestNG/actions)

### Test reporting
If Allure is configured for the current test run, generate or serve the report using the Maven goals configured in `pom.xml`:

```bash
mvn allure:report
mvn allure:serve
```

## ⚙️ Prerequisites

Install the following before running the project locally:

- Git
- A JDK compatible with the project configuration
- Apache Maven (if Maven Wrapper is not included)
- Google Chrome
- An IDE such as Eclipse or IntelliJ IDEA

For Claude/MCP usage, you will also need a compatible Claude client with MCP support and the MCP server configured for your local project.

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/Chethu17/Flipkart-Selenium-TestNG.git
cd Flipkart-Selenium-TestNG
```

### 2. Check your Java and Maven installations

```bash
java -version
mvn -version
```

Use a JDK version compatible with the project’s `pom.xml` and CI workflow.

### 3. Download dependencies and build

```bash
mvn clean install
```

### 4. Run the TestNG suite

```bash
mvn clean test
```

The Maven command runs the test configuration defined by the project. If your local setup uses a specific TestNG XML file or Maven profile, use the corresponding configuration from the repository.

## 🤖 Running the MCP Server

The MCP server is part of this project. Its launch command depends on how the server is configured and packaged.

If the project is configured with the Maven Exec plugin and `mcp.MCPServer` as its main class, start it with:

```bash
mvn exec:java
```

If your configuration uses a different launcher, use the command and arguments configured for that MCP server.

### Connect it to Claude

1. Build the project and confirm that its dependencies are available.
2. Add the MCP server configuration to the MCP settings for your Claude client.
3. Set the correct project working directory and Java/Maven launch command.
4. Restart or refresh Claude.
5. Confirm that the server connects and that its registered tools appear.
6. Try a request that uses one of the tools exposed by the server.

**Important:** Do not copy a generic MCP configuration without checking your server’s transport, launch arguments, and working directory. These must match the implementation in this repository. Never place passwords, access tokens, or other secrets in a committed configuration file.

## 🔄 CI/CD Workflow

The GitHub Actions workflow is maintained in:

```text
.github/workflows/
```

The workflow configuration defines the trigger events, Java setup, dependency installation, and Maven test command. Open the workflow file to see the exact steps and triggers currently enabled.

[View workflow runs](https://github.com/Chethu17/Flipkart-Selenium-TestNG/actions)

## 📂 Repository Structure

The repository’s actual folder and package structure is available in the `src/` directory and project root. Key configuration files include:

```text
Flipkart-Selenium-TestNG/
├── .github/
│   └── workflows/       # GitHub Actions CI workflow
├── src/                 # Application and test source code
├── pom.xml               # Maven dependencies and build configuration
├── testng.xml            # TestNG suite configuration
└── README.md
```

The MCP server and its tools are maintained within the source tree.

## 🔍 Troubleshooting

- **Maven build fails:** Check `java -version`, `mvn -version`, and the JDK version configured in the CI workflow.
- **Browser does not start:** Confirm Chrome is installed and that the local or CI browser setup is compatible with the Selenium configuration.
- **Tests time out:** Check the locator, page state, and wait conditions before increasing timeout values.
- **MCP server does not connect:** Verify the Claude MCP configuration, working directory, launch command, transport, and server logs.
- **No Allure report appears:** Confirm that the test run generated Allure result files and that the Allure Maven plugin is configured.

## 🎯 What I Learned / Demonstrated

- Designing and maintaining a Java Selenium automation framework.
- Applying Page Object Model and reusable automation methods.
- Organizing test execution with TestNG.
- Running browser automation in a headless CI environment.
- Integrating test execution into a GitHub Actions workflow.
- Generating and reviewing automated test reports.
- Exploring MCP and connecting an AI client to automation capabilities.
- Using natural-language interaction to invoke supported test operations and inspect results.

## 👨‍💻 Author

**Chethan M**

[GitHub Profile](https://github.com/Chethu17) · [Project Repository](https://github.com/Chethu17/Flipkart-Selenium-TestNG)

---

If you find this project useful, consider giving the repository a ⭐.
