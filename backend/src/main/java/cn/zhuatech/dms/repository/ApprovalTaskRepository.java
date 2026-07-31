/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.dms.repository; import cn.zhuatech.dms.model.ApprovalTask; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface ApprovalTaskRepository extends JpaRepository<ApprovalTask,Long>{List<ApprovalTask> findTop10ByOrderByIdDesc();long countByResult(ApprovalTask.Result result);}
