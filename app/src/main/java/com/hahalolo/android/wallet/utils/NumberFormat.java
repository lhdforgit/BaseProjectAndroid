/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.utils;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by ndn on 10/15/18.
 */
public final class NumberFormat {

    private NumberFormat() {

    }

    /**
     * {@see https://stackoverflow.com/questions/12490793/android-currency-symbol-ordering}
     * Not all countries expect the currency symbol before the amount.
     * <p>
     * If you always want the currency format to match an Americanised expectation, leave the locale as Locale.US.
     * If you want the currency to display in a localised way, leave your implementation as is.
     * <p>
     * See this brief guide (from Microsoft, no less):
     * <p>
     * Globalisation Step-by-Step {@see https://docs.microsoft.com/en-us/globalization/locale/currency-formatting}
     * <p>
     * I'd guess what you may be trying to achieve is to display the currency in a format appropriate to its locale?
     * If that's the case, just match the locale to the currency you're using, before calling the method.
     * <p>
     * Note that the format can even vary in the same country.
     * In Canada, it's reasonably common to see English speakers use the format $50.00, whereas French-Canadians may use 50,00 $.
     * <p>
     * Also see this question on UX:
     * <p>
     * https://ux.stackexchange.com/questions/22574/where-to-place-currency-symbol-when-localizing-and-what-to-do-with-odd-symbols
     * <p>
     * Without currency {@see https://stackoverflow.com/questions/8658205/format-currency-without-currency-symbol}
     *
     * @param curr  the ISO 4217 code of the currency
     * @param value price number
     * @return price format with curr
     */
    public static String priceFormatWithCurr(String curr, Double value) {
        try {
            // Default price locale. US
            java.text.NumberFormat format = java.text.NumberFormat.getCurrencyInstance(Locale.US);
            format.setCurrency(Currency.getInstance(curr));
            return format.format(value);
        } catch (Exception e) {
            return "";
        }
    }



    /**
     * Discount is the amount off on a product. A $10 discount mean product is selling at $10 less than MRP/Buying Price/actual price of product.
     * <p>
     * Discount = BuyPrice - SalePrice;
     * <p>
     * SalePrice= BuyPrice - Discount;
     * <p>
     * Discount % is percent by which a product got cheaper from MRP/Buy Price/actual price of product.
     * A 10% Discount means if something have MRP value $X then after discount of 10% its sale price will be $ X-X(10/100)
     * <p>
     * Discount% = ( (Buy Price-SalePrice)/BuyPrice)x100;
     * <p>
     * SalePrice = BuyPrice-( (BuyPrice*Discount%)/100);
     *
     * @param buyPriceValue  buy price value
     * @param salePriceValue sale price value
     * @return Discount value
     */
    public static int calculateDiscount(String buyPriceValue, String salePriceValue) {
        try {
            if (buyPriceValue == null || buyPriceValue.isEmpty() || salePriceValue == null || salePriceValue.isEmpty())
                return 0;
            return calculateDiscount(Double.valueOf(buyPriceValue), Double.valueOf(salePriceValue));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int calculateDiscount(double buyPriceValue, double salePriceValue) {
        try {
            BigDecimal buyPrice = BigDecimal.valueOf(buyPriceValue);
            if (buyPrice.equals(BigDecimal.ZERO))
                return 0;
            BigDecimal salePrice = BigDecimal.valueOf(salePriceValue);
            BigDecimal discount = ((buyPrice.subtract(salePrice)).multiply(BigDecimal.valueOf(100))).divide(buyPrice);

            return (int) discount.doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int calculateSale(double buyPriceValue, double salePriceValue) {
        return 100 - calculateDiscount(buyPriceValue, salePriceValue);
    }

    public static int calculateSale(String buyPriceValue, String salePriceValue) {
        return 100 - calculateDiscount(buyPriceValue, salePriceValue);
    }

    public static String calculateDiscountString(double buyPriceValue, double salePriceValue) {
        try {
            return String.valueOf(calculateDiscount(buyPriceValue, salePriceValue));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String calculateDiscountString(String buyPriceValue, String salePriceValue) {
        try {
            return String.valueOf(calculateDiscount(buyPriceValue, salePriceValue));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String calculateSaleString(double buyPriceValue, double salePriceValue) {
        try {
            return String.valueOf(calculateSale(buyPriceValue, salePriceValue));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String calculateSaleString(String buyPriceValue, String salePriceValue) {
        try {
            return String.valueOf(calculateSale(buyPriceValue, salePriceValue));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
