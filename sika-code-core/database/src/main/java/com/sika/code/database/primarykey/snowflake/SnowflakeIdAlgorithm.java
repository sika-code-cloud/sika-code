package com.sika.code.database.primarykey.snowflake;

import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.date.util.DateUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Twitter_Snowflake<br>
 * SnowFlake的结构如下(每部分用-分开):<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 -
 * 000000000000 <br>
 * twitter的雪花算法，是将id按二进制比特位切割，不同的位区间，表示不同的含义，也即是不同位区间的值生成方式不同，从而生成唯一的id。
 * 如位区间可分为时间位区间、集群位区间、机器位区间、自增位区间，这样可在不同时间内、不同集群、 不同机器间，生成全局唯一的id。
 * 在此以生成64位（即long型）为例进行介绍（其实区间位可以根据具体的业务需要自行指定）。 1、位区间化分
 * 最高位（即第64位，从右向左数）为符号位，不使用；
 * 41位（第23位到第63位）为时间位，可使用个数为2199023255551个，以毫秒为单位，大约69.5年
 * 4位（第19位到第22位）为集群位，可使用个数为16个； 4位（第15位到第18位）为机器位，可使用个数为16个；
 * 14位（第1位到第12位）为序列号位，即是从0开始自增，可使用个数为16384个
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，
 * SnowFlake每秒能够产生1630万ID左右。
 * <p>
 * 使用KeyGeneratorConfig配置类生成SnowflakeIdWorkerBO示例
 */
public class SnowflakeIdAlgorithm {
    /**
     * 雪花算法的实体容器
     */
    private static final Map<Class<?>, SnowflakeIdAlgorithm> SNOWFLAKEID_ALGORITHM_MAP = new ConcurrentHashMap<>();

    private SnowflakeIdAlgorithm() {

    }

    private SnowflakeIdAlgorithm(long workerId, long dataCenterId) {
        buidWorkerIdAndDatacenterId(workerId, dataCenterId);
    }

    /**
     * <p>
     * 每一个实体类一个雪花算法对象
     * </p>
     *
     * @param workerId
     * @param datacenterId
     * @param entityClass
     * @return SnowflakeIdAlgorithm
     * @author daiqi
     * @date 2019/3/20 16:46
     */
    public static final SnowflakeIdAlgorithm getSingleInstance(long workerId, long datacenterId, final Class<?> entityClass) {
        Class<?> snowflakeIdClass = entityClass;
        if (BaseUtil.isNull(snowflakeIdClass)) {
            snowflakeIdClass = Object.class;
        }
        SnowflakeIdAlgorithm snowflakeIdAlgorithm = SNOWFLAKEID_ALGORITHM_MAP.get(snowflakeIdClass);
        if (BaseUtil.isNull(snowflakeIdAlgorithm)) {
            SNOWFLAKEID_ALGORITHM_MAP.put(snowflakeIdClass, new SnowflakeIdAlgorithm(workerId, datacenterId));
        }
        return SNOWFLAKEID_ALGORITHM_MAP.get(snowflakeIdClass);
    }


    public static final SnowflakeIdAlgorithm getSingleInstance(Long workerId, Long datacenterId) {
        return getSingleInstance(workerId, datacenterId, Object.class);
    }

    // ==============================Fields===========================================
    /**
     * 开始时间截 (2015-01-01)
     */
    private final long twepoch = 1420041600000L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 4L;

    /**
     * 数据标识id所占的位数
     */
    private final long datacenterIdBits = 4L;

    /**
     * 支持的最大机器id，结果是16 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << (workerIdBits));

    /**
     * 支持的最大数据标识id，结果是16
     */
    private final long maxDatacenterId = -1L ^ (-1L << (datacenterIdBits));

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 14L;

    /**
     * 机器ID向左移14位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据标识id向左移18位(14+4)
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(3+3+16)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 生成序列的掩码，这里为2的14次方 (0b11111111111111=0xfff=16384)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~15)
     */
    private long workerId;

    /**
     * 数据中心ID(0~15)
     */
    private long dataCenterId;

    /**
     * 毫秒内序列(0~16383)
     */
    private volatile long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private volatile long lastTimestamp = -1L;

    // ==============================Constructors=====================================

    private SnowflakeIdAlgorithm buidWorkerIdAndDatacenterId(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = datacenterId;
        return this;
    }

    // ==============================Methods==========================================

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = DateUtil.current(false);
        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }
        // 上次生成ID的时间截
        lastTimestamp = timestamp;
        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift)
                | (dataCenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = DateUtil.current(false);
        while (timestamp <= lastTimestamp) {
            timestamp = DateUtil.current(false);
        }
        return timestamp;
    }

}
