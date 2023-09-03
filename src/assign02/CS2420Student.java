package assign02;

/**
 * This class represents a CS2420 student, which extends UofUStudent class.
 * This class contains additional private instance variables for the students
 * contact information, scores on assignments, exams, labs, and quizzes.
 *
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-09-04
 */
public class CS2420Student extends UofUStudent {

    private EmailAddress contactInfo;
    private double assignmentScore;
    private double assignmentNumber;
    private double examScore;
    private double examNumber;
    private double labScore;
    private double labNumber;
    private double quizScore;
    private double quizNumber;
    private double examWeight = .45;
    private double assignmentWeight = .35;
    private double quizWeight = .10;
    private double labWeight = .10;

    /**
     * Creates a student from the given first name, last name, and uNID.
     *
     * @param firstName
     * @param lastName
     * @param uNID
     */
    public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo) {
        super(firstName, lastName, uNID);
        this.contactInfo = contactInfo;
    }

    /**
     * Finds the students contact info and returns it.
     *
     * @return contactInfo
     */
    public EmailAddress getContactInfo() {
        return this.contactInfo;
    }

    /**
     * Adds a score to a students grade in a specific category.
     *
     * @param score - a value between 0-100 to represent the grade they got on a specific category item
     * @param category - either assignments, exam, lab, or quiz
     */
    public void addScore(double score, String category) {
        // perform a certain command depending on what the category is.
        // in that category, add the score to that specific field, and
        // increase the number of items added to that field by one
        switch (category) {
            case "assignment":
                this.assignmentScore = this.assignmentScore + score;
                this.assignmentNumber = this.assignmentNumber + 1;
                break;
            case "exam":
                this.examScore = this.examScore + score;
                this.examNumber = this.examNumber + 1;
                break;
            case "lab":
                this.labScore = this.labScore + score;
                this.labNumber = this.labNumber + 1;
                break;
            case "quiz":
                this.quizScore = this.quizScore + score;
                this.quizNumber = this.quizNumber + 1;
                break;
            // if String "category" was none of the above, do nothing
            default:
                break;
        }
    }

    /**
     * Computes the final grade of a student using the grade that a student has gotten
     * in the categories of "assignments", "exams", "quiz", and "lab" and uses the CS2420
     * syllabus grade weights in order to calculate the final grade of the student.
     *
     * @return final Score - double value that represents the final grade of the student
     */
    public double computeFinalScore() {
        // check if exam score average is below 65%
        if ((this.examScore / this.examNumber) < 65) {
            return this.examScore / this.examNumber;
        }
        // check that each category has a score
        if (this.assignmentScore == 0 || this.examScore == 0 || this.labScore == 0 || this.quizScore == 0) {
            return 0.0;
        }
        // take all individual scores and multiply by their grade weight
        double finalAssignmentScore = (this.assignmentScore / this.assignmentNumber) * this.assignmentWeight;
        double finalExamScore = (this.examScore / this.examNumber) * this.examWeight;
        double finalLabScore = (this.labScore / this.labNumber) * this.labWeight;
        double finalQuizScore = (this.quizScore / this.quizNumber) * this.quizWeight;
        // return final grade = sum of all scores
        return finalAssignmentScore + finalExamScore + finalLabScore + finalQuizScore;
    }

    /**
     * Assigns a string representation of the final grade of a student based off the method
     * computeFinalScore() in order to get a (double) finalScore value, and then using the cutoff
     * percentages from the CS2420 syllabus, assigns a corresponding letter grade.
     *
     * @return finalGrade - string representation of the student's final grade based off (double) finalScore.
     */
    public String computeFinalGrade() {
        // declare empty string variable to hold final grade
        String finalGrade = "";
        // find the numerical representation of the students final grade
        double finalScore = computeFinalScore();
        // using the percentage cutoff's from the CS2420 syllabus, assign a letter grade
        // to the student once their finalScore is in the right category. If they have a
        // zero score in any of the categories ("assignments", "exams", "quiz", "labs")
        // they automatically get a "N/A" final grade. Anything else, they will get assigned
        // a letter grade representation.
        if (finalScore == 0) {
            finalGrade = "N/A";
        } else if (finalScore <= 59.9) {
            finalGrade = "E";
        } else if (finalScore <= 62.9) {
            finalGrade = "D-";
        } else if (finalScore <= 66.9) {
            finalGrade = "D";
        } else if (finalScore <= 69.9) {
            finalGrade = "D+";
        } else if (finalScore <= 72.9) {
            finalGrade = "C-";
        } else if (finalScore <= 76.9) {
            finalGrade = "C";
        } else if (finalScore <= 79.9) {
            finalGrade = "C+";
        } else if (finalScore <= 82.9) {
            finalGrade = "B-";
        } else if (finalScore <= 86.9) {
            finalGrade = "B";
        } else if (finalScore <= 89.9) {
            finalGrade = "B+";
        } else if (finalScore <= 92.9) {
            finalGrade = "A-";
        } else if (finalScore <= 100.0) {
            finalGrade = "A";
        }
        // return the (String) finalGrade representation of their final grade in the class.
        return finalGrade;
    }
}