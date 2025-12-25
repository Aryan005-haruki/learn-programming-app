# C Learning - Android App

A comprehensive C Programming learning application for Android with interactive lessons, quizzes, and progress tracking.

## Features

- 📚 **32 Comprehensive Lessons** across 10 modules
- 💻 **Interactive Code Examples** with syntax highlighting
- ❓ **Chapter-wise Quizzes** with explanations
- 📊 **Progress Tracking** for lessons and scores
- 🌐 **Hinglish Content** (Hindi + English mix)
- ✅ **Practice Questions** for each lesson

## Modules

1. **Basics** - Introduction to C, History, Features, Program Structure
2. **Data & Operators** - Data types, Variables, I/O, Operators
3. **Control Statements** - if, if-else, switch case
4. **Loops** - for, while, do-while, nested loops
5. **Functions** - Function basics, Arguments, Recursion
6. **Arrays & Strings** - 1D/2D arrays, String functions
7. **Pointers** - Pointer basics, Arithmetic, Arrays with pointers
8. **Structures** - Structure, Union, Enum
9. **File Handling** - File operations, Read/Write
10. **Practice & Quiz** - Practice programs and comprehensive quiz

## Tech Stack

- **Language**: Java
- **UI**: Material Design Components
- **Data Storage**: SharedPreferences (Firebase ready)
- **Architecture**: MVC Pattern

## Project Structure

```
app/
├── activities/          # All activity classes
├── adapters/           # RecyclerView adapters
├── models/             # Data models
├── utils/              # Utility classes (DataProvider)
└── res/
    ├── layout/         # XML layouts
    ├── drawable/       # Drawables and backgrounds
    ├── values/         # Colors, strings, themes
    └── menu/           # Menu resources
```

## Setup Instructions

1. Open project in Android Studio
2. Sync Gradle files
3. Run on emulator or physical device

## Firebase Integration (Optional)

To add Firebase:
1. Create Firebase project
2. Download `google-services.json`
3. Place in `app/` directory
4. Uncomment Firebase dependencies in `app/build.gradle`
5. Uncomment Firebase classpath in root `build.gradle`

## Screenshots

- Modern gradient UI design
- Card-based layouts
- Interactive code viewer
- Quiz system with explanations
- Progress tracking dashboard

## Version

**1.0** - Initial Release

## License

All rights reserved © 2024
