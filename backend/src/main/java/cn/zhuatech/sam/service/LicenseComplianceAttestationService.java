/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.sam.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class LicenseComplianceAttestationService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (!request.publisherNormalized()) blockers.add("软件发行商与产品版本尚未标准化");
        if (!request.proofOfEntitlementAvailable()) blockers.add("许可证权益证明缺失");
        if (request.prohibitedEditionInstalled()) blockers.add("发现禁止安装的软件版本");
        if (request.activeInstallations() > request.entitledInstallations()) blockers.add("活跃安装数超过采购权益");
        if (!request.auditOwnerAssigned()) blockers.add("未指定许可证合规责任人");
        if (!blockers.isEmpty()) {
            actions.add("暂停合规签证，回收超配授权或补齐采购与治理证据");
            return new Assessment(Decision.BLOCKED, blockers, actions);
        }
        if (request.unidentifiedInstallations() > 0 || !request.contractTermsReviewed()) {
            if (request.unidentifiedInstallations() > 0) actions.add("识别未归属安装并确认责任设备");
            if (!request.contractTermsReviewed()) actions.add("复核虚拟化、灾备和间接访问条款");
            return new Assessment(Decision.REMEDIATE, blockers, actions);
        }
        actions.add("签发本期合规证明并冻结安装、权益和合同快照");
        return new Assessment(Decision.ATTEST, blockers, actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String softwareProduct, boolean publisherNormalized,
                          boolean proofOfEntitlementAvailable,
                          @Min(0) int entitledInstallations, @Min(0) int activeInstallations,
                          @Min(0) int unidentifiedInstallations, boolean prohibitedEditionInstalled,
                          boolean auditOwnerAssigned, boolean contractTermsReviewed) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Assessment(Decision decision, List<String> blockers, List<String> actions) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Decision { ATTEST, REMEDIATE, BLOCKED }
}
