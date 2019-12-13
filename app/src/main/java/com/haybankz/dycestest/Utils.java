package com.haybankz.dycestest;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;

public class Utils {

    /*
     * @param input : string ,  string to format
     * @param addKobo : boolean , to add .00 at the end of formatted string or not
     * returns string
     * this method formats a string to have the commas after 3 zero and adds/not .00 to the
     * formatted string depending on the value of addKobo
     */
    public static String formatAmount(int i, boolean addKobo) {

        String input = ""+i;

        String formatted = "";
        try {
            Long longval;
            if (!input.isEmpty()) {
                if (input.contains(",")) {
                    input = input.replace(",", "");
                }

                longval = Long.parseLong(input);
                DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                decimalFormat.applyPattern("#,###,###,###,###,###");
                formatted = decimalFormat.format(longval);

            }

            if (addKobo) {
                formatted += ".00";
            }

        } catch (NumberFormatException e) {
//            Log.e("Utils", "formatString: ", e);
        }

        return formatted;
    }
}
