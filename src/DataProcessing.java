import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DataProcessing 
{
    public static String fromInputStr;
    public static String toOutputStr;
    public static String fromUnit;
    public static String toUnit;
    
    private static Document doc;
    private static String fullText;

    public static void GetResultFromURL()
    {
        try
        {
            doc = Jsoup.connect(CurrencyConvesionURL.UseWise(fromInputStr, fromUnit, toUnit)).get();
            fullText = doc.body().text();
            StartDataProcessingFromWise();        
        }
        catch (IOException eWise)
        {
            // try 
            // {
            //     doc = Jsoup.connect(CurrencyConvesionURL.UseXE(fromInputStr, fromUnit, toUnit)).get();
            //     fullText = doc.body().text();
            //     //StartDataProcessing();
            //     System.out.println("Use XE, algorithm other");
            // } 
            // catch (IOException eXE) 
            // {
            //     try 
            //     {
            //         doc = Jsoup.connect(CurrencyConvesionURL.UseForbes(fromInputStr, fromUnit, toUnit)).get();
            //         fullText = doc.body().text();
            //         //StartDataProcessing();
            //         System.out.println("Use Forbes, algorithm other");
            //     } 
            //     catch (IOException eForbes) 
            //     {
            //         //chưa biết site nào khác...
            //     }
            // }
        }
    }

    private static void StartDataProcessingFromWise()
    {
        String retrived = "";
        String strStart = "real exchange rate Amount";
        String strEnd = " Mid-market exchange";

        int start = fullText.indexOf(strStart) + "real exchange rate Amount ".length();
        int end = fullText.indexOf(strEnd);

        if (start < end && start != -1) 
        {
            retrived = fullText.substring(start, end);
        }
        System.out.println(retrived);

        Pattern p = ValidNumbers();
        Matcher m = p.matcher(retrived);

        int count = 0;
        while (m.find()) 
        {
            count++;
            if (count == 2)
                toOutputStr = m.group();        
        }

        System.out.println(toOutputStr);
    }
    /**
     *  \\d+: \\d tương ứng với một chữ số từ 0 đến 9. Dấu + sau \\d có nghĩa là phần tử trước nó (ở đây là \\d) có thể xuất hiện một hoặc nhiều lần. Ví dụ, chuỗi “42” sẽ khớp với mẫu \\d+.
        (,\\d{3})*: Dấu ngoặc đơn tạo một nhóm. Trong nhóm này, ,\\d{3} tương ứng với một dấu phẩy theo sau là ba chữ số. 
        Dấu * sau ngoặc đơn có nghĩa là nhóm có thể xuất hiện không hoặc nhiều lần.
        \\.?: Dấu chấm . thường được sử dụng để khớp với bất kỳ ký tự nào trong regex. Tuy nhiên, khi nó được ghi là \\. thì nó chỉ khớp với ký tự dấu chấm. 
        Dấu hỏi ? sau \\. có nghĩa là phần tử trước nó (ở đây là \\.) có thể xuất hiện không hoặc một lần.
        \\d*: Tương tự như \\d+, nhưng dấu * sau \\d có nghĩa là phần tử trước nó (ở đây là \\d) có thể xuất hiện không hoặc nhiều lần.
        Vì vậy, biểu thức chính quy (\\d+(,\\d{3})*\\.?\\d*) sẽ khớp với các chuỗi số 
        có dạng như “123456”, “123,456”, “123.456”, “123,456.789”, và tương tự
     * @return
     */
    private static Pattern ValidNumbers()
    {
        return Pattern.compile("(\\d+(,\\d{3})*\\.?\\d*)");
    }

    /*private static void GiaiThuatSai()
    {
        try
        {
            Document doc = Jsoup.connect(CurrencyConvesionURL.UseWise(fromInput, fromUnit, toUnit)).get();
            String fullText = doc.body().text();
            
            String retrived = "";
            String strStart;
            try 
            {
                // Kiểm tra có phải là số nguyên không
                Integer.parseInt(UI.GetFromInput());
                strStart = String.format("Amount %s", (int)fromInputStr);
            } 
            catch (NumberFormatException e) 
            {
                // is double
                strStart = String.format("Amount %s", fromInputStr);
            }
            //khi kết quả Wise trả về số có dấu , (như 100,000 thay vì 100000)            
            String strEnd = " Mid-market exchange";

            int start = fullText.indexOf(strStart) + "Amount ".length();
            int end = fullText.indexOf(strEnd);

            if (start < end && start != -1) 
            {
                retrived = fullText.substring(start, end);
            }
            System.out.println("retrived: " + retrived);

            Pattern p = Pattern.compile("(\\d+\\.?\\d*)");
            Matcher m = p.matcher(retrived);

            int count = 0;
            while (m.find()) 
            {
                count++;
                if (m.group().contains(","))
                {
                    if (count == 4)
                        toOutputStr = m.group();
                }
                else
                {
                    if (count == 2)
                        toOutputStr = m.group();
                }
            }

            System.out.println(CurrencyConvesionURL.UseWise(fromInput, fromUnit, toUnit));
            System.out.println(String.format("%s %s = %s %s", fromInput, fromUnit, toOutputStr , toUnit));
        }
        catch (IOException ex)
        {
        }
    }*/    
}
