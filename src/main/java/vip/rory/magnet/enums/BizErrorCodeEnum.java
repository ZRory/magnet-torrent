package vip.rory.magnet.enums;

/**
 * @author zhanghangtian
 * @date 2019年7月3日 下午2:55:35
 */
public enum BizErrorCodeEnum implements BizEnum {

    /**
     * 10000-19999 共通定义
     */
    SUCCESS(10000, "SUCCESS", "操作成功"),
    OPERATION_FAILED(10024, "OPERATION_FAILED", "操作失败"),

    MAGNET_ILLEGAL(20001, "MAGNET_ILLEGAL", "磁力链接非法"),
    MAGNET_DOWNLOAD_TIMEOUT(20002, "MAGNET_DOWNLOAD_TIMEOUT", "磁力链接获取种子超时"),

    End(99999, "End", "不需要执行");

    private int    code;
    private String name;
    private String desc;

    BizErrorCodeEnum(int code, String name, String desc) {

        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
