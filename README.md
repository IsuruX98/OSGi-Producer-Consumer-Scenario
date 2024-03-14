# Home Automation System

## Overview

This project implements a Home Automation System using the OSGi (Open Service Gateway initiative) framework. It consists of separate bundles for producers and consumers, facilitating communication between devices and controllers within a home automation network.

## Scenario Explanation

The system employs a Producer-Consumer design pattern, where producers are responsible for generating data or events, and consumers consume these data or events to perform relevant actions.

### Producers

1. **Light Control Service**: Controls the state of lights within the home.
2. **Thermistor Control Service**: Manages the temperature control system within the home.
3. **Security System Service**: Handles security-related events and controls security devices.
4. **Smart App Control Service**: Manages the state of various smart appliances within the home.

### Consumers

1. **Light Safe Package**: Controls the lighting system and manages security.
2. **Temp Ease Package**: Manages temperature settings and controls smart appliances.
3. **Bright Touch Package**: Controls lighting and smart appliances.
4. **Secure Comfort Package**: Controls security and temperature.
5. **All In One Service Consumer**: Provides comprehensive control over multiple aspects of the home automation system.

## Installation and Setup

- Right-click on the package containing AllInOneConsumer.
- Select "Run As" -> "Run Configuration".
- In the pop-up menu, search for "OSGi Framework" and create a new configuration.
- Select LightControlProducer, SecuritySystemProvider, SmartAppControlProducer, ThermistorControlProducer, and AllInOneConsumer from the Workspace. 
   - Run the configuration.

### Prerequisites

- Java JDK 8 or higher
- Eclipse IDE with Equinox

## Sample Screenshots

![Screenshot 2024-03-14 165817](https://github.com/IsuruX98/OSGi-Producer-Consumer-Scenario/assets/104721314/e671dbe2-465e-4ae1-83c6-f375bbbd458f)

![Screenshot 2024-03-14 165835](https://github.com/IsuruX98/OSGi-Producer-Consumer-Scenario/assets/104721314/df30405f-4792-4e64-81cf-28bace619920)

![Screenshot 2024-03-14 165854](https://github.com/IsuruX98/OSGi-Producer-Consumer-Scenario/assets/104721314/1c7e1960-3dd3-47e6-9093-8a50b83ce593)

![Screenshot 2024-03-14 165914](https://github.com/IsuruX98/OSGi-Producer-Consumer-Scenario/assets/104721314/0509d3d3-1a82-40f4-8893-503c0ec55f3b)

![Screenshot 2024-03-14 165927](https://github.com/IsuruX98/OSGi-Producer-Consumer-Scenario/assets/104721314/f34b6f8e-888f-40a6-a321-9db4c798237d)


## Documents

