# 🎙️ jLLM: Java Voice-Enabled Client for Local LLMs

A robust, MVC-patterned Java application that acts as a voice-enabled interface for Large Language Models running locally via Ollama. 

This project demonstrates clean architecture principles, utilizing interfaces for flexible model integration and data persistence, coupled with Text-to-Speech (TTS) capabilities for an interactive assistant experience.

## 🚀 Key Features

*   **Local LLM Integration:** Communicates seamlessly with local models using `ollama4j`.
*   **Text-to-Speech (TTS):** Features a `VoiceConsoleView` that reads the AI's responses aloud using native TTS adapters.
*   **Multiple LLM Strategies:** Designed with an `ILLM` interface, allowing dynamic switching between different model behaviors (`LLMSmart`, `LLMFake`, `LLMCSV`).
*   **Data Persistence:** Saves and loads conversation histories using both JSON (Gson) and XML (Jackson) repositories.
*   **Clean MVC Architecture:** Strict separation of concerns (Model-View-Controller) making the codebase scalable, testable, and easy to maintain.

## 🛠️ Tech Stack & Libraries

*   **Language:** Java
*   **AI Integration:** [Ollama4j](https://github.com/amithkoujalgi/ollama4j)
*   **Text-to-Speech:** JAdapter for Native TTS
*   **Data Serialization:** 
    *   Gson (JSON)
    *   Jackson (XML)
*   **Logging:** SLF4J & Log4j Core
*   **Build System:** Ant

## 📂 Architecture Overview

The application follows a strict MVC pattern:
*   `model/`: Contains the core business logic, including `Conversation`, `Message`, `Frase`, and the data persistence interfaces (`IRepository`, `JSONRepository`, `XMLRepository`).
*   `view/`: Handles user interaction through multiple interfaces, including standard console output (`SimpleConsoleView`) and voice-enabled output (`VoiceConsoleView`).
*   `controller/`: Orchestrates the communication between the views, the persistence layer, and the LLM wrappers.

## ⚙️ Getting Started

### Prerequisites
1.  **Java:** Ensure you have JDK 11 or higher installed.
2.  **Ollama:** You must have [Ollama](https://ollama.com/) installed and running locally on your machine with your preferred model downloaded (e.g., `ollama run llama3`).

### Installation
1. Clone the repository:
   ```bash
   git clone [https://github.com/diegousal/jLLM-Ollama-Voice-Client.git](https://github.com/diegousal/jLLM-Ollama-Voice-Client.git)

### Running
1. Open the project in your preferred IDE (Eclipse, NetBeans, or VS Code).
2. Ensure all .jar files in the lib directory are added to your project's build path/classpath.
3. Run the Main class located in src/main/Main.java.
