package scheduler;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Excel {
    static List<String> headers = new ArrayList<>();
    public static void convert() {
        convertDataToHtml("D:\\2019 POCs\\AkbarTravels\\AkbarTravels\\ExcelUploadFromTravels\\converted_data.html");
        List<Transitvisavia> lstData = getDataFromHtmlFile("converted_data.html");
        Transitvisavia newData  = new Transitvisavia();
        newData.setId(lstData.size()+1);
        newData.setFromIndiaToCountry("USA");
        newData.setViaCountry("UK");
        newData.setDisplayMessage("This is a new  message data from automation.");
        lstData.add(newData);
        writeDataToExcel("D:\\2019 POCs\\AkbarTravels\\AkbarTravels\\ExcelUploadFromTravels\\transitvisavia.xls",lstData);
    }

    public static void convertDataToHtml(String destPath) {
        File folder = new File("D:\\2019 POCs\\AkbarTravels\\AkbarTravels\\ExcelDownloadedFromTravels");
        File[] files = folder.listFiles();
        File fileToRead = null;
        for (File file : files) {
            String fName = FilenameUtils.getBaseName(file.getName());
            if (fName.equalsIgnoreCase("transitvisavia")) {
                file.setReadable(true);
                fileToRead = file;
            }
        }//End for
        File outfile = new File(destPath);
        try (FileInputStream instream = new FileInputStream(fileToRead);
             FileOutputStream outstream = new FileOutputStream(outfile))
        {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = instream.read(buffer)) > 0) {
                outstream.write(buffer, 0, length);
            }
            System.out.println("Data copied successfully to html!!");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static List<Transitvisavia> getDataFromHtmlFile(String filePath) {
        //Read HTML
        List<Transitvisavia> lstData = new ArrayList<Transitvisavia>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder contentBuilder = new StringBuilder();
            String str = "";
            while (true) {
                try {
                    if (!((str = bufferedReader.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                contentBuilder.append(str);
            }
            //Data in string now

            int index = contentBuilder.indexOf("<!DOCTYPE");
            contentBuilder.replace(0, index, "");

            Document doc = Jsoup.parse(contentBuilder.toString());
            Element table = doc.getElementById("gvList");
            Elements rows = table.getElementsByTag("tr");

            Element headerRow = rows.get(0);

            for (Element th : headerRow.getElementsByTag("th")) {
                headers.add(th.text().trim());
            }

            for (int i = 1; i < rows.size(); i++) {
                //Start of row
                Transitvisavia data = new Transitvisavia();
                Elements cells = rows.get(i).getElementsByTag("td");
                data.setId(Integer.parseInt(cells.get(0).text()));
                data.setFromIndiaToCountry(cells.get(1).text());
                data.setViaCountry(cells.get(2).text());
                data.setDisplayMessage(cells.get(3).text());
                lstData.add(data);
            }
        } catch (Throwable th) {
            th.printStackTrace();

        }
        return lstData;
    }

    public static void writeDataToExcel(String filePath,List<Transitvisavia> lstData) {
        try(FileOutputStream outstream = new FileOutputStream(filePath)){
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
            HSSFSheet sheet = hssfWorkbook.createSheet("transitvisavia");

            int lastCreatedRow = -1;
            HSSFRow row = sheet.createRow(++lastCreatedRow);

            int cellIndex = 0;
            for (String header : headers) {
                row.createCell(cellIndex).setCellValue(header);
                cellIndex++;
            }

            //Write Data to excel
            for (Transitvisavia data : lstData) {
                row = sheet.createRow(++lastCreatedRow);
                row.createCell(0).setCellValue(data.getId());
                row.createCell(1).setCellValue(data.getFromIndiaToCountry());
                row.createCell(2).setCellValue(data.getViaCountry());
                row.createCell(3).setCellValue(data.getDisplayMessage());
            }

            hssfWorkbook.write(outstream);
            hssfWorkbook.close();

            System.out.println("================================================");
            System.out.println("DATA WRITTEN TO EXCEL SUCCESSFULLY");
            System.out.println("Total row created: " + lastCreatedRow +"");
            System.out.println("================================================");
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Excel.convert();
    }

}
