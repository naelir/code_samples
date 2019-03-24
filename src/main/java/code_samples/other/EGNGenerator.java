package code_samples.other;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

/**
 * $EGN_WEIGHTS = array(2,4,8,5,10,9,7,3,6); $EGN_REGIONS["Благоевград"] = 43; /
 * от 000 до 043 / $EGN_REGIONS["Бургас"] = 93; / от 044 до 093 /
 * $EGN_REGIONS["Варна"] = 139; / от 094 до 139 / $EGN_REGIONS["Велико Търново"]
 * = 169; / от 140 до 169 / $EGN_REGIONS["Видин"] = 183; / от 170 до 183 /
 * $EGN_REGIONS["Враца"] = 217; / от 184 до 217 / $EGN_REGIONS["Габрово"] = 233;
 * / от 218 до 233 / $EGN_REGIONS["Кърджали"] = 281; / от 234 до 281 /
 * $EGN_REGIONS["Кюстендил"] = 301; / от 282 до 301 / $EGN_REGIONS["Ловеч"] =
 * 319; / от 302 до 319 / $EGN_REGIONS["Монтана"] = 341; / от 320 до 341 /
 * $EGN_REGIONS["Пазарджик"] = 377; / от 342 до 377 / $EGN_REGIONS["Перник"] =
 * 395; / от 378 до 395 / $EGN_REGIONS["Плевен"] = 435; / от 396 до 435 /
 * $EGN_REGIONS["Пловдив"] = 501; / от 436 до 501 / $EGN_REGIONS["Разград"] =
 * 527; / от 502 до 527 / $EGN_REGIONS["Русе"] = 555; / от 528 до 555 /
 * $EGN_REGIONS["Силистра"] = 575; / от 556 до 575 / $EGN_REGIONS["Сливен"] =
 * 601; / от 576 до 601 / $EGN_REGIONS["Смолян"] = 623; / от 602 до 623 /
 * $EGN_REGIONS["София - град"] = 721; / от 624 до 721 / $EGN_REGIONS["София -
 * окръг"] = 751; / от 722 до 751 / $EGN_REGIONS["Стара Загора"] = 789; / от 752
 * до 789 / $EGN_REGIONS["Добрич (Толбухин)"] = 821; / от 790 до 821 /
 * $EGN_REGIONS["Търговище"] = 843; / от 822 до 843 / $EGN_REGIONS["Хасково"] =
 * 871; / от 844 до 871 / $EGN_REGIONS["Шумен"] = 903; / от 872 до 903 /
 * $EGN_REGIONS["Ямбол"] = 925; / от 904 до 925 /
 * $EGN_REGIONS["Друг/Неизвестен"] = 999; от 926 до 999 - Такъв регион понякога
 * се ползва при родени преди 1900, за родени в чужбина или ако в даден регион
 * се родят повече деца от предвиденото. Доколкото ми е известно няма правило
 * при ползването на 926 - 999
 *
 */
public class EGNGenerator {
	private static final int HIGHEST_YEAR = 2100;

	private static final int LOWEST_YEAR = 1900;

	private static final Integer[] POSITION_WEIGHTS = new Integer[] { 2, 4, 8, 5, 10, 9, 7, 3, 6 };

	public List<String> generate(final int startYear, final int endYear, final int startRegionCode,
			final int endRegionCode) {
		assertThat(startYear).isBetween(LOWEST_YEAR, HIGHEST_YEAR);
		assertThat(endYear).isBetween(LOWEST_YEAR, HIGHEST_YEAR);
		assertThat(startYear).isLessThanOrEqualTo(endYear);
		final List<String> list = new ArrayList<>();
		for (int year = startYear; year <= endYear; year++)
			for (int month = 1; month <= 12; month++)
				for (int day = 1; day <= 31; day++)
					for (int regionCode = startRegionCode; regionCode <= endRegionCode; regionCode++) {
						final int yearLastDigits = year % 100;
						final String format = String.format("%02d%02d%02d%03d", yearLastDigits, month, day, regionCode);
						final String[] split = format.split("");
						assertThat(split).hasSize(POSITION_WEIGHTS.length);
						int sum = 0;
						for (int i = 0; i < 9; i++)
							sum += Integer.parseInt(split[i]) * POSITION_WEIGHTS[i];
						final int roundedDivider = sum / 11;
						final int difference = sum - roundedDivider * 11;
						final int differenceRemainder = difference % 11;
						int controlDigit = 0;
						if (differenceRemainder < 10)
							controlDigit = differenceRemainder;
						int monthValue = month;
						if (year >= 2000)
							monthValue = month + 40;
						final String egn = String.format("%02d%02d%02d%03d%d", yearLastDigits, monthValue, day,
								regionCode, controlDigit);
						list.add(egn);
					}
		return list;
	}
}
