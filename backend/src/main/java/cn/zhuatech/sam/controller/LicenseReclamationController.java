/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.sam.controller;

import cn.zhuatech.sam.common.ApiResponse;
import cn.zhuatech.sam.service.LicenseReclamationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/sam/insights")
public class LicenseReclamationController {
    private final LicenseReclamationService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public LicenseReclamationController(LicenseReclamationService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/license-reclamation")
    public ApiResponse<LicenseReclamationService.Result> evaluate(
        @Valid @RequestBody LicenseReclamationService.Request request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
