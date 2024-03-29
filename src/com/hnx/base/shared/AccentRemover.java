package com.hnx.base.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccentRemover {
	private static char[] SPECIAL_CHARACTERS = { 
		'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò',
		'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê',
		'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă', 'Đ', 'đ',
		'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ',
		'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ', 'Ắ', 'ắ', 'Ằ', 'ằ',
		'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế',
		'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị',
		'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ', 'ổ', 'Ỗ', 'ỗ', 'Ộ',
		'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ',
		'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự', 'Ỹ', 'Ỳ', 'ỹ', 'ỳ', 'ỷ'};

private static char[] REPLACEMENTS = { 
		'A', 'A', 'A', 'A', 'E', 'E', 'E',
		'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a',
		'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A',
		'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a',
		'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
		'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e',
		'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I',
		'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
		'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
		'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
		'U', 'u', 'Y', 'Y', 'y', 'y', 'y'};

public static String toUrlFriendly(String s) {
	int maxLength = Math.min(s.length(), 236);
	char[] buffer = new char[maxLength];
	int n = 0;
	for (int i = 0; i < maxLength; i++) {
		char ch = s.charAt(i);
		buffer[n] = removeAccent(ch);
		// skip not printable characters
		if (buffer[n] > 31) {
			n++;
		}
	}
	// skip trailing slashes
	while (n > 0 && buffer[n - 1] == '/') {
		n--;
	}
	return String.valueOf(buffer, 0, n);
}

public static char removeAccent(char ch) {
	int index = Arrays.binarySearch(SPECIAL_CHARACTERS, ch);
	if (index >= 0) {
		ch = REPLACEMENTS[index];
	}
	return ch;
}

public static String removeAccent(String s) {
	if (s == null || s.isEmpty())
		return "";
	StringBuilder sb = new StringBuilder(s);
	for (int i = 0; i < sb.length(); i++) {
		sb.setCharAt(i, removeAccent(sb.charAt(i)));
	}
	return sb.toString();
}

public static List<String> removeAccent(List<String> s) {
	List<String> results = new ArrayList<String>();
	for (String rs : s) {
		String result = removeAccent(rs);
		if (rs != null && !rs.isEmpty()) {
			results.add(result);
		}
	}
	return results;
}

public static String formatStringForLink(String title) {
	return toUrlFriendly(title.toLowerCase()).replaceAll("[^a-zA-Z0-9_-]", "-").replaceAll("--", "-");
}

public static String formatStringKeyWordNews(String title) {
	return toUrlFriendly(title.toLowerCase()).replaceAll(";", ",");
}
}
