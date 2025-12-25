package com.clearning.app.utils;

import com.clearning.app.models.Program;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaProgramDataProvider {
    private static Map<String, List<Program>> programsByCategory = new HashMap<>();

    static {
        initializePrograms();
    }

    private static void initializePrograms() {
        // ==================== BASIC PROGRAMS ====================
        List<Program> basicPrograms = new ArrayList<>();
        basicPrograms.add(new Program(
            "Hello World",
            "A simple program to print Hello World.",
            "public class HelloWorld {\\n    public static void main(String[] args) {\\n        System.out.println(\\\"Hello, World!\\\");\\n    }\\n}",
            "Hello, World!"
        ));
        basicPrograms.add(new Program(
            "Sum of Two Numbers",
            "Add two numbers and display result.",
            "public class Sum {\\n    public static void main(String[] args) {\\n        int a = 10, b = 20;\\n        int sum = a + b;\\n        System.out.println(\\\"Sum = \\\" + sum);\\n    }\\n}",
            "Sum = 30"
        ));
        basicPrograms.add(new Program(
            "Swap Two Numbers",
            "Swap using temporary variable.",
            "public class Swap {\\n    public static void main(String[] args) {\\n        int a = 5, b = 10;\\n        System.out.println(\\\"Before: a=\\\" + a + \\\", b=\\\" + b);\\n        int temp = a;\\n        a = b;\\n        b = temp;\\n        System.out.println(\\\"After: a=\\\" + a + \\\", b=\\\" + b);\\n    }\\n}",
            "Before: a=5, b=10\\nAfter: a=10, b=5"
        ));
        basicPrograms.add(new Program(
            "Even or Odd",
            "Check if number is even or odd.",
            "public class EvenOdd {\\n    public static void main(String[] args) {\\n        int num = 7;\\n        if (num % 2 == 0)\\n            System.out.println(num + \\\" is even\\\");\\n        else\\n            System.out.println(num + \\\" is odd\\\");\\n    }\\n}",
            "7 is odd"
        ));
        basicPrograms.add(new Program(
            "Largest of Three",
            "Find largest among three numbers.",
            "public class Largest {\\n    public static void main(String[] args) {\\n        int a = 10, b = 25, c = 15;\\n        int max = (a > b) ? ((a > c) ? a : c) : ((b > c) ? b : c);\\n        System.out.println(\\\"Largest = \\\" + max);\\n    }\\n}",
            "Largest = 25"
        ));
        basicPrograms.add(new Program(
            "Factorial",
            "Calculate factorial of number.",
            "public class Factorial {\\n    public static void main(String[] args) {\\n        int n = 5, fact = 1;\\n        for (int i = 1; i <= n; i++)\\n            fact *= i;\\n        System.out.println(\\\"Factorial = \\\" + fact);\\n    }\\n}",
            "Factorial = 120"
        ));
        basicPrograms.add(new Program(
            "Prime Number",
            "Check if number is prime.",
            "public class Prime {\\n    public static void main(String[] args) {\\n        int n = 29;\\n        boolean isPrime = true;\\n        if (n < 2) isPrime = false;\\n        for (int i = 2; i <= n/2; i++) {\\n            if (n % i == 0) {\\n                isPrime = false;\\n                break;\\n            }\\n        }\\n        System.out.println(n + (isPrime ? \\\" is prime\\\" : \\\" is not prime\\\"));\\n    }\\n}",
            "29 is prime"
        ));
        basicPrograms.add(new Program(
            "Fibonacci Series",
            "Print Fibonacci series.",
            "public class Fibonacci {\\n    public static void main(String[] args) {\\n        int n = 10, t1 = 0, t2 = 1;\\n        System.out.print(\\\"Fibonacci: \\\");\\n        for (int i = 1; i <= n; i++) {\\n            System.out.print(t1 + \\\" \\\");\\n            int next = t1 + t2;\\n            t1 = t2;\\n            t2 = next;\\n        }\\n    }\\n}",
            "Fibonacci: 0 1 1 2 3 5 8 13 21 34"
        ));
        basicPrograms.add(new Program(
            "Palindrome Number",
            "Check if number is palindrome.",
            "public class Palindrome {\\n    public static void main(String[] args) {\\n        int n = 121, rev = 0, temp = n;\\n        while (temp > 0) {\\n            rev = rev * 10 + temp % 10;\\n            temp /= 10;\\n        }\\n        System.out.println(n + (rev == n ? \\\" is palindrome\\\" : \\\" is not palindrome\\\"));\\n    }\\n}",
            "121 is palindrome"
        ));
        basicPrograms.add(new Program(
            "Armstrong Number",
            "Check Armstrong number.",
            "public class Armstrong {\\n    public static void main(String[] args) {\\n        int n = 153, sum = 0, temp = n;\\n        while (temp > 0) {\\n            int digit = temp % 10;\\n            sum += digit * digit * digit;\\n            temp /= 10;\\n        }\\n        System.out.println(n + (sum == n ? \\\" is Armstrong\\\" : \\\" is not Armstrong\\\"));\\n    }\\n}",
            "153 is Armstrong"
        ));
        basicPrograms.add(new Program(
            "LCM of Two Numbers",
            "Find LCM of two numbers.",
            "public class LCM {\\n    public static void main(String[] args) {\\n        int a = 12, b = 18;\\n        int max = (a > b) ? a : b;\\n        while (true) {\\n            if (max % a == 0 && max % b == 0) {\\n                System.out.println(\\\"LCM = \\\" + max);\\n                break;\\n            }\\n            max++;\\n        }\\n    }\\n}",
            "LCM = 36"
        ));
        basicPrograms.add(new Program(
            "Sum of Digits",
            "Calculate sum of digits.",
            "public class SumDigits {\\n    public static void main(String[] args) {\\n        int n = 12345, sum = 0;\\n        while (n > 0) {\\n            sum += n % 10;\\n            n /= 10;\\n        }\\n        System.out.println(\\\"Sum = \\\" + sum);\\n    }\\n}",
            "Sum = 15"
        ));
        basicPrograms.add(new Program(
            "Reverse Number",
            "Reverse digits of number.",
            "public class ReverseNum {\\n    public static void main(String[] args) {\\n        int n = 12345, rev = 0;\\n        while (n > 0) {\\n            rev = rev * 10 + n % 10;\\n            n /= 10;\\n        }\\n        System.out.println(\\\"Reversed = \\\" + rev);\\n    }\\n}",
            "Reversed = 54321"
        ));
        basicPrograms.add(new Program(
            "Perfect Number",
            "Check if number is perfect.",
            "public class PerfectNum {\\n    public static void main(String[] args) {\\n        int n = 28, sum = 0;\\n        for (int i = 1; i < n; i++)\\n            if (n % i == 0) sum += i;\\n        System.out.println(n + (sum == n ? \\\" is perfect\\\" : \\\" is not perfect\\\"));\\n    }\\n}",
            "28 is perfect"
        ));
        basicPrograms.add(new Program(
            "Strong Number",
            "Check strong number.",
            "public class StrongNum {\\n    public static void main(String[] args) {\\n        int n = 145, sum = 0, temp = n;\\n        while (temp > 0) {\\n            int digit = temp % 10, fact = 1;\\n            for (int i = 1; i <= digit; i++) fact *= i;\\n            sum += fact;\\n            temp /= 10;\\n        }\\n        System.out.println(n + (sum == n ? \\\" is strong\\\" : \\\" is not strong\\\"));\\n    }\\n}",
            "145 is strong"
        ));
        programsByCategory.put("Basic Programs", basicPrograms);

        // ==================== CONTROL STATEMENTS ====================
        List<Program> controlPrograms = new ArrayList<>();
        controlPrograms.add(new Program(
            "if Statement",
            "Basic if statement.",
            "public class IfDemo {\\n    public static void main(String[] args) {\\n        int num = 10;\\n        if (num > 0)\\n            System.out.println(\\\"Positive\\\");\\n    }\\n}",
            "Positive"
        ));
        controlPrograms.add(new Program(
            "if-else",
            "If-else statement.",
            "public class IfElse {\\n    public static void main(String[] args) {\\n        int num = -5;\\n        if (num >= 0)\\n            System.out.println(\\\"Positive\\\");\\n        else\\n            System.out.println(\\\"Negative\\\");\\n    }\\n}",
            "Negative"
        ));
        controlPrograms.add(new Program(
            "else-if Ladder",
            "Multiple conditions.",
            "public class ElseIf {\\n    public static void main(String[] args) {\\n        int marks = 75;\\n        if (marks >= 90)\\n            System.out.println(\\\"Grade A\\\");\\n        else if (marks >= 75)\\n            System.out.println(\\\"Grade B\\\");\\n        else if (marks >= 60)\\n            System.out.println(\\\"Grade C\\\");\\n        else\\n            System.out.println(\\\"Grade D\\\");\\n    }\\n}",
            "Grade B"
        ));
        controlPrograms.add(new Program(
            "switch Statement",
            "Switch case example.",
            "public class SwitchDemo {\\n    public static void main(String[] args) {\\n        int day = 3;\\n        switch (day) {\\n            case 1: System.out.println(\\\"Monday\\\"); break;\\n            case 2: System.out.println(\\\"Tuesday\\\"); break;\\n            case 3: System.out.println(\\\"Wednesday\\\"); break;\\n            default: System.out.println(\\\"Other\\\");\\n        }\\n    }\\n}",
            "Wednesday"
        ));
        controlPrograms.add(new Program(
            "Calculator",
            "Simple calculator using switch.",
            "public class Calculator {\\n    public static void main(String[] args) {\\n        int a = 10, b = 5;\\n        char op = '+';\\n        switch (op) {\\n            case '+': System.out.println(a + b); break;\\n            case '-': System.out.println(a - b); break;\\n            case '*': System.out.println(a * b); break;\\n            case '/': System.out.println(a / b); break;\\n        }\\n    }\\n}",
            "15"
        ));
        controlPrograms.add(new Program(
            "Ternary Operator",
            "Conditional operator.",
            "public class Ternary {\\n    public static void main(String[] args) {\\n        int a = 10, b = 20;\\n        int max = (a > b) ? a : b;\\n        System.out.println(\\\"Max = \\\" + max);\\n    }\\n}",
            "Max = 20"
        ));
        controlPrograms.add(new Program(
            "Vowel or Consonant",
            "Check if character is vowel.",
            "public class VowelCheck {\\n    public static void main(String[] args) {\\n        char ch = 'a';\\n        if (ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u')\\n            System.out.println(\\\"Vowel\\\");\\n        else\\n            System.out.println(\\\"Consonant\\\");\\n    }\\n}",
            "Vowel"
        ));
        controlPrograms.add(new Program(
            "Positive Negative Zero",
            "Check number type.",
            "public class NumType {\\n    public static void main(String[] args) {\\n        int num = 0;\\n        if (num > 0)\\n            System.out.println(\\\"Positive\\\");\\n        else if (num < 0)\\n            System.out.println(\\\"Negative\\\");\\n        else\\n            System.out.println(\\\"Zero\\\");\\n    }\\n}",
            "Zero"
        ));
        controlPrograms.add(new Program(
            "Leap Year",
            "Check if year is leap year.",
            "public class LeapYear {\\n    public static void main(String[] args) {\\n        int year = 2024;\\n        if ((year%4==0 && year%100!=0) || year%400==0)\\n            System.out.println(\\\"Leap year\\\");\\n        else\\n            System.out.println(\\\"Not leap year\\\");\\n    }\\n}",
            "Leap year"
        ));
        controlPrograms.add(new Program(
            "Alphabet Check",
            "Check if character is alphabet.",
            "public class AlphabetCheck {\\n    public static void main(String[] args) {\\n        char ch = 'A';\\n        if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))\\n            System.out.println(\\\"Alphabet\\\");\\n        else\\n            System.out.println(\\\"Not alphabet\\\");\\n    }\\n}",
            "Alphabet"
        ));
        controlPrograms.add(new Program(
            "Triangle Validity",
            "Check if triangle is valid.",
            "public class TriangleValid {\\n    public static void main(String[] args) {\\n        int a = 3, b = 4, c = 5;\\n        if (a+b>c && b+c>a && c+a>b)\\n            System.out.println(\\\"Valid triangle\\\");\\n        else\\n            System.out.println(\\\"Invalid triangle\\\");\\n    }\\n}",
            "Valid triangle"
        ));
        controlPrograms.add(new Program(
            "Grade Calculator",
            "Calculate grade from marks.",
            "public class GradeCalc {\\n    public static void main(String[] args) {\\n        int marks = 85;\\n        char grade;\\n        if (marks >= 90) grade = 'A';\\n        else if (marks >= 80) grade = 'B';\\n        else if (marks >= 70) grade = 'C';\\n        else if (marks >= 60) grade = 'D';\\n        else grade = 'F';\\n        System.out.println(\\\"Grade: \\\" + grade);\\n    }\\n}",
            "Grade: B"
        ));
        programsByCategory.put("Control Statements", controlPrograms);

        // ==================== LOOPS & PATTERNS ====================
        List<Program> loopPrograms = new ArrayList<>();
        loopPrograms.add(new Program(
            "for Loop",
            "Print 1 to 10.",
            "public class ForLoop {\\n    public static void main(String[] args) {\\n        for (int i = 1; i <= 10; i++)\\n            System.out.print(i + \\\" \\\");\\n    }\\n}",
            "1 2 3 4 5 6 7 8 9 10"
        ));
        loopPrograms.add(new Program(
            "while Loop",
            "Sum of N numbers.",
            "public class WhileLoop {\\n    public static void main(String[] args) {\\n        int n = 10, sum = 0, i = 1;\\n        while (i <= n) {\\n            sum += i;\\n            i++;\\n        }\\n        System.out.println(\\\"Sum = \\\" + sum);\\n    }\\n}",
            "Sum = 55"
        ));
        loopPrograms.add(new Program(
            "do-while Loop",
            "Print using do-while.",
            "public class DoWhile {\\n    public static void main(String[] args) {\\n        int i = 1;\\n        do {\\n            System.out.print(i + \\\" \\\");\\n            i++;\\n        } while (i <= 5);\\n    }\\n}",
            "1 2 3 4 5"
        ));
        loopPrograms.add(new Program(
            "Star Pattern",
            "Right triangle pattern.",
            "public class Pattern {\\n    public static void main(String[] args) {\\n        for (int i = 1; i <= 5; i++) {\\n            for (int j = 1; j <= i; j++)\\n                System.out.print(\\\"* \\\");\\n            System.out.println();\\n        }\\n    }\\n}",
            "*\\n* *\\n* * *\\n* * * *\\n* * * * *"
        ));
        loopPrograms.add(new Program(
            "Pyramid Pattern",
            "Print pyramid.",
            "public class Pyramid {\\n    public static void main(String[] args) {\\n        int n = 5;\\n        for (int i = 1; i <= n; i++) {\\n            for (int j = 1; j <= n-i; j++)\\n                System.out.print(\\\" \\\");\\n            for (int j = 1; j <= 2*i-1; j++)\\n                System.out.print(\\\"*\\\");\\n            System.out.println();\\n        }\\n    }\\n}",
            "    *\\n   ***\\n  *****\\n *******\\n*********"
        ));
        loopPrograms.add(new Program(
            "Number Pattern",
            "Print number pattern.",
            "public class NumPattern {\\n    public static void main(String[] args) {\\n        for (int i = 1; i <= 5; i++) {\\n            for (int j = 1; j <= i; j++)\\n                System.out.print(j + \\\" \\\");\\n            System.out.println();\\n        }\\n    }\\n}",
            "1\\n1 2\\n1 2 3\\n1 2 3 4\\n1 2 3 4 5"
        ));
        loopPrograms.add(new Program(
            "Floyd Triangle",
            "Print Floyd's triangle.",
            "public class Floyd {\\n    public static void main(String[] args) {\\n        int num = 1;\\n        for (int i = 1; i <= 5; i++) {\\n            for (int j = 1; j <= i; j++)\\n                System.out.print(num++ + \\\" \\\");\\n            System.out.println();\\n        }\\n    }\\n}",
            "1\\n2 3\\n4 5 6\\n7 8 9 10\\n11 12 13 14 15"
        ));
        loopPrograms.add(new Program(
            "Hollow Square",
            "Print hollow square pattern.",
            "public class HollowSquare {\\n    public static void main(String[] args) {\\n        int n = 5;\\n        for (int i = 1; i <= n; i++) {\\n            for (int j = 1; j <= n; j++) {\\n                if (i==1 || i==n || j==1 || j==n)\\n                    System.out.print(\\\"* \\\");\\n                else\\n                    System.out.print(\\\"  \\\");\\n            }\\n            System.out.println();\\n        }\\n    }\\n}",
            "* * * * *\\n*       *\\n*       *\\n*       *\\n* * * * *"
        ));
        loopPrograms.add(new Program(
            "Inverted Pyramid",
            "Print inverted pyramid.",
            "public class InvPyramid {\\n    public static void main(String[] args) {\\n        int n = 5;\\n        for (int i = n; i >= 1; i--) {\\n            for (int j = 1; j <= n-i; j++)\\n                System.out.print(\\\" \\\");\\n            for (int j = 1; j <= 2*i-1; j++)\\n                System.out.print(\\\"*\\\");\\n            System.out.println();\\n        }\\n    }\\n}",
            "*********\\n *******\\n  *****\\n   ***\\n    *"
        ));
        loopPrograms.add(new Program(
            "Nested Loop Table",
            "Multiplication table using nested loop.",
            "public class NestedTable {\\n    public static void main(String[] args) {\\n        for (int i = 1; i <= 3; i++) {\\n            for (int j = 1; j <= 5; j++)\\n                System.out.print((i*j) + \\\" \\\");\\n            System.out.println();\\n        }\\n    }\\n}",
            "1 2 3 4 5\\n2 4 6 8 10\\n3 6 9 12 15"
        ));
        loopPrograms.add(new Program(
            "Sum 1 to N",
            "Sum of first N numbers.",
            "public class SumN {\\n    public static void main(String[] args) {\\n        int n = 100, sum = 0;\\n        for (int i = 1; i <= n; i++)\\n            sum += i;\\n        System.out.println(\\\"Sum = \\\" + sum);\\n    }\\n}",
            "Sum = 5050"
        ));
        loopPrograms.add(new Program(
            "Even Numbers",
            "Print even numbers 1 to 20.",
            "public class EvenNums {\\n    public static void main(String[] args) {\\n        for (int i = 2; i <= 20; i += 2)\\n            System.out.print(i + \\\" \\\");\\n    }\\n}",
            "2 4 6 8 10 12 14 16 18 20"
        ));
        loopPrograms.add(new Program(
            "Odd Numbers",
            "Print odd numbers 1 to 20.",
            "public class OddNums {\\n    public static void main(String[] args) {\\n        for (int i = 1; i <= 20; i += 2)\\n            System.out.print(i + \\\" \\\");\\n    }\\n}",
            "1 3 5 7 9 11 13 15 17 19"
        ));
        loopPrograms.add(new Program(
            "Countdown",
            "Print countdown from 10 to 1.",
            "public class Countdown {\\n    public static void main(String[] args) {\\n        for (int i = 10; i >= 1; i--)\\n            System.out.print(i + \\\" \\\");\\n    }\\n}",
            "10 9 8 7 6 5 4 3 2 1"
        ));
        loopPrograms.add(new Program(
            "Alphabet Pattern",
            "Print alphabet pattern.",
            "public class AlphaPattern {\\n    public static void main(String[] args) {\\n        char ch = 'A';\\n        for (int i = 1; i <= 5; i++) {\\n            for (int j = 1; j <= i; j++)\\n                System.out.print(ch++ + \\\" \\\");\\n            System.out.println();\\n        }\\n    }\\n}",
            "A\\nB C\\nD E F\\nG H I J\\nK L M N O"
        ));
        loopPrograms.add(new Program(
            "Factorial Table",
            "Print factorial of 1 to 10.",
            "public class FactTable {\\n    public static void main(String[] args) {\\n        for (int i = 1; i <= 10; i++) {\\n            int fact = 1;\\n            for (int j = 1; j <= i; j++)\\n                fact *= j;\\n            System.out.println(i + \\\"! = \\\" + fact);\\n        }\\n    }\\n}",
            "1! = 1\\n2! = 2\\n3! = 6\\n...\\n10! = 3628800"
        ));
        loopPrograms.add(new Program(
            "Power Table",
            "Print powers of 2.",
            "public class PowerTable {\\n    public static void main(String[] args) {\\n        int pow = 1;\\n        for (int i = 0; i <= 10; i++) {\\n            System.out.println(\\\"2^\\\" + i + \\\" = \\\" + pow);\\n            pow *= 2;\\n        }\\n    }\\n}",
            "2^0 = 1\\n2^1 = 2\\n2^2 = 4\\n...\\n2^10 = 1024"
        ));
        loopPrograms.add(new Program(
            "Infinite Loop Break",
            "Controlled infinite loop.",
            "public class InfiniteBreak {\\n    public static void main(String[] args) {\\n        int count = 0;\\n        while (true) {\\n            System.out.print(count + \\\" \\\");\\n            count++;\\n            if (count == 10) break;\\n        }\\n    }\\n}",
            "0 1 2 3 4 5 6 7 8 9"
        ));
        programsByCategory.put("Loops & Patterns", loopPrograms);

        // ==================== ARRAYS & STRINGS ====================
        List<Program> arrayPrograms = new ArrayList<>();
        arrayPrograms.add(new Program(
            "Array Sum",
            "Sum of array elements.",
            "public class ArraySum {\\n    public static void main(String[] args) {\\n        int[] arr = {1, 2, 3, 4, 5};\\n        int sum = 0;\\n        for (int num : arr)\\n            sum += num;\\n        System.out.println(\\\"Sum = \\\" + sum);\\n    }\\n}",
            "Sum = 15"
        ));
        arrayPrograms.add(new Program(
            "Find Largest",
            "Largest element in array.",
            "public class FindLargest {\\n    public static void main(String[] args) {\\n        int[] arr = {10, 25, 5, 30, 15};\\n        int max = arr[0];\\n        for (int i = 1; i < arr.length; i++)\\n            if (arr[i] > max) max = arr[i];\\n        System.out.println(\\\"Largest = \\\" + max);\\n    }\\n}",
            "Largest = 30"
        ));
        arrayPrograms.add(new Program(
            "Reverse Array",
            "Reverse array elements.",
            "public class ReverseArray {\\n    public static void main(String[] args) {\\n        int[] arr = {1, 2, 3, 4, 5};\\n        for (int i = 0; i < arr.length/2; i++) {\\n            int temp = arr[i];\\n            arr[i] = arr[arr.length-1-i];\\n            arr[arr.length-1-i] = temp;\\n        }\\n        for (int num : arr)\\n            System.out.print(num + \\\" \\\");\\n    }\\n}",
            "5 4 3 2 1"
        ));
        arrayPrograms.add(new Program(
            "String Length",
            "Find string length.",
            "public class StrLength {\\n    public static void main(String[] args) {\\n        String str = \\\"Hello\\\";\\n        System.out.println(\\\"Length = \\\" + str.length());\\n    }\\n}",
            "Length = 5"
        ));
        arrayPrograms.add(new Program(
            "String Reverse",
            "Reverse a string.",
            "public class StrReverse {\\n    public static void main(String[] args) {\\n        String str = \\\"Hello\\\";\\n        String rev = \\\"\\\";\\n        for (int i = str.length()-1; i >= 0; i--)\\n            rev += str.charAt(i);\\n        System.out.println(\\\"Reversed: \\\" + rev);\\n    }\\n}",
            "Reversed: olleH"
        ));
        arrayPrograms.add(new Program(
            "Search Element",
            "Linear search in array.",
            "public class SearchElement {\\n    public static void main(String[] args) {\\n        int[] arr = {10, 20, 30, 40, 50};\\n        int key = 30, found = -1;\\n        for (int i = 0; i < arr.length; i++) {\\n            if (arr[i] == key) {\\n                found = i;\\n                break;\\n            }\\n        }\\n        System.out.println(\\\"Found at index: \\\" + found);\\n    }\\n}",
            "Found at index: 2"
        ));
        arrayPrograms.add(new Program(
            "Array Average",
            "Calculate average of array.",
            "public class ArrayAvg {\\n    public static void main(String[] args) {\\n        int[] arr = {10, 20, 30, 40, 50};\\n        int sum = 0;\\n        for (int num : arr) sum += num;\\n        double avg = (double)sum / arr.length;\\n        System.out.println(\\\"Average = \\\" + avg);\\n    }\\n}",
            "Average = 30.0"
        ));
        arrayPrograms.add(new Program(
            "Find Smallest",
            "Find smallest element.",
            "public class FindSmallest {\\n    public static void main(String[] args) {\\n        int[] arr = {10, 25, 5, 30, 15};\\n        int min = arr[0];\\n        for (int i = 1; i < arr.length; i++)\\n            if (arr[i] < min) min = arr[i];\\n        System.out.println(\\\"Smallest = \\\" + min);\\n    }\\n}",
            "Smallest = 5"
        ));
        arrayPrograms.add(new Program(
            "Copy Array",
            "Copy array elements.",
            "public class CopyArray {\\n    public static void main(String[] args) {\\n        int[] arr1 = {1, 2, 3, 4, 5};\\n        int[] arr2 = new int[5];\\n        for (int i = 0; i < arr1.length; i++)\\n            arr2[i] = arr1[i];\\n        for (int num : arr2)\\n            System.out.print(num + \\\" \\\");\\n    }\\n}",
            "1 2 3 4 5"
        ));
        arrayPrograms.add(new Program(
            "Bubble Sort",
            "Sort array using bubble sort.",
            "public class BubbleSort {\\n    public static void main(String[] args) {\\n        int[] arr = {5, 2, 8, 1, 9};\\n        for (int i = 0; i < arr.length-1; i++)\\n            for (int j = 0; j < arr.length-i-1; j++)\\n                if (arr[j] > arr[j+1]) {\\n                    int temp = arr[j];\\n                    arr[j] = arr[j+1];\\n                    arr[j+1] = temp;\\n                }\\n        for (int num : arr)\\n            System.out.print(num + \\\" \\\");\\n    }\\n}",
            "1 2 5 8 9"
        ));
        arrayPrograms.add(new Program(
            "Count Even Odd",
            "Count even and odd numbers.",
            "public class CountEvenOdd {\\n    public static void main(String[] args) {\\n        int[] arr = {1, 2, 3, 4, 5, 6};\\n        int even = 0, odd = 0;\\n        for (int num : arr)\\n            if (num % 2 == 0) even++;\\n            else odd++;\\n        System.out.println(\\\"Even: \\\" + even + \\\", Odd: \\\" + odd);\\n    }\\n}",
            "Even: 3, Odd: 3"
        ));
        arrayPrograms.add(new Program(
            "String Concatenation",
            "Concatenate two strings.",
            "public class StrConcat {\\n    public static void main(String[] args) {\\n        String str1 = \\\"Hello \\\";\\n        String str2 = \\\"World\\\";\\n        System.out.println(str1 + str2);\\n    }\\n}",
            "Hello World"
        ));
        arrayPrograms.add(new Program(
            "String Compare",
            "Compare two strings.",
            "public class StrCompare {\\n    public static void main(String[] args) {\\n        String str1 = \\\"Hello\\\";\\n        String str2 = \\\"Hello\\\";\\n        if (str1.equals(str2))\\n            System.out.println(\\\"Equal\\\");\\n        else\\n            System.out.println(\\\"Not equal\\\");\\n    }\\n}",
            "Equal"
        ));
        arrayPrograms.add(new Program(
            "Palindrome String",
            "Check if string is palindrome.",
            "public class PalindromeStr {\\n    public static void main(String[] args) {\\n        String str = \\\"madam\\\";\\n        String rev = \\\"\\\";\\n        for (int i = str.length()-1; i >= 0; i--)\\n            rev += str.charAt(i);\\n        if (str.equals(rev))\\n            System.out.println(\\\"Palindrome\\\");\\n        else\\n            System.out.println(\\\"Not palindrome\\\");\\n    }\\n}",
            "Palindrome"
        ));
        arrayPrograms.add(new Program(
            "Count Vowels",
            "Count vowels in string.",
            "public class CountVowels {\\n    public static void main(String[] args) {\\n        String str = \\\"Hello World\\\";\\n        int count = 0;\\n        for (char c : str.toLowerCase().toCharArray())\\n            if (c=='a'||c=='e'||c=='i'||c=='o'||c=='u')\\n                count++;\\n        System.out.println(\\\"Vowels: \\\" + count);\\n    }\\n}",
            "Vowels: 3"
        ));
        arrayPrograms.add(new Program(
            "Uppercase to Lowercase",
            "Convert string to lowercase.",
            "public class ToLower {\\n    public static void main(String[] args) {\\n        String str = \\\"HELLO\\\";\\n        System.out.println(str.toLowerCase());\\n    }\\n}",
            "hello"
        ));
        arrayPrograms.add(new Program(
            "Lowercase to Uppercase",
            "Convert string to uppercase.",
            "public class ToUpper {\\n    public static void main(String[] args) {\\n        String str = \\\"hello\\\";\\n        System.out.println(str.toUpperCase());\\n    }\\n}",
            "HELLO"
        ));
        arrayPrograms.add(new Program(
            "String Substring",
            "Extract substring.",
            "public class SubStr {\\n    public static void main(String[] args) {\\n        String str = \\\"Hello World\\\";\\n        System.out.println(str.substring(0, 5));\\n    }\\n}",
            "Hello"
        ));
        arrayPrograms.add(new Program(
            "String Replace",
            "Replace characters in string.",
            "public class StrReplace {\\n    public static void main(String[] args) {\\n        String str = \\\"Hello World\\\";\\n        System.out.println(str.replace('o', 'a'));\\n    }\\n}",
            "Hella Warld"
        ));
        arrayPrograms.add(new Program(
            "2D Array Sum",
            "Sum of 2D array elements.",
            "public class Array2DSum {\\n    public static void main(String[] args) {\\n        int[][] arr = {{1,2,3}, {4,5,6}};\\n        int sum = 0;\\n        for (int[] row : arr)\\n            for (int num : row)\\n                sum += num;\\n        System.out.println(\\\"Sum = \\\" + sum);\\n    }\\n}",
            "Sum = 21"
        ));
        programsByCategory.put("Arrays & Strings", arrayPrograms);

        // ==================== METHODS ====================
        List<Program> methodPrograms = new ArrayList<>();
        methodPrograms.add(new Program(
            "Simple Method",
            "Method without parameters.",
            "public class SimpleMethod {\\n    static void greet() {\\n        System.out.println(\\\"Hello!\\\");\\n    }\\n    public static void main(String[] args) {\\n        greet();\\n    }\\n}",
            "Hello!"
        ));
        methodPrograms.add(new Program(
            "Method with Parameters",
            "Method with parameters.",
            "public class MethodParam {\\n    static int add(int a, int b) {\\n        return a + b;\\n    }\\n    public static void main(String[] args) {\\n        System.out.println(\\\"Sum = \\\" + add(5, 3));\\n    }\\n}",
            "Sum = 8"
        ));
        methodPrograms.add(new Program(
            "Method Overloading",
            "Same name different parameters.",
            "public class Overload {\\n    static int add(int a, int b) { return a + b; }\\n    static double add(double a, double b) { return a + b; }\\n    public static void main(String[] args) {\\n        System.out.println(add(5, 3));\\n        System.out.println(add(5.5, 3.2));\\n    }\\n}",
            "8\\n8.7"
        ));
        methodPrograms.add(new Program(
            "Recursion",
            "Factorial using recursion.",
            "public class Recursion {\\n    static int factorial(int n) {\\n        if (n == 0) return 1;\\n        return n * factorial(n-1);\\n    }\\n    public static void main(String[] args) {\\n        System.out.println(\\\"5! = \\\" + factorial(5));\\n    }\\n}",
            "5! = 120"
        ));
        methodPrograms.add(new Program(
            "Return Value Method",
            "Method returning value.",
            "public class ReturnMethod {\\n    static int square(int n) {\\n        return n * n;\\n    }\\n    public static void main(String[] args) {\\n        System.out.println(\\\"Square: \\\" + square(5));\\n    }\\n}",
            "Square: 25"
        ));
        methodPrograms.add(new Program(
            "Void Method",
            "Method without return value.",
            "public class VoidMethod {\\n    static void printMsg() {\\n        System.out.println(\\\"Hello from method!\\\");\\n    }\\n    public static void main(String[] args) {\\n        printMsg();\\n    }\\n}",
            "Hello from method!"
        ));
        methodPrograms.add(new Program(
            "Pass by Value",
            "Demonstrate pass by value.",
            "public class PassByValue {\\n    static void change(int x) {\\n        x = 100;\\n    }\\n    public static void main(String[] args) {\\n        int num = 10;\\n        change(num);\\n        System.out.println(\\\"num = \\\" + num);\\n    }\\n}",
            "num = 10"
        ));
        methodPrograms.add(new Program(
            "Array as Parameter",
            "Pass array to method.",
            "public class ArrayParam {\\n    static void printArray(int[] arr) {\\n        for (int num : arr)\\n            System.out.print(num + \\\" \\\");\\n    }\\n    public static void main(String[] args) {\\n        int[] nums = {1, 2, 3, 4, 5};\\n        printArray(nums);\\n    }\\n}",
            "1 2 3 4 5"
        ));
        methodPrograms.add(new Program(
            "Fibonacci Recursion",
            "Fibonacci using recursion.",
            "public class FibRecursion {\\n    static int fib(int n) {\\n        if (n <= 1) return n;\\n        return fib(n-1) + fib(n-2);\\n    }\\n    public static void main(String[] args) {\\n        for (int i = 0; i < 10; i++)\\n            System.out.print(fib(i) + \\\" \\\");\\n    }\\n}",
            "0 1 1 2 3 5 8 13 21 34"
        ));
        methodPrograms.add(new Program(
            "Sum Recursion",
            "Sum using recursion.",
            "public class SumRecursion {\\n    static int sum(int n) {\\n        if (n == 0) return 0;\\n        return n + sum(n-1);\\n    }\\n    public static void main(String[] args) {\\n        System.out.println(\\\"Sum = \\\" + sum(10));\\n    }\\n}",
            "Sum = 55"
        ));
        programsByCategory.put("Methods", methodPrograms);

        // ==================== OOP - CLASSES & OBJECTS ====================
        List<Program> oopPrograms = new ArrayList<>();
        oopPrograms.add(new Program(
            "Simple Class",
            "Create class and object.",
            "class Student {\\n    String name;\\n    int age;\\n    void display() {\\n        System.out.println(name + \\\", \\\" + age);\\n    }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Student s = new Student();\\n        s.name = \\\"Alice\\\";\\n        s.age = 20;\\n        s.display();\\n    }\\n}",
            "Alice, 20"
        ));
        oopPrograms.add(new Program(
            "Constructor",
            "Using constructor.",
            "class Student {\\n    String name;\\n    Student(String n) { name = n; }\\n    void display() { System.out.println(name); }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Student s = new Student(\\\"Bob\\\");\\n        s.display();\\n    }\\n}",
            "Bob"
        ));
        oopPrograms.add(new Program(
            "this Keyword",
            "Using this keyword.",
            "class Student {\\n    String name;\\n    Student(String name) {\\n        this.name = name;\\n    }\\n    void display() {\\n        System.out.println(this.name);\\n    }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Student s = new Student(\\\"Charlie\\\");\\n        s.display();\\n    }\\n}",
            "Charlie"
        ));
        oopPrograms.add(new Program("Getters and Setters", "Encapsulation example.", "class Person {\\n    private String name;\\n    public void setName(String n) { name = n; }\\n    public String getName() { return name; }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Person p = new Person();\\n        p.setName(\\\"John\\\");\\n        System.out.println(p.getName());\\n    }\\n}", "John"));
        oopPrograms.add(new Program("Multiple Objects", "Create multiple objects.", "class Car {\\n    String model;\\n    Car(String m) { model = m; }\\n    void display() { System.out.println(model); }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Car c1 = new Car(\\\"Tesla\\\");\\n        Car c2 = new Car(\\\"BMW\\\");\\n        c1.display();\\n        c2.display();\\n    }\\n}", "Tesla\\nBMW"));
        oopPrograms.add(new Program("Static Variable", "Class variable example.", "class Counter {\\n    static int count = 0;\\n    Counter() { count++; }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        new Counter();\\n        new Counter();\\n        System.out.println(\\\"Count: \\\" + Counter.count);\\n    }\\n}", "Count: 2"));
        oopPrograms.add(new Program("Static Method", "Class method example.", "class Math {\\n    static int add(int a, int b) {\\n        return a + b;\\n    }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        System.out.println(Math.add(5, 3));\\n    }\\n}", "8"));
        oopPrograms.add(new Program("Constructor Overloading", "Multiple constructors.", "class Box {\\n    int size;\\n    Box() { size = 10; }\\n    Box(int s) { size = s; }\\n    void show() { System.out.println(\\\"Size: \\\" + size); }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        new Box().show();\\n        new Box(20).show();\\n    }\\n}", "Size: 10\\nSize: 20"));
        oopPrograms.add(new Program("Object as Parameter", "Pass object to method.", "class Number {\\n    int val;\\n    Number(int v) { val = v; }\\n}\\npublic class Main {\\n    static void show(Number n) {\\n        System.out.println(n.val);\\n    }\\n    public static void main(String[] args) {\\n        show(new Number(100));\\n    }\\n}", "100"));
        oopPrograms.add(new Program("Return Object", "Method returning object.", "class Point {\\n    int x, y;\\n    Point(int a, int b) { x=a; y=b; }\\n    Point add(Point p) {\\n        return new Point(x+p.x, y+p.y);\\n    }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Point p1 = new Point(1,2);\\n        Point p2 = new Point(3,4);\\n        Point p3 = p1.add(p2);\\n        System.out.println(p3.x + \\\",\\\" + p3.y);\\n    }\\n}", "4,6"));
        oopPrograms.add(new Program("Access Modifiers", "Public vs Private.", "class Demo {\\n    public int pub = 10;\\n    private int priv = 20;\\n    public int getPriv() { return priv; }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Demo d = new Demo();\\n        System.out.println(d.pub);\\n        System.out.println(d.getPriv());\\n    }\\n}", "10\\n20"));
        oopPrograms.add(new Program("Final Variable", "Constant in class.", "class Circle {\\n    final double PI = 3.14;\\n    double area(double r) {\\n        return PI * r * r;\\n    }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        System.out.println(new Circle().area(5));\\n    }\\n}", "78.5"));
        oopPrograms.add(new Program("Nested Class", "Class inside class.", "class Outer {\\n    class Inner {\\n        void display() {\\n            System.out.println(\\\"Inner class\\\");\\n        }\\n    }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Outer.Inner obj = new Outer().new Inner();\\n        obj.display();\\n    }\\n}", "Inner class"));
        oopPrograms.add(new Program("Object Array", "Array of objects.", "class Student {\\n    String name;\\n    Student(String n) { name = n; }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Student[] arr = new Student[3];\\n        arr[0] = new Student(\\\"A\\\");\\n        arr[1] = new Student(\\\"B\\\");\\n        arr[2] = new Student(\\\"C\\\");\\n        for(Student s : arr)\\n            System.out.println(s.name);\\n    }\\n}", "A\\nB\\nC"));
        oopPrograms.add(new Program("toString Method", "Override toString.", "class Book {\\n    String title;\\n    Book(String t) { title = t; }\\n    public String toString() {\\n        return \\\"Book: \\\" + title;\\n    }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        System.out.println(new Book(\\\"Java\\\"));\\n    }\\n}", "Book: Java"));
        programsByCategory.put("OOP - Classes & Objects", oopPrograms);

        // ==================== OOP - INHERITANCE ====================
        List<Program> inheritancePrograms = new ArrayList<>();
        inheritancePrograms.add(new Program(
            "Simple Inheritance",
            "Inherit from parent.",
            "class Animal {\\n    void eat() { System.out.println(\\\"Eating\\\"); }\\n}\\nclass Dog extends Animal {\\n    void bark() { System.out.println(\\\"Barking\\\"); }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Dog d = new Dog();\\n        d.eat();\\n        d.bark();\\n    }\\n}",
            "Eating\\nBarking"
        ));
        inheritancePrograms.add(new Program(
            "Method Overriding",
            "Override parent method.",
            "class Animal {\\n    void sound() { System.out.println(\\\"Animal sound\\\"); }\\n}\\nclass Dog extends Animal {\\n    void sound() { System.out.println(\\\"Bark\\\"); }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Dog d = new Dog();\\n        d.sound();\\n    }\\n}",
            "Bark"
        ));
        inheritancePrograms.add(new Program("super Keyword", "Call parent constructor.", "class Animal {\\n    Animal() { System.out.println(\\\"Animal created\\\"); }\\n}\\nclass Dog extends Animal {\\n    Dog() {\\n        super();\\n        System.out.println(\\\"Dog created\\\");\\n    }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        new Dog();\\n    }\\n}", "Animal created\\nDog created"));
        inheritancePrograms.add(new Program("Multilevel Inheritance", "Chain of inheritance.", "class A {\\n    void showA() { System.out.println(\\\"A\\\"); }\\n}\\nclass B extends A {\\n    void showB() { System.out.println(\\\"B\\\"); }\\n}\\nclass C extends B {\\n    void showC() { System.out.println(\\\"C\\\"); }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        C obj = new C();\\n        obj.showA();\\n        obj.showB();\\n        obj.showC();\\n    }\\n}", "A\\nB\\nC"));
        inheritancePrograms.add(new Program("Hierarchical Inheritance", "Multiple child classes.", "class Shape {\\n    void draw() { System.out.println(\\\"Drawing\\\"); }\\n}\\nclass Circle extends Shape {}\\nclass Square extends Shape {}\\npublic class Main {\\n    public static void main(String[] args) {\\n        new Circle().draw();\\n        new Square().draw();\\n    }\\n}", "Drawing\\nDrawing"));
        inheritancePrograms.add(new Program("Protected Access", "Protected member access.", "class Parent {\\n    protected int num = 100;\\n}\\nclass Child extends Parent {\\n    void show() {\\n        System.out.println(\\\"Num: \\\" + num);\\n    }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        new Child().show();\\n    }\\n}", "Num: 100"));
        inheritancePrograms.add(new Program("Method Override", "Override parent method.", "class Vehicle {\\n    void run() { System.out.println(\\\"Vehicle running\\\"); }\\n}\\nclass Car extends Vehicle {\\n    void run() { System.out.println(\\\"Car running\\\"); }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        new Car().run();\\n    }\\n}", "Car running"));
        inheritancePrograms.add(new Program("super Method Call", "Call parent method.", "class Parent {\\n    void display() { System.out.println(\\\"Parent\\\"); }\\n}\\nclass Child extends Parent {\\n    void display() {\\n        super.display();\\n        System.out.println(\\\"Child\\\");\\n    }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        new Child().display();\\n    }\\n}", "Parent\\nChild"));
        inheritancePrograms.add(new Program("Constructor Chain", "Constructor chaining.", "class A {\\n    A() { System.out.println(\\\"A\\\"); }\\n}\\nclass B extends A {\\n    B() { System.out.println(\\\"B\\\"); }\\n}\\nclass C extends B {\\n    C() { System.out.println(\\\"C\\\"); }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        new C();\\n    }\\n}", "A\\nB\\nC"));
        inheritancePrograms.add(new Program("Final Method", "Prevent method override.", "class Parent {\\n    final void show() {\\n        System.out.println(\\\"Cannot override\\\");\\n    }\\n}\\nclass Child extends Parent {}\\npublic class Main {\\n    public static void main(String[] args) {\\n        new Child().show();\\n    }\\n}", "Cannot override"));
        inheritancePrograms.add(new Program("Final Class", "Prevent inheritance.", "final class Parent {\\n    void display() {\\n        System.out.println(\\\"Final class\\\");\\n    }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        new Parent().display();\\n    }\\n}", "Final class"));
        inheritancePrograms.add(new Program("IS-A Relationship", "Inheritance relationship.", "class Animal {}\\nclass Dog extends Animal {}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Dog d = new Dog();\\n        System.out.println(d instanceof Animal);\\n        System.out.println(d instanceof Dog);\\n    }\\n}", "true\\ntrue"));
        programsByCategory.put("OOP - Inheritance", inheritancePrograms);

        // ==================== OOP - POLYMORPHISM ====================
        List<Program> polyPrograms = new ArrayList<>();
        polyPrograms.add(new Program(
            "Runtime Polymorphism",
            "Method overriding.",
            "class Animal {\\n    void sound() { System.out.println(\\\"Animal\\\"); }\\n}\\nclass Dog extends Animal {\\n    void sound() { System.out.println(\\\"Bark\\\"); }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Animal a = new Dog();\\n        a.sound();\\n    }\\n}",
            "Bark"
        ));
        polyPrograms.add(new Program(
            "instanceof",
            "Check object type.",
            "class Animal {}\\nclass Dog extends Animal {}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Animal a = new Dog();\\n        System.out.println(a instanceof Dog);\\n    }\\n}",
            "true"
        ));
        polyPrograms.add(new Program("Upcasting", "Parent reference to child.", "class Animal {}\\nclass Dog extends Animal {}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Animal a = new Dog();\\n        System.out.println(\\\"Upcasting done\\\");\\n    }\\n}", "Upcasting done"));
        polyPrograms.add(new Program("Downcasting", "Child reference from parent.", "class Animal {}\\nclass Dog extends Animal {\\n    void bark() { System.out.println(\\\"Bark\\\"); }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Animal a = new Dog();\\n        Dog d = (Dog) a;\\n        d.bark();\\n    }\\n}", "Bark"));
        polyPrograms.add(new Program("Method Overriding", "Runtime polymorphism.", "class Shape {\\n    void area() { System.out.println(\\\"Area\\\"); }\\n}\\nclass Circle extends Shape {\\n    void area() { System.out.println(\\\"Circle area\\\"); }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Shape s = new Circle();\\n        s.area();\\n    }\\n}", "Circle area"));
        polyPrograms.add(new Program("Abstract Class", "Abstract class example.", "abstract class Animal {\\n    abstract void sound();\\n}\\nclass Dog extends Animal {\\n    void sound() { System.out.println(\\\"Bark\\\"); }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Animal a = new Dog();\\n        a.sound();\\n    }\\n}", "Bark"));
        polyPrograms.add(new Program("Interface", "Interface implementation.", "interface Drawable {\\n    void draw();\\n}\\nclass Circle implements Drawable {\\n    public void draw() {\\n        System.out.println(\\\"Drawing circle\\\");\\n    }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Drawable d = new Circle();\\n        d.draw();\\n    }\\n}", "Drawing circle"));
        polyPrograms.add(new Program("Multiple Interfaces", "Implement multiple interfaces.", "interface A {\\n    void showA();\\n}\\ninterface B {\\n    void showB();\\n}\\nclass C implements A, B {\\n    public void showA() { System.out.println(\\\"A\\\"); }\\n    public void showB() { System.out.println(\\\"B\\\"); }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        C obj = new C();\\n        obj.showA();\\n        obj.showB();\\n    }\\n}", "A\\nB"));
        polyPrograms.add(new Program("Covariant Return", "Return subtype.", "class A {\\n    A get() { return this; }\\n}\\nclass B extends A {\\n    B get() { return this; }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        new B().get();\\n        System.out.println(\\\"Covariant return\\\");\\n    }\\n}", "Covariant return"));
        polyPrograms.add(new Program("Dynamic Binding", "Runtime method binding.", "class Parent {\\n    void show() { System.out.println(\\\"Parent\\\"); }\\n}\\nclass Child extends Parent {\\n    void show() { System.out.println(\\\"Child\\\"); }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        Parent p = new Child();\\n        p.show();\\n    }\\n}", "Child"));
        programsByCategory.put("OOP - Polymorphism", polyPrograms);

        // ==================== EXCEPTION HANDLING ====================
        List<Program> exceptionPrograms = new ArrayList<>();
        exceptionPrograms.add(new Program(
            "try-catch",
            "Handle exceptions.",
            "public class TryCatch {\\n    public static void main(String[] args) {\\n        try {\\n            int result = 10 / 0;\\n        } catch (ArithmeticException e) {\\n            System.out.println(\\\"Error: \\\" + e.getMessage());\\n        }\\n    }\\n}",
            "Error: / by zero"
        ));
        exceptionPrograms.add(new Program(
            "finally Block",
            "Always executes.",
            "public class Finally {\\n    public static void main(String[] args) {\\n        try {\\n            int[] arr = {1, 2, 3};\\n            System.out.println(arr[5]);\\n        } catch (Exception e) {\\n            System.out.println(\\\"Error\\\");\\n        } finally {\\n            System.out.println(\\\"Finally\\\");\\n        }\\n    }\\n}",
            "Error\\nFinally"
        ));
        exceptionPrograms.add(new Program("Multiple catch", "Handle multiple exceptions.", "public class MultipleCatch {\\n    public static void main(String[] args) {\\n        try {\\n            int[] arr = {1, 2};\\n            System.out.println(arr[5]);\\n        } catch (ArrayIndexOutOfBoundsException e) {\\n            System.out.println(\\\"Array error\\\");\\n        } catch (Exception e) {\\n            System.out.println(\\\"General error\\\");\\n        }\\n    }\\n}", "Array error"));
        exceptionPrograms.add(new Program("throws Keyword", "Declare exception.", "public class ThrowsDemo {\\n    static void check() throws ArithmeticException {\\n        throw new ArithmeticException(\\\"Error\\\");\\n    }\\n    public static void main(String[] args) {\\n        try {\\n            check();\\n        } catch (Exception e) {\\n            System.out.println(e.getMessage());\\n        }\\n    }\\n}", "Error"));
        exceptionPrograms.add(new Program("Custom Exception", "User-defined exception.", "class MyException extends Exception {\\n    MyException(String msg) { super(msg); }\\n}\\npublic class Main {\\n    public static void main(String[] args) {\\n        try {\\n            throw new MyException(\\\"Custom error\\\");\\n        } catch (MyException e) {\\n            System.out.println(e.getMessage());\\n        }\\n    }\\n}", "Custom error"));
        exceptionPrograms.add(new Program("Nested try", "Try inside try.", "public class NestedTry {\\n    public static void main(String[] args) {\\n        try {\\n            try {\\n                int a = 10/0;\\n            } catch (ArithmeticException e) {\\n                System.out.println(\\\"Inner catch\\\");\\n            }\\n            int[] arr = new int[5];\\n            arr[10] = 50;\\n        } catch (Exception e) {\\n            System.out.println(\\\"Outer catch\\\");\\n        }\\n    }\\n}", "Inner catch\\nOuter catch"));
        exceptionPrograms.add(new Program("NullPointerException", "Handle null pointer.", "public class NullPointer {\\n    public static void main(String[] args) {\\n        try {\\n            String str = null;\\n            System.out.println(str.length());\\n        } catch (NullPointerException e) {\\n            System.out.println(\\\"Null error\\\");\\n        }\\n    }\\n}", "Null error"));
        exceptionPrograms.add(new Program("NumberFormatException", "Parse error handling.", "public class NumberFormat {\\n    public static void main(String[] args) {\\n        try {\\n            int num = Integer.parseInt(\\\"abc\\\");\\n        } catch (NumberFormatException e) {\\n            System.out.println(\\\"Invalid number\\\");\\n        }\\n    }\\n}", "Invalid number"));
        programsByCategory.put("Exception Handling", exceptionPrograms);

        // ==================== COLLECTIONS ====================
        List<Program> collectionPrograms = new ArrayList<>();
        collectionPrograms.add(new Program(
            "ArrayList",
            "Dynamic array.",
            "import java.util.ArrayList;\\npublic class ArrayListDemo {\\n    public static void main(String[] args) {\\n        ArrayList<String> list = new ArrayList<>();\\n        list.add(\\\"Java\\\");\\n        list.add(\\\"Python\\\");\\n        System.out.println(list);\\n    }\\n}",
            "[Java, Python]"
        ));
        collectionPrograms.add(new Program(
            "HashMap",
            "Key-value pairs.",
            "import java.util.HashMap;\\npublic class HashMapDemo {\\n    public static void main(String[] args) {\\n        HashMap<String, Integer> map = new HashMap<>();\\n        map.put(\\\"Java\\\", 95);\\n        System.out.println(map);\\n    }\\n}",
            "{Java=95}"
        ));
        collectionPrograms.add(new Program("ArrayList Operations", "Add, remove, get operations.", "import java.util.ArrayList;\\npublic class ArrayListOps {\\n    public static void main(String[] args) {\\n        ArrayList<Integer> list = new ArrayList<>();\\n        list.add(10);\\n        list.add(20);\\n        list.add(30);\\n        list.remove(1);\\n        System.out.println(list);\\n        System.out.println(\\\"Get: \\\" + list.get(0));\\n    }\\n}", "[10, 30]\\nGet: 10"));
        collectionPrograms.add(new Program("LinkedList", "LinkedList example.", "import java.util.LinkedList;\\npublic class LinkedListDemo {\\n    public static void main(String[] args) {\\n        LinkedList<String> list = new LinkedList<>();\\n        list.add(\\\"A\\\");\\n        list.add(\\\"B\\\");\\n        list.addFirst(\\\"Start\\\");\\n        list.addLast(\\\"End\\\");\\n        System.out.println(list);\\n    }\\n}", "[Start, A, B, End]"));
        collectionPrograms.add(new Program("HashSet Operations", "Set operations.", "import java.util.HashSet;\\npublic class HashSetOps {\\n    public static void main(String[] args) {\\n        HashSet<Integer> set = new HashSet<>();\\n        set.add(10);\\n        set.add(20);\\n        set.add(10);\\n        System.out.println(set);\\n        System.out.println(\\\"Contains 10: \\\" + set.contains(10));\\n    }\\n}", "[10, 20]\\nContains 10: true"));
        collectionPrograms.add(new Program("HashMap Operations", "Map operations.", "import java.util.HashMap;\\npublic class HashMapOps {\\n    public static void main(String[] args) {\\n        HashMap<String, Integer> map = new HashMap<>();\\n        map.put(\\\"A\\\", 1);\\n        map.put(\\\"B\\\", 2);\\n        map.remove(\\\"A\\\");\\n        System.out.println(map);\\n        System.out.println(\\\"Get B: \\\" + map.get(\\\"B\\\"));\\n    }\\n}", "{B=2}\\nGet B: 2"));
        collectionPrograms.add(new Program("Iterator", "Iterate collection.", "import java.util.*;\\npublic class IteratorDemo {\\n    public static void main(String[] args) {\\n        ArrayList<String> list = new ArrayList<>();\\n        list.add(\\\"A\\\");\\n        list.add(\\\"B\\\");\\n        list.add(\\\"C\\\");\\n        Iterator<String> it = list.iterator();\\n        while(it.hasNext())\\n            System.out.print(it.next() + \\\" \\\");\\n    }\\n}", "A B C"));
        collectionPrograms.add(new Program("TreeSet", "Sorted set.", "import java.util.TreeSet;\\npublic class TreeSetDemo {\\n    public static void main(String[] args) {\\n        TreeSet<Integer> set = new TreeSet<>();\\n        set.add(30);\\n        set.add(10);\\n        set.add(20);\\n        System.out.println(set);\\n    }\\n}", "[10, 20, 30]"));
        collectionPrograms.add(new Program("Vector", "Vector example.", "import java.util.Vector;\\npublic class VectorDemo {\\n    public static void main(String[] args) {\\n        Vector<String> v = new Vector<>();\\n        v.add(\\\"Java\\\");\\n        v.add(\\\"Python\\\");\\n        System.out.println(v);\\n        System.out.println(\\\"Size: \\\" + v.size());\\n    }\\n}", "[Java, Python]\\nSize: 2"));
        collectionPrograms.add(new Program("Stack", "Stack operations.", "import java.util.Stack;\\npublic class StackDemo {\\n    public static void main(String[] args) {\\n        Stack<Integer> stack = new Stack<>();\\n        stack.push(10);\\n        stack.push(20);\\n        stack.push(30);\\n        System.out.println(\\\"Pop: \\\" + stack.pop());\\n        System.out.println(\\\"Peek: \\\" + stack.peek());\\n    }\\n}", "Pop: 30\\nPeek: 20"));
        collectionPrograms.add(new Program("Queue", "Queue operations.", "import java.util.*;\\npublic class QueueDemo {\\n    public static void main(String[] args) {\\n        Queue<String> q = new LinkedList<>();\\n        q.add(\\\"A\\\");\\n        q.add(\\\"B\\\");\\n        q.add(\\\"C\\\");\\n        System.out.println(\\\"Remove: \\\" + q.remove());\\n        System.out.println(\\\"Peek: \\\" + q.peek());\\n    }\\n}", "Remove: A\\nPeek: B"));
        collectionPrograms.add(new Program("Collections Sort", "Sort collection.", "import java.util.*;\\npublic class CollectionsSort {\\n    public static void main(String[] args) {\\n        ArrayList<Integer> list = new ArrayList<>();\\n        list.add(30);\\n        list.add(10);\\n        list.add(20);\\n        Collections.sort(list);\\n        System.out.println(list);\\n    }\\n}", "[10, 20, 30]"));
        programsByCategory.put("Collections", collectionPrograms);
    }

    public static List<Program> getProgramsByCategory(String category) {
        return programsByCategory.getOrDefault(category, new ArrayList<>());
    }

    public static List<String> getAllCategories() {
        return new ArrayList<>(programsByCategory.keySet());
    }
}
