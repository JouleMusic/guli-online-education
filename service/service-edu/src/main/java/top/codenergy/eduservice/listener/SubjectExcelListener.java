package top.codenergy.eduservice.listener;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import top.codenergy.eduservice.entity.EduSubject;
import top.codenergy.eduservice.entity.excel.SubjectData;
import top.codenergy.eduservice.service.EduSubjectService;
import top.codenergy.servicebase.exceptionhandler.GuliException;

/**
 * @author wangyu
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
//    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectExcelListener.class);
//    /**
//     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
//     */
//    private static final int BATCH_COUNT = 5;
//    List<SubjectData> list = new ArrayList<>();

    private EduSubjectService subjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService){
        this.subjectService = eduSubjectService;
    }

    public SubjectExcelListener() {

    }

    /**
     * When analysis one row trigger invoke function.
     * 读取excel内容，逐行读取
     * 每有一条数据来就会调用一次invoke
     * @param data 對應SubjectData
     * @param context
     */
    @Override
    public void invoke(SubjectData data, AnalysisContext context) {
        if (ObjectUtil.isEmpty(data)){
            throw new GuliException(20001,"文件数据为空");
        }
        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //判斷一級分類是否重複
        EduSubject existOneSubject = this.existOneSubject(data.getOneSubjectName());
        // 如果没有相同一级分类（不重复），进行添加
        if (existOneSubject == null){
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(data.getOneSubjectName());
            subjectService.save(existOneSubject);
        }
        //获取一级分类id值，必须要先得到一级分类id值，才能添加二级分类，二级分类的parent_id就是一级分类的id
        String pid = existOneSubject.getId();
        //添加二级分类
        //判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(data.getTwoSubjectName(), pid);
        if (existTwoSubject == null){
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(data.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }

    }

    /**
     * 判斷一級分類不能重複添加
     * @param name
     * @return
     */
    private EduSubject existOneSubject(String name){
        LambdaQueryWrapper<EduSubject> eduSubjectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduSubjectLambdaQueryWrapper.eq(EduSubject::getTitle,name)
                .eq(EduSubject::getParentId,"0");
        EduSubject oneSubject = subjectService.getOne(eduSubjectLambdaQueryWrapper);
        return oneSubject;
    }
    /**
     * 判斷二級分類不能重複添加
     * @param name
     * @return
     */
    private EduSubject existTwoSubject(String name, String pid){
        LambdaQueryWrapper<EduSubject> eduSubjectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduSubjectLambdaQueryWrapper.eq(EduSubject::getTitle,name)
                .eq(EduSubject::getParentId,pid);
        EduSubject twoSubject = subjectService.getOne(eduSubjectLambdaQueryWrapper);
        return twoSubject;
    }

    /**
     * if have something to do after all analysis
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

}
