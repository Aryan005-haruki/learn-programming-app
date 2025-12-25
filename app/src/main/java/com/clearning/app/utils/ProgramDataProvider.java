package com.clearning.app.utils;

import com.clearning.app.models.Program;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramDataProvider {
    private static Map<String, List<Program>> programsByCategory = new HashMap<>();

    static {
        initializePrograms();
    }
    
    // Note: This file contains 100+ C programs across 10 categories for comprehensive learning

    private static void initializePrograms() {
        // ==================== BASIC PROGRAMS ====================
        List<Program> basicPrograms = new ArrayList<>();
        basicPrograms.add(new Program(
            "Hello World",
            "A simple program to print 'Hello, World!' on the screen.",
            "#include <stdio.h>\n\nint main() {\n    printf(\"Hello, World!\\n\");\n    return 0;\n}",
            "Hello, World!"
        ));
        basicPrograms.add(new Program(
            "Sum of Two Numbers",
            "Program to add two numbers and display the result.",
            "#include <stdio.h>\n\nint main() {\n    int a = 10, b = 20, sum;\n    sum = a + b;\n    printf(\"Sum = %d\\n\", sum);\n    return 0;\n}",
            "Sum = 30"
        ));
        basicPrograms.add(new Program(
            "Swap Two Numbers",
            "Swap two numbers using a temporary variable.",
            "#include <stdio.h>\n\nint main() {\n    int a = 5, b = 10, temp;\n    printf(\"Before: a=%d, b=%d\\n\", a, b);\n    temp = a; a = b; b = temp;\n    printf(\"After: a=%d, b=%d\\n\", a, b);\n    return 0;\n}",
            "Before: a=5, b=10\nAfter: a=10, b=5"
        ));
        basicPrograms.add(new Program(
            "Even or Odd",
            "Check if a number is even or odd.",
            "#include <stdio.h>\n\nint main() {\n    int num = 7;\n    if(num % 2 == 0)\n        printf(\"%d is even\\n\", num);\n    else\n        printf(\"%d is odd\\n\", num);\n    return 0;\n}",
            "7 is odd"
        ));
        basicPrograms.add(new Program(
            "Largest of Three Numbers",
            "Find the largest among three numbers.",
            "#include <stdio.h>\n\nint main() {\n    int a=10, b=25, c=15, max;\n    max = (a>b) ? ((a>c)?a:c) : ((b>c)?b:c);\n    printf(\"Largest = %d\\n\", max);\n    return 0;\n}",
            "Largest = 25"
        ));
        basicPrograms.add(new Program(
            "Leap Year Check",
            "Check if a year is a leap year.",
            "#include <stdio.h>\n\nint main() {\n    int year = 2024;\n    if((year%4==0 && year%100!=0) || year%400==0)\n        printf(\"%d is a leap year\\n\", year);\n    else\n        printf(\"%d is not a leap year\\n\", year);\n    return 0;\n}",
            "2024 is a leap year"
        ));
        basicPrograms.add(new Program(
            "Multiplication Table",
            "Print multiplication table of a number.",
            "#include <stdio.h>\n\nint main() {\n    int n = 5, i;\n    for(i=1; i<=10; i++)\n        printf(\"%d x %d = %d\\n\", n, i, n*i);\n    return 0;\n}",
            "5 x 1 = 5\n5 x 2 = 10\n...\n5 x 10 = 50"
        ));
        programsByCategory.put("Basic", basicPrograms);

        // ==================== ARRAY PROGRAMS ====================
        List<Program> arrayPrograms = new ArrayList<>();
        arrayPrograms.add(new Program(
            "Array Sum",
            "Calculate sum of all elements in an array.",
            "#include <stdio.h>\n\nint main() {\n    int arr[] = {1,2,3,4,5}, sum=0, i;\n    for(i=0; i<5; i++) sum += arr[i];\n    printf(\"Sum = %d\\n\", sum);\n    return 0;\n}",
            "Sum = 15"
        ));
        arrayPrograms.add(new Program(
            "Find Largest Element",
            "Find the largest element in an array.",
            "#include <stdio.h>\n\nint main() {\n    int arr[] = {10,25,5,30,15}, max=arr[0], i;\n    for(i=1; i<5; i++)\n        if(arr[i]>max) max=arr[i];\n    printf(\"Largest = %d\\n\", max);\n    return 0;\n}",
            "Largest = 30"
        ));
        arrayPrograms.add(new Program(
            "Array in Ascending Order",
            "Sort array in ascending order using bubble sort.",
            "#include <stdio.h>\n\nint main() {\n    int arr[] = {5,2,8,1,9}, i, j, temp;\n    for(i=0; i<4; i++)\n        for(j=0; j<4-i; j++)\n            if(arr[j]>arr[j+1]) {\n                temp=arr[j]; arr[j]=arr[j+1]; arr[j+1]=temp;\n            }\n    printf(\"Sorted: \");\n    for(i=0; i<5; i++) printf(\"%d \", arr[i]);\n    return 0;\n}",
            "Sorted: 1 2 5 8 9"
        ));
        arrayPrograms.add(new Program(
            "Array Initialization",
            "Different ways to initialize arrays.",
            "#include <stdio.h>\n\nint main() {\n    int arr1[5] = {1,2,3,4,5};\n    int arr2[] = {10,20,30};\n    int i;\n    printf(\"Array 1: \");\n    for(i=0; i<5; i++) printf(\"%d \", arr1[i]);\n    printf(\"\\nArray 2: \");\n    for(i=0; i<3; i++) printf(\"%d \", arr2[i]);\n    return 0;\n}",
            "Array 1: 1 2 3 4 5\nArray 2: 10 20 30"
        ));
        arrayPrograms.add(new Program(
            "Find Smallest Element",
            "Find the smallest element in an array.",
            "#include <stdio.h>\n\nint main() {\n    int arr[] = {10,25,5,30,15}, min=arr[0], i;\n    for(i=1; i<5; i++)\n        if(arr[i]<min) min=arr[i];\n    printf(\"Smallest = %d\\n\", min);\n    return 0;\n}",
            "Smallest = 5"
        ));
        arrayPrograms.add(new Program(
            "Reverse an Array",
            "Reverse the elements of an array.",
            "#include <stdio.h>\n\nint main() {\n    int arr[] = {1,2,3,4,5}, i, temp;\n    for(i=0; i<2; i++) {\n        temp = arr[i];\n        arr[i] = arr[4-i];\n        arr[4-i] = temp;\n    }\n    printf(\"Reversed: \");\n    for(i=0; i<5; i++) printf(\"%d \", arr[i]);\n    return 0;\n}",
            "Reversed: 5 4 3 2 1"
        ));
        arrayPrograms.add(new Program(
            "Search Element in Array",
            "Linear search to find an element.",
            "#include <stdio.h>\n\nint main() {\n    int arr[] = {10,20,30,40,50}, key=30, i, found=0;\n    for(i=0; i<5; i++) {\n        if(arr[i]==key) { found=1; break; }\n    }\n    if(found) printf(\"%d found at index %d\\n\", key, i);\n    else printf(\"%d not found\\n\", key);\n    return 0;\n}",
            "30 found at index 2"
        ));
        arrayPrograms.add(new Program(
            "Count Even and Odd",
            "Count even and odd numbers in array.",
            "#include <stdio.h>\n\nint main() {\n    int arr[] = {1,2,3,4,5,6}, even=0, odd=0, i;\n    for(i=0; i<6; i++)\n        if(arr[i]%2==0) even++; else odd++;\n    printf(\"Even: %d, Odd: %d\\n\", even, odd);\n    return 0;\n}",
            "Even: 3, Odd: 3"
        ));
        arrayPrograms.add(new Program(
            "Copy Array",
            "Copy elements from one array to another.",
            "#include <stdio.h>\n\nint main() {\n    int arr1[] = {1,2,3,4,5}, arr2[5], i;\n    for(i=0; i<5; i++) arr2[i] = arr1[i];\n    printf(\"Copied array: \");\n    for(i=0; i<5; i++) printf(\"%d \", arr2[i]);\n    return 0;\n}",
            "Copied array: 1 2 3 4 5"
        ));
        arrayPrograms.add(new Program(
            "2D Matrix Addition",
            "Add two 2x2 matrices.",
            "#include <stdio.h>\n\nint main() {\n    int a[2][2]={{1,2},{3,4}}, b[2][2]={{5,6},{7,8}}, c[2][2], i, j;\n    for(i=0; i<2; i++)\n        for(j=0; j<2; j++)\n            c[i][j] = a[i][j] + b[i][j];\n    printf(\"Result:\\n\");\n    for(i=0; i<2; i++) {\n        for(j=0; j<2; j++) printf(\"%d \", c[i][j]);\n        printf(\"\\n\");\n    }\n    return 0;\n}",
            "Result:\n6 8\n10 12"
        ));
        programsByCategory.put("Array", arrayPrograms);

        // ==================== FUNCTIONS PROGRAMS ====================
        List<Program> functionPrograms = new ArrayList<>();
        functionPrograms.add(new Program(
            "Factorial using Function",
            "Calculate factorial using recursion.",
            "#include <stdio.h>\n\nint factorial(int n) {\n    if(n==0) return 1;\n    return n * factorial(n-1);\n}\n\nint main() {\n    printf(\"Factorial of 5 = %d\\n\", factorial(5));\n    return 0;\n}",
            "Factorial of 5 = 120"
        ));
        functionPrograms.add(new Program(
            "Prime Check Function",
            "Check if number is prime using function.",
            "#include <stdio.h>\n\nint isPrime(int n) {\n    int i;\n    if(n<2) return 0;\n    for(i=2; i<=n/2; i++)\n        if(n%i==0) return 0;\n    return 1;\n}\n\nint main() {\n    int num = 29;\n    if(isPrime(num)) printf(\"%d is prime\\n\", num);\n    else printf(\"%d is not prime\\n\", num);\n    return 0;\n}",
            "29 is prime"
        ));
        functionPrograms.add(new Program(
            "Fibonacci using Recursion",
            "Generate Fibonacci series using recursion.",
            "#include <stdio.h>\n\nint fib(int n) {\n    if(n<=1) return n;\n    return fib(n-1) + fib(n-2);\n}\n\nint main() {\n    int i;\n    printf(\"Fibonacci: \");\n    for(i=0; i<10; i++) printf(\"%d \", fib(i));\n    return 0;\n}",
            "Fibonacci: 0 1 1 2 3 5 8 13 21 34"
        ));
        functionPrograms.add(new Program(
            "GCD using Function",
            "Find GCD of two numbers using Euclidean algorithm.",
            "#include <stdio.h>\n\nint gcd(int a, int b) {\n    if(b==0) return a;\n    return gcd(b, a%b);\n}\n\nint main() {\n    printf(\"GCD of 48 and 18 = %d\\n\", gcd(48, 18));\n    return 0;\n}",
            "GCD of 48 and 18 = 6"
        ));
        functionPrograms.add(new Program(
            "Power Function",
            "Calculate power using recursion.",
            "#include <stdio.h>\n\nint power(int base, int exp) {\n    if(exp==0) return 1;\n    return base * power(base, exp-1);\n}\n\nint main() {\n    printf(\"2^5 = %d\\n\", power(2, 5));\n    return 0;\n}",
            "2^5 = 32"
        ));
        programsByCategory.put("Functions", functionPrograms);

        // ==================== FORMULA PROGRAMS ====================
        List<Program> formulaPrograms = new ArrayList<>();
        formulaPrograms.add(new Program(
            "Area of Circle",
            "Calculate area of a circle.",
            "#include <stdio.h>\n#define PI 3.14159\n\nint main() {\n    float r = 5.0, area;\n    area = PI * r * r;\n    printf(\"Area = %.2f\\n\", area);\n    return 0;\n}",
            "Area = 78.54"
        ));
        formulaPrograms.add(new Program(
            "Simple Interest",
            "Calculate simple interest.",
            "#include <stdio.h>\n\nint main() {\n    float p=1000, r=5, t=2, si;\n    si = (p*r*t)/100;\n    printf(\"Simple Interest = %.2f\\n\", si);\n    return 0;\n}",
            "Simple Interest = 100.00"
        ));
        formulaPrograms.add(new Program(
            "Compound Interest",
            "Calculate compound interest.",
            "#include <stdio.h>\n#include <math.h>\n\nint main() {\n    float p=1000, r=5, t=2, ci;\n    ci = p * (pow(1+r/100, t) - 1);\n    printf(\"Compound Interest = %.2f\\n\", ci);\n    return 0;\n}",
            "Compound Interest = 102.50"
        ));
        formulaPrograms.add(new Program(
            "Area of Triangle",
            "Calculate area using base and height.",
            "#include <stdio.h>\n\nint main() {\n    float base=10, height=5, area;\n    area = 0.5 * base * height;\n    printf(\"Area = %.2f\\n\", area);\n    return 0;\n}",
            "Area = 25.00"
        ));
        formulaPrograms.add(new Program(
            "Perimeter of Rectangle",
            "Calculate perimeter of rectangle.",
            "#include <stdio.h>\n\nint main() {\n    float length=10, width=5, perimeter;\n    perimeter = 2 * (length + width);\n    printf(\"Perimeter = %.2f\\n\", perimeter);\n    return 0;\n}",
            "Perimeter = 30.00"
        ));
        formulaPrograms.add(new Program(
            "Celsius to Fahrenheit",
            "Convert temperature from Celsius to Fahrenheit.",
            "#include <stdio.h>\n\nint main() {\n    float celsius=25, fahrenheit;\n    fahrenheit = (celsius * 9/5) + 32;\n    printf(\"%.2f°C = %.2f°F\\n\", celsius, fahrenheit);\n    return 0;\n}",
            "25.00°C = 77.00°F"
        ));
        formulaPrograms.add(new Program(
            "Distance Formula",
            "Calculate distance between two points.",
            "#include <stdio.h>\n#include <math.h>\n\nint main() {\n    float x1=1, y1=1, x2=4, y2=5, dist;\n    dist = sqrt(pow(x2-x1,2) + pow(y2-y1,2));\n    printf(\"Distance = %.2f\\n\", dist);\n    return 0;\n}",
            "Distance = 5.00"
        ));
        formulaPrograms.add(new Program(
            "Speed Formula",
            "Calculate speed = distance/time.",
            "#include <stdio.h>\n\nint main() {\n    float distance=100, time=2, speed;\n    speed = distance / time;\n    printf(\"Speed = %.2f km/h\\n\", speed);\n    return 0;\n}",
            "Speed = 50.00 km/h"
        ));
        programsByCategory.put("Formula", formulaPrograms);

        // ==================== DATA STRUCTURES ====================
        List<Program> dataStructurePrograms = new ArrayList<>();
        dataStructurePrograms.add(new Program(
            "Stack Implementation",
            "Simple stack using array.",
            "#include <stdio.h>\n#define MAX 5\n\nint stack[MAX], top=-1;\n\nvoid push(int val) {\n    if(top==MAX-1) printf(\"Stack Full\\n\");\n    else stack[++top] = val;\n}\n\nint main() {\n    push(10); push(20); push(30);\n    printf(\"Top = %d\\n\", stack[top]);\n    return 0;\n}",
            "Top = 30"
        ));
        dataStructurePrograms.add(new Program(
            "Queue Implementation",
            "Simple queue using array.",
            "#include <stdio.h>\n#define MAX 5\n\nint queue[MAX], front=-1, rear=-1;\n\nvoid enqueue(int val) {\n    if(rear==MAX-1) printf(\"Queue Full\\n\");\n    else {\n        if(front==-1) front=0;\n        queue[++rear] = val;\n    }\n}\n\nint main() {\n    enqueue(10); enqueue(20); enqueue(30);\n    printf(\"Front = %d\\n\", queue[front]);\n    return 0;\n}",
            "Front = 10"
        ));
        dataStructurePrograms.add(new Program(
            "Linked List Node",
            "Create a simple linked list node.",
            "#include <stdio.h>\n#include <stdlib.h>\n\nstruct Node {\n    int data;\n    struct Node* next;\n};\n\nint main() {\n    struct Node* head = (struct Node*)malloc(sizeof(struct Node));\n    head->data = 10;\n    head->next = NULL;\n    printf(\"Node data = %d\\n\", head->data);\n    return 0;\n}",
            "Node data = 10"
        ));
        programsByCategory.put("Data Structures", dataStructurePrograms);

        // ==================== FILE HANDLING ====================
        List<Program> filePrograms = new ArrayList<>();
        filePrograms.add(new Program(
            "Write to File",
            "Write text to a file.",
            "#include <stdio.h>\n\nint main() {\n    FILE *fp = fopen(\"test.txt\", \"w\");\n    if(fp==NULL) {\n        printf(\"Error\\n\");\n        return 1;\n    }\n    fprintf(fp, \"Hello, File!\\n\");\n    fclose(fp);\n    printf(\"File written\\n\");\n    return 0;\n}",
            "File written"
        ));
        filePrograms.add(new Program(
            "Read from File",
            "Read text from a file.",
            "#include <stdio.h>\n\nint main() {\n    FILE *fp = fopen(\"test.txt\", \"r\");\n    char str[100];\n    if(fp==NULL) {\n        printf(\"Error\\n\");\n        return 1;\n    }\n    fgets(str, 100, fp);\n    printf(\"Read: %s\", str);\n    fclose(fp);\n    return 0;\n}",
            "Read: Hello, File!"
        ));
        programsByCategory.put("File Handling", filePrograms);

        // ==================== POINTERS ====================
        List<Program> pointerPrograms = new ArrayList<>();
        pointerPrograms.add(new Program(
            "Pointer Basics",
            "Introduction to pointers and addresses.",
            "#include <stdio.h>\n\nint main() {\n    int num = 10;\n    int *ptr = &num;\n    printf(\"Value = %d\\n\", *ptr);\n    printf(\"Address = %p\\n\", ptr);\n    return 0;\n}",
            "Value = 10\nAddress = 0x..."
        ));
        pointerPrograms.add(new Program(
            "Swap using Pointers",
            "Swap two numbers using pointers.",
            "#include <stdio.h>\n\nvoid swap(int *a, int *b) {\n    int temp = *a;\n    *a = *b;\n    *b = temp;\n}\n\nint main() {\n    int x=5, y=10;\n    printf(\"Before: x=%d, y=%d\\n\", x, y);\n    swap(&x, &y);\n    printf(\"After: x=%d, y=%d\\n\", x, y);\n    return 0;\n}",
            "Before: x=5, y=10\nAfter: x=10, y=5"
        ));
        pointerPrograms.add(new Program(
            "Pointer Arithmetic",
            "Demonstrate pointer arithmetic operations.",
            "#include <stdio.h>\n\nint main() {\n    int arr[] = {10,20,30,40,50};\n    int *ptr = arr;\n    printf(\"First = %d\\n\", *ptr);\n    ptr++;\n    printf(\"Second = %d\\n\", *ptr);\n    ptr += 2;\n    printf(\"Fourth = %d\\n\", *ptr);\n    return 0;\n}",
            "First = 10\nSecond = 20\nFourth = 40"
        ));
        pointerPrograms.add(new Program(
            "Array and Pointer",
            "Access array elements using pointers.",
            "#include <stdio.h>\n\nint main() {\n    int arr[] = {1,2,3,4,5}, i;\n    int *ptr = arr;\n    printf(\"Array: \");\n    for(i=0; i<5; i++)\n        printf(\"%d \", *(ptr+i));\n    return 0;\n}",
            "Array: 1 2 3 4 5"
        ));
        pointerPrograms.add(new Program(
            "Pointer to Pointer",
            "Double pointer example.",
            "#include <stdio.h>\n\nint main() {\n    int num = 100;\n    int *ptr1 = &num;\n    int **ptr2 = &ptr1;\n    printf(\"Value = %d\\n\", **ptr2);\n    printf(\"Address = %p\\n\", *ptr2);\n    return 0;\n}",
            "Value = 100\nAddress = 0x..."
        ));
        programsByCategory.put("Pointers", pointerPrograms);

        // ==================== LOOPS ====================
        List<Program> loopPrograms = new ArrayList<>();
        loopPrograms.add(new Program(
            "For Loop - Print 1 to 10",
            "Print numbers from 1 to 10 using for loop.",
            "#include <stdio.h>\n\nint main() {\n    int i;\n    for(i=1; i<=10; i++)\n        printf(\"%d \", i);\n    return 0;\n}",
            "1 2 3 4 5 6 7 8 9 10"
        ));
        loopPrograms.add(new Program(
            "While Loop - Sum of N Numbers",
            "Calculate sum of first N natural numbers.",
            "#include <stdio.h>\n\nint main() {\n    int n=10, sum=0, i=1;\n    while(i<=n) {\n        sum += i;\n        i++;\n    }\n    printf(\"Sum = %d\\n\", sum);\n    return 0;\n}",
            "Sum = 55"
        ));
        loopPrograms.add(new Program(
            "Do-While Loop",
            "Print numbers using do-while loop.",
            "#include <stdio.h>\n\nint main() {\n    int i=1;\n    do {\n        printf(\"%d \", i);\n        i++;\n    } while(i<=5);\n    return 0;\n}",
            "1 2 3 4 5"
        ));
        loopPrograms.add(new Program(
            "Nested Loop - Multiplication Table",
            "Print multiplication table using nested loops.",
            "#include <stdio.h>\n\nint main() {\n    int i, j;\n    for(i=1; i<=3; i++) {\n        for(j=1; j<=5; j++)\n            printf(\"%d \", i*j);\n        printf(\"\\n\");\n    }\n    return 0;\n}",
            "1 2 3 4 5\n2 4 6 8 10\n3 6 9 12 15"
        ));
        loopPrograms.add(new Program(
            "Break Statement",
            "Exit loop using break statement.",
            "#include <stdio.h>\n\nint main() {\n    int i;\n    for(i=1; i<=10; i++) {\n        if(i==6) break;\n        printf(\"%d \", i);\n    }\n    return 0;\n}",
            "1 2 3 4 5"
        ));
        loopPrograms.add(new Program(
            "Continue Statement",
            "Skip iteration using continue.",
            "#include <stdio.h>\n\nint main() {\n    int i;\n    for(i=1; i<=10; i++) {\n        if(i%2==0) continue;\n        printf(\"%d \", i);\n    }\n    return 0;\n}",
            "1 3 5 7 9"
        ));
        loopPrograms.add(new Program(
            "Infinite Loop with Break",
            "Controlled infinite loop.",
            "#include <stdio.h>\n\nint main() {\n    int count=0;\n    while(1) {\n        printf(\"%d \", count);\n        count++;\n        if(count==5) break;\n    }\n    return 0;\n}",
            "0 1 2 3 4"
        ));
        programsByCategory.put("Loops", loopPrograms);

        // ==================== STRINGS ====================
        List<Program> stringPrograms = new ArrayList<>();
        stringPrograms.add(new Program(
            "String Length",
            "Find length of a string.",
            "#include <stdio.h>\n#include <string.h>\n\nint main() {\n    char str[] = \"Hello\";\n    printf(\"Length = %d\\n\", strlen(str));\n    return 0;\n}",
            "Length = 5"
        ));
        stringPrograms.add(new Program(
            "String Copy",
            "Copy one string to another.",
            "#include <stdio.h>\n#include <string.h>\n\nint main() {\n    char src[] = \"Hello\";\n    char dest[20];\n    strcpy(dest, src);\n    printf(\"Copied: %s\\n\", dest);\n    return 0;\n}",
            "Copied: Hello"
        ));
        stringPrograms.add(new Program(
            "String Concatenation",
            "Concatenate two strings.",
            "#include <stdio.h>\n#include <string.h>\n\nint main() {\n    char str1[20] = \"Hello \";\n    char str2[] = \"World\";\n    strcat(str1, str2);\n    printf(\"%s\\n\", str1);\n    return 0;\n}",
            "Hello World"
        ));
        stringPrograms.add(new Program(
            "String Compare",
            "Compare two strings.",
            "#include <stdio.h>\n#include <string.h>\n\nint main() {\n    char str1[] = \"Hello\";\n    char str2[] = \"Hello\";\n    if(strcmp(str1, str2)==0)\n        printf(\"Strings are equal\\n\");\n    else\n        printf(\"Strings are not equal\\n\");\n    return 0;\n}",
            "Strings are equal"
        ));
        stringPrograms.add(new Program(
            "Reverse a String",
            "Reverse a string manually.",
            "#include <stdio.h>\n#include <string.h>\n\nint main() {\n    char str[] = \"Hello\";\n    int len = strlen(str), i;\n    char temp;\n    for(i=0; i<len/2; i++) {\n        temp = str[i];\n        str[i] = str[len-1-i];\n        str[len-1-i] = temp;\n    }\n    printf(\"Reversed: %s\\n\", str);\n    return 0;\n}",
            "Reversed: olleH"
        ));
        stringPrograms.add(new Program(
            "Palindrome Check",
            "Check if string is palindrome.",
            "#include <stdio.h>\n#include <string.h>\n\nint main() {\n    char str[] = \"madam\";\n    int len = strlen(str), i, flag=1;\n    for(i=0; i<len/2; i++) {\n        if(str[i] != str[len-1-i]) {\n            flag = 0; break;\n        }\n    }\n    if(flag) printf(\"Palindrome\\n\");\n    else printf(\"Not palindrome\\n\");\n    return 0;\n}",
            "Palindrome"
        ));
        stringPrograms.add(new Program(
            "Count Vowels",
            "Count vowels in a string.",
            "#include <stdio.h>\n#include <string.h>\n\nint main() {\n    char str[] = \"Hello World\";\n    int count=0, i;\n    for(i=0; str[i]; i++) {\n        char c = str[i];\n        if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||\n           c=='A'||c=='E'||c=='I'||c=='O'||c=='U')\n            count++;\n    }\n    printf(\"Vowels = %d\\n\", count);\n    return 0;\n}",
            "Vowels = 3"
        ));
        stringPrograms.add(new Program(
            "Uppercase to Lowercase",
            "Convert string to lowercase.",
            "#include <stdio.h>\n#include <ctype.h>\n\nint main() {\n    char str[] = \"HELLO\";\n    int i;\n    for(i=0; str[i]; i++)\n        str[i] = tolower(str[i]);\n    printf(\"%s\\n\", str);\n    return 0;\n}",
            "hello"
        ));
        programsByCategory.put("Strings", stringPrograms);

        // ==================== PATTERNS ====================
        List<Program> patternPrograms = new ArrayList<>();
        patternPrograms.add(new Program(
            "Square Pattern",
            "Print a square pattern of stars.",
            "#include <stdio.h>\n\nint main() {\n    int i, j, n=5;\n    for(i=0; i<n; i++) {\n        for(j=0; j<n; j++)\n            printf(\"* \");\n        printf(\"\\n\");\n    }\n    return 0;\n}",
            "* * * * *\n* * * * *\n* * * * *\n* * * * *\n* * * * *"
        ));
        patternPrograms.add(new Program(
            "Right Triangle",
            "Print right-angled triangle pattern.",
            "#include <stdio.h>\n\nint main() {\n    int i, j, n=5;\n    for(i=1; i<=n; i++) {\n        for(j=1; j<=i; j++)\n            printf(\"* \");\n        printf(\"\\n\");\n    }\n    return 0;\n}",
            "*\n* *\n* * *\n* * * *\n* * * * *"
        ));
        patternPrograms.add(new Program(
            "Inverted Triangle",
            "Print inverted triangle pattern.",
            "#include <stdio.h>\n\nint main() {\n    int i, j, n=5;\n    for(i=n; i>=1; i--) {\n        for(j=1; j<=i; j++)\n            printf(\"* \");\n        printf(\"\\n\");\n    }\n    return 0;\n}",
            "* * * * *\n* * * *\n* * *\n* *\n*"
        ));
        patternPrograms.add(new Program(
            "Pyramid Pattern",
            "Print pyramid pattern.",
            "#include <stdio.h>\n\nint main() {\n    int i, j, n=5;\n    for(i=1; i<=n; i++) {\n        for(j=1; j<=n-i; j++) printf(\" \");\n        for(j=1; j<=2*i-1; j++) printf(\"*\");\n        printf(\"\\n\");\n    }\n    return 0;\n}",
            "    *\n   ***\n  *****\n *******\n*********"
        ));
        patternPrograms.add(new Program(
            "Diamond Pattern",
            "Print diamond pattern.",
            "#include <stdio.h>\n\nint main() {\n    int i, j, n=5;\n    for(i=1; i<=n; i++) {\n        for(j=1; j<=n-i; j++) printf(\" \");\n        for(j=1; j<=2*i-1; j++) printf(\"*\");\n        printf(\"\\n\");\n    }\n    for(i=n-1; i>=1; i--) {\n        for(j=1; j<=n-i; j++) printf(\" \");\n        for(j=1; j<=2*i-1; j++) printf(\"*\");\n        printf(\"\\n\");\n    }\n    return 0;\n}",
            "    *\n   ***\n  *****\n *******\n*********\n *******\n  *****\n   ***\n    *"
        ));
        patternPrograms.add(new Program(
            "Number Pattern",
            "Print number pattern.",
            "#include <stdio.h>\n\nint main() {\n    int i, j;\n    for(i=1; i<=5; i++) {\n        for(j=1; j<=i; j++)\n            printf(\"%d \", j);\n        printf(\"\\n\");\n    }\n    return 0;\n}",
            "1\n1 2\n1 2 3\n1 2 3 4\n1 2 3 4 5"
        ));
        patternPrograms.add(new Program(
            "Floyd's Triangle",
            "Print Floyd's triangle.",
            "#include <stdio.h>\n\nint main() {\n    int i, j, num=1;\n    for(i=1; i<=5; i++) {\n        for(j=1; j<=i; j++)\n            printf(\"%d \", num++);\n        printf(\"\\n\");\n    }\n    return 0;\n}",
            "1\n2 3\n4 5 6\n7 8 9 10\n11 12 13 14 15"
        ));
        patternPrograms.add(new Program(
            "Hollow Square",
            "Print hollow square pattern.",
            "#include <stdio.h>\n\nint main() {\n    int i, j, n=5;\n    for(i=1; i<=n; i++) {\n        for(j=1; j<=n; j++) {\n            if(i==1 || i==n || j==1 || j==n)\n                printf(\"* \");\n            else\n                printf(\"  \");\n        }\n        printf(\"\\n\");\n    }\n    return 0;\n}",
            "* * * * *\n*       *\n*       *\n*       *\n* * * * *"
        ));
        programsByCategory.put("Patterns", patternPrograms);

        // ==================== NUMBERS ====================
        List<Program> numberPrograms = new ArrayList<>();
        numberPrograms.add(new Program(
            "Prime Number",
            "Check if number is prime.",
            "#include <stdio.h>\n\nint main() {\n    int n=29, i, isPrime=1;\n    for(i=2; i<=n/2; i++)\n        if(n%i==0) { isPrime=0; break; }\n    if(isPrime) printf(\"%d is prime\\n\", n);\n    else printf(\"%d is not prime\\n\", n);\n    return 0;\n}",
            "29 is prime"
        ));
        numberPrograms.add(new Program(
            "Factorial",
            "Calculate factorial of a number.",
            "#include <stdio.h>\n\nint main() {\n    int n=5, fact=1, i;\n    for(i=1; i<=n; i++) fact *= i;\n    printf(\"Factorial of %d = %d\\n\", n, fact);\n    return 0;\n}",
            "Factorial of 5 = 120"
        ));
        numberPrograms.add(new Program(
            "Fibonacci Series",
            "Print Fibonacci series.",
            "#include <stdio.h>\n\nint main() {\n    int n=10, t1=0, t2=1, i, next;\n    printf(\"Fibonacci: \");\n    for(i=1; i<=n; i++) {\n        printf(\"%d \", t1);\n        next = t1+t2; t1=t2; t2=next;\n    }\n    return 0;\n}",
            "Fibonacci: 0 1 1 2 3 5 8 13 21 34"
        ));
        numberPrograms.add(new Program(
            "Armstrong Number",
            "Check Armstrong number.",
            "#include <stdio.h>\n#include <math.h>\n\nint main() {\n    int n=153, sum=0, temp=n;\n    while(temp>0) {\n        int digit=temp%10;\n        sum += pow(digit,3);\n        temp /= 10;\n    }\n    if(sum==n) printf(\"Armstrong\\n\");\n    else printf(\"Not Armstrong\\n\");\n    return 0;\n}",
            "Armstrong"
        ));
        numberPrograms.add(new Program(
            "Palindrome Number",
            "Check if number is palindrome.",
            "#include <stdio.h>\n\nint main() {\n    int n=121, rev=0, temp=n;\n    while(temp>0) {\n        rev = rev*10 + temp%10;\n        temp /= 10;\n    }\n    if(rev==n) printf(\"Palindrome\\n\");\n    else printf(\"Not palindrome\\n\");\n    return 0;\n}",
            "Palindrome"
        ));
        numberPrograms.add(new Program(
            "Perfect Number",
            "Check if number is perfect.",
            "#include <stdio.h>\n\nint main() {\n    int n=28, sum=0, i;\n    for(i=1; i<n; i++)\n        if(n%i==0) sum += i;\n    if(sum==n) printf(\"Perfect number\\n\");\n    else printf(\"Not perfect\\n\");\n    return 0;\n}",
            "Perfect number"
        ));
        numberPrograms.add(new Program(
            "Strong Number",
            "Check strong number (sum of factorials).",
            "#include <stdio.h>\n\nint main() {\n    int n=145, sum=0, temp=n;\n    while(temp>0) {\n        int digit=temp%10, fact=1;\n        for(int i=1; i<=digit; i++) fact*=i;\n        sum += fact;\n        temp /= 10;\n    }\n    if(sum==n) printf(\"Strong number\\n\");\n    else printf(\"Not strong\\n\");\n    return 0;\n}",
            "Strong number"
        ));
        numberPrograms.add(new Program(
            "HCF/GCD",
            "Find HCF of two numbers.",
            "#include <stdio.h>\n\nint main() {\n    int a=48, b=18, hcf, i;\n    for(i=1; i<=a && i<=b; i++)\n        if(a%i==0 && b%i==0) hcf=i;\n    printf(\"HCF = %d\\n\", hcf);\n    return 0;\n}",
            "HCF = 6"
        ));
        numberPrograms.add(new Program(
            "LCM",
            "Find LCM of two numbers.",
            "#include <stdio.h>\n\nint main() {\n    int a=12, b=18, max;\n    max = (a>b) ? a : b;\n    while(1) {\n        if(max%a==0 && max%b==0) {\n            printf(\"LCM = %d\\n\", max);\n            break;\n        }\n        max++;\n    }\n    return 0;\n}",
            "LCM = 36"
        ));
        numberPrograms.add(new Program(
            "Binary to Decimal",
            "Convert binary to decimal.",
            "#include <stdio.h>\n#include <math.h>\n\nint main() {\n    int binary=1010, decimal=0, i=0;\n    while(binary>0) {\n        int digit = binary%10;\n        decimal += digit * pow(2,i);\n        i++;\n        binary /= 10;\n    }\n    printf(\"Decimal = %d\\n\", decimal);\n    return 0;\n}",
            "Decimal = 10"
        ));
        programsByCategory.put("Numbers", numberPrograms);

        // ==================== RECURSION ====================
        List<Program> recursionPrograms = new ArrayList<>();
        recursionPrograms.add(new Program(
            "Factorial Recursion",
            "Factorial using recursion.",
            "#include <stdio.h>\n\nint factorial(int n) {\n    if(n==0) return 1;\n    return n * factorial(n-1);\n}\n\nint main() {\n    printf(\"Factorial = %d\\n\", factorial(5));\n    return 0;\n}",
            "Factorial = 120"
        ));
        recursionPrograms.add(new Program(
            "Fibonacci Recursion",
            "Fibonacci using recursion.",
            "#include <stdio.h>\n\nint fib(int n) {\n    if(n<=1) return n;\n    return fib(n-1) + fib(n-2);\n}\n\nint main() {\n    for(int i=0; i<10; i++)\n        printf(\"%d \", fib(i));\n    return 0;\n}",
            "0 1 1 2 3 5 8 13 21 34"
        ));
        recursionPrograms.add(new Program(
            "Sum of N Numbers",
            "Sum using recursion.",
            "#include <stdio.h>\n\nint sum(int n) {\n    if(n==0) return 0;\n    return n + sum(n-1);\n}\n\nint main() {\n    printf(\"Sum = %d\\n\", sum(10));\n    return 0;\n}",
            "Sum = 55"
        ));
        recursionPrograms.add(new Program(
            "Power Recursion",
            "Calculate power using recursion.",
            "#include <stdio.h>\n\nint power(int base, int exp) {\n    if(exp==0) return 1;\n    return base * power(base, exp-1);\n}\n\nint main() {\n    printf(\"2^5 = %d\\n\", power(2,5));\n    return 0;\n}",
            "2^5 = 32"
        ));
        recursionPrograms.add(new Program(
            "GCD Recursion",
            "GCD using Euclidean algorithm.",
            "#include <stdio.h>\n\nint gcd(int a, int b) {\n    if(b==0) return a;\n    return gcd(b, a%b);\n}\n\nint main() {\n    printf(\"GCD = %d\\n\", gcd(48,18));\n    return 0;\n}",
            "GCD = 6"
        ));
        recursionPrograms.add(new Program(
            "Reverse Number Recursion",
            "Reverse number using recursion.",
            "#include <stdio.h>\n\nint rev=0;\nvoid reverse(int n) {\n    if(n==0) return;\n    rev = rev*10 + n%10;\n    reverse(n/10);\n}\n\nint main() {\n    reverse(123);\n    printf(\"Reversed = %d\\n\", rev);\n    return 0;\n}",
            "Reversed = 321"
        ));
        recursionPrograms.add(new Program(
            "Sum of Digits Recursion",
            "Sum digits using recursion.",
            "#include <stdio.h>\n\nint sumDigits(int n) {\n    if(n==0) return 0;\n    return n%10 + sumDigits(n/10);\n}\n\nint main() {\n    printf(\"Sum = %d\\n\", sumDigits(123));\n    return 0;\n}",
            "Sum = 6"
        ));
        recursionPrograms.add(new Program(
            "Binary Search Recursion",
            "Binary search using recursion.",
            "#include <stdio.h>\n\nint binarySearch(int arr[], int l, int r, int x) {\n    if(r>=l) {\n        int mid = l+(r-l)/2;\n        if(arr[mid]==x) return mid;\n        if(arr[mid]>x) return binarySearch(arr,l,mid-1,x);\n        return binarySearch(arr,mid+1,r,x);\n    }\n    return -1;\n}\n\nint main() {\n    int arr[]={2,3,4,10,40};\n    int result = binarySearch(arr,0,4,10);\n    printf(\"Found at index %d\\n\", result);\n    return 0;\n}",
            "Found at index 3"
        ));
        programsByCategory.put("Recursion", recursionPrograms);

        // ==================== GRAPHICS ====================
        List<Program> graphicsPrograms = new ArrayList<>();
        graphicsPrograms.add(new Program(
            "Draw Line",
            "Draw a horizontal line.",
            "#include <stdio.h>\n\nint main() {\n    int length = 20;\n    for(int i=0; i<length; i++)\n        printf(\"-\");\n    printf(\"\\n\");\n    return 0;\n}",
            "--------------------"
        ));
        graphicsPrograms.add(new Program(
            "Draw Rectangle",
            "Draw rectangle with borders.",
            "#include <stdio.h>\n\nint main() {\n    int rows=5, cols=10;\n    for(int i=0; i<rows; i++) {\n        for(int j=0; j<cols; j++) {\n            if(i==0||i==rows-1||j==0||j==cols-1)\n                printf(\"*\");\n            else printf(\" \");\n        }\n        printf(\"\\n\");\n    }\n    return 0;\n}",
            "**********\n*        *\n*        *\n*        *\n**********"
        ));
        graphicsPrograms.add(new Program(
            "Draw Circle Pattern",
            "Approximate circle using stars.",
            "#include <stdio.h>\n#include <math.h>\n\nint main() {\n    int r=5, i, j;\n    for(i=-r; i<=r; i++) {\n        for(j=-r; j<=r; j++) {\n            if(abs(i*i + j*j - r*r) <= r)\n                printf(\"*\");\n            else printf(\" \");\n        }\n        printf(\"\\n\");\n    }\n    return 0;\n}",
            "Circle pattern displayed"
        ));
        graphicsPrograms.add(new Program(
            "Draw Triangle",
            "Draw filled triangle.",
            "#include <stdio.h>\n\nint main() {\n    int n=5;\n    for(int i=1; i<=n; i++) {\n        for(int j=1; j<=i; j++)\n            printf(\"*\");\n        printf(\"\\n\");\n    }\n    return 0;\n}",
            "*\n**\n***\n****\n*****"
        ));
        graphicsPrograms.add(new Program(
            "Draw Diamond",
            "Draw diamond shape.",
            "#include <stdio.h>\n\nint main() {\n    int n=5, i, j;\n    for(i=1; i<=n; i++) {\n        for(j=1; j<=n-i; j++) printf(\" \");\n        for(j=1; j<=2*i-1; j++) printf(\"*\");\n        printf(\"\\n\");\n    }\n    for(i=n-1; i>=1; i--) {\n        for(j=1; j<=n-i; j++) printf(\" \");\n        for(j=1; j<=2*i-1; j++) printf(\"*\");\n        printf(\"\\n\");\n    }\n    return 0;\n}",
            "    *\n   ***\n  *****\n *******\n*********\n *******\n  *****\n   ***\n    *"
        ));
        programsByCategory.put("Graphics", graphicsPrograms);

        // ==================== OPERATORS & EXPRESSIONS ====================
        List<Program> operatorPrograms = new ArrayList<>();
        operatorPrograms.add(new Program(
            "Arithmetic Operators",
            "All arithmetic operations.",
            "#include <stdio.h>\n\nint main() {\n    int a=10, b=3;\n    printf(\"Addition: %d\\n\", a+b);\n    printf(\"Subtraction: %d\\n\", a-b);\n    printf(\"Multiplication: %d\\n\", a*b);\n    printf(\"Division: %d\\n\", a/b);\n    printf(\"Modulus: %d\\n\", a%b);\n    return 0;\n}",
            "Addition: 13\nSubtraction: 7\nMultiplication: 30\nDivision: 3\nModulus: 1"
        ));
        operatorPrograms.add(new Program(
            "Relational Operators",
            "Comparison operators.",
            "#include <stdio.h>\n\nint main() {\n    int a=5, b=10;\n    printf(\"a==b: %d\\n\", a==b);\n    printf(\"a!=b: %d\\n\", a!=b);\n    printf(\"a>b: %d\\n\", a>b);\n    printf(\"a<b: %d\\n\", a<b);\n    printf(\"a>=b: %d\\n\", a>=b);\n    printf(\"a<=b: %d\\n\", a<=b);\n    return 0;\n}",
            "a==b: 0\na!=b: 1\na>b: 0\na<b: 1\na>=b: 0\na<=b: 1"
        ));
        operatorPrograms.add(new Program(
            "Logical Operators",
            "AND, OR, NOT operators.",
            "#include <stdio.h>\n\nint main() {\n    int a=1, b=0;\n    printf(\"a && b: %d\\n\", a&&b);\n    printf(\"a || b: %d\\n\", a||b);\n    printf(\"!a: %d\\n\", !a);\n    printf(\"!b: %d\\n\", !b);\n    return 0;\n}",
            "a && b: 0\na || b: 1\n!a: 0\n!b: 1"
        ));
        operatorPrograms.add(new Program(
            "Bitwise Operators",
            "Bitwise AND, OR, XOR, NOT.",
            "#include <stdio.h>\n\nint main() {\n    int a=5, b=3;\n    printf(\"a & b: %d\\n\", a&b);\n    printf(\"a | b: %d\\n\", a|b);\n    printf(\"a ^ b: %d\\n\", a^b);\n    printf(\"~a: %d\\n\", ~a);\n    printf(\"a << 1: %d\\n\", a<<1);\n    printf(\"a >> 1: %d\\n\", a>>1);\n    return 0;\n}",
            "a & b: 1\na | b: 7\na ^ b: 6\n~a: -6\na << 1: 10\na >> 1: 2"
        ));
        operatorPrograms.add(new Program(
            "Increment/Decrement",
            "Pre and post increment/decrement.",
            "#include <stdio.h>\n\nint main() {\n    int a=5, b=5;\n    printf(\"a++: %d\\n\", a++);\n    printf(\"a: %d\\n\", a);\n    printf(\"++b: %d\\n\", ++b);\n    printf(\"b: %d\\n\", b);\n    return 0;\n}",
            "a++: 5\na: 6\n++b: 6\nb: 6"
        ));
        operatorPrograms.add(new Program(
            "Assignment Operators",
            "Compound assignment operators.",
            "#include <stdio.h>\n\nint main() {\n    int a=10;\n    a += 5; printf(\"a+=5: %d\\n\", a);\n    a -= 3; printf(\"a-=3: %d\\n\", a);\n    a *= 2; printf(\"a*=2: %d\\n\", a);\n    a /= 4; printf(\"a/=4: %d\\n\", a);\n    a %= 3; printf(\"a%%=3: %d\\n\", a);\n    return 0;\n}",
            "a+=5: 15\na-=3: 12\na*=2: 24\na/=4: 6\na%=3: 0"
        ));
        operatorPrograms.add(new Program(
            "Ternary Operator",
            "Conditional operator.",
            "#include <stdio.h>\n\nint main() {\n    int a=10, b=20;\n    int max = (a>b) ? a : b;\n    printf(\"Max = %d\\n\", max);\n    int min = (a<b) ? a : b;\n    printf(\"Min = %d\\n\", min);\n    return 0;\n}",
            "Max = 20\nMin = 10"
        ));
        operatorPrograms.add(new Program(
            "Sizeof Operator",
            "Get size of data types.",
            "#include <stdio.h>\n\nint main() {\n    printf(\"int: %d bytes\\n\", sizeof(int));\n    printf(\"float: %d bytes\\n\", sizeof(float));\n    printf(\"double: %d bytes\\n\", sizeof(double));\n    printf(\"char: %d bytes\\n\", sizeof(char));\n    return 0;\n}",
            "int: 4 bytes\nfloat: 4 bytes\ndouble: 8 bytes\nchar: 1 bytes"
        ));
        programsByCategory.put("Operators", operatorPrograms);

        // ==================== CONDITIONALS ====================
        List<Program> conditionalPrograms = new ArrayList<>();
        conditionalPrograms.add(new Program(
            "Simple If",
            "Basic if statement.",
            "#include <stdio.h>\n\nint main() {\n    int num = 10;\n    if(num > 0)\n        printf(\"Positive number\\n\");\n    return 0;\n}",
            "Positive number"
        ));
        conditionalPrograms.add(new Program(
            "If-Else",
            "If-else statement.",
            "#include <stdio.h>\n\nint main() {\n    int num = -5;\n    if(num >= 0)\n        printf(\"Positive\\n\");\n    else\n        printf(\"Negative\\n\");\n    return 0;\n}",
            "Negative"
        ));
        conditionalPrograms.add(new Program(
            "If-Else-If Ladder",
            "Multiple conditions.",
            "#include <stdio.h>\n\nint main() {\n    int marks = 75;\n    if(marks >= 90) printf(\"Grade A\\n\");\n    else if(marks >= 75) printf(\"Grade B\\n\");\n    else if(marks >= 60) printf(\"Grade C\\n\");\n    else printf(\"Grade D\\n\");\n    return 0;\n}",
            "Grade B"
        ));
        conditionalPrograms.add(new Program(
            "Nested If",
            "If inside if.",
            "#include <stdio.h>\n\nint main() {\n    int num = 10;\n    if(num > 0) {\n        if(num % 2 == 0)\n            printf(\"Positive even\\n\");\n        else\n            printf(\"Positive odd\\n\");\n    }\n    return 0;\n}",
            "Positive even"
        ));
        conditionalPrograms.add(new Program(
            "Switch Case",
            "Switch statement example.",
            "#include <stdio.h>\n\nint main() {\n    int day = 3;\n    switch(day) {\n        case 1: printf(\"Monday\\n\"); break;\n        case 2: printf(\"Tuesday\\n\"); break;\n        case 3: printf(\"Wednesday\\n\"); break;\n        default: printf(\"Invalid\\n\");\n    }\n    return 0;\n}",
            "Wednesday"
        ));
        conditionalPrograms.add(new Program(
            "Switch Calculator",
            "Calculator using switch.",
            "#include <stdio.h>\n\nint main() {\n    int a=10, b=5;\n    char op='+';\n    switch(op) {\n        case '+': printf(\"%d\\n\", a+b); break;\n        case '-': printf(\"%d\\n\", a-b); break;\n        case '*': printf(\"%d\\n\", a*b); break;\n        case '/': printf(\"%d\\n\", a/b); break;\n        default: printf(\"Invalid\\n\");\n    }\n    return 0;\n}",
            "15"
        ));
        conditionalPrograms.add(new Program(
            "Goto Statement",
            "Using goto for jump.",
            "#include <stdio.h>\n\nint main() {\n    int i=1;\n    start:\n    printf(\"%d \", i);\n    i++;\n    if(i<=5) goto start;\n    return 0;\n}",
            "1 2 3 4 5"
        ));
        programsByCategory.put("Conditionals", conditionalPrograms);

        // ==================== STRUCTURES & UNIONS ====================
        List<Program> structurePrograms = new ArrayList<>();
        structurePrograms.add(new Program(
            "Simple Structure",
            "Define and use structure.",
            "#include <stdio.h>\n\nstruct Student {\n    int id;\n    char name[50];\n    float marks;\n};\n\nint main() {\n    struct Student s1 = {1, \"John\", 85.5};\n    printf(\"ID: %d\\n\", s1.id);\n    printf(\"Name: %s\\n\", s1.name);\n    printf(\"Marks: %.2f\\n\", s1.marks);\n    return 0;\n}",
            "ID: 1\nName: John\nMarks: 85.50"
        ));
        structurePrograms.add(new Program(
            "Array of Structures",
            "Multiple structure variables.",
            "#include <stdio.h>\n\nstruct Point {\n    int x, y;\n};\n\nint main() {\n    struct Point p[3] = {{1,2}, {3,4}, {5,6}};\n    for(int i=0; i<3; i++)\n        printf(\"Point %d: (%d,%d)\\n\", i+1, p[i].x, p[i].y);\n    return 0;\n}",
            "Point 1: (1,2)\nPoint 2: (3,4)\nPoint 3: (5,6)"
        ));
        structurePrograms.add(new Program(
            "Nested Structure",
            "Structure within structure.",
            "#include <stdio.h>\n\nstruct Date {\n    int day, month, year;\n};\n\nstruct Employee {\n    int id;\n    struct Date joinDate;\n};\n\nint main() {\n    struct Employee e = {101, {15, 6, 2020}};\n    printf(\"ID: %d\\n\", e.id);\n    printf(\"Join: %d/%d/%d\\n\", e.joinDate.day, e.joinDate.month, e.joinDate.year);\n    return 0;\n}",
            "ID: 101\nJoin: 15/6/2020"
        ));
        structurePrograms.add(new Program(
            "Structure Pointer",
            "Access structure using pointer.",
            "#include <stdio.h>\n\nstruct Point {\n    int x, y;\n};\n\nint main() {\n    struct Point p = {10, 20};\n    struct Point *ptr = &p;\n    printf(\"x = %d, y = %d\\n\", ptr->x, ptr->y);\n    return 0;\n}",
            "x = 10, y = 20"
        ));
        structurePrograms.add(new Program(
            "Union Example",
            "Union vs Structure.",
            "#include <stdio.h>\n\nunion Data {\n    int i;\n    float f;\n    char c;\n};\n\nint main() {\n    union Data d;\n    d.i = 10;\n    printf(\"i = %d\\n\", d.i);\n    d.f = 3.14;\n    printf(\"f = %.2f\\n\", d.f);\n    return 0;\n}",
            "i = 10\nf = 3.14"
        ));
        structurePrograms.add(new Program(
            "Typedef with Structure",
            "Using typedef for simplicity.",
            "#include <stdio.h>\n\ntypedef struct {\n    int x, y;\n} Point;\n\nint main() {\n    Point p1 = {5, 10};\n    printf(\"Point: (%d, %d)\\n\", p1.x, p1.y);\n    return 0;\n}",
            "Point: (5, 10)"
        ));
        programsByCategory.put("Structures", structurePrograms);

        // ==================== DYNAMIC MEMORY ====================
        List<Program> memoryPrograms = new ArrayList<>();
        memoryPrograms.add(new Program(
            "Malloc Example",
            "Allocate memory using malloc.",
            "#include <stdio.h>\n#include <stdlib.h>\n\nint main() {\n    int *ptr = (int*)malloc(5 * sizeof(int));\n    for(int i=0; i<5; i++)\n        ptr[i] = i+1;\n    for(int i=0; i<5; i++)\n        printf(\"%d \", ptr[i]);\n    free(ptr);\n    return 0;\n}",
            "1 2 3 4 5"
        ));
        memoryPrograms.add(new Program(
            "Calloc Example",
            "Allocate and initialize to zero.",
            "#include <stdio.h>\n#include <stdlib.h>\n\nint main() {\n    int *ptr = (int*)calloc(5, sizeof(int));\n    printf(\"Initial values: \");\n    for(int i=0; i<5; i++)\n        printf(\"%d \", ptr[i]);\n    free(ptr);\n    return 0;\n}",
            "Initial values: 0 0 0 0 0"
        ));
        memoryPrograms.add(new Program(
            "Realloc Example",
            "Resize allocated memory.",
            "#include <stdio.h>\n#include <stdlib.h>\n\nint main() {\n    int *ptr = (int*)malloc(3 * sizeof(int));\n    ptr[0]=1; ptr[1]=2; ptr[2]=3;\n    ptr = (int*)realloc(ptr, 5 * sizeof(int));\n    ptr[3]=4; ptr[4]=5;\n    for(int i=0; i<5; i++)\n        printf(\"%d \", ptr[i]);\n    free(ptr);\n    return 0;\n}",
            "1 2 3 4 5"
        ));
        memoryPrograms.add(new Program(
            "Free Memory",
            "Deallocate memory properly.",
            "#include <stdio.h>\n#include <stdlib.h>\n\nint main() {\n    int *ptr = (int*)malloc(sizeof(int));\n    *ptr = 100;\n    printf(\"Value = %d\\n\", *ptr);\n    free(ptr);\n    printf(\"Memory freed\\n\");\n    return 0;\n}",
            "Value = 100\nMemory freed"
        ));
        memoryPrograms.add(new Program(
            "Dynamic 2D Array",
            "Allocate 2D array dynamically.",
            "#include <stdio.h>\n#include <stdlib.h>\n\nint main() {\n    int **arr = (int**)malloc(2 * sizeof(int*));\n    for(int i=0; i<2; i++)\n        arr[i] = (int*)malloc(3 * sizeof(int));\n    int val=1;\n    for(int i=0; i<2; i++)\n        for(int j=0; j<3; j++)\n            arr[i][j] = val++;\n    for(int i=0; i<2; i++) {\n        for(int j=0; j<3; j++)\n            printf(\"%d \", arr[i][j]);\n        printf(\"\\n\");\n    }\n    for(int i=0; i<2; i++) free(arr[i]);\n    free(arr);\n    return 0;\n}",
            "1 2 3\n4 5 6"
        ));
        programsByCategory.put("Dynamic Memory", memoryPrograms);

        // ==================== SEARCHING & SORTING ====================
        List<Program> searchSortPrograms = new ArrayList<>();
        searchSortPrograms.add(new Program(
            "Linear Search",
            "Search element sequentially.",
            "#include <stdio.h>\n\nint main() {\n    int arr[] = {10,20,30,40,50};\n    int key=30, found=-1;\n    for(int i=0; i<5; i++) {\n        if(arr[i]==key) { found=i; break; }\n    }\n    if(found!=-1) printf(\"Found at %d\\n\", found);\n    else printf(\"Not found\\n\");\n    return 0;\n}",
            "Found at 2"
        ));
        searchSortPrograms.add(new Program(
            "Binary Search",
            "Search in sorted array.",
            "#include <stdio.h>\n\nint main() {\n    int arr[] = {10,20,30,40,50};\n    int key=30, l=0, r=4, mid;\n    while(l<=r) {\n        mid = (l+r)/2;\n        if(arr[mid]==key) { printf(\"Found at %d\\n\", mid); break; }\n        if(arr[mid]<key) l=mid+1;\n        else r=mid-1;\n    }\n    return 0;\n}",
            "Found at 2"
        ));
        searchSortPrograms.add(new Program(
            "Bubble Sort",
            "Sort using bubble sort.",
            "#include <stdio.h>\n\nint main() {\n    int arr[] = {5,2,8,1,9}, n=5;\n    for(int i=0; i<n-1; i++)\n        for(int j=0; j<n-i-1; j++)\n            if(arr[j]>arr[j+1]) {\n                int temp=arr[j]; arr[j]=arr[j+1]; arr[j+1]=temp;\n            }\n    printf(\"Sorted: \");\n    for(int i=0; i<n; i++) printf(\"%d \", arr[i]);\n    return 0;\n}",
            "Sorted: 1 2 5 8 9"
        ));
        searchSortPrograms.add(new Program(
            "Selection Sort",
            "Sort using selection sort.",
            "#include <stdio.h>\n\nint main() {\n    int arr[] = {64,25,12,22,11}, n=5;\n    for(int i=0; i<n-1; i++) {\n        int min=i;\n        for(int j=i+1; j<n; j++)\n            if(arr[j]<arr[min]) min=j;\n        int temp=arr[i]; arr[i]=arr[min]; arr[min]=temp;\n    }\n    printf(\"Sorted: \");\n    for(int i=0; i<n; i++) printf(\"%d \", arr[i]);\n    return 0;\n}",
            "Sorted: 11 12 22 25 64"
        ));
        searchSortPrograms.add(new Program(
            "Insertion Sort",
            "Sort using insertion sort.",
            "#include <stdio.h>\n\nint main() {\n    int arr[] = {12,11,13,5,6}, n=5;\n    for(int i=1; i<n; i++) {\n        int key=arr[i], j=i-1;\n        while(j>=0 && arr[j]>key) {\n            arr[j+1]=arr[j]; j--;\n        }\n        arr[j+1]=key;\n    }\n    printf(\"Sorted: \");\n    for(int i=0; i<n; i++) printf(\"%d \", arr[i]);\n    return 0;\n}",
            "Sorted: 5 6 11 12 13"
        ));
        searchSortPrograms.add(new Program(
            "Quick Sort",
            "Sort using quick sort.",
            "#include <stdio.h>\n\nvoid quickSort(int arr[], int low, int high) {\n    if(low<high) {\n        int pivot=arr[high], i=low-1;\n        for(int j=low; j<high; j++)\n            if(arr[j]<pivot) {\n                i++;\n                int temp=arr[i]; arr[i]=arr[j]; arr[j]=temp;\n            }\n        int temp=arr[i+1]; arr[i+1]=arr[high]; arr[high]=temp;\n        int pi=i+1;\n        quickSort(arr,low,pi-1);\n        quickSort(arr,pi+1,high);\n    }\n}\n\nint main() {\n    int arr[]={10,7,8,9,1,5}, n=6;\n    quickSort(arr,0,n-1);\n    printf(\"Sorted: \");\n    for(int i=0; i<n; i++) printf(\"%d \", arr[i]);\n    return 0;\n}",
            "Sorted: 1 5 7 8 9 10"
        ));
        searchSortPrograms.add(new Program(
            "Merge Sort",
            "Sort using merge sort.",
            "#include <stdio.h>\n\nvoid merge(int arr[], int l, int m, int r) {\n    int n1=m-l+1, n2=r-m;\n    int L[n1], R[n2];\n    for(int i=0; i<n1; i++) L[i]=arr[l+i];\n    for(int j=0; j<n2; j++) R[j]=arr[m+1+j];\n    int i=0, j=0, k=l;\n    while(i<n1 && j<n2) {\n        if(L[i]<=R[j]) arr[k++]=L[i++];\n        else arr[k++]=R[j++];\n    }\n    while(i<n1) arr[k++]=L[i++];\n    while(j<n2) arr[k++]=R[j++];\n}\n\nvoid mergeSort(int arr[], int l, int r) {\n    if(l<r) {\n        int m=l+(r-l)/2;\n        mergeSort(arr,l,m);\n        mergeSort(arr,m+1,r);\n        merge(arr,l,m,r);\n    }\n}\n\nint main() {\n    int arr[]={12,11,13,5,6,7}, n=6;\n    mergeSort(arr,0,n-1);\n    printf(\"Sorted: \");\n    for(int i=0; i<n; i++) printf(\"%d \", arr[i]);\n    return 0;\n}",
            "Sorted: 5 6 7 11 12 13"
        ));
        searchSortPrograms.add(new Program(
            "Count Occurrences",
            "Count how many times element appears.",
            "#include <stdio.h>\n\nint main() {\n    int arr[] = {1,2,3,2,4,2,5};\n    int key=2, count=0;\n    for(int i=0; i<7; i++)\n        if(arr[i]==key) count++;\n    printf(\"%d appears %d times\\n\", key, count);\n    return 0;\n}",
            "2 appears 3 times"
        ));
        programsByCategory.put("Searching & Sorting", searchSortPrograms);
    }

    public static List<Program> getProgramsByCategory(String category) {
        return programsByCategory.getOrDefault(category, new ArrayList<>());
    }

    public static List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Basic");
        categories.add("Operators");
        categories.add("Conditionals");
        categories.add("Loops");
        categories.add("Numbers");
        categories.add("Array");
        categories.add("Strings");
        categories.add("Functions");
        categories.add("Recursion");
        categories.add("Pointers");
        categories.add("Structures");
        categories.add("Dynamic Memory");
        categories.add("Patterns");
        categories.add("Graphics");
        categories.add("Formula");
        categories.add("Searching & Sorting");
        categories.add("Data Structures");
        categories.add("File Handling");
        return categories;
    }
}
