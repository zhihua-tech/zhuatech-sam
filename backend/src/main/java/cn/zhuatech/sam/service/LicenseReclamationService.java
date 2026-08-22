/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.sam.service;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class LicenseReclamationService {
    public Result evaluate(Request request) {
        int excess = Math.max(0, request.assignedLicenses() - request.activeUsers90Days() - request.minimumReserve());
        int reclaimable = Math.min(excess, request.dormantUsers());
        BigDecimal annualSavings = request.annualUnitCost().multiply(BigDecimal.valueOf(reclaimable))
            .setScale(2, RoundingMode.HALF_UP);
        double reclaimRate = request.assignedLicenses() == 0 ? 0 : (double) reclaimable / request.assignedLicenses();
        String decision = request.businessCritical() ? "REVIEW"
            : reclaimRate >= .05 ? "RECLAIM" : request.renewalDays() <= 60 ? "REVIEW" : "HOLD";
        List<String> actions = new ArrayList<>();
        if (reclaimable > 0) actions.add("通知休眠用户确认后回收 " + reclaimable + " 个许可证");
        if (request.renewalDays() <= 60) actions.add("在续费谈判前更新真实使用基线");
        if (request.businessCritical()) actions.add("由应用负责人确认业务连续性与备用账号");
        if (actions.isEmpty()) actions.add("保持当前授权并按月监控活跃度");
        return new Result(request.softwareCode(), reclaimable, round(reclaimRate),
            annualSavings, decision, actions);
    }

    private double round(double value) { return Math.round(value * 10_000D) / 10_000D; }

    public record Request(@NotBlank String softwareCode, @Min(0) int assignedLicenses,
                          @Min(0) int activeUsers90Days, @Min(0) int dormantUsers,
                          @Min(0) int minimumReserve, @DecimalMin("0") BigDecimal annualUnitCost,
                          @Min(0) int renewalDays, boolean businessCritical) {}
    public record Result(String softwareCode, int reclaimableLicenses, double reclaimRate,
                         BigDecimal potentialAnnualSavings, String decision, List<String> actions) {}
}
