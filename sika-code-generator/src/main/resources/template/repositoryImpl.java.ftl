package ${package.Entity};

import ${sikaPackage.Entity}.${sikaEntityBodyName}PO;
import ${sikaPackage.Mapper}.${sikaEntityBodyName}Mapper;
import ${sikaPackage.Repository}.${sikaEntityBodyName}Repository;
import com.cat.reconciliation.platform.infrastructure.db.repository.impl.BaseRepositoryMyBatisPlusImpl;
import org.springframework.stereotype.Repository;
import cn.hutool.core.lang.Assert;

/**
 * <p>
 * ${table.comment!} 持久化操作实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Repository
public class ${entity} extends BaseRepositoryMyBatisPlusImpl<${sikaEntityBodyName}PO${r","} ${sikaPrimaryType}, ${sikaEntityBodyName}Mapper> implements ${sikaEntityBodyName}Repository {

    @Override
    public void verify${sikaEntityBodyName}UnExistById(${sikaPrimaryType} id) {
        Assert.notNull(id, "${table.comment!}主键ID不能为空");
        ${sikaEntityBodyName}PO po = findByPrimaryKey(id);
        Assert.notNull(po, "主键【{}】对应的${table.comment!}数据不存在，请核实", id);
    }
}

