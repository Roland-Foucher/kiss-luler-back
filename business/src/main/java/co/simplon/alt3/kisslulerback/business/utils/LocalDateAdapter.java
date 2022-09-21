package co.simplon.alt3.kisslulerback.business.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * permet de personnaliser la façon de parser les Json, dans notre cas les
 * localDate non pris en compte par Gson
 */
public class LocalDateAdapter implements JsonSerializer<LocalDate> {

  /**
   * format les date le LocalTime pour le JSON
   */
  @Override
  public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {

    return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
  }

}