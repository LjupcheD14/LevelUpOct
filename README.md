## The task
Let’s imagine you’re creating an e-commerce website. The site is looking good and you are ready to implement payments. The first step of your payment process is entering payment information. One of the possibilities is to pay with a credit card. You use an external payment system to process the payments, and the external system has a limited number of payments it can process. To reduce the strain on the external payment system (caused by incorrect credit card details being entered), you decide to implement a second validation system which runs on your server and is not limited. This system is intended to perform simple sanity checks of the credit card information and respond with either a success or failure (more details below). The system is split into two parts, Frontend and Backend, and you will be making both of them. Your task is to implement a simple page with an input form to take in credit card information and send it to a Backend API for validation (that you will also implement). The Backend API should respond with either success or failure, and you should react appropriately in the Frontend (e.g. display a green check mark or a stop sign). The exact details of the implementation (e.g. page layout, API spec) are left entirely to your creativity. We are aware that this validation could be done on the Frontend, but we want you to showcase that you can make an API request and write some backend code :) Here is an example of a validation algorithm that can be used:

The expiry date of the credit card (year and month) must be AFTER present time The CVV (security code) of the credit card must be exactly 3 digits long

Unless it’s an American Express card, in which case the CVV must be exactly 4 digits long American Express are cards whose PAN (card numbers) starts with either “34” or “37”

The PAN (card number) is between 16 and 19 digits long Last digit of the PAN (card number) is checked using Luhn’s algorithm

We’d like to see you implement at least Points 1 to 3 so that we have something to discuss, but feel free to give Point 4 a try as well :)

# Note
This solution is in React-frontend and Java spring boot backend.
I have a solution for the same task in React-frontend and Node-backend, the solution: https://github.com/LjupcheD14/LevelUp

### The solution
Used technologies:
Java version: 17.0.8;
Spring boot version: 3.1.4;
Apache Maven 3.9.4
React version: 18.2.0;
Axios version: 1.5.1;
Bootstrap version 5.3.2;

# LevelUp Project Setup:
To get started with the LevelUp project, follow these steps:

Clone the repository: Clone the project repository to your local machine using the Git clone command.

### Start the backend application:

Run the java spring boot application, The backend application will be running on "localhost:8080".

### Start the frontend application:
Navigate to the "LevelUpOct/react-frontend" directory in your terminal or command prompt. Run the command "npm install" and then "npm start" to start the frontend application. The frontend application will be running on "localhost:3000".

By following these steps, you will have both the backend and frontend applications up and running, allowing you to work on the LevelUp project.
