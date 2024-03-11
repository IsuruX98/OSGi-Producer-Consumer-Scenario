# Home Automation System

## Overview

This project implements a Home Automation System using the OSGi (Open Service Gateway initiative) framework. It consists of separate bundles for producers and consumers, facilitating communication between devices and controllers within a home automation network.

## Scenario Explanation

The system employs a Producer-Consumer design pattern, where producers are responsible for generating data or events, and consumers consume these data or events to perform relevant actions.

### Producers

1. **Light Control Service**: Controls the state of lights within the home.
2. **Thermostat Control Service**: Manages the temperature control system within the home.
3. **Security System Service**: Handles security-related events and controls security devices.
4. **Smart Appliance Control Service**: Manages the state of various smart appliances within the home.

### Consumers

1. **Home Control and Security System Consumer**: Controls the overall home automation system and manages security.
2. **Thermostat and Appliance Control Consumer**: Manages temperature settings and controls smart appliances.
3. **Light and Appliance Control Consumer**: Controls lighting and smart appliances.
4. **Multi-Control Consumer**: Provides comprehensive control over multiple aspects of the home automation system.

## Installation and Setup

### Prerequisites

- Java JDK 8 or higher
- Eclipse IDE with Equinox

## Sample Screenshots

Include screenshots demonstrating the successful installation and operation of each bundle.

## Documents

Contributions are welcome! If you encounter any issues or have suggestions for improvements, please feel free to open an issue or submit a pull request.
