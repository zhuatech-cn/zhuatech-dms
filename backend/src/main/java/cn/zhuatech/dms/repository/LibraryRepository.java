/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.dms.repository; import cn.zhuatech.dms.model.Library; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface LibraryRepository extends JpaRepository<Library,Long>{Optional<Library> findByCode(String code);}
