/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.sam;

import cn.zhuatech.sam.service.LicenseReclamationService;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LicenseReclamationServiceTests {
    private final LicenseReclamationService service = new LicenseReclamationService();

    @Test void reclaimsMaterialDormantLicensePool() {
        var result = service.evaluate(new LicenseReclamationService.Request(
            "DESIGN-SUITE", 100, 65, 25, 5, new BigDecimal("3600"), 45, false));
        assertEquals(25, result.reclaimableLicenses());
        assertEquals(new BigDecimal("90000.00"), result.potentialAnnualSavings());
        assertEquals("RECLAIM", result.decision());
    }

    @Test void reviewsCriticalApplicationBeforeReclamation() {
        var result = service.evaluate(new LicenseReclamationService.Request(
            "ERP-CORE", 50, 35, 10, 5, new BigDecimal("5000"), 30, true));
        assertEquals("REVIEW", result.decision());
    }
}
