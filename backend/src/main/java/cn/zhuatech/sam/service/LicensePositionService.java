/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.sam.service;
import jakarta.validation.constraints.*; import org.springframework.stereotype.Service; import java.math.*; import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class LicensePositionService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public PositionResult evaluate(PositionRequest request){
        int effectiveDemand=request.assignedLicenses()+request.unrecognizedInstalls(); int surplus=request.purchasedLicenses()-effectiveDemand; int reclaimable=Math.max(0,request.assignedLicenses()-request.activeNinetyDays());
        BigDecimal exposure=BigDecimal.valueOf(Math.max(0,-surplus)).multiply(request.unitCost()).setScale(2,RoundingMode.HALF_UP);
        String status=surplus<0?"NON_COMPLIANT":reclaimable>0||request.renewalDays()<120?"OPTIMIZE":"COMPLIANT";
        List<String> actions=new ArrayList<>(); if(surplus<0)actions.add("补购许可证或移除超额安装"); if(reclaimable>0)actions.add("回收连续九十天未活跃的授权"); if(request.renewalDays()<120)actions.add("在续费前完成权益与实际使用复核"); if(actions.isEmpty())actions.add("许可证权益与使用需求保持平衡");
        return new PositionResult(surplus,reclaimable,exposure,status,actions);
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record PositionRequest(@NotNull @Min(0) Integer purchasedLicenses,@NotNull @Min(0) Integer assignedLicenses,@NotNull @Min(0) Integer activeNinetyDays,@NotNull @Min(0) Integer unrecognizedInstalls,@NotNull @DecimalMin("0.00") BigDecimal unitCost,@NotNull @Min(0) Integer renewalDays){}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record PositionResult(int surplus,int reclaimable,BigDecimal financialExposure,String status,List<String> actions){}
}
