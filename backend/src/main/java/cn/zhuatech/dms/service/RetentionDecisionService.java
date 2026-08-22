/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.dms.service;
import jakarta.validation.constraints.*; import org.springframework.stereotype.Service; import java.util.ArrayList; import java.util.List;
@Service public class RetentionDecisionService {
    public Result evaluate(Request r){
        int remaining=r.retentionPeriodDays()-r.documentAgeDays();
        String decision=r.legalHold()||r.pendingApproval()?"HOLD":r.businessActive()||remaining>0?"KEEP":r.containsPersonalData()?"REVIEW":"DISPOSE";
        List<String> controls=new ArrayList<>(); if(r.legalHold())controls.add("命中法律保全，禁止删除或修改"); if(r.pendingApproval())controls.add("等待审批流程结束");
        if(r.businessActive())controls.add("仍被业务流程引用"); if(r.containsPersonalData())controls.add("处置前执行个人信息合规复核"); if("DISPOSE".equals(decision))controls.add("生成处置清单并保留审计证据");
        return new Result(remaining,Math.max(0,-remaining),decision,controls);
    }
    public record Request(@NotBlank String documentNo,@Min(0) int documentAgeDays,@Positive int retentionPeriodDays,
        boolean legalHold,boolean pendingApproval,boolean businessActive,boolean containsPersonalData){}
    public record Result(int remainingRetentionDays,int overdueDays,String decision,List<String> controls){}
}

