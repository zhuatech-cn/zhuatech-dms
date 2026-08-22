/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.dms.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="dms_approval_task") public class ApprovalTask extends BaseEntity {
    public enum Result { PENDING, PASSED, FAILED }
    @Column(nullable=false,unique=true,length=32) private String approvalTaskNo; @ManyToOne(optional=false,fetch=FetchType.LAZY) private Document document;
    @Column(nullable=false,length=30) private String approvalTaskType; @Column(nullable=false) private int sampleQty; @Column(nullable=false) private int defectQty; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Result result;
    @Column(length=50) private String inspector; @Column(nullable=false) private LocalDateTime createdAt;
    protected ApprovalTask(){} public ApprovalTask(String approvalTaskNo,Document document,String approvalTaskType,int sampleQty,int defectQty,Result result,String inspector){this.approvalTaskNo=approvalTaskNo;this.document=document;this.approvalTaskType=approvalTaskType;this.sampleQty=sampleQty;this.defectQty=defectQty;this.result=result;this.inspector=inspector;this.createdAt=LocalDateTime.now();}
    public String getApprovalTaskNo(){return approvalTaskNo;} public Document getDocument(){return document;} public String getApprovalTaskType(){return approvalTaskType;} public int getSampleQty(){return sampleQty;} public int getDefectQty(){return defectQty;} public Result getResult(){return result;} public String getInspector(){return inspector;}
}
