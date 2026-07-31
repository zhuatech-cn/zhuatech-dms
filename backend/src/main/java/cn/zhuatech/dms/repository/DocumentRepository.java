/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.dms.repository; import cn.zhuatech.dms.model.Document; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface DocumentRepository extends JpaRepository<Document,Long>{List<Document> findAllByOrderByDueDateAsc();List<Document> findByLibraryCodeOrderByDueDateAsc(String code);long countByStatus(Document.Status status);}
