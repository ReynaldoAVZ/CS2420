package assign02;

/**
 * This class represents a generic CS2420 student, which extends UofUStudent class.
 * This class contains additional private instance variables for the students
 * contact information, scores on assignments, exams, labs, and quizzes.
 *
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-09-06
 */
public class CS2420StudentGeneric<Type> extends UofUStudent {

    private Type contactInfo;
    private double assignmentScore = 0;
    private double assignmentNumber = 0;
    private double examScore = 0;

    private double examNumber = 0;
    private double labScore = 0;
    private double labNumber = 0;
    private double quizScore = 0;

    private double quizNumber = 0;
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
    public CS2420StudentGeneric(String firstName, String lastName, int uNID, Type contactInfo) {
        super(firstName, lastName, uNID);
        this.contactInfo = contactInfo;
    }

    /**
     * Retrieves the contact information of the current student the method is called on.
     *
     * @return contactInfo - students (Type) contact information
     */
    public Type getContactInfo() {
        return this.contactInfo;
    }

    /**
     * Add a score value to a specific category for the current student this method is called on.
     *
     * @param score - (double) score value that is added on to a specific field in student
     * @param category - (String) category that holds either "assignment", "exam", "lab", or "quiz".
     */
    public void addScore(double score, String category) {
        // switch statement that adds a score value to a specific field in student object
        // that is dependent on the string in category. if it's none of the accepted categories,
        // do nothing
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
            default:
                break;
        }
    }

    /**
     * Computes the final score of the current student this method is called on. This is computed
     * using the fields in the student object that correspond with their grades in each category. If
     * their exam average is below 65%, automatically return that average as the students final grade.
     *
     * @return finalScore - the sum of the final score for each category in the student object with each field
     *                      being multiplied with their respective grade weight according to the CS2420 syllabus
     *                      grade policies.
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
        double assignmentScoreFinal = (this.assignmentScore / this.assignmentNumber) * this.assignmentWeight;
        double examScoreFinal = (this.examScore / this.examNumber) * this.examWeight;
        double labScoreFinal = (this.labScore / this.labNumber) * this.labWeight;
        double quizScoreFinal = (this.quizScore / this.quizNumber) * this.quizWeight;
        // return final grade = sum of all scores
        double finalScore = assignmentScoreFinal + examScoreFinal + labScoreFinal + quizScoreFinal;
        return finalScore;
    }

    /**
     * This method computes the final grade of the student object that this method is called on. It does it
     * by using computeFinalScore() to get a (double) finalScore value. This value is then used in a else-if structure
     * to determine in what grade category the student should be assigned.
     *
     * @return finalGrade - (String) value that represents the final grade of the student object this method is called on.
     */
    public String computeFinalGrade() {
        // Declare a string to contain the student finalGrade
        String finalGrade = "Did not assign a grade";
        // Attain the student final score using the computeFinalScore() method
        double finalScore = computeFinalScore();
        // compares finalScore with the CS2420 syllabus grade ranges, and assigns
        // a final letter grade dependent on where that finalScore value is in between.
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
        // return the (String) finalGrade value
        return finalGrade;
    }
}