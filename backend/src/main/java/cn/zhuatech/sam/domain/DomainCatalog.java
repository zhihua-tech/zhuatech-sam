/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.sam.domain;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component public class DomainCatalog {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String systemName(){return "知华 SAM 软件资产管理平台";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String sceneName(){return "软件发现、许可证权益、分配使用、合规核算与续费优化";}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<SeedItem> seedItems(){return List.of(
        new SeedItem("SAM-20260801-001","设计软件许可证缺口核算","处理中","软件合规组","紧急"),
        new SeedItem("SAM-20260801-002","休眠开发工具授权回收","待处理","终端资产组","高"),
        new SeedItem("SAM-20260801-003","未识别软件安装归属确认","已完成","安全运营组","中"),
        new SeedItem("SAM-20260801-004","年度框架协议续费准备","处理中","采购协同组","高"));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<String> recommendedActions(){return List.of("优先补齐许可证缺口与未识别安装","回收长期未活跃的高成本授权","在续费前核对使用率、单价和合同权益");}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record SeedItem(String recordNo,String title,String status,String owner,String priority){}
}
