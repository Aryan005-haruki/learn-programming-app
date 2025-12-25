package com.clearning.app.utils;

import com.clearning.app.models.Program;
import java.util.ArrayList;
import java.util.List;

public class PracticeDataProvider {
    
    public static List<Program> getProgramsBySet(int setNumber) {
        List<Program> programs = new ArrayList<>();
        
        switch (setNumber) {
            case 1:
                programs = getSet1Basics();
                break;
            case 2:
                programs = getSet2Conditions();
                break;
            case 3:
                programs = getSet3Loops();
                break;
            case 4:
                programs = getSet4ArraysStrings();
                break;
            case 5:
                programs = getSet5FunctionsPointers();
                break;
            case 6:
                programs = getSet6StructuresFiles();
                break;
        }
        
        return programs;
    }
    
    private static List<Program> getSet1Basics() {
        List<Program> programs = new ArrayList<>();
        
        programs.add(new Program(
            "Hello World",
            "Print 'Hello, World!' on screen",
            "#include <stdio.h>\\n\\nint main() {\\n    printf(\\\"Hello, World!\\\");\\n    return 0;\\n}",
            "Hello, World!"
        ));
        
        programs.add(new Program(
            "Print Your Name",
            "Take user's name as input and print it",
            "#include <stdio.h>\\n\\nint main() {\\n    char name[50];\\n    printf(\\\"Enter your name: \\\");\\n    scanf(\\\"%s\\\", name);\\n    printf(\\\"Hello, %s!\\\", name);\\n    return 0;\\n}",
            "Enter your name: Aryan\\nHello, Aryan!"
        ));
        
        programs.add(new Program(
            "Add Two Numbers",
            "Take two numbers and print their sum",
            "#include <stdio.h>\\n\\nint main() {\\n    int a, b, sum;\\n    printf(\\\"Enter two numbers: \\\");\\n    scanf(\\\"%d %d\\\", &a, &b);\\n    sum = a + b;\\n    printf(\\\"Sum = %d\\\", sum);\\n    return 0;\\n}",
            "Enter two numbers: 5 3\\nSum = 8"
        ));
        
        programs.add(new Program(
            "Area of Circle",
            "Calculate area of circle given radius",
            "#include <stdio.h>\\n#define PI 3.14159\\n\\nint main() {\\n    float radius, area;\\n    printf(\\\"Enter radius: \\\");\\n    scanf(\\\"%f\\\", &radius);\\n    area = PI * radius * radius;\\n    printf(\\\"Area = %.2f\\\", area);\\n    return 0;\\n}",
            "Enter radius: 5\\nArea = 78.54"
        ));
        
        programs.add(new Program(
            "Swap Two Numbers",
            "Swap two numbers using a temporary variable",
            "#include <stdio.h>\\n\\nint main() {\\n    int a = 10, b = 20, temp;\\n    printf(\\\"Before: a=%d, b=%d\\\\n\\\", a, b);\\n    temp = a;\\n    a = b;\\n    b = temp;\\n    printf(\\\"After: a=%d, b=%d\\\", a, b);\\n    return 0;\\n}",
            "Before: a=10, b=20\\nAfter: a=20, b=10"
        ));
        
        return programs;
    }
    
    private static List<Program> getSet2Conditions() {
        List<Program> programs = new ArrayList<>();
        
        programs.add(new Program(
            "Check Even or Odd",
            "Check if a number is even or odd",
            "#include <stdio.h>\\n\\nint main() {\\n    int num;\\n    printf(\\\"Enter a number: \\\");\\n    scanf(\\\"%d\\\", &num);\\n    if (num % 2 == 0)\\n        printf(\\\"%d is Even\\\", num);\\n    else\\n        printf(\\\"%d is Odd\\\", num);\\n    return 0;\\n}",
            "Enter a number: 7\\n7 is Odd"
        ));
        
        programs.add(new Program(
            "Largest of 3 Numbers",
            "Find the largest among three numbers",
            "#include <stdio.h>\\n\\nint main() {\\n    int a, b, c;\\n    printf(\\\"Enter 3 numbers: \\\");\\n    scanf(\\\"%d %d %d\\\", &a, &b, &c);\\n    if (a >= b && a >= c)\\n        printf(\\\"Largest = %d\\\", a);\\n    else if (b >= c)\\n        printf(\\\"Largest = %d\\\", b);\\n    else\\n        printf(\\\"Largest = %d\\\", c);\\n    return 0;\\n}",
            "Enter 3 numbers: 5 12 8\\nLargest = 12"
        ));
        
        programs.add(new Program(
            "Check Leap Year",
            "Check if a year is leap year or not",
            "#include <stdio.h>\\n\\nint main() {\\n    int year;\\n    printf(\\\"Enter year: \\\");\\n    scanf(\\\"%d\\\", &year);\\n    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))\\n        printf(\\\"%d is a Leap Year\\\", year);\\n    else\\n        printf(\\\"%d is not a Leap Year\\\", year);\\n    return 0;\\n}",
            "Enter year: 2024\\n2024 is a Leap Year"
        ));
        
        programs.add(new Program(
            "Positive or Negative",
            "Check if number is positive, negative or zero",
            "#include <stdio.h>\\n\\nint main() {\\n    int num;\\n    printf(\\\"Enter a number: \\\");\\n    scanf(\\\"%d\\\", &num);\\n    if (num > 0)\\n        printf(\\\"Positive\\\");\\n    else if (num < 0)\\n        printf(\\\"Negative\\\");\\n    else\\n        printf(\\\"Zero\\\");\\n    return 0;\\n}",
            "Enter a number: -5\\nNegative"
        ));
        
        programs.add(new Program(
            "Grade Calculation",
            "Calculate grade based on marks",
            "#include <stdio.h>\\n\\nint main() {\\n    int marks;\\n    printf(\\\"Enter marks: \\\");\\n    scanf(\\\"%d\\\", &marks);\\n    if (marks >= 90)\\n        printf(\\\"Grade: A+\\\");\\n    else if (marks >= 80)\\n        printf(\\\"Grade: A\\\");\\n    else if (marks >= 70)\\n        printf(\\\"Grade: B\\\");\\n    else if (marks >= 60)\\n        printf(\\\"Grade: C\\\");\\n    else\\n        printf(\\\"Grade: F\\\");\\n    return 0;\\n}",
            "Enter marks: 85\\nGrade: A"
        ));
        
        return programs;
    }
    
    private static List<Program> getSet3Loops() {
        List<Program> programs = new ArrayList<>();
        
        programs.add(new Program(
            "Print 1 to N",
            "Print numbers from 1 to N",
            "#include <stdio.h>\\n\\nint main() {\\n    int n;\\n    printf(\\\"Enter N: \\\");\\n    scanf(\\\"%d\\\", &n);\\n    for (int i = 1; i <= n; i++) {\\n        printf(\\\"%d \\\", i);\\n    }\\n    return 0;\\n}",
            "Enter N: 5\\n1 2 3 4 5"
        ));
        
        programs.add(new Program(
            "Sum of Natural Numbers",
            "Calculate sum of first N natural numbers",
            "#include <stdio.h>\\n\\nint main() {\\n    int n, sum = 0;\\n    printf(\\\"Enter N: \\\");\\n    scanf(\\\"%d\\\", &n);\\n    for (int i = 1; i <= n; i++) {\\n        sum += i;\\n    }\\n    printf(\\\"Sum = %d\\\", sum);\\n    return 0;\\n}",
            "Enter N: 10\\nSum = 55"
        ));
        
        programs.add(new Program(
            "Factorial",
            "Calculate factorial of a number",
            "#include <stdio.h>\\n\\nint main() {\\n    int n, fact = 1;\\n    printf(\\\"Enter number: \\\");\\n    scanf(\\\"%d\\\", &n);\\n    for (int i = 1; i <= n; i++) {\\n        fact *= i;\\n    }\\n    printf(\\\"Factorial = %d\\\", fact);\\n    return 0;\\n}",
            "Enter number: 5\\nFactorial = 120"
        ));
        
        programs.add(new Program(
            "Fibonacci Series",
            "Print Fibonacci series up to N terms",
            "#include <stdio.h>\\n\\nint main() {\\n    int n, a = 0, b = 1, next;\\n    printf(\\\"Enter N: \\\");\\n    scanf(\\\"%d\\\", &n);\\n    printf(\\\"%d %d \\\", a, b);\\n    for (int i = 3; i <= n; i++) {\\n        next = a + b;\\n        printf(\\\"%d \\\", next);\\n        a = b;\\n        b = next;\\n    }\\n    return 0;\\n}",
            "Enter N: 7\\n0 1 1 2 3 5 8"
        ));
        
        programs.add(new Program(
            "Prime Number Check",
            "Check if a number is prime",
            "#include <stdio.h>\\n\\nint main() {\\n    int n, isPrime = 1;\\n    printf(\\\"Enter number: \\\");\\n    scanf(\\\"%d\\\", &n);\\n    if (n <= 1) isPrime = 0;\\n    for (int i = 2; i * i <= n; i++) {\\n        if (n % i == 0) {\\n            isPrime = 0;\\n            break;\\n        }\\n    }\\n    if (isPrime)\\n        printf(\\\"%d is Prime\\\", n);\\n    else\\n        printf(\\\"%d is not Prime\\\", n);\\n    return 0;\\n}",
            "Enter number: 17\\n17 is Prime"
        ));
        
        return programs;
    }
    
    private static List<Program> getSet4ArraysStrings() {
        List<Program> programs = new ArrayList<>();
        
        programs.add(new Program(
            "Array Sum",
            "Calculate sum of array elements",
            "#include <stdio.h>\\n\\nint main() {\\n    int arr[] = {1, 2, 3, 4, 5};\\n    int sum = 0;\\n    for (int i = 0; i < 5; i++) {\\n        sum += arr[i];\\n    }\\n    printf(\\\"Sum = %d\\\", sum);\\n    return 0;\\n}",
            "Sum = 15"
        ));
        
        programs.add(new Program(
            "Largest Element",
            "Find largest element in array",
            "#include <stdio.h>\\n\\nint main() {\\n    int arr[] = {12, 35, 1, 10, 34, 1};\\n    int max = arr[0];\\n    for (int i = 1; i < 6; i++) {\\n        if (arr[i] > max)\\n            max = arr[i];\\n    }\\n    printf(\\\"Largest = %d\\\", max);\\n    return 0;\\n}",
            "Largest = 35"
        ));
        
        programs.add(new Program(
            "Reverse Array",
            "Reverse an array",
            "#include <stdio.h>\\n\\nint main() {\\n    int arr[] = {1, 2, 3, 4, 5};\\n    int n = 5;\\n    printf(\\\"Original: \\\");\\n    for (int i = 0; i < n; i++) printf(\\\"%d \\\", arr[i]);\\n    printf(\\\"\\\\nReversed: \\\");\\n    for (int i = n-1; i >= 0; i--) printf(\\\"%d \\\", arr[i]);\\n    return 0;\\n}",
            "Original: 1 2 3 4 5\\nReversed: 5 4 3 2 1"
        ));
        
        programs.add(new Program(
            "String Length",
            "Calculate string length without strlen()",
            "#include <stdio.h>\\n\\nint main() {\\n    char str[] = \\\"Hello\\\";\\n    int len = 0;\\n    while (str[len] != '\\\\0') {\\n        len++;\\n    }\\n    printf(\\\"Length = %d\\\", len);\\n    return 0;\\n}",
            "Length = 5"
        ));
        
        programs.add(new Program(
            "Palindrome String",
            "Check if string is palindrome",
            "#include <stdio.h>\\n#include <string.h>\\n\\nint main() {\\n    char str[] = \\\"madam\\\";\\n    int len = strlen(str);\\n    int isPalindrome = 1;\\n    for (int i = 0; i < len/2; i++) {\\n        if (str[i] != str[len-i-1]) {\\n            isPalindrome = 0;\\n            break;\\n        }\\n    }\\n    if (isPalindrome)\\n        printf(\\\"Palindrome\\\");\\n    else\\n        printf(\\\"Not Palindrome\\\");\\n    return 0;\\n}",
            "Palindrome"
        ));
        
        return programs;
    }
    
    private static List<Program> getSet5FunctionsPointers() {
        List<Program> programs = new ArrayList<>();
        
        programs.add(new Program(
            "Function to Add",
            "Create function to add two numbers",
            "#include <stdio.h>\\n\\nint add(int a, int b) {\\n    return a + b;\\n}\\n\\nint main() {\\n    int result = add(5, 3);\\n    printf(\\\"Sum = %d\\\", result);\\n    return 0;\\n}",
            "Sum = 8"
        ));
        
        programs.add(new Program(
            "Call by Value",
            "Demonstrate call by value",
            "#include <stdio.h>\\n\\nvoid modify(int x) {\\n    x = 100;\\n    printf(\\\"Inside function: %d\\\\n\\\", x);\\n}\\n\\nint main() {\\n    int num = 10;\\n    printf(\\\"Before: %d\\\\n\\\", num);\\n    modify(num);\\n    printf(\\\"After: %d\\\", num);\\n    return 0;\\n}",
            "Before: 10\\nInside function: 100\\nAfter: 10"
        ));
        
        programs.add(new Program(
            "Swap Using Pointers",
            "Swap two numbers using pointers",
            "#include <stdio.h>\\n\\nvoid swap(int *a, int *b) {\\n    int temp = *a;\\n    *a = *b;\\n    *b = temp;\\n}\\n\\nint main() {\\n    int x = 10, y = 20;\\n    printf(\\\"Before: x=%d, y=%d\\\\n\\\", x, y);\\n    swap(&x, &y);\\n    printf(\\\"After: x=%d, y=%d\\\", x, y);\\n    return 0;\\n}",
            "Before: x=10, y=20\\nAfter: x=20, y=10"
        ));
        
        programs.add(new Program(
            "Pointer to Array",
            "Access array using pointer",
            "#include <stdio.h>\\n\\nint main() {\\n    int arr[] = {10, 20, 30};\\n    int *ptr = arr;\\n    for (int i = 0; i < 3; i++) {\\n        printf(\\\"%d \\\", *(ptr + i));\\n    }\\n    return 0;\\n}",
            "10 20 30"
        ));
        
        programs.add(new Program(
            "Pointer Basics",
            "Basic pointer operations",
            "#include <stdio.h>\\n\\nint main() {\\n    int num = 42;\\n    int *ptr = &num;\\n    printf(\\\"Value: %d\\\\n\\\", num);\\n    printf(\\\"Address: %p\\\\n\\\", ptr);\\n    printf(\\\"Value via pointer: %d\\\", *ptr);\\n    return 0;\\n}",
            "Value: 42\\nAddress: 0x7fff5c...\\nValue via pointer: 42"
        ));
        
        return programs;
    }
    
    private static List<Program> getSet6StructuresFiles() {
        List<Program> programs = new ArrayList<>();
        
        programs.add(new Program(
            "Student Structure",
            "Create and use student structure",
            "#include <stdio.h>\\n\\nstruct Student {\\n    char name[50];\\n    int age;\\n    float marks;\\n};\\n\\nint main() {\\n    struct Student s1 = {\\\"Aryan\\\", 20, 85.5};\\n    printf(\\\"Name: %s\\\\n\\\", s1.name);\\n    printf(\\\"Age: %d\\\\n\\\", s1.age);\\n    printf(\\\"Marks: %.1f\\\", s1.marks);\\n    return 0;\\n}",
            "Name: Aryan\\nAge: 20\\nMarks: 85.5"
        ));
        
        programs.add(new Program(
            "Employee Salary",
            "Calculate employee salary with structure",
            "#include <stdio.h>\\n\\nstruct Employee {\\n    char name[50];\\n    float basic;\\n    float hra;\\n};\\n\\nint main() {\\n    struct Employee e = {\\\"John\\\", 50000, 10000};\\n    float total = e.basic + e.hra;\\n    printf(\\\"Employee: %s\\\\n\\\", e.name);\\n    printf(\\\"Total Salary: %.2f\\\", total);\\n    return 0;\\n}",
            "Employee: John\\nTotal Salary: 60000.00"
        ));
        
        programs.add(new Program(
            "File Write",
            "Write data to a file",
            "#include <stdio.h>\\n\\nint main() {\\n    FILE *fp = fopen(\\\"test.txt\\\", \\\"w\\\");\\n    if (fp == NULL) {\\n        printf(\\\"Error!\\\");\\n        return 1;\\n    }\\n    fprintf(fp, \\\"Hello, File!\\\");\\n    fclose(fp);\\n    printf(\\\"File written successfully\\\");\\n    return 0;\\n}",
            "File written successfully"
        ));
        
        programs.add(new Program(
            "File Read",
            "Read data from a file",
            "#include <stdio.h>\\n\\nint main() {\\n    FILE *fp = fopen(\\\"test.txt\\\", \\\"r\\\");\\n    if (fp == NULL) {\\n        printf(\\\"Error!\\\");\\n        return 1;\\n    }\\n    char buffer[100];\\n    fscanf(fp, \\\"%s\\\", buffer);\\n    printf(\\\"Read: %s\\\", buffer);\\n    fclose(fp);\\n    return 0;\\n}",
            "Read: Hello,"
        ));
        
        programs.add(new Program(
            "Append to File",
            "Append data to existing file",
            "#include <stdio.h>\\n\\nint main() {\\n    FILE *fp = fopen(\\\"test.txt\\\", \\\"a\\\");\\n    if (fp == NULL) {\\n        printf(\\\"Error!\\\");\\n        return 1;\\n    }\\n    fprintf(fp, \\\"\\\\nNew Line\\\");\\n    fclose(fp);\\n    printf(\\\"Data appended\\\");\\n    return 0;\\n}",
            "Data appended"
        ));
        
        return programs;
    }
}
