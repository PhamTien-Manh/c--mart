package edu.cmart.util.method;

import java.text.Normalizer;

public class Convert {

    public static String convertToNonDiacritic(String text) {
        String normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalizedText
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("Đ", "D")
                .replaceAll("đ", "d");
    }
}
