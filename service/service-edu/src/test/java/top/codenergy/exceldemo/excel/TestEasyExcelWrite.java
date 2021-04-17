package top.codenergy.exceldemo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcelWrite {
    public static void main(String[] args) {
        //1 设置写入文件夹的地址和excel名称
        String filename = "D:/write.xlsx";
        //2 调用方法实现写操作
        //write 的两个参数;文件路径，实体类的class
        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());
    }

    /**
     * 创建方法返回list集合
     */
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy" + i);
            list.add(data);
        }
        return list;
    }
}
