package com.launchcode.LCBackend.dto.responce;

import com.launchcode.LCBackend.dto.MatchDto;
import com.launchcode.LCBackend.dto.MetaDataDto;

import java.util.List;

public record ReportApiResponce(
        List<MatchDto> matches,
        MetaDataDto meta
) {


}
