/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.sam.controller;

import cn.zhuatech.sam.common.ApiResponse;
import cn.zhuatech.sam.service.LicenseComplianceAttestationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise/sam")
public class LicenseComplianceAttestationController {
    private final LicenseComplianceAttestationService service;
    public LicenseComplianceAttestationController(LicenseComplianceAttestationService service) { this.service = service; }
    @PostMapping("/license-compliance-attestation")
    public ApiResponse<LicenseComplianceAttestationService.Assessment> assess(
        @Valid @RequestBody LicenseComplianceAttestationService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
