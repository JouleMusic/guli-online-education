package top.codenergy.exceldemo.excel;

import com.alibaba.excel.EasyExcel;

public class TestEasyExcelRead {
    public static void main(String[] args) {
        //1 设置要读取的文件夹的地址和excel名称
        String filename = "D:/write.xlsx";
        //2 调用方法实现读操作
        //read 的两个参数;文件路径，实体类的class
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet("学生列表").doRead();
    }
}
