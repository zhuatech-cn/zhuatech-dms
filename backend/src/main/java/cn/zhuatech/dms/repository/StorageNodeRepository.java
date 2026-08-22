/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.dms.repository; import cn.zhuatech.dms.model.StorageNode; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface StorageNodeRepository extends JpaRepository<StorageNode,Long>{List<StorageNode> findAllByOrderByCodeAsc();long countByStatus(StorageNode.Status status);}
