package assign01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This tester class assesses the correctness of the Vector class.
 *
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a
 *                 correctly implemented equals method.  Be careful of false
 *                 positives (i.e., tests that pass because your equals method
 *                 incorrectly returns true).
 *
 * @author Aaron Wood and Reynaldo Villarreal Zambrano
 * @version 2023-08-23
 */
public class MathVectorTester {

	private MathVector rowVec, rowVecTranspose, colVecTranspose, unitVec, sumVec, colVec, zeroColVec, zeroRowVec, singleRowVec, singleColVec, fiveSumVec;

	@BeforeEach
	public void setUp() throws Exception {
		// Creates a row vector with three elements: 3.0, 1.0, 2.0
		rowVec = new MathVector(new double[][]{{3, 1, 2}});

		// Creates a column vector with three elements: 3.0, 1.0, 2.0
		rowVecTranspose = new MathVector(new double[][]{{3}, {1}, {2}});

		// Creates a row vector with three elements: 1.0, 1.0, 1.0
		unitVec = new MathVector(new double[][]{{1, 1, 1}});

		// Creates a row vector with three elements: 4.0, 2.0, 3.0
		sumVec = new MathVector(new double[][]{{4, 2, 3}});

		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
		colVec = new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}});

		// Creates a row vector with three elements: 3.0, 1.0, 2.0
		colVecTranspose = new MathVector(new double[][]{{-11, 2.5, 36, -3.14, 7.1}});
		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1\
		fiveSumVec = new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}});

		// Creates a column vector with five elements: 0.0, 0.0, 0.0, 0.0, 0.0
		zeroColVec = new MathVector(new double[][] {{0.0}, {0.0}, {0.0}, {0.0}, {0.0}});

		// Creates a row vector with five elements: 0.0, 0.0, 0.0, 0.0, 0.0
		zeroRowVec = new MathVector(new double[][] {{0, 0, 0, 0, 0}});

		// Creates a row vector with only a single element: 1.0
		singleRowVec = new MathVector(new double[][]{{1.0}});

		// Creates a column vector with only a single element: 1.0
		singleColVec = new MathVector(new double[][]{{1.0}});
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void smallRowVectorEquality() {
		assertTrue(rowVec.equals(new MathVector(new double[][]{{3, 1, 2}})));
	}

	@Test
	public void smallRowVectorInequality() {
		assertFalse(rowVec.equals(unitVec));
	}

	@Test
	public void createVectorFromBadArray() {
		double arr[][] = {{1, 2}, {3, 4}};
		assertThrows(IllegalArgumentException.class, () -> {
			new MathVector(arr);
		});
		// NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}

	@Test
	public void transposeSmallRowVector() {
		MathVector transposeResult = rowVec.transpose();
		assertTrue(transposeResult.equals(rowVecTranspose));
	}

	@Test
	public void transposeSmallColVector() {
		MathVector transposeResult = colVec.transpose();
		assertTrue(transposeResult.equals(colVecTranspose));
	}

	@Test
	public void transposeSingleRowVector() {
		MathVector transposeResult = singleRowVec.transpose();
		assertTrue(transposeResult.equals(singleColVec));
	}

	@Test
	public void transposeSingleColVector() {
		MathVector transposeResult = singleColVec.transpose();
		assertTrue(transposeResult.equals(singleRowVec));
	}
	@Test
	public void addRowAndColVectors() {
		assertThrows(IllegalArgumentException.class, () -> {
			rowVec.add(colVec);
		});
		// NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}

	@Test
	public void addSmallRowVectors() {
		MathVector addResult = rowVec.add(unitVec);
		assertTrue(addResult.equals(sumVec));
	}

	@Test
	public void addSmallColVectors() {
		MathVector addResult = colVec.add(zeroColVec);
		assertTrue(addResult.equals(fiveSumVec));
	}

	@Test
	public void dotProductSmallRowVectors() {
		double dotProdResult = rowVec.dotProduct(unitVec);
		assertEquals(dotProdResult, 3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0);
	}
	@Test
	public void dotProductZeroAndNumberVector() {
		double resultVal = zeroColVec.dotProduct(colVec);
		assertEquals(0, resultVal);
	}

	@Test
	public void dotProductZeroAndZeroVector(){
		double resultVal = zeroColVec.dotProduct(zeroColVec);
		assertEquals(0, resultVal);
	}

	@Test
	public void smallRowVectorLength() {
		double vecLength = rowVec.magnitude();
		assertEquals(vecLength, Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0));
	}
	@Test
	public void magnitudeRowVector(){
		double resultVal = rowVec.magnitude();
		assertEquals(Math.sqrt(14), resultVal);
	}

	@Test
	public void magnitudeColVector() {
		MathVector newVec = rowVec.transpose();
		double resultVal = newVec.magnitude();
		assertEquals(Math.sqrt(14), resultVal);
	}
	@Test
	public void magnitudeZeroVector(){
		double resultVal = zeroRowVec.magnitude();
		assertEquals(Math.sqrt(0), resultVal);
	}

	@Test
	public void smallRowVectorNormalize() {
		MathVector normalVec = rowVec.normalize();
		double length = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
		assertTrue(normalVec.equals(new MathVector(new double[][]{{3.0 / length, 1.0 / length, 2.0 / length}})));
	}

	@Test
	public void smallColVectorNormalize(){
		MathVector normalVec = colVec.normalize();
		double length = colVec.magnitude();
		assertTrue(normalVec.equals(new MathVector(new double[][]{{-11 / length}, {2.5 / length}, {36 / length}, {-3.14 / length}, {7.1 / length}})));
	}

	@Test
	public void singleRowVectorNormalize() {
		MathVector normalVec = singleRowVec.normalize();
		double length = singleRowVec.magnitude();
		assertTrue(normalVec.equals(new MathVector(new double[][]{{1 / length}})));
	}

	@Test
	public void singleColVecNormalize() {
		MathVector singleColVec = singleRowVec.transpose();
		MathVector normalVec = singleColVec.normalize();
		double length = singleColVec.magnitude();
		assertTrue(normalVec.equals(new MathVector(new double [][]{{1 / length}})));
	}

	@Test
	public void zeroVecNormalize() {
		assertThrows(IllegalArgumentException.class, () -> {
			zeroColVec.normalize();
		});
	}

	@Test
	public void smallColVectorToString() {
		String resultStr = "-11.0\n2.5\n36.0\n-3.14\n7.1";
		assertEquals(resultStr, colVec.toString());
	}

	@Test
	public void smallZeroColVectorToString() {
		String resultStr = "0.0\n0.0\n0.0\n0.0\n0.0";
		assertEquals(zeroColVec.toString(), resultStr);
	}

	@Test
	public void smallZeroRowVectorToString() {
		String resultStr = "0.0 0.0 0.0 0.0 0.0";
		assertEquals(zeroRowVec.toString(), resultStr);
	}

	@Test
	public void singleValueRowVectorToString(){
		String resultStr = "1.0";
		assertEquals(singleRowVec.toString(), resultStr);
	}

	@Test
	public void singleValueColVectorToString(){
		MathVector singleColVec = singleRowVec.transpose();
		String resultStr = "1.0";
		assertEquals(singleColVec.toString(), resultStr);
	}

	@Test
	public void vectorWithZeroElements() {
		MathVector zeroVec = new MathVector(new double[][]{{0, 0, 0}});
		assertTrue(zeroVec.equals(new MathVector(new double[][]{{0, 0, 0}})));
	}

	@Test
	public void emptyVectorEquality() {
		assertThrows(IllegalArgumentException.class, () -> {
			new MathVector(new double[][]{{}});
		});
	}

}
/*

	@Test
	public void dotProductEmptyVectors() {
	}
	@Test
	public void normalizeEmptyVector() {
	}

}

 */
