package com.green.springjpa.entity;

import com.green.springjpa.config.relationenum.AbstractEnumCodeConverter;
import com.green.springjpa.config.relationenum.EnumMapperType;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


// SchoolTypeCode[] schoolTypeCodeArray = [ SchoolTypeCode.ELEMENTARY, SchoolTypeCode.MIDDLE, SchoolTypeCode.HIGH ];
@Getter
@RequiredArgsConstructor
public enum SchoolTypeCode implements EnumMapperType {

      ELEMENTARY("00101", "초등학교")
    , MIDDLE("00102", "중학교")
    , HIGH("00103", "고등학교")
    ;

    private final String code;
    private final String value;

    @Converter(autoApply = true) //@Converter(autoApply = true): SchoolTypeCode Enum을 사용하는 Entity는 자동으로 컨버터가 작동한다.
    public static class CodeConverter extends AbstractEnumCodeConverter<SchoolTypeCode> {
        public CodeConverter() {
            super(SchoolTypeCode.class, false);
        }
    }
}
