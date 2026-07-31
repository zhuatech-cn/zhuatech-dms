# DMS 架构说明

版权所有 © 2026 上海如静知华信息科技有限公司。

Vue 提供文控管理端和文档协作端；Spring Boot 负责版本、审批、权限、存储节点和审计接口；MySQL 保存元数据。二进制文件在企业部署中应进入对象存储，并启用服务端加密、不可变备份和病毒扫描。

角色：`DOCUMENT_MANAGER`、`CONTRIBUTOR`、`QUALITY`、`ADMIN`。
