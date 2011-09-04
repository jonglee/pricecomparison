package com.selectquote.product.data;

import java.util.HashMap;
import java.util.Map;

import static com.selectquote.product.config.InputParameters.*;

/**
 * Category
 * <p/>
 * Date: Feb 16, 2009
 *
 * @author jonglee
 */
public class Category {
    private final Type type;

    //different age group premiums, e.g: age 30, 40, 50, and 60
    private final Map<Age, PremiumInfo[]> premiums = new HashMap<Age, PremiumInfo[]>();

    public Category(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void addPremiums(Age age, PremiumInfo[] info) {
        premiums.put(age, info);
    }

    public PremiumInfo[] getPremiums(Age age) {
        return premiums.get(age);
    }

    public enum Age {

        THIRTY(30), FORTY(40), FIFTY(50), SIXTY(60);

        public final int age;

        private Age(int age) {
            this.age = age;
        }
    }

    public enum Type {

        /**
         * 50 K FaceAmount
         */
        _50K_PREFERPLUS_NON_SMOKER_10_YEAR(FIFTY_K, PREFERRED_PLUS, false, TEN_YEAR,
                "50K Preferred Plus Non Tobacco 10 Year"),

        _50K_PREFERPLUS_NON_SMOKER_15_YEAR(FIFTY_K, PREFERRED_PLUS, false, FIFTEEN_YEAR,
                "50K Preferred Plus Non Tobacco 15 Year"),

        _50K_PREFERPLUS_NON_SMOKER_20_YEAR(FIFTY_K, PREFERRED_PLUS, false, TWENTY_YEAR,
                "50K Preferred Plus Non Tobacco 20 Year"),

        _50K_PREFERPLUS_NON_SMOKER_30_YEAR(FIFTY_K, PREFERRED_PLUS, false, THIRTY_YEAR,
                "50K Preferred Plus Non Tobacco 30 Year"),

        _50K_PREFER_NON_SMOKER_10_YEAR(FIFTY_K, PREFERRED, false, TEN_YEAR,
                "50K Preferred Non Tobacco 10 Year"),

        _50K_PREFER_NON_SMOKER_15_YEAR(FIFTY_K, PREFERRED, false, FIFTEEN_YEAR,
                "50K Preferred Non Tobacco 15 Year"),

        _50K_PREFER_NON_SMOKER_20_YEAR(FIFTY_K, PREFERRED, false, TWENTY_YEAR,
                "50K Preferred Non Tobacco 20 Year"),

        _50K_PREFER_NON_SMOKER_30_YEAR(FIFTY_K, PREFERRED, false, THIRTY_YEAR,
                "50K Preferred Non Tobacco 30 Year"),

        _50K_PREFER_SMOKER_10_YEAR(FIFTY_K, PREFERRED, true, TEN_YEAR,
                "50K Preferred Tobacco 10 Year"),

        _50K_PREFER_SMOKER_15_YEAR(FIFTY_K, PREFERRED, true, FIFTEEN_YEAR,
                "50K Preferred Tobacco 15 Year"),

        _50K_PREFER_SMOKER_20_YEAR(FIFTY_K, PREFERRED, true, TWENTY_YEAR,
                "50K Preferred Tobacco 20 Year"),

        _50K_PREFER_SMOKER_30_YEAR(FIFTY_K, PREFERRED, true, THIRTY_YEAR,
                "50K Preferred Tobacco 30 Year"),

        _50K_STANDARD_PLUS_NON_SMOKER_10_YEAR(FIFTY_K, STANDARD_PLUS, false, TEN_YEAR,
                "50K Standard Plus Non Tobacco 10 Year"),

        _50K_STANDARD_PLUS_NON_SMOKER_15_YEAR(FIFTY_K, STANDARD_PLUS, false, FIFTEEN_YEAR,
                "50K Standard Plus Non Tobacco 15 Year"),

        _50K_STANDARD_PLUS_NON_SMOKER_20_YEAR(FIFTY_K, STANDARD_PLUS, false, TWENTY_YEAR,
                "50K Standard Plus Non Tobacco 20 Year"),

        _50K_STANDARD_PLUS_NON_SMOKER_30_YEAR(FIFTY_K, STANDARD_PLUS, false, THIRTY_YEAR,
                "50K Standard Plus Non Tobacco 30 Year"),

        _50K_STANDARD_PLUS_SMOKER_10_YEAR(FIFTY_K, STANDARD_PLUS, true, TEN_YEAR,
                "50K Standard Plus Tobacco 10 Year"),

        _50K_STANDARD_PLUS_SMOKER_15_YEAR(FIFTY_K, STANDARD_PLUS, true, FIFTEEN_YEAR,
                "50K Standard Plus Tobacco 15 Year"),

        _50K_STANDARD_PLUS_SMOKER_20_YEAR(FIFTY_K, STANDARD_PLUS, true, TWENTY_YEAR,
                "50K Standard Plus Tobacco 20 Year"),

        _50K_STANDARD_PLUS_SMOKER_30_YEAR(FIFTY_K, STANDARD_PLUS, true, THIRTY_YEAR,
                "50K Standard Plus Tobacco 30 Year"),

        _50K_STANDARD_NON_SMOKER_10_YEAR(FIFTY_K, STANDARD, false, TEN_YEAR,
                "50K Standard Non Tobacco 10 Year"),

        _50K_STANDARD_NON_SMOKER_15_YEAR(FIFTY_K, STANDARD, false, FIFTEEN_YEAR,
                "50K Standard Non Tobacco 15 Year"),

        _50K_STANDARD_NON_SMOKER_20_YEAR(FIFTY_K, STANDARD, false, TWENTY_YEAR,
                "50K Standard Non Tobacco 20 Year"),

        _50K_STANDARD_NON_SMOKER_30_YEAR(FIFTY_K, STANDARD, false, THIRTY_YEAR,
                "50K Standard Non Tobacco 30 Year"),

        _50K_STANDARD_SMOKER_10_YEAR(FIFTY_K, STANDARD, true, TEN_YEAR,
                "50K Standard Tobacco 10 Year"),

        _50K_STANDARD_SMOKER_15_YEAR(FIFTY_K, STANDARD, true, FIFTEEN_YEAR,
                "50K Standard Tobacco 15 Year"),

        _50K_STANDARD_SMOKER_20_YEAR(FIFTY_K, STANDARD, true, TWENTY_YEAR,
                "50K Standard Tobacco 20 Year"),

        _50K_STANDARD_SMOKER_30_YEAR(FIFTY_K, STANDARD, true, THIRTY_YEAR,
                "50K Standard Tobacco 30 Year"),

        /**
         * 100 K FaceAmount
         */
        _100K_PREFERPLUS_NON_SMOKER_10_YEAR(HUNDRED_K, PREFERRED_PLUS, false, TEN_YEAR,
                "100K Preferred Plus Non Tobacco 10 Year"),

        _100K_PREFERPLUS_NON_SMOKER_15_YEAR(HUNDRED_K, PREFERRED_PLUS, false, FIFTEEN_YEAR,
                "100K Preferred Plus Non Tobacco 15 Year"),

        _100K_PREFERPLUS_NON_SMOKER_20_YEAR(HUNDRED_K, PREFERRED_PLUS, false, TWENTY_YEAR,
                "100K Preferred Plus Non Tobacco 20 Year"),

        _100K_PREFERPLUS_NON_SMOKER_30_YEAR(HUNDRED_K, PREFERRED_PLUS, false, THIRTY_YEAR,
                "100K Preferred Plus Non Tobacco 30 Year"),

        _100K_PREFER_NON_SMOKER_10_YEAR(HUNDRED_K, PREFERRED, false, TEN_YEAR,
                "100K Preferred Non Tobacco 10 Year"),

        _100K_PREFER_NON_SMOKER_15_YEAR(HUNDRED_K, PREFERRED, false, FIFTEEN_YEAR,
                "100K Preferred Non Tobacco 15 Year"),

        _100K_PREFER_NON_SMOKER_20_YEAR(HUNDRED_K, PREFERRED, false, TWENTY_YEAR,
                "100K Preferred Non Tobacco 20 Year"),

        _100K_PREFER_NON_SMOKER_30_YEAR(HUNDRED_K, PREFERRED, false, THIRTY_YEAR,
                "100K Preferred Non Tobacco 30 Year"),

        _100K_PREFER_SMOKER_10_YEAR(HUNDRED_K, PREFERRED, true, TEN_YEAR,
                "100K Preferred Tobacco 10 Year"),

        _100K_PREFER_SMOKER_15_YEAR(HUNDRED_K, PREFERRED, true, FIFTEEN_YEAR,
                "100K Preferred Tobacco 15 Year"),

        _100K_PREFER_SMOKER_20_YEAR(HUNDRED_K, PREFERRED, true, TWENTY_YEAR,
                "100K Preferred Tobacco 20 Year"),

        _100K_PREFER_SMOKER_30_YEAR(HUNDRED_K, PREFERRED, true, THIRTY_YEAR,
                "100K Preferred Tobacco 30 Year"),

        _100K_STANDARD_PLUS_NON_SMOKER_10_YEAR(HUNDRED_K, STANDARD_PLUS, false, TEN_YEAR,
                "100K Standard Plus Non Tobacco 10 Year"),

        _100K_STANDARD_PLUS_NON_SMOKER_15_YEAR(HUNDRED_K, STANDARD_PLUS, false, FIFTEEN_YEAR,
                "100K Standard Plus Non Tobacco 15 Year"),

        _100K_STANDARD_PLUS_NON_SMOKER_20_YEAR(HUNDRED_K, STANDARD_PLUS, false, TWENTY_YEAR,
                "100K Standard Plus Non Tobacco 20 Year"),

        _100K_STANDARD_PLUS_NON_SMOKER_30_YEAR(HUNDRED_K, STANDARD_PLUS, false, THIRTY_YEAR,
                "100K Standard Plus Non Tobacco 30 Year"),

        _100K_STANDARD_PLUS_SMOKER_10_YEAR(HUNDRED_K, STANDARD_PLUS, true, TEN_YEAR,
                "100K Standard Plus Tobacco 10 Year"),

        _100K_STANDARD_PLUS_SMOKER_15_YEAR(HUNDRED_K, STANDARD_PLUS, true, FIFTEEN_YEAR,
                "100K Standard Plus Tobacco 15 Year"),

        _100K_STANDARD_PLUS_SMOKER_20_YEAR(HUNDRED_K, STANDARD_PLUS, true, TWENTY_YEAR,
                "100K Standard Plus Tobacco 20 Year"),

        _100K_STANDARD_PLUS_SMOKER_30_YEAR(HUNDRED_K, STANDARD_PLUS, true, THIRTY_YEAR,
                "100K Standard Plus Tobacco 30 Year"),

        _100K_STANDARD_NON_SMOKER_10_YEAR(HUNDRED_K, STANDARD, false, TEN_YEAR,
                "100K Standard Non Tobacco 10 Year"),

        _100K_STANDARD_NON_SMOKER_15_YEAR(HUNDRED_K, STANDARD, false, FIFTEEN_YEAR,
                "100K Standard Non Tobacco 15 Year"),

        _100K_STANDARD_NON_SMOKER_20_YEAR(HUNDRED_K, STANDARD, false, TWENTY_YEAR,
                "100K Standard Non Tobacco 20 Year"),

        _100K_STANDARD_NON_SMOKER_30_YEAR(HUNDRED_K, STANDARD, false, THIRTY_YEAR,
                "100K Standard Non Tobacco 30 Year"),

        _100K_STANDARD_SMOKER_10_YEAR(HUNDRED_K, STANDARD, true, TEN_YEAR,
                "100K Standard Tobacco 10 Year"),

        _100K_STANDARD_SMOKER_15_YEAR(HUNDRED_K, STANDARD, true, FIFTEEN_YEAR,
                "100K Standard Tobacco 15 Year"),

        _100K_STANDARD_SMOKER_20_YEAR(HUNDRED_K, STANDARD, true, TWENTY_YEAR,
                "100K Standard Tobacco 20 Year"),

        _100K_STANDARD_SMOKER_30_YEAR(HUNDRED_K, STANDARD, true, THIRTY_YEAR,
                "100K Standard Tobacco 30 Year"),

        /**
         * 250K FaceAmount
         */
        _250K_PREFERPLUS_NON_SMOKER_10_YEAR(TWO_FIFTY_K, PREFERRED_PLUS, false, TEN_YEAR,
                "250K Preferred Plus Non Tobacco 10 Year"),

        _250K_PREFERPLUS_NON_SMOKER_15_YEAR(TWO_FIFTY_K, PREFERRED_PLUS, false, FIFTEEN_YEAR,
                "250K Preferred Plus Non Tobacco 15 Year"),

        _250K_PREFERPLUS_NON_SMOKER_20_YEAR(TWO_FIFTY_K, PREFERRED_PLUS, false, TWENTY_YEAR,
                "250K Preferred Plus Non Tobacco 20 Year"),

        _250K_PREFERPLUS_NON_SMOKER_30_YEAR(TWO_FIFTY_K, PREFERRED_PLUS, false, THIRTY_YEAR,
                "250K Preferred Plus Non Tobacco 30 Year"),

        _250K_PREFER_NON_SMOKER_10_YEAR(TWO_FIFTY_K, PREFERRED, false, TEN_YEAR,
                "250K Preferred Non Tobacco 10 Year"),

        _250K_PREFER_NON_SMOKER_15_YEAR(TWO_FIFTY_K, PREFERRED, false, FIFTEEN_YEAR,
                "250K Preferred Non Tobacco 15 Year"),

        _250K_PREFER_NON_SMOKER_20_YEAR(TWO_FIFTY_K, PREFERRED, false, TWENTY_YEAR,
                "250K Preferred Non Tobacco 20 Year"),

        _250K_PREFER_NON_SMOKER_30_YEAR(TWO_FIFTY_K, PREFERRED, false, THIRTY_YEAR,
                "250K Preferred Non Tobacco 30 Year"),

        _250K_PREFER_SMOKER_10_YEAR(TWO_FIFTY_K, PREFERRED, true, TEN_YEAR,
                "250K Preferred Tobacco 10 Year"),

        _250K_PREFER_SMOKER_15_YEAR(TWO_FIFTY_K, PREFERRED, true, FIFTEEN_YEAR,
                "250K Preferred Tobacco 15 Year"),

        _250K_PREFER_SMOKER_20_YEAR(TWO_FIFTY_K, PREFERRED, true, TWENTY_YEAR,
                "250K Preferred Tobacco 20 Year"),

        _250K_PREFER_SMOKER_30_YEAR(TWO_FIFTY_K, PREFERRED, true, THIRTY_YEAR,
                "250K Preferred Tobacco 30 Year"),

        _250K_STANDARD_PLUS_NON_SMOKER_10_YEAR(TWO_FIFTY_K, STANDARD_PLUS, false, TEN_YEAR,
                "250K Standard Plus Non Tobacco 10 Year"),

        _250K_STANDARD_PLUS_NON_SMOKER_15_YEAR(TWO_FIFTY_K, STANDARD_PLUS, false, FIFTEEN_YEAR,
                "250K Standard Plus Non Tobacco 15 Year"),

        _250K_STANDARD_PLUS_NON_SMOKER_20_YEAR(TWO_FIFTY_K, STANDARD_PLUS, false, TWENTY_YEAR,
                "250K Standard Plus Non Tobacco 20 Year"),

        _250K_STANDARD_PLUS_NON_SMOKER_30_YEAR(TWO_FIFTY_K, STANDARD_PLUS, false, THIRTY_YEAR,
                "250K Standard Plus Non Tobacco 30 Year"),

        _250K_STANDARD_PLUS_SMOKER_10_YEAR(TWO_FIFTY_K, STANDARD_PLUS, true, TEN_YEAR,
                "250K Standard Plus Tobacco 10 Year"),

        _250K_STANDARD_PLUS_SMOKER_15_YEAR(TWO_FIFTY_K, STANDARD_PLUS, true, FIFTEEN_YEAR,
                "250K Standard Plus Tobacco 15 Year"),

        _250K_STANDARD_PLUS_SMOKER_20_YEAR(TWO_FIFTY_K, STANDARD_PLUS, true, TWENTY_YEAR,
                "250K Standard Plus Tobacco 20 Year"),

        _250K_STANDARD_PLUS_SMOKER_30_YEAR(TWO_FIFTY_K, STANDARD_PLUS, true, THIRTY_YEAR,
                "250K Standard Plus Tobacco 30 Year"),

        _250K_STANDARD_NON_SMOKER_10_YEAR(TWO_FIFTY_K, STANDARD, false, TEN_YEAR,
                "250K Standard Non Tobacco 10 Year"),

        _250K_STANDARD_NON_SMOKER_15_YEAR(TWO_FIFTY_K, STANDARD, false, FIFTEEN_YEAR,
                "250K Standard Non Tobacco 15 Year"),

        _250K_STANDARD_NON_SMOKER_20_YEAR(TWO_FIFTY_K, STANDARD, false, TWENTY_YEAR,
                "250K Standard Non Tobacco 20 Year"),

        _250K_STANDARD_NON_SMOKER_30_YEAR(TWO_FIFTY_K, STANDARD, false, THIRTY_YEAR,
                "250K Standard Non Tobacco 30 Year"),

        _250K_STANDARD_SMOKER_10_YEAR(TWO_FIFTY_K, STANDARD, true, TEN_YEAR,
                "250K Standard Tobacco 10 Year"),

        _250K_STANDARD_SMOKER_15_YEAR(TWO_FIFTY_K, STANDARD, true, FIFTEEN_YEAR,
                "250K Standard Tobacco 15 Year"),

        _250K_STANDARD_SMOKER_20_YEAR(TWO_FIFTY_K, STANDARD, true, TWENTY_YEAR,
                "250K Standard Tobacco 20 Year"),

        _250K_STANDARD_SMOKER_30_YEAR(TWO_FIFTY_K, STANDARD, true, THIRTY_YEAR,
                "250K Standard Tobacco 30 Year"),

        /**
         * 500K FaceAmount
         */
        _500K_PREFERPLUS_NON_SMOKER_10_YEAR(FIVE_HUNDRED_K, PREFERRED_PLUS, false, TEN_YEAR,
                "500K Preferred Plus Non Tobacco 10 Year"),

        _500K_PREFERPLUS_NON_SMOKER_15_YEAR(FIVE_HUNDRED_K, PREFERRED_PLUS, false, FIFTEEN_YEAR,
                "500K Preferred Plus Non Tobacco 15 Year"),

        _500K_PREFERPLUS_NON_SMOKER_20_YEAR(FIVE_HUNDRED_K, PREFERRED_PLUS, false, TWENTY_YEAR,
                "500K Preferred Plus Non Tobacco 20 Year"),

        _500K_PREFERPLUS_NON_SMOKER_30_YEAR(FIVE_HUNDRED_K, PREFERRED_PLUS, false, THIRTY_YEAR,
                "500K Preferred Plus Non Tobacco 30 Year"),

        _500K_PREFER_NON_SMOKER_10_YEAR(FIVE_HUNDRED_K, PREFERRED, false, TEN_YEAR,
                "500K Preferred Non Tobacco 10 Year"),

        _500K_PREFER_NON_SMOKER_15_YEAR(FIVE_HUNDRED_K, PREFERRED, false, FIFTEEN_YEAR,
                "500K Preferred Non Tobacco 15 Year"),

        _500K_PREFER_NON_SMOKER_20_YEAR(FIVE_HUNDRED_K, PREFERRED, false, TWENTY_YEAR,
                "500K Preferred Non Tobacco 20 Year"),

        _500K_PREFER_NON_SMOKER_30_YEAR(FIVE_HUNDRED_K, PREFERRED, false, THIRTY_YEAR,
                "500K Preferred Non Tobacco 30 Year"),

        _500K_PREFER_SMOKER_10_YEAR(FIVE_HUNDRED_K, PREFERRED, true, TEN_YEAR,
                "500K Preferred Tobacco 10 Year"),

        _500K_PREFER_SMOKER_15_YEAR(FIVE_HUNDRED_K, PREFERRED, true, FIFTEEN_YEAR,
                "500K Preferred Tobacco 15 Year"),

        _500K_PREFER_SMOKER_20_YEAR(FIVE_HUNDRED_K, PREFERRED, true, TWENTY_YEAR,
                "500K Preferred Tobacco 20 Year"),

        _500K_PREFER_SMOKER_30_YEAR(FIVE_HUNDRED_K, PREFERRED, true, THIRTY_YEAR,
                "500K Preferred Tobacco 30 Year"),

        _500K_STANDARD_PLUS_NON_SMOKER_10_YEAR(FIVE_HUNDRED_K, STANDARD_PLUS, false, TEN_YEAR,
                "500K Standard Plus Non Tobacco 10 Year"),

        _500K_STANDARD_PLUS_NON_SMOKER_15_YEAR(FIVE_HUNDRED_K, STANDARD_PLUS, false, FIFTEEN_YEAR,
                "500K Standard Plus Non Tobacco 15 Year"),

        _500K_STANDARD_PLUS_NON_SMOKER_20_YEAR(FIVE_HUNDRED_K, STANDARD_PLUS, false, TWENTY_YEAR,
                "500K Standard Plus Non Tobacco 20 Year"),

        _500K_STANDARD_PLUS_NON_SMOKER_30_YEAR(FIVE_HUNDRED_K, STANDARD_PLUS, false, THIRTY_YEAR,
                "500K Standard Plus Non Tobacco 30 Year"),

        _500K_STANDARD_PLUS_SMOKER_10_YEAR(FIVE_HUNDRED_K, STANDARD_PLUS, true, TEN_YEAR,
                "500K Standard Plus Tobacco 10 Year"),

        _500K_STANDARD_PLUS_SMOKER_15_YEAR(FIVE_HUNDRED_K, STANDARD_PLUS, true, FIFTEEN_YEAR,
                "500K Standard Plus Tobacco 15 Year"),

        _500K_STANDARD_PLUS_SMOKER_20_YEAR(FIVE_HUNDRED_K, STANDARD_PLUS, true, TWENTY_YEAR,
                "500K Standard Plus Tobacco 20 Year"),

        _500K_STANDARD_PLUS_SMOKER_30_YEAR(FIVE_HUNDRED_K, STANDARD_PLUS, true, THIRTY_YEAR,
                "500K Standard Plus Tobacco 30 Year"),

        _500K_STANDARD_NON_SMOKER_10_YEAR(FIVE_HUNDRED_K, STANDARD, false, TEN_YEAR,
                "500K Standard Non Tobacco 10 Year"),

        _500K_STANDARD_NON_SMOKER_15_YEAR(FIVE_HUNDRED_K, STANDARD, false, FIFTEEN_YEAR,
                "500K Standard Non Tobacco 15 Year"),

        _500K_STANDARD_NON_SMOKER_20_YEAR(FIVE_HUNDRED_K, STANDARD, false, TWENTY_YEAR,
                "500K Standard Non Tobacco 20 Year"),

        _500K_STANDARD_NON_SMOKER_30_YEAR(FIVE_HUNDRED_K, STANDARD, false, THIRTY_YEAR,
                "500K Standard Non Tobacco 30 Year"),

        _500K_STANDARD_SMOKER_10_YEAR(FIVE_HUNDRED_K, STANDARD, true, TEN_YEAR,
                "500K Standard Tobacco 10 Year"),

        _500K_STANDARD_SMOKER_15_YEAR(FIVE_HUNDRED_K, STANDARD, true, FIFTEEN_YEAR,
                "500K Standard Tobacco 15 Year"),

        _500K_STANDARD_SMOKER_20_YEAR(FIVE_HUNDRED_K, STANDARD, true, TWENTY_YEAR,
                "500K Standard Tobacco 20 Year"),

        _500K_STANDARD_SMOKER_30_YEAR(FIVE_HUNDRED_K, STANDARD, true, THIRTY_YEAR,
                "500K Standard Tobacco 30 Year"),

        /**
         * 1 Million FaceAmount
         */
        _1Mil_PREFERPLUS_NON_SMOKER_10_YEAR(ONE_MILLION, PREFERRED_PLUS, false, TEN_YEAR,
                "1 Million Preferred Plus Non Tobacco 10 Year"),

        _1Mil_PREFERPLUS_NON_SMOKER_15_YEAR(ONE_MILLION, PREFERRED_PLUS, false, FIFTEEN_YEAR,
                "1 Million Preferred Plus Non Tobacco 15 Year"),

        _1Mil_PREFERPLUS_NON_SMOKER_20_YEAR(ONE_MILLION, PREFERRED_PLUS, false, TWENTY_YEAR,
                "1 Million Preferred Plus Non Tobacco 20 Year"),

        _1Mil_PREFERPLUS_NON_SMOKER_30_YEAR(ONE_MILLION, PREFERRED_PLUS, false, THIRTY_YEAR,
                "1 Million Preferred Plus Non Tobacco 30 Year"),

        _1Mil_PREFER_NON_SMOKER_10_YEAR(ONE_MILLION, PREFERRED, false, TEN_YEAR,
                "1 Million Preferred Non Tobacco 10 Year"),

        _1Mil_PREFER_NON_SMOKER_15_YEAR(ONE_MILLION, PREFERRED, false, FIFTEEN_YEAR,
                "1 Million Preferred Non Tobacco 15 Year"),

        _1Mil_PREFER_NON_SMOKER_20_YEAR(ONE_MILLION, PREFERRED, false, TWENTY_YEAR,
                "1 Million Preferred Non Tobacco 20 Year"),

        _1Mil_PREFER_NON_SMOKER_30_YEAR(ONE_MILLION, PREFERRED, false, THIRTY_YEAR,
                "1 Million Preferred Non Tobacco 30 Year"),

        _1Mil_PREFER_SMOKER_10_YEAR(ONE_MILLION, PREFERRED, true, TEN_YEAR,
                "1 Million Preferred Tobacco 10 Year"),

        _1Mil_PREFER_SMOKER_15_YEAR(ONE_MILLION, PREFERRED, true, FIFTEEN_YEAR,
                "1 Million Preferred Tobacco 15 Year"),

        _1Mil_PREFER_SMOKER_20_YEAR(ONE_MILLION, PREFERRED, true, TWENTY_YEAR,
                "1 Million Preferred Tobacco 20 Year"),

        _1Mil_PREFER_SMOKER_30_YEAR(ONE_MILLION, PREFERRED, true, THIRTY_YEAR,
                "1 Million Preferred Tobacco 30 Year"),

        _1Mil_STANDARD_PLUS_NON_SMOKER_10_YEAR(ONE_MILLION, STANDARD_PLUS, false, TEN_YEAR,
                "1 Million Standard Plus Non Tobacco 10 Year"),

        _1Mil_STANDARD_PLUS_NON_SMOKER_15_YEAR(ONE_MILLION, STANDARD_PLUS, false, FIFTEEN_YEAR,
                "1 Million Standard Plus Non Tobacco 15 Year"),

        _1Mil_STANDARD_PLUS_NON_SMOKER_20_YEAR(ONE_MILLION, STANDARD_PLUS, false, TWENTY_YEAR,
                "1 Million Standard Plus Non Tobacco 20 Year"),

        _1Mil_STANDARD_PLUS_NON_SMOKER_30_YEAR(ONE_MILLION, STANDARD_PLUS, false, THIRTY_YEAR,
                "1 Million Standard Plus Non Tobacco 30 Year"),

        _1Mil_STANDARD_PLUS_SMOKER_10_YEAR(ONE_MILLION, STANDARD_PLUS, true, TEN_YEAR,
                "1 Million Standard Plus Tobacco 10 Year"),

        _1Mil_STANDARD_PLUS_SMOKER_15_YEAR(ONE_MILLION, STANDARD_PLUS, true, FIFTEEN_YEAR,
                "1 Million Standard Plus Tobacco 15 Year"),

        _1Mil_STANDARD_PLUS_SMOKER_20_YEAR(ONE_MILLION, STANDARD_PLUS, true, TWENTY_YEAR,
                "1 Million Standard Plus Tobacco 20 Year"),

        _1Mil_STANDARD_PLUS_SMOKER_30_YEAR(ONE_MILLION, STANDARD_PLUS, true, THIRTY_YEAR,
                "1 Million Standard Plus Tobacco 30 Year"),

        _1Mil_STANDARD_NON_SMOKER_10_YEAR(ONE_MILLION, STANDARD, false, TEN_YEAR,
                "1 Million Standard Non Tobacco 10 Year"),

        _1Mil_STANDARD_NON_SMOKER_15_YEAR(ONE_MILLION, STANDARD, false, FIFTEEN_YEAR,
                "1 Million Standard Non Tobacco 15 Year"),

        _1Mil_STANDARD_NON_SMOKER_20_YEAR(ONE_MILLION, STANDARD, false, TWENTY_YEAR,
                "1 Million Standard Non Tobacco 20 Year"),

        _1Mil_STANDARD_NON_SMOKER_30_YEAR(ONE_MILLION, STANDARD, false, THIRTY_YEAR,
                "1 Million Standard Non Tobacco 30 Year"),

        _1Mil_STANDARD_SMOKER_10_YEAR(ONE_MILLION, STANDARD, true, TEN_YEAR,
                "1 Million Standard Tobacco 10 Year"),

        _1Mil_STANDARD_SMOKER_15_YEAR(ONE_MILLION, STANDARD, true, FIFTEEN_YEAR,
                "1 Million Standard Tobacco 15 Year"),

        _1Mil_STANDARD_SMOKER_20_YEAR(ONE_MILLION, STANDARD, true, TWENTY_YEAR,
                "1 Million Standard Tobacco 20 Year"),

        _1Mil_STANDARD_SMOKER_30_YEAR(ONE_MILLION, STANDARD, true, THIRTY_YEAR,
                "1 Million Standard Tobacco 30 Year");                

        public final String faceAmount;
        public final String health;
        public final boolean smoker;
        public final String term;
        public final String categoryName;

        private Type(String faceAmount, String health, boolean smoker, String term, String categoryName) {
            this.faceAmount = faceAmount;
            this.health = health;
            this.smoker = smoker;
            this.term = term;
            this.categoryName = categoryName;
        }

        @Override
        public String toString() {
            return categoryName;
        }
        }
}
