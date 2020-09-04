package com.leandromaro.challenge.rest.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.sun.media.sound.InvalidDataException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class CustomerDateAndTimeDeserialize extends JsonDeserializer<Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    @Override
    public Date deserialize(JsonParser paramJsonParser,
                            DeserializationContext paramDeserializationContext)
            throws IOException {
        String str = paramJsonParser.getText().trim();
        try {
            String[] split = str.split(" ");
            String date = split[0];
            String time = split[1];

            String year = date.split("-")[0];
            String month = date.split("-")[1];
            String day = date.split("-")[2];

            String hour = time.split(":")[0];
            String minute = time.split(":")[1];
            String second = time.split(":")[2];

            if(isaValidDateOrTime(year, month, day, hour, minute, second)){
                throw new InvalidDataException("Invalid Date Param");
            }
            return dateFormat.parse(str);
        } catch (Exception e) {
            throw new InvalidDataException("Invalid Date Param");
        }
    }

    private boolean isaValidDateOrTime(String year, String month, String day, String hour, String minute, String second) {
        return isValidDate(year, month, day) || isValidTime(hour, minute, second);
    }

    private boolean isValidTime(String hour, String minute, String second) {
        return hour.length()>2 || minute.length()>2 || second.length()>2;
    }

    private boolean isValidDate(String year, String month, String day) {
        return (year.length()>4) || (month.length()>2) || (day.length()>2);
    }
}