package assign02;

/**
 * This class represents a CS2420 student, which extends UofUStudent class.
 * This class contains additional private instance variables for the students
 * contact information, scores on assignments, exams, labs, and quizzes.
 *
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-09-01
 */public class CS2420Student extends UofUStudent {

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

    public EmailAddress getContactInfo() {
        return this.contactInfo;
    }

    public void addScore(double score, String category) {
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
        this.assignmentScore = (this.assignmentScore / this.assignmentNumber) * this.assignmentWeight;
        this.examScore = (this.examScore / this.examNumber) * this.examWeight;
        this.labScore = (this.labScore / this.labNumber) * this.labWeight;
        this.quizScore = (this.quizScore / this.quizNumber) * this.quizWeight;
        // return final grade = sum of all scores
        return this.assignmentScore + this.examScore + this.labScore + this.quizScore;
    }

    public String computeFinalGrade() {
        String finalGrade = "";
        double finalScore = computeFinalScore();
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
        return finalGrade;
    }
}