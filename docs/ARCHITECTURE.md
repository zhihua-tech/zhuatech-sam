# 架构说明

```text
Vue 3 管理端 / 响应式 H5
          │ HTTP / JSON
Spring Security → Controller → Service → Spring Data JPA → MySQL 8
                                  │
                     许可证头寸与合规规则
```

当前版本以单体分层架构呈现软件资产管理主线。`LicensePositionService` 计算采购权益、有效需求、回收空间和财务敞口，`WorkItem` 可继续扩展为发现、归一、核算、处置及续费任务。生产化建议接入终端发现、采购合同、厂商产品使用权规则库和审批系统，并保留完整证据链。
