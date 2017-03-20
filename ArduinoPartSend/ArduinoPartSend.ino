#include <Wire.h>
byte message = (byte)'a';

void setup()
{
  Wire.begin(42); // join i2c bus
}

void loop()
{
  Wire.beginTransmission(42); // transmit to device #44 (0x2c)
                              // device address is specified in datasheet
  Wire.write(message);             // sends value byte 
  Wire.endTransmission();     // stop transmitting
  delay(500);
}

