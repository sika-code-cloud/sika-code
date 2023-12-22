package com.sika.code.demo.interfaces.business.user.controller;

import com.sika.code.core.result.Result;
import com.sika.code.demo.application.business.user.service.UserService;
import com.sika.code.demo.domain.business.order.repository.OrderRepository;
import com.sika.code.demo.infrastructure.business.user.pojo.dto.UserDTO;
import com.sika.code.demo.infrastructure.business.user.pojo.query.OrderQuery;
import com.sika.code.demo.infrastructure.business.user.pojo.query.UserQuery;
import com.sika.code.demo.interfaces.common.controller.BaseBizController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:53
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseBizController {

    @Resource
    private UserService userService;
    @Resource
    private OrderRepository orderRepository;

    @Resource
    private ThreadPoolExecutor messageConsumeDynamicExecutor;

    @Resource
    private ThreadPoolExecutor dtpExecutor1;

    @RequestMapping(value = "save")
    public Result save(@RequestBody UserDTO dto) {
        return success(userService.save(dto));
    }

    @RequestMapping(value = "saveBatch")
    public Result saveBatch(@RequestBody List<UserDTO> dtos) {
        return success(userService.saveBatch(dtos));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody UserQuery query) {
        return success(userService.page(query));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody UserQuery query) {
        return success(userService.find(query));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody UserQuery query) {
        return success(userService.list(query));
    }

    @RequestMapping(value = "orders")
    public Result list(@RequestBody OrderQuery query) {
        //        try {
        //            messageConsumeDynamicExecutor.execute(() -> {
        //                try {
        //                    TimeUnit.SECONDS.sleep(10L);
        //                } catch (InterruptedException e) {
        //                    throw new RuntimeException(e);
        //                }
        //            });
        //        } catch (Exception e) {
        //            log.error(e.getMessage(), e);
        //        }
        //        try {
        //            dtpExecutor1.execute(() -> {
        //                try {
        //                    TimeUnit.SECONDS.sleep(10L);
        //                } catch (InterruptedException e) {
        //                    throw new RuntimeException(e);
        //                }
        //            });
        //        } catch (Exception e) {
        //            log.error(e.getMessage(), e);
        //        }
//        try (HintManager hintManager = HintManager.getInstance()) {
//            hintManager.addDatabaseShardingValue("t_mch_order", 2021);
//            hintManager.addTableShardingValue("t_mch_order", 1027);
//            orderRepository.list(query);
//        }
        orderRepository.list(query);
        return success("success");
    }

    @RequestMapping(value = "readData")
    public Result readData(@RequestBody UserQuery query) {
        return success(userService.readData(query));
    }

    @RequestMapping(value = "writeData")
    public Result writeData(@RequestBody List<UserDTO> userDTOS) {
        userService.writeData(userDTOS);
        return success(new LinkedHashMap<>());
    }

}
