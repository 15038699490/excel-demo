package com.yzw.exceldemo.excel;

import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author yzw
 */
public class ExcelObject {
    private static final String Workbook = "Workbook";

    public static void readCheckbox(String path) {
        FileInputStream file = null;
        InputStream istream = null;
        try {
            file = new FileInputStream(new File(path));
            POIFSFileSystem poifs = new POIFSFileSystem(file);
            istream = poifs.createDocumentInputStream(Workbook);
            HSSFRequest req = new HSSFRequest();
            req.addListenerForAllRecords(new EventExample());
            HSSFEventFactory factory = new HSSFEventFactory();
            factory.processEvents(req, istream);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (file != null)
                    file.close();
                if (istream != null)
                    istream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 写个main方法来做一个测试
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("ReadExcelFile");
        //此处为我使用的一个线程用来存储每次复选框的识别列表（目前不需要）
        ObjectList.setCurrentFlag(new ArrayList());
        System.out.println("ReadCheckbox");
        readCheckbox("C:\\Users\\Administrator\\Desktop\\复选框测试.xls");
    }
}


