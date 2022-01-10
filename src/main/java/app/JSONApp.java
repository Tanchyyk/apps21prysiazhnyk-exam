package app;

import domain.*;
import json.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JSONApp {
    public static void main(String[] args) {
        Json jYear = new JsonNumber(2);
        print(jYear); // 2

        Json jMarks = new JsonArray(new JsonNumber(3), new JsonNumber(4));
        print(jMarks); // [3, 4]

        JsonPair name = new JsonPair("name", new JsonString("Andrii"));
        JsonPair surname = new JsonPair("surname", new JsonString("Rodionov"));
        JsonPair marks = new JsonPair("marks", jMarks);
        JsonPair year = new JsonPair("year", jYear);
        JsonObject jsonObj = new JsonObject(name, surname, year, marks);
        print(jsonObj); // {'name': 'Andrii', 'surname': 'Rodionov', 'year': 2, 'marks': [3, 4]}

        print(jsonObj.projection("surname", "age", "year", "marks")); // {'surname': 'Rodionov', 'year': 2, 'marks': [3, 4]}

        BasicStudent basicStudent = new BasicStudent("Andrii", "Rodionov", 2);
        print(basicStudent.toJsonObject()); // {'name': 'Andrii', 'surname': 'Rodionov', 'year': 2}

    }

    private static void print(Json json) {
        System.out.println(json.toJson());
    }

    public static JsonObject sessionResult() {
        JsonObject jsonObject = null;

        JsonPair name = new JsonPair("name", new JsonString("Andrii"));
        JsonPair surname = new JsonPair("surname", new JsonString("Rodionov"));
        JsonPair year = new JsonPair("year", new JsonNumber(2));

        JsonPair oopCourse = new JsonPair("course", new JsonString("OOP"));
        JsonPair oopMark = new JsonPair("mark", new JsonNumber(3));
        JsonPair passedOop = new JsonPair("passed", new JsonBoolean(true));
        JsonObject oop = new JsonObject(oopCourse, oopMark, passedOop);

        JsonPair englishCourse = new JsonPair("course", new JsonString("English"));
        JsonPair markEnglish = new JsonPair("mark", new JsonNumber(5));
        JsonPair passedEnglish = new JsonPair("passed", new JsonBoolean(true));
        JsonObject english = new JsonObject(englishCourse, markEnglish, passedEnglish);

        JsonPair mathCourse = new JsonPair("course", new JsonString("Math"));
        JsonPair mathMark = new JsonPair("mark", new JsonNumber(2));
        JsonPair passedMath = new JsonPair("passed", new JsonBoolean(false));
        JsonObject math = new JsonObject(mathCourse, mathMark, passedMath);

        JsonArray exams = new JsonArray(oop, english, math);
        JsonPair resultExams = new JsonPair("exams", exams);

        jsonObject = new JsonObject(name, surname, year, resultExams);

        return jsonObject;
    }
}
