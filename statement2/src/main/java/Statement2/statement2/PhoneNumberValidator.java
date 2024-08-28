package Statement2.statement2;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PhoneNumberValidator {

	public static boolean isValidIndianNumber(String phoneNumber) {
		// Remove leading zeros and country code
		phoneNumber = phoneNumber.replaceAll("^0+", "");
		phoneNumber = phoneNumber.replaceAll("^\\+?91?", "");

		// Check if the remaining string is exactly 10 digits
		if (phoneNumber.length() != 10 || !phoneNumber.matches("\\d+")) {
			return false;
		}

		// Check if the first digit is in the allowed set (6, 7, 8, or 9)
		int firstDigit = Integer.parseInt(phoneNumber.substring(0, 1));
		return firstDigit >= 6 && firstDigit <= 9;
	}

	public static void main(String[] args) {
		String[] testCases = { "+919876543210", "09876543210", "9876543210", "1234567890", "+91 9876543210",
				"91 9876543210", "09876543210", "1234567890" };

		int validCount = 0;
		for (String phoneNumber : testCases) {
			boolean isValid = isValidIndianNumber(phoneNumber);
			System.out.println("Phone Number: " + phoneNumber + ", Valid: " + isValid);
			if (isValid) {
				validCount++;
			}
		}

		System.out.println("\nTotal valid numbers: " + validCount);
	}
}
