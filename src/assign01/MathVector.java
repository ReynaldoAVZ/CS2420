package assign01;

/**
 * This class represents a simple row or column vector of numbers.
 * In a row vector, the numbers are written horizontally (eg, `{{1, 2, 3, 4}}`).
 * In a column vector, the numbers are written vertically (eg, `{{1}, {2}, {3}, {4}}`).
 *
 * @author Aaron Wood and Reynaldo Villarreal Zambrano
 * @version 2023-08-28
 */
public class MathVector {

	// 2D array to hold the numbers of the vector, either along the columns or rows
	private double[][] data;
	// set to true for a row vector and false for a column vector
	private boolean isRowVector;
	// count of elements in the vector
	private int vectorSize;

	/**
	 * Creates a new row or column vector.
	 * For a row vector, the input array is expected to have 1 row and a positive number of columns,
	 * and this number of columns represents the vector's length.
	 * For a column vector, the input array is expected to have 1 column and a positive number of rows,
	 * and this number of rows represents the vector's length.
	 *
	 * @param data - a 2D array to hold the numbers of the vector
	 * @throws IllegalArgumentException if the numbers of rows and columns in the input 2D array is not
	 *                                  compatible with a row or column vector
	 */
	public MathVector(double[][] data) {
		if (data.length == 0)
			throw new IllegalArgumentException("Number of rows must be positive.");
		if (data[0].length == 0)
			throw new IllegalArgumentException("Number of columns must be positive.");

		if (data.length == 1) {
			// This is a row vector with length = number of columns.
			this.isRowVector = true;
			this.vectorSize = data[0].length;
		} else if (data[0].length == 1) {
			// This is a column vector with length = number of rows.
			this.isRowVector = false;
			this.vectorSize = data.length;
		} else
			throw new IllegalArgumentException("Either the number of rows or the number of columns must be 1.");

		// Create the array and copy data over.
		if (this.isRowVector)
			this.data = new double[1][vectorSize];
		else
			this.data = new double[vectorSize][1];
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < this.data[0].length; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}

	/**
	 * Determines whether this vector is "equal to" another vector, where equality is
	 * defined as both vectors being row (or both being column), having the same
	 * vector length, and containing the same numbers in the same positions.
	 *
	 * @param other - another vector to compare
	 */
	public boolean equals(Object other) {
		// can't compare two objects that aren't both instances of MathVector objects
		if (!(other instanceof MathVector)) {
			return false;
		}
		// cast the type MathVector on to other object
		MathVector otherVec = (MathVector) other;
		// check if both vectors are either rows or columns, but both must be the same
		if (otherVec.isRowVector != this.isRowVector) {
			return false;
		}
		// if both vectors are rows
		else if (this.isRowVector) {
			// declare holder variables
			double vectorVal1;
			double vectorVal2;
			// iterate through all values in vector
			for (int i = 0; i < this.vectorSize; i++) {
				// index out values from both vectors to do comparison
				vectorVal1 = this.data[0][i];
				vectorVal2 = otherVec.data[0][i];
				// compare both values and return false if they're not the same
				if (vectorVal1 != vectorVal2) {
					return false;
				}
			}
		}
		// if both vectors are columns
		else {
			// declare holder variables
			double vectorVal1;
			double vectorVal2;
			// iterate through all the values in vector
			for (int i = 0; i < this.vectorSize; i++) {
				// index out values from both vectors to do comparison
				vectorVal1 = this.data[i][0];
				vectorVal2 = otherVec.data[i][0];
				// compare both values and return false if they're not the same
				if (vectorVal1 != vectorVal2) {
					return false;
				}
			}
		}
		// if all values are the same, and are same type (row or col), return true
		return true;
	}

	/**
	 * Generates a returns a new vector that is the transposed version of this vector.
	 */
	public MathVector transpose() {
		// if the given vector is a column vector
		if (!this.isRowVector) {
			// declare a new row vector
			MathVector newVec = new MathVector(new double[1][this.vectorSize]);
			// for loop to go through all the values of this.data
			for (int i = 0; i < this.vectorSize; i++) {
				// index out value in original vector and place into new transposed vector
				newVec.data[0][i] = this.data[i][0];
			}
			// return the newVec back to function call
			return newVec;
		}
		// if the given vector is a row vector
		else {
			// declare a new column vector
			MathVector tempVec = new MathVector(new double[this.vectorSize][1]);
			// for loop to go through all the values of this.data
			for (int i = 0; i < this.vectorSize; i++) {
				// index out value in original vector and place into new transposed vector
				tempVec.data[i][0] = this.data[0][i];
			}
			// return the newVec back to function call
			return tempVec;
		}
	}

	/**
	 * Generates and returns a new vector representing the sum of this vector and another vector.
	 *
	 * @param other - another vector to be added to this vector
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *                                  the same length or column vectors of the same length
	 */
	public MathVector add(MathVector other) {
		// check and make sure that both this vector and other vector are either row or column vector
		if (this.isRowVector != other.isRowVector) {
			throw new IllegalArgumentException("Vectors are not both columns or rows.");
		}
		// check and make sure that both vectors are the same size
		if (this.vectorSize != other.vectorSize) {
			throw new IllegalArgumentException("Vector sizes are not the same.");
		}
		// declare new vector that will be returned
		MathVector sumVec;
		// if this vector is a row vector
		if (this.isRowVector) {
			// specify the dimensions of sumVec and create it into a row vector
			sumVec = new MathVector(new double[1][this.vectorSize]);
			// for loop to get all the values in this and other vector
			for (int i = 0; i < this.vectorSize; i++) {
				sumVec.data[0][i] = this.data[0][i] + other.data[0][i];
			}
		}
		// if this vector is a column vector
		else {
			// specify the dimensions of sumVec and create it into a column vector
			sumVec = new MathVector(new double[this.vectorSize][1]);
			// for loop to get all the values in this and other vector
			for (int i = 0; i < this.vectorSize; i++) {
				sumVec.data[i][0] = this.data[i][0] + other.data[i][0];
			}
		}
		// return sumVec to function call
		return sumVec;
	}

	/**
	 * Computes and returns the dot product of this vector and another vector.
	 *
	 * @param other - another vector to be combined with this vector to produce the dot product
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *                                  the same length or column vectors of the same length
	 */
	public double dotProduct(MathVector other) {
		// check and make sure that both this vector and other vector are either row or column vector
		if (this.isRowVector != other.isRowVector) {
			throw new IllegalArgumentException("Vectors are not both columns or rows.");
		}
		// check and make sure that both vectors are the same size
		if (this.vectorSize != other.vectorSize) {
			throw new IllegalArgumentException("Vector sizes are not the same.");
		}
		// declare temporary vector
		MathVector dotVec;
		// declare double holder variable
		double dotProductVal = 0;
		// if the array is a row vector
		if (this.isRowVector) {
			// assign corresponding dimensions for temporary vector
			dotVec = new MathVector(new double[1][this.vectorSize]);
			// iterate through all the values of this and other vector and multiply each corresponding term together
			for (int i = 0; i < this.vectorSize; i++) {
				dotVec.data[0][i] = this.data[0][i] * other.data[0][i];
			}
			// add each corresponding value from dotVec into a total sum
			for (int i = 0; i < dotVec.vectorSize; i++) {
				dotProductVal = dotProductVal + dotVec.data[0][i];
			}
		} else {
			// assign corresponding dimensions for temporary vector
			dotVec = new MathVector(new double[this.vectorSize][1]);
			// iterate through all the values of this and other vector and multiply each corresponding term together
			for (int i = 0; i < this.vectorSize; i++) {
				dotVec.data[i][0] = this.data[i][0] * other.data[i][0];
			}
			// add each corresponding value from dotVec into a total sum
			for (int i = 0; i < dotVec.vectorSize; i++) {
				dotProductVal = dotProductVal + dotVec.data[i][0];
			}
		}
		// return the result of dot product from two vectors
		return dotProductVal;
	}

	/**
	 * Computes and returns this vector's magnitude (also known as a vector's length) .
	 */
	public double magnitude() {
		// double holder variable that will be used to sum up everything
		double magnitudeVal = 0;
		// double holder variable that will be used to sum up everything
		double squaredVal;
		// if vector is a column vector
		if (!this.isRowVector) {
			// iterate through all the values and square the term and add to total sum
			for (int i = 0; i < this.vectorSize; i++) {
				squaredVal = this.data[i][0] * this.data[i][0];
				magnitudeVal = magnitudeVal + squaredVal;
			}
		}
		// if vector is a row vector
		else {
			// iterate through all the values and square the term and add to total sum
			for (int i = 0; i < this.vectorSize; i++) {
				squaredVal = this.data[0][i] * this.data[0][i];
				magnitudeVal = magnitudeVal + squaredVal;
			}
		}
		// return the magnitude of the total sum of the terms squared = magnitude
		return Math.sqrt(magnitudeVal);
	}

	/**
	 * Generates and returns a normalized version of this vector.
	 */
	public MathVector normalize() {
		// get the magnitude of whatever vector was passed
		double length = this.magnitude();
		// declare a vector that will be returned
		MathVector newVec;
		// if this vector is a column vector
		if (!this.isRowVector) {
			// assign the correct dimensions of the newVec
			newVec = new MathVector(new double[this.vectorSize][1]);
			// iterate through all the values to perform correct math
			for (int i = 0; i < this.vectorSize; i++) {
				// check for division of 0 by 0
				if (this.data[i][0] == 0 && length == 0) {
					throw new IllegalArgumentException("Cannot do division of 0 / 0. Results in NaN");
				}
				// if it's not 0 divided by 0, you're good to go
				else {
					newVec.data[i][0] = this.data[i][0] / length;
				}
			}
		}
		// if this vector is a row vector
		else {
			// assign the correct dimensions of the newVec
			newVec = new MathVector(new double[1][this.vectorSize]);
			// iterate through all the values to perform correct math
			for (int i = 0; i < this.vectorSize; i++) {
				// check for division of 0 by 0
				if (this.data[0][i] == 0 && length == 0) {
					throw new IllegalArgumentException("Cannot do division of 0 / 0. Results in NaN");
				}
				// if it's not 0 divided by 0, you're good to go again
				else {
					newVec.data[0][i] = this.data[0][i] / length;
				}
			}
		}
		// return the normalized vector (unit vector)
		return newVec;
	}

	/**
	 * Generates and returns a textual representation of this vector.
	 * For example, "1.0 2.0 3.0 4.0 5.0" for a sample row vector of length 5 and
	 * "1.0
	 * 2.0
	 * 3.0
	 * 4.0
	 * 5.0" for a sample column vector of length 5.
	 * In both cases, notice the lack of a newline or space after the last number.
	 */
	public String toString() {
		// declare an empty string that will be changed
		String string = "";
		// if this is a row vector
		if (this.isRowVector) {
			// iterate through all the values in this vector
			for (int i = 0; i < this.vectorSize; i++) {
				// if the loop is on the very last value of the vector
				if (i == this.vectorSize - 1) {
					string = string + this.data[0][i];
				}
				// if the loop is on any value before the last value of the vector
				else {
					string = string + this.data[0][i] + " ";
				}
			}
        }
		// if this is a column vector
		else {
			// iterate through all the values in this vector
			for (int i = 0; i < this.vectorSize; i++) {
				// if the loop is on the very last value of the vector
				if (i == this.vectorSize - 1) {
					string = string + this.data[i][0];
				}
				// if the loop in on any value before the last value of the vector
				else {
					string = string + this.data[i][0] + "\n";
				}
			}
        }
		// return the final string once all loops are done
        return string;
    }
}