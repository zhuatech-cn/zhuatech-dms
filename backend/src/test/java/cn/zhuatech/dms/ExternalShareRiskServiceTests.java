/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.dms;

import cn.zhuatech.dms.service.ExternalShareRiskService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExternalShareRiskServiceTests {
    private final ExternalShareRiskService service = new ExternalShareRiskService();

    @Test
    void blocksRestrictedPersonalDataShare() {
        var result = service.evaluate(new ExternalShareRiskService.Request(
            "DOC-2026-1001", "RESTRICTED", 2, 24, true, true, false, true));

        assertEquals(70, result.riskScore());
        assertEquals("BLOCK", result.decision());
        assertFalse(result.shareLinkAllowed());
    }

    @Test
    void allowsProtectedPublicDocumentShare() {
        var result = service.evaluate(new ExternalShareRiskService.Request(
            "DOC-2026-1002", "PUBLIC", 3, 48, true, true, false, false));

        assertEquals("ALLOW", result.decision());
        assertTrue(result.shareLinkAllowed());
    }
}
