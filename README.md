# innopolis-express

Our App is an Android App where you can view a list of food that is made by
Inoopolise Unuversity's Canteen, by using this app you can also , check the
details of the meals you will order, and order multiple meals for you and your
friends, just by using our App, it simple to use , Add item to cart and make a
one step checkout !

# How to run Mobile Application 
Prerequisites: Android Studio 
1. Open Android Studio 
2. Click “Import project (Gradle, Eclipse, ADT, etc.)” 
3. Choose folder “MVP” 
4. Click “Open” 
5. Wait until all dependencies will be resolved 
6. Make sure that the chosen configuration is “APP”
7. Chose device 
8. Click on the Run button (green triangle)  

# How to run admin web panel web app locally: 
Prerequisites: you need to create an account using mobile application ("Sign Up" use case). Use credentials from the sign up in web admin panel. 
1. If you don't have Node.js and npm installed on your computer, please download it and install according to the instructions on https://nodejs.org/en/download/
2. Run this command in terminal: npm install -g firebase-tools (If you have mac or linux use **sudo** before the command)
3. After successful installation, navigate in terminal to the root project directory, open the folder "Admin panel". Your should be in folder “../innopolis-express/Admn Panel”
4. Run the following command: firebase serve. It will create a local host on your machine. 
5. Login to firebase using command **firebase login**. If you don't have access to the system - ask us in telegram (https://t.me/laskau)
6. You'll see the address on which the server was set up (usually, http://localhost:5000)
7. (Optional) the web platform can be accessed on https://admin-inno-express.firebaseapp.com/ 

# How to run admin web panel web tests:
Prerequisites: If you don't have Node.js and npm installed on your computer, please download it and install according to the instructions on https://nodejs.org/en/download/
1. Navigate to the folder “../innopolis-express/Admn Panel” in your terminal
2. Install all dependencies: `npm install` (If you have mac or linux use **sudo** before the command)
3. Run all tests: `npm run test`
4. Results will be displayed in terminal
