/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.dms.dto;
import jakarta.validation.constraints.*; import java.time.*; import java.util.List;
public final class DmsDto { private DmsDto(){}
    public record Metric(String label,String value,String hint,String tone){}
    public record DocumentView(Long id,String orderNo,String productCode,String productName,String library,String workshop,int plannedQty,int completedQty,int defectQty,LocalDate dueDate,String status,String batchNo,int progress){}
    public record StorageNodeView(String code,String name,String library,String status,int oee,LocalDateTime lastHeartbeat){}
    public record ApprovalTaskView(String approvalTaskNo,String orderNo,String productName,String approvalTaskType,int sampleQty,int defectQty,String result,String inspector){}
    public record Dashboard(List<Metric> metrics,List<DocumentView> documents,List<StorageNodeView> storageNode,List<ApprovalTaskView> approvalTasks){}
    public record ReportRequest(@NotBlank String operationName,@Positive int goodQty,@PositiveOrZero int defectQty,@Size(max=200) String remark){}
    public record ReportResult(String orderNo,int completedQty,int defectQty,int progress,String status){}
}
