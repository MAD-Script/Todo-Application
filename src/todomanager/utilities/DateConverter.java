package todomanager.utilities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {

  public static Date convertLocalDateToDate(LocalDate localDate) {
    Instant instant = localDate
      .atStartOfDay(ZoneId.systemDefault())
      .toInstant();
    return Date.from(instant);
  }

  public static LocalDate convertDateToLocalDate(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }
}
