package com.sparrow.poi;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.*;

/**
 * @author: zh_harry@163.com
 * @date: 2019-04-15 14:04
 * @description:
 */
public class WordTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("钱怡", "钱钱2222");
        replaceAndGenerateWord("/Users/harry/install-workspace/简历/模板/yingjiesheng.doc"
                , "/Users/harry/install-workspace/简历/模板/yingjiesheng-desc.doc",
                map);

        map.clear();
        map.put("钱怡", "钱钱2222");
        replaceAndGenerateWord("/Users/harry/install-workspace/简历/模板/lagou.doc"
                , "/Users/harry/install-workspace/简历/模板/lagou-desc.doc",
                map);


        map.clear();
        map.put("钱怡", "钱钱2222");
        replaceAndGenerateWord("/Users/harry/install-workspace/简历/模板/guangxirencai.doc"
                , "/Users/harry/install-workspace/简历/模板/guangxirencai-desc.doc",
                map);


        map.clear();
        map.put("钱怡", "钱钱2222");
        replaceAndGenerateWord("/Users/harry/install-workspace/简历/模板/hr800.doc"
                , "/Users/harry/install-workspace/简历/模板/hr800-desc.doc",
                map);





    }

    private static Map<Integer, Picture> pictureMap = new HashMap<>();

    private static Set<Range> deletingRuns = new HashSet<>();

    public static boolean replaceAndGenerateWord(String srcPath, String destPath, Map<String, String> map) {
        String[] sp = srcPath.split("\\.");
        String[] dp = destPath.split("\\.");
        if ((sp.length > 0) && (dp.length > 0)) {// 判断文件有无扩展名
// 比较文件扩展名
            if (sp[sp.length - 1].equalsIgnoreCase("docx")) {
                try {
                    XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath));
// 替换段落中的指定文字


                    Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
                    while (itPara.hasNext()) {
                        XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (int i = 0; i < runs.size(); i++) {

                            String oneparaString = runs.get(i).getText(runs.get(i).getTextPosition());
                            if (oneparaString != null) {
                                for (Map.Entry<String, String> entry : map.entrySet()) {
                                    oneparaString = oneparaString.replace(entry.getKey(), entry.getValue());
                                }
                                runs.get(i).setText(oneparaString, 0);
                            }
                        }
                    }


// 替换表格中的指定文字
                    Iterator<XWPFTable> itTable = document.getTablesIterator();
                    while (itTable.hasNext()) {
                        XWPFTable table = (XWPFTable) itTable.next();
                        int rcount = table.getNumberOfRows();
                        for (int i = 0; i < rcount; i++) {
                            XWPFTableRow row = table.getRow(i);
                            List<XWPFTableCell> cells = row.getTableCells();
                            for (XWPFTableCell cell : cells) {
                                String cellTextString = cell.getText();
                                for (Map.Entry<String, String> e : map.entrySet()) {
                                    if (cellTextString.contains(e.getKey()))
                                        cellTextString = cellTextString.replace(e.getKey(), e.getValue());
                                }
                                cell.removeParagraph(0);
                                cell.setText(cellTextString);
                            }
                        }
                    }
                    FileOutputStream outStream = null;
                    outStream = new FileOutputStream(destPath);
                    document.write(outStream);
                    outStream.close();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }


            } else
// doc只能生成doc，如果生成docx会出错
                if ((sp[sp.length - 1].equalsIgnoreCase("doc"))
                        && (dp[dp.length - 1].equalsIgnoreCase("doc"))) {
                    HWPFDocument document = null;
                    try {
                        document = new HWPFDocument(new FileInputStream(srcPath));


                        PicturesTable picturesTable = document.getPicturesTable();
                        List<Picture> pictures = picturesTable.getAllPictures();


                        for (int i = 0; i < pictures.size(); i++) {

                            Picture picture = pictures.get(i);
                            pictureMap.put(picture.getStartOffset(), picture);
                            System.out.println(picture.getStartOffset());
                            //document.delete(picture.getStartOffset(),picture.getSize());
                            picture.writeImageContent(new FileOutputStream(new File("/Users/harry/install-workspace/简历/模板/lagou/p" + i + ".jpg")));
                        }
                        document.getHeaderStoryRange().delete();
                        document.getFootnoteRange().delete();
                        document.getCommentsRange().delete();
                        document.getEndnoteRange().delete();
                        document.getMainTextboxRange().delete();

                        System.out.println(document.getRange().numParagraphs());
                        for (int i = 0; i < 1; i++) {
                            Paragraph paragraph = document.getRange().getParagraph(i);

                            System.out.println(document.getRange().getParagraph(i));
                            System.out.println(paragraph.numSections());
                            //document.getRange().getParagraph(i).delete();
                        }
                        System.out.println(document.getRange().numSections());
                        for (int i = 0; i < document.getRange().numSections(); i++) {
                            System.out.println(document.getRange().getSection(i));
                            System.out.println(document.getRange().getSection(i).numParagraphs());
                            //document.getRange().getSection(i).delete();
                        }
                        System.out.println(document.getRange().numCharacterRuns());

                        list(document.getRange(), document);

                        //deletingRuns.iterator().next().delete();

                        Range range = document.getRange();


                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            range.replaceText(entry.getKey(), entry.getValue());
                            System.out.println(range.type());
                        }
                        FileOutputStream outStream = null;
                        outStream = new FileOutputStream(destPath);
                        document.write(outStream);
                        outStream.close();
                        return true;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return false;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                } else {
                    return false;
                }
        } else {
            return false;
        }
    }

    private static void list(Range parent, HWPFDocument document) {
        for(int i=0;i<parent.numSections();i++){
            Section section =parent.getSection(i);
            for(int j=0;j<section.numParagraphs();j++){
                Paragraph paragraph=section.getParagraph(j);

                for(int k=0;k<paragraph.numCharacterRuns();k++){
                    CharacterRun run=paragraph.getCharacterRun(k);
                    if(document.getPicturesTable().hasPicture(run)){
                        Picture picture=document.getPicturesTable().extractPicture(run,true);
                        run.delete();
                    }
                }
            }
        }
    }

}
