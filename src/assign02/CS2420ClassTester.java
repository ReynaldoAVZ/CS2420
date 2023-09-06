package assign02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

/**
 * This class contains tests for CS2420Class.
 * 
 * @author Aaron Wood and Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-09-06
 */
public class CS2420ClassTester {

	private CS2420Class emptyClass, verySmallClass, smallClass, veryBigClass;
	
	@BeforeEach
	void setUp() throws Exception {
		emptyClass = new CS2420Class();
		
		verySmallClass = new CS2420Class();
		verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, new EmailAddress("hi", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Drew", "Hall", 2323232, new EmailAddress("howdy", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Riley", "Nguyen", 4545454, new EmailAddress("hello", "gmail.com")));

		smallClass = new CS2420Class();
		smallClass.addAll("src/assign02/a_small_2420_class.txt");
		
		// FILL IN -- Extend this tester to add more tests for the CS 2420 classes above, as well as to
		// create and test larger CS 2420 classes.
		// (HINT: For larger CS 2420 classes, generate random names, uNIDs, contact info, and scores in a 
		// loop, instead of typing one at a time.)
		//Random rng = new Random();
		//PrintWriter writer = new PrintWriter("very_big_class.txt", StandardCharsets.UTF_8);
		//String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		//String[] emailEnd = new String[]{"gmail.com", "outlook.com", "icloud.com", "ahmed.com", "yahoo.com"};
		veryBigClass = new CS2420Class();
		veryBigClass.addAll("src/assign02/very_big_class.txt");

		// code used to generate random students that will go in veryBigClass
//		for (int i = 0; i < 100; i++) {
//			int lastNameLength = rng.nextInt(3, 8);
//			int firstNameLength = rng.nextInt(3, 8);
//			int emailAddressLength = rng.nextInt(3, 8);
//			String lastName = "";
//			String firstName = "";
//			int randomUNID = rng.nextInt(1000000, 10000000);
//			String emailAddress = "";
//			String totalAssignmentScores = "";
//			String totalExamScores = "";
//			String totalLabScores = "";
//			String totalQuizScores = "";
//			int assignmentAmount = rng.nextInt(1, 11);
//			int labAmount = rng.nextInt(1, 11);
//			int quizAmount = rng.nextInt(1, 11);
//			for (int j = 0; j < lastNameLength; j++) {
//				lastName = lastName + characters.charAt(rng.nextInt(characters.length()));
//			}
//			for (int k = 0; k < firstNameLength; k++) {
//				firstName = firstName + characters.charAt(rng.nextInt(characters.length()));
//			}
//			for (int l = 0; l < emailAddressLength; l++) {
//				emailAddress = emailAddress + characters.charAt(rng.nextInt(characters.length()));
//			}
//			for (int m = 0; m < assignmentAmount; m++){
//				int assignmentScore = rng.nextInt(0, 101);
//				totalAssignmentScores = totalAssignmentScores + " " + assignmentScore;
//			}
//			for (int n = 0; n < 3; n++){
//				int examScore = rng.nextInt(0, 101);
//				totalExamScores = totalExamScores + " " + examScore;
//			}
//			for (int o = 0; o < labAmount; o++){
//				int labScore = rng.nextInt(0, 101);
//				totalLabScores = totalLabScores + " " + labScore;
//			}
//			for (int p = 0; p < quizAmount; p++){
//				int quizScore = rng.nextInt(0, 101);
//				totalQuizScores = totalQuizScores + " " + quizScore;
//			}
//			int randomIndex = rng.nextInt(0, 5);
//			String emailExtension = emailEnd[randomIndex];
//			// add student info
//			writer.println(firstName + " " + lastName + " (u" + randomUNID + ") " + emailAddress + "@" + emailExtension);
//			// add student assignment score
//			writer.println(totalAssignmentScores);
//			// add student exam score
//			writer.println(totalExamScores);
//			// add student lab score
//			writer.println(totalLabScores);
//			// add student quiz score
//			writer.println(totalQuizScores);
//		}
//		writer.close();

	}
	// Very big CS2420 class tests ------------------------------------------------------------------------
	@Test
	public void testVeryBigClassContactInfo() {
		ArrayList<EmailAddress> contactInfo = veryBigClass.getContactList();
		assertEquals(100, contactInfo.size());
	}

	@Test
	public void testAddStudentBigClass(){
		CS2420Student student = new CS2420Student("Mikhail", "Ahmed", 9111111, new EmailAddress("MichaelTheMouse", "ahmed.com"));
		boolean actual = veryBigClass.addStudent(student);
		assertTrue(actual);
	}

	@Test
	public void lookUpStudentUNIDBigClass(){
		CS2420Student actual = new CS2420Student("EMIRDK", "XPITAD", 8117540, new EmailAddress("QAAUAP", "ahmed.com"));
		CS2420Student student = veryBigClass.lookup(8117540);
		assertEquals(actual, student);
	}

	@Test
	public void lookUpStudentContactInfoBigClass() {
		UofUStudent expectedStudent = new UofUStudent("JWI", "PWOFO", 3760201);
		ArrayList<CS2420Student> actualStudents = veryBigClass.lookup(new EmailAddress("NLHS", "outlook.com"));
		assertEquals(1, actualStudents.size());
		assertEquals(expectedStudent, actualStudents.get(0));
	}

	@Test
	public void computeClassAverageBigClass(){
		assertEquals(47.98, veryBigClass.computeClassAverage(), 0.1);
	}
	@Test
	public void addScoreToStudentVeryBigClass() {
		CS2420Student student = veryBigClass.lookup(5922722);
		student.addScore(86.5, "assignment");
		student.addScore(55, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals(64.5, student.computeFinalScore(), 0.001);
	}
	// Empty CS 2420 class tests --------------------------------------------------------------------------
	@Test
	public void testEmptyLookupUNID() {
		assertNull(emptyClass.lookup(1234567));
	}
	
	@Test
	public void testEmptyLookupContactInfo() {
		ArrayList<CS2420Student> students = emptyClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(0, students.size());
	}
	
	@Test
	public void testEmptyAddScore() {
		// ensure no exceptions thrown
		emptyClass.addScore(1234567, 100, "assignment");
	}

	@Test
	public void testEmptyClassAverage() {
		assertEquals(0, emptyClass.computeClassAverage(), 0);
	}
	
	@Test
	public void testEmptyContactList() {
		ArrayList<EmailAddress> contactList = emptyClass.getContactList();
		assertEquals(0, contactList.size());
	}

	// Very small CS 2420 class tests --------------------------------------------------------------------

	@Test
	public void testVerySmallLookupUNID() {
		UofUStudent expected = new UofUStudent("Drew", "Hall", 2323232);
		CS2420Student actual = verySmallClass.lookup(2323232);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testVerySmallLookupContactInfo() {
		UofUStudent expectedStudent = new UofUStudent("Riley", "Nguyen", 4545454);
		ArrayList<CS2420Student> actualStudents = verySmallClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(1, actualStudents.size());
		assertEquals(expectedStudent, actualStudents.get(0));
	}
	
	@Test
	public void testVerySmallAddDuplicateStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, 
				new EmailAddress("hi", "gmail.com")));
		assertFalse(actual);
	}
	
	@Test
	public void testVerySmallAddNewStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010100, 
				new EmailAddress("hi", "gmail.com")));
		assertTrue(actual);		
	}

	@Test
	public void testVerySmallStudentFinalScore0() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}
	
	@Test
	public void testVerySmallStudentFinalGradeNA() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(100, "lab");
		assertEquals("N/A", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalScore() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(55, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals(55, student.computeFinalScore(), 0.001);
	}
	
	@Test
	public void testVerySmallStudentFinalGrade() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals("B", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentComputeScoreTwice() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		student.computeFinalScore();   
		student.addScore(70, "lab");
		student.addScore(54.5, "exam");				
		assertEquals(64.75, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testVerySmallUpdateName() {
		verySmallClass.lookup(1010101).updateName("John", "Doe");
		ArrayList<CS2420Student> students = verySmallClass.lookup(new EmailAddress("hi", "gmail.com"));
		assertEquals("John", students.get(0).getFirstName());
		assertEquals("Doe", students.get(0).getLastName());
	}

	// Small CS 2420 class tests -------------------------------------------------------------------------

	@Test
	public void testSmallLookupContactInfo() {
		UofUStudent expectedStudent1 = new UofUStudent("Kennedy", "Miller", 888888);
		UofUStudent expectedStudent2 = new UofUStudent("Taylor", "Miller", 999999);

		ArrayList<CS2420Student> actualStudents = smallClass.lookup(new EmailAddress("we_love_puppies", "geemail.com"));
		assertEquals(2, actualStudents.size());
		assertTrue(actualStudents.contains(expectedStudent1));
		assertTrue(actualStudents.contains(expectedStudent2));
	}
	
	@Test
	public void testSmallGetContactList() {
		ArrayList<EmailAddress> actual = smallClass.getContactList();
		assertEquals(9, actual.size());
	}
		
	@Test
	public void testSmallStudentFinalScore() {
		CS2420Student student = smallClass.lookup(333333);
		assertEquals(95.5345, student.computeFinalScore(), 0.1);
	}
		
	@Test
	public void testSmallComputeClassAverage() {
		assertEquals(78.356, smallClass.computeClassAverage(), 0.1);
	}
}

	// Big