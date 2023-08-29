package quiz01;

public class quiz01 {
    public static void main(String[] args) {
        /* Question 8

        Consider the following snippet of Java code:

        Which of the following is true of executing this code?

                Group of answer choices

        1. When executed, it prints: d is 3 and i is 3

        2. When executed, it prints: d is 3 and i is 3.14

        3. When executed, it prints: d is 3.14 and i is 3

        4. When executed, it prints: d is 3.14 and i is 3.14

        5. The code does not compile.

        6. When executed, the code raises an exception

         */
        double d = 3.14;
        int r = (int) d;
        System.out.println("d is " + d + " and i is " + r);

        /* Question 9

        Consider the following snippet of Java code:

        Which of the following is true of executing this code?

            Group of answer choices

        When executed, it prints: 1

        When executed, it prints: 12

        When executed, it prints: 123

        When executed, it prints: 1

        When executed, it prints: 2

        When executed, it prints: 23

        When executed, it prints: 3

        The code does not compile.

        When executed, the code raises an exception.

         */
        boolean b = true;
        if (10 > 100 && b)
            System.out.print("1");
        else
            System.out.print("2");
        if (10 > 100 || b)
            System.out.println("3");

        /* Question 11

        Code snippet A:

        */

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    System.out.println("Hello, World.");
                }
            }
        }

        /*
        Code snippet B:
        */

        for (int x = 0; x < 100; x++) {
            System.out.println("Hello, World.");
            for (int y = 0; y < 100; y++) {
                System.out.println("Hello, World.");
                for (int z = 0; z < 100; z++) {
                    System.out.println("Hello, World.");
                }
            }
        }
        /*
        Which code snippet prints Hello, World. more times?

        Group of answer choices

        Code snippet A prints more.

        Code snippet B prints more.

        Both code snippets print the same amount.

        /* Question 12
        Consider the following Java statement:
        */
        char[] arr = new char[50];

        /*
        Which of the following expressions accesses the last
        character of the array arr?


                Group of answer choices

        arr[50]

        arr[last]

        arr[arr.length - 1]

        none of these choices

        */

        /* Question 13
        Suppose that we have defined a class called Point that contains only
        two public instance variables, int x and int y. Consider the following
        snippet of Java code:
         */

        /*Point p1 = null;
        if(flag)
            p1 = new Point();
        p1.x = 3;
        p1.y = -5;

        Point p2 = p1;
        p2.x = 10;
        System.out.println("p1.x is " + p1.x + " and p2.x is " + p2.x);
        /*

        Suppose that the value of flag is true. Which of the following is true of executing this code?

                Group of answer choices

        When executed, it prints: p1.x is 3 and p2.x is 3

        When executed, it prints: p1.x is 3 and p2.x is 10

        When executed, it prints: p1.x is 10 and p2.x is 10

        The code does not compile.

        When executed, the code raises an exception.

         */

        /* Question 14
        Referring to the code in the previous question, suppose that the value of flag is false. Which of the following is true of executing this code?
            Group of answer choices

        When executed, it prints: p1.x is 3 and p2.x is 3

        When executed, it prints: p1.x is 3 and p2.x is 10

        When executed, it prints: p1.x is 10 and p2.x is 10

        The code does not compile.

        When executed, the code raises an exception.
         */
    }
}
