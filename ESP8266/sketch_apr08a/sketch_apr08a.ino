#include <Arduino.h>
#include <ESP8266WiFi.h>
#include <ArduinoJson.h>
#include <Adafruit_Sensor.h>
#include <DHT.h>
#include <ESP8266HTTPClient.h>
#include <string.h>

#define DHTPIN 2     // Digital pin connected to the DHT sensor
#define DHTTYPE    DHT11  
#define WorkingLed 5
#define NoInternetLed 4  

const char* ssid = "MERCUSYS_98EB";
const char* password = "matei123";

HTTPClient http;

String fingerPrint = "82:88:7C:B6:41:71:8B:04:67:A5:10:C2:34:40:24:04:78:A6:7E:55";
String api = "https://weatherstation.conveyor.cloud";
unsigned long previousReadingMillis = 0;
const long readingInterval = 5000;
unsigned long previousPostMillis = 0;
const long postInterval = 1800000;
DHT dht(DHTPIN, DHTTYPE);

float t;
float h;

void setup() {
  // put your setup code here, to run once:
  pinMode(WorkingLed,OUTPUT);
  pinMode(NoInternetLed,OUTPUT);
  Serial.begin(9600);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) 
  {
     digitalWrite(NoInternetLed,HIGH);
     digitalWrite(WorkingLed,LOW);
     delay(500);
     Serial.print("*");
  }
  dht.begin();
}

void loop() {
 digitalWrite(NoInternetLed,LOW);
 digitalWrite(WorkingLed,HIGH);
 unsigned long currentMillis = millis();
  if (currentMillis - previousReadingMillis >= readingInterval) {
    // save the last time you updated the DHT values
    previousReadingMillis = currentMillis;
    // Read temperature as Celsius (the default)
    float newT = dht.readTemperature();
    // Read temperature as Fahrenheit (isFahrenheit = true)
    //float newT = dht.readTemperature(true);
    // if temperature read failed, don't change t value
    if (isnan(newT)) {
      Serial.println("Failed to read from DHT sensor!");
    }
    else {
      t = newT;
      Serial.print("Temperatura:");
      Serial.println(t);
    }
    // Read Humidity
    float newH = dht.readHumidity();
    // if humidity read failed, don't change h value 
    if (isnan(newH)) {
      Serial.println("Failed to read from DHT sensor!");
    }
    else {
      h = newH;
      Serial.print("Umiditate:");
      Serial.println(h);
    }
  }
  
 
  if(currentMillis - previousPostMillis >= postInterval){
      Serial.println(h);
      PostSensorValue("temperature",t);
      PostSensorValue("humidity",h);
      previousPostMillis = currentMillis;
   }
}
 void PostSensorValue(String type, float value)
    {
      Serial.println("making POST request");
      String contentType = "application/json";
    
      const int capacity = JSON_OBJECT_SIZE(2) + 66;
      StaticJsonBuffer<capacity> JSONbuffer;
      JsonObject& JSONencoder = JSONbuffer.createObject();
      
      JSONencoder["Type"] = type;
      JSONencoder["Value"] = value;
      Serial.println(value);
      
      char JSONmessageBuffer[capacity];
      JSONencoder.prettyPrintTo(JSONmessageBuffer, sizeof(JSONmessageBuffer));
      
      http.begin(api+"/api/measurements/measurement/create",fingerPrint);      //Specify request destination
        http.addHeader("Content-Type", "application/json");  //Specify content-type header
     
        int httpCode = http.POST(JSONmessageBuffer);   //Send the request
        String payload = http.getString();                                        //Get the response payload
     
        Serial.println(httpCode);   //Print HTTP return code
        Serial.println(payload);    //Print request response payload
    
        http.end();  //Close connection
        
    }
