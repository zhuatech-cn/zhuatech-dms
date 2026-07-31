/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.dms.model;
import jakarta.persistence.*; import java.time.LocalDate;
@Entity @Table(name="dms_document") public class Document extends BaseEntity {
    public enum Status { RELEASED, RUNNING, PAUSED, COMPLETED }
    @Column(nullable=false,unique=true,length=32) private String orderNo; @Column(nullable=false,length=40) private String productCode; @Column(nullable=false,length=120) private String productName;
    @ManyToOne(optional=false,fetch=FetchType.LAZY) private Library library; @Column(nullable=false) private int plannedQty; @Column(nullable=false) private int completedQty; @Column(nullable=false) private int defectQty;
    @Column(nullable=false) private LocalDate dueDate; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Status status; @Column(length=40) private String batchNo;
    protected Document(){} public Document(String orderNo,String productCode,String productName,Library library,int plannedQty,int completedQty,int defectQty,LocalDate dueDate,Status status,String batchNo){this.orderNo=orderNo;this.productCode=productCode;this.productName=productName;this.library=library;this.plannedQty=plannedQty;this.completedQty=completedQty;this.defectQty=defectQty;this.dueDate=dueDate;this.status=status;this.batchNo=batchNo;}
    public String getOrderNo(){return orderNo;} public String getProductCode(){return productCode;} public String getProductName(){return productName;} public Library getLibrary(){return library;} public int getPlannedQty(){return plannedQty;} public int getCompletedQty(){return completedQty;} public int getDefectQty(){return defectQty;} public LocalDate getDueDate(){return dueDate;} public Status getStatus(){return status;} public String getBatchNo(){return batchNo;} public void report(int good,int defect){completedQty+=good;defectQty+=defect;if(completedQty>=plannedQty)status=Status.COMPLETED;else status=Status.RUNNING;}
}
