#include <Wire.h>

void setup()
{
  Serial.begin(9600);
  Wire.begin(42);                // join i2c bus with address #42
  Wire.onReceive(receiveEvent); // register event
}

void loop()
{
  delay(100);
}

// function that executes whenever data is received from master
// this function is registered as an event, see setup()
void receiveEvent(int howMany)
{
  String message = "";
 
  while ( Wire.available() > 0 )
  {
    char n=(char)Wire.read();
    if(((int)n)>((int)(' ')))
   message += n; 
  }
  Serial.println("received: " + message);
  if (message == "message") 
  {
    Serial.println("message received");
  }
}
