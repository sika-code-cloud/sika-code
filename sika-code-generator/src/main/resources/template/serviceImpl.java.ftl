package ${package.Entity};

import ${sikaPackage.Repository}.${sikaEntityBodyName}Repository;
import ${sikaPackage.Service}.${sikaEntityBodyName}Service;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${entity} implements ${sikaEntityBodyName}Service {

private final CheckConfigRepository checkConfigRepository;

/**
* 查询对账核心配置
*/
@Override
public CheckConfig queryById(Long id) {
return checkConfigRepository.selectById(id);
}

/**
* 查询对账核心配置列表
*/
@Override
public TableDataInfo<CheckConfig> queryPageList(CheckConfig bo, MpPageQuery pageQuery) {
 LambdaQueryWrapper<CheckConfig> lqw = buildQueryWrapper(bo);
  Page<CheckConfig> result = checkConfigRepository.selectPage(pageQuery.build(), lqw);
   return TableDataInfo.build(result);
   }

   /**
   * 查询对账核心配置列表
   */
   @Override
   public List<CheckConfig> queryList(CheckConfig bo) {
    LambdaQueryWrapper<CheckConfig> lqw = buildQueryWrapper(bo);
     return checkConfigRepository.selectList(lqw);
     }

         /**
         * 新增对账核心配置
         */
         @Override
         public Boolean insertByBo(CheckConfig bo) {
         CheckConfig add = BeanUtil.toBean(bo, CheckConfig.class);
         validEntityBeforeSave(add);
         boolean flag = checkConfigRepository.insert(add) > 0;
         if (flag) {
         bo.setId(add.getId());
         }
         return flag;
         }

         /**
         * 修改对账核心配置
         */
         @Override
         public Boolean updateByBo(CheckConfig bo) {
         CheckConfig update = BeanUtil.toBean(bo, CheckConfig.class);
         validEntityBeforeSave(update);
         return checkConfigRepository.updateById(update) > 0;
         }

         /**
         * 保存前的数据校验
         */
         private void validEntityBeforeSave(CheckConfig entity) {
         //TODO 做一些数据校验,如唯一约束
         }

         /**
         * 批量删除对账核心配置
         */
         @Override
         public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
          if (isValid) {
          //TODO 做一些业务上的校验,判断是否需要校验
          }
          return checkConfigRepository.deleteBatchIds(ids) > 0;
          }

}
