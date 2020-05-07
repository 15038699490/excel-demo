package com.yzw.exceldemo.excel;

import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SubRecord;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yzw
 */
public class EventExample implements HSSFListener {

    private final static Pattern P = Pattern.compile("\\[sid=0x000A.+?\\[0(\\d),");

    @Override
    public void processRecord(Record record) {

        switch (record.getSid()) {
            case ObjRecord.sid:
                ObjRecord objRec = (ObjRecord) record;

                List<SubRecord> subRecords = objRec.getSubRecords();
                for (SubRecord subRecord : subRecords) {

                    if (!(subRecord instanceof CommonObjectDataSubRecord)) {
                        Matcher m = P.matcher(subRecord.toString());
                        if (m.find()) {
                            String checkBit = m.group(1);
                            if (checkBit.length() == 1) {
                                boolean checked = "1".equals(checkBit);
                                ObjectList.getCurrentFlag().add(checked);

                                System.out.println(ObjectList.getCurrentFlag().size()+"--------"+checked+"-----------"+checkBit);
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
}
