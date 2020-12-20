package com.coolweather.android.dbsupport;

import java.util.HashSet;
import java.util.Set;

public class SnowflakeIdWorkerV1 {
    /********************** Fields ***************************/
    /**
     * 开始时间截
     */
    private static final long START_STMP;

    static {
        // START_STMP是服务器第一次上线时间点, 设置后不允许修改
        START_STMP = 2096211200000L;
    }

    /**
     * 每一部分占用的位数
     */
    // 序列号占用的位数
    private final static long SEQUENCE_BITS = 12;
    // 数据中心占用的位数
    private final static long DATA_CENTER_BITS = 5;
    // 机器标识占用的位数
    private final static long MACHINE_BITS = 5;


    /**
     * 每一部分的最大值，这个移位算法，可以很快的计算出几位二进制数所能表示的最大十进制数
     */
    // 序列号最大值
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BITS);
    // 数据中心最大id
    private final static long MAX_DATA_CENTER_ID = -1L ^ (-1L << DATA_CENTER_BITS);
    // 机器标识最大id
    private final static long MAX_MACHINE_ID = -1L ^ (-1L << MACHINE_BITS);

    /**
     * 每一部分向左的位移
     */
    // 机器左移位数
    private final static long MACHINE_LEFT_SHIFT_BITS = SEQUENCE_BITS;
    // 数据中心左移位数
    private final static long DATA_CENTER_LEFT_SHIFT_BITS = SEQUENCE_BITS + MACHINE_BITS;
    // 时间戳左移位数
    private final static long TIMESTAMP_LEFT_SHIFT_BITS = SEQUENCE_BITS + DATA_CENTER_BITS + MACHINE_BITS;


    /**
     * 支持参数
     */
    // 数据中心
    private long dataCenterId;
    // 机器标识
    private long machineId;
    // 序列号
    private long sequence = 0L;
    // 上一次时间戳
    private long lastStamp = -1L;


    /********************** Constructors ***************************/

    public SnowflakeIdWorkerV1(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException("dataCenterId can't be greater than MAX_DATA_CENTER_ID or less than 0");
        }
        if (machineId > MAX_MACHINE_ID || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_ID or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }


    /********************** Methods ***************************/

    /**
     * 产生下一个ID
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        /** 获取当前时间 **/
        long currentStamp = getCurrentTime();

        /** 阻塞时间 **/
        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (currentStamp < lastStamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastStamp - currentStamp));
        }

        // 如果是同一时间生成的，则进行自增序列号
        if (lastStamp == currentStamp) {
            // 相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                // 阻塞到下一个毫秒，获得新的时间戳
                currentStamp = wait2NextTime();
            }
        }
        // 时间戳改变，重置序列号
        else {
            sequence = 0L;
        }

        // 上次生成ID的时间截
        lastStamp = currentStamp;

        /** 生成id **/
        // 移位并通过或运算拼到一起组成64位的ID
        // 时间戳部分
        long timestampShift = ((currentStamp - START_STMP) << TIMESTAMP_LEFT_SHIFT_BITS);
        // 数据中心部分
        long centerShift = dataCenterId << DATA_CENTER_LEFT_SHIFT_BITS;

        // 机器标识部分
        long machineShift = machineId << MACHINE_LEFT_SHIFT_BITS;
        // 序列号部分
        long sequenceShift = sequence;

        // 或逻辑，拼接id值
        long id = timestampShift | centerShift | machineShift | sequenceShift;
        return id;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @return 当前时间戳
     */
    private long wait2NextTime() {
        long timestamp = getCurrentTime();
        while (timestamp <= lastStamp) {
            timestamp = getCurrentTime();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    private long getCurrentTime() {
        return System.currentTimeMillis();
    }

}
