//package com.example.AlatiMNS;
//
//import com.example.AlatiMNS.model.Item;
//import com.example.AlatiMNS.model.Kupac;
//import com.example.AlatiMNS.repository.ItemRepository;
//import com.example.AlatiMNS.repository.KupacRepository;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//    @Autowired
//    private ItemRepository itemRepository;
//
//
//    @Override
//    public void run(String... args) throws Exception{
//        try {
//            // Specify the path to your Excel file
//            FileInputStream file = new FileInputStream(new File("/home/nenad/Downloads/Cenovnici/Eibenstock.xlsx"));
//
//            // Create Workbook instance for XLSX file
//            XSSFWorkbook workbook = new XSSFWorkbook(file);
//
//            // Get the first sheet from the workbook
//            XSSFSheet sheet = workbook.getSheetAt(0);
//
//            // Iterate through each rows one by one
//            int j = 0;
//            for (Row row : sheet) {
//                // Iterate through each cells
//                Item newItem = new Item();
////                if(j < 1){
////                    j++;
////                    continue;
////                }
//                int i=0;
//                for (Cell cell : row) {
//                    // Handle different cell types accordingly
//                    switch(i){
//
//                        case 0:
//                            if(cell.getCellType() == CellType.STRING){
//                                System.out.print(cell.getStringCellValue()+ "\t");
//                                newItem.setNaziv(cell.getStringCellValue());
//                            }else{
//                                newItem.setNaziv("");
//                                System.out.print("(0) NEMA NAZIVA" + "\t");
//                            }
//                            break;
//                        case 1:
//                            if(cell.getCellType() == CellType.STRING)
//                            {
//                                System.out.print(cell.getStringCellValue() + "\t");
//                                newItem.setKodBroj(cell.getStringCellValue());
//                            }else if(cell.getCellType() == CellType.NUMERIC){
//                                Long kodBroj = 0L;
//                                kodBroj = Math.round(cell.getNumericCellValue());
//                                newItem.setKodBroj(String.valueOf(kodBroj));
//                                System.out.print(kodBroj + "\t");
//                            }else{
//                                System.out.print("(1)NEMA KOD BROJ" + "\t");
//                            }
//                            break;
//                        case 2:
//                            if(cell.getCellType() == CellType.NUMERIC){
//                                float cena = 0F;
//                                cena = (float)cell.getNumericCellValue();
//                                newItem.setCena(cena);
//                                System.out.print(cena + "\t");
//                            }else{
//                                //newItem.setCena(0F);
//                                System.out.print("(2) NEMA CENE" + "\t");
//                                String cleanedInput = cell.getStringCellValue().replaceAll("[^0-9]", "");
//
//                                // Parse to integer
//                                int number = Integer.parseInt(cleanedInput);
//                                number /= 100;
//                                newItem.setCena((float)number);
//
//                                System.out.println(newItem.getCena()); // Output: 58080
//                            }
//                            break;
//                        default:
//                            System.out.print("Invalid Data Type\t");
//                    }
//                    newItem.setModel("");
//                    newItem.setEanKod("");
//                    //itemRepository.save(newItem);
//                    i++;
//                }
//                System.out.println();
//            }
//            // Close the file input stream
//            file.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
