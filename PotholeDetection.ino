#include <Firebase.h>
#include <FirebaseArduino.h>
#include <FirebaseCloudMessaging.h>
#include <FirebaseError.h>
#include <FirebaseObject.h>

#include <SoftwareSerial.h>
#include <TinyGPS++.h>
#include <ESP8266WiFi.h>

#define FIREBASE_HOST "projectbe-d99023.firebaseio.com"
#define FIREBASE_AUTH "qfwT0JPxc9aYOFdaL5a5F9PU68423bcv2icQXv5eA"



static const int RXPin = 4, TXPin = 5; //setting pins D1 and D2 for serial communication with GPS
static const uint32_t GPSBaud = 9600;


const char* ssid     = "A1";
const char* password = "123456789";


TinyGPSPlus gps;
WiFiClient  client;

// The serial connection to the GPS device
SoftwareSerial ss(RXPin, TXPin);

void setup()
{
  Serial.begin(115200);
  pinMode(D3,INPUT);
  pinMode(D4,OUTPUT);
  ss.begin(GPSBaud);
  Serial.println(F("DeviceExample.ino"));
  Serial.println(F("A simple demonstration of TinyGPS++ with an attached GPS module"));
  Serial.print(F("Testing TinyGPS++ library v. "));Serial.println(TinyGPSPlus::libraryVersion());
  Serial.println();

  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
   Serial.println("");
  Serial.println("WiFi connected");  
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  Serial.print("Netmask: ");
  Serial.println(WiFi.subnetMask());
  Serial.print("Gateway: ");
  Serial.println(WiFi.gatewayIP());
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  
}

void loop()
{
  digitalWrite(D4,LOW);
delayMicroseconds(5);
digitalWrite(D4,HIGH);
delayMicroseconds(10);
digitalWrite(D4,LOW);
double t=pulseIn(D3,HIGH);
double x=t*0.0343;
Serial.println(x);
  while (ss.available() > 0)
    if (gps.encode(ss.read()))
      { 
  if(x>30)
{
        displayInfo(x);
      }
      else
      delay(3);
      }

  if (millis() > 5000 && gps.charsProcessed() < 10)
  {
    Serial.println(F("No GPS detected: check wiring."));
    while(true);
  }
  
}
 void displayInfo(float x)
{
  Serial.print(F("  Date/Time: "));
  if (gps.date.isValid())
  {
    Serial.print(gps.date.month());
    Serial.print(F("/"));
    Serial.print(gps.date.day());
    Serial.print(F("/"));
    Serial.print(gps.date.year());
  }
  else
  {
    Serial.print(F("INVALID"));
  }

  Serial.print(F(" "));
  if (gps.time.isValid())
  {
    if (gps.time.hour() < 10) Serial.print(F("0"));
    Serial.print(gps.time.hour());
    Serial.print(F(":"));
    if (gps.time.minute() < 10) Serial.print(F("0"));
    Serial.print(gps.time.minute());
    Serial.print(F(":"));
    if (gps.time.second() < 10) Serial.print(F("0"));
    Serial.print(gps.time.second());
    Serial.print(F("."));
    if (gps.time.centisecond() < 10) Serial.print(F("0"));
    Serial.print(gps.time.centisecond());

  }
  else
  {
    Serial.print(F("INVALID"));
  }


  if (gps.location.isValid())
  {

    double latitude = (gps.location.lat());
    double longitude = (gps.location.lng());
    
    String latbuf;
    latbuf += (String(latitude, 6));
    Serial.println(latbuf);

    String lonbuf;
    lonbuf += (String(longitude, 6));
    Serial.println(lonbuf);
        String timestamp; 
   timestamp= gps.time.hour();  
    timestamp+= ':';
    timestamp+=gps.time.minute();
    timestamp+=':';
    timestamp+= gps.time.second();
    timestamp+= '.';
    timestamp+=gps.time.centisecond();
StaticJsonBuffer<200> jsonBuffer;
JsonObject& root = jsonBuffer.createObject();
root["timestamp"]= timestamp;
root["latitude"]= latbuf;
root["longitude"]= lonbuf;
root["depth"]=x/10;
String name = Firebase.push("/locations",root);
 if (Firebase.failed()) {
      Serial.print("setting /number failed:");
      Serial.println(Firebase.error());  
      return;
  }
  }
  else
  {
    Serial.print(F("INVALID"));
  }
  Serial.println();
}
