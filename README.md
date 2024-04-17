# text-analyzer

Backend: Java + Spring Boot.
Frontend: Typescript + Angular

This is a basic text analysier that counts characters based on selected character types (vowels, consonants, punctuation, german Umlaute).
After submitting a request, the results are fetched from the backend. However, if the user checks the "offline mode" checkbox, no fetch is executed and the results are provided by the frontend, which contains the same logic as the backend.

Special care was taken to satisfy the Single Responsibility and Open-Close design principles, to allow for easy addition of more character types (such as language-specific characters) without neccessitating a modification of existing code.

To start the backend, please start TextAnalyzerApplication.java with an IDE of your choice, like IntelliJ IDEA.

To start the frontend, please follow these steps:

1. Make sure you have node installed. If not, please follow the directions for installation on nodejs.org
2. To install Angular CLI, please run the following command from a Terminal window: npm install -g @angular/cli.
3. After cloning the repository, please navigate to the frontend root directory (Frontend/text-analyzer) and install npm dependencies with the command: npm install.
4. run the command: ng serve to start the backend application and click on the loink to the localhost. Alternatively, ng serve --open will automatically open the page in your default browser.
