package domain;

import json.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    protected Tuple<String, Integer>[] exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = exams;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject[] examDetails = new JsonObject[exams.length];

        int counter = 0;
        for (Tuple<String, Integer> exam : exams) {
            JsonPair course = new JsonPair("course", new JsonString(exam.key));
            JsonPair mark = new JsonPair("mark", new JsonNumber(exam.value));
            boolean ifPassed = exam.value > 2;
            JsonPair passed = new JsonPair("passed", new JsonBoolean(ifPassed));
            JsonObject detail = new JsonObject(course, mark, passed);
            examDetails[counter] = detail;
            counter++;
        }
        JsonPair name = new JsonPair("name", new JsonString(this.name));
        JsonPair surname = new JsonPair("surname", new JsonString(this.surname));
        JsonPair year = new JsonPair("year", new JsonNumber(this.year));
        JsonPair exam = new JsonPair("exams", new JsonArray(examDetails));

        return new JsonObject(name, surname, year, exam);
    }
}