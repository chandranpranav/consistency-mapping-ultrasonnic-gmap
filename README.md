# Road Consistency Mapping using IOT
 The Project helps in determining the consistency of the road. It comprises of two modules mainly : Hardware & Software. It uses Ultrasonic Sensor to measure the distance between the vehicle and the ground. The device is fixed below the vehicle and a threshold distance (the ground clearance) is calculated, if the distance is greater than the 'threshold distance' then the GPS location is sent to the realtime database (Firebase). These co ordinates are then plotted on the App for user to judge the condition of the route he desires to take.



## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

```
Arduino IDE
Android Studio
```

### Installing

A step by step series of examples that tell you how to get a development env running
```
Arduino IDE
```

1. Get the latest version from the [download page](https://www.arduino.cc/en/Main/Software). You can choose between the Installer (.exe) and the Zip packages. We suggest you use the first one that installs directly everything you need to use the Arduino Software (IDE), including the drivers. With the Zip package you need to install the drivers manually. The Zip file is also useful if you want to create a [portable installation](https://www.arduino.cc/en/Guide/PortableIDE).

2. When the download finishes, proceed with the installation and please allow the driver installation process when you get a warning from the operating system.

3. Choose the components to install

4. Choose the installation directory (we suggest to keep the default one)

5.The process will extract and install all the required files to execute properly the Arduino Software (IDE)

When the Arduino Software (IDE) is properly installed you can go back to the [Getting Started Home](https://www.arduino.cc/en/Guide/HomePage) and choose your board from the list on the right of the page.



```
Android Studio
```
1. Download Android Studio:
Download the latest version of Android studio from [official website](https://developer.android.com/studio/index.html).

2. Install Android Studio :
To install Android Studio, in prior we should have JDK on our machine and set up JAVA_HOME. If you do not have JDK setup yet, follow our previous articles how to install Java8 on windows 10 and [how to set JAVA_HOME on Windows 10](https://www.onlinetutorialspoint.com/java8/java-8-how-to-set-java_home-on-windows10.html).

3. After downloading the android-studio-ide-171.4443003-windows.exe file, follow the below steps to install.

Step – 1 : Right click on downloaded .exe file and Run as Administrator.

Step – 2 : You will see the below  Setup wizard.

Step – 3 : Select Android Virtual Device and click on Next >

Step – 4 : It will show the installation path, default it could be in C:\Program Files\Android\, If you want to change the installation directory, you can freely use the Browse button and give the location and click on Next.

Step - 5 : Click on Install.

Step - 6 : Actual installation in progress..
		  After completing installation process click on Next >

Step - 7 : Tick on Start Android Studio and Click on Finish.

This is how, we can install Android Studio on windows 10, as we tick on Start Android Studio option, the Android Studio will launch automatically as soon as we click on Finish button.

Now Lets move on to Android Studio Setup.

Step - 8 : It will ask for the Android Studio installation type. Mostly we can go with Standard setup, if you have any specific configuration setup then you can select the Custom option to manual setup.

Step - 9 : It will ask for the confirmation for installation setting wizard.

Step - 10 : It will download all necessary components to launch the Android Studio and setting up the environment.

Step - 11 : After download and installation click on Finish.
            We have successfully installed the Android Studio



## Deployment

1. Flash the code for NodeMCU in the given folder
2. Generate The API key for Google Maps

## Built With

* [Arduino IDe](https://www.arduino.cc/) - Used to program the NodeMCU
* [Android Studio](https://developer.android.com/studio) - Used to create app
* [Firebase](https://firebase.google.com/) - The Realtime Database Server
