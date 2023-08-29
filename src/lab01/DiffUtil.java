package lab01;

/**
 * This class contains a utility method for finding the smallest difference.
 *
 * @author Aaron Wood and ??
 * @version 2023-08-23
 */
public class DiffUtil {

	/**
	 * Computes and returns the smallest difference (absolute value of subtraction)
	 * among every pair of integers in the input array.
	 *
	 * @param arr - input array of integers
	 * @throws IllegalArgumentException - if the array contains less than two items
	 */
	public static int findSmallestDiff(int[] arr) {
		if (arr.length < 2)
			throw new IllegalArgumentException("Array must be > 1 element");

		int diff = Math.abs(arr[0] - arr[1]);

		for (int i = 0; i < arr.length; i++)
			for (int j = i + 1; j < arr.length; j++) {
				int tmp_diff = Math.abs(arr[i] - arr[j]);

				if (tmp_diff < diff)
					diff = tmp_diff;
			}

		return diff;
	}
}
