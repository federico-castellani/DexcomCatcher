# DexcomCatcher

An Android application that captures and displays real-time glucose data from Dexcom Continuous Glucose Monitoring (CGM) devices.

## Overview

DexcomCatcher is a lightweight Android app that listens for Dexcom CGM broadcasts and displays glucose readings in a simple, easy-to-read interface. The app runs as a foreground service to continuously monitor glucose data in the background.

## Features

- **Real-time Glucose Monitoring**: Captures live glucose readings from Dexcom CGM devices
- **Trend Indicators**: Displays glucose trend arrows (rising, falling, stable)
- **Background Service**: Continues monitoring even when the app is not in the foreground
- **Notification Support**: Runs as a foreground service with persistent notification
- **Simple UI**: Clean, minimal interface showing current glucose value, trend, and timestamps

## Requirements

- Android 11 (API level 30) or higher
- Dexcom CGM app installed and configured
- Permission to access Dexcom CGM data

## Permissions

The app requires the following permissions:
- `com.dexcom.cgm.EXTERNAL_PERMISSION` - Access to Dexcom CGM data
- `android.permission.FOREGROUND_SERVICE` - Run background service
- `android.permission.POST_NOTIFICATIONS` - Show notifications

## Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd DexcomCatcher
   ```

2. Open the project in Android Studio

3. Build and install the APK:
   ```bash
   ./gradlew assembleDebug
   ```

4. Install on your Android device and grant the required permissions

## Usage

1. Launch the app
2. Grant the Dexcom external permission when prompted
3. The app will start monitoring glucose data automatically
4. View real-time glucose readings on the main screen
5. The service will continue running in the background

## Technical Details

### Architecture

- **MainActivity**: Main UI activity that displays glucose data
- **DexcomService**: Foreground service that manages the broadcast receiver
- **DexcomReceiver**: Broadcast receiver that listens for Dexcom CGM data

### Data Structure

The app processes glucose data from Dexcom broadcasts containing:
- Glucose value (mg/dL)
- Trend arrow direction
- Timestamp information
- Historical readings (up to 288 data points)

### Key Components

- **Service**: [`DexcomService`](app/src/main/java/com/dexcomcatcher/DexcomService.java) - Manages background operation
- **Receiver**: [`DexcomReceiver`](app/src/main/java/com/dexcomcatcher/DexcomReceiver.java) - Handles Dexcom broadcasts
- **Activity**: [`MainActivity`](app/src/main/java/com/dexcomcatcher/MainActivity.java) - Main user interface

## Configuration

### Build Configuration

- **Minimum SDK**: 30 (Android 11)
- **Target SDK**: 33 (Android 13)
- **Compile SDK**: 33

### Dependencies

```gradle
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'com.google.android.material:material:1.9.0'
implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
```

## Development

### Building from Source

1. Ensure you have Android Studio and Java 17+ installed
2. Clone the repository
3. Open in Android Studio
4. Sync Gradle files
5. Build and run on your device

### Testing

Run unit tests:
```bash
./gradlew test
```

Run instrumented tests:
```bash
./gradlew connectedAndroidTest
```

## Troubleshooting

### Common Issues

1. **Permission Denied**: Ensure Dexcom app is installed and the external permission is granted
2. **No Data Received**: Verify Dexcom CGM is paired and transmitting data
3. **Service Stops**: Check battery optimization settings for the app

### Logs

Monitor app logs using:
```bash
adb logcat | grep DexcomCatcher
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## Disclaimer

This application is for educational and personal use only. It is not intended for medical diagnosis or treatment. Always consult with healthcare professionals for medical decisions.

## Support

For issues and questions, please open an issue on the GitHub repository.
