package com.clearning.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.clearning.app.models.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DataProvider - Handles local data storage and retrieval
 * This will be replaced with Firebase later
 */
public class DataProvider {
    private static DataProvider instance;
    private SharedPreferences prefs;
    private Gson gson;
    private static final String PREFS_NAME = "CLearningPrefs";
    private static final String KEY_USER = "current_user";
    private static final String KEY_LOGGED_IN = "is_logged_in";

    private DataProvider(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public static synchronized DataProvider getInstance(Context context) {
        if (instance == null) {
            instance = new DataProvider(context.getApplicationContext());
        }
        return instance;
    }

    // User Management
    public void saveUser(User user) {
        String userJson = gson.toJson(user);
        prefs.edit().putString(KEY_USER, userJson).apply();
        prefs.edit().putBoolean(KEY_LOGGED_IN, true).apply();
    }

    public User getCurrentUser() {
        String userJson = prefs.getString(KEY_USER, null);
        if (userJson != null) {
            return gson.fromJson(userJson, User.class);
        }
        return null;
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_LOGGED_IN, false);
    }

    public void logout() {
        prefs.edit().clear().apply();
    }

    public boolean updateUserProfile(String name, String email, String phone) {
        User currentUser = getCurrentUser();
        if (currentUser != null) {
            currentUser.setName(name);
            currentUser.setEmail(email);
            currentUser.setPhone(phone);
            saveUser(currentUser);
            return true;
        }
        return false;
    }

    // Get all chapters for C Programming
    public List<Chapter> getChapters() {
        List<Chapter> chapters = new ArrayList<>();
        
        chapters.add(new Chapter("chapter_1", "c_programming", "Module 1: Basics", 1, 5));
        chapters.add(new Chapter("chapter_2", "c_programming", "Module 2: Data & Operators", 2, 4));
        chapters.add(new Chapter("chapter_3", "c_programming", "Module 3: Control Statements", 3, 3));
        chapters.add(new Chapter("chapter_4", "c_programming", "Module 4: Loops", 4, 4));
        chapters.add(new Chapter("chapter_5", "c_programming", "Module 5: Functions", 5, 3));
        chapters.add(new Chapter("chapter_6", "c_programming", "Module 6: Arrays & Strings", 6, 3));
        chapters.add(new Chapter("chapter_7", "c_programming", "Module 7: Pointers", 7, 3));
        chapters.add(new Chapter("chapter_8", "c_programming", "Module 8: Structures", 8, 3));
        chapters.add(new Chapter("chapter_9", "c_programming", "Module 9: File Handling", 9, 2));
        chapters.add(new Chapter("chapter_10", "c_programming", "📝 Quiz", 10, 9));
        
        // Update progress for each chapter based on completed lessons
        User currentUser = getCurrentUser();
        if (currentUser != null) {
            for (Chapter chapter : chapters) {
                int completedCount = 0;
                List<Lesson> lessons = getLessonsForChapter(chapter.getChapterId());
                
                for (Lesson lesson : lessons) {
                    if (currentUser.isLessonCompleted(lesson.getLessonId())) {
                        completedCount++;
                    }
                }
                
                chapter.setCompletedLessons(completedCount);
            }
        }
        
        return chapters;
    }

    // Get lessons for a specific chapter
    public List<Lesson> getLessonsForChapter(String chapterId) {
        List<Lesson> lessons = new ArrayList<>();
        
        switch (chapterId) {
            // C Programming Chapters
            case "chapter_1":
                lessons = getModule1Lessons();
                break;
            case "chapter_2":
                lessons = getModule2Lessons();
                break;
            case "chapter_3":
                lessons = getModule3Lessons();
                break;
            case "chapter_4":
                lessons = getModule4Lessons();
                break;
            case "chapter_5":
                lessons = getModule5Lessons();
                break;
            case "chapter_6":
                lessons = getModule6Lessons();
                break;
            case "chapter_7":
                lessons = getModule7Lessons();
                break;
            case "chapter_8":
                lessons = getModule8Lessons();
                break;
            case "chapter_9":
                lessons = getModule9Lessons();
                break;
            case "chapter_10":
                lessons = getModule10Lessons();
                break;
            
            // Java Programming Chapters
            case "java_chapter_1":
                lessons = getJavaModule1Lessons();
                break;
            case "java_chapter_2":
                lessons = getJavaModule2Lessons();
                break;
            case "java_chapter_3":
                lessons = getJavaModule3Lessons();
                break;
            case "java_chapter_4":
                lessons = getJavaModule4Lessons();
                break;
            case "java_chapter_5":
                lessons = getJavaModule5Lessons();
                break;
            case "java_chapter_6":
                lessons = getJavaModule6Lessons();
                break;
            case "java_chapter_7":
                lessons = getJavaModule7Lessons();
                break;
            case "java_chapter_8":
                lessons = getJavaModule8Lessons();
                break;
            case "java_chapter_9":
                lessons = getJavaModule9Lessons();
                break;
            case "java_chapter_10":
                lessons = getJavaModule10Lessons();
                break;
            case "java_chapter_11":
                lessons = getJavaModule11Lessons();
                break;
            case "java_chapter_12":
                lessons = getJavaModule12Lessons();
                break;
            case "java_chapter_13":
                lessons = getJavaModule13Lessons();
                break;
        }
        
        return lessons;
    }

    // Module 1: Basics
    private List<Lesson> getModule1Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        Lesson lesson1 = new Lesson(
            "lesson_1", "chapter_1", "What is C?",
            // English explanation
            "🎯 What is C Programming Language?\n\n" +
            "Computers only understand 0s and 1s (machine language). A programming language is a way to communicate with computers - something humans can easily write and understand!\n\n" +
            "📝 What is C Language?\n" +
            "C is a powerful programming language created by Dennis Ritchie in 1972. It is fast, portable, and very popular.\n\n" +
            "🌟 Why Learn C?\n" +
            "• Foundation: If you learn C, you can easily learn any language (Java, Python, C++)\n" +
            "• Speed: C programs execute very fast\n" +
            "• Jobs: High demand in system programming and embedded systems\n" +
            "• Control: You can directly control hardware and memory\n\n" +
            "🎓 Where is it Used?\n" +
            "• Operating Systems (Windows, Linux)\n" +
            "• Games and Graphics\n" +
            "• Embedded Systems (TV, Car, Washing Machine)\n" +
            "• Backend of Mobile Apps\n\n" +
            "C language is called the 'Mother of All Languages' because modern languages are inspired by C!",
            "",
            "#include <stdio.h>\n\nint main() {\n    printf(\"Hello, World!\");\n    return 0;\n}",
            "Hello, World!",
            "💡 C language is case-sensitive, meaning 'printf' and 'Printf' are different.\n" +
            "💡 Every C program starts with the main() function.\n" +
            "💡 Semicolon (;) is required at the end of every statement.",
            "Q: What is the extension of C language?\nAns: .c"
            , 1
        );
        // Set Hinglish explanation
        lesson1.setExplanationHinglish(
            "🎯 C Programming Language Kya Hai?\n\n" +
            "Computer sirf 0 aur 1 (machine language) samajhta hai. Programming language ek tarika hai computer se baat karne ka - jo humans easily likh aur samajh sakte hain!\n\n" +
            "📝 C Language Kya Hai?\n" +
            "C ek powerful programming language hai jo 1972 mein Dennis Ritchie ne banai thi. Yeh fast, portable, aur bahut popular hai.\n\n" +
            "🌟 C Kyu Seekhein?\n" +
            "• Foundation: Agar C seekh liye to koi bhi language (Java, Python, C++) easily seekh sakte ho\n" +
            "• Speed: C programs bahut fast execute hote hain\n" +
            "• Jobs: System programming, embedded systems mein demand hai\n" +
            "• Control: Hardware aur memory ko directly control kar sakte ho\n\n" +
            "🎓 Kahan Use Hoti Hai?\n" +
            "• Operating Systems (Windows, Linux)\n" +
            "• Games aur Graphics\n" +
            "• Embedded Systems (TV, Car, Washing Machine)\n" +
            "• Mobile Apps ka backend\n\n" +
            "C language ko 'Mother of All Languages' kehte hain kyunki modern languages C se inspired hain!"
        );
        lessons.add(lesson1);
        
        Lesson lesson2 = new Lesson(
            "lesson_2", "chapter_1", "History of C",
            // English explanation
            "📜 The Story of C Language\n\n" +
            "In 1969, scientists at Bell Labs were developing the UNIX operating system. Initially, they used assembly language, which was very difficult.\n\n" +
            "👨‍💻 Dennis Ritchie - Creator of C\n" +
            "Dennis Ritchie thought - 'There should be a language that is easier than assembly but equally powerful.' And in 1972, C language was born!\n\n" +
            "📖 The Famous Book\n" +
            "In 1978, Dennis Ritchie and Brian Kernighan wrote 'The C Programming Language' book. This book became so famous that it's called 'K&R C'.\n\n" +
            "🌍 Impact of C\n" +
            "• UNIX operating system was rewritten in C\n" +
            "• C++ (1983): Advanced version of C\n" +
            "• Java (1995): Inspired by C\n" +
            "• Python, JavaScript: All have C as their foundation\n\n" +
            "That's why C is called the 'Mother of All Languages'!",
            "",
            "// C language timeline\n// 1969-1970: B language developed\n// 1972: C language created\n// 1978: K&R C book published\n// 1989: ANSI C standardized\n// 1999: C99 standard released\n// 2011: C11 standard released",
            "",
            "📚 C language is also called the 'mother of all languages'.\n" +
            "📚 C++, Java, C#, and many other languages are inspired by C.\n" +
            "📚 Even today, C language is very popular for system programming.",
            "Q: Who created C language?\nAns: Dennis Ritchie"
            , 2
        );
        // Set Hinglish explanation
        lesson2.setExplanationHinglish(
            "📜 C Language Ki Kahani\n\n" +
            "1969 mein Bell Labs mein scientists UNIX operating system bana rahe the. Pehle unhone assembly language use ki jo bahut difficult thi.\n\n" +
            "👨‍💻 Dennis Ritchie - C Ke Creator\n" +
            "Dennis Ritchie ne socha - 'Koi aisi language honi chahiye jo assembly se easy ho lekin utni hi powerful ho.' Aur 1972 mein C language ka janam hua!\n\n" +
            "📖 The Famous Book\n" +
            "1978 mein Dennis Ritchie aur Brian Kernighan ne 'The C Programming Language' book likhi. Yeh book itni famous hui ki isse 'K&R C' kehte hain.\n\n" +
            "🌍 C Ka Impact\n" +
            "• UNIX operating system C mein rewrite kiya gaya\n" +
            "• C++ (1983): C ka advanced version\n" +
            "• Java (1995): C se inspired\n" +
            "• Python, JavaScript: Sabki foundation C hai\n\n" +
            "Isliye C ko 'Mother of All Languages' kehte hain!"
        );
        lessons.add(lesson2);
        
        Lesson lesson3 = new Lesson(
            "lesson_3", "chapter_1", "Features of C",
            // English explanation
            "✨ Features of C Language\n\n" +
            "1️⃣ Simple and Easy\n" +
            "Only 32 keywords. English-like syntax. Example: printf(\"Hello\"); - Prints Hello on screen!\n\n" +
            "2️⃣ Super Fast (Compiled Language)\n" +
            "C uses a compiler that converts the entire code into machine code at once. Result: Rocket speed! 🚀\n\n" +
            "3️⃣ Portable\n" +
            "Write code once, run anywhere - Windows, Linux, Mac. Just change the compiler!\n\n" +
            "4️⃣ Middle-Level Language\n" +
            "C offers the best of both worlds:\n" +
            "• High-level: Humans can easily understand\n" +
            "• Low-level: Can directly control hardware\n\n" +
            "5️⃣ Rich Library\n" +
            "Ready-made functions: printf(), scanf(), sqrt() - No need to reinvent the wheel!\n\n" +
            "6️⃣ Structured Programming\n" +
            "Break code into small functions. Code stays organized and debugging is easy!\n\n" +
            "Summary: C is simple, fast, and powerful!",
            "",
            "// C language features demonstration\n#include <stdio.h>\n\nint main() {\n    // Simple syntax\n    int num = 10;\n    \n    // Fast execution\n    printf(\"Number: %d\\n\", num);\n    \n    // Portable code\n    return 0;\n}",
            "Number: 10",
            "⚡ C language allows direct memory access using pointers.\n" +
            "⚡ C supports recursion.\n" +
            "⚡ Dynamic memory allocation is possible.",
            "Q: What are the features of C language?\nAns: Fast, portable, and powerful"
            , 3
        );
        // Set Hinglish explanation
        lesson3.setExplanationHinglish(
            "✨ C Language Ki Khaasiyatein\n\n" +
            "1️⃣ Simple aur Easy\n" +
            "Sirf 32 keywords hain. English jaisi syntax. Example: printf(\"Hello\"); - Screen par Hello print karega!\n\n" +
            "2️⃣ Super Fast (Compiled Language)\n" +
            "C compiler use karta hai jo pura code ek baar mein machine code mein convert karta hai. Result: Rocket ki speed! 🚀\n\n" +
            "3️⃣ Portable\n" +
            "Ek baar code likho, kahin bhi chalao - Windows, Linux, Mac. Bas compiler change karo!\n\n" +
            "4️⃣ Middle-Level Language\n" +
            "C dono duniya ka best hai:\n" +
            "• High-level: Humans easily samajh sakte hain\n" +
            "• Low-level: Hardware ko directly control kar sakte ho\n\n" +
            "5️⃣ Rich Library\n" +
            "Ready-made functions: printf(), scanf(), sqrt() - Wheel reinvent nahi karna padta!\n\n" +
            "6️⃣ Structured Programming\n" +
            "Code ko chhote functions mein tod sakte ho. Code organized aur debugging easy!\n\n" +
            "Summary: C simple hai, fast hai, powerful hai!"
        );
        lessons.add(lesson3);
        
        Lesson lesson4 = new Lesson(
            "lesson_4", "chapter_1", "C Program Structure",
            // English explanation
            "Every C program has a basic structure:\n" +
            "1. Preprocessor Directives - #include statements\n" +
            "2. Global Declarations - Variables and functions\n" +
            "3. main() Function - Program entry point\n" +
            "4. User-defined Functions - Your custom functions\n\n" +
            "Inside main() function:\n" +
            "- Variable declarations\n" +
            "- Statements and expressions\n" +
            "- Return statement",
            "// Basic structure\n#include <header_files>\n\n// Global declarations\n\nint main() {\n    // Local declarations\n    // Statements\n    return 0;\n}",
            "#include <stdio.h>\n\nint globalVar = 100;  // Global variable\n\nint main() {\n    int localVar = 50;  // Local variable\n    \n    printf(\"Global: %d\\n\", globalVar);\n    printf(\"Local: %d\\n\", localVar);\n    \n    return 0;\n}",
            "Global: 100\nLocal: 50",
            "🔧 #include <stdio.h> is used for standard input/output functions.\n" +
            "🔧 main() function has int return type.\n" +
            "🔧 return 0; indicates successful execution.",
            "Q: What is the entry point of a C program?\nAns: main() function"
            , 4
        );
        // Set Hinglish explanation
        lesson4.setExplanationHinglish(
            "Har C program ka ek basic structure hota hai:\n" +
            "1. Preprocessor Directives - #include statements\n" +
            "2. Global Declarations - Variables aur functions\n" +
            "3. main() Function - Program ka entry point\n" +
            "4. User-defined Functions - Apne custom functions\n\n" +
            "main() function mein:\n" +
            "- Variable declarations\n" +
            "- Statements aur expressions\n" +
            "- Return statement"
        );
        lessons.add(lesson4);
        
        Lesson lesson5 = new Lesson(
            "lesson_5", "chapter_1", "First C Program",
            // English explanation
            "Let's write our first C program! This program will print 'Hello, World!' on the screen.\n\n" +
            "Program parts:\n" +
            "1. #include <stdio.h> - Includes the Standard Input/Output library\n" +
            "2. int main() - Main function where the program starts\n" +
            "3. printf() - Prints output on the screen\n" +
            "4. return 0 - Program completed successfully",
            "printf(\"text\");",
            "#include <stdio.h>\n\nint main() {\n    printf(\"Hello, World!\\n\");\n    printf(\"Welcome to C Programming\\n\");\n    printf(\"Let's learn together!\\n\");\n    \n    return 0;\n}",
            "Hello, World!\nWelcome to C Programming\nLet's learn together!",
            "✨ \\n is used for new line.\n" +
            "✨ Use double quotes (\" \") in printf().\n" +
            "✨ Semicolon (;) is required after every statement.",
            "Q: Which function is used to print output on screen?\nAns: printf()"
            , 5
        );
        // Set Hinglish explanation
        lesson5.setExplanationHinglish(
            "Chalo apna pehla C program likhte hain! Yeh program screen par 'Hello, World!' print karega.\n\n" +
            "Program ke parts:\n" +
            "1. #include <stdio.h> - Standard Input/Output library include karta hai\n" +
            "2. int main() - Main function jahan se program start hota hai\n" +
            "3. printf() - Screen par output print karta hai\n" +
            "4. return 0 - Program successfully complete hua"
        );
        lessons.add(lesson5);
        
        return lessons;
    }

    // Module 2: Data & Operators
    private List<Lesson> getModule2Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        Lesson lesson6 = new Lesson(
            "lesson_6", "chapter_2", "Data Types",
            // English explanation
            "C language uses data types to store different kinds of data:\n\n" +
            "1. int - Integer numbers (1, 2, 100, -50)\n" +
            "2. float - Decimal numbers (3.14, 2.5, -1.5)\n" +
            "3. double - Large decimal numbers (high precision)\n" +
            "4. char - Single character ('A', 'b', '1')\n\n" +
            "Each data type has its own memory size:\n" +
            "- int: 4 bytes\n" +
            "- float: 4 bytes\n" +
            "- double: 8 bytes\n" +
            "- char: 1 byte",
            "data_type variable_name;",
            "#include <stdio.h>\n\nint main() {\n    int age = 25;\n    float price = 99.99;\n    double pi = 3.14159265359;\n    char grade = 'A';\n    \n    printf(\"Age: %d\\n\", age);\n    printf(\"Price: %.2f\\n\", price);\n    printf(\"Pi: %.10lf\\n\", pi);\n    printf(\"Grade: %c\\n\", grade);\n    \n    return 0;\n}",
            "Age: 25\nPrice: 99.99\nPi: 3.1415926536\nGrade: A",
            "📊 %d for integer, %f for float, %lf for double, %c for char.\n" +
            "📊 Use sizeof() operator to check data type size.\n" +
            "📊 unsigned keyword stores only positive values.",
            "Q: Which data type is used to store decimal numbers?\nAns: float or double"
            , 1
        );
        // Set Hinglish explanation
        lesson6.setExplanationHinglish(
            "C language mein different types ka data store karne ke liye data types use hote hain:\n\n" +
            "1. int - Integer numbers (1, 2, 100, -50)\n" +
            "2. float - Decimal numbers (3.14, 2.5, -1.5)\n" +
            "3. double - Large decimal numbers (high precision)\n" +
            "4. char - Single character ('A', 'b', '1')\n\n" +
            "Har data type ki apni memory size hoti hai:\n" +
            "- int: 4 bytes\n" +
            "- float: 4 bytes\n" +
            "- double: 8 bytes\n" +
            "- char: 1 byte"
        );
        lessons.add(lesson6);
        
        Lesson lesson7 = new Lesson(
            "lesson_7", "chapter_2", "Variables & Constants",
            // English explanation
            "Variables: Containers that store values and can be changed.\n" +
            "Constants: Values that don't change in the program.\n\n" +
            "Variable declaration:\n" +
            "- First data type, then variable name\n" +
            "- Variable name can have letters, digits, and underscore\n" +
            "- First character must be a letter or underscore\n\n" +
            "Two ways to define constants:\n" +
            "1. #define preprocessor\n" +
            "2. const keyword",
            "#define CONSTANT_NAME value\nconst data_type variable_name = value;",
            "#include <stdio.h>\n\n#define PI 3.14159\n#define MAX_SIZE 100\n\nint main() {\n    int radius = 5;\n    const int MIN_AGE = 18;\n    \n    float area = PI * radius * radius;\n    \n    printf(\"Radius: %d\\n\", radius);\n    printf(\"Area: %.2f\\n\", area);\n    printf(\"Min Age: %d\\n\", MIN_AGE);\n    printf(\"Max Size: %d\\n\", MAX_SIZE);\n    \n    return 0;\n}",
            "Radius: 5\nArea: 78.54\nMin Age: 18\nMax Size: 100",
            "🔐 Writing constants in uppercase is good practice.\n" +
            "🔐 const variable must be initialized.\n" +
            "🔐 Variable names are case-sensitive (age and Age are different).",
            "Q: What is the difference between variable and constant?\nAns: Variable can change, constant cannot"
            , 2
        );
        // Set Hinglish explanation
        lesson7.setExplanationHinglish(
            "Variables: Aise containers hain jo values store karte hain aur change ho sakti hain.\n" +
            "Constants: Aise values hain jo program mein change nahi hoti.\n\n" +
            "Variable declaration:\n" +
            "- Pehle data type, phir variable name\n" +
            "- Variable name letters, digits, aur underscore se ban sakta hai\n" +
            "- Pehla character letter ya underscore hona chahiye\n\n" +
            "Constants define karne ke 2 tarike:\n" +
            "1. #define preprocessor\n" +
            "2. const keyword"
        );
        lessons.add(lesson7);
        
        Lesson lesson8 = new Lesson(
            "lesson_8", "chapter_2", "Input/Output (printf, scanf)",
            // English explanation
            "Input/Output is an important part of C programming:\n\n" +
            "printf() - To display output\n" +
            "- Uses format specifiers (%d, %f, %c, %s)\n" +
            "- Can print multiple values\n\n" +
            "scanf() - To take input from user\n" +
            "- Must provide variable address (&)\n" +
            "- Must use format specifier",
            "printf(\"format\", variables);\nscanf(\"format\", &variables);",
            "#include <stdio.h>\n\nint main() {\n    int age;\n    float height;\n    char initial;\n    \n    printf(\"Enter your age: \");\n    scanf(\"%d\", &age);\n    \n    printf(\"Enter your height (in meters): \");\n    scanf(\"%f\", &height);\n    \n    printf(\"Enter your initial: \");\n    scanf(\" %c\", &initial);\n    \n    printf(\"\\nYour Details:\\n\");\n    printf(\"Age: %d years\\n\", age);\n    printf(\"Height: %.2f meters\\n\", height);\n    printf(\"Initial: %c\\n\", initial);\n    \n    return 0;\n}",
            "Enter your age: 25\nEnter your height (in meters): 1.75\nEnter your initial: A\n\nYour Details:\nAge: 25 years\nHeight: 1.75 meters\nInitial: A",
            "⌨️ & (address operator) is required in scanf().\n" +
            "⌨️ Adding space before %c solves whitespace issues.\n" +
            "⌨️ Can take multiple inputs together.",
            "Q: Which function is used to take input from user?\nAns: scanf()"
            , 3
        );
        // Set Hinglish explanation
        lesson8.setExplanationHinglish(
            "Input/Output C programming ka important part hai:\n\n" +
            "printf() - Output display karne ke liye\n" +
            "- Format specifiers use karte hain (%d, %f, %c, %s)\n" +
            "- Multiple values print kar sakte hain\n\n" +
            "scanf() - User se input lene ke liye\n" +
            "- Variable ka address (&) dena zaroori hai\n" +
            "- Format specifier use karna padta hai"
        );
        lessons.add(lesson8);
        
        Lesson lesson9 = new Lesson(
            "lesson_9", "chapter_2", "Operators",
            // English explanation
            "C has different types of operators:\n\n" +
            "1. Arithmetic Operators: +, -, *, /, % (modulus)\n" +
            "2. Relational Operators: ==, !=, >, <, >=, <=\n" +
            "3. Logical Operators: && (AND), || (OR), ! (NOT)\n" +
            "4. Assignment Operators: =, +=, -=, *=, /=\n" +
            "5. Increment/Decrement: ++, --\n\n" +
            "Operators are used for calculations and comparisons.",
            "result = operand1 operator operand2;",
            "#include <stdio.h>\n\nint main() {\n    int a = 10, b = 3;\n    \n    // Arithmetic\n    printf(\"Addition: %d\\n\", a + b);\n    printf(\"Subtraction: %d\\n\", a - b);\n    printf(\"Multiplication: %d\\n\", a * b);\n    printf(\"Division: %d\\n\", a / b);\n    printf(\"Modulus: %d\\n\", a % b);\n    \n    // Relational\n    printf(\"\\na > b: %d\\n\", a > b);\n    printf(\"a == b: %d\\n\", a == b);\n    \n    // Increment\n    a++;\n    printf(\"\\nAfter a++: %d\\n\", a);\n    \n    return 0;\n}",
            "Addition: 13\nSubtraction: 7\nMultiplication: 30\nDivision: 3\nModulus: 1\n\na > b: 1\na == b: 0\n\nAfter a++: 11",
            "➗ % (modulus) gives the remainder.\n" +
            "➗ == is for comparison, = is for assignment.\n" +
            "➗ 1 means true, 0 means false in C.",
            "Q: Which operator is used to get remainder?\nAns: % (modulus)"
            , 4
        );
        // Set Hinglish explanation
        lesson9.setExplanationHinglish(
            "C mein different types ke operators hain:\n\n" +
            "1. Arithmetic Operators: +, -, *, /, % (modulus)\n" +
            "2. Relational Operators: ==, !=, >, <, >=, <=\n" +
            "3. Logical Operators: && (AND), || (OR), ! (NOT)\n" +
            "4. Assignment Operators: =, +=, -=, *=, /=\n" +
            "5. Increment/Decrement: ++, --\n\n" +
            "Operators ka use calculations aur comparisons ke liye hota hai."
        );
        lessons.add(lesson9);
        
        return lessons;
    }

    // Module 3: Control Statements
    private List<Lesson> getModule3Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        Lesson lesson10 = new Lesson(
            "lesson_10", "chapter_3", "if statement",
            // English explanation
            "🤔 if Statement - Decision Making\n\n" +
            "In real life we make decisions: IF it's raining, THEN take an umbrella. Programming is the same - if statement!\n\n" +
            "📝 What Does if Statement Do?\n" +
            "if statement helps the computer make decisions. If the condition is TRUE, code will execute. If FALSE, it will be skipped.\n\n" +
            "🔍 Syntax:\n" +
            "if (condition) {\n" +
            "    // Code will execute\n" +
            "}\n\n" +
            "💡 Example: Voting eligibility\n" +
            "int age = 20;\n" +
            "if (age >= 18) {\n" +
            "    printf(\"You can vote!\");\n" +
            "}\n\n" +
            "What will happen?\n" +
            "• age = 20, and 20 >= 18 is TRUE\n" +
            "• So \"You can vote!\" will print\n\n" +
            "What if age = 15?\n" +
            "• 15 >= 18 is FALSE\n" +
            "• Code will be skipped, nothing will print\n\n" +
            "Remember: Condition must always be in parentheses ()!",
            "if (condition) {\n    // code to execute if true\n}",
            "#include <stdio.h>\n\nint main() {\n    int age = 20;\n    \n    if (age >= 18) {\n        printf(\"You are eligible to vote!\\n\");\n    }\n    \n    int marks = 85;\n    \n    if (marks >= 90) {\n        printf(\"Excellent!\\n\");\n    }\n    \n    if (marks >= 60) {\n        printf(\"You passed!\\n\");\n    }\n    \n    return 0;\n}",
            "You are eligible to vote!\nYou passed!",
            "✅ Condition must be written in parentheses ().\n" +
            "✅ Curly braces are optional for single statement.\n" +
            "✅ Can check multiple conditions.",
            "Q: When is if statement used?\nAns: For decision making"
            , 1
        );
        // Set Hinglish explanation
        lesson10.setExplanationHinglish(
            "🤔 if Statement - Decision Making\n\n" +
            "Real life mein hum decisions lete hain: AGAR barish ho rahi hai, TO chhata le jao. Programming mein bhi same - if statement!\n\n" +
            "📝 if Statement Kya Karta Hai?\n" +
            "if statement computer ko decision lene mein help karta hai. Agar condition TRUE hai, to code execute hoga. FALSE hai to skip ho jayega.\n\n" +
            "🔍 Syntax:\n" +
            "if (condition) {\n" +
            "    // Code execute hoga\n" +
            "}\n\n" +
            "💡 Example: Voting eligibility\n" +
            "int age = 20;\n" +
            "if (age >= 18) {\n" +
            "    printf(\"You can vote!\");\n" +
            "}\n\n" +
            "Kya hoga?\n" +
            "• age = 20, aur 20 >= 18 TRUE hai\n" +
            "• Isliye \"You can vote!\" print hoga\n\n" +
            "Agar age = 15 hoti?\n" +
            "• 15 >= 18 FALSE hai\n" +
            "• Code skip ho jayega, kuch print nahi hoga\n\n" +
            "Yaad Rakho: Condition hamesha parentheses () mein likhni hai!"
        );
        lessons.add(lesson10);
        
        Lesson lesson11 = new Lesson(
            "lesson_11", "chapter_3", "if-else",
            // English explanation
            "⚖️ if-else - Handle Both Options\n\n" +
            "Sometimes just if is not enough. Think: IF you pass THEN party, OTHERWISE work hard. To handle both cases - if-else!\n\n" +
            "📝 What is if-else?\n" +
            "if-else has 2 blocks:\n" +
            "• if block - When condition is TRUE\n" +
            "• else block - When condition is FALSE\n" +
            "Guarantee: ONE of them will definitely execute!\n\n" +
            "🔍 Syntax:\n" +
            "if (condition) {\n" +
            "    // TRUE code\n" +
            "} else {\n" +
            "    // FALSE code\n" +
            "}\n\n" +
            "💡 Example: Even/Odd\n" +
            "int num = 7;\n" +
            "if (num % 2 == 0) {\n" +
            "    printf(\"Even\");\n" +
            "} else {\n" +
            "    printf(\"Odd\");\n" +
            "}\n" +
            "Output: Odd\n\n" +
            "🎓 else if - Multiple Conditions\n" +
            "For many options use else if. The FIRST TRUE condition's block will execute!\n\n" +
            "Important: Only ONE block will execute!",
            "if (condition) {\n    // true block\n} else {\n    // false block\n}",
            "#include <stdio.h>\n\nint main() {\n    int marks = 75;\n    \n    if (marks >= 90) {\n        printf(\"Grade: A+\\n\");\n    } else if (marks >= 80) {\n        printf(\"Grade: A\\n\");\n    } else if (marks >= 70) {\n        printf(\"Grade: B\\n\");\n    } else if (marks >= 60) {\n        printf(\"Grade: C\\n\");\n    } else {\n        printf(\"Grade: F\\n\");\n    }\n    \n    int num = 7;\n    \n    if (num % 2 == 0) {\n        printf(\"%d is even\\n\", num);\n    } else {\n        printf(\"%d is odd\\n\", num);\n    }\n    \n    return 0;\n}",
            "Grade: B\n7 is odd",
            "🔀 else if allows checking multiple conditions.\n" +
            "🔀 Only one block will execute (whichever is true first).\n" +
            "🔀 else is optional.",
            "Q: How many blocks execute in if-else?\nAns: Only one"
            , 2
        );
        // Set Hinglish explanation
        lesson11.setExplanationHinglish(
            "⚖️ if-else - Dono Options Handle Karo\n\n" +
            "Kabhi kabhi sirf if kaafi nahi hota. Socho: AGAR pass ho gaye TO party, WARNA mehnat karo. Dono cases handle karne ke liye if-else!\n\n" +
            "📝 if-else Kya Hai?\n" +
            "if-else mein 2 blocks hote hain:\n" +
            "• if block - Jab condition TRUE ho\n" +
            "• else block - Jab condition FALSE ho\n" +
            "Guarantee: Dono mein se EK zaroor chalega!\n\n" +
            "🔍 Syntax:\n" +
            "if (condition) {\n" +
            "    // TRUE wala code\n" +
            "} else {\n" +
            "    // FALSE wala code\n" +
            "}\n\n" +
            "💡 Example: Even/Odd\n" +
            "int num = 7;\n" +
            "if (num % 2 == 0) {\n" +
            "    printf(\"Even\");\n" +
            "} else {\n" +
            "    printf(\"Odd\");\n" +
            "}\n" +
            "Output: Odd\n\n" +
            "🎓 else if - Multiple Conditions\n" +
            "Kai options ke liye else if use karo. Jo PEHLI condition TRUE hogi, wahi block chalega!\n\n" +
            "Important: Sirf EK block execute hoga!"
        );
        lessons.add(lesson11);
        
        Lesson lesson12 = new Lesson(
            "lesson_12", "chapter_3", "switch case",
            // English explanation
            "🎛️ switch case - Menu of Multiple Options\n\n" +
            "Think of a restaurant menu: Option 1-Pizza, 2-Burger, 3-Pasta. You choose a number, you get that dish! switch case works the same way.\n\n" +
            "📝 What is switch case?\n" +
            "When you need to check many fixed values of one variable, use switch case. It's cleaner than if-else ladder.\n\n" +
            "🔍 Syntax:\n" +
            "switch (variable) {\n" +
            "    case value1:\n" +
            "        // Code\n" +
            "        break;\n" +
            "    case value2:\n" +
            "        // Code\n" +
            "        break;\n" +
            "    default:\n" +
            "        // No match\n" +
            "}\n\n" +
            "💡 Example: Days\n" +
            "int day = 3;\n" +
            "switch (day) {\n" +
            "    case 1: printf(\"Monday\"); break;\n" +
            "    case 2: printf(\"Tuesday\"); break;\n" +
            "    case 3: printf(\"Wednesday\"); break;\n" +
            "    default: printf(\"Invalid\");\n" +
            "}\n" +
            "Output: Wednesday\n\n" +
            "⚠️ Why is break Important?\n" +
            "If you don't use break, next cases will also execute (fall-through)!\n\n" +
            "Use: When you need to check multiple fixed values of one variable",
            "switch (variable) {\n    case value1:\n        // code\n        break;\n    case value2:\n        // code\n        break;\n    default:\n        // code\n}",
            "#include <stdio.h>\n\nint main() {\n    int day = 3;\n    \n    switch (day) {\n        case 1:\n            printf(\"Monday\\n\");\n            break;\n        case 2:\n            printf(\"Tuesday\\n\");\n            break;\n        case 3:\n            printf(\"Wednesday\\n\");\n            break;\n        case 4:\n            printf(\"Thursday\\n\");\n            break;\n        case 5:\n            printf(\"Friday\\n\");\n            break;\n        case 6:\n            printf(\"Saturday\\n\");\n            break;\n        case 7:\n            printf(\"Sunday\\n\");\n            break;\n        default:\n            printf(\"Invalid day\\n\");\n    }\n    \n    return 0;\n}",
            "Wednesday",
            "🔄 break statement exits the switch.\n" +
            "🔄 Without break, fall-through occurs.\n" +
            "🔄 Both char and int can be used in switch.",
            "Q: Why is break important in switch case?\nAns: To prevent next case from executing"
            , 3
        );
        // Set Hinglish explanation
        lesson12.setExplanationHinglish(
            "🎛️ switch case - Multiple Options Ka Menu\n\n" +
            "Socho restaurant mein menu hai: Option 1-Pizza, 2-Burger, 3-Pasta. Tum number choose karte ho, tumhe wahi dish milti hai! switch case bhi aisa hi kaam karta hai.\n\n" +
            "📝 switch case Kya Hai?\n" +
            "Jab ek variable ki bahut saari fixed values check karni ho, tab switch case use karte hain. Yeh if-else ladder se zyada clean hai.\n\n" +
            "🔍 Syntax:\n" +
            "switch (variable) {\n" +
            "    case value1:\n" +
            "        // Code\n" +
            "        break;\n" +
            "    case value2:\n" +
            "        // Code\n" +
            "        break;\n" +
            "    default:\n" +
            "        // Koi match nahi\n" +
            "}\n\n" +
            "💡 Example: Days\n" +
            "int day = 3;\n" +
            "switch (day) {\n" +
            "    case 1: printf(\"Monday\"); break;\n" +
            "    case 2: printf(\"Tuesday\"); break;\n" +
            "    case 3: printf(\"Wednesday\"); break;\n" +
            "    default: printf(\"Invalid\");\n" +
            "}\n" +
            "Output: Wednesday\n\n" +
            "⚠️ break Kyu Zaroori?\n" +
            "Agar break nahi lagaya to next cases bhi execute honge (fall-through)!\n\n" +
            "Use: Ek variable ki multiple fixed values check karni ho"
        );
        lessons.add(lesson12);
        
        return lessons;
    }

    // Placeholder methods for other modules (will be implemented similarly)
    private List<Lesson> getModule4Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        // Module 4: Loops - for, while, do-while, nested loops
        Lesson lesson13 = new Lesson(
            "lesson_13", "chapter_4", "for loop",
            // English explanation
            "🔄 for Loop - Repeat Code\n\n" +
            "Imagine you need to print numbers 1 to 10. Writing separate printf() for each number is boring! for loop makes it easy.\n\n" +
            "📝 What Does for Loop Do?\n" +
            "for loop executes code repeatedly as long as the condition is TRUE. When you KNOW how many times to repeat, for loop is perfect!\n\n" +
            "🔍 Syntax:\n" +
            "for (initialization; condition; increment) {\n" +
            "    // Code to repeat\n" +
            "}\n\n" +
            "Three Parts:\n" +
            "• initialization: Where loop starts (i=0)\n" +
            "• condition: Until when it runs (i<5)\n" +
            "• increment: What changes each time (i++)\n\n" +
            "💡 Example: Print 0 to 4\n" +
            "for (int i=0; i<5; i++) {\n" +
            "    printf(\"%d \", i);\n" +
            "}\n\n" +
            "How It Works?\n" +
            "1. i=0 is set\n" +
            "2. Check: 0<5? YES → print 0, i++ → i=1\n" +
            "3. Check: 1<5? YES → print 1, i++ → i=2\n" +
            "4. This continues...\n" +
            "5. Check: 5<5? NO → Loop stops!\n\n" +
            "Use Cases: Traverse arrays, print tables, create patterns",
            "for(int i=0; i<count; i++) { }",
            "for(int i=0; i<5; i++) { printf(\"%d \", i); }",
            "0 1 2 3 4",
            "Loop counter, condition, and increment in one line",
            "Q: What is for loop syntax?"
            , 1
        );
        // Set Hinglish explanation
        lesson13.setExplanationHinglish(
            "🔄 for Loop - Code Ko Repeat Karo\n\n" +
            "Socho tumhe 1 se 10 tak numbers print karne hain. Har number ke liye alag printf() likhna boring hai! for loop isse easy banata hai.\n\n" +
            "📝 for Loop Kya Karta Hai?\n" +
            "for loop code ko baar baar execute karta hai jab tak condition TRUE hai. Jab aapko PATA ho ki kitni baar repeat karna hai, tab for loop perfect hai!\n\n" +
            "🔍 Syntax:\n" +
            "for (initialization; condition; increment) {\n" +
            "    // Repeat hone wala code\n" +
            "}\n\n" +
            "Teen Parts:\n" +
            "• initialization: Loop kahan se start (i=0)\n" +
            "• condition: Kab tak chalega (i<5)\n" +
            "• increment: Har baar kya change (i++)\n\n" +
            "💡 Example: 0 se 4 tak print karo\n" +
            "for (int i=0; i<5; i++) {\n" +
            "    printf(\"%d \", i);\n" +
            "}\n\n" +
            "Kaise Kaam Karta Hai?\n" +
            "1. i=0 set hota hai\n" +
            "2. Check: 0<5? YES → print 0, i++ → i=1\n" +
            "3. Check: 1<5? YES → print 1, i++ → i=2\n" +
            "4. Aisa chalte rehta hai...\n" +
            "5. Check: 5<5? NO → Loop stop!\n\n" +
            "Use Cases: Arrays traverse karna, tables print karna, patterns banana"
        );
        lessons.add(lesson13);
        Lesson lesson14 = new Lesson(
            "lesson_14", "chapter_4", "while loop",
            // English explanation
            "🔁 while Loop - Until Condition is True\n\n" +
            "while loop is used when you need to check the condition FIRST, then execute code. When you DON'T know the exact iteration count, use while loop.\n\n" +
            "📝 What Does while Loop Do?\n" +
            "while loop checks the condition. If TRUE, code executes, then condition is checked again. This continues until condition becomes FALSE.\n\n" +
            "🔍 Syntax:\n" +
            "while (condition) {\n" +
            "    // Code will repeat\n" +
            "}\n\n" +
            "💡 Example:\n" +
            "int i = 0;\n" +
            "while (i < 5) {\n" +
            "    printf(\"%d \", i);\n" +
            "    i++;  // Increment is important!\n" +
            "}\n\n" +
            "Flow:\n" +
            "• Check: 0<5? YES → print 0, i=1\n" +
            "• Check: 1<5? YES → print 1, i=2\n" +
            "• Check: 2<5? YES → print 2, i=3\n" +
            "• Check: 3<5? YES → print 3, i=4\n" +
            "• Check: 4<5? YES → print 4, i=5\n" +
            "• Check: 5<5? NO → Stop!\n\n" +
            "⚠️ Important: Don't forget increment/decrement, otherwise infinite loop!\n\n" +
            "Use Cases: User input validation, menu-driven programs, when iteration count is unknown",
            "while(condition) { }",
            "int i=0; while(i<5) { printf(\"%d \", i); i++; }",
            "0 1 2 3 4",
            "Condition is checked first",
            "Q: When to use while loop?"
            , 2
        );
        // Set Hinglish explanation
        lesson14.setExplanationHinglish(
            "🔁 while Loop - Jab Tak Condition True\n\n" +
            "while loop tab use hota hai jab aapko PEHLE condition check karni ho, phir code execute karna ho. Jab iterations ki exact count NAHI pata, tab while loop use karte hain.\n\n" +
            "📝 while Loop Kya Karta Hai?\n" +
            "while loop condition ko check karta hai. Agar TRUE hai to code execute hota hai, phir dobara condition check hoti hai. Yeh tab tak chalta hai jab tak condition FALSE na ho jaye.\n\n" +
            "🔍 Syntax:\n" +
            "while (condition) {\n" +
            "    // Code repeat hoga\n" +
            "}\n\n" +
            "💡 Example:\n" +
            "int i = 0;\n" +
            "while (i < 5) {\n" +
            "    printf(\"%d \", i);\n" +
            "    i++;  // Increment zaroori hai!\n" +
            "}\n\n" +
            "Flow:\n" +
            "• Check: 0<5? YES → print 0, i=1\n" +
            "• Check: 1<5? YES → print 1, i=2\n" +
            "• Check: 2<5? YES → print 2, i=3\n" +
            "• Check: 3<5? YES → print 3, i=4\n" +
            "• Check: 4<5? YES → print 4, i=5\n" +
            "• Check: 5<5? NO → Stop!\n\n" +
            "⚠️ Important: Increment/decrement karna mat bhoolna, warna infinite loop ho jayega!\n\n" +
            "Use Cases: User input validation, menu-driven programs, jab iterations count unknown ho"
        );
        lessons.add(lesson14);
        
        Lesson lesson15 = new Lesson(
            "lesson_15", "chapter_4", "do-while loop",
            // English explanation
            "🔂 do-while Loop - Do First, Check Later\n\n" +
            "do-while loop is like while loop, but with one important difference: Code executes FIRST, condition is checked LATER.\n\n" +
            "📝 What Does do-while Loop Do?\n" +
            "Guarantee: Code will execute AT LEAST ONCE, even if condition is FALSE!\n\n" +
            "🔍 Syntax:\n" +
            "do {\n" +
            "    // Code executes first\n" +
            "} while (condition);  // Semicolon is important!\n\n" +
            "💡 Example:\n" +
            "int i = 0;\n" +
            "do {\n" +
            "    printf(\"%d \", i);\n" +
            "    i++;\n" +
            "} while (i < 5);\n\n" +
            "⚖️ while vs do-while Difference:\n\n" +
            "while loop:\n" +
            "int i = 10;\n" +
            "while (i < 5) {\n" +
            "    printf(\"Hello\");  // Will NEVER execute!\n" +
            "}\n\n" +
            "do-while loop:\n" +
            "int i = 10;\n" +
            "do {\n" +
            "    printf(\"Hello\");  // Will execute ONCE!\n" +
            "} while (i < 5);\n\n" +
            "Use Cases: Menu-driven programs, input validation (retry on wrong input), when at least one execution is required\n\n" +
            "Remember: Don't forget semicolon (;) after while!",
            "do { } while(condition);",
            "int i=0; do { printf(\"%d \", i); i++; } while(i<5);",
            "0 1 2 3 4",
            "Guaranteed to execute at least once",
            "Q: When to use do-while loop?"
            , 3
        );
        // Set Hinglish explanation
        lesson15.setExplanationHinglish(
            "🔂 do-while Loop - Pehle Karo, Phir Check Karo\n\n" +
            "do-while loop while loop jaisa hi hai, lekin ek important difference hai: Code PEHLE execute hota hai, BAAD mein condition check hoti hai.\n\n" +
            "📝 do-while Loop Kya Karta Hai?\n" +
            "Guarantee: Code KAM SE KAM EK BAAR zaroor execute hoga, chahe condition FALSE hi kyu na ho!\n\n" +
            "🔍 Syntax:\n" +
            "do {\n" +
            "    // Code pehle chalega\n" +
            "} while (condition);  // Semicolon zaroori!\n\n" +
            "💡 Example:\n" +
            "int i = 0;\n" +
            "do {\n" +
            "    printf(\"%d \", i);\n" +
            "    i++;\n" +
            "} while (i < 5);\n\n" +
            "⚖️ while vs do-while Difference:\n\n" +
            "while loop:\n" +
            "int i = 10;\n" +
            "while (i < 5) {\n" +
            "    printf(\"Hello\");  // Kabhi nahi chalega!\n" +
            "}\n\n" +
            "do-while loop:\n" +
            "int i = 10;\n" +
            "do {\n" +
            "    printf(\"Hello\");  // EK BAAR chalega!\n" +
            "} while (i < 5);\n\n" +
            "Use Cases: Menu-driven programs, input validation (galat input pe retry), jab kam se kam ek baar execution zaroori ho\n\n" +
            "Yaad Rakho: while ke baad semicolon (;) lagana mat bhoolna!"
        );
        lessons.add(lesson15);
        Lesson lesson16 = new Lesson(
            "lesson_16", "chapter_4", "Nested loops",
            // English explanation
            "🔄🔄 Nested Loops - Loop Inside Loop\n\n" +
            "Nested loop means one loop INSIDE another loop. Very useful for 2D structures (tables, patterns, matrices).\n\n" +
            "📝 What Does Nested Loop Do?\n" +
            "When outer loop runs once, inner loop executes COMPLETELY. Then outer loop runs again, and inner loop executes completely again.\n\n" +
            "💡 Example: 3x3 Star Pattern\n" +
            "for (int i=1; i<=3; i++) {      // Outer: Rows\n" +
            "    for (int j=1; j<=3; j++) {  // Inner: Columns\n" +
            "        printf(\"* \");\n" +
            "    }\n" +
            "    printf(\"\\n\");  // New line after each row\n" +
            "}\n\n" +
            "How It Works?\n" +
            "• i=1: Inner loop runs 3 times → * * * \n" +
            "• i=2: Inner loop runs 3 times → * * * \n" +
            "• i=3: Inner loop runs 3 times → * * * \n\n" +
            "Output:\n" +
            "* * *\n" +
            "* * *\n" +
            "* * *\n\n" +
            "🎯 Real Examples:\n" +
            "• Print multiplication table\n" +
            "• Traverse 2D arrays\n" +
            "• Patterns (triangle, pyramid, diamond)\n" +
            "• Matrix operations\n\n" +
            "Tip: Outer loop controls rows, inner loop controls columns!",
            "for(i=...) { for(j=...) { } }",
            "for(int i=1; i<=3; i++) { for(int j=1; j<=3; j++) { printf(\"* \"); } printf(\"\\n\"); }",
            "* * *\n* * *\n* * *",
            "For creating patterns and tables",
            "Q: What is nested loop?"
            , 4
        );
        // Set Hinglish explanation
        lesson16.setExplanationHinglish(
            "🔄🔄 Nested Loops - Loop Ke Andar Loop\n\n" +
            "Nested loop matlab ek loop ke ANDAR dusra loop. Yeh 2D structures (tables, patterns, matrices) ke liye bahut useful hai.\n\n" +
            "📝 Nested Loop Kya Karta Hai?\n" +
            "Outer loop ek baar chalti hai, to inner loop PURI tarah se execute hoti hai. Phir outer loop dobara chalti hai, aur inner loop phir se puri chalti hai.\n\n" +
            "💡 Example: 3x3 Star Pattern\n" +
            "for (int i=1; i<=3; i++) {      // Outer: Rows\n" +
            "    for (int j=1; j<=3; j++) {  // Inner: Columns\n" +
            "        printf(\"* \");\n" +
            "    }\n" +
            "    printf(\"\\n\");  // New line after each row\n" +
            "}\n\n" +
            "Kaise Kaam Karta Hai?\n" +
            "• i=1: Inner loop 3 baar chalti → * * * \n" +
            "• i=2: Inner loop 3 baar chalti → * * * \n" +
            "• i=3: Inner loop 3 baar chalti → * * * \n\n" +
            "Output:\n" +
            "* * *\n" +
            "* * *\n" +
            "* * *\n\n" +
            "🎯 Real Examples:\n" +
            "• Multiplication table print karna\n" +
            "• 2D arrays traverse karna\n" +
            "• Patterns (triangle, pyramid, diamond)\n" +
            "• Matrix operations\n\n" +
            "Tip: Outer loop rows control karta hai, inner loop columns control karta hai!"
        );
        lessons.add(lesson16);
        
        return lessons;
    }

    private List<Lesson> getModule5Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        // Module 5: Functions
        Lesson lesson17 = new Lesson(
            "lesson_17", "chapter_5", "Functions basics",
            // English explanation
            "🎯 Functions - Organize Your Code\n\n" +
            "Imagine you need to do the same task repeatedly - like printing \"Hello\". Instead of writing printf() everywhere, create a function!\n\n" +
            "📝 What is a Function?\n" +
            "A function is a block of code that:\n" +
            "• Performs a specific task\n" +
            "• Can be called by name\n" +
            "• Can be reused multiple times\n\n" +
            "🔍 Function Syntax:\n" +
            "return_type function_name(parameters) {\n" +
            "    // Code\n" +
            "    return value;\n" +
            "}\n\n" +
            "Parts:\n" +
            "• return_type: What it returns (int, float, void)\n" +
            "• function_name: Name of the function\n" +
            "• parameters: Input values (optional)\n" +
            "• return: Output value (optional)\n\n" +
            "💡 Simple Example:\n" +
            "void greet() {\n" +
            "    printf(\"Hello, World!\");\n" +
            "}\n\n" +
            "int main() {\n" +
            "    greet();  // Function call\n" +
            "    greet();  // Call again\n" +
            "    return 0;\n" +
            "}\n\n" +
            "Output: Hello, World!Hello, World!\n\n" +
            "✨ Benefits of Functions:\n" +
            "• Reusability: Write once, use anywhere\n" +
            "• Organization: Clean and readable code\n" +
            "• Debugging: Easily find errors\n" +
            "• Teamwork: Different people can write different functions",
            "return_type function_name(parameters) { // code }",
            "#include <stdio.h>\nvoid greet() { printf(\"Hello!\\n\"); }\nint main() { greet(); return 0; }",
            "Hello!",
            "Functions keep code organized",
            "Q: Why use functions?"
            , 1
        );
        // Set Hinglish explanation
        lesson17.setExplanationHinglish(
            "🎯 Functions - Code Ko Organize Karo\n\n" +
            "Socho tumhe ek hi kaam baar baar karna hai - jaise \"Hello\" print karna. Har jagah printf() likhne se achha hai ek function bana lo!\n\n" +
            "📝 Function Kya Hai?\n" +
            "Function ek code ka block hai jo:\n" +
            "• Ek specific kaam karta hai\n" +
            "• Naam se call kar sakte ho\n" +
            "• Baar baar reuse kar sakte ho\n\n" +
            "🔍 Function Syntax:\n" +
            "return_type function_name(parameters) {\n" +
            "    // Code\n" +
            "    return value;\n" +
            "}\n\n" +
            "Parts:\n" +
            "• return_type: Kya return karega (int, float, void)\n" +
            "• function_name: Function ka naam\n" +
            "• parameters: Input values (optional)\n" +
            "• return: Output value (optional)\n\n" +
            "💡 Simple Example:\n" +
            "void greet() {\n" +
            "    printf(\"Hello, World!\");\n" +
            "}\n\n" +
            "int main() {\n" +
            "    greet();  // Function call\n" +
            "    greet();  // Dobara call\n" +
            "    return 0;\n" +
            "}\n\n" +
            "Output: Hello, World!Hello, World!\n\n" +
            "✨ Functions Ke Fayde:\n" +
            "• Reusability: Ek baar likho, kahin bhi use karo\n" +
            "• Organization: Code clean aur readable\n" +
            "• Debugging: Errors easily find karo\n" +
            "• Teamwork: Different functions different log likh sakte hain"
        );
        lessons.add(lesson17);
        Lesson lesson18 = new Lesson(
            "lesson_18", "chapter_5", "Function arguments",
            // English explanation
            "📥 Function Arguments - Pass Data\n\n" +
            "To make functions more powerful, we can pass DATA to them. This allows using the same function with different values!\n\n" +
            "📝 What are Arguments?\n" +
            "Arguments are values passed when calling a function. Inside the function, they're called parameters.\n\n" +
            "🔍 Syntax:\n" +
            "// Function definition\n" +
            "int add(int a, int b) {  // a, b = parameters\n" +
            "    return a + b;\n" +
            "}\n\n" +
            "// Function call\n" +
            "int result = add(5, 3);  // 5, 3 = arguments\n\n" +
            "💡 Complete Example:\n" +
            "int add(int a, int b) {\n" +
            "    return a + b;\n" +
            "}\n\n" +
            "int main() {\n" +
            "    int sum1 = add(5, 3);    // sum1 = 8\n" +
            "    int sum2 = add(10, 20);  // sum2 = 30\n" +
            "    printf(\"%d %d\", sum1, sum2);\n" +
            "    return 0;\n" +
            "}\n\n" +
            "How It Works?\n" +
            "• add(5, 3) call → a=5, b=3 are set\n" +
            "• return 5+3 → 8 is returned\n" +
            "• sum1 = 8 is stored\n\n" +
            "🎯 Types:\n" +
            "• Call by Value: Copy is passed (default in C)\n" +
            "• Multiple Parameters: Separate with comma\n\n" +
            "Remember: Parameter data types and order must match!",
            "function_name(arg1, arg2);",
            "int add(int a, int b) { return a+b; }\nint main() { int sum = add(5, 3); printf(\"%d\", sum); return 0; }",
            "8",
            "Arguments make functions flexible",
            "Q: How to pass data to functions?"
            , 2
        );
        // Set Hinglish explanation
        lesson18.setExplanationHinglish(
            "📥 Function Arguments - Data Pass Karo\n\n" +
            "Functions ko zyada powerful banane ke liye hum unhe DATA pass kar sakte hain. Isse same function different values ke saath use kar sakte ho!\n\n" +
            "📝 Arguments Kya Hain?\n" +
            "Arguments wo values hain jo function ko call karte waqt pass karte hain. Function ke andar inhe parameters kehte hain.\n\n" +
            "🔍 Syntax:\n" +
            "// Function definition\n" +
            "int add(int a, int b) {  // a, b = parameters\n" +
            "    return a + b;\n" +
            "}\n\n" +
            "// Function call\n" +
            "int result = add(5, 3);  // 5, 3 = arguments\n\n" +
            "💡 Complete Example:\n" +
            "int add(int a, int b) {\n" +
            "    return a + b;\n" +
            "}\n\n" +
            "int main() {\n" +
            "    int sum1 = add(5, 3);    // sum1 = 8\n" +
            "    int sum2 = add(10, 20);  // sum2 = 30\n" +
            "    printf(\"%d %d\", sum1, sum2);\n" +
            "    return 0;\n" +
            "}\n\n" +
            "Kaise Kaam Karta Hai?\n" +
            "• add(5, 3) call → a=5, b=3 set hota hai\n" +
            "• return 5+3 → 8 return hota hai\n" +
            "• sum1 = 8 store hota hai\n\n" +
            "🎯 Types:\n" +
            "• Call by Value: Copy pass hoti hai (C mein default)\n" +
            "• Multiple Parameters: Comma se separate karo\n\n" +
            "Yaad Rakho: Parameters ki data type aur order match honi chahiye!"
        );
        lessons.add(lesson18);
        Lesson lesson19 = new Lesson(
            "lesson_19", "chapter_5", "Recursion",
            // English explanation
            "🔄 Recursion - Function Calls Itself\n\n" +
            "Recursion is a special technique where a function calls ITSELF. Used to break complex problems into simple steps.\n\n" +
            "📝 What is Recursion?\n" +
            "When a function calls itself within its code, it's called recursion. Each recursive call makes the problem smaller.\n\n" +
            "⚠️ Important: BASE CONDITION is essential, otherwise infinite recursion!\n\n" +
            "💡 Example: Factorial (5! = 5×4×3×2×1)\n" +
            "int factorial(int n) {\n" +
            "    if (n <= 1)           // Base condition\n" +
            "        return 1;\n" +
            "    return n * factorial(n-1);  // Recursive call\n" +
            "}\n\n" +
            "How It Works?\n" +
            "factorial(5):\n" +
            "• 5 * factorial(4)\n" +
            "  • 4 * factorial(3)\n" +
            "    • 3 * factorial(2)\n" +
            "      • 2 * factorial(1)\n" +
            "        • return 1 (base case)\n" +
            "      • return 2*1 = 2\n" +
            "    • return 3*2 = 6\n" +
            "  • return 4*6 = 24\n" +
            "• return 5*24 = 120\n\n" +
            "🎯 2 Parts of Recursion:\n" +
            "1. Base Case: When recursion stops\n" +
            "2. Recursive Case: Function calls itself\n\n" +
            "Use Cases: Factorial, Fibonacci, Tree traversal, Sorting algorithms\n\n" +
            "Tip: Recursion is powerful but uses more memory. For simple problems, loops are better!",
            "function calls itself",
            "int factorial(int n) { if(n<=1) return 1; return n*factorial(n-1); }\nint main() { printf(\"%d\", factorial(5)); return 0; }",
            "120",
            "Base condition prevents infinite recursion",
            "Q: What is recursion?"
            , 3
        );
        // Set Hinglish explanation
        lesson19.setExplanationHinglish(
            "🔄 Recursion - Function Khud Ko Call Kare\n\n" +
            "Recursion ek special technique hai jahan function KHUD KO call karta hai. Yeh complex problems ko simple steps mein todne ke liye use hota hai.\n\n" +
            "📝 Recursion Kya Hai?\n" +
            "Jab ek function apne code ke andar khud ko call kare, use recursion kehte hain. Har recursive call problem ko chhota karta hai.\n\n" +
            "⚠️ Important: BASE CONDITION zaroori hai, warna infinite recursion ho jayega!\n\n" +
            "💡 Example: Factorial (5! = 5×4×3×2×1)\n" +
            "int factorial(int n) {\n" +
            "    if (n <= 1)           // Base condition\n" +
            "        return 1;\n" +
            "    return n * factorial(n-1);  // Recursive call\n" +
            "}\n\n" +
            "Kaise Kaam Karta Hai?\n" +
            "factorial(5):\n" +
            "• 5 * factorial(4)\n" +
            "  • 4 * factorial(3)\n" +
            "    • 3 * factorial(2)\n" +
            "      • 2 * factorial(1)\n" +
            "        • return 1 (base case)\n" +
            "      • return 2*1 = 2\n" +
            "    • return 3*2 = 6\n" +
            "  • return 4*6 = 24\n" +
            "• return 5*24 = 120\n\n" +
            "🎯 Recursion Ke 2 Parts:\n" +
            "1. Base Case: Jab recursion stop hoga\n" +
            "2. Recursive Case: Function khud ko call karega\n\n" +
            "Use Cases: Factorial, Fibonacci, Tree traversal, Sorting algorithms\n\n" +
            "Tip: Recursion powerful hai lekin memory zyada use karta hai. Simple problems ke liye loops better hain!"
        );
        lessons.add(lesson19);
        return lessons;
    }

    private List<Lesson> getModule6Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        // Module 6: Arrays & Strings
        Lesson lesson20 = new Lesson(
            "lesson_20", "chapter_6", "One-D array",
            // English explanation
            "📊 Arrays - Store Multiple Values Together\n\n" +
            "Imagine you need to store marks of 5 students. Will you create 5 separate variables? (marks1, marks2, marks3...)? Too messy! Array solves this.\n\n" +
            "📝 What is an Array?\n" +
            "An array is a container that can store multiple values of the SAME TYPE. All values under one name, accessed by different index numbers.\n\n" +
            "🔍 Syntax:\n" +
            "data_type array_name[size];\n\n" +
            "Example:\n" +
            "int marks[5];  // Can store 5 integers\n\n" +
            "💡 Initializing Array:\n" +
            "int arr[5] = {10, 20, 30, 40, 50};\n\n" +
            "Index:  0   1   2   3   4\n" +
            "Value: 10  20  30  40  50\n\n" +
            "⚠️ Important: Array index starts from 0!\n" +
            "• First element: arr[0] = 10\n" +
            "• Second element: arr[1] = 20\n" +
            "• Last element: arr[4] = 50\n\n" +
            "🎯 Accessing Array:\n" +
            "printf(\"%d\", arr[2]);  // Output: 30\n" +
            "arr[3] = 100;  // Replace 40 with 100\n\n" +
            "Traverse with loop:\n" +
            "for (int i=0; i<5; i++) {\n" +
            "    printf(\"%d \", arr[i]);\n" +
            "}\n\n" +
            "Use Cases: Store marks, temperatures, scores, list of numbers",
            "data_type array_name[size];",
            "int arr[5] = {1,2,3,4,5};\nfor(int i=0; i<5; i++) printf(\"%d \", arr[i]);",
            "1 2 3 4 5",
            "Array index starts from 0",
            "Q: What is an array?"
            , 1
        );
        // Set Hinglish explanation
        lesson20.setExplanationHinglish(
            "📊 Arrays - Multiple Values Ek Saath Store Karo\n\n" +
            "Socho tumhe 5 students ke marks store karne hain. Kya tum 5 alag variables banoge? (marks1, marks2, marks3...)? Bahut messy! Array isse solve karta hai.\n\n" +
            "📝 Array Kya Hai?\n" +
            "Array ek container hai jo EK HI TYPE ke multiple values store kar sakta hai. Sab values ek naam ke neeche, alag-alag index numbers se access hoti hain.\n\n" +
            "🔍 Syntax:\n" +
            "data_type array_name[size];\n\n" +
            "Example:\n" +
            "int marks[5];  // 5 integers store kar sakta hai\n\n" +
            "💡 Array Initialize Karna:\n" +
            "int arr[5] = {10, 20, 30, 40, 50};\n\n" +
            "Index:  0   1   2   3   4\n" +
            "Value: 10  20  30  40  50\n\n" +
            "⚠️ Important: Array index 0 se start hota hai!\n" +
            "• Pehla element: arr[0] = 10\n" +
            "• Doosra element: arr[1] = 20\n" +
            "• Last element: arr[4] = 50\n\n" +
            "🎯 Array Access Karna:\n" +
            "printf(\"%d\", arr[2]);  // Output: 30\n" +
            "arr[3] = 100;  // 40 ko 100 se replace\n\n" +
            "Loop se traverse karna:\n" +
            "for (int i=0; i<5; i++) {\n" +
            "    printf(\"%d \", arr[i]);\n" +
            "}\n\n" +
            "Use Cases: Marks store karna, temperatures, scores, list of numbers"
        );
        lessons.add(lesson20);
        Lesson lesson21 = new Lesson(
            "lesson_21", "chapter_6", "Two-D array",
            // English explanation
            "📊📊 2D Arrays - Table-like Structure\n\n" +
            "If 1D array is a line, then 2D array is a TABLE - with rows and columns. Like an Excel spreadsheet!\n\n" +
            "📝 What is a 2D Array?\n" +
            "2D array is an array of arrays. Data is organized in rows and columns. Perfect for matrix operations!\n\n" +
            "🔍 Syntax:\n" +
            "data_type array_name[rows][columns];\n\n" +
            "Example:\n" +
            "int matrix[2][3];  // 2 rows, 3 columns\n\n" +
            "💡 Visualization:\n" +
            "int matrix[2][3] = {\n" +
            "    {1, 2, 3},    // Row 0\n" +
            "    {4, 5, 6}     // Row 1\n" +
            "};\n\n" +
            "Table format:\n" +
            "     Col0  Col1  Col2\n" +
            "Row0:  1     2     3\n" +
            "Row1:  4     5     6\n\n" +
            "🎯 Accessing:\n" +
            "matrix[0][1] = 2  // Row 0, Column 1\n" +
            "matrix[1][2] = 6  // Row 1, Column 2\n\n" +
            "Traverse with nested loops:\n" +
            "for (int i=0; i<2; i++) {        // Rows\n" +
            "    for (int j=0; j<3; j++) {    // Columns\n" +
            "        printf(\"%d \", matrix[i][j]);\n" +
            "    }\n" +
            "    printf(\"\\n\");\n" +
            "}\n\n" +
            "Use Cases: Matrix operations, game boards (chess, tic-tac-toe), image processing",
            "data_type array[rows][cols];",
            "int matrix[2][2] = {{1,2},{3,4}};\nprintf(\"%d\", matrix[0][1]);",
            "2",
            "Nested loops traverse 2D arrays",
            "Q: How to declare 2D array?"
            , 2
        );
        // Set Hinglish explanation
        lesson21.setExplanationHinglish(
            "📊📊 2D Arrays - Table Jaisa Structure\n\n" +
            "Agar 1D array ek line hai, to 2D array ek TABLE hai - rows aur columns ke saath. Jaise Excel spreadsheet!\n\n" +
            "📝 2D Array Kya Hai?\n" +
            "2D array ek array of arrays hai. Isme data rows aur columns mein organize hota hai. Matrix operations ke liye perfect!\n\n" +
            "🔍 Syntax:\n" +
            "data_type array_name[rows][columns];\n\n" +
            "Example:\n" +
            "int matrix[2][3];  // 2 rows, 3 columns\n\n" +
            "💡 Visualization:\n" +
            "int matrix[2][3] = {\n" +
            "    {1, 2, 3},    // Row 0\n" +
            "    {4, 5, 6}     // Row 1\n" +
            "};\n\n" +
            "Table format:\n" +
            "     Col0  Col1  Col2\n" +
            "Row0:  1     2     3\n" +
            "Row1:  4     5     6\n\n" +
            "🎯 Access Karna:\n" +
            "matrix[0][1] = 2  // Row 0, Column 1\n" +
            "matrix[1][2] = 6  // Row 1, Column 2\n\n" +
            "Nested loops se traverse:\n" +
            "for (int i=0; i<2; i++) {        // Rows\n" +
            "    for (int j=0; j<3; j++) {    // Columns\n" +
            "        printf(\"%d \", matrix[i][j]);\n" +
            "    }\n" +
            "    printf(\"\\n\");\n" +
            "}\n\n" +
            "Use Cases: Matrix operations, game boards (chess, tic-tac-toe), image processing"
        );
        lessons.add(lesson21);
        Lesson lesson22 = new Lesson(
            "lesson_22", "chapter_6", "String functions",
            // English explanation
            "🔤 Strings - Handle Text\n\n" +
            "In C, string is not a special data type - it's a character array ending with null character (\\0).\n\n" +
            "📝 What is a String?\n" +
            "String is a sequence of characters. In C, string = char array + null terminator.\n\n" +
            "Example:\n" +
            "char name[6] = \"Hello\";\n" +
            "Index: 0='H', 1='e', 2='l', 3='l', 4='o', 5='\\0'\n\n" +
            "🔧 Important String Functions (string.h):\n\n" +
            "1️⃣ strlen() - Get length\n" +
            "char str[] = \"Hello\";\n" +
            "int len = strlen(str);  // len = 5\n\n" +
            "2️⃣ strcpy() - Copy\n" +
            "char dest[20];\n" +
            "strcpy(dest, \"Hello\");  // dest = \"Hello\"\n\n" +
            "3️⃣ strcat() - Concatenate (join)\n" +
            "char str1[20] = \"Hello\";\n" +
            "strcat(str1, \" World\");  // str1 = \"Hello World\"\n\n" +
            "4️⃣ strcmp() - Compare\n" +
            "int result = strcmp(\"abc\", \"abc\");  // result = 0 (equal)\n" +
            "int result = strcmp(\"abc\", \"xyz\");  // result < 0 (abc smaller)\n\n" +
            "⚠️ Important:\n" +
            "• Must #include <string.h> to use string functions\n" +
            "• Destination string must have enough space\n" +
            "• strcmp() returns 0 when strings are equal\n\n" +
            "Use Cases: Store names, text processing, handle user input",
            "#include <string.h>",
            "char str[20] = \"Hello\";\nprintf(\"Length: %d\", strlen(str));",
            "Length: 5",
            "strlen, strcpy, strcmp, strcat are important functions",
            "Q: How to get string length?"
            , 3
        );
        // Set Hinglish explanation
        lesson22.setExplanationHinglish(
            "🔤 Strings - Text Ko Handle Karo\n\n" +
            "C mein string koi special data type nahi hai - yeh character array hai jo null character (\\0) se end hota hai.\n\n" +
            "📝 String Kya Hai?\n" +
            "String characters ka sequence hai. C mein string = char array + null terminator.\n\n" +
            "Example:\n" +
            "char name[6] = \"Hello\";\n" +
            "Index: 0='H', 1='e', 2='l', 3='l', 4='o', 5='\\0'\n\n" +
            "🔧 Important String Functions (string.h):\n\n" +
            "1️⃣ strlen() - Length nikalna\n" +
            "char str[] = \"Hello\";\n" +
            "int len = strlen(str);  // len = 5\n\n" +
            "2️⃣ strcpy() - Copy karna\n" +
            "char dest[20];\n" +
            "strcpy(dest, \"Hello\");  // dest = \"Hello\"\n\n" +
            "3️⃣ strcat() - Concatenate (jodna)\n" +
            "char str1[20] = \"Hello\";\n" +
            "strcat(str1, \" World\");  // str1 = \"Hello World\"\n\n" +
            "4️⃣ strcmp() - Compare karna\n" +
            "int result = strcmp(\"abc\", \"abc\");  // result = 0 (equal)\n" +
            "int result = strcmp(\"abc\", \"xyz\");  // result < 0 (abc chota)\n\n" +
            "⚠️ Important:\n" +
            "• String functions use karne ke liye #include <string.h> zaroori hai\n" +
            "• Destination string mein enough space honi chahiye\n" +
            "• strcmp() 0 return karta hai jab strings equal ho\n\n" +
            "Use Cases: Names store karna, text processing, user input handle karna"
        );
        lessons.add(lesson22);
        return lessons;
    }

    private List<Lesson> getModule7Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        // Module 7: Pointers
        Lesson lesson23 = new Lesson(
            "lesson_23", "chapter_7", "Introduction to pointers",
            // English explanation
            "👉 Pointers - Memory Address\n\n" +
            "Pointer is C's most powerful and tricky concept. It stores a variable's ADDRESS, not the value!\n\n" +
            "📝 What is a Pointer?\n" +
            "Pointer is a variable that stores the memory address of another variable. Instead of storing data, it stores WHERE the data is located.\n\n" +
            "🔍 Syntax:\n" +
            "data_type *pointer_name;\n\n" +
            "Example:\n" +
            "int *ptr;  // Pointer to integer\n\n" +
            "💡 Two Important Operators:\n" +
            "• & (Address-of): Gets variable's address\n" +
            "• * (Dereference): Gets value at address\n\n" +
            "Example:\n" +
            "int num = 10;\n" +
            "int *ptr = &num;  // ptr stores address of num\n\n" +
            "printf(\"%d\", num);    // Output: 10 (value)\n" +
            "printf(\"%p\", &num);   // Output: 0x7fff... (address)\n" +
            "printf(\"%p\", ptr);    // Output: 0x7fff... (same address)\n" +
            "printf(\"%d\", *ptr);   // Output: 10 (value via pointer)\n\n" +
            "🎯 How It Works:\n" +
            "num = 10\n" +
            "Address of num = 0x100\n" +
            "ptr = 0x100 (stores address)\n" +
            "*ptr = 10 (value at that address)\n\n" +
            "⚠️ Important:\n" +
            "• Pointer must be initialized before use\n" +
            "• Uninitialized pointer is dangerous!\n" +
            "• NULL pointer = pointer pointing to nothing\n\n" +
            "Use Cases: Dynamic memory, arrays, functions, data structures",
            "data_type *pointer_name;",
            "int num = 10;\nint *ptr = &num;\nprintf(\"Value: %d, Address: %p\", *ptr, ptr);",
            "Value: 10, Address: 0x7fff...",
            "& gives address, * accesses value",
            "Q: What does pointer store?"
            , 1
        );
        // Set Hinglish explanation
        lesson23.setExplanationHinglish(
            "👉 Pointers - Memory Ka Address\n\n" +
            "Pointer C language ki sabse powerful aur tricky concept hai. Yeh variable ka ADDRESS store karta hai, value nahi!\n\n" +
            "📝 Pointer Kya Hai?\n" +
            "Pointer ek special variable hai jo dusre variable ka MEMORY ADDRESS store karta hai. Isse hum directly memory ko access aur modify kar sakte hain.\n\n" +
            "🔍 Syntax:\n" +
            "data_type *pointer_name;\n\n" +
            "💡 Example:\n" +
            "int num = 10;        // Normal variable\n" +
            "int *ptr = &num;     // Pointer variable\n\n" +
            "Kya ho raha hai?\n" +
            "• num mein value 10 store hai\n" +
            "• &num num ka address deta hai (jaise: 0x7fff5c)\n" +
            "• ptr mein wo address store hota hai\n\n" +
            "🎯 Two Important Operators:\n\n" +
            "1️⃣ & (Address-of operator)\n" +
            "&num → num ka address deta hai\n\n" +
            "2️⃣ * (Dereference operator)\n" +
            "*ptr → ptr ke address par jo value hai wo deta hai\n\n" +
            "Complete Example:\n" +
            "int num = 10;\n" +
            "int *ptr = &num;\n" +
            "printf(\"Value: %d\", *ptr);      // Output: 10\n" +
            "printf(\"Address: %p\", ptr);     // Output: 0x7fff5c...\n" +
            "*ptr = 20;  // num ki value change ho jayegi!\n" +
            "printf(\"New value: %d\", num);   // Output: 20\n\n" +
            "⚠️ Common Mistakes:\n" +
            "• * declare karte waqt lagta hai: int *ptr;\n" +
            "• * value access karne ke liye: *ptr\n" +
            "• & address lene ke liye: &num\n\n" +
            "Use Cases: Dynamic memory, arrays, functions mein pass by reference"
        );
        lessons.add(lesson23);
        Lesson lesson24 = new Lesson(
            "lesson_24", "chapter_7", "Pointer arithmetic",
            // English explanation
            "➕➖ Pointer Arithmetic - Math on Pointers\n\n" +
            "You can perform arithmetic operations on pointers - add, subtract, increment, decrement. Very useful for traversing arrays!\n\n" +
            "📝 What is Pointer Arithmetic?\n" +
            "When you add/subtract from a pointer, it moves by the SIZE of the data type, not by 1 byte.\n\n" +
            "💡 Operations:\n" +
            "• ptr++ : Move to next element\n" +
            "• ptr-- : Move to previous element\n" +
            "• ptr + n : Move n elements forward\n" +
            "• ptr - n : Move n elements backward\n\n" +
            "Example with int array:\n" +
            "int arr[5] = {10, 20, 30, 40, 50};\n" +
            "int *ptr = arr;  // Points to arr[0]\n\n" +
            "printf(\"%d\", *ptr);      // 10\n" +
            "ptr++;                    // Move to next\n" +
            "printf(\"%d\", *ptr);      // 20\n" +
            "ptr += 2;                 // Move 2 forward\n" +
            "printf(\"%d\", *ptr);      // 40\n\n" +
            "🎯 How It Works:\n" +
            "If ptr = 1000 (address)\n" +
            "ptr++ doesn't make it 1001!\n" +
            "For int (4 bytes): ptr++ makes it 1004\n" +
            "For char (1 byte): ptr++ makes it 1001\n\n" +
            "⚠️ Important:\n" +
            "• Pointer arithmetic depends on data type size\n" +
            "• Can only do arithmetic on pointers of same type\n" +
            "• Going beyond array bounds is dangerous!\n\n" +
            "Use Cases: Array traversal, string manipulation, memory navigation",
            "ptr++, ptr--, ptr+n",
            "int arr[] = {10,20,30};\nint *ptr = arr;\nprintf(\"%d %d\", *ptr, *(ptr+1));",
            "10 20",
            "Pointer increment moves to next element",
            "Q: What does ptr++ do?"
            , 2
        );
        // Set Hinglish explanation
        lesson24.setExplanationHinglish(
            "➕➖ Pointer Arithmetic - Pointers Par Math\n\n" +
            "Pointers par arithmetic operations kar sakte ho - add, subtract, increment, decrement. Yeh arrays traverse karne mein bahut useful hai!\n\n" +
            "📝 Pointer Arithmetic Kya Hai?\n" +
            "Jab pointer ko increment/decrement karte ho, to yeh data type ke SIZE ke hisaab se move hota hai, 1 byte nahi!\n\n" +
            "💡 Example:\n" +
            "int arr[] = {10, 20, 30, 40, 50};\n" +
            "int *ptr = arr;  // ptr pehle element ko point karta hai\n\n" +
            "🎯 Operations:\n\n" +
            "1️⃣ ptr++ (Increment)\n" +
            "ptr++;  // Next element par jata hai\n" +
            "// int = 4 bytes, to address 4 bytes aage move hoga\n\n" +
            "2️⃣ ptr-- (Decrement)\n" +
            "ptr--;  // Previous element par jata hai\n\n" +
            "3️⃣ ptr + n (Addition)\n" +
            "ptr + 2;  // 2 elements aage\n\n" +
            "4️⃣ ptr - n (Subtraction)\n" +
            "ptr - 1;  // 1 element peeche\n\n" +
            "Complete Example:\n" +
            "int arr[] = {10, 20, 30};\n" +
            "int *ptr = arr;\n" +
            "printf(\"%d\", *ptr);      // 10 (arr[0])\n" +
            "ptr++;\n" +
            "printf(\"%d\", *ptr);      // 20 (arr[1])\n" +
            "printf(\"%d\", *(ptr+1));  // 30 (arr[2])\n\n" +
            "⚖️ Array vs Pointer:\n" +
            "arr[i] == *(arr + i)  // Dono same hain!\n\n" +
            "Use Cases: Array traversal, dynamic memory navigation, string manipulation"
        );
        lessons.add(lesson24);
        Lesson lesson25 = new Lesson(
            "lesson_25", "chapter_7", "Pointers with array",
            // English explanation
            "🔗 Pointers and Arrays - Best Friends\n\n" +
            "Array name itself is a POINTER! Understanding this concept is very important because arrays and pointers are closely related.\n\n" +
            "📝 Array-Pointer Relationship:\n" +
            "When you declare an array, the array name is a pointer to its first element.\n\n" +
            "int arr[5] = {10, 20, 30, 40, 50};\n" +
            "// arr is same as &arr[0]\n\n" +
            "💡 Two Ways to Access:\n\n" +
            "1️⃣ Array notation:\n" +
            "arr[0], arr[1], arr[2]...\n\n" +
            "2️⃣ Pointer notation:\n" +
            "*arr, *(arr+1), *(arr+2)...\n\n" +
            "Both are SAME!\n" +
            "arr[i] = *(arr + i)\n\n" +
            "Example:\n" +
            "int arr[3] = {10, 20, 30};\n" +
            "int *ptr = arr;\n\n" +
            "printf(\"%d\", arr[0]);    // 10\n" +
            "printf(\"%d\", *arr);      // 10 (same!)\n" +
            "printf(\"%d\", arr[1]);    // 20\n" +
            "printf(\"%d\", *(arr+1));  // 20 (same!)\n" +
            "printf(\"%d\", ptr[2]);    // 30\n" +
            "printf(\"%d\", *(ptr+2));  // 30 (same!)\n\n" +
            "🎯 Traversing Array with Pointer:\n" +
            "for (int *p = arr; p < arr+5; p++) {\n" +
            "    printf(\"%d \", *p);\n" +
            "}\n\n" +
            "⚠️ Important:\n" +
            "• Array name is a constant pointer (can't change it)\n" +
            "• Pointer variable can be changed\n" +
            "• arr++ is INVALID, but ptr++ is valid\n\n" +
            "Use Cases: Efficient array operations, passing arrays to functions",
            "arr[i] == *(arr+i)",
            "int arr[] = {10,20,30};\nint *ptr = arr;\nprintf(\"%d %d\", arr[1], *(ptr+1));",
            "20 20",
            "Array name is a pointer to first element",
            "Q: What is relationship between arrays and pointers?"
            , 3
        );
        // Set Hinglish explanation
        lesson25.setExplanationHinglish(
            "🔗 Pointers aur Arrays - Best Friends\n\n" +
            "Array name khud ek POINTER hai! Yeh concept samajhna bahut important hai kyunki arrays aur pointers closely related hain.\n\n" +
            "📝 Array Name = Pointer\n" +
            "Jab tum array declare karte ho, array ka naam pehle element ka address hota hai.\n\n" +
            "int arr[5] = {1, 2, 3, 4, 5};\n" +
            "// arr == &arr[0]  // Dono same address!\n\n" +
            "💡 Two Ways to Access:\n\n" +
            "1️⃣ Array Notation:\n" +
            "arr[0], arr[1], arr[2]...\n\n" +
            "2️⃣ Pointer Notation:\n" +
            "*arr, *(arr+1), *(arr+2)...\n\n" +
            "Dono SAME hain!\n" +
            "arr[i] = *(arr + i)\n\n" +
            "Example:\n" +
            "int arr[3] = {10, 20, 30};\n" +
            "int *ptr = arr;  // Ya int *ptr = &arr[0];\n\n" +
            "printf(\"%d\", arr[0]);    // 10\n" +
            "printf(\"%d\", *arr);      // 10 (same!)\n" +
            "printf(\"%d\", arr[1]);    // 20\n" +
            "printf(\"%d\", *(arr+1));  // 20 (same!)\n" +
            "printf(\"%d\", ptr[2]);    // 30\n" +
            "printf(\"%d\", *(ptr+2));  // 30 (same!)\n\n" +
            "🎯 Pointer se Array Traverse:\n" +
            "for (int *p = arr; p < arr+3; p++) {\n" +
            "    printf(\"%d \", *p);\n" +
            "}\n\n" +
            "⚠️ Important:\n" +
            "• Array name constant pointer hai (change nahi kar sakte)\n" +
            "• Pointer variable change kar sakte hain\n" +
            "• arr++ INVALID hai, lekin ptr++ valid hai\n\n" +
            "Use Cases: Efficient array operations, functions mein arrays pass karna"
        );
        lessons.add(lesson25);
        return lessons;
    }

    private List<Lesson> getModule8Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        // Module 8: Structures
        Lesson lesson26 = new Lesson(
            "lesson_26", "chapter_8", "Structure",
            // English explanation
            "📦 Structures - Group Different Types\n\n" +
            "Imagine you need to store student details: name, age, marks. Instead of creating three separate variables, group them all in a structure!\n\n" +
            "📝 What is a Structure?\n" +
            "Structure is a user-defined data type that groups variables of DIFFERENT types under one name.\n\n" +
            "🔍 Syntax:\n" +
            "struct structure_name {\n" +
            "    data_type member1;\n" +
            "    data_type member2;\n" +
            "    ...\n" +
            "};\n\n" +
            "💡 Example: Student Structure\n" +
            "struct Student {\n" +
            "    char name[50];\n" +
            "    int age;\n" +
            "    float marks;\n" +
            "};\n\n" +
            "// Create variable\n" +
            "struct Student s1;\n\n" +
            "// Access members\n" +
            "s1.age = 20;\n" +
            "strcpy(s1.name, \"Rahul\");\n" +
            "s1.marks = 85.5;\n\n" +
            "printf(\"%s is %d years old\", s1.name, s1.age);\n\n" +
            "🎯 Initialize at Declaration:\n" +
            "struct Student s1 = {\"Rahul\", 20, 85.5};\n\n" +
            "⚠️ Important:\n" +
            "• Use dot (.) operator to access members\n" +
            "• Can create array of structures\n" +
            "• Can pass structures to functions\n" +
            "• Each structure variable has its own copy of members\n\n" +
            "Use Cases: Store related data together, database records, complex data modeling",
            "struct structure_name { members; };",
            "struct Student { char name[50]; int age; };\nstruct Student s1 = {\"Raj\", 20};\nprintf(\"%s is %d years old\", s1.name, s1.age);",
            "Raj is 20 years old",
            "Dot operator accesses members",
            "Q: What is a structure?"
            , 1
        );
        // Set Hinglish explanation
        lesson26.setExplanationHinglish(
            "📦 Structures - Different Types Ko Group Karo\n\n" +
            "Socho tumhe ek student ki details store karni hai: name, age, marks. Teen alag variables banane se achha hai sab ko ek structure mein group kar lo!\n\n" +
            "📝 Structure Kya Hai?\n" +
            "Structure ek user-defined data type hai jo DIFFERENT types ke data ko ek saath store kar sakta hai. Yeh real-world entities represent karne ke liye perfect hai.\n\n" +
            "🔍 Syntax:\n" +
            "struct structure_name {\n" +
            "    data_type member1;\n" +
            "    data_type member2;\n" +
            "    ...\n" +
            "};\n\n" +
            "💡 Example: Student Structure\n" +
            "struct Student {\n" +
            "    char name[50];\n" +
            "    int age;\n" +
            "    float marks;\n" +
            "};\n\n" +
            "🎯 Structure Variable Banana:\n" +
            "struct Student s1;  // Declaration\n\n" +
            "// Initialize karna:\n" +
            "struct Student s1 = {\"Raj\", 20, 85.5};\n\n" +
            "// Members access karna (dot operator):\n" +
            "s1.age = 21;\n" +
            "printf(\"%s\", s1.name);\n" +
            "printf(\"%f\", s1.marks);\n\n" +
            "Complete Example:\n" +
            "struct Student {\n" +
            "    char name[50];\n" +
            "    int age;\n" +
            "};\n\n" +
            "int main() {\n" +
            "    struct Student s1 = {\"Aryan\", 20};\n" +
            "    printf(\"Name: %s\\n\", s1.name);\n" +
            "    printf(\"Age: %d\\n\", s1.age);\n" +
            "    return 0;\n" +
            "}\n\n" +
            "✨ Structures Ke Fayde:\n" +
            "• Related data ko organize karna\n" +
            "• Code readability improve hoti hai\n" +
            "• Real-world entities model karna easy\n\n" +
            "Use Cases: Student records, employee data, book information, coordinates (x,y)"
        );
        lessons.add(lesson26);
        Lesson lesson27 = new Lesson(
            "lesson_27", "chapter_8", "Union",
            // English explanation
            "🔄 Union - Same Memory, Different Uses\n\n" +
            "Union looks like structure, but with one BIG difference: All members share the SAME MEMORY!\n\n" +
            "📝 What is a Union?\n" +
            "Union is like structure, but all members occupy the same memory location. Only ONE member can hold value at a time.\n\n" +
            "🔍 Syntax:\n" +
            "union union_name {\n" +
            "    data_type member1;\n" +
            "    data_type member2;\n" +
            "    ...\n" +
            "};\n\n" +
            "💡 Example:\n" +
            "union Data {\n" +
            "    int i;\n" +
            "    float f;\n" +
            "    char c;\n" +
            "};\n\n" +
            "union Data d;\n" +
            "d.i = 10;     // Stores integer\n" +
            "printf(\"%d\", d.i);  // 10\n\n" +
            "d.f = 3.14;   // Overwrites integer!\n" +
            "printf(\"%f\", d.f);  // 3.14\n" +
            "printf(\"%d\", d.i);  // Garbage! (overwritten)\n\n" +
            "⚖️ Structure vs Union:\n\n" +
            "Structure:\n" +
            "• Each member has separate memory\n" +
            "• All members can hold values simultaneously\n" +
            "• Size = sum of all members\n\n" +
            "Union:\n" +
            "• All members share same memory\n" +
            "• Only one member can hold value at a time\n" +
            "• Size = size of largest member\n\n" +
            "🎯 Memory Comparison:\n" +
            "struct S { int i; float f; };  // Size: 8 bytes\n" +
            "union U { int i; float f; };   // Size: 4 bytes\n\n" +
            "⚠️ Important:\n" +
            "• Use when you need only one member at a time\n" +
            "• Saves memory\n" +
            "• Be careful - changing one member affects others!\n\n" +
            "Use Cases: Memory optimization, type conversion, embedded systems",
            "union union_name { members; };",
            "union Data { int i; float f; };\nunion Data d;\nd.i = 10;\nd.f = 3.14;\nprintf(\"%f\", d.f);",
            "3.14",
            "Union members share same memory",
            "Q: What is difference between structure and union?"
            , 2
        );
        // Set Hinglish explanation
        lesson27.setExplanationHinglish(
            "🔄 Union - Same Memory, Different Uses\n\n" +
            "Union structure jaisa hi lagta hai, lekin ek BIG difference hai: Sab members SAME MEMORY share karte hain!\n\n" +
            "📝 Union Kya Hai?\n" +
            "Union mein sab members same memory location use karte hain. Ek time par sirf EK member ka data valid hota hai.\n\n" +
            "🔍 Syntax:\n" +
            "union union_name {\n" +
            "    data_type member1;\n" +
            "    data_type member2;\n" +
            "};\n\n" +
            "💡 Example:\n" +
            "union Data {\n" +
            "    int i;\n" +
            "    float f;\n" +
            "    char c;\n" +
            "};\n\n" +
            "union Data d;\n" +
            "d.i = 10;\n" +
            "printf(\"%d\", d.i);  // 10\n\n" +
            "d.f = 3.14;  // Integer overwrite ho jayega!\n" +
            "printf(\"%f\", d.f);  // 3.14\n" +
            "printf(\"%d\", d.i);  // Garbage value!\n\n" +
            "⚖️ Structure vs Union:\n\n" +
            "Structure:\n" +
            "• Har member ka alag memory\n" +
            "• Sab members saath mein value hold kar sakte\n" +
            "• Size = sab members ka sum\n\n" +
            "Union:\n" +
            "• Sab members same memory share\n" +
            "• Ek time par sirf ek member\n" +
            "• Size = sabse bade member ka size\n\n" +
            "🎯 Memory Example:\n" +
            "struct S { int i; float f; };  // 8 bytes\n" +
            "union U { int i; float f; };   // 4 bytes\n\n" +
            "⚠️ Important:\n" +
            "• Jab sirf ek member chahiye tab use karo\n" +
            "• Memory save hoti hai\n" +
            "• Dhyan rakhna - ek member change karne se dusre affect hote!\n\n" +
            "Use Cases: Memory optimization, type conversion, embedded systems"
        );
        lessons.add(lesson27);
        Lesson lesson28 = new Lesson(
            "lesson_28", "chapter_8", "Enum",
            // English explanation
            "🔢 Enum - Named Constants\n\n" +
            "Enum (Enumeration) is a way to define named integer constants. It makes code more readable and maintainable.\n\n" +
            "📝 What is an Enum?\n" +
            "Enum is a user-defined type that groups related constants. By default, the first value is 0, then 1, 2, 3...\n\n" +
            "🔍 Syntax:\n" +
            "enum enum_name {\n" +
            "    constant1,\n" +
            "    constant2,\n" +
            "    constant3\n" +
            "};\n\n" +
            "💡 Example: Days of Week\n" +
            "enum Day {\n" +
            "    MON,    // 0\n" +
            "    TUE,    // 1\n" +
            "    WED,    // 2\n" +
            "    THU,    // 3\n" +
            "    FRI     // 4\n" +
            "};\n\n" +
            "enum Day today = WED;\n" +
            "printf(\"%d\", today);  // Output: 2\n\n" +
            "🎯 Custom Values:\n" +
            "enum Day {\n" +
            "    MON = 1,  // Start from 1\n" +
            "    TUE,      // 2\n" +
            "    WED,      // 3\n" +
            "    THU = 10, // Jump to 10\n" +
            "    FRI       // 11\n" +
            "};\n\n" +
            "Complete Example:\n" +
            "enum Color { RED, GREEN, BLUE };\n\n" +
            "int main() {\n" +
            "    enum Color c = GREEN;\n" +
            "    if (c == GREEN) {\n" +
            "        printf(\"Go!\");\n" +
            "    }\n" +
            "    return 0;\n" +
            "}\n\n" +
            "✨ Enum Benefits:\n" +
            "• Improves code readability (GREEN vs 1)\n" +
            "• Prevents typos\n" +
            "• Provides meaningful names\n\n" +
            "Use Cases: Days, months, colors, status codes, menu options",
            "enum enum_name { constant1, constant2 };",
            "enum Day {MON=1, TUE, WED};\nenum Day today = TUE;\nprintf(\"%d\", today);",
            "2",
            "Code readability improve hoti hai",
            "Q: Enum kyu use karte hain?", 3
        );
        // Set Hinglish explanation
        lesson28.setExplanationHinglish(
            "🔢 Enum - Named Constants\n\n" +
            "Enum (Enumeration) named integer constants define karne ka tarika hai. Yeh code ko zyada readable aur maintainable banata hai.\n\n" +
            "📝 Enum Kya Hai?\n" +
            "Enum ek user-defined type hai jo related constants ko group karta hai. By default, pehla value 0 hota hai, phir 1, 2, 3...\n\n" +
            "🔍 Syntax:\n" +
            "enum enum_name {\n" +
            "    constant1,\n" +
            "    constant2,\n" +
            "    constant3\n" +
            "};\n\n" +
            "💡 Example: Days of Week\n" +
            "enum Day {\n" +
            "    MON,    // 0\n" +
            "    TUE,    // 1\n" +
            "    WED,    // 2\n" +
            "    THU,    // 3\n" +
            "    FRI     // 4\n" +
            "};\n\n" +
            "enum Day today = WED;\n" +
            "printf(\"%d\", today);  // Output: 2\n\n" +
            "🎯 Custom Values:\n" +
            "enum Day {\n" +
            "    MON = 1,  // Start from 1\n" +
            "    TUE,      // 2\n" +
            "    WED,      // 3\n" +
            "    THU = 10, // Jump to 10\n" +
            "    FRI       // 11\n" +
            "};\n\n" +
            "Complete Example:\n" +
            "enum Color { RED, GREEN, BLUE };\n\n" +
            "int main() {\n" +
            "    enum Color c = GREEN;\n" +
            "    if (c == GREEN) {\n" +
            "        printf(\"Go!\");\n" +
            "    }\n" +
            "    return 0;\n" +
            "}\n\n" +
            "✨ Enum Ke Fayde:\n" +
            "• Code readable hota hai (GREEN vs 1)\n" +
            "• Typos se bachata hai\n" +
            "• Meaningful names\n\n" +
            "Use Cases: Days, months, colors, status codes, menu options"
        );
        lessons.add(lesson28);
        return lessons;
    }

    private List<Lesson> getModule9Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        // Module 9: File Handling
        Lesson lesson29 = new Lesson(
            "lesson_29", "chapter_9", "File open/close",
            // English explanation
            "📁 File Handling - Store Data Permanently\n\n" +
            "Want to keep data even after program closes? Use file handling! You can permanently store data in files.\n\n" +
            "📝 What is File Handling?\n" +
            "File handling allows reading from and writing to files on disk. Data persists even after program ends.\n\n" +
            "🔍 File Operations:\n" +
            "1. Open file\n" +
            "2. Read/Write data\n" +
            "3. Close file\n\n" +
            "💡 Opening a File:\n" +
            "FILE *fptr;\n" +
            "fptr = fopen(\"filename.txt\", \"mode\");\n\n" +
            "Modes:\n" +
            "• \"r\" - Read (file must exist)\n" +
            "• \"w\" - Write (creates new, overwrites existing)\n" +
            "• \"a\" - Append (adds to end)\n" +
            "• \"r+\" - Read and Write\n\n" +
            "Example:\n" +
            "FILE *fptr = fopen(\"data.txt\", \"w\");\n\n" +
            "if (fptr == NULL) {\n" +
            "    printf(\"Error opening file!\");\n" +
            "    return 1;\n" +
            "}\n\n" +
            "🎯 Closing a File:\n" +
            "fclose(fptr);\n\n" +
            "⚠️ Important:\n" +
            "• Always check if file opened successfully\n" +
            "• Always close file after use\n" +
            "• Forgetting to close can cause data loss\n" +
            "• FILE is defined in stdio.h\n\n" +
            "Complete Example:\n" +
            "FILE *fptr = fopen(\"test.txt\", \"w\");\n" +
            "if (fptr != NULL) {\n" +
            "    // Do file operations\n" +
            "    fclose(fptr);\n" +
            "}\n\n" +
            "Use Cases: Save game progress, store user data, logging, configuration files",
            "FILE *fp = fopen(\"file.txt\", \"mode\");\nfclose(fp);",
            "FILE *fp = fopen(\"test.txt\", \"w\");\nif(fp != NULL) {\n    fprintf(fp, \"Hello File\");\n    fclose(fp);\n}",
            "File created with content",
            "Modes: r(read), w(write), a(append)",
            "Q: How to open a file?"
            , 1
        );
        // Set Hinglish explanation
        lesson29.setExplanationHinglish(
            "📁 File Handling - Data Permanently Store Karo\n\n" +
            "Program band hone ke baad bhi data save rakhna hai? File handling use karo! Files mein data permanently store kar sakte ho.\n\n" +
            "📝 File Handling Kya Hai?\n" +
            "File handling se hum disk par files create, read, write, aur close kar sakte hain. Data program ke baad bhi safe rahta hai.\n\n" +
            "🔍 Basic Steps:\n" +
            "1. File open karo (fopen)\n" +
            "2. Operations karo (read/write)\n" +
            "3. File close karo (fclose)\n\n" +
            "💡 File Open Karna:\n" +
            "FILE *fp;\n" +
            "fp = fopen(\"filename.txt\", \"mode\");\n\n" +
            "🎯 File Modes:\n" +
            "• \"r\" - Read (file exist honi chahiye)\n" +
            "• \"w\" - Write (naya file ya overwrite)\n" +
            "• \"a\" - Append (end mein add karo)\n" +
            "• \"r+\" - Read + Write\n" +
            "• \"w+\" - Write + Read\n\n" +
            "Complete Example:\n" +
            "#include <stdio.h>\n\n" +
            "int main() {\n" +
            "    FILE *fp;\n" +
            "    fp = fopen(\"test.txt\", \"w\");\n" +
            "    \n" +
            "    if (fp == NULL) {\n" +
            "        printf(\"Error opening file!\");\n" +
            "        return 1;\n" +
            "    }\n" +
            "    \n" +
            "    fprintf(fp, \"Hello File!\");\n" +
            "    fclose(fp);  // ZAROORI!\n" +
            "    return 0;\n" +
            "}\n\n" +
            "⚠️ Important:\n" +
            "• File open karne ke baad NULL check karo\n" +
            "• Operations ke baad fclose() karna ZAROORI hai\n" +
            "• Mode sahi choose karo (r/w/a)\n\n" +
            "Use Cases: Data storage, logs, configuration files, reports"
        );
        lessons.add(lesson29);
        Lesson lesson30 = new Lesson(
            "lesson_30", "chapter_9", "Read/Write file",
            // English explanation
            "✍️📖 File Read/Write - Data Operations\n\n" +
            "After opening a file, you can read or write data to it. Different functions for different operations.\n\n" +
            "📝 Writing to File:\n\n" +
            "1️⃣ fprintf() - Formatted write\n" +
            "FILE *fptr = fopen(\"data.txt\", \"w\");\n" +
            "fprintf(fptr, \"Hello %s\", \"World\");\n" +
            "fclose(fptr);\n\n" +
            "2️⃣ fputs() - Write string\n" +
            "fputs(\"Hello World\", fptr);\n\n" +
            "3️⃣ fputc() - Write single character\n" +
            "fputc('A', fptr);\n\n" +
            "💡 Reading from File:\n\n" +
            "1️⃣ fscanf() - Formatted read\n" +
            "FILE *fptr = fopen(\"data.txt\", \"r\");\n" +
            "char str[50];\n" +
            "fscanf(fptr, \"%s\", str);\n" +
            "fclose(fptr);\n\n" +
            "2️⃣ fgets() - Read line\n" +
            "char line[100];\n" +
            "fgets(line, 100, fptr);\n\n" +
            "3️⃣ fgetc() - Read single character\n" +
            "char ch = fgetc(fptr);\n\n" +
            "🎯 Complete Example:\n\n" +
            "// Writing\n" +
            "FILE *fptr = fopen(\"student.txt\", \"w\");\n" +
            "fprintf(fptr, \"Name: %s\\n\", \"Rahul\");\n" +
            "fprintf(fptr, \"Age: %d\\n\", 20);\n" +
            "fclose(fptr);\n\n" +
            "// Reading\n" +
            "fptr = fopen(\"student.txt\", \"r\");\n" +
            "char name[50];\n" +
            "int age;\n" +
            "fscanf(fptr, \"Name: %s\", name);\n" +
            "fscanf(fptr, \"Age: %d\", &age);\n" +
            "printf(\"%s is %d years old\", name, age);\n" +
            "fclose(fptr);\n\n" +
            "⚠️ Important:\n" +
            "• Check if file opened successfully\n" +
            "• Use correct mode (r/w/a)\n" +
            "• Close file after operations\n" +
            "• feof() checks if end of file reached\n\n" +
            "Use Cases: Save/load data, text processing, data analysis, file conversion",
            "fprintf(fp, \"format\", data); fscanf(fp, \"format\", &var);",
            "FILE *fp = fopen(\"test.txt\", \"w\");\nfprintf(fp, \"Score: %d\", 95);\nfclose(fp);\nfp = fopen(\"test.txt\", \"r\");\nint score;\nfscanf(fp, \"Score: %d\", &score);\nprintf(\"%d\", score);\nfclose(fp);",
            "95",
            "fprintf writes, fscanf reads",
            "Q: How to write to a file?"
            , 2
        );
        // Set Hinglish explanation
        lesson30.setExplanationHinglish(
            "✍️📖 File Read/Write - Data Operations\n\n" +
            "File open karne ke baad usme data read ya write kar sakte ho. Different functions hain different operations ke liye.\n\n" +
            "📝 File Operations:\n\n" +
            "1️⃣ Write to File (fprintf)\n" +
            "fprintf(fp, \"format\", data);\n\n" +
            "Example:\n" +
            "FILE *fp = fopen(\"data.txt\", \"w\");\n" +
            "fprintf(fp, \"Score: %d\", 95);\n" +
            "fclose(fp);\n" +
            "// File mein: Score: 95\n\n" +
            "2️⃣ Read from File (fscanf)\n" +
            "fscanf(fp, \"format\", &variable);\n\n" +
            "Example:\n" +
            "FILE *fp = fopen(\"data.txt\", \"r\");\n" +
            "int score;\n" +
            "fscanf(fp, \"Score: %d\", &score);\n" +
            "printf(\"Score = %d\", score);\n" +
            "fclose(fp);\n\n" +
            "🎯 Other Useful Functions:\n\n" +
            "• fgetc(fp) - Ek character read\n" +
            "• fputc('A', fp) - Ek character write\n" +
            "• fgets(str, size, fp) - String read\n" +
            "• fputs(str, fp) - String write\n\n" +
            "💡 Complete Example:\n" +
            "// Write\n" +
            "FILE *fp = fopen(\"marks.txt\", \"w\");\n" +
            "fprintf(fp, \"Math: %d\\n\", 85);\n" +
            "fprintf(fp, \"Science: %d\\n\", 90);\n" +
            "fclose(fp);\n\n" +
            "// Read\n" +
            "fp = fopen(\"marks.txt\", \"r\");\n" +
            "int m1, m2;\n" +
            "fscanf(fp, \"Math: %d\", &m1);\n" +
            "fscanf(fp, \"Science: %d\", &m2);\n" +
            "printf(\"Total: %d\", m1+m2);\n" +
            "fclose(fp);\n\n" +
            "⚠️ Important Points:\n" +
            "• Read ke liye file exist honi chahiye\n" +
            "• Write mode mein file overwrite ho jati hai\n" +
            "• Append mode se end mein add hota hai\n" +
            "• feof() se check karo file end hui ya nahi\n\n" +
            "Use Cases: Data save/load karna, text files process karna, reports generate karna"
        );
        lessons.add(lesson30);
        return lessons;
    }

    private List<Lesson> getModule10Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        // Module 10: Comprehensive Quiz Module - 9 Quizzes (one per module)
        
        lessons.add(new Lesson("lesson_31", "chapter_10", "📝 Module 1 Quiz: C Basics", 
            "Test your knowledge of C Programming basics!\n\n" +
            "Topics Covered:\n" +
            "• What is C?\n" +
            "• History of C\n" +
            "• Features of C\n" +
            "• Basic syntax\n" +
            "• Program structure\n\n" +
            "📊 Total Questions: 10\n" +
            "🎯 Passing: 60%", 
            "", "", "", 
            "Click 'Take Quiz' button below to start!", 
            "Q: Ready for Module 1 Quiz?", 1));
        
        lessons.add(new Lesson("lesson_32", "chapter_10", "📝 Module 2 Quiz: Data & Operators", 
            "Test your knowledge of Data Types and Operators!\n\n" +
            "Topics Covered:\n" +
            "• Data types (int, float, char, double)\n" +
            "• Variables and constants\n" +
            "• Arithmetic operators\n" +
            "• Relational & logical operators\n" +
            "• Type casting\n\n" +
            "📊 Total Questions: 20-25\n" +
            "🎯 Passing: 60%", 
            "", "", "", 
            "Click 'Take Quiz' button below to start!", 
            "Q: Ready for Module 2 Quiz?", 2));
        
        lessons.add(new Lesson("lesson_33", "chapter_10", "📝 Module 3 Quiz: Control Statements", 
            "Test your knowledge of Control Statements!\n\n" +
            "Topics Covered:\n" +
            "• if statement\n" +
            "• if-else\n" +
            "• switch-case\n" +
            "• Nested conditions\n" +
            "• Ternary operator\n\n" +
            "📊 Total Questions: 20-25\n" +
            "🎯 Passing: 60%", 
            "", "", "", 
            "Click 'Take Quiz' button below to start!", 
            "Q: Ready for Module 3 Quiz?", 3));
        
        lessons.add(new Lesson("lesson_34", "chapter_10", "📝 Module 4 Quiz: Loops", 
            "Test your knowledge of Loops!\n\n" +
            "Topics Covered:\n" +
            "• for loop\n" +
            "• while loop\n" +
            "• do-while loop\n" +
            "• Nested loops\n" +
            "• break & continue\n\n" +
            "📊 Total Questions: 20-25\n" +
            "🎯 Passing: 60%", 
            "", "", "", 
            "Click 'Take Quiz' button below to start!", 
            "Q: Ready for Module 4 Quiz?", 4));
        
        lessons.add(new Lesson("lesson_35", "chapter_10", "📝 Module 5 Quiz: Functions", 
            "Test your knowledge of Functions!\n\n" +
            "Topics Covered:\n" +
            "• Function basics\n" +
            "• Function arguments\n" +
            "• Return types\n" +
            "• Recursion\n" +
            "• Call by value/reference\n\n" +
            "📊 Total Questions: 20-25\n" +
            "🎯 Passing: 60%", 
            "", "", "", 
            "Click 'Take Quiz' button below to start!", 
            "Q: Ready for Module 5 Quiz?", 5));
        
        lessons.add(new Lesson("lesson_36", "chapter_10", "📝 Module 6 Quiz: Arrays & Strings", 
            "Test your knowledge of Arrays and Strings!\n\n" +
            "Topics Covered:\n" +
            "• 1D and 2D arrays\n" +
            "• Array operations\n" +
            "• String basics\n" +
            "• String functions\n" +
            "• Character arrays\n\n" +
            "📊 Total Questions: 20-25\n" +
            "🎯 Passing: 60%", 
            "", "", "", 
            "Click 'Take Quiz' button below to start!", 
            "Q: Ready for Module 6 Quiz?", 6));
        
        lessons.add(new Lesson("lesson_37", "chapter_10", "📝 Module 7 Quiz: Pointers", 
            "Test your knowledge of Pointers!\n\n" +
            "Topics Covered:\n" +
            "• Pointer basics\n" +
            "• Address & dereference operators\n" +
            "• Pointer arithmetic\n" +
            "• Pointers with arrays\n" +
            "• Pointer to pointer\n\n" +
            "📊 Total Questions: 20-25\n" +
            "🎯 Passing: 60%", 
            "", "", "", 
            "Click 'Take Quiz' button below to start!", 
            "Q: Ready for Module 7 Quiz?", 7));
        
        lessons.add(new Lesson("lesson_38", "chapter_10", "📝 Module 8 Quiz: Structures", 
            "Test your knowledge of Structures!\n\n" +
            "Topics Covered:\n" +
            "• Structure basics\n" +
            "• Union\n" +
            "• Enum\n" +
            "• typedef\n" +
            "• Nested structures\n\n" +
            "📊 Total Questions: 20-25\n" +
            "🎯 Passing: 60%", 
            "", "", "", 
            "Click 'Take Quiz' button below to start!", 
            "Q: Ready for Module 8 Quiz?", 8));
        
        lessons.add(new Lesson("lesson_39", "chapter_10", "📝 Module 9 Quiz: File Handling", 
            "Test your knowledge of File Handling!\n\n" +
            "Topics Covered:\n" +
            "• File operations\n" +
            "• fopen, fclose\n" +
            "• File modes\n" +
            "• fprintf, fscanf\n" +
            "• File reading/writing\n\n" +
            "📊 Total Questions: 20-25\n" +
            "🎯 Passing: 60%", 
            "", "", "", 
            "Click 'Take Quiz' button below to start!", 
            "Q: Ready for Module 9 Quiz?", 9));
        
        // Final Comprehensive C Quiz - 100 Questions
        lessons.add(new Lesson("c_final_quiz", "chapter_final", "🏆 Final Quiz: Complete C Test", 
            "🎯 Comprehensive C Programming Assessment!\n\n" +
            "Test your complete C knowledge with 100 questions!\n\n" +
            "📊 Difficulty Levels:\n" +
            "• Easy: Questions 1-25\n" +
            "• Medium: Questions 26-50\n" +
            "• Hard: Questions 51-75\n" +
            "• Very Hard: Questions 76-100\n\n" +
            "⏱️ Time: 120 minutes\n" +
            "🎯 Passing: 70%\n\n" +
            "Complete all 9 modules before attempting!", 
            "", "", "", 
            "Click 'Take Quiz' for final assessment!", 
            "Q: Ready for ultimate C challenge?", 10));
        
        return lessons;
    }

    // Get quizzes for a chapter
    public List<Quiz> getQuizzesForChapter(String chapterId) {
        List<Quiz> quizzes = new ArrayList<>();
        
        // Sample quizzes for each chapter
        switch (chapterId) {
            case "chapter_1":
                // Quiz 1: Who developed C language?
                Quiz quiz1_1 = createQuiz("quiz_1_1", "chapter_1",
                    "Who developed the C language?",
                    "Bjarne Stroustrup", "Dennis Ritchie", "James Gosling", "Guido van Rossum",
                    1, "Dennis Ritchie developed the C language in 1972 at Bell Labs.");
                quiz1_1.setQuestionHinglish("C language ko kisne develop kiya?");
                quiz1_1.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Bjarne Stroustrup", "Dennis Ritchie", "James Gosling", "Guido van Rossum"
                )));
                quiz1_1.setExplanationHinglish("Dennis Ritchie ne 1972 mein Bell Labs mein C language develop ki thi.");
                quizzes.add(quiz1_1);
                
                // Quiz 2: C program extension
                Quiz quiz1_2 = createQuiz("quiz_1_2", "chapter_1",
                    "What is the file extension for C programs?",
                    ".cpp", ".c", ".java", ".py",
                    1, "C program files have the extension .c, such as program.c");
                quiz1_2.setQuestionHinglish("C program ka extension kya hota hai?");
                quiz1_2.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    ".cpp", ".c", ".java", ".py"
                )));
                quiz1_2.setExplanationHinglish("C program files ka extension .c hota hai, jaise program.c");
                quizzes.add(quiz1_2);
                
                // Quiz 3: Type of language
                Quiz quiz1_3 = createQuiz("quiz_1_3", "chapter_1",
                    "What type of language is C?",
                    "High-level", "Low-level", "Middle-level", "Assembly",
                    2, "C is a middle-level language that provides both low-level and high-level features.");
                quiz1_3.setQuestionHinglish("C language kis type ki language hai?");
                quiz1_3.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "High-level", "Low-level", "Middle-level", "Assembly"
                )));
                quiz1_3.setExplanationHinglish("C ek middle-level language hai jo low-level aur high-level dono features provide karti hai.");
                quizzes.add(quiz1_3);
                
                // Quiz 4: Starting function
                Quiz quiz1_4 = createQuiz("quiz_1_4", "chapter_1",
                    "Every C program starts with which function?",
                    "start()", "begin()", "main()", "init()",
                    2, "Every C program starts with the main() function. It is the entry point of the program.");
                quiz1_4.setQuestionHinglish("Har C program kis function se start hota hai?");
                quiz1_4.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "start()", "begin()", "main()", "init()"
                )));
                quiz1_4.setExplanationHinglish("Har C program main() function se start hota hai. Yeh program ka entry point hai.");
                quizzes.add(quiz1_4);
                
                // Quiz 5: printf header file
                Quiz quiz1_5 = createQuiz("quiz_1_5", "chapter_1",
                    "In which header file is the printf() function defined?",
                    "stdlib.h", "stdio.h", "string.h", "math.h",
                    1, "The printf() function is defined in the stdio.h (Standard Input/Output) header file.");
                quiz1_5.setQuestionHinglish("printf() function kis header file mein define hai?");
                quiz1_5.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "stdlib.h", "stdio.h", "string.h", "math.h"
                )));
                quiz1_5.setExplanationHinglish("printf() function stdio.h (Standard Input/Output) header file mein define hai.");
                quizzes.add(quiz1_5);
                break;
                
            case "chapter_2":
                // Quiz 1: int size
                Quiz quiz2_1 = createQuiz("quiz_2_1", "chapter_2",
                    "What is the size of int data type?",
                    "2 bytes", "4 bytes", "8 bytes", "1 byte",
                    1, "On modern systems, the int data type is 4 bytes.");
                quiz2_1.setQuestionHinglish("int data type ki size kitni hoti hai?");
                quiz2_1.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "2 bytes", "4 bytes", "8 bytes", "1 byte"
                )));
                quiz2_1.setExplanationHinglish("Modern systems mein int data type ki size 4 bytes hoti hai.");
                quizzes.add(quiz2_1);
                
                // Quiz 2: Decimal numbers
                Quiz quiz2_2 = createQuiz("quiz_2_2", "chapter_2",
                    "Which data type is used to store decimal numbers?",
                    "int", "char", "float", "void",
                    2, "float and double are used to store decimal numbers.");
                quiz2_2.setQuestionHinglish("Decimal numbers store karne ke liye kaunsa data type use hota hai?");
                quiz2_2.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "int", "char", "float", "void"
                )));
                quiz2_2.setExplanationHinglish("float aur double decimal numbers store karne ke liye use hote hain.");
                quizzes.add(quiz2_2);
                
                // Quiz 3: Ampersand in scanf
                Quiz quiz2_3 = createQuiz("quiz_2_3", "chapter_2",
                    "Why is & (ampersand) used in scanf()?",
                    "For value", "For address", "For pointer", "For reference",
                    1, "In scanf(), & is used to provide the address of the variable so the value can be stored at that location.");
                quiz2_3.setQuestionHinglish("scanf() mein & (ampersand) kyu use hota hai?");
                quiz2_3.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Value ke liye", "Address ke liye", "Pointer ke liye", "Reference ke liye"
                )));
                quiz2_3.setExplanationHinglish("scanf() mein & variable ka address dene ke liye use hota hai taaki value us location par store ho sake.");
                quizzes.add(quiz2_3);
                
                // Quiz 4: Modulus operator
                Quiz quiz2_4 = createQuiz("quiz_2_4", "chapter_2",
                    "What will be the result of 10 % 3?",
                    "3", "1", "0", "10",
                    1, "The % (modulus) operator returns the remainder. 10 ÷ 3 = 3 remainder 1, so the answer is 1.");
                quiz2_4.setQuestionHinglish("10 % 3 ka result kya hoga?");
                quiz2_4.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "3", "1", "0", "10"
                )));
                quiz2_4.setExplanationHinglish("% (modulus) operator remainder deta hai. 10 ÷ 3 = 3 remainder 1, isliye answer 1 hai.");
                quizzes.add(quiz2_4);
                
                // Quiz 5: Post vs Pre increment
                Quiz quiz2_5 = createQuiz("quiz_2_5", "chapter_2",
                    "What is the difference between a++ and ++a?",
                    "No difference", "Post-increment vs Pre-increment", "Syntax error", "Both are invalid",
                    1, "a++ is post-increment (use first, then increment), ++a is pre-increment (increment first, then use).");
                quiz2_5.setQuestionHinglish("a++ aur ++a mein kya difference hai?");
                quiz2_5.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Koi difference nahi", "Post-increment vs Pre-increment", "Syntax error", "Dono invalid hain"
                )));
                quiz2_5.setExplanationHinglish("a++ post-increment hai (pehle use, phir increment), ++a pre-increment hai (pehle increment, phir use).");
                quizzes.add(quiz2_5);
                break;
                
            case "chapter_3":
                // Quiz 1: if statement brackets
                Quiz quiz3_1 = createQuiz("quiz_3_1", "chapter_3",
                    "In which brackets do we write the condition in an if statement?",
                    "{ }", "[ ]", "( )", "< >",
                    2, "In if statements, the condition is written in parentheses ( ).");
                quiz3_1.setQuestionHinglish("if statement mein condition ko kis bracket mein likhte hain?");
                quiz3_1.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "{ }", "[ ]", "( )", "< >"
                )));
                quiz3_1.setExplanationHinglish("if statement mein condition ko parentheses ( ) mein likhte hain.");
                quizzes.add(quiz3_1);
                
                // Quiz 2: break in switch
                Quiz quiz3_2 = createQuiz("quiz_3_2", "chapter_3",
                    "Why is the break statement used in switch case?",
                    "To end loop", "To prevent next case from executing", "To end program", "To handle errors",
                    1, "The break statement exits the switch case, otherwise the next case will also execute (fall-through).");
                quiz3_2.setQuestionHinglish("switch case mein break statement kyu use hota hai?");
                quiz3_2.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Loop khatam karne ke liye", "Next case execute hone se rokne ke liye", "Program end karne ke liye", "Error handle karne ke liye"
                )));
                quiz3_2.setExplanationHinglish("break statement se switch case se bahar aa jate hain, nahi to next case bhi execute ho jayega (fall-through).");
                quizzes.add(quiz3_2);
                
                // Quiz 3: Nested if-else levels
                Quiz quiz3_3 = createQuiz("quiz_3_3", "chapter_3",
                    "How many levels can nested if-else have?",
                    "Only 2", "Only 3", "No limit", "Only 5",
                    2, "There is no limit to nested if-else, but for readability, it's best to keep it to 3-4 levels.");
                quiz3_3.setQuestionHinglish("Nested if-else mein kitne levels ho sakte hain?");
                quiz3_3.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Sirf 2", "Sirf 3", "Koi limit nahi", "Sirf 5"
                )));
                quiz3_3.setExplanationHinglish("Nested if-else mein koi limit nahi hai, lekin readability ke liye 3-4 levels tak hi rakhna chahiye.");
                quizzes.add(quiz3_3);
                
                // Quiz 4: Ternary operator
                Quiz quiz3_4 = createQuiz("quiz_3_4", "chapter_3",
                    "What is the syntax of the ternary operator?",
                    "condition ? true : false", "condition : true ? false", "true ? condition : false", "? condition : true false",
                    0, "Ternary operator: condition ? value_if_true : value_if_false");
                quiz3_4.setQuestionHinglish("Ternary operator ka syntax kya hai?");
                quiz3_4.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "condition ? true : false", "condition : true ? false", "true ? condition : false", "? condition : true false"
                )));
                quiz3_4.setExplanationHinglish("Ternary operator: condition ? value_if_true : value_if_false");
                quizzes.add(quiz3_4);
                
                // Quiz 5: default case
                Quiz quiz3_5 = createQuiz("quiz_3_5", "chapter_3",
                    "When does the default case execute in switch case?",
                    "Always", "Never", "When no case matches", "After first case",
                    2, "The default case executes when no other case matches.");
                quiz3_5.setQuestionHinglish("switch case mein default case kab execute hota hai?");
                quiz3_5.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Hamesha", "Kabhi nahi", "Jab koi case match na ho", "Pehle case ke baad"
                )));
                quiz3_5.setExplanationHinglish("default case tab execute hota hai jab koi bhi case match nahi hota.");
                quizzes.add(quiz3_5);
                break;
                
            case "chapter_4":
                // Quiz 1: for loop parts
                Quiz quiz4_1 = createQuiz("quiz_4_1", "chapter_4",
                    "How many parts does a for loop have?",
                    "1", "2", "3", "4",
                    2, "A for loop has 3 parts: initialization, condition, and increment/decrement.");
                quiz4_1.setQuestionHinglish("for loop ke kitne parts hote hain?");
                quiz4_1.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "1", "2", "3", "4"
                )));
                quiz4_1.setExplanationHinglish("for loop ke 3 parts hote hain: initialization, condition, increment/decrement.");
                quizzes.add(quiz4_1);
                
                // Quiz 2: do-while minimum execution
                Quiz quiz4_2 = createQuiz("quiz_4_2", "chapter_4",
                    "How many times will code execute at minimum in a do-while loop?",
                    "0 times", "1 time", "2 times", "Depends on condition",
                    1, "In a do-while loop, code executes at least 1 time because the condition is checked after execution.");
                quiz4_2.setQuestionHinglish("do-while loop mein code kam se kam kitni baar execute hoga?");
                quiz4_2.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "0 baar", "1 baar", "2 baar", "Condition par depend karta hai"
                )));
                quiz4_2.setExplanationHinglish("do-while loop mein code kam se kam 1 baar zaroor execute hota hai kyunki condition baad mein check hoti hai.");
                quizzes.add(quiz4_2);
                
                // Quiz 3: Infinite loop
                Quiz quiz4_3 = createQuiz("quiz_4_3", "chapter_4",
                    "What do we write to create an infinite loop?",
                    "for(;;)", "while(1)", "do{}while(1)", "All are correct",
                    3, "All three methods are correct for creating an infinite loop: for(;;), while(1), do{}while(1)");
                quiz4_3.setQuestionHinglish("Infinite loop banane ke liye kya likhte hain?");
                quiz4_3.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "for(;;)", "while(1)", "do{}while(1)", "Sabhi sahi hain"
                )));
                quiz4_3.setExplanationHinglish("Teeno tarike infinite loop banane ke liye sahi hain: for(;;), while(1), do{}while(1)");
                quizzes.add(quiz4_3);
                
                // Quiz 4: break statement
                Quiz quiz4_4 = createQuiz("quiz_4_4", "chapter_4",
                    "What does the break statement do?",
                    "Skips the loop", "Exits the loop", "Restarts the loop", "Slows the loop",
                    1, "The break statement immediately terminates the loop and exits from it.");
                quiz4_4.setQuestionHinglish("break statement ka kya kaam hai?");
                quiz4_4.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Loop ko skip karta hai", "Loop se bahar nikalta hai", "Loop ko restart karta hai", "Loop ko slow karta hai"
                )));
                quiz4_4.setExplanationHinglish("break statement loop ko turant terminate kar deta hai aur loop se bahar aa jate hain.");
                quizzes.add(quiz4_4);
                
                // Quiz 5: continue statement
                Quiz quiz4_5 = createQuiz("quiz_4_5", "chapter_4",
                    "What does the continue statement do?",
                    "Ends the loop", "Skips current iteration and goes to next", "Pauses the loop", "Reverses the loop",
                    1, "The continue statement skips the current iteration and directly goes to the next iteration.");
                quiz4_5.setQuestionHinglish("continue statement kya karta hai?");
                quiz4_5.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Loop khatam kar deta hai", "Current iteration skip karke next iteration par jata hai", "Loop ko pause karta hai", "Loop ko reverse karta hai"
                )));
                quiz4_5.setExplanationHinglish("continue statement current iteration ko skip karke directly next iteration par chala jata hai.");
                quizzes.add(quiz4_5);
                break;
                
            case "chapter_5":
                // Quiz 1: void return type
                Quiz quiz5_1 = createQuiz("quiz_5_1", "chapter_5",
                    "What is the return type of a function that doesn't return any value?",
                    "int", "void", "null", "empty",
                    1, "void return type means the function will not return any value.");
                quiz5_1.setQuestionHinglish("Function jo koi value return nahi karta uska return type kya hoga?");
                quiz5_1.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "int", "void", "null", "empty"
                )));
                quiz5_1.setExplanationHinglish("void return type ka matlab hai ki function koi value return nahi karega.");
                quizzes.add(quiz5_1);
                
                // Quiz 2: Recursion base condition
                Quiz quiz5_2 = createQuiz("quiz_5_2", "chapter_5",
                    "What is essential in recursion?",
                    "Loop", "Base condition", "Array", "Pointer",
                    1, "A base condition is essential in recursion to prevent infinite recursion.");
                quiz5_2.setQuestionHinglish("Recursion mein kya zaroori hai?");
                quiz5_2.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Loop", "Base condition", "Array", "Pointer"
                )));
                quiz5_2.setExplanationHinglish("Recursion mein base condition zaroori hai taaki infinite recursion na ho.");
                quizzes.add(quiz5_2);
                
                // Quiz 3: Call by value
                Quiz quiz5_3 = createQuiz("quiz_5_3", "chapter_5",
                    "What is passed in call by value?",
                    "Variable's address", "Copy of variable", "Variable's reference", "Pointer",
                    1, "In call by value, a copy of the variable is passed, the original value doesn't change.");
                quiz5_3.setQuestionHinglish("Function call by value mein kya pass hota hai?");
                quiz5_3.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Variable ka address", "Variable ki copy", "Variable ka reference", "Pointer"
                )));
                quiz5_3.setExplanationHinglish("Call by value mein variable ki copy pass hoti hai, original value change nahi hoti.");
                quizzes.add(quiz5_3);
                
                // Quiz 4: Function prototype
                Quiz quiz5_4 = createQuiz("quiz_5_4", "chapter_5",
                    "Why do we declare a function prototype?",
                    "To save memory", "To inform compiler about the function", "To increase speed", "To handle errors",
                    1, "Function prototype informs the compiler about the function's return type and parameters in advance.");
                quiz5_4.setQuestionHinglish("Function prototype kyu declare karte hain?");
                quiz5_4.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Memory save karne ke liye", "Compiler ko function ke baare mein batane ke liye", "Speed badhane ke liye", "Error handle karne ke liye"
                )));
                quiz5_4.setExplanationHinglish("Function prototype compiler ko function ke return type aur parameters ke baare mein pehle se bata deta hai.");
                quizzes.add(quiz5_4);
                
                // Quiz 5: main() return type
                Quiz quiz5_5 = createQuiz("quiz_5_5", "chapter_5",
                    "What is the return type of main() function?",
                    "void", "int", "float", "char",
                    1, "The main() function has a return type of int, which tells the operating system the program's status.");
                quiz5_5.setQuestionHinglish("main() function ka return type kya hota hai?");
                quiz5_5.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "void", "int", "float", "char"
                )));
                quiz5_5.setExplanationHinglish("main() function ka return type int hota hai, jo operating system ko program ki status batata hai.");
                quizzes.add(quiz5_5);
                break;
            
            case "chapter_6":
                // Quiz 1: Array index
                Quiz quiz6_1 = createQuiz("quiz_6_1", "chapter_6",
                    "Where does array index start from?",
                    "0", "1", "-1", "Anything",
                    0, "In C, array index always starts from 0.");
                quiz6_1.setQuestionHinglish("Array index kahan se start hota hai?");
                quiz6_1.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "0", "1", "-1", "Kuch bhi"
                )));
                quiz6_1.setExplanationHinglish("C mein array index hamesha 0 se start hota hai.");
                quizzes.add(quiz6_1);
                
                // Quiz 2: 2D array syntax
                Quiz quiz6_2 = createQuiz("quiz_6_2", "chapter_6",
                    "What is the correct syntax to declare a 2D array?",
                    "int arr[3,4]", "int arr[3][4]", "int arr(3)(4)", "int arr{3}{4}",
                    1, "2D array is declared in the format: int arr[rows][columns]");
                quiz6_2.setQuestionHinglish("2D array declare karne ka sahi syntax kya hai?");
                quiz6_2.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "int arr[3,4]", "int arr[3][4]", "int arr(3)(4)", "int arr{3}{4}"
                )));
                quiz6_2.setExplanationHinglish("2D array: int arr[rows][columns] format mein declare hota hai.");
                quizzes.add(quiz6_2);
                
                // Quiz 3: What is string
                Quiz quiz6_3 = createQuiz("quiz_6_3", "chapter_6",
                    "What is a string?",
                    "Single character", "Character array", "Integer array", "Float array",
                    1, "A string is a character array that ends with a null character (\\0).");
                quiz6_3.setQuestionHinglish("String kya hai?");
                quiz6_3.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Single character", "Character array", "Integer array", "Float array"
                )));
                quiz6_3.setExplanationHinglish("String ek character array hai jo null character (\\0) se end hota hai.");
                quizzes.add(quiz6_3);
                
                // Quiz 4: strlen function
                Quiz quiz6_4 = createQuiz("quiz_6_4", "chapter_6",
                    "What does strlen() function return?",
                    "String size in bytes", "String length (excluding null character)", "String address", "String type",
                    1, "strlen() function returns the length of the string, not counting the null character.");
                quiz6_4.setQuestionHinglish("strlen() function kya return karta hai?");
                quiz6_4.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "String ka size bytes mein", "String ki length (null character ko chhod kar)", "String ka address", "String ka type"
                )));
                quiz6_4.setExplanationHinglish("strlen() function string ki length return karta hai, null character ko count nahi karta.");
                quizzes.add(quiz6_4);
                
                // Quiz 5: strcmp function
                Quiz quiz6_5 = createQuiz("quiz_6_5", "chapter_6",
                    "When does strcmp() function return 0?",
                    "When both strings are different", "When both strings are same", "When first string is larger", "When second string is larger",
                    1, "strcmp() function returns 0 when both strings are exactly the same.");
                quiz6_5.setQuestionHinglish("strcmp() function kab 0 return karta hai?");
                quiz6_5.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Jab dono strings different ho", "Jab dono strings same ho", "Jab pehli string badi ho", "Jab doosri string badi ho"
                )));
                quiz6_5.setExplanationHinglish("strcmp() function 0 return karta hai jab dono strings exactly same hoti hain.");
                quizzes.add(quiz6_5);
                break;
            
            case "chapter_7":
                // Quiz 1: What pointer stores
                Quiz quiz7_1 = createQuiz("quiz_7_1", "chapter_7",
                    "What does a pointer store?",
                    "Value", "Memory address", "Data type", "Variable name",
                    1, "A pointer stores the memory address of a variable.");
                quiz7_1.setQuestionHinglish("Pointer kya store karta hai?");
                quiz7_1.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Value", "Memory address", "Data type", "Variable name"
                )));
                quiz7_1.setExplanationHinglish("Pointer variable ka memory address store karta hai.");
                quizzes.add(quiz7_1);
                
                // Quiz 2: Address operator
                Quiz quiz7_2 = createQuiz("quiz_7_2", "chapter_7",
                    "Which is the address operator?",
                    "*", "&", "#", "@",
                    1, "& (ampersand) is the address operator that gives the address of a variable.");
                quiz7_2.setQuestionHinglish("Address operator kaun sa hai?");
                quiz7_2.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "*", "&", "#", "@"
                )));
                quiz7_2.setExplanationHinglish("& (ampersand) address operator hai jo variable ka address deta hai.");
                quizzes.add(quiz7_2);
                
                // Quiz 3: Dereference operator
                Quiz quiz7_3 = createQuiz("quiz_7_3", "chapter_7",
                    "Which is the dereference operator?",
                    "&", "*", "->", ".",
                    1, "* (asterisk) is the dereference operator that gives the value stored at the pointer's address.");
                quiz7_3.setQuestionHinglish("Dereference operator kaun sa hai?");
                quiz7_3.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "&", "*", "->", "."
                )));
                quiz7_3.setExplanationHinglish("* (asterisk) dereference operator hai jo pointer ke address par stored value deta hai.");
                quizzes.add(quiz7_3);
                
                // Quiz 4: NULL pointer
                Quiz quiz7_4 = createQuiz("quiz_7_4", "chapter_7",
                    "What does NULL pointer mean?",
                    "Pointer that doesn't point to anything", "Pointer that points to 0", "Invalid pointer", "All are correct",
                    0, "NULL pointer is a pointer that doesn't point to any valid memory location.");
                quiz7_4.setQuestionHinglish("NULL pointer ka kya matlab hai?");
                quiz7_4.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Pointer jo kisi ko point nahi karta", "Pointer jo 0 ko point karta hai", "Invalid pointer", "Sabhi sahi hain"
                )));
                quiz7_4.setExplanationHinglish("NULL pointer wo pointer hai jo kisi bhi valid memory location ko point nahi karta.");
                quizzes.add(quiz7_4);
                
                // Quiz 5: Pointer arithmetic
                Quiz quiz7_5 = createQuiz("quiz_7_5", "chapter_7",
                    "What does ptr++ do in pointer arithmetic?",
                    "Moves pointer 1 byte forward", "Moves pointer forward by data type size", "Increases pointer value by 1", "Nothing",
                    1, "ptr++ moves the pointer forward according to its data type size (e.g., 4 bytes for int*).");
                quiz7_5.setQuestionHinglish("Pointer arithmetic mein ptr++ kya karta hai?");
                quiz7_5.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Pointer ko 1 byte aage badhata hai", "Pointer ko data type ke size ke hisab se aage badhata hai", "Pointer ki value 1 badhata hai", "Kuch nahi"
                )));
                quiz7_5.setExplanationHinglish("ptr++ pointer ko uske data type ke size ke hisab se aage badhata hai (e.g., int* ke liye 4 bytes).");
                quizzes.add(quiz7_5);
                break;
            
            case "chapter_8":
                // Quiz 1: Structure different types
                Quiz quiz8_1 = createQuiz("quiz_8_1", "chapter_8",
                    "Can we group different data types in a structure?",
                    "Yes", "No", "Only int and float", "Only same type",
                    0, "Yes, we can group different data types together in a structure.");
                quiz8_1.setQuestionHinglish("Structure mein different data types ko group kar sakte hain?");
                quiz8_1.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Haan", "Nahi", "Sirf int aur float", "Sirf same type"
                )));
                quiz8_1.setExplanationHinglish("Structure mein different data types ko ek saath group kar sakte hain.");
                quizzes.add(quiz8_1);
                
                // Quiz 2: Structure member access
                Quiz quiz8_2 = createQuiz("quiz_8_2", "chapter_8",
                    "Which operator is used to access structure members?",
                    "->", ".", ":", "*",
                    1, "The dot operator (.) is used to access structure members.");
                quiz8_2.setQuestionHinglish("Structure member access karne ke liye kaun sa operator use hota hai?");
                quiz8_2.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "->", ".", ":", "*"
                )));
                quiz8_2.setExplanationHinglish("Dot operator (.) structure member access karne ke liye use hota hai.");
                quizzes.add(quiz8_2);
                
                // Quiz 3: Union vs Structure
                Quiz quiz8_3 = createQuiz("quiz_8_3", "chapter_8",
                    "What is the difference between Union and Structure?",
                    "No difference", "In Union all members share same memory", "Union can have only one data type", "Structure is faster",
                    1, "In Union, all members share the same memory location, whereas in Structure, each member has separate memory.");
                quiz8_3.setQuestionHinglish("Union aur Structure mein kya difference hai?");
                quiz8_3.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Koi difference nahi", "Union mein sabhi members same memory share karte hain", "Union mein sirf ek data type ho sakta hai", "Structure faster hai"
                )));
                quiz8_3.setExplanationHinglish("Union mein sabhi members same memory location share karte hain, jabki structure mein alag-alag memory hoti hai.");
                quizzes.add(quiz8_3);
                
                // Quiz 4: typedef
                Quiz quiz8_4 = createQuiz("quiz_8_4", "chapter_8",
                    "What is the use of typedef?",
                    "To create new data type", "To give new name to existing type", "To convert types", "To check types",
                    1, "typedef is used to give a new name to an existing data type.");
                quiz8_4.setQuestionHinglish("typedef ka kya use hai?");
                quiz8_4.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "New data type banane ke liye", "Existing type ko naya naam dene ke liye", "Type convert karne ke liye", "Type check karne ke liye"
                )));
                quiz8_4.setExplanationHinglish("typedef existing data type ko naya naam dene ke liye use hota hai.");
                quizzes.add(quiz8_4);
                
                // Quiz 5: Arrow operator
                Quiz quiz8_5 = createQuiz("quiz_8_5", "chapter_8",
                    "When is the arrow operator (->) used?",
                    "For array access", "For pointer to structure member access", "For function call", "For loop",
                    1, "The arrow operator (->) is used to access members of a structure through a pointer.");
                quiz8_5.setQuestionHinglish("Arrow operator (->) kab use hota hai?");
                quiz8_5.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Array access ke liye", "Pointer to structure member access ke liye", "Function call ke liye", "Loop ke liye"
                )));
                quiz8_5.setExplanationHinglish("Arrow operator (->) pointer to structure ke members ko access karne ke liye use hota hai.");
                quizzes.add(quiz8_5);
                break;
            
            case "chapter_9":
                // Quiz 1: File open function
                Quiz quiz9_1 = createQuiz("quiz_9_1", "chapter_9",
                    "Which function is used to open a file?",
                    "open()", "fopen()", "file_open()", "readfile()",
                    1, "The fopen() function is used to open a file.");
                quiz9_1.setQuestionHinglish("File open karne ke liye kaun sa function use hota hai?");
                quiz9_1.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "open()", "fopen()", "file_open()", "readfile()"
                )));
                quiz9_1.setExplanationHinglish("fopen() function file open karne ke liye use hota hai.");
                quizzes.add(quiz9_1);
                
                // Quiz 2: File mode 'r'
                Quiz quiz9_2 = createQuiz("quiz_9_2", "chapter_9",
                    "What does file mode 'r' mean?",
                    "Write mode", "Read mode", "Append mode", "Read and write",
                    1, "'r' mode opens the file for reading.");
                quiz9_2.setQuestionHinglish("File mode 'r' ka kya matlab hai?");
                quiz9_2.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Write mode", "Read mode", "Append mode", "Read and write"
                )));
                quiz9_2.setExplanationHinglish("'r' mode file ko read karne ke liye open karta hai.");
                quizzes.add(quiz9_2);
                
                // Quiz 3: File mode 'w'
                Quiz quiz9_3 = createQuiz("quiz_9_3", "chapter_9",
                    "What does file mode 'w' do?",
                    "Reads the file", "Writes to file (existing content deleted)", "Appends to file", "Copies the file",
                    1, "'w' mode opens the file for writing, if the file exists its content is deleted.");
                quiz9_3.setQuestionHinglish("File mode 'w' kya karta hai?");
                quiz9_3.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "File ko read karta hai", "File ko write karta hai (existing content delete ho jata hai)", "File mein append karta hai", "File ko copy karta hai"
                )));
                quiz9_3.setExplanationHinglish("'w' mode file ko write karne ke liye open karta hai, agar file exist karti hai to uska content delete ho jata hai.");
                quizzes.add(quiz9_3);
                
                // Quiz 4: File close function
                Quiz quiz9_4 = createQuiz("quiz_9_4", "chapter_9",
                    "Which function is used to close a file?",
                    "close()", "fclose()", "file_close()", "end()",
                    1, "The fclose() function is used to close a file.");
                quiz9_4.setQuestionHinglish("File close karne ke liye kaun sa function use hota hai?");
                quiz9_4.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "close()", "fclose()", "file_close()", "end()"
                )));
                quiz9_4.setExplanationHinglish("fclose() function file close karne ke liye use hota hai.");
                quizzes.add(quiz9_4);
                
                // Quiz 5: fprintf vs printf
                Quiz quiz9_5 = createQuiz("quiz_9_5", "chapter_9",
                    "What is the difference between fprintf() and printf()?",
                    "No difference", "fprintf() writes to file", "fprintf() is faster", "printf() writes to file",
                    1, "fprintf() writes formatted output to a file, while printf() prints to the screen.");
                quiz9_5.setQuestionHinglish("fprintf() aur printf() mein kya difference hai?");
                quiz9_5.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Koi difference nahi", "fprintf() file mein write karta hai", "fprintf() faster hai", "printf() file mein write karta hai"
                )));
                quiz9_5.setExplanationHinglish("fprintf() file mein formatted output write karta hai, jabki printf() screen par print karta hai.");
                quizzes.add(quiz9_5);
                break;
            
            case "chapter_10":
                // Final Quiz - 50 Comprehensive MCQs (Level-Based)
                // EASY LEVEL (15 MCQs) - Basic concepts, syntax, simple recall
                
                // Quiz 1: Who created C
                Quiz quiz10_1 = createQuiz("quiz_10_1", "chapter_10",
                    "Who created the C language?",
                    "Dennis Ritchie", "Bjarne Stroustrup", "James Gosling", "Guido van Rossum",
                    0, "Dennis Ritchie developed the C language in 1972.");
                quiz10_1.setQuestionHinglish("C language ko kisne banaya?");
                quiz10_1.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Dennis Ritchie", "Bjarne Stroustrup", "James Gosling", "Guido van Rossum"
                )));
                quiz10_1.setExplanationHinglish("Dennis Ritchie ne 1972 mein C language develop ki thi.");
                quizzes.add(quiz10_1);
                
                // Quiz 2: int size
                Quiz quiz10_2 = createQuiz("quiz_10_2", "chapter_10",
                    "How many bytes is the int data type?",
                    "2 bytes", "4 bytes", "8 bytes", "1 byte",
                    1, "The int data type is typically 4 bytes on modern systems.");
                quiz10_2.setQuestionHinglish("int data type kitne bytes ka hota hai?");
                quiz10_2.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "2 bytes", "4 bytes", "8 bytes", "1 byte"
                )));
                quiz10_2.setExplanationHinglish("int data type typically 4 bytes ka hota hai modern systems par.");
                quizzes.add(quiz10_2);
                
                // Quiz 3: if-else
                Quiz quiz10_3 = createQuiz("quiz_10_3", "chapter_10",
                    "What happens when the condition in an if statement is FALSE?",
                    "Program crashes", "else block executes", "Loop runs", "Error occurs",
                    1, "In if-else, when the condition is FALSE, the else block executes.");
                quiz10_3.setQuestionHinglish("if statement mein condition FALSE hone par kya hota hai?");
                quiz10_3.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Program crash hota hai", "else block execute hota hai", "Loop chalta hai", "Error aata hai"
                )));
                quiz10_3.setExplanationHinglish("if-else mein condition FALSE hone par else block execute hota hai.");
                quizzes.add(quiz10_3);
                
                // Quiz 4: for loop parts
                Quiz quiz10_4 = createQuiz("quiz_10_4", "chapter_10",
                    "What are the three parts of a for loop?",
                    "start, end, step", "initialization, condition, increment", "begin, check, update", "init, test, change",
                    1, "A for loop has initialization, condition, and increment/decrement.");
                quiz10_4.setQuestionHinglish("for loop ke teen parts kya hain?");
                quiz10_4.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "start, end, step", "initialization, condition, increment", "begin, check, update", "init, test, change"
                )));
                quiz10_4.setExplanationHinglish("for loop mein initialization, condition, aur increment/decrement hote hain.");
                quizzes.add(quiz10_4);
                
                // Quiz 5: Array index
                Quiz quiz10_5 = createQuiz("quiz_10_5", "chapter_10",
                    "Where does array index start from?",
                    "1", "0", "-1", "Depends on size",
                    1, "In C, array index always starts from 0.");
                quiz10_5.setQuestionHinglish("Array index kahan se start hota hai?");
                quiz10_5.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "1", "0", "-1", "Depends on size"
                )));
                quiz10_5.setExplanationHinglish("C mein array index hamesha 0 se start hota hai.");
                quizzes.add(quiz10_5);
                
                // Quiz 6: Pointer
                Quiz quiz10_6 = createQuiz("quiz_10_6", "chapter_10",
                    "What does a pointer store?",
                    "Value", "Memory address", "Data type", "Variable name",
                    1, "A pointer stores the memory address of a variable.");
                quiz10_6.setQuestionHinglish("Pointer kya store karta hai?");
                quiz10_6.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Value", "Memory address", "Data type", "Variable name"
                )));
                quiz10_6.setExplanationHinglish("Pointer variable ka memory address store karta hai.");
                quizzes.add(quiz10_6);
                
                // Quiz 7: Structure
                Quiz quiz10_7 = createQuiz("quiz_10_7", "chapter_10",
                    "Can we group different data types in a structure?",
                    "Yes", "No", "Only int and float", "Only same type",
                    0, "Yes, we can group different data types together in a structure.");
                quiz10_7.setQuestionHinglish("Structure mein different data types ko group kar sakte hain?");
                quiz10_7.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Haan", "Nahi", "Sirf int aur float", "Sirf same type"
                )));
                quiz10_7.setExplanationHinglish("Structure mein different data types ko ek saath group kar sakte hain.");
                quizzes.add(quiz10_7);
                
                // Quiz 8: File open
                Quiz quiz10_8 = createQuiz("quiz_10_8", "chapter_10",
                    "Which function is used to open a file?",
                    "open()", "fopen()", "file_open()", "readfile()",
                    1, "The fopen() function is used to open a file.");
                quiz10_8.setQuestionHinglish("File open karne ke liye kaun sa function use hota hai?");
                quiz10_8.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "open()", "fopen()", "file_open()", "readfile()"
                )));
                quiz10_8.setExplanationHinglish("fopen() function file open karne ke liye use hota hai.");
                quizzes.add(quiz10_8);
                
                // Quiz 9: while loop
                Quiz quiz10_9 = createQuiz("quiz_10_9", "chapter_10",
                    "When is the condition checked in a while loop?",
                    "After the loop", "Before the loop", "In the middle of loop", "Never",
                    1, "In a while loop, the condition is checked BEFORE the loop executes.");
                quiz10_9.setQuestionHinglish("while loop mein condition kab check hoti hai?");
                quiz10_9.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Loop ke baad", "Loop se pehle", "Loop ke beech mein", "Kabhi nahi"
                )));
                quiz10_9.setExplanationHinglish("while loop mein condition loop execute hone se PEHLE check hoti hai.");
                quizzes.add(quiz10_9);
                
                // Quiz 10: Recursion
                Quiz quiz10_10 = createQuiz("quiz_10_10", "chapter_10",
                    "What is essential in recursion?",
                    "Loop", "Base condition", "Array", "File",
                    1, "A base condition is essential in recursion to avoid infinite recursion.");
                quiz10_10.setQuestionHinglish("Recursion mein kya zaroori hai?");
                quiz10_10.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Loop", "Base condition", "Array", "File"
                )));
                quiz10_10.setExplanationHinglish("Recursion mein base condition zaroori hai infinite recursion se bachne ke liye.");
                quizzes.add(quiz10_10);
                
                // Quiz 11: strcmp
                Quiz quiz10_11 = createQuiz("quiz_10_11", "chapter_10",
                    "What does the strcmp() function do?",
                    "String copy", "String compare", "String length", "String reverse",
                    1, "The strcmp() function compares two strings.");
                quiz10_11.setQuestionHinglish("strcmp() function kya karta hai?");
                quiz10_11.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "String copy", "String compare", "String length", "String reverse"
                )));
                quiz10_11.setExplanationHinglish("strcmp() function do strings ko compare karta hai.");
                quizzes.add(quiz10_11);
                
                // Quiz 12: break in switch
                Quiz quiz10_12 = createQuiz("quiz_10_12", "chapter_10",
                    "Why is the break statement necessary in switch case?",
                    "For speed", "To prevent fall-through", "To save memory", "For syntax",
                    1, "The break statement prevents fall-through so that next cases don't execute.");
                quiz10_12.setQuestionHinglish("switch case mein break statement kyu zaroori hai?");
                quiz10_12.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Speed ke liye", "Fall-through rokne ke liye", "Memory save karne ke liye", "Syntax ke liye"
                )));
                quiz10_12.setExplanationHinglish("break statement fall-through rokta hai taaki next cases execute na ho.");
                quizzes.add(quiz10_12);
                
                // Quiz 13: 2D array indices
                Quiz quiz10_13 = createQuiz("quiz_10_13", "chapter_10",
                    "How many indices does a 2D array have?",
                    "1", "2", "3", "4",
                    1, "A 2D array has 2 indices - row and column.");
                quiz10_13.setQuestionHinglish("2D array mein kitne indices hote hain?");
                quiz10_13.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "1", "2", "3", "4"
                )));
                quiz10_13.setExplanationHinglish("2D array mein 2 indices hote hain - row aur column.");
                quizzes.add(quiz10_13);
                
                // Quiz 14: void function
                Quiz quiz10_14 = createQuiz("quiz_10_14", "chapter_10",
                    "What does a void function return?",
                    "0", "NULL", "Nothing", "-1",
                    2, "A void function does not return any value.");
                quiz10_14.setQuestionHinglish("void function kya return karta hai?");
                quiz10_14.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "0", "NULL", "Kuch nahi", "-1"
                )));
                quiz10_14.setExplanationHinglish("void function koi value return nahi karta.");
                quizzes.add(quiz10_14);
                
                // Quiz 15: Comments
                Quiz quiz10_15 = createQuiz("quiz_10_15", "chapter_10",
                    "How do we write comments in C?",
                    "# comment", "// comment", "<!-- comment -->", "' comment",
                    1, "In C, we write single line comments with // and multi-line comments with /* */.");
                quiz10_15.setQuestionHinglish("C mein comments kaise likhte hain?");
                quiz10_15.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "# comment", "// comment", "<!-- comment -->", "' comment"
                )));
                quiz10_15.setExplanationHinglish("C mein // se single line comment aur /* */ se multi-line comment likhte hain.");
                quizzes.add(quiz10_15);
                
                // MEDIUM LEVEL (15 MCQs) - Application, code analysis, moderate complexity
                
                // Quiz 16: File Extension
                Quiz quiz10_16 = createQuiz("quiz_10_16", "chapter_10",
                    "What is the file extension for C programs?",
                    ".c", ".cpp", ".java", ".py",
                    0, "C programs use the .c file extension.");
                quiz10_16.setQuestionHinglish("C program ka file extension kya hai?");
                quiz10_16.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    ".c", ".cpp", ".java", ".py"
                )));
                quiz10_16.setExplanationHinglish("C programs .c file extension use karte hain.");
                quizzes.add(quiz10_16);
                
                // Quiz 17: Semicolon
                Quiz quiz10_17 = createQuiz("quiz_10_17", "chapter_10",
                    "What is used to terminate a statement in C?",
                    "Period (.)", "Comma (,)", "Semicolon (;)", "Colon (:)",
                    2, "Every statement in C must end with a semicolon (;).");
                quiz10_17.setQuestionHinglish("C mein statement kaise end hota hai?");
                quiz10_17.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Period (.)", "Comma (,)", "Semicolon (;)", "Colon (:)"
                )));
                quiz10_17.setExplanationHinglish("C mein har statement semicolon (;) se end hota hai.");
                quizzes.add(quiz10_17);
                
                // Quiz 18: Pre vs Post Increment
                Quiz quiz10_18 = createQuiz("quiz_10_18", "chapter_10",
                    "What is the difference between ++a and a++?",
                    "No difference", "Pre-increment vs Post-increment", "Syntax error", "Both invalid",
                    1, "++a is pre-increment (increment first, then use), a++ is post-increment (use first, then increment).");
                quiz10_18.setQuestionHinglish("++a aur a++ mein kya difference hai?");
                quiz10_18.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Koi difference nahi", "Pre-increment vs Post-increment", "Syntax error", "Dono invalid hain"
                )));
                quiz10_18.setExplanationHinglish("++a pre-increment hai (pehle increment, phir use), a++ post-increment hai (pehle use, phir increment).");
                quizzes.add(quiz10_18);
                
                // Quiz 19: Modulus Operator
                Quiz quiz10_19 = createQuiz("quiz_10_19", "chapter_10",
                    "What will be the result of 10 % 3?",
                    "3", "1", "0", "10",
                    1, "The % (modulus) operator returns the remainder. 10 ÷ 3 = 3 remainder 1.");
                quiz10_19.setQuestionHinglish("10 % 3 ka result kya hoga?");
                quiz10_19.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "3", "1", "0", "10"
                )));
                quiz10_19.setExplanationHinglish("% (modulus) operator remainder deta hai. 10 ÷ 3 = 3 remainder 1, isliye answer 1 hai.");
                quizzes.add(quiz10_19);
                
                // Quiz 20: do-while Execution
                Quiz quiz10_20 = createQuiz("quiz_10_20", "chapter_10",
                    "How many times will code execute at minimum in a do-while loop?",
                    "0 times", "1 time", "2 times", "Depends on condition",
                    1, "In a do-while loop, code executes at least 1 time because the condition is checked after execution.");
                quiz10_20.setQuestionHinglish("do-while loop mein code kam se kam kitni baar execute hoga?");
                quiz10_20.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "0 baar", "1 baar", "2 baar", "Condition par depend karta hai"
                )));
                quiz10_20.setExplanationHinglish("do-while loop mein code kam se kam 1 baar zaroor execute hota hai kyunki condition baad mein check hoti hai.");
                quizzes.add(quiz10_20);
                
                // Quiz 21: Address Operator
                Quiz quiz10_21 = createQuiz("quiz_10_21", "chapter_10",
                    "Which is the address operator?",
                    "*", "&", "#", "@",
                    1, "& (ampersand) is the address operator that gives the address of a variable.");
                quiz10_21.setQuestionHinglish("Address operator kaun sa hai?");
                quiz10_21.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "*", "&", "#", "@"
                )));
                quiz10_21.setExplanationHinglish("& (ampersand) address operator hai jo variable ka address deta hai.");
                quizzes.add(quiz10_21);
                
                // Quiz 22: Call by Value
                Quiz quiz10_22 = createQuiz("quiz_10_22", "chapter_10",
                    "What is passed in call by value?",
                    "Variable's address", "Copy of variable", "Variable's reference", "Pointer",
                    1, "In call by value, a copy of the variable is passed, the original value doesn't change.");
                quiz10_22.setQuestionHinglish("Call by value mein kya pass hota hai?");
                quiz10_22.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Variable ka address", "Variable ki copy", "Variable ka reference", "Pointer"
                )));
                quiz10_22.setExplanationHinglish("Call by value mein variable ki copy pass hoti hai, original value change nahi hoti.");
                quizzes.add(quiz10_22);
                
                // Quiz 23: strlen Function
                Quiz quiz10_23 = createQuiz("quiz_10_23", "chapter_10",
                    "What does strlen() function return?",
                    "String size in bytes", "String length (excluding null)", "String address", "String type",
                    1, "strlen() function returns the length of the string, not counting the null character.");
                quiz10_23.setQuestionHinglish("strlen() function kya return karta hai?");
                quiz10_23.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "String ka size bytes mein", "String ki length (null character ko chhod kar)", "String ka address", "String ka type"
                )));
                quiz10_23.setExplanationHinglish("strlen() function string ki length return karta hai, null character ko count nahi karta.");
                quizzes.add(quiz10_23);
                
                // Quiz 24: Union vs Structure
                Quiz quiz10_24 = createQuiz("quiz_10_24", "chapter_10",
                    "What is the difference between Union and Structure?",
                    "No difference", "Union members share same memory", "Union has one type only", "Structure is faster",
                    1, "In Union, all members share the same memory location, whereas in Structure, each has separate memory.");
                quiz10_24.setQuestionHinglish("Union aur Structure mein kya difference hai?");
                quiz10_24.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Koi difference nahi", "Union mein sabhi members same memory share karte hain", "Union mein sirf ek data type ho sakta hai", "Structure faster hai"
                )));
                quiz10_24.setExplanationHinglish("Union mein sabhi members same memory location share karte hain, jabki structure mein alag-alag memory hoti hai.");
                quizzes.add(quiz10_24);
                
                // Quiz 25: File Mode 'r'
                Quiz quiz10_25 = createQuiz("quiz_10_25", "chapter_10",
                    "What does file mode 'r' mean?",
                    "Write mode", "Read mode", "Append mode", "Read and write",
                    1, "'r' mode opens the file for reading.");
                quiz10_25.setQuestionHinglish("File mode 'r' ka kya matlab hai?");
                quiz10_25.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Write mode", "Read mode", "Append mode", "Read and write"
                )));
                quiz10_25.setExplanationHinglish("'r' mode file ko read karne ke liye open karta hai.");
                quizzes.add(quiz10_25);
                
                // Quiz 26: Ternary Operator
                Quiz quiz10_26 = createQuiz("quiz_10_26", "chapter_10",
                    "What is the syntax of the ternary operator?",
                    "condition ? true : false", "condition : true ? false", "true ? condition : false", "? condition : true false",
                    0, "Ternary operator: condition ? value_if_true : value_if_false");
                quiz10_26.setQuestionHinglish("Ternary operator ka syntax kya hai?");
                quiz10_26.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "condition ? true : false", "condition : true ? false", "true ? condition : false", "? condition : true false"
                )));
                quiz10_26.setExplanationHinglish("Ternary operator: condition ? value_if_true : value_if_false");
                quizzes.add(quiz10_26);
                
                // Quiz 27: String Terminator
                Quiz quiz10_27 = createQuiz("quiz_10_27", "chapter_10",
                    "Which character terminates a string in C?",
                    "\\0", "\\n", "\\t", "EOF",
                    0, "Strings in C are terminated by the null character (\\0).");
                quiz10_27.setQuestionHinglish("C mein string kaise end hoti hai?");
                quiz10_27.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "\\0", "\\n", "\\t", "EOF"
                )));
                quiz10_27.setExplanationHinglish("C mein strings null character (\\0) se end hoti hain.");
                quizzes.add(quiz10_27);
                
                // Quiz 28: Ampersand in scanf
                Quiz quiz10_28 = createQuiz("quiz_10_28", "chapter_10",
                    "Why is & (ampersand) used in scanf()?",
                    "For value", "For address", "For pointer", "For reference",
                    1, "In scanf(), & is used to provide the address of the variable so the value can be stored.");
                quiz10_28.setQuestionHinglish("scanf() mein & (ampersand) kyu use hota hai?");
                quiz10_28.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Value ke liye", "Address ke liye", "Pointer ke liye", "Reference ke liye"
                )));
                quiz10_28.setExplanationHinglish("scanf() mein & variable ka address dene ke liye use hota hai taaki value us location par store ho sake.");
                quizzes.add(quiz10_28);
                
                // Quiz 29: default Case
                Quiz quiz10_29 = createQuiz("quiz_10_29", "chapter_10",
                    "When does the default case execute in switch?",
                    "Always", "Never", "When no case matches", "After first case",
                    2, "The default case executes when no other case matches.");
                quiz10_29.setQuestionHinglish("switch case mein default case kab execute hota hai?");
                quiz10_29.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Hamesha", "Kabhi nahi", "Jab koi case match na ho", "Pehle case ke baad"
                )));
                quiz10_29.setExplanationHinglish("default case tab execute hota hai jab koi bhi case match nahi hota.");
                quizzes.add(quiz10_29);
                
                // Quiz 30: Structure Member Access
                Quiz quiz10_30 = createQuiz("quiz_10_30", "chapter_10",
                    "Which operator is used to access structure members?",
                    "->", ".", ":", "*",
                    1, "The dot operator (.) is used to access structure members.");
                quiz10_30.setQuestionHinglish("Structure member access karne ke liye kaun sa operator use hota hai?");
                quiz10_30.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "->", ".", ":", "*"
                )));
                quiz10_30.setExplanationHinglish("Dot operator (.) structure member access karne ke liye use hota hai.");
                quizzes.add(quiz10_30);
                
                // DIFFICULT LEVEL (20 MCQs) - Advanced scenarios, debugging, problem-solving
                
                // Quiz 31: Pointer Arithmetic
                Quiz quiz10_31 = createQuiz("quiz_10_31", "chapter_10",
                    "What does ptr++ do in pointer arithmetic?",
                    "Moves 1 byte forward", "Moves by data type size", "Increases value by 1", "Nothing",
                    1, "ptr++ moves the pointer forward according to its data type size (e.g., 4 bytes for int*).");
                quiz10_31.setQuestionHinglish("Pointer arithmetic mein ptr++ kya karta hai?");
                quiz10_31.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Pointer ko 1 byte aage badhata hai", "Pointer ko data type ke size ke hisab se aage badhata hai", "Pointer ki value 1 badhata hai", "Kuch nahi"
                )));
                quiz10_31.setExplanationHinglish("ptr++ pointer ko uske data type ke size ke hisab se aage badhata hai (e.g., int* ke liye 4 bytes).");
                quizzes.add(quiz10_31);
                
                // Quiz 32: Output Prediction
                Quiz quiz10_32 = createQuiz("quiz_10_32", "chapter_10",
                    "What will be the output of: int a=5; printf(\"%d\", ++a);",
                    "5", "6", "Error", "Undefined",
                    1, "++a is pre-increment, so a becomes 6 before printing.");
                quiz10_32.setQuestionHinglish("Output kya hoga: int a=5; printf(\"%d\", ++a);");
                quiz10_32.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "5", "6", "Error", "Undefined"
                )));
                quiz10_32.setExplanationHinglish("++a pre-increment hai, isliye a pehle 6 ban jata hai phir print hota hai.");
                quizzes.add(quiz10_32);
                
                // Quiz 33: Dereference Operator
                Quiz quiz10_33 = createQuiz("quiz_10_33", "chapter_10",
                    "Which is the dereference operator?",
                    "&", "*", "->", ".",
                    1, "* (asterisk) is the dereference operator that gives the value stored at the pointer's address.");
                quiz10_33.setQuestionHinglish("Dereference operator kaun sa hai?");
                quiz10_33.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "&", "*", "->", "."
                )));
                quiz10_33.setExplanationHinglish("* (asterisk) dereference operator hai jo pointer ke address par stored value deta hai.");
                quizzes.add(quiz10_33);
                
                // Quiz 34: NULL Pointer
                Quiz quiz10_34 = createQuiz("quiz_10_34", "chapter_10",
                    "What does NULL pointer mean?",
                    "Pointer that doesn't point to anything", "Pointer that points to 0", "Invalid pointer", "All are correct",
                    0, "NULL pointer is a pointer that doesn't point to any valid memory location.");
                quiz10_34.setQuestionHinglish("NULL pointer ka kya matlab hai?");
                quiz10_34.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Pointer jo kisi ko point nahi karta", "Pointer jo 0 ko point karta hai", "Invalid pointer", "Sabhi sahi hain"
                )));
                quiz10_34.setExplanationHinglish("NULL pointer wo pointer hai jo kisi bhi valid memory location ko point nahi karta.");
                quizzes.add(quiz10_34);
                
                // Quiz 35: Array Decay
                Quiz quiz10_35 = createQuiz("quiz_10_35", "chapter_10",
                    "What happens when an array name is used in an expression?",
                    "Error occurs", "Decays to pointer", "Creates copy", "Nothing",
                    1, "When an array name is used in an expression, it decays to a pointer to its first element.");
                quiz10_35.setQuestionHinglish("Array name ko expression mein use karne par kya hota hai?");
                quiz10_35.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Error aata hai", "Pointer ban jata hai", "Copy ban jati hai", "Kuch nahi"
                )));
                quiz10_35.setExplanationHinglish("Array name expression mein use karne par wo pointer ban jata hai jo pehle element ko point karta hai.");
                quizzes.add(quiz10_35);
                
                // Quiz 36: Function Prototype
                Quiz quiz10_36 = createQuiz("quiz_10_36", "chapter_10",
                    "Why do we declare a function prototype?",
                    "To save memory", "To inform compiler about function", "To increase speed", "To handle errors",
                    1, "Function prototype informs the compiler about the function's return type and parameters in advance.");
                quiz10_36.setQuestionHinglish("Function prototype kyu declare karte hain?");
                quiz10_36.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Memory save karne ke liye", "Compiler ko function ke baare mein batane ke liye", "Speed badhane ke liye", "Error handle karne ke liye"
                )));
                quiz10_36.setExplanationHinglish("Function prototype compiler ko function ke return type aur parameters ke baare mein pehle se bata deta hai.");
                quizzes.add(quiz10_36);
                
                // Quiz 37: Infinite Loop
                Quiz quiz10_37 = createQuiz("quiz_10_37", "chapter_10",
                    "Which creates an infinite loop?",
                    "for(;;)", "while(1)", "do{}while(1)", "All are correct",
                    3, "All three methods create an infinite loop: for(;;), while(1), do{}while(1)");
                quiz10_37.setQuestionHinglish("Infinite loop kaise banate hain?");
                quiz10_37.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "for(;;)", "while(1)", "do{}while(1)", "Sabhi sahi hain"
                )));
                quiz10_37.setExplanationHinglish("Teeno tarike infinite loop banane ke liye sahi hain: for(;;), while(1), do{}while(1)");
                quizzes.add(quiz10_37);
                
                // Quiz 38: Arrow Operator
                Quiz quiz10_38 = createQuiz("quiz_10_38", "chapter_10",
                    "When is the arrow operator (->) used?",
                    "For array access", "For pointer to structure member", "For function call", "For loop",
                    1, "The arrow operator (->) is used to access members of a structure through a pointer.");
                quiz10_38.setQuestionHinglish("Arrow operator (->) kab use hota hai?");
                quiz10_38.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Array access ke liye", "Pointer to structure member access ke liye", "Function call ke liye", "Loop ke liye"
                )));
                quiz10_38.setExplanationHinglish("Arrow operator (->) pointer to structure ke members ko access karne ke liye use hota hai.");
                quizzes.add(quiz10_38);
                
                // Quiz 39: typedef Usage
                Quiz quiz10_39 = createQuiz("quiz_10_39", "chapter_10",
                    "What is the use of typedef?",
                    "To create new type", "To give new name to existing type", "To convert types", "To check types",
                    1, "typedef is used to give a new name to an existing data type.");
                quiz10_39.setQuestionHinglish("typedef ka kya use hai?");
                quiz10_39.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "New data type banane ke liye", "Existing type ko naya naam dene ke liye", "Type convert karne ke liye", "Type check karne ke liye"
                )));
                quiz10_39.setExplanationHinglish("typedef existing data type ko naya naam dene ke liye use hota hai.");
                quizzes.add(quiz10_39);
                
                // Quiz 40: File Mode 'w'
                Quiz quiz10_40 = createQuiz("quiz_10_40", "chapter_10",
                    "What does file mode 'w' do?",
                    "Reads file", "Writes to file (deletes existing)", "Appends to file", "Copies file",
                    1, "'w' mode opens file for writing, if file exists its content is deleted.");
                quiz10_40.setQuestionHinglish("File mode 'w' kya karta hai?");
                quiz10_40.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "File ko read karta hai", "File ko write karta hai (existing content delete ho jata hai)", "File mein append karta hai", "File ko copy karta hai"
                )));
                quiz10_40.setExplanationHinglish("'w' mode file ko write karne ke liye open karta hai, agar file exist karti hai to uska content delete ho jata hai.");
                quizzes.add(quiz10_40);
                
                // Quiz 41: fprintf vs printf
                Quiz quiz10_41 = createQuiz("quiz_10_41", "chapter_10",
                    "What is the difference between fprintf() and printf()?",
                    "No difference", "fprintf() writes to file", "fprintf() is faster", "printf() writes to file",
                    1, "fprintf() writes formatted output to a file, while printf() prints to the screen.");
                quiz10_41.setQuestionHinglish("fprintf() aur printf() mein kya difference hai?");
                quiz10_41.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Koi difference nahi", "fprintf() file mein write karta hai", "fprintf() faster hai", "printf() file mein write karta hai"
                )));
                quiz10_41.setExplanationHinglish("fprintf() file mein formatted output write karta hai, jabki printf() screen par print karta hai.");
                quizzes.add(quiz10_41);
                
                // Quiz 42: Nested if-else
                Quiz quiz10_42 = createQuiz("quiz_10_42", "chapter_10",
                    "How many levels can nested if-else have?",
                    "Only 2", "Only 3", "No limit", "Only 5",
                    2, "There is no limit to nested if-else, but for readability, keep it to 3-4 levels.");
                quiz10_42.setQuestionHinglish("Nested if-else mein kitne levels ho sakte hain?");
                quiz10_42.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Sirf 2", "Sirf 3", "Koi limit nahi", "Sirf 5"
                )));
                quiz10_42.setExplanationHinglish("Nested if-else mein koi limit nahi hai, lekin readability ke liye 3-4 levels tak hi rakhna chahiye.");
                quizzes.add(quiz10_42);
                
                // Quiz 43: break vs continue
                Quiz quiz10_43 = createQuiz("quiz_10_43", "chapter_10",
                    "What does the continue statement do?",
                    "Ends loop", "Skips current iteration", "Pauses loop", "Reverses loop",
                    1, "The continue statement skips the current iteration and directly goes to the next iteration.");
                quiz10_43.setQuestionHinglish("continue statement kya karta hai?");
                quiz10_43.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Loop khatam kar deta hai", "Current iteration skip karke next iteration par jata hai", "Loop ko pause karta hai", "Loop ko reverse karta hai"
                )));
                quiz10_43.setExplanationHinglish("continue statement current iteration ko skip karke directly next iteration par chala jata hai.");
                quizzes.add(quiz10_43);
                
                // Quiz 44: main() Return Type
                Quiz quiz10_44 = createQuiz("quiz_10_44", "chapter_10",
                    "What is the return type of main() function?",
                    "void", "int", "float", "char",
                    1, "The main() function has a return type of int, which tells the OS the program's status.");
                quiz10_44.setQuestionHinglish("main() function ka return type kya hota hai?");
                quiz10_44.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "void", "int", "float", "char"
                )));
                quiz10_44.setExplanationHinglish("main() function ka return type int hota hai, jo operating system ko program ki status batata hai.");
                quizzes.add(quiz10_44);
                
                // Quiz 45: 2D Array Declaration
                Quiz quiz10_45 = createQuiz("quiz_10_45", "chapter_10",
                    "What is the correct syntax to declare a 2D array?",
                    "int arr[3,4]", "int arr[3][4]", "int arr(3)(4)", "int arr{3}{4}",
                    1, "2D array is declared in the format: int arr[rows][columns]");
                quiz10_45.setQuestionHinglish("2D array declare karne ka sahi syntax kya hai?");
                quiz10_45.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "int arr[3,4]", "int arr[3][4]", "int arr(3)(4)", "int arr{3}{4}"
                )));
                quiz10_45.setExplanationHinglish("2D array: int arr[rows][columns] format mein declare hota hai.");
                quizzes.add(quiz10_45);
                
                // Quiz 46: Starting Function
                Quiz quiz10_46 = createQuiz("quiz_10_46", "chapter_10",
                    "Which function is the starting point of a C program?",
                    "start()", "begin()", "main()", "run()",
                    2, "Every C program starts execution from the main() function.");
                quiz10_46.setQuestionHinglish("C program kahan se start hota hai?");
                quiz10_46.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "start()", "begin()", "main()", "run()"
                )));
                quiz10_46.setExplanationHinglish("Har C program main() function se start hota hai.");
                quizzes.add(quiz10_46);
                
                // Quiz 47: fclose Function
                Quiz quiz10_47 = createQuiz("quiz_10_47", "chapter_10",
                    "Which function is used to close a file?",
                    "close()", "fclose()", "file_close()", "end()",
                    1, "The fclose() function is used to close a file.");
                quiz10_47.setQuestionHinglish("File close karne ke liye kaun sa function use hota hai?");
                quiz10_47.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "close()", "fclose()", "file_close()", "end()"
                )));
                quiz10_47.setExplanationHinglish("fclose() function file close karne ke liye use hota hai.");
                quizzes.add(quiz10_47);
                
                // Quiz 48: printf Header
                Quiz quiz10_48 = createQuiz("quiz_10_48", "chapter_10",
                    "Which header file is needed for printf()?",
                    "stdio.h", "stdlib.h", "string.h", "math.h",
                    0, "printf() function is defined in stdio.h (Standard Input/Output) header file.");
                quiz10_48.setQuestionHinglish("printf() ke liye kaun sa header file chahiye?");
                quiz10_48.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "stdio.h", "stdlib.h", "string.h", "math.h"
                )));
                quiz10_48.setExplanationHinglish("printf() function stdio.h (Standard Input/Output) header file mein define hai.");
                quizzes.add(quiz10_48);
                
                // Quiz 49: break Statement
                Quiz quiz10_49 = createQuiz("quiz_10_49", "chapter_10",
                    "What does the break statement do in a loop?",
                    "Skips the loop", "Exits the loop", "Restarts the loop", "Slows the loop",
                    1, "The break statement immediately terminates the loop and exits from it.");
                quiz10_49.setQuestionHinglish("Loop mein break statement kya karta hai?");
                quiz10_49.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "Loop ko skip karta hai", "Loop se bahar nikalta hai", "Loop ko restart karta hai", "Loop ko slow karta hai"
                )));
                quiz10_49.setExplanationHinglish("break statement loop ko turant terminate kar deta hai aur loop se bahar aa jate hain.");
                quizzes.add(quiz10_49);
                
                // Quiz 50: strcmp Function
                Quiz quiz10_50 = createQuiz("quiz_10_50", "chapter_10",
                    "What does the strcmp() function do?",
                    "String copy", "String compare", "String length", "String reverse",
                    1, "The strcmp() function compares two strings.");
                quiz10_50.setQuestionHinglish("strcmp() function kya karta hai?");
                quiz10_50.setOptionsHinglish(new ArrayList<>(Arrays.asList(
                    "String copy", "String compare", "String length", "String reverse"
                )));
                quiz10_50.setExplanationHinglish("strcmp() function do strings ko compare karta hai.");
                quizzes.add(quiz10_50);
                break;
            
            // C Programming Final Quiz - 100 Questions
            case "chapter_final":
                // EASY (1-25)
                quizzes.add(createQuiz("cfq1", "chapter_final", "Who developed C?", "James Gosling", "Dennis Ritchie", "Bjarne Stroustrup", "Guido", 1, "Dennis Ritchie developed C at Bell Labs in 1972."));
                quizzes.add(createQuiz("cfq2", "chapter_final", "C file extension?", ".c", ".cpp", ".java", ".py", 0, "C files have .c extension."));
                quizzes.add(createQuiz("cfq3", "chapter_final", "Size of int?", "2 bytes", "4 bytes", "8 bytes", "Varies", 1, "int is typically 4 bytes."));
                quizzes.add(createQuiz("cfq4", "chapter_final", "Which stores decimal?", "int", "char", "float", "void", 2, "float stores decimals."));
                quizzes.add(createQuiz("cfq5", "chapter_final", "printf does?", "Input", "Output", "Calculate", "Store", 1, "printf outputs data."));
                quizzes.add(createQuiz("cfq6", "chapter_final", "scanf needs?", "Value", "Address (&)", "Pointer", "Reference", 1, "scanf needs address."));
                quizzes.add(createQuiz("cfq7", "chapter_final", "Array index starts?", "0", "1", "-1", "Varies", 0, "Index starts from 0."));
                quizzes.add(createQuiz("cfq8", "chapter_final", "Loop repeats?", "Once", "Multiple times", "Never", "Random", 1, "Loop repeats code."));
                quizzes.add(createQuiz("cfq9", "chapter_final", "while checks?", "After", "Before", "Never", "Random", 1, "while checks first."));
                quizzes.add(createQuiz("cfq10", "chapter_final", "% operator?", "Division", "Modulus", "Multiply", "Add", 1, "% is modulus."));
                quizzes.add(createQuiz("cfq11", "chapter_final", "\\n is?", "Tab", "Newline", "Space", "Null", 1, "\\n is newline."));
                quizzes.add(createQuiz("cfq12", "chapter_final", "String ends?", "\\0", "\\n", "EOF", "NULL", 0, "String ends with \\0."));
                quizzes.add(createQuiz("cfq13", "chapter_final", "Function is?", "Variable", "Reusable block", "Loop", "Operator", 1, "Function is reusable."));
                quizzes.add(createQuiz("cfq14", "chapter_final", "void means?", "Empty", "No return", "Error", "Int", 1, "void = no return."));
                quizzes.add(createQuiz("cfq15", "chapter_final", "Header extension?", ".h", ".c", ".cpp", ".txt", 0, "Headers use .h."));
                quizzes.add(createQuiz("cfq16", "chapter_final", "#include is?", "Comment", "Preprocessor", "Function", "Variable", 1, "#include preprocesses."));
                quizzes.add(createQuiz("cfq17", "chapter_final", "break exits?", "Function", "Loop", "Program", "Nothing", 1, "break exits loop."));
                quizzes.add(createQuiz("cfq18", "chapter_final", "continue skips?", "Loop", "Iteration", "Function", "Nothing", 1, "continue skips iteration."));
                quizzes.add(createQuiz("cfq19", "chapter_final", "main returns?", "void", "int", "float", "char", 1, "main returns int."));
                quizzes.add(createQuiz("cfq20", "chapter_final", "Comment in C?", "//", "/* */", "Both", "##", 2, "Both are comments."));
                quizzes.add(createQuiz("cfq21", "chapter_final", "Variable stores?", "Function", "Data", "Loop", "Nothing", 1, "Variable stores data."));
                quizzes.add(createQuiz("cfq22", "chapter_final", "const makes?", "Variable", "Constant", "Function", "Loop", 1, "const = constant."));
                quizzes.add(createQuiz("cfq23", "chapter_final", "++ does?", "Add 10", "Increment", "Multiply", "Divide", 1, "++ increments."));
                quizzes.add(createQuiz("cfq24", "chapter_final", "&& is?", "AND", "OR", "NOT", "XOR", 0, "&& is logical AND."));
                quizzes.add(createQuiz("cfq25", "chapter_final", "|| is?", "AND", "OR", "NOT", "XOR", 1, "|| is logical OR."));
                
                // MEDIUM (26-50)
                quizzes.add(createQuiz("cfq26", "chapter_final", "Pointer stores?", "Value", "Address", "Type", "Name", 1, "Pointer = address."));
                quizzes.add(createQuiz("cfq27", "chapter_final", "& operator?", "Value", "Address", "Pointer", "Deref", 1, "& gives address."));
                quizzes.add(createQuiz("cfq28", "chapter_final", "* on pointer?", "Address", "Value", "Type", "Nothing", 1, "* dereferences."));
                quizzes.add(createQuiz("cfq29", "chapter_final", "2D array?", "arr[3,4]", "arr[3][4]", "arr(3)(4)", "arr{3}{4}", 1, "2D: arr[row][col]."));
                quizzes.add(createQuiz("cfq30", "chapter_final", "strlen counts?", "With \\0", "Without \\0", "Bytes", "All", 1, "strlen excludes \\0."));
                quizzes.add(createQuiz("cfq31", "chapter_final", "strcmp 0 means?", "Different", "Equal", "Greater", "Less", 1, "0 = equal strings."));
                quizzes.add(createQuiz("cfq32", "chapter_final", "struct groups?", "Functions", "Variables", "Loops", "Arrays", 1, "struct groups vars."));
                quizzes.add(createQuiz("cfq33", "chapter_final", "Union shares?", "Nothing", "Memory", "Functions", "Type", 1, "Union shares memory."));
                quizzes.add(createQuiz("cfq34", "chapter_final", "typedef creates?", "Variable", "Alias", "Function", "Loop", 1, "typedef = alias."));
                quizzes.add(createQuiz("cfq35", "chapter_final", "FILE is?", "Keyword", "Type", "Function", "Variable", 1, "FILE is type."));
                quizzes.add(createQuiz("cfq36", "chapter_final", "'r' mode?", "Read", "Write", "Append", "Binary", 0, "'r' = read."));
                quizzes.add(createQuiz("cfq37", "chapter_final", "'w' mode?", "Read", "Write", "Append", "Binary", 1, "'w' = write."));
                quizzes.add(createQuiz("cfq38", "chapter_final", "fclose does?", "Open", "Close", "Read", "Write", 1, "fclose closes file."));
                quizzes.add(createQuiz("cfq39", "chapter_final", "Recursion needs?", "Loop", "Base case", "Array", "Pointer", 1, "Needs base case."));
                quizzes.add(createQuiz("cfq40", "chapter_final", "Call by value?", "Copy", "Address", "Reference", "Pointer", 0, "Passes copy."));
                quizzes.add(createQuiz("cfq41", "chapter_final", "Call by reference?", "Copy", "Address", "Value", "Nothing", 1, "Passes address."));
                quizzes.add(createQuiz("cfq42", "chapter_final", "static retains?", "Nothing", "Value", "Type", "Scope", 1, "static retains value."));
                quizzes.add(createQuiz("cfq43", "chapter_final", "Global scope?", "Function", "Program", "File", "Block", 1, "Global = program."));
                quizzes.add(createQuiz("cfq44", "chapter_final", "Macro is?", "Function", "Substitution", "Variable", "Loop", 1, "Macro substitutes."));
                quizzes.add(createQuiz("cfq45", "chapter_final", "sizeof returns?", "Type", "Bytes", "Bits", "Name", 1, "sizeof = bytes."));
                quizzes.add(createQuiz("cfq46", "chapter_final", "NULL value?", "0", "1", "-1", "Garbage", 0, "NULL = 0."));
                quizzes.add(createQuiz("cfq47", "chapter_final", "malloc allocates?", "Stack", "Heap", "Static", "Register", 1, "malloc = heap."));
                quizzes.add(createQuiz("cfq48", "chapter_final", "free does?", "Allocate", "Deallocate", "Initialize", "Copy", 1, "free deallocates."));
                quizzes.add(createQuiz("cfq49", "chapter_final", "realloc?", "Allocate", "Resize", "Free", "Copy", 1, "realloc resizes."));
                quizzes.add(createQuiz("cfq50", "chapter_final", "Ternary?", "?:", "??", ":?", "?.", 0, "?: is ternary."));
                
                // HARD (51-75)  
                quizzes.add(createQuiz("cfq51", "chapter_final", "a++ vs ++a?", "Same", "Post vs Pre", "Error", "Nothing", 1, "Post vs pre increment."));
                quizzes.add(createQuiz("cfq52", "chapter_final", "Pointer arithmetic?", "Not allowed", "Type-based", "Only +", "Only -", 1, "Type-based arithmetic."));
                quizzes.add(createQuiz("cfq53", "chapter_final", "Array name?", "Variable", "Const pointer", "Normal", "Function", 1, "Array = const ptr."));
                quizzes.add(createQuiz("cfq54", "chapter_final", "Pointer to pointer?", "Not possible", "**ptr", "*ptr*", "ptr**", 1, "**ptr syntax."));
                quizzes.add(createQuiz("cfq55", "chapter_final", "Function pointer?", "Not possible", "Possible", "Only main", "Deprecated", 1, "Function ptrs possible."));
                quizzes.add(createQuiz("cfq56", "chapter_final", "Dangling pointer?", "NULL", "Freed memory", "Valid", "Array", 1, "Points to freed."));
                quizzes.add(createQuiz("cfq57", "chapter_final", "Memory leak?", "Overflow", "Not freeing", "NULL", "Stack overflow", 1, "Not freeing memory."));
                quizzes.add(createQuiz("cfq58", "chapter_final", "Segfault?", "Syntax", "Invalid access", "Compile error", "Warning", 1, "Invalid memory access."));
                quizzes.add(createQuiz("cfq59", "chapter_final", "Buffer overflow?", "Empty", "Write beyond", "Read", "NULL", 1, "Writes beyond buffer."));
                quizzes.add(createQuiz("cfq60", "chapter_final", "volatile prevents?", "Linking", "Optimization", "Compilation", "Execution", 1, "Prevents optimization."));
                quizzes.add(createQuiz("cfq61", "chapter_final", "register suggests?", "Memory", "Register storage", "Required", "Deprecated", 1, "Suggests register."));
                quizzes.add(createQuiz("cfq62", "chapter_final", "extern declares?", "Local", "External variable", "Function", "Static", 1, "External variable."));
                quizzes.add(createQuiz("cfq63", "chapter_final", "inline suggests?", "Not possible", "Expansion", "Required", "Deprecated", 1, "Suggests inline."));
                quizzes.add(createQuiz("cfq64", "chapter_final", "Bit fields?", "Not allowed", "Allowed", "Only int", "Deprecated", 1, "Allowed in struct."));
                quizzes.add(createQuiz("cfq65", "chapter_final", "Union size?", "Sum", "Largest member", "Product", "Fixed", 1, "Size of largest."));
                quizzes.add(createQuiz("cfq66", "chapter_final", "Enum starts?", "1", "0", "-1", "NULL", 1, "Starts from 0."));
                quizzes.add(createQuiz("cfq67", "chapter_final", "sizeof(char)?", "1", "2", "4", "Varies", 0, "Always 1 byte."));
                quizzes.add(createQuiz("cfq68", "chapter_final", "Complex numbers?", "Built-in", "complex.h", "Not possible", "Only float", 1, "Via complex.h."));
                quizzes.add(createQuiz("cfq69", "chapter_final", "VLA allowed?", "No", "C99", "C89", "Deprecated", 1, "VLA in C99."));
                quizzes.add(createQuiz("cfq70", "chapter_final", "Designated init?", "No", "C99", "C89", "Deprecated", 1, "C99 feature."));
                quizzes.add(createQuiz("cfq71", "chapter_final", "Compound literals?", "No", "C99", "C89", "Deprecated", 1, "C99 feature."));
                quizzes.add(createQuiz("cfq72", "chapter_final", "Flexible array?", "Not allowed", "Last member", "Any position", "Deprecated", 1, "Last struct member."));
                quizzes.add(createQuiz("cfq73", "chapter_final", "restrict hints?", "Nothing", "Non-aliasing", "Required", "Deprecated", 1, "Non-aliasing pointers."));
                quizzes.add(createQuiz("cfq74", "chapter_final", "_Bool type?", "No", "C99 boolean", "C89", "Typedef", 1, "Boolean in C99."));
                quizzes.add(createQuiz("cfq75", "chapter_final", "stdint.h has?", "Strings", "Fixed-width ints", "Math", "I/O", 1, "Fixed-width integers."));
                
                // VERY HARD (76-100)
                quizzes.add(createQuiz("cfq76", "chapter_final", "Sequence points?", "Semicolons", "Side effect completion", "Functions", "Loops", 1, "Side effects complete."));
                quizzes.add(createQuiz("cfq77", "chapter_final", "Undefined behavior?", "Defined", "Unpredictable", "Error", "Warning", 1, "Unpredictable behavior."));
                quizzes.add(createQuiz("cfq78", "chapter_final", "Implementation-defined?", "Not standard", "Varies by compiler", "Always same", "Error", 1, "Varies by compiler."));
                quizzes.add(createQuiz("cfq79", "chapter_final", "Unspecified?", "Always same", "Multiple outcomes", "Error", "Warning", 1, "Multiple valid results."));
                quizzes.add(createQuiz("cfq80", "chapter_final", "Strict aliasing?", "Naming", "Type-based", "Functions", "Alignment", 1, "Type-based aliasing."));
                quizzes.add(createQuiz("cfq81", "chapter_final", "Alignment?", "Optional", "Required for some", "Not in C", "Deprecated", 1, "Some need alignment."));
                quizzes.add(createQuiz("cfq82", "chapter_final", "Struct padding?", "Never", "For alignment", "Bug", "Optional", 1, "Ensures alignment."));
                quizzes.add(createQuiz("cfq83", "chapter_final", "packed removes?", "Nothing", "Padding", "Members", "Nothing", 1, "Removes padding."));
                quizzes.add(createQuiz("cfq84", "chapter_final", "setjmp/longjmp?", "Loops", "Non-local jumps", "Error", "Deprecated", 1, "Non-local jumps."));
                quizzes.add(createQuiz("cfq85", "chapter_final", "Signal handling?", "No", "signal.h", "Manual", "Deprecated", 1, "Via signal.h."));
                quizzes.add(createQuiz("cfq86", "chapter_final", "Atomic ops?", "No", "stdatomic.h C11", "Manual", "Not possible", 1, "C11 stdatomic.h."));
                quizzes.add(createQuiz("cfq87", "chapter_final", "Thread support?", "No", "threads.h C11", "Manual", "Deprecated", 1, "C11 threads.h."));
                quizzes.add(createQuiz("cfq88", "chapter_final", "Generic?", "No", "_Generic C11", "Templates", "Macros", 1, "_Generic in C11."));
                quizzes.add(createQuiz("cfq89", "chapter_final", "Static assert?", "No", "_Static_assert C11", "Runtime", "Deprecated", 1, "Compile-time checks."));
                quizzes.add(createQuiz("cfq90", "chapter_final", "Anonymous struct?", "No", "C11", "Only unions", "Deprecated", 1, "C11 allows."));
                quizzes.add(createQuiz("cfq91", "chapter_final", "Unicode?", "No", "char16_t C11", "UTF-8 only", "Not possible", 1, "C11 Unicode types."));
                quizzes.add(createQuiz("cfq92", "chapter_final", "gets() is?", "Safe", "Unsafe deprecated", "Preferred", "Fast", 1, "Unsafe deprecated."));
                quizzes.add(createQuiz("cfq93", "chapter_final", "fgets advantage?", "Faster", "Buffer protection", "Simpler", "Deprecated", 1, "Buffer overflow protection."));
                quizzes.add(createQuiz("cfq94", "chapter_final", "snprintf?", "Faster", "Bounds checking", "Simpler", "Deprecated", 1, "Has bounds checking."));
                quizzes.add(createQuiz("cfq95", "chapter_final", "strtok modifies?", "Nothing", "Original string", "Copy", "Returns", 1, "Modifies original."));
                quizzes.add(createQuiz("cfq96", "chapter_final", "const prevents?", "Nothing", "Modification", "Optional", "Deprecated", 1, "Prevents modification."));
                quizzes.add(createQuiz("cfq97", "chapter_final", "Prototypes?", "Optional", "Type safety", "Deprecated", "Required", 1, "Enable type checking."));
                quizzes.add(createQuiz("cfq98", "chapter_final", "Forward declaration?", "No", "Before definition", "Deprecated", "Error", 1, "Declares before define."));
                quizzes.add(createQuiz("cfq99", "chapter_final", "Header guards?", "Optional", "Prevent double inclusion", "Deprecated", "Required", 1, "Prevent double include."));
                quizzes.add(createQuiz("cfq100", "chapter_final", "MISRA C?", "Standard", "Safety guidelines", "Compiler", "IDE", 1, "Safety coding standard."));
                break;
            
            // Java Programming Quizzes
            case "java_chapter_1": // Java Basics
                quizzes.add(createQuiz("jq1_1", "java_chapter_1",
                    "Who developed Java?", "Dennis Ritchie", "James Gosling", "Bjarne Stroustrup", "Guido van Rossum",
                    1, "James Gosling developed Java at Sun Microsystems in 1995."));
                quizzes.add(createQuiz("jq1_2", "java_chapter_1",
                    "What is the extension of Java source files?", ".class", ".jar", ".java", ".jav",
                    2, "Java source files have .java extension, compiled files have .class extension."));
                quizzes.add(createQuiz("jq1_3", "java_chapter_1",
                    "What is JVM?", "Java Virtual Machine", "Java Variable Method", "Java Verified Module", "Java Visual Manager",
                    0, "JVM (Java Virtual Machine) executes Java bytecode and provides platform independence."));
                quizzes.add(createQuiz("jq1_4", "java_chapter_1",
                    "Which company currently owns Java?", "Google", "Microsoft", "Oracle", "IBM",
                    2, "Oracle Corporation owns Java after acquiring Sun Microsystems in 2010."));
                quizzes.add(createQuiz("jq1_5", "java_chapter_1",
                    "What is the main method signature in Java?", "void main()", "public static void main(String[] args)", "static void main()", "main(String args)",
                    1, "The correct main method signature is: public static void main(String[] args)"));
                break;
                
            case "java_chapter_2": // Data Types & Variables
                quizzes.add(createQuiz("jq2_1", "java_chapter_2",
                    "What is the size of int in Java?", "2 bytes", "4 bytes", "8 bytes", "Depends on platform",
                    1, "In Java, int is always 4 bytes (32 bits) regardless of platform."));
                quizzes.add(createQuiz("jq2_2", "java_chapter_2",
                    "Which is a primitive data type?", "String", "int", "Integer", "Array",
                    1, "int is a primitive data type. String and Integer are wrapper classes."));
                quizzes.add(createQuiz("jq2_3", "java_chapter_2",
                    "What is the default value of boolean?", "true", "false", "0", "null",
                    1, "The default value of boolean is false."));
                quizzes.add(createQuiz("jq2_4", "java_chapter_2",
                    "Which keyword is used for constants?", "const", "final", "static", "constant",
                    1, "The final keyword is used to declare constants in Java."));
                quizzes.add(createQuiz("jq2_5", "java_chapter_2",
                    "What stores decimal numbers?", "int", "char", "double", "boolean",
                    2, "double and float are used to store decimal numbers."));
                break;
                
            case "java_chapter_3": // Operators
                quizzes.add(createQuiz("jq3_1", "java_chapter_3",
                    "What is the result of 10 % 3?", "3", "1", "0", "3.33",
                    1, "The modulus operator % returns the remainder. 10 % 3 = 1"));
                quizzes.add(createQuiz("jq3_2", "java_chapter_3",
                    "What is ++ operator called?", "Addition", "Increment", "Plus", "Double",
                    1, "++ is the increment operator that increases value by 1."));
                quizzes.add(createQuiz("jq3_3", "java_chapter_3",
                    "What does && operator represent?", "Logical OR", "Logical AND", "Bitwise AND", "Conditional",
                    1, "&& is the logical AND operator."));
                quizzes.add(createQuiz("jq3_4", "java_chapter_3",
                    "What is the ternary operator syntax?", "condition ? true : false", "if ? then : else", "? condition : value", "true : false ? condition",
                    0, "Ternary operator: condition ? value_if_true : value_if_false"));
                quizzes.add(createQuiz("jq3_5", "java_chapter_3",
                    "What is the result of 5 == 5?", "5", "true", "false", "10",
                    1, "The == operator checks equality and returns true if both values are equal."));
                break;
                
            case "java_chapter_4": // Control Statements
                quizzes.add(createQuiz("jq4_1", "java_chapter_4",
                    "Which is used for multiple conditions?", "if", "if-else", "switch", "for",
                    2, "switch statement is used for multiple conditions based on a single variable."));
                quizzes.add(createQuiz("jq4_2", "java_chapter_4",
                    "What does break do in switch?", "Starts loop", "Exits switch", "Continues to next case", "Ends program",
                    1, "break statement exits the switch block."));
                quizzes.add(createQuiz("jq4_3", "java_chapter_4",
                    "When is else block executed?", "Always", "When if is true", "When if is false", "Never",
                    2, "else block executes when the if condition is false."));
                quizzes.add(createQuiz("jq4_4", "java_chapter_4",
                    "What is nested if?", "if inside if", "Multiple if", "if with else", "if with switch",
                    0, "Nested if means an if statement inside another if statement."));
                quizzes.add(createQuiz("jq4_5", "java_chapter_4",
                    "Can switch work with String?", "No", "Yes (Java 7+)", "Only in Java 6", "Only with char",
                    1, "From Java 7 onwards, switch can work with String values."));
                break;
                
            case "java_chapter_5": // Loops
                quizzes.add(createQuiz("jq5_1", "java_chapter_5",
                    "Which loop checks condition first?", "for", "while", "do-while", "All",
                    1, "while loop checks the condition before executing the body."));
                quizzes.add(createQuiz("jq5_2", "java_chapter_5",
                    "How many times does do-while execute at minimum?", "0", "1", "2", "Depends",
                    1, "do-while loop executes at least once because condition is checked after execution."));
                quizzes.add(createQuiz("jq5_3", "java_chapter_5",
                    "What does break do in a loop?", "Pause loop", "Exit loop", "Skip iteration", "Restart loop",
                    1, "break statement immediately exits the loop."));
                quizzes.add(createQuiz("jq5_4", "java_chapter_5",
                    "What does continue do?", "Exit loop", "Skip current iteration", "Pause loop", "Repeat iteration",
                    1, "continue skips the current iteration and moves to the next one."));
                quizzes.add(createQuiz("jq5_5", "java_chapter_5",
                    "Which loop is best for known iterations?", "while", "do-while", "for", "foreach",
                    2, "for loop is best when the number of iterations is known in advance."));
                break;
                
            case "java_chapter_6": // Arrays & Strings
                quizzes.add(createQuiz("jq6_1", "java_chapter_6",
                    "Where does array index start?", "0", "1", "-1", "Anywhere",
                    0, "In Java, array index starts from 0."));
                quizzes.add(createQuiz("jq6_2", "java_chapter_6",
                    "How to find array length?", "array.size()", "array.length", "array.length()", "length(array)",
                    1, "Use array.length to get the length of an array."));
                quizzes.add(createQuiz("jq6_3", "java_chapter_6",
                    "What is String?", "Primitive type", "Class", "Keyword", "Operator",
                    1, "String is a class in Java, not a primitive type."));
                quizzes.add(createQuiz("jq6_4", "java_chapter_6",
                    "Which method compares strings?", "compare()", "equals()", "==", "strcmp()",
                    1, "equals() method is used to compare string content."));
                quizzes.add(createQuiz("jq6_5", "java_chapter_6",
                    "Are strings mutable in Java?", "Yes", "No", "Sometimes", "Depends on JVM",
                    1, "Strings are immutable in Java. Once created, they cannot be changed."));
                break;
                
            case "java_chapter_7": // Methods
                quizzes.add(createQuiz("jq7_1", "java_chapter_7",
                    "What is a method?", "Variable", "Function", "Class", "Package",
                    1, "A method is a function that belongs to a class."));
                quizzes.add(createQuiz("jq7_2", "java_chapter_7",
                    "What does void mean?", "Empty method", "No return value", "No parameters", "Static method",
                    1, "void means the method does not return any value."));
                quizzes.add(createQuiz("jq7_3", "java_chapter_7",
                    "What is method overloading?", "Same name, different parameters", "Different name, same parameters", "Same everything", "Overriding",
                    0, "Method overloading means multiple methods with same name but different parameters."));
                quizzes.add(createQuiz("jq7_4", "java_chapter_7",
                    "Can a method return multiple values?", "Yes", "No, only one", "Only with arrays", "Only with objects",
                    1, "A method can return only one value (but it can be an array or object)."));
                quizzes.add(createQuiz("jq7_5", "java_chapter_7",
                    "What is recursion?", "Loop", "Method calling itself", "Method overloading", "Inheritance",
                    1, "Recursion is when a method calls itself."));
                break;
                
            case "java_chapter_8": // OOP - Classes & Objects
                quizzes.add(createQuiz("jq8_1", "java_chapter_8",
                    "What is a class?", "Object", "Blueprint for objects", "Method", "Variable",
                    1, "A class is a blueprint or template for creating objects."));
                quizzes.add(createQuiz("jq8_2", "java_chapter_8",
                    "What is an object?", "Class", "Instance of a class", "Method", "Variable",
                    1, "An object is an instance of a class."));
                quizzes.add(createQuiz("jq8_3", "java_chapter_8",
                    "Which keyword creates an object?", "class", "new", "object", "create",
                    1, "The new keyword is used to create an object."));
                quizzes.add(createQuiz("jq8_4", "java_chapter_8",
                    "What is a constructor?", "Method", "Variable", "Special method to initialize objects", "Class",
                    2, "A constructor is a special method that initializes objects."));
                quizzes.add(createQuiz("jq8_5", "java_chapter_8",
                    "Can a constructor return a value?", "Yes", "No", "Only int", "Only void",
                    1, "Constructors do not have a return type, not even void."));
                break;
                
            case "java_chapter_9": // OOP - Inheritance
                quizzes.add(createQuiz("jq9_1", "java_chapter_9",
                    "What is inheritance?", "Creating objects", "Acquiring properties from parent class", "Method overloading", "Encapsulation",
                    1, "Inheritance allows a class to acquire properties and methods from a parent class."));
                quizzes.add(createQuiz("jq9_2", "java_chapter_9",
                    "Which keyword is used for inheritance?", "inherit", "extends", "implements", "super",
                    1, "The extends keyword is used for inheritance."));
                quizzes.add(createQuiz("jq9_3", "java_chapter_9",
                    "Can Java class extend multiple classes?", "Yes", "No", "Only interfaces", "Sometimes",
                    1, "Java does not support multiple inheritance. A class can extend only one class."));
                quizzes.add(createQuiz("jq9_4", "java_chapter_9",
                    "What is super keyword used for?", "Create object", "Call parent class members", "Define class", "Loop",
                    1, "super keyword is used to access parent class members."));
                quizzes.add(createQuiz("jq9_5", "java_chapter_9",
                    "Which class is parent of all classes?", "Class", "Object", "Parent", "Main",
                    1, "Object class is the parent of all classes in Java."));
                break;
                
            case "java_chapter_10": // OOP - Polymorphism
                quizzes.add(createQuiz("jq10_1", "java_chapter_10",
                    "What is polymorphism?", "One form", "Many forms", "Single inheritance", "Encapsulation",
                    1, "Polymorphism means many forms - same method behaving differently."));
                quizzes.add(createQuiz("jq10_2", "java_chapter_10",
                    "What is method overriding?", "Same class, different methods", "Parent-child, same method signature", "Different parameters", "Static method",
                    1, "Method overriding is when child class provides specific implementation of parent's method."));
                quizzes.add(createQuiz("jq10_3", "java_chapter_10",
                    "Which annotation is used for overriding?", "@Override", "@Overload", "@Super", "@Parent",
                    0, "@Override annotation is used to indicate method overriding."));
                quizzes.add(createQuiz("jq10_4", "java_chapter_10",
                    "Can we override static methods?", "Yes", "No", "Sometimes", "Only in child",
                    1, "Static methods cannot be overridden, they can be hidden."));
                quizzes.add(createQuiz("jq10_5", "java_chapter_10",
                    "What is runtime polymorphism?", "Method overloading", "Method overriding", "Inheritance", "Encapsulation",
                    1, "Runtime polymorphism is achieved through method overriding."));
                break;
                
            case "java_chapter_11": // Exception Handling
                quizzes.add(createQuiz("jq11_1", "java_chapter_11",
                    "What is an exception?", "Error in code", "Runtime problem", "Compilation error", "Warning",
                    1, "An exception is an event that disrupts normal program flow at runtime."));
                quizzes.add(createQuiz("jq11_2", "java_chapter_11",
                    "Which block handles exceptions?", "if", "try-catch", "switch", "for",
                    1, "try-catch block is used to handle exceptions."));
                quizzes.add(createQuiz("jq11_3", "java_chapter_11",
                    "What is finally block?", "Handles exception", "Always executes", "Optional catch", "Error handling",
                    1, "finally block always executes whether exception occurs or not."));
                quizzes.add(createQuiz("jq11_4", "java_chapter_11",
                    "Which keyword throws exception manually?", "throw", "throws", "catch", "try",
                    0, "throw keyword is used to throw an exception manually."));
                quizzes.add(createQuiz("jq11_5", "java_chapter_11",
                    "What is NullPointerException?", "Null value", "Accessing null object reference", "Empty string", "Zero division",
                    1, "NullPointerException occurs when trying to use null object reference."));
                break;
                
            case "java_chapter_12": // Collections
                quizzes.add(createQuiz("jq12_1", "java_chapter_12",
                    "What is ArrayList?", "Array", "Dynamic array", "Static list", "Fixed size list",
                    1, "ArrayList is a dynamic array that can grow or shrink in size."));
                quizzes.add(createQuiz("jq12_2", "java_chapter_12",
                    "Which interface does ArrayList implement?", "Map", "List", "Set", "Queue",
                    1, "ArrayList implements the List interface."));
                quizzes.add(createQuiz("jq12_3", "java_chapter_12",
                    "How to add element to ArrayList?", "insert()", "add()", "put()", "push()",
                    1, "add() method is used to add elements to ArrayList."));
                quizzes.add(createQuiz("jq12_4", "java_chapter_12",
                    "What is HashSet?", "Ordered collection", "Unordered unique collection", "Key-value pairs", "Queue",
                    1, "HashSet stores unique elements in unordered manner."));
                quizzes.add(createQuiz("jq12_5", "java_chapter_12",
                    "What is HashMap?", "List", "Set", "Key-value pairs", "Array",
                    2, "HashMap stores data in key-value pairs."));
                quizzes.add(createQuiz("jq12_6", "java_chapter_12",
                    "Does ArrayList allow duplicates?", "Yes", "No", "Sometimes", "Only null",
                    0, "ArrayList allows duplicate elements."));
                quizzes.add(createQuiz("jq12_7", "java_chapter_12",
                    "Which is faster for search: ArrayList or LinkedList?", "ArrayList", "LinkedList", "Both same", "Depends",
                    0, "ArrayList is faster for random access/search operations."));
                break;
            
            // Java Final Comprehensive Quiz - 70 Questions
            case "java_chapter_final":
                // EASY QUESTIONS (1-15)
                quizzes.add(createQuiz("jfq1", "java_chapter_final", "Who created Java?", "Dennis Ritchie", "James Gosling", "Bjarne Stroustrup", "Guido van Rossum", 1, "James Gosling created Java at Sun Microsystems."));
                quizzes.add(createQuiz("jfq2", "java_chapter_final", "What is Java?", "Scripting language", "Programming language", "Markup language", "Query language", 1, "Java is an object-oriented programming language."));
                quizzes.add(createQuiz("jfq3", "java_chapter_final", "Which keyword declares a constant?", "const", "final", "static", "constant", 1, "The final keyword is used to declare constants."));
                quizzes.add(createQuiz("jfq4", "java_chapter_final", "What is JVM?", "Java Virtual Machine", "Java Variable Method", "Java Version Manager", "Java Verified Module", 0, "JVM stands for Java Virtual Machine."));
                quizzes.add(createQuiz("jfq5", "java_chapter_final", "Which is a primitive type?", "String", "int", "Integer", "Array", 1, "int is a primitive data type."));
                quizzes.add(createQuiz("jfq6", "java_chapter_final", "What is the size of int?", "2 bytes", "4 bytes", "8 bytes", "Depends", 1, "int is always 4 bytes in Java."));
                quizzes.add(createQuiz("jfq7", "java_chapter_final", "Which loop checks condition first?", "for", "while", "do-while", "foreach", 1, "while loop checks condition before execution."));
                quizzes.add(createQuiz("jfq8", "java_chapter_final", "What does ++ do?", "Multiply by 2", "Increment by 1", "Add 10", "Square", 1, "++ increments the value by 1."));
                quizzes.add(createQuiz("jfq9", "java_chapter_final", "Array index starts from?", "0", "1", "-1", "Depends", 0, "Array index always starts from 0 in Java."));
                quizzes.add(createQuiz("jfq10", "java_chapter_final", "Which creates an object?", "class", "new", "object", "create", 1, "The new keyword creates objects."));
                quizzes.add(createQuiz("jfq11", "java_chapter_final", "What is main method return type?", "void", "int", "String", "boolean", 0, "main method has void return type."));
                quizzes.add(createQuiz("jfq12", "java_chapter_final", "Default value of boolean?", "true", "false", "0", "null", 1, "Default value of boolean is false."));
                quizzes.add(createQuiz("jfq13", "java_chapter_final", "Which handles exceptions?", "if-else", "try-catch", "switch", "for", 1, "try-catch block handles exceptions."));
                quizzes.add(createQuiz("jfq14", "java_chapter_final", "String is?", "Primitive", "Class", "Keyword", "Operator", 1, "String is a class in Java."));
                quizzes.add(createQuiz("jfq15", "java_chapter_final", "What does void mean?", "Empty", "No return", "No parameters", "Static", 1, "void means no return value."));
                
                // MEDIUM QUESTIONS (16-30)
                quizzes.add(createQuiz("jfq16", "java_chapter_final", "What is method overloading?", "Same name, different params", "Different name", "Inheritance", "Polymorphism", 0, "Method overloading means same name with different parameters."));
                quizzes.add(createQuiz("jfq17", "java_chapter_final", "Which is true about constructors?", "Must have return type", "Name must match class", "Can be static", "Cannot have parameters", 1, "Constructor name must match the class name."));
                quizzes.add(createQuiz("jfq18", "java_chapter_final", "What is inheritance?", "Creating objects", "Acquiring parent properties", "Method overloading", "Exception handling", 1, "Inheritance means acquiring properties from parent class."));
                quizzes.add(createQuiz("jfq19", "java_chapter_final", "Which keyword for inheritance?", "inherit", "extends", "implements", "super", 1, "extends keyword is used for inheritance."));
                quizzes.add(createQuiz("jfq20", "java_chapter_final", "Can Java class extend multiple classes?", "Yes", "No", "Sometimes", "With interface", 1, "Java does not support multiple inheritance."));
                quizzes.add(createQuiz("jfq21", "java_chapter_final", "What is polymorphism?", "One form", "Many forms", "Single class", "No inheritance", 1, "Polymorphism means many forms."));
                quizzes.add(createQuiz("jfq22", "java_chapter_final", "ArrayList implements which interface?", "Map", "List", "Set", "Queue", 1, "ArrayList implements List interface."));
                quizzes.add(createQuiz("jfq23", "java_chapter_final", "Does ArrayList allow duplicates?", "Yes", "No", "Sometimes", "Only null", 0, "ArrayList allows duplicate elements."));
                quizzes.add(createQuiz("jfq24", "java_chapter_final", "What is encapsulation?", "Data hiding", "Inheritance", "Polymorphism", "Abstraction", 0, "Encapsulation is data hiding using access modifiers."));
                quizzes.add(createQuiz("jfq25", "java_chapter_final", "Static methods belong to?", "Object", "Class", "Interface", "Package", 1, "Static methods belong to the class, not objects."));
                quizzes.add(createQuiz("jfq26", "java_chapter_final", "Which loop executes at least once?", "for", "while", "do-while", "foreach", 2, "do-while loop executes at least once."));
                quizzes.add(createQuiz("jfq27", "java_chapter_final", "How to compare strings?", "==", "equals()", "compare()", "strcmp()", 1, "Use equals() method to compare string content."));
                quizzes.add(createQuiz("jfq28", "java_chapter_final", "finally block always executes?", "Yes", "No", "Sometimes", "Only with catch", 0, "finally block always executes."));
                quizzes.add(createQuiz("jfq29", "java_chapter_final", "What is super keyword?", "Create object", "Access parent members", "Static", "Final", 1, "super keyword accesses parent class members."));
                quizzes.add(createQuiz("jfq30", "java_chapter_final", "Can we override static methods?", "Yes", "No", "Sometimes", "With super", 1, "Static methods cannot be overridden."));
                
                // HARD QUESTIONS (31-50)
                quizzes.add(createQuiz("jfq31", "java_chapter_final", "What is difference between == and equals()?", "No difference", "== checks reference, equals() checks content", "== is faster", "equals() is deprecated", 1, "== compares references, equals() compares actual content."));
                quizzes.add(createQuiz("jfq32", "java_chapter_final", "Can constructor be private?", "Yes", "No", "Only in abstract class", "Only static", 0, "Constructor can be private (used in Singleton pattern)."));
                quizzes.add(createQuiz("jfq33", "java_chapter_final", "What is method overriding?", "Same class methods", "Child redefines parent method", "Different parameters", "Static methods", 1, "Method overriding is when child class redefines parent's method."));
                quizzes.add(createQuiz("jfq34", "java_chapter_final", "Which collection doesn't allow duplicates?", "ArrayList", "LinkedList", "HashSet", "Vector", 2, "HashSet does not allow duplicate elements."));
                quizzes.add(createQuiz("jfq35", "java_chapter_final", "What is autoboxing?", "Automatic sorting", "Primitive to wrapper conversion", "Auto increment", "Automatic casting", 1, "Autoboxing is automatic conversion of primitive to wrapper class."));
                quizzes.add(createQuiz("jfq36", "java_chapter_final", "Can interface have concrete methods?", "No", "Yes, with default keyword", "Only private", "Only static", 1, "From Java 8, interfaces can have default methods."));
                quizzes.add(createQuiz("jfq37", "java_chapter_final", "What is marker interface?", "Has many methods", "Has no methods", "Abstract class", "Final class", 1, "Marker interface has no methods (e.g., Serializable)."));
                quizzes.add(createQuiz("jfq38", "java_chapter_final", "Difference between String and StringBuilder?", "No difference", "String is immutable, StringBuilder is mutable", "StringBuilder is faster always", "String is deprecated", 1, "String is immutable, StringBuilder is mutable and efficient for string operations."));
                quizzes.add(createQuiz("jfq39", "java_chapter_final", "What is NullPointerException?", "Null value error", "Accessing null reference", "Empty string", "Zero division", 1, "NullPointerException occurs when accessing null object reference."));
                quizzes.add(createQuiz("jfq40", "java_chapter_final", "Can we override main method?", "Yes", "No", "Only in subclass", "With super", 1, "main() method cannot be overridden as it's static."));
                quizzes.add(createQuiz("jfq41", "java_chapter_final", "What is abstraction?", "Hiding implementation", "Showing everything", "Data type", "Loop", 0, "Abstraction is hiding implementation details."));
                quizzes.add(createQuiz("jfq42", "java_chapter_final", "abstract class can have constructor?", "Yes", "No", "Only default", "Only private", 0, "Abstract class can have constructors."));
                quizzes.add(createQuiz("jfq43", "java_chapter_final", "Difference between ArrayList and LinkedList?", "No difference", "ArrayList faster for access, LinkedList for insertion", "LinkedList is deprecated", "ArrayList is immutable", 1, "ArrayList is faster for random access, LinkedList for insertions/deletions."));
                quizzes.add(createQuiz("jfq44", "java_chapter_final", "Can we have multiple catch blocks?", "No", "Yes", "Only two", "Only with finally", 1, "We can have multiple catch blocks for different exceptions."));
                quizzes.add(createQuiz("jfq45", "java_chapter_final", "What is this keyword?", "Parent reference", "Current object reference", "Static reference", "Super class", 1, "this keyword refers to current object."));
                quizzes.add(createQuiz("jfq46", "java_chapter_final", "Can interface extend multiple interfaces?", "No", "Yes", "Only two", "With implements", 1, "An interface can extend multiple interfaces."));
                quizzes.add(createQuiz("jfq47", "java_chapter_final", "What is covariant return type?", "Same return type", "Subclass return type", "Void return", "No return", 1, "Covariant return type allows overriding method to return subclass type."));
                quizzes.add(createQuiz("jfq48", "java_chapter_final", "Difference between throw and throws?", "No difference", "throw for manual exception, throws for declaration", "throw is deprecated", "throws is keyword only", 1, "throw is used to throw exception, throws declares exceptions."));
                quizzes.add(createQuiz("jfq49", "java_chapter_final", "Can finally block be skipped?", "Never", "Yes, with System.exit()", "With return", "With break", 1, "finally can be skipped only with System.exit()."));

                quizzes.add(createQuiz("jfq50", "java_chapter_final", "What is HashMap key requirement?", "Must be String", "Must override hashCode() and equals()", "Must be integer", "Must be final", 1, "HashMap keys should properly override hashCode() and equals()."));
                
                // VERY HARD QUESTIONS (51-70)
                quizzes.add(createQuiz("jfq51", "java_chapter_final", "What is happens-before relationship?", "Execution order", "Memory visibility guarantee in multithreading", "Method calling order", "Class loading order", 1, "Happens-before defines memory visibility in concurrent programming."));
                quizzes.add(createQuiz("jfq52", "java_chapter_final", "Difference between Comparable and Comparator?", "No different", "Comparable for natural order, Comparator for custom", "Comparable is deprecated", "Comparator is interface only", 1, "Comparable defines natural ordering, Comparator allows custom sorting."));
                quizzes.add(createQuiz("jfq53", "java_chapter_final", "What is transient keyword?", "For inheritance", "Skip serialization of field", "Make variable constant", "Thread-safe", 1, "transient keyword prevents field from being serialized."));
                quizzes.add(createQuiz("jfq54", "java_chapter_final", "What is volatile keyword?", "Makes constant", "Ensures visibility across threads", "Prevents inheritance", "Makes static", 1, "volatile ensures variable changes are visible to all threads."));
                quizzes.add(createQuiz("jfq55", "java_chapter_final", "Can we synchronize constructor?", "Yes", "No", "Only static", "With volatile", 1, "Constructors cannot be synchronized."));
                quizzes.add(createQuiz("jfq56", "java_chapter_final", "What is double brace initialization?", "Two variables", "Anonymous inner class initialization", "Double data type", "Two constructors", 1, "Double brace initialization uses anonymous inner class for collection initialization."));
                quizzes.add(createQuiz("jfq57", "java_chapter_final", "Difference between fail-fast and fail-safe?", "No difference", "fail-fast throws exception, fail-safe doesn't", "fail-fast is faster", "fail-safe is deprecated", 1, "fail-fast iterators throw ConcurrentModificationException, fail-safe work on cloned collection."));
                quizzes.add(createQuiz("jfq58", "java_chapter_final", "What is method reference in Java 8?", "Reference variable", "Shorthand for lambda", "Pointer", "Address operator", 1, "Method reference (::) is shorthand syntax for lambda expressions."));
                quizzes.add(createQuiz("jfq59", "java_chapter_final", "Can lambda expression access local variables?", "No", "Yes, if effectively final", "Only static", "Only instance", 1, "Lambda can access local variables if they are effectively final."));
                quizzes.add(createQuiz("jfq60", "java_chapter_final", "What is Optional class?", "For primitives", "Container for null-safe operations", "For arrays", "For strings", 1, "Optional is a container to handle null values gracefully."));
                quizzes.add(createQuiz("jfq61", "java_chapter_final", "Difference between map() and flatMap()?", "No difference", "map returns stream of values, flatMap flattens nested streams", "flatMap is deprecated", "map is faster", 1, "flatMap flattens nested streams into single stream."));
                quizzes.add(createQuiz("jfq62", "java_chapter_final", "What is Stream in Java 8?", "Input/output", "Sequence of elements for functional operations", "Thread", "Network connection", 1, "Stream is a sequence of elements supporting functional-style operations."));
                quizzes.add(createQuiz("jfq63", "java_chapter_final", "Can Stream be reused?", "Yes", "No", "Sometimes", "With reset()", 1, "Streams cannot be reused after a terminal operation."));
                quizzes.add(createQuiz("jfq64", "java_chapter_final", "What is CompletableFuture?", "Thread class", "For asynchronous programming", "Collection type", "Exception type", 1, "CompletableFuture is used for asynchronous, non-blocking programming."));
                quizzes.add(createQuiz("jfq65", "java_chapter_final", "Difference between synchronized block and method?", "No difference", "Block can synchronize on any object, method on this", "Block is deprecated", "Method is faster", 1, "Synchronized block can lock on any object, method locks on 'this'."));
                quizzes.add(createQuiz("jfq66", "java_chapter_final", "What is ConcurrentHashMap?", "Thread-unsafe map", "Thread-safe map without locking entire map", "Deprecated map", "Sorted map", 1, "ConcurrentHashMap provides thread-safety with better concurrency than Hashtable."));
                quizzes.add(createQuiz("jfq67", "java_chapter_final", "Can we clone singleton object?", "Yes", "No, must override clone()", "Always", "Never", 1, "Singleton should override clone() to prevent cloning."));
                quizzes.add(createQuiz("jfq68", "java_chapter_final", "What is phantom reference?", "Null reference", "Reference for cleanup before garbage collection", "Strong reference", "Weak reference", 1, "PhantomReference is used for cleanup actions before object is garbage collected."));
                quizzes.add(createQuiz("jfq69", "java_chapter_final", "Difference between  CountDownLatch and CyclicBarrier?", "No difference", "CountDownLatch is one-time, CyclicBarrier is reusable", "CountDownLatch is deprecated", "CyclicBarrier is faster", 1, "CountDownLatch is one-time use, CyclicBarrier can be reset and reused."));
                quizzes.add(createQuiz("jfq70", "java_chapter_final", "What is ForkJoinPool?", "Thread pool", "Framework for parallel task execution", "Collection type", "Exception handler", 1, "ForkJoinPool is a framework for executing recursive parallel tasks efficiently."));
                
                // Additional questions (71-100)
                quizzes.add(createQuiz("jfq71", "java_chapter_final", "instanceof operator checks?", "Value", "Type at runtime", "Compile-time type", "Memory", 1, "instanceof checks object type at runtime."));
                quizzes.add(createQuiz("jfq72", "java_chapter_final", "Wrapper class for int?", "Int", "Integer", "Number", "Primitive", 1, "Integer is wrapper for int."));
                quizzes.add(createQuiz("jfq73", "java_chapter_final", "Autoboxing converts?", "String to int", "Primitive to wrapper", "Object to primitive", "Class to object", 1, "Autoboxing: primitive to wrapper automatically."));
                quizzes.add(createQuiz("jfq74", "java_chapter_final", "Unboxing converts?", "Wrapper to primitive", "Primitive to wrapper", "String to int", "Object to class", 0, "Unboxing: wrapper to primitive."));
                quizzes.add(createQuiz("jfq75", "java_chapter_final", "Immutable class?", "ArrayList", "String", "StringBuilder", "HashMap", 1, "String is immutable in Java."));
                quizzes.add(createQuiz("jfq76", "java_chapter_final", "StringBuilder vs StringBuffer?", "Same", "StringBuilder not thread-safe", "StringBuffer deprecated", "No difference", 1, "StringBuilder is not thread-safe, StringBuffer is."));
                quizzes.add(createQuiz("jfq77", "java_chapter_final", "equals() vs ==?", "Same", "equals() compares content, == references", "== is deprecated", "No difference", 1, "equals() checks content, == checks references."));
                quizzes.add(createQuiz("jfq78", "java_chapter_final", "hashCode() must?", "Return unique value", "Be consistent with equals()", "Return 0", "Be final", 1, "hashCode() must be consistent with equals()."));
                quizzes.add(createQuiz("jfq79", "java_chapter_final", "clone() creates?", "Deep copy", "Shallow copy by default", "No copy", "Reference", 1, "clone() creates shallow copy by default."));
                quizzes.add(createQuiz("jfq80", "java_chapter_final", "Deep copy requires?", "clone() only", "Manual copying", "Automatic", "Not possible", 1, "Deep copy needs manual implementation."));
                quizzes.add(createQuiz("jfq81", "java_chapter_final", "Serializable is?", "Class", "Marker interface", "Abstract class", "Annotation", 1, "Serializable is a marker interface."));
                quizzes.add(createQuiz("jfq82", "java_chapter_final", "transient prevents?", "Inheritance", "Serialization", "Instantiation", "Compilation", 1, "transient prevents field serialization."));
                quizzes.add(createQuiz("jfq83", "java_chapter_final", "Enum can implement?", "Nothing", "Interface", "Abstract class", "Multiple classes", 1, "Enum can implement interfaces."));
                quizzes.add(createQuiz("jfq84", "java_chapter_final", "Enum constructor is?", "public", "private", "protected", "default", 1, "Enum constructor is always private."));
                quizzes.add(createQuiz("jfq85", "java_chapter_final", "varargs syntax?", "type...", "type[]", "...type", "type*", 0, "varargs: Type... name"));
                quizzes.add(createQuiz("jfq86", "java_chapter_final", "varargs must be?", "First param", "Last param", "Any position", "Only param", 1, "varargs must be last parameter."));
                quizzes.add(createQuiz("jfq87", "java_chapter_final", "Annotations start with?", "#", "@", "$", "&", 1, "Annotations use @ symbol."));
                quizzes.add(createQuiz("jfq88", "java_chapter_final", "@Override purpose?", "Required", "Compile-time check", "Runtime check", "Documentation", 1, "@Override enables compile-time verification."));
                quizzes.add(createQuiz("jfq89", "java_chapter_final", "@Deprecated marks?", "Error", "Outdated code", "Required", "Final", 1, "@Deprecated marks outdated elements."));
                quizzes.add(createQuiz("jfq90", "java_chapter_final", "@SuppressWarnings suppresses?", "Errors", "Compiler warnings", "Exceptions", "Nothing", 1, "@SuppressWarnings hides compiler warnings."));
                quizzes.add(createQuiz("jfq91", "java_chapter_final", "Reflection allows?", "Nothing", "Runtime class inspection", "Compilation", "Optimization", 1, "Reflection inspects classes at runtime."));
                quizzes.add(createQuiz("jfq92", "java_chapter_final", "Class.forName() returns?", "Object", "Class object", "Instance", "String", 1, "Returns Class object for given name."));
                quizzes.add(createQuiz("jfq93", "java_chapter_final", "Generics provide?", "Performance", "Type safety", "Inheritance", "Polymorphism", 1, "Generics provide compile-time type safety."));
                quizzes.add(createQuiz("jfq94", "java_chapter_final", "Type erasure happens?", "Runtime", "Compile-time", "Never", "Both", 1, "Type erasure occurs at compile-time."));
                quizzes.add(createQuiz("jfq95", "java_chapter_final", "Bounded type: <T extends Number>?", "T can be any type", "T must be Number or subclass", "T must be superclass", "Syntax error", 1, "T must be Number or its subclass."));
                quizzes.add(createQuiz("jfq96", "java_chapter_final", "Wildcard <?> means?", "Unknown type", "Any type", "No type", "Error", 1, "<?> represents unknown type."));
                quizzes.add(createQuiz("jfq97", "java_chapter_final", "<? extends T> is?", "Lower bound", "Upper bound", "Exact type", "Error", 1, "<? extends T> is upper bounded wildcard."));
                quizzes.add(createQuiz("jfq98", "java_chapter_final", "<? super T> is?", "Upper bound", "Lower bound", "Exact type", "Error", 1, "<? super T> is lower bounded wildcard."));
                quizzes.add(createQuiz("jfq99", "java_chapter_final", "PECS principle?", "Performance", "Producer Extends Consumer Super", "Pattern", "Protocol", 1, "PECS: Producer Extends, Consumer Super for wildcards."));
                quizzes.add(createQuiz("jfq100", "java_chapter_final", "Java ecosystem includes?", "Only JVM", "JVM, Libraries, Tools, Frameworks", "Compiler only", "IDE only", 1, "Java ecosystem is comprehensive: JVM, libraries, tools, and frameworks."));
                break;
        }
        
        return quizzes;
    }

    private Quiz createQuiz(String id, String chapterId, String question, 
                           String opt1, String opt2, String opt3, String opt4,
                           int correctAnswer, String explanation) {
        ArrayList<String> options = new ArrayList<>();
        options.add(opt1);
        options.add(opt2);
        options.add(opt3);
        options.add(opt4);
        return new Quiz(id, chapterId, question, options, correctAnswer, explanation);
    }

    // ==================== JAVA PROGRAMMING METHODS ====================
    
    // Get all chapters for Java Programming
    public List<Chapter> getJavaChapters() {
        List<Chapter> chapters = new ArrayList<>();
        
        chapters.add(new Chapter("java_chapter_1", "java_programming", "Module 1: Java Basics", 1, 5));
        chapters.add(new Chapter("java_chapter_2", "java_programming", "Module 2: Data Types & Variables", 2, 5));
        chapters.add(new Chapter("java_chapter_3", "java_programming", "Module 3: Operators", 3, 4));
        chapters.add(new Chapter("java_chapter_4", "java_programming", "Module 4: Control Statements", 4, 4));
        chapters.add(new Chapter("java_chapter_5", "java_programming", "Module 5: Loops", 5, 4));
        chapters.add(new Chapter("java_chapter_6", "java_programming", "Module 6: Arrays & Strings", 6, 5));
        chapters.add(new Chapter("java_chapter_7", "java_programming", "Module 7: Methods", 7, 4));
        chapters.add(new Chapter("java_chapter_8", "java_programming", "Module 8: OOP - Classes & Objects", 8, 5));
        chapters.add(new Chapter("java_chapter_9", "java_programming", "Module 9: OOP - Inheritance", 9, 4));
        chapters.add(new Chapter("java_chapter_10", "java_programming", "Module 10: OOP - Polymorphism", 10, 4));
        chapters.add(new Chapter("java_chapter_11", "java_programming", "Module 11: Exception Handling", 11, 4));
        chapters.add(new Chapter("java_chapter_12", "java_programming", "Module 12: Collections", 12, 4));
        chapters.add(new Chapter("java_chapter_13", "java_programming", "📝 Quiz", 13, 12));
        
        // Update progress for each chapter based on completed lessons
        User currentUser = getCurrentUser();
        if (currentUser != null) {
            for (Chapter chapter : chapters) {
                int completedCount = 0;
                List<Lesson> lessons = getLessonsForChapter(chapter.getChapterId());
                
                for (Lesson lesson : lessons) {
                    if (currentUser.isLessonCompleted(lesson.getLessonId())) {
                        completedCount++;
                    }
                }
                
                chapter.setCompletedLessons(completedCount);
            }
        }
        
        return chapters;
    }
    
    // Java Module 1: Java Basics
    private List<Lesson> getJavaModule1Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        Lesson lesson1 = new Lesson(
            "java_lesson_1", "java_chapter_1", "What is Java?",
            // English explanation
            "🎯 What is Java Programming Language?\n\n" +
            "Java is one of the most popular programming languages in the world! It was created by James Gosling at Sun Microsystems in 1995.\n\n" +
            "📝 What Makes Java Special?\n" +
            "Java is an Object-Oriented Programming (OOP) language that follows the principle: 'Write Once, Run Anywhere' (WORA). This means you write code once and it can run on any device that has Java installed!\n\n" +
            "🌟 Why Learn Java?\n" +
            "• Platform Independent: Runs on Windows, Mac, Linux, Android\n" +
            "• Object-Oriented: Easy to organize and maintain code\n" +
            "• Secure: Built-in security features\n" +
            "• Rich API: Thousands of ready-to-use classes\n" +
            "• High Demand: Used by millions of developers worldwide\n\n" +
            "🎓 Where is Java Used?\n" +
            "• Android Apps (90% of Android apps use Java)\n" +
            "• Web Applications (Banking, E-commerce)\n" +
            "• Enterprise Software\n" +
            "• Games and Desktop Applications\n" +
            "• Big Data Technologies (Hadoop, Spark)\n\n" +
            "Java is called a 'Platform Independent' language because of the Java Virtual Machine (JVM)!",
            "",
            "public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, Java!\");\n    }\n}",
            "Hello, Java!",
            "💡 Java is case-sensitive: 'Main' and 'main' are different.\n" +
            "💡 Every Java program must have a main() method.\n" +
            "💡 Java files must have .java extension.",
            "Q: Who created Java?\nAns: James Gosling"
            , 1
        );
        lesson1.setExplanationHinglish(
            "🎯 Java Programming Language Kya Hai?\n\n" +
            "Java duniya ki sabse popular programming languages mein se ek hai! Isse James Gosling ne Sun Microsystems mein 1995 mein banaya tha.\n\n" +
            "📝 Java Ko Kya Special Banata Hai?\n" +
            "Java ek Object-Oriented Programming (OOP) language hai jo is principle ko follow karti hai: 'Write Once, Run Anywhere' (WORA). Matlab ek baar code likho aur kisi bhi device par chala sakte ho jahan Java installed hai!\n\n" +
            "🌟 Java Kyu Seekhein?\n" +
            "• Platform Independent: Windows, Mac, Linux, Android sabpe chalti hai\n" +
            "• Object-Oriented: Code ko organize aur maintain karna easy hai\n" +
            "• Secure: Built-in security features hain\n" +
            "• Rich API: Hazaaron ready-to-use classes hain\n" +
            "• High Demand: Millions of developers worldwide use karte hain\n\n" +
            "🎓 Java Kahan Use Hoti Hai?\n" +
            "• Android Apps (90% Android apps Java mein bane hain)\n" +
            "• Web Applications (Banking, E-commerce)\n" +
            "• Enterprise Software\n" +
            "• Games aur Desktop Applications\n" +
            "• Big Data Technologies (Hadoop, Spark)\n\n" +
            "Java ko 'Platform Independent' language kehte hain Java Virtual Machine (JVM) ki wajah se!"
        );
        lessons.add(lesson1);
        
        Lesson lesson2 = new Lesson(
            "java_lesson_2", "java_chapter_1", "History of Java",
            // English explanation
            "📜 The Amazing Story of Java\n\n" +
            "In 1991, James Gosling and his team at Sun Microsystems started a project called 'Green Project'. Their goal was to create a language for digital devices like set-top boxes and TVs.\n\n" +
            "👨‍💻 Birth of Java (Originally 'Oak')\n" +
            "Initially, the language was called 'Oak' (named after an oak tree outside Gosling's office). But 'Oak' was already trademarked, so they renamed it 'Java' (inspired by Java coffee)!\n\n" +
            "📖 Java Timeline:\n" +
            "• 1991: Green Project started\n" +
            "• 1995: Java 1.0 released - 'Write Once, Run Anywhere'\n" +
            "• 1996: JDK 1.0 released\n" +
            "• 1998: Java 2 (J2SE, J2EE, J2ME)\n" +
            "• 2004: Java 5 - Major update with Generics\n" +
            "• 2006: Sun made Java open source\n" +
            "• 2010: Oracle acquired Sun Microsystems\n" +
            "• 2014: Java 8 - Lambda expressions\n" +
            "• 2021: Java 17 - LTS version\n\n" +
            "🌍 Impact of Java:\n" +
            "• 3 billion devices run Java\n" +
            "• Most popular language for Android development\n" +
            "• Used by companies like Google, Amazon, Netflix\n\n" +
            "Java's motto: 'Write Once, Run Anywhere' changed the programming world!",
            "",
            "// Java version history\n// 1995: Java 1.0 - The beginning\n// 1998: Java 1.2 - Collections Framework\n// 2004: Java 5 - Generics, Annotations\n// 2014: Java 8 - Lambda, Stream API\n// 2021: Java 17 - Latest LTS",
            "",
            "📚 Java was originally designed for interactive television.\n" +
            "📚 The Java mascot is 'Duke' - a friendly character.\n" +
            "📚 Java is owned by Oracle Corporation since 2010.",
            "Q: What was Java originally called?\nAns: Oak"
            , 2
        );
        lesson2.setExplanationHinglish(
            "📜 Java Ki Amazing Kahani\n\n" +
            "1991 mein James Gosling aur unki team ne Sun Microsystems mein ek project start kiya jiska naam tha 'Green Project'. Unka goal tha digital devices jaise set-top boxes aur TVs ke liye language banana.\n\n" +
            "👨‍💻 Java Ka Janam (Pehle 'Oak' Tha)\n" +
            "Shuru mein is language ka naam 'Oak' tha (Gosling ke office ke bahar ek oak tree ke naam par). Lekin 'Oak' already trademarked tha, toh unhone naam badal kar 'Java' rakha (Java coffee se inspired)!\n\n" +
            "📖 Java Timeline:\n" +
            "• 1991: Green Project shuru hui\n" +
            "• 1995: Java 1.0 release - 'Write Once, Run Anywhere'\n" +
            "• 1996: JDK 1.0 release\n" +
            "• 1998: Java 2 (J2SE, J2EE, J2ME)\n" +
            "• 2004: Java 5 - Generics ke saath major update\n" +
            "• 2006: Sun ne Java ko open source banaya\n" +
            "• 2010: Oracle ne Sun Microsystems ko acquire kiya\n" +
            "• 2014: Java 8 - Lambda expressions\n" +
            "• 2021: Java 17 - LTS version\n\n" +
            "🌍 Java Ka Impact:\n" +
            "• 3 billion devices Java chalate hain\n" +
            "• Android development ke liye sabse popular language\n" +
            "• Google, Amazon, Netflix jaise companies use karti hain\n\n" +
            "Java ka motto: 'Write Once, Run Anywhere' ne programming world ko badal diya!"
        );
        lessons.add(lesson2);
        
        Lesson lesson3 = new Lesson(
            "java_lesson_3", "java_chapter_1", "Features of Java",
            // English explanation
            "✨ Amazing Features of Java\n\n" +
            "1️⃣ Simple and Easy to Learn\n" +
            "Java syntax is based on C++, but simpler. No pointers, no operator overloading. Clean and readable code!\n\n" +
            "2️⃣ Object-Oriented\n" +
            "Everything in Java is an object. This makes code modular, flexible, and reusable. Concepts: Classes, Objects, Inheritance, Polymorphism.\n\n" +
            "3️⃣ Platform Independent (WORA)\n" +
            "Write code once, run anywhere! Java code is compiled to bytecode which runs on any platform with JVM.\n\n" +
            "4️⃣ Secure\n" +
            "• No explicit pointers\n" +
            "• Programs run inside JVM sandbox\n" +
            "• Bytecode verification\n" +
            "• Security Manager\n\n" +
            "5️⃣ Robust (Strong)\n" +
            "• Strong memory management\n" +
            "• Automatic garbage collection\n" +
            "• Exception handling\n" +
            "• Type checking\n\n" +
            "6️⃣ Multithreaded\n" +
            "Can perform multiple tasks simultaneously. Great for games, animations, and web applications!\n\n" +
            "7️⃣ High Performance\n" +
            "Just-In-Time (JIT) compiler makes Java fast. Bytecode is converted to native machine code at runtime.\n\n" +
            "8️⃣ Distributed\n" +
            "Java supports networking. Easy to create distributed applications using RMI and EJB.\n\n" +
            "Summary: Java is simple, secure, portable, and powerful!",
            "",
            "// Java features demonstration\npublic class Features {\n    public static void main(String[] args) {\n        // Simple syntax\n        int number = 100;\n        \n        // Object-oriented\n        String message = \"Java is awesome!\";\n        \n        // Platform independent\n        System.out.println(message);\n        System.out.println(\"Number: \" + number);\n    }\n}",
            "Java is awesome!\nNumber: 100",
            "⚡ Java automatically manages memory with Garbage Collection.\n" +
            "⚡ Java supports multithreading for concurrent execution.\n" +
            "⚡ Java is both compiled and interpreted language.",
            "Q: What makes Java platform independent?\nAns: JVM (Java Virtual Machine)"
            , 3
        );
        lesson3.setExplanationHinglish(
            "✨ Java Ki Amazing Features\n\n" +
            "1️⃣ Simple aur Seekhne Mein Easy\n" +
            "Java ka syntax C++ par based hai, lekin zyada simple. Pointers nahi, operator overloading nahi. Clean aur readable code!\n\n" +
            "2️⃣ Object-Oriented\n" +
            "Java mein sab kuch object hai. Isse code modular, flexible, aur reusable banta hai. Concepts: Classes, Objects, Inheritance, Polymorphism.\n\n" +
            "3️⃣ Platform Independent (WORA)\n" +
            "Ek baar code likho, kahin bhi chalao! Java code bytecode mein compile hota hai jo kisi bhi platform par JVM ke saath chal sakta hai.\n\n" +
            "4️⃣ Secure\n" +
            "• Explicit pointers nahi\n" +
            "• Programs JVM sandbox ke andar chalte hain\n" +
            "• Bytecode verification\n" +
            "• Security Manager\n\n" +
            "5️⃣ Robust (Strong)\n" +
            "• Strong memory management\n" +
            "• Automatic garbage collection\n" +
            "• Exception handling\n" +
            "• Type checking\n\n" +
            "6️⃣ Multithreaded\n" +
            "Ek saath multiple tasks perform kar sakta hai. Games, animations, aur web applications ke liye best!\n\n" +
            "7️⃣ High Performance\n" +
            "Just-In-Time (JIT) compiler Java ko fast banata hai. Bytecode runtime par native machine code mein convert hota hai.\n\n" +
            "8️⃣ Distributed\n" +
            "Java networking support karta hai. RMI aur EJB use karke distributed applications banana easy hai.\n\n" +
            "Summary: Java simple, secure, portable, aur powerful hai!"
        );
        lessons.add(lesson3);
        
        Lesson lesson4 = new Lesson(
            "java_lesson_4", "java_chapter_1", "JVM, JRE, and JDK",
            // English explanation
            "🔧 Understanding Java's Core Components\n\n" +
            "To understand Java, you must know these three important components:\n\n" +
            "1️⃣ JVM (Java Virtual Machine)\n" +
            "• The heart of Java's platform independence\n" +
            "• Executes Java bytecode\n" +
            "• Provides runtime environment\n" +
            "• Different JVM for different platforms (Windows JVM, Linux JVM)\n" +
            "• Makes 'Write Once, Run Anywhere' possible\n\n" +
            "2️⃣ JRE (Java Runtime Environment)\n" +
            "• JRE = JVM + Library Classes\n" +
            "• Needed to RUN Java programs\n" +
            "• Contains JVM and standard libraries\n" +
            "• If you only want to run Java apps, install JRE\n\n" +
            "3️⃣ JDK (Java Development Kit)\n" +
            "• JDK = JRE + Development Tools\n" +
            "• Needed to DEVELOP Java programs\n" +
            "• Contains compiler (javac), debugger, and other tools\n" +
            "• If you want to write Java code, install JDK\n\n" +
            "📊 Relationship:\n" +
            "JDK ⊃ JRE ⊃ JVM\n" +
            "(JDK contains JRE, JRE contains JVM)\n\n" +
            "🔄 How Java Works:\n" +
            "1. Write code (.java file)\n" +
            "2. Compile with javac → bytecode (.class file)\n" +
            "3. JVM executes bytecode\n" +
            "4. Output displayed\n\n" +
            "Remember: JDK for developers, JRE for users, JVM for execution!",
            "",
            "// Java execution process\n// Step 1: Write code\npublic class Hello {\n    public static void main(String[] args) {\n        System.out.println(\"Understanding JVM!\");\n    }\n}\n\n// Step 2: Compile - javac Hello.java → Hello.class\n// Step 3: Run - java Hello\n// Step 4: JVM executes bytecode",
            "Understanding JVM!",
            "🔧 JVM is platform dependent, but Java is platform independent.\n" +
            "🔧 Bytecode is platform independent intermediate code.\n" +
            "🔧 JIT compiler improves performance by compiling bytecode to native code.",
            "Q: What is needed to develop Java programs?\nAns: JDK (Java Development Kit)"
            , 4
        );
        lesson4.setExplanationHinglish(
            "🔧 Java Ke Core Components Ko Samajhna\n\n" +
            "Java samajhne ke liye aapko teen important components pata hone chahiye:\n\n" +
            "1️⃣ JVM (Java Virtual Machine)\n" +
            "• Java ki platform independence ka dil\n" +
            "• Java bytecode ko execute karta hai\n" +
            "• Runtime environment provide karta hai\n" +
            "• Har platform ke liye alag JVM (Windows JVM, Linux JVM)\n" +
            "• 'Write Once, Run Anywhere' ko possible banata hai\n\n" +
            "2️⃣ JRE (Java Runtime Environment)\n" +
            "• JRE = JVM + Library Classes\n" +
            "• Java programs CHALANE ke liye chahiye\n" +
            "• JVM aur standard libraries contain karta hai\n" +
            "• Agar sirf Java apps run karne hain, JRE install karo\n\n" +
            "3️⃣ JDK (Java Development Kit)\n" +
            "• JDK = JRE + Development Tools\n" +
            "• Java programs BANANA ke liye chahiye\n" +
            "• Compiler (javac), debugger, aur other tools contain karta hai\n" +
            "• Agar Java code likhna hai, JDK install karo\n\n" +
            "📊 Relationship:\n" +
            "JDK ⊃ JRE ⊃ JVM\n" +
            "(JDK mein JRE hai, JRE mein JVM hai)\n\n" +
            "🔄 Java Kaise Kaam Karta Hai:\n" +
            "1. Code likho (.java file)\n" +
            "2. javac se compile karo → bytecode (.class file)\n" +
            "3. JVM bytecode ko execute kare\n" +
            "4. Output display ho\n\n" +
            "Yaad Rakho: JDK developers ke liye, JRE users ke liye, JVM execution ke liye!"
        );
        lessons.add(lesson4);
        
        Lesson lesson5 = new Lesson(
            "java_lesson_5", "java_chapter_1", "First Java Program",
            // English explanation
            "🎉 Let's Write Our First Java Program!\n\n" +
            "We'll create a simple program that prints 'Hello, World!' on the screen.\n\n" +
            "📝 Program Structure:\n\n" +
            "1. class Declaration\n" +
            "   • Every Java program must have at least one class\n" +
            "   • Class name must match filename\n" +
            "   • Example: HelloWorld.java must have 'class HelloWorld'\n\n" +
            "2. main() Method\n" +
            "   • Entry point of every Java program\n" +
            "   • Syntax: public static void main(String[] args)\n" +
            "   • Program execution starts from main()\n\n" +
            "3. System.out.println()\n" +
            "   • Used to print output on console\n" +
            "   • System: built-in class\n" +
            "   • out: output stream\n" +
            "   • println: print line (adds new line)\n\n" +
            "🔍 Understanding Each Part:\n" +
            "• public: accessible from anywhere\n" +
            "• static: can be called without creating object\n" +
            "• void: doesn't return any value\n" +
            "• main: method name (fixed)\n" +
            "• String[] args: command line arguments\n\n" +
            "💻 How to Run:\n" +
            "1. Save as HelloWorld.java\n" +
            "2. Compile: javac HelloWorld.java\n" +
            "3. Run: java HelloWorld\n" +
            "4. Output: Hello, World!",
            "public class ClassName {\n    public static void main(String[] args) {\n        // Your code here\n    }\n}",
            "public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n        System.out.println(\"Welcome to Java Programming\");\n        System.out.println(\"Let's learn together!\");\n    }\n}",
            "Hello, World!\nWelcome to Java Programming\nLet's learn together!",
            "✨ Class name and filename must be exactly same (case-sensitive).\n" +
            "✨ Java is case-sensitive: 'Main' and 'main' are different.\n" +
            "✨ Every statement must end with semicolon (;).",
            "Q: What is the entry point of a Java program?\nAns: main() method"
            , 5
        );
        lesson5.setExplanationHinglish(
            "🎉 Chalo Apna Pehla Java Program Likhte Hain!\n\n" +
            "Hum ek simple program banayenge jo screen par 'Hello, World!' print karega.\n\n" +
            "📝 Program Structure:\n\n" +
            "1. class Declaration\n" +
            "   • Har Java program mein kam se kam ek class honi chahiye\n" +
            "   • Class name aur filename same hone chahiye\n" +
            "   • Example: HelloWorld.java mein 'class HelloWorld' hona chahiye\n\n" +
            "2. main() Method\n" +
            "   • Har Java program ka entry point\n" +
            "   • Syntax: public static void main(String[] args)\n" +
            "   • Program main() se start hota hai\n\n" +
            "3. System.out.println()\n" +
            "   • Console par output print karne ke liye\n" +
            "   • System: built-in class\n" +
            "   • out: output stream\n" +
            "   • println: print line (new line add karta hai)\n\n" +
            "🔍 Har Part Ko Samajhna:\n" +
            "• public: kahin se bhi accessible\n" +
            "• static: object banaye bina call ho sakta hai\n" +
            "• void: koi value return nahi karta\n" +
            "• main: method ka naam (fixed)\n" +
            "• String[] args: command line arguments\n\n" +
            "💻 Kaise Run Karein:\n" +
            "1. HelloWorld.java ke naam se save karo\n" +
            "2. Compile: javac HelloWorld.java\n" +
            "3. Run: java HelloWorld\n" +
            "4. Output: Hello, World!"
        );
        lessons.add(lesson5);
        
        return lessons;
    }
    
    // Java Module 2: Data Types & Variables
    private List<Lesson> getJavaModule2Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        // Lesson 1: Primitive Data Types
        Lesson lesson1 = new Lesson(
            "java_lesson_6", "java_chapter_2", "Primitive Data Types",
            "📊 Understanding Java Data Types\n\n" +
            "Data types specify the size and type of values that can be stored in a variable.\n\n" +
            "🔢 8 Primitive Data Types in Java:\n\n" +
            "1️⃣ byte (1 byte): -128 to 127\n" +
            "2️⃣ short (2 bytes): -32,768 to 32,767\n" +
            "3️⃣ int (4 bytes): -2 billion to 2 billion\n" +
            "4️⃣ long (8 bytes): Very large numbers\n" +
            "5️⃣ float (4 bytes): Decimal numbers (6-7 digits)\n" +
            "6️⃣ double (8 bytes): Decimal numbers (15 digits)\n" +
            "7️⃣ char (2 bytes): Single character\n" +
            "8️⃣ boolean (1 bit): true or false\n\n" +
            "💡 Most commonly used: int, double, char, boolean",
            "dataType variableName = value;",
            "public class DataTypes {\n    public static void main(String[] args) {\n        byte age = 25;\n        int salary = 50000;\n        double price = 99.99;\n        char grade = 'A';\n        boolean isActive = true;\n        \n        System.out.println(\"Age: \" + age);\n        System.out.println(\"Salary: \" + salary);\n        System.out.println(\"Price: \" + price);\n        System.out.println(\"Grade: \" + grade);\n        System.out.println(\"Active: \" + isActive);\n    }\n}",
            "Age: 25\nSalary: 50000\nPrice: 99.99\nGrade: A\nActive: true",
            "✨ int is default for integers, double for decimals.\n" +
            "✨ Use L suffix for long: 1000000L\n" +
            "✨ Use F suffix for float: 3.14F",
            "Q: Which data type stores true/false?\nAns: boolean", 1
        );
        lesson1.setExplanationHinglish(
            "📊 Java Data Types Ko Samajhna\n\n" +
            "Data types specify karte hain ki variable mein kitna aur kis type ka data store ho sakta hai.\n\n" +
            "🔢 Java Mein 8 Primitive Data Types:\n\n" +
            "1️⃣ byte (1 byte): -128 se 127 tak\n" +
            "2️⃣ short (2 bytes): -32,768 se 32,767 tak\n" +
            "3️⃣ int (4 bytes): -2 billion se 2 billion tak\n" +
            "4️⃣ long (8 bytes): Bahut bade numbers\n" +
            "5️⃣ float (4 bytes): Decimal numbers (6-7 digits)\n" +
            "6️⃣ double (8 bytes): Decimal numbers (15 digits)\n" +
            "7️⃣ char (2 bytes): Ek single character\n" +
            "8️⃣ boolean (1 bit): true ya false\n\n" +
            "💡 Sabse zyada use hone wale: int, double, char, boolean"
        );
        lessons.add(lesson1);
        
        // Lesson 2: Variables
        Lesson lesson2 = new Lesson(
            "java_lesson_7", "java_chapter_2", "Variables in Java",
            "📦 What are Variables?\n\n" +
            "A variable is a container that holds data. Think of it as a labeled box where you store values.\n\n" +
            "📝 Variable Declaration:\n" +
            "dataType variableName;\n\n" +
            "📝 Variable Initialization:\n" +
            "variableName = value;\n\n" +
            "📝 Declaration + Initialization:\n" +
            "dataType variableName = value;\n\n" +
            "🎯 Variable Naming Rules:\n" +
            "✅ Can start with letter, _, or $\n" +
            "✅ Can contain letters, digits, _, $\n" +
            "✅ Case-sensitive (age ≠ Age)\n" +
            "❌ Cannot use keywords (int, class, etc.)\n" +
            "❌ Cannot start with digit\n\n" +
            "🌟 Best Practices:\n" +
            "• Use meaningful names (age, not a)\n" +
            "• Use camelCase (firstName, not firstname)\n" +
            "• Start with lowercase letter",
            "int age = 25;\nString name = \"John\";",
            "public class Variables {\n    public static void main(String[] args) {\n        // Declaration\n        int age;\n        \n        // Initialization\n        age = 20;\n        \n        // Declaration + Initialization\n        String name = \"Alice\";\n        double height = 5.6;\n        \n        // Multiple variables\n        int x = 10, y = 20, z = 30;\n        \n        System.out.println(\"Name: \" + name);\n        System.out.println(\"Age: \" + age);\n        System.out.println(\"Height: \" + height);\n        System.out.println(\"Sum: \" + (x + y + z));\n    }\n}",
            "Name: Alice\nAge: 20\nHeight: 5.6\nSum: 60",
            "💡 Always initialize variables before using them.\n" +
            "💡 Choose descriptive variable names.\n" +
            "💡 Java is case-sensitive: age ≠ Age",
            "Q: Can a variable name start with a number?\nAns: No", 2
        );
        lesson2.setExplanationHinglish(
            "📦 Variables Kya Hote Hain?\n\n" +
            "Variable ek container hai jo data hold karta hai. Isse ek labeled box ki tarah samjho jahan aap values store karte ho.\n\n" +
            "📝 Variable Declaration:\n" +
            "dataType variableName;\n\n" +
            "📝 Variable Initialization:\n" +
            "variableName = value;\n\n" +
            "📝 Declaration + Initialization:\n" +
            "dataType variableName = value;\n\n" +
            "🎯 Variable Naming Ke Rules:\n" +
            "✅ Letter, _, ya $ se start ho sakta hai\n" +
            "✅ Letters, digits, _, $ contain kar sakta hai\n" +
            "✅ Case-sensitive hai (age ≠ Age)\n" +
            "❌ Keywords use nahi kar sakte (int, class, etc.)\n" +
            "❌ Digit se start nahi ho sakta\n\n" +
            "🌟 Best Practices:\n" +
            "• Meaningful names use karo (age, not a)\n" +
            "• camelCase use karo (firstName, not firstname)\n" +
            "• Lowercase letter se start karo"
        );
        lessons.add(lesson2);
        
        // Lesson 3: Type Casting
        Lesson lesson3 = new Lesson(
            "java_lesson_8", "java_chapter_2", "Type Casting",
            "🔄 Type Casting in Java\n\n" +
            "Converting one data type to another is called type casting.\n\n" +
            "📤 Widening Casting (Automatic):\n" +
            "Smaller type → Larger type\n" +
            "byte → short → int → long → float → double\n" +
            "No data loss!\n\n" +
            "📥 Narrowing Casting (Manual):\n" +
            "Larger type → Smaller type\n" +
            "double → float → long → int → short → byte\n" +
            "May cause data loss!\n\n" +
            "Syntax: (targetType) value\n\n" +
            "Example:\n" +
            "int num = (int) 9.8; // 9.8 becomes 9",
            "// Widening\nint i = 100;\ndouble d = i;\n\n// Narrowing\ndouble d = 9.8;\nint i = (int) d;",
            "public class TypeCasting {\n    public static void main(String[] args) {\n        // Widening Casting (Automatic)\n        int myInt = 9;\n        double myDouble = myInt;\n        System.out.println(\"Int: \" + myInt);\n        System.out.println(\"Double: \" + myDouble);\n        \n        // Narrowing Casting (Manual)\n        double price = 99.99;\n        int roundedPrice = (int) price;\n        System.out.println(\"Original: \" + price);\n        System.out.println(\"Rounded: \" + roundedPrice);\n        \n        // char to int\n        char letter = 'A';\n        int asciiValue = letter;\n        System.out.println(\"Char: \" + letter);\n        System.out.println(\"ASCII: \" + asciiValue);\n    }\n}",
            "Int: 9\nDouble: 9.0\nOriginal: 99.99\nRounded: 99\nChar: A\nASC II: 65",
            "⚠️ Narrowing casting may lose data (9.8 → 9).\n" +
            "⚠️ Always use parentheses for manual casting.\n" +
            "💡 char can be cast to int to get ASCII value.",
            "Q: What is automatic type casting called?\nAns: Widening Casting", 3
        );
        lesson3.setExplanationHinglish(
            "🔄 Java Mein Type Casting\n\n" +
            "Ek data type ko dusre mein convert karna type casting kehlata hai.\n\n" +
            "📤 Widening Casting (Automatic):\n" +
            "Chhota type → Bada type\n" +
            "byte → short → int → long → float → double\n" +
            "Koi data loss nahi!\n\n" +
            "📥 Narrowing Casting (Manual):\n" +
            "Bada type → Chhota type\n" +
            "double → float → long → int → short → byte\n" +
            "Data loss ho sakta hai!\n\n" +
            "Syntax: (targetType) value\n\n" +
            "Example:\n" +
            "int num = (int) 9.8; // 9.8 ban jayega 9"
        );
        lessons.add(lesson3);
        
        // Lesson 4: Constants (final keyword)
        Lesson lesson4 = new Lesson(
            "java_lesson_9", "java_chapter_2", "Constants (final keyword)",
            "🔒 Constants in Java\n\n" +
            "A constant is a variable whose value cannot be changed once assigned.\n\n" +
            "📌 Using 'final' Keyword:\n" +
            "final dataType VARIABLE_NAME = value;\n\n" +
            "🎯 Why Use Constants?\n" +
            "• Prevent accidental changes\n" +
            "• Make code more readable\n" +
            "• Easy to maintain (change in one place)\n\n" +
            "📝 Naming Convention:\n" +
            "• Use UPPERCASE letters\n" +
            "• Separate words with underscore\n" +
            "• Example: MAX_VALUE, PI, TAX_RATE\n\n" +
            "Example:\n" +
            "final double PI = 3.14159;\n" +
            "final int MAX_AGE = 100;",
            "final dataType NAME = value;",
            "public class Constants {\n    public static void main(String[] args) {\n        // Declaring constants\n        final double PI = 3.14159;\n        final int MAX_STUDENTS = 50;\n        final String SCHOOL_NAME = \"ABC School\";\n        \n        // Using constants\n        double radius = 5.0;\n        double area = PI * radius * radius;\n        \n        System.out.println(\"PI: \" + PI);\n        System.out.println(\"Max Students: \" + MAX_STUDENTS);\n        System.out.println(\"School: \" + SCHOOL_NAME);\n        System.out.println(\"Circle Area: \" + area);\n        \n        // This will cause error:\n        // PI = 3.14; // Cannot assign to final variable\n    }\n}",
            "PI: 3.14159\nMax Students: 50\nSchool: ABC School\nCircle Area: 78.53975",
            "🔐 final variables must be initialized when declared.\n" +
            "🔐 Cannot change value after initialization.\n" +
            "💡 Use UPPERCASE for constant names.",
            "Q: Which keyword creates a constant?\nAns: final", 4
        );
        lesson4.setExplanationHinglish(
            "🔒 Java Mein Constants\n\n" +
            "Constant ek aisa variable hai jiska value ek baar assign karne ke baad change nahi ho sakta.\n\n" +
            "📌 'final' Keyword Ka Use:\n" +
            "final dataType VARIABLE_NAME = value;\n\n" +
            "🎯 Constants Kyu Use Karein?\n" +
            "• Galti se changes nahi ho sakte\n" +
            "• Code zyada readable banta hai\n" +
            "• Maintain karna easy (ek jagah change karo)\n\n" +
            "📝 Naming Convention:\n" +
            "• UPPERCASE letters use karo\n" +
            "• Words ko underscore se separate karo\n" +
            "• Example: MAX_VALUE, PI, TAX_RATE\n\n" +
            "Example:\n" +
            "final double PI = 3.14159;\n" +
            "final int MAX_AGE = 100;"
        );
        lessons.add(lesson4);
        
        // Lesson 5: Input/Output
        Lesson lesson5 = new Lesson(
            "java_lesson_10", "java_chapter_2", "Input and Output",
            "⌨️ Taking User Input in Java\n\n" +
            "Java uses Scanner class to read input from user.\n\n" +
            "📥 Steps to Use Scanner:\n" +
            "1. Import: import java.util.Scanner;\n" +
            "2. Create object: Scanner sc = new Scanner(System.in);\n" +
            "3. Read input: sc.nextInt(), sc.nextLine(), etc.\n\n" +
            "🔧 Scanner Methods:\n" +
            "• nextInt() - Read integer\n" +
            "• nextDouble() - Read decimal\n" +
            "• nextLine() - Read string\n" +
            "• next() - Read single word\n" +
            "• nextBoolean() - Read true/false\n\n" +
            "📤 Output Methods:\n" +
            "• System.out.print() - Print without newline\n" +
            "• System.out.println() - Print with newline\n" +
            "• System.out.printf() - Formatted output",
            "import java.util.Scanner;\nScanner sc = new Scanner(System.in);\nint num = sc.nextInt();",
            "import java.util.Scanner;\n\npublic class InputOutput {\n    public static void main(String[] args) {\n        Scanner scanner = new Scanner(System.in);\n        \n        System.out.print(\"Enter your name: \");\n        String name = scanner.nextLine();\n        \n        System.out.print(\"Enter your age: \");\n        int age = scanner.nextInt();\n        \n        System.out.print(\"Enter your height: \");\n        double height = scanner.nextDouble();\n        \n        System.out.println(\"\\n=== Your Details ===\");\n        System.out.println(\"Name: \" + name);\n        System.out.println(\"Age: \" + age);\n        System.out.println(\"Height: \" + height);\n        \n        scanner.close();\n    }\n}",
            "Enter your name: John\nEnter your age: 25\nEnter your height: 5.9\n\n=== Your Details ===\nName: John\nAge: 25\nHeight: 5.9",
            "📌 Always import java.util.Scanner\n" +
            "📌 Close scanner with scanner.close()\n" +
            "💡 Use nextLine() after nextInt() to avoid issues",
            "Q: Which class is used for input?\nAns: Scanner", 5
        );
        lesson5.setExplanationHinglish(
            "⌨️ Java Mein User Input Lena\n\n" +
            "Java mein Scanner class use karke user se input lete hain.\n\n" +
            "📥 Scanner Use Karne Ke Steps:\n" +
            "1. Import karo: import java.util.Scanner;\n" +
            "2. Object banao: Scanner sc = new Scanner(System.in);\n" +
            "3. Input padho: sc.nextInt(), sc.nextLine(), etc.\n\n" +
            "🔧 Scanner Ke Methods:\n" +
            "• nextInt() - Integer padhta hai\n" +
            "• nextDouble() - Decimal padhta hai\n" +
            "• nextLine() - String padhta hai\n" +
            "• next() - Single word padhta hai\n" +
            "• nextBoolean() - true/false padhta hai\n\n" +
            "📤 Output Methods:\n" +
            "• System.out.print() - Newline ke bina print\n" +
            "• System.out.println() - Newline ke saath print\n" +
            "• System.out.printf() - Formatted output"
        );
        lessons.add(lesson5);
        
        return lessons;
    }

    
    // Java Module 3: Operators
    private List<Lesson> getJavaModule3Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        lessons.add(createJavaLesson(11, 3, "Arithmetic Operators",
            "➕ Arithmetic Operators in Java\n\n" +
            "Used to perform mathematical operations.\n\n" +
            "Operators:\n" +
            "+ (Addition)\n" +
            "- (Subtraction)\n" +
            "* (Multiplication)\n" +
            "/ (Division)\n" +
            "% (Modulus - Remainder)",
            "int a = 10, b = 3;\nint sum = a + b;\nint mod = a % b;",
            "public class Arithmetic {\n    public static void main(String[] args) {\n        int a = 10, b = 3;\n        System.out.println(\"Sum: \" + (a + b));\n        System.out.println(\"Difference: \" + (a - b));\n        System.out.println(\"Product: \" + (a * b));\n        System.out.println(\"Quotient: \" + (a / b));\n        System.out.println(\"Remainder: \" + (a % b));\n    }\n}",
            "Sum: 13\nDifference: 7\nProduct: 30\nQuotient: 3\nRemainder: 1",
            "💡 Division of integers gives integer result.\n💡 Use % to find remainder.",
            "Q: What does % operator do?\nAns: Finds remainder",
            "➕ Java Mein Arithmetic Operators\n\nMathematical operations karne ke liye use hote hain.\n\nOperators:\n+ (Jodna)\n- (Ghatana)\n* (Guna)\n/ (Bhag)\n% (Modulus - Sheshfal)"
        ));
        
        lessons.add(createJavaLesson(12, 3, "Relational Operators",
            "🔍 Relational (Comparison) Operators\n\n" +
            "Compare two values and return boolean.\n\n" +
            "== (Equal to)\n" +
            "!= (Not equal to)\n" +
            "> (Greater than)\n" +
            "< (Less than)\n" +
            ">= (Greater than or equal)\n" +
            "<= (Less than or equal)",
            "int a = 5, b = 10;\nboolean result = a < b;",
            "public class Relational {\n    public static void main(String[] args) {\n        int a = 5, b = 10;\n        System.out.println(\"a == b: \" + (a == b));\n        System.out.println(\"a != b: \" + (a != b));\n        System.out.println(\"a > b: \" + (a > b));\n        System.out.println(\"a < b: \" + (a < b));\n    }\n}",
            "a == b: false\na != b: true\na > b: false\na < b: true",
            "⚠️ Use == for comparison, = for assignment.\n💡 Result is always boolean.",
            "Q: What is the result type of relational operators?\nAns: boolean",
            "🔍 Relational Operators\n\nDo values ko compare karte hain aur boolean return karte hain.\n\n== (Barabar hai)\n!= (Barabar nahi hai)\n> (Bada hai)\n< (Chhota hai)\n>= (Bada ya barabar)\n<= (Chhota ya barabar)"
        ));
        
        lessons.add(createJavaLesson(13, 3, "Logical Operators",
            "🧠 Logical Operators\n\n" +
            "Combine multiple conditions.\n\n" +
            "&& (AND) - Both must be true\n" +
            "|| (OR) - At least one must be true\n" +
            "! (NOT) - Reverses the condition",
            "boolean a = true, b = false;\nboolean result = a && b;",
            "public class Logical {\n    public static void main(String[] args) {\n        boolean a = true, b = false;\n        System.out.println(\"a && b: \" + (a && b));\n        System.out.println(\"a || b: \" + (a || b));\n        System.out.println(\"!a: \" + (!a));\n    }\n}",
            "a && b: false\na || b: true\n!a: false",
            "💡 && requires both conditions true.\n💡 || requires at least one true.\n💡 ! reverses the boolean value.",
            "Q: Which operator reverses boolean?\nAns: ! (NOT)",
            "🧠 Logical Operators\n\nKayi conditions ko combine karte hain.\n\n&& (AND) - Dono true hone chahiye\n|| (OR) - Kam se kam ek true ho\n! (NOT) - Condition ko ulta kar deta hai"
        ));
        
        lessons.add(createJavaLesson(14, 3, "Assignment & Increment Operators",
            "📝 Assignment & Increment Operators\n\n" +
            "Assignment:\n" +
            "= (Assign)\n" +
            "+= (Add and assign)\n" +
            "-= (Subtract and assign)\n\n" +
            "Increment/Decrement:\n" +
            "++ (Increment by 1)\n" +
            "-- (Decrement by 1)\n" +
            "++a (Pre-increment)\n" +
            "a++ (Post-increment)",
            "int a = 5;\na += 3; // a = a + 3\na++; // a = a + 1",
            "public class Assignment {\n    public static void main(String[] args) {\n        int a = 5;\n        a += 3;\n        System.out.println(\"After +=: \" + a);\n        \n        int b = 10;\n        System.out.println(\"b++: \" + (b++));\n        System.out.println(\"After: \" + b);\n        \n        int c = 10;\n        System.out.println(\"++c: \" + (++c));\n    }\n}",
            "After +=: 8\nb++: 10\nAfter: 11\n++c: 11",
            "💡 a++ uses value then increments.\n💡 ++a increments then uses value.\n💡 += is shorthand for a = a + value.",
            "Q: What is the difference between ++a and a++?\nAns: ++a increments first, a++ increments after",
            "📝 Assignment & Increment Operators\n\nAssignment:\n= (Assign karna)\n+= (Jod kar assign)\n-= (Ghata kar assign)\n\nIncrement/Decrement:\n++ (1 se badhana)\n-- (1 se ghatana)\n++a (Pehle badha, phir use)\na++ (Pehle use, phir badha)"
        ));
        
        return lessons;
    }
    
    // Java Module 4: Control Statements
    private List<Lesson> getJavaModule4Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        lessons.add(createJavaLesson(15, 4, "if Statement",
            "🔀 if Statement\n\n" +
            "Executes code only if condition is true.\n\n" +
            "Syntax:\n" +
            "if (condition) {\n" +
            "    // code\n" +
            "}",
            "if (condition) {\n    // code\n}",
            "public class IfStatement {\n    public static void main(String[] args) {\n        int age = 18;\n        \n        if (age >= 18) {\n            System.out.println(\"You can vote!\");\n        }\n        \n        int marks = 85;\n        if (marks > 80) {\n            System.out.println(\"Excellent!\");\n        }\n    }\n}",
            "You can vote!\nExcellent!",
            "✅ Condition must be boolean.\n✅ Use curly braces for multiple statements.\n💡 Code executes only if condition is true.",
            "Q: What type must the if condition be?\nAns: boolean",
            "🔀 if Statement\n\nCode tab chalata hai jab condition true ho.\n\nSyntax:\nif (condition) {\n    // code\n}"
        ));
        
        lessons.add(createJavaLesson(16, 4, "if-else Statement",
            "🔀 if-else Statement\n\n" +
            "Executes one block if true, another if false.\n\n" +
            "Syntax:\n" +
            "if (condition) {\n" +
            "    // code if true\n" +
            "} else {\n" +
            "    // code if false\n" +
            "}",
            "if (condition) {\n    // true block\n} else {\n    // false block\n}",
            "public class IfElse {\n    public static void main(String[] args) {\n        int number = 7;\n        \n        if (number % 2 == 0) {\n            System.out.println(\"Even number\");\n        } else {\n            System.out.println(\"Odd number\");\n        }\n        \n        int age = 15;\n        if (age >= 18) {\n            System.out.println(\"Adult\");\n        } else {\n            System.out.println(\"Minor\");\n        }\n    }\n}",
            "Odd number\nMinor",
            "💡 Either if block OR else block executes, never both.\n💡 else is optional.\n✅ Good for binary decisions.",
            "Q: Can both if and else blocks execute?\nAns: No, only one",
            "🔀 if-else Statement\n\nAgar condition true hai toh ek block, false hai toh dusra block chalega.\n\nSyntax:\nif (condition) {\n    // true wala code\n} else {\n    // false wala code\n}"
        ));
        
        lessons.add(createJavaLesson(17, 4, "else-if Ladder",
            "🪜 else-if Ladder\n\n" +
            "Check multiple conditions in sequence.\n\n" +
            "Syntax:\n" +
            "if (condition1) {\n" +
            "    // code1\n" +
            "} else if (condition2) {\n" +
            "    // code2\n" +
            "} else {\n" +
            "    // default code\n" +
            "}",
            "if (cond1) {...}\nelse if (cond2) {...}\nelse {...}",
            "public class ElseIf {\n    public static void main(String[] args) {\n        int marks = 75;\n        \n        if (marks >= 90) {\n            System.out.println(\"Grade: A\");\n        } else if (marks >= 75) {\n            System.out.println(\"Grade: B\");\n        } else if (marks >= 60) {\n            System.out.println(\"Grade: C\");\n        } else {\n            System.out.println(\"Grade: F\");\n        }\n    }\n}",
            "Grade: B",
            "💡 Conditions checked from top to bottom.\n💡 First true condition executes, rest are skipped.\n✅ Use for multiple exclusive conditions.",
            "Q: How many blocks execute in else-if ladder?\nAns: Only one",
            "🪜 else-if Ladder\n\nKayi conditions ko sequence mein check karta hai.\n\nSyntax:\nif (condition1) {\n    // code1\n} else if (condition2) {\n    // code2\n} else {\n    // default code\n}"
        ));
        
        lessons.add(createJavaLesson(18, 4, "switch Statement",
            "🔄 switch Statement\n\n" +
            "Select one of many code blocks to execute.\n\n" +
            "Syntax:\n" +
            "switch (variable) {\n" +
            "    case value1:\n" +
            "        // code\n" +
            "        break;\n" +
            "    case value2:\n" +
            "        // code\n" +
            "        break;\n" +
            "    default:\n" +
            "        // default code\n" +
            "}",
            "switch (day) {\n    case 1:\n        // Monday\n        break;\n    default:\n        // Other\n}",
            "public class Switch {\n    public static void main(String[] args) {\n        int day = 3;\n        \n        switch (day) {\n            case 1:\n                System.out.println(\"Monday\");\n                break;\n            case 2:\n                System.out.println(\"Tuesday\");\n                break;\n            case 3:\n                System.out.println(\"Wednesday\");\n                break;\n            default:\n                System.out.println(\"Other day\");\n        }\n    }\n}",
            "Wednesday",
            "⚠️ Don't forget break statement!\n💡 default is optional.\n💡 Works with int, char, String, enum.",
            "Q: What happens if you forget break?\nAns: Fall-through to next case",
            "🔄 switch Statement\n\nKayi code blocks mein se ek ko select karta hai.\n\nSyntax:\nswitch (variable) {\n    case value1:\n        // code\n        break;\n    default:\n        // default code\n}"
        ));
        
        return lessons;
    }
    
    // Java Module 5: Loops
    private List<Lesson> getJavaModule5Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        lessons.add(createJavaLesson(19, 5, "for Loop",
            "🔁 for Loop\n\n" +
            "Repeat code a specific number of times.\n\n" +
            "Syntax:\n" +
            "for (initialization; condition; update) {\n" +
            "    // code\n" +
            "}\n\n" +
            "Parts:\n" +
            "1. Initialization: Start value\n" +
            "2. Condition: When to stop\n" +
            "3. Update: Change after each iteration",
            "for (int i = 0; i < 5; i++) {\n    System.out.println(i);\n}",
            "public class ForLoop {\n    public static void main(String[] args) {\n        // Print 1 to 5\n        for (int i = 1; i <= 5; i++) {\n            System.out.println(i);\n        }\n        \n        // Print even numbers\n        for (int i = 2; i <= 10; i += 2) {\n            System.out.println(i);\n        }\n    }\n}",
            "1\n2\n3\n4\n5\n2\n4\n6\n8\n10",
            "💡 Best when you know iteration count.\n💡 i++ increments after each loop.\n✅ Can use any variable name, not just i.",
            "Q: Which loop is best for known iterations?\nAns: for loop",
            "🔁 for Loop\n\nCode ko specific baar repeat karta hai.\n\nSyntax:\nfor (initialization; condition; update) {\n    // code\n}\n\nParts:\n1. Initialization: Shuru ki value\n2. Condition: Kab rukna hai\n3. Update: Har baar baad mein change"
        ));
        
        lessons.add(createJavaLesson(20, 5, "while Loop",
            "🔄 while Loop\n\n" +
            "Repeat code while condition is true.\n\n" +
            "Syntax:\n" +
            "while (condition) {\n" +
            "    // code\n" +
            "    // update\n" +
            "}\n\n" +
            "Checks condition BEFORE executing.",
            "while (condition) {\n    // code\n}",
            "public class WhileLoop {\n    public static void main(String[] args) {\n        int i = 1;\n        \n        while (i <= 5) {\n            System.out.println(i);\n            i++;\n        }\n        \n        // Sum of first 10 numbers\n        int sum = 0, n = 1;\n        while (n <= 10) {\n            sum += n;\n            n++;\n        }\n        System.out.println(\"Sum: \" + sum);\n    }\n}",
            "1\n2\n3\n4\n5\nSum: 55",
            "💡 Condition checked before each iteration.\n⚠️ Don't forget to update loop variable!\n💡 May not execute if condition is false initially.",
            "Q: When is while condition checked?\nAns: Before each iteration",
            "🔄 while Loop\n\nJab tak condition true hai tab tak code repeat hota hai.\n\nSyntax:\nwhile (condition) {\n    // code\n    // update\n}\n\nCondition PEHLE check hoti hai."
        ));
        
        lessons.add(createJavaLesson(21, 5, "do-while Loop",
            "🔁 do-while Loop\n\n" +
            "Execute code first, then check condition.\n\n" +
            "Syntax:\n" +
            "do {\n" +
            "    // code\n" +
            "} while (condition);\n\n" +
            "Checks condition AFTER executing.\n" +
            "Executes at least once!",
            "do {\n    // code\n} while (condition);",
            "public class DoWhile {\n    public static void main(String[] args) {\n        int i = 1;\n        \n        do {\n            System.out.println(i);\n            i++;\n        } while (i <= 5);\n        \n        // Executes at least once\n        int x = 10;\n        do {\n            System.out.println(\"Executed!\");\n        } while (x < 5); // false but still runs once\n    }\n}",
            "1\n2\n3\n4\n5\nExecuted!",
            "✅ Executes at least once guaranteed.\n💡 Condition checked after execution.\n💡 Good for menu-driven programs.",
            "Q: How many times does do-while execute minimum?\nAns: At least once",
            "🔁 do-while Loop\n\nPehle code chalao, phir condition check karo.\n\nSyntax:\ndo {\n    // code\n} while (condition);\n\nCondition BAAD mein check hoti hai.\nKam se kam ek baar zaroor chalega!"
        ));
        
        lessons.add(createJavaLesson(22, 5, "break and continue",
            "🛑 break and continue\n\n" +
            "Control loop execution.\n\n" +
            "break:\n" +
            "• Exits the loop immediately\n" +
            "• Skips remaining iterations\n\n" +
            "continue:\n" +
            "• Skips current iteration\n" +
            "• Continues with next iteration",
            "break; // exit loop\ncontinue; // skip to next",
            "public class BreakContinue {\n    public static void main(String[] args) {\n        // break example\n        for (int i = 1; i <= 10; i++) {\n            if (i == 5) break;\n            System.out.print(i + \" \");\n        }\n        System.out.println();\n        \n        // continue example\n        for (int i = 1; i <= 5; i++) {\n            if (i == 3) continue;\n            System.out.print(i + \" \");\n        }\n    }\n}",
            "1 2 3 4\n1 2 4 5",
            "💡 break exits the entire loop.\n💡 continue skips only current iteration.\n⚠️ Use carefully to avoid confusion.",
            "Q: What does break do in a loop?\nAns: Exits the loop immediately",
            "🛑 break aur continue\n\nLoop execution ko control karte hain.\n\nbreak:\n• Loop se turant bahar nikal jao\n• Baaki iterations skip ho jate hain\n\ncontinue:\n• Current iteration skip karo\n• Agle iteration pe jao"
        ));
        
        return lessons;
    }
    
    // Java Module 6: Arrays & Strings
    private List<Lesson> getJavaModule6Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        lessons.add(createJavaLesson(23, 6, "Arrays in Java",
            "📊 Arrays in Java\n\n" +
            "Array is a collection of similar type elements.\n\n" +
            "Declaration:\n" +
            "dataType[] arrayName;\n\n" +
            "Initialization:\n" +
            "arrayName = new dataType[size];\n\n" +
            "Combined:\n" +
            "int[] numbers = new int[5];\n" +
            "int[] nums = {1, 2, 3, 4, 5};",
            "int[] arr = new int[5];\nint[] nums = {1,2,3};",
            "public class Arrays {\n    public static void main(String[] args) {\n        int[] numbers = {10, 20, 30, 40, 50};\n        \n        System.out.println(\"First: \" + numbers[0]);\n        System.out.println(\"Length: \" + numbers.length);\n        \n        for (int i = 0; i < numbers.length; i++) {\n            System.out.println(numbers[i]);\n        }\n    }\n}",
            "First: 10\nLength: 5\n10\n20\n30\n40\n50",
            "💡 Array index starts from 0.\n💡 Use .length to get array size.\n⚠️ ArrayIndexOutOfBoundsException if index invalid.",
            "Q: What is the first index of array?\nAns: 0",
            "📊 Java Mein Arrays\n\nArray ek collection hai similar type elements ka.\n\nDeclaration:\ndataType[] arrayName;\n\nInitialization:\narrayName = new dataType[size];\n\nCombined:\nint[] numbers = new int[5];\nint[] nums = {1, 2, 3, 4, 5};"
        ));
        
        lessons.add(createJavaLesson(24, 6, "Multidimensional Arrays",
            "🎯 Multidimensional Arrays\n\n" +
            "Array of arrays (2D, 3D, etc.)\n\n" +
            "2D Array Declaration:\n" +
            "int[][] matrix = new int[3][3];\n\n" +
            "Initialization:\n" +
            "int[][] matrix = {\n" +
            "    {1, 2, 3},\n" +
            "    {4, 5, 6},\n" +
            "    {7, 8, 9}\n" +
            "};",
            "int[][] matrix = new int[3][3];",
            "public class MultiArray {\n    public static void main(String[] args) {\n        int[][] matrix = {\n            {1, 2, 3},\n            {4, 5, 6},\n            {7, 8, 9}\n        };\n        \n        for (int i = 0; i < 3; i++) {\n            for (int j = 0; j < 3; j++) {\n                System.out.print(matrix[i][j] + \" \");\n            }\n            System.out.println();\n        }\n    }\n}",
            "1 2 3\n4 5 6\n7 8 9",
            "💡 First index is row, second is column.\n💡 Use nested loops for 2D arrays.\n✅ Good for matrices, tables.",
            "Q: How to access 2D array element?\nAns: array[row][col]",
            "🎯 Multidimensional Arrays\n\nArray of arrays (2D, 3D, etc.)\n\n2D Array Declaration:\nint[][] matrix = new int[3][3];\n\nInitialization:\nint[][] matrix = {\n    {1, 2, 3},\n    {4, 5, 6}\n};"
        ));
        
        lessons.add(createJavaLesson(25, 6, "Strings in Java",
            "📝 Strings in Java\n\n" +
            "String is a sequence of characters.\n\n" +
            "Creation:\n" +
            "String name = \"Java\";\n" +
            "String s = new String(\"Hello\");\n\n" +
            "Strings are immutable (cannot be changed).\n\n" +
            "Common Methods:\n" +
            "• length() - Get length\n" +
            "• charAt(i) - Get character at index\n" +
            "• substring(start, end) - Extract part\n" +
            "• toUpperCase() - Convert to uppercase\n" +
            "• toLowerCase() - Convert to lowercase",
            "String str = \"Hello\";",
            "public class Strings {\n    public static void main(String[] args) {\n        String name = \"Java Programming\";\n        \n        System.out.println(\"Length: \" + name.length());\n        System.out.println(\"Char at 0: \" + name.charAt(0));\n        System.out.println(\"Substring: \" + name.substring(0, 4));\n        System.out.println(\"Uppercase: \" + name.toUpperCase());\n        System.out.println(\"Lowercase: \" + name.toLowerCase());\n    }\n}",
            "Length: 16\nChar at 0: J\nSubstring: Java\nUppercase: JAVA PROGRAMMING\nLowercase: java programming",
            "💡 Strings are objects, not primitive types.\n💡 Use equals() to compare strings, not ==.\n✅ String is immutable.",
            "Q: How to compare two strings?\nAns: Using equals() method",
            "📝 Java Mein Strings\n\nString characters ka sequence hai.\n\nCreation:\nString name = \"Java\";\nString s = new String(\"Hello\");\n\nStrings immutable hain (change nahi ho sakte).\n\nCommon Methods:\n• length() - Length nikalna\n• charAt(i) - Index par character\n• substring(start, end) - Part nikalna\n• toUpperCase() - Uppercase mein\n• toLowerCase() - Lowercase mein"
        ));
        
        lessons.add(createJavaLesson(26, 6, "String Methods",
            "🔧 Important String Methods\n\n" +
            "concat() - Join strings\n" +
            "equals() - Compare strings\n" +
            "equalsIgnoreCase() - Compare ignoring case\n" +
            "contains() - Check if contains substring\n" +
            "startsWith() - Check if starts with\n" +
            "endsWith() - Check if ends with\n" +
            "replace() - Replace characters\n" +
            "trim() - Remove whitespace\n" +
            "split() - Split into array",
            "str.equals(\"text\");\nstr.contains(\"sub\");",
            "public class StringMethods {\n    public static void main(String[] args) {\n        String s1 = \"Hello\";\n        String s2 = \"World\";\n        \n        System.out.println(s1.concat(\" \" + s2));\n        System.out.println(s1.equals(\"Hello\"));\n        System.out.println(s1.contains(\"ell\"));\n        System.out.println(s1.replace('l', 'L'));\n        \n        String text = \"  Java  \";\n        System.out.println(text.trim());\n    }\n}",
            "Hello World\ntrue\ntrue\nHeLLo\nJava",
            "✅ Use equals() not == for string comparison.\n💡 String methods return new string (immutable).\n💡 trim() removes leading/trailing spaces.",
            "Q: Which method joins two strings?\nAns: concat()",
            "🔧 Important String Methods\n\nconcat() - Strings jodna\nequals() - Strings compare karna\nequalsIgnoreCase() - Case ignore karke compare\ncontains() - Substring check karna\nstartsWith() - Shuru check karna\nendsWith() - End check karna\nreplace() - Characters replace karna\ntrim() - Whitespace hatana\nsplit() - Array mein split karna"
        ));
        
        lessons.add(createJavaLesson(27, 6, "StringBuilder",
            "🔨 StringBuilder in Java\n\n" +
            "Mutable alternative to String.\n\n" +
            "Why StringBuilder?\n" +
            "• Strings are immutable\n" +
            "• StringBuilder is mutable\n" +
            "• Better performance for modifications\n\n" +
            "Common Methods:\n" +
            "• append() - Add at end\n" +
            "• insert() - Add at position\n" +
            "• delete() - Remove characters\n" +
            "• reverse() - Reverse string\n" +
            "• toString() - Convert to String",
            "StringBuilder sb = new StringBuilder();",
            "public class StringBuilderEx {\n    public static void main(String[] args) {\n        StringBuilder sb = new StringBuilder(\"Hello\");\n        \n        sb.append(\" World\");\n        System.out.println(sb);\n        \n        sb.insert(5, \",\");\n        System.out.println(sb);\n        \n        sb.reverse();\n        System.out.println(sb);\n        \n        String result = sb.toString();\n    }\n}",
            "Hello World\nHello, World\ndlroW ,olleH",
            "💡 StringBuilder is mutable.\n💡 Use for frequent string modifications.\n✅ Better performance than String concatenation.",
            "Q: What is the advantage of StringBuilder?\nAns: Mutable and better performance",
            "🔨 StringBuilder in Java\n\nString ka mutable alternative.\n\nKyu StringBuilder?\n• Strings immutable hain\n• StringBuilder mutable hai\n• Modifications ke liye better performance\n\nCommon Methods:\n• append() - End mein add\n• insert() - Position par add\n• delete() - Characters remove\n• reverse() - String reverse\n• toString() - String mein convert"
        ));
        
        return lessons;
    }
    
    // Java Module 7: Methods
    private List<Lesson> getJavaModule7Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        lessons.add(createJavaLesson(28, 7, "Methods in Java",
            "⚙️ Methods in Java\n\n" +
            "A method is a block of code that performs a specific task.\n\n" +
            "Syntax:\n" +
            "returnType methodName(parameters) {\n" +
            "    // code\n" +
            "    return value;\n" +
            "}\n\n" +
            "Parts:\n" +
            "• returnType: Data type of return value (void if none)\n" +
            "• methodName: Name of method\n" +
            "• parameters: Input values (optional)\n" +
            "• return: Send value back (if not void)",
            "returnType methodName(params) {\n    return value;\n}",
            "public class Methods {\n    public static void main(String[] args) {\n        greet();\n        int sum = add(5, 3);\n        System.out.println(\"Sum: \" + sum);\n    }\n    \n    static void greet() {\n        System.out.println(\"Hello!\");\n    }\n    \n    static int add(int a, int b) {\n        return a + b;\n    }\n}",
            "Hello!\nSum: 8",
            "💡 void means no return value.\n💡 static methods can be called without object.\n✅ Methods improve code reusability.",
            "Q: What does void mean?\nAns: No return value",
            "⚙️ Java Mein Methods\n\nMethod ek code block hai jo specific task perform karta hai.\n\nSyntax:\nreturnType methodName(parameters) {\n    // code\n    return value;\n}\n\nParts:\n• returnType: Return value ka data type (void agar koi nahi)\n• methodName: Method ka naam\n• parameters: Input values (optional)\n• return: Value wapas bhejta hai (agar void nahi)"
        ));
        
        lessons.add(createJavaLesson(29, 7, "Method Parameters",
            "📥 Method Parameters\n\n" +
            "Parameters are values passed to methods.\n\n" +
            "Types:\n" +
            "1. No parameters\n" +
            "2. Single parameter\n" +
            "3. Multiple parameters\n\n" +
            "Parameter Passing:\n" +
            "• Pass by value (primitives)\n" +
            "• Pass by reference (objects)",
            "void method(int a, int b) {\n    // code\n}",
            "public class Parameters {\n    public static void main(String[] args) {\n        printName(\"Alice\");\n        int max = findMax(10, 20);\n        System.out.println(\"Max: \" + max);\n        \n        greet(\"Bob\", 25);\n    }\n    \n    static void printName(String name) {\n        System.out.println(\"Name: \" + name);\n    }\n    \n    static int findMax(int a, int b) {\n        return (a > b) ? a : b;\n    }\n    \n    static void greet(String name, int age) {\n        System.out.println(name + \" is \" + age);\n    }\n}",
            "Name: Alice\nMax: 20\nBob is 25",
            "💡 Parameters are local to method.\n💡 Can have multiple parameters.\n✅ Parameter names can be anything.",
            "Q: Are parameters local or global?\nAns: Local to method",
            "📥 Method Parameters\n\nParameters wo values hain jo methods ko pass ki jati hain.\n\nTypes:\n1. Koi parameter nahi\n2. Ek parameter\n3. Multiple parameters\n\nParameter Passing:\n• Pass by value (primitives)\n• Pass by reference (objects)"
        ));
        
        lessons.add(createJavaLesson(30, 7, "Method Overloading",
            "🔄 Method Overloading\n\n" +
            "Same method name, different parameters.\n\n" +
            "Rules:\n" +
            "• Same method name\n" +
            "• Different number of parameters OR\n" +
            "• Different types of parameters\n\n" +
            "Benefits:\n" +
            "• Code readability\n" +
            "• Flexibility\n" +
            "• Same operation, different inputs",
            "void print(int a) {}\nvoid print(String s) {}\nvoid print(int a, int b) {}",
            "public class Overloading {\n    public static void main(String[] args) {\n        System.out.println(add(5, 3));\n        System.out.println(add(5, 3, 2));\n        System.out.println(add(5.5, 3.2));\n    }\n    \n    static int add(int a, int b) {\n        return a + b;\n    }\n    \n    static int add(int a, int b, int c) {\n        return a + b + c;\n    }\n    \n    static double add(double a, double b) {\n        return a + b;\n    }\n}",
            "8\n10\n8.7",
            "💡 Overloading is compile-time polymorphism.\n💡 Return type alone cannot differentiate.\n✅ Parameters must differ.",
            "Q: What is method overloading?\nAns: Same name, different parameters",
            "🔄 Method Overloading\n\nSame method name, alag parameters.\n\nRules:\n• Same method name\n• Alag number of parameters YA\n• Alag types of parameters\n\nBenefits:\n• Code readability\n• Flexibility\n• Same operation, different inputs"
        ));
        
        lessons.add(createJavaLesson(31, 7, "Recursion",
            "🔁 Recursion in Java\n\n" +
            "A method calling itself.\n\n" +
            "Components:\n" +
            "1. Base case - When to stop\n" +
            "2. Recursive case - Method calls itself\n\n" +
            "Example: Factorial\n" +
            "5! = 5 × 4 × 3 × 2 × 1 = 120\n\n" +
            "Warning:\n" +
            "• Must have base case\n" +
            "• Can cause stack overflow if no base case",
            "int factorial(int n) {\n    if (n == 0) return 1;\n    return n * factorial(n-1);\n}",
            "public class Recursion {\n    public static void main(String[] args) {\n        System.out.println(\"5! = \" + factorial(5));\n        System.out.println(\"Sum 1-10: \" + sum(10));\n    }\n    \n    static int factorial(int n) {\n        if (n == 0 || n == 1) {\n            return 1;\n        }\n        return n * factorial(n - 1);\n    }\n    \n    static int sum(int n) {\n        if (n == 1) return 1;\n        return n + sum(n - 1);\n    }\n}",
            "5! = 120\nSum 1-10: 55",
            "⚠️ Always have a base case!\n💡 Recursion uses more memory than loops.\n✅ Good for tree/graph problems.",
            "Q: What is recursion?\nAns: Method calling itself",
            "🔁 Recursion in Java\n\nEk method khud ko call karta hai.\n\nComponents:\n1. Base case - Kab rukna hai\n2. Recursive case - Method khud ko call karta hai\n\nExample: Factorial\n5! = 5 × 4 × 3 × 2 × 1 = 120\n\nWarning:\n• Base case hona chahiye\n• Stack overflow ho sakta hai agar base case nahi"
        ));
        
        return lessons;
    }
    
    // Java Module 8: OOP - Classes & Objects
    private List<Lesson> getJavaModule8Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        lessons.add(createJavaLesson(32, 8, "Classes and Objects",
            "🏗️ Classes and Objects\n\n" +
            "Class: Blueprint/template for objects\n" +
            "Object: Instance of a class\n\n" +
            "Class contains:\n" +
            "• Variables (attributes/properties)\n" +
            "• Methods (behaviors/functions)\n\n" +
            "Syntax:\n" +
            "class ClassName {\n" +
            "    // variables\n" +
            "    // methods\n" +
            "}\n\n" +
            "Creating Object:\n" +
            "ClassName obj = new ClassName();",
            "class Student {\n    String name;\n    int age;\n}\nStudent s = new Student();",
            "class Student {\n    String name;\n    int age;\n    \n    void display() {\n        System.out.println(name + \", \" + age);\n    }\n}\n\npublic class Main {\n    public static void main(String[] args) {\n        Student s1 = new Student();\n        s1.name = \"Alice\";\n        s1.age = 20;\n        s1.display();\n        \n        Student s2 = new Student();\n        s2.name = \"Bob\";\n        s2.age = 22;\n        s2.display();\n    }\n}",
            "Alice, 20\nBob, 22",
            "💡 Class is template, object is instance.\n💡 Use 'new' keyword to create objects.\n✅ Can create multiple objects from one class.",
            "Q: What is an object?\nAns: Instance of a class",
            "🏗️ Classes aur Objects\n\nClass: Objects ka blueprint/template\nObject: Class ka instance\n\nClass mein hota hai:\n• Variables (attributes/properties)\n• Methods (behaviors/functions)\n\nSyntax:\nclass ClassName {\n    // variables\n    // methods\n}\n\nObject Banana:\nClassName obj = new ClassName();"
        ));
        
        lessons.add(createJavaLesson(33, 8, "Constructors",
            "🔨 Constructors in Java\n\n" +
            "Special method to initialize objects.\n\n" +
            "Features:\n" +
            "• Same name as class\n" +
            "• No return type\n" +
            "• Called automatically when object is created\n\n" +
            "Types:\n" +
            "1. Default Constructor (no parameters)\n" +
            "2. Parameterized Constructor (with parameters)",
            "class Student {\n    Student() {\n        // constructor\n    }\n}",
            "class Student {\n    String name;\n    int age;\n    \n    // Default constructor\n    Student() {\n        name = \"Unknown\";\n        age = 0;\n    }\n    \n    // Parameterized constructor\n    Student(String n, int a) {\n        name = n;\n        age = a;\n    }\n    \n    void display() {\n        System.out.println(name + \", \" + age);\n    }\n}\n\npublic class Main {\n    public static void main(String[] args) {\n        Student s1 = new Student();\n        s1.display();\n        \n        Student s2 = new Student(\"Alice\", 20);\n        s2.display();\n    }\n}",
            "Unknown, 0\nAlice, 20",
            "💡 Constructor name = class name.\n💡 No return type for constructors.\n✅ Can have multiple constructors (overloading).",
            "Q: What is a constructor?\nAns: Special method to initialize objects",
            "🔨 Constructors in Java\n\nObjects ko initialize karne ka special method.\n\nFeatures:\n• Class ke naam jaisa\n• Koi return type nahi\n• Object banne par automatically call hota hai\n\nTypes:\n1. Default Constructor (parameters nahi)\n2. Parameterized Constructor (parameters ke saath)"
        ));
        
        lessons.add(createJavaLesson(34, 8, "this Keyword",
            "👉 'this' Keyword\n\n" +
            "Refers to current object.\n\n" +
            "Uses:\n" +
            "1. Differentiate instance variables from parameters\n" +
            "2. Call another constructor\n" +
            "3. Pass current object as parameter\n" +
            "4. Return current object\n\n" +
            "Example:\n" +
            "this.name = name; // this.name is instance variable",
            "this.variableName",
            "class Student {\n    String name;\n    int age;\n    \n    Student(String name, int age) {\n        this.name = name;  // this.name is instance variable\n        this.age = age;    // name, age are parameters\n    }\n    \n    void display() {\n        System.out.println(this.name + \", \" + this.age);\n    }\n    \n    Student getObject() {\n        return this;  // return current object\n    }\n}\n\npublic class Main {\n    public static void main(String[] args) {\n        Student s = new Student(\"Alice\", 20);\n        s.display();\n    }\n}",
            "Alice, 20",
            "💡 'this' refers to current object.\n💡 Use to avoid naming conflicts.\n✅ Makes code more readable.",
            "Q: What does 'this' keyword refer to?\nAns: Current object",
            "👉 'this' Keyword\n\nCurrent object ko refer karta hai.\n\nUses:\n1. Instance variables aur parameters mein farak\n2. Dusre constructor ko call karna\n3. Current object ko parameter ki tarah pass karna\n4. Current object return karna\n\nExample:\nthis.name = name; // this.name instance variable hai"
        ));
        
        lessons.add(createJavaLesson(35, 8, "Access Modifiers",
            "🔒 Access Modifiers\n\n" +
            "Control access to classes, methods, variables.\n\n" +
            "Types:\n" +
            "1. public - Accessible everywhere\n" +
            "2. private - Only within same class\n" +
            "3. protected - Same package + subclasses\n" +
            "4. default - Same package only\n\n" +
            "Encapsulation:\n" +
            "• Make variables private\n" +
            "• Provide public getter/setter methods",
            "private int age;\npublic int getAge() {\n    return age;\n}",
            "class Student {\n    private String name;\n    private int age;\n    \n    public void setName(String name) {\n        this.name = name;\n    }\n    \n    public String getName() {\n        return name;\n    }\n    \n    public void setAge(int age) {\n        if (age > 0) {\n            this.age = age;\n        }\n    }\n    \n    public int getAge() {\n        return age;\n    }\n}\n\npublic class Main {\n    public static void main(String[] args) {\n        Student s = new Student();\n        s.setName(\"Alice\");\n        s.setAge(20);\n        System.out.println(s.getName() + \", \" + s.getAge());\n    }\n}",
            "Alice, 20",
            "🔐 private = most restrictive.\n🔐 public = least restrictive.\n💡 Use private for data hiding.",
            "Q: Which modifier is most restrictive?\nAns: private",
            "🔒 Access Modifiers\n\nClasses, methods, variables ka access control karte hain.\n\nTypes:\n1. public - Har jagah accessible\n2. private - Sirf same class mein\n3. protected - Same package + subclasses\n4. default - Sirf same package\n\nEncapsulation:\n• Variables ko private banao\n• Public getter/setter methods do"
        ));
        
        lessons.add(createJavaLesson(36, 8, "static Keyword",
            "⚡ static Keyword\n\n" +
            "Belongs to class, not objects.\n\n" +
            "static variable:\n" +
            "• Shared by all objects\n" +
            "• One copy for all\n\n" +
            "static method:\n" +
            "• Can be called without object\n" +
            "• Cannot use 'this'\n" +
            "• Can only access static members\n\n" +
            "static block:\n" +
            "• Executes when class is loaded",
            "static int count;\nstatic void display() {}",
            "class Counter {\n    static int count = 0;\n    \n    Counter() {\n        count++;\n    }\n    \n    static void displayCount() {\n        System.out.println(\"Count: \" + count);\n    }\n}\n\npublic class Main {\n    public static void main(String[] args) {\n        Counter c1 = new Counter();\n        Counter c2 = new Counter();\n        Counter c3 = new Counter();\n        \n        Counter.displayCount();  // Called without object\n    }\n}",
            "Count: 3",
            "💡 static members belong to class.\n💡 Can access without creating object.\n✅ Good for utility methods.",
            "Q: Can static method use 'this'?\nAns: No",
            "⚡ static Keyword\n\nClass ka hota hai, objects ka nahi.\n\nstatic variable:\n• Sabhi objects share karte hain\n• Sabke liye ek copy\n\nstatic method:\n• Object ke bina call ho sakta hai\n• 'this' use nahi kar sakta\n• Sirf static members access kar sakta\n\nstatic block:\n• Class load hone par execute hota"
        ));
        
        return lessons;
    }
    
    // Java Module 9: OOP - Inheritance
    private List<Lesson> getJavaModule9Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        lessons.add(createJavaLesson(37, 9, "Inheritance Basics",
            "🧱 Inheritance in Java\n\n" +
            "Mechanism where one class acquires properties of another.\n\n" +
            "Parent Class (Superclass): Class being inherited from\n" +
            "Child Class (Subclass): Class that inherits\n\n" +
            "Syntax:\n" +
            "class Child extends Parent {\n" +
            "    // child members\n" +
            "}\n\n" +
            "Benefits:\n" +
            "• Code reusability\n" +
            "• Method overriding\n" +
            "• Hierarchical classification",
            "class Child extends Parent {}",
            "class Animal {\n    void eat() {\n        System.out.println(\"Eating...\");\n    }\n}\n\nclass Dog extends Animal {\n    void bark() {\n        System.out.println(\"Barking...\");\n    }\n}\n\npublic class Main {\n    public static void main(String[] args) {\n        Dog d = new Dog();\n        d.eat();   // Inherited method\n        d.bark();  // Own method\n    }\n}",
            "Eating...\nBarking...",
            "💡 Child class inherits all non-private members.\n💡 Use 'extends' keyword.\n✅ Java supports single inheritance only.",
            "Q: Which keyword is used for inheritance?\nAns: extends",
            "🧱 Inheritance in Java\n\nEk class dusri class ki properties acquire karti hai.\n\nParent Class (Superclass): Jisse inherit karte hain\nChild Class (Subclass): Jo inherit karta hai\n\nSyntax:\nclass Child extends Parent {\n    // child members\n}\n\nBenefits:\n• Code reusability\n• Method overriding\n• Hierarchical classification"
        ));
        
        lessons.add(createJavaLesson(38, 9, "super Keyword",
            "⬆️ 'super' Keyword\n\n" +
            "Refers to parent class.\n\n" +
            "Uses:\n" +
            "1. Access parent class variables\n" +
            "2. Call parent class methods\n" +
            "3. Call parent class constructor\n\n" +
            "Syntax:\n" +
            "super.variable\n" +
            "super.method()\n" +
            "super(); // constructor",
            "super.variableName\nsuper.methodName()\nsuper();",
            "class Animal {\n    String color = \"White\";\n    \n    Animal() {\n        System.out.println(\"Animal created\");\n    }\n}\n\nclass Dog extends Animal {\n    String color = \"Black\";\n    \n    Dog() {\n        super();  // Call parent constructor\n        System.out.println(\"Dog created\");\n    }\n    \n    void display() {\n        System.out.println(\"Dog color: \" + color);\n        System.out.println(\"Animal color: \" + super.color);\n    }\n}\n\npublic class Main {\n    public static void main(String[] args) {\n        Dog d = new Dog();\n        d.display();\n    }\n}",
            "Animal created\nDog created\nDog color: Black\nAnimal color: White",
            "💡 super() must be first statement in constructor.\n💡 Use super to access hidden parent members.\n✅ Helps avoid naming conflicts.",
            "Q: What does super keyword do?\nAns: Refers to parent class",
            "⬆️ 'super' Keyword\n\nParent class ko refer karta hai.\n\nUses:\n1. Parent class variables access karna\n2. Parent class methods call karna\n3. Parent class constructor call karna\n\nSyntax:\nsuper.variable\nsuper.method()\nsuper(); // constructor"
        ));
        
        lessons.add(createJavaLesson(39, 9, "Method Overriding",
            "🔄 Method Overriding\n\n" +
            "Child class provides specific implementation of parent method.\n\n" +
            "Rules:\n" +
            "• Same method name\n" +
            "• Same parameters\n" +
            "• Same return type\n" +
            "• IS-A relationship (inheritance)\n\n" +
            "@Override annotation (optional but recommended)",
            "@Override\nvoid methodName() {\n    // new implementation\n}",
            "class Animal {\n    void sound() {\n        System.out.println(\"Animal makes sound\");\n    }\n}\n\nclass Dog extends Animal {\n    @Override\n    void sound() {\n        System.out.println(\"Dog barks\");\n    }\n}\n\nclass Cat extends Animal {\n    @Override\n    void sound() {\n        System.out.println(\"Cat meows\");\n    }\n}\n\npublic class Main {\n    public static void main(String[] args) {\n        Animal a = new Animal();\n        a.sound();\n        \n        Dog d = new Dog();\n        d.sound();\n        \n        Cat c = new Cat();\n        c.sound();\n    }\n}",
            "Animal makes sound\nDog barks\nCat meows",
            "💡 Overriding is runtime polymorphism.\n💡 Cannot override static/final/private methods.\n✅ Use @Override annotation.",
            "Q: What is method overriding?\nAns: Child class redefines parent method",
            "🔄 Method Overriding\n\nChild class parent method ka specific implementation deta hai.\n\nRules:\n• Same method name\n• Same parameters\n• Same return type\n• IS-A relationship (inheritance)\n\n@Override annotation (optional but recommended)"
        ));
        
        lessons.add(createJavaLesson(40, 9, "Types of Inheritance",
            "🌳 Types of Inheritance\n\n" +
            "1. Single Inheritance:\n" +
            "   A → B\n\n" +
            "2. Multilevel Inheritance:\n" +
            "   A → B → C\n\n" +
            "3. Hierarchical Inheritance:\n" +
            "   A → B, A → C\n\n" +
            "Note: Java doesn't support multiple inheritance with classes (but supports with interfaces).",
            "// Single\nclass B extends A {}\n\n// Multilevel\nclass C extends B {}\n\n// Hierarchical\nclass B extends A {}\nclass C extends A {}",
            "// Multilevel Inheritance Example\nclass GrandParent {\n    void show1() {\n        System.out.println(\"GrandParent\");\n    }\n}\n\nclass Parent extends GrandParent {\n    void show2() {\n        System.out.println(\"Parent\");\n    }\n}\n\nclass Child extends Parent {\n    void show3() {\n        System.out.println(\"Child\");\n    }\n}\n\npublic class Main {\n    public static void main(String[] args) {\n        Child c = new Child();\n        c.show1();  // From GrandParent\n        c.show2();  // From Parent\n        c.show3();  // Own method\n    }\n}",
            "GrandParent\nParent\nChild",
            "⚠️ Java doesn't support multiple inheritance.\n💡 Multilevel: Chain of inheritance.\n💡 Hierarchical: Multiple children from one parent.",
            "Q: Does Java support multiple inheritance?\nAns: No (with classes)",
            "🌳 Inheritance Ke Types\n\n1. Single Inheritance:\n   A → B\n\n2. Multilevel Inheritance:\n   A → B → C\n\n3. Hierarchical Inheritance:\n   A → B, A → C\n\nNote: Java classes ke saath multiple inheritance support nahi karta (lekin interfaces ke saath karta hai)."
        ));
        
        return lessons;
    }
    
    // Java Module 10: OOP - Polymorphism
    private List<Lesson> getJavaModule10Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        lessons.add(createJavaLesson(41, 10, "Polymorphism Basics",
            "🎭 Polymorphism in Java\n\n" +
            "'Many forms' - Same entity behaves differently.\n\n" +
            "Types:\n" +
            "1. Compile-time (Static)\n" +
            "   - Method Overloading\n" +
            "   - Operator Overloading (not in Java)\n\n" +
            "2. Runtime (Dynamic)\n" +
            "   - Method Overriding\n" +
            "   - Achieved through inheritance",
            "Animal a = new Dog();\na.sound(); // Dog's sound",
            "class Animal {\n    void sound() {\n        System.out.println(\"Animal sound\");\n    }\n}\n\nclass Dog extends Animal {\n    void sound() {\n        System.out.println(\"Bark\");\n    }\n}\n\nclass Cat extends Animal {\n    void sound() {\n        System.out.println(\"Meow\");\n    }\n}\n\npublic class Main {\n    public static void main(String[] args) {\n        Animal a;\n        \n        a = new Dog();\n        a.sound();  // Bark\n        \n        a = new Cat();\n        a.sound();  // Meow\n    }\n}",
            "Bark\nMeow",
            "💡 Polymorphism = One interface, many implementations.\n💡 Decided at runtime (dynamic binding).\n✅ Increases flexibility.",
            "Q: What is polymorphism?\nAns: One entity, many forms",
            "🎭 Polymorphism in Java\n\n'Kai roop' - Ek entity alag tarike se behave karti hai.\n\nTypes:\n1. Compile-time (Static)\n   - Method Overloading\n\n2. Runtime (Dynamic)\n   - Method Overriding\n   - Inheritance ke through achieve hota"
        ));
        
        lessons.add(createJavaLesson(42, 10, "Upcasting and Downcasting",
            "⬆️⬇️ Upcasting and Downcasting\n\n" +
            "Upcasting:\n" +
            "• Child to Parent reference\n" +
            "• Automatic (implicit)\n" +
            "• Safe\n\n" +
            "Downcasting:\n" +
            "• Parent to Child reference\n" +
            "• Manual (explicit)\n" +
            "• May cause ClassCastException",
            "// Upcasting\nAnimal a = new Dog();\n\n// Downcasting\nDog d = (Dog) a;",
            "class Animal {\n    void eat() {\n        System.out.println(\"Eating\");\n    }\n}\n\nclass Dog extends Animal {\n    void bark() {\n        System.out.println(\"Barking\");\n    }\n}\n\npublic class Main {\n    public static void main(String[] args) {\n        // Upcasting (automatic)\n        Animal a = new Dog();\n        a.eat();\n        // a.bark(); // Error! Animal doesn't have bark()\n        \n        // Downcasting (manual)\n        Dog d = (Dog) a;\n        d.bark();  // Now works\n        d.eat();\n    }\n}",
            "Eating\nBarking\nEating",
            "💡 Upcasting is always safe.\n⚠️ Downcasting may fail at runtime.\n💡 Use instanceof to check before downcasting.",
            "Q: Which casting is automatic?\nAns: Upcasting",
            "⬆️⬇️ Upcasting aur Downcasting\n\nUpcasting:\n• Child se Parent reference\n• Automatic (implicit)\n• Safe\n\nDowncasting:\n• Parent se Child reference\n• Manual (explicit)\n• ClassCastException ho sakta hai"
        ));
        
        lessons.add(createJavaLesson(43, 10, "instanceof Operator",
            "❓ instanceof Operator\n\n" +
            "Check if object is instance of a class.\n\n" +
            "Syntax:\n" +
            "object instanceof ClassName\n\n" +
            "Returns:\n" +
            "• true if object is instance\n" +
            "• false otherwise\n\n" +
            "Use: Before downcasting to avoid ClassCastException",
            "if (obj instanceof Dog) {\n    Dog d = (Dog) obj;\n}",
            "class Animal {}\nclass Dog extends Animal {}\nclass Cat extends Animal {}\n\npublic class Main {\n    public static void main(String[] args) {\n        Animal a = new Dog();\n        \n        System.out.println(a instanceof Dog);     // true\n        System.out.println(a instanceof Animal);  // true\n        System.out.println(a instanceof Cat);     // false\n        \n        // Safe downcasting\n        if (a instanceof Dog) {\n            Dog d = (Dog) a;\n            System.out.println(\"Safely downcasted\");\n        }\n    }\n}",
            "true\ntrue\nfalse\nSafely downcasted",
            "💡 Always use instanceof before downcasting.\n💡 Prevents ClassCastException.\n✅ Returns boolean value.",
            "Q: What does instanceof return?\nAns: boolean (true/false)",
            "❓ instanceof Operator\n\nCheck karta hai ki object kisi class ka instance hai ya nahi.\n\nSyntax:\nobject instanceof ClassName\n\nReturns:\n• true agar object instance hai\n• false otherwise\n\nUse: Downcasting se pehle ClassCastException avoid karne ke liye"
        ));
        
        lessons.add(createJavaLesson(44, 10, "Abstract Classes",
            "🎨 Abstract Classes\n\n" +
            "Incomplete class that cannot be instantiated.\n\n" +
            "Features:\n" +
            "• Declared with 'abstract' keyword\n" +
            "• Can have abstract methods (no body)\n" +
            "• Can have concrete methods\n" +
            "• Cannot create objects\n" +
            "• Must be extended\n\n" +
            "Abstract method: Method without implementation",
            "abstract class Animal {\n    abstract void sound();\n    void eat() { }\n}",
            "abstract class Animal {\n    abstract void sound();  // Abstract method\n    \n    void eat() {  // Concrete method\n        System.out.println(\"Eating...\");\n    }\n}\n\nclass Dog extends Animal {\n    void sound() {\n        System.out.println(\"Bark\");\n    }\n}\n\npublic class Main {\n    public static void main(String[] args) {\n        // Animal a = new Animal(); // Error!\n        Animal a = new Dog();\n        a.sound();\n        a.eat();\n    }\n}",
            "Bark\nEating...",
            "⚠️ Cannot instantiate abstract class.\n💡 Child must implement all abstract methods.\n✅ Used for achieving abstraction.",
            "Q: Can we create object of abstract class?\nAns: No",
            "🎨 Abstract Classes\n\nIncomplete class jiska object nahi bana sakte.\n\nFeatures:\n• 'abstract' keyword se declare\n• Abstract methods ho sakte hain (body nahi)\n• Concrete methods ho sakte hain\n• Objects nahi bana sakte\n• Extend karna padta hai\n\nAbstract method: Bina implementation ka method"
        ));
        
        return lessons;
    }
    
    // Java Module 11: Exception Handling
    private List<Lesson> getJavaModule11Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        lessons.add(createJavaLesson(45, 11, "Exception Handling Basics",
            "⚠️ Exception Handling\n\n" +
            "Handle runtime errors gracefully.\n\n" +
            "Exception: Abnormal event that disrupts program flow.\n\n" +
            "Keywords:\n" +
            "• try - Code that may throw exception\n" +
            "• catch - Handle the exception\n" +
            "• finally - Always executes\n" +
            "• throw - Throw exception manually\n" +
            "• throws - Declare exception",
            "try {\n    // code\n} catch (Exception e) {\n    // handle\n}",
            "public class ExceptionDemo {\n    public static void main(String[] args) {\n        try {\n            int result = 10 / 0;  // ArithmeticException\n            System.out.println(result);\n        } catch (ArithmeticException e) {\n            System.out.println(\"Error: \" + e.getMessage());\n        }\n        \n        System.out.println(\"Program continues...\");\n    }\n}",
            "Error: / by zero\nProgram continues...",
            "💡 Without try-catch, program would crash.\n💡 catch block handles the exception.\n✅ Program continues after handling.",
            "Q: Which block handles exceptions?\nAns: catch",
            "⚠️ Exception Handling\n\nRuntime errors ko gracefully handle karna.\n\nException: Abnormal event jo program flow disturb karta hai.\n\nKeywords:\n• try - Code jo exception throw kar sakta\n• catch - Exception handle karna\n• finally - Hamesha execute hota\n• throw - Manually exception throw\n• throws - Exception declare karna"
        ));
        
        lessons.add(createJavaLesson(46, 11, "try-catch-finally",
            "🛡️ try-catch-finally\n\n" +
            "try: Contains risky code\n" +
            "catch: Handles exception\n" +
            "finally: Always executes (cleanup code)\n\n" +
            "Multiple catch blocks:\n" +
            "Handle different exceptions differently.\n\n" +
            "finally executes even if exception occurs or not.",
            "try {}\ncatch (Exception e) {}\nfinally {}",
            "public class TryCatchFinally {\n    public static void main(String[] args) {\n        try {\n            int[] arr = {1, 2, 3};\n            System.out.println(arr[5]);  // Exception\n        } catch (ArrayIndexOutOfBoundsException e) {\n            System.out.println(\"Array index error\");\n        } finally {\n            System.out.println(\"Finally block executed\");\n        }\n        \n        System.out.println(\"End\");\n    }\n}",
            "Array index error\nFinally block executed\nEnd",
            "💡 finally always executes.\n💡 Use finally for cleanup (close files, etc.).\n✅ Can have multiple catch blocks.",
            "Q: Does finally block always execute?\nAns: Yes",
            "🛡️ try-catch-finally\n\ntry: Risky code\ncatch: Exception handle\nfinally: Hamesha execute (cleanup code)\n\nMultiple catch blocks:\nAlag exceptions ko alag handle karo.\n\nfinally exception ho ya na ho, execute hota hai."
        ));
        
        lessons.add(createJavaLesson(47, 11, "throw and throws",
            "💥 throw and throws\n\n" +
            "throw:\n" +
            "• Manually throw exception\n" +
            "• Used inside method\n" +
            "• Syntax: throw new ExceptionType();\n\n" +
            "throws:\n" +
            "• Declare exception\n" +
            "• Used in method signature\n" +
            "• Syntax: void method() throws Exception",
            "throw new Exception();\nvoid method() throws Exception {}",
            "public class ThrowThrows {\n    static void checkAge(int age) throws Exception {\n        if (age < 18) {\n            throw new Exception(\"Age must be 18+\");\n        }\n        System.out.println(\"Valid age\");\n    }\n    \n    public static void main(String[] args) {\n        try {\n            checkAge(15);\n        } catch (Exception e) {\n            System.out.println(\"Error: \" + e.getMessage());\n        }\n    }\n}",
            "Error: Age must be 18+",
            "💡 throw = throw exception object.\n💡 throws = declare that method may throw.\n✅ Caller must handle declared exceptions.",
            "Q: Which keyword throws exception manually?\nAns: throw",
            "💥 throw aur throws\n\nthrow:\n• Manually exception throw karna\n• Method ke andar use\n• Syntax: throw new ExceptionType();\n\nthrows:\n• Exception declare karna\n• Method signature mein use\n• Syntax: void method() throws Exception"
        ));
        
        lessons.add(createJavaLesson(48, 11, "Custom Exceptions",
            "🎯 Custom Exceptions\n\n" +
            "Create your own exception classes.\n\n" +
            "Steps:\n" +
            "1. Extend Exception class\n" +
            "2. Add constructors\n" +
            "3. Throw using 'throw'\n\n" +
            "Benefits:\n" +
            "• Meaningful exception names\n" +
            "• Application-specific errors\n" +
            "• Better error handling",
            "class MyException extends Exception {\n    MyException(String msg) {\n        super(msg);\n    }\n}",
            "class InvalidAgeException extends Exception {\n    InvalidAgeException(String message) {\n        super(message);\n    }\n}\n\npublic class CustomException {\n    static void validate(int age) throws InvalidAgeException {\n        if (age < 18) {\n            throw new InvalidAgeException(\"Not eligible\");\n        }\n        System.out.println(\"Eligible\");\n    }\n    \n    public static void main(String[] args) {\n        try {\n            validate(15);\n        } catch (InvalidAgeException e) {\n            System.out.println(\"Error: \" + e.getMessage());\n        }\n    }\n}",
            "Error: Not eligible",
            "💡 Extend Exception for checked exceptions.\n💡 Extend RuntimeException for unchecked.\n✅ Makes code more readable.",
            "Q: Which class to extend for custom exception?\nAns: Exception",
            "🎯 Custom Exceptions\n\nApne exception classes banana.\n\nSteps:\n1. Exception class extend karo\n2. Constructors add karo\n3. 'throw' use karke throw karo\n\nBenefits:\n• Meaningful exception names\n• Application-specific errors\n• Better error handling"
        ));
        
        return lessons;
    }
    
    // Java Module 12: Collections
    private List<Lesson> getJavaModule12Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        lessons.add(createJavaLesson(49, 12, "ArrayList",
            "📚 ArrayList in Java\n\n" +
            "Dynamic array that can grow/shrink.\n\n" +
            "Features:\n" +
            "• Resizable array\n" +
            "• Can store objects only\n" +
            "• Allows duplicates\n" +
            "• Maintains insertion order\n\n" +
            "Common Methods:\n" +
            "• add() - Add element\n" +
            "• get() - Get element\n" +
            "• remove() - Remove element\n" +
            "• size() - Get size",
            "ArrayList<Type> list = new ArrayList<>();",
            "import java.util.ArrayList;\n\npublic class ArrayListDemo {\n    public static void main(String[] args) {\n        ArrayList<String> list = new ArrayList<>();\n        \n        list.add(\"Java\");\n        list.add(\"Python\");\n        list.add(\"C++\");\n        \n        System.out.println(\"List: \" + list);\n        System.out.println(\"Size: \" + list.size());\n        System.out.println(\"First: \" + list.get(0));\n        \n        list.remove(1);\n        System.out.println(\"After remove: \" + list);\n    }\n}",
            "List: [Java, Python, C++]\nSize: 3\nFirst: Java\nAfter remove: [Java, C++]",
            "💡 Import java.util.ArrayList\n💡 Use generics <Type> for type safety.\n✅ Better than arrays for dynamic size.",
            "Q: Can ArrayList size change?\nAns: Yes, it's dynamic",
            "📚 ArrayList in Java\n\nDynamic array jo grow/shrink ho sakta hai.\n\nFeatures:\n• Resizable array\n• Sirf objects store\n• Duplicates allowed\n• Insertion order maintain\n\nCommon Methods:\n• add() - Element add\n• get() - Element get\n• remove() - Element remove\n• size() - Size get"
        ));
        
        lessons.add(createJavaLesson(50, 12, "HashMap",
            "🗺️ HashMap in Java\n\n" +
            "Stores key-value pairs.\n\n" +
            "Features:\n" +
            "• Key-value mapping\n" +
            "• No duplicate keys\n" +
            "• One null key allowed\n" +
            "• Fast retrieval\n\n" +
            "Common Methods:\n" +
            "• put(key, value) - Add pair\n" +
            "• get(key) - Get value\n" +
            "• remove(key) - Remove pair\n" +
            "• containsKey() - Check key",
            "HashMap<K, V> map = new HashMap<>();",
            "import java.util.HashMap;\n\npublic class HashMapDemo {\n    public static void main(String[] args) {\n        HashMap<String, Integer> map = new HashMap<>();\n        \n        map.put(\"Java\", 95);\n        map.put(\"Python\", 90);\n        map.put(\"C++\", 85);\n        \n        System.out.println(\"Map: \" + map);\n        System.out.println(\"Java score: \" + map.get(\"Java\"));\n        System.out.println(\"Contains C++? \" + map.containsKey(\"C++\"));\n        \n        map.remove(\"Python\");\n        System.out.println(\"After remove: \" + map);\n    }\n}",
            "Map: {Java=95, Python=90, C++=85}\nJava score: 95\nContains C++? true\nAfter remove: {Java=95, C++=85}",
            "💡 Keys must be unique.\n💡 Values can be duplicate.\n✅ Fast O(1) average lookup time.",
            "Q: Can HashMap have duplicate keys?\nAns: No",
            "🗺️ HashMap in Java\n\nKey-value pairs store karta hai.\n\nFeatures:\n• Key-value mapping\n• Duplicate keys nahi\n• Ek null key allowed\n• Fast retrieval\n\nCommon Methods:\n• put(key, value) - Pair add\n• get(key) - Value get\n• remove(key) - Pair remove\n• containsKey() - Key check"
        ));
        
        lessons.add(createJavaLesson(51, 12, "HashSet",
            "🎲 HashSet in Java\n\n" +
            "Stores unique elements.\n\n" +
            "Features:\n" +
            "• No duplicates\n" +
            "• No guaranteed order\n" +
            "• One null allowed\n" +
            "• Fast operations\n\n" +
            "Common Methods:\n" +
            "• add() - Add element\n" +
            "• remove() - Remove element\n" +
            "• contains() - Check element\n" +
            "• size() - Get size",
            "HashSet<Type> set = new HashSet<>();",
            "import java.util.HashSet;\n\npublic class HashSetDemo {\n    public static void main(String[] args) {\n        HashSet<String> set = new HashSet<>();\n        \n        set.add(\"Java\");\n        set.add(\"Python\");\n        set.add(\"Java\");  // Duplicate, won't be added\n        set.add(\"C++\");\n        \n        System.out.println(\"Set: \" + set);\n        System.out.println(\"Size: \" + set.size());\n        System.out.println(\"Contains Java? \" + set.contains(\"Java\"));\n        \n        set.remove(\"Python\");\n        System.out.println(\"After remove: \" + set);\n    }\n}",
            "Set: [Java, C++, Python]\nSize: 3\nContains Java? true\nAfter remove: [Java, C++]",
            "💡 Automatically removes duplicates.\n💡 Order not guaranteed.\n✅ Good for unique collections.",
            "Q: Does HashSet allow duplicates?\nAns: No",
            "🎲 HashSet in Java\n\nUnique elements store karta hai.\n\nFeatures:\n• Duplicates nahi\n• Order guaranteed nahi\n• Ek null allowed\n• Fast operations\n\nCommon Methods:\n• add() - Element add\n• remove() - Element remove\n• contains() - Element check\n• size() - Size get"
        ));
        
        lessons.add(createJavaLesson(52, 12, "Iterating Collections",
            "🔄 Iterating Collections\n\n" +
            "Ways to iterate:\n\n" +
            "1. for-each loop\n" +
            "2. Iterator\n" +
            "3. for loop (with index)\n" +
            "4. forEach() method (Java 8+)\n\n" +
            "Best Practice:\n" +
            "Use for-each for simple iteration.",
            "for (Type item : collection) {\n    // use item\n}",
            "import java.util.*;\n\npublic class Iteration {\n    public static void main(String[] args) {\n        ArrayList<String> list = new ArrayList<>();\n        list.add(\"Java\");\n        list.add(\"Python\");\n        list.add(\"C++\");\n        \n        // for-each loop\n        for (String lang : list) {\n            System.out.println(lang);\n        }\n        \n        // Iterator\n        Iterator<String> it = list.iterator();\n        while (it.hasNext()) {\n            System.out.println(it.next());\n        }\n    }\n}",
            "Java\nPython\nC++\nJava\nPython\nC++",
            "💡 for-each is simplest and most readable.\n💡 Use Iterator to remove during iteration.\n✅ forEach() method available in Java 8+.",
            "Q: Which loop is best for collections?\nAns: for-each loop",
            "🔄 Collections Ko Iterate Karna\n\nIterate karne ke tarike:\n\n1. for-each loop\n2. Iterator\n3. for loop (index ke saath)\n4. forEach() method (Java 8+)\n\nBest Practice:\nSimple iteration ke liye for-each use karo."
        ));
        
        return lessons;
    }
    
    // Java Module 13: Quiz
    private List<Lesson> getJavaModule13Lessons() {
        List<Lesson> lessons = new ArrayList<>();
        
        lessons.add(new Lesson("java_quiz_1", "java_chapter_1", "📝 Module 1 Quiz: Java Basics", 
            "Test Java Basics!\n\nTopics: JVM, JRE, JDK\n\n📊 Questions: 5\n🎯 Passing: 60%", 
            "", "", "", "Click 'Take Quiz' to start!", "Q: Ready?", 1));
        
        lessons.add(new Lesson("java_quiz_2", "java_chapter_2", "📝 Module 2 Quiz: Data Types & Variables", 
            "Test Data Types!\n\nTopics: Primitives, Variables\n\n📊 Questions: 5\n🎯 Passing: 60%", 
            "", "", "", "Click 'Take Quiz' to start!", "Q: Ready?", 2));
        
        lessons.add(new Lesson("java_quiz_3", "java_chapter_3", "📝 Module 3 Quiz: Operators", 
            "Test Operators!\n\nTopics: Arithmetic, Logical\n\n📊 Questions: 5\n🎯 Passing: 60%", 
            "", "", "", "Click 'Take Quiz' to start!", "Q: Ready?", 3));
        
        lessons.add(new Lesson("java_quiz_4", "java_chapter_4", "📝 Module 4 Quiz: Control Statements", 
            "Test Control Statements!\n\nTopics: if-else, switch\n\n📊 Questions: 5\n🎯 Passing: 60%", 
            "", "", "", "Click 'Take Quiz' to start!", "Q: Ready?", 4));
        
        lessons.add(new Lesson("java_quiz_5", "java_chapter_5", "📝 Module 5 Quiz: Loops", 
            "Test Loops!\n\nTopics: for, while, do-while\n\n📊 Questions: 5\n🎯 Passing: 60%", 
            "", "", "", "Click 'Take Quiz' to start!", "Q: Ready?", 5));
        
        lessons.add(new Lesson("java_quiz_6", "java_chapter_6", "📝 Module 6 Quiz: Arrays & Strings", 
            "Test Arrays!\n\nTopics: Arrays, String methods\n\n📊 Questions: 5\n🎯 Passing: 60%", 
            "", "", "", "Click 'Take Quiz' to start!", "Q: Ready?", 6));
        
        lessons.add(new Lesson("java_quiz_7", "java_chapter_7", "📝 Module 7 Quiz: Methods", 
            "Test Methods!\n\nTopics: Methods, overloading\n\n📊 Questions: 5\n🎯 Passing: 60%", 
            "", "", "", "Click 'Take Quiz' to start!", "Q: Ready?", 7));
        
        lessons.add(new Lesson("java_quiz_8", "java_chapter_8", "📝 Module 8 Quiz: Classes & Objects", 
            "Test OOP!\n\nTopics: Class, Object, Constructor\n\n📊 Questions: 5\n🎯 Passing: 60%", 
            "", "", "", "Click 'Take Quiz' to start!", "Q: Ready?", 8));
        
        lessons.add(new Lesson("java_quiz_9", "java_chapter_9", "📝 Module 9 Quiz: Inheritance", 
            "Test Inheritance!\n\nTopics: extends, super\n\n📊 Questions: 5\n🎯 Passing: 60%", 
            "", "", "", "Click 'Take Quiz' to start!", "Q: Ready?", 9));
        
        lessons.add(new Lesson("java_quiz_10", "java_chapter_10", "📝 Module 10 Quiz: Polymorphism", 
            "Test Polymorphism!\n\nTopics: Overriding, @Override\n\n📊 Questions: 5\n🎯 Passing: 60%", 
            "", "", "", "Click 'Take Quiz' to start!", "Q: Ready?", 10));
        
        lessons.add(new Lesson("java_quiz_11", "java_chapter_11", "📝 Module 11 Quiz: Exception Handling", 
            "Test Exceptions!\n\nTopics: try-catch, throw\n\n📊 Questions: 5\n🎯 Passing: 60%", 
            "", "", "", "Click 'Take Quiz' to start!", "Q: Ready?", 11));
        
        lessons.add(new Lesson("java_quiz_12", "java_chapter_12", "📝 Module 12 Quiz: Collections", 
            "Test Collections!\n\nTopics: ArrayList, HashMap\n\n📊 Questions: 7\n🎯 Passing: 60%", 
            "", "", "", "Click 'Take Quiz' to start!", "Q: Ready?", 12));
        
        // Final Comprehensive Quiz - 100 Questions
        lessons.add(new Lesson("java_final_quiz", "java_chapter_final", "🏆 Final Quiz: Complete Java Test", 
            "🎯 Comprehensive Java Assessment!\n\n" +
            "Test your complete Java knowledge with 100 questions!\n\n" +
            "📊 Difficulty Levels:\n" +
            "• Easy: Questions 1-15\n" +
            "• Medium: Questions 16-30\n" +
            "• Hard: Questions 31-50\n" +
            "• Very Hard: Questions 51-100\n\n" +
            "⏱️ Time: 120 minutes\n" +
            "🎯 Passing: 70%\n\n" +
            "Complete all 12 modules before attempting!", 
            "", "", "", 
            "Click 'Take Quiz' for final assessment!", 
            "Q: Ready for final challenge?", 13));
        
        return lessons;
    }
    
    // Helper method to create Java lessons with bilingual support
    private Lesson createJavaLesson(int lessonNum, int chapterNum, String title,
                                    String explanationEn, String syntax, String code, 
                                    String output, String notes, String practice, 
                                    String explanationHi) {
        Lesson lesson = new Lesson(
            "java_lesson_" + lessonNum,
            "java_chapter_" + chapterNum,
            title,
            explanationEn,
            syntax,
            code,
            output,
            notes,
            practice,
            lessonNum
        );
        lesson.setExplanationHinglish(explanationHi);
        return lesson;
    }
}

