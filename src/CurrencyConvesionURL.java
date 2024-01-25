public class CurrencyConvesionURL
{
    //#region currencies
    public static String[] worldCurrencies = new String[]
    {
        "AED", // United Arab Emirates dirham
        "AFN", // Afghan afghani
        "ALL", // Albanian lek
        "AMD", // Armenian dram
        "ANG", // Netherlands Antillean guilder
        "AOA", // Angolan kwanza
        "ARS", // Argentine peso
        "AUD", // Australian dollar
        "AWG", // Aruban florin
        "AZN", // Azerbaijani manat
        "BAM", // Bosnia and Herzegovina convertible mark
        "BBD", // Barbadian dollar
        "BDT", // Bangladeshi taka
        "BGN", // Bulgarian lev
        "BHD", // Bahraini dinar
        "BIF", // Burundian franc
        "BMD", // Bermudian dollar
        "BND", // Brunei dollar
        "BOB", // Bolivian boliviano
        "BRL", // Brazilian real
        "BSD", // Bahamian dollar
        "BTN", // Bhutanese ngultrum
        "BWP", // Botswana pula
        "BYN", // Belarusian ruble
        "BZD", // Belize dollar
        "CAD", // Canadian dollar
        "CDF", // Congolese franc
        "CHF", // Swiss franc
        "CLP", // Chilean peso
        "CNY", // Chinese yuan
        "COP", // Colombian peso
        "CRC", // Costa Rican colón
        "CUC", // Cuban convertible peso
        "CUP", // Cuban peso
        "CVE", // Cape Verdean escudo
        "CZK", // Czech koruna
        "DJF", // Djiboutian franc
        "DKK", // Danish krone
        "DOP", // Dominican peso
        "DZD", // Algerian dinar
        "EGP", // Egyptian pound
        "ERN", // Eritrean nakfa
        "ETB", // Ethiopian birr
        "EUR", // Euro
        "FJD", // Fijian dollar
        "FKP", // Falkland Islands pound
        "GBP", // British pound
        "GEL", // Georgian lari
        "GHS", // Ghanaian cedi
        "GIP", // Gibraltar pound
        "GMD", // Gambian dalasi
        "GNF", // Guinean franc
        "GTQ", // Guatemalan quetzal
        "GYD", // Guyanese dollar
        "HKD", // Hong Kong dollar
        "HNL", // Honduran lempira
        "HRK", // Croatian kuna
        "HTG", // Haitian gourde
        "HUF", // Hungarian forint
        "IDR", // Indonesian rupiah
        "ILS", // Israeli new shekel
        "INR", // Indian rupee
        "IQD", // Iraqi dinar
        "IRR", // Iranian rial
        "ISK", // Icelandic króna
        "JMD", // Jamaican dollar
        "JOD", // Jordanian dinar
        "JPY", // Japanese yen
        "KES", // Kenyan shilling
        "KGS", // Kyrgyzstani som
        "KHR", // Cambodian riel
        "KMF", // Comorian franc
        "KPW", // North Korean won
        "KRW", // South Korean won
        "KWD", // Kuwaiti dinar
        "KYD", // Cayman Islands dollar
        "KZT", // Kazakhstani tenge
        "LAK", // Lao kip
        "LBP", // Lebanese pound
        "LKR", // Sri Lankan rupee
        "LRD", // Liberian dollar
        "LSL", // Lesotho loti
        "LYD", // Libyan dinar
        "MAD", // Moroccan dirham
        "MDL", // Moldovan leu
        "MGA", // Malagasy ariary
        "MKD", // Macedonian denar
        "MMK", // Burmese kyat
        "MNT", // Mongolian tögrög
        "MOP", // Macanese pataca
        "MRU", // Mauritanian ouguiya
        "MUR", // Mauritian rupee
        "MVR", // Maldivian rufiyaa
        "MWK", // Malawian kwacha
        "MXN", // Mexican peso
        "MYR", // Malaysian ringgit
        "MZN", // Mozambican metical
        "NAD", // Namibian dollar
        "NGN", // Nigerian naira
        "NIO", // Nicaraguan córdoba
        "NOK", // Norwegian krone
        "NPR", // Nepalese rupee
        "NZD", // New Zealand dollar
        "OMR", // Omani rial
        "PAB", // Panamanian balboa
        "PEN", // Peruvian sol
        "PGK", // Papua New Guinean kina
        "PHP", // Philippine peso
        "PKR", // Pakistani rupee
        "PLN", // Polish złoty
        "PYG", // Paraguayan guaraní
        "QAR", // Qatari riyal
        "RON", // Romanian leu
        "RSD", // Serbian dinar
        "RUB", // Russian ruble
        "RWF", // Rwandan franc
        "SAR", // Saudi riyal
        "SBD", // Solomon Islands dollar
        "SCR", // Seychellois rupee
        "SDG", // Sudanese pound
        "SEK", // Swedish krona
        "SGD", // Singapore dollar
        "SHP", // Saint Helena pound
        "SLL", // Sierra Leonean leone
        "SOS", // Somali shilling
        "SRD", // Surinamese dollar
        "SSP", // South Sudanese pound
        "STN", // São Tomé and Príncipe dobra
        "SVC", // Salvadoran colón
        "SYP", // Syrian pound
        "SZL", // Swazi lilangeni
        "THB", // Thai baht
        "TJS", // Tajikistani somoni
        "TMT", // Turkmenistan manat
        "TND", // Tunisian dinar
        "TOP", // Tongan paʻanga
        "TRY", // Turkish lira
        "TTD", // Trinidad and Tobago dollar
        "TWD", // New Taiwan dollar
        "TZS", // Tanzanian shilling
        "UAH", // Ukrainian hryvnia
        "UGX", // Ugandan shilling
        "USD", // United States dollar
        "UYU", // Uruguayan peso
        "UZS", // Uzbekistani soʻm
        "VES", // Venezuelan bolívar soberano
        "VND", // Vietnamese đồng
        "VUV", // Vanuatu vatu
        "WST", // Samoan tālā
        "XAF", // Central African CFA franc
        "XCD", // East Caribbean dollar
        "XOF", // West African CFA franc
        "XPF", // CFP franc
        "YER", // Yemeni rial
        "ZAR", // South African rand
        "ZMW", // Zambian kwacha
    };
    //#endregion

    /**
     * <p>Lấy url tới Conversion Currency xe.com</p>
     *
     * @param amount Mô tả cho tham số thứ nhất
     * @param from Đơn vị tiền tệ cần đổi
     * @param to Đơn vị tiền tệ được đổi
     * @return Mô tả về giá trị trả về
     */
    public static String UseXE(double amount, String from, String to)
    {
        String fromUpper = from.toUpperCase();
        
        String toUpper = to.toUpperCase();

        String url = String.format("https://www.xe.com/currencyconverter/convert/?Amount=%s&From=%s&To=%s", amount, fromUpper, toUpper);
        
        if (IsValidCurrency(fromUpper) && IsValidCurrency(toUpper) && amount >= 1)
            return url;
        else if (amount < 1)
            return "So tien khong duoc nho hon 0!";

        return "Khong co loai tien te nay!";
    }

    public static String UseWise(String amount, String from, String to)
    {
        String fromUpper = from.toUpperCase();
        
        String toUpper = to.toUpperCase();
        
        if (IsValidCurrency(fromUpper) && IsValidCurrency(toUpper))
        {
            String fromLower = from.toLowerCase();
        
            String toLower = to.toLowerCase();

            String url = String.format("https://wise.com/gb/currency-converter/%s-to-%s-rate?amount=%s", fromLower, toLower, amount);            
            
            return url;
        }

        return "Khong co loai tien te nay!";
    }

    public static String UseForbes(double amount, String from, String to)
    {
        String fromUpper = from.toUpperCase();
        
        String toUpper = to.toUpperCase();
        
        if (IsValidCurrency(fromUpper) && IsValidCurrency(toUpper) && amount >= 1)
        {
            String fromLower = from.toLowerCase();
        
            String toLower = to.toLowerCase();

            String url = String.format("https://www.forbes.com/advisor/money-transfer/currency-converter/%s-%s/?amount=%s", fromLower, toLower, amount);
            
            return url;
        }
        else if (amount < 1)
            return "So tien khong duoc nho hon 0!";

        return "Khong co loai tien te nay!";
    }

    public static String UseConvertWorld(int amount, String from, String to)
    {
        String url = String.format("https://www.convertworld.com/en/currency/{british-pound}/%s-to-%s.html#%s", 
        /* nameCurrency, */ from, to, amount);
        
        if (IsValidCurrency(from) && IsValidCurrency(to) && amount >= 0)
            return url;            
        else if (amount < 0)
            return "So tien khong duoc am!";

        return "Khong co loai tien te nay!";
    }

    private static boolean IsValidCurrency(String input)
    {
        for (String currency : worldCurrencies) 
        {
            if (currency.equals(input)) 
            {
                return true;
            }
        }
        return false;
    }
}
