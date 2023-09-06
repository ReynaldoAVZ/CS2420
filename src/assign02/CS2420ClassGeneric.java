package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ServiceConfigurationError;

/**
 * This Java class represents an unordered collection of University of Utah students enrolled in CS 2420.
 *
 * NOTE: The word "Class" in the name of this Java class means a collection of students and should not
 *       be confused with the Java term class, which is a blueprint for making objects.
 *
 * @author Aaron Wood and Reynaldo Villarreal and Mikhail Ahmed
 * @version 2023-09-06
 */
public class CS2420ClassGeneric<Type> {

    private ArrayList<CS2420StudentGeneric> studentList;

    /**
     * Creates an empty CS 2420 class.
     */
    public CS2420ClassGeneric() {
        this.studentList = new ArrayList<CS2420StudentGeneric>();
    }

    /**
     * Adds the given student to the collection of students in CS 2420, avoiding duplicates.
     *
     * @param student - student to be added to this CS 2420 class
     * @return true if the student was added,
     * false if the student was not added because they already exist in the collection
     */
    public boolean addStudent(CS2420StudentGeneric student) {
        // Avoid adding same student in 2420
        // Return true if student is added
        if (!(studentList.contains(student))){
            studentList.add(student);
            return true;
        }

        // Otherwise false if student was already in the list
        return false;
    }

    /**
     * Retrieves the CS 2420 student with the given uNID.
     *
     * @param uNID - uNID of student to be retrieved
     * @return the CS 2420 student with the given uNID, or null if no such student exists in the collection
     */
    public CS2420StudentGeneric<Type> lookup(int uNID) {
        // check for an empty student list
        if (studentList.size() == 0) {
            return null;
        }
        // iterate through all the students in student list
        for (CS2420StudentGeneric student : studentList)
        {
            // get the current student ID and match it with the ID we're searching for
            int studentID = student.getUNID();
            if (studentID == uNID)
                return student;
        }
        // if no student in student list matches our uNID, return null
        return null;
    }

    /**
     * Retrieves the CS 2420 student(s) with the given contact information.
     *
     * @param contactInfo - contact information of student(s) to be retrieved
     * @return a list of the CS 2420 student(s) with the given contact information (in any order),
     * or an empty list if no such students exist in the collection
     */
    public ArrayList<CS2420StudentGeneric<Type>> lookup(Type contactInfo) {
        // Creates an array list for students w/ corresponding contact info
        ArrayList retrievedStudents = new ArrayList<CS2420StudentGeneric>();
        // Loop through students and compare contact information
        for (CS2420StudentGeneric student : studentList) {
            // if current student contact info matches what we're looking for
            if (student.getContactInfo().equals(contactInfo))
                // add that student to the list
                retrievedStudents.add(student);
        }
        // return the student list whose contact info matches
        return retrievedStudents;
    }

    /**
     * Adds an assignment, exam, lab, or quiz score for the CS 2420 student with the given uNID.
     * <p>
     * NOTE: If the category string is not one of "assignment", "exam", "lab", or "quiz", or
     * if no student with the uNID exists in the collection, then this method has no effect.
     *
     * @param uNID     - uNID of student whose score is to be added
     * @param score    - the score to be added
     * @param category - the category in which to add the score
     */
    public void addScore(int uNID, double score, String category) {
        // find the student whose uNID matches with what we're looking for
        CS2420StudentGeneric student = lookup(uNID);
        // if no student exists, do nothing
        if (student == null) {
            return;
        }
        // if student exists, add to their score for a category
        student.addScore(score, category);
    }

    /**
     * Computes the average score of all CS 2420 students in the collection.
     *
     * @return the average score, or 0 if there are no students in the collection
     */
    public double computeClassAverage() {
        // declare a sum variable that will hold classAverage
        double classAverage = 0;
        // if the student list is empty
        if (studentList.size() == 0) {
            return 0.0;
        }
        // iterate through all the students in student list
        for (CS2420StudentGeneric student : studentList) {
            // add the current student final score to the sum variable
            classAverage += student.computeFinalScore();
        }
        // divide the sum variable by the number of students in student list
        classAverage = classAverage / studentList.size();
        // return the classAverage
        return classAverage;
    }

    /**
     * Creates a list of contact information for all CS 2420 students in the collection.
     *
     * @return the duplicate-free list of contact information, in any order
     */
    public ArrayList<Type> getContactList() {
        // create a new list that will contain contact information
        ArrayList contactList = new ArrayList<Type>();
        // iterate through all the students in student list
        for (CS2420StudentGeneric student : studentList) {
            // if the current students contact info is not in the new list
            if (! contactList.contains(student.getContactInfo()))
                // add their contact info to the list
                contactList.add(student.getContactInfo());
        }
        // return the list of contact information of students
        return contactList;
    }

    /**
     * Returns the list of CS 2420 students in this class, sorted by
     uNID in ascending order.
     */
    public ArrayList<CS2420StudentGeneric<Type>> getOrderedByUNID() {
        // create a new list of students that will be ordered by UNID
        ArrayList<CS2420StudentGeneric<Type>> studentListCopy = new ArrayList<CS2420StudentGeneric<Type>>();
        // iterate through each student in student list
        for(CS2420StudentGeneric<Type> student : studentList)
            // add the student to the new list of students that will be sorted
            studentListCopy.add(student);
        // sort the student list copy using a private comparator that uses student uNID to sort in ascending order
        sort(studentListCopy, new OrderByUNID());
        // return the sorted list
        return studentListCopy;
    }
    /**
     * Returns the list of CS 2420 students in this class, sorted by
     last name in lexicographical order.
     * Breaks ties in last names using first names (lexicographical
     order).
     * Breaks ties in first names using uNIDs (ascending order).
     */
    public ArrayList<CS2420StudentGeneric<Type>> getOrderedByName() {
        // create a new list of students that will be ordered by name
        ArrayList<CS2420StudentGeneric<Type>> studentNameListCopy = new ArrayList<CS2420StudentGeneric<Type>>();
        // iterate through each student in student list
        for (CS2420StudentGeneric<Type> student : studentList)
            // add the student to the copy list
            studentNameListCopy.add(student);
        // sort the student list copy using a private comparator that uses student last name and first name to sort
        // in lexicographical order
        sort(studentNameListCopy, new OrderByName());
        // return the sorted list
        return studentNameListCopy;
    }
    /**
     * Returns the list of CS 2420 students in this class with a final
     score of at least cutoffScore,
     * sorted by final score in descending order.
     * Breaks ties in final scores using uNIDs (ascending order).
     *
     * @param cutoffScore - value that a student's final score must be
    at or above to be included
     * in the returned list
     */
    public ArrayList<CS2420StudentGeneric<Type>> getOrderedByScore(double cutoffScore) {
        // create a new list of students that will be ordered by final score
        ArrayList<CS2420StudentGeneric<Type>> studentScoreList = new ArrayList<CS2420StudentGeneric<Type>>();
        // iterate through each student in student list
        // create a final score variable that will be used to save student info in loop
        double finalScore;
        for (CS2420StudentGeneric<Type> student : studentList) {
            // assign the final score of the current student
            finalScore = student.computeFinalScore();
            // check if that final score is above a specific threshold to be added into the list
            if (finalScore > cutoffScore)
                // add the student if their score is higher than the cutoff
                studentScoreList.add(student);
        }
        // sort the student score list using a private comparator that uses the final grade and uNID's to sort
        sort(studentScoreList, new OrderByScore());
        // return the student score list
        return studentScoreList;
    }
    /**
     * Performs a SELECTION SORT on the input ArrayList.
     * 1. Finds the smallest item in the list.
     * 2. Swaps the smallest item with the first item in the list.
     * 3. Reconsiders the list be the remaining unsorted portion (second
     item to Nth item) and repeats steps 1, 2, and 3.
     */
    private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
        for(int i = 0; i < list.size() - 1; i++) {
            int j;
            int minIndex;
            for(j = i + 1, minIndex = i; j < list.size(); j++)
                if(c.compare(list.get(j), list.get(minIndex)) < 0)
                    minIndex = j;
            ListType temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }

    /**
     * Comparator that defines an ordering among CS 2420 students using
     their final grade.
     * uNIDs are guaranteed to be unique, making a tie-breaker
     unnecessary if the final grade is the same.
     */
    protected class OrderByScore implements Comparator<CS2420StudentGeneric<Type>> {
        public int compare(CS2420StudentGeneric<Type> student1, CS2420StudentGeneric<Type> student2) {
            // get the final scores of both student objects
            double student1Score = student1.computeFinalScore();
            double student2Score = student2.computeFinalScore();
            // if the student1 score is less than student2's score
            if (student1Score < student2Score)
                return 1;
            // if both students have the same score
            else if (student1Score == student2Score) {
                // get both student's uNID's
                int student1UNID = student1.getUNID();
                int student2UNID = student2.getUNID();
                // if student1's uNID is less than student2's uNID
                if (student1UNID - student2UNID < 0)
                    return -1;
                // student1's uNID is more than student2's
                else
                    return 1;
            }
            // student1's score is more than student2's score
            else
                return -1;
        }
    }
    /**
     * Comparator that defines an ordering among CS 2420 students using
     their uNIDs.
     * uNIDs are guaranteed to be unique, making a tie-breaker
     unnecessary.
     */
    protected class OrderByUNID implements Comparator<CS2420StudentGeneric<Type>> {

        /**
         * Returns a negative value if lhs (left-hand side) is
         smaller than rhs (right-hand side).
         * Returns a positive value if lhs is larger than rhs.
         * Returns 0 if lhs and rhs are equal.
         */
        public int compare(CS2420StudentGeneric<Type> lhs, CS2420StudentGeneric<Type> rhs) {

            return lhs.getUNID() - rhs.getUNID();
        }
    }
    /**
     * Comparator that defines an ordering among CS 2420 students using
     their names.
     * Compares by last name, then first name (if last names are the
     same), then uNID
     * (if both names are the same). uNIDs are guaranteed to be unique.
     */
    protected class OrderByName implements Comparator<CS2420StudentGeneric<Type>> {

        /**
         * Returns a negative value if student1 (left-hand side) is
         smaller than student2 (right-hand side). This is done
         using compareTo in order to determine whether student1
         last name is lexicographically greater or less than
         student2.
         * Returns a positive value if student1 is greater than student2.
         * Returns a negative value if student1 is less than student2
         */
        public int compare(CS2420StudentGeneric<Type> student1, CS2420StudentGeneric<Type> student2) {
            // get the last name of both student object
            String lastNameStudent1 = student1.getLastName();
            String lastNameStudent2 = student2.getLastName();
            // if the last name of student1 is less than student2 (lexicographically)
            if (lastNameStudent1.compareTo(lastNameStudent2) < 0)
                return -1;
            // if the last name of student1 is the same as student2
            else if (lastNameStudent1.compareTo(lastNameStudent2) == 0) {
                // get the first name of student1 and student2
                String firstNameStudent1 = student1.getFirstName();
                String firstNameStudent2 = student2.getFirstName();
                // if the first name of student1 is less than student2 (lexicographically)
                if (firstNameStudent1.compareTo(firstNameStudent2) < 0)
                    return -1;
                // if the first name of student1 is the same as student2
                else if (firstNameStudent1.compareTo(firstNameStudent2) == 0) {
                    // get the uNID of student1 and student2
                    int student1UNID = student1.getUNID();
                    int student2UNID = student2.getUNID();
                    // if student1 uNID is less than student2 uNID
                    if (student1UNID - student2UNID < 0)
                        return -1;
                    // student1 uNID is more than student2 uNID
                    else
                        return 1;
                }
                // student1 first name is greater than student2 first name (lexicographically)
                else
                    return 1;
                }
            // student1 last name is greater than student2 last name (lexicographically)
            else
                return 1;
        }
    }
}
