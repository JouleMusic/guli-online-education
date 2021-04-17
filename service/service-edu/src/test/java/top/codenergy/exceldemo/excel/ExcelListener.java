package top.codenergy.exceldemo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<DemoData> {
    /**
     * When analysis one row trigger invoke function.
     * 一行一行读取数据
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        System.out.println("***" + data);
    }

    /**
     * 读取表头内容
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头：" + headMap);
    }

    /**
     * if have something to do after all analysis
     * 读取完成后可以做一些事情
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
