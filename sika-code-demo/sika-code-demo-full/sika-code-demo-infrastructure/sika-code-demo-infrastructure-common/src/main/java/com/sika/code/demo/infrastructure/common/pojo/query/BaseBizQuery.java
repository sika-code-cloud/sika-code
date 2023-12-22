package com.sika.code.demo.infrastructure.common.pojo.query;

import com.sika.code.core.base.pojo.query.PageQuery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseBizQuery extends PageQuery<Long> {
}
