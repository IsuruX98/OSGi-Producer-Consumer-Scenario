# Home Automation System

## Overview

The Smart Home Management System is an extensive project utilizing the flexibility and extensible characteristics of the OSGi framework. The system consists of various packages tailored to different functionalities, aiming to provide seamless integration and control over different aspects of home automation.

### Package Descriptions:

- **Light Safe Package (Consumer)**: Facilitates control over lighting systems and manages security features.
- **Temp Ease Package (Consumer)**: Manages temperature settings and controls smart appliances.
- **Bright Touch Package (Consumer)**: Controls lighting and smart appliances.
- **Secure Comfort Package (Consumer)**: Integrates security and temperature control functionalities.
- **All In One Service Consumer**: Offers comprehensive control over multiple aspects of the home automation system.

### Producers:

1. **Light Control Service**: Controls home lighting.
2. **Thermistor Control Service**: Manages temperature control.
3. **Security System Service**: Handles security-related events and devices.
4. **Smart App Control Service**: Manages smart appliances.

## Scenario Explanation

The system follows a Producer-Consumer design pattern, where producers generate data or events, and consumers utilize this information to perform relevant actions.

## Installation and Setup

1. Right-click on the package containing AllInOneConsumer.
2. Select "Run As" -> "Run Configuration".
3. Create a new configuration under "OSGi Framework".
4. Select required components: LightControlProducer, SecuritySystemProvider, SmartAppControlProducer, ThermistorControlProducer, and AllInOneConsumer from the Workspace.
5. Run the configuration.

### Prerequisites

- Java JDK 8 or higher
- Eclipse IDE with Equinox

## Sample Screenshots

![Screenshot 1](https://github.com/IsuruX98/OSGi-Producer-Consumer-Scenario/assets/104721314/e671dbe2-465e-4ae1-83c6-f375bbbd458f)

![Screenshot 2](https://github.com/IsuruX98/OSGi-Producer-Consumer-Scenario/assets/104721314/df30405f-4792-4e64-81cf-28bace619920)

![Screenshot 3](https://github.com/IsuruX98/OSGi-Producer-Consumer-Scenario/assets/104721314/1c7e1960-3dd3-47e6-9093-8a50b83ce593)

![Screenshot 4](https://github.com/IsuruX98/OSGi-Producer-Consumer-Scenario/assets/104721314/0509d3d3-1a82-40f4-8893-503c0ec55f3b)

![Screenshot 5](https://github.com/IsuruX98/OSGi-Producer-Consumer-Scenario/assets/104721314/f34b6f8e-888f-40a6-a321-9db4c798237d)

## Documents

- [Report: 3Y.2S.WE.SE.WE.1.1_IT21169380_SA_Assignment1.docx](https://github.com/IsuruX98/OSGi-Producer-Consumer-Scenario/files/14605662/3Y.2S.WE.SE.WE.1.1_IT21169380_SA_Assignment1.docx)
