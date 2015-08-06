package com.example.xiaolong.crasyandroid.util;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiaolong on 15-8-6.
 */
public class FileDisplayUtils {

    public static final String SUPER = "super";
    public static final String THIS = "this";

    public static String[] KEY = {
        "abstract",
                "default",
                "null",
                "synchronized",
                "boolean",
                "do",
                "if",
                "package",
                "this",
                "break",
                "double",
                "implements",
                "private",
                "threadsafe",
                "byte",
                "else",
                "import",
                "protected",
                "throw",
                "extends",
                "instanceof",
                "public",
                "transient",
                "case",
                "false",
                "int",
                "return",
                "true",
                "catch",
                "final",
                "interface",
                "short",
                "try",
                "char",
                "finally",
                "long",
                "static",
                "void",
                "class",
                "float",
                "native",
                "super",
                "while",
                "for",
                "new",
                "switch",
                "continue"
    };

    public static List<String> TAG = Arrays.asList(KEY);

    public static SpannableStringBuilder getSpannableString(String content) {
        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        String cutContent = content.replaceAll("\\)", " ").replaceAll("\\(", " ").replaceAll("\n", " ").replaceAll("<", " ").replaceAll(">", " ");
        String[] contentArray = cutContent.split("\\s+");

        for (String keyString : contentArray) {
            if (TAG.contains(keyString) || keyString.startsWith(SUPER) || keyString.startsWith(THIS)) {
                if (keyString.startsWith(SUPER)) keyString = SUPER;
                if (keyString.startsWith(THIS)) keyString = THIS;
                List<Integer> mPosition = findAllTAGStringLocation(keyString, cutContent);

                int begin = 0;
                if (mPosition.size() > 0) {
                    for (int i = 0; i < mPosition.size(); i++) {
                        begin = mPosition.get(i);
                        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
                        builder.setSpan(redSpan, begin, begin + keyString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    }
                }
            }

            if (keyString.charAt(0) >= 'A' && keyString.charAt(0) <= 'Z') {
                List<Integer> mPosition = findAllStringLocation(keyString, cutContent);

                int begin = 0;
                if (mPosition.size() > 0) {
                    for (int i = 0; i < mPosition.size(); i++) {
                        begin = mPosition.get(i);
                        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.GREEN);
                        builder.setSpan(redSpan, begin, begin + keyString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    }
                }
            }
        }
        return builder;
    }

    public static List<Integer> findAllTAGStringLocation(String search, String target) {
        int foundPosition;
        int startPosition = 0;
        List<Integer> foundItems = new LinkedList();

        if (search.equals(SUPER) || search.equals(THIS)) {
            foundPosition = target.indexOf(" " + search + ".");
            while (foundPosition > -1 && startPosition < target.length()) {
                foundItems.add(foundPosition + 1);
                startPosition = foundPosition + 1;
                foundPosition = target.indexOf(" " + search + ".", startPosition + search.length() + 2);
            }

            foundPosition = target.indexOf(" " + search + " ");
            while (foundPosition > -1 && startPosition < target.length()) {
                foundItems.add(foundPosition + 1);
                startPosition = foundPosition + 1;
                foundPosition = target.indexOf(" " + search + " ", startPosition + search.length() + 2);
            }

            return foundItems;
        }

        if (search.contains(".")) return foundItems;

        if (target.indexOf(search) != 0) {
            foundPosition = target.indexOf(" " + search + " ");
        } else {
            foundPosition = 0;
        }

        while (foundPosition > -1 && startPosition < target.length()) {
            if (foundPosition == 0) {
                foundItems.add(foundPosition);
            } else {
                foundItems.add(foundPosition + 1);
            }
            startPosition = foundPosition + 1;
            foundPosition = target.indexOf(" " + search + " ", startPosition + search.length());
        }
        return foundItems;
    }

    public static List<Integer> findAllStringLocation(String search, String target) {
        int foundPosition;
        int startPosition = 0;
        List<Integer> foundItems = new LinkedList();

        if (search.contains(".")) return foundItems;

        if (target.indexOf(search) != 0) {
            foundPosition = target.indexOf(" " + search + " ");
        } else {
            foundPosition = 0;
        }

        while (foundPosition > -1 && startPosition < target.length()) {
            if (foundPosition == 0) {
                foundItems.add(foundPosition);
            } else {
                foundItems.add(foundPosition + 1);
            }
            startPosition = foundPosition + 1;
            foundPosition = target.indexOf(" " + search + " ", startPosition + search.length());
        }
        return foundItems;
    }
}
