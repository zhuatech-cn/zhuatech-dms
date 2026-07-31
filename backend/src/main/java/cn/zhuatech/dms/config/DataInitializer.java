/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.dms.config;

import cn.zhuatech.dms.model.*;
import cn.zhuatech.dms.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(LibraryRepository libraries, DocumentRepository documents,
                           StorageNodeRepository storageNodes, ApprovalTaskRepository approvals,
                           UserRepository users, PasswordEncoder encoder) {
        return args -> {
            if (libraries.count() > 0) return;
            Library quality = libraries.save(new Library("LIB-QA", "质量体系文件库", "质量管理部", 5000));
            Library rd = libraries.save(new Library("LIB-RD", "研发技术文档库", "研发中心", 12000));
            Library contract = libraries.save(new Library("LIB-LEG", "合同与法务档案库", "法务部", 3600));

            Document d1 = documents.save(new Document("DOC-QA-2608-018", "SOP-QA-017", "来料检验作业指导书", quality, 42, 36, 1, LocalDate.now().plusDays(1), Document.Status.RUNNING, "V3.2"));
            Document d2 = documents.save(new Document("DOC-RD-2608-021", "SPEC-AX120", "AX120 产品技术规范", rd, 68, 52, 2, LocalDate.now().plusDays(2), Document.Status.RUNNING, "V2.6"));
            Document d3 = documents.save(new Document("DOC-LEG-2608-006", "TPL-NDA-04", "供应商保密协议模板", contract, 18, 0, 0, LocalDate.now().plusDays(4), Document.Status.RELEASED, "V1.4"));
            Document d4 = documents.save(new Document("DOC-QA-2607-015", "MAN-QMS-001", "质量管理手册", quality, 96, 96, 1, LocalDate.now(), Document.Status.COMPLETED, "V5.0"));

            storageNodes.saveAll(List.of(
                new StorageNode("NODE-SH-01", "上海主存储节点", quality, StorageNode.Status.RUNNING, 84),
                new StorageNode("NODE-RD-02", "研发加密存储节点", rd, StorageNode.Status.RUNNING, 73),
                new StorageNode("NODE-ARCH-01", "长期归档存储", contract, StorageNode.Status.IDLE, 68),
                new StorageNode("NODE-DR-01", "异地灾备节点", quality, StorageNode.Status.ALARM, 57)
            ));
            approvals.saveAll(List.of(
                new ApprovalTask("APR-260801-032", d1, "部门会签", 4, 0, ApprovalTask.Result.PASSED, "周妍"),
                new ApprovalTask("APR-260801-011", d2, "技术复核", 3, 1, ApprovalTask.Result.FAILED, "沈清和"),
                new ApprovalTask("APR-260731-018", d4, "发布审批", 5, 0, ApprovalTask.Result.PASSED, "顾清"),
                new ApprovalTask("APR-260802-003", d3, "法务审查", 2, 0, ApprovalTask.Result.PENDING, "陆承")
            ));
            String demo = encoder.encode("Demo@2026");
            users.saveAll(List.of(
                new UserAccount("operator", demo, "许文博", UserAccount.Role.CONTRIBUTOR, "LIB-RD"),
                new UserAccount("planner", demo, "周妍", UserAccount.Role.DOCUMENT_MANAGER, null),
                new UserAccount("quality", demo, "顾清", UserAccount.Role.QUALITY, null),
                new UserAccount("admin", encoder.encode("ZhuaTech@2026"), "系统管理员", UserAccount.Role.ADMIN, null)
            ));
        };
    }
}
