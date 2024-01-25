import javax.swing.SwingUtilities;

public class Program
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            new UI().Start();
        });
    }
    /*private static void TestCases()
    {
        String str = "00.1000.000";
        String str1 = "1000.000";
        String str2 = "1000.3300";
        String str3 = "000.00300";
        String str4 = "0100.00300";
        String str5 = "00.001.00100";
        String str6 = "00.00100.0001000";
        System.out.println(CorrectNumber(str));
        System.out.println(CorrectNumber(str1));
        System.out.println(CorrectNumber(str2));
        System.out.println(CorrectNumber(str3));
        System.out.println(CorrectNumber(str4));
        System.out.println(CorrectNumber(str5));
        System.out.println(CorrectNumber(str6));

        String str7 = "00.0010,0.0001000";
        String str8 = "00.0100,0.0,001000";
        String str9 = "00,00.0,100,0.0,0010,00";

        String new7 = RemovedComma(str7);
        String new8 = RemovedComma(str8);
        String new9 = RemovedComma(str9);

        if (IsInputValid(new7))
            System.out.println(CorrectNumber(new7));

        if (IsInputValid(new8))
            System.out.println(CorrectNumber(new8));
            
        if (IsInputValid(new9))
            System.out.println(CorrectNumber(new9));

        String str10 = "0.0";
        System.out.println(CorrectNumber(str10));
        String str11 = "0.000";
        System.out.println(CorrectNumber(str11));        
        String str12 = "000.000";
        System.out.println(CorrectNumber(str12));
        String str13 = "000.000.00";
        System.out.println(CorrectNumber(str13));
    }
    private static String RemovedComma(String str) // (1)
    {
        if (str.contains(","))
        {
            return str.replace(",", "");
        }
        return str;
    }

    private static boolean IsInputValid(String str) // (2)
    {
        String regex = "^[0-9,\\.]*$";
        boolean match = str.matches(regex);
        return match;
    }

    private static String CorrectNumber(String str) // (3)
    {
        String[] parts = str.split("\\.");

        String result = parts[0].replaceFirst("^0+(?!$)", "");

        if (parts.length > 1)
        {
            if ((Integer.parseInt(parts[0]) > 1 && Integer.parseInt(parts[1]) == 0))
                result += parts[1].replaceAll("0*$", "");
            else if ((Integer.parseInt(parts[0]) == 0 && Integer.parseInt(parts[1]) == 0))
                result = "ZERO NUMBER";               
            else
                result += "." + parts[1].replaceAll("0*$", "");
        }
        return result;
    }
    */
}