/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.sam.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LicenseComplianceAttestationServiceTest {
    private final LicenseComplianceAttestationService service = new LicenseComplianceAttestationService();
    @Test void attestsControlledLicensePosition() {
        var r = service.assess(new LicenseComplianceAttestationService.Request("ERP", true, true, 100, 95, 0, false, true, true));
        assertThat(r.decision()).isEqualTo(LicenseComplianceAttestationService.Decision.ATTEST);
    }
    @Test void blocksOverDeploymentAndMissingEvidence() {
        var r = service.assess(new LicenseComplianceAttestationService.Request("DB", false, false, 10, 20, 0, true, false, true));
        assertThat(r.decision()).isEqualTo(LicenseComplianceAttestationService.Decision.BLOCKED);
        assertThat(r.blockers()).hasSize(5);
    }
    @Test void remediatesUnidentifiedInstallsAndTerms() {
        var r = service.assess(new LicenseComplianceAttestationService.Request("IDE", true, true, 20, 18, 2, false, true, false));
        assertThat(r.decision()).isEqualTo(LicenseComplianceAttestationService.Decision.REMEDIATE);
        assertThat(r.actions()).hasSize(2);
    }
}
